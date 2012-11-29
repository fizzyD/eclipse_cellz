package games.cellz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class DishView extends JPanel {
    DishModel dish;
    Dimension d;
    int foodRadius = 10;

    public DishView(DishModel dish) {
        this.dish = dish;
        d = new Dimension(dish.width, dish.height);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                addFood(e.getX(), e.getY());
            }
        });
    }

    public void addFood(int x, int y) {
        // System.out.println("Adding food: " + x + " : " + y);
        dish.add(new Food(x, y, foodRadius));
        // repaint();
    }

    public void paintComponent(Graphics g) {
        // paint the background
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);

        // now paint the forground

        for (Iterator i = dish.particles.iterator(); i.hasNext();) {
            // need to get the radius etc, then draw it
            Particle p = (Particle) i.next();
            // System.out.println(p);
            if (p.valid) {
                g.setColor(p.color);
                int r = (int) p.radius();
                g.fillOval((int) p.s.x - r, (int) p.s.y - r, 2 * r, 2 * r);
            }
        }
    }

    public Dimension getPreferredSize() {
        return d;
    }
}
