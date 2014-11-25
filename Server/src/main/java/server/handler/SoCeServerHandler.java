package server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;

/**
 * Created by Justin on 25.11.2014.
 */
public class SoCeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        //final ByteBuf time = ctx.alloc().buffer(4);
        //time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        String serverInfo = "";
        try {
            final ByteBuf buffer = ctx.alloc().buffer(serverInfo.getBytes("UTF-8").length);
            buffer.writeBytes(serverInfo.getBytes("UTF-8"));

            /*
            *  A ChannelFuture represents an I/O operation which has not yet occurred.
            *  It means, any requested operation might not have been performed yet
            *  because all operations are asynchronous in Netty.
            */

            final ChannelFuture f = ctx.writeAndFlush(buffer);
            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    //assert f == future;
                    //ctx.close();
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // Do something with msg
            ((ByteBuf) msg).release();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
