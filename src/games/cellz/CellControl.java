
package games.cellz;

import games.math.Vector2d;

import java.util.Collection;

/**
 * This interface defines what actions are available for the cell controller
 * to perform.
 *
 * It is given the state of the Dish, and must make its decisions based on
 * that.
 *
 * The output is the desired force to be applied
 */

public interface CellControl {
    // return the force vector
    public Vector2d getForce( CellModel me, Collection particles );

    // decide whether or not to split
    // will only be called when the mass is greater than
    // some minimum threshold mass
    public boolean split( CellModel me );
}
