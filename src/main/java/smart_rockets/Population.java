package smart_rockets;


import java.util.ArrayList;
import java.util.List;

public class Population {

    private final Animation animation;
    Rocket[] rockets;
    int popSize = 25;
    private List<Rocket> matingPool;

    public Population(Animation animation) {
        this.animation = animation;
        rockets = new Rocket[popSize];
        for (int i=0; i<popSize; i++) {
            rockets[i] = new Rocket(animation);
        }
    }

    public void evaluate() {
        float maxFit = 0;
        for (Rocket rocket : rockets) {
            rocket.evaluate();
            if (rocket.fitness > maxFit) {
                maxFit = rocket.fitness;
            }
        }

        for (Rocket rocket : rockets) {
            rocket.fitness /= maxFit;
        }

        this.matingPool = new ArrayList<>();
        for (Rocket rocket : rockets) {
            float n = rocket.fitness * 100;
            for (int j=0; j<n; j++) {
                matingPool.add(rocket);
            }
        }
    }

    public void selection() {
        Rocket parentA = matingPool.get((int) animation.random(matingPool.size()));
        Rocket parentB = matingPool.get((int) animation.random(matingPool.size()));
        Rocket child = parentA.crossover(parentB);

    }

    public void run() {
        for (int i=0; i<this.popSize; i++) {
            rockets[i].update();
            rockets[i].show();
        }
    }
}
