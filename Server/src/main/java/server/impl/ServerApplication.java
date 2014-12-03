package server.impl;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lib.cluster.SoCeCluster;
import lib.cluster.SoCeClusterManager;
import lib.cluster.SoCeServer;
import lib.ldap.impl.SoCeLDAPClient;
import lib.logger.LoggerInstance;
import lib.module.IModuleManager;
import lib.network.message.INetworkMessage;
import lib.network.message.handler.INetworkHandlerManager;
import lib.network.message.handler.factory.NetworkHandlerFactory;
import lib.network.message.handler.impl.DefaultNetworkHandlerManager;
import lib.network.message.impl.ServerInfoRequest;
import lib.queue.impl.SoCePriorityQueue;
import lib.server.IServer;
import lib.server.hazelcast.HazelcastManager;
import lib.task.IModuleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.handler.ServerInfoHandler;
import server.handler.SoCeServerHandler;
import server.network.event.EventQueueThreadPool;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Justin on 23.11.2014.
 */
public class ServerApplication implements IServer {

    protected static int buildNumber = 1;
    ServerModuleManagerImpl moduleManager = null;
    private Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    protected SoCeServer server = null;
    protected DefaultNetworkHandlerManager defaultNetworkHandlerManager = null;
    protected NetworkHandlerFactory networkHandlerFactory = null;

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
    public INetworkHandlerManager getNetworkHandlerManager() {
        return this.defaultNetworkHandlerManager;
    }

    @Override
    public NetworkHandlerFactory getNetworkHandlerFactory() {
        return this.networkHandlerFactory;
    }

    @Override
    public void run() {
        //connect to hazelcast cluster
        ClientConfig clientConfig = null;
        try {
            clientConfig = new XmlClientConfigBuilder("./hazelcast.xml").build();
        } catch (IOException e) {
            LoggerInstance.getLogger().error("hazelcast io exception in class ServerApplication in method run() while loading hazelcast client configuration file " + e.getLocalizedMessage() + ".");
            e.printStackTrace();
        }
        HazelcastManager.connect(clientConfig);

        //check if the hazelcast client does exists.
        File f = new File("hazelcast.xml");

        if (!f.exists()) {
            LoggerInstance.getLogger().error("No hazelcast client configuration file was found.");
        }

        this.defaultNetworkHandlerManager = new DefaultNetworkHandlerManager();
        this.networkHandlerFactory = new NetworkHandlerFactory(this.defaultNetworkHandlerManager);

        //register ServerInfoRequest, so the server can handle this message type
        this.getNetworkHandlerManager().registerHandler(ServerInfoRequest.class.getCanonicalName(), new ServerInfoHandler());

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

        SoCeClusterManager clusterManager = new SoCeClusterManager(cluster);
        Thread clusterManagerThread = new Thread(clusterManager);
        clusterManagerThread.start();

        //get queue
        BlockingQueue<INetworkMessage> eventQueue = HazelcastManager.getClient().getQueue("server-event-queue-" + this.server.getServerID());

        //create EventQueueHandlerThreadPool
        EventQueueThreadPool eventQueueThreadPool = new EventQueueThreadPool(server, eventQueue, networkHandlerFactory);
        Thread eventQueueThread = new Thread(eventQueueThreadPool);
        eventQueueThread.start();

        //load modules
        this.moduleManager = new ServerModuleManagerImpl(this, ServerApplication.buildNumber);
        moduleManager.loadModules("./modules");

        ServerManager serverManager = new ServerManager(this.server, eventQueue, this);
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
