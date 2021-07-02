package main;

public class Hunt {

    private float initialExp;
    private float finishExp;
    private long initialTime;
    private long finishTime;
    private boolean hunting = false;
    private float previousExp;
    private long lastExpGainedTime;
    private long huntingTime;

    public void startHunt(float currentExp) {
        this.initialTime = System.currentTimeMillis();
        this.lastExpGainedTime = 0;
        this.initialExp = currentExp;
        this.hunting = true;
    }

    public void endHunt(float finishExp) {
        this.finishTime = System.currentTimeMillis();
        this.finishExp = finishExp;
        this.hunting = false;
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

}
