package alfredeperjesi.game.hangman.domain;

import com.google.common.base.Optional;

import java.util.List;

public interface GameRepository {
    Optional<Game> getByPlayerName(final String playerName);

    List<Game> findActiveGames();

    void save(Game newGame);
}
