package taxify.Interfaces;

import java.time.LocalDate;

/**
 * Interface representing a driver in the Taxify system.
 * Provides methods for retrieving driver information, managing ratings,
 * and associating a driver with a taxi company.
 */
public interface IDriver {

    /**
     * Gets the unique identifier of the driver.
     *
     * @return the driver's ID
     */
    public int getId();

     /**
     * Gets the first name of the driver.
     *
     * @return the driver's first name
     */
    public String getFirstName();
    /**
     * Gets the last name of the driver.
     *
     * @return the driver's last name
     */
    public String getLastName();

    /**
     * Gets the gender of the driver.
     *
     * @return the driver's gender as a character (e.g., 'M' or 'F')
     */
    public char getGender();

    /**
     * Gets the birth date of the driver.
     *
     * @return the driver's birth date
     */
    public LocalDate getBirthDate();

    /**
     * Gets the number of years the driver has been driving professionally.
     *
     * @return the driver's years of experience
     */
    public int getYearsExperience();

    /**
     * Gets the current average rating of the driver.
     *
     * @return the driver's rating
     */
    public double getRating();

    /**
     * Adds a new rating for the driver. The implementation should handle updating the average rating.
     *
     * @param rating the rating to be given to the driver
     */
    public void giveRating(double rating);

    /**
     * Assigns the driver to a specific taxi company.
     *
     * @param company the taxi company to associate with the driver
     */
    public void setCompany(ITaxiCompany company);
}