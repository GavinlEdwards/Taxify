package taxify;

import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicle;

/**
 * Class responsible for handling the logic of ride-sharing between a user and a vehicle.
 * This class contains the logic to determine if a ride-sharing opportunity is possible and
 * starts the ride-sharing service if accepted.
 */
public class RideShare {
    
    /**
     * Provides a ride-share opportunity between the given vehicle and user.
     * It randomly determines if a ride-share is possible, and if accepted, 
     * starts the ride-share service with either the same destination or a new destination.
     *
     * @param taxi the vehicle offering the ride-share
     * @param user the user requesting the ride-share
     */
    public static void provideRideShare(IVehicle taxi, IUser user) {
        // both users accepting rideshare
        System.out.println("Ride Share possible with vehicle " + taxi.getId());
        if(Math.random() > .1 && Math.random() < .9) {
            System.out.println("Ride Share accepted");
            if(Math.random() < .8) {
                taxi.startRideShare(user, new Service(user, taxi.getLocation(), taxi.getDestination(),taxi.getService().getSoundMode()));
                System.out.println("Same destination");
            } else {
                System.out.println("New destination");
                taxi.startRideShare(user, new Service(user, taxi.getLocation(), ApplicationLibrary.randomLocation(), taxi.getService().getSoundMode()));
                
            }
        }
        
    }

}
