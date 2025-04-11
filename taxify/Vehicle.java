package taxify;

import taxify.Interfaces.IDriver;
import taxify.Interfaces.ILocation;
import taxify.Interfaces.IRoute;
import taxify.Interfaces.IService;
import taxify.Interfaces.ISoundMode;
import taxify.Interfaces.IStatistics;
import taxify.Interfaces.ITaxiCompany;
import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicle;

public abstract class Vehicle implements IVehicle {
    protected int id;
    protected ITaxiCompany company;
    protected IService service;
    protected VehicleStatus status;
    protected ILocation location;
    protected ILocation destination;
    protected IStatistics statistics;
    protected IRoute route;
    protected IDriver driver;
    protected ISoundMode soundMode = new StandardSound();
    protected boolean rideShare = false;
    protected IUser rideShareUser;
    protected IService rideShareService;
    protected boolean hasOccupant = false;


    public Vehicle(int id, ILocation location) {        
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = new Route(this.location, this.destination);
    }

    @Override
    public int getId() {
        // return id
        return this.id;
    }
 
    @Override
    public ILocation getLocation() {
        // return location
        return this.location;
    }

    @Override
    public ILocation getDestination() {
        // return destination
        return this.destination;
    }
    
    @Override
    public IService getService() {
        // return service
        return this.service;
    }
    
    @Override
    public IStatistics getStatistics() {
        // return statistics
        return this.statistics;
    }
    
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"
        
        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = new Route(this.location, this.destination);        
        this.status = VehicleStatus.PICKUP;
        this.soundMode = service.getSoundMode();
    }

    @Override
    public void startService() {
        // set destination to the service drop-off location, and status to "service"
        
        this.destination = service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    }

    @Override
    public void endService() {
        // update vehicle statistics
        
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();
        
        // if the service is rated by the user, update statistics
        
        if (this.service.getStars() != 0) {
            this.driver.giveRating(this.service.getStars());
            this.statistics.updateStars(this.service.getStars());
            this.statistics.updateReviews();
        }
        
        // set service to null, and status to "free"
        
        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.FREE;
    }

    @Override
    public void startRideShare(IUser user, IService service) {
        System.out.println("ride share started with vehicle" + this.id + " and user " + user.getId());
        this.rideShareUser = user;
        this.rideShareService = service;
        this.destination = this.rideShareService.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        System.out.println(this.route.toString() + this.destination.toString());
        this.rideShare = true;
        System.out.println("rideshareuser id is" + rideShareUser.getId());
        rideShareUser.setService(true);
    }

    @Override
    public void endRideShare() {
        
        System.out.println("worked");


        this.company.arrivedAtDropoffLocationRideShare(this, rideShareService);
        this.destination = this.service.getDropoffLocation();
        if(this.rideShareService.getDropoffLocation() == this.destination) {
            notifyArrivalAtDropoffLocation();
        } else {
            this.route = new Route(this.location, this.destination);
        }
        
        rideShare = false;

        IService temp = this.service;
        this.service = rideShareService;
        this.statistics.updateBilling(this.calculateCost());
        this.service = temp;
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();

        if (rideShareService.getStars() != 0) {
            this.driver.giveRating(this.rideShareService.getStars());
            this.statistics.updateStars(this.rideShareService.getStars());
            this.statistics.updateReviews();
        }

    }

    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the service
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }
        
    @Override
    public void notifyArrivalAtDropoffLocation() {
        // notify the company that the vehicle is at the drop off location and end the service
        this.company.arrivedAtDropoffLocation(this);
        this.endService();
     }
        
    @Override
    public boolean isFree() {
        // returns true if the status of the vehicle is "free" and false otherwise
        if (this.status == VehicleStatus.FREE) {
            return true;
        } else {
            return false;
        }

    }   
    
    @Override
    public void move() {
        // get the next location from the driving route
        
        this.location = this.route.getNextLocation();        
        if(rideShare) {
            System.out.println(this.location + rideShareService.getDropoffLocation().toString());
        }
        // if the route has more locations the vehicle continues its route, otherwise the vehicle has arrived to a pickup or drop off location
        
        if (!this.route.hasLocations()) {
            if (this.service == null) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = new Route(this.location, this.destination);
            }
            else {
                // check if the vehicle has arrived to a pickup or drop off location

                ILocation origin = this.service.getPickupLocation();
                ILocation destination = this.service.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (rideShare && this.location.getX() == rideShareService.getDropoffLocation().getX() && this.location.getY() == rideShareService.getDropoffLocation().getY()) {

                    this.endRideShare();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();

                }     
            }
        }
    }

    @Override
    public double calculateCost() {
        return this.service.calculateDistance()*this.service.getSoundMode().getMultiplier();
    }

    @Override
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
               ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString(): ((this.status == VehicleStatus.PICKUP) ?
               " to pickup user " + this.service.getUser().getId() : " in service "));
    }    

    @Override
    public IDriver getDriver() {
        return driver;
    }

    @Override
    public void setDriver(IDriver driver) {
        this.driver = driver;
    }

    @Override
    public boolean hasOccupant() {
        return this.hasOccupant;
    }

    @Override
    public void setOccupantStatus(boolean bool) {
        this.hasOccupant = bool;
    }
}