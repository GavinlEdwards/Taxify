package taxify.Interfaces;

/**
 * Interface representing a service (such as a ride) in the Taxify system.
 * Defines methods to access service details such as the user, locations,
 * ratings, and sound mode, as well as calculating distance.
 */
public interface IService {

    /**
     * Gets the user who requested the service.
     *
     * @return the {@link IUser} associated with the service
     */
    public IUser getUser();

    /**
     * Gets the pickup location for the service.
     *
     * @return the {@link ILocation} where the user is picked up
     */
    public ILocation getPickupLocation();

    /**
     * Gets the dropoff location for the service.
     *
     * @return the {@link ILocation} where the user is dropped off
     */
    public ILocation getDropoffLocation();

    /**
     * Gets the number of stars (rating) given for the service.
     *
     * @return the star rating for the service
     */
    public int getStars();

    /**
     * Sets the number of stars (rating) for the service.
     *
     * @param stars the star rating to be set
     */
    public void setStars(int stars);

    /**
     * Calculates the distance between the pickup and dropoff locations.
     *
     * @return the distance of the service in appropriate units
     */
    public int calculateDistance();
    
    /**
     * Returns a string representation of the service.
     * Typically includes user and location details.
     *
     * @return a string describing the service
     */
    @Override
    public String toString();

    /**
     * Gets the sound mode used during the service.
     * This refers to in-ride audio experience preferences.
     *
     * @return the {@link ISoundMode} associated with the service
     */
    public ISoundMode getSoundMode();
}
