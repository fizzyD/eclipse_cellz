package games.cellz;

import java.util.*;

public class DishModel {
    public int width, height;
    public HashSet particles;
    Random random;
    static int foodSize = 4;
    Collection c;
    Stack queue;

    public DishModel(int width, int height) {
        this(width, height, new Random());
    }

    public DishModel(int width, int height, Random random) {
        this.width = width;
        this.height = height;
        this.random = random;
        particles = new HashSet();
        queue = new Stack();
    }

    public int nParticles() {
        return particles.size();
    }

    public void add(Particle particle) {
        // add the food or cell to the dish
        queue.add(particle);
        // particles.add(particle);
        // System.out.println(particles.size() + " particles");
    }

    public void addRandomFood(int n) {
        for (int i = 0; i < n; i++) {
            addRandomFood();
        }
    }

    public void addRandomFood() {
        Food food = new Food(random.nextInt(width), random.nextInt(height), foodSize);
        add(food);
    }

    public void updateModel() {

        // first add all the items in the queue
        while (!queue.empty()) {
            particles.add(queue.pop());
        }

        // iterate over all the objects, deciding what to do
        // each time
        // each particle can either be alive or dead
        // they start off all alive - but then the ones
        // that are eaten are marked as dead

        for (Iterator i = particles.iterator(); i.hasNext();) {
            // first of all update them all
            Particle p = (Particle) i.next();
            if (p.valid) {
                p.updateState(particles, this);
            }
        }

        // now clean up...
        for (Iterator i = particles.iterator(); i.hasNext();) {
            Particle p = (Particle) i.next();
            if (!p.valid) {
                i.remove();
            }
        }
    }
}
