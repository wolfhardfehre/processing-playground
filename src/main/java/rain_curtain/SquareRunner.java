package rain_curtain;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PSurface;

import javax.swing.*;

public class SquareRunner extends PApplet {

    private Projection projection;
    float radius = 400;
    float u = TWO_PI * radius;

    public void settings() {
        size((int) u, (int) u);
    }

    public void setup() {
        projection = new CylindricalProjection(80F, u, radius);
    }

    public void draw() {
        background(0);
        //ellipse(mouseX, mouseY, 70, 60);
        translate(width/2, height/2);
        square(mouseX, mouseY, 200, 2);
        //polygon(mouseX, mouseY, 80, 20);
    }

    void polygon(float x, float y, float radius, int npoints) {
        float angle = TWO_PI / npoints;
        beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
            float sx = x + cos(a) * radius;
            float sy = y + sin(a) * radius;
            vertex(sx, sy);
        }
        endShape(CLOSE);
    }

    private void square(float x, float y, float width, int pointsPerSide) {
        float step = width / pointsPerSide;
        beginShape();
        float sx = x;
        float sy = y;
        for (float a = step; a < width; a += step) {
            sx += a;
            float[] res = projection.project(sx, sy);
            vertex(res[0], res[1]);
        }
        println(sx, sy);
        for (float a = step; a < width; a += step) {
            sy += a;
            float[] res = projection.project(sx, sy);
            vertex(res[0], res[1]);
        }
        println(sx, sy);
        for (float a = step; a < width; a += step) {
            sx -= a;
            float[] res = projection.project(sx, sy);
            vertex(res[0], res[1]);
        }
        println(sx, sy);
        for (float a = step; a < width; a += step) {
            sy -= a;
            float[] res = projection.project(sx, sy);
            vertex(res[0], res[1]);
        }
        println(sx, sy);
        endShape(CLOSE);
    }

    public static void main(String... args) {

        //create your JFrame
        JFrame frame = new JFrame("JFrame Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create your sketch
        SquareRunner pt = new SquareRunner();

        //get the PSurface from the sketch
        PSurface ps = pt.initSurface();

        //initialize the PSurface
        ps.setSize(600, 600);

        //get the SmoothCanvas that holds the PSurface
        PSurfaceAWT.SmoothCanvas smoothCanvas = (PSurfaceAWT.SmoothCanvas) ps.getNative();

        //SmoothCanvas can be used as a Component
        frame.add(smoothCanvas);

        //make your JFrame visible
        frame.setSize(600, 600);
        frame.setVisible(true);

        //start your sketch
        ps.startThread();
    }
}