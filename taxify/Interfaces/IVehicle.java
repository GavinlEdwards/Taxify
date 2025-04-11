package taxify.Interfaces;

public interface IVehicle extends IMovable {

    public int getId();
    public ILocation getLocation();
    public ILocation getDestination();
    public IService getService();
    public IStatistics getStatistics();
    public void setCompany(ITaxiCompany company);
    public void pickService(IService service);
    public void startService();
    public void endService();
    public void notifyArrivalAtPickupLocation();
    public void notifyArrivalAtDropoffLocation();
    public boolean isFree();
    public double calculateCost();
    public String toString();
    public IDriver getDriver();
    public void setDriver(IDriver driver);
    public void startRideShare(IUser user, IService service);
    public void endRideShare();
    public boolean isTaxi();
    public boolean hasOccupant();
    public void setOccupantStatus(boolean bool);

}
