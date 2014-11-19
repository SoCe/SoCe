package lib.module;

import java.util.List;

/**
 * Created by Justin on 18.11.2014.
 */
public interface IModuleLoader {
    public void loadModules ();
    public void startModules ();
    public void stopModules ();
    public List<IModule> getModules();
}
