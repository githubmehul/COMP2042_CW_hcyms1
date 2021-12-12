package model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {

    @Test
    void makeRectangle() {
        assertEquals(new Rectangle(new Point(300 , 506), new Dimension(1, 1)), PlayerModel.makeRectangle(1 , 1));
    }


    @Test
    void setInnerColor() {
        assertEquals(Color.GREEN.darker().darker(), PlayerModel.getBorderColor());
    }

    @Test
    void setBorderColor() {
        assertEquals(Color.GREEN, PlayerModel.getInnerColor());
    }
}