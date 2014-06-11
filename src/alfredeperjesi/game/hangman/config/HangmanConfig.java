package alfredeperjesi.game.hangman.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("alfredeperjesi.game.hangman")
@PropertySource("classpath:hangman.properties")
public class HangmanConfig {

    @Value("${repository.file.folder}")
    private String repositoryFileFolder;

    @Value("${wordProvider.file}")
    private String wordFileName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
        return props;
    }

    @Bean
    public String repositoryFolder() {
        return repositoryFileFolder;
    }

    @Bean
    public String wordFileName() {
        return wordFileName;
    }
}
