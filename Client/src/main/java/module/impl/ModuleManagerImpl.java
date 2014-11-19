package module.impl;

import lib.logger.LoggerInstance;
import lib.module.IModule;
import lib.module.IModuleManager;
import lib.module.impl.DefaultModuleLoader;

import java.util.HashMap;

/**
 * Created by Justin on 19.11.2014.
 */
public class ModuleManagerImpl implements IModuleManager {

    protected HashMap<String,IModule> modules = new HashMap<String,IModule>();
    protected HashMap<String,Boolean> isModuleLoadedList = new HashMap<String,Boolean>();
    protected int buildNumber = 0;

    public ModuleManagerImpl (int buildNumber) {
        this.buildNumber = buildNumber;
    }

    @Override
    public IModule getModuleByComID(String comID) {
        if (this.modules.containsKey(comID)) {
            return this.modules.get(comID);
        } else {
            return null;
        }
    }

    @Override
    public void startModules() {
        //
    }

    @Override
    public void stopModules() {
        //
    }

    @Override
    public boolean isLoaded(String comID) {
        if (this.isModuleLoadedList.containsKey(comID) && this.isModuleLoadedList.get(comID)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void loadModules(String modulePath) {
        DefaultModuleLoader defaultModuleLoader = new DefaultModuleLoader(modulePath);
        defaultModuleLoader.loadModules();

        for (IModule module : defaultModuleLoader.getModules()) {
            //check if the module is compatible with the current build number of the application
            if (module.isAccepted(this.buildNumber)) {
                //add module to moduleList
                this.modules.put(module.getName(), module);

                //set module manager
                module.setModuleManager(this);

                //start module
                if (module.startModule()) {
                    this.isModuleLoadedList.put(module.getName(), true);
                    LoggerInstance.getLogger().debug("start module " + module.getName() + ".");
                } else {
                    this.isModuleLoadedList.put(module.getName(), false);
                }
            } else {
                LoggerInstance.getLogger().debug("ModuleManagerImpl: module " + module.getName() + " BuildNumber " + module.getBuildNumber() + " is not compatible with the application buildNumber " + this.buildNumber + ".");
            }
        }
    }
}
