package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hunt {

    private int initialLevel;
    private int currentLevel;
    private int finishLevel;
    private float initialExp;
    private float currentExp;
    private float finishExp;
    private long initialTime;
    private long finishTime;
    private boolean hunting = false;
    private float previousExp;
    private int previousLevel;
    private long lastExpGainedTime;
    private float expGained;

    private static final Logger log = LogManager.getLogger(Hunt.class);

    public void startHunt(int currentLevel, float currentExp) {
        log.debug("Starting hunt");
        this.initialTime = System.currentTimeMillis();
        this.initialLevel = currentLevel;
        this.initialExp = currentExp;
        this.lastExpGainedTime = 0;
        this.hunting = true;
    }

    public void endHunt() {
        log.debug("Ending hunt");
        log.info(this.toString());
        this.finishTime = System.currentTimeMillis();
        this.finishLevel = currentLevel;
        this.finishExp = currentExp;
        this.hunting = false;
    }

    @Override
    public String toString() {
        return "\nInitial level   |   Initial Exp   |   Finish level   |   Finish Exp\n" + initialLevel + "   |   " + initialExp + "   |   " + finishLevel + "   |   " + finishExp;
    }


    public boolean isHunting() {
        return this.hunting;
    }

    public float getPreviousExp() {
        return previousExp;
    }

    public void setPreviousExp(float previousExp) {
        this.previousExp = previousExp;
    }

    public int getPreviousLevel() {
        return previousLevel;
    }

    public void setPreviousLevel(int previousLevel) {
        this.previousLevel = previousLevel;
    }

    public long getLastExpGainedTime() {
        return lastExpGainedTime;
    }

    public void setLastExpGainedTime(long lastExpGainedTime) {
        this.lastExpGainedTime = lastExpGainedTime;
    }

    public float getInitialExp() {
        return initialExp;
    }

    public void setInitialExp(float initialExp) {
        this.initialExp = initialExp;
    }

    public float getFinishExp() {
        return finishExp;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public long getHuntingTime() {
        return System.currentTimeMillis() - this.initialTime;
    }

    public int getInitialLevel() {
        return initialLevel;
    }

    public int getFinishLevel() {
        return finishLevel;
    }

    public float getExpGained() {
        return expGained;
    }

    public void setExpGained(float expGained) {
        this.expGained = expGained;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public float getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(float currentExp) {
        this.currentExp = currentExp;
    }
}
