package games.cellz;

//import utilities.StatisticalSummary;
//import utilities.ElapsedTimer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.util.Random;

public class DishController extends Thread {
    DishModel model;
    DishView view;
    JFrame frame;
    int width = 640;
    int height = 480;
    static int delay = 30;
    Label label;

    public static void main(String[] args) throws Exception {
        int nFood = 10;
        DishController dc = new DishController( nFood );
    }

    public DishController(int nFood) {
        frame = new JFrame();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setTitle("Cellz!");
        // get same behaviour every time for now
        model = new DishModel(width, height, new Random(1) );
        CellModel cell = new CellModel(width / 2, height / 2, new GreedyControl());
        CellModel sc = new SmartCell(width / 2, height / 2, new GreedyControl());
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
        start();
    }

    public void run() {
        // StatisticalSummary mt = new StatisticalSummary();
        // ElapsedTimer t = new ElapsedTimer();
        int x = 0;
        // mt.add( t.elapsed());
        // System.out.println( mt );
        while (true) {
            try {
                sleep(delay);
                model.updateModel();
                view.repaint();
                x++;
                if (x % (1000 / delay) == 0) {
                    // label.setText( model.nParticles() + " : " + ( x * 1000 ) / t.elapsed() );
                    x = 0;
                    // mt.add( t.elapsed() );
                    // t.reset();
                    // mt.reset();
                }
            } catch (Exception e) {
                // System.out.println( e );
                e.printStackTrace();
            }
        }
    }
}
