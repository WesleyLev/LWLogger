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

    TRACE(TRACE_INT, LWColor.CYAN),
    DEBUG(DEBUG_INT, LWColor.PURPLE),
    INFO(INFO_INT, LWColor.BLUE),
    WARN(WARN_INT, LWColor.YELLOW),
    ERROR(ERROR_INT, LWColor.RED),
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

    @NotNull
    public static LWLevel levelToLWLevel(final int level) {
        return levels.get(level);
    }

    public static Map<Integer, LWLevel> getLevels() {
        return levels;
    }

    public int getLevel() {
        return level;
    }

    public LWColor getColor() {
        return color;
    }
}
