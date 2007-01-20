/*
 * Créé le 22 mars 2005
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package org.compiere.web;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.interfaces.MD5;
import org.compiere.interfaces.MD5Home;


/**
 * Servlet Class
 *
 * @web.servlet              name="GetMD5File"
 *                           display-name="Name for GetMD5File"
 *                           description="Description for GetMD5File"
 * @web.servlet-mapping      url-pattern="/GetMD5File"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 * @web.ejb-ref 		     name="ejb/compiere/MD5"
 * 						     type="Session"
 * 						     home = "org.compiere.interfaces.MD5Home"
 * 							 remote = "org.compiere.interfaces.MD5"
 * 							 
 * @jboss.ejb-ref-jndi       ref-name="ejb/compiere/MD5"
 * 							 jndi-name = "ejb/compiere/MD5"
 */
public class GetMD5FileServlet extends HttpServlet {

	/**
	 * 
	 */
	private MD5Home home;
	
	public GetMD5FileServlet() {
		super();
		// TODO Raccord de constructeur auto-généré		
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try
		{
			Context context = new InitialContext();
			Object ref = context.lookup("java:/comp/env/ejb/compiere/MD5");
			home = (MD5Home)javax.rmi.PortableRemoteObject.narrow(ref,MD5Home.class);
		}
		catch(Exception e)
		{
			throw new ServletException("Error getting EJB: java:/comp/env/ejb/compiere/MD5");
		}	
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,
		IOException {
		// TODO Auto-generated method stub
		MD5 md5=null;
		String file = req.getParameter("File");
		PrintWriter out = resp.getWriter();
		out.println("<HTML><HEAD><TITLE>MD5 Hash</TITLE></HEAD><BODY>");
		try
		{
			md5 = home.create();	
		}
		catch(javax.ejb.CreateException e)
		{
			out.println("<H1>Error javax.ejb.CreateException home.create();</H1>");
			out.println("</BODY></HTML>");
			throw new ServletException("Error CreateException");
		}
		out.println("File is: "+ file + "<BR>MD5 : "+ md5.getFileMD5(file)+"<BR>");
		//out.println(md5.getFileAsolutePath(file));
		out.println("</BODY></HTML>");
	}

}