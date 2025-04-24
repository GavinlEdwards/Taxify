package taxify;

/**
 * Enum representing the different statuses of a vehicle in the Taxify system.
 * 
 */
public enum VehicleStatus {
    FREE,      // The vehicle is available for booking
    BOOKED,    // The vehicle is booked and waiting for the user
    PICKUP,    // The vehicle is en route to pick up the user
    SERVICE    // The vehicle is actively serving the user
}