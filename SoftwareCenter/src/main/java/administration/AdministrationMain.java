package administration;

import jface.impl.ConnectDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
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
        shell.setSize(200, 400);
        shell.setActive();
        shell.forceActive();
        shell.setLocation(600, 600);

        shell.setLayout(new FillLayout());

        //http://www.vogella.com/tutorials/EclipseDialogs/article.html

        ConnectDialog dialog = new ConnectDialog(shell);
        dialog.create();
        if (dialog.open() == Window.OK) {
            System.out.println(dialog.getHost());
            System.out.println(dialog.getPort());
        }

        ConnectWindow connectWindow = new ConnectWindow(shell);

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

}
