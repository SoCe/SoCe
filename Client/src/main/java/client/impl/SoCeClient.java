package client.impl;

import client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lib.client.IClient;
import lib.logger.LoggerInstance;

/**
 * Created by Justin on 25.11.2014.
 */
public class SoCeClient implements Runnable {

    protected String host = "";
    protected int port = 50999;
    protected IClient client = null;

    public SoCeClient (String host, int port, IClient client) {
        this.host = host;
        this.port = port;
        this.client = client;
    }

    @Override
    public void run() {
        LoggerInstance.getLogger().debug("start network client to " + this.host + ":" + this.port + ".");
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.softCachingResolver(ClassLoader.getSystemClassLoader())),
                            new ObjectEncoder(),
                            new ClientHandler(SoCeClient.this.client));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(this.host, this.port).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
