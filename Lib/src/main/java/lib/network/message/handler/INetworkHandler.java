package lib.network.message.handler;

import io.netty.channel.ChannelHandlerContext;
import lib.network.message.INetworkMessage;

/**
 * Created by Justin on 25.11.2014.
 */
public interface INetworkHandler {
    public void receive (ChannelHandlerContext ctx, INetworkMessage message);
}
