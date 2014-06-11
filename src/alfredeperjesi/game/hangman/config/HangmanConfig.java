package alfredeperjesi.game.hangman.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
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

    @Bean(name = "repositoryFolder")
    public String repositoryFolder() {
        return repositoryFileFolder;
    }

    @Bean(name = "wordFileName")
    public String wordFileName() {
        return wordFileName;
    }
}
