package taxify;
import taxify.Interfaces.ILocation;

/**
 * Represents a location in a 2D coordinate system.
 * Implements the ILocation interface.
 * This class is used to track the position of vehicles, users, and other entities within the system.
 */
public class Location implements ILocation {
    private int x;
    private int y;

    /**
     * Constructs a Location object with the specified x and y coordinates.
     *
     * @param x the x-coordinate of the location
     * @param y the y-coordinate of the location
     */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * {@inheritDoc}
     * Returns the x-coordinate of the location.
     *
     * @return the x-coordinate
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * {@inheritDoc}
     * Returns the y-coordinate of the location.
     *
     * @return the y-coordinate
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * {@inheritDoc}
     * Returns a string representation of the location in the format (x, y).
     *
     * @return a string representing the location
     */
    @Override
    public String toString() {
        return "(" + this.getX() + "," + this.getY() + ")";
    }
}