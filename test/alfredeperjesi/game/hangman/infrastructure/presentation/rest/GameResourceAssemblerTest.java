package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import alfredeperjesi.game.hangman.Fixtures;
import org.junit.Assert;
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

        assertThat(gameResource.actualWord(), equalTo(GAME.actualWord()));
        assertThat(gameResource.missedLetterCount(), equalTo(GAME.missedLetterCount()));
    }
}
