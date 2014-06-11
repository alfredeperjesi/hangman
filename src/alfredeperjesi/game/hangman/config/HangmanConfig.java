package alfredeperjesi.game.hangman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("alfredeperjesi.game.hangman")
public class HangmanConfig {
    @Bean
    public String wordFileName() {
        return "words.txt";
    }
}
