package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import org.junit.Before;
import org.junit.Test;

import static alfredeperjesi.game.hangman.Fixtures.GAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GameResourceAssemblerTest {
    private GameResourceAssembler gameResourceAssembler;

    @Before
    public void setUp() {
        gameResourceAssembler = new GameResourceAssembler();
    }

    @Test
    public void assembleAssemblesTheProperResource() {
        GameResource gameResource = gameResourceAssembler.assemble(GAME);

        assertThat(gameResource.getActualWord(), equalTo(GAME.actualWord()));
        assertThat(gameResource.getMissedLetterCount(), equalTo(GAME.missedLetterCount()));
    }
}
