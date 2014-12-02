package administration;

import administration.impl.SoCeMenuManager;
import jface.impl.ConnectDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swt.AdministrationWindow;
import swt.ConnectWindow;

/**
 * Created by Justin on 27.11.2014.
 */
public class AdministrationMain {

    public static void main (String[] args) {
        //https://gcc.gnu.org/java/

        //JavaFX for web applications

        //start swt in new thread
        AdministrationApplication administrationApplication = new AdministrationApplication();
        Thread thread = new Thread(administrationApplication);
        thread.start();
    }

}
