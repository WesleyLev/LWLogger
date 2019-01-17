package fr.levasseur.lwlogger.api.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class DirGeneratorException extends Exception {

    /**
     * Instantiates a new DirGenerator exception.
     *
     * @param p the p
     */
    public DirGeneratorException(@NotNull final String p) {
        super("(" + DirGeneratorException.class.getPackage().getName() + ") » Generate error. *" + p + "*");
    }
}
