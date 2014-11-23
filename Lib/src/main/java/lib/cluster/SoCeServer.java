package lib.cluster;

/**
 * Created by Justin on 23.11.2014.
 */
public class SoCeServer {

    protected int serverID = -1;
    protected String serverName = "";
    protected String host = "";
    protected int port = 0;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

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
