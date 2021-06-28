package ui;

import javax.swing.*;
import java.awt.*;

public class BotUiFrame extends JFrame {

    public BotUiFrame() {
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.setLocation(30, 300);
        this.setBackground(new Color(0, 0, 0, 255));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
