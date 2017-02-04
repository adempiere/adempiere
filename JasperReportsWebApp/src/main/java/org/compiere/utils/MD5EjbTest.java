/*
 * Créé le 26 mars 2005
 *
 * TODO Pour changer le modčle de ce fichier généré, allez ŕ :
 * Fenętre - Préférences - Java - Style de code - Modčles de code
 */
package org.compiere.utils;

import java.net.InetAddress;
import java.util.Hashtable;

import javax.naming.InitialContext;

import org.compiere.interfaces.MD5;
/**
 * @author rlemeill
 */

public class MD5EjbTest {
	
	/**************************************************************************
	 * 	constructor
	 *  @param serverName 
	 *  @param context 
	 *  @param Filename
	 */
	public MD5EjbTest (String serverName,String Filename)
	{
		Hashtable env = new Hashtable();
		env.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		env.put(InitialContext.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
		env.put(InitialContext.PROVIDER_URL, serverName);
		System.out.println ("Creating context ...");
		System.out.println ("  " + env);
		InitialContext context = null;
		try
		{
			context = new InitialContext(env);
		}
		catch (Exception e)
		{
			System.err.println("ERROR: Could not create context: " + e);
			return;
		}		
		testEjb(serverName,context,Filename);
	}
	
	
	/**************************************************************************
	 * 	testing entry point
	 *  @param serverName 
	 *  @param context 
	 *  @param Filename
	 */
	public static void testEjb(String serverName, InitialContext context, String Filename)
	{
		try{
			System.out.println("MD5 Creation at: "+MD5.JNDI_NAME);
			MD5 md5 = (MD5) context.lookup(MD5.JNDI_NAME);
			System.out.println("Creation OK");
			System.out.println("MD5 for " + Filename + " is " +md5.getFileMD5(Filename));
		}
		catch(Exception e)
		{
			
		}
	}
	
	/**************************************************************************
	 * 	Start Method
	 *  @param args serverName Filename
	 */
	public static void main(String[] args)
	{
		String serverName = null;
		String Filename = null;
		if (args.length > 0)
			serverName = args[0];
		if (args.length > 1)
			Filename = args[1];
		if (serverName == null || serverName.length() == 0)
		{
			try
			{
				serverName = InetAddress.getLocalHost().getHostName();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		MD5EjbTest myMD5EjbTest = new MD5EjbTest(serverName,Filename);

	}
}
