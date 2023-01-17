package com.davixdevelop.worldeditselections.logger;

import org.apache.logging.log4j.Logger;

public class ApacheLogger implements ILogger {
    Logger logger;

    public ApacheLogger(Logger logger){
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
