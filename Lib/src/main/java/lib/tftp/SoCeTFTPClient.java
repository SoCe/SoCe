package lib.tftp;

import lib.tftp.exception.TFTPFileAlreadyExistsException;
import lib.tftp.listener.TFTPConnectionLostListener;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 * Created by Justin on 19.11.2014.
 */
public interface SoCeTFTPClient {
    public void setTransferMode (int transferMode);
    public void connect (String host, int port) throws UnknownHostException;
    public void close ();
    public void downloadFile (String localFile, String remoteFile, boolean overwriteFileIfExists) throws TFTPFileAlreadyExistsException, IOException;
    public void setTFTPConnectionLostListener (TFTPConnectionLostListener tftpConnectionLostListener);
}
