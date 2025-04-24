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

/**
 * The Vehicle class represents a vehicle in the Taxify system. It handles vehicle-specific information 
 * such as location, service status, route, and the associated driver. The class provides methods for 
 * starting and ending services, calculating ride costs, and managing ride-sharing functionality.
 */
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

    /**
     * Constructs a Vehicle object with a unique ID and initial location.
     * 
     * @param id the unique identifier for the vehicle
     * @param location the initial location of the vehicle
     */
    public Vehicle(int id, ILocation location) {        
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = new Route(this.location, this.destination);
    }

    /**
     * Gets the unique ID of the vehicle.
     * 
     * @return the vehicle's ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Gets the current location of the vehicle.
     * 
     * @return the vehicle's location
     */
    @Override
    public ILocation getLocation() {
        return this.location;
    }

    /**
     * Gets the destination of the vehicle.
     * 
     * @return the vehicle's destination
     */
    @Override
    public ILocation getDestination() {
        return this.destination;
    }

    /**
     * Gets the service currently assigned to the vehicle.
     * 
     * @return the vehicle's service
     */
    @Override
    public IService getService() {
        return this.service;
    }

    /**
     * Gets the statistics associated with the vehicle.
     * 
     * @return the vehicle's statistics
     */
    @Override
    public IStatistics getStatistics() {
        return this.statistics;
    }

    /**
     * Sets the taxi company that owns the vehicle.
     * 
     * @param company the company to set
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    /**
     * Assigns a service to the vehicle and sets up the route to the pickup location.
     * 
     * @param service the service to pick up
     */
    @Override
    public void pickService(IService service) {
        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = new Route(this.location, this.destination);        
        this.status = VehicleStatus.PICKUP;
        this.soundMode = service.getSoundMode();
    }

    /**
     * Starts the service by setting the destination to the drop-off location and updating the route.
     */
    @Override
    public void startService() {
        this.destination = service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    }

    /**
     * Ends the service by updating statistics and resetting the vehicle's state.
     */
    @Override
    public void endService() {
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();

        if (this.service.getStars() != 0) {
            this.driver.giveRating(this.service.getStars());
            this.statistics.updateStars(this.service.getStars());
            this.statistics.updateReviews();
        }

        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.FREE;
    }

    /**
     * Starts a ride-sharing service with a user, setting the drop-off location and updating the route.
     * 
     * @param user the user requesting the ride-share
     * @param service the service associated with the ride-share
     */
    @Override
    public void startRideShare(IUser user, IService service) {
        System.out.println("Ride share started with vehicle " + this.id + " and user " + user.getId());
        this.rideShareUser = user;
        this.rideShareService = service;
        this.destination = this.rideShareService.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.rideShare = true;
        System.out.println("Ride Share User id is " + rideShareUser.getId());
        rideShareUser.setService(true);
    }

    /**
     * Ends the ride-sharing service and updates statistics, including ratings.
     */
    @Override
    public void endRideShare() {
        this.company.arrivedAtDropoffLocationRideShare(this, rideShareService);
        this.destination = this.service.getDropoffLocation();
        ILocation tempDropoffLocation = this.rideShareService.getDropoffLocation();

        IService temp = this.service;
        this.service = rideShareService;
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();
        this.service = temp;

        if (rideShareService.getStars() != 0) {
            this.driver.giveRating(this.rideShareService.getStars());
            this.statistics.updateStars(this.rideShareService.getStars());
            this.statistics.updateReviews();
        }

        if (tempDropoffLocation == this.destination) {
            notifyArrivalAtDropoffLocation();
        } else {
            this.route = new Route(this.location, this.destination);
        }

        rideShare = false;
    }

    /**
     * Notifies the taxi company that the vehicle has arrived at the pickup location.
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }

    /**
     * Notifies the taxi company that the vehicle has arrived at the drop-off location and ends the service.
     */
    @Override
    public void notifyArrivalAtDropoffLocation() {
        this.company.arrivedAtDropoffLocation(this);
        this.endService();
    }

    /**
     * Checks if the vehicle is free (not currently in use).
     * 
     * @return true if the vehicle is free, false otherwise
     */
    @Override
    public boolean isFree() {
        return this.status == VehicleStatus.FREE;
    }

    /**
     * Moves the vehicle one step along the route and handles various events like reaching the pickup or drop-off location.
     */
    @Override
    public void move() {
        this.location = this.route.getNextLocation();

        if (!this.route.hasLocations()) {
            if (this.service == null) {
                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = new Route(this.location, this.destination);
            } else {
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

    /**
     * Calculates the cost of the current service, considering factors such as ride-sharing and sound mode.
     * 
     * @return the calculated cost for the service
     */
    @Override
    public double calculateCost() {
        if (rideShare) {
            return this.service.calculateDistance() * this.service.getSoundMode().getMultiplier() * 0.75;
        }
        return this.service.calculateDistance() * this.service.getSoundMode().getMultiplier();
    }

    /**
     * Provides a string representation of the vehicle's current status, location, and route.
     * 
     * @return a string representation of the vehicle
     */
    @Override
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
               ((this.status == VehicleStatus.FREE) ? " is free with path " + this.route.toString() : ((this.status == VehicleStatus.PICKUP) ?
               " to pickup user " + this.service.getUser().getId() : " in service "));
    }

    /**
     * Gets the driver of the vehicle.
     * 
     * @return the driver of the vehicle
     */
    @Override
    public IDriver getDriver() {
        return driver;
    }

    /**
     * Sets the driver of the vehicle.
     * 
     * @param driver the driver to set
     */
    @Override
    public void setDriver(IDriver driver) {
        this.driver = driver;
    }

    /**
     * Checks if the vehicle currently has an occupant (a user in the vehicle).
     * 
     * @return true if the vehicle has an occupant, false otherwise
     */
    @Override
    public boolean hasOccupant() {
        return this.hasOccupant;
    }

    /**
     * Sets the occupant status of the vehicle (whether it has an occupant or not).
     * 
     * @param bool true if the vehicle has an occupant, false otherwise
     */
    @Override
    public void setOccupantStatus(boolean bool) {
        this.hasOccupant = bool;
    }
}