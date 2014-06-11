package alfredeperjesi.game.hangman.infrastructure.persistence.file;

import alfredeperjesi.game.hangman.domain.Game;
import alfredeperjesi.game.hangman.domain.GameRepository;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileGameRepository implements GameRepository {
    @Override
    public Optional<Game> getByPlayerName(final String playerName) {
        return Optional.absent();
    }

    @Override
    public List<Game> findActiveGames() {
        return Lists.newArrayList();
    }

    @Override
    public void save(final Game newGame) {

    }
}
