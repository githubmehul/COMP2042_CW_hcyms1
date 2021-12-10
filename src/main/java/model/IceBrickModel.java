package model;
import controller.BrickController;
import java.awt.*;
import java.awt.geom.Point2D;


public class IceBrickModel extends BrickController {
    private static Color INNER_COLOR = new Color(2, 28, 118);
    private static Color BORDER_COLOR = new Color(5, 207, 255);
    private static final int FAST_STRENGTH = 1;

    public IceBrickModel(Point point, Dimension size){
        super(point,size,FAST_STRENGTH);
    }

    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
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
        super.impact();
        if (super.isBroken()) {
            LevelModel.getWall().setBallXSpeed(2);
            LevelModel.getWall().setBallYSpeed(-2);
        }
    }
    @Override
    protected Color setBrickInnerColor() {
        return INNER_COLOR;
    }

    @Override
    protected Color setBrickBorderColor() {
        return BORDER_COLOR;}

}