package ui;

import main.bot.Bot;
import main.helpers.ExpUiLabelBuilder;
import main.helpers.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.IOException;

public class Application implements Runnable {

    private BotUi ui = new BotUi();

    private static final Logger log = LogManager.getLogger(Application.class);

    @Override
    public void run() {
        Bot bot = new Bot(Game.BLACK_DESERT);

        while (true) {
            while (bot.isGameActive()) {
                try {
                    bot.runBot();
                    ExpUiLabelBuilder.setLabelText(ui.currentLevelLabel, ui.currentExpLabel, ui.expPerHourLabel,
                            ui.expGainedLabel,
                            ui.timeToNextLevelLabel, bot);
                } catch (IOException | AWTException e) {
                    log.error("Couldn't run method runBot()");
                }
                ui.frame.pack();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
