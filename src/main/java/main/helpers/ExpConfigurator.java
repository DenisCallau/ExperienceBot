package main.helpers;

public class ExpConfigurator {

    protected ExpType expType;
    private int firstExpIndex;
    private String lastExpIndex;
    private int firstLevelIndex;
    private int lastLevelIndex;

    public ExpConfigurator(int firstExpIndex, String lastExpIndex, int firstLevelIndex, int lastLevelIndex) {
        this.firstExpIndex = firstExpIndex;
        this.lastExpIndex = lastExpIndex;
        this.firstLevelIndex = firstLevelIndex;
        this.lastLevelIndex = lastLevelIndex;
    }

    public String sanitizeExp(String string) {
        return string.substring(firstExpIndex, string.indexOf(lastExpIndex));
    }

    public String sanitizeLevel(String string) {
        return string.substring(firstLevelIndex, lastLevelIndex);
    }
}
