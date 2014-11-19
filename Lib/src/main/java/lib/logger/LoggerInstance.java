package lib.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Justin on 21.10.2014.
 */
public class LoggerInstance {

    private static Logger LOG = LoggerFactory.getLogger(LoggerInstance.class);

    public static Logger getLogger () {
        return LoggerInstance.LOG;
    }

    public static void setLogger (Logger LOG) {
        LoggerInstance.LOG = LOG;
    }

}
