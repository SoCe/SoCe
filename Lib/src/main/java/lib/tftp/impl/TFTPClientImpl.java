package lib.tftp.impl;

import lib.logger.LoggerInstance;
import lib.tftp.SoCeTFTPClient;
import lib.tftp.listener.TFTPConnectionLostListener;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Justin on 19.11.2014.
 */
public class TFTPClientImpl implements SoCeTFTPClient {

    protected TFTPClient tftpClient = null;
    protected int transferMode = TFTP.BINARY_MODE;
    protected TFTPConnectionLostListener tftpConnectionLostListener = null;

    @Override
    public void setTransferMode(int transferMode) {
        this.transferMode = transferMode;
    }

    @Override
    public void connect(String host, int port) throws UnknownHostException {
        this.tftpClient = new TFTPClient();

        LoggerInstance.getLogger().debug("Connecting to tftp server " + host + ":" + port + "...");

        this.tftpClient.setMaxTimeouts(60000);
        this.tftpClient.setDefaultTimeout(60000);

        try {
            this.tftpClient.open(port, InetAddress.getByName(host));
        } catch (SocketException e) {
            LoggerInstance.getLogger().error("TFTPClientImpl: SocketException in method connect(), could not open local udp port " + this.tftpClient.getLocalAddress() + ":" + this.tftpClient.getLocalPort() + " " + e.getLocalizedMessage());
        } finally {
            LoggerInstance.getLogger().debug("TFTP connection to tftp server " + host + ":" + port + ".");
        }
    }

    @Override
    public void close() {
        this.tftpClient.close();
        LoggerInstance.getLogger().debug("TFTP connection closed.");
    }

    @Override
    public void downloadFile(String localFile, String remoteFile) {
        if (this.tftpClient != null && this.tftpClient.isOpen()) {
            FileOutputStream output = null;
            File file = null;

            file = new File(localFile);
        } else {
            if (this.tftpConnectionLostListener != null) {
                this.tftpConnectionLostListener.onTFTPConnectionLost();
            }
        }
    }

    @Override
    public void setTFTPConnectionLostListener(TFTPConnectionLostListener tftpConnectionLostListener) {
        this.tftpConnectionLostListener = tftpConnectionLostListener;
    }
}
