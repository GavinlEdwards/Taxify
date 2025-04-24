package taxify.Interfaces;

/**
 * Interface for handling statistical data in the Taxify system.
 * This interface provides methods for accessing and updating statistics 
 * such as services, reviews, ratings, distance traveled, and billing.
 */
public interface IStatistics {
    
    /**
     * Gets the total number of services provided.
     *
     * @return the number of services
     */
    public int getServices();

    /**
     * Gets the total number of reviews received.
     *
     * @return the number of reviews
     */
    public int getReviews();

    /**
     * Gets the average rating (in stars) for the services.
     *
     * @return the average rating
     */
    public double getStars();

    /**
     * Gets the total distance traveled in the services.
     *
     * @return the total distance traveled, in appropriate units
     */
    public int getDistance();

    /**
     * Gets the total billing amount for the services.
     *
     * @return the total billing amount
     */
    public double getBilling();

    /**
     * Updates the number of services provided.
     */
    public void updateServices();

    /**
     * Updates the number of reviews received.
     */
    public void updateReviews();

    /**
     * Updates the average rating with a new star rating.
     *
     * @param stars the new rating to be added
     */
    public void updateStars(int stars);

    /**
     * Updates the total distance traveled.
     *
     * @param distance the additional distance to be added
     */
    public void updateDistance(int distance);

    /**
     * Updates the total billing amount.
     *
     * @param billing the amount to be added to the total billing
     */
    public void updateBilling(double billing);
}