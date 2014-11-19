package lib.tftp.exception;

/**
 * Created by Justin on 19.11.2014.
 */
public class TFTPFileAlreadyExistsException extends Exception {

    public TFTPFileAlreadyExistsException (String message) {
        super(message);
    }

}
