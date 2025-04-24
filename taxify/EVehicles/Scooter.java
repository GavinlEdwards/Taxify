package taxify.EVehicles;

import taxify.EVehicle;
import taxify.Interfaces.ILocation;

/**
 * Represents a Scooter in the Taxify system.
 * Inherits from the {@link EVehicle} class and provides Scooter-specific behavior.
 */
public class Scooter extends EVehicle{

    /**
     * Constructs a Scooter with the specified ID and location.
     *
     * @param id       the unique identifier for the Scooter
     * @param location the initial location of the Scooter
     */
    public Scooter(int id, ILocation location) {
        super(id, location);
        
    }

    /**
     * Indicates whether this vehicle is a taxi.
     *
     * @return false, since a Scooter is not a taxi
     */
    @Override
    public boolean isTaxi() {
        return false;
    }

     /**
     * Calculates the cost of using the Scooter.
     * The cost is calculated as 15% of the base vehicle cost plus 1.
     *
     * @return the calculated cost of the Scooter ride
     */
    @Override
    public double calculateCost() {
        return super.calculateCost()*.15+1;
    }

      /**
     * Determines whether the Scooter is fully charged.
     * A Scooter is considered charged only when its battery level is 100%.
     *
     * @return true if the battery level is exactly 100, false otherwise
     */
    @Override
    public boolean isCharged() {
        if(this.battery == 100) {
            return true;
        }
        return false;
    }
}
