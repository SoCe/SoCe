package server.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lib.cluster.SoCeServer;
import lib.logger.LoggerInstance;
import lib.server.IServer;
import server.handler.SoCeServerHandler;

import java.util.concurrent.Executors;

/**
 * Created by Justin on 25.11.2014.
 */
public class ServerManager implements Runnable {

    protected SoCeServer server = null;
    protected IServer serverApplication = null;

    public ServerManager (SoCeServer server, IServer serverApplication) {
        this.server = server;
        this.serverApplication = serverApplication;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new SoCeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(this.server.getPort()).sync();

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LoggerInstance.getLogger().debug("Shutdown server instance.");

            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void sendData () {
        //
    }
}
