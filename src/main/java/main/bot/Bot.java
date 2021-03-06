package main.bot;

import main.Calculator;
import main.Hunt;
import main.Screenshot;
import main.Session;
import main.helpers.Game;
import main.helpers.JnaHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

public class Bot {

    private final JnaHelper jnaHelper = new JnaHelper();

    private static final Logger log = LogManager.getLogger(Bot.class);

    protected Game game;
    protected Calculator calculator;
    protected DecimalFormat df = new DecimalFormat("0");
    protected int currentLevel;
    protected Screenshot screenshot;
    protected float currentExp;
    protected float expPerHour;
    protected float timeToNextLevel;
    protected String timeToNextLevelString;
    protected Session session;
    protected Hunt hunt;

    public Bot(Game game) {
        this.game = game;
        screenshot = new Screenshot(game);
        screenshot.setTesseractDatapath();
        calculator = new Calculator();
        session = new Session();
        hunt = new Hunt();
    }

    public void runBot() throws IOException, AWTException {
        currentExp = screenshot.getCurrentExp(game.levelExpLocations);
        currentLevel = screenshot.getCurrentLevel(game.levelExpLocations);

        if (session.isNewSession()) {
            session.startSession(currentLevel, currentExp);
        }

        if (!hunt.isHunting()) {
            hunt.startHunt(currentLevel, currentExp);
        }

        if (currentExp != hunt.getPreviousExp()) {
            hunt.setLastExpGainedTime(System.currentTimeMillis());
        }

        hunt.setCurrentExp(currentExp);
        hunt.setCurrentLevel(currentLevel);

        expPerHour = calculator.expPerHour(hunt, currentExp);

        timeToNextLevel = calculator.timeToNextLevel(currentExp, expPerHour, currentLevel, game);
        if (timeToNextLevel < 1) {
            setTimeToNextLevelString(df.format(timeToNextLevel * 60) + " minutes");
        } else {
            setTimeToNextLevelString((int) timeToNextLevel + " hours and " + df.format((timeToNextLevel * 60) % 60) + " minutes");
        }
        log.debug("Time to next level: " + timeToNextLevelString);

        hunt.setPreviousExp(currentExp);
        hunt.setPreviousLevel(currentLevel);

//        if (hunt.isHunting() && (System.currentTimeMillis() - hunt.getLastExpGainedTime()) > 1200) {
//            log.debug("Ending hunt");
//            hunt.endHunt(currentLevel, currentExp);
//        }
    }

    public boolean isGameActive() {
        return jnaHelper.getWindowName().contains(game.windowName);
    }

    public int getCurrentLevel() {
        return currentLevel;
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

    public Session getSession() {
        return session;
    }


}
