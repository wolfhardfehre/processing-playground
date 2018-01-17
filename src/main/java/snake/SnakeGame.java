package snake;

import processing.core.PApplet;
import processing.core.PVector;


public class SnakeGame extends PApplet {

    private float scale = 10;
    private int direction = RIGHT;
    private Snake snake;
    private PVector food;
    private int cols;
    private int rows;

    public void settings(){
        size(600, 600);
    }

    public void setup() {
        frameRate(10);
        snake = new Snake(this, scale);
        cols = floor(width/scale);
        rows = floor(height/scale);
        placeFood();
    }

    public void placeFood() {
        food = new PVector(floor(random(cols)), floor(random(rows)));
        food.mult(scale);
    }

    public void keyPressed() {
        switch (keyCode) {
            case UP:
                if (direction != DOWN) {
                    snake.direction(0, -1);
                    direction = UP;
                }
                break;
            case DOWN:
                if (direction != UP) {
                    snake.direction(0, 1);
                    direction = DOWN;
                }
                break;
            case RIGHT:
                if (direction != LEFT) {
                    snake.direction(1, 0);
                    direction = RIGHT;
                }
                break;
            case LEFT:
                if (direction != RIGHT) {
                    snake.direction(-1, 0);
                    direction = LEFT;
                }
                break;
        }
    }

    public void draw() {
        background(51);
        snake.update();
        snake.show();

        if (snake.consume(food)) {
            snake.grow();
            placeFood();
        }

        fill(255, 0, 100);
        rect(food.x, food.y, scale, scale);
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { SnakeGame.class.getName() });
    }
}
