package swt;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * Created by Justin on 27.11.2014.
 */
public class ConnectWindow extends ApplicationWindow {

    public ConnectWindow () {
        super(null);

        // Don't return from open() until window closes
        setBlockOnOpen(true);

        // Open the main window
        open();

        // Dispose the display
        Display.getCurrent().dispose();
    }

    protected Control createContents(Composite parent) {
        // Create a Hello, World label
        Label label = new Label(parent, SWT.CENTER);
        label.setText("www.java2s.com");
        return label;
    }

}
