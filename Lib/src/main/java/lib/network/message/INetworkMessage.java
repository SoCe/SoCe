package lib.network.message;

import io.netty.channel.ChannelHandlerContext;

import java.io.Serializable;

/**
 * Created by Justin on 25.11.2014.
 */
public interface INetworkMessage extends Serializable {
    public String getMessageClass ();
    public ChannelHandlerContext getChannelHandlerContext ();
    public void setChannelHandlerContext (ChannelHandlerContext ctx);
}
