package fr.levasseur.lwlogger.api.tool;

import fr.levasseur.lwlogger.api.exception.DirGeneratorException;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static java.lang.System.getProperty;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class DirGenerator extends File {

    /**
     * Instantiates a new Dir generator.
     *
     * @param dirName the dir name
     * @throws DirGeneratorException the dir generator exception
     */
    public DirGenerator(@NotNull final String dirName) throws DirGeneratorException {
        super(getProperty("os.name").toLowerCase().contains("win") ? getProperty("user.home") + "\\AppData\\Roaming\\." + dirName : getProperty("os.name").toLowerCase().contains("mac") ? getProperty("user.home") + "/Library/Application Support/" + dirName : getProperty("user.home") + "/." + dirName);
        if (!this.exists())
            if (!this.mkdir())
                throw new DirGeneratorException(dirName);
    }
}
