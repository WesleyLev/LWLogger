package fr.levasseur.lwlogger;

import fr.levasseur.lwlogger.api.exception.DirGeneratorException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.spi.LoggerFactoryBinder;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class LWLoggerBinder implements LoggerFactoryBinder {

    private final LWLoggerBinder SINGLETON;
    private final LWFactory factory;

    /**
     * Instantiates a new LWLoggerBinder.
     *
     * @param logsDir the logs dir
     * @throws DirGeneratorException the dir generator exception
     */
    public LWLoggerBinder(@NotNull final String logsDir) throws DirGeneratorException {
        this.factory = new LWFactory(logsDir);
        this.SINGLETON = this;
    }

    /**
     * Gets singleton.
     *
     * @return the singleton
     */
    public LWLoggerBinder getSingleton() {
        return this.SINGLETON;
    }

    @Override
    public LWFactory getLoggerFactory() {
        return this.factory;
    }

    @Override
    public String getLoggerFactoryClassStr() {
        return this.factory.getClass().getName();
    }
}
