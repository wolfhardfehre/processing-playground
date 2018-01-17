package circle_packing;

import processing.core.PApplet;

public class Circle {
    private final PApplet packing;
    float x, y, r;
    boolean growing = true;

    Circle(PApplet packing, float x, float y) {
        this.packing = packing;
        this.x = x;
        this.y = y;
        r = 1;
    }

    void grow() {
        if (growing) {
            r = r + 1;
        }
    }

    boolean edges() {
        return x + r > packing.width || x - r < 0 ||
                y + r > packing.height || y - r < 0;
    }

    void show() {
        packing.stroke(255);
        packing.strokeWeight(2);
        packing.noFill();
        packing.ellipse(x, y, r*2, r*2);
    }
}
