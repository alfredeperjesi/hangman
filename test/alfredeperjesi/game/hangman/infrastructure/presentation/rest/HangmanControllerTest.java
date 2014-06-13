package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import static alfredeperjesi.game.hangman.Fixtures.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import alfredeperjesi.game.hangman.Fixtures;
import alfredeperjesi.game.hangman.application.GameService;

import alfredeperjesi.game.hangman.domain.Game;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
        when(gameService.guess(PLAYER_NAME, EXISTING_LETTER_LOWER)).thenReturn(GAME);
        when(gameResourceAssembler.assemble(GAME)).thenReturn(GAME_RESOURCE);

        GameResource gameResource = hangmanController.guess(PLAYER_NAME, EXISTING_LETTER_LOWER);

        assertThat(gameResource, equalTo(GAME_RESOURCE));
    }

    @Test
    public void createDelegatesToGameService() {
        when(gameService.create(PLAYER_NAME)).thenReturn(GAME);
        when(gameResourceAssembler.assemble(GAME)).thenReturn(GAME_RESOURCE);

        GameResource gameResource = hangmanController.create(PLAYER_NAME);

        assertThat(gameResource, equalTo(GAME_RESOURCE));
    }

    @Test
    public void getDelegatesToGameService() {
        when(gameService.get(PLAYER_NAME)).thenReturn(GAME);
        when(gameResourceAssembler.assemble(GAME)).thenReturn(GAME_RESOURCE);

        GameResource gameResource = hangmanController.get(PLAYER_NAME);

        assertThat(gameResource, equalTo(GAME_RESOURCE));
    }

    @Test
    public void findActivesDelegatesToGameService() {
        ArrayList<Game> games = Lists.newArrayList(GAME);
        when(gameService.findActiveGames()).thenReturn(games);
        when(gameResourceAssembler.assemble(games)).thenReturn(Lists.newArrayList(GAME_RESOURCE));

        List<GameResource> gameResources = hangmanController.findActives();

        assertThat(gameResources.size(), equalTo(1));
        assertThat(gameResources.get(0), equalTo(GAME_RESOURCE));
    }
}
