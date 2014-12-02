package administration;

import administration.impl.AdministrationModuleManagerImpl;
import administration.impl.SoCeMenuManager;
import jface.impl.ConnectDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swt.AdministrationWindow;

/**
 * Created by Justin on 02.12.2014.
 */
public class AdministrationApplication implements Runnable {

    protected AdministrationModuleManagerImpl moduleManager = null;
    protected SoCeMenuManager menuManager = null;
    protected Shell shell = null;
    protected static int buildNumber = 1;
    protected Display display = null;

    public AdministrationApplication () {
        //
    }

    @Override
    public void run() {
        this.display = new Display();
        this.shell = new Shell(display);

        //create menuManager for modules
        this.menuManager = new SoCeMenuManager(this.shell);

        //load modules
        this.moduleManager = new AdministrationModuleManagerImpl(this.menuManager, AdministrationApplication.buildNumber);
        moduleManager.loadModules("./modules");

        shell.setText("Application");
        shell.setSize(600, 800);
        shell.setActive();
        shell.forceActive();
        shell.setLocation(600, 600);

        shell.setLayout(new FillLayout());

        //set the window location to center.
        center(shell);

        //http://www.vogella.com/tutorials/EclipseDialogs/article.html

        ConnectDialog dialog = new ConnectDialog(shell);
        dialog.create();
        if (dialog.open() == Window.OK) {
            System.out.println(dialog.getHost());
            System.out.println(dialog.getPort());
        }

        shell.setText("SoftwareCenter Management Tools");

        //create SoCeMenuManager for modules
        SoCeMenuManager menuManager = new SoCeMenuManager(shell);

        //create main application window
        AdministrationWindow administrationWindow = new AdministrationWindow(shell, menuManager);

        //main event loop
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        /*ConnectWindow connectWindow = new ConnectWindow(shell);

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }*/

        display.dispose();
    }

    public static void center(Shell shell) {

        Rectangle bds = shell.getDisplay().getBounds();

        Point p = shell.getSize();

        int nLeft = (bds.width - p.x) / 2;
        int nTop = (bds.height - p.y) / 2;

        shell.setBounds(nLeft, nTop, p.x, p.y);
    }

    public void close () {
        this.moduleManager.stopModules();
        System.exit(0);
    }
}
