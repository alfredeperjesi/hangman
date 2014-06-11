package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import alfredeperjesi.game.hangman.application.GameService;
import alfredeperjesi.game.hangman.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HangmanController {
    private final GameService gameService;
    private final GameResourceAssembler gameResourceAssembler;

    @Autowired
    public HangmanController(final GameService gameService, final GameResourceAssembler gameResourceAssembler) {
        this.gameService = gameService;
        this.gameResourceAssembler = gameResourceAssembler;
    }

    @RequestMapping(value = "/games/{playerName}", method = RequestMethod.GET, produces = "application/json")
    public GameResource getGame(@PathVariable("playerName") final String playerName) {
        Game game = gameService.getGameByPlayerName(playerName);
        return gameResourceAssembler.assemble(game);
    }

    @RequestMapping(value = "/games/{playerName}", method = RequestMethod.POST, produces = "application/json")
    public GameResource create(@PathVariable("playerName") final String playerName) {
        Game game = gameService.create(playerName);
        return gameResourceAssembler.assemble(game);
    }


}
