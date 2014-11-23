package server.impl;

import lib.cluster.SoCeCluster;
import lib.cluster.SoCeServer;
import lib.ldap.impl.SoCeLDAPClient;
import lib.module.IModuleManager;
import lib.queue.impl.SoCePriorityQueue;
import lib.server.IServer;
import lib.task.IModuleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Justin on 23.11.2014.
 */
public class ServerApplication implements IServer {

    protected static int buildNumber = 1;
    ServerModuleManagerImpl moduleManager = null;
    private Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    @Override
    public SoCePriorityQueue<IModuleTask> getQueue() {
        return null;
    }

    @Override
    public IModuleManager getModuleManager() {
        return this.moduleManager;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public SoCeLDAPClient getLDAPClient() {
        return null;
    }

    @Override
    public void run() {
        this.getLogger().debug("Start modules.");

        //http://blog.knoldus.com/2011/03/07/testing-rest-with-grizzly/

        //Cluster Configuration
        SoCeCluster cluster = new SoCeCluster();

        //build up cluster
        SoCeServer server = new SoCeServer();
        server.setServerID(1);
        server.setServerName("Main Server #1");
        server.setHost("localhost");
        server.setPort(50999);

        cluster.addServer(server);

        //load modules
        this.moduleManager = new ServerModuleManagerImpl(this, ServerApplication.buildNumber);
        moduleManager.loadModules("./modules");

        //start modules
        moduleManager.startModules();
    }

    public void close () {
        this.moduleManager.stopModules();
        System.exit(0);
    }
}
