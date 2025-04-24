package taxify.Interfaces;

import java.util.List;

/**
 * Interface for finding available (free) vehicles in the Taxify system.
 * Implementing classes will define how to search for free vehicles 
 * from a list of available vehicles.
 */
public interface IVehicleFinder {

    /**
     * Finds a free vehicle from a list of vehicles.
     * This method will return the index or ID of the first available vehicle.
     *
     * @param vehicles a list of {@link IVehicle} to search through
     * @return the index or ID of the first free vehicle, or -1 if no vehicle is available
     */
    int findFreeVehicle(List<IVehicle> vehicles);

    /**
     * Returns a string representation of the vehicle finder.
     * This could include details about the search criteria or strategy.
     *
     * @return a string representation of the vehicle finder
     */
    @Override
    String toString();
}