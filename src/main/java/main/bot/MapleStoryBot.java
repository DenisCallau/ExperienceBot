package main.bot;

import main.CalculatorMapleStoryImpl;
import main.helpers.ExpLocations;

import java.awt.*;
import java.io.IOException;

public class MapleStoryBot extends Bot {

    public MapleStoryBot() throws IOException, AWTException {
        super();
        this.gameWindowName = "MapleLegends";
        screenLocation = ExpLocations.MAPLE_STORY;
        calculator = new CalculatorMapleStoryImpl();
    }

//            System.out.println("Current Exp: " + currentExp.getExp());
//            System.out.println("Exp Gained: " + (currentExp.getExp() - hunt.getInitialExp().getExp()));
//            System.out.println("Exp/Hour: " + expPerHour.getExp());

}
