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

import org.compiere.acct.Fact;
import org.compiere.util.*;

/**
 *	Model Validation Engine
 *	
 *  @author Jorg Janke
 *  @version $Id: ModelValidationEngine.java,v 1.2 2006/07/30 00:58:38 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>FR [ 1670025 ] ModelValidator.afterLoadPreferences will be useful
 * 				<li>BF [ 1679692 ] fireDocValidate doesn't treat exceptions as errors
 * 				<li>FR [ 1724662 ] Support Email should contain model validators info
 */
public class ModelValidationEngine 
{

	/**
	 * 	Get Singleton
	 *	@return engine
	 */
	public synchronized static ModelValidationEngine get()
	{
		if (s_engine == null)
			s_engine = new ModelValidationEngine();
		return s_engine;
	}	//	get
	
	/** Engine Singleton				*/
	private static ModelValidationEngine s_engine = null;
	/* flag to indicate a missing model validation class */
	private static String missingModelValidationMessage = null; 
	
	
	/**************************************************************************
	 * 	Constructor.
	 * 	Creates Model Validators
	 */
	private ModelValidationEngine ()
	{
		super ();
		// Load global validators
		
		MTable table = MTable.get(new Properties(), X_AD_ModelValidator.Table_ID);
		Query query = table.createQuery("IsActive='Y'", null);
		try {
			List<PO> entityTypes = query.list();
			for (PO po : entityTypes)
			{
				X_AD_ModelValidator entityType = (X_AD_ModelValidator)po;
				String className = entityType.getModelValidationClass();
				if (className == null || className.length() == 0)
					continue;
				loadValidatorClass(null, className);
			}
		} catch (Exception e)
		{
			//logging to db will try to init ModelValidationEngine again!
			//log.warning(e.getLocalizedMessage());
			// System.err.println(e.getLocalizedMessage());
			missingModelValidationMessage = missingModelValidationMessage + e.toString() + " global" + '\n';
		}
		
		// Go through all Clients and start Validators 
		MClient[] clients = MClient.getAll(new Properties());
		for (int i = 0; i < clients.length; i++) 
		{
			String classNames = clients[i].getModelValidationClasses();
			if (classNames == null || classNames.length() == 0)
				continue;
			loadValidatorClasses(clients[i], classNames);
		}
		//logging to db will try to init ModelValidationEngine again!
		//log.config(toString());
		// System.out.println(toString());
	}	//	ModelValidatorEngine
	
	private void loadValidatorClasses(MClient client, String classNames) 
	{
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
				loadValidatorClass(client, className);					
			}
			catch (Exception e)
			{
				//logging to db will try to init ModelValidationEngine again!
				//log.log(Level.SEVERE, className + ": " + e.getMessage());
				// System.err.println(className + ": " + e.getMessage());
				missingModelValidationMessage = missingModelValidationMessage + e.toString() + " on client " + client.getName() + '\n';
			}
		}
	}
	
	private void loadValidatorClass(MClient client, String className) {
		try
		{
			//
			Class clazz = Class.forName(className);
			ModelValidator validator = (ModelValidator)clazz.newInstance();
			initialize(validator, client);					
		}
		catch (Exception e)
		{
			//logging to db will try to init ModelValidationEngine again!
			//log.log(Level.SEVERE, className + ": " + e.getMessage());
			// System.err.println(e.toString());
			missingModelValidationMessage = missingModelValidationMessage + e.toString() + " on client " + client.getName() + '\n';
		}
	}
	
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
	/** Accounting Facts Validation Listeners   */
	private Hashtable<String,ArrayList<FactsValidator>>m_factsValidateListeners = new Hashtable<String,ArrayList<FactsValidator>>();
	
	private ArrayList<ModelValidator> m_globalValidators = new ArrayList<ModelValidator>();
	
	/**
	 * 	Initialize and add validator
	 *	@param validator
	 *	@param client
	 */
	private void initialize(ModelValidator validator, MClient client)
	{
		if (client == null)
			m_globalValidators.add(validator);
		m_validators.add(validator);
		validator.initialize(this, client);		
		
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
			if (AD_Client_ID == validator.getAD_Client_ID()
				|| m_globalValidators.contains(validator))
			{
				String error = validator.login(AD_Org_ID, AD_Role_ID, AD_User_ID);
				if (error != null && error.length() > 0)
					return error;
			}
		}
		if (AD_User_ID == 0 && AD_Role_ID == 0)
			; // don't validate for user system on role system
		else
			if (missingModelValidationMessage != null) {
				MSystem system = MSystem.get(Env.getCtx());
				if (system.isFailOnMissingModelValidator())
					return missingModelValidationMessage;
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
		String propertyName =
			m_globalValidators.contains(listener) 
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
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
		String propertyName = 
			m_globalValidators.contains(listener) 
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
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
	public String fireModelChange (PO po, int changeType)
	{
		if (po == null || m_modelChangeListeners.size() == 0)
			return null;
		
		String propertyName = po.get_TableName() + "*";
		ArrayList<ModelValidator> list = m_modelChangeListeners.get(propertyName);
		if (list != null)
		{
			//ad_entitytype.modelvalidationclasses
			String error = fireModelChange(po, changeType, list);
			if (error != null && error.length() > 0)
				return error;
		}
		
		propertyName = po.get_TableName() + po.getAD_Client_ID();
		list = m_modelChangeListeners.get(propertyName);
		if (list != null)
		{
			//ad_client.modelvalidationclasses
			String error = fireModelChange(po, changeType, list);
			if (error != null && error.length() > 0)
				return error;
		}
		
		return null;
	}	//	fireModelChange
	
	private String fireModelChange(PO po, int changeType, ArrayList<ModelValidator> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			try
			{
				ModelValidator validator = list.get(i);
				if (validator.getAD_Client_ID() == po.getAD_Client_ID()
					|| m_globalValidators.contains(validator))
				{
					String error = validator.modelChange(po, changeType);
					if (error != null && error.length() > 0)
						return error;
				}
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
	}
	
	
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
		String propertyName = 
			m_globalValidators.contains(listener) 
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
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
		String propertyName = 
			m_globalValidators.contains(listener) 
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		ArrayList list = (ArrayList)m_docValidateListeners.get(propertyName);
		if (list == null)
			return;
		list.remove(listener);
		if (list.size() == 0)
			m_docValidateListeners.remove(propertyName);
	}	//	removeDocValidate
	
	/**
	 * 	Fire Document Validation.
	 * 	Call docValidate method of added validators
	 *	@param po persistent objects
	 *	@param timing see ModelValidator.TIMING_ constants
     *	@return error message or null
	 */
	public String fireDocValidate (PO po, int docTiming)
	{
		if (po == null || m_docValidateListeners.size() == 0)
			return null;
		
		String propertyName = po.get_TableName() + "*";
		ArrayList<ModelValidator> list = m_docValidateListeners.get(propertyName);
		if (list != null)
		{
			//ad_entitytype.modelvalidationclasses
			String error = fireDocValidate(po, docTiming, list);
			if (error != null && error.length() > 0)
				return error;
		}
		
		propertyName = po.get_TableName() + po.getAD_Client_ID();
		list = m_docValidateListeners.get(propertyName);
		if (list != null)
		{
			//ad_client.modelvalidationclasses
			String error = fireDocValidate(po, docTiming, list);
			if (error != null && error.length() > 0)
				return error;
		}
		
		return null;
	}	//	fireDocValidate
	
	private String fireDocValidate(PO po, int docTiming, ArrayList<ModelValidator> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			ModelValidator validator = null;
			try
			{
				validator = list.get(i);
				if (validator.getAD_Client_ID() == po.getAD_Client_ID()
					|| m_globalValidators.contains(validator))
				{
					String error = validator.docValidate(po, docTiming);
					if (error != null && error.length() > 0)
						return error;
				}
			}
			catch (Exception e)
			{
				// Exeptions are errors and should stop the document processing - teo_sarca [ 1679692 ]
				// log.log(Level.SEVERE, validator.toString(), e);
				String error = e.getMessage();
				if (error == null)
					error = e.toString();
				return error;
			}
		}
		return null;
	}
	
	/**************************************************************************
	 * 	Add Accounting Facts Validation Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void addFactsValidate (String tableName, FactsValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		//
		String propertyName = 
			m_globalValidators.contains(listener) 
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		ArrayList<FactsValidator> list = (ArrayList<FactsValidator>)m_factsValidateListeners.get(propertyName);
		if (list == null)
		{
			list = new ArrayList<FactsValidator>();
			list.add(listener);
			m_factsValidateListeners.put(propertyName, list);
		}
		else
			list.add(listener);
	}	//	addFactsValidate

	/**
	 * 	Remove Accounting Facts Validation Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void removeFactsValidate (String tableName, FactsValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		String propertyName = 
			m_globalValidators.contains(listener) 
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		ArrayList list = (ArrayList)m_factsValidateListeners.get(propertyName);
		if (list == null)
			return;
		list.remove(listener);
		if (list.size() == 0)
			m_factsValidateListeners.remove(propertyName);
	}	//	removeFactsValidate
	
	/**
	 * Fire Accounting Facts Validation.
	 * Call factsValidate method of added validators
	 * @param schema
	 * @param facts
	 * @param doc
	 * @param po
	 * @return error message or null
	 */
	public String fireFactsValidate (MAcctSchema schema, List<Fact> facts, PO po)
	{
		if (schema == null || facts == null || po == null || m_factsValidateListeners.size() == 0)
			return null;
		
		String propertyName = po.get_TableName() + "*";
		ArrayList<FactsValidator> list = (ArrayList<FactsValidator>)m_factsValidateListeners.get(propertyName);
		if (list != null)
		{
			//ad_entitytype.modelvalidationclasses
			String error = fireFactsValidate(schema, facts, po, list);
			if (error != null && error.length() > 0)
				return error;
		}
		
		propertyName = po.get_TableName() + po.getAD_Client_ID();
		list = (ArrayList<FactsValidator>)m_factsValidateListeners.get(propertyName);
		if (list != null)
		{
			//ad_client.modelvalidationclasses
			String error = fireFactsValidate(schema, facts, po, list);
			if (error != null && error.length() > 0)
				return error;
		}
		
		return null;
	}	//	fireFactsValidate
	
	private String fireFactsValidate(MAcctSchema schema, List<Fact> facts, PO po,  ArrayList<FactsValidator> list)
	{
		for (int i = 0; i < list.size(); i++)
		{
			FactsValidator validator = null;
			try
			{
				validator = list.get(i);
				if (validator.getAD_Client_ID() == po.getAD_Client_ID())
				{
					String error = validator.factsValidate(schema, facts, po);
					if (error != null && error.length() > 0)
						return error;
				}
			}
			catch (Exception e)
			{
				// Exeptions are errors and should stop the document processing - teo_sarca [ 1679692 ]
				// log.log(Level.SEVERE, validator.toString(), e);
				String error = e.getMessage();
				if (error == null)
					error = e.toString();
				return error;
			}
		}
		return null;
	}
	
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
	
	/**
	 *  Create Model Validators Info
	 *  @param sb optional string buffer
	 *  @param ctx context
	 *  @return Model Validators Info
	 *  
	 *  @author Teo Sarca, FR [ 1724662 ]
	 */
	public StringBuffer getInfoDetail(StringBuffer sb, Properties ctx) {
		if (sb == null)
			sb = new StringBuffer();
		sb.append("=== ModelValidationEngine ===").append(Env.NL);
		sb.append("Validators #").append(m_validators.size()).append(Env.NL);
		for (ModelValidator mv : m_validators) {
			sb.append(mv.toString()).append(Env.NL);
		}
		sb.append(Env.NL).append(Env.NL);
		//
		sb.append("ModelChange #").append(m_modelChangeListeners.size()).append(Env.NL);
		Iterator<String> it = m_modelChangeListeners.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			ArrayList<ModelValidator> list = m_modelChangeListeners.get(key);
			for (ModelValidator mv : list) {
				sb.append(key).append(": ").append(mv.toString()).append(Env.NL);
			}
		}
		sb.append(Env.NL).append(Env.NL);
		//
		sb.append("DocValidate #").append(m_docValidateListeners.size()).append(Env.NL);
		it = m_docValidateListeners.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			ArrayList<ModelValidator> list = m_docValidateListeners.get(key);
			for (ModelValidator mv : list) {
				sb.append(key).append(": ").append(mv.toString()).append(Env.NL);
			}
		}
		sb.append(Env.NL).append(Env.NL);
		//
		return sb;
	}
	
	/**
	 * After Load Preferences into Context for selected client.
	 * @param ctx context
	 * @see org.compiere.util.Login#loadPreferences(KeyNamePair, KeyNamePair, java.sql.Timestamp, String)
	 * @author Teo Sarca - FR [ 1670025 ] - https://sourceforge.net/tracker/index.php?func=detail&aid=1670025&group_id=176962&atid=879335
	 */
	public void afterLoadPreferences (Properties ctx)
	{
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		for (int i = 0; i < m_validators.size(); i++) 
		{
			ModelValidator validator = (ModelValidator)m_validators.get(i);
			if (AD_Client_ID == validator.getAD_Client_ID()
				|| m_globalValidators.contains(validator))
			{
				java.lang.reflect.Method m = null;
				try {
					m = validator.getClass().getMethod("afterLoadPreferences", new Class[]{Properties.class});
				}
				catch(NoSuchMethodException e) {
					// ignore
				}
				try {
					if (m != null)
						m.invoke(validator, ctx);
				}
				catch (Exception e) {
					log.warning("" + validator + ": " + e.getLocalizedMessage());
				}
			}
		}
	}
	
}	//	ModelValidatorEngine
