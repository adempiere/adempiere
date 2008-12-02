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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.WArchive;
import org.adempiere.webui.WRequest;
import org.adempiere.webui.WZoomAcross;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.apps.WReport;
import org.adempiere.webui.apps.form.WCreateFrom;
import org.adempiere.webui.apps.form.WPayment;
import org.adempiere.webui.component.CWindowToolbar;
import org.adempiere.webui.component.IADTab;
import org.adempiere.webui.component.IADTabList;
import org.adempiere.webui.component.Listbox;
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
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.Lookup;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Listitem;

/**
 * 
 * This class is based on org.compiere.apps.APanel written by Jorg Janke.
 * @author Jorg Janke
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author <a href="mailto:hengsin@gmail.com">Low Heng Sin</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public abstract class AbstractADWindowPanel extends AbstractUIPart implements ToolbarListener,
        EventListener, DataStatusListener, ActionListener, ASyncProcess
{
    private static final long    serialVersionUID = 1L;

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

    private GridTab              curTab;

    private boolean              m_onlyCurrentRows = true;

    private IADTabpanel           curTabpanel;

    protected CWindowToolbar     toolbar;

    private int                  curTabIndex;

    protected String             title;
    
    private boolean              newRecord;
    
    private boolean 			 boolChanges = false;
    
	private int m_onlyCurrentDays = 0;
	
	private Component parent;

	private boolean m_uiLocked;

	private boolean m_findCancelled;

	private int embeddedTabindex = -1;
	
	protected Map<Integer, ADTabpanel> includedMap = new HashMap<Integer, ADTabpanel>();

	private IADTabpanel embeddedTabPanel; 

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
    public AbstractADWindowPanel(Properties ctx, int windowNo, GridWindow gridWindow, int tabIndex, IADTabpanel tabPanel)
    {
        this.ctx = ctx;
        this.curWindowNo = windowNo;
        this.gridWindow = gridWindow;
        this.embeddedTabindex = tabIndex;
        this.embeddedTabPanel = tabPanel;
        curTabpanel = tabPanel;
        if (gridWindow != null && tabIndex >= 0)
        	curTab = gridWindow.getTab(tabIndex);
        
        initComponents();
    }
    
	public Component createPart(Object parent)
    {				
		if (parent instanceof Component)
			this.parent = (Component) parent;
		
		adTab = createADTab();
		adTab.addSelectionEventListener(this);
        
        return super.createPart(parent);
    }

	public StatusBarPanel getStatusBar()
    {
    	return statusBar;
    }
	
	public boolean isEmbedded() {
		return embeddedTabindex >= 0;
	}
    
    private void initComponents()
    {
        /** Initalise toolbar */
        toolbar = new CWindowToolbar(isEmbedded());
        toolbar.addListener(this);

        statusBar = new StatusBarPanel();                
    }

    protected abstract IADTab createADTab();

	public boolean initPanel(int adWindowId, MQuery query)
    {
        // Set AutoCommit for this Window
		if (embeddedTabindex < 0)
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
	        gridWindow = new GridWindow(gWindowVO);
	        title = gridWindow.getName();
	        
	        // Set SO/AutoNew for Window
	        Env.setContext(ctx, curWindowNo, "IsSOTrx", gridWindow.isSOTrx());
	        if (!autoNew && gridWindow.isTransaction())
	        {
	            Env.setAutoNew(ctx, curWindowNo, true);
	        }
		}

        m_onlyCurrentRows =  embeddedTabindex < 0 && gridWindow.isTransaction();
        
        /**
         * Window Tabs
         */
        if (embeddedTabindex < 0)
        {
	        int tabSize = gridWindow.getTabCount();
	        
	        for (int tab = 0; tab < tabSize; tab++)
	        {
	            initTab(query, tab);
	            if (tab == 0 && curTab == null && m_findCancelled)
	            	return false;
	        }
	        Env.setContext(ctx, curWindowNo, "WindowName", gridWindow.getName());
        }
        else
        {
        	initEmbeddedTab(query, embeddedTabindex);
        }
                
        if (curTab != null)
        	curTab.getTableModel().setChanged(false);
        
        if (embeddedTabindex < 0)
        {
	        curTabIndex = 0;        
	
	        adTab.setSelectedIndex(0);
	        toolbar.enableTabNavigation(adTab.getTabCount() > 1);
	        toolbar.enableFind(true);
	        adTab.evaluate(null);
	        
	        if (gridWindow.isTransaction())
	        {
	        	toolbar.enableHistoryRecords(true);
	        }
        }
        else 
        {
        	curTabIndex = embeddedTabindex;
        	toolbar.enableTabNavigation(false);
	        toolbar.enableFind(true);
	        toolbar.enableHistoryRecords(false);
        }

        updateToolbar();
        
        return true;
    }

	private void initEmbeddedTab(MQuery query, int tabIndex) {
		GridTab gTab = gridWindow.getTab(tabIndex);
		gTab.addDataStatusListener(this);
		adTab.addTab(gTab, embeddedTabPanel);
		if (gTab.isSortTab()) {
			((ADSortTab)embeddedTabPanel).registerAPanel(this);
		} else {
			((ADTabpanel)embeddedTabPanel).init(this, curWindowNo, gTab, gridWindow);
		}
	}

	protected void initTab(MQuery query, int tabIndex) {
		gridWindow.initTab(tabIndex);

		GridTab gTab = gridWindow.getTab(tabIndex);
		Env.setContext(ctx, curWindowNo, tabIndex, "TabLevel", Integer
		        .toString(gTab.getTabLevel()));
		            
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
		    
		    curTab = gTab;                
		    curTabIndex = tabIndex;
		}
				
		if (gTab.isSortTab())
		{
			ADSortTab sortTab = new ADSortTab(curWindowNo, gTab);
			if (includedMap.containsKey(gTab.getAD_Tab_ID()))
		    {
		    	includedMap.get(gTab.getAD_Tab_ID()).embed(ctx, curWindowNo, gridWindow, gTab.getAD_Tab_ID(), tabIndex, sortTab);
		    }
			else
			{
				adTab.addTab(gTab, sortTab);
				sortTab.registerAPanel(this);
				if (tabIndex == 0) {
					curTabpanel = sortTab;
					curTabpanel.createUI();
					curTabpanel.query(m_onlyCurrentRows, m_onlyCurrentDays, 0);
					curTabpanel.activate(true);
				}
				gTab.addDataStatusListener(this);
			}
		}
		else
		{
			//build embedded tab map
			ADTabpanel fTabPanel = new ADTabpanel();
			GridField[] fields = gTab.getTableModel().getFields();
		    for(int i = 0; i < fields.length; i++)
		    {
		    	if (fields[i].getIncluded_Tab_ID() > 0)
		    	{
		    		includedMap.put(fields[i].getIncluded_Tab_ID(), fTabPanel);
		    	}
		    }
		    
		    if (includedMap.containsKey(gTab.getAD_Tab_ID()))
		    {
		    	includedMap.get(gTab.getAD_Tab_ID()).embed(ctx, curWindowNo, gridWindow, gTab.getAD_Tab_ID(), tabIndex, fTabPanel);
		    }
		    else
		    {	
		    	gTab.addDataStatusListener(this);
		    	fTabPanel.init(this, curWindowNo, gTab, gridWindow);
		    	adTab.addTab(gTab, fTabPanel);
			    if (tabIndex == 0) {
			    	fTabPanel.createUI();                
			    	curTabpanel = fTabPanel;
			    	curTabpanel.query(m_onlyCurrentRows, m_onlyCurrentDays, 0);
			        curTabpanel.activate(true);
			    }
		    }
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
        if (query != null && query.isActive() && query.getRecordCount() < 10)
            return query;
        //
        StringBuffer where = new StringBuffer();
        // Query automatically if high volume and no query
        boolean require = mTab.isHighVolume();
        if (!require && !m_onlyCurrentRows) // No Trx Window
        {
            String wh1 = mTab.getWhereExtended();
            if (wh1 == null || wh1.length() == 0)
                wh1 = mTab.getWhereClause();
            if (wh1 != null && wh1.length() > 0)
                where.append(wh1);
            //
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
            GridField[] findFields = mTab.getFields();
            FindWindow find = new FindWindow(curWindowNo,
                    mTab.getName(), mTab.getAD_Table_ID(), mTab.getTableName(),
                    where.toString(), findFields, 10, mTab.getAD_Tab_ID()); // no query below 10
            if (find.getTitle() != null && find.getTitle().length() > 0) {
            	// Title is not set when the number of rows is below the minRecords parameter (10)
                find.setVisible(true);
                AEnv.showWindow(find);
                if (!find.isCancel())
                	query = find.getQuery();
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
    }

    /**
     * @see ToolbarListener#onFirst()
     */
    public void onFirst()
    {
        curTab.navigate(0);
    }

    /**
     * @see ToolbarListener#onLast()
     */
    public void onLast()
    {
        curTab.navigate(curTab.getRowCount() - 1);
    }

    /**
     * @see ToolbarListener#onNext()
     */
    public void onNext()
    {
        curTab.navigateRelative(+1);
    }

    /**
     * @see ToolbarListener#onPrevious()
     */
    public void onPrevious()
    {
        curTab.navigateRelative(-1);
    }

    /**
     * @see ToolbarListener#onHistoryRecords()
     */
    public void onHistoryRecords()
    {
		logger.info("");
		
		if (gridWindow.isTransaction())
		{
			if (curTab.needSave(true, true)/* && !onSave(false)*/)
				return;

			WOnlyCurrentDays ocd = new WOnlyCurrentDays();
			m_onlyCurrentDays = ocd.getCurrentDays();
			
			history(m_onlyCurrentDays);
		}
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
		
		curTab.setQuery(null);	//	reset previous queries
		MRole role = MRole.getDefault(); 
		int maxRows = role.getMaxQueryRecords();
		
		logger.config("OnlyCurrent=" + m_onlyCurrentRows 
			+ ", Days=" + m_onlyCurrentDays
			+ ", MaxRows=" + maxRows);
		
		curTab.query(m_onlyCurrentRows, onlyCurrentDays, maxRows);

    }
    
    /**
     * @see ToolbarListener#onAttachment()
     */
    public void onAttachment()
    {
		int record_ID = curTab.getRecord_ID();
		logger.info("Record_ID=" + record_ID);
		
		if (record_ID == -1)	//	No Key
		{
			//aAttachment.setEnabled(false);
			return;
		}

		//	Attachment va = 
		new WAttachment (	curWindowNo, curTab.getAD_AttachmentID(), 
							curTab.getAD_Table_ID(), record_ID, null);
		
		curTab.loadAttachments();				//	reload
		//aAttachment.setPressed(m_curTab.hasAttachment());
	}
    
    /**
     * @see ToolbarListener#onToggle()
     */
    public void onToggle()
    {
    	curTabpanel.switchRowPresentation();    	
    }
    
    public boolean onExit()
    {
    	String message = "Please save changes before closing";
    	
    	if (!boolChanges)
    	{
    		return true;
    	}
    	else
    		FDialog.info(this.curWindowNo, null, message);
    	
    	return false;
    }

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
            	tabList.setSelectedIndex(curTabIndex);
            }
        }
    }

	private boolean setActiveTab(int newTabIndex) {
		boolean back = false;

		int oldTabIndex = curTabIndex;            

		if (oldTabIndex == newTabIndex)
		{
		    return true;
		}
		
		if (curTab != null)
		{
			if (curTab.isSortTab())
			{
				if (curTabpanel instanceof ADSortTab) 
				{
					((ADSortTab)curTabpanel).saveData();
					((ADSortTab)curTabpanel).unregisterPanel();
				}
			}
			else if (curTab.needSave(true, false)) 
		    {
		    	if (curTab.needSave(true, true))
				{
					//	Automatic Save
					if (Env.isAutoCommit(ctx, curWindowNo)
						&& (curTab.getCommitWarning() == null || curTab.getCommitWarning().trim().length() == 0))
					{
						if (!curTab.dataSave(true))
						{	
							//  there is a problem, stop here
							return false;
						}
					}
					//  explicitly ask when changing tabs
					else if (FDialog.ask(curWindowNo, this.getComponent(), "SaveChanges?", curTab.getCommitWarning()))
					{   //  yes we want to save
						if (!curTab.dataSave(true))
						{   
							//  there is a problem, stop here
							return false;
						}
					}
					else    //  Don't save
						curTab.dataIgnore();
				}
				else    //  new record, but nothing changed
					curTab.dataIgnore();
			}   //  there is a change
		}

		if (!adTab.updateSelectedIndex(oldTabIndex, newTabIndex))
		{
		    FDialog.warn(curWindowNo, "TabSwitchJumpGo", title);
		    return false;
		}

		back = (newTabIndex < oldTabIndex);

		IADTabpanel oldTabpanel = curTabpanel;
		IADTabpanel newTabpanel = adTab.getSelectedTabpanel();
		curTab = newTabpanel.getGridTab();
		
		if (oldTabpanel != null)
			oldTabpanel.activate(false);
		newTabpanel.activate(true);

		if (!back)
		{
		    newTabpanel.query();
		}
		else
		{
		    newTabpanel.refresh();
		}
		
		curTabIndex = newTabIndex;
		curTabpanel = newTabpanel;
		
		if (curTabpanel instanceof ADSortTab) 
		{
			((ADSortTab)curTabpanel).registerAPanel(this);
		}

		updateToolbar();
		
		return true;
	}

	private void updateToolbar() 
	{
		toolbar.enableChanges(curTab.isReadOnly());
		toolbar.enabledNew(curTab.isInsertRecord());
		toolbar.enableCopy(curTab.isInsertRecord());

		toolbar.enableTabNavigation(curTabIndex > 0, 
		        curTabIndex < (adTab.getTabCount() - 1));
	}

    public void dataStatusChanged(DataStatusEvent e)
    {
    	//ignore non-ui thread event for now.
    	if (Executions.getCurrent() == null)
    		return;
    	
        logger.info(e.getMessage());
        String dbInfo = e.getMessage();
        if (curTab != null && curTab.isQueryActive())
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
                if (pos != -1)  // replace CR/NL
                    sb.replace(pos, pos+1, " - ");
                statusBar.setStatusLine (sb.toString (), e.isError ());
            }
        }

        //  Confirm Error
        if (e.isError() && !e.isConfirmed())
        {
            FDialog.error(curWindowNo, null, e.getAD_Message(), e.getInfo());
            e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        }
        //  Confirm Warning
        else if (e.isWarning() && !e.isConfirmed())
        {
            FDialog.warn(curWindowNo, null, e.getAD_Message(), e.getInfo());
            e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        }

        //  update Navigation
        boolean firstRow = e.isFirstRow();
        boolean lastRow = e.isLastRow();
        toolbar.enableFirstNavigation(!firstRow && !curTab.isSortTab());
        toolbar.enableLastNavigation(!lastRow && !curTab.isSortTab());

        //  update Change
        boolean changed = e.isChanged() || e.isInserting();
        boolChanges = changed;
        boolean readOnly = curTab.isReadOnly();
        boolean insertRecord = !readOnly;
        
        if (insertRecord)
        {
            insertRecord = curTab.isInsertRecord();
        }
        toolbar.enabledNew(!changed && insertRecord && !curTab.isSortTab());
        toolbar.enableCopy(!changed && insertRecord && !curTab.isSortTab());
        toolbar.enableRefresh(!changed);
        toolbar.enableDelete(!changed && !readOnly && !curTab.isSortTab());
        //
        if (readOnly && curTab.isAlwaysUpdateField())
        {
            readOnly = false;
        }
        toolbar.enableIgnore(changed && !readOnly);
        toolbar.enableSave(changed && !readOnly);
        
        //
        //  No Rows
        if (e.getTotalRows() == 0 && insertRecord) 
        {
            toolbar.enabledNew(true);
            toolbar.enableDelete(false);
            toolbar.enableDeleteSelection(false);
        }
        else 
        {
            toolbar.enableDeleteSelection(true);
        }

        //  History (on first Tab only)
        if (isFirstTab())
        {
            toolbar.getButton("HistoryRecords").setPressed(!curTab.isOnlyCurrentRows());
        }

        //  Transaction info
        String trxInfo = curTab.getTrxInfo();
        if (trxInfo != null)
        {
            statusBar.setInfo(trxInfo);
        }

        //  Check Attachment
        boolean canHaveAttachment = curTab.canHaveAttachment();       //  not single _ID column
        //
        if (canHaveAttachment && e.isLoading() && 
                curTab.getCurrentRow() > e.getLoadedRows())
        {
            canHaveAttachment = false;
        }
        if (canHaveAttachment && curTab.getRecord_ID() == -1)    //   No Key
        {
            canHaveAttachment = false;
        }
        if (canHaveAttachment)
        {
            toolbar.enableAttachment(true);
            toolbar.getButton("Attachment").setPressed(curTab.hasAttachment());
        }
        else
        {
            toolbar.enableAttachment(false);
        }
        
        toolbar.getButton("Find").setPressed(curTab.isQueryActive());
        //  Lock Indicator
       /* if (m_isPersonalLock)
        {
            aLock.setPressed(m_curTab.isLocked());
        }*/

        adTab.evaluate(e);
        
        toolbar.enablePrint(true);
        toolbar.enableReport(true);
    }

    public boolean isFirstTab()
    {
        int selTabIndex = adTab.getSelectedIndex();
        return (selTabIndex == 0);
    }

    /**
     * @see ToolbarListener#onRefresh()
     */
    public void onRefresh()
    {
        curTab.dataRefreshAll();
        curTabpanel.dynamicDisplay(0);
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
        if (!curTab.isInsertRecord())
        {
            logger.warning("Insert Record disabled for Tab");
            return;
        }
        
        newRecord = curTab.dataNew(false);
        if (newRecord)
        {
            curTabpanel.dynamicDisplay(0);
            toolbar.enableChanges(false);
            toolbar.enableDelete(false);
            toolbar.enableDeleteSelection(false);
            toolbar.enableNavigation(false);
            toolbar.enableTabNavigation(false);
            toolbar.enableIgnore(true);
            toolbar.enablePrint(true);
            toolbar.enableReport(true);
        }
        else
        {
            logger.severe("Could not create new record");
        }
    }
    
    // Elaine 2008/11/19
    /**
     * @see ToolbarListener#onCopy()
     */
    public void onCopy()
    {
        if (!curTab.isInsertRecord())
        {
            logger.warning("Insert Record disabled for Tab");
            return;
        }
        
        newRecord = curTab.dataNew(true);
        if (newRecord)
        {
            curTabpanel.dynamicDisplay(0);
            toolbar.enableChanges(false);
            toolbar.enableDelete(false);
            toolbar.enableDeleteSelection(false); // Elaine 2008/12/02
            toolbar.enableNavigation(false);
            toolbar.enableTabNavigation(false);
            toolbar.enableIgnore(true);
            toolbar.enablePrint(true);
            toolbar.enableReport(true);
        }
        else
        {
            logger.severe("Could not create new record");
        }
    }
    //
    
    /**
     * @see ToolbarListener#onFind()
     */
    public void onFind()
    {
        if (curTab == null)
            return;
        
        //  Gets Fields from AD_Field_v
        GridField[] findFields = GridField.createFields(ctx, curTab.getWindowNo(), 0,curTab.getAD_Tab_ID());
        FindWindow find = new FindWindow (curTab.getWindowNo(), curTab.getName(),
            curTab.getAD_Table_ID(), curTab.getTableName(), 
            curTab.getWhereExtended(), findFields, 1, curTab.getAD_Tab_ID());
        AEnv.showWindow(find);
        MQuery query = find.getQuery();
        
        find = null;

        //  Confirmed query
        if (query != null)
        {
            m_onlyCurrentRows = false;          //  search history too
            curTab.setQuery(query);
            curTabpanel.query(m_onlyCurrentRows, m_onlyCurrentDays, 0);   //  autoSize
        }
        
        curTab.dataRefresh(); // Elaine 2008/07/25
    }
    
    /**
     * @see ToolbarListener#onIgnore()
     */
    public void onIgnore()
    {
    	if (curTab.isSortTab())
    	{
    		curTabpanel.refresh();
    		toolbar.enableIgnore(false);
    	}
    	else
    	{
	        curTab.dataIgnore();
	        curTab.dataRefresh();
	        curTabpanel.dynamicDisplay(0);
	        toolbar.enableIgnore(false);
    	}
    }
    
    /**
     * @see ToolbarListener#onSave()
     */
    public void onSave()
    {
    	if (curTab.isSortTab())
    	{
    		((ADSortTab)curTabpanel).saveData();
    		toolbar.enableSave(true);	//	set explicitly
    		toolbar.enableIgnore(false);
    	}
    	else
    	{
    		if (curTab.getCommitWarning() != null && curTab.getCommitWarning().trim().length() > 0)
    		{
    			if (!FDialog.ask(curWindowNo, this.getComponent(), "SaveChanges?", curTab.getCommitWarning()))
    			{
    				return;
    			}
    		}
	    	boolean retValue = curTab.dataSave(true);
	        
	        if (!retValue)
	        {
	        	//actual error will prompt in the dataStatusChanged event
//	            FDialog.error(curWindowNo, parent, "SaveIgnored");
	            statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "SaveIgnored"), true);
	        }
	        curTabpanel.dynamicDisplay(0);
    	}
    }
    
    /**
     * @see ToolbarListener#onDelete()
     */
    public void onDelete()
    {
        if (curTab.isReadOnly())
        {
            return;
        }
        
        if (FDialog.ask(curWindowNo, null, "DeleteRecord?"))
        {
            if (!curTab.dataDelete())
            {
                FDialog.error(curWindowNo, "Could not delete record", "Error");
            }
        }
        curTabpanel.dynamicDisplay(0);
    }
    
    // Elaine 2008/12/01
	/**
	 * @see ToolbarListener#onDelete()
	 */
    public void onDeleteSelection()
	{
		if (curTab.isReadOnly())
        {
            return;
        }
		
		//show table with deletion rows -> value, name...
		final Window messagePanel = new Window();
		messagePanel.setBorder("normal");
		messagePanel.setWidth("600px");
		messagePanel.setTitle(Msg.getMsg(Env.getCtx(), "Find").replaceAll("&", "") + ": " + title);
        messagePanel.setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
        messagePanel.setClosable(true);
        messagePanel.setSizable(true);
		
		final Listbox listbox = new Listbox();
		listbox.setHeight("400px");
		
		// Display the first 5 fields data exclude Organization, Client and YesNo field data
		Vector<String> columnNames = new Vector<String>();
		GridField[] fields = curTab.getFields();
		for(int i = 0, count = 0; i < fields.length && count < 5; i++)
		{
			GridField field = fields[i];
			if(field.getColumnName().equalsIgnoreCase("AD_Org_ID") 
					|| field.getColumnName().equalsIgnoreCase("AD_Client_ID")
					|| field.getDisplayType() == DisplayType.YesNo)
				continue;
			columnNames.add(field.getColumnName());
			count++;
		}
		
		Vector<String> data = new Vector<String>();
		int noOfRows = curTab.getRowCount();
		for(int i=0; i<noOfRows; i++)
		{
			StringBuffer displayValue = new StringBuffer();
			if("".equals(curTab.getKeyColumnName()))
			{
				ArrayList<String> parentColumnNames = curTab.getParentColumnNames();
				for (Iterator<String> iter = parentColumnNames.iterator(); iter.hasNext();) 
				{
					String columnName = iter.next();
					GridField field = curTab.getField(columnName);
					if(field.isLookup()){
						Lookup lookup = field.getLookup();
						if (lookup != null){
							displayValue = displayValue.append(lookup.getDisplay(curTab.getValue(i,columnName))).append(" | ");
						} else {
							displayValue = displayValue.append(curTab.getValue(i,columnName)).append(" | ");
						}
					} else {
						displayValue = displayValue.append(curTab.getValue(i,columnName)).append(" | ");
					}
				}
			} else {
				displayValue = displayValue.append(curTab.getValue(i,curTab.getKeyColumnName()));
			}

			for(int j=0; j < columnNames.size(); j++)
			{
				Object value = curTab.getValue(i, columnNames.get(j));
				if(value == null) continue; // skip when value is null
				String text = value.toString().trim();
				if(text.length() == 0) continue; // skip when value is empty
				if(text.length() > 30)
					text = text.substring(0, 30); // display the first 30 characters
				displayValue = displayValue.append(" | ").append(text);
			}

			data.add(displayValue.toString());
		}
		
		for(int i = 0; i < data.size(); i++)
		{
			String record = data.get(i);
			listbox.appendItem(record, record);
		}
		
		listbox.setMultiple(true);
		messagePanel.appendChild(listbox);

		Div div = new Div();
		div.setAlign("center");
		messagePanel.appendChild(div);
		
		Hbox hbox = new Hbox();
		div.appendChild(hbox);
		
		Button btnOk = new Button();
		btnOk.setLabel("OK");
		btnOk.setImage("/images/Ok16.png");
		btnOk.addEventListener(Events.ON_CLICK, new EventListener() 
		{
			public void onEvent(Event event) throws Exception 
			{
				if (FDialog.ask(curWindowNo, messagePanel, "DeleteSelection"))
		        {
					logger.fine("ok");
					Set selectedValues = listbox.getSelectedItems();
					if(selectedValues != null)
					{
						for(Iterator<Listitem> iter = selectedValues.iterator(); iter.hasNext();)
						{
							Listitem li = iter.next();
							if(li != null)
								logger.fine((String) li.getValue());
						}
					}
					
					int[] indices = listbox.getSelectedIndices();
					Arrays.sort(indices);
					int offset = 0;
					for (int i = 0; i < indices.length; i++) 
					{
						curTab.navigate(indices[i]-offset);
						if (curTab.dataDelete())
						{
							offset++;
						}
					}
					curTabpanel.dynamicDisplay(0);
					
		            messagePanel.dispose();
		        } else {
					logger.fine("cancel");
				}
			}
		});
		hbox.appendChild(btnOk);
			
		Button btnCancel = new Button();
		btnCancel.setLabel("Cancel");
		btnCancel.setImage("/images/Cancel16.png");
		btnCancel.addEventListener(Events.ON_CLICK, new EventListener() 
		{
			public void onEvent(Event event) throws Exception 
			{
				messagePanel.dispose();
			}
		});
		hbox.appendChild(btnCancel);
				
		AEnv.showWindow(messagePanel);
	}
	//

    /**
     * @see ToolbarListener#onPrint()
     */
	public void onPrint() {
		//Get process defined for this tab
		int AD_Process_ID = curTab.getAD_Process_ID();
		//log.info("ID=" + AD_Process_ID);

		//	No report defined
		if (AD_Process_ID == 0)
		{
			onReport();
			
			return;
		}

		//TODO: cmd_save(false) -> onSave ?
		//onSave();
		//
		int table_ID = curTab.getAD_Table_ID();
		int record_ID = curTab.getRecord_ID();
		
		if (!getComponent().getDesktop().isServerPushEnabled())
			getComponent().getDesktop().enableServerPush(true);
		
		ProcessModalDialog dialog = new ProcessModalDialog(null,this.getTitle(),this,0,
				AD_Process_ID,table_ID, record_ID, true);
		if (dialog.isValid()) {
			dialog.setPosition("center");
			try {
				dialog.setPage(this.getComponent().getPage());
				dialog.doModal();
			} 
			catch (InterruptedException e) {
			}
		}
	}

	/**
     * @see ToolbarListener#onReport()
     */
	public void onReport() {
		if (!MRole.getDefault().isCanReport(curTab.getAD_Table_ID()))
		{
			FDialog.error(curWindowNo, parent, "AccessCannotReport");
			return;
		}
		
		//TODO: cmd_save(false); -> onSave ?

		//	Query
		MQuery query = new MQuery(curTab.getTableName());
		//	Link for detail records
		String queryColumn = curTab.getLinkColumnName();
		//	Current row otherwise
		if (queryColumn.length() == 0)
			queryColumn = curTab.getKeyColumnName();
		//	Find display
		String infoName = null;
		String infoDisplay = null;
		for (int i = 0; i < curTab.getFieldCount(); i++)
		{
			GridField field = curTab.getField(i);
			if (field.isKey())
				infoName = field.getHeader();
			if ((field.getColumnName().equals("Name") || field.getColumnName().equals("DocumentNo") )
				&& field.getValue() != null)
				infoDisplay = field.getValue().toString();
			if (infoName != null && infoDisplay != null)
				break;
		}
		if (queryColumn.length() != 0)
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

		new WReport (curTab.getAD_Table_ID(), query, toolbar.getEvent().getTarget(), curWindowNo);
		
	}
	
	/**
     * @see ToolbarListener#onZoomAcross()
     */
	public void onZoomAcross() {
		if (toolbar.getEvent() != null)
		{
			int record_ID = curTab.getRecord_ID();
			if (record_ID <= 0)
				return;

			//	Query
			MQuery query = new MQuery();
			//	Current row
			String link = curTab.getKeyColumnName();
			//	Link for detail records
			if (link.length() == 0)
				link = curTab.getLinkColumnName();
			if (link.length() != 0)
			{
				if (link.endsWith("_ID"))
					query.addRestriction(link, MQuery.EQUAL,
						new Integer(Env.getContextAsInt(ctx, curWindowNo, link)));
				else
					query.addRestriction(link, MQuery.EQUAL,
						Env.getContext(ctx, curWindowNo, link));
			}
			new WZoomAcross (toolbar.getEvent().getTarget(), curTab.getTableName(), query);
		}
	}
    
	// Elaine 2008/07/17
	/**
     * @see ToolbarListener#onActiveWorkflows()
     */
	public void onActiveWorkflows() {
		if (toolbar.getEvent() != null)
		{
			if (curTab.getRecord_ID() <= 0) 
				return;
			else 
				AEnv.startWorkflowProcess(curTab.getAD_Table_ID(), curTab.getRecord_ID());
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
			if (curTab.getRecord_ID() <= 0) 
				return;
			
			int C_BPartner_ID = 0;
			Object bpartner = curTab.getValue("C_BPartner_ID");
			if(bpartner != null)
				C_BPartner_ID = Integer.valueOf(bpartner.toString());
			
			new WRequest(toolbar.getEvent().getTarget(), curTab.getAD_Table_ID(), curTab.getRecord_ID(), C_BPartner_ID);
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
			if (curTab.getRecord_ID() <= 0) 
				return;
			
			new WArchive(toolbar.getEvent().getTarget(), curTab.getAD_Table_ID(), curTab.getRecord_ID());
		}
	}
	
	//
    
	/**************************************************************************
	 *	Start Button Process
	 *  @param vButton button
	 */
	private void actionButton (WButtonEditor wButton)
	{
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
		
		if (curTab.needSave(true, false))
			onSave();

		int table_ID = curTab.getAD_Table_ID();

		//	Record_ID
		
		int record_ID = curTab.getRecord_ID();
		
		//	Record_ID - Language Handling
		
		if (record_ID == -1 && curTab.getKeyColumnName().equals("AD_Language"))
			record_ID = Env.getContextAsInt (ctx, curWindowNo, "AD_Language_ID");

		//	Record_ID - Change Log ID
		
		if (record_ID == -1 
			&& (wButton.getProcess_ID() == 306 || wButton.getProcess_ID() == 307))
		{
			Integer id = (Integer)curTab.getValue("AD_ChangeLog_ID");
			record_ID = id.intValue();
		}
		
		//	Ensure it's saved
		
		if (record_ID == -1 && curTab.getKeyColumnName().endsWith("_ID"))
		{
			FDialog.error(curWindowNo, parent, "SaveErrorRowNotFound");
			return;
		}

		//	Pop up Payment Rules

		if (col.equals("PaymentRule"))
		{
			WPayment vp = new WPayment(curWindowNo, curTab, wButton);
			
			
			if (vp.isInitOK())		//	may not be allowed
			{
				vp.setVisible(true);
				AEnv.showWindow(vp);
			}
			//vp.dispose();
			
			if (vp.needSave())
			{
				onSave();
				onRefresh();
			}
		} // PaymentRule

		//	Pop up Document Action (Workflow)

		else if (col.equals("DocAction"))
		{
			WDocActionPanel win = new WDocActionPanel(curTab);
			if (win.getNumberOfOptions() == 0)
			{
				logger.info("DocAction - No Options");
				return;
			}
			else
			{
				AEnv.showWindow(win);
				
				if (!win.isStartProcess())
					return;
				
				//batch = win.isBatch();
				startWOasking = true;
				//vda.dispose();
			}		
		} // DocAction

		//  Pop up Create From
		
		else if (col.equals("CreateFrom"))
		{
			// curWindowNo
			WCreateFrom wcf = WCreateFrom.create(curTab);
			
			if (wcf != null)
			{
				if (wcf.isInitOK())
				{
					wcf.setVisible(true);
					curTab.dataRefresh();
				}
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
					return;
				}
			}

			//  Check Post Status
			Object ps = curTab.getValue("Posted");
			
			if (ps != null && ps.equals("Y"))
			{
				new org.adempiere.webui.acct.WAcctViewer(Env.getContextAsInt (ctx, curWindowNo, "AD_Client_ID"), 
						curTab.getAD_Table_ID(), curTab.getRecord_ID());
			}
			else
			{
				if (FDialog.ask(curWindowNo, null, "PostImmediate?"))
				{
					boolean force = ps != null && !ps.equals ("N");		//	force when problems
					
					String error = AEnv.postImmediate (curWindowNo, Env.getAD_Client_ID(ctx),
						curTab.getAD_Table_ID(), curTab.getRecord_ID(), force);
					
					curTab.dataRefresh();
					
					if (error != null)
						FDialog.error(curWindowNo, null, "PostingError-N", error);
				}
			}
			return;
		}   //  Posted
			
		/**
		 *  Start Process ----
		 */

		logger.config("Process_ID=" + wButton.getProcess_ID() + ", Record_ID=" + record_ID);
		
		if (wButton.getProcess_ID() == 0)
			return;
		
		//	Save item changed
		
		if (curTab.needSave(true, false))
			this.onSave();

		// hengsin - [ 1639242 ] Inconsistent appearance of Process/Report Dialog
		// globalqss - Add support for Don't ShowHelp option in Process
		// this code must be changed if integrated the parameters and help in only one window
		/*
		MProcess pr = new MProcess(m_ctx, vButton.getProcess_ID(), null);
		if (pr.getShowHelp() != null && pr.getShowHelp().equals("N")) {
			startWOasking = true;
		}
		// end globalqss
		
		//	Ask user to start process, if Description and Help is not empty
		
		if (!startWOasking && !(vButton.getDescription().equals("") && vButton.getHelp().equals("")))
			if (!ADialog.ask(m_curWindowNo, this, "StartProcess?", 
				//	"<b><i>" + vButton.getText() + "</i></b><br>" +
				vButton.getDescription() + "\n" + vButton.getHelp()))
				return;
		//
		String title = vButton.getDescription();
		if (title == null || title.length() == 0)
			title = vButton.getName();
		ProcessInfo pi = new ProcessInfo (title, vButton.getProcess_ID(), table_ID, record_ID);
		pi.setAD_User_ID (Env.getAD_User_ID(m_ctx));
		pi.setAD_Client_ID (Env.getAD_Client_ID(m_ctx));
		pi.setIsBatch(batch);

	//	Trx trx = Trx.get(Trx.createTrxName("AppsPanel"), true);
		ProcessCtl.process(this, m_curWindowNo, pi, null); //  calls lockUI, unlockUI
		*/

		if (!getComponent().getDesktop().isServerPushEnabled())
			getComponent().getDesktop().enableServerPush(true);
		
		ProcessModalDialog dialog = new ProcessModalDialog(null, 
				Env.getHeader(ctx, curWindowNo), this, curWindowNo,  
				wButton.getProcess_ID(), table_ID, record_ID, startWOasking);
		
		if (dialog.isValid()) 
		{
			dialog.setWidth("500px");
			dialog.setVisible(true);
			dialog.setPosition("center");
			AEnv.showWindow(dialog);           
		}
//         curTab.dataRefresh();
//         curTabpanel.dynamicDisplay(0);
	} // actionButton
   
	public void actionPerformed(ActionEvent event) 
	{
		 if (event.getSource() instanceof WButtonEditor)
	     {
	      	actionButton((WButtonEditor)event.getSource());
	     }
	}
	
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
				Executions.activate(getComponent().getDesktop());
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
		&& pi.getAD_Process_ID() != curTab.getAD_Process_ID()
		&& pi.isReportingProcess() == false;
		//
		//  Process Result
					
		if (Executions.getCurrent() != null)
		{
			if (notPrint)		//	refresh if not print 
			{
				updateUI(pi);
			}
			Clients.showBusy(null, false);
		}
		else
		{
			try {
				//get full control of desktop
				Executions.activate(getComponent().getDesktop());
				try {                    
					if (notPrint)		//	refresh if not print
					{
						updateUI(pi);
					}
                	Clients.showBusy(null, false);
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
		curTab.dataRefresh();
		//	Timeout
		if (pi.isTimeout())		//	set temporarily to R/O
			Env.setContext(ctx, curWindowNo, "Processed", "Y");
		curTabpanel.dynamicDisplay(0);
		//	Update Status Line
		String summary = pi.getSummary();
		if (summary != null && summary.indexOf('@') != -1)
			pi.setSummary(Msg.parseTranslation(Env.getCtx(), summary));
		statusBar.setStatusLine(pi.getSummary(), pi.isError());
		//	Get Log Info
		ProcessInfoUtil.setLogFromDB(pi);
		String logInfo = pi.getLogInfo();
		if (logInfo.length() > 0)
			FDialog.info(curWindowNo, this.getComponent(), Env.getHeader(ctx, curWindowNo),
				pi.getTitle() + "<br>" + logInfo);
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
		return curTab;
	}
	
	/**
	 * @return windowNo
	 */
	public int getWindowNo() {
		return curWindowNo;
	}
	
}
