package main.bot;

import main.CalculatorBlackDesertImpl;
import main.Exp;
import main.Session;
import main.helpers.ExpLocations;

import java.awt.*;
import java.io.IOException;

public class BlackDesertBot extends Bot {

    public BlackDesertBot() throws IOException, AWTException {
        super();
        this.gameWindowName = "Black Desert";
        screenLocation = ExpLocations.BLACK_DESERT;
        currentExp = new Exp(screenshot.getCurrentExp(screenLocation));
        calculator = new CalculatorBlackDesertImpl();
    }

    @Override
    public void runBot(Session session) throws IOException, AWTException, InterruptedException {

        Thread.sleep(1000);
        if (isGameActive()) {

            session.setInitialExp(new Exp(screenshot.getCurrentExp(screenLocation)));

            if(session.isNewSession()) {
                hunt.setInitialExp(session.getInitialExp());
                hunt.setPreviousExp(session.getInitialExp());
                session.setNewSession(false);
            }

            if (!hunt.isHunting() && hunt.getLastExpGainedTime() < 120000) {
                System.out.println("Started hunting");
                hunt.startHunt(new Exp(screenshot.getCurrentExp(screenLocation)));
                session.setNewSession(false);
            }

            currentExp = new Exp(screenshot.getCurrentExp(screenLocation));

            if (currentExp.getExpPercentage() != hunt.getPreviousExp().getExpPercentage()) {
                hunt.setLastExpGainedTime(System.currentTimeMillis());
            }

            expPerHour = calculator.expPerHour(hunt, currentExp);
            timeToNextLevel = calculator.timeToNextLevel(currentExp, expPerHour);

            if (timeToNextLevel < 1) {
                setTimeToNextLevelString(df.format(timeToNextLevel * 60) + " minutes");
            } else {
                setTimeToNextLevelString((int) timeToNextLevel + " hours and " + df.format((timeToNextLevel * 60) % 60) + " minutes");
            }

            long huntingTime = (System.currentTimeMillis() - hunt.getLastExpGainedTime());
            System.out.println("Now: " + System.currentTimeMillis());
            System.out.println("Last gained: " + hunt.getLastExpGainedTime());
            System.out.println("Hunting time: " + huntingTime);

            hunt.setPreviousExp(currentExp);

            if (hunt.isHunting() && (System.currentTimeMillis() - hunt.getLastExpGainedTime()) > 120000) {
                System.out.println("Ended hunting");
                hunt.endHunt(currentExp);
            }

        }

    }

}
