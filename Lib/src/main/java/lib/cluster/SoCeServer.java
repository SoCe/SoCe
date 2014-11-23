package lib.cluster;

/**
 * Created by Justin on 23.11.2014.
 */
public class SoCeServer {

    protected int serverID = -1;
    protected String serverName = "";

    public SoCeServer () {
        //
    }

    public String getServerName () {
        return this.serverName;
    }

    public void setServerName (String serverName) {
        this.serverName = serverName;
    }

    public int getServerID () {
        return this.serverID;
    }

    public void setServerID (int serverID) {
        this.serverID = serverID;
    }

}
