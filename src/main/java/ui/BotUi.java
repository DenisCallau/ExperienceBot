package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BotUi {

    protected BotUiFrame frame = new BotUiFrame();
    private BotUiPanel panel = new BotUiPanel();
    protected JButton resetButton = new JButton();
    protected BotUiLabel currentLevelLabel = new BotUiLabel();
    protected BotUiLabel currentExpLabel = new BotUiLabel();
    protected BotUiLabel expPerHourLabel = new BotUiLabel();
    protected BotUiLabel expGainedLabel = new BotUiLabel();
    protected BotUiLabel timeToNextLevelLabel = new BotUiLabel();
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

        resetButton.setFocusable(false);
        resetButton.setBackground(new Color(26, 26, 26));
        resetButton.setForeground(new Color(222, 222, 222));
        resetButton.setText("Reset");
        resetButton.setFont(new Font("Default", Font.PLAIN, 10));
        c.gridy = 5;
        panel.add(resetButton, c);

        frame.add(panel);

        frame.pack();

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                }
            }
        });

    }



}
