package main;

import main.helpers.ExpConfigurator;
import main.helpers.ExpPercentageConfigurator;
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
    private ExpConfigurator expConfigurator = new ExpPercentageConfigurator(0, "%");

    private static final Logger log = LogManager.getLogger(Calculator.class);

    public float expPerHour(Hunt hunt, float currentExp) {
        expPerHour = 0;
        now = System.currentTimeMillis();

        expGained = currentExp - hunt.getInitialExp();
        long timeDiff = now - hunt.getInitialTime();

        try {
            float seconds = timeDiff / 1000;
            float minutes = seconds / 60;
            hoursDiff = minutes / 60;
            expPerHour = (expGained / hoursDiff);
            return expPerHour;
        } catch (ArithmeticException e) {
            log.error("Invalid data for calculations");
            return expPerHour;
        }
    }


    public float timeToNextLevel(float currentExp, float expPerHour, int currentLevel, Game game) {
        if (expConfigurator.getExpType() == ExpType.PERCENTAGE) {
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
