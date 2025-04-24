package taxify;
import taxify.Interfaces.ILocation;
import taxify.Interfaces.IService;
import taxify.Interfaces.ISoundMode;
import taxify.Interfaces.IUser;


/**
 * Represents a service requested by a user in the Taxify system.
 * It contains information about the user, pickup and dropoff locations,
 * the rating (stars), and the sound mode during the service.
 */
public class Service implements IService {
    private IUser user;
    private ILocation pickup;
    private ILocation dropoff;
    private int stars;
    private ISoundMode soundMode;
    
    /**
     * Constructs a Service object with the specified user, pickup location, dropoff location,
     * and sound mode.
     * 
     * @param user the user who requested the service
     * @param pickup the pickup location for the service
     * @param dropoff the dropoff location for the service
     * @param soundMode the sound mode for the service
     */
    public Service(IUser user, ILocation pickup, ILocation dropoff, ISoundMode soundMode) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff; 
        this.stars = 0;
        this.soundMode = soundMode;
    }
    
    /**
     * Returns the user who requested the service.
     * 
     * @return the user who requested the service
     */
    @Override
    public IUser getUser() {
        return this.user;
    }
    
    /**
     * Returns the pickup location for the service.
     * 
     * @return the pickup location
     */
    @Override
    public ILocation getPickupLocation() {
        return this.pickup;
    }
    
    /**
     * Returns the dropoff location for the service.
     * 
     * @return the dropoff location
     */
    @Override
    public ILocation getDropoffLocation() {
        return this.dropoff;
    }
    
    /**
     * Returns the rating (stars) of the service.
     * 
     * @return the stars rating for the service
     */
    @Override
    public int getStars() {
        return this.stars;
    }
    
    /**
     * Sets the rating (stars) for the service.
     * 
     * @param stars the rating to set for the service
     */
    @Override
    public void setStars(int stars) {
        this.stars = stars;
    }
    
    /**
     * Calculates the distance between the pickup and dropoff locations using
     * the Manhattan distance formula.
     * 
     * @return the calculated distance between pickup and dropoff locations
     */
    @Override
    public int calculateDistance() {
        return Math.abs(this.pickup.getX() - this.dropoff.getX()) + Math.abs(this.pickup.getY() - this.dropoff.getY());
    }
    
    /**
     * Returns a string representation of the service, showing the pickup and dropoff locations.
     * 
     * @return a string representing the service route (pickup to dropoff)
     */
    @Override
    public String toString() {
        return this.getPickupLocation().toString() + " to " + this.getDropoffLocation().toString();
    }

    /**
     * Returns the sound mode associated with the service.
     * 
     * @return the sound mode of the service
     */
    @Override
    public ISoundMode getSoundMode() {
        return soundMode;
    }



}