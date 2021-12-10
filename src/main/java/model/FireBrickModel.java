package model;
import controller.BrickController;


import java.awt.*;
import java.awt.geom.Point2D;


public class FireBrickModel extends BrickController {
    private static Color INNER_COLOR = new Color(247, 55, 24);
    private static Color BORDER_COLOR = new Color(255, 203, 0);
    private static final int FAST_STRENGTH = 2;

    public FireBrickModel(Point point, Dimension size){
        super(point,size,FAST_STRENGTH);
    }

    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            brickimpact();
        }
        return super.isBroken();
    }

    @Override
    protected Shape makeBrickShape(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getChildBrickShape() {
        return super.getParentBrickShape();
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

}