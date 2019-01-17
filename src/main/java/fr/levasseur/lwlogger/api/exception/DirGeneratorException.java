package fr.levasseur.lwlogger.api.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class DirGeneratorException extends Exception {

    public DirGeneratorException(@NotNull final String p) {
        super("(" + DirGeneratorException.class.getPackage().getName() + ") » Generate error. *" + p + "*");
    }
}
