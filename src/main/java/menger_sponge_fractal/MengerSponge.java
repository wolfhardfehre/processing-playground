package menger_sponge_fractal;


import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;


public class MengerSponge extends PApplet {

    float a = 0;
    List<Box> boxes;

    public void settings(){
        size(800, 800, P3D);
    }

    public void setup() {
        Box box = new Box(this, 0F, 0F, 0F, 200F);
        boxes = new ArrayList<>();
        boxes.add(box);
    }

    public void mousePressed() {
        List<Box> next = new ArrayList<>();
        for (Box b : boxes) {
            next.addAll(b.generate());
        }
        boxes = next;
    }

    public void draw(){
        background(51);
        stroke(255);
        noFill();
        lights();

        translate(width/2, height/2);

        rotateX(a);
        rotateY(a*0.4F);
        rotateZ(a*0.1F);

        boxes.forEach(Box::show);

        a+= 0.01;
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { MengerSponge.class.getName() });
    }
}
