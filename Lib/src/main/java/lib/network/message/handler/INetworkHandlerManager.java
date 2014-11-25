package lib.network.message.handler;

import lib.network.message.INetworkMessage;

/**
 * Created by Justin on 25.11.2014.
 */
public interface INetworkHandlerManager {
    public void registerHandler (String handlerName, INetworkHandler handler);
    public INetworkHandler getHandler (INetworkMessage message);
}
