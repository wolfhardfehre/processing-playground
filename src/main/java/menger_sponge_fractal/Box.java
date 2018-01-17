package menger_sponge_fractal;


import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Box {

    private final float r;
    private final MengerSponge sponge;
    private PVector pos;

    public Box(MengerSponge sponge, float x, float y, float z, float r) {
        this.sponge = sponge;
        this.pos = new PVector(x, y, z);
        this.r = r;
    }

    public List<Box> generate() {
        List<Box> boxes = new ArrayList<>();
        for (int x = -1; x<2; x++) {
            for (int y = -1; y<2; y++) {
                for (int z = -1; z<2; z++) {
                    int sum = PApplet.abs(x) + PApplet.abs(y) + PApplet.abs(z);
                    if (sum > 1) {
                        float nr = r / 3;
                        Box b = new Box(sponge, pos.x + x * nr, pos.y + y * nr, pos.z + z * nr, nr);
                        boxes.add(b);
                    }
                }
            }
        }
        return boxes;
    }

    public void show() {
        sponge.pushMatrix();
        sponge.translate(pos.x, pos.y, pos.z);
        sponge.stroke(200);
        sponge.fill(255);
        sponge.box(r);
        sponge.popMatrix();
    }
}
