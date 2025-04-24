package taxify.Vehicles;

import taxify.Interfaces.ILocation;
import taxify.Vehicle;

/**
 * Represents a Taxi vehicle in the Taxify system.
 * This class extends the {@link Vehicle} class and implements specific behavior
 * for taxi vehicles, such as cost calculation and taxi status.
 */
public class Taxi extends Vehicle{

    /**
     * Constructor for the Taxi vehicle.
     * Initializes the taxi with a unique ID and a location.
     *
     * @param id       the unique identifier for the taxi
     * @param location the current location of the taxi
     */
    public Taxi(int id, ILocation location) {
        super(id,location);
    }

    /**
     * Calculates the cost of the service for the taxi.
     * The cost is based on the base cost calculated by the parent class,
     * with an additional multiplier of 2 for taxi vehicles.
     *
     * @return the calculated cost of the taxi service
     */
    @Override
    public double calculateCost() {
        return super.calculateCost()*2;
    }

    /**
     * Determines if the vehicle is a taxi.
     * Taxi vehicles are considered taxis.
     *
     * @return true as this vehicle is a taxi
     */
    @Override
    public boolean isTaxi() {
        return true;
    }
}
