package lib.module.administration;

import lib.administration.ISoCeMenuManager;
import lib.module.IModuleManager;

/**
 * Created by Justin on 02.12.2014.
 */
public interface IAdministrationModuleManager extends IModuleManager {
    public ISoCeMenuManager getMenuManager ();
}
