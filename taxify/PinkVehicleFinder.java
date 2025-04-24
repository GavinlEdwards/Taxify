package taxify;

import java.util.List;

import taxify.Interfaces.IVehicle;
import taxify.Interfaces.IVehicleFinder;


/**
 * Class responsible for finding available vehicles that meet specific criteria for a "Pink Ride."
 * This class implements the IVehicleFinder interface and is designed to find vehicles with female drivers.
 */
public class PinkVehicleFinder implements IVehicleFinder {

    /**
     * {@inheritDoc}
     * Finds a free vehicle from the list of vehicles that has a female driver. 
     * If no such vehicle is found after traversing all available vehicles, it returns -1.
     * 
     * @param vehicles the list of vehicles to search from
     * @return the index of a vehicle with a female driver and that is free, or -1 if no such vehicle is found
     */
    @Override
    public int findFreeVehicle(List<IVehicle> vehicles) {
        int val = (int) (Math.random()*vehicles.size());
        int max = vehicles.size()*2;
        int count = 0;
        while(!vehicles.get(val).isFree() || vehicles.get(val).getDriver().getGender()!='f') {
            val = (val+1) % vehicles.size();
            count+=1;
            if(count >= max) {
                return -1;
            }
        }
        return val;
    }

    /**
     * {@inheritDoc}
     * Returns a string representation of the vehicle finder.
     *
     * @return a string "Pink ride" representing the type of vehicle search
     */
    @Override
    public String toString() {
        return "Pink ride";
    }
}
