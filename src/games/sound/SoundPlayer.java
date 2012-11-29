package games.sound;

import javax.sound.midi.*;

/**
 *
 *  Easy class for playing midi notes on the default
 *  synthesizer and instrument.
 *  Tested with Java 1.4
 *
 */

public class SoundPlayer {

    public static void main(String[] args) throws Exception {
        // demonstrate how to use the sound player
        SoundPlayer player = new SoundPlayer();

        // run through some notes
        for (int i = 10; i < 100; i++) {
            player.play(i);
            Thread.sleep(200);
            player.stop(i);
        }
        // allow notes to finish playing
        Thread.sleep(2000);
        player.close();
        System.exit(0);
    }

    static long timeStamp = -1;
    Synthesizer synth;
    Receiver rcvr;
    ShortMessage myMsg;
    int volume = 127;      // default of max volume
    int channel = 0; // channel selects the virtual instrument to play on

    public SoundPlayer() {
        try {
            synth = MidiSystem.getSynthesizer();
            System.out.println("Default Synth: " + synth);
            synth.open();
            rcvr = synth.getReceiver();
            System.out.println("rcvr = " + rcvr);
            System.out.println("Opened it...");
            myMsg = new ShortMessage();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void close() {
        synth.close();
    }

    public void play(int pitch) {
        play(pitch, volume);
    }

    public void stop(int pitch) {
        try {
            myMsg.setMessage(ShortMessage.NOTE_OFF, channel, pitch, volume);
            rcvr.send(myMsg, timeStamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(int pitch, int volume) {
        try {
            myMsg.setMessage(ShortMessage.NOTE_ON, channel, pitch, volume);
            rcvr.send(myMsg, timeStamp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
