package lib.cluster;

/**
 * Created by Justin on 27.11.2014.
 */
public class SoCeClusterManager {

    protected SoCeCluster cluster = null;

    public SoCeClusterManager (SoCeCluster cluster) {
        this.cluster = cluster;
    }

    public void setCluster (SoCeCluster cluster) {
        this.cluster = cluster;
    }

}
