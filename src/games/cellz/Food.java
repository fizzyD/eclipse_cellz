package games.cellz;

import java.awt.*;

public class Food extends Particle {

    public Food(int x, int y, int r) {
        super( x , y );

        // unclean - messing with graphics in the model
        color = Color.red;
        addMass( r * r );

    }

    /**
     * this was used to make the food bigger for a screen-shot
    public double radius() {
        return radius * 3;
    }
     */
}
