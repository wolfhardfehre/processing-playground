package rain_curtain;

import processing.core.PApplet;

public class RectRotator extends PApplet {

    private float step = 0F;
    private float radius = 100;

    public void settings(){
        size(600, 600);
    }

    public void setup() {
        smooth();
    }

    public void draw(){

        background(0);
        float rho = PI * step;
        float x = radius * cos(rho);
        float y = radius * sin(rho);
        translate(width/2, height/2);
        rotate(rho);
        rect(100, 100, 52, 52);
        step += 0.01;
        /*
        background(0);

        // move the center of rotation
        // to the center of the sketch
        translate(width/2, height/2);

        // rotate around the center of the sketch
        rotate(radians(frameCount));

        // draw a red dot at
        // the center of the sketch
        fill(255, 0, 0);
        ellipse(0, 0, 20, 20);

        // draw a rectangle with
        // its top-left corner
        // at the center of rotation
        fill(255);
        rect(50, 50, 50, 50);
        */
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { RectRotator.class.getName() });
    }
}
