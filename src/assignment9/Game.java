package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
    private Snake snake;
    private Food food;

    public Game() {
        StdDraw.enableDoubleBuffering();
        snake = new Snake();
        food = new Food();
    }

    public void play() {
        showIntroScreen();

        while (snake.isInbounds()) {
            int dir = getKeypress();
            if (dir != -1) {
                snake.changeDirection(dir);
            }
            snake.move();

            if (snake.eatFood(food)) {
                food.generateNewPosition();
            }

            updateDrawing();
            StdDraw.pause(50);
        }

        showGameOverScreen();
    }

    private int getKeypress() {
        if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            return 1;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            return 2;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            return 3;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            return 4;
        } else {
            return -1;
        }
    }

    /**
     * Clears the screen, draws the snake and food, pauses, and shows the content
     */
    private void updateDrawing() {
        StdDraw.clear();
        snake.draw();
        food.draw();
        StdDraw.text(0.9, 0.95, "Score: " + snake.snakeSize());
        StdDraw.show();
    }

    private void showIntroScreen() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLUE);

        // Draw title
        StdDraw.text(0.5, 0.7, "MEGA SNAKE GAME UPDATED");

        // Draw instructions
        StdDraw.text(0.5, 0.5, "Use THE W, A, S, D KEYS TO control");
        StdDraw.text(0.5, 0.4, "Eat the red food to grow");

        // Draw start prompt
        StdDraw.text(0.5, 0.3, "Press any key to start!");

        StdDraw.show();

        // Wait for the user to press any key to start
        while (!StdDraw.hasNextKeyTyped()) {
            StdDraw.pause(100);
        }

        // Clear the key buffer
        StdDraw.nextKeyTyped();
    }

    private void showGameOverScreen() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.RED);

        // Draw game over message
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.text(0.5, 0.4, "Score: " + snake.snakeSize());

        StdDraw.show();
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}