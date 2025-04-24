package taxify;

import java.time.LocalDate;

import taxify.Interfaces.IService;
import taxify.Interfaces.ITaxiCompany;
import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicleFinder;

/**
 * The User class represents a user in the Taxify system. It contains user-specific information such as 
 * name, gender, birth date, and service status. It also provides functionality to request a ride service
 * and rate a completed service.
 */
public class User implements IUser {
    private int id;
    private String firstName;
    private String lastName;
    private char gender;
    private LocalDate birthDate;
    private ITaxiCompany company;
    private boolean service;
    private IVehicleFinder finder = new VehicleFinder();

    /**
     * Constructs a User object with the given details.
     * 
     * @param id the unique identifier for the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param gender the gender of the user (represented by a character)
     * @param birthDate the birth date of the user
     */
    public User(int id, String firstName, String lastName, char gender, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.service = false;
    }
    
    /**
     * Gets the unique ID of the user.
     * 
     * @return the user's ID
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Gets the first name of the user.
     * 
     * @return the user's first name
     */
    @Override
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the last name of the user.
     * 
     * @return the user's last name
     */
    @Override
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the gender of the user.
     * 
     * @return the user's gender (represented by a character)
     */
    @Override
    public char getGender() {
        return this.gender;
    }

    /**
     * Gets the birth date of the user.
     * 
     * @return the user's birth date
     */
    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Checks if the user is currently using a service.
     * 
     * @return true if the user is using a service, false otherwise
     */
    @Override
    public boolean getService() {
        return this.service;
    }

    /**
     * Sets the user's service status (whether they are using a service or not).
     * 
     * @param service true if the user is using a service, false otherwise
     */
    @Override
    public void setService(boolean service) {
        this.service = service;
    }

    /**
     * Sets the company that provides services to the user.
     * 
     * @param company the company providing services to the user
     */
    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    /**
     * Requests a ride service from the user's associated taxi company.
     * Uses the vehicle finder to find a vehicle and initiate the service request.
     */
    @Override
    public void requestService() {
        this.company.provideService(this.id, finder);
    }

    /**
     * Rates a completed service by assigning a random star rating between 1 and 5.
     * Users rate about 50% of the services they use.
     * 
     * @param service the service to be rated
     */
    @Override
    public void rateService(IService service) {
        // Users rate around 50% of the services (1 to 5 stars)
        if (ApplicationLibrary.rand() % 2 == 0) {
            service.setStars(ApplicationLibrary.rand(5) + 1);
        }
    }

    /**
     * Provides a string representation of the user, including their ID and name.
     * 
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s", this.getFirstName() + " " + this.getLastName());
    }
}
