package jface.impl;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by Justin on 27.11.2014.
 */
public class ConnectDialog extends TitleAreaDialog {

    private Text txthostName;
    private Text portText;

    private String host;
    private String port;
    private String user = "";
    private String pass = "";

    public ConnectDialog (Shell parent) {
        super(parent);
    }

    @Override
    public void create() {
        super.create();
        setTitle("Verbindungsdialog SoftwareCenter Server");
        setMessage("Eine Verbindung zum SoftwareCenter Server herstellen", IMessageProvider.INFORMATION);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout(2, false);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(layout);

        createHostText(container);
        createPortText(container);
        createUserText(container);
        createPassText(container);

        return area;
    }

    private void createHostText(Composite container) {
        Label lbtFirstName = new Label(container, SWT.NONE);
        lbtFirstName.setText("Host: ");

        GridData dataFirstName = new GridData();
        dataFirstName.grabExcessHorizontalSpace = true;
        dataFirstName.horizontalAlignment = GridData.FILL;

        txthostName = new Text(container, SWT.BORDER);
        txthostName.setLayoutData(dataFirstName);
    }

    private void createPortText(Composite container) {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("Port: ");

        GridData dataLastName = new GridData();
        dataLastName.grabExcessHorizontalSpace = true;
        dataLastName.horizontalAlignment = GridData.FILL;
        portText = new Text(container, SWT.BORDER);
        portText.setText("50999");
        portText.setLayoutData(dataLastName);
    }

    private void createUserText(Composite container) {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("User: ");

        GridData dataLastName = new GridData();
        dataLastName.grabExcessHorizontalSpace = true;
        dataLastName.horizontalAlignment = GridData.FILL;
        portText = new Text(container, SWT.BORDER);
        portText.setText("administrator");
        portText.setLayoutData(dataLastName);
    }

    private void createPassText(Composite container) {
        Label lbtLastName = new Label(container, SWT.NONE);
        lbtLastName.setText("Password: ");

        GridData dataLastName = new GridData();
        dataLastName.grabExcessHorizontalSpace = true;
        dataLastName.horizontalAlignment = GridData.FILL;
        portText = new Text(container, SWT.BORDER);
        portText.setText("");
        portText.setLayoutData(dataLastName);
    }



    @Override
    protected boolean isResizable() {
        return true;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    // save content of the Text fields because they get disposed
    // as soon as the Dialog closes
    private void saveInput() {
        host = txthostName.getText();
        port = portText.getText();

    }

    @Override
    protected void okPressed() {
        saveInput();
        super.okPressed();
    }

}
