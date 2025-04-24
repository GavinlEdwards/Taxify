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

/**
 * The TaxiCompany class represents a taxi company that manages users, vehicles, and services.
 * It provides functionalities such as assigning vehicles to users, managing ride-sharing services, 
 * and notifying observers of events.
 */
public class TaxiCompany implements ITaxiCompany, ISubject {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;
    private IObserver observer;
    
    /**
     * Constructs a TaxiCompany with a name, a list of users, and a list of vehicles.
     * 
     * @param name the name of the taxi company
     * @param users the list of users
     * @param vehicles the list of vehicles
     */
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
    
    /**
     * Gets the name of the taxi company.
     * 
     * @return the name of the taxi company
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the total number of services provided by the taxi company.
     * 
     * @return the total number of services
     */
    @Override
    public int getTotalServices() {
        return this.totalServices;
    }
    
    /**
     * Provides a service by assigning a free vehicle to a user. 
     * If no free vehicle is available, it returns false.
     * 
     * @param user the user requesting the service
     * @param finder the vehicle finder used to search for a free vehicle
     * @return true if the service is successfully assigned, false otherwise
     */
    @Override
    public boolean provideService(int user, IVehicleFinder finder) {
        int userIndex = findUserIndex(user);        
        int vehicleIndex = finder.findFreeVehicle(vehicles);
        
        if(totalServices > 3 && Math.random() > .6 && this.findInServiceTaxi()!=-1) {
            RideShare.provideRideShare(vehicles.get(this.findInServiceTaxi()), this.findFreeUser());
        }
        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks
        
        if (vehicleIndex != -1) {
            if(vehicles.get(vehicleIndex) instanceof Vehicle) {
            makeGService(finder, userIndex, vehicleIndex);
            } else {
                System.out.println("creates eservice");
                makeEService(finder, userIndex, vehicleIndex);
            }
            return true;
        }
        
        return false;
    }

    /**
     * Creates a standard service with a random pickup and drop-off location.
     * 
     * @param finder the vehicle finder used to search for a free vehicle
     * @param userIndex the index of the user requesting the service
     * @param vehicleIndex the index of the vehicle assigned to the service
     */
    private void makeGService(IVehicleFinder finder, int userIndex, int vehicleIndex) {
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
    }

    /**
     * Creates an electric vehicle service with a destination of a charging station.
     * 
     * @param finder the vehicle finder used to search for a free vehicle
     * @param userIndex the index of the user requesting the service
     * @param vehicleIndex the index of the vehicle assigned to the service
     */
    private void makeEService(IVehicleFinder finder, int userIndex, int vehicleIndex) {
        ILocation origin, destination;
        origin = vehicles.get(vehicleIndex).getLocation();
        do {
            destination = ApplicationLibrary.getChargingStation(); 
        } while(ApplicationLibrary.distance(origin,destination) < ApplicationLibrary.MINIMUM_DISTANCE);

        this.users.get(userIndex).setService(true);

        IService service;
        service = new Service(this.users.get(userIndex), origin, destination, new StandardSound());

        this.vehicles.get(vehicleIndex).pickService(service);

        notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + " of type " + finder.toString() + ", the ride is assigned to " +
                       this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                       this.vehicles.get(vehicleIndex).getLocation().toString());

        this.totalServices++;
    }

    /**
     * Notifies the observer that a vehicle has arrived at the pickup location.
     * 
     * @param vehicle the vehicle that has arrived
     */
    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        // notify the observer a vehicle arrived at the pickup location
        observer.updateObserver(vehicle.getId() + " has arrived at the pickup location- " + vehicle.getLocation());
        vehicle.setOccupantStatus(true);
    }
    
    /**
     * Notifies the observer that a vehicle has arrived at the drop-off location and updates the service.
     * 
     * @param vehicle the vehicle that has arrived
     */
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

    
    /**
     * Adds an observer to the taxi company.
     * 
     * @param observer the observer to add
     */
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }
    
    /**
     * Notifies the observer with a specific message.
     * 
     * @param message the message to send to the observer
     */
    @Override
    public void notifyObserver(String message) {
        observer.updateObserver(message);
    }
    

    /**
     * Finds a free user who is not currently in a service.
     * 
     * @return the first available user
     */
    private IUser findFreeUser() {
        int val = (int) (Math.random()*users.size());
        while(users.get(val).getService()) {
            val = (val+1) % users.size();
        }
        return users.get(val);
    }


    /**
     * Finds a vehicle that is currently in service and has an occupant.
     * 
     * @return the index of the vehicle if found, or -1 if none is found
     */
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

    /**
     * Finds the index of a user by their ID.
     * 
     * @param id the ID of the user
     * @return the index of the user in the users list
     */
    private int findUserIndex(int id) {
        for(int i = 0; i<users.size(); i++) {
            if(users.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Notifies the observer that a vehicle has arrived at the drop-off location for a ride-share service.
     * 
     * @param vehicle the vehicle that has arrived
     * @param rideShareService the ride-share service
     */
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