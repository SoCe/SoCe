package lib.module.impl;

import lib.module.IModule;
import lib.module.IModuleLoader;

import java.util.List;

/**
 * Created by Justin on 19.11.2014.
 */
public class DefaultModuleLoader implements IModuleLoader {
    @Override
    public void loadModules() {
        //http://jaxenter.com/tips-for-writing-pluggable-java-ee-applications-105281.html
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
    public List<IModule> getModules() {
        return null;
    }
}
