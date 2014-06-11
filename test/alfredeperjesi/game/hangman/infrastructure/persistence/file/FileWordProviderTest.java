package alfredeperjesi.game.hangman.infrastructure.persistence.file;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import alfredeperjesi.game.hangman.application.WordProvider;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileWordProviderTest {

    private static final String WORDS = "words.txt";

    private static final String NON_EXISTENT = "non_existent.txt";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private WordProvider wordProvider;

	@Before
	public void setUp() throws IOException {
		wordProvider = new FileWordProvider(WORDS);
	}

    @Test
    public void constructorThrowsExceptionWhenFileDoesNotExist() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Failed to load word file non_existent.txt");

        wordProvider = new FileWordProvider(NON_EXISTENT);
    }

	@Test
	public void getWordReturnsWithAWord() {
		String word = wordProvider.getWord();

		assertThat(word, notNullValue());
	}
}
