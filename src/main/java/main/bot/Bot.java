package main.bot;

import main.*;
import main.helpers.ExpLocations;
import main.helpers.JnaHelper;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

public abstract class Bot {

    private final JnaHelper jnaHelper = new JnaHelper();

    protected String gameWindowName;
    protected ExpLocations screenLocation;
    protected Calculator calculator;
    protected DecimalFormat df = new DecimalFormat("0");
    protected Screenshot screenshot = new Screenshot();
    protected Exp currentExp;
    protected Exp expPerHour;
    protected float timeToNextLevel;
    protected String timeToNextLevelString;
    protected Session session;
    protected Hunt hunt;
    protected boolean afk = false;


    public Bot() throws IOException, AWTException {
        session = new Session();
        hunt = new Hunt();
        session.startSession();
        expPerHour = new Exp();

//        currentExp = new Exp(screenshot.getCurrentExp(screenLocation));
//        System.out.println(getCurrentExp().getExpPercentage());
    }

    public void runBot(Session session) throws IOException, AWTException, InterruptedException {

        if (isGameActive()) {
            Thread.sleep(1000);
            if (!hunt.isHunting()) {
                hunt.startHunt(currentExp);
            }

            currentExp = new Exp(screenshot.getCurrentExp(screenLocation));

            expPerHour = calculator.expPerHour(hunt, currentExp);
            timeToNextLevel = calculator.timeToNextLevel(currentExp, expPerHour);

            if (timeToNextLevel < 1) {
                setTimeToNextLevelString(df.format(timeToNextLevel * 60) + " minutes");
            } else {
                setTimeToNextLevelString((int) timeToNextLevel + " hours and " + df.format((timeToNextLevel * 60) % 60) + " minutes");
            }

        }

    }

    public boolean isGameActive() {
        return jnaHelper.getWindowName().contains(gameWindowName);
    }

    public Exp getCurrentExp() {
        return currentExp;
    }

    public Exp getExpPerHour() {
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
