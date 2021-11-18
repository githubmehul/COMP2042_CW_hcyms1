package controller;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * Crack Controller is responsible for providing the Crack Implementation
 */
public class CrackController {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private BrickController brick;
    private GeneralPath crack;

    private int crackDepth;
    private int steps;


    /**
     * Sets the General Path , with the crackDepth and steps
     * @param crackDepth
     * @param steps
     */
    public CrackController( int crackDepth, int steps) {
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
    }
    /**
     * Method to draw the Crack
     * @return crack
     */
    public GeneralPath draw() {
        return crack;
    }
    /**
     * Method to reset the crack
     */
    public void reset() {
        crack.reset();
    }

    /**
     *Sets the Start and End Location of the Crack
     * @param point
     * @param direction
     */
    public void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.getBrickFace().getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();

        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);

                break;

        }
    }

    /**
     * Crack Path Implementation
     * @param start
     * @param end
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();
        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;

        int bound = crackDepth;
        int jump = bound * 5;

        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump, JUMP_PROBABILITY);

            path.lineTo(x, y);

        }
        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return BrickController.getRnd().nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {

        if (BrickController.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    private Point makeRandomPoint(Point from, Point to, int direction) {
        Point out = new Point();
        int pos;
        switch (direction) {
            case HORIZONTAL:
                pos = BrickController.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = BrickController.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }

}