package main;

import main.helpers.ExpType;
import main.helpers.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Calculator {

    protected float expGained;
    protected float expPerHour;
    protected float timeToNextLevel;
    protected long now;
    protected float hoursDiff;
    private float totalExpToNextLevel;
    private float expToNextLevel;

    private static final Logger log = LogManager.getLogger(Calculator.class);

    public float expPerHour(Hunt hunt, float currentExp) {
        log.debug("Started exp/hour calculation");
        now = System.currentTimeMillis();
        expGained = (currentExp + ((hunt.getCurrentLevel() - hunt.getInitialLevel()) * 100)) - hunt.getInitialExp();
        hunt.setExpGained(expGained);

        long timeDiff = now - hunt.getInitialTime();

        try {
            float seconds = timeDiff / 1000;
            float minutes = seconds / 60;
            hoursDiff = minutes / 60;
            expPerHour = (expGained / hoursDiff);
            log.debug("Finished exp/hour calculation: " + expPerHour);
            return expPerHour;
        } catch (ArithmeticException e) {
            log.error("Invalid data for calculations");
            return 0;
        }
    }


    public float timeToNextLevel(float currentExp, float expPerHour, int currentLevel, Game game) {
        log.debug("Started time to next level calculation");
        if (game.expType == ExpType.PERCENTAGE) {
            totalExpToNextLevel = 100;
        } else {
            JSONObject levelJson = game.expTableJson.getJSONObject(String.valueOf(currentLevel));
            totalExpToNextLevel = (float) levelJson.get("expToLevelUp");
        }

        expToNextLevel = totalExpToNextLevel - currentExp;


        if (expPerHour > 0) {
            timeToNextLevel = expToNextLevel / expPerHour;
            log.debug("Finished time to next level calculation: " + timeToNextLevel);
            return timeToNextLevel;
        } else {
            log.debug("Couldn't calculate time to next level");
            return 0.0f;
        }
    }

}
