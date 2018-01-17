package rain_curtain;


import processing.core.PApplet;

import static processing.core.PApplet.sq;


public class Star {

    private final Starfield starfield;
    private final float sqrInner, sqrOuter;
    private float x, y, z, pz;

    public Star(Starfield starfield, int inner, int outer) {
        this.starfield = starfield;
        this.sqrInner = sq(inner);
        this.sqrOuter = sq(outer);
        this.x = starfield.random(-starfield.width, starfield.width);
        this.y = starfield.random(-starfield.height, starfield.height);
        this.z = starfield.random(starfield.width);
        this.pz = z;
    }

    public void update() {
        z = z - starfield.speed;
        if (z < 1) {
            z = starfield.width;
            this.x = starfield.random(-starfield.width, starfield.width);
            this.y = starfield.random(-starfield.height, starfield.height);
            this.pz = z;
        }
    }

    public void show() {
        starfield.fill(starfield.hue, 255,255);
        starfield.noStroke();

        float sx = PApplet.map(x / z, 0, 1, 0, starfield.width);
        float sy = PApplet.map(y / z, 0, 1, 0, starfield.height);

        float r = PApplet.map(z, 0, starfield.width, 16, 0);

        float sqrRadius = sq(sx) + sq(sy);
        if (sqrRadius > sqrInner && sqrRadius < sqrOuter) {
            starfield.ellipse(sx, sy, r, r);
            /*
            float px = PApplet.map(x / pz, 0, 1, 0, starfield.width);
            float py = PApplet.map(y / pz, 0, 1, 0, starfield.height);
            starfield.stroke(starfield.hue, 255, 255);
            starfield.line(px, py, sx, sy);
            */
        }

        pz = z;
    }
}
