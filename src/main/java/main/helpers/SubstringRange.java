package main.helpers;

public class SubstringRange {

    private int startIndex;
    private int endIndex;
    private String startCharacter;
    private String endCharacter;

    public SubstringRange(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public SubstringRange(int startIndex, String endCharacter) {
        this.startIndex = startIndex;
        this.endCharacter = endCharacter;
    }

    public SubstringRange(String startCharacter, int endIndex) {
        this.startCharacter = startCharacter;
        this.endIndex = endIndex;
    }

    public SubstringRange(String startCharacter, String endCharacter) {
        this.startCharacter = startCharacter;
        this.endCharacter = endCharacter;
    }

}
