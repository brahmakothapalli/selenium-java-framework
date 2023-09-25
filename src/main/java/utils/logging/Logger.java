package utils.logging;

import org.slf4j.LoggerFactory;

public final class Logger {

    private static final ThreadLocal<org.slf4j.Logger> LOG4J = ThreadLocal
            .withInitial(()-> LoggerFactory.getLogger(String.valueOf(Thread.currentThread().getId())));

    private static final ThreadLocal<Logger> LOGGER_INSTANCE = ThreadLocal.withInitial(Logger::new);

    public static Logger getInstance(){
        return LOGGER_INSTANCE.get();
    }

    public void info(String message){
        LOG4J.get().info(message);
    }

    public void error(String message){
        LOG4J.get().error(message);
    }

    public void warn(String message){
        LOG4J.get().warn(message);
    }

    public void releaseLogger(){
        LOGGER_INSTANCE.remove();
    }

}
