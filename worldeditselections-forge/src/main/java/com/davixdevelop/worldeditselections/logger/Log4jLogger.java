package com.davixdevelop.worldeditselections.logger;

import org.apache.logging.log4j.Logger;

/**
 * Simple implementation of a logger through which to send error/debug messages, based on the Log4j Logger
 *
 * @author DavixDevelop
 */
public class Log4jLogger implements ILogger {
    Logger logger;

    public Log4jLogger(Logger logger){
        this.logger = logger;
    }

    @Override
    public void sendError(String error) {
        logger.error(error);
    }

    @Override
    public void sendDebug(String debug) {

    }
}
