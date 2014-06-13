package alfredeperjesi.game.hangman.domain;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static alfredeperjesi.game.hangman.Fixtures.PLAYER_NAME;
import static alfredeperjesi.game.hangman.Fixtures.WORD;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Game game;

    @Before
    public void setUp() {
        game = new Game(PLAYER_NAME, WORD);
    }

    @Test
    public void constructorThrowsNullPointerExceptionWhenPlayerNameIsNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Player name cannot be null");

        new Game(null, WORD);
    }

    @Test
    public void constructorThrowsNullPointerExceptionWhenWordIsNull() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Word cannot be null");

        new Game(PLAYER_NAME, null);
    }

    @Test
    public void guessDoesNotChangeTheActualWordWhenTheGuessedLetterIsNotInTheWord() {
        game.guess('a');

        String result = game.actualWord();

        assertThat(result, equalTo("__________"));
    }

    @Test
    public void guessIncreasesTheMissedLetterCountWhenTheGuessedLetterIsNotInTheWord() {
        game.guess('a');

        int result = game.missedLetterCount();

        assertThat(result, equalTo(1));
    }

    @Test
    public void guessThrowsIllegalArgumentExceptionWhenTheGuessedLetterHasBeenGuessed() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Letter 'a' has already been guessed.");

        game.guess('a');
        game.guess('a');
    }


    @Test
    public void guessChangeTheActualWordWhenTheGuessedLetterIsInTheWord() {
        game.guess('e');

        String result = game.actualWord();

        assertThat(result, equalTo("_e__e_____"));
    }

    @Test
    public void guessThrowsIllegalStateExceptionWhenTheActualWordIsEqualsToTheWord() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("The game has already been won by word secretword.");

        game.guess('s');
        game.guess('e');
        game.guess('c');
        game.guess('r');
        game.guess('t');
        game.guess('w');
        game.guess('o');
        game.guess('d');

        game.guess('b');
    }

    @Test
    public void guessSetsTheActualWordToTheWordWhenTheMissedLettersAreTheMaximum() {
        game.guess('a');
        game.guess('b');
        game.guess('f');
        game.guess('g');
        game.guess('h');
        game.guess('i');
        game.guess('j');
        game.guess('k');
        game.guess('l');

        game.guess('m');

        assertThat(game.missedLetterCount(), equalTo(10));
        assertThat(game.actualWord(), equalTo(WORD));
    }

    @Test
    public void guessThrowsIllegalStateExceptionWhenTheMissedLettersAreMoreThanTheMaximum() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("The game has already lost. Word is secretword.");

        game.guess('a');
        game.guess('b');
        game.guess('f');
        game.guess('g');
        game.guess('h');
        game.guess('i');
        game.guess('j');
        game.guess('k');
        game.guess('l');
        game.guess('m');

        game.guess('n');
    }

}
