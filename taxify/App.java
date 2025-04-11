/*
 * This source file was generated by the Gradle 'init' task
 */
package taxify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import taxify.Interfaces.IDriver;
import taxify.Interfaces.IUser;
import taxify.Interfaces.IVehicle;
import taxify.Vehicles.Shuttle;
import taxify.Vehicles.Taxi;

public class App {
   
    public static void main(String[] args) {
        
        
        // Instaniate users
        IUser user1 = new User(1, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user2 = new User(2, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user3 = new User(3, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user4 = new User(4, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user5 = new User(5, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user6 = new User(6, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user7 = new User(7, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user8 = new User(8, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user9 = new User(9, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user10 = new User(10, "Gavin", "Edwards", 'm', LocalDate.now());
        IUser user11 = new User(11, "Gen", "Pew", 'f', LocalDate.now());
        IUser user12 = new User(12, "Gen", "Pew", 'f', LocalDate.now());
        IUser user13 = new User(13, "Gen", "Pew", 'f', LocalDate.now());
        IUser user14 = new User(14, "Gen", "Pew", 'f', LocalDate.now());
        IUser user15 = new User(15, "Gen", "Pew", 'f', LocalDate.now());
        
        IDriver driver1 = new Driver(1, "Emily", "Wang", 'f',LocalDate.now(),3);
        IDriver driver2 = new Driver(2, "Emily", "Wang", 'f',LocalDate.now(),3);
        IDriver driver3 = new Driver(3, "Emily", "Wang", 'f',LocalDate.now(),3);
        IDriver driver4 = new Driver(4, "Emily", "Wang", 'f',LocalDate.now(),3);
        IDriver driver5 = new Driver(5, "Emily", "Wang", 'f',LocalDate.now(),3);

        IDriver driver6 = new Driver(6, "Josh", "Hunt", 'm', LocalDate.now(),6);
        IDriver driver7 = new Driver(7, "Josh", "Hunt", 'm', LocalDate.now(),6);
        IDriver driver8 = new Driver(8, "Josh", "Hunt", 'm', LocalDate.now(),6);
        IDriver driver9 = new Driver(9, "Josh", "Hunt", 'm', LocalDate.now(),6);
        IDriver driver10 = new Driver(10, "Josh", "Hunt", 'm', LocalDate.now(),6);


        List<IUser> users = new ArrayList<IUser>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
        users.add(user13);
        users.add(user14);
        users.add(user15);

        //Instaniates vehicles
        IVehicle vehicle1 = new Shuttle(1,ApplicationLibrary.randomLocation() );
        IVehicle vehicle2 = new Shuttle(2,ApplicationLibrary.randomLocation() );
        IVehicle vehicle3 = new Shuttle(3,ApplicationLibrary.randomLocation() );
        IVehicle vehicle4 = new Shuttle(4,ApplicationLibrary.randomLocation() );

        IVehicle vehicle5 = new Taxi(5,ApplicationLibrary.randomLocation() );
        IVehicle vehicle6 = new Taxi(6,ApplicationLibrary.randomLocation() );
        IVehicle vehicle7 = new Taxi(7,ApplicationLibrary.randomLocation() );
        IVehicle vehicle8 = new Taxi(8,ApplicationLibrary.randomLocation() );
        IVehicle vehicle9 = new Taxi(9,ApplicationLibrary.randomLocation() );
        IVehicle vehicle10 = new Taxi(10,ApplicationLibrary.randomLocation() );
        
        vehicle1.setDriver(driver1);
        vehicle2.setDriver(driver2);
        vehicle3.setDriver(driver3);
        vehicle4.setDriver(driver4);
        vehicle5.setDriver(driver5);
        vehicle6.setDriver(driver6);
        vehicle7.setDriver(driver7);
        vehicle8.setDriver(driver8);
        vehicle9.setDriver(driver9);
        vehicle10.setDriver(driver10);




        List<IVehicle> vehicles = new ArrayList<IVehicle>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);
        vehicles.add(vehicle5);
        vehicles.add(vehicle6);
        vehicles.add(vehicle7);
        vehicles.add(vehicle8);
        vehicles.add(vehicle9);
        vehicles.add(vehicle10);

        // Instaniates company and simulator
        TaxiCompany company = new TaxiCompany("company1", users, vehicles);
        ApplicationSimulator simulator = new ApplicationSimulator(company, users, vehicles);
        
        company.addObserver(simulator);

        simulator.requestService();
        simulator.requestService();
        simulator.requestService();
        simulator.requestService();
        simulator.requestService();
        int count = 5;

        while(count!=0) {
            double rand = Math.random();
            if (rand < .2) {
                simulator.requestService();
            }
            simulator.update();
            count = company.getTotalServices();
        }
        
        simulator.showStatistics();
    }
}
