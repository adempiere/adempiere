/*
 * Generated file - Do not edit!
 */
package org.compiere.interfaces;

/**
 * Remote interface for compiere/MD5.
 * @xdoclet-generated at Feb 27, 2006 1:18:49 PM
 */
public interface MD5
   extends javax.ejb.EJBObject
{
   /**
    * Business method
    * @param Filename
    * @return AbsolutePath on server    */
   public java.lang.String getFileAsolutePath( java.lang.String Filename ) throws java.rmi.RemoteException;

   /**
    * Business method
    * @param FileName
    * @return hash base64 encoded    */
   public java.lang.String getFileMD5( java.lang.String FileName ) throws java.rmi.RemoteException;

}
