package taxify.Interfaces;

public interface ITaxiCompany {

    public String getName();    
    public int getTotalServices();
    public boolean provideService(int user, IVehicleFinder finder);
    public void arrivedAtPickupLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocationRideShare(IVehicle vehicle, IService rideShareService);

}
