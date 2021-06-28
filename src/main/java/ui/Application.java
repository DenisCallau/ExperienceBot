package ui;

import main.Session;
import main.bot.BlackDesertBot;
import main.helpers.ExpUiLabelBuilder;

import java.awt.*;
import java.io.IOException;

public class Application implements Runnable {

    private BotUi ui = new BotUi();

    @Override
    public void run() {
        Session session = new Session();
        session.setNewSession(true);
        BlackDesertBot bot = null;
        try {
            bot = new BlackDesertBot();
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }


        while(true) {
            try {
                bot.runBot(session);
                if (bot.getHunt().isHunting()) {
                    ExpUiLabelBuilder.setLabelText(ui.currentExpLabel, ui.expPerHourLabel, ui.expGainedLabel, ui.timeToNextLevelLabel, bot);
                } else {
                    ExpUiLabelBuilder.setLabelText(ui.currentExpLabel, ui.expPerHourLabel, ui.expGainedLabel, ui.timeToNextLevelLabel);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

//            ui.currentExpLabel.setText(("Current Exp: " + bot.getCurrentExp().getExp()));
//            ui.expPerHourLabel.setText(("Exp/Hour: " + bot.getExpPerHour().getExp()));
//            ui.expGainedLabel.setText(("Exp Gained: " + (bot.getCurrentExp().getExp() - bot.getHunt().getInitialExp().getExp())));
//            ui.timeToNextLevelLabel.setText(("Time to level up: " + bot.getTimeToNextLevelString()));
//
            ui.frame.pack();
        }


    }
}
