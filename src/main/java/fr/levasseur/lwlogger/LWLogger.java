package fr.levasseur.lwlogger;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static fr.levasseur.lwlogger.LWLevel.*;

/**
 * Class © LEVASSEUR
 *
 * @author Wesley LEVASSEUR
 * @version 1.0.0
 */
public class LWLogger implements Logger {

    /**
     * The Date format.
     */
    protected static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String lineFormat = "[{DATE}] [{LEVEL}-{NAME}] {MESSAGE}";
    private final LWFactory factory;
    private final String name;
    private boolean debug = false, trace = false, info = true, warn = true, error = true;

    /**
     * Instantiates a new LWLogger.
     *
     * @param name    the name
     * @param factory the factory
     */
    public LWLogger(@NotNull final String name, @NotNull final LWFactory factory) {
        this.name = name;
        this.factory = factory;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets debug.
     *
     * @param debug the debug
     */
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }

    /**
     * Sets trace.
     *
     * @param trace the trace
     */
    public void setTrace(final boolean trace) {
        this.trace = trace;
    }

    /**
     * Sets info.
     *
     * @param info the info
     */
    public void setInfo(final boolean info) {
        this.info = info;
    }

    /**
     * Sets warn.
     *
     * @param warn the warn
     */
    public void setWarn(final boolean warn) {
        this.warn = warn;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(final boolean error) {
        this.error = error;
    }

    private void log(final int level, final String message, final Throwable t) {
        if (!this.isEnabled(level))
            return;
        String date = dateFormat.format(new Date()), str = String.valueOf(levelToLWLevel(level)), line = this.formatLine(date, str, message), color = levelToLWLevel(level).getColor().getStr();
        PrintStream tS = System.out;
        tS.println(color + line);
        this.factory.log(line);
        if (t != null) {
            StringBuilder throwMessage = new StringBuilder();
            throwMessage.append(formatLine(date, str, t.toString()));
            for (StackTraceElement traceElement : t.getStackTrace())
                throwMessage.append("\n").append(formatLine(date, str, "\tat " + traceElement.toString()));
            tS.println(color + throwMessage.toString());
            this.factory.log(throwMessage.toString());
        }
        tS.flush();
    }

    private String formatLine(final String date, final String level, final String message) {
        return LWLogger.lineFormat.replace("{DATE}", date).replace("{LEVEL}", level).replace("{NAME}", computeShortName()).replace("{MESSAGE}", message);
    }

    private String getFormattedDate() {
        return LWLogger.dateFormat.format(new Date());
    }

    private String computeShortName() {
        return this.name.substring(this.name.lastIndexOf(".") + 1);
    }

    private void formatAndLog(final int level, final String format, final Object a, final Object a2) {
        if (!isEnabled(level))
            return;
        FormattingTuple fT = MessageFormatter.format(format, a, a2);
        this.log(level, fT.getMessage(), fT.getThrowable());
    }

    private void formatAndLog(final int level, final String format, final Object... a) {
        if (!this.isEnabled(level))
            return;
        FormattingTuple fT = MessageFormatter.arrayFormat(format, a);
        this.log(level, fT.getMessage(), fT.getThrowable());
    }

    private boolean isEnabled(final int level) {
        return (TRACE.getLevel() == level ? this.trace : DEBUG.getLevel() == level ? this.debug : INFO.getLevel() == level ? this.info : WARN.getLevel() == level ? this.warn : ERROR.getLevel() != level || this.error);
    }

    @Override
    public boolean isTraceEnabled() {
        return this.isEnabled(TRACE.getLevel());
    }

    @Override
    public void trace(final String msg) {
        this.log(TRACE.getLevel(), msg, null);
    }

    @Override
    public void trace(final String format, final Object arg) {
        this.formatAndLog(TRACE.getLevel(), format, arg, null);
    }

    @Override
    public void trace(final String format, final Object arg1, final Object arg2) {
        this.formatAndLog(TRACE.getLevel(), format, arg1, arg2);
    }

    @Override
    public void trace(final String format, final Object... arguments) {
        this.formatAndLog(TRACE.getLevel(), format, arguments);
    }

    @Override
    public void trace(final String msg, final Throwable t) {
        this.log(TRACE.getLevel(), msg, t);
    }

    @Override
    public boolean isTraceEnabled(@Ignore final Marker ignore) {
        return this.isTraceEnabled();
    }

    @Override
    public void trace(@Ignore final Marker ignore, final String msg) {
        this.trace(msg);
    }

    @Override
    public void trace(@Ignore final Marker ignore, final String format, final Object arg) {
        this.trace(format, arg);
    }

    @Override
    public void trace(@Ignore final Marker ignore, final String format, final Object arg1, final Object arg2) {
        this.trace(format, arg1, arg2);
    }

    @Override
    public void trace(@Ignore final Marker ignore, final String format, final Object... argArray) {
        this.trace(format, argArray);
    }

    @Override
    public void trace(@Ignore final Marker ignore, final String msg, final Throwable t) {
        this.trace(msg, t);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.isEnabled(DEBUG.getLevel());
    }

    @Override
    public void debug(final String msg) {
        this.log(DEBUG.getLevel(), msg, null);
    }

    @Override
    public void debug(final String format, final Object arg) {
        this.formatAndLog(DEBUG.getLevel(), format, arg, null);
    }

    @Override
    public void debug(final String format, final Object arg1, final Object arg2) {
        this.formatAndLog(DEBUG.getLevel(), format, arg1, arg2);
    }

    @Override
    public void debug(final String format, final Object... arguments) {
        this.formatAndLog(DEBUG.getLevel(), format, arguments);
    }

    @Override
    public void debug(final String msg, final Throwable t) {
        this.log(DEBUG.getLevel(), msg, t);
    }

    @Override
    public boolean isDebugEnabled(@Ignore final Marker ignore) {
        return this.isDebugEnabled();
    }


    @Override
    public void debug(@Ignore final Marker ignore, final String msg) {
        this.debug(msg);
        this.isDebugEnabled(ignore);
    }

    @Override
    public void debug(@Ignore final Marker ignore, final String format, final Object arg) {
        this.debug(format, arg);
    }

    @Override
    public void debug(@Ignore final Marker ignore, final String format, final Object arg1, final Object arg2) {
        this.debug(format, arg1, arg2);
    }

    @Override
    public void debug(@Ignore final Marker ignore, final String format, final Object... arguments) {
        this.debug(format, arguments);
    }

    @Override
    public void debug(@Ignore final Marker ignore, final String msg, final Throwable t) {
        this.debug(msg, t);

    }

    @Override
    public boolean isInfoEnabled() {
        return this.isEnabled(INFO.getLevel());
    }

    @Override
    public void info(final String msg) {
        this.log(INFO.getLevel(), msg, null);
    }

    @Override
    public void info(final String format, final Object arg) {
        this.formatAndLog(INFO.getLevel(), format, arg, null);
    }

    @Override
    public void info(final String format, final Object arg1, final Object arg2) {
        this.formatAndLog(INFO.getLevel(), format, arg1, arg2);
    }

    @Override
    public void info(final String format, final Object... arguments) {
        this.formatAndLog(INFO.getLevel(), format, arguments);
    }

    @Override
    public void info(final String msg, final Throwable t) {
        this.log(INFO.getLevel(), msg, t);
    }

    @Override
    public boolean isInfoEnabled(@Ignore final Marker ignore) {
        return this.isInfoEnabled();
    }

    @Override
    public void info(@Ignore final Marker ignore, final String msg) {
        this.info(msg);
    }

    @Override
    public void info(@Ignore final Marker ignore, final String format, final Object arg) {
        this.info(format, arg);
    }

    @Override
    public void info(@Ignore final Marker ignore, final String format, final Object arg1, final Object arg2) {
        this.info(format, arg1, arg2);
    }

    @Override
    public void info(@Ignore final Marker ignore, final String format, final Object... arguments) {
        this.info(format, arguments);
    }

    @Override
    public void info(@Ignore final Marker ignore, final String msg, final Throwable t) {
        this.info(msg, t);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.isEnabled(WARN.getLevel());
    }

    @Override
    public void warn(final String msg) {
        this.log(WARN.getLevel(), msg, null);
    }

    @Override
    public void warn(final String format, final Object arg) {
        this.formatAndLog(WARN.getLevel(), format, arg, null);
    }

    @Override
    public void warn(final String format, final Object... arguments) {
        this.formatAndLog(WARN.getLevel(), format, arguments);
    }

    @Override
    public void warn(final String format, final Object arg1, final Object arg2) {
        this.formatAndLog(WARN.getLevel(), format, arg1, arg2);
    }

    @Override
    public void warn(final String msg, final Throwable t) {
        this.log(WARN.getLevel(), msg, t);
    }

    @Override
    public boolean isWarnEnabled(@Ignore final Marker ignore) {
        return this.isWarnEnabled();
    }

    @Override
    public void warn(@Ignore final Marker ignore, final String msg) {
        this.warn(msg);
    }

    @Override
    public void warn(@Ignore final Marker ignore, final String format, final Object arg) {
        this.warn(format, arg);
    }

    @Override
    public void warn(@Ignore final Marker ignore, final String format, final Object arg1, final Object arg2) {
        this.warn(format, arg1, arg2);
    }

    @Override
    public void warn(@Ignore final Marker ignore, final String format, final Object... arguments) {
        this.warn(format, arguments);
    }

    @Override
    public void warn(@Ignore final Marker ignore, final String msg, final Throwable t) {
        this.warn(msg, t);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.isEnabled(ERROR.getLevel());
    }

    @Override
    public void error(final String msg) {
        this.log(ERROR.getLevel(), msg, null);
    }

    @Override
    public void error(final String format, final Object arg) {
        this.formatAndLog(ERROR.getLevel(), format, arg, null);
    }

    @Override
    public void error(final String format, final Object arg1, final Object arg2) {
        this.formatAndLog(ERROR.getLevel(), format, arg1, arg2);
    }

    @Override
    public void error(final String format, final Object... arguments) {
        this.formatAndLog(ERROR.getLevel(), format, arguments);
    }

    @Override
    public void error(final String msg, final Throwable t) {
        this.log(ERROR.getLevel(), msg, t);
    }

    @Override
    public boolean isErrorEnabled(@Ignore final Marker ignore) {
        return isErrorEnabled();
    }

    @Override
    public void error(@Ignore final Marker ignore, final String msg) {
        this.error(msg);
    }

    @Override
    public void error(@Ignore final Marker ignore, final String format, final Object arg) {
        this.error(format, arg);
    }

    @Override
    public void error(@Ignore final Marker ignore, final String format, final Object arg1, final Object arg2) {
        this.error(format, arg1, arg2);
    }

    @Override
    public void error(@Ignore final Marker ignore, final String format, final Object... arguments) {
        this.error(format, arguments);
    }

    @Override
    public void error(@Ignore final Marker ignore, final String msg, final Throwable t) {
        this.error(msg, t);
    }
}
