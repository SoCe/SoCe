package server.impl;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lib.cluster.SoCeCluster;
import lib.cluster.SoCeServer;
import lib.ldap.impl.SoCeLDAPClient;
import lib.module.IModuleManager;
import lib.queue.impl.SoCePriorityQueue;
import lib.server.IServer;
import lib.task.IModuleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.handler.SoCeServerHandler;

/**
 * Created by Justin on 23.11.2014.
 */
public class ServerApplication implements IServer {

    protected static int buildNumber = 1;
    ServerModuleManagerImpl moduleManager = null;
    private Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    protected SoCeServer server = null;

    @Override
    public SoCePriorityQueue<IModuleTask> getQueue() {
        return null;
    }

    @Override
    public IModuleManager getModuleManager() {
        return this.moduleManager;
    }

    @Override
    public Logger getLogger() {
        return this.logger;
    }

    @Override
    public SoCeLDAPClient getLDAPClient() {
        return null;
    }

    @Override
    public void run() {
        this.getLogger().debug("Start modules.");

        //http://blog.knoldus.com/2011/03/07/testing-rest-with-grizzly/

        //Cluster Configuration
        SoCeCluster cluster = new SoCeCluster();

        //build up cluster
        SoCeServer server = new SoCeServer();
        server.setServerID(1);
        server.setServerName("Main Server #1");
        server.setHost("localhost");
        server.setPort(50999);
        this.server = server;

        cluster.addServer(server);

        //load modules
        this.moduleManager = new ServerModuleManagerImpl(this, ServerApplication.buildNumber);
        moduleManager.loadModules("./modules");

        ServerManager serverManager = new ServerManager(this.server, this);
        Thread thread = new Thread(serverManager);
        thread.start();

        //start modules
        moduleManager.startModules();
    }

    public void close () {
        this.moduleManager.stopModules();
        System.exit(0);
    }
}
