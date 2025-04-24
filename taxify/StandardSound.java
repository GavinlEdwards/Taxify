package taxify;

import taxify.Interfaces.ISoundMode;

/**
 * Represents the standard sound mode for a ride.
 * The multiplier for this mode is 1, meaning no additional cost is applied for the sound.
 */
public class StandardSound implements ISoundMode {
    /**
     * Returns the sound multiplier for the Standard Sound mode.
     * The multiplier is 1, meaning there is no effect on the service cost for this sound mode.
     * 
     * @return the multiplier for the sound mode, which is 1 for Standard Sound
     */
    @Override
    public double getMultiplier() {
        return 1;
    }
    

}
