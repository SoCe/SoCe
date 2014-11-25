package client.impl;

import lib.client.IClient;
import lib.logger.LoggerInstance;
import lib.module.IModuleManager;
import lib.network.message.handler.INetworkHandlerManager;
import lib.network.message.handler.factory.NetworkHandlerFactory;
import lib.network.message.handler.impl.DefaultNetworkHandlerManager;
import lib.queue.impl.SoCePriorityQueue;
import lib.task.IModuleTask;
import module.impl.ModuleManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Justin on 22.11.2014.
 */
public class ClientApplication implements IClient {

    protected static int buildNumber = 1;
    ModuleManagerImpl moduleManager = null;
    private Logger logger = LoggerFactory.getLogger(ClientApplication.class);
    protected DefaultNetworkHandlerManager defaultNetworkHandlerManager = null;
    protected NetworkHandlerFactory networkHandlerFactory = null;

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
    public INetworkHandlerManager getNetworkHandlerManager() {
        return null;
    }

    @Override
    public NetworkHandlerFactory getNetworkHandlerFactory() {
        return this.networkHandlerFactory;
    }

    @Override
    public void run() {
        this.defaultNetworkHandlerManager = new DefaultNetworkHandlerManager();
        this.networkHandlerFactory = new NetworkHandlerFactory(this.defaultNetworkHandlerManager);

        this.getLogger().debug("Start modules.");

        //http://blog.knoldus.com/2011/03/07/testing-rest-with-grizzly/

        //load modules
        this.moduleManager = new ModuleManagerImpl(this, ClientApplication.buildNumber);
        moduleManager.loadModules("./modules");

        //start network client
        SoCeClient client = new SoCeClient("localhost", 50999, this);

        //start client in a new thread
        Thread thread = new Thread(client);
        thread.start();

        //start modules
        moduleManager.startModules();
    }

    public void close () {
        this.moduleManager.stopModules();
        System.exit(0);
    }
}
