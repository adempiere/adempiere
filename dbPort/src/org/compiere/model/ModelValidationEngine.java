/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.beans.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Model Validation Engine
 *	
 *  @author Jorg Janke
 *  @version $Id: ModelValidationEngine.java,v 1.2 2006/07/30 00:58:38 jjanke Exp $
 */
public class ModelValidationEngine 
{
	/**
	 * 	Get Singleton
	 *	@return engine
	 */
	public static ModelValidationEngine get()
	{
		if (s_engine == null)
			s_engine = new ModelValidationEngine();
		return s_engine;
	}	//	get
	
	/** Engine Singleton				*/
	private static ModelValidationEngine s_engine = null; 
	
	
	/**************************************************************************
	 * 	Constructor.
	 * 	Creates Model Validators
	 */
	private ModelValidationEngine ()
	{
		super ();
		//	Go through all Clients and start Validators 
		MClient[] clients = MClient.getAll(new Properties());
		for (int i = 0; i < clients.length; i++) 
		{
			String classNames = clients[i].getModelValidationClasses();
			if (classNames == null || classNames.length() == 0)
				continue;
			StringTokenizer st = new StringTokenizer(classNames, ";");
			while (st.hasMoreTokens())
			{
				String className = null;			
				try
				{
					className = st.nextToken();
					if (className == null)
						continue;
					className = className.trim();
					if (className.length() == 0)
						continue;
					//
					Class clazz = Class.forName(className);
					ModelValidator validator = (ModelValidator)clazz.newInstance();
					initialize(validator, clients[i]);					
				}
				catch (Exception e)
				{
					log.log(Level.SEVERE, className + ": " + e.getMessage());
				}
			}
		}
		log.config(toString());
	}	//	ModelValidatorEngine
	
	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger(ModelValidationEngine.class);
	/** Change Support			*/
	private VetoableChangeSupport m_changeSupport = new VetoableChangeSupport(this);

	/**	Validators						*/
	private ArrayList<ModelValidator>	m_validators = new ArrayList<ModelValidator>();
	/**	Model Change Listeners			*/
	private Hashtable<String,ArrayList<ModelValidator>>	m_modelChangeListeners = new Hashtable<String,ArrayList<ModelValidator>>();
	/**	Document Validation Listeners			*/
	private Hashtable<String,ArrayList<ModelValidator>>	m_docValidateListeners = new Hashtable<String,ArrayList<ModelValidator>>();

	/**
	 * 	Initialize and add validator
	 *	@param validator
	 *	@param client
	 */
	private void initialize(ModelValidator validator, MClient client)
	{
		validator.initialize(this, client);
		m_validators.add(validator);
	}	//	initialize

	/**
	 * 	Called when login is complete
	 * 	@param AD_Client_ID client
	 *	@param AD_Org_ID org
	 *	@param AD_Role_ID role
	 *	@param AD_User_ID user
	 *	@return error message or null
	 */
	public String loginComplete (int AD_Client_ID, int AD_Org_ID, int AD_Role_ID, int AD_User_ID)
	{
		for (int i = 0; i < m_validators.size(); i++) 
		{
			ModelValidator validator = (ModelValidator)m_validators.get(i);
			if (AD_Client_ID == validator.getAD_Client_ID())
			{
				String error = validator.login(AD_Org_ID, AD_Role_ID, AD_User_ID);
				if (error != null && error.length() > 0)
					return error;
			}
		}
		return null;
	}	//	loginComplete
	
	
	/**************************************************************************
	 * 	Add Model Change Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void addModelChange (String tableName, ModelValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		//
		String propertyName = tableName + listener.getAD_Client_ID();
		ArrayList<ModelValidator> list = (ArrayList<ModelValidator>)m_modelChangeListeners.get(propertyName);
		if (list == null)
		{
			list = new ArrayList<ModelValidator>();
			list.add(listener);
			m_modelChangeListeners.put(propertyName, list);
		}
		else
			list.add(listener);
	}	//	addModelValidator

	/**
	 * 	Remove Model Change Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void removeModelChange (String tableName, ModelValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		String propertyName = tableName + listener.getAD_Client_ID();
		ArrayList list = (ArrayList)m_modelChangeListeners.get(propertyName);
		if (list == null)
			return;
		list.remove(listener);
		if (list.size() == 0)
			m_modelChangeListeners.remove(propertyName);
	}	//	removeModelValidator
	
	/**
	 * 	Fire Model Change.
	 * 	Call modelChange method of added validators
	 *	@param po persistent objects
	 *	@param type ModelValidator.TYPE_*
	 *	@return error message or NULL for no veto
	 */
	public String fireModelChange (PO po, int type)
	{
		if (po == null || m_modelChangeListeners.size() == 0)
			return null;
		//
		String propertyName = po.get_TableName() + po.getAD_Client_ID();
		ArrayList list = (ArrayList)m_modelChangeListeners.get(propertyName);
		if (list == null)
			return null;
		
		//
		for (int i = 0; i < list.size(); i++)
		{
			try
			{
				ModelValidator validator = (ModelValidator)list.get(i);
				String error = validator.modelChange(po, type);
				if (error != null && error.length() > 0)
					return error;
			}
			catch (Exception e)
			{
				String error = e.getMessage();
				if (error == null)
					error = e.toString();
				return error;
			}
		}
		return null;
	}	//	fireModelChange
	
	
	/**************************************************************************
	 * 	Add Document Validation Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void addDocValidate (String tableName, ModelValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		//
		String propertyName = tableName + listener.getAD_Client_ID();
		ArrayList<ModelValidator> list = (ArrayList<ModelValidator>)m_docValidateListeners.get(propertyName);
		if (list == null)
		{
			list = new ArrayList<ModelValidator>();
			list.add(listener);
			m_docValidateListeners.put(propertyName, list);
		}
		else
			list.add(listener);
	}	//	addDocValidate

	/**
	 * 	Remove Document Validation Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void removeDocValidate (String tableName, ModelValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		String propertyName = tableName + listener.getAD_Client_ID();
		ArrayList list = (ArrayList)m_docValidateListeners.get(propertyName);
		if (list == null)
			return;
		list.remove(listener);
		if (list.size() == 0)
			m_docValidateListeners.remove(propertyName);
	}	//	removeModelValidator
	
	/**
	 * 	Fire Document Validation.
	 * 	Call docValidate method of added validators
	 *	@param po persistent objects
	 *	@param timing see ModelValidator.TIMING_ constants
     *	@return error message or null
	 */
	public String fireDocValidate (PO po, int timing)
	{
		if (po == null || m_docValidateListeners.size() == 0)
			return null;
		//
		String propertyName = po.get_TableName() + po.getAD_Client_ID();
		ArrayList list = (ArrayList)m_docValidateListeners.get(propertyName);
		if (list == null)
			return null;
		
		//
		for (int i = 0; i < list.size(); i++)
		{
			ModelValidator validator = null;
			try
			{
				validator = (ModelValidator)list.get(i);
				String error = validator.docValidate(po, timing);
				if (error != null && error.length() > 0)
					return error;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, validator.toString(), e);
			}
		}
		return null;
	}	//	fireModelChange
	
	/**
	* 	String Representation
	*	@return info
	*/
	public String toString()
	{
		StringBuffer sb = new StringBuffer("ModelValidationEngine[");
		sb.append("Validators=#").append(m_validators.size())
			.append(", ModelChange=#").append(m_modelChangeListeners.size())
			.append(", DocValidate=#").append(m_docValidateListeners.size())
			.append("]");
		return sb.toString();
	}	//	toString
}	//	ModelValidatorEngine
