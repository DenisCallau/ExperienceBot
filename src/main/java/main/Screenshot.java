package main;

import main.helpers.ExpConfigurator;
import main.helpers.ExpType;
import main.helpers.Game;
import main.helpers.LevelExpLocations;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Screenshot {

    private ExpConfigurator expConfigurator;
    private String method;
    private Tesseract tess4j = new Tesseract();

    private static final Logger log = LogManager.getLogger(Screenshot.class);

    public void setTesseractDatapath() {
        log.debug(System.getenv("programfiles"));
        log.debug(System.getenv("programfiles(x86)"));

        String dirPath = "C:/Program Files/Tesseract-OCR/tessdata";
        File file = new File(dirPath);

        if (file.exists()) {
            tess4j.setDatapath(dirPath);
            log.debug("Tesseract path found in " + System.getenv("programfiles") + "/Tesseract-OCR/tessdata");
            tess4j.setLanguage("eng");
            tess4j.setTessVariable("tessedit_char_whitelist", "0123456789[].%");
        } else {
            log.error("Tesseract Datapath couldn't be found");
        }
    }

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
        log.debug("getCurrentExp method evoked");
        method = "exp";
        try {
            float exp = Float.parseFloat(expConfigurator.sanitizeExp(this.takeScreenshot(new Rectangle(screenLocation.expX,
                    screenLocation.expY,
                    screenLocation.expWidth, screenLocation.expHeight))));
            log.debug("Exp identified: " + exp);
            return exp;
        } catch (Exception e) {
            log.error("Couldn't calculate current exp");
            return 0f;
        }
    }

    public int getCurrentLevel(LevelExpLocations screenLocation) throws IOException, AWTException {
        log.debug("getCurrentLevel method evoked");
        method = "level";
        try {
            String lvl = expConfigurator.sanitizeLevel(this.takeScreenshot(new Rectangle(screenLocation.levelX, screenLocation.levelY,
                    screenLocation.levelWidth, screenLocation.levelHeight)));
            lvl = lvl.replaceAll("[^0-9]", "");
            log.debug("Level identified: " + lvl);
            return Integer.parseInt(lvl);
        } catch (Exception e) {
            log.error("Couldn't calculate current level");
            return 0;
        }
    }

    private String takeScreenshot(Rectangle area) throws IOException, AWTException {
        log.debug("Capturing screenshot");
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(area));

            Image nova = image.getScaledInstance((int) area.getWidth() * 1, (int) area.getHeight() * 1,
                    Image.SCALE_DEFAULT);

            BufferedImage bimage = new BufferedImage((int) area.getWidth() * 1, (int) area.getHeight() * 1,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(nova, 0, 0, null);
            bGr.dispose();

            File file;
            if (method.equals("exp")) {
                file = new File(System.getProperty("user.home") + "/ExperienceBot/expImage.png");
            } else {
                file = new File(System.getProperty("user.home") + "/ExperienceBot/levelImage.png");
            }

            log.debug("Writing file " + file.getPath());

            ImageIO.write(bimage, "png", file);
            return this.binarizeImage(bimage);
        } catch (Exception e) {
            log.error("Error while takeScreenshot(): " + Arrays.toString(e.getStackTrace()));
            return null;
        }


    }

    private String binarizeImage(BufferedImage image) throws IOException {
        log.debug("Binarizing screenshot");
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
            file = new File(System.getProperty("user.home") + "/ExperienceBot/expBinarizedImage.png");
        } else {
            file = new File(System.getProperty("user.home") + "/ExperienceBot/levelBinarizedImage.png");
        }
        ImageIO.write(binarizedImage, "png", file);

        return this.doOcr(file);

    }

    private String doOcr(File file) {
        log.debug("Starting OCR");
        try {
            String value = tess4j.doOCR(file);
            return value;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }


}
