package games.cellz;

import games.sound.SoundPlayer;
import games.math.Vector2d;

import java.util.Collection;
import java.util.Iterator;

public class CellModel extends Particle {
    // this should implement the control part to
    // work out some force to apply
    // for example - it could work out the location
    // of the closest piece of food, then head straight
    // for it
    static CellSounds player = new CellSounds(false);

    CellControl control;
    Vector2d a;
    static double maxVelocity = 15;
    static double friction = 0.03;
    static double initMass = 50;

    public CellModel(double x, double y, CellControl control) {
        this( x, y, control, initMass );
    }

    public CellModel(double x, double y, CellControl control, double initMass) {
        super(x, y);
        this.control = control;
        a = new Vector2d();
        addMass(initMass);
    }

    public void updateState(Collection particles, DishModel dish) {
        Vector2d force = control.getForce(this, particles);
        // now work out the velocity
        a.set(force);
        a.div(mass);
        // now apply the velocity
        v.add(a);
        // now limit it
        v.limit(maxVelocity);
        // now update position
        // System.out.println("Updated " + v + " : " + a);
        s.add(v);
        // now apply friction
        player.move( v );
        v.mul(1.0 - friction);

        // now check see if there's anything we can eat in the new position
        for (Iterator i = particles.iterator(); i.hasNext();) {
            Particle p = (Particle) i.next();
            if (p.valid) {
                double dist = s.dist(p.s);
                // now if there is some interesting distance, see what else we
                // really want to get hold of...
                // see if they touch...
                if ( dist < (radius + p.radius()) ) {
                    // okay - contact - do something
                    if (p instanceof Food) {
                        addMass(p.mass);
                        player.eatFood( (int) mass / 3 );
                        p.valid = false;
                        // why not add some food at a random location as well?
                        dish.addRandomFood();
                    }
                }
            }
        }
        // if we're still valid (think this is always true)
        if (valid) {
            if ( control.split( this ) ) {
                CellModel child = new CellModel( s.x, s.y, control, mass / 2 );
                // distribute the mass
                child.v.set(v);;
                child.v.mul(-1);
                addMass( -mass / 2 );
                dish.add( child );
                // System.out.println("Split: " + this + " : " + child);
                // System.out.println( v + " : " + child.v );
                // child.s.add(new Vector2d( 10, 10 ) );
            }
        }
    }
}
