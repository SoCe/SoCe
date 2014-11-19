package lib.module;

/**
 * Created by Justin on 19.11.2014.
 */
public interface IModule {
    public String getName ();
    public int getBuildNumber ();
    public void setModuleManager ();
    public void startModule ();
    public void stopModule ();
}
