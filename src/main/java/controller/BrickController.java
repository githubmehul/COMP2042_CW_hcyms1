package controller;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * The abstract class Brick provides a 'template' for the different types of
 * Bricks in the game. It is responsible for defining its shape, looks and
 * location
 */
abstract public class BrickController {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private static Random rnd;

    private String name;
    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    public BrickController(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        setRnd(new Random());
        broken = false;
        this.name = name;
        setBrickFace(makeBrickFace(pos,size));
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public abstract Shape getBrick();



    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


    public final int findImpact(BallController b){
        if(broken)
            return 0;
        int out  = 0;
        if(getBrickFace().contains(b.getRightLocation()))
            out = LEFT_IMPACT;
        else if(getBrickFace().contains(b.getLeftLocation()))
            out = RIGHT_IMPACT;
        else if(getBrickFace().contains(b.getUpLocation()))
            out = DOWN_IMPACT;
        else if(getBrickFace().contains(b.getDownLocation()))
            out = UP_IMPACT;
        return out;
    }

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }


    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    public static Random getRnd() {
        return rnd;
    }

    public static void setRnd(Random rnd) {
        BrickController.rnd = rnd;
    }
}




