package lib.network.message;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Justin on 25.11.2014.
 */
public class NetworkMessage implements INetworkMessage {

    protected ChannelHandlerContext ctx = null;

    @Override
    public String getMessageClass() {
        return this.getClass().getCanonicalName();
    }

    @Override
    public ChannelHandlerContext getChannelHandlerContext() {
        return this.ctx;
    }

    @Override
    public void setChannelHandlerContext(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
