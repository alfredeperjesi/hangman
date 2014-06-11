package alfredeperjesi.game.hangman.infrastructure.presentation.rest;

import alfredeperjesi.game.hangman.application.GameService;
import alfredeperjesi.game.hangman.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/{playerName}", method = RequestMethod.GET)
    public GameResource getGame(@PathVariable("playerName") final String playerName) {
        Game game = gameService.getGameByPlayerName(playerName);
        return gameResourceAssembler.assemble(game);
    }

    @RequestMapping(value = "/{playerName}", method = RequestMethod.POST)
    public GameResource create(@PathVariable("playerName") final String playerName) {
        Game game = gameService.create(playerName);
        return gameResourceAssembler.assemble(game);
    }


}
