package swt;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * Created by Justin on 27.11.2014.
 */
public class ConnectWindow extends ApplicationWindow {

    public ConnectWindow (Shell shell) {
        super(shell);

        this.setStatus("Open");

        // Don't return from open() until window closes
        setBlockOnOpen(true);

        // Open the main window
        open();

        // Dispose the display
        Display.getCurrent().dispose();
    }

    @Override
    protected Control createContents(Composite parent) {
        Composite composite = parent;

        // Create a Hello, World label
        Label label = new Label(composite, SWT.CENTER);
        label.setText("www.java2s.com");

        Label hostLabel = new Label(composite, SWT.CENTER);
        hostLabel.setText("Server IP: ");
        hostLabel.setLocation(200, 20);

        Label portLabel = new Label(composite, SWT.CENTER);
        portLabel.setText("Port: ");
        portLabel.setLocation(200, 60);

        Label userLabel = new Label(composite, SWT.CENTER);
        userLabel.setText("User: ");
        userLabel.setLocation(200, 100);

        Label passLabel = new Label(composite, SWT.CENTER);
        userLabel.setText("Password: ");
        userLabel.setLocation(200, 140);

        composite.setSize(600, 400);
        composite.forceFocus();

        return composite;
    }

}
