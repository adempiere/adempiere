/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import javax.servlet.ServletRequest;

import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeManager;
import org.compiere.acct.Doc;
import org.compiere.model.GridWindowVO;
import org.compiere.model.Lookup;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MLookup;
import org.compiere.model.MMenu;
import org.compiere.model.MQuery;
import org.compiere.model.MSession;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.CacheMgt;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Language;
import org.zkoss.web.servlet.Servlets;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 *  ZK Application Environment and utilities
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: AEnv.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *
 *  Colin Rooney (croo) & kstan_79 RFE#1670185
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1176">
 * 		@see FR [ 1176 ] Look and feel style to ADempiere 390 - Change icons on Work Flow</a>
 */
public final class AEnv
{
	public static final String LOCALE = "#Locale";

	/**
	 *  Show in the center of the screen.
	 *  (pack, set location and set visibility)
	 * 	@param window Window to position
	 */
	public static void showCenterScreen(Window window)
	{
		SessionManager.getAppDesktop().showWindow(window, "center");
	}   //  showCenterScreen

	/**
	 *	Position window in center of the screen
	 * 	@param window Window to position
	 */
	public static void positionCenterScreen(Window window)
	{
		showCenterScreen(window);
	}	//	positionCenterScreen

	/**
	 *  Show in the center of the screen.
	 *  (pack, set location and set visibility)
	 * 	@param window Window to position
	 * 	@param position
	 */
	public static void showScreen(Window window, String position)
	{
		SessionManager.getAppDesktop().showWindow(window, position);
	}   //  showScreen

	/**
	 *	Position in center of the parent window.
	 *  (pack, set location and set visibility)
	 * 	@param parent Parent Window
	 * 	@param window Window to position
	 */
	public static void showCenterWindow(Window parent, Window window)
	{
		parent.appendChild(window);
		showScreen(window, "parent,center");
	}   //  showCenterWindow

	/**
	 *  Get Mnemonic character from text.
	 *  @param text text with '&'
	 *  @return Mnemonic or 0
	 */
	public static char getMnemonic (String text)
	{

		int pos = text.indexOf('&');
		if (pos != -1)					//	We have a nemonic
			return text.charAt(pos+1);
		return 0;

	}   //  getMnemonic


	/*************************************************************************
	 * 	Zoom
	 *	@param AD_Table_ID
	 *	@param Record_ID
	 */
	public static void zoom (int AD_Table_ID, int Record_ID)
	{
		String TableName = null;
		int AD_Window_ID = 0;
		int PO_Window_ID = 0;
		String sql = "SELECT TableName, AD_Window_ID, PO_Window_ID FROM AD_Table WHERE AD_Table_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				TableName = rs.getString(1);
				AD_Window_ID = rs.getInt(2);
				PO_Window_ID = rs.getInt(3);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		//  Nothing to Zoom to
		if (TableName == null || AD_Window_ID == 0)
			return;

		//	PO Zoom ?
		boolean isSOTrx = true;
		if (PO_Window_ID != 0)
		{
			String whereClause = TableName + "_ID=" + Record_ID;
			isSOTrx = DB.isSOTrx(TableName, whereClause);
			if (!isSOTrx)
				AD_Window_ID = PO_Window_ID;
		}

		log.config(TableName + " - Record_ID=" + Record_ID + " (IsSOTrx=" + isSOTrx + ")");
		zoom(AD_Window_ID, MQuery.getEqualQuery(TableName + "_ID", Record_ID));
	}	//	zoom

	/**
	 *	Exit System
	 *  @param status System exit status (usually 0 for no error)
	 */
	public static void exit (int status)
	{
		Env.exitEnv(status);
	}	//	exit

	public static void logout()
	{
		Env.logout();


	}

	/**
	 * 	Start Workflow Process Window
	 *	@param AD_Table_ID optional table
	 *	@param Record_ID optional record
	 */
	public static void startWorkflowProcess (int AD_Table_ID, int Record_ID)
	{
		if (s_workflow_Window_ID <= 0)
		{
			int AD_Window_ID = DB.getSQLValue(null, "SELECT AD_Window_ID FROM AD_Window WHERE Name = 'Workflow Process'");
			s_workflow_Window_ID = AD_Window_ID;
		}

		if (s_workflow_Window_ID <= 0)
			return;

		MQuery query = new MQuery();
		query.addRestriction("AD_Table_ID", MQuery.EQUAL, AD_Table_ID);
		query.addRestriction("Record_ID", MQuery.EQUAL, Record_ID);
		AEnv.zoom(s_workflow_Window_ID, query);
	}	//	startWorkflowProcess


	/*************************************************************************/

	/** Workflow Window		*/
	private static int		s_workflow_Window_ID = 0;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(AEnv.class);

	/**	Window Cache		*/
	private static Map<String, CCache<Integer,GridWindowVO>> windowCache = new HashMap<String, CCache<Integer,GridWindowVO>>();

	/**
	 *  Get Window Model
	 *
	 *  @param WindowNo  Window No
	 *  @param AD_Window_ID window
	 *  @param AD_Menu_ID menu
	 *  @return Model Window Value Obkect
	 */
	public static GridWindowVO getMWindowVO (int WindowNo, int AD_Window_ID, int AD_Menu_ID)
	{

		log.config("Window=" + WindowNo + ", AD_Window_ID=" + AD_Window_ID);
		GridWindowVO mWindowVO = null;
		String windowKey = Env.getLanguage(Env.getCtx()).getLocale() + "|" + Env.getAD_Role_ID(Env.getCtx());
		if (AD_Window_ID != 0 && Ini.isCacheWindow())	//	try cache
		{
			synchronized (windowCache)
			{
				CCache<Integer,GridWindowVO> cache = windowCache.get(windowKey);
				if (cache != null)
				{
					mWindowVO = cache.get(AD_Window_ID);
					if (mWindowVO != null)
					{
						mWindowVO = mWindowVO.clone(WindowNo);
						log.info("Cached=" + mWindowVO);
					}
				}
			}
		}

		//  Create Window Model on Client
		if (mWindowVO == null)
		{
			log.config("create local");
			mWindowVO = GridWindowVO.create (Env.getCtx(), WindowNo, AD_Window_ID, AD_Menu_ID);
			if (mWindowVO != null)
			{
				synchronized (windowCache)
				{
					CCache<Integer,GridWindowVO> cache = windowCache.get(windowKey);
					if (cache == null)
					{
						cache = new CCache<Integer, GridWindowVO>("AD_Window", 10);
						windowCache.put(windowKey, cache);
					}
					cache.put(AD_Window_ID, mWindowVO);
				}
			}
		}	//	from Client
		if (mWindowVO == null)
			return null;

		//  Check (remote) context
		if (!mWindowVO.ctx.equals(Env.getCtx()))
		{
			//  Remote Context is called by value, not reference
			//  Add Window properties to context
			Enumeration<Object> keyEnum = mWindowVO.ctx.keys();
			while (keyEnum.hasMoreElements())
			{
				String key = (String)keyEnum.nextElement();
				if (key.startsWith(WindowNo+"|"))
				{
					String value = mWindowVO.ctx.getProperty (key);
					Env.setContext(Env.getCtx(), key, value);
				}
			}
			//  Sync Context
			mWindowVO.setCtx(Env.getCtx());
		}
		return mWindowVO;

	}   //  getWindow

	/**
	 *  Post Immediate
	 *  @param  WindowNo 		window
	 *  @param  AD_Table_ID     Table ID of Document
	 *  @param  AD_Client_ID    Client ID of Document
	 *  @param  Record_ID       Record ID of this document
	 *  @param  force           force posting
	 *  @return null if success, otherwise error
	 */
	public static String postImmediate (int WindowNo, int AD_Client_ID,
		int AD_Table_ID, int Record_ID, boolean force)
	{

		log.info("Window=" + WindowNo
			+ ", AD_Table_ID=" + AD_Table_ID + "/" + Record_ID
			+ ", Force=" + force);
		
		String error = null;
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(Env.getCtx(), AD_Client_ID);
		error = Doc.postImmediate(ass, AD_Table_ID, Record_ID, force, null);

		return error;
	}   //  postImmediate

	/**
	 *  Cache Reset
	 *  @param  tableName	table name
	 *  @param  Record_ID	record id
	 */
	public static void cacheReset (String tableName, int Record_ID)
	{

		log.config("TableName=" + tableName + ", Record_ID=" + Record_ID);

		CacheMgt.get().reset(tableName, Record_ID);
	}   //  cacheReset

	/**
	 *  Validate permissions to access Info queries on the view menu
	*   @author kstan_79
	*   @return true if access is allowed
	*/

	public static boolean canAccessInfo(String infoWindowName)
	{
		boolean result=false;
		int roleid= Env.getAD_Role_ID(Env.getCtx());
		String sqlRolePermission="Select COUNT(AD_ROLE_ID) AS ROWCOUNT FROM AD_ROLE WHERE AD_ROLE_ID=" + roleid
	                              + " AND ALLOW_INFO_" + infoWindowName + "='Y'";

		log.config(sqlRolePermission);
		PreparedStatement prolestmt = null;
		ResultSet rs = null;
		try
		{
			prolestmt = DB.prepareStatement (sqlRolePermission, null);

			rs = prolestmt.executeQuery ();

			rs.next();

			if (rs.getInt("ROWCOUNT")>0)
			{
				result=true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "(1)", e);
		}
		finally
		{
			DB.close(rs, prolestmt);
		}

		return result;

	} // 	canAccessInfo

    public static void actionRefresh(Lookup lookup, Object value, boolean mandatory)
    {
        if (lookup == null)
            return;

        lookup.refresh();
        if (lookup.isValidated())
            lookup.fillComboBox(mandatory, false, false, false);
        else
            lookup.fillComboBox(mandatory, true, false, false);
    }
    /**
     *
     * @param lookup
     * @param value
     */
    public static void actionZoom(Lookup lookup, Object value)
    {
        if (lookup == null)
            return;
        //
        MQuery zoomQuery = lookup.getZoomQuery();

        //  If not already exist or exact value
        if (zoomQuery == null || value != null)
        {
            zoomQuery = new MQuery();   //  ColumnName might be changed in MTab.validateQuery
            String column = lookup.getColumnName();
			//	Check if it is a Table Reference
			if (lookup instanceof MLookup && DisplayType.List == lookup.getDisplayType())
			{
				int AD_Reference_ID = ((MLookup)lookup).getAD_Reference_Value_ID();
				column = "AD_Ref_List_ID";
				value = DB.getSQLValue(null, "SELECT AD_Ref_List_ID FROM AD_Ref_List WHERE AD_Reference_ID=? AND Value=?", AD_Reference_ID, value);
			}
			//strip off table name, fully qualify name doesn't work when zoom into detail tab
			if (column.indexOf(".") > 0)
			{
				int p = column.indexOf(".");
				String tableName = column.substring(0, p);
				column = column.substring(column.indexOf(".")+1);
				zoomQuery.setZoomTableName(tableName);
				zoomQuery.setZoomColumnName(column);            	
			}
			else
			{
				zoomQuery.setZoomColumnName(column);
				//remove _ID to get table name
				zoomQuery.setZoomTableName(column.substring(0, column.length() - 3));
			}
			zoomQuery.setZoomValue(value);
			zoomQuery.addRestriction(column, MQuery.EQUAL, value);
			zoomQuery.setRecordCount(1);    //  guess
        }
        int windowId = lookup.getZoom(zoomQuery);
        zoom(windowId, zoomQuery);
    }

	/**
	 * Zoom to a window with the provided window id and filters according to the
	 * query
	 * @param AD_Window_ID Window on which to zoom
	 * @param query Filter to be applied on the records.
	 */
	public static void zoom(int AD_Window_ID, MQuery query)
	{
		SessionManager.getAppDesktop().showZoomWindow(AD_Window_ID, query);
	}

	public static void showWindow(Window win)
	{
		SessionManager.getAppDesktop().showWindow(win);
	}

	/**
	 * 	Zoom
	 *	@param query query
	 */
	public static void zoom (MQuery query)
	{
		if (query == null || query.getTableName() == null || query.getTableName().length() == 0)
			return;
		String TableName = query.getTableName();
		int AD_Window_ID = 0;
		int PO_Window_ID = 0;
		String sql = "SELECT AD_Window_ID, PO_Window_ID FROM AD_Table WHERE TableName=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setString(1, TableName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				AD_Window_ID = rs.getInt(1);
				PO_Window_ID = rs.getInt(2);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		//  Nothing to Zoom to
		if (AD_Window_ID == 0)
			return;

		//	PO Zoom ?
		boolean isSOTrx = true;
		if (PO_Window_ID != 0)
		{
			isSOTrx = DB.isSOTrx(TableName, query.getWhereClause(false));
			if (!isSOTrx)
				AD_Window_ID = PO_Window_ID;
		}

		log.config(query + " (IsSOTrx=" + isSOTrx + ")");

		zoom(AD_Window_ID, query);
	}


	/**
	 *  Get ImageIcon.
	 *
	 *  @param fileNameInImageDir full file name in imgaes folder (e.g. Bean16.png)
	 *  @return image
	 */
    public static URI getImage(String fileNameInImageDir) {
        return getImage(fileNameInImageDir, false);
    }   //  getImageIcon
    
    /**
	 *  Get ImageIcon.
	 *
	 *  @param fileNameInImageDir full file name in imgaes folder (e.g. Bean16.png)
	 *  @param dark if is a dark image
	 *  @return image
	 */
    public static URI getImage(String fileNameInImageDir, boolean dark) {
        URI uri = null;
        try
        {	
        	String folder = ITheme.IMAGE_FOLDER;
        	if(dark) {
        		folder = ITheme.IMAGE_FOLDER_DARK;
        	}
            uri = new URI(folder + fileNameInImageDir);
        }
        catch (URISyntaxException exception)
        {
            log.log(Level.SEVERE, "Not found: " +  fileNameInImageDir);
            return null;
        }
        return uri;
    }   //  getImageIcon


    /**
     *
     * @return boolean
     */
    public static boolean isFirefox2() {
    	Execution execution = Executions.getCurrent();
    	if (execution == null)
    		return false;

    	Object n = execution.getNativeRequest();
    	if (n instanceof ServletRequest) {
    		String userAgent = Servlets.getUserAgent((ServletRequest) n);
    		return userAgent.indexOf("Firefox/2") >= 0;
    	} else {
    		return false;
    	}
    }
    
    /**
     * @return boolean
     */
    public static boolean isBrowserSupported() {
    	Execution execution = Executions.getCurrent();
    	if (execution == null)
    		return false;

    	Object n = execution.getNativeRequest();
    	boolean supported = false;
    	if (n instanceof ServletRequest) {
    		String userAgent = Servlets.getUserAgent((ServletRequest) n);
    		if (userAgent.indexOf("Firefox") >= 0) {
    			supported = true;
    		} else if (userAgent.indexOf("AppleWebKit") >= 0) {
    			if (userAgent.indexOf("Chrome") >= 0 || userAgent.indexOf("Safari") >= 0) {
    				supported = true;
    			} 
    		} 
    	} 
    	return supported;
    }
    
    /**
     * @return true if user agent is internet explorer
     */
    public static boolean isInternetExplorer()
    {
    	Execution execution = Executions.getCurrent();
    	if (execution == null)
    		return false;

    	Object n = execution.getNativeRequest();
    	if (n instanceof ServletRequest) {
    		String userAgent = Servlets.getUserAgent((ServletRequest) n);
    		if (userAgent.indexOf("MSIE ") >= 0) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     *
     * @param parent
     * @param child
     * @return boolean
     */
    public static boolean contains(Component parent, Component child) {
    	if (child == parent)
    		return true;

    	Component c = child.getParent();
    	while (c != null) {
    		if (c == parent)
    			return true;
    		c = c.getParent();
    	}

    	return false;
    }

    /**
     *
     * @param pdfList
     * @param outFile
     * @throws IOException
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public static void mergePdf(List<File> pdfList, File outFile) throws IOException,
			DocumentException, FileNotFoundException {
		Document document = null;
		PdfWriter copy = null;
		for (File f : pdfList)
		{
			PdfReader reader = new PdfReader(f.getAbsolutePath());
			if (document == null)
			{
				document = new Document(reader.getPageSizeWithRotation(1));
				copy = PdfWriter.getInstance(document, new FileOutputStream(outFile));
				document.open();
			}
			int pages = reader.getNumberOfPages();
			PdfContentByte cb = copy.getDirectContent();
			for (int i = 1; i <= pages; i++) {
				document.newPage();
				PdfImportedPage page = copy.getImportedPage(reader, i);
				cb.addTemplate(page, 0, 0);
			}
		}
		document.close();
    }

    /**
	 *	Get window title
	 *  @param ctx context
	 *  @param WindowNo window
	 *  @return Header String
	 */
	public static String getWindowHeader(Properties ctx, int WindowNo)
	{
		StringBuffer sb = new StringBuffer();
		if (WindowNo > 0){
			sb.append(Env.getContext(ctx, WindowNo, "WindowName", false)).append("  ");
			final String documentNo = Env.getContext(ctx, WindowNo, "DocumentNo", false);
			final String value = Env.getContext(ctx, WindowNo, "Value", false);
			final String name = Env.getContext(ctx, WindowNo, "Name", false);
			if(!"".equals(documentNo)) {
				sb.append(documentNo).append("  ");
			}
			if(!"".equals(value)) {
				sb.append(value).append("  ");
			}
			if(!"".equals(name)) {
				sb.append(name).append("  ");
			}
		}
		return sb.toString();
	}	//	getHeader

	/**
	 * @param ctx
	 * @return Language
	 */
	public static Language getLanguage(Properties ctx) {
		Locale locale = getLocale(ctx);
		Language language = Env.getLanguage(ctx);
		if (!language.getLocale().equals(locale)) {
			Language tmp = Language.getLanguage(locale.toString());
			String adLanguage = language.getAD_Language();
			language = new Language(tmp.getName(), adLanguage, tmp.getLocale(), tmp.isDecimalPoint(),
	    			tmp.getDateFormat().toPattern(), tmp.getMediaSize());
		}
		Env.verifyLanguage(ctx, language);
		return language;
	}

	/**
	 * @param ctx
	 * @return Locale
	 */
	public static Locale getLocale(Properties ctx) {
		String value = Env.getContext(ctx, AEnv.LOCALE);
        Locale locale = null;
        if (value != null && value.length() > 0)
        {
	        String[] components = value.split("\\_");
	        String language = components.length > 0 ? components[0] : "";
	        String country = components.length > 1 ? components[1] : "";
	        locale = new Locale(language, country);
        }
        else
        {
        	locale = Env.getLanguage(ctx).getLocale();
        }

        return locale;
	}
	
	/**
	 * Get title for dialog window
	 * @param ctx
	 * @param windowNo
	 * @return dialog header
	 */
	public static String getDialogHeader(Properties ctx, int windowNo) {
		StringBuffer sb = new StringBuffer();
		if (windowNo > 0){
			sb.append(Env.getContext(ctx, windowNo, "WindowName", false)).append("  ");
			final String documentNo = Env.getContext(ctx, windowNo, "DocumentNo", false);
			final String value = Env.getContext(ctx, windowNo, "Value", false);
			final String name = Env.getContext(ctx, windowNo, "Name", false);
			if(!"".equals(documentNo)) {
				sb.append(documentNo).append("  ");
			}
			if(!"".equals(value)) {
				sb.append(value).append("  ");
			}
			if(!"".equals(name)) {
				sb.append(name).append("  ");
			}
		}
		String header = sb.toString().trim();
		if (header.length() == 0)
			header = ThemeManager.getBrowserTitle();
		return header;
	}
	
	/**
	 * logout AD_Session
	 */
	public static void logout0()
	{
		String sessionID = Env.getContext(Env.getCtx(), "#AD_Session_ID");
		windowCache.remove(sessionID);
		// End Session
		MSession session = MSession.get(Env.getCtx(), false); // finish
		if (session != null)
			session.logout();
	}
	
	/**
	 * Get current desktop
	 * 
	 * @return Desktop
	 */
	public static Desktop getDesktop()
	{
		boolean inUIThread = Executions.getCurrent() != null;
		if (inUIThread)
		{
			return Executions.getCurrent().getDesktop();
		}
		else
		{
			WeakReference<Desktop> ref = DesktopRunnable.getThreadLocalDesktop();
			return ref != null ? ref.get() : null;
		}
	}
	
	/**
	 * Get icon from action
	 * @param action
	 * @return
	 */
	public static String getMenuIconFile(String action) {
		String iconPath = null;
		if (action.equals(MMenu.ACTION_Report))
			iconPath = ITheme.MENU_REPORT_IMAGE;
        else if (action.equals(MMenu.ACTION_Process))
        	iconPath = ITheme.MENU_PROCESS_IMAGE;
        else if (action.equals(MMenu.ACTION_WorkFlow))
        	iconPath = ITheme.MENU_WORKFLOW_IMAGE;
        else if (action.equals(MMenu.ACTION_Task))
        	iconPath = ITheme.MENU_TASK_IMAGE;
        else if (action.equals(MMenu.ACTION_Workbench))
        	iconPath = ITheme.MENU_WORKBENCH_IMAGE;
        else if (action.equals(MMenu.ACTION_SmartBrowse))
        	iconPath = ITheme.MENU_BROWSER_IMAGE;
        else
        	iconPath = ITheme.MENU_WINDOW_IMAGE;
		//	Default
		return iconPath;
	}
}	//	AEnv
