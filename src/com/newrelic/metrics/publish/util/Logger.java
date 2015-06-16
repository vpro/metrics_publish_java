package com.newrelic.metrics.publish.util;

/**
 * Logger class which supports logging at debug, info, warn, error, and fatal levels.
 * By default the log level is info.
 * <p/>
 * Logging is directed to the console and to a log file. The log file name and location is configurable.
 * <p/>
 * <p/>
 * For usage see, {@link #getLogger(Class)}
 * <p/>
 * <p/>
 */
public final class Logger {

    private final org.slf4j.Logger logger;

    private Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    /**
     * Get a logger for a specific class.
     * <p/>
     * For better visibility into where log messages are reported from,
     * it is recommended to create a static logger once per class.
     * <p/>
     * <p/>
     * Examples:
     * <p/>
     * <pre>
     * {@code
     * private static final Logger logger = Logger.getLogger(ExampleAgent.class);
     * logger.debug("debug log message");
     * logger.error(new RuntimeError(), "error log message", "\tsecond message");
     * }
     *
     *
     * @param klass the class name will be used for the name of the logger
     * @return Logger
     */
    public static Logger getLogger(Class<?> klass) {
        return new Logger((org.slf4j.LoggerFactory.getLogger(klass)));
    }

    private static boolean isNullOrEmptyString(String value) {
        return value == null || value.length() == 0;
    }

    /**
     * Log a message with a variable number of arguments at the debug level.
     * Only logs if the debug level is enabled.
     *
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void debug(Object... messages) {
        if(logger.isDebugEnabled()) {
            logger.debug(buildMessage(messages));
        }
    }

    /**
     * Log a throwable and a message with a variable number of arguments at the debug level.
     * Only logs if the debug level is enabled.
     *
     * @param throwable
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void debug(Throwable throwable, Object... messages) {
        if(logger.isDebugEnabled()) {
            logger.debug(buildMessage(messages), throwable);
        }
    }

    /**
     * Log a message with a variable number of arguments at the info level.
     * Only logs if the info level is enabled.
     *
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void info(Object... messages) {
        if(logger.isInfoEnabled()) {
            logger.info(buildMessage(messages));
        }
    }

    /**
     * Log a throwable and a message with a variable number of arguments at the info level.
     * Only logs if the info level is enabled.
     *
     * @param throwable
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void info(Throwable throwable, Object... messages) {
        if(logger.isInfoEnabled()) {
            logger.info(buildMessage(messages), throwable);
        }
    }

    /**
     * Log a message with a variable number of arguments at the warn level.
     * Only logs if the warn level is enabled.
     *
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void warn(Object... messages) {
        if(logger.isWarnEnabled()) {
            logger.warn(buildMessage(messages));
        }
    }

    /**
     * Log a throwable and a message with a variable number of arguments at the warn level.
     * Only logs if the warn level is enabled.
     *
     * @param throwable
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void warn(Throwable throwable, Object... messages) {
        if(logger.isWarnEnabled()) {
            logger.warn(buildMessage(messages), throwable);
        }
    }

    /**
     * Log a message with a variable number of arguments at the error level.
     * Only logs if the error level is enabled.
     *
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void error(Object... messages) {
        if(logger.isErrorEnabled()) {
            logger.error(buildMessage(messages));
        }
    }

    /**
     * Log a throwable and a message with a variable number of arguments at the error level.
     * Only logs if the error level is enabled.
     *
     * @param throwable
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void error(Throwable throwable, Object... messages) {
        if(logger.isErrorEnabled()) {
            logger.error(buildMessage(messages), throwable);
        }
    }

    /**
     * Log a message with a variable number of arguments at the fatal level.
     * Only logs if the fatal level is enabled.
     * <p/>
     * <p/>
     * Note: Currently fatal logs at the same level as error. This may change in a future release.
     *
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void fatal(Object... messages) {
        error(messages);
    }

    /**
     * Log a throwable and a message with a variable number of arguments at the fatal level.
     * Only logs if the fatal level is enabled.
     * <p/>
     * <p/>
     * Note: Currently fatal logs at the same level as error. This may change in a future release.
     *
     * @param throwable
     * @param messages
     * @throws IllegalArgumentException if messages is null
     */
    public void fatal(Throwable throwable, Object... messages) {
        error(throwable, messages);
    }

    static String buildMessage(Object... messages) {
        if(messages == null) {
            throw new IllegalArgumentException("'messages' cannot be null");
        }

        StringBuilder builder = new StringBuilder();
        for(Object message : messages) {
            builder.append(message);
        }
        return builder.toString();
    }
}
