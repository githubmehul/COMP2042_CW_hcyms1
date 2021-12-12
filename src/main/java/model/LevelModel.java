package model;

import controller.BrickController;
import controller.WallController;

import java.awt.*;

/**
 * The LevelModel class creates levels for the Game. I separated these
 * methods from the WallController class  the Game
 * should not be responsible for creating the levels.
 * This class contains simple implementations of next level as well.
 */
public class LevelModel {

    private static final int LEVELS_COUNT = 6;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int FIRE = 4;
    private static final int ICE = 5;
    private static WallController wall;
    private final BrickController[][] levels;
    private int level = 0;

    public LevelModel(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, WallController wall) {
        levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
        LevelModel.wall = wall;
    }

    public static WallController getWall() {
        return wall;
    }

    /**
     * Creates the 1st Level for The ClayBrickModel
     *
     * @param drawArea       - Area of Wall
     * @param brickCnt       - Brick Count
     * @param lineCnt        - Line Count
     * @param brickSizeRatio - Ratio of Brick
     * @return tmp
     */
    private BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, LevelModel.CLAY);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrickModel(p, brickSize);
        }
        return tmp;

    }

    /**
     * Implement the Chessboard Pattern for the levels after the 1st level
     *
     * @param drawArea       - Area of Wall
     * @param brickCnt       - Brick Count
     * @param lineCnt        - Line Count
     * @param brickSizeRatio - Ratio of Brick
     * @param typeA          - Brick Type A
     * @param typeB          - Brick Type B
     * @return tmp
     */

    private BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }


    /**
     * Uses the array to call the assigned methods to create the Level Layout
     *
     * @param drawArea            - Area of Wall
     * @param brickCount          - Brick Count
     * @param lineCount           - Line Count
     * @param brickDimensionRatio - Ratio of Brick
     * @return levels
     */
    private BrickController[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        BrickController[][] tmp = new BrickController[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio);
        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        tmp[4] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, FIRE, CLAY);
        tmp[5] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, FIRE, ICE);
        return tmp;
    }

    /**
     * Calls the Make Brick when the Brick Type is called.
     *
     * @param point - Coordinate of Brick
     * @param size  - Size of Brick
     * @param type  - Type of Brick
     * @return out - Shape of Brick
     */
    private BrickController makeBrick(Point point, Dimension size, int type) {
        return switch (type) {
            case CLAY -> new ClayBrickModel(point, size);
            case STEEL -> new SteelBrickModel(point, size);
            case CEMENT -> new CementBrickModel(point, size);
            case FIRE -> new FireBrickModel(point, size);
            case ICE -> new IceBrickModel(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }

    /**
     * Sets the next level
     */
    public void nextLevel() {
        wall.setBricks(levels[level++]);
        wall.setBrickCount(getWall().getBricks().length);
    }

    /**
     * Checks if there is a next level
     *
     * @return boolean
     */
    public boolean hasLevel() {
        return level < levels.length;
    }

    /**
     * Returns the next level
     *
     * @return level number
     */
    public int getLevel() {
        return level;
    }


}