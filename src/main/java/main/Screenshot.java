package main;

import main.helpers.ExpConfigurator;
import main.helpers.ExpType;
import main.helpers.Game;
import main.helpers.LevelExpLocations;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screenshot {

    private ExpConfigurator expConfigurator;
    private String method;

    public Screenshot(Game game) {
        if (game.expType == ExpType.PERCENTAGE) {
            this.expConfigurator = new ExpConfigurator(game.levelExpLocations.firstIndexExp,
                    game.levelExpLocations.lastIndexExp, game.levelExpLocations.firstLevelIndex,
                    game.levelExpLocations.lastLevelIndex);
        } else {
            this.expConfigurator = new ExpConfigurator(game.levelExpLocations.firstIndexExp,
                    game.levelExpLocations.lastIndexExp, game.levelExpLocations.firstLevelIndex,
                    game.levelExpLocations.lastLevelIndex);
        }
    }

    public float getCurrentExp(LevelExpLocations screenLocation) throws IOException, AWTException {
        method = "exp";
        try {
            return Float.parseFloat(expConfigurator.sanitizeExp(this.takeScreenshot(new Rectangle(screenLocation.expX,
                    screenLocation.expY,
                    screenLocation.expWidth, screenLocation.expHeight))));
        } catch (Exception e) {
            return 0f;
        }
    }

    public int getCurrentLevel(LevelExpLocations screenLocation) throws IOException, AWTException {
        method = "level";
        try {
            String lvl = expConfigurator.sanitizeLevel(this.takeScreenshot(new Rectangle(screenLocation.levelX, screenLocation.levelY,
                    screenLocation.levelWidth, screenLocation.levelHeight)));
            lvl = lvl.replaceAll("[^0-9]", "");
            return Integer.parseInt(lvl);
        } catch (Exception e) {
            return 0;
        }
    }

    private String takeScreenshot(Rectangle area) throws IOException, AWTException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(area));

        Image nova = image.getScaledInstance((int) area.getWidth() * 1, (int) area.getHeight() * 1,
                Image.SCALE_DEFAULT);

        BufferedImage bimage = new BufferedImage((int) area.getWidth() * 1, (int) area.getHeight() * 1,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(nova, 0, 0, null);
        bGr.dispose();

        File file;
        if(method.equals("exp")) {
            file = new File("./src/main/resources/expImage.png");
        } else {
            file = new File("./src/main/resources/levelImage.png");
        }
        ImageIO.write(bimage, "png", file);

        return this.binarizeImage(bimage);
    }

    private String binarizeImage(BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage binarizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int val = image.getRGB(i, j);
                int r = (0x00ff0000 & val) >> 16;
                int g = (0x0000ff00 & val) >> 8;
                int b = (0x000000ff & val);

                int m = (r + g + b);
                if (m > 383) {
                    binarizedImage.setRGB(i, j, Color.BLACK.getRGB());
                } else {
                    binarizedImage.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
        }

        File file;
        if(method.equals("exp")) {
            file = new File("./src/main/resources/expBinarizedImage.png");
        } else {
            file = new File("./src/main/resources/levelBinarizedImage.png");
        }
        ImageIO.write(binarizedImage, "png", file);

        return this.doOcr(file);

    }

    private String doOcr(File file) {
        Tesseract tess4j = new Tesseract();
        tess4j.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        tess4j.setLanguage("eng");
        tess4j.setTessVariable("tessedit_char_whitelist", "0123456789[].%");
        try {
            String value = tess4j.doOCR(file);
            return value;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }


}
