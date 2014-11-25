package lib.client;

import lib.module.IModuleManager;
import lib.network.message.handler.INetworkHandlerManager;
import lib.queue.impl.SoCePriorityQueue;
import lib.task.IModuleTask;
import org.slf4j.Logger;

/**
 * Created by Justin on 22.11.2014.
 */
public interface IClient extends Runnable {
    public SoCePriorityQueue<IModuleTask> getQueue ();
    public IModuleManager getModuleManager ();
    public Logger getLogger ();
    public INetworkHandlerManager getNetworkHandlerManager ();
}
