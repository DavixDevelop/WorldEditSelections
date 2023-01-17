package com.davixdevelop.worldeditselections.logger;

/**
 * Simple interface for logger through which to send error/debug messages
 *
 * @author DavixDevelop
 */
public interface ILogger {
    void sendError(String error);
    void sendDebug(String debug);
}
