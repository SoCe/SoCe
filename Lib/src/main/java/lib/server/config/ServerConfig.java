package lib.server.config;

import lib.config.SoCeConfig;
import lib.logger.LoggerInstance;

import java.util.HashMap;

/**
 * Created by Justin on 21.10.2014.
 */
public class ServerConfig extends SoCeConfig {

    protected static HashMap<String,Integer> portlist = new HashMap<String,Integer>();

    public static int getPort (String servername) {
        if (ServerConfig.portlist.containsKey(servername)) {
            return ServerConfig.portlist.get(servername);
        } else {
            LoggerInstance.getLogger().error("No port for server service " + servername + ".");
            return 0;
        }
    }

    public static void setPort (String servername, int port) {
        ServerConfig.portlist.put(servername, port);
    }

}
