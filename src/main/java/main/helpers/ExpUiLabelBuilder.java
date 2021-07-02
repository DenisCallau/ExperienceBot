package main.helpers;

import main.bot.Bot;
import ui.BotUiLabel;

import java.text.DecimalFormat;

public abstract class ExpUiLabelBuilder {

    private static final DecimalFormat df = new DecimalFormat("0.000");

    public static void setLabelText(BotUiLabel currentLevelLabel, BotUiLabel currentExpLabel,
                                    BotUiLabel expPerHourLabel, BotUiLabel expGainedLabel,
                                    BotUiLabel timeToNextLevelLabel, Bot bot) {
        if (bot.getHunt().isHunting()) {
            currentLevelLabel.setText(("Current level: " + bot.getCurrentLevel()));
            currentExpLabel.setText(("Current Exp: " + df.format(bot.getCurrentExp())) + "%");
            expPerHourLabel.setText(("Exp/Hour: " + df.format(bot.getExpPerHour()) + "%"));
            expGainedLabel.setText(("Exp Gained: " + df.format((bot.getCurrentExp() - bot.getHunt().getInitialExp())) + "%"));
            timeToNextLevelLabel.setText(("Time to level up: " + bot.getTimeToNextLevelString()));
        } else {
            currentLevelLabel.setText("");
            currentExpLabel.setText("");
            expPerHourLabel.setText("");
            expGainedLabel.setText("");
            timeToNextLevelLabel.setText("");
        }
    }

}
