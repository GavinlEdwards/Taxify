package taxify.Interfaces;

public interface ISubject {
    
    public void addObserver(IObserver observer);
    public void notifyObserver(String message);
    
}