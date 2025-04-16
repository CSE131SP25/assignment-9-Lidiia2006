package assignment9;

import java.util.LinkedList;

public class Snake {

    private static double SEGMENT_SIZE = 0.02;
    private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
    private LinkedList<BodySegment> segments;
    private double deltaX;
    private double deltaY;

    public Snake() {
        segments = new LinkedList<>();
        deltaX = MOVEMENT_SIZE;
        deltaY = 0;
        // Start with one segment at the center of the screen
        segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
    }

    public void changeDirection(int direction) {
        if(direction == 1) { //up
            deltaY = MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 2) { //down
            deltaY = -MOVEMENT_SIZE;
            deltaX = 0;
        } else if (direction == 3) { //left
            deltaY = 0;
            deltaX = -MOVEMENT_SIZE;
        } else if (direction == 4) { //right
            deltaY = 0;
            deltaX = MOVEMENT_SIZE;
        }
    }

    /**
     * Moves the snake by updating the position of each of the segments
     * based on the current direction of travel
     */
    public void move() {
        double newX = segments.getFirst().getX() + deltaX;
        double newY = segments.getFirst().getY() + deltaY;

        // Move body segments by updating their positions
        for (int i = segments.size() - 1; i > 0; i--) {
            BodySegment current = segments.get(i);
            BodySegment previous = segments.get(i - 1);
            current.setPosition(previous.getX(), previous.getY());
        }

        // Move the head
        segments.getFirst().setPosition(newX, newY);
    }

    /**
     * Draws the snake by drawing each segment
     */
    public void draw() {
        for (BodySegment segment : segments) {
            segment.draw();
        }
    }

    /**
     * The snake attempts to eat the given food, growing if it does so successfully
     * @param f the food to be eaten
     * @return true if the snake successfully ate the food
     */
    public boolean eatFood(Food f) {
        BodySegment head = segments.getFirst();
        double distance = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + Math.pow(head.getY() - f.getY(), 2));
        if (distance <= SEGMENT_SIZE) {
            // Add a new segment at the tail's position
            BodySegment tail = segments.getLast();
            segments.add(new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE));
            return true;
        }
        return false;
    }

    /**
     * Returns true if the head of the snake is in bounds
     * @return whether or not the head is in the bounds of the window
     */
    public boolean isInbounds() {
        BodySegment head = segments.getFirst();
        return head.getX() >= 0 && head.getX() <= 1 && head.getY() >= 0 && head.getY() <= 1;
    }

    public int snakeSize() {
        return segments.size();
    }
}