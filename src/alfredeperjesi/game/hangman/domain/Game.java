package alfredeperjesi.game.hangman.domain;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game implements Serializable {
    public static final int MAX_MISSED_LETTERS_COUNT = 10;

    private final String playerName;
    private final String word;
    private final Set<Character> guessedLetters;
    private final Set<Character> missedLetters;

    private String actualWord;

    public Game(final String playerName, final String word) {
        Validate.notNull(playerName, "Player name cannot be null");
        Validate.notNull(word, "Word cannot be null");
        this.playerName = playerName;
        this.word = word.toLowerCase();
        guessedLetters = new HashSet<>();
        missedLetters = new HashSet<>();
        actualWord = org.apache.commons.lang3.StringUtils.leftPad("", word.length(), "_");
    }

    public void guess(Character guess) {
        Validate.validState(hasNotLost(), String.format("The game has already lost. Word is %s.", word));
        Validate.validState(hasNotWon() && hasNotLost(), String.format("The game has already been won by word %s.", word));
        Validate.isTrue(hasNotGuessed(guess), String.format("Letter '%s' has already been guessed.", guess));
        char guessInLowerCase = toLowerCase(guess);
        if (word.contains(String.valueOf(guessInLowerCase))) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guessInLowerCase) {
                    actualWord = String.format("%s%s%s", actualWord.substring(0, i), guessInLowerCase, actualWord.substring(i + 1));
                }
            }
        } else {
            missedLetters.add(guess);
            if(missedLetterCount() == MAX_MISSED_LETTERS_COUNT) {
                actualWord = word;
            }
        }
        guessedLetters.add(guess);
    }

    private boolean hasNotGuessed(Character guess) {
        return !guessedLetters.contains(guess);
    }

    private boolean hasNotWon() {
        return !actualWord.equals(word);
    }

    private boolean hasNotLost() {
        return missedLetters.size() < MAX_MISSED_LETTERS_COUNT;
    }

    private char toLowerCase(Character guess) {
        return CharUtils.toString(guess.charValue()).toLowerCase().charAt(0);
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

    public Set<Character> missedLetters() {
        return missedLetters;
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
