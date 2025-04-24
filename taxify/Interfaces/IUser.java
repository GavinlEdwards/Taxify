package taxify.Interfaces;

import java.time.LocalDate;

/**
 * Interface representing a user in the Taxify system.
 * Defines methods for accessing user information, requesting services, 
 * and rating services.
 */
public interface IUser {
    
    /**
     * Gets the unique identifier of the user.
     *
     * @return the user's ID
     */
    public int getId();

    /**
     * Gets the first name of the user.
     *
     * @return the user's first name
     */
    public String getFirstName();

    /**
     * Gets the last name of the user.
     *
     * @return the user's last name
     */
    public String getLastName();

    /**
     * Gets the gender of the user.
     *
     * @return the user's gender as a character (e.g., 'M' or 'F')
     */
    public char getGender();

    /**
     * Gets the birth date of the user.
     *
     * @return the user's birth date
     */
    public LocalDate getBirthDate();

    /**
     * Checks if the user has an active service or ride.
     *
     * @return true if the user has an active service, false otherwise
     */
    public boolean getService();

    /**
     * Sets the service status for the user (active or inactive).
     *
     * @param service the new service status to be set
     */
    public void setService(boolean service);

    /**
     * Assigns a taxi company to the user.
     *
     * @param company the taxi company to be set for the user
     */
    public void setCompany(ITaxiCompany company);

    /**
     * Requests a service (ride) for the user.
     * The method will initiate the process of finding and assigning a vehicle.
     */
    public void requestService();

    /**
     * Rates a service that the user has used.
     *
     * @param service the service to be rated
     */
    public void rateService(IService service);

    /**
     * Returns a string representation of the user.
     * Typically includes the user's name and ID.
     *
     * @return a string describing the user
     */
    @Override
    public String toString();
}
