/*
 * Créé le 22 mars 2005
 *
 * TODO Pour changer le modčle de ce fichier généré, allez ŕ :
 * Fenętre - Préférences - Java - Style de code - Modčles de code
 */
package org.compiere.ejb;

import java.net.URL;

import javax.ejb.Stateless;

import org.compiere.interfaces.MD5;
import org.compiere.util.CLogger;
import org.compiere.utils.DigestOfFile;

/**
 *  @ejb:ejb-ref ejb-name="compiere/MD5"
 *              ref-name="compiere/MD5"
 */
@Stateless(mappedName=MD5.JNDI_NAME, name=MD5.EJB_NAME)
public class MD5Bean implements MD5 {
	/**
	 * 
	 */
	private CLogger log = null;
	
	public MD5Bean() {
		super();
		log = CLogger.getCLogger(MD5Bean.class);
	}

	/**
	 * Business method
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
	 * @param Filename
	 * @return AbsolutePath on server
	 */
	public String getFileAsolutePath(String Filename)
	{
		java.net.URL currentFile = Thread.currentThread().getContextClassLoader().getResource(Filename);
		return currentFile.toString();
	}
}