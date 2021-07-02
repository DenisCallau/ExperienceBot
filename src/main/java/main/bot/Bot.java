package main.bot;

import main.*;
import main.helpers.ExpConfigurator;
import main.helpers.ExpLocations;
import main.helpers.JnaHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

public class Bot {

    private final JnaHelper jnaHelper = new JnaHelper();

    protected String gameWindowName;
    protected ExpLocations screenLocation;
    protected Calculator calculator;
    protected DecimalFormat df = new DecimalFormat("0");
    protected Screenshot screenshot = new Screenshot();
    protected float currentExp;
    protected float expPerHour;
    protected float timeToNextLevel;
    protected String timeToNextLevelString;
    protected Session session;
    protected Hunt hunt;

    final Logger log = LogManager.getLogger(this.getClass());

    public Bot(ExpLocations screenLocation) {
        //TODO: Pass the game as parameter instead of ExpLocations.
        this.screenLocation = screenLocation;
        calculator = new Calculator();
        session = new Session();
        hunt = new Hunt();
    }

    public void runBot() throws IOException, AWTException {
        currentExp = screenshot.getCurrentExp(screenLocation);

        if (session.isNewSession()) {
            log.debug("Starting session");
            session.startSession(currentExp);
        }

        if (!hunt.isHunting() && hunt.getLastExpGainedTime() < 12000) {
            log.debug("Starting hunt");
            hunt.startHunt(currentExp);
        }

        if (currentExp != hunt.getPreviousExp()) {
            hunt.setLastExpGainedTime(System.currentTimeMillis());
        }

        expPerHour = calculator.expPerHour(hunt, currentExp);
        timeToNextLevel = calculator.timeToNextLevel(currentExp, expPerHour);

        if (timeToNextLevel < 1) {
            setTimeToNextLevelString(df.format(timeToNextLevel * 60) + " minutes");
        } else {
            setTimeToNextLevelString((int) timeToNextLevel + " hours and " + df.format((timeToNextLevel * 60) % 60) + " minutes");
        }

        hunt.setPreviousExp(currentExp);

        if (hunt.isHunting() && (System.currentTimeMillis() - hunt.getLastExpGainedTime()) > 120000) {
            log.debug("Ending hunt");
            hunt.endHunt(currentExp);
        }
    }

    public boolean isGameActive() {
        return jnaHelper.getWindowName().contains(gameWindowName);
    }

    public float getCurrentExp() {
        return currentExp;
    }

    public float getExpPerHour() {
        return expPerHour;
    }

    public String getTimeToNextLevelString() {
        return timeToNextLevelString;
    }

    public void setTimeToNextLevelString(String timeToNextLevelString) {
        this.timeToNextLevelString = timeToNextLevelString;
    }

    public Hunt getHunt() {
        return hunt;
    }

}
