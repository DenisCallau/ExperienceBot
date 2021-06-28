package main;

public class Session {

    private int initialLevel;
    private int finishLevel;
    private Exp initialExp;
    private Exp finishExp;
    private long initialTime;
    private long finishTime;
    private boolean newSession;

    public void startSession() {
        this.initialTime = System.currentTimeMillis();
    }

    public void endSession() {
        this.finishTime = System.currentTimeMillis();
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

    public Exp getInitialExp() {
        return initialExp;
    }

    public void setInitialExp(Exp initialExp) {
        this.initialExp = initialExp;
    }

    public Exp getFinishExp() {
        return finishExp;
    }

    public long getInitialTime() {
        return initialTime;
    }

    public long getFinishTime() {
        return finishTime;
    }



}
