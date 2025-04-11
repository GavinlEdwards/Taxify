package taxify;

import taxify.Interfaces.ISoundMode;

public class StandardSound implements ISoundMode {

    @Override
    public double getMultiplier() {
        return 1;
    }
    

}
