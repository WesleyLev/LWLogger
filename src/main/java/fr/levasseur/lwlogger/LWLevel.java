package fr.levasseur.lwlogger;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.spi.LocationAwareLogger.*;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
enum LWLevel {

    /**
     * Trace LWLevel.
     */
    TRACE(TRACE_INT, LWColor.CYAN),
    /**
     * Debug LWLevel.
     */
    DEBUG(DEBUG_INT, LWColor.PURPLE),
    /**
     * Info LWLevel.
     */
    INFO(INFO_INT, LWColor.BLUE),
    /**
     * Warn LWLevel.
     */
    WARN(WARN_INT, LWColor.YELLOW),
    /**
     * Error LWLevel.
     */
    ERROR(ERROR_INT, LWColor.RED),
    /**
     * Console LWLevel.
     */
    CONSOLE(50, LWColor.GREEN);


    private static final Map<Integer, LWLevel> levels;

    static {
        levels = new HashMap<>();
        LWLevel[] a;
        final int j = (a = values()).length;
        for (int i = 0; i < j; i++) {
            LWLevel r = a[i];
            levels.put(r.getLevel(), r);
        }
    }

    private final int level;
    private final LWColor color;

    LWLevel(final int level, @NotNull final LWColor color) {
        this.level = level;
        this.color = color;
    }

    /**
     * Level to LWLevel LWLevel.
     *
     * @param level the level
     * @return the LWLevel
     */
    @NotNull
    public static LWLevel levelToLWLevel(final int level) {
        return levels.get(level);
    }

    /**
     * Gets levels.
     *
     * @return the levels
     */
    public static Map<Integer, LWLevel> getLevels() {
        return levels;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public LWColor getColor() {
        return color;
    }
}
