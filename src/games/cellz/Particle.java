package games.cellz;

import games.math.Vector2d;

import java.awt.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 09-Nov-2003
 * Time: 15:25:58
 * To change this template use Options | File Templates.
 */
public class Particle {
    Vector2d s; // position
    Vector2d v; // velocity
    double mass, radius;
    Color color = Color.blue;
    // set this flag to false after a particle
    // has been eaten - it will then be removed from the
    // set of particles at the next iteration
    public boolean valid;

    public Particle() {}

    public Particle(double x, double y) {
        s = new Vector2d( x , y );
        v = new Vector2d();
        valid = true;
    }

    public double radius() {
        return radius;
    }

    public void addMass( double m ) {
        mass += m;
        radius = Math.sqrt( mass );
    }

    // override this to make them do something!!!
    public void updateState(Collection particles, DishModel dish) {

    }

    public String toString() {
        return getClass().getName() + " : " + s.toString() + " r: " + (int )radius;
    }

}
