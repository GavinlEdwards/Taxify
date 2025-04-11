package taxify;

import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicle;

public class RideShare {
    

    public void provideRideShare(IVehicle taxi, IUser user) {
        // both users accepting rideshare
        System.out.println("rideShare possible with vehicle " + taxi.getId());
        if(Math.random() > .1 && Math.random() < .9) {
            System.out.println("ride Share accepted");
            if(Math.random() < .8) {
                taxi.startRideShare(user, new Service(user, taxi.getLocation(), taxi.getDestination(),taxi.getService().getSoundMode()));
                System.out.println("same destination");
            } else {
                taxi.startRideShare(user, new Service(user, taxi.getLocation(), ApplicationLibrary.randomLocation(), taxi.getService().getSoundMode()));
                System.out.println("new destination");
            }
        }
        
    }

}
