package taxify.Interfaces;

/**
 * Interface representing a route in the Taxify system.
 * A route consists of a sequence of locations and defines
 * methods to access the next location and check if more locations exist.
 */
public interface IRoute {

    /**
     * Checks if there are remaining locations in the route.
     *
     * @return true if there are more locations to visit, false otherwise
     */
    public boolean hasLocations();

    /**
     * Retrieves the next location in the route.
     * 
     * @return the next {@link ILocation} in the route
     */
    public ILocation getNextLocation();

    /**
     * Returns a string representation of the route.
     * This may include a description or list of locations.
     *
     * @return a string representation of the route
     */
    @Override
    public String toString();

}
