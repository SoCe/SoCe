package lib.network.message.handler.impl;

import io.netty.channel.ChannelHandlerContext;
import lib.network.message.INetworkMessage;
import lib.network.message.handler.INetworkHandler;

/**
 * Created by Justin on 25.11.2014.
 */
public class DefaultNetworkHandler implements INetworkHandler {
    @Override
    public void receive(ChannelHandlerContext ctx, INetworkMessage message) {
        //
    }

    @Override
    public INetworkHandler clone() throws CloneNotSupportedException {
        return (INetworkHandler) super.clone();
    }
}
