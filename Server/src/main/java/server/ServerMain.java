package server;

import lib.cluster.SoCeCluster;
import lib.cluster.SoCeServer;
import org.apache.log4j.BasicConfigurator;
import server.impl.ServerApplication;

/**
 * Created by Justin on 18.11.2014.
 */
public class ServerMain {

    public static void main (String[] args) {
        //Log4J
        BasicConfigurator.configure();

        ServerApplication clientApplication = new ServerApplication();
        Thread thread = new Thread(clientApplication);
        thread.start();
    }

}
