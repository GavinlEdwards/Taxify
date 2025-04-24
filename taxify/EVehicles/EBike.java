package taxify.EVehicles;

import taxify.EVehicle;
import taxify.Interfaces.ILocation;
/**
 * Represents an electric bike (EBike) in the Taxify system.
 * Extends the {@link EVehicle} class and implements specific logic for EBikes.
 */
public class EBike extends EVehicle {

     /**
     * Constructs an EBike with a specified ID and initial location.
     *
     * @param id       the unique identifier for the EBike
     * @param location the initial location of the EBike
     */
    public EBike(int id, ILocation location) {
        super(id, location);
        
    }

    /**
     * Indicates whether this vehicle is a taxi.
     *
     * @return false, since an EBike is not a taxi
     */
    @Override
    public boolean isTaxi() {
        return false;

    }

     /**
     * Calculates the cost of using the EBike.
     * The cost is computed as 25% of the base vehicle cost plus 1.
     *
     * @return the calculated cost of the EBike ride
     */
    @Override 
    public double calculateCost() {
        return super.calculateCost()*.25+1;
    }

    
    /**
     * Determines whether the EBike has sufficient charge.
     * An EBike is considered charged if its battery level is above 25%.
     *
     * @return true if the battery level is above 25, false otherwise
     */
    @Override
    public boolean isCharged() {
        if (this.battery > 25) {
            return true;
        }
        return false;
    }
}
