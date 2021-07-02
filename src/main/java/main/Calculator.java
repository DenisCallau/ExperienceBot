package main;

import main.bot.Bot;
import main.helpers.ExpConfigurator;
import main.helpers.ExpPercentageConfigurator;
import main.helpers.ExpType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Calculator {

    protected float expGained;
    protected float expPerHour;
    protected long now;
    protected float hoursDiff;
    private float totalExpToNextLevel;
    private float expToNextLevel;
    private ExpConfigurator expConfigurator = new ExpPercentageConfigurator(0, "%");

    final Logger log = LogManager.getLogger(this.getClass());

    public float expPerHour(Hunt hunt, float currentExp) {
        expPerHour = 0;
        now = System.currentTimeMillis();

        expGained = currentExp - hunt.getInitialExp();
        long timeDiff = now - hunt.getInitialTime();

        try {
            float seconds  = timeDiff / 1000;
            float minutes = seconds / 60;
            hoursDiff = minutes / 60;
            expPerHour = (expGained / hoursDiff);
            return expPerHour;
        } catch (ArithmeticException e) {
            log.error("Invalid data for calculations");
            return expPerHour;
        }
    }


    public float timeToNextLevel(float currentExp, float expPerHour) {
        if (expConfigurator.getExpType() == ExpType.PERCENTAGE) {
            totalExpToNextLevel = 100;
            expToNextLevel = totalExpToNextLevel - currentExp;
        } else {
            //TODO: Here I'll have to implement a method to get from a JSON the total experience for each level
            // or implement a way to get the current percentage of the current level
            totalExpToNextLevel = 0;
            expToNextLevel = 0;
        }

        if(expPerHour > 0) {
            return expToNextLevel / expPerHour;
        } else {
            return 0.0f;
        }
    }
    
    public void setExpConfigurator(ExpConfigurator expConfigurator) {
        this.expConfigurator = expConfigurator;
    }


}
