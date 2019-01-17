package fr.levasseur.lwlogger;

import org.jetbrains.annotations.NotNull;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
enum LWColor {
    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    private final String str;

    LWColor(@NotNull final String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
