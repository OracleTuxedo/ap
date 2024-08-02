package mti.az;

import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogObject implements Serializable {
    // private static final long serialVersionUID = -3332952364956602788L;
    private static final long serialVersionUID = 1L;

    private transient Logger logger;

    protected final String name;

    public LogObject() {
        this.name = getClass().getName();
    }

    public LogObject(String loggerName) {
        this.name = loggerName;
    }

    private void initLogger() {
        if (logger == null)
            logger = LogManager.getLogger(name);
    }

    public boolean isDebugEnabled() {
        initLogger();
        return logger.isDebugEnabled();
    }

    public void debug(String message) {
        if (isDebugEnabled()) {
            logger.debug(message);
        }
    }

    public void debug(String message, Object... objects) {
        if (isDebugEnabled()) {
            logger.debug(message, objects);
        }
    }

    public void debug(String arg0, Throwable throwable) {
        if (isDebugEnabled()) {
            logger.debug(arg0, throwable);
        }
    }

    public boolean isWarnEnabled() {
        initLogger();
        return logger.isWarnEnabled();
    }

    public void warn(String message) {
        if (isWarnEnabled()) {
            logger.warn(message);
        }
    }

    public void warn(String message, Object... objects) {
        if (isWarnEnabled()) {
            logger.warn(message, objects);
        }
    }

    public void warn(String arg0, Throwable throwable) {
        if (isWarnEnabled()) {
            logger.warn(arg0, throwable);
        }
    }

    public boolean isErrorEnabled() {
        initLogger();
        return logger.isErrorEnabled();
    }

    public void error(String message) {
        if (isErrorEnabled()) {
            logger.error(message);
        }
    }

    public void error(String message, Object... objects) {
        if (isErrorEnabled()) {
            logger.error(message, objects);
        }
    }

    public void error(String arg0, Throwable throwable) {
        if (isErrorEnabled()) {
            logger.error(arg0, throwable);
        }
    }

    public boolean isInfoEnabled() {
        initLogger();
        return logger.isInfoEnabled();
    }

    public void info(String message) {
        if (isInfoEnabled()) {
            logger.info(message);
        }
    }

    public void info(String message, Object... objects) {
        if (isInfoEnabled()) {
            logger.info(message, objects);
        }
    }

    public void info(String arg0, Throwable throwable) {
        if (isInfoEnabled()) {
            logger.info(arg0, throwable);
        }
    }
}
