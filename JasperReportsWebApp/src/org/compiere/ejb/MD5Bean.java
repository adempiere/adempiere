/*
 * Créé le 22 mars 2005
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package org.compiere.ejb;

import java.net.URL;
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import org.compiere.util.CLogger;
import org.compiere.utils.DigestOfFile;

/**
 *  @ejb:bean name="compiere/MD5"
 *           display-name="Compiere Server MD5 hash computation"
 *           type="Stateless"
 *           transaction-type="Bean"
 *           jndi-name="ejb/compiere/MD5"
 *           view-type="remote"
 *
 *  @ejb:ejb-ref ejb-name="compiere/MD5"
 *              ref-name="compiere/MD5"
 */
public class MD5Bean implements SessionBean {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private CLogger log = null;
	
	public MD5Bean() {
		super();
		log = CLogger.getCLogger(MD5Bean.class);
			}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#setSessionContext(javax.ejb.SessionContext)
	 */
	public void setSessionContext(SessionContext ctx)
		throws EJBException,
		RemoteException {
		// TODO Raccord de méthode auto-généré

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Raccord de méthode auto-généré

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Raccord de méthode auto-généré

	}

	/* (non-Javadoc)
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Raccord de méthode auto-généré

	}

	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
		// TODO Auto-generated method stub
	}

	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 * @param FileName
	 * @return hash base64 encoded
	 */
	public String getFileMD5(String FileName) {
		String hash = null;
		String absoluteFilename = null;
		String filepartOnly = null;
		try
		{
			//Thread.currentThread().getContextClassLoader().getResource("");
			if ( FileName.startsWith("http://") )
			{
				//extract absolute path by requesting jboss/tomcat
				String[] filePathParts = FileName.split("/");
				filepartOnly = filePathParts[filePathParts.length-1];
				URL currentFile = Thread.currentThread().getContextClassLoader().getResource(filepartOnly);
				System.out.println(filepartOnly);
				absoluteFilename = currentFile.getFile();
				log.info("decoded absolute path name for "+filepartOnly +" is "+absoluteFilename);
			}
			else
			{
				absoluteFilename = FileName;
			}
			
			if (absoluteFilename != null)
			{
				DigestOfFile md5DigestAgent = new DigestOfFile("MD5");
				hash = md5DigestAgent.digestAsBase64(new java.io.File(absoluteFilename));	
			}
			
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
			return null;
		}
		
		return hash;
	}
	
	/**
	 * Business method
	 * @ejb.interface-method  view-type = "remote"
	 * @param Filename
	 * @return AbsolutePath on server
	 */
	public String getFileAsolutePath(String Filename)
	{
		java.net.URL currentFile = Thread.currentThread().getContextClassLoader().getResource(Filename);
		return currentFile.toString();
	}
}