package main.helpers;

public class ExpPercentageConfigurator extends ExpConfigurator {

    public ExpPercentageConfigurator(int startIndex, String endCharacter) {
        this.expType = ExpType.PERCENTAGE;
        this.substringRange = new SubstringRange(startIndex, endCharacter);
    }

}
