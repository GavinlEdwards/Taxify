package taxify;

import java.time.LocalDate;

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
 * Abstract class representing an electric vehicle in the Taxify system.
 * Contains properties and methods that manage the vehicle's state, its interactions
 * with services, users, and its movement across locations.
 */
public abstract class EVehicle implements IVehicle {
    protected int id;
    protected ITaxiCompany company;
    protected IService service;
    protected VehicleStatus status;
    protected ILocation location;
    protected ILocation destination;
    protected IStatistics statistics;
    protected IRoute route;
    protected IDriver driver = new Driver(9999, "Non", "Driver", 'x',LocalDate.now(),999);
    protected ISoundMode soundMode = new StandardSound();
    protected boolean rideShare = false;
    protected IUser rideShareUser;
    protected IService rideShareService;
    protected boolean hasOccupant = false;
    protected int battery = 100;
    
    /**
     * Constructs an electric vehicle with a unique ID and initial location.
     * Initializes the vehicle's state, service, route, and statistics.
     *
     * @param id the unique identifier for the vehicle
     * @param location the current location of the vehicle
     */
    public EVehicle(int id, ILocation location) {        
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;        
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = new Route(this.location, this.destination);
    }

    /**
     * {@inheritDoc}
     * Returns the unique identifier of the vehicle.
     *
     * @return the vehicle's ID
     */
    @Override
    public int getId() {
        // return id
        return this.id;
    }
    
    /**
     * {@inheritDoc}
     * Returns the current location of the vehicle.
     *
     * @return the vehicle's location
     */
    @Override
    public ILocation getLocation() {
        // return location
        return this.location;
    }

    /**
     * {@inheritDoc}
     * Returns the destination location of the vehicle.
     *
     * @return the vehicle's destination
     */
    @Override
    public ILocation getDestination() {
        // return destination
        return this.destination;
    }
    
    /**
     * {@inheritDoc}
     * Returns the current service associated with the vehicle.
     *
     * @return the current service
     */
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
    
    /**
     * {@inheritDoc}
     * Returns the statistics of the vehicle, including distance traveled, services performed, and billing.
     *
     * @return the vehicle's statistics
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
    
    /**
     * {@inheritDoc}
     * Picks up a service and changes the vehicle's status to "booked".
     *
     * @param service the service to be picked up
     */
    @Override
    public void pickService(IService service) {
        // pick a service and set status to "booked"
        
        this.service = service;        
        this.status = VehicleStatus.BOOKED;
        
    }

    /**
     * {@inheritDoc}
     * Starts the service by setting the destination to the service's drop-off location and updating the vehicle's status.
     */

    @Override
    public void startService() {
        // set destination to the service drop-off location, and status to "service"
        
        this.destination = service.getDropoffLocation();
        this.route = new Route(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    }

    /**
     * {@inheritDoc}
     * Ends the service, updates statistics, and sets the vehicle's status to "free".
     */
    @Override
    public void endService() {
        // update vehicle statistics
        
        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();
        
        // if the service is rated by the user, update statistics
        
        
        // set service to null, and status to "free"
        
        this.service = null;
        
        this.status = VehicleStatus.FREE;
    }

    /**
     * {@inheritDoc}
     * Starts a ride share, but this method is not used by electric vehicles.
     */
    @Override
    public void startRideShare(IUser user, IService service) {
        // not used by evehicles
        System.out.println("ride share failed");
    }

    /**
     * {@inheritDoc}
     * Ends the ride share, but this method is not used by electric vehicles.
     */
    @Override
    public void endRideShare() {
        // not used by evehicles
        System.out.println("failed");

    }

    /**
     * {@inheritDoc}
     * Notifies the company that the vehicle has arrived at the pickup location and starts the service.
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the service
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    }
    
    /**
     * {@inheritDoc}
     * Notifies the company that the vehicle has arrived at the drop-off location and ends the service.
     */
    @Override
    public void notifyArrivalAtDropoffLocation() {
        // notify the company that the vehicle is at the drop off location and end the service
        this.company.arrivedAtDropoffLocation(this);
        this.endService();
     }
    
    /**
     * {@inheritDoc}
     * Returns true if the vehicle is free, otherwise returns false.
     *
     * @return true if the vehicle is free, false otherwise
     */
    @Override
    public boolean isFree() {
        // returns true if the status of the vehicle is "free" and false otherwise
        if (this.status == VehicleStatus.FREE) {
            return true;
        } else {
            return false;
        }

    }   
    
    /**
     * {@inheritDoc}
     * Moves the vehicle to its next location. If the vehicle is free, it may charge at a station.
     * If the vehicle is booked, there is a chance it will start the service.
     * If the vehicle is in service, it will move toward the destination.
     */
    @Override
    public void move() {
        // if vehicle is free its at a charging station and has a chance to charge
        if(this.status == VehicleStatus.FREE) {
            if(this.battery != 100 && Math.random() > .5) {
                this.setBattery(25);
            }
        } else if(this.status == VehicleStatus.BOOKED) {
        // if vehicle is booked chance of user arriving and starting service
            if (Math.random() > .75) {
                this.startService();
                this.status = VehicleStatus.SERVICE;
            }
        } else {
            //move to next location
            this.location = this.route.getNextLocation();
            //if no location in route arrived at destination
            if(!this.route.hasLocations()) {
                notifyArrivalAtDropoffLocation();
                this.setBattery(-25);
            }
        }
    }

    /**
     * {@inheritDoc}
     * Calculates the cost of the service based on the distance.
     *
     * @return the calculated cost
     */
    @Override
    public double calculateCost() {
        return this.service.calculateDistance();
    }

    /**
     * {@inheritDoc}
     * Returns a string representation of the vehicle, including its current location and status.
     *
     * @return a string representation of the vehicle
     */
    @Override
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
               ((this.status == VehicleStatus.FREE) ? " is free at location " + this.location.toString(): ((this.status == VehicleStatus.BOOKED) ?
               " waiting for user " + this.service.getUser().getId() : " in service "));
    }    

    /**
     * {@inheritDoc}
     * Returns the driver of the vehicle.
     *
     * @return the driver
     */
    @Override
    public IDriver getDriver() {
        return driver;
    }

    /**
     * {@inheritDoc}
     * Sets the driver of the vehicle. This method is not used by electric vehicles.
     *
     * @param driver the driver to set
     */
    @Override
    public void setDriver(IDriver driver) {
        // not used by EVehicles
    }
    
    /**
     * {@inheritDoc}
     * Returns true if the vehicle has an occupant, false otherwise.
     *
     * @return true if the vehicle has an occupant, false otherwise
     */
    @Override
    public boolean hasOccupant() {
        return this.hasOccupant;
    }

    /**
     * {@inheritDoc}
     * Sets the occupant status of the vehicle.
     *
     * @param bool the occupant status to set
     */
    @Override
    public void setOccupantStatus(boolean bool) {
        this.hasOccupant = bool;
    }

    /**
     * Sets the battery level of the vehicle. Positive values increase the battery, negative values decrease it.
     *
     * @param battery the amount to change the battery level by
     */
    public void setBattery(int battery) {
        this.battery = this.battery + battery;
    }

    /**
     * Checks if the vehicle's battery is fully charged.
     *
     * @return true if the battery is fully charged, false otherwise
     */
    public boolean isCharged() {
        if(battery == 100) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the current battery level of the vehicle.
     *
     * @return the battery level as a percentage
     */
    public int getBattery() {
        return battery;
    }
}