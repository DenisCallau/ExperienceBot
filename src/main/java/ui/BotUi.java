package ui;

import java.awt.*;

public class BotUi {

    protected BotUiFrame frame;
    private BotUiPanel panel;
    protected BotUiLabel currentExpLabel;
    protected BotUiLabel expPerHourLabel;
    protected BotUiLabel expGainedLabel;
    protected BotUiLabel timeToNextLevelLabel;
    private GridBagConstraints c;

    public BotUi() {
        frame = new BotUiFrame();
        panel = new BotUiPanel();

        c = new GridBagConstraints();

        currentExpLabel = new BotUiLabel();
        expPerHourLabel = new BotUiLabel();
        expGainedLabel = new BotUiLabel();
        timeToNextLevelLabel = new BotUiLabel();

        c.fill  = GridBagConstraints.LINE_START;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridy = 0;
        panel.add(currentExpLabel, c);
        c.gridy = 1;
        panel.add(expPerHourLabel, c);
        c.gridy = 2;
        panel.add(expGainedLabel, c);
        c.gridy = 3;
        panel.add(timeToNextLevelLabel, c);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

}
