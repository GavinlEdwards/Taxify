package taxify;

import java.util.List;

import taxify.Interfaces.IVehicle;
import taxify.Interfaces.IVehicleFinder;

public class PinkVehicleFinder implements IVehicleFinder {

    @Override
    public int findFreeVehicle(List<IVehicle> vehicles) {
        int val = (int) (Math.random()*vehicles.size());
        while(!vehicles.get(val).isFree() || vehicles.get(val).getDriver().getGender()!='f') {
            val = (val+1) % vehicles.size();
        }
        return val;
    }

    @Override
    public String toString() {
        return "Pink ride";
    }
}
