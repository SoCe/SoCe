package lib.ldap;

import javax.naming.NamingException;

/**
 * Created by Justin on 23.11.2014.
 */
public interface ILDAPClient {
    public void setHost (String host);
    public String getHost ();
    public void setPort (int port);
    public int getPort ();
    public void connect () throws NamingException;
    public void close ();
}
