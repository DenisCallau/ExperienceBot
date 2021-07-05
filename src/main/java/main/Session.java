package main;

import main.bot.Bot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {

    private int initialLevel;
    private int finishLevel;
    private float initialExp;
    private float finishExp;
    private long initialTime;
    private long finishTime;
    private boolean newSession = true;

    private static final Logger log = LogManager.getLogger(Session.class);

    public void startSession(int initialLevel, float initialExp) {
        log.debug("Starting session");
        this.initialTime = System.currentTimeMillis();
        this.initialLevel = initialLevel;
        this.initialExp = initialExp;
        this.newSession = false;
    }

    public void endSession(int finishLevel, float finishExp) {
        log.debug("Ending session");
        this.finishTime = System.currentTimeMillis();
        this.finishLevel = finishLevel;
        this.finishExp = finishExp;
    }

    public boolean isNewSession() {
        return newSession;
    }

    public void setNewSession(boolean newSession) {
        this.newSession = newSession;
    }

    public int getInitialLevel() {
        return initialLevel;
    }

    public int getFinishLevel() {
        return finishLevel;
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



}
