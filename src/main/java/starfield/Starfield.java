package starfield;


import processing.core.PApplet;


public class Starfield extends PApplet {

    private Star[] stars = new Star[400];
    public float speed;

    public void settings(){
        size(800, 800);
    }

    public void setup() {
        for (int i=0; i<stars.length; i++) {
            stars[i] = new Star(this);
        }
    }

    public void draw(){
        speed = map(mouseX, 0, width, 0, 20);
        background(0);
        translate(width/2, height/2);
        for (Star star : stars) {
            star.update();
            star.show();
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { Starfield.class.getName() });
    }
}
