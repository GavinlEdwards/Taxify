package taxify.Interfaces;

/**
 * Interface for a subject in the Observer design pattern.
 * Classes implementing this interface manage a list of observers
 * and notify them when changes occur.
 */
public interface ISubject {

    /**
     * Adds an observer to the list of observers.
     * The observer will be notified when the subject's state changes.
     *
     * @param observer the observer to be added
     */
    public void addObserver(IObserver observer);

    /**
     * Notifies all observers about a change or update.
     * This method sends a message to all observers to inform them of the change.
     *
     * @param message the message to send to the observers
     */
    public void notifyObserver(String message);
}