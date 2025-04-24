package taxify.Interfaces;

/**
 * Interface for an application simulator in the Taxify system.
 * Defines core functionalities such as updating the simulation,
 * requesting services, and displaying statistics or views.
 */
public interface IApplicationSimulator {
    
    /**
     * Displays the main user interface or view of the application.
     */
    public void show();

    /**
     * Displays statistical data related to the simulation,
     * such as completed rides, service usage, or vehicle performance.
     */
    public void showStatistics();

    /**
     * Updates the state of the simulation.
     * This may include updating vehicle positions, battery levels, or ongoing services.
     */
    public void update();

    /**
     * Initiates a new service request in the simulation.
     * This could involve assigning a vehicle to a rider or initiating a ride.
     */
    public void requestService();
    
    /**
     * Retrieves the total number of services requested or completed in the simulation.
     *
     * @return the total number of services
     */
    public int getTotalServices();
    
}
