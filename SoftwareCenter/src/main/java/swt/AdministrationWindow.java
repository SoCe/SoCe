package swt;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * Created by Justin on 27.11.2014.
 */
public class AdministrationWindow extends ApplicationWindow {

    public AdministrationWindow (Shell shell) {
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

        composite.setSize(600, 400);
        composite.forceFocus();

        return composite;
    }

}
