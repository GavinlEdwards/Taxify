package taxify;

import java.util.List;

import taxify.Interfaces.ILocation;
import taxify.Interfaces.IObserver;
import taxify.Interfaces.IService;
import taxify.Interfaces.ISubject;
import taxify.Interfaces.ITaxiCompany;
import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicle;
import taxify.Interfaces.IVehicleFinder;

public class TaxiCompany implements ITaxiCompany, ISubject {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;
    private RideShare rideShare = new RideShare();

    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;        
        this.totalServices = 0;
        

        for (IUser user : this.users) {
            user.setCompany(this);
        }
        
        for (IVehicle vehicle : this.vehicles) {
            vehicle.setCompany(this);
        }
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTotalServices() {
        return this.totalServices;
    }
        
    @Override
    public boolean provideService(int user, IVehicleFinder finder) {
        int userIndex = findUserIndex(user);        
        int vehicleIndex = finder.findFreeVehicle(vehicles);
        
        if(totalServices > 3 && Math.random() > .6 && this.findInServiceTaxi()!=-1) {
            this.rideShare.provideRideShare(vehicles.get(this.findInServiceTaxi()), this.findFreeUser());
        }
        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks
        
        if (vehicleIndex != -1) {
            ILocation origin, destination;
            
            do {
                
                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);
                
            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) < ApplicationLibrary.MINIMUM_DISTANCE);
            
            // update the user status
                       
            this.users.get(userIndex).setService(true);
            
            // create a service with the user, the pickup and the drop-off location
            IService service;
            if(Math.random() > .75) {
                 service = new Service(this.users.get(userIndex), origin, destination, new SilentMode());
            } else {
                 service = new Service(this.users.get(userIndex), origin, destination, new StandardSound());
            }
            // assign the new service to the vehicle
            
            this.vehicles.get(vehicleIndex).pickService(service);            
             
            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + " of type " + finder.toString() + ", the ride is assigned to " +
                           this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                           this.vehicles.get(vehicleIndex).getLocation().toString());
            
            // update the counter of services
            
            this.totalServices++;
            
            return true;
        }
        
        return false;
    }

    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        // notify the observer a vehicle arrived at the pickup location
        observer.updateObserver(vehicle.getId() + " has arrived at the pickup location- " + vehicle.getLocation());
        vehicle.setOccupantStatus(true);
    }
    
    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        // a vehicle arrives at the drop-off location
        
        IService service = vehicle.getService();       
        int user = service.getUser().getId();
        int userIndex = findUserIndex(user);
       
        // the taxi company requests the user to rate the service, and updates its status

        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services
        
        this.totalServices--;    
        vehicle.setOccupantStatus(false);
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);         
    }

    
    
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }
    
    @Override
    public void notifyObserver(String message) {
        observer.updateObserver(message);
    }
    
    private int findFreeVehicle() {
        int val = (int) (Math.random()*vehicles.size());
        while(!vehicles.get(val).isFree()) {
            val = (val+1) % vehicles.size();
        }
        return val;
    }

    private IUser findFreeUser() {
        int val = (int) (Math.random()*users.size());
        while(users.get(val).getService()) {
            val = (val+1) % users.size();
        }
        return users.get(val);
    }



    private int findInServiceTaxi() {
        int val = (int) (Math.random()*vehicles.size());
        int count = 0;
        while(!vehicles.get(val).hasOccupant() || !vehicles.get(val).isTaxi()) {
            val = (val+1) % vehicles.size();
            count++;
            if(count >= vehicles.size()*2) {
                return -1;
            }
        }
        return val;
    
    }

    private int findUserIndex(int id) {
        for(int i = 0; i<users.size(); i++) {
            if(users.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void arrivedAtDropoffLocationRideShare(IVehicle vehicle, IService rideShareService) {
         // a vehicle arrives at the drop-off location
        
         IService service = rideShareService;      
         int user = service.getUser().getId();
         int userIndex = findUserIndex(user);
        
         // the taxi company requests the user to rate the service, and updates its status
 
         this.users.get(userIndex).rateService(service);
         this.users.get(userIndex).setService(false);
 
         // update the counter of services
         
        
         rideShareService.getUser().setService(false);
         notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off ride share user " + user); 
    }
}