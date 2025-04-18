package taxify.Interfaces;

public interface IService {

    public IUser getUser();
    public ILocation getPickupLocation();
    public ILocation getDropoffLocation();
    public int getStars();
    public void setStars(int stars);
    public int calculateDistance();
    public String toString();
    public ISoundMode getSoundMode();
}
