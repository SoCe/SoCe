package administration;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by Justin on 27.11.2014.
 */
public class AdministrationMain {

    public static void main (String[] args) {
        //https://gcc.gnu.org/java/

        //JavaFX for web applications

        Display display = new Display();
        Shell shell = new Shell(display);

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }

}
