package lib.cluster;

import lib.server.hazelcast.HazelcastManager;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Justin on 27.11.2014.
 */
public class SoCeClusterManager implements Runnable {

    protected SoCeCluster cluster = null;

    public SoCeClusterManager (SoCeCluster cluster) {
        this.cluster = cluster;
    }

    public void setCluster (SoCeCluster cluster) {
        this.cluster = cluster;
    }

    public void addServer (SoCeServer server) {
        this.cluster.addServer(server);

        //add server connections
        ConcurrentMap<String,SoCeServer> connections = HazelcastManager.getClient().getMap("server-connections");
        connections.put(server.getHost() + ":" + server.getPort() + "", server);

        List<SoCeServer> serverList = HazelcastManager.getClient().getList("serverList");
        serverList.add(server);
    }

    @Override
    public void run() {
        ConcurrentMap<String,SoCeServer> connections = HazelcastManager.getClient().getMap("server-connections");
        connections.put("127.0.0.1", cluster.getServerList().get(0));

        List<SoCeServer> serverList = HazelcastManager.getClient().getList("serverList");
    }
}
