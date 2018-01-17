package smart_rockets;

import processing.core.PApplet;
import processing.core.PVector;


public class Animation extends PApplet {

    private Population population;
    int lifeSpan = 200;
    PVector target;
    public int count;

    public static void main(String[] args) {
        PApplet.main(new String[] { Animation.class.getName() });
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        population = new Population(this);
        target = new PVector(width/2, 50);
    }

    public void draw() {
        background(0);
        population.run();

        count++;
        if (count == lifeSpan) {
            population = new Population(this);
            count = 0;
        }

        ellipse(target.x, target.y, 16, 16);
    }
}
