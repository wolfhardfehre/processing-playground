package smart_rockets;


import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;


public class Rocket {

    private final Animation animation;
    private final Dna dna;
    PVector position;
    PVector velocity;
    PVector acceleration;
    public float fitness;

    public Rocket(Animation animation) {
        this(animation, new Dna(animation));
    }

    public Rocket(Animation animation, Dna dna) {
        this.animation = animation;
        this.position = new PVector(animation.width/2, animation.height);
        this.velocity = new PVector();
        this.acceleration = new PVector();
        this.dna = dna;
    }

    public void applyForce(PVector force) {
        this.acceleration.add(force);
    }

    public void update() {
        applyForce(dna.genes[animation.count%animation.lifeSpan]);
        animation.count++;
        velocity.add(acceleration);
        position.add(velocity);
        acceleration.mult(0);
    }

    public void show() {
        animation.pushMatrix();
        animation.noStroke();
        animation.fill(255, 150);
        animation.translate(position.x, position.y);
        animation.rotate(velocity.heading());
        animation.rectMode(PConstants.CENTER);
        animation.rect(0, 0, 50, 10);
        animation.popMatrix();
    }

    public void evaluate() {
        float d = PApplet.dist(position.x, position.y,
                animation.target.x, animation.target.y);
        this.fitness = PApplet.map(d, 0, animation.width, animation.width, 0);
    }

    public Rocket crossover(Rocket partner) {
        int length = dna.genes.length;
        int mid = PApplet.floor(animation.random(length));
        PVector[] newGenes = new PVector[length];
        for (int i=0; i<length; i++) {
            newGenes[i] = i > mid ? dna.genes[i] : partner.dna.genes[i];
        }
        return new Rocket(animation, new Dna(newGenes));
    }
}
