package lib.ldap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin on 24.11.2014.
 */
public class LDAPNode {

    protected LDAPNode owner = null;
    protected String nodeName = "";
    protected List<LDAPNode> nodeList = new ArrayList<LDAPNode>();

    public LDAPNode () {
        //
    }

    public LDAPNode (LDAPNode owner, String nodeName) {
        this.owner = owner;
        this.nodeName = nodeName;
    }

    public LDAPNode getOwner () {
        //https://docs.oracle.com/javase/tutorial/jndi/ldap/jndi.html

        return this.owner;
    }

    public String getFullyNamespace () {
        if (this.owner != null) {
            return this.owner.getFullyNamespace() + "," + this.getNamespace();
        } else {
            return this.getNamespace();
        }
    }

    public String getNamespace () {
        return "dc=" + this.nodeName;
    }

}
