package server.handler.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lib.logger.LoggerInstance;
import lib.network.message.NetworkMessage;

import java.io.UnsupportedEncodingException;

/**
 * Created by Justin on 03.12.2014.
 */
public class SoCeServerObjectHandler extends SimpleChannelInboundHandler {
    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //cast to NetworkMessage
        NetworkMessage message = (NetworkMessage) o;

        //http://www.rune-server.org/programming/application-development/tutorials/485607-read-write-object-netty-implementation.html

        LoggerInstance.getLogger().debug("Message received.");
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        //
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        LoggerInstance.getLogger().error(cause.getLocalizedMessage());
        cause.printStackTrace();
        ctx.close();
    }
}