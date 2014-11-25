package lib.network.message.impl;

import lib.network.message.NetworkMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 25.11.2014.
 */
public class ServerInfoMessage extends NetworkMessage {

    protected int buildNumber = 1;
    protected String version = "1.0.0";
    protected List<String> moduleList = new ArrayList<String>();

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }

    public List<String> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<String> moduleList) {
        this.moduleList = moduleList;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
