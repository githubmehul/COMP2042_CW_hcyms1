package controller;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * Crack Controller is responsible for providing the Bricks the 'Crack' Feature.
 * Required by Cement Brick.
 */
public class CrackController {

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;
    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;
    private final BrickController brickController;
    private final GeneralPath crackPath;

    private final int crackDepth;
    private final int crackNoise;

    /**
     * Sets the General Path , with the crackDepth and crackNoise
     *
     * @param brickController - Calls in Brick Controller Object
     * @param crackDepth      - Crack Depth
     * @param crackNoise      - The Steps in the Crack (sort of like graph noise)
     */
    public CrackController(BrickController brickController, int crackDepth, int crackNoise) {
        this.brickController = brickController;
        crackPath = new GeneralPath();
        this.crackDepth = crackDepth;
        this.crackNoise = crackNoise;
    }

    /**
     * Method to draw the Crack
     *
     * @return crack
     */
    public GeneralPath draw() {
        return crackPath;
    }

    /**
     * Method to reset the crack
     */
    public void reset() {
        crackPath.reset();
    }

    /**
     * Sets the Start and End Location of the Crack
     *
     * @param impactPoint - The Point of Impact
     * @param direction   - The direction of the crack
     */
    public void makeCrack(Point2D impactPoint, int direction) {
        Rectangle bounds = brickController.getParentBrickShape().getBounds();

        Point impactCrack = new Point((int) impactPoint.getX(), (int) impactPoint.getY());
        Point startPoint = new Point();
        Point endPoint = new Point();

        switch (direction) {
            case LEFT:
                startPoint.setLocation(bounds.x + bounds.width, bounds.y);
                endPoint.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(startPoint, endPoint, VERTICAL);
                makeCrack(impactCrack, tmp);

                break;
            case RIGHT:
                startPoint.setLocation(bounds.getLocation());
                endPoint.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(startPoint, endPoint, VERTICAL);
                makeCrack(impactCrack, tmp);
                break;
            case UP:
                startPoint.setLocation(bounds.x, bounds.y + bounds.height);
                endPoint.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(startPoint, endPoint, HORIZONTAL);
                makeCrack(impactCrack, tmp);
                break;
            case DOWN:
                startPoint.setLocation(bounds.getLocation());
                endPoint.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(startPoint, endPoint, HORIZONTAL);
                makeCrack(impactCrack, tmp);
                break;
        }
    }

    /**
     * Method is responsible for drawing the Crack Model from the Start till the end.
     *
     * @param startCrack - Start Location of Crack
     * @param endCrack   - End Location of Crack
     */
    protected void makeCrack(Point startCrack, Point endCrack) {
        GeneralPath path = new GeneralPath();
        path.moveTo(startCrack.x, startCrack.y);
        double w = (endCrack.x - startCrack.x) / (double) crackNoise;
        double h = (endCrack.y - startCrack.y) / (double) crackNoise;
        int bound = crackDepth;
        int jump = bound * 5;
        double x, y;
        for (int i = 1; i < crackNoise; i++) {
            x = (i * w) + startCrack.x;
            y = (i * h) + startCrack.y + randomInBounds(bound);
            if (inMiddle(i, crackNoise))
                y += jumps(jump);
            path.lineTo(x, y);
        }
        path.lineTo(endCrack.x, endCrack.y);
        crackPath.append(path, true);
    }

    /**
     * Shapes the Crack Bounds
     *
     * @param bound - the crackDepth
     * @return A random value to create the crack depth
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return BrickController.getRnd().nextInt(n) - bound;
    }

    /**
     * Responsible for the alignment of the crack
     *
     * @param i         - an iterated value
     * @param divisions - The crackNoise
     * @return if i>low and i< up - used in the if statement in makeCrack.
     */
    private boolean inMiddle(int i, int divisions) {
        int low = (CrackController.CRACK_SECTIONS / divisions);
        int up = low * (divisions - 1);
        return (i > low) && (i < up);
    }

    /**
     * Jump Method is responsible for returning the randomInBounds based on the jump probability
     *
     * @param bound  - The CrackDepth
     * @return - Calls the randomInBounds Method
     */
    private int jumps(int bound) {
        if (BrickController.getRnd().nextDouble() > CrackController.JUMP_PROBABILITY)
            return randomInBounds(bound);
        return 0;

    }

    /**
     * Makes a Random Point from and to the end of the Crack in the specified Direction
     *
     * @param from      - From the point of impact
     * @param to        - To the end of impact
     * @param direction - Either Horizontal or Vertical
     * @return out
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {
        Point out = new Point();
        int pos;
        switch (direction) {
            case HORIZONTAL -> {
                pos = BrickController.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
            }
            case VERTICAL -> {
                pos = BrickController.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
            }
        }
        return out;
    }

}