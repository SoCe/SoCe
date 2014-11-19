package lib.module;

/**
 * Created by Justin on 19.11.2014.
 */
public interface IModuleManager {
    public IModule getModuleByComID (String comID);
    public void startModules ();
    public void stopModules ();
    public boolean isLoaded (String comID);
    public void loadModules (String modulePath);
}
