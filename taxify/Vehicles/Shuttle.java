package taxify.Vehicles;

import taxify.Interfaces.ILocation;
import taxify.Vehicle;

public class Shuttle extends Vehicle{


    public Shuttle(int id, ILocation location) {
        super(id,location);
    }


    @Override
    public double calculateCost() {
        return super.calculateCost()*1.5;
    }

    @Override
    public boolean isTaxi() {
        return false;
    }
}
