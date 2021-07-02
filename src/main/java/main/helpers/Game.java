package main.helpers;

import org.json.JSONObject;

import java.io.IOException;

public enum Game {

    MAPLE_LEGENDS("Maple Legends", "MapleLegends", LevelExpLocations.MAPLE_LEGENDS, ExpType.INTEGER, "mapleLegends/"),
    BLACK_DESERT("Black Desert", "Black Desert", LevelExpLocations.BLACK_DESERT, ExpType.PERCENTAGE);

    public String name;
    public LevelExpLocations levelExpLocations;
    public String windowName;
    public ExpType expType;
    public JSONObject expTableJson;

    Game(String name, String windowName, LevelExpLocations levelExpLocations, ExpType expType) {
        this.name = name;
        this.levelExpLocations = levelExpLocations;
        this.windowName = windowName;
        this.expType = expType;
    }

    Game(String name, String windowName, LevelExpLocations levelExpLocations, ExpType expType,
         String gameResourcesFolder) {
        this.name = name;
        this.levelExpLocations = levelExpLocations;
        this.windowName = windowName;
        this.expType = expType;
        try {
            this.expTableJson = ExpTableCreator.createExpTable(gameResourcesFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
