/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.apps;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.controller.SmallViewController;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.I_AD_Process;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Controller for Process Parameter, it allow to developer create different views from it
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 *		<li>FR [ 295 ] Report viewer re-query
 *		@see https://github.com/adempiere/adempiere/issues/295
 *		<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *		@see https://github.com/adempiere/adempiere/issues/352
 *		<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *		@see https://github.com/adempiere/adempiere/issues/298
 *		<a href="https://github.com/adempiere/adempiere/issues/566">
 * 		@see FR [ 566 ] Process parameter don't have a parameter like only information</a>
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] Parameter Panel & SmartBrowser criteria do not set gridField value
 */
public abstract class ProcessController extends SmallViewController {
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 *  @param columns
	 */
	public ProcessController(int WindowNo, ProcessInfo pi, int columns) {
		//	Get parameters
		windowNo = WindowNo;
		processInfo = pi;
		this.columns = columns;
	}	//	ProcessParameterPanel
	
	/**
	 * Standard Constructor
	 * @param WindowNo
	 * @param pi
	 */
	public ProcessController(int WindowNo, ProcessInfo pi) {
		this(WindowNo, pi, COLUMNS_1);
	}

	private int			windowNo;
	private ProcessInfo processInfo;
	private MProcess 	process;
	//	BR [ 300 ]
	private boolean 	isProcessed = false;
	private boolean 	isError;
	private int 		columns;
	private String		textMsg = null;
	private boolean 	isOnlyPanel;
	private boolean 	showButtons = true;
	private boolean 	showDescription = true;
	private boolean 	autoStart;
	private boolean 	isOKPressed = false;
	/**	Information Only	*/
	private HashMap<Integer, Boolean>	fieldsInfoOnly;
	private List<MPInstance> savedParams;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessController.class);
	//	Constants
	/**	For one Column		*/
	public static final int COLUMNS_1 = 1;
	/**	For two Column		*/
	public static final int COLUMNS_2 = 2;
	//
	
	/**	
	 * After init method
	 */
	public abstract void afterInit();
	
	/**
	 * Verify if is Auto Start Process
	 * @return
	 */
	public boolean isAutoStart() {
		return autoStart;
	}
	
	/**
	 * Set if is Auto Start Process
	 * @param autoStart
	 */
	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}
	
	/**
	 * Verify if it process has been processed
	 * @return
	 */
	public boolean isProcessed() {
		return isProcessed;
	}
	
	/**
	 * Set value for is processed
	 * @param isProcessed
	 */
	private void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	/**
	 * show buttons (Print Screen, Cancel, Ok)
	 * @param showButtons
	 */
	public void setShowButtons(boolean showButtons) {
		this.showButtons = showButtons;
	}
	
	/**
	 * Show process Description
	 * @param showDescription
	 */
	public void setShowDescription(boolean showDescription) {
		this.showDescription = showDescription;
	}
	
	/**
	 * Verify if it show Description
	 * @return
	 */
	public boolean isShowDescription() {
		return showDescription;
	}
	
	/**
	 * Verify if it should show Description
	 * @return
	 */
	public boolean isShowButtons() {
		return showButtons;
	}
	
	/**
	 * Verify if it run the process or is just for panel
	 * @return
	 */
	public boolean isOnlyPanel() {
		return isOnlyPanel;
	}
	
	/**
	 * Set if it handle the process or is just for panel
	 * @param isOnlyPanel
	 */
	public void setIsOnlyPanel(boolean isOnlyPanel) {
		this.isOnlyPanel = isOnlyPanel;
	}
	
	/**
	 * Verify if Ok action is pressed
	 * @return
	 */
	public boolean isOkPressed() {
		return isOKPressed;
	}
	
	/**
	 * Set Is Ok Action Pressed
	 * @param isOkPressed
	 */
	public void setIsOkPressed(boolean isOkPressed) {
		this.isOKPressed = isOkPressed;
	}
	
	/**
	 * Get Process Name
	 * @return
	 */
	public String getName() {
		return process.get_Translation(I_AD_Process.COLUMNNAME_Name);
	}
	
	/**
	 * Get Process Description
	 * @return
	 */
	public String getDescription() {
		return process.get_Translation(I_AD_Process.COLUMNNAME_Description);
	}
	
	/**
	 * Get Process Help
	 * @return
	 */
	public String getHelp() {
		return process.get_Translation(I_AD_Process.COLUMNNAME_Help);
	}
	
	/**
	 * Show help?
	 * @return
	 */
	public String getShowHelp() {
		return process.getShowHelp();
	}
	
	/**
	 * is Report
	 * @return
	 */
	public boolean isReport() {
		return process.isReport();
	}
	
	/**
	 * Get Text Message and validate name
	 * @return
	 */
	public String getTextMsg() {
		if(textMsg != null)
			return textMsg;
		//	
		String name = getName();
		if (name == null) {
			throw new AdempiereException("@NotFound@ @AD_Process_ID@=" + processInfo.getAD_Process_ID() + ". @CheckMissingTrl@");
		}
		String description = getDescription();
		String help = getHelp();
		//	
		StringBuffer messageText = new StringBuffer();
		messageText.append("<b>");
		//	For Description
		if (description == null
				|| description.trim().length() == 0) {
			messageText.append(Msg.getMsg(Env.getCtx(), "StartProcess?"));
		} else {
			messageText.append(description);
		}
		//	
		messageText.append("</b>");
		//	For help
		if (help != null
				&& help.trim().length() > 0) {
			messageText.append("<p>").append(help).append("</p>");
		}
		//	
		return messageText.toString();
	}
	
	/**
	 * Is Information only
	 * @param processParaID
	 * @return
	 */
	public boolean isInfoOnly(int processParaID) {
		Boolean isInfoOnly = fieldsInfoOnly.get(processParaID);
		return isInfoOnly != null && isInfoOnly;
	}
	
	/**
	 * Is error
	 * @return
	 */
	public boolean isError() {
		return isError;
	}
		
	/**
	 * Get Columns
	 * @return
	 */
	public int getColumns() {
		return columns;
	}
	
	/**
	 * Set Columns
	 * @param columns
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	/**
	 *	Read Fields to display
	 *  @return true if loaded OK
	 */
	public boolean loadData() {
		log.config("");	
		//	Create Fields
		boolean hasFields = false;
		process = MProcess.get(Env.getCtx(), processInfo.getAD_Process_ID());
		//	Load Parameter
		for(MProcessPara para : process.getASPParameters()) {
			hasFields = true;
			createField(para, windowNo);
		}
		//	
		return hasFields;
	}	//	init
	
	/**
	 * Load Saved Instances
	 */
	private void loadSavedInstances() {
		savedParams = MPInstance.get(Env.getCtx(), 
				processInfo.getAD_Process_ID(), processInfo.getAD_User_ID());
	}
	
	/**
	 * Get Saved Instances
	 * @param reQuery
	 * @return
	 */
	public List<MPInstance> getSavedInstances(boolean reQuery) {
		//	ReQuery
		if(reQuery) {
			loadSavedInstances();
		}
		//	Return
		return savedParams;
	}
	
	/**
	 * Init View
	 * @return
	 */
	public boolean createFieldsAndEditors() {
		//  Create fields and editors and sync them
		boolean isOk = super.init();
		afterInit();
		return isOk;
	}
		
	
	/**
	 * Validate Parameters
	 * @return null if nothing happens
	 */
	public String validateParameters() {
		log.config("");

		String msg = validateFields();
		//	Valid if there is no message
		if (msg != null && msg.length() > 0) {
			processInfo.setSummary(msg);
			processInfo.setError(true);
			return msg;
		}
		//	All OK
		return null;
	}	//	validateParameters

	/**
	 * Validate and save parameters if not happened error
	 * @return null if nothing happened
	 */
	public String saveParameters() {
		log.config("");
		setIsProcessed(false);
		//	Valid parameters
		String validError = validateParameters();
		if(validError != null)
			return validError;

		//	Save Process instance if it is not saved
		//	FR [ 295 ]
		if(processInfo.getAD_PInstance_ID() <= 0) {
			MPInstance instance = null;
			//	Set to null for reload
			//	BR [ 380 ]
			processInfo.setParameter(null);
			try {
				instance = new MPInstance(Env.getCtx(), 
						processInfo.getAD_Process_ID(), processInfo.getRecord_ID());
				instance.saveEx();
				//	Set Instance
				processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			} catch (Exception e) { 
				processInfo.setSummary (e.getLocalizedMessage()); 
				processInfo.setError (true); 
				log.warning(processInfo.toString()); 
				return processInfo.getSummary(); 
			}
		}
		
		/**********************************************************************
		 *	Save Now
		 */
		for (int i = 0; i < getFieldSize(); i++) {
			//	Get Values
			GridField field = (GridField) getField(i);
			GridField fieldTo = (GridField) getFieldTo(i);
			//	FR [ 566 ] Only Information
			if(field.isInfoOnly())
				continue;
			//	Validate
			field.validateValue();
			Object result = getValue(i);
			Object result2 = null;
			if (fieldTo != null) {
				result2 = getValue_To(i);
			}
			
			//	Create Parameter
			MPInstancePara para = new MPInstancePara (Env.getCtx(), processInfo.getAD_PInstance_ID(), i);
			para.setParameterName(field.getColumnName());
			//	
			if (result instanceof Timestamp || result2 instanceof Timestamp) {	//	Date
				para.setP_Date((Timestamp)result);
				if (fieldTo != null && result2 != null)
					para.setP_Date_To((Timestamp)result2);
			} else if (result instanceof Integer || result2 instanceof Integer) {	//	Integer
				if (result != null) {
					Integer ii = (Integer)result;
					para.setP_Number(ii.intValue());
				} if (fieldTo != null && result2 != null) {
					Integer ii = (Integer)result2;
					para.setP_Number_To(ii.intValue());
				}
			} else if (result instanceof BigDecimal || result2 instanceof BigDecimal) {	//	BigDecimal
				para.setP_Number ((BigDecimal)result);
				if (fieldTo != null && result2 != null)
					para.setP_Number_To ((BigDecimal)result2);
			} else if (result instanceof Boolean) {	//	Boolean
				Boolean bb = (Boolean)result;
				String value = bb.booleanValue() ? "Y" : "N";
				para.setP_String (value);
				//	to does not make sense
			} else {	//	String
				if (result != null)
					para.setP_String (result.toString());
				if (fieldTo != null && result2 != null)
					para.setP_String_To (result2.toString());
			}
			//  Info
			para.setInfo (getDisplay(i));
			if (fieldTo != null)
				para.setInfo_To (getDisplay_To(i));
			//
			para.saveEx();
			log.fine(para.toString());
		}	//	for every parameter
		setIsProcessed(true);
		return null;
	}	//	saveParameters
	
	/**
	 * Load parameter from a name
	 * @param saveName
	 * @return
	 */
	public boolean loadParameters(String saveName) {
		//	Validate Instance List
		if(savedParams == null
				|| saveName == null)
			return false;
		//	
		for (MPInstance instance: savedParams) {
			if (instance.getName().equals(saveName)) {
				loadParameters(instance);
				return true;
			}
		}
		//	
		return false;
	}
	
	/**
	 * Load values from saved parameters
	 * @param instance
	 * @return
	 */
	public boolean loadParameters(MPInstance instance) {
		log.config("");
		//	
		MPInstancePara[] params = instance.getParameters();
		for (int j = 0; j < getFieldSize(); j++) {
			//	Get Values
			GridField field = (GridField) getField(j);
			GridField fieldTo = (GridField) getFieldTo(j);
			//	Set Values
			setValue(j, null);
			if (fieldTo != null)
				setValue_To(j, null);

			for ( int i = 0; i < params.length; i++)
			{
				MPInstancePara para = params[i];
				para.getParameterName();

				if ( field.getColumnName().equals(para.getParameterName()) )
				{
					if (para.getP_Date() != null || para.getP_Date_To() != null )
					{
						setValue(j, para.getP_Date());
						if (fieldTo != null)
							setValue_To(j, para.getP_Date_To());
					}

					//	String
					else if ( para.getP_String() != null || para.getP_String_To() != null )
					{
						setValue(j, para.getP_String());
						if (fieldTo != null)
							setValue_To(j, para.getP_String_To());
					}
					else if ( !Env.ZERO.equals(para.getP_Number()) || !Env.ZERO.equals(para.getP_Number_To()) )
					{
						setValue(j, para.getP_Number());
						if (fieldTo != null)
							setValue_To(j, para.getP_Number_To());
					}

					log.fine(para.toString());
					break;
				}
			} // for every saved parameter
		}	//	for every field
		return true;
	}
	
	/**
	 * Create New Instance
	 * @param saveName
	 * @return
	 */
	public String createNewInstance(String saveName) {
		String errorMsg = null;
		try {
			MPInstance instance = new MPInstance(Env.getCtx(), processInfo.getAD_Process_ID(), processInfo.getRecord_ID());
			instance.setName(saveName);
			instance.saveEx();
			processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			//	Get Parameters
			errorMsg = saveParameters();
		} catch(Exception ex) {
			errorMsg = ex.getLocalizedMessage();
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
		//	Default Return
		return errorMsg;
	}
	
	/**
	 * Update a instance from Name
	 * @param saveName
	 * @return
	 */
	public String updateInstance(String saveName) {
		//	Validate Instance List
		if(savedParams == null
				|| saveName == null)
			return null;
		//	
		String errorMsg = null;
		try {
			for (MPInstance instance: savedParams) {
				if (instance.getName().equals(saveName)) {
					processInfo.setAD_PInstance_ID(instance.getAD_PInstance_ID());
					for (MPInstancePara para : instance.getParameters()) {
						para.deleteEx(true);
					}
					//	Save
					errorMsg = saveParameters();
					if(errorMsg != null)
						throw new AdempiereException(errorMsg);
				}
			}
		} catch(Exception ex) {
			errorMsg = ex.getLocalizedMessage();
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
		//	Default Return
		return errorMsg;
	}
	
	/**
	 * Delete a Instance from name
	 * @param saveName
	 * @return
	 */
	public String deleteInstance(String saveName) {
		//	Validate Instance List
		if(savedParams == null
				|| saveName == null)
			return null;
		//	
		String errorMsg = null;
		try {
			for (MPInstance instance: savedParams) {
				if (instance.getName().equals(saveName)) {
					instance.deleteEx(true);
				}
			}
		} catch(Exception ex) {
			errorMsg = ex.getLocalizedMessage();
			log.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
		}
		//	Default Return
		return errorMsg;
	}
			
	/**
	 * Get the Window number
	 * @return
	 */
	public int getWindowNo() {
		return windowNo;
	}
		
	/**
	 * Set Process Info
	 * @param processInfo
	 */
	public void setProcessInfo(ProcessInfo processInfo) {
		this.processInfo = processInfo;
	}
	
	/**
	 * Get Process Info
	 * FR [ 352 ]
	 * @return
	 */
	public ProcessInfo getProcessInfo() {
		return processInfo;
	}
	
	/**
	 * Get Log
	 * @param reload
	 * @return
	 */
	public String getLogInfo(boolean reload) {
		if(reload) {
			if(process.getProcedureName() != null
					&& process.getProcedureName().trim().length() > 0)
			ProcessInfoUtil.setLogFromDB(processInfo);
		}
		//	
		StringBuffer messageText = new StringBuffer();
		messageText.append("<p><font color=\"").append(processInfo.isError() ? "#FF0000" : "#0000FF").append("\">** ")
			.append(processInfo.getSummary())
			.append("</font></p>");
		//	Add Log
		messageText.append(processInfo.getLogInfo(true));
		//	Return
		return messageText.toString();
	}
	
}	//	ProcessParameterPanel
