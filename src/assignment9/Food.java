package assignment9;

import java.util.Random;
import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

    public static double FOOD_SIZE = 0.02;
    private double x, y;

    /**
     * Creates a new Food at a random location
     */
    public Food() {
        generateNewPosition();
    }

    public void generateNewPosition() {
        Random rand = new Random();
        x = rand.nextDouble() * (1 - FOOD_SIZE) + FOOD_SIZE / 2; // makes sure the generated value is within the bounds
        y = rand.nextDouble() * (1 - FOOD_SIZE) + FOOD_SIZE / 2;
    }

    /**
     * Draws the Food
     */
    public void draw() {
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledCircle(x, y, FOOD_SIZE / 2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}