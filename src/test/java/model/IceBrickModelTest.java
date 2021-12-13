package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class IceBrickModelTest {
    IceBrickModel iceBrickModel = new IceBrickModel(new Point() , new Dimension(1, 1));

    @Test
    void setImpact() {
        assertFalse(iceBrickModel.isBroken());
        iceBrickModel.impact();
    }

    @Test
    void makeBrickShape() {
        assertEquals(new Rectangle(new Point(), new Dimension(1, 1)), iceBrickModel.makeBrickShape(new Point(), new Dimension(1, 1)));
    }

    @Test
    void getChildBrickShape() {
        assertEquals(iceBrickModel.getParentBrickShape(), iceBrickModel.getChildBrickShape());
    }

    @Test
    void brickImpact() {
        iceBrickModel.impact();
        assertFalse(iceBrickModel.isBroken());
    }

    @Test
    void setBrickInnerColor() {
        assertEquals(new Color(2, 28, 118), iceBrickModel.getBrickInnerColor());
    }

    @Test
    void setBrickBorderColor() {
        assertEquals(new Color(5, 207, 255), iceBrickModel.getBrickBorderColor());
    }
}