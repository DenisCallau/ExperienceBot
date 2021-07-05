package ui;

import main.bot.Bot;
import main.helpers.ExpUiLabelBuilder;
import main.helpers.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Application implements Runnable {

    private BotUi ui = new BotUi();

    private static final Logger log = LogManager.getLogger(Application.class);

    @Override
    public void run() {
        Bot bot = new Bot(Game.BLACK_DESERT);
        log.debug("Application started for " + Game.BLACK_DESERT.name);

        while (true) {


            if (bot.isGameActive()) {
                ui.frame.setVisible(true);
            } else {
                ui.frame.setVisible(false);
            }

            while (bot.isGameActive()) {
                log.info("=========================== Started tick ===========================");
                try {
                    bot.runBot();
                    ExpUiLabelBuilder.setLabelText(ui.currentLevelLabel, ui.currentExpLabel, ui.expPerHourLabel,
                            ui.expGainedLabel,
                            ui.timeToNextLevelLabel, bot);
                } catch (IOException | AWTException e) {
                    log.error("Couldn't run method runBot()");
                }

                ui.resetButton.addActionListener(e -> bot.getHunt().endHunt());
                ui.closeButton.addActionListener(e -> System.exit(0));

                ui.frame.pack();
                log.info("=========================== Finished tick ===========================");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        }

    }
}
