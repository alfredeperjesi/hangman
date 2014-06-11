package alfredeperjesi.game.hangman.application;

import alfredeperjesi.game.hangman.domain.Game;
import alfredeperjesi.game.hangman.domain.GameRepository;
import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static alfredeperjesi.game.hangman.Fixtures.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    public static final String MISSING_PLAYER_NAME = "missing";
    public static final Optional<Game> ABSENT_GAME = Optional.absent();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private GameRepository gameRepository;

    @Mock
    private WordProvider wordProvider;

    private GameService gameService;

    @Before
    public void setUp() {
        gameService = new GameService(gameRepository, wordProvider);
    }

    @Test
    public void getByIdThrowsIllegalArgumentExceptionWhenGameIsMissingForId() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Game is missing by player name missing");
        when(gameRepository.getByPlayerName(MISSING_PLAYER_NAME)).thenReturn(ABSENT_GAME);

        gameService.getGameByPlayerName(MISSING_PLAYER_NAME);
    }

    @Test
    public void getByIdReturnsWithTheGameWhenGameExistsForId() {
        when(gameRepository.getByPlayerName(MISSING_PLAYER_NAME)).thenReturn(Optional.of(GAME));

        Game game = gameService.getGameByPlayerName(MISSING_PLAYER_NAME);

        assertThat(game, equalTo(GAME));
    }

    @Test
    public void findActiveGamesDelegatesToTheRepository() {
        when(gameRepository.findActiveGames()).thenReturn(GAMES);

        List<Game> games = gameService.findActiveGames();

        assertThat(games, equalTo(GAMES));
    }

    @Test
    public void createThrowsIllegalArgumentExceptionWhenGameExistsByPlayerName() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Game already exists by player name player");

        when(gameRepository.getByPlayerName(PLAYER_NAME)).thenReturn(Optional.of(GAME));

        gameService.create(PLAYER_NAME);
    }

    @Test
    public void createCreatesAndPersistTheGameWhenGameDoesNotExistByPlayerName() {
        when(gameRepository.getByPlayerName(PLAYER_NAME)).thenReturn(ABSENT_GAME);
        when(wordProvider.getWord()).thenReturn(WORD);

        Game game = gameService.create(PLAYER_NAME);

        assertThat(game, equalTo(GAME));
    }
}
