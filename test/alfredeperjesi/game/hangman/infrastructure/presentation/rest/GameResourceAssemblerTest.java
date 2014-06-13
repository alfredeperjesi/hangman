package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void assembleAssemblesTheProperResources() {
        List<GameResource> gameResources = gameResourceAssembler.assemble(Lists.newArrayList(GAME));

        assertThat(gameResources.size(), equalTo(1));
        assertThat(gameResources.get(0).getActualWord(), equalTo(GAME.actualWord()));
        assertThat(gameResources.get(0).getMissedLetterCount(), equalTo(GAME.missedLetterCount()));
    }
}
