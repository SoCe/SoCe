package lib.module.server;

import lib.module.IModuleManager;
import lib.server.IServer;

/**
 * Created by Justin on 23.11.2014.
 */
public interface IServerModuleManager extends IModuleManager {
    public IServer getServerApplication ();
}
