package lib.network.message.handler.impl;

import lib.network.message.INetworkMessage;
import lib.network.message.handler.INetworkHandler;
import lib.network.message.handler.INetworkHandlerManager;

import java.util.HashMap;

/**
 * Created by Justin on 25.11.2014.
 */
public class DefaultNetworkHandlerManager implements INetworkHandlerManager {

    protected HashMap<String,INetworkHandler> handlerHashMap = new HashMap<String,INetworkHandler>();

    public void registerHandler (String handlerName, INetworkHandler handler) {
        this.handlerHashMap.put(handlerName, handler);
    }

    public INetworkHandler getHandler (INetworkMessage message) {
        if (this.handlerHashMap.containsKey(message.getMessageClass())) {
            return this.handlerHashMap.get(message.getMessageClass());
        } else {
            return new DefaultNetworkHandler();
        }
    }

}
