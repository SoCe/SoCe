package module;

import lib.module.IModule;
import lib.module.IModuleManager;

/**
 * Created by Justin on 19.11.2014.
 */
public class InventoryModule implements IModule {
    @Override
    public String getName() {
        return "com.jukusoft.soce.modules.inventory";
    }

    @Override
    public int getBuildNumber() {
        return 0;
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
