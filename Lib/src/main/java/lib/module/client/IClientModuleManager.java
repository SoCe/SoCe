package lib.module.client;

import lib.client.IClient;
import lib.module.IModuleManager;

/**
 * Created by Justin on 23.11.2014.
 */
public interface IClientModuleManager extends IModuleManager {
    public IClient getClientApplication ();
}
