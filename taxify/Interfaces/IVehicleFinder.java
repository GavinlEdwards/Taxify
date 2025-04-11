package taxify.Interfaces;

import java.util.List;

public interface IVehicleFinder {
    
    
    int findFreeVehicle(List<IVehicle> vehicles);
    String toString();

}
