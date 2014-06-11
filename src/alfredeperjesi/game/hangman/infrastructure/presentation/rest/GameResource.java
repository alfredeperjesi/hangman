package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

public class GameResource {

    private final String actualWord;
    private final int missedLetterCount;

    public GameResource(String actualWord, int missedLetterCount) {
        this.actualWord = actualWord;
        this.missedLetterCount = missedLetterCount;
    }

    public String actualWord() {
        return actualWord;
    }

    public int missedLetterCount() {
        return missedLetterCount;
    }
}
