package server;

import lib.cluster.SoCeCluster;
import lib.cluster.SoCeServer;
import org.apache.log4j.BasicConfigurator;

/**
 * Created by Justin on 18.11.2014.
 */
public class ServerMain {

    public static void main (String[] args) {
        //Log4J
        BasicConfigurator.configure();

        //Cluster Configuration
        SoCeCluster cluster = new SoCeCluster();

        //build up cluster
        SoCeServer server = new SoCeServer();
        server.setServerID(1);
        server.setServerName("Main Server #1");

        cluster.addServer(server);
    }

}
