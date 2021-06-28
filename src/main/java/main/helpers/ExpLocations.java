package main.helpers;

public enum ExpLocations {

    MAPLE_STORY(745, 918, 90, 12),
    BLACK_DESERT(43, 73, 71, 21);

    public int x;
    public int y;
    public int width;
    public int height;

    ExpLocations(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


}
