package client.impl;

import lib.client.IClient;
import lib.logger.LoggerInstance;
import lib.module.IModuleManager;
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
    public void run() {
        this.getLogger().debug("Start modules.");

        //http://blog.knoldus.com/2011/03/07/testing-rest-with-grizzly/

        //load modules
        this.moduleManager = new ModuleManagerImpl(this, ClientApplication.buildNumber);
        moduleManager.loadModules("./modules");

        //start modules
        moduleManager.startModules();
    }

    public void close () {
        this.moduleManager.stopModules();
        System.exit(0);
    }
}
