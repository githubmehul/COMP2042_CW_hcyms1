package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickModelTest {
    ClayBrickModel clayBrickModel = new ClayBrickModel(new Point(), new Dimension(1, 1));

    @Test
    void makeBrickShape() {
        assertEquals(new Rectangle(new Point(), new Dimension(1, 1)), clayBrickModel.makeBrickShape(new Point(), new Dimension(1, 1)));
    }

    @Test
    void setBrickInnerColor() {
        assertEquals(new Color(178, 34, 34).darker(), clayBrickModel.getBrickInnerColor());
    }

    @Test
    void setBrickBorderColor() {
        assertEquals(Color.GRAY, clayBrickModel.getBrickBorderColor());
    }

    @Test
    void getChildBrickShape() {
        assertEquals(clayBrickModel.getParentBrickShape(), clayBrickModel.getChildBrickShape());
    }

    @Test
    void setImpact() {
        assertFalse(clayBrickModel.isBroken());
        clayBrickModel.impact();
        assertTrue(clayBrickModel.isBroken());
    }
}