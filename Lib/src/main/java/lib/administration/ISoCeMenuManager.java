package lib.administration;

import org.eclipse.swt.widgets.Menu;

/**
 * Created by Justin on 02.12.2014.
 */
public interface ISoCeMenuManager {
    public void setMenu (String menuName);
    public void putMenu (String menuName, Menu menu);
}
