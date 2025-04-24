package taxify.Interfaces;

/**
 * Interface representing a movable entity in the Taxify system.
 * Any class implementing this interface must define how the entity moves.
 */
public interface IMovable {

    /**
     * Moves the entity to a new position.
     * The specifics of movement (e.g., direction, speed, destination)
     * are defined by the implementing class.
     */
    public void move();
    
}