package taxify;

import taxify.Interfaces.ISoundMode;

/**
 * Represents a sound mode in which the volume multiplier is set to a lower level.
 * The multiplier is higher than the standard sound mode, indicating a more "silent" experience.
 */

public class SilentMode implements ISoundMode {

    /**
     * Returns the sound multiplier for the Silent Mode.
     * This multiplier increases the cost slightly (by 15%) compared to the normal sound mode.
     * 
     * @return the multiplier for the sound mode, which is 1.15 for Silent Mode
     */
    @Override
    public double getMultiplier() {
        return 1.15;
    }
    
}
