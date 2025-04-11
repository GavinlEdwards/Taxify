package taxify;

import java.util.List;

import taxify.Interfaces.IVehicle;
import taxify.Interfaces.IVehicleFinder;

public class VehicleFinder implements IVehicleFinder {

    @Override
    public int findFreeVehicle(List<IVehicle> vehicles) {
        int val = (int) (Math.random()*vehicles.size());
        while(!vehicles.get(val).isFree()) {
            val = (val+1) % vehicles.size();
        }
        return val;
    }

    @Override
    public String toString() {
        return "Standard ride";
    }

}
