package alfredeperjesi.game.hangman.infrastructure.persistence.file;

import alfredeperjesi.game.hangman.application.WordProvider;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class FileWordProvider implements WordProvider {
    private final List<String> words;
    private SecureRandom secureRandom;

    @Autowired
    public FileWordProvider(final String wordFileName) {
        try {
            words = IOUtils.readLines(getClass().getClassLoader().getResourceAsStream(wordFileName));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load word file %s", wordFileName), e);
        }
        secureRandom = new SecureRandom();
    }

    @Override
    public String getWord() {
        return words.get(secureRandom.nextInt(words.size()));
    }
}
