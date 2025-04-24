package taxify.Vehicles;

import taxify.Interfaces.ILocation;
import taxify.Vehicle;

/**
 * Represents a Shuttle vehicle in the Taxify system.
 * This class extends the {@link Vehicle} class and implements specific behavior
 * for shuttle vehicles, such as cost calculation and taxi status.
 */
public class Shuttle extends Vehicle{

    /**
     * Constructor for the Shuttle vehicle.
     * Initializes the shuttle with a unique ID and a location.
     *
     * @param id       the unique identifier for the shuttle
     * @param location the current location of the shuttle
     */
    public Shuttle(int id, ILocation location) {
        super(id,location);
    }

    /**
     * Calculates the cost of the service for the shuttle.
     * The cost is based on the base cost calculated by the parent class,
     * with an additional multiplier of 1.5 for shuttle vehicles.
     *
     * @return the calculated cost of the shuttle service
     */
    @Override
    public double calculateCost() {
        return super.calculateCost()*1.5;
    }

    /**
     * Determines if the vehicle is a taxi.
     * Shuttle vehicles are not considered taxis.
     *
     * @return false as shuttle vehicles are not taxis
     */
    @Override
    public boolean isTaxi() {
        return false;
    }
}
