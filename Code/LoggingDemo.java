
package learnJava;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingDemo {
    public static void main(String[] args) {
        Logger.getGlobal().info("Starting program");

        Logger.getGlobal().setLevel(Level.OFF);
        
        Logger.getGlobal().info("Global logger turned off");
        Logger logger = Logger.getLogger("com.horstmann.corejava");
        logger.fine("Global logger turned off");
        
        logger.setLevel(Level.FINE);
        logger.fine("Set logger to fine");
        logger.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        
        handler.setLevel(Level.FINE);        
        logger.addHandler(handler);
        logger.fine("Configured handler");

// May 07, 2020 2:33:25 PM learnJava.LoggingDemo main
// INFO: Starting program
// May 07, 2020 2:33:25 PM learnJava.LoggingDemo main
// FINE: Configured handler

    }
}
