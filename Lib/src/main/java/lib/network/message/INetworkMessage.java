package lib.network.message;

import java.io.Serializable;

/**
 * Created by Justin on 25.11.2014.
 */
public interface INetworkMessage extends Serializable {
    public String getMessageClass ();
}
