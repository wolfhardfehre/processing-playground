package rain_curtain;


import processing.core.PApplet;


public class Starfield extends PApplet {

    private Star[] stars = new Star[4000];
    float speed;
    int hue = 0;

    public void settings(){
        size(1000, 700);
    }

    public void setup() {
        colorMode(HSB);
        int outer = 200;
        int inner = 150;
        for (int i=0; i<stars.length; i++) {
            stars[i] = new Star(this, inner, outer);
        }
    }

    public void draw(){
        speed = map(mouseX, 0, width, 0, 20);
        background(0);
        translate(width/2, height/2);
        for (Star star : stars) {
            hue += 0.2;
            star.update();
            star.show();
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { Starfield.class.getName() });
    }
}
