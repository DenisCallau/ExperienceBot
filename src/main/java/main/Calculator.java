package main;

import main.helpers.ExpType;
import main.helpers.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Calculator {

    protected float expGained;
    protected float expPerHour;
    protected long now;
    protected float hoursDiff;
    private float totalExpToNextLevel;
    private float expToNextLevel;

    private static final Logger log = LogManager.getLogger(Calculator.class);

    public float expPerHour(Hunt hunt, float currentExp) {
        now = System.currentTimeMillis();


        expGained = (currentExp + ((hunt.getCurrentLevel() - hunt.getInitialLevel()) * 100)) - hunt.getInitialExp();
        hunt.setExpGained(expGained);

        long timeDiff = now - hunt.getInitialTime();

        try {
            float seconds = timeDiff / 1000;
            float minutes = seconds / 60;
            hoursDiff = minutes / 60;
            expPerHour = (expGained / hoursDiff);
            return expPerHour;
        } catch (ArithmeticException e) {
            log.error("Invalid data for calculations");
            return 0;
        }
    }


    public float timeToNextLevel(float currentExp, float expPerHour, int currentLevel, Game game) {
        if (game.expType == ExpType.PERCENTAGE) {
            totalExpToNextLevel = 100;
        } else {
            JSONObject levelJson = game.expTableJson.getJSONObject(String.valueOf(currentLevel));
            totalExpToNextLevel = (float) levelJson.get("expToLevelUp");
        }

        expToNextLevel = totalExpToNextLevel - currentExp;

        if (expPerHour > 0) {
            return expToNextLevel / expPerHour;
        } else {
            return 0.0f;
        }
    }

}
