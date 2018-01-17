package lorenz_attractor;


import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class LorenzAttractor extends PApplet {
    float dt = 0.01f;
    float x = 0.01f, y = 0, z = 0;
    float gamma = 10;
    float rho = 28;
    float beta = 8.0f/3.0f;
    List<PVector> points = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main(new String[] { LorenzAttractor.class.getName() });
    }

    public void settings(){
        size(800, 600, P3D);
    }

    public void setup() {
        colorMode(HSB);
    }

    public void draw() {
        background(0);
        float dx = (gamma * (y - x)) * dt;
        float dy = (x * (rho - z) - y) * dt;
        float dz = (x * y - beta * z) * dt;

        x += dx;
        y += dy;
        z += dz;

        points.add(new PVector(x, y, z));

        translate(width/2, height/2, -80);
        scale(5);
        stroke(255);
        noFill();

        float hue = 0;
        beginShape();
        for (PVector point : points) {
            stroke(hue, 255, 255);
            vertex(point.x, point.y, point.z);
            hue += 0.1;
        }
        endShape();
    }
}
