package lib.network.message.handler;

import lib.network.message.INetworkMessage;

/**
 * Created by Justin on 25.11.2014.
 */
public interface INetworkHandler {
    public void receive (INetworkMessage message);
}
