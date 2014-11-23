package lib.ldap.impl;

import lib.ldap.ILDAPClient;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * Created by Justin on 23.11.2014.
 */
public class SoCeLDAPClient implements ILDAPClient {

    protected Hashtable env = new Hashtable();
    protected DirContext dirContext = null;
    protected String host = "";
    protected int port = 389;
    protected String username = "";
    protected String password = "";

    @Override
    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String getHost() {
        return this.host;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void connect() throws NamingException {
        //http://www.stefan-seelmann.de/media/presentations/JUGM2008_JavaUndLDAP.pdf
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://" + this.host + ":" + this.port + "");

        //Authentification
        env.put(Context.SECURITY_AUTHENTICATION,"simple");
        env.put(Context.SECURITY_PRINCIPAL,"cn=" + this.username);
        env.put(Context.SECURITY_CREDENTIALS,"" + this.password + "");

        DirContext ctx = new InitialDirContext(env);
    }

    @Override
    public void close() {
        //
    }
}
