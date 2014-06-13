package alfredeperjesi.game.hangman;

import alfredeperjesi.game.hangman.domain.Game;
import alfredeperjesi.game.hangman.infrastructure.presentation.rest.GameResource;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;

public final class Fixtures {

    public static final String PLAYER_NAME = "player";
    public static final String WORD = "secretword";
    public static final Game GAME = new Game(PLAYER_NAME, WORD);
    public static final List<Game> GAMES = Lists.newArrayList(GAME);
    public static final char EXISTING_LETTER_LOWER = 'c';

    public static final GameResource GAME_RESOURCE = new GameResource(PLAYER_NAME, WORD, 0, new HashSet<Character>());

    private Fixtures() {

    }
}
