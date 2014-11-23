package lib.cluster;

/**
 * Created by Justin on 23.11.2014.
 */
public interface SoCeServerStateUpdateListener {
    public void onUpdateServerState (SoCeCluster cluster, SoCeServer server);
}
