package administration;

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

        Display display = new Display();
        Shell shell = new Shell(display);

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

        //create main application window
        AdministrationWindow administrationWindow = new AdministrationWindow(shell);

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

}
