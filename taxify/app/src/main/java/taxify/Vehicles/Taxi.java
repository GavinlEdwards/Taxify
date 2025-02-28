package taxify.Vehicles;

import taxify.Interfaces.ILocation;
import taxify.Vehicle;

public class Taxi extends Vehicle{


    public Taxi(int id, ILocation location) {
        super(id,location);
    }


    @Override
    public double calculateCost() {
        return super.calculateCost()*2;
    }
}
