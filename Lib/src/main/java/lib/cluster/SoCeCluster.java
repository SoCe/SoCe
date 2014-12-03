package lib.cluster;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 23.11.2014.
 */
public class SoCeCluster implements Serializable {

    protected List<SoCeServer> serverList = new ArrayList<SoCeServer>();
    protected String clusterName = "";
    protected int clusterID = 1;

    public SoCeCluster () {
        //
    }

    public void addServer (SoCeServer server) {
        this.serverList.add(server);
    }

    public void removeServer (SoCeServer server) {
        this.serverList.remove(server);
    }

    public List<SoCeServer> getServerList () {
        return this.serverList;
    }

}
