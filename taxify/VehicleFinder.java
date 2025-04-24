package taxify;

import java.util.List;

import taxify.Interfaces.IVehicle;
import taxify.Interfaces.IVehicleFinder;

/**
 * A class responsible for finding a free vehicle in the Taxify system.
 * It implements the IVehicleFinder interface to search through a list of vehicles 
 * and return the index of a free vehicle.
 */
public class VehicleFinder implements IVehicleFinder {

    /**
     * Finds a free vehicle from the given list of vehicles.
     * The method randomly selects a vehicle from the list and checks if it is free
     * and an instance of the `Vehicle` class. If a free vehicle is found, its index is returned.
     * 
     * @param vehicles The list of available vehicles.
     * @return The index of the first free vehicle, or -1 if no free vehicle is found within a reasonable number of attempts.
     */
    @Override
    public int findFreeVehicle(List<IVehicle> vehicles) {
        int val = (int) (Math.random() * vehicles.size());
        int max = vehicles.size() * 2;
        int count = 0;
        while (!vehicles.get(val).isFree() || !(vehicles.get(val) instanceof Vehicle)) {
            val = (val + 1) % vehicles.size();
            count += 1;
            if (count >= max) {
                return -1;
            }
        }
        return val;
    }

    /**
     * Returns the type of ride provided by this vehicle finder.
     * 
     * @return The string "Standard ride" representing the ride type.
     */
    @Override
    public String toString() {
        return "Standard ride";
    }
}