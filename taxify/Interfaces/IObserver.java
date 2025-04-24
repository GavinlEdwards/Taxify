package taxify.Interfaces;

/**
 * Interface for an observer in the Observer design pattern.
 * Classes implementing this interface will receive updates
 * from a subject they are observing.
 */
public interface IObserver {
    
    /**
     * Called to notify the observer of a change or update.
     *
     * @param message the message or information sent to the observer
     */
    public void updateObserver(String message);

}