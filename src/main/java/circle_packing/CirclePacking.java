package circle_packing;


import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

public class CirclePacking extends PApplet {

    ArrayList<Circle> circles;
    ArrayList<PVector> spots;
    PImage img;

    public static void main(String[] args) {
        PApplet.main(new String[] { CirclePacking.class.getName() });
    }

    public void settings(){
        size(537, 471);
    }

    public void setup() {
        frameRate(15);
        init();
        cropImageByBrightness(254);
    }

    private void init() {
        img = loadImage("src/main/java/circle_packing/data/ticket_easy.png");
        img.loadPixels();
        circles = new ArrayList<>();
        spots = new ArrayList<>();
    }

    private void cropImageByBrightness(int threshold) {
        for (int i=0; i < img.height * img.width; i++) {
            if (isBrighterThan(threshold, i)) {
                spots.add(new PVector(i % img.width, i / img.width));
            }
        }
    }
    
    private boolean isBrighterThan(int threshold, int i) {
        return brightness(img.pixels[i]) > threshold;
    }

    public void draw() {
        background(0);

        int total = 10;
        int count = 0;
        int attempts = 0;

        while (count < total) {
            Circle newCircle = getCircle();
            if (newCircle != null) {
                circles.add(newCircle);
                count++;
            }
            attempts++;
            if (attempts > 1000) {
                noLoop();
                println("FINISHED");
                break;
            }
        }

        for (Circle circle : circles) {
            if (circle.growing) {
                if (circle.edges()) {
                    circle.growing = false;
                } else {
                    for (Circle other : circles) {
                        if (circle != other) {
                            float d = dist(circle.x, circle.y, other.x, other.y);
                            if (d - 2 < circle.r + other.r) {
                                circle.growing = false;
                            }
                        }
                    }
                }
            }
            circle.show();
            circle.grow();
        }
    }

    Circle getCircle() {

        int r = (int) random(0, spots.size());
        PVector spot = spots.get(r);
        float x = spot.x;
        float y = spot.y;

        boolean valid = true;
        for (Circle c : circles) {
            float d = dist(x, y, c.x, c.y);
            if (d < c.r + 2) {
                valid = false;
                break;
            }
        }

        if (valid) {
            return new Circle(this, x, y);
        } else {
            return null;
        }
    }
}
