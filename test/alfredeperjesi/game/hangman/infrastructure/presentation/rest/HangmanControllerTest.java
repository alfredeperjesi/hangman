package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import static alfredeperjesi.game.hangman.Fixtures.GAME;
import static alfredeperjesi.game.hangman.Fixtures.GAME_RESOURCE;
import static alfredeperjesi.game.hangman.Fixtures.PLAYER_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import alfredeperjesi.game.hangman.Fixtures;
import alfredeperjesi.game.hangman.application.GameService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HangmanControllerTest {
    @Mock
    private GameService gameService;

    @Mock
    private GameResourceAssembler gameResourceAssembler;

    private HangmanController hangmanController;

    @Before
    public void setUp() {
        hangmanController = new HangmanController(gameService, gameResourceAssembler);
    }

    @Test
    public void getGameDelegatesToGameService() {
        when(gameService.getGameByPlayerName(PLAYER_NAME)).thenReturn(GAME);
        when(gameResourceAssembler.assemble(GAME)).thenReturn(Fixtures.GAME_RESOURCE);

        GameResource gameResource = hangmanController.getGame(PLAYER_NAME);

        assertThat(gameResource, equalTo(GAME_RESOURCE));
    }

    @Test
    public void createDelegatesToGameService() {
        when(gameService.create(PLAYER_NAME)).thenReturn(GAME);
        when(gameResourceAssembler.assemble(GAME)).thenReturn(Fixtures.GAME_RESOURCE);

        GameResource gameResource = hangmanController.create(PLAYER_NAME);

        assertThat(gameResource, equalTo(GAME_RESOURCE));
    }
}
