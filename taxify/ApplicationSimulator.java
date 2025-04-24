package taxify;

import java.util.ArrayList;
import java.util.List;

import taxify.Interfaces.IApplicationSimulator;
import taxify.Interfaces.IObserver;
import taxify.Interfaces.ITaxiCompany;
import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicle;

/**
 * Simulates the Taxify application, handling the interaction between users, vehicles,
 * and the taxi company. It provides functionality to display vehicle status, statistics,
 * move vehicles, and request services.
 */
public class ApplicationSimulator implements IApplicationSimulator, IObserver {

    private ITaxiCompany company;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private List<IVehicle> gasVehicles = new ArrayList<>();
    private List<IVehicle> eVehicles = new ArrayList<>();

    /**
     * Constructs an ApplicationSimulator instance with a taxi company, list of users, and vehicles.
     * It categorizes the vehicles into gas-powered and electric vehicles.
     *
     * @param company the taxi company managing the vehicles and services
     * @param users   the list of users who can request services
     * @param vehicles the list of vehicles available in the system
     */
    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
        for(IVehicle vehicle: vehicles) {
            if(vehicle instanceof Vehicle) {
                gasVehicles.add(vehicle);
            } else {
                eVehicles.add(vehicle);
            }
        }
    }
    
    /**
     * Displays the status of all vehicles in the system, showing details like vehicle ID and location.
     */
    @Override
    public void show() {
        // show the status of the vehicles
        
        System.out.println("\n" + this.company.getName() + " status \n");

        for (IVehicle vehicle : this.vehicles) {
            System.out.println(vehicle.toString());
        }   
    }
    
    /**
     * Displays the statistics of the company, including the services, distance traveled,
     * billing, reviews, and ratings for both gas-powered and electric vehicles.
     */
    @Override
    public void showStatistics() {
        // show the statistics of the company
        
        String s = "\n" + this.company.getName() + " statistics \n";
        
        for (IVehicle vehicle : this.gasVehicles) {            
            s = s + "\n" +
            
            String.format("%-8s", vehicle.getClass().getSimpleName()) +
            String.format("%2s", vehicle.getId()) + " " +
            String.format("%2s", vehicle.getStatistics().getServices()) + " services " + 
            String.format("%3s", vehicle.getStatistics().getDistance()) + " km. " +
            String.format("%3s", vehicle.getStatistics().getBilling()) + " eur. " +
            String.format("%2s", vehicle.getStatistics().getReviews()) + " reviews " +
            String.format("%-4s", vehicle.getStatistics().getStars()) + " stars";
            System.out.println("Driver " + vehicle.getDriver().getId() + " has rating " + vehicle.getDriver().getRating());
        }
        for (IVehicle vehicle: this.eVehicles) {
            s = s + "\n" +
            String.format("%-8s", vehicle.getClass().getSimpleName()) +
            String.format("%2s", vehicle.getId()) + " " +
            String.format("%2s", vehicle.getStatistics().getServices()) + " services " + 
            String.format("%3s", vehicle.getStatistics().getDistance()) + " km. " +
            String.format("%3s", vehicle.getStatistics().getBilling()) + " eur. " +
            String.format("%3s", ((EVehicle) vehicle).getBattery() + "% battery.");
            
        }        
        System.out.println(s);        
    }    

    /**
     * Updates the state of the system by moving all vehicles to their next location.
     */
    @Override
    public void update() {
        // move vehicles to their next location
        
        for (IVehicle vehicle : this.vehicles) {
               vehicle.move();
        }
    }

    /**
     * Requests a service for a user, assigning an appropriate vehicle based on the user's preferences.
     * If the user is female, there is a chance they may request a PinkVehicleFinder or EVehicleFinder.
     * Otherwise, a general vehicle is requested.
     */
    @Override
    public void requestService() {      
        int size = this.users.size();
        int val = (int) (Math.random()*size);
        while(users.get(val).getService()) {
            val = (val + 1) % size;
        }
        if(users.get(val).getGender()=='f' && Math.random() > .5) {
            this.company.provideService(users.get(val).getId(), new PinkVehicleFinder());
        } else if(Math.random() > .75){
            this.company.provideService(users.get(val).getId(), new EVehicleFinder());
        } else {
            this.company.provideService(users.get(val).getId(), new VehicleFinder());
        }
        // finds an available user and requests a service to the Taxi Company
    }
    
    /**
     * Returns the total number of services provided by the company.
     *
     * @return the total number of services provided
     */
    @Override
    public int getTotalServices() {
        return this.company.getTotalServices();
    }
    
    /**
     * Receives updates from the subject and prints the provided message.
     * This method is part of the Observer pattern.
     *
     * @param message the message to be displayed to the observer
     */
    @Override
    public void updateObserver(String message) {
        System.out.println(message);
    }
}
