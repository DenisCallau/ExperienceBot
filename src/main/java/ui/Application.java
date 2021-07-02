package ui;

import main.bot.Bot;
import main.helpers.ExpLocations;
import main.helpers.ExpUiLabelBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;

public class Application implements Runnable {

    private BotUi ui = new BotUi();

    final Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void run() {
        Bot bot = new Bot(ExpLocations.BLACK_DESERT);

        //TODO: Implement a way to select the game and set it's parameters, like the experience screen location,
        //window name, etc.

        while (true) {
            try {
                bot.runBot();
                ExpUiLabelBuilder.setLabelText(ui.currentExpLabel, ui.expPerHourLabel, ui.expGainedLabel,
                        ui.timeToNextLevelLabel, bot);
            } catch (IOException | AWTException e) {
                log.error("Couldn't run method runBot()");
            }
            ui.frame.pack();
        }


    }
}
