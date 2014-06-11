package alfredeperjesi.game.hangman.infrastructure.persistence.file;

import static alfredeperjesi.game.hangman.Fixtures.GAME;
import static alfredeperjesi.game.hangman.Fixtures.PLAYER_NAME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import alfredeperjesi.game.hangman.domain.Game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

public class FileGameRepositoryTest {

	private static final String TEST_REPOSITORY = "testRepository";

	private FileGameRepository fileGameRepository;

	@Before
	public void setUp() {
		fileGameRepository = new FileGameRepository(TEST_REPOSITORY);
	}

	@After
	public void tearDown() throws IOException {
		FileUtils.deleteDirectory(new File(TEST_REPOSITORY));
	}

	@Test
	public void getByPlayerNameReturnsWithAbsentWhenNoGameByPlayerName() {
		Optional<Game> result = fileGameRepository.getByPlayerName(PLAYER_NAME);

		assertThat(result.isPresent(), equalTo(false));
	}

	@Test
	public void getByPlayerNameReturnsWithTheProperGameWhenGameFoundByPlayerName() {
		fileGameRepository.save(GAME);
		Optional<Game> result = fileGameRepository.getByPlayerName(PLAYER_NAME);

		assertThat(result.isPresent(), equalTo(true));
	}

    @Test
    public void findActiveGamesReturnsWithEmptyListWhenNoActiveGame() {
        List<Game> result = fileGameRepository.findActiveGames();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(0));
    }

    @Test
    public void findActiveGamesReturnsWithActiveGame() {
        fileGameRepository.save(GAME);
        List<Game> result = fileGameRepository.findActiveGames();

        assertThat(result, notNullValue());
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0), equalTo(GAME));
    }
}
