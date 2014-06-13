package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import java.util.Set;

public class GameResource {

    private final String playerName;
    private final String actualWord;
    private final int missedLetterCount;
    private final Set<Character> missedLetters;

    public GameResource() {
        this(null, null, 0, null);
    }

    public GameResource(final String playerName, final String actualWord, final int missedLetterCount, final Set<Character> missedLetters) {
        this.playerName = playerName;
        this.actualWord = actualWord;
        this.missedLetterCount = missedLetterCount;
        this.missedLetters = missedLetters;
    }

    public String getActualWord() {
        return actualWord;
    }

    public int getMissedLetterCount() {
        return missedLetterCount;
    }

    public Set<Character> getMissedLetters() {
        return missedLetters;
    }

    public String getPlayerName() {
        return playerName;
    }
}
