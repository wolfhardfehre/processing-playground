package starfield;


import processing.core.PApplet;


public class Star {

    private final Starfield starfield;
    float x, y, z, pz;

    public Star(Starfield starfield) {
        this.starfield = starfield;
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
        starfield.fill(255);
        starfield.noStroke();

        float sx = PApplet.map(x / z, 0, 1, 0, starfield.width);
        float sy = PApplet.map(y / z, 0, 1, 0, starfield.height);

        float r = PApplet.map(z, 0, starfield.width, 16, 0);

        starfield.ellipse(sx, sy, r, r);

        float px = PApplet.map(x / pz, 0, 1, 0, starfield.width);
        float py = PApplet.map(y / pz, 0, 1, 0, starfield.height);

        pz = z;

        starfield.stroke(255);
        starfield.line(px, py, sx, sy);
    }
}
