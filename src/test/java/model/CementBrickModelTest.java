package model;
import java.awt.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CementBrickModelTest {
    CementBrickModel cementBrick = new CementBrickModel(new Point(), new Dimension(1,1));
    @Test
    void makeBrickShape() {
        assertEquals(new Rectangle(new Point(), new Dimension(1,1)) , cementBrick.makeBrickShape( new Point(), new Dimension(1 , 1)));
    }

    @Test
    void setBrickBorderColor() {
        assertEquals(new Color(217, 199, 175), cementBrick.getBrickBorderColor());
    }

    @Test
    void setBrickInnerColor() {
        assertEquals(new Color(147, 147, 147), cementBrick.getBrickInnerColor());
    }

    @Test
    void setImpact() {
        cementBrick.impact();
        assertFalse(cementBrick.isBroken());
        cementBrick.impact();
        assertTrue(cementBrick.isBroken());
    }

    @Test
    void getChildBrickShape() {
    }

    @Test
    void repair() {
        cementBrick.impact();
        cementBrick.repair();
        assertEquals(2, CementBrickModel.getBrickStrength());
    }
}