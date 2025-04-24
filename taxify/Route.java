package taxify;

import taxify.Interfaces.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a route between two locations in the Taxify system.
 * This class calculates the path between a starting location and a destination,
 * and provides methods for getting the next location in the route and checking
 * if there are any locations remaining in the route.
 */
public class Route implements IRoute {
    private List<ILocation> route;
    
    /**
     * Constructs a Route instance with the specified start location and destination.
     * It generates the list of locations that form the route between the two points.
     * 
     * @param location the starting location of the vehicle
     * @param destination the destination location of the vehicle
     */
    public Route(ILocation location, ILocation destination) {
        this.route = setRoute(location, destination);
    }
    
    /**
     * Checks if there are more locations in the route.
     * 
     * @return true if the route contains one or more locations, false otherwise
     */
    @Override
    public boolean hasLocations() {
        return !this.route.isEmpty();
    }
    
    /**
     * Returns the next location in the route and removes it from the list of locations.
     * 
     * @return the next location in the route
     */
    @Override
    public ILocation getNextLocation() {
        ILocation location = this.route.get(0);
        
        this.route.remove(0);
        
        return location;
    }
    
    /**
     * Returns a string representation of the entire route as a sequence of locations.
     * 
     * @return a string representing the route
     */
    @Override
    public String toString() {
        String route = "";
           
           for (ILocation location : this.route) {
               route = route + location.toString() + " ";
           }
       
           return route;        
    }
    
    /**
     * Calculates and returns a list of locations representing the path between the
     * start location and the destination, with each step incrementing either the x
     * or y coordinate until the destination is reached.
     * 
     * @param location the starting location
     * @param destination the destination location
     * @return a list of locations representing the route
     */
    private static List<ILocation> setRoute(ILocation location, ILocation destination) {
        List<ILocation> route = new ArrayList<ILocation>();
        
        int x1 = location.getX();
        int y1 = location.getY();
        
        int x2 = destination.getX();
        int y2 = destination.getY();
        
        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
       
        for (int i=1; i<=dx; i++) {
            x1 = (x1 < x2) ? x1 + 1 : x1 - 1;

            route.add(new Location(x1, y1));
        }
        
        for (int i=1; i<=dy; i++) {
            y1 = (y1 < y2) ? y1 + 1 : y1 - 1;
            
            route.add(new Location(x1, y1));
        }
        
        return route;
    }       
}