package alfredeperjesi.game.hangman.domain;

import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Game implements Serializable {
    public static final int MAX_MISSED_LETTERS_COUNT = 6;

    private final String playerName;
    private final String word;
    private final Set<Character> guessedLetters;
    private final Set<Character> missedLetters;

    private String actualWord;

    public Game(final String playerName, final String word) {
        Validate.notNull(playerName, "Player name cannot be null");
        Validate.notNull(word, "Word cannot be null");
        this.playerName = playerName;
        this.word = word;
        guessedLetters = new HashSet<>();
        missedLetters = new HashSet<>();
        actualWord = org.apache.commons.lang3.StringUtils.leftPad("", word.length(), "_");
    }

    public void guess(Character guess) {
        Validate.validState(!actualWord.equals(word), String.format("The game has already been won by word %s.", word));
        Validate.validState(missedLetters.size() != MAX_MISSED_LETTERS_COUNT, String.format("The game has already lost. Word is %s.", word));
        Validate.isTrue(!guessedLetters.contains(guess), String.format("Letter '%s' has already been guessed.", guess));
        if (word.contains(String.valueOf(guess))) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess.charValue()) {
                    actualWord = String.format("%s%s%s", actualWord.substring(0, i), guess, actualWord.substring(i + 1));
                }
            }
        } else {
            missedLetters.add(guess);
        }
        guessedLetters.add(guess);
    }

    public String actualWord() {
        return actualWord;
    }

    public int missedLetterCount() {
        return missedLetters.size();
    }

    public String playerName() {
        return playerName;
    }

    public boolean isActive() {
        return missedLetterCount() < MAX_MISSED_LETTERS_COUNT;
    }

    // GENERATED CODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (playerName != null ? !playerName.equals(game.playerName) : game.playerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        return result;
    }
    // GENERATED CODE

}
