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

package org.adempiere.webui.panel;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.logging.Level;

import org.adempiere.model.MBrowse;
import org.adempiere.webui.WArchive;
import org.adempiere.webui.WRequest;
import org.adempiere.webui.WSearch;
import org.adempiere.webui.WZoomAcross;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.apps.WReport;
import org.adempiere.webui.apps.form.WCreateFromFactory;
import org.adempiere.webui.apps.form.WPayment;
import org.adempiere.webui.apps.form.WQuickEntrySheet;
import org.adempiere.webui.component.AbstractADTab;
import org.adempiere.webui.component.CWindowToolbar;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.component.IADTab;
import org.adempiere.webui.component.IADTabList;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.event.ActionEvent;
import org.adempiere.webui.event.ActionListener;
import org.adempiere.webui.event.ToolbarListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.FindWindow;
import org.adempiere.webui.window.WChat;
import org.adempiere.webui.window.WDeleteSelection;
import org.adempiere.webui.window.WRecordAccessDialog;
import org.compiere.grid.ICreateFrom;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.MProcess;
import org.compiere.model.MQuery;
import org.compiere.model.MRecentItem;
import org.compiere.model.MRole;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.eevolution.form.WBrowser;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 *
 * This class is based on org.compiere.apps.APanel written by Jorg Janke.
 * @author Jorg Janke
 *
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 *
 * @author Cristina Ghita, www.arhipac.ro
 * @see FR [ 2877111 ] See identifiers columns when delete records https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2877111&group_id=176962
 *
 * @author hengsin, hengsin.low@idalica.com
 * @see FR [2887701] https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2887701&group_id=176962
 * @sponsor www.metas.de
 *
 * @author Teo Sarca, teo.sarca@gmail.com
 *  	<li>BF [ 2992540 ] Grid/Panel not refreshed after process run
 *  		https://sourceforge.net/tracker/?func=detail&aid=2992540&group_id=176962&atid=955896
 *  	<li>BF [ 2985892 ] Opening a window using a new record query is not working
 *  		https://sourceforge.net/tracker/?func=detail&aid=2985892&group_id=176962&atid=955896
 * @author e-Evolution , victor.perez@e-evolution.com
 *      <li>Implement embedded or horizontal tab panel https://adempiere.atlassian.net/browse/ADEMPIERE-319
 *      <li>New ADempiere 3.8.0 ZK Theme Light  https://adempiere.atlassian.net/browse/ADEMPIERE-320
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 127 ] ZK Open Form return "Process Error
 *		@see https://github.com/adempiere/adempiere/issues/127
 *		<li> FR [ 114 ] Change "Create From" UI for Form like Dialog in window without "hardcode"
 *		@see https://github.com/adempiere/adempiere/issues/114
 *		<li> BR [ 147 ] Form called from window must has access to process
 *		@see https://github.com/adempiere/adempiere/issues/147
 *		<a href="https://github.com/adempiere/adempiere/issues/592">
 * 		@see FR [ 592 ] Delete Selection dialog is not MVC</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/990">
 * 		@see FR [ 990 ] Sort Tab is not MVC</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/884">
 * 		@see FR [ 884 ] Recent Items in Dashboard</a>
 * @author Raul Muñoz, rMunoz@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 1004 ] Bad size for processing dialog on ZK Web UI
 *		<li> BR [ 1059 ] Error when a process button deletes a record from the window
 */
public abstract class AbstractADWindowPanel extends AbstractUIPart implements ToolbarListener,
        EventListener, DataStatusListener, ActionListener, ASyncProcess
{
    private static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(AbstractADWindowPanel.class);
    }

    private Properties           ctx;

    private GridWindow           gridWindow;

    protected StatusBarPanel     statusBar;
    
    protected IADTab          	 adTab;

    private int                  curWindowNo;

    private GridTab              currentTab;
    
    private boolean              m_onlyCurrentRows = true;

    private IADTabPanel curTabPanel;

    protected CWindowToolbar     toolbar;

    private int                  currentTabIndex;

    protected String             title;

    private boolean              newRecord;

    private boolean 			 boolChanges = false;

	private int m_onlyCurrentDays = 0;

	private Component parent;

	private boolean m_uiLocked;

	private boolean m_findCancelled;

	private int embeddedTabIndex = -1;

	protected Map<Integer, ADTabPanel> includedMap = new HashMap<Integer, ADTabPanel>();

	private IADTabPanel embeddedTabPanel;

	private boolean m_findCreateNew;
	
	public Borderlayout layout;
	
	public North north = new North();
	
	private WProcessAction processAction;
	
	private GridPanel quickGridPanel = null;
	

	/**
	 * Constructor for non-embedded mode
	 * @param ctx
	 * @param windowNo
	 */
    public AbstractADWindowPanel(Properties ctx, int windowNo)
    {
        this(ctx, windowNo, null, -1, null);
    }

    /**
     * Constructor for embedded mode
     * @param ctx
     * @param windowNo
     * @param gridWindow
     * @param tabIndex
     * @param tabPanel
     */
    public AbstractADWindowPanel(Properties ctx, int windowNo, GridWindow gridWindow, int tabIndex, IADTabPanel tabPanel)
    {
        this.ctx = ctx;
        this.curWindowNo = windowNo;
        this.gridWindow = gridWindow;
        this.embeddedTabIndex = tabIndex;
        this.embeddedTabPanel = tabPanel;
        curTabPanel = tabPanel;
        if (gridWindow != null && tabIndex >= 0)
        	currentTab = gridWindow.getTab(tabIndex);

        initComponents();
    }

    /**
     * @param parent
     * @return Component
     */
	public Component createPart(Object parent)
    {
		if (parent instanceof Component)
			this.parent = (Component) parent;

		adTab = createADTab();
		adTab.addSelectionEventListener(this);
		if (adTab instanceof AbstractADTab)
		{
			((AbstractADTab)adTab).setADWindowPanel(this);
		}

        return super.createPart(parent);
    }

	/**
	 * @return StatusBarPanel
	 */
	public StatusBarPanel getStatusBar()
    {
    	return statusBar;
    }
	
	/**
	 * @return boolean
	 */
	public boolean isEmbedded() {
		return embeddedTabIndex >= 0;
	}

    private void initComponents()
    {
        /** Initalise toolbar */
        toolbar = new CWindowToolbar(isEmbedded());
        toolbar.addListener(this);
        statusBar = new StatusBarPanel(isEmbedded());
    }

    /**
     * @return IADTab
     */
    protected abstract IADTab createADTab();

    private void focusToActivePanel() {
    	//IADTabpanel adTabPanel = adTab.getSelectedTabpanel();
		//if (adTabPanel != null && adTabPanel instanceof HtmlBasedComponent) {
		//	((HtmlBasedComponent)adTabPanel).focus();
		//}
        IADTabPanel adTabPanel = toolbar.getCurrentPanel();
        if (adTabPanel != null && adTabPanel instanceof HtmlBasedComponent) {
        	((HtmlBasedComponent)adTabPanel).focus();
        }
	}

    /**
     * @param adWindowId
     * @param query
     * @return boolean
     */
	public boolean initPanel(int adWindowId, MQuery query)
    {
		// This temporary validation code is added to check the reported bug
		// [ adempiere-ZK Web Client-2832968 ] User context lost?
		// https://sourceforge.net/tracker/?func=detail&atid=955896&aid=2832968&group_id=176962
		// it's harmless, if there is no bug then this must never fail
		Session currSess = Executions.getCurrent().getDesktop().getSession();
		int checkad_user_id = -1;
		if (currSess != null && currSess.getAttribute("Check_AD_User_ID") != null)
			checkad_user_id = (Integer)currSess.getAttribute("Check_AD_User_ID");
		if (checkad_user_id!=Env.getAD_User_ID(ctx))
		{
			String msg = "Timestamp=" + new Date() 
					+ ", Bug 2832968 SessionUser="
					+ checkad_user_id
					+ ", ContextUser="
					+ Env.getAD_User_ID(ctx)
					+ ".  Please report conditions to your system administrator or in sf tracker 2832968";
			ApplicationException ex = new ApplicationException(msg);
			logger.log(Level.SEVERE, msg, ex);
			throw ex;
		}
		// End of temporary code for [ adempiere-ZK Web Client-2832968 ] User context lost?

		// Set AutoCommit for this Window
		if (embeddedTabIndex < 0)
		{
			Env.setAutoCommit(ctx, curWindowNo, Env.isAutoCommit(ctx));
			boolean autoNew = Env.isAutoNew(ctx);
			Env.setAutoNew(ctx, curWindowNo, autoNew);

	        GridWindowVO gWindowVO = AEnv.getMWindowVO(curWindowNo, adWindowId, 0);
	        if (gWindowVO == null)
	        {
	            throw new ApplicationException(Msg.getMsg(ctx,
	                    "AccessTableNoView")
	                    + "(No Window Model Info)");
	        }
	        gridWindow = new GridWindow(gWindowVO, true);
	        title = gridWindow.getName();

	        // Set SO/AutoNew for Window
	        Env.setContext(ctx, curWindowNo, "IsSOTrx", gridWindow.isSOTrx());
	        if (!autoNew && gridWindow.isTransaction())
	        {
	            Env.setAutoNew(ctx, curWindowNo, true);
	        }
		}

        m_onlyCurrentRows =  embeddedTabIndex < 0 && gridWindow.isTransaction();

        MQuery detailQuery = null;
        /**
         * Window Tabs
         */
        if (embeddedTabIndex < 0)
        {
        	if (query != null && query.getZoomTableName() != null && query.getZoomColumnName() != null
					&& query.getZoomValue() instanceof Integer && (Integer)query.getZoomValue() > 0)
	    	{
	    		if (!query.getZoomTableName().equalsIgnoreCase(gridWindow.getTab(0).getTableName()))
	    		{
	    			detailQuery = query;
	    			query = new MQuery();
	    			query.addRestriction("1=2");
	    			query.setRecordCount(0);
	    		}
	    	}

	        int tabSize = gridWindow.getTabCount();

	        for (int tab = 0; tab < tabSize; tab++)
	        {
	            initTab(query, tab);
	            if (tab == 0 && currentTab == null && m_findCancelled)
	            	return false;
	        }
	        setActiveTab(0);
	        getToolbar().setCurrentPanel(curTabPanel);
	        
	        Env.setContext(ctx, curWindowNo, "WindowName", gridWindow.getName());
        }
        else
        {
        	initEmbeddedTab(query, embeddedTabIndex);
        }

        if (currentTab != null)
        	currentTab.getTableModel().setChanged(false);

        if (embeddedTabIndex < 0)
        {
	        currentTabIndex = 0;

	        adTab.setSelectedIndex(0);
	        toolbar.enableTabNavigation(adTab.getTabCount() > 1);
	        toolbar.enableFind(true);
	        adTab.evaluate(null);

	        if (gridWindow.isTransaction())
	        {
	        	toolbar.enableHistoryRecords(true);
	        }

	        if (detailQuery != null && zoomToDetailTab(detailQuery))
	        {
	        	return true;
	        }
        }
        else
        {
        	currentTabIndex = embeddedTabIndex;
        	toolbar.enableTabNavigation(false);
	        toolbar.enableFind(true);
	        toolbar.enableHistoryRecords(false);
        }
       
        	

        updateToolbar();

        return true;
    }

	private boolean zoomToDetailTab(MQuery query) {
		//zoom to detail
        if (query != null && query.getZoomTableName() != null && query.getZoomColumnName() != null)
    	{
    		GridTab gTab = gridWindow.getTab(0);
    		if (!query.getZoomTableName().equalsIgnoreCase(gTab.getTableName()))
    		{
    			int tabSize = gridWindow.getTabCount();

    	        for (int tab = 0; tab < tabSize; tab++)
    	        {
    	        	gTab = gridWindow.getTab(tab);
    	        	if (gTab.isSortTab())
    	        		continue;
    	        	if (gTab.getTableName().equalsIgnoreCase(query.getZoomTableName()))
    	        	{
    	        		if (doZoomToDetail(gTab, query, tab)) {
	        				return true;
	        			}
    	        	}
    	        }
    		}
    	}
        return false;
	}

	private boolean doZoomToDetail(GridTab gTab, MQuery query, int tabIndex) {
		GridField[] fields = gTab.getFields();
		for (GridField field : fields)
		{
			if (field.getColumnName().equalsIgnoreCase(query.getZoomColumnName()))
			{
				gridWindow.initTab(tabIndex);
				int parentId = DB.getSQLValue(null, "SELECT " + gTab.getLinkColumnName() + " FROM " + gTab.getTableName() + " WHERE " + query.getWhereClause());
				if (parentId > 0)
				{
					Map<Integer, Object[]>parentMap = new TreeMap<Integer, Object[]>();
					int index = tabIndex;
					int oldpid = parentId;
					GridTab currentTab = gTab;
					while (index > 0)
					{
						index--;
						GridTab pTab = gridWindow.getTab(index);
						if (pTab.getTabLevel() < currentTab.getTabLevel())
						{
							gridWindow.initTab(index);
							if (index > 0)
							{
								if (pTab.getLinkColumnName() != null && pTab.getLinkColumnName().trim().length() > 0)
								{
									int pid = DB.getSQLValue(null, "SELECT " + pTab.getLinkColumnName() + " FROM " + pTab.getTableName() + " WHERE " + currentTab.getLinkColumnName() + " = ?", oldpid);
									if (pid > 0)
									{
										parentMap.put(index, new Object[]{currentTab.getLinkColumnName(), oldpid});
										oldpid = pid;
										currentTab = pTab;
									}
									else
									{
										parentMap.clear();
										break;
									}
								}
							}
							else
							{
								parentMap.put(index, new Object[]{currentTab.getLinkColumnName(), oldpid});
							}
						}
					}

					for(Map.Entry<Integer, Object[]> entry : parentMap.entrySet())
					{
						GridTab pTab = gridWindow.getTab(entry.getKey());
						Object[] value = entry.getValue();
						MQuery pquery = new MQuery(pTab.getAD_Table_ID());
						pquery.addRestriction((String)value[0], "=", value[1]);
						pTab.setQuery(pquery);
						IADTabPanel tp = adTab.findADTabpanel(pTab);
        				tp.createUI();
        				tp.query();
					}

					MQuery targetQuery = new MQuery(gTab.getAD_Table_ID());
					targetQuery.addRestriction(gTab.getLinkColumnName(), "=", parentId);
					gTab.setQuery(targetQuery);
					IADTabPanel gc = null;
					if (!includedMap.containsKey(gTab.getAD_Tab_ID()))
					{
						gc = adTab.findADTabpanel(gTab);
					}
					else
					{
						ADTabPanel parent = (ADTabPanel)includedMap.get(gTab.getAD_Tab_ID());
						gc = parent.findEmbeddedPanel(gTab);
					}

					gc.createUI();
					gc.query(false, 0, 0);

					GridTable table = gTab.getTableModel();
    				int count = table.getRowCount();
    				for(int i = 0; i < count; i++)
    				{
    					int id = table.getKeyID(i);
    					if (id == ((Integer)query.getZoomValue()).intValue())
    					{
    						if (!includedMap.containsKey(gTab.getAD_Tab_ID()))
    						{
    							setActiveTab(gridWindow.getTabIndex(gTab));
    						}
    						else
    						{
    							IADTabPanel parent = includedMap.get(gTab.getAD_Tab_ID());
    							int pindex = gridWindow.getTabIndex(parent.getGridTab());
    							if (pindex >= 0)
    								setActiveTab(pindex);
    						}
    						gTab.setCurrentRow(i);
    						return true;
    					}
    				}
				}
			}
		}
		return false;
	}

	private void initEmbeddedTab(MQuery query, int tabIndex) {
		GridTab gTab = gridWindow.getTab(tabIndex);
		gTab.addDataStatusListener(this);
		adTab.addTab(gTab, embeddedTabPanel);
		if (gTab.isSortTab()) {
			((WSortTab)embeddedTabPanel).registerAPanel(this);
		} else {
			((ADTabPanel)embeddedTabPanel).init(this, curWindowNo, gTab, gridWindow);
		}
	}

	/**
	 * @param query
	 * @param tabIndex
	 */
	protected void initTab(MQuery query, int tabIndex) {
		gridWindow.initTab(tabIndex);

		GridTab gTab = gridWindow.getTab(tabIndex);
		Env.setContext(ctx, curWindowNo, tabIndex, GridTab.CTX_TabLevel, Integer.toString(gTab.getTabLevel()));

		// Query first tab
		if (tabIndex == 0)
		{
		    query = initialQuery(query, gTab);
		    if (gTab.isHighVolume() && m_findCancelled)
		    	return;

		    if (query != null && query.getRecordCount() <= 1)
		    {
		        // goSingleRow = true;
		    }
		    // Set initial Query on first tab
		    if (query != null)
		    {
		        m_onlyCurrentRows = false;
		        gTab.setQuery(query);
		    }

		    currentTab = gTab;
		    currentTabIndex = tabIndex;
		}

		if (gTab.isSortTab())
		{
			WSortTab sortTab = new WSortTab(curWindowNo, gTab);
            toolbar.setCurrentPanel(sortTab);
			sortTab.setGlobalToolbar(toolbar);
			if (includedMap.containsKey(gTab.getAD_Tab_ID()))
		    {
				IADTabPanel includePanel = includedMap.get(gTab.getAD_Tab_ID());
                if (includePanel.isEmbedded())
                    includedMap.get(gTab.getAD_Tab_ID()).embed(ctx, curWindowNo, gridWindow, gTab.getAD_Tab_ID(), tabIndex, sortTab);
                else
				    includedMap.get(gTab.getAD_Tab_ID()).HorizontalEmbedded(ctx, curWindowNo, gridWindow, gTab.getAD_Tab_ID(), tabIndex, sortTab);
		    }
			else
			{
				adTab.addTab(gTab, sortTab);
				sortTab.registerAPanel(this);
				if (tabIndex == 0) {
					curTabPanel = sortTab;
					curTabPanel.createUI();
					curTabPanel.query(m_onlyCurrentRows, m_onlyCurrentDays, MRole.getDefault().getMaxQueryRecords());
					curTabPanel.activate(true);
				}
				gTab.addDataStatusListener(this);
			}
		}
		else
		{
			//build embedded tab map
			ADTabPanel fTabPanel = new ADTabPanel();
            toolbar.setCurrentPanel(fTabPanel);
			fTabPanel.setGlobalToolbar(toolbar);
			GridField[] fields = gTab.getTableModel().getFields();
		    for(int i = 0; i < fields.length; i++)
		    {
		    	if (fields[i].getIncluded_Tab_ID() > 0)
		    	{
                    if(fields[i].isEmbedded())
                        fTabPanel.setIsEmbedded(fields[i].isEmbedded());

		    		includedMap.put(fields[i].getIncluded_Tab_ID(), fTabPanel);
		    	}
		    }

		    if (includedMap.containsKey(gTab.getAD_Tab_ID()))
		    {
		    	IADTabPanel includePanel = includedMap.get(gTab.getAD_Tab_ID());
                if (includePanel.isEmbedded())
                    includedMap.get(gTab.getAD_Tab_ID()).embed(ctx, curWindowNo, gridWindow, gTab.getAD_Tab_ID(), tabIndex, fTabPanel);
                else
                    includedMap.get(gTab.getAD_Tab_ID()).HorizontalEmbedded(ctx, curWindowNo, gridWindow, gTab.getAD_Tab_ID(), tabIndex, fTabPanel);

		    	fTabPanel.setGlobalToolbar(toolbar);
		    }
		    else
		    {
		    	gTab.addDataStatusListener(this);
		    	fTabPanel.init(this, curWindowNo, gTab, gridWindow);
		    	adTab.addTab(gTab, fTabPanel);
			    if (tabIndex == 0) {
			    	fTabPanel.createUI();
			    	curTabPanel = fTabPanel;
                    toolbar.setCurrentPanel(curTabPanel);
			    	curTabPanel.setGlobalToolbar(toolbar);
			    	curTabPanel.query(m_onlyCurrentRows, m_onlyCurrentDays, MRole.getDefault().getMaxQueryRecords());
			        curTabPanel.activate(true);
			    }
		    }
		}

		if (tabIndex == 0) {
			toolbar.setCurrentPanel(curTabPanel);	
			if (currentTab.isHighVolume() && m_findCreateNew)
				onNew();
		    else if (query == null && currentTab.getRowCount() == 0 && Env.isAutoNew(ctx, curWindowNo))
		    	onNew();
		    else if (!currentTab.isReadOnly() && currentTab.isQueryNewRecord())
		    	onNew();
		}
	}

    /**
     * Initial Query
     *
     * @param query
     *            initial query
     * @param mTab
     *            tab
     * @return query or null
     */
    private MQuery initialQuery(MQuery query, GridTab mTab)
    {
        // We have a (Zoom) query
        if (query != null && query.isActive())
            return query;
        //
        StringBuffer where = new StringBuffer(Env.parseContext(ctx, curWindowNo, mTab.getWhereExtended(), false));
        // Query automatically if high volume and no query
        boolean require = mTab.isHighVolume();
        if (!require && !m_onlyCurrentRows) // No Trx Window
        {
            if (query != null)
            {
                String wh2 = query.getWhereClause();
                if (wh2.length() > 0)
                {
                    if (where.length() > 0)
                        where.append(" AND ");
                    where.append(wh2);
                }
            }
            //
            StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM ")
                    .append(mTab.getTableName());
            if (where.length() > 0)
                sql.append(" WHERE ").append(where);
            // Does not consider security
            int no = DB.getSQLValue(null, sql.toString());
            //
            require = MRole.getDefault().isQueryRequire(no);
        }
        // Show Query
        if (require)
        {
        	m_findCancelled = false;
        	m_findCreateNew = false;
            GridField[] findFields = mTab.getFields();
            FindWindow find = new FindWindow(curWindowNo,
                    mTab.getName(), mTab.getAD_Table_ID(), mTab.getTableName(),
                    where.toString(), findFields, 10, mTab.getAD_Tab_ID()); // no query below 10
            if (find.getTitle() != null && find.getTitle().length() > 0) {
            	// Title is not set when the number of rows is below the minRecords parameter (10)
                if (!find.isCancel())
                {
                	query = find.getQuery();
                	m_findCreateNew = find.isCreateNew();
                }
                else
                	m_findCancelled = true;
                find = null;
            }
        }
        return query;
    } // initialQuery

    public String getTitle()
    {
        return title;
    }

    /**
     * @see ToolbarListener#onDetailRecord()
     */
    public void onDetailRecord()
    {
        int maxInd = adTab.getTabCount() - 1;
        int curInd = adTab.getSelectedIndex();
        if (curInd < maxInd)
        {
            setActiveTab(curInd + 1);
        }

        focusToActivePanel();
    }

	/**
     * @see ToolbarListener#onParentRecord()
     */
    public void onParentRecord()
    {
        int curInd = adTab.getSelectedIndex();
        if (curInd > 0)
        {
            setActiveTab(curInd - 1);
        }

        focusToActivePanel();
    }

    /**
     * @see ToolbarListener#onFirst()
     */
    public void onFirst()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        currentTab.navigate(0);
        focusToActivePanel();
    }

    /**
     * @see ToolbarListener#onLast()
     */
    public void onLast()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        currentTab.navigate(currentTab.getRowCount() - 1);
        focusToActivePanel();
    }

    /**
     * @see ToolbarListener#onNext()
     */
    public void onNext()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        currentTab.navigateRelative(+1);
        focusToActivePanel();
    }

    /**
     * @see ToolbarListener#onPrevious()
     */
    public void onPrevious()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        currentTab.navigateRelative(-1);
        focusToActivePanel();
    }

    // Elaine 2008/12/04
	private Menupopup 	m_popup = null;
	private Menuitem 	m_lock = null;
	private Menuitem 	m_access = null;

	/**
	 *	@see ToolbarListener#onLock()
	 */
	public void onLock()
	{
		if (!toolbar.isPersonalLock)
			return;
		final int record_ID = currentTab.getRecord_ID();
		if (record_ID == -1)	//	No Key
			return;

		if(m_popup == null)
		{
			m_popup = new Menupopup();

			m_lock = new Menuitem(Msg.translate(Env.getCtx(), "Lock"));
			m_popup.appendChild(m_lock);
			m_lock.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					currentTab.lock(Env.getCtx(),  currentTab.getRecord_ID() , !toolbar.getButton("Lock").isPressed());
					currentTab.loadAttachments();			//	reload

					toolbar.lock(currentTab.isLocked());
				}
			});

			m_access = new Menuitem(Msg.translate(Env.getCtx(), "RecordAccessDialog"));
			m_popup.appendChild(m_access);
			m_access.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					new WRecordAccessDialog(null, currentTab.getAD_Table_ID(), currentTab.getRecord_ID());

					toolbar.lock(currentTab.isLocked());
				}
			});

			m_popup.setPage(toolbar.getButton("Lock").getPage());
		}
		m_popup.open(toolbar.getButton("Lock"));
	}	//	lock
	//

    /**
     * @see ToolbarListener#onHistoryRecords()
     */
    public void onHistoryRecords()
    {
		logger.info("");

		if (gridWindow.isTransaction())
		{
			if (currentTab.needSave(true, true) && !onSave(false))
				return;

			WOnlyCurrentDays ocd = new WOnlyCurrentDays();
			m_onlyCurrentDays = ocd.getCurrentDays();

			history(m_onlyCurrentDays);
		}
		focusToActivePanel();
    }

    private void history(int onlyCurrentDays)
    {
		if (onlyCurrentDays == 1)	//	Day
		{
			m_onlyCurrentRows = true;
			onlyCurrentDays = 0; 	//	no Created restriction
		}
		else
			m_onlyCurrentRows = false;

		currentTab.setQuery(null);	//	reset previous queries
		MRole role = MRole.getDefault();
		int maxRows = role.getMaxQueryRecords();

		logger.config("OnlyCurrent=" + m_onlyCurrentRows
			+ ", Days=" + m_onlyCurrentDays
			+ ", MaxRows=" + maxRows);

		currentTab.query(m_onlyCurrentRows, onlyCurrentDays, maxRows);

    }

    /**
     * @see ToolbarListener#onAttachment()
     */
    public void onAttachment()
    {
		int record_ID = currentTab.getRecord_ID();
		logger.info("Record_ID=" + record_ID);

		if (record_ID == -1)	//	No Key
		{
			//aAttachment.setEnabled(false);
			return;
		}

		//	Attachment va =
		new WAttachment (	curWindowNo, currentTab.getAD_AttachmentID(),
							currentTab.getAD_Table_ID(), record_ID, null);

		currentTab.loadAttachments();				//	reload
		toolbar.getButton("Attachment").setPressed(currentTab.hasAttachment());
		focusToActivePanel();
	}

    public void onChat()
    {
    	int recordId = currentTab.getRecord_ID();
    	logger.info("Record_ID=" + recordId);

		if (recordId == -1)	//	No Key
		{
			return;
		}

		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < currentTab.getFieldCount(); i++)
		{
			GridField field = currentTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}
		String description = infoName + ": " + infoDisplay;
		
    	new WChat(curWindowNo, currentTab.getCM_ChatID(), currentTab.getAD_Table_ID(), recordId, description, null);
    	currentTab.loadChats();
		toolbar.getButton("Chat").setPressed(currentTab.hasChat());
		focusToActivePanel();
    }
    
    /**
     * @see ToolbarListener#onToggle()
     */
    public void onToggle()
    {
    	toolbar.getCurrentPanel().switchRowPresentation();
    	focusToActivePanel();
    }

    /**
     * @return boolean
     */
    public boolean onExit()
    {
    	String message = Msg.getMsg(ctx, "SaveBeforeClose");

    	if (!boolChanges)
    	{
    		return true;
    	}
    	else
    		FDialog.info(this.curWindowNo, null, message);

    	return false;
    }

    /**
     * @param event
     * @see EventListener#onEvent(Event)
     */
    public void onEvent(Event event)
    {
    	if (!Events.ON_SELECT.equals(event.getName()))
    		return;

    	IADTabList tabList = null;
    	Component target = event.getTarget();
    	if (target instanceof IADTabList)
    	{
    		tabList = (IADTabList) target;
    	}
    	else
    	{
    		target = target.getParent();
    		while(target != null)
    		{
    			if (target instanceof IADTabList)
    	    	{
    	    		tabList = (IADTabList) target;
    	    		break;
    	    	}
    			target = target.getParent();
    		}
    	}

        if (tabList != null)
        {
        	int newTabIndex = tabList.getSelectedIndex();

            if (setActiveTab(newTabIndex))
            {
            	//force sync model
            	tabList.refresh();
            }
            else
            {
            	//reset to original
            	tabList.setSelectedIndex(currentTabIndex);
            }
        }
    }

	private boolean setActiveTab(int newTabIndex) {
		boolean back = false;

		int oldTabIndex = currentTabIndex;

		if (oldTabIndex == newTabIndex)
		{
		    return true;
		}
		
		if (currentTab != null)
		{
			if (currentTab.isSortTab())
			{
				if (curTabPanel instanceof WSortTab)
				{
					((WSortTab) curTabPanel).saveData();
					((WSortTab) curTabPanel).unregisterPanel();
				}
			}
			else if (currentTab.needSave(true, false))
		    {
		    	if (currentTab.needSave(true, true))
				{
					//	Automatic Save
					if (Env.isAutoCommit(ctx, curWindowNo)
						&& (currentTab.getCommitWarning() == null || currentTab.getCommitWarning().trim().length() == 0))
					{
						if (!currentTab.dataSave(true))
						{
							showLastError();
							//  there is a problem, stop here
							return false;
						}
					}
					//  explicitly ask when changing tabs
					else if (FDialog.ask(curWindowNo, this.getComponent(), "SaveChanges?", currentTab.getCommitWarning()))
					{   //  yes we want to save
						if (!currentTab.dataSave(true))
						{
							showLastError();
							//  there is a problem, stop here
							return false;
						}
					}
					else    //  Don't save
					{
						int newRecord= currentTab.getTableModel().getNewRow();     //VOSS COM

						if( newRecord == -1)
							currentTab.dataIgnore();
						else
						{
							return false;
						}
					}
				}
				else    //  new record, but nothing changed
					currentTab.dataIgnore();
			}   //  there is a change
		}

		if (!adTab.updateSelectedIndex(oldTabIndex, newTabIndex))
		{
		    FDialog.warn(curWindowNo, "TabSwitchJumpGo", title);
		    return false;
		}

		IADTabPanel oldTabPanel = curTabPanel;
		toolbar.setPreviousPanel(oldTabPanel);
		IADTabPanel newTabPanel = adTab.getSelectedTabpanel();		
	
		currentTab = newTabPanel.getGridTab();	
			
		if (oldTabPanel != null) {
			oldTabPanel.activate(false); 
			if(oldTabPanel.getListPanel() != null) {
				oldTabPanel.getListPanel().addKeyListener();
			}
		}
		newTabPanel.activate(true);
		if(newTabPanel.getListPanel() != null) {
			newTabPanel.getListPanel().addKeyListener();
		}
		back = (newTabIndex < oldTabIndex);
		if (back && newTabPanel.getTabLevel() > 0)
		{
			if (newTabPanel.getTabLevel() >= oldTabPanel.getTabLevel())
				back = false;
			else if ((newTabIndex - oldTabIndex) > 1)
			{
				for (int i = oldTabIndex - 1; i > newTabIndex; i--)
				{
					IADTabPanel next = adTab.getADTabpanel(i);
					if (next != null && next.getTabLevel() <= newTabPanel.getTabLevel())
					{
						back = false;
						break;
					}
				}
			}
		}

		if (!back)
		{
		    newTabPanel.query();
		}
		else
		{
			newTabPanel.getGridTab().dataRefreshAll();
		}

		currentTabIndex = newTabIndex;
		curTabPanel = newTabPanel;
		toolbar.setCurrentPanel(curTabPanel);
		curTabPanel.setGlobalToolbar(toolbar);


		if (curTabPanel instanceof WSortTab)
		{
			((WSortTab) curTabPanel).registerAPanel(this);
		}
		else
		{
			if (currentTab.getRowCount() == 0 && Env.isAutoNew(ctx, getWindowNo()))
			{
				onNew();
			}
		}

		updateToolbar();

		return true;
	}

	private void updateToolbar()
	{
		if(!isEmbedded()
				&& !(curTabPanel instanceof WSortTab))
    	{
            toolbar.enableChanges(currentTab.isReadOnly());
            toolbar.enabledNew(currentTab.isInsertRecord());
            toolbar.enableCopy(currentTab.isInsertRecord());

            toolbar.enableTabNavigation(currentTabIndex > 0,
                    currentTabIndex < (adTab.getTabCount() - 1));

            toolbar.getButton("Attachment").setPressed(currentTab.hasAttachment());
            toolbar.getButton("Chat").setPressed(currentTab.hasChat());
            if (isFirstTab())
            {
                toolbar.getButton("HistoryRecords").setPressed(!currentTab.isOnlyCurrentRows());
            }
            toolbar.getButton("Find").setPressed(currentTab.isQueryActive());

            if (toolbar.isPersonalLock)
            {
                toolbar.lock(currentTab.isLocked());
            }

            toolbar.enablePrint(currentTab.isPrinted());

            if (gridWindow.isTransaction() && isFirstTab())
            {
                toolbar.enableHistoryRecords(true);
            }
            else
            {
                toolbar.enableHistoryRecords(false);
            }
            toolbar.enableGridToggle(true);
    		toolbar.enableArchive(true);
    		toolbar.enableWorkflows(true);
    		toolbar.enableRequests(true);
    		toolbar.enableProductInfo(true);
    		toolbar.enableZoomAcross(true);
    		toolbar.enableFind(true);
    		toolbar.enableRefresh(true);
    		toolbar.enableAttachment(true);
    		toolbar.enableChat(true);
    		toolbar.enableNavigation(true);
    		toolbar.enableProcess(true);
    	} else if(curTabPanel instanceof WSortTab) {
    		toolbar.enableGridToggle(false);
    		toolbar.enableNew(false);
    		toolbar.enableCopy(false);
    		toolbar.enableReport(false);
    		toolbar.enablePrint(false);
    		toolbar.enableArchive(false);
    		toolbar.enableWorkflows(false);
    		toolbar.enableRequests(false);
    		toolbar.enableProductInfo(false);
    		toolbar.enableZoomAcross(false);
    		toolbar.enableDelete(false);
    		toolbar.enableDeleteSelection(false);
    		toolbar.enableFind(false);
    		toolbar.enableRefresh(false);
    		toolbar.enableAttachment(false);
    		toolbar.enableChat(false);
    		toolbar.enableNavigation(false);
    		toolbar.enableProcess(false);
    	}

	}

	/**
	 * @param e
	 * @see DataStatusListener#dataStatusChanged(DataStatusEvent)
	 */
    public void dataStatusChanged(DataStatusEvent e)
    {
    		
    	//ignore non-ui thread event for now.
    	if (Executions.getCurrent() == null || curTabPanel == null ||  curTabPanel.getGlobalToolbar() == null)
    		return;
    	CWindowToolbar toolbar = curTabPanel.getGlobalToolbar();
    	
        logger.info(e.getMessage());
        String dbInfo = e.getMessage();
        if (currentTab != null && currentTab.isQueryActive())
            dbInfo = "[ " + dbInfo + " ]";
        statusBar.setStatusDB(dbInfo, e);

        //  Set Message / Info
        if (e.getAD_Message() != null || e.getInfo() != null)
        {
            StringBuffer sb = new StringBuffer();
            String msg = e.getMessage();
            if (msg != null && msg.length() > 0)
            {
                sb.append(Msg.getMsg(Env.getCtx(), e.getAD_Message()));
            }
            String info = e.getInfo();
            if (info != null && info.length() > 0)
            {
                if (sb.length() > 0 && !sb.toString().trim().endsWith(":"))
                    sb.append(": ");
                sb.append(info);
            }
            if (sb.length() > 0)
            {
                int pos = sb.indexOf("\n");
                if (pos != -1 && pos+1 < sb.length())  // replace CR/NL
                {
                    sb.replace(pos, pos+1, " - ");
            	}
                boolean showPopup = e.isError() 
                	|| (!GridTab.DEFAULT_STATUS_MESSAGE.equals(e.getAD_Message()) && !GridTable.DATA_REFRESH_MESSAGE.equals(e.getAD_Message()));
                	statusBar.setStatusLine (sb.toString (), e.isError(), showPopup);
            }
        }
        
        //  Confirm Error
        if (e.isError() && !e.isConfirmed())
        {
        	//focus to error field
        	ADTabPanel tabPanel = (ADTabPanel) getADTab().getSelectedTabpanel();
        	GridField[] fields = currentTab.getFields();
        	for (GridField field : fields)
        	{
        		if (field.isError())
        		{
        			tabPanel.setFocusToField(field.getColumnName());
        			tabPanel.updateMandatoryLabels();
        			
        			break;
        		}
        	}
            e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        }
        
        //  Confirm Warning
        else if (e.isWarning() && !e.isConfirmed())
        {
        	((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
        	FDialog.warn(curWindowNo, null, e.getAD_Message(), e.getInfo());
        	
        	e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        	((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
        }

        //  update Navigation
        boolean firstRow = e.isFirstRow();
        boolean lastRow = e.isLastRow();
        toolbar.enableFirstNavigation(!firstRow && !currentTab.isSortTab());
        toolbar.enableLastNavigation(!lastRow && !currentTab.isSortTab());

        //  update Change
        boolean changed = e.isChanged() || e.isInserting();
        boolChanges = changed;
        boolean readOnly = currentTab.isReadOnly();
        boolean insertRecord = !readOnly;

        if (insertRecord)
        {
            insertRecord = currentTab.isInsertRecord();
        }
//        toolbar.enabledNew(!changed && insertRecord && !curTab.isSortTab());
        toolbar.enabledNew(insertRecord && !currentTab.isSortTab());
        toolbar.enableCopy(!changed && insertRecord && !currentTab.isSortTab());
        toolbar.enableRefresh(!changed);
        toolbar.enableDelete(!changed && !readOnly && !currentTab.isSortTab());
        toolbar.enableDeleteSelection(!changed && !readOnly && !currentTab.isSortTab());
        //
        if (readOnly && currentTab.isAlwaysUpdateField())
        {
            readOnly = false;
        }
        toolbar.enableIgnore(changed && !readOnly);
        //	Add to recent items
        if (changed && !readOnly && !toolbar.isSaveEnable()) {
        	addToRecentItems();
        }
        //	
        toolbar.enableSave(changed && !readOnly);

        //
        //  No Rows
        if (e.getTotalRows() == 0 && insertRecord)
        {
            toolbar.enabledNew(true);
            toolbar.enableDelete(false);
            toolbar.enableDeleteSelection(false);
        }

        //  History (on first Tab only)
        if (isFirstTab())
        {
            toolbar.getButton("HistoryRecords").setPressed(!currentTab.isOnlyCurrentRows());
        }

        //  Transaction info
        String trxInfo = currentTab.getTrxInfo();
        if (trxInfo != null) {
            statusBar.setInfo(trxInfo);
        }
        //	reload Field
        if(getADTab() != null) {
        	ADTabPanel tabPanel = (ADTabPanel) getADTab().getSelectedTabpanel();
        	if(tabPanel != null) {
        		tabPanel.reloadFieldTrxInfo();
        	}
        }
        //  Check Attachment
        boolean canHaveAttachment = currentTab.canHaveAttachment();       //  not single _ID column
        //
        if (canHaveAttachment && e.isLoading() &&
                currentTab.getCurrentRow() > e.getLoadedRows())
        {
            canHaveAttachment = false;
        }
        if (canHaveAttachment && currentTab.getRecord_ID() == -1)    //   No Key
        {
            canHaveAttachment = false;
        }
        if (canHaveAttachment)
        {
            toolbar.enableAttachment(true);
            toolbar.getButton("Attachment").setPressed(currentTab.hasAttachment());
        }
        else
        {
            toolbar.enableAttachment(false);
        }

        // Check Chat
        boolean canHaveChat = true;
        if (e.isLoading() &&
                currentTab.getCurrentRow() > e.getLoadedRows())
        {
            canHaveChat = false;
        }
        if (canHaveChat && currentTab.getRecord_ID() == -1)    //   No Key
        {
            canHaveChat = false;
        }
        if (canHaveChat)
        {
            toolbar.enableChat(true);
            toolbar.getButton("Chat").setPressed(currentTab.hasChat());
        }
        else
        {
        	toolbar.enableChat(false);
        }
        
        toolbar.getButton("Find").setPressed(currentTab.isQueryActive());

        // Elaine 2008/12/05
        //  Lock Indicator
        if (toolbar.isPersonalLock)
        {
			toolbar.lock(currentTab.isLocked());
        }
        //

        adTab.evaluate(e);
        toolbar.enablePrint(currentTab.isPrinted());
        toolbar.enableReport(true);
    }
    
    /**
     * Add to recent items
     */
    private void addToRecentItems() {
		//	Add Recent Items
    	if (currentTab.getRecord_ID() > 0) {
        	if (currentTabIndex == 0) {
        		MRecentItem.addWindowChange(ctx, currentTab.getAD_Table_ID(),
        				currentTab.getRecord_ID(), currentTab.getAD_Window_ID(),
        				currentTab.getAD_Tab_ID());
        	} else {
        		//	when a detail record is modified add header to recent items
        		GridTab mainTab = gridWindow.getTab(0);
        		if (mainTab != null) {
        			MRecentItem.addWindowChange(ctx, mainTab.getAD_Table_ID(),
        					mainTab.getRecord_ID(), mainTab.getAD_Window_ID(),
        					mainTab.getAD_Tab_ID());
        		}
        	}
        }
	}

    /**
     * @return boolean
     */
    public boolean isFirstTab()
    {
        int selTabIndex = adTab.getSelectedIndex();
        return (selTabIndex == 0);
    }

    /**
     * refresh all row
     * @param fireEvent
     */
    public void onRefresh(boolean fireEvent)
    {
    	onSave(false);
    	GridTab currentTab=toolbar.getCurrentPanel().getGridTab();
        currentTab.dataRefreshAll(fireEvent, true);
        curTabPanel.dynamicDisplay(0);
        focusToActivePanel();
    }

    /**
     * @see ToolbarListener#onRefresh()
     */
    public void onRefresh()
    {
    	onRefresh(true);
    }

    /**
     * @see ToolbarListener#onHelp()
     */
    public void onHelp()
    {
    	WebDoc doc = gridWindow.getHelpDoc(true);
		SessionManager.getAppDesktop().showURL(doc, "Help", true);
    }

    /**
     * @see ToolbarListener#onNew()
     */
    public void onNew()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
    	((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().refresh(currentTab);
    	

    	if (!currentTab.isInsertRecord())
        {
            logger.warning("Insert Record disabled for Tab");
            return;
        }

        if (!autoSave()) {
        	return;
        }

        
        newRecord = currentTab.dataNew(false);
        if (newRecord)
        {
            //curTabPanel.dynamicDisplay(0);
            toolbar.getCurrentPanel().dynamicDisplay(0);
            toolbar.enableChanges(false);
            toolbar.enableDelete(false);
    		toolbar.enableDeleteSelection(false);
            toolbar.enableNavigation(false);
            toolbar.enableTabNavigation(false);
            toolbar.enableIgnore(true);
            toolbar.enablePrint(currentTab.isPrinted());
            toolbar.enableReport(true);
        }
        else
        {
            logger.severe("Could not create new record");
        }
        focusToActivePanel();
        ((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().onPostSelectedRowChanged();
    }

    private boolean autoSave() {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
    	//  has anything changed?
		if (currentTab.needSave(true, false))
		{   //  do we have real change
			if (currentTab.needSave(true, true))
			{
				if (!onSave(false))
				{
					return false;
				}
			}
			else    //  new record, but nothing changed
                currentTab.dataIgnore();
		}   //  there is a change
		return true;
	}

	// Elaine 2008/11/19
    /**
     * @see ToolbarListener#onCopy()
     */
    public void onCopy()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        if (!currentTab.isInsertRecord())
        {
            logger.warning("Insert Record disabled for Tab");
            return;
        }

        newRecord = currentTab.dataNew(true);
        if (newRecord)
        {
            curTabPanel.dynamicDisplay(0);
            toolbar.enableChanges(false);
            toolbar.enableDelete(false);
            toolbar.enableDeleteSelection(false); // Elaine 2008/12/02
            toolbar.enableNavigation(false);
            toolbar.enableTabNavigation(false);
            toolbar.enableIgnore(true);
            toolbar.enablePrint(currentTab.isPrinted());
            toolbar.enableReport(true);
        }
        else
        {
            logger.severe("Could not create new record");
        }
        focusToActivePanel();
    }
    //

    /**
     * @see ToolbarListener#onFind()
     */
    public void onFind()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();

    	if (currentTab == null)
            return;
        
        if (!onSave(false))
        	return;

        //  Gets Fields from AD_Field_v
        GridField[] findFields = GridField.createFields(ctx, currentTab.getWindowNo(), 0,currentTab.getAD_Tab_ID());
        //  Open a popup or the search window
        new WSearch (this, toolbar.getEvent().getTarget(), currentTab, findFields);
    }

    public void onFindCallback(MQuery query)
    {
        //  Confirmed query
        if (query != null)
        {
        	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        	m_onlyCurrentRows = false;          //  search history too
            currentTab.setQuery(query);
            curTabPanel.query(m_onlyCurrentRows, m_onlyCurrentDays, MRole.getDefault().getMaxQueryRecords());   //  autoSize
            currentTab.dataRefresh(false); // Elaine 2008/07/25
            onRefresh();

            focusToActivePanel();
        }

    }

    /**
     * @see ToolbarListener#onIgnore()
     */
    public void onIgnore()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
    	if (currentTab.isSortTab())
    	{
    		curTabPanel.refresh();
    		toolbar.enableIgnore(false);
    	}
    	else
    	{
            currentTab.dataIgnore();
            currentTab.dataRefresh(true);
	        curTabPanel.dynamicDisplay(0);
	        toolbar.enableIgnore(false);
	        curTabPanel.getListPanel().focusCurrentCol();
	        if(quickGridPanel!=null) {
	        	quickGridPanel.dynamicDisplay(0);
	        	quickGridPanel.focusCurrentCol();
	        	quickGridPanel.onIgnore();
	        	toolbar.enableDelete(true);
		        toolbar.enableDeleteSelection(true);
		        updateToolbar();
	        }
	        
    	}
    	curTabPanel.getListPanel().addKeyListener();
    	focusToActivePanel();
    }

    /**
     * @see ToolbarListener#onSave()
     */
    public void onSave()
    {
    	onSave(true);
    	focusToActivePanel();
    }

    /**
     * @param onSaveEvent
     */
    private boolean onSave(boolean onSaveEvent)
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
    	boolean wasChanged = toolbar.isSaveEnable();
    	
    	if (currentTab.isSortTab())
    	{
    		((WSortTab) curTabPanel).saveData();
    		return true;
    	}
    	else
    	{
    		if (onSaveEvent && currentTab.getCommitWarning() != null && currentTab.getCommitWarning().trim().length() > 0)
    		{
    			if (!FDialog.ask(curWindowNo, this.getComponent(), "SaveChanges?", currentTab.getCommitWarning()))
    			{
    				return false;
    			}
    		}
	    	boolean retValue = currentTab.dataSave(onSaveEvent);

	        if (!retValue)
	        {
	        	showLastError();
	            return false;
	        } else if (!onSaveEvent) //need manual refresh
	        {
                currentTab.setCurrentRow(currentTab.getCurrentRow());
	        }
	        //curTabPanel.dynamicDisplay(0);
	        //curTabPanel.afterSave(onSaveEvent);
	        toolbar.getCurrentPanel().dynamicDisplay(0);
	        toolbar.getCurrentPanel().afterSave(onSaveEvent);

	        if (wasChanged) {
		        addToRecentItems();
	        }
	        return true;
    	}
    }

	private void showLastError() {
		String msg = CLogger.retrieveErrorString(null);
		if (msg != null)
		{
			statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), msg), true, true);
		}
		//other error will be catch in the dataStatusChanged event
	}

    /**
     * @see ToolbarListener#onDelete()
     */
    public void onDelete()
    {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
        if (currentTab.isReadOnly())
        {
            return;
        }

        if (FDialog.ask(curWindowNo, null, "DeleteRecord?"))
        {
        	//error will be catch in the dataStatusChanged event
            currentTab.dataDelete();
        }
        curTabPanel.dynamicDisplay(0);
        focusToActivePanel();
    }

    // Elaine 2008/12/01
	/**
	 * @see ToolbarListener#onDelete()
	 */
    public void onDeleteSelection() {
    	GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
		if (currentTab.isReadOnly())
            return;
        //	
		WDeleteSelection dSelection = new WDeleteSelection(currentTab);
		dSelection.showDialog();
		if(dSelection.isOkPressed()) {
			logger.fine("ok");
			int[] indices = dSelection.getSelection();
			Arrays.sort(indices);
			int offset = 0;
			for (int i = 0; i < indices.length; i++) {
				currentTab.navigate(indices[i]-offset);
				if (currentTab.dataDelete()) {
					offset++;
				}
			}
			curTabPanel.dynamicDisplay(0);
		} else {
			logger.fine("cancel");
		}
		//	Set Focus
		focusToActivePanel();
	}
	//

    /**
     * @see ToolbarListener#onPrint()
     */
	public void onPrint() {
		GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
		//Get process defined for this tab
		int AD_Process_ID = currentTab.getAD_Process_ID();
		//log.info("ID=" + AD_Process_ID);

		//	No report defined
		if (AD_Process_ID == 0)
		{
			onReport();

			return;
		}

		if (!onSave(false))
			return;
		//
		int tableId = currentTab.getAD_Table_ID();
		int recordId = currentTab.getRecord_ID();

		ProcessModalDialog processModalDialog = new ProcessModalDialog(this,getWindowNo(), AD_Process_ID,tableId, recordId, true);
		if (processModalDialog.isValidDialog()) {
			processModalDialog.setPosition("center");
			try {
				processModalDialog.setPage(this.getComponent().getPage());
				processModalDialog.doModal();
			}
			catch (InterruptedException e) {
			}
		}
		else
			processModalDialog.runProcess();
	}

	/**
     * @see ToolbarListener#onReport()
     */
	public void onReport() {
		GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
		if (!MRole.getDefault().isCanReport(currentTab.getAD_Table_ID()))
		{
			FDialog.error(curWindowNo, parent, "AccessCannotReport");
			return;
		}

		if (!onSave(false))
			return;

		//	Query
		//MQuery query = curTabx.getQuery() ; //  new MQuery(curTabx.getTableName());
		
		MQuery oldQuery = currentTab.getQuery() ; //  new MQuery(curTabx.getTableName());
        MQuery query = new MQuery(currentTab.getTableName()) ;
        
        try {
 
            query = oldQuery.deepCopy();
        }
        catch (Exception e) {
            
            query = oldQuery;
            e.printStackTrace();
        }
        
        
		if(currentTab.getWhereClause() !=null && !currentTab.getWhereClause().isEmpty())
		{
			if(currentTab.getWhereClause().length()>0)
				query.addRestriction(Env.parseContext(ctx, curWindowNo, currentTab.getWhereClause(), false));
		}
		//	Link for detail records
		String queryColumn = currentTab.getLinkColumnName();
		//	Current row otherwise
		if (queryColumn.length() == 0)
			queryColumn = currentTab.getKeyColumnName();
		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < currentTab.getFieldCount(); i++)
		{
			GridField field = currentTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}
		if (queryColumn.length() != 0 && query.getRestrictionCount() == 0)
		{
			if (queryColumn.endsWith("_ID"))
				query.addRestriction(queryColumn, MQuery.EQUAL,
					new Integer(Env.getContextAsInt(ctx, curWindowNo, queryColumn)),
					infoName, infoDisplay);
			else
				query.addRestriction(queryColumn, MQuery.EQUAL,
					Env.getContext(ctx, curWindowNo, queryColumn),
					infoName, infoDisplay);
		}

		new WReport (currentTab.getAD_Table_ID(), query, toolbar.getEvent().getTarget(), curWindowNo , currentTab.getWhereExtended());

	}

	/**
     * @see ToolbarListener#onZoomAcross()
     */
	public void onZoomAcross() {
		if (toolbar.getEvent() != null)
		{
			GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
			int record_ID = currentTab.getRecord_ID();
			if (record_ID <= 0)
				return;

			//	Query
			MQuery query = new MQuery();
			//	Current row
			String link = currentTab.getKeyColumnName();
			//	Link for detail records
			if (link.length() == 0)
				link = currentTab.getLinkColumnName();
			if (link.length() != 0)
			{
				if (link.endsWith("_ID"))
					query.addRestriction(link, MQuery.EQUAL,
						new Integer(Env.getContextAsInt(ctx, curWindowNo, link)));
				else
					query.addRestriction(link, MQuery.EQUAL,
						Env.getContext(ctx, curWindowNo, link));
			}
			new WZoomAcross(toolbar.getEvent().getTarget(), currentTab
					.getTableName(), currentTab.getAD_Window_ID(), query);
		}
	}
	
	/**
     * @see ToolbarListener#onProcess()
     */
	public void onProcess() {
		if (toolbar.getEvent() != null) {
			GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
			int record_ID = currentTab.getRecord_ID();
			if (record_ID <= 0)
				return;
			//	
			if(processAction == null) {
				processAction = new WProcessAction(this);
			}
			processAction.openOption(toolbar.getEvent().getTarget());			
		}
	}

	// Elaine 2008/07/17
	/**
     * @see ToolbarListener#onActiveWorkflows()
     */
	public void onActiveWorkflows() {
		if (toolbar.getEvent() != null)
		{
			GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
			if (currentTab.getRecord_ID() <= 0)
				return;
			else
				AEnv.startWorkflowProcess(currentTab.getAD_Table_ID(), currentTab.getRecord_ID());
		}
	}
	//

	// Elaine 2008/07/22
	/**
     * @see ToolbarListener#onRequests()
     */
	public void onRequests()
	{
		if (toolbar.getEvent() != null)
		{
			GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
			if (currentTab.getRecord_ID() <= 0)
				return;

			int C_BPartner_ID = 0;
			Object bpartner = currentTab.getValue("C_BPartner_ID");
			if(bpartner != null)
				C_BPartner_ID = Integer.valueOf(bpartner.toString());

			new WRequest(toolbar.getEvent().getTarget(), currentTab.getAD_Table_ID(), currentTab.getRecord_ID(), C_BPartner_ID);
		}
	}
	//

	// Elaine 2008/07/22
	/**
     * @see ToolbarListener#onProductInfo()
     */
	public void onProductInfo()
	{
		InfoPanel.showProduct(0);
	}
	//

	// Elaine 2008/07/28
	/**
     * @see ToolbarListener#onArchive()
     */
	public void onArchive()
	{
		if (toolbar.getEvent() != null)
		{
			GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
			if (currentTab.getRecord_ID() <= 0)
				return;

			new WArchive(toolbar.getEvent().getTarget(), currentTab.getAD_Table_ID(), currentTab.getRecord_ID());
		}
	}

	//

	/**************************************************************************
	 *	Start Button Process
	 *  @param wButton button
	 */
	public void actionButton (WButtonEditor wButton) {
		GridTab currentTab = toolbar.getCurrentPanel().getGridTab();
		if (currentTab.hasChangedCurrentTabAndParents()) {
			String msg = CLogger.retrieveErrorString("Please ReQuery Window");
			FDialog.error(curWindowNo, parent, null, msg);
			return;
		}

		logger.info(wButton.toString());

		boolean startWOasking = false;
		String col = wButton.getColumnName();

		//  Zoom
		if (col.equals("Record_ID"))
		{
			int AD_Table_ID = Env.getContextAsInt (ctx, curWindowNo, "AD_Table_ID");
			int Record_ID = Env.getContextAsInt (ctx, curWindowNo, "Record_ID");

			AEnv.zoom(AD_Table_ID, Record_ID);
			return;
		} // Zoom

		//  save first	---------------

		if (currentTab.needSave(true, false))
		{
			if (!onSave(false))
				return;
		}

		int table_ID = currentTab.getAD_Table_ID();

		//	Record_ID

		int record_ID = currentTab.getRecord_ID();

		//	Record_ID - Language Handling

		if (record_ID == -1 && currentTab.getKeyColumnName().equals("AD_Language"))
			record_ID = Env.getContextAsInt (ctx, curWindowNo, "AD_Language_ID");

		//	Record_ID - Change Log ID

		if (record_ID == -1
			&& (wButton.getProcess_ID() == 306 || wButton.getProcess_ID() == 307))
		{
			Integer id = (Integer)currentTab.getValue("AD_ChangeLog_ID");
			record_ID = id.intValue();
		}

		//	Ensure it's saved

		if (record_ID == -1 && currentTab.getKeyColumnName().endsWith("_ID"))
		{
			FDialog.error(curWindowNo, parent, "SaveErrorRowNotFound");
			return;
		}
		((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
		boolean isProcessMandatory = false;
		MProcess process = null;
		if(wButton.getProcess_ID() != 0) {
			process = MProcess.get(ctx, wButton.getProcess_ID());
		}
		//	Pop up Payment Rules

		if (col.equals("PaymentRule"))
		{
			WPayment vp = new WPayment(curWindowNo, currentTab, wButton);


			if (vp.isInitOK())		//	may not be allowed
			{
				vp.setVisible(true);
				AEnv.showWindow(vp);
			}
			//vp.dispose();

			if (vp.needSave())
			{
				onSave(false);
				onRefresh(false);
			}
			((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
		} // PaymentRule

		//	Pop up Document Action (Workflow)

		else if (col.equals("DocAction")
				|| (col.equals("StartProcess")
						&& process != null
						&& process.getAD_Workflow_ID() != 0))
		{
			isProcessMandatory = true;
			WDocActionPanel win = new WDocActionPanel(currentTab);
			if (win.getNumberOfOptions() == 0)
			{
				logger.info("DocAction - No Options");
				((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
				return;
			}
			else
			{
				AEnv.showWindow(win);

				if (!win.isStartProcess()) {
					((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
					return;
				}

				//batch = win.isBatch();
				startWOasking = true;
				//vda.dispose();
			}
		} // DocAction

		//  Pop up Create From

		else if (col.equals("CreateFrom"))
		{
			ICreateFrom cf = WCreateFromFactory.create(currentTab);

			if(cf != null)
			{
				if(cf.isInitOK())
				{
					cf.showWindow();
                    currentTab.dataRefresh();
				}
				((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
				return;
			}
			// else may start process
		} // CreateFrom

		//  Posting -----

		else if (col.equals("Posted") && MRole.getDefault().isShowAcct())
		{
			//  Check Doc Status

			String processed = Env.getContext(ctx, curWindowNo, "Processed");

			if (!processed.equals("Y"))
			{
				String docStatus = Env.getContext(ctx, curWindowNo, "DocStatus");

				if (DocAction.STATUS_Completed.equals(docStatus)
					|| DocAction.STATUS_Closed.equals(docStatus)
					|| DocAction.STATUS_Reversed.equals(docStatus)
					|| DocAction.STATUS_Voided.equals(docStatus))
					;
				else
				{
					FDialog.error(curWindowNo, parent, "PostDocNotComplete");
					((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
					return;
				}
			}

			// try to get table and record id from context data (eg for unposted view)
			// otherwise use current table/record
			int tableId = Env.getContextAsInt(ctx, curWindowNo, "AD_Table_ID", true);
			int recordId = Env.getContextAsInt(ctx, curWindowNo, "Record_ID", true);
			if ( tableId == 0 || recordId == 0 )
			{
				tableId = currentTab.getAD_Table_ID();
				recordId = currentTab.getRecord_ID();
			}

			//  Check Post Status
			Object ps = currentTab.getValue("Posted");

			if (ps != null && ps.equals("Y"))
			{
				new org.adempiere.webui.acct.WAcctViewer(Env.getContextAsInt (ctx, curWindowNo, "AD_Client_ID"),
						tableId, recordId);
			}
			else
			{
				if (FDialog.ask(curWindowNo, null, "PostImmediate?"))
				{
					boolean force = ps != null && !ps.equals ("N");		//	force when problems

					String error = AEnv.postImmediate (curWindowNo, Env.getAD_Client_ID(ctx),
						tableId, recordId, force);

					if (error != null)
						FDialog.error(curWindowNo, null, "PostingError-N", error);

					onRefresh(false);
				}
			}
			((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
			return;
		}   //  Posted

		/**
		 *  Start Process ----
		 */

		logger.config("Process_ID=" + wButton.getProcess_ID() + ", Record_ID=" + record_ID);

		if (wButton.getProcess_ID() == 0)
		{
			if (isProcessMandatory)
			{
				FDialog.error(curWindowNo, null, null, Msg.parseTranslation(ctx, "@NotFound@ @AD_Process_ID@"));
			}
			return;
		}

		//	Save item changed

		if (currentTab.needSave(true, false))
		{
			if (!onSave(false)) {
				((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
				return;
			}
		}

		//	Validate Access
		//	BR [ 147 ]
		MRole role = MRole.getDefault(ctx, false);
		Boolean accessRW = role.checkProcessAccess(process.getAD_Process_ID());
		if(accessRW == null
				|| !accessRW.booleanValue()) {
			FDialog.error(curWindowNo, parent, "AccessCannotProcess");
			((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
			return;
		}
		int adFormID = process.getAD_Form_ID();
		//	Yamel Senih BR[ 127 ], 2015-11-25
		//	Bug with launch form
		int adBrowseID = process.getAD_Browse_ID();
		if (adFormID != 0 )
		{
			String title = wButton.getDescription();
			if (title == null || title.length() == 0)
				title = wButton.getDisplay();
			ProcessInfo pi = new ProcessInfo (title, wButton.getProcess_ID(), table_ID, record_ID);
			pi.setWindowNo(curWindowNo);
			pi.setAD_User_ID (Env.getAD_User_ID(ctx));
			pi.setAD_Client_ID (Env.getAD_Client_ID(ctx));
			ADForm form = ADForm.openForm(adFormID);
			form.setProcessInfo(pi);
			//	Yamel Senih FR [ 114 ], 2015-11-25
			form.setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
			form.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
			form.setClosable(true);
			form.setMaximizable(true);
			form.setSizable(true);
			form.setContentStyle("overflow: auto");
			AEnv.showWindow(form);
			//	End Yamel Senih
			currentTab.dataRefreshAll();
		} else if (adBrowseID != 0 )
		{
			String title = wButton.getDescription();
			if (title == null || title.length() == 0)
				title = wButton.getDisplay();
			ProcessInfo pi = new ProcessInfo (title, wButton.getProcess_ID(), table_ID, record_ID);
			pi.setAD_User_ID (Env.getAD_User_ID(ctx));
			pi.setAD_Client_ID (Env.getAD_Client_ID(ctx));
			MBrowse browse = new MBrowse(Env.getCtx(), adBrowseID , null);
			WBrowser browser = new WBrowser(true, curWindowNo, "" , browse, "", true, "", "Y".equals(Env.isSOTrx(Env.getCtx(), curWindowNo)));
			browser.setProcessInfo(pi);
			CustomForm ff =  browser.getForm();
			ff.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
			ff.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
			ff.setTitle(title);
			SessionManager.getAppDesktop().showWindow(ff);
			onRefresh(false);
		}
		else
		{
			ProcessModalDialog dialog = new ProcessModalDialog(this, curWindowNo,
					wButton.getProcess_ID(), table_ID, record_ID, startWOasking);
			//	BR [ 1004 ]
			dialog.setVisible(true);
			dialog.setPosition("center");
			AEnv.showWindow(dialog);

			//onRefresh(true); // Need to fire events to activate subordinate tabs.
		}
		((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().addKeyListener();
		((ADTabPanel)toolbar.getCurrentPanel()).getListPanel().focusCurrentCol();
	} // actionButton

	/**
	 * @param event
	 * @see ActionListener#actionPerformed(ActionEvent)
	 */
	public void actionPerformed(ActionEvent event)
	{
		 if (event.getSource() instanceof WButtonEditor)
	     {
			String error = processButtonCallout((WButtonEditor)event.getSource());
			if (error != null && error.trim().length() > 0)
			{
				statusBar.setStatusLine(error, true);
				return;
			}
			actionButton((WButtonEditor)event.getSource());
	     }
	}

	/**************************************************************************
	 *  Process Callout(s).
	 *  <p>
	 *  The Callout is in the string of
	 *  "class.method;class.method;"
	 * If there is no class name, i.e. only a method name, the class is regarded
	 * as CalloutSystem.
	 * The class needs to comply with the Interface Callout.
	 *
	 * @param button button
	 * @return error message or ""
	 * @see org.compiere.model.Callout
	 */
	private String processButtonCallout (WButtonEditor button)
	{
		GridField field = currentTab.getField(button.getColumnName());
		return currentTab.processCallout(field);
	}	//	processButtonCallout

	/**
	 *
	 * @return IADTab
	 */
	public IADTab getADTab() {
		return adTab;
	}

	/**
	 * @param pi
	 */
	public void executeASync(ProcessInfo pi) {
	}

	/**
	 * @return boolean
	 */
	public boolean isUILocked() {
		return m_uiLocked;
	}

	/**
	 * @param pi
	 */
	public void lockUI(ProcessInfo pi) {
		if (m_uiLocked) return;

		m_uiLocked = true;

		if (Executions.getCurrent() != null)
			Clients.showBusy(null, true);
		else
		{
			try {
				//get full control of desktop
				Executions.activate(getComponent().getDesktop(), 2000);
				try {
					Clients.showBusy(null, true);
                } catch(Error ex){
                	throw ex;
                } finally{
                	//release full control of desktop
                	Executions.deactivate(getComponent().getDesktop());
                }
			} catch (Exception e) {
				logger.log(Level.WARNING, "Failed to lock UI.", e);
			}
		}
	}

	/**
	 * @param pi
	 */
	public void unlockUI(ProcessInfo pi) {
		if (!m_uiLocked) return;

		m_uiLocked = false;
		boolean notPrint = pi != null
		&& pi.getAD_Process_ID() != currentTab.getAD_Process_ID()
		&& pi.isReportingProcess() == false;
		currentTab.dataRefresh();
		//
		//  Process Result

		if (Executions.getCurrent() != null)
		{
			if (notPrint)		//	refresh if not print
			{
				updateUI(pi);
			} else {
				Clients.showBusy(null, false);
			}
		}
		else
		{
			try {
				//get full control of desktop
				Executions.activate(getComponent().getDesktop(), 500);
				try {
					if (notPrint)		//	refresh if not print
					{
						updateUI(pi);
					} else {
						Clients.showBusy(null, false);
					}
                } catch(Error ex){
                	throw ex;
                } finally{
                	//release full control of desktop
                	Executions.deactivate(getComponent().getDesktop());
                }
			} catch (Exception e) {
				logger.log(Level.WARNING, "Failed to update UI upon unloc.", e);
			}
		}
	}

	private void updateUI(ProcessInfo pi) {
		//	Refresh data
		//n.setHeight("68px");
		//	BR [ 1059 ]
		//	curTab.dataRefresh(false);
		//	Timeout
		if (pi.isTimeout())		//	set temporarily to R/O
			Env.setContext(ctx, curWindowNo, "Processed", "Y");
		curTabPanel.dynamicDisplay(0);
		//	Update Status Line
		String summary = pi.getSummary();
		if (summary != null && summary.indexOf('@') != -1)
			pi.setSummary(Msg.parseTranslation(Env.getCtx(), summary));
		statusBar.setStatusLine(pi.getSummary(), pi.isError(), pi.isError());
		//	Get Log Info
		ProcessInfoUtil.setLogFromDB(pi);
		String logInfo = pi.getLogInfo();
		//	
		Clients.showBusy(null, false);
		if (logInfo.length() > 0)
			FDialog.info(curWindowNo, this.getComponent(), Env.getHeader(ctx, curWindowNo),
				pi.getTitle() + "<br>" + logInfo);
		// BR[ 1306 ] Get Log Error 
		Boolean logError = pi.isError();
		if (logError)
			FDialog.error(curWindowNo, this.getComponent(), pi.getSummary());
	}

	/**
	 *
	 * @return toolbar instance
	 */
	public CWindowToolbar getToolbar() {
		return toolbar;
	}

	/**
	 * @return active grid tab
	 */
	public GridTab getActiveGridTab() {
		return currentTab;
	}

	/**
	 * @return windowNo
	 */
	public int getWindowNo() {
		return curWindowNo;
	}
	
	/**
	 * @return parent
	 */
	public Component getParent() {
		return this.parent;
	}
	
	public void onQuickEntry() {
		logger.log(Level.FINE, "Invoke Quick Entry Form");
		ADTabPanel tabPanel = (ADTabPanel) getADTab().getSelectedTabpanel();
		GridTab currentGrid = tabPanel.getGridTab();
			m_popup = new Menupopup();

			Menuitem m_open = new Menuitem(Msg.translate(Env.getCtx(), "AllRecords"));
			m_popup.appendChild(m_open);
			
			
			m_open.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					MQuery query = tabPanel.getGridTab().getQuery();
					showQuickGridPanel(query, tabPanel, currentGrid, false);
				}
			});

			Menuitem newRecord = new Menuitem(Msg.translate(Env.getCtx(), "NewRecord"));
			m_popup.appendChild(newRecord);
			newRecord.addEventListener(Events.ON_CLICK, new EventListener()
			{
				public void onEvent(Event event) throws Exception
				{
					MQuery query = tabPanel.getGridTab().getQuery().getNoRecordQuery(currentGrid.get_TableName(), true);
					showQuickGridPanel(query, tabPanel, currentGrid, true);
				}
			});

			m_popup.setPage(toolbar.getButton("QuickEntry").getPage());
		
		m_popup.open(toolbar.getButton("QuickEntry"));
	
////		if (!SessionManager.registerQuickGrid(tabPanel.getGridTab().getAD_Tab_ID())) {
////			logger.fine("TabID=" + tabPanel.getGridTab().getAD_Tab_ID() + "  is already opened.");
////			return;
////		}
//		
		
		
	}
	
	private void showQuickGridPanel(MQuery query, ADTabPanel tabPanel, GridTab currentGrid, boolean isAutoNew) {
		GridTab quickGridTab = new GridTab(tabPanel.getGridTab().getM_vo(), gridWindow);
		quickGridTab.setLinkColumnName(tabPanel.getGridTab().getLinkColumnName());
		quickGridTab.query(false);
		quickGridTab.setQuery(query);
		quickGridTab.setQuickEntry(true);
		curTabPanel.getListPanel().addKeyListener();
		toolbar.enableHelp(false);
		boolean isChangeView = false;
		if(!((ADTabPanel)curTabPanel).isGridView()) {
			curTabPanel.switchRowPresentation();
			isChangeView = true;
		}
		
		//quickGrid.init(quickGridTab);
		WQuickEntrySheet form = new WQuickEntrySheet(curTabPanel.getListPanel(), quickGridTab, tabPanel, this, m_onlyCurrentDays, m_onlyCurrentRows);
		form.setPosition("center");
		form.setSizable(true);
		if(isAutoNew)
			curTabPanel.getListPanel().createNewLine();
		AEnv.showCenterScreen(form);
		form.dispose();
		adTab.getSelectedTabpanel().getListPanel().addKeyListener();
		if(isChangeView) {
			curTabPanel.switchRowPresentation();
		}
		currentTab.setQuickEntry(false);
		toolbar.enableHelp(true);
		tabPanel.getGridTab().setQuickEntry(false);
		currentTab.dataRefreshAll();
		curTabPanel.getListPanel().refresh(currentGrid);
		curTabPanel.getListPanel().invalidate();
		curTabPanel.appendChild(curTabPanel.getListPanel());
		curTabPanel.invalidate();
		if(!((ADTabPanel)curTabPanel).isGridView()) {
			curTabPanel.getListPanel().addKeyListener();
		}
		m_popup = null;
		form.detach();
		form=null;
	}
		public GridPanel getQuickGridPanel() {
			return quickGridPanel;
		}
	
}
