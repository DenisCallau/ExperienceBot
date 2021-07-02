package ui;

import java.awt.*;

public class BotUi {

    protected BotUiFrame frame = new BotUiFrame();
    private BotUiPanel panel = new BotUiPanel();
    protected BotUiLabel currentLevelLabel = new BotUiLabel();
    ;
    protected BotUiLabel currentExpLabel = new BotUiLabel();
    ;
    protected BotUiLabel expPerHourLabel = new BotUiLabel();
    ;
    protected BotUiLabel expGainedLabel = new BotUiLabel();
    ;
    protected BotUiLabel timeToNextLevelLabel = new BotUiLabel();
    ;
    private GridBagConstraints c = new GridBagConstraints();

    public BotUi() {
        c.fill = GridBagConstraints.LINE_START;
        c.anchor = GridBagConstraints.LINE_START;

        c.gridy = 0;
        panel.add(currentLevelLabel, c);
        c.gridy = 1;
        panel.add(currentExpLabel, c);
        c.gridy = 2;
        panel.add(expPerHourLabel, c);
        c.gridy = 3;
        panel.add(expGainedLabel, c);
        c.gridy = 4;
        panel.add(timeToNextLevelLabel, c);

        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

}
