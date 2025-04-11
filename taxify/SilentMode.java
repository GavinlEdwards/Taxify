package taxify;

import taxify.Interfaces.ISoundMode;

public class SilentMode implements ISoundMode {

    @Override
    public double getMultiplier() {
        return 1.15;
    }
    
}
