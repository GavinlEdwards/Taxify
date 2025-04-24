package taxify.Interfaces;

/**
 * Interface representing a taxi company in the Taxify system.
 * Defines methods for managing services, vehicle arrivals, and handling ride-sharing.
 */
public interface ITaxiCompany {

    /**
     * Gets the name of the taxi company.
     *
     * @return the name of the taxi company
     */
    public String getName();
    /**
     * Gets the total number of services provided by the taxi company.
     *
     * @return the total number of services
     */    
    public int getTotalServices();
    
    /**
     * Provides a service to a user by finding an available vehicle.
     * 
     * @param user   the user requesting the service
     * @param finder the vehicle finder used to locate a suitable vehicle
     * @return true if the service is successfully provided, false otherwise
     */
    public boolean provideService(int user, IVehicleFinder finder);

    /**
     * Notifies that a vehicle has arrived at the pickup location.
     *
     * @param vehicle the vehicle that has arrived at the pickup location
     */
    public void arrivedAtPickupLocation(IVehicle vehicle);

    /**
     * Notifies that a vehicle has arrived at the dropoff location.
     *
     * @param vehicle the vehicle that has arrived at the dropoff location
     */
    public void arrivedAtDropoffLocation(IVehicle vehicle);

    /**
     * Notifies that a vehicle has arrived at the dropoff location for a ride-sharing service.
     *
     * @param vehicle           the vehicle that has arrived at the dropoff location
     * @param rideShareService the ride-sharing service associated with the vehicle
     */
    public void arrivedAtDropoffLocationRideShare(IVehicle vehicle, IService rideShareService);

}
