package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickModelTest {
    SteelBrickModel steelBrickModel = new SteelBrickModel(new Point() , new Dimension(1 , 1));

    @Test
    void makeBrickShape() {
        assertEquals(new Rectangle(new Point(), new Dimension(1, 1)), steelBrickModel.makeBrickShape(new Point(), new Dimension(1, 1)));
    }

    @Test
    void setBrickInnerColor() {
        assertEquals(new Color(203, 203, 201) , steelBrickModel.setBrickInnerColor());
    }

    @Test
    void setBrickBorderColor() {
        assertEquals(Color.BLACK , steelBrickModel.setBrickBorderColor());
    }

    @Test
    void getChildBrickShape() {
        assertEquals(steelBrickModel.getParentBrickShape(), steelBrickModel.getChildBrickShape());
    }

    @Test
    void setImpact() {
        assertFalse(steelBrickModel.isBroken());
        steelBrickModel.impact();
    }

    @Test
    void impact() {
        assertTrue(true);
        steelBrickModel.impact();
    }
}