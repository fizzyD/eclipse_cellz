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
    boolean play_sound;
    
    public CellSounds(boolean play_sound) {
    	this.play_sound = play_sound;
    	if (this.play_sound){
    		player = new SoundPlayer();
    	} else{
    		System.out.println("sound disabled");
    	}
    }

    public void move(Vector2d v) {
        int mag = (int) v.mag();
        if (mag > 0) {
            // player.play( mag + 64 );
        }
    }

    public void eatFood(int mass) {
    	if(this.play_sound){
    		player.play( mass + 20 );
    		player.play( mass + 32 );
    	}
    }

    public void eatCell() {
    	if(this.play_sound){
    		player.play( 80 );
    		player.play( 84 );
    	}
    }

}
