package lib.module;

/**
 * Created by Justin on 19.11.2014.
 */
public interface IModule {
    public String getName ();
    public int getBuildNumber ();
    public boolean isAccepted (int build);
    public void setModuleManager (IModuleManager moduleManager);
    public boolean startModule ();
    public boolean stopModule ();
    public boolean isClientModule ();
    public boolean isServerModule ();
}
