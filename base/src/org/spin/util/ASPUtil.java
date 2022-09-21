/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import org.adempiere.core.domains.models.I_AD_Browse;
import org.adempiere.core.domains.models.I_AD_BrowseCustom;
import org.adempiere.core.domains.models.I_AD_Browse_Field;
import org.adempiere.core.domains.models.I_AD_Field;
import org.adempiere.core.domains.models.I_AD_Process;
import org.adempiere.core.domains.models.I_AD_ProcessCustom;
import org.adempiere.core.domains.models.I_AD_Process_Para;
import org.adempiere.core.domains.models.I_AD_Tab;
import org.adempiere.core.domains.models.I_AD_Window;
import org.adempiere.core.domains.models.I_AD_WindowCustom;
import org.adempiere.core.domains.models.I_ASP_Level;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.Adempiere;
import org.compiere.model.MBrowseCustom;
import org.compiere.model.MBrowseFieldCustom;
import org.compiere.model.MColumn;
import org.compiere.model.MField;
import org.compiere.model.MFieldCustom;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessCustom;
import org.compiere.model.MProcessPara;
import org.compiere.model.MProcessParaCustom;
import org.compiere.model.MRole;
import org.compiere.model.MTab;
import org.compiere.model.MTabCustom;
import org.compiere.model.MTable;
import org.compiere.model.MWindow;
import org.compiere.model.MWindowCustom;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Util;

/**
 * Class for handle ASP Util as wrapper for standard process, window and smart browse
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 */
public class ASPUtil {
	
	/**	Instance	*/
	private static ASPUtil instance = null;
	/**	Client	*/
	private int clientId;
	/**	Role	*/
	private int roleId;
	/**	User	*/
	private int userId;
	/**	Language	*/
	private String language;
	/**	Context	*/
	private Properties context;
	private final static String CACHE_KEY = "Client_Customization_";
	/**	Process	Cache */
	private static CCache<String, MProcess> processCache = new CCache<String, MProcess>(CACHE_KEY + I_AD_Process.Table_Name, 20);
	/**	Process	Parameter Cache */
	private static CCache<String, List<MProcessPara>> processParameterCache = new CCache<String, List<MProcessPara>>(CACHE_KEY + I_AD_Process_Para.Table_Name, 20);
	/**	Browse Cache */
	private static CCache<String, MBrowse> browseCache = new CCache<String, MBrowse>(CACHE_KEY + I_AD_Browse.Table_Name, 20);
	/**	Process	Parameter Cache */
	private static CCache<String, List<MBrowseField>> browseFieldCache = new CCache<String, List<MBrowseField>>(CACHE_KEY + I_AD_Browse_Field.Table_Name, 20);
	/**	Window	Cache */
	private static CCache<String, MWindow> windowCache = new CCache<String, MWindow>(CACHE_KEY + I_AD_Window.Table_Name, 20);
	/**	Tab	Cache */
	private static CCache<String, List<MTab>> tabCache = new CCache<String, List<MTab>>(CACHE_KEY + I_AD_Tab.Table_Name, 20);
	/**	Field	Cache */
	private static CCache<String, List<MField>> fieldCache = new CCache<String, List<MField>>(CACHE_KEY + I_AD_Field.Table_Name, 20);
	
	/**	Level	*/
	private final int CLIENT = 1;
	private final int ROLE = 2;
	private final int USER = 3;
	
	/**
	 * Private constructor
	 */
	private ASPUtil(Properties context, int clientId, int roleId, int userId, String language) {
		if(context == null) {
			throw new AdempiereException("@ContextIsMandatory@");
		}
		//	Load values
		loadValues(context, clientId, roleId, userId, language);
	}
	
	/**
	 * Load default values
	 * @param context
	 * @param clientId
	 * @param roleId
	 * @param userId
	 * @param language
	 */
	private void loadValues(Properties context, int clientId, int roleId, int userId, String language) {
		this.context = context;
		this.clientId = clientId; 
		this.roleId = roleId;
		this.userId = userId;
		this.language = language;
	}
	
	/**
	 * Get instance from context
	 * @param context
	 * @return
	 */
	public static ASPUtil getInstance(Properties context) {
		return getInstance(context, Env.getAD_Client_ID(context), Env.getAD_Role_ID(context), Env.getAD_User_ID(context), Env.getAD_Language(context));
	}
	
	/**
	 * Get instance for ASP
	 * @param context
	 * @return
	 */
	public static ASPUtil getInstance(Properties context, int clientId, int roleId, int userId, String language) {
		if(instance == null) {
			instance = new ASPUtil(context, clientId, roleId, userId, language);
		} else {
			instance.loadValues(context, clientId, roleId, userId, language);
		}
		//	
		return instance;
	}
	
	/**
	 * Get instance without context
	 * @return
	 */
	public static ASPUtil getInstance() {
		return getInstance(Env.getCtx());
	}
	
	/**
	 * Get process for User / Role / Client / Dictionary
	 * @param processId
	 * @return
	 */
	public MProcess getProcess(int processId) {
		//	User level
		MProcess process = processCache.get(getUserKey(processId));
		//	Role Level
		if(process == null) {
			process = processCache.get(getRoleKey(processId));
		}
		//	Client Level (ASP)
		if(process == null) {
			process = processCache.get(getClientKey(processId));
		}
		//	Dictionary Level Base
		if(process == null) {
			process = processCache.get(getDictionaryKey(processId));
		}
		//	Reload
		if(process == null) {
			process = getProcessForASP(processId);
		}
		return process;
	}
	
	/**
	 * Get browse for User / Role / Client / Dictionary
	 * @param browseId
	 * @return
	 */
	public MBrowse getBrowse(int browseId) {
		//	User level
		MBrowse browse = browseCache.get(getUserKey(browseId));
		//	Role Level
		if(browse == null) {
			browse = browseCache.get(getRoleKey(browseId));
		}
		//	Client Level (ASP)
		if(browse == null) {
			browse = browseCache.get(getClientKey(browseId));
		}
		//	Dictionary Level Base
		if(browse == null) {
			browse = browseCache.get(getDictionaryKey(browseId));
		}
		//	Reload
		if(browse == null) {
			browse = getBrowseForASP(browseId);
		}
		return browse;
	}
	
	/**
	 * Get browse fields
	 * @param browseId
	 * @return
	 */
	public List<MBrowseField> getBrowseFields(int browseId) {
		if(browseCache.get(getDictionaryKey(browseId)) == null) {
			getBrowse(browseId);
		}
		//	User level
		if(browseCache.get(getUserKey(browseId)) != null) {
			return browseFieldCache.get(getUserKey(browseId));
		}
		//	Role Level
		if(browseCache.get(getRoleKey(browseId)) != null) {
			return browseFieldCache.get(getRoleKey(browseId));
		}
		//	Client Level (ASP)
		if(browseCache.get(getClientKey(browseId)) != null) {
			return browseFieldCache.get(getClientKey(browseId));
		}
		//	Dictionary Level Base
		return browseFieldCache.get(getDictionaryKey(browseId));
	}
	
	/**
	 * Get Browse Criteria fields
	 * @param browseId
	 * @return
	 */
	public List<MBrowseField> getBrowseCriteriaFields(int browseId) {
		List<MBrowseField> fields = getBrowseFields(browseId);
		if(fields == null) {
			return null;
		}
		//	Filter
		return fields.stream()
			.filter(field -> field.isActive() && field.isQueryCriteria())
			.sorted(Comparator.comparing(MBrowseField::getSeqNoGrid))
			.collect(Collectors.toList());
	}
	
	/**
	 * Get Browse Identifier fields
	 * @param browseId
	 * @return
	 */
	public List<MBrowseField> getBrowseIdentifierFields(int browseId) {
		List<MBrowseField> fields = getBrowseFields(browseId);
		if(fields == null) {
			return null;
		}
		//	Filter
		return fields.stream()
			.filter(field -> field.isActive() && field.isIdentifier())
			.sorted(Comparator.comparing(MBrowseField::getSeqNo))
			.collect(Collectors.toList());
	}
	
	/**
	 * Get Browse Identifier fields
	 * @param browseId
	 * @return
	 */
	public List<MBrowseField> getBrowseOrderByFields(int browseId) {
		List<MBrowseField> fields = getBrowseFields(browseId);
		if(fields == null) {
			return null;
		}
		//	Filter
		return fields.stream()
			.filter(field -> field.isActive() && field.isOrderBy() && field.isDisplayed())
			.sorted(Comparator.comparing(MBrowseField::getSeqNo))
			.collect(Collectors.toList());
	}
	
	/**
	 * Get Browse Identifier fields
	 * @param browseId
	 * @return
	 */
	public List<MBrowseField> getBrowseDisplayFields(int browseId) {
		List<MBrowseField> fields = getBrowseFields(browseId);
		if(fields == null) {
			return null;
		}
		//	Filter
		return fields.stream()
			.filter(field -> field.isActive() && field.isDisplayed() || field.isIdentifier())
			.sorted(Comparator.comparing(MBrowseField::getSeqNo))
			.collect(Collectors.toList());
	}
	
	/**
	 * Get Browse Key
	 * @param browseId
	 * @return
	 */
	public MBrowseField getBrowseFieldKey(int browseId) {
		List<MBrowseField> fields = getBrowseFields(browseId);
		if(fields == null) {
			return null;
		}
		//	Filter
		return fields.stream()
			.filter(field -> field.isActive() && field.isKey())
			.sorted(Comparator.comparing(MBrowseField::getSeqNo))
			.findFirst()
			.get();
	}
	
	/**
	 * Get window for User / Role / Client / Dictionary
	 * @param windowId
	 * @return
	 */
	public MWindow getWindow(int windowId) {
		//	User level
		MWindow window = windowCache.get(getUserKey(windowId));
		//	Role Level
		if(window == null) {
			window = windowCache.get(getRoleKey(windowId));
		}
		//	Client Level (ASP)
		if(window == null) {
			window = windowCache.get(getClientKey(windowId));
		}
		//	Dictionary Level Base
		if(window == null) {
			window = windowCache.get(getDictionaryKey(windowId));
		}
		//	Reload
		if(window == null) {
			window = getWindowForASP(windowId);
		}
		
		return window;
	}
	
	/**
	 * Get Tabs of window
	 * @param windowId
	 * @return
	 */
	public List<MTab> getWindowTabs(int windowId) {
		if(windowCache.get(getDictionaryKey(windowId)) == null) {
			getWindow(windowId);
		}
		//	User level
		if(windowCache.get(getUserKey(windowId)) != null) {
			return tabCache.get(getUserKey(windowId));
		}
		//	Role Level
		if(windowCache.get(getRoleKey(windowId)) != null) {
			return tabCache.get(getRoleKey(windowId));
		}
		//	Client Level (ASP)
		if(windowCache.get(getClientKey(windowId)) != null) {
			return tabCache.get(getClientKey(windowId));
		}
		//	Dictionary Level Base
		return tabCache.get(getDictionaryKey(windowId));
	}
	
	/**
	 * Get window Tab from ID
	 * @param windowId
	 * @param tabId
	 * @return
	 */
	public MTab getWindowTab(int windowId, int tabId) {
		List<MTab> tabs = getWindowTabs(windowId);
		if(tabs == null
				|| tabs.isEmpty()) {
			return null;
		}
		Optional<MTab> optionalTab = tabs.stream().filter(tab -> tab.getAD_Tab_ID() == tabId).findFirst();
		if(optionalTab.isPresent()) {
			return optionalTab.get();
		}
		return null;
	}
	
	/**
	 * Get Tabs of window
	 * @param tabId
	 * @return
	 */
	public List<MField> getWindowFields(int tabId) {
		MTab tab = MTab.get(context, tabId);
		int windowId = tab.getAD_Window_ID();
		if(windowCache.get(getDictionaryKey(tab.getAD_Window_ID())) == null
				|| fieldCache.get(getDictionaryKey(tabId)) == null) {
			getWindow(windowId);
		}
		//	User level
		if(windowCache.get(getUserKey(windowId)) != null) {
			return fieldCache.get(getUserKey(tabId));
		}
		//	Role Level
		if(windowCache.get(getRoleKey(windowId)) != null) {
			return fieldCache.get(getRoleKey(tabId));
		}
		//	Client Level (ASP)
		if(windowCache.get(getClientKey(windowId)) != null) {
			return fieldCache.get(getClientKey(tabId));
		}
		//	Dictionary Level Base
		return fieldCache.get(getDictionaryKey(tabId));
	}
	
	/**
	 * Get Process Parameter
	 * @param processId
	 * @return
	 */
	public List<MProcessPara> getProcessParameters(int processId) {
		if(processCache.get(getDictionaryKey(processId)) == null) {
			getProcess(processId);
		}
		//	User level
		if(processCache.get(getUserKey(processId)) != null) {
			return processParameterCache.get(getUserKey(processId));
		}
		//	Role Level
		if(processCache.get(getRoleKey(processId)) != null) {
			return processParameterCache.get(getRoleKey(processId));
		}
		//	Client Level (ASP)
		if(processCache.get(getClientKey(processId)) != null) {
			return processParameterCache.get(getClientKey(processId));
		}
		//	Dictionary Level Base
		return processParameterCache.get(getDictionaryKey(processId));
	}
	
	/**
	 * Get / Load process for ASP
	 * @param processId
	 * @return
	 */
	private MProcess getProcessForASP(int processId) {
		MProcess process = MProcess.get(context, processId);
		if(process == null) {
			return process;
		}
		loadTranslation(process);
		//	Save dictionary
		processCache.put(getDictionaryKey(processId), process.getDuplicated());
		//	Old compatibility
		MTable newTable = MTable.get(context, I_AD_ProcessCustom.Table_ID);
		if(newTable == null
				|| Util.isEmpty(newTable.getTableName())) {
			loadProcessParameters(process);
			return process;
		}
		//	Merge Process for client (ASP)
		process = getClientProcess(process);
		//	Merge Process for role
		process = getRoleProcess(process);
		//	Merge Process for user
		process = getUserProcess(process);
		//	
		return process;
	}
	
	/**
	 * Get / Load browse for ASP
	 * @param processId
	 * @return
	 */
	private MBrowse getBrowseForASP(int processId) {
		MBrowse browse = MBrowse.get(context, processId);
		if(browse == null) {
			return browse;
		}
		//	Save dictionary
		browseCache.put(getDictionaryKey(processId), browse.getDuplicated());
		//	Old compatibility
		MTable newTable = MTable.get(context, I_AD_BrowseCustom.Table_ID);
		if(newTable == null
				|| Util.isEmpty(newTable.getTableName())) {
			loadBrowseFields(browse);
			return browse;
		}
		//	Merge Browse for client (ASP)
		browse = getClientBrowse(browse);
		//	Merge Browse for role
		browse = getRoleBrowse(browse);
		//	Merge Browse for user
		browse = getUserBrowse(browse);
		//	
		return browse;
	}
	
	/**
	 * Get / Load process for ASP
	 * @param windowId
	 * @return
	 */
	private MWindow getWindowForASP(int windowId) {
		MWindow window = MWindow.get(context, windowId);
		//	Save dictionary
		windowCache.put(getDictionaryKey(windowId), window.getDuplicated());
		//	Old compatibility
		MTable newTable = MTable.get(context, I_AD_WindowCustom.Table_Name);
		if(newTable == null
				|| Util.isEmpty(newTable.getTableName())) {
			loadWindowTabs(window);
			return window;
		}
		//	Merge Window for client (ASP)
		window = getClientWindow(window);
		//	Merge Window for role
		window = getRoleWindow(window);
		//	Merge Window for user
		window = getUserWindow(window);
		//	
		return window;
	}
	
	/**
	 * Merge parameters with custom parameters
	 * @param processParameters
	 * @param customProcessParameters
	 * @param overwrite
	 */
	private List<MProcessPara> mergeParameters(List<MProcessPara> processParameters, List<MProcessParaCustom> customProcessParameters, boolean overwrite) {
		List<MProcessPara> mergedParameters = null;
		if(overwrite) {
			mergedParameters = new ArrayList<>();
			for(MProcessPara parameter : processParameters) {
				MProcessPara parameterToAdd = parameter.getDuplicated();
				parameterToAdd.setIsActive(false);
				mergedParameters.add(parameterToAdd);
			}
		} else {
			mergedParameters = new ArrayList<>(processParameters);
		}
		//	merge all parameters
		for(int index = 0; index < mergedParameters.size(); index++) {
			MProcessPara parameter = mergedParameters.get(index);
			customProcessParameters.stream()
			.filter(customParameter -> customParameter.getAD_Process_Para_ID() == parameter.getAD_Process_Para_ID())
			.forEach(customParameter -> {
				mergeProcessParameter(parameter, customParameter, overwrite);
			});
			mergedParameters.set(index, parameter);
		}
		//
		mergedParameters.sort(Comparator.comparing(MProcessPara::getSeqNo));
		return mergedParameters;
	}
	
	/**
	 * Merge fields with custom fields
	 * @param browseFields
	 * @param customBrowseFields
	 * @param overwrite
	 */
	private List<MBrowseField> mergeBrowseFields(List<MBrowseField> browseFields, List<MBrowseFieldCustom> customBrowseFields, boolean overwrite) {
		List<MBrowseField> mergedFields = null;
		if(overwrite) {
			mergedFields = new ArrayList<>();
			for(MBrowseField field : browseFields) {
				MBrowseField fieldToAdd = field.getDuplicated();
				fieldToAdd.setIsActive(false);
				fieldToAdd.setIsDisplayed(false);
				mergedFields.add(fieldToAdd);
			}
		} else {
			mergedFields = new ArrayList<>(browseFields);
		}
		//	merge all parameters
		for(int index = 0; index < mergedFields.size(); index++) {
			MBrowseField field = mergedFields.get(index);
			customBrowseFields.stream()
			.filter(customParameter -> customParameter.getAD_Browse_Field_ID() == field.getAD_Browse_Field_ID())
			.forEach(customField -> {
				mergeBrowseField(field, customField, overwrite);
			});
			mergedFields.set(index, field);
		}
		//
		mergedFields.sort(Comparator.comparing(MBrowseField::getSeqNo));
		return mergedFields;
	}
	
	/**
	 * Merge parameters with custom parameters
	 * @param windowTabs
	 * @param customWindowTabs
	 * @param overwrite
	 */
	private List<MTab> mergeTabs(List<MTab> windowTabs, List<MTabCustom> customWindowTabs, boolean overwrite, int level) {
		List<MTab> mergedTabs = null;
		if(overwrite) {
			mergedTabs = new ArrayList<>();
			for(MTab parameter : windowTabs) {
				MTab tabToAdd = parameter.getDuplicated();
				tabToAdd.setIsActive(false);
				mergedTabs.add(tabToAdd);
			}
		} else {
			mergedTabs = new ArrayList<>(windowTabs);
		}
		//	merge all parameters
		for(int index = 0; index < mergedTabs.size(); index++) {
			MTab tab = mergedTabs.get(index);
			List<MField> fields = loadFields(tab);
			if(level == CLIENT) {
				fields = fieldCache.get(getClientKey(tab.getAD_Tab_ID()));
			} else if(level == ROLE) {
				fields = fieldCache.get(getClientKey(tab.getAD_Tab_ID()));
			} else if(level == USER) {
				fields = fieldCache.get(getRoleKey(tab.getAD_Tab_ID()));
			}
			for(MTabCustom customTab : customWindowTabs) {
				if(customTab.getAD_Tab_ID() != tab.getAD_Tab_ID()) {
					continue;
				}
				mergeTab(tab, customTab, overwrite, fields, level);
			}
			mergedTabs.set(index, tab);
		}
		//
		mergedTabs.sort(Comparator.comparing(MTab::getSeqNo));
		return mergedTabs;
	}
	
	/**
	 * Load process parameters
	 * @param process
	 */
	private List<MProcessPara> loadProcessParameters(MProcess process) {
		List<MProcessPara> parameters = processParameterCache.get(getDictionaryKey(process.getAD_Process_ID()));
		if(parameters != null) {
			return parameters;
		}
		processParameterCache.put(getDictionaryKey(process.getAD_Process_ID()), process.getParametersAsList());
		//	ASP Client
		parameters = process.getASPParameters();
		parameters.forEach(parameter -> loadTranslation(parameter));
		processParameterCache.put(getClientKey(process.getAD_Process_ID()), parameters);
		processParameterCache.put(getRoleKey(process.getAD_Process_ID()), parameters);
		processParameterCache.put(getUserKey(process.getAD_Process_ID()), parameters);
		return parameters;
	}
	
	/**
	 * Load browse parameters
	 * @param browse
	 */
	private List<MBrowseField> loadBrowseFields(MBrowse browse) {
		List<MBrowseField> fields = browseFieldCache.get(getDictionaryKey(browse.getAD_Browse_ID()));
		if(fields != null) {
			return fields;
		}
		browseFieldCache.put(getDictionaryKey(browse.getAD_Browse_ID()), browse.getFields());
		//	ASP Client
		fields = browse.getFields();
		browseFieldCache.put(getClientKey(browse.getAD_Browse_ID()), fields);
		browseFieldCache.put(getRoleKey(browse.getAD_Browse_ID()), fields);
		browseFieldCache.put(getUserKey(browse.getAD_Browse_ID()), fields);
		return fields;
	}
	
	/**
	 * Load window tabs
	 * @param window
	 */
	private List<MTab> loadWindowTabs(MWindow window) {
		List<MTab> tabs = tabCache.get(getDictionaryKey(window.getAD_Window_ID()));
		if(tabs != null) {
			return tabs;
		}
		//	Tab List
		tabs = window.getASPTabs();
		tabs.forEach(tab -> loadTranslation(tab));
		tabCache.put(getDictionaryKey(window.getAD_Window_ID()), tabs);
		//	ASP Client
		tabCache.put(getClientKey(window.getAD_Window_ID()), tabs);
		tabCache.put(getRoleKey(window.getAD_Window_ID()), tabs);
		tabCache.put(getUserKey(window.getAD_Window_ID()), tabs);
		//	Load fields
		tabs.stream().forEach(tab -> {
			loadFields(tab);
		});
		return tabs;
	}
	
	/**
	 * Load fields
	 * @param tab
	 */
	private List<MField> loadFields(MTab tab) {
		List<MField> fields = fieldCache.get(getDictionaryKey(tab.getAD_Tab_ID()));
		if(fields != null) {
			return fields;
		}
		//	Tab List
		fields = tab.getASPFields();
		//	Change translation
		fields.forEach(field -> loadTranslation(field));
		fieldCache.put(getDictionaryKey(tab.getAD_Tab_ID()), fields);
		//	ASP Client
		fieldCache.put(getClientKey(tab.getAD_Tab_ID()), fields);
		fieldCache.put(getRoleKey(tab.getAD_Tab_ID()), fields);
		fieldCache.put(getUserKey(tab.getAD_Tab_ID()), fields);
		return fields;
	}
	
	
	/**
	 * Window for Client of ASP
	 * @param windowId
	 * @return
	 */
	private List<MWindowCustom> getClientWindowList(int windowId) {
		String whereClause = "EXISTS(SELECT 1 FROM ASP_ClientLevel cl "
				+ "WHERE cl.AD_Client_ID = ? "
				+ "AND cl.ASP_Level_ID = AD_WindowCustom.ASP_Level_ID) "
				+ "AND AD_Window_ID = ?";
		//	Get
		return new Query(context, I_AD_WindowCustom.Table_Name, whereClause, null)
				.setParameters(clientId, windowId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get client process list for ASP
	 * @param processId
	 * @return
	 */
	private List<MProcessCustom> getClientProcessList(int processId) {
		String whereClause = "EXISTS(SELECT 1 FROM ASP_ClientLevel cl "
				+ "WHERE cl.AD_Client_ID = ? "
				+ "AND cl.ASP_Level_ID = AD_ProcessCustom.ASP_Level_ID) "
				+ "AND AD_Process_ID = ?";
		//	Get
		return new Query(context, I_AD_ProcessCustom.Table_Name, whereClause, null)
				.setParameters(clientId, processId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get client browse list for ASP
	 * @param browseId
	 * @return
	 */
	private List<MBrowseCustom> getClientBrowseList(int browseId) {
		String whereClause = "EXISTS(SELECT 1 FROM ASP_ClientLevel cl "
				+ "WHERE cl.AD_Client_ID = ? "
				+ "AND cl.ASP_Level_ID = AD_BrowseCustom.ASP_Level_ID) "
				+ "AND AD_Browse_ID = ?";
		//	Get
		return new Query(context, I_AD_BrowseCustom.Table_Name, whereClause, null)
				.setParameters(clientId, browseId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get role where clause for included
	 * @return
	 */
	private String getIncludedRoleWhereClause() {
		StringBuffer inClause = new StringBuffer("IN(").append(roleId);
		MRole.get(context, roleId).getIncludedRoles(true).forEach(role -> inClause.append(", ").append(role.getAD_Role_ID()));
		//	Add last
		inClause.append(")");
		if(MTable.get(context, "ASP_Level_Access") == null) {
			return "AD_Role_ID " + inClause;
		}
		StringBuffer aspAccess = new StringBuffer("IN(0");
		new Query(context, I_ASP_Level.Table_Name, "EXISTS(SELECT 1 FROM ASP_Level_Access la WHERE la.ASP_Level_ID = ASP_Level.ASP_Level_ID AND la.AD_Role_ID " + inClause.toString() + ")", null)
			.getIDsAsList().forEach(levelId -> aspAccess.append(", ").append(levelId));
		aspAccess.append(")");
		return "(AD_Role_ID " + inClause + " OR ASP_Level_ID " + aspAccess + ")";
	}
	
	/**
	 * Get role window list for ASP
	 * @param windowId
	 * @return
	 */
	private List<MWindowCustom> getRoleWindowList(int windowId) {
		//	Add last
		String whereClause = getIncludedRoleWhereClause() + " AND AD_Window_ID = ?";
		//	Get
		return new Query(context, I_AD_WindowCustom.Table_Name, whereClause, null)
				.setParameters(windowId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get role process list for ASP
	 * @param processId
	 * @return
	 */
	private List<MProcessCustom> getRoleProcessList(int processId) {
		String whereClause = getIncludedRoleWhereClause() + " AND AD_Process_ID = ?";
		//	Get
		return new Query(context, I_AD_ProcessCustom.Table_Name, whereClause, null)
				.setParameters(processId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get role browse list for ASP
	 * @param browseId
	 * @return
	 */
	private List<MBrowseCustom> getRoleBrowseList(int browseId) {
		String whereClause = getIncludedRoleWhereClause() + " AND AD_Browse_ID = ?";
		//	Get
		return new Query(context, I_AD_BrowseCustom.Table_Name, whereClause, null)
				.setParameters(browseId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get user process list for ASP
	 * @param processId
	 * @return
	 */
	private List<MProcessCustom> getUserProcessList(int processId) {
		String whereClause = "AD_User_ID = ? AND AD_Process_ID = ?";
		//	Get
		return new Query(context, I_AD_ProcessCustom.Table_Name, whereClause, null)
				.setParameters(userId, processId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get user browse list for ASP
	 * @param browseId
	 * @return
	 */
	private List<MBrowseCustom> getUserBrowseList(int browseId) {
		String whereClause = "AD_User_ID = ? AND AD_Browse_ID = ?";
		//	Get
		return new Query(context, I_AD_BrowseCustom.Table_Name, whereClause, null)
				.setParameters(userId, browseId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get user window list for ASP
	 * @param windowId
	 * @return
	 */
	private List<MWindowCustom> getUserWindowList(int windowId) {
		String whereClause = "AD_User_ID = ? AND AD_Window_ID = ?";
		//	Get
		return new Query(context, I_AD_WindowCustom.Table_Name, whereClause, null)
				.setParameters(userId, windowId)
				.setOnlyActiveRecords(true)
				.list();
	}
	
	/**
	 * Get client window from dictionary process
	 * @param window
	 * @return
	 */
	private MWindow getClientWindow(MWindow window) {
		List<MTab> clientTabs = loadWindowTabs(window);
		MWindow clientWindow = window.getDuplicated();
		List<MWindowCustom> customWindowList = getClientWindowList(window.getAD_Window_ID());
		if(customWindowList != null
				&& customWindowList.size() > 0) {
			for(MWindowCustom customWindow : customWindowList) {
				mergeWindow(clientWindow, customWindow);
				//	Merge parameters
				clientTabs = mergeTabs(clientTabs, customWindow.getTabs(), customWindow.getHierarchyType().equals(MWindowCustom.HIERARCHYTYPE_Overwrite), CLIENT);
			}
			//	Save client
			windowCache.put(getClientKey(window.getAD_Window_ID()), clientWindow);
			tabCache.put(getClientKey(window.getAD_Window_ID()), clientTabs);
		} else {
			loadTranslation(window);
		}
		//	return
		return clientWindow;
	}
	
	/**
	 * Get client process from dictionary process
	 * @param process
	 * @return
	 */
	private MProcess getClientProcess(MProcess process) {
		List<MProcessPara> clientParameters = loadProcessParameters(process);
		MProcess clientProcess = process.getDuplicated();
		List<MProcessCustom> customProcessList = getClientProcessList(process.getAD_Process_ID());
		if(customProcessList != null
				&& customProcessList.size() > 0) {
			for(MProcessCustom customProcess : customProcessList) {
				mergeProcess(clientProcess, customProcess);
				//	Merge parameters
				clientParameters = mergeParameters(clientParameters, customProcess.getParameters(), customProcess.getHierarchyType().equals(MProcessCustom.HIERARCHYTYPE_Overwrite));
			}
			//	Save client
			processCache.put(getClientKey(process.getAD_Process_ID()), clientProcess);
			processParameterCache.put(getClientKey(process.getAD_Process_ID()), clientParameters);
		}
		//	return
		return clientProcess;
	}
	
	/**
	 * Get client process from dictionary process
	 * @param browse
	 * @return
	 */
	private MBrowse getClientBrowse(MBrowse browse) {
		List<MBrowseField> clientBrowseFields = loadBrowseFields(browse);
		MBrowse clientBrowse = browse.getDuplicated();
		List<MBrowseCustom> customBrowseList = getClientBrowseList(browse.getAD_Browse_ID());
		if(customBrowseList != null
				&& customBrowseList.size() > 0) {
			for(MBrowseCustom customBrowse : customBrowseList) {
				mergeBrowse(clientBrowse, customBrowse);
				//	Merge Fields
				clientBrowseFields = mergeBrowseFields(clientBrowseFields, customBrowse.getFields(), customBrowse.getHierarchyType().equals(MBrowseCustom.HIERARCHYTYPE_Overwrite));
			}
			//	Save client
			browseCache.put(getClientKey(browse.getAD_Browse_ID()), clientBrowse);
			browseFieldCache.put(getClientKey(browse.getAD_Browse_ID()), clientBrowseFields);
		}
		//	return
		return clientBrowse;
	}
	
	/**
	 * Get / Merge browse for role
	 * @param browse
	 * @return
	 */
	private MBrowse getRoleBrowse(MBrowse browse) {
		MBrowse roleBrowse = browse.getDuplicated();
		List<MBrowseField> roleFields = browseFieldCache.get(getRoleKey(browse.getAD_Browse_ID()));
		List<MBrowseCustom> customBrowseList = getRoleBrowseList(browse.getAD_Browse_ID());
		if(customBrowseList != null
				&& customBrowseList.size() > 0) {
			for(MBrowseCustom customBrowse : customBrowseList) {
				mergeBrowse(roleBrowse, customBrowse);
				//	Merge Fields
				roleFields = mergeBrowseFields(roleFields, customBrowse.getFields(), customBrowse.getHierarchyType().equals(MBrowseCustom.HIERARCHYTYPE_Overwrite));
			}
			//	Save role
			browseCache.put(getRoleKey(browse.getAD_Browse_ID()), roleBrowse);
			browseFieldCache.put(getRoleKey(browse.getAD_Browse_ID()), roleFields);
		}
		//	return
		return roleBrowse;
	}
	
	/**
	 * Get / Merge process for user
	 * @param browse
	 * @return
	 */
	private MBrowse getUserBrowse(MBrowse browse) {
		MBrowse userFields = browse.getDuplicated();
		List<MBrowseField> userBrowseFields = browseFieldCache.get(getUserKey(browse.getAD_Browse_ID()));
		List<MBrowseCustom> customBrowseList = getUserBrowseList(browse.getAD_Browse_ID());
		if(customBrowseList != null
				&& customBrowseList.size() > 0) {
			for(MBrowseCustom customBrowse : customBrowseList) {
				mergeBrowse(userFields, customBrowse);
				//	Merge parameters
				userBrowseFields = mergeBrowseFields(userBrowseFields, customBrowse.getFields(), customBrowse.getHierarchyType().equals(MBrowseCustom.HIERARCHYTYPE_Overwrite));
			}
			//	Save user
			browseCache.put(getUserKey(browse.getAD_Browse_ID()), userFields);
			browseFieldCache.put(getUserKey(browse.getAD_Browse_ID()), userBrowseFields);
		}
		//	return
		return userFields;
	}
	
	/**
	 * Get / Merge process for role
	 * @param process
	 * @return
	 */
	private MProcess getRoleProcess(MProcess process) {
		MProcess roleProcess = process.getDuplicated();
		List<MProcessPara> roleParameters = processParameterCache.get(getRoleKey(process.getAD_Process_ID()));
		List<MProcessCustom> customProcessList = getRoleProcessList(process.getAD_Process_ID());
		if(customProcessList != null
				&& customProcessList.size() > 0) {
			for(MProcessCustom customProcess : customProcessList) {
				mergeProcess(roleProcess, customProcess);
				//	Merge parameters
				roleParameters = mergeParameters(roleParameters, customProcess.getParameters(), customProcess.getHierarchyType().equals(MProcessCustom.HIERARCHYTYPE_Overwrite));
			}
			//	Save role
			processCache.put(getRoleKey(process.getAD_Process_ID()), roleProcess);
			processParameterCache.put(getRoleKey(process.getAD_Process_ID()), roleParameters);
		}
		//	return
		return roleProcess;
	}
	
	/**
	 * Get / Merge window for role
	 * @param window
	 * @return
	 */
	private MWindow getRoleWindow(MWindow window) {
		MWindow roleWindow = window.getDuplicated();
		List<MTab> roleTabs = tabCache.get(getClientKey(window.getAD_Window_ID()));
		List<MWindowCustom> customWindowList = getRoleWindowList(window.getAD_Window_ID());
		if(customWindowList != null
				&& customWindowList.size() > 0) {
			for(MWindowCustom customWindow : customWindowList) {
				mergeWindow(roleWindow, customWindow);
				//	Merge parameters
				roleTabs = mergeTabs(roleTabs, customWindow.getTabs(), customWindow.getHierarchyType().equals(MProcessCustom.HIERARCHYTYPE_Overwrite), ROLE);
			}
			//	Save role
			windowCache.put(getRoleKey(window.getAD_Window_ID()), roleWindow);
			tabCache.put(getRoleKey(window.getAD_Window_ID()), roleTabs);
		}
		//	return
		return roleWindow;
	}
	
	/**
	 * Get / Merge process for user
	 * @param process
	 * @return
	 */
	private MProcess getUserProcess(MProcess process) {
		MProcess userProcess = process.getDuplicated();
		List<MProcessPara> userParameters = processParameterCache.get(getUserKey(process.getAD_Process_ID()));
		List<MProcessCustom> customProcessList = getUserProcessList(process.getAD_Process_ID());
		if(customProcessList != null
				&& customProcessList.size() > 0) {
			for(MProcessCustom customProcess : customProcessList) {
				mergeProcess(userProcess, customProcess);
				//	Merge parameters
				userParameters = mergeParameters(userParameters, customProcess.getParameters(), customProcess.getHierarchyType().equals(MProcessCustom.HIERARCHYTYPE_Overwrite));
			}
			//	Save user
			processCache.put(getUserKey(process.getAD_Process_ID()), userProcess);
			processParameterCache.put(getUserKey(process.getAD_Process_ID()), userParameters);
		}
		//	return
		return userProcess;
	}
	
	/**
	 * Get / Merge window for user
	 * @param window
	 * @return
	 */
	private MWindow getUserWindow(MWindow window) {
		MWindow userWindow = window.getDuplicated();
		List<MTab> userTabs = tabCache.get(getRoleKey(window.getAD_Window_ID()));
		List<MWindowCustom> customProcessList = getUserWindowList(window.getAD_Window_ID());
		if(customProcessList != null
				&& customProcessList.size() > 0) {
			for(MWindowCustom customProcess : customProcessList) {
				mergeWindow(userWindow, customProcess);
				//	Merge parameters
				userTabs = mergeTabs(userTabs, customProcess.getTabs(), customProcess.getHierarchyType().equals(MProcessCustom.HIERARCHYTYPE_Overwrite), USER);
			}
			//	Save user
			windowCache.put(getUserKey(window.getAD_Window_ID()), userWindow);
			tabCache.put(getUserKey(window.getAD_Window_ID()), userTabs);
		}
		//	return
		return userWindow;
	}
	
	/**
	 * Get Client Key from object Id
	 * @param objectIdmergePO
	 * @return
	 */
	private String getClientKey(int objectId) {
		return objectId + "|C|" + clientId + "|" + language;
	}
	
	/**
	 * Get Role Key from object Id
	 * @param objectId
	 * @return
	 */
	private String getRoleKey(int objectId) {
		return objectId + "|R|" + roleId + "|" + language;
	}
	
	/**
	 * Get User Key from object Id
	 * @param objectId
	 * @return
	 */
	private String getUserKey(int objectId) {
		return objectId + "|U|" + userId + "|" + language;
	}
	
	/**
	 * Get dictionary Key
	 * @param objectId
	 * @return
	 */
	private String getDictionaryKey(int objectId) {
		return String.valueOf(objectId) + "|" + language;
	}
	
	/**
	 * Merge Process with custom process
	 * @param process
	 * @param customProcess
	 */
	private void mergeProcess(MProcess process, MProcessCustom customProcess) {
		//	Name
		if(!Util.isEmpty(customProcess.getName())) {
			process.setName(customProcess.getName());
		}
		//	Description
		if(!Util.isEmpty(customProcess.getDescription())) {
			process.setDescription(customProcess.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customProcess.getHelp())) {
			process.setHelp(customProcess.getHelp());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customProcess.get_Translation(I_AD_Process.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = process.get_Translation(I_AD_Process.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				process.setName(value);
			}
			//	Description
			value = customProcess.get_Translation(I_AD_Process.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = process.get_Translation(I_AD_Process.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				process.setDescription(value);
			}
			//	Help
			value = customProcess.get_Translation(I_AD_Process.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = process.get_Translation(I_AD_Process.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				process.setHelp(value);
			}
		}
		//	
		//	Show Help
		if(!Util.isEmpty(customProcess.getShowHelp())) {
			process.setShowHelp(customProcess.getShowHelp());
		}
		//	Report View
		if(customProcess.getAD_ReportView_ID() > 0) {
			process.setAD_ReportView_ID(customProcess.getAD_ReportView_ID());
		}
		//	Print Format
		if(customProcess.getAD_PrintFormat_ID() > 0) {
			process.setAD_PrintFormat_ID(customProcess.getAD_PrintFormat_ID());
		}
		//	Direct Print
		if(!Util.isEmpty(customProcess.getIsDirectPrint())) {
			process.setIsDirectPrint(customProcess.getIsDirectPrint().equals("Y"));
		}
		//	Smart Browse
		if(customProcess.getAD_Browse_ID() > 0) {
			process.setAD_Browse_ID(customProcess.getAD_Browse_ID());
		}
		//	Form
		if(customProcess.getAD_Form_ID() > 0) {
			process.setAD_Form_ID(customProcess.getAD_Form_ID());
		}
		//	Workflow
		if(customProcess.getAD_Workflow_ID() > 0) {
			process.setAD_Workflow_ID(customProcess.getAD_Workflow_ID());
		}
	}
	
	/**
	 * Merge Browse with custom browse
	 * @param browse
	 * @param customBrowse
	 */
	private void mergeBrowse(MBrowse browse, MBrowseCustom customBrowse) {
		//	Name
		if(!Util.isEmpty(customBrowse.getName())) {
			browse.setName(customBrowse.getName());
		}
		//	Description
		if(!Util.isEmpty(customBrowse.getDescription())) {
			browse.setDescription(customBrowse.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customBrowse.getHelp())) {
			browse.setHelp(customBrowse.getHelp());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customBrowse.get_Translation(I_AD_Browse.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = browse.get_Translation(I_AD_Browse.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				browse.setName(value);
			}
			//	Description
			value = customBrowse.get_Translation(I_AD_Browse.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = browse.get_Translation(I_AD_Browse.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				browse.setDescription(value);
			}
			//	Help
			value = customBrowse.get_Translation(I_AD_Browse.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = browse.get_Translation(I_AD_Browse.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				browse.setHelp(value);
			}
		}
		//	Process
		if(customBrowse.getAD_Process_ID() > 0) {
			browse.setAD_Process_ID(customBrowse.getAD_Process_ID());
		}		
		//	Where Clause
		if(!Util.isEmpty(customBrowse.getWhereClause())) {
			browse.setWhereClause(customBrowse.getWhereClause());
		}
		//	Window
		if(customBrowse.getAD_Window_ID() > 0) {
			browse.setAD_Window_ID(customBrowse.getAD_Window_ID());
		}
		//	Table
		if(customBrowse.getAD_Table_ID() > 0) {
			browse.setAD_Table_ID(customBrowse.getAD_Table_ID());
		}
		//	Updateable
		if(!Util.isEmpty(customBrowse.getIsUpdateable())) {
			browse.setIsUpdateable(customBrowse.getIsUpdateable().equals("Y"));
		}
		//	Deleteable
		if(!Util.isEmpty(customBrowse.getIsDeleteable())) {
			browse.setIsDeleteable(customBrowse.getIsDeleteable().equals("Y"));
		}
		//	Is Selected by Default
		if(!Util.isEmpty(customBrowse.getIsSelectedByDefault())) {
			browse.setIsSelectedByDefault(customBrowse.getIsSelectedByDefault().equals("Y"));
		}
		//	Is Collapsible by Default
		if(!Util.isEmpty(customBrowse.getIsCollapsibleByDefault())) {
			browse.setIsCollapsibleByDefault(customBrowse.getIsCollapsibleByDefault().equals("Y"));
		}
		//	Is Executed by Default
		if(!Util.isEmpty(customBrowse.getIsExecutedQueryByDefault())) {
			browse.setIsExecutedQueryByDefault(customBrowse.getIsExecutedQueryByDefault().equals("Y"));
		}
		//	Show Total
		if(!Util.isEmpty(customBrowse.getIsShowTotal())) {
			browse.setIsShowTotal(customBrowse.getIsShowTotal().equals("Y"));
		}
	}
	
	/**
	 * Merge Window with custom window
	 * @param window
	 * @param customWindow
	 */
	private void mergeWindow(MWindow window, MWindowCustom customWindow) {
		//	Name
		if(!Util.isEmpty(customWindow.getName())) {
			window.setName(customWindow.getName());
		}
		//	Description
		if(!Util.isEmpty(customWindow.getDescription())) {
			window.setDescription(customWindow.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customWindow.getHelp())) {
			window.setHelp(customWindow.getHelp());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customWindow.get_Translation(I_AD_Window.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = window.get_Translation(I_AD_Window.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				window.setName(value);
			}
			//	Description
			value = customWindow.get_Translation(I_AD_Window.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = window.get_Translation(I_AD_Window.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				window.setDescription(value);
			}
			//	Help
			value = customWindow.get_Translation(I_AD_Window.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = window.get_Translation(I_AD_Window.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				window.setHelp(value);
			}
		}
		//	Is Read Only
		if(!Util.isEmpty(customWindow.getWindowType())) {
			window.setWindowType(customWindow.getWindowType());
		}
		//	Context Info
		if(customWindow.getAD_ContextInfo_ID() > 0) {
			window.setAD_ContextInfo_ID(customWindow.getAD_ContextInfo_ID());
		}
	}
	
	/**
	 * Load translation for tab
	 * @param entity
	 */
	private void loadTranslation(PO entity) {
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = entity.get_Translation(I_AD_Tab.COLUMNNAME_Name, language);
			if(!Util.isEmpty(value)) {
				entity.set_ValueOfColumn(I_AD_Tab.COLUMNNAME_Name, value);
			}
			//	Description
			value = entity.get_Translation(I_AD_Tab.COLUMNNAME_Description, language);
			if(!Util.isEmpty(value)) {
				entity.set_ValueOfColumn(I_AD_Tab.COLUMNNAME_Description, value);
			}
			//	Help
			value = entity.get_Translation(I_AD_Tab.COLUMNNAME_Help, language);
			if(!Util.isEmpty(value)) {
				entity.set_ValueOfColumn(I_AD_Tab.COLUMNNAME_Help, value);
			}
		}
	}
	
	/**
	 * Merge Tab with custom window
	 * @param tab
	 * @param customTab
	 * @param overwrite
	 * @param fields
	 * @param level
	 */
	private void mergeTab(MTab tab, MTabCustom customTab, boolean overwrite, List<MField> fields, int level) {
		//	Name
		if(!Util.isEmpty(customTab.getName())) {
			tab.setName(customTab.getName());
		}
		//	Description
		if(!Util.isEmpty(customTab.getDescription())) {
			tab.setDescription(customTab.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customTab.getHelp())) {
			tab.setHelp(customTab.getHelp());
		}
		//	Commit Warning
		if(!Util.isEmpty(customTab.getCommitWarning())) {
			tab.setCommitWarning(customTab.getCommitWarning());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customTab.get_Translation(I_AD_Tab.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = tab.get_Translation(I_AD_Tab.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				tab.setName(value);
			}
			//	Description
			value = customTab.get_Translation(I_AD_Tab.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = tab.get_Translation(I_AD_Tab.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				tab.setDescription(value);
			}
			//	Help
			value = customTab.get_Translation(I_AD_Tab.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = tab.get_Translation(I_AD_Tab.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				tab.setHelp(value);
			}
			//	Commit Warning
			value = customTab.get_Translation(I_AD_Tab.COLUMNNAME_CommitWarning, language);
			if(Util.isEmpty(value)) {
				value = tab.get_Translation(I_AD_Tab.COLUMNNAME_CommitWarning, language);
			}
			if(!Util.isEmpty(value)) {
				tab.setCommitWarning(value);
			}
		}
		if(overwrite) {
			tab.setIsActive(customTab.isActive());
			tab.setSeqNo(customTab.getSeqNo());
			//	Tab Level
			tab.setTabLevel(customTab.getTabLevel());
		} else {
			if(customTab.isActive()) {
				tab.setIsActive(customTab.isActive());
			}
			if(customTab.getSeqNo() > 0) {
				tab.setSeqNo(customTab.getSeqNo());
			}
			//	Tab Level
			if(customTab.getTabLevel() > 0) {
				tab.setTabLevel(customTab.getTabLevel());	
			}
		}
		//	Single-Row
		if(!Util.isEmpty(customTab.getIsSingleRow())) {
			tab.setIsSingleRow(customTab.getIsSingleRow().equals("Y"));
		}
		//	Process
		if(customTab.getAD_Process_ID() > 0) {
			tab.setAD_Process_ID(customTab.getAD_Process_ID());
		}		
		//	Read Only
		if(!Util.isEmpty(customTab.getIsReadOnly())) {
			tab.setIsReadOnly(customTab.getIsReadOnly().equals("Y"));
		}
		//	Insert Records
		if(!Util.isEmpty(customTab.getIsInsertRecord())) {
			tab.setIsInsertRecord(customTab.getIsInsertRecord().equals("Y"));
		}
		//	Context Info
		if(customTab.getAD_ContextInfo_ID() > 0) {
			tab.setAD_ContextInfo_ID(customTab.getAD_ContextInfo_ID());
		}
		//	Image
		if(customTab.getAD_Image_ID() > 0) {
			tab.setAD_Image_ID(customTab.getAD_Image_ID());
		}
		//	Display Logic
		if(!Util.isEmpty(customTab.getDisplayLogic())) {
			tab.setDisplayLogic(customTab.getDisplayLogic());
		}
		//	Read Only Logic
		if(!Util.isEmpty(customTab.getReadOnlyLogic())) {
			tab.setReadOnlyLogic(customTab.getReadOnlyLogic());
		}
		//	Where Clause
		if(!Util.isEmpty(customTab.getWhereClause())) {
			tab.setWhereClause(customTab.getWhereClause());
		}
		//	Order By Clause
		if(!Util.isEmpty(customTab.getOrderByClause())) {
			tab.setOrderByClause(customTab.getOrderByClause());
		}
		//	
		fields = mergeFields(fields, customTab.getFields(), overwrite);
		//	Put here
		if(level == CLIENT) {
			fieldCache.put(getClientKey(tab.getAD_Tab_ID()), fields);
		} else if(level == ROLE) {
			fieldCache.put(getRoleKey(tab.getAD_Tab_ID()), fields);
		} else if(level == USER) {
			fieldCache.put(getUserKey(tab.getAD_Tab_ID()), fields);
		}
	}
	
	/**
	 * Merge parameters with custom parameters
	 * @param fields
	 * @param customFields
	 * @param overwrite
	 */
	private List<MField> mergeFields(List<MField> fields, List<MFieldCustom> customFields, boolean overwrite) {
		List<MField> mergedFields = null;
		if(overwrite) {
			mergedFields = new ArrayList<>();
			for(MField field : fields) {
				MField fieldToAdd = field.getDuplicated();
				MColumn column = MColumn.get(field.getCtx(), field.getAD_Column_ID());
				if(!column.isKey()
						&& !column.isParent()) {
					fieldToAdd.setIsActive(false);
					fieldToAdd.setIsDisplayed(false);
					fieldToAdd.setIsDisplayedGrid(false);
				}
				mergedFields.add(fieldToAdd);
			}
		} else {
			mergedFields = new ArrayList<>(fields);
		}
		//	merge all parameters
		for(int index = 0; index < mergedFields.size(); index++) {
			MField field = mergedFields.get(index);
			customFields.stream()
			.filter(customField -> customField.getAD_Field_ID() == field.getAD_Field_ID())
			.forEach(customField -> {
				mergeField(field, customField, overwrite);
			});
			mergedFields.set(index, field);
		}
		//
		mergedFields.sort(Comparator.comparing(MField::getSeqNo));
		return mergedFields;
	}
	
	/**
	 * Merge Process Parameter with custom process
	 * @param processParameter
	 * @param customProcessParameter
	 * @param overwrite
	 */
	private void mergeProcessParameter(MProcessPara processParameter, MProcessParaCustom customProcessParameter, boolean overwrite) {
		//	Name
		if(!Util.isEmpty(customProcessParameter.getName())) {
			processParameter.setName(customProcessParameter.getName());
		}
		//	Description
		if(!Util.isEmpty(customProcessParameter.getDescription())) {
			processParameter.setDescription(customProcessParameter.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customProcessParameter.getHelp())) {
			processParameter.setHelp(customProcessParameter.getHelp());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customProcessParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = processParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				processParameter.setName(value);
			}
			//	Description
			value = customProcessParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = processParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				processParameter.setDescription(value);
			}
			//	Help
			value = customProcessParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = processParameter.get_Translation(I_AD_Process_Para.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				processParameter.setHelp(value);
			}
		}
		//	Reference
		if(customProcessParameter.getAD_Reference_ID() > 0) {
			processParameter.setAD_Reference_ID(customProcessParameter.getAD_Reference_ID());
		}
		//	Reference Key
		if(customProcessParameter.getAD_Reference_Value_ID() > 0) {
			processParameter.setAD_Reference_Value_ID(customProcessParameter.getAD_Reference_Value_ID());
		}
		//	Mandatory
		if(!Util.isEmpty(customProcessParameter.getIsMandatory())) {
			processParameter.setIsMandatory(customProcessParameter.getIsMandatory().equals("Y"));
		}
		//	Validation Rule
		if(customProcessParameter.getAD_Val_Rule_ID() > 0) {
			processParameter.setAD_Val_Rule_ID(customProcessParameter.getAD_Val_Rule_ID());
		}
		//	Merge or overwrite
		if(overwrite) {
			//	Sequence
			processParameter.setSeqNo(customProcessParameter.getSeqNo());
			//	Active
			processParameter.setIsActive(customProcessParameter.isActive());
		} else {
			//	Sequence
			if(customProcessParameter.getSeqNo() > 0) {
				processParameter.setSeqNo(customProcessParameter.getSeqNo());
			}
		}
		//	Active
		processParameter.setIsActive(customProcessParameter.isActive());
		//	Default Logic
		if(!Util.isEmpty(customProcessParameter.getDefaultValue())) {
			processParameter.setDefaultValue(customProcessParameter.getDefaultValue());
		}
		//	Default value to
		if(!Util.isEmpty(customProcessParameter.getDefaultValue2())) {
			processParameter.setDefaultValue2(customProcessParameter.getDefaultValue2());
		}
		//	Range
		if(!Util.isEmpty(customProcessParameter.getIsRange())) {
			processParameter.setIsRange(customProcessParameter.getIsRange().equals("Y"));
		}
		//	Display Logic
		if(!Util.isEmpty(customProcessParameter.getDisplayLogic())) {
			processParameter.setDisplayLogic(customProcessParameter.getDisplayLogic());
		}
		//	Read Only Logic
		if(!Util.isEmpty(customProcessParameter.getReadOnlyLogic())) {
			processParameter.setReadOnlyLogic(customProcessParameter.getReadOnlyLogic());
		}
		//	Information Only
		if(!Util.isEmpty(customProcessParameter.getIsInfoOnly())) {
			processParameter.setIsInfoOnly(customProcessParameter.getIsInfoOnly().equals("Y"));
		}
		//	Value Format
		if(!Util.isEmpty(customProcessParameter.getVFormat())) {
			processParameter.setVFormat(customProcessParameter.getVFormat());
		}
		//	Min Value
		if(!Util.isEmpty(customProcessParameter.getValueMin())) {
			processParameter.setValueMin(customProcessParameter.getValueMin());
		}
		//	Max Value
		if(!Util.isEmpty(customProcessParameter.getValueMax())) {
			processParameter.setValueMax(customProcessParameter.getValueMax());
		}
	}
	
	/**
	 * Merge Browse Field with custom browse
	 * @param browseField
	 * @param customBrowseField
	 * @param overwrite
	 */
	private void mergeBrowseField(MBrowseField browseField, MBrowseFieldCustom customBrowseField, boolean overwrite) {
		//	Name
		if(!Util.isEmpty(customBrowseField.getName())) {
			browseField.setName(customBrowseField.getName());
		}
		//	Description
		if(!Util.isEmpty(customBrowseField.getDescription())) {
			browseField.setDescription(customBrowseField.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customBrowseField.getHelp())) {
			browseField.setHelp(customBrowseField.getHelp());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customBrowseField.get_Translation(I_AD_Browse_Field.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = browseField.get_Translation(I_AD_Browse_Field.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				browseField.setName(value);
			}
			//	Description
			value = customBrowseField.get_Translation(I_AD_Browse_Field.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = browseField.get_Translation(I_AD_Browse_Field.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				browseField.setDescription(value);
			}
			//	Help
			value = customBrowseField.get_Translation(I_AD_Browse_Field.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = browseField.get_Translation(I_AD_Browse_Field.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				browseField.setHelp(value);
			}
		}
		//	Reference
		if(customBrowseField.getAD_Reference_ID() > 0) {
			browseField.setAD_Reference_ID(customBrowseField.getAD_Reference_ID());
		}
		//	Reference Key
		if(customBrowseField.getAD_Reference_Value_ID() > 0) {
			browseField.setAD_Reference_Value_ID(customBrowseField.getAD_Reference_Value_ID());
		}
		//	Mandatory
		if(!Util.isEmpty(customBrowseField.getIsMandatory())) {
			browseField.setIsMandatory(customBrowseField.getIsMandatory().equals("Y"));
		}
		//	Validation Rule
		if(customBrowseField.getAD_Val_Rule_ID() > 0) {
			browseField.setAD_Val_Rule_ID(customBrowseField.getAD_Val_Rule_ID());
		}
		//	Merge or overwrite
		if(overwrite) {
			//	Sequence
			browseField.setSeqNo(customBrowseField.getSeqNo());
			browseField.setSeqNoGrid(customBrowseField.getSeqNoGrid());
			browseField.setIsActive(customBrowseField.isActive());
			browseField.setIsDisplayed(customBrowseField.isDisplayed());
			browseField.setIsQueryCriteria(customBrowseField.isQueryCriteria());
			browseField.setIsOrderBy(customBrowseField.isOrderBy());
			browseField.setSortNo(customBrowseField.getSortNo());
		} else {
			//	Sequence
			if(customBrowseField.getSeqNo() > 0) {
				browseField.setSeqNo(customBrowseField.getSeqNo());
				browseField.setSeqNoGrid(customBrowseField.getSeqNoGrid());
			}
			if(customBrowseField.getSeqNoGrid() > 0) {
				browseField.setSeqNoGrid(customBrowseField.getSeqNoGrid());
			}
			if(customBrowseField.isActive()) {
				browseField.setIsActive(customBrowseField.isActive());
			}
			if(customBrowseField.isQueryCriteria()) {
				browseField.setIsQueryCriteria(customBrowseField.isQueryCriteria());
			}
		}
		//	Default Logic
		if(!Util.isEmpty(customBrowseField.getDefaultValue())) {
			browseField.setDefaultValue(customBrowseField.getDefaultValue());
		}
		//	Default value to
		if(!Util.isEmpty(customBrowseField.getDefaultValue2())) {
			browseField.setDefaultValue2(customBrowseField.getDefaultValue2());
		}
		//	Range
		if(!Util.isEmpty(customBrowseField.getIsRange())) {
			browseField.setIsRange(customBrowseField.getIsRange().equals("Y"));
		}
		//	Display Logic
		if(!Util.isEmpty(customBrowseField.getDisplayLogic())) {
			browseField.setDisplayLogic(customBrowseField.getDisplayLogic());
		}
		//	Read Only Logic
		if(!Util.isEmpty(customBrowseField.getReadOnlyLogic())) {
			browseField.setReadOnlyLogic(customBrowseField.getReadOnlyLogic());
		}
		//	Information Only
		if(!Util.isEmpty(customBrowseField.getIsInfoOnly())) {
			browseField.setIsInfoOnly(customBrowseField.getIsInfoOnly().equals("Y"));
		}
		//	Value Format
		if(!Util.isEmpty(customBrowseField.getVFormat())) {
			browseField.setVFormat(customBrowseField.getVFormat());
		}
		//	Min Value
		if(!Util.isEmpty(customBrowseField.getValueMin())) {
			browseField.setValueMin(customBrowseField.getValueMin());
		}
		//	Max Value
		if(!Util.isEmpty(customBrowseField.getValueMax())) {
			browseField.setValueMax(customBrowseField.getValueMax());
		}
		//	Axis Column
		if(customBrowseField.getAxis_Column_ID() > 0) {
			browseField.setAxis_Column_ID(customBrowseField.getAxis_Column_ID());
		}
		//	Parent Axis Column
		if(customBrowseField.getAxis_Parent_Column_ID() > 0) {
			browseField.setAxis_Parent_Column_ID(customBrowseField.getAxis_Parent_Column_ID());
		}
	}
	
	/**
	 * Merge field with custom process
	 * @param field
	 * @param customField
	 * @param overwrite
	 */
	private void mergeField(MField field, MFieldCustom customField, boolean overwrite) {
		//	Name
		if(!Util.isEmpty(customField.getName())) {
			field.setName(customField.getName());
		}
		//	Description
		if(!Util.isEmpty(customField.getDescription())) {
			field.setDescription(customField.getDescription());
		}
		//	Help
		if(!Util.isEmpty(customField.getHelp())) {
			field.setHelp(customField.getHelp());
		}
		//	Translation
		if(!Language.isBaseLanguage(language)) {
			//	Name
			String value = customField.get_Translation(I_AD_Field.COLUMNNAME_Name, language);
			if(Util.isEmpty(value)) {
				value = field.get_Translation(I_AD_Field.COLUMNNAME_Name, language);
			}
			if(!Util.isEmpty(value)) {
				field.setName(value);
			}
			//	Description
			value = customField.get_Translation(I_AD_Field.COLUMNNAME_Description, language);
			if(Util.isEmpty(value)) {
				value = field.get_Translation(I_AD_Field.COLUMNNAME_Description, language);
			}
			if(!Util.isEmpty(value)) {
				field.setDescription(value);
			}
			//	Help
			value = customField.get_Translation(I_AD_Field.COLUMNNAME_Help, language);
			if(Util.isEmpty(value)) {
				value = field.get_Translation(I_AD_Field.COLUMNNAME_Help, language);
			}
			if(!Util.isEmpty(value)) {
				field.setHelp(value);
			}
		}
		//	Sequence
		if(overwrite) {
			field.setSeqNo(customField.getSeqNo());
			//	Grid Sequence
			field.setSeqNoGrid(customField.getSeqNoGrid());
			//	Active
			field.setIsActive(customField.isActive());
		} else {
			if(customField.getSeqNo() > 0) {
				field.setSeqNo(customField.getSeqNo());
			}
			if(customField.getSeqNoGrid() > 0) {
				field.setSeqNoGrid(customField.getSeqNoGrid());
			}
			if(customField.isActive()) {
				field.setIsActive(customField.isActive());
			}
		}
		//	Is Displayed
		field.setIsDisplayed(customField.isDisplayed());
		//	Is Embedded
		if(!Util.isEmpty(customField.getIsEmbedded())) {
			field.setIsEmbedded(customField.getIsEmbedded().equals("Y"));
		}
		//	Field Group
		if(customField.getAD_FieldGroup_ID() > 0) {
			field.setAD_FieldGroup_ID(customField.getAD_FieldGroup_ID());
		}
		//	Displayed in grid
		field.setIsDisplayedGrid(customField.isDisplayedGrid());
		//	Read Only
		if(!Util.isEmpty(customField.getIsReadOnly())) {
			field.setIsReadOnly(customField.getIsReadOnly().equals("Y"));
		}
		//	Updateable
		if(!Util.isEmpty(customField.getIsAllowCopy())) {
			field.setIsAllowCopy(customField.getIsAllowCopy().equals("Y"));
		}
		//	Display Logic
		if(!Util.isEmpty(customField.getDisplayLogic())) {
			field.setDisplayLogic(customField.getDisplayLogic());
		}
		//	Encrypted
		if(!Util.isEmpty(customField.getIsEncrypted())) {
			field.setIsEncrypted(customField.getIsEncrypted().equals("Y"));
		}
		//	Same Line
		if(!Util.isEmpty(customField.getIsSameLine())) {
			field.setIsSameLine(customField.getIsSameLine().equals("Y"));
		}		
		//	Record Sort No
		if(customField.getSortNo() > 0) {
			field.setSortNo(new BigDecimal(customField.getSortNo()));
		}
		//	Obscure
		if(!Util.isEmpty(customField.getObscureType())) {
			field.setObscureType(customField.getObscureType());
		}
		//	Heading
		if(!Util.isEmpty(customField.getIsHeading())) {
			field.setIsHeading(customField.getIsHeading().equals("Y"));
		}
		//	Field Only
		if(!Util.isEmpty(customField.getIsFieldOnly())) {
			field.setIsFieldOnly(customField.getIsFieldOnly().equals("Y"));
		}
		//	Field Definition
		if(customField.getAD_FieldDefinition_ID() > 0) {
			field.setAD_FieldDefinition_ID(customField.getAD_FieldDefinition_ID());
		}
		//	Reference
		if(customField.getAD_Reference_ID() > 0) {
			field.setAD_Reference_ID(customField.getAD_Reference_ID());
		}
		//	Reference Key
		if(customField.getAD_Reference_Value_ID() > 0) {
			field.setAD_Reference_Value_ID(customField.getAD_Reference_Value_ID());
		}
		//	Validation Rule
		if(customField.getAD_Val_Rule_ID() > 0) {
			field.setAD_Val_Rule_ID(customField.getAD_Val_Rule_ID());
		}		
		//	Mandatory
		if(!Util.isEmpty(customField.getIsMandatory())) {
			field.setIsMandatory(customField.getIsMandatory());
		}
		//	Image
		if(customField.getAD_Image_ID() > 0) {
			field.setAD_Image_ID(customField.getAD_Image_ID());
		}
		//	Default Logic
		if(!Util.isEmpty(customField.getDefaultValue())) {
			field.setDefaultValue(customField.getDefaultValue());
		}
		//	Quick Entry
		if(!Util.isEmpty(customField.getIsQuickEntry())) {
			field.setIsQuickEntry(customField.getIsQuickEntry().equals("Y"));
		}
		//	Default Logic
		if(!Util.isEmpty(customField.getInfoFactoryClass())) {
			field.setInfoFactoryClass(customField.getInfoFactoryClass());
		}
		//	Context Info
		if(customField.getAD_ContextInfo_ID() > 0) {
			field.setAD_ContextInfo_ID(customField.getAD_ContextInfo_ID());
		}
	}
	
	/**
	 * Test It
	 * @param args
	 */
	public static void main(String args[]) {
		Adempiere.startup(false);
		int clientId = 11;
		int roleId = 102;
		int userId = 100;
		String language = "es_VE";
		ASPUtil aspUtil = ASPUtil.getInstance(Env.getCtx(), clientId, roleId, userId, language);
		//	
		MProcess process = aspUtil.getProcess(54015);
		List<MProcessPara> processParameterList = aspUtil.getProcessParameters(54015);
		//	
		
		System.err.println("Name " + process.getName());
		for(MProcessPara parameter : processParameterList) {
			System.err.println(parameter.getColumnName() + ", " + parameter.isActive() + ", " + parameter.isRange());
		}
		
		MWindow window = aspUtil.getWindow(53553);
		List<MTab> tabs = aspUtil.getWindowTabs(53553);
		System.err.println("Window == " + window.getName());
		for(MTab tab : tabs) {
			System.err.println("Tab == " + tab.getName());
			List<MField> fields = aspUtil.getWindowFields(tab.getAD_Tab_ID());
			for(MField field : fields) {
				System.err.println("Field == " + field.getName() + " Displayed: " + field.isDisplayed() + " Active: " + field.isActive());
			}
		}
	}
}
