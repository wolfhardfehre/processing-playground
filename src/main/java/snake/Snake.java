package snake;


import processing.core.PApplet;
import processing.core.PVector;

import java.util.LinkedList;
import java.util.Queue;

public class Snake {

    private final SnakeGame game;
    private float scale;
    private float x = 0;
    private float y = 0;
    private float xs = 1;
    private float ys = 0;
    private int total = 1;
    private Queue<PVector> snake;

    public Snake(SnakeGame game, float scale) {
        this.game = game;
        this.scale = scale;
        this.snake = new LinkedList<>();
    }

    public void update() {
        x += xs * scale;
        y += ys * scale;

        x = PApplet.constrain(x, 0, game.width - scale);
        y = PApplet.constrain(y, 0, game.height - scale);

        death();

        PVector head = new PVector(x, y);
        snake.add(head);

        if (snake.size() > total) {
            snake.remove();
        }
    }

    public void show() {
        for (PVector pos : snake) {
            game.fill(255);
            game.rect(pos.x, pos.y, scale, scale);
        }
    }

    public void direction(int xs, int ys) {
        this.xs = xs;
        this.ys = ys;
    }

    public boolean consume(PVector pos) {
        float d = PApplet.dist(x, y, pos.x, pos.y);
        return d < 1;
    }

    public void grow() {
        total++;
    }

    public void death() {
        for (PVector joint : snake) {
            float d = PApplet.dist(joint.x, joint.y, x, y);
            if (d < 1) {
                game.noLoop();
                System.out.println(">>>> Game Over <<<<");
            }
        }
    }
}
