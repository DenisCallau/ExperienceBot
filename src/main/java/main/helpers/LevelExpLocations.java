package main.helpers;

public enum LevelExpLocations {

    MAPLE_LEGENDS(0, 0, 0, 0, 745, 918, 90, 12, 0, "%", 0, 2),
    BLACK_DESERT(60, 37, 38, 33, 43, 73, 71, 21, 0, "%", 0, 2);

    public int levelX;
    public int levelY;
    public int levelWidth;
    public int levelHeight;

    public int expX;
    public int expY;
    public int expWidth;
    public int expHeight;

    public int firstIndexExp;
    public String lastIndexExp;
    public int firstLevelIndex;
    public int lastLevelIndex;

    LevelExpLocations(int levelX, int levelY, int levelWidth, int levelHeight, int expX, int expY, int expWidth,
                      int expHeight, int firstIndexExp, String lastIndexExp, int firstLevelIndex, int lastLevelIndex) {
        this.levelX = levelX;
        this.levelY = levelY;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;

        this.expX = expX;
        this.expY = expY;
        this.expWidth = expWidth;
        this.expHeight = expHeight;

        this.firstIndexExp = firstIndexExp;
        this.lastIndexExp = lastIndexExp;
        this.firstLevelIndex = firstLevelIndex;
        this.lastLevelIndex = lastLevelIndex;
    }


}
