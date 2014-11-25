package lib.network.message.handler.factory;

import lib.network.message.INetworkMessage;
import lib.network.message.handler.INetworkHandler;
import lib.network.message.handler.INetworkHandlerManager;
import lib.network.message.handler.impl.DefaultNetworkHandler;

/**
 * Created by Justin on 25.11.2014.
 */
public class NetworkHandlerFactory {

    protected INetworkHandlerManager networkHandlerManager = null;

    public NetworkHandlerFactory (INetworkHandlerManager networkHandlerManager) {
        this.networkHandlerManager = networkHandlerManager;
    }

    public INetworkHandler buildNetworkHandler (INetworkMessage message) {
        INetworkHandler handlerPrototyp = this.networkHandlerManager.getHandler(message);

        //http://howtodoinjava.com/2013/01/04/prototype-design-pattern-in-java/

        try {
            return (INetworkHandler) handlerPrototyp.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return new DefaultNetworkHandler();
        }
    }
}
