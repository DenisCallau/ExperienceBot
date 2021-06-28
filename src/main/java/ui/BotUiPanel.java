package ui;

import javax.swing.*;
import java.awt.*;

public class BotUiPanel extends JPanel {

    public BotUiPanel() {
        this.setSize(900, 600);
        this.setBackground(new Color(0, 0, 0, 255));
        LayoutManager2 layout = new GridBagLayout();
        this.setLayout(layout);
    }

}
