package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import alfredeperjesi.game.hangman.domain.Game;
import org.springframework.stereotype.Component;

@Component
public class GameResourceAssembler {
    public GameResource assemble(Game game) {
        return new GameResource(game.actualWord(), game.missedLetterCount());
    }
}
