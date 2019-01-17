package fr.levasseur.lwlogger;

import fr.levasseur.lwlogger.api.exception.DirGeneratorException;
import fr.levasseur.lwlogger.api.tool.DirGenerator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.ILoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class LWFactory implements ILoggerFactory {

    private final File logsDir;
    private final ConcurrentMap<String, LWLogger> loggers = new ConcurrentHashMap<>();
    private final List<String> logs = new ArrayList<>();

    /**
     * Instantiates a new LWFactory.
     *
     * @param logsDir the logs dir
     * @throws DirGeneratorException the dir generator exception
     */
    LWFactory(@NotNull final String logsDir) throws DirGeneratorException {
        this.logsDir = new DirGenerator(logsDir);
    }

    @Override
    public LWLogger getLogger(@NotNull final String name) {
        LWLogger logger = loggers.get(name);
        if (logger != null)
            return logger;
        else {
            LWLogger n = new LWLogger(name, this);
            LWLogger o = loggers.putIfAbsent(name, n);
            return o == null ? n : o;
        }
    }

    /**
     * Log.
     *
     * @param log the log
     */
    void log(@NotNull final String log) {
        this.logs.add(log);
    }

    /**
     * Save.
     *
     * @throws IOException the io exception
     */
    public void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.logsDir.getPath() + "/" + LWLogger.dateFormat.format(new Date()).replace(":", "-") + ".log"), StandardCharsets.UTF_8));
        for (String line : logs) {
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
    }
}
