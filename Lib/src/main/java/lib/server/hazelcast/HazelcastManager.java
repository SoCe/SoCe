package lib.server.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import lib.logger.LoggerInstance;
import lib.server.config.ServerConfig;

/**
 * Created by Justin on 21.10.2014.
 */
public class HazelcastManager {

    private static HazelcastInstance client = null;

    public static HazelcastInstance connect (String ip, int port) {
        LoggerInstance.getLogger().debug("Connecting to hazelcast " + ip + ":" + port + ".");

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getGroupConfig().setName("dev").setPassword("dev-pass");
        clientConfig.getNetworkConfig().addAddress(ip, ip + ":" + port);
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        //IMap map = client.getMap("customers");
        //System.out.println("Map Size:" + map.size());

        //BlockingQueue<String> queue = client.getQueue("queue");
        //queue.put("Hello!");
        //System.out.println("Message send by client!");

        HazelcastManager.client = client;

        return client;
    }

    public static HazelcastInstance connect (ClientConfig clientConfig) {
        LoggerInstance.getLogger().debug("Connecting to Hazelcast...");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        HazelcastManager.client = client;
        return client;
    }

    public static void close () {
        if (client != null) {
            try {
                client.shutdown();
            } catch (Exception e) {
                LoggerInstance.getLogger().error("Exception in class HazelcastManager " + e.getLocalizedMessage() + ".");
            }
        }
    }

    public static HazelcastInstance getClient () {
        if (HazelcastManager.client != null) {
            return HazelcastManager.client;
        } else {
            LoggerInstance.getLogger().debug("HazelcastClient isnt initialized.");

            //load data from config
            if (ServerConfig.getObject("hazelcast_ip") != null && ServerConfig.getObject("hazelcast_port") != null) {
                String hazelcast_ip = (String) ServerConfig.getObject("hazelcast_ip");
                int port = (int) ServerConfig.getObject("hazelcast_port");

                LoggerInstance.getLogger().debug("Auto connect to hazelcast " + hazelcast_ip + ":" + port + ".");
                HazelcastManager.connect(hazelcast_ip, port);

                return HazelcastManager.client;
            }

            return null;
        }
    }

}
