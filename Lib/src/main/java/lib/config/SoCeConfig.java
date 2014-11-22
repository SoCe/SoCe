package lib.config;

import lib.logger.LoggerInstance;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Justin on 22.11.2014.
 */
public class SoCeConfig {

    private static HashMap<String,Object> config = new HashMap<String,Object>();
    protected static Ini ini = null;

    public static Object getObject (String key) {
        if (SoCeConfig.config.containsKey(key)) {
            return SoCeConfig.config.get(key);
        } else {
            LoggerInstance.getLogger().warn("there isnt an config object with key " + key + ".");
            return null;
        }
    }

    public static void setObject (String key, Object object) {
        SoCeConfig.config.put(key, object);
    }

    public static Ini getIni () {
        return ini;
    }

    public static void loadConfigFile (String filename) {
        ini = new Ini();
        try {
            ini.load(new FileReader(new File(filename)));
        } catch (IOException e) {
            LoggerInstance.getLogger().warn("IOException in class MinigamesConfig while loading an ini config file " + e.getLocalizedMessage() + ".");
        }
    }

}
