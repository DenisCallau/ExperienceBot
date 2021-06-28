package main;

public abstract class Calculator {

    protected int expGained;
    protected float expGainedPercentage;
    protected Exp expPerHour;
    protected long now;
    protected float timeToNextLevel;
    protected float hoursDiff;

    public abstract Exp expPerHour(Hunt hunt, Exp currentExp);
    public abstract float timeToNextLevel(Exp currentExp, Exp expPerHour);

}
