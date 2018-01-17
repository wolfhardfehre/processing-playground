package smart_rockets;


import processing.core.PVector;


public class Dna {

    PVector[] genes;

    public Dna(Animation animation) {
        this.genes = new PVector[animation.lifeSpan];
        for (int i=0; i<animation.lifeSpan; i++) {
            float x = animation.random(-0.1f, 0.1f);
            float y = animation.random(-0.1f, 0.1f);
            this.genes[i] = new PVector(x, y);
        }
    }

    public Dna(PVector[] genes) {
        this.genes = genes;
    }
}
