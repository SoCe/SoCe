package module.server;

import lib.module.IModuleManager;
import lib.module.server.IServerModule;

/**
 * Created by Justin on 22.11.2014.
 */
public class InventoryServerModule implements IServerModule {
    @Override
    public String getName() {
        return "com.jukusoft.soce.modules.inventory";
    }

    @Override
    public int getBuildNumber() {
        return 0;
    }

    @Override
    public boolean isAccepted(int build) {
        return true;
    }

    @Override
    public void setModuleManager(IModuleManager moduleManager) {
        //http://www.timomeinen.de/2013/12/maven-copy-artifact-to-different-directory-than-the-local-repository/
    }

    @Override
    public boolean startModule() {
        return false;
    }

    @Override
    public boolean stopModule() {
        return false;
    }

    @Override
    public boolean isClientModule() {
        return false;
    }

    @Override
    public boolean isServerModule() {
        return false;
    }
}
