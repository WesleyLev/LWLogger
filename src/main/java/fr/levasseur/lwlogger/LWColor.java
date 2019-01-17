package fr.levasseur.lwlogger;

import org.jetbrains.annotations.NotNull;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
enum LWColor {
    /**
     * Reset LWColor.
     */
    RESET("\u001B[0m"),
    /**
     * Black LWColor.
     */
    BLACK("\u001B[30m"),
    /**
     * Red LWColor.
     */
    RED("\u001B[31m"),
    /**
     * Green LWColor.
     */
    GREEN("\u001B[32m"),
    /**
     * Yellow LWColor.
     */
    YELLOW("\u001B[33m"),
    /**
     * Blue LWColor.
     */
    BLUE("\u001B[34m"),
    /**
     * Purple LWColor.
     */
    PURPLE("\u001B[35m"),
    /**
     * Cyan LWColor.
     */
    CYAN("\u001B[36m"),
    /**
     * White LWColor.
     */
    WHITE("\u001B[37m");

    private final String str;

    LWColor(@NotNull final String str) {
        this.str = str;
    }

    /**
     * Gets str.
     *
     * @return the str
     */
    public String getStr() {
        return str;
    }
}
