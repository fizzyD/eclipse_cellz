package games.cellz;

import utilities.StatisticalSummary;
import utilities.ElapsedTimer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.util.Random;

import org.neuroph.contrib.neat.gen.*;

public class DishController extends Thread {
    DishModel model;
    DishView view;
    JFrame frame;
    int width = 640;
    int height = 480;
    //static int delay = 30;
    static int draw_delay = 30;
    Label label;

    public static void main(String[] args) throws Exception {
        int nFood = 10;
        DishController dc = new DishController( nFood );
    }

    public DishController(int nFood) {

    	this.DishInit(nFood);
        start();
    }
    
    public void DishInit(int nFood) {
    	if (frame != null){
        	frame.dispose();
    	}
    	//frame.removeAll(); // clear the frame
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setTitle("Cellz!");
        // get same behaviour every time for now
        model = new DishModel(width, height, new Random(1) );
        CellModel cell = new CellModel(width / 2, height / 2, new BruteControl());
        model.add(cell);
        // Food food = new Food(width * 2 / 3, height * 2 / 3, 5);
        model.addRandomFood( nFood );
        // model.add(food);
        view = new DishView(model);
        frame.getContentPane().add(view);
        frame.pack();
        frame.show();
        label = new Label("################################");
        // new CloseableFrame(label, "Timing", true);
    }

    public void run() {
    	int update_to_display_ratio = 100;
        ElapsedTimer t = new ElapsedTimer();
    	final int iterations = 5000;
        final int generations = 10;
        int current_generation = 0;
        while(generations > current_generation){
        	int x = 0;
        	StatisticalSummary mt = new StatisticalSummary();
        	DishInit(10);
        	try {
        		view.repaint();
        		sleep(draw_delay);
        	} catch (Exception e){
        		e.printStackTrace();
        	}
        	mt.add( t.elapsed());
        	System.out.println( mt );
        	// for some number of simulations
        	// run a number of different Cells
        	// then apply genetics to evolve them
        	int iteration = 0;
        	while (iterations > iteration) {
        		++iteration;
        		try {
        			model.updateModel();
        			if ((0 == iteration%update_to_display_ratio)) {
        				// label.setText( model.nParticles() + " : " + ( x * 1000 ) / t.elapsed() );
        				// mt.add( t.elapsed() );
        				// t.reset();
        				// mt.reset();
        				mt.add( t.elapsed());
        				System.out.println( mt );
        				System.out.println("iteration = " + iteration);
        				System.out.println( "cell population " +model.nParticles() );                    
        				//System.out.println("repaint");
        				view.repaint();
        				sleep(draw_delay);
        			}
        		} catch (Exception e) {
        			// System.out.println( e );
        			e.printStackTrace();
        		}
        	}
        	
        	System.out.println("Generation = "+current_generation);
        	++current_generation;
        }
        
        //System.out.println( "particles " +model.nParticles() );
    }
}
