package taxify.Interfaces;


/**
 * Interface representing a vehicle in the Taxify system.
 * A vehicle can move, pick up and drop off services, track its statistics,
 * and handle ride-sharing functionality.
 */
public interface IVehicle extends IMovable {

    /**
     * Gets the unique identifier of the vehicle.
     *
     * @return the vehicle's ID
     */
    public int getId();

    /**
     * Gets the current location of the vehicle.
     *
     * @return the current {@link ILocation} of the vehicle
     */
    public ILocation getLocation();

    /**
     * Gets the destination location of the vehicle.
     *
     * @return the destination {@link ILocation} of the vehicle
     */
    public ILocation getDestination();

    /**
     * Gets the current service that the vehicle is providing.
     *
     * @return the {@link IService} being provided by the vehicle
     */
    public IService getService();

    /**
     * Gets the statistics associated with the vehicle.
     *
     * @return the {@link IStatistics} for the vehicle
     */
    public IStatistics getStatistics();

    /**
     * Sets the taxi company to which the vehicle belongs.
     *
     * @param company the {@link ITaxiCompany} to assign to the vehicle
     */
    public void setCompany(ITaxiCompany company);

    /**
     * Assigns a service to the vehicle.
     *
     * @param service the {@link IService} to be assigned to the vehicle
     */
    public void pickService(IService service);

    /**
     * Starts the service (ride) for the vehicle.
     */
    public void startService();

    /**
     * Ends the service (ride) for the vehicle.
     */
    public void endService();

    /**
     * Notifies that the vehicle has arrived at the pickup location.
     */
    public void notifyArrivalAtPickupLocation();

    /**
     * Notifies that the vehicle has arrived at the dropoff location.
     */
    public void notifyArrivalAtDropoffLocation();

    /**
     * Checks if the vehicle is free (not currently occupied by a service).
     *
     * @return true if the vehicle is free, false if it is occupied
     */
    public boolean isFree();

    /**
     * Calculates the cost of the current service for the vehicle.
     *
     * @return the calculated cost of the service
     */
    public double calculateCost();

    /**
     * Returns a string representation of the vehicle.
     * Typically includes vehicle details like ID, location, and status.
     *
     * @return a string describing the vehicle
     */
    @Override
    public String toString();

    /**
     * Gets the driver of the vehicle.
     *
     * @return the {@link IDriver} driving the vehicle
     */
    public IDriver getDriver();

    /**
     * Sets the driver for the vehicle.
     *
     * @param driver the {@link IDriver} to assign to the vehicle
     */
    public void setDriver(IDriver driver);

    /**
     * Starts a ride-sharing service for the vehicle.
     * This may involve adding users to a shared ride and adjusting service details.
     *
     * @param user    the {@link IUser} requesting to share the ride
     * @param service the {@link IService} for the shared ride
     */
    public void startRideShare(IUser user, IService service);

    /**
     * Ends the ride-sharing service for the vehicle.
     * This may involve updating service details and freeing the vehicle.
     */
    public void endRideShare();

    /**
     * Checks if the vehicle is a taxi.
     *
     * @return true if the vehicle is a taxi, false otherwise
     */
    public boolean isTaxi();

    /**
     * Checks if the vehicle has an occupant (either a driver or passenger).
     *
     * @return true if the vehicle has an occupant, false otherwise
     */
    public boolean hasOccupant();

    /**
     * Sets the occupant status of the vehicle.
     * This determines whether the vehicle is occupied or free.
     *
     * @param bool the new occupant status (true for occupied, false for free)
     */
    public void setOccupantStatus(boolean bool);
}