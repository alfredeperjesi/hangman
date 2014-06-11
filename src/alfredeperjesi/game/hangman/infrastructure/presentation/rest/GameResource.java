package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

public class GameResource {

    private final String actualWord;
    private final int missedLetterCount;

    public GameResource() {
        this(null, 0);
    }

    public GameResource(String actualWord, int missedLetterCount) {
        this.actualWord = actualWord;
        this.missedLetterCount = missedLetterCount;
    }

    public String getActualWord() {
        return actualWord;
    }

    public int getMissedLetterCount() {
        return missedLetterCount;
    }
}
