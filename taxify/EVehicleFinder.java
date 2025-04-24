package taxify;

import java.util.ArrayList;
import java.util.List;

import taxify.Interfaces.IVehicle;
import taxify.Interfaces.IVehicleFinder;

/**
 * Implementation of the IVehicleFinder interface, designed to find a free electric vehicle 
 * from a list of vehicles based on the availability and charging status.
 */
public class EVehicleFinder implements IVehicleFinder {

    /**
     * {@inheritDoc}
     * Finds a free electric vehicle from the given list of vehicles.
     * The vehicle must be an instance of EVehicle, be marked as free, and be fully charged.
     *
     * @param vehicles the list of vehicles to search through
     * @return the index of the first free and charged electric vehicle, or -1 if no such vehicle is found
     */
    @Override
    public int findFreeVehicle(List<IVehicle> vehicles) {
        List<IVehicle> eVehicles = new ArrayList<>();
        for(IVehicle vehicle: vehicles) {
            if(vehicle instanceof EVehicle) {
                eVehicles.add(vehicle);
            }        
        }
        int val = (int) (Math.random()*eVehicles.size());
        while(!eVehicles.get(val).isFree() || !((EVehicle)eVehicles.get(val)).isCharged()) {
            val = (val+1) % eVehicles.size();
        }
        for(int i = 0; i<vehicles.size(); i++) {
            if(vehicles.get(i) == eVehicles.get(val)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * Returns a string representation of the vehicle finder, identifying it as an electric vehicle finder.
     *
     * @return a string description of the vehicle finder
     */
    @Override
    public String toString() {
        return "Electric Ride";
    }
}
