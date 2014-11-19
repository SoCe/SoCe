package lib.tftp.listener;

import java.io.File;

/**
 * Created by Justin on 19.11.2014.
 */
public interface FileReceivedListener {
    public void onReceive (String filename, File file);
}
