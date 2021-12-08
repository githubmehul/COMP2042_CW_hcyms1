package model;
import controller.BallController;
import controller.BrickController;
import controller.CrackController;
import controller.WallController;


import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


public class FireBrickModel extends BrickController {
    private static Color INNER_COLOR = new Color(247, 55, 24);
    private static Color BORDER_COLOR = new Color(255, 203, 0);
    private static final int FAST_STRENGTH = 2;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    private CrackController Crack;
    private Shape brickShape;

    public FireBrickModel(Point point, Dimension size){
        super(point,size,FAST_STRENGTH);
        Crack = new CrackController(this , DEF_CRACK_DEPTH, DEF_STEPS);
        brickShape = super.getBrickShape();
    }

    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            Crack.makeCrack(point,dir);
            //update the brick
            updateBrick();
            brickimpact();
        }
        return super.isBroken();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.getBrickShape();
    }

    public void brickimpact() {
        if (super.isBroken()) {
            LevelModel.getWall().setBallXSpeed(7);
            LevelModel.getWall().setBallYSpeed(-7);
        }
    }
    @Override
    protected Color setBrickInnerColor() {
        return INNER_COLOR;
    }

    @Override
    protected Color setBrickBorderColor() {
        return BORDER_COLOR;
    }
    /**
     * Draw cracks on the Brick if it's not broken.
     */
    private void updateBrick(){
        //if the brick is struck
        if(!super.isBroken()){
            //Create the Crack on the Brick
            GeneralPath gp = Crack.draw();
            gp.append(super.getBrickShape(),false);
            //Update the BrickFace to the Crack
            brickShape = gp;
        }
    }
    /**
     * Calls the parent's {@code repair()} method and resets the cracks.
     */
    public void repair(){
        //Call the repair method in brickcontroller
        super.repair();
        //reset the crack
        Crack.reset();
        //update the brickface
        brickShape = super.getBrickShape();
    }

}