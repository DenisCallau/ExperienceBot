package ui;

import javax.swing.*;
import java.awt.*;

public class BotUiLabel extends JLabel {

    public BotUiLabel() {
        this.setFont(new Font("Default", Font.BOLD, 16));
        this.setForeground(new Color(94,209, 44));
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }
}
