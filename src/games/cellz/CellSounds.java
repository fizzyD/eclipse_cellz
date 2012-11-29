package games.cellz;

import games.sound.SoundPlayer;
import games.math.Vector2d;

/**
 * Created by IntelliJ IDEA.
 * User: sml
 * Date: 10-Nov-2003
 * Time: 14:33:55
 * To change this template use Options | File Templates.
 */
public class CellSounds {
    SoundPlayer player;

    public CellSounds() {
        player = new SoundPlayer();
    }

    public void move(Vector2d v) {
        int mag = (int) v.mag();
        if (mag > 0) {
            // player.play( mag + 64 );
        }
    }

    public void eatFood(int mass) {
        player.play( mass + 20 );
        player.play( mass + 32 );
    }

    public void eatCell() {
        player.play( 80 );
        player.play( 84 );
    }

}
