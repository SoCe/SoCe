package administration.impl;

        import lib.administration.ISoCeMenuManager;
        import org.eclipse.swt.SWT;
        import org.eclipse.swt.widgets.Menu;
        import org.eclipse.swt.widgets.Shell;

        import java.util.HashMap;

/**
 * Created by Justin on 02.12.2014.
 */
public class SoCeMenuManager implements ISoCeMenuManager {

    protected HashMap<String,Menu> menuHashMap = new HashMap<String,Menu>();
    protected Shell shell = null;

    public SoCeMenuManager (Shell shell) {
        this.shell = shell;

        Menu mainMenu = new Menu(shell, SWT.BAR);
        this.menuHashMap.put("main", mainMenu);
    }

}
