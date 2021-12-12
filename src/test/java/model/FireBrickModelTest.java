package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FireBrickModelTest {
    FireBrickModel fireBrickModel = new FireBrickModel(new Point() , new Dimension(1 , 1));

    @Test
    void setImpact() {
        fireBrickModel.impact();
        assertFalse(fireBrickModel.isBroken());
        fireBrickModel.impact();
        assertTrue(fireBrickModel.isBroken());
    }

    @Test
    void makeBrickShape() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)) , fireBrickModel.makeBrickShape( new Point(), new Dimension(1 , 1)));
    }

    @Test
    void getChildBrickShape() {
        assertEquals(fireBrickModel.getParentBrickShape(),fireBrickModel.getChildBrickShape());
    }

    @Test
    void brickImpact() {
        fireBrickModel.impact();
        assertFalse(fireBrickModel.isBroken());
    }

    @Test
    void setBrickInnerColor() {
        assertEquals(new Color(247, 55, 24), fireBrickModel.getBrickInnerColor());
    }

    @Test
    void setBrickBorderColor() {
        assertEquals(new Color(255, 203, 0), fireBrickModel.getBrickBorderColor());
    }
}