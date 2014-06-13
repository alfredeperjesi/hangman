package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import alfredeperjesi.game.hangman.application.GameService;
import alfredeperjesi.game.hangman.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/games", produces = "application/json")
public class HangmanController {
    private final GameService gameService;
    private final GameResourceAssembler gameResourceAssembler;

    @Autowired
    public HangmanController(final GameService gameService, final GameResourceAssembler gameResourceAssembler) {
        this.gameService = gameService;
        this.gameResourceAssembler = gameResourceAssembler;
    }

    @RequestMapping(value = "/{playerName}/{guessedLetter}", method = RequestMethod.POST)
    public GameResource guess(@PathVariable("playerName") final String playerName, @PathVariable("guessedLetter") final Character guessedLetter) {
        Game game = gameService.guess(playerName, guessedLetter);
        return gameResourceAssembler.assemble(game);
    }

    @RequestMapping(value = "/{playerName}", method = RequestMethod.POST)
    public GameResource create(@PathVariable("playerName") final String playerName) {
        Game game = gameService.create(playerName);
        return gameResourceAssembler.assemble(game);
    }

    @RequestMapping(value = "/{playerName}", method = RequestMethod.GET)
    public GameResource get(@PathVariable("playerName") final String playerName) {
        Game game = gameService.get(playerName);
        return gameResourceAssembler.assemble(game);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GameResource> findActives() {
        List<Game> games = gameService.findActiveGames();
        return gameResourceAssembler.assemble(games);
    }
}
