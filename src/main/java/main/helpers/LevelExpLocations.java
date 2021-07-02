package main.helpers;

public enum LevelExpLocations {

    MAPLE_LEGENDS(0, 0, 0, 0, 745, 918, 90, 12),
    BLACK_DESERT(59, 37, 422, 33, 43, 73, 71, 21);

    public int levelX;
    public int levelY;
    public int levelWidth;
    public int levelHeight;

    public int expX;
    public int expY;
    public int expWidth;
    public int expHeight;

    LevelExpLocations(int levelX, int levelY, int levelWidth, int levelHeight, int expX, int expY, int expWidth,
                      int expHeight) {
        this.levelX = levelX;
        this.levelY = levelY;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;

        this.expX = expX;
        this.expY = expY;
        this.expWidth = expWidth;
        this.expHeight = expHeight;
    }


}
