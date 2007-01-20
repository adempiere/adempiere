/*
 * Generated file - Do not edit!
 */
package org.compiere.interfaces;

import java.lang.*;
import java.net.URL;
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import org.compiere.util.CLogger;
import org.compiere.utils.DigestOfFile;

/**
 * Home interface for compiere/MD5. Lookup using {1}
 * @xdoclet-generated at Feb 27, 2006 1:18:49 PM
 */
public interface MD5Home
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/compiere/MD5";
   public static final String JNDI_NAME="ejb/compiere/MD5";

   public org.compiere.interfaces.MD5 create() throws javax.ejb.CreateException, java.rmi.RemoteException;

}
