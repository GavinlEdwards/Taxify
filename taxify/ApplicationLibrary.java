package taxify;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import taxify.Interfaces.ILocation;

/**
 * Utility class providing various methods related to the application's library.
 * This includes random number generation, distance calculation, random location generation,
 * and handling charging stations.
 */
public class ApplicationLibrary {

    /**
     * Minimum distance required between locations for a valid destination.
     */
    public static final int MINIMUM_DISTANCE = 3;

    /**
     * Width of the map, used for random location generation.
     */
    private static final int MAP_WIDTH = 10;

    /**
     * Height of the map, used for random location generation.
     */
    private static final int MAP_HEIGHT = 10;
    
    /**
     * List of charging stations, initialized with random locations.
     */    
    private static final List<ILocation> chargingStations = new ArrayList<>(List.of(
        ApplicationLibrary.randomLocation(), 
        ApplicationLibrary.randomLocation(), 
        ApplicationLibrary.randomLocation(), 
        ApplicationLibrary.randomLocation(), 
        ApplicationLibrary.randomLocation(), 
        ApplicationLibrary.randomLocation()
    ));

    /**
     * Generates a random integer value.
     * The random value is between 0 (inclusive) and 9767 (exclusive).
     * 
     * @return a randomly generated integer
     */
    public static int rand() {
        Random random = new Random();
        
        return random.nextInt(9767);
    }
    
    /**
     * Generates a random integer value up to the specified maximum value.
     * The random value is between 0 (inclusive) and the specified max (exclusive).
     * 
     * @param max the maximum value for the random number
     * @return a randomly generated integer in the range [0, max)
     */
    public static int rand(int max) {
        Random random = new Random();

        return random.nextInt(9767) % max;
    }
    
    /**
     * Calculates the Manhattan distance between two locations.
     * The Manhattan distance is the sum of the absolute differences of their coordinates.
     * 
     * @param a the first location
     * @param b the second location
     * @return the Manhattan distance between the two locations
     */
    public static int distance(ILocation a, ILocation b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }
    
    /**
     * Generates a random location on the map.
     * The location's coordinates are random values within the width and height of the map.
     * 
     * @return a random {@link ILocation} object
     */
    public static ILocation randomLocation() {
        return new Location(rand(MAP_WIDTH), rand(MAP_HEIGHT));
    }
    
    /**
     * Generates a random destination location that is at least a certain minimum distance away
     * from the given location.
     * 
     * @param location the origin location
     * @return a random {@link ILocation} object that is at least MINIMUM_DISTANCE away from the origin
     */
    public static ILocation randomLocation(ILocation location) {
        ILocation destination;
        
        do {
            
            destination = new Location(rand(MAP_WIDTH), rand(MAP_HEIGHT));
            
        } while (distance(location, destination) < MINIMUM_DISTANCE);  
            
        return destination;
    }

    /**
     * Returns a random charging station from the list of available charging stations.
     * 
     * @return a randomly selected {@link ILocation} representing a charging station
     */
    public static ILocation getChargingStation() {
        int val = (int) (Math.random()*(chargingStations.size()));
        return chargingStations.get(val);
    }
}