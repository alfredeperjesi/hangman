package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import alfredeperjesi.game.hangman.domain.Game;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameResourceAssembler {
    public GameResource assemble(Game game) {
        return new GameResource(game.playerName(), game.actualWord(), game.missedLetterCount(), game.missedLetters());
    }

    public List<GameResource> assemble(List<Game> games) {
        List<GameResource> gameResources = Lists.newArrayList();
        for (Game game : games) {
            gameResources.add(assemble(game));
        }
        return gameResources;
    }
}
