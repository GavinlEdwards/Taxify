package taxify.Interfaces;

/**
 * Interface representing a sound mode setting for a service in the Taxify system.
 * Sound modes can influence factors like cost, depending on the user's preferences.
 */
public interface ISoundMode {

    /**
     * Gets the cost multiplier associated with this sound mode.
     * This multiplier can be used to adjust the total cost of a service.
     *
     * @return the multiplier for the sound mode
     */
    double getMultiplier();

}
