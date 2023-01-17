package com.davixdevelop.worldeditselections.logger;

import java.util.logging.Logger;

/**
 * Simple implementation of a logger through which to send error/debug messages, based on the default Java Logger
 *
 * @author DavixDevelop
 */
public class JavaLogger implements ILogger{
    Logger logger;

    public JavaLogger(Logger logger){
        this.logger = logger;
    }
    @Override
    public void sendError(String error) {
        logger.warning(error);
    }

    @Override
    public void sendDebug(String debug) {
        logger.info(debug);
    }
}
