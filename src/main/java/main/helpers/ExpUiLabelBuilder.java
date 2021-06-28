package main.helpers;

import main.bot.Bot;
import ui.BotUiLabel;

import java.text.DecimalFormat;

public abstract class ExpUiLabelBuilder {


    private static final DecimalFormat df = new DecimalFormat("0.000");

    public static void setLabelText(BotUiLabel currentExpLabel, BotUiLabel expPerHourLabel, BotUiLabel expGainedLabel
            , BotUiLabel timeToNextLevelLabel, Bot bot) {
        currentExpLabel.setText(("Current Exp: " + df.format(bot.getCurrentExp().getExpPercentage())) + "%");
        expPerHourLabel.setText(("Exp/Hour: " + df.format(bot.getExpPerHour().getExpPercentage()) + "%"));
        expGainedLabel.setText(("Exp Gained: " + df.format((bot.getCurrentExp().getExpPercentage() - bot.getHunt().getInitialExp().getExpPercentage())) + "%"));
        timeToNextLevelLabel.setText(("Time to level up: " + bot.getTimeToNextLevelString()));
    }

    public static void setLabelText(BotUiLabel currentExpLabel, BotUiLabel expPerHourLabel, BotUiLabel expGainedLabel
            , BotUiLabel timeToNextLevelLabel) {
        currentExpLabel.setText("");
        expPerHourLabel.setText("");
        expGainedLabel.setText("");
        timeToNextLevelLabel.setText("");
    }

}
