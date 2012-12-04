package games.cellz;

import games.math.Vector2d;

import java.util.Collection;
import java.util.Iterator;

public class BruteControl implements CellControl {
    Vector2d force;

    public static int threshold = 200;
    static double maxForce = threshold / 10;

    public BruteControl() {
        force = new Vector2d();
    }

    public boolean split(CellModel me) {
    	return false;
    	//return (me.mass > threshold);
    }

    public Vector2d getForce(CellModel me, Collection particles) {
        // find the closest particle

        Particle closest = null; // (Particle) i.next();
        force.set(0, 0);
        // find the closest particle, and track it!!!
        double minDist = 100000; // me.s.dist( closest.s );
        for (Iterator i = particles.iterator(); i.hasNext();) {
            Particle p = (Particle) i.next();
            if (p.valid) { // don't mess with already eaten food!
                if (Food.class.isInstance(p)) {
                    double dist = me.s.dist(p.s);
                    if (p != me && dist < minDist) {
                        minDist = dist;
                        closest = p;
                    }
                }
            }
        }
        if (closest == null) {
            return force;
        } else {
            // now compute the force vector to the closest one
            // System.out.println("Closest: " + closest);
            force.add(closest.s);
            force.subtract(me.s);
            force.setMag( maxForce );
            // System.out.println("Force = " + force);
        }
        return force;
    }
}
