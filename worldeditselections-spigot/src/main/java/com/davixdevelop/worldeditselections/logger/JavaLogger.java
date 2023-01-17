package com.davixdevelop.worldeditselections.logger;

import java.util.logging.Logger;

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
