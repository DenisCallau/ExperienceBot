package main.helpers;

public class ExpIntegerConfigurator extends ExpConfigurator {

    public ExpIntegerConfigurator(int startIndex, int endIndex) {
        this.expType = ExpType.INTEGER;
        this.substringRange = new SubstringRange(startIndex, endIndex);
    }

}
