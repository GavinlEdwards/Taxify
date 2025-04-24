package taxify.Interfaces;

/**
 * Interface representing a location in the Taxify system.
 * Provides methods for accessing the X and Y coordinates,
 * as well as a string representation of the location.
 */
public interface ILocation {

    /**
     * Gets the X-coordinate of the location.
     *
     * @return the X-coordinate
     */
    public int getX();

    /**
     * Gets the Y-coordinate of the location.
     *
     * @return the Y-coordinate
     */
    public int getY();

    /**
     * Returns a string representation of the location.
     * This is typically used for logging or display purposes.
     *
     * @return a string representation of the location
     */
    @Override
    public String toString();
}

