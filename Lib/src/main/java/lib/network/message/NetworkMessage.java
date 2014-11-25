package lib.network.message;

/**
 * Created by Justin on 25.11.2014.
 */
public class NetworkMessage implements INetworkMessage {
    @Override
    public String getMessageClass() {
        return this.getClass().getCanonicalName();
    }
}
