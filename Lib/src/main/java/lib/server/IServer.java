package lib.server;

import lib.ldap.impl.SoCeLDAPClient;
import lib.module.IModuleManager;
import lib.network.message.handler.INetworkHandlerManager;
import lib.queue.impl.SoCePriorityQueue;
import lib.task.IModuleTask;
import org.slf4j.Logger;

/**
 * Created by Justin on 22.11.2014.
 */
public interface IServer extends Runnable {
    public SoCePriorityQueue<IModuleTask> getQueue ();
    public IModuleManager getModuleManager ();
    public Logger getLogger ();
    public SoCeLDAPClient getLDAPClient ();
    public INetworkHandlerManager getNetworkHandlerManager ();
}
