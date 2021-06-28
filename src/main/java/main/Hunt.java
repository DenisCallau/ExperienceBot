package main;

public class Hunt {

    private Exp initialExp;
    private Exp finishExp;
    private long initialTime;
    private long finishTime;
    private boolean hunting = false;
    private Exp previousExp;
    private long lastExpGainedTime;

    public void startHunt(Exp currentExp) {
        this.initialTime = System.currentTimeMillis();
        this.initialExp = currentExp;
        this.hunting = true;
    }

    public void endHunt(Exp finishExp) {
        this.finishTime = System.currentTimeMillis();
        this.finishExp = finishExp;
        this.hunting = false;
    }

    public boolean isHunting() {
        return this.hunting;
    }

    public Exp getPreviousExp() {
        return previousExp;
    }

    public void setPreviousExp(Exp previousExp) {
        this.previousExp = previousExp;
    }

    public long getLastExpGainedTime() {
        return lastExpGainedTime;
    }

    public void setLastExpGainedTime(long lastExpGainedTime) {
        this.lastExpGainedTime = lastExpGainedTime;
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
