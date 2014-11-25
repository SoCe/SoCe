package server.handler;

import io.netty.channel.ChannelHandlerContext;
import lib.network.message.INetworkMessage;
import lib.network.message.handler.INetworkHandler;
import lib.network.message.impl.ServerInfoRequest;

/**
 * Created by Justin on 25.11.2014.
 */
public class ServerInfoHandler implements INetworkHandler {
    @Override
    public void receive(ChannelHandlerContext ctx, INetworkMessage message) {
        ServerInfoRequest request = (ServerInfoRequest) message;
    }
}
