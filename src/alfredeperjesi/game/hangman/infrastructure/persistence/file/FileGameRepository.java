package alfredeperjesi.game.hangman.infrastructure.persistence.file;

import alfredeperjesi.game.hangman.domain.Game;
import alfredeperjesi.game.hangman.domain.GameRepository;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileGameRepository implements GameRepository {
    private String repositoryFolder;

    @Autowired
    public FileGameRepository(@Qualifier("repositoryFolder") final String repositoryFolder) {
        try {
            FileUtils.forceMkdir(new File(repositoryFolder));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to create repository folder %s", repositoryFolder), e);
        }
        this.repositoryFolder = repositoryFolder;
    }

    @Override
    public Optional<Game> getByPlayerName(final String playerName) {
        try(InputStream file = new FileInputStream(getFileName(playerName));
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer)) {
            return Optional.of((Game) input.readObject());
        } catch (Exception e) {
        }

        return Optional.absent();
    }

    private File getFileName(String playerName) {
        return new File(repositoryFolder, playerName);
    }

    @Override
    public List<Game> findActiveGames() {
        ArrayList<Game> games = Lists.newArrayList();

        for (final File fileEntry : new File(repositoryFolder).listFiles()) {
            Optional<Game> game = getByPlayerName(fileEntry.getName());
            if(game.get().isActive()) {
                games.add(game.get());
            }
        }

        return games;
    }

    @Override
    public void save(final Game newGame) {

        try(OutputStream file = new FileOutputStream(getFileName(newGame.playerName()));
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);) {
             output.writeObject(newGame);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Unable to save game with player name %s", newGame.playerName()), e);
        }
    }
}
