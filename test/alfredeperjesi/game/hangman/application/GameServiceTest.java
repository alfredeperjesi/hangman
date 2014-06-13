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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    public static final String MISSING_PLAYER_NAME = "missing";
    public static final Optional<Game> ABSENT_GAME = Optional.absent();
    public static final char EXISTING_LETTER_UPPER = 'C';
    public static final char MISSING_LETTER = 'a';

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private GameRepository gameRepository;

    @Mock
    private WordProvider wordProvider;

    private Game initialGame;

    private GameService gameService;

    @Before
    public void setUp() {
        initialGame = new Game(PLAYER_NAME, WORD);
        gameService = new GameService(gameRepository, wordProvider);
    }

    @Test
    public void findActiveGamesDelegatesToTheRepository() {
        when(gameRepository.findActiveGames()).thenReturn(GAMES);

        List<Game> games = gameService.findActiveGames();

        assertThat(games, equalTo(GAMES));
    }

    @Test
    public void createCreatesAndPersistTheGameWhenGameDoesNotExistByPlayerName() {
        when(wordProvider.getWord()).thenReturn(WORD);

        Game game = gameService.create(PLAYER_NAME);

        verify(gameRepository).save(game);
        assertThat(game, equalTo(game));
    }

    @Test
    public void guessThrowsIllegalArgumentExceptionWhenGameIsMissingForId() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Game does not exist by player name missing");
        when(gameRepository.getByPlayerName(MISSING_PLAYER_NAME)).thenReturn(ABSENT_GAME);

        gameService.guess(MISSING_PLAYER_NAME, EXISTING_LETTER_LOWER);
    }

    @Test
    public void guessIncreasesTheMissedLetterCountAndDoesNotChangeTheActualWordWhenGameExistsForIdAndLetterIsWrong() {
        when(gameRepository.getByPlayerName(PLAYER_NAME)).thenReturn(Optional.of(initialGame));

        Game game = gameService.guess(PLAYER_NAME, MISSING_LETTER);

        verify(gameRepository).save(game);
        assertThat(game.actualWord(), equalTo("__________"));
        assertThat(game.missedLetterCount(), equalTo(1));
    }

    @Test
    public void guessDoesNotIncreaseTheMissedLetterCountAndChangeTheActualWordWhenGameExistsForIdAndLetterIsGoodInLowerCase() {
        when(gameRepository.getByPlayerName(PLAYER_NAME)).thenReturn(Optional.of(initialGame));

        Game game = gameService.guess(PLAYER_NAME, EXISTING_LETTER_LOWER);

        verify(gameRepository).save(game);
        assertThat(game.actualWord(), equalTo("__c_______"));
        assertThat(game.missedLetterCount(), equalTo(0));
    }

    @Test
    public void guessDoesNotIncreaseTheMissedLetterCountAndChangeTheActualWordWhenGameExistsForIdAndLetterIsGoodInUpperCase() {
        when(gameRepository.getByPlayerName(PLAYER_NAME)).thenReturn(Optional.of(initialGame));

        Game game = gameService.guess(PLAYER_NAME, EXISTING_LETTER_UPPER);

        verify(gameRepository).save(game);
        assertThat(game.actualWord(), equalTo("__c_______"));
        assertThat(game.missedLetterCount(), equalTo(0));
    }

    @Test
    public void getThrowsIllegalArgumentExceptionWhenGameIsMissingForId() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Game does not exist by player name missing");
        when(gameRepository.getByPlayerName(MISSING_PLAYER_NAME)).thenReturn(ABSENT_GAME);

        gameService.get(MISSING_PLAYER_NAME);
    }

    @Test
    public void getReturnsWithGameWhenGameExistsForId() {
        when(gameRepository.getByPlayerName(PLAYER_NAME)).thenReturn(Optional.of(initialGame));

        Game game = gameService.get(PLAYER_NAME);

        assertThat(game, equalTo(initialGame));
    }
}
