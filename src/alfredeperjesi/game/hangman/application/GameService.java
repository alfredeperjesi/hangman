package alfredeperjesi.game.hangman.application;

import alfredeperjesi.game.hangman.domain.Game;
import alfredeperjesi.game.hangman.domain.GameRepository;
import com.google.common.base.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final WordProvider wordProvider;

    @Autowired
    public GameService(final GameRepository gameRepository, final WordProvider wordProvider) {
        this.wordProvider = wordProvider;
        this.gameRepository = gameRepository;
    }

    public List<Game> findActiveGames() {
        return gameRepository.findActiveGames();
    }

    public Game get(final String playerName) {
        Optional<Game> existingGame = gameRepository.getByPlayerName(playerName);
        Validate.isTrue(existingGame.isPresent(), String.format("Game does not exist by player name %s", playerName));
        return existingGame.get();
    }

    public Game create(final String playerName) {
        String word = wordProvider.getWord();
        Game newGame = new Game(playerName, word);
        gameRepository.save(newGame);
        return newGame;
    }

    public Game guess(String playerName, Character guessedLetter) {
        Optional<Game> existingGame = gameRepository.getByPlayerName(playerName);
        Validate.isTrue(existingGame.isPresent(), String.format("Game does not exist by player name %s", playerName));
        Game game = existingGame.get();
        game.guess(guessedLetter);
        gameRepository.save(game);
        return game;
    }
}
