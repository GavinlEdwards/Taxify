package taxify;
import taxify.Interfaces.IStatistics;

/**
 * A class that implements the IStatistics interface and tracks the statistics 
 * for a vehicle or service, including the number of services, ratings, stars, 
 * distance traveled, and billing.
 */
public class Statistics implements IStatistics {
    private int services;
    private int ratings;
    private int stars;
    private int distance;
    private double billing;
    
    /**
     * Constructor to initialize all statistics fields to their default values.
     */
    public Statistics() {
        this.services = 0;
        this.ratings = 0;
        this.stars = 0;
        this.distance = 0;
        this.billing = 0;
    }
    
    /**
     * Returns the total number of services performed.
     * 
     * @return the number of services
     */
    @Override
    public int getServices() {
        return this.services;
    }
    
    /**
     * Returns the total number of reviews/ratings given.
     * 
     * @return the number of reviews
     */
    @Override
    public int getReviews() {
        return this.ratings;
    }
    
    /**
     * Returns the average rating based on the total stars and reviews.
     * The rating is rounded to two decimal places.
     * 
     * @return the average rating (stars per review)
     */
    @Override
    public double getStars() {
        double stars = (double) this.stars / (double) this.ratings;
        
        return Math.round(stars*100.0)/100.0;
    }
    
    /**
     * Returns the total distance traveled by the vehicle.
     * 
     * @return the total distance in some unit
     */
    @Override
    public int getDistance() {
        return this.distance;
    }
    
    /**
     * Returns the total billing amount.
     * 
     * @return the total billing amount
     */
    @Override
    public double getBilling() {
        return this.billing;
    }
    
    /**
     * Increments the service count by 1.
     */
    @Override
    public void updateServices() {
        this.services++;
    }
    
    /**
     * Increments the review count by 1.
     */
    @Override
    public void updateReviews() {
        this.ratings++;
    }
    
     /**
     * Adds the given number of stars to the total star count.
     * 
     * @param stars the number of stars to add
     */
    @Override
    public void updateStars(int stars) {
        this.stars = this.stars + stars;
    }
    
    /**
     * Adds the given distance to the total distance traveled.
     * 
     * @param distance the distance to add
     */
    @Override
    public void updateDistance(int distance) {
        this.distance = this.distance + distance;
    }
    
    /**
     * Adds the given billing amount to the total billing.
     * 
     * @param billing the amount to add to the billing
     */
    @Override
    public void updateBilling(double billing) {
        this.billing = this.billing + billing;
    }    
}
