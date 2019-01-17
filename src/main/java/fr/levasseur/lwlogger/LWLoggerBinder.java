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

    public LWLoggerBinder(@NotNull final String logsDir) throws DirGeneratorException {
        this.factory = new LWFactory(logsDir);
        this.SINGLETON = this;
    }

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
