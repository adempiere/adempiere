/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

import org.adempiere.core.domains.models.I_AD_ModelValidator;
import org.adempiere.core.domains.models.X_AD_ModelValidator;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.ImportValidator;
import org.adempiere.process.ImportProcess;
import org.compiere.acct.Fact;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;

import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

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
 * 				<li>FR [ 2788276 ] Data Import Validator
 * 					https://sourceforge.net/tracker/?func=detail&aid=2788276&group_id=176962&atid=879335
 * 				<li>BF [ 2804135 ] Global FactsValidator are not invoked
 * 					https://sourceforge.net/tracker/?func=detail&aid=2804135&group_id=176962&atid=879332
 * 				<li>BF [ 2819617 ] NPE if script validator rule returns null
 * 					https://sourceforge.net/tracker/?func=detail&aid=2819617&group_id=176962&atid=879332
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 				<li>BF [ 2947607 ] Model Validator Engine duplicate listeners 
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 * 				<li> Add support to model validator definition by client
 */
public class ModelValidationEngine {
	
	/**
	 * 	Get Singleton
	 *	@return modelValidatorEngine
	 */
	public synchronized static ModelValidationEngine get() {
		if (modelValidatorEngine == null)
			modelValidatorEngine = new ModelValidationEngine();
		return modelValidatorEngine;
	}	//	get
	
	/** Engine Singleton				*/
	private static ModelValidationEngine modelValidatorEngine = null;
	/* flag to indicate a missing model validation class */
	private static String missingModelValidationMessage = null; 
	
	
	/**************************************************************************
	 * 	Constructor.
	 * 	Creates Model Validators
	 */
	private ModelValidationEngine () {
		super ();
		// Load global validators
		try {
			List<X_AD_ModelValidator> entityTypes = new Query(Env.getCtx(), I_AD_ModelValidator.Table_Name, null, null)
					.setOnlyActiveRecords(true)
					.setOrderBy(I_AD_ModelValidator.COLUMNNAME_SeqNo)
					.list();
			entityTypes
				.stream()
				.filter(validator -> !Util.isEmpty(validator.getModelValidationClass()))
				.forEach(validator -> loadValidatorClass(validator.getAD_Client_ID() == 0? null: MClient.get(Env.getCtx(), validator.getAD_Client_ID()), validator.getModelValidationClass()));
		} catch (Exception e) {
			//logging to db will try to init ModelValidationEngine again!
			//log.warning(e.getLocalizedMessage());
			// System.err.println(e.getLocalizedMessage());
			missingModelValidationMessage = missingModelValidationMessage + e.toString() + " global" + '\n';
		}
		
		// Go through all Clients and start Validators
		Optional.ofNullable(MClient.getAll(Env.getCtx()))
				.ifPresent(clientList -> Arrays.stream(clientList)
								.filter(client -> client.getModelValidationClasses() != null && client.getModelValidationClasses().length() > 0)
								.forEach(client -> loadValidatorClasses(client, client.getModelValidationClasses())));
		//logging to db will try to init ModelValidationEngine again!
	}	//	ModelValidatorEngine
	
	private void loadValidatorClasses(MClient client, String classNames)  {
		StringTokenizer st = new StringTokenizer(classNames, ";");
		while (st.hasMoreTokens()) {
			String className = null;			
			try {
				className = st.nextToken();
				if (className == null)
					continue;
				className = className.trim();
				if (className.length() == 0)
					continue;
				//
				loadValidatorClass(client, className);					
			} catch (Exception e) {
				//logging to db will try to init ModelValidationEngine again!
				missingModelValidationMessage = missingModelValidationMessage + e.toString() + " on client " + client.getName() + '\n';
			}
		}
	}
	
	private void loadValidatorClass(MClient client, String className) {
		if(existValidatorClass(className)) {
			log.warning((client != null ? ("client " + client.getName()) : " global") + " already exists for class: " + className);
			return;
		}
		try {
			//
			Class<?> clazz = Class.forName(className);
			ModelValidator validator = (ModelValidator)clazz.newInstance();
			initialize(validator, client);
		} catch (Exception e) {
			//logging to db will try to init ModelValidationEngine again!
			//log.log(Level.SEVERE, className + ": " + e.getMessage());
			// System.err.println(e.toString());
			missingModelValidationMessage = missingModelValidationMessage + e.toString() + 
			 (client != null ? (" on client " + client.getName()) : " global") + '\n';
		}
	}
	
	/**	Logger					*/
	private static CLogger log = CLogger.getCLogger(ModelValidationEngine.class);
	/**	Verify Class	*/
	private List<String> validatorClasses = new ArrayList<String>();
	/**	Validators						*/
	private List<ModelValidator> validators = new ArrayList<ModelValidator>();
	/**	Model Change Listeners			*/
	private Hashtable<String,List<ModelValidator>> modelChangeListeners = new Hashtable<String,List<ModelValidator>>();
	/**	Document Validation Listeners			*/
	private Hashtable<String,List<ModelValidator>> documentValidateListeners = new Hashtable<String,List<ModelValidator>>();
	/** Accounting Facts Validation Listeners   */
	private Hashtable<String,List<FactsValidator>> factsValidateListeners = new Hashtable<String,List<FactsValidator>>();
	/** Data Import Validation Listeners   */
	private Hashtable<String,List<ImportValidator>> importValidateListeners = new Hashtable<String,List<ImportValidator>>();
	
	private List<ModelValidator> globalValidators = new ArrayList<ModelValidator>();
	
	/**
	 * 	Initialize and add validator
	 *	@param validator
	 *	@param client
	 */
	private void initialize(ModelValidator validator, MClient client) {
		if (client == null)
			globalValidators.add(validator);
		validators.add(validator);
		validator.initialize(this, client);		
		
	}	//	initialize

	/**
	 * Verify if exist a validator class
	 * @param validatorClass
	 * @return
	 */
	private boolean existValidatorClass(String validatorClass) {
		if(Util.isEmpty(validatorClass)) {
			return true;
		}
		boolean alreadyExist = validatorClasses
				.stream()
				.filter(validatorClassToFind -> validatorClassToFind.equals(validatorClass))
				.findFirst()
				.isPresent();
		if(!alreadyExist) {
			validatorClasses.add(validatorClass);
		}
		return alreadyExist;
	}
	
	/**
	 * 	Called when login is complete
	 * 	@param clientId client
	 *	@param orgId org
	 *	@param roleId role
	 *	@param userId user
	 *	@return error message or null
	 */
	public String loginComplete (int clientId, int orgId, int roleId, int userId)
	{
		Optional.ofNullable(validators)
				.ifPresent( ValidatorList ->
							ValidatorList.stream()
									.filter(modelValidator -> modelValidator.getAD_Client_ID() == clientId || globalValidators.contains(modelValidator))
									.forEach(modelValidator -> {
										String error = modelValidator.login(orgId, roleId, userId);
										if (error != null && error.length() > 0)
											throw new AdempiereException(error);
									}));
		
		// now process the script model validator login
		Optional.ofNullable(MRule.getModelValidatorLoginRules(Env.getCtx()))
				.ifPresent(rules -> rules.stream()
								.filter(Objects::nonNull)
								.filter(loginRule -> loginRule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs)
										          && loginRule.getEventType().equals(MRule.EVENTTYPE_ModelValidatorLoginEvent))
								.forEach(loginRule -> {
									// currently just JSR 223 supported
									String error;
									try {
										ScriptEngine engine = loginRule.getScriptEngine();
										MRule.setContext(engine, Env.getCtx(), 0);  // no window
										// now add the method arguments to the modelValidatorEngine
										engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", Env.getCtx());
										engine.put(MRule.ARGUMENTS_PREFIX + "AD_Client_ID", clientId);
										engine.put(MRule.ARGUMENTS_PREFIX + "AD_Org_ID", orgId);
										engine.put(MRule.ARGUMENTS_PREFIX + "AD_Role_ID", roleId);
										engine.put(MRule.ARGUMENTS_PREFIX + "AD_User_ID", userId);
										Object retval = engine.eval(loginRule.getScript());
										error = (retval == null ? "" : retval.toString());
									} catch (Exception e) {
										throw new AdempiereException(e);
									}
									if (error != null && error.length() > 0)
										throw new AdempiereException(error);
								}));
		
		if (userId == 0 && roleId == 0)
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
		String propertyName = globalValidators.contains(listener)
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		List<ModelValidator> modelValidators = modelChangeListeners.get(propertyName);
		if (modelValidators == null)
		{
			modelValidators = new ArrayList<>();
			modelValidators.add(listener);
			modelChangeListeners.put(propertyName, modelValidators);
		}
		else
			modelValidators.add(listener);
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
		String propertyName = globalValidators.contains(listener)
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		List<ModelValidator> modelValidators = modelChangeListeners.get(propertyName);
		if (modelValidators == null)
			return;
		modelValidators.remove(listener);
		if (modelValidators.size() == 0)
			modelChangeListeners.remove(propertyName);
	}	//	removeModelValidator
	
	/**
	 * 	Fire Model Change.
	 * 	Call modelChange method of added validators
	 *	@param po persistent objects
	 *	@param changeType ModelValidator.TYPE_*
	 *	@return error message or NULL for no veto
	 */
	public String fireModelChange (PO po, int changeType)
	{
		if (po == null || modelChangeListeners.size() == 0)
			return null;
		
		String propertyName = po.get_TableName() + "*";
		List<ModelValidator> modelValidators = modelChangeListeners.get(propertyName);
		if (modelValidators != null)
		{
			//ad_entitytype.modelvalidationclasses
			String error = fireModelChange(po, changeType, modelValidators);
			if (error != null && error.length() > 0)
				return error;
		}
		
		propertyName = po.get_TableName() + po.getAD_Client_ID();
		modelValidators = modelChangeListeners.get(propertyName);
		if (modelValidators != null)
		{
			//ad_client.modelvalidationclasses
			String error = fireModelChange(po, changeType, modelValidators);
			if (error != null && error.length() > 0)
				return error;
		}
		
		// now process the script model validator for this event
		Optional.ofNullable(MTableScriptValidator.getModelValidatorRules(po.getCtx(), po.get_Table_ID(), ModelValidator.tableEventValidators[changeType]))
				.ifPresent(	tableScriptValidatorList ->
							tableScriptValidatorList.stream()
									.filter(Objects::nonNull)
									.forEach(tableScriptValidator -> {
										MRule rule = MRule.get(po.getCtx(), tableScriptValidator.getAD_Rule_ID());
										// currently just JSR 223 supported
										if (rule != null
												&& rule.isActive()
												&& rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs)
												&& rule.getEventType().equals(MRule.EVENTTYPE_ModelValidatorTableEvent)) {
											String error;
											try {
												ScriptEngine engine = rule.getScriptEngine();
												MRule.setContext(engine, po.getCtx(), 0);  // no window
												// now add the method arguments to the modelValidatorEngine
												engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", po.getCtx());
												engine.put(MRule.ARGUMENTS_PREFIX + "PO", po);
												engine.put(MRule.ARGUMENTS_PREFIX + "Type", changeType);
												engine.put(MRule.ARGUMENTS_PREFIX + "Event", ModelValidator.tableEventValidators[changeType]);
												Object retval = engine.eval(rule.getScript());
												error = (retval == null ? "" : retval.toString());
											} catch (Exception e) {
												throw new AdempiereException(e);
											}
											if (error != null && error.length() > 0)
												throw new AdempiereException(error);
										}
									}));
		return null;
	}	//	fireModelChange
	
	private String fireModelChange(PO po, int changeType, List<ModelValidator> modelValidators)
	{
		Optional.ofNullable(modelValidators)
				.ifPresent(	modelValidatorList ->
							modelValidatorList.stream()
									.filter(modelValidator -> modelValidator.getAD_Client_ID() == po.getAD_Client_ID()
											|| globalValidators.contains(modelValidator))
									.forEach(modelValidator -> {
										try {
											String error = modelValidator.modelChange(po, changeType);
											if (error != null && error.length() > 0) {
												if (log.isLoggable(Level.FINE)) {
													log.log(Level.FINE, "po=" + po + " validator=" + modelValidator + " changeType=" + changeType);
												}
												throw new AdempiereException(error);
											}
										} catch (Exception e) {
											//log the exception
											log.log(Level.SEVERE, e.getLocalizedMessage(), e);
											String error = e.getLocalizedMessage();
											if (error == null)
												error = e.toString();
											throw new AdempiereException(error);
										}
									}));
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
		String propertyName = globalValidators.contains(listener)
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		List<ModelValidator> modelValidators = documentValidateListeners.get(propertyName);
		if (modelValidators == null)
		{
			modelValidators = new ArrayList<ModelValidator>();
			modelValidators.add(listener);
			documentValidateListeners.put(propertyName, modelValidators);
		}
		else if (!modelValidators.contains(listener))
		{    
		    modelValidators.add(listener);
		}
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
		String propertyName = globalValidators.contains(listener)
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		List<ModelValidator> modelValidators = documentValidateListeners.get(propertyName);
		if (modelValidators == null)
			return;
		modelValidators.remove(listener);
		if (modelValidators.size() == 0)
			documentValidateListeners.remove(propertyName);
	}	//	removeDocValidate
	
	/**
	 * 	Fire Document Validation.
	 * 	Call docValidate method of added validators
	 *	@param po persistent objects
	 *	@param docTiming see ModelValidator.TIMING_ constants
     *	@return error message or null
	 */
	public String fireDocValidate (PO po, int docTiming)
	{
		if (po == null || documentValidateListeners.size() == 0)
			return null;
		
		String propertyName = po.get_TableName() + "*";
		List<ModelValidator> modelValidators = documentValidateListeners.get(propertyName);
		if (modelValidators != null)
		{
			//ad_entitytype.modelvalidationclasses
			String error = fireDocValidate(po, docTiming, modelValidators);
			if (error != null && error.length() > 0)
				return error;
		}
		
		propertyName = po.get_TableName() + po.getAD_Client_ID();
		modelValidators = documentValidateListeners.get(propertyName);
		if (modelValidators != null)
		{
			//ad_client.modelvalidationclasses
			String error = fireDocValidate(po, docTiming, modelValidators);
			if (error != null && error.length() > 0)
				return error;
		}
		
		// now process the script model validator for this event
		List<MTableScriptValidator> tableScriptValidators = MTableScriptValidator.getModelValidatorRules(po.getCtx(), po.get_Table_ID(), ModelValidator.documentEventValidators[docTiming]);
		Optional.ofNullable(tableScriptValidators)
				.ifPresent(	tableScriptValidatorList ->
							tableScriptValidatorList.stream()
									.filter(Objects::nonNull)
									.forEach(tableScriptValidator -> {
										MRule rule = MRule.get(po.getCtx(), tableScriptValidator.getAD_Rule_ID());
										// currently just JSR 223 supported
										if (rule != null
												&& rule.isActive()
												&& rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs)
												&& rule.getEventType().equals(MRule.EVENTTYPE_ModelValidatorDocumentEvent)) {
											String error;
											try {
												ScriptEngine engine = rule.getScriptEngine();
												MRule.setContext(engine, po.getCtx(), 0);  // no window
												// now add the method arguments to the modelValidatorEngine
												engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", po.getCtx());
												engine.put(MRule.ARGUMENTS_PREFIX + "PO", po);
												engine.put(MRule.ARGUMENTS_PREFIX + "Type", docTiming);
												engine.put(MRule.ARGUMENTS_PREFIX + "Event", ModelValidator.documentEventValidators[docTiming]);
												Object retval = engine.eval(rule.getScript());
												error = (retval == null ? "" : retval.toString());
											} catch (Exception e) {
												throw new AdempiereException(e);
											}
											if (error != null && error.length() > 0)
												throw new AdempiereException(error);
										}
									}));
		return null;
	}	//	fireDocValidate
	
	private String fireDocValidate(PO po, int docTiming, List<ModelValidator> modelValidators)
	{
		Optional.ofNullable(modelValidators)
				.ifPresent(	modelValidatorList ->
							modelValidatorList.stream()
									.filter(modelValidator -> modelValidator.getAD_Client_ID() == po.getAD_Client_ID() || globalValidators.contains(modelValidator))
									.forEach(modelValidator -> {
										try {
											String error = modelValidator.docValidate(po, docTiming);
											if (error != null && error.length() > 0) {
												if (log.isLoggable(Level.FINE)) {
													log.log(Level.FINE, "po=" + po + " validator=" + modelValidator + " timing=" + docTiming);
												}
												throw new AdempiereException(error);
											}
										} catch (Exception e) {
											//log the stack trace
											log.log(Level.SEVERE, e.getLocalizedMessage(), e);
											// Exeptions are errors and should stop the document processing - teo_sarca [ 1679692 ]
											String error = e.getLocalizedMessage();
											if (error == null)
												error = e.toString();
											throw new AdempiereException(error);
										}
									}));
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
		String propertyName = globalValidators.contains(listener)
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		List<FactsValidator> factsValidators = factsValidateListeners.get(propertyName);
		if (factsValidators == null)
		{
			factsValidators = new ArrayList<FactsValidator>();
			factsValidators.add(listener);
			factsValidateListeners.put(propertyName, factsValidators);
		}
		else
			factsValidators.add(listener);
	}	//	addFactsValidate

	/**************************************************************************
	 * 	Add Date Import Validation Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void addImportValidate (String importTableName, ImportValidator listener)
	{
		String propertyName = importTableName + "*";
		List<ImportValidator> importValidators = importValidateListeners.get(propertyName);
		if (importValidators == null)
		{
			importValidators = new ArrayList<>();
			importValidators.add(listener);
			importValidateListeners.put(propertyName, importValidators);
		}
		else
		{
			importValidators.add(listener);
		}
	}

	/**
	 * 	Remove Accounting Facts Validation Listener
	 *	@param tableName table name
	 *	@param listener listener
	 */
	public void removeFactsValidate (String tableName, FactsValidator listener)
	{
		if (tableName == null || listener == null)
			return;
		String propertyName = globalValidators.contains(listener)
				? tableName + "*"
				: tableName + listener.getAD_Client_ID();
		List<FactsValidator> factsValidators = factsValidateListeners.get(propertyName);
		if (factsValidators == null)
			return;
		factsValidators.remove(listener);
		if (factsValidators.size() == 0)
			factsValidateListeners.remove(propertyName);
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
		if (schema == null || facts == null || po == null || factsValidateListeners.size() == 0)
			return null;
		
		String propertyName = po.get_TableName() + "*";
		List<FactsValidator> factsValidators = factsValidateListeners.get(propertyName);
		if (factsValidators != null)
		{
			String error = fireFactsValidate(schema, facts, po, factsValidators);
			if (error != null && error.length() > 0)
				return error;
		}
		
		propertyName = po.get_TableName() + po.getAD_Client_ID();
		factsValidators = factsValidateListeners.get(propertyName);
		if (factsValidators != null)
		{
			//ad_client.modelvalidationclasses
			String error = fireFactsValidate(schema, facts, po, factsValidators);
			if (error != null && error.length() > 0)
				return error;
		}
		
		return null;
	}	//	fireFactsValidate
	
	private String fireFactsValidate(MAcctSchema schema, List<Fact> facts, PO po,  List<FactsValidator> factsValidators)
	{
		Optional.ofNullable(factsValidators)
				.ifPresent(	factsValidatorList ->
							factsValidatorList.stream()
									.filter(factsValidator -> factsValidator.getAD_Client_ID() == po.getAD_Client_ID() || globalValidators.contains(factsValidator))
									.forEach(factsValidator -> {
										try {
											String error = factsValidator.factsValidate(schema, facts, po);
											if (error != null && error.length() > 0) {
												if (log.isLoggable(Level.FINE)) {
													log.log(Level.FINE, "po=" + po + " schema=" + schema + " validator=" + factsValidator);
												}
												throw new AdempiereException(error);
											}
										} catch (Exception e) {
											//log the stack trace
											log.log(Level.SEVERE, e.getLocalizedMessage(), e);
											// Exeptions are errors and should stop the document processing - teo_sarca [ 1679692 ]
											String error = e.getLocalizedMessage();
											if (error == null)
												error = e.toString();

											throw new AdempiereException(error);
										}
									}));
		return null;
	}

	/**
	 * Fire Import Validation.
	 * Call {@link ImportValidator#validate(ImportProcess, Object, Object, int)} or registered validators.
	 * @param process import process
	 * @param importModel import record (e.g. X_I_BPartner)  
	 * @param targetModel target model (e.g. MBPartner, MBPartnerLocation, MUser)
	 * @param timing see ImportValidator.TIMING_* constants
	 */
	public void fireImportValidate (ImportProcess process, PO importModel, PO targetModel, int timing)
	{
		String propertyName = process.getImportTableName() + "*";
		Optional.ofNullable(importValidateListeners.get(propertyName))
				.ifPresent(	importValidateListenerList ->
							importValidateListenerList.stream()
									.filter(Objects::nonNull)
									.forEach(importValidator -> importValidator.validate(process, importModel, targetModel, timing)));
	}
	
	/**
	* 	String Representation
	*	@return info
	*/
	public String toString()
	{
		StringBuffer sb = new StringBuffer("ModelValidationEngine[");
		sb.append("Validators=#").append(validators.size())
			.append(", ModelChange=#").append(modelChangeListeners.size())
			.append(", DocValidate=#").append(documentValidateListeners.size())
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
		sb.append("Validators #").append(validators.size()).append(Env.NL);
		for (ModelValidator modelValidator : validators) {
			sb.append(modelValidator.toString()).append(Env.NL);
		}
		sb.append(Env.NL).append(Env.NL);
		//
		sb.append("ModelChange #").append(modelChangeListeners.size()).append(Env.NL);
		Iterator<String> it = modelChangeListeners.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			List<ModelValidator> list = modelChangeListeners.get(key);
			for (ModelValidator modelValidator : list) {
				sb.append(key).append(": ").append(modelValidator.toString()).append(Env.NL);
			}
		}
		sb.append(Env.NL).append(Env.NL);
		//
		sb.append("DocValidate #").append(documentValidateListeners.size()).append(Env.NL);
		it = documentValidateListeners.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			List<ModelValidator> list = documentValidateListeners.get(key);
			for (ModelValidator modelValidator : list) {
				sb.append(key).append(": ").append(modelValidator.toString()).append(Env.NL);
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
		int clientId = Env.getAD_Client_ID(ctx);
		Optional.ofNullable(validators)
				.ifPresent(	validatorList ->
							validatorList.stream()
									.filter(modelValidator -> modelValidator.getAD_Client_ID() == clientId || globalValidators.contains(modelValidator))
									.forEach(modelValidator -> {
										java.lang.reflect.Method method = null;
										try {
											method = modelValidator.getClass().getMethod("afterLoadPreferences", new Class[]{Properties.class});
										} catch (NoSuchMethodException e) {
											// ignore
										}
										try {
											if (method != null)
												method.invoke(modelValidator, ctx);
										} catch (Exception e) {
											log.warning("" + modelValidator + ": " + e.getLocalizedMessage());
										}
									}));
	}

	/**
	 * Before Save Properties for selected client.
	 */
	public void beforeSaveProperties ()
	{
		int clientId = Env.getAD_Client_ID(Env.getCtx());
		Optional.ofNullable(validators)
				.ifPresent(	validatorList ->
							validatorList.stream()
									.filter(modelValidator -> modelValidator.getAD_Client_ID() == clientId || globalValidators.contains(modelValidator))
									.forEach(modelValidator -> {
										java.lang.reflect.Method method = null;
										try {
											method = modelValidator.getClass().getMethod("beforeSaveProperties");
										} catch (NoSuchMethodException e) {
											// ignore
										}
										try {
											if (method != null)
												method.invoke(modelValidator);
										} catch (Exception e) {
											log.warning("" + modelValidator + ": " + e.getLocalizedMessage());
										}
									}));
	}
}	//	ModelValidatorEngine