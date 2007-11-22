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

import java.util.Properties;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.apps.WReport;
import org.adempiere.webui.apps.form.WCreateFrom;
import org.adempiere.webui.apps.form.WPayment;
import org.adempiere.webui.component.CWindowToolbar;
import org.adempiere.webui.component.FTabbox;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.event.ActionEvent;
import org.adempiere.webui.event.ActionListener;
import org.adempiere.webui.event.ToolbarListener;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.adempiere.webui.window.FindWindow;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridWindow;
import org.compiere.model.GridWindowVO;
import org.compiere.model.GridWorkbench;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.WebDoc;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Vbox;

/**
 * 
 * This class is based on org.compiere.apps.APanel written by Jorg Janke.
 * @author Jorg Janke
 * 
 * @author <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date Feb 25, 2007
 * @version $Revision: 0.10 $
 */
public class ADWindowPanel extends Vbox implements ToolbarListener,
        EventListener, DataStatusListener, ActionListener
{
    private static final long    serialVersionUID = 1L;

    private static final CLogger logger;

    static
    {
        logger = CLogger.getCLogger(ADWindowPanel.class);
    }

    private Properties           ctx;

    private GridWindow           gridWindow;

    private StatusBarPanel       statusBar;

    private FTabbox              tabbox;

    private GridWorkbench        workbench;

    private int                  curWindowNo;

    private GridTab              curTab;

    private boolean              m_onlyCurrentRows;

    private ADTabpanel            curTabpanel;

    private CWindowToolbar       toolbar;

    private int                  curTabIndex;

    private String               title;
    
    private boolean              newRecord;
    
    private boolean 			boolChanges = false;
    
    private GridPanel gridPanel;
    
    private boolean viewed = false;

	private boolean changesOccured = false;

	private int m_onlyCurrentDays = 0;
    
    public ADWindowPanel()
    {
    	
    }
    
    public ADWindowPanel(Properties ctx, int windowNo)
    {
        this.ctx = ctx;
        this.curWindowNo = windowNo;
        
        init(); 
    }
    
    public void showTabbox(boolean visible)
    {
    	if (visible)
    		tabbox.setVisible(true);
    	else
    		tabbox.setVisible(false);
    }
    
	private void init()
    {
        initComponents();
        
        Vbox vbox = new Vbox();
        vbox.appendChild(toolbar);
        vbox.appendChild(tabbox);
        vbox.appendChild(statusBar);
        vbox.appendChild(gridPanel);
        this.appendChild(vbox);
        tabbox.addEventListener(Events.ON_SELECT, this);
    }

    public StatusBarPanel getStatusBar()
    {
    	return statusBar;
    }
    
    private void initComponents()
    {
        /** Initalise toolbar */
        toolbar = new CWindowToolbar();
        toolbar.addListener(this);

        statusBar = new StatusBarPanel();

        tabbox = new FTabbox();
        
        gridPanel = new GridPanel(curWindowNo, this);
        gridPanel.showGrid(false);
    }

    public boolean initPanel(int adWindowId, MQuery query)
    {
        // Set AutoCommit for this Window
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

        /**
         * Window Tabs
         */
        int tabSize = gridWindow.getTabCount();
        for (int tab = 0; tab < tabSize; tab++)
        {
            gridWindow.initTab(tab);

            GridTab gTab = gridWindow.getTab(tab);
            Env.setContext(ctx, curWindowNo, tab, "TabLevel", Integer
                    .toString(gTab.getTabLevel()));

            ADTabpanel fTabPanel = new ADTabpanel();
            fTabPanel.init(this, curWindowNo, gTab, gridWindow);
            gTab.addDataStatusListener(this);
            tabbox.addTab(gTab, fTabPanel);

            // Query first tab
            if (tab == 0)
            {
                query = initialQuery(query, gTab);
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
                curTabpanel = fTabPanel;
                curTabIndex = tab;
            }
        }

        Env.setContext(ctx, curWindowNo, "WindowName", gridWindow.getName());
        curTab.getTableModel().setChanged(false);
        curTabIndex = 0;
        curTabpanel.query();
        curTabpanel.activate(true);

        if (tabbox.getTabCount() > 0)
        {
            tabbox.setSelectedIndex(0);
        }
        toolbar.enableTabNavigation(tabbox.getTabCount() > 1);
        toolbar.enableFind(true);
        tabbox.evaluate(null);
        this.appendChild(tabbox);

        if (gridWindow.isTransaction())
        {
        	toolbar.enableHistoryRecords(true);
        	history(1);
        }
        
        return true;
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
            GridField[] findFields = mTab.getFields();
            FindWindow find = new FindWindow(curWindowNo,
                    mTab.getName(), mTab.getAD_Table_ID(), mTab.getTableName(),
                    where.toString(), findFields, 10); // no query below 10
            find.setVisible(true);
            AEnv.showWindow(find);
            query = find.getQuery();  
            find = null;
        }
        return query;
    } // initialQuery
    
    public String getTitle()
    {
        return title;
    }

    public void onDetailRecord()
    {
        int maxInd = tabbox.getTabs().getChildren().size() - 1;
        int curInd = tabbox.getSelectedIndex();
        if (curInd < maxInd)
        {
            tabbox.setSelectedIndex(curInd + 1);
        }
    }

    public void onParentRecord()
    {
        int curInd = tabbox.getSelectedIndex();
        if (curInd > 0)
        {
            tabbox.setSelectedIndex(curInd - 1);
        }
    }

    public void onFirst()
    {
        curTab.navigate(0);
    }

    public void onLast()
    {
        curTab.navigate(curTab.getRowCount() - 1);
    }

    public void onNext()
    {
        curTab.navigateRelative(+1);
    }

    public void onPrevious()
    {
        curTab.navigateRelative(-1);
    }

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
    
    public void onAttachment()
    {
/*		int record_ID = curTab.getRecord_ID();
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
*/	}
    
    public void onGridToggle()
    {
    	//curTabpanel.switchRowPresentation();
    	
    	if (!viewed)
    	{
    		gridPanel.init(curTab);
    		viewed = true;
    	}
    	
    	if (changesOccured)
    	{
    		changesOccured = false;
    		gridPanel.clear();
    		gridPanel.init(curTab);
    	}
    	
    	if (tabbox.isVisible())
    	{
    		tabbox.setVisible(false);
    		gridPanel.showGrid(true);
    	}
    	else
    	{
    		tabbox.setVisible(true);
    		gridPanel.showGrid(false);
    	}
    	
    }
    
    public void onExit()
    {
    	String message = "Please save changes before closing";
    	
    	if (!boolChanges)
    	{
    		SessionManager.getAppDesktop().removeWindow();
    	}
    	else
    		FDialog.info(this.curWindowNo, this, message);
    }

    public boolean isAsap()
    {
        return true;
    }

    private void find()
    {
    	MQuery mquery = new MQuery(curTab.getAD_Table_ID());
    	
    }
    
    public void onEvent(Event event)
    {
        if (event.getTarget() instanceof Tab && tabbox.containsTab((Tab)event.getTarget()))
        {
           
            boolean back = false;

            int oldTabIndex = curTabIndex;
            int newTabIndex = tabbox.getSelectedIndex();

            if (oldTabIndex == newTabIndex)
            {
                return;
            }
            
            if (curTabpanel.isEditing())
            {
                FDialog.warn(curWindowNo, "Please save your changes before " +
                        "switching tabs!!!", title);
                tabbox.setSelectedIndex(curTabIndex);
                return;
            }

            if (!tabbox.updateSelectedIndex(oldTabIndex, newTabIndex))
            {
                FDialog.warn(curWindowNo, "TabSwitchJumpGo", title);
                return;
            }

            back = (newTabIndex < oldTabIndex);

            ADTabpanel oldTabpanel = curTabpanel;
            ADTabpanel newTabpanel = tabbox.getSelectedTabpanel();
            curTab = newTabpanel.getGridTab();

            if (!back)
            {
                newTabpanel.query();
            }
            else
            {
                newTabpanel.refresh();
            }

            oldTabpanel.activate(false);
            newTabpanel.activate(true);
            curTabIndex = newTabIndex;
            curTabpanel = newTabpanel;

            toolbar.enableChanges(curTab.isReadOnly());
            toolbar.enabledNew(curTab.isInsertRecord());

            toolbar.enableTabNavigation(curTabIndex > 0, 
                    curTabIndex < (tabbox.getTabCount() - 1));
        }
    }

    public void dataStatusChanged(DataStatusEvent e)
    {
       /* // update Navigation
        boolean firstRow = e.isFirstRow();
        boolean lastRow = e.isLastRow();
        toolbar.enableFirstNavigation(!firstRow);
        toolbar.enableLastNavigation(!lastRow);

        // update Change
        boolean changed = e.isChanged() || e.isInserting();
        boolean readOnly = curTab.isReadOnly();
        boolean insertRecord = !readOnly;
        if (insertRecord)
        {
            insertRecord = curTab.isInsertRecord();
        }

        toolbar.enabledNew(!changed && insertRecord);
        toolbar.enableRefresh(!changed);
        toolbar.enableDelete(!changed && !readOnly);

        if (readOnly && curTab.isAlwaysUpdateField())
        {
            readOnly = false;
        }

        lblRecords.setValue(e.getMessage());
        tabbox.evaluate(e);*/
        
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
            FDialog.error(curWindowNo, this, e.getAD_Message(), e.getInfo());
            e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        }
        //  Confirm Warning
        else if (e.isWarning() && !e.isConfirmed())
        {
            FDialog.warn(curWindowNo, this, e.getAD_Message(), e.getInfo());
            e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        }

        //  update Navigation
        boolean firstRow = e.isFirstRow();
        boolean lastRow = e.isLastRow();
        toolbar.enableFirstNavigation(!firstRow);
        toolbar.enableLastNavigation(!lastRow);

        //  update Change
        boolean changed = e.isChanged() || e.isInserting();
        boolChanges = changed;
        boolean readOnly = curTab.isReadOnly();
        boolean insertRecord = !readOnly;
        
        if (insertRecord)
        {
            insertRecord = curTab.isInsertRecord();
        }
        toolbar.enabledNew(!changed && insertRecord);
        toolbar.enableCopy(!changed && insertRecord);
        toolbar.enableRefresh(!changed);
        toolbar.enableDelete(!changed && !readOnly);
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

        //  Single-Multi
//        aMulti.setPressed(!m_curGC.isSingleRow());

        //  History (on first Tab only)
        if (isFirstTab())
        {
//            aHistory.setPressed(!curTab.isOnlyCurrentRows());
        }

        //  Transaction info
        String trxInfo = curTab.getTrxInfo();
        if (trxInfo != null)
        {
//            statusBar.setInfo(trxInfo);
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
            /*aAttachment.setPressed(m_curTab.hasAttachment());
            aChat.setEnabled(true);
            aChat.setPressed(m_curTab.hasChat());*/
        }
        else
        {
            toolbar.enableAttachment(false);
//            aChat.setEnabled(false);
        }
        //  Lock Indicator
       /* if (m_isPersonalLock)
        {
            aLock.setPressed(m_curTab.isLocked());
        }*/

        tabbox.evaluate(e);
        
        
        toolbar.enablePrint(true);
        toolbar.enableReport(true);
    }

    public boolean isFirstTab()
    {
        int selTabIndex = tabbox.getSelectedIndex();
        return (selTabIndex == 0);
    }

    public void onRefresh()
    {
        curTab.dataRefreshAll();
        curTabpanel.dynamicDisplay(0);
    }
    
    public void onHelp()
    {
    	WebDoc doc = gridWindow.getHelpDoc(true);
		SessionManager.getAppDesktop().showURL(doc, "Help", true);
    }
    
    public void onNew()
    {
        if (!curTab.isInsertRecord())
        {
            logger.warning("Insert Record disabled for Tab");
            return;
        }
        
        newRecord = curTab.dataNew (false);
        if (newRecord)
        {
            
        	curTabpanel.editRecord(true);
            curTabpanel.dynamicDisplay(0);
            toolbar.enableChanges(false);
            toolbar.enableDelete(false);
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
    
    public void onFind()
    {
        if (curTab == null)
            return;
        
        //  Gets Fields from AD_Field_v
        GridField[] findFields = GridField.createFields(ctx, curTab.getWindowNo(), 0,curTab.getAD_Tab_ID());
        FindWindow find = new FindWindow (curTab.getWindowNo(), curTab.getName(),
            curTab.getAD_Table_ID(), curTab.getTableName(), 
            curTab.getWhereExtended(), findFields, 1);
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
    }
    
    public void onIgnore()
    {
        curTab.dataIgnore();
        curTab.dataRefresh();
        curTabpanel.dynamicDisplay(0);
        curTabpanel.editRecord(false);
        toolbar.enableIgnore(false);
    }
    
    public void onEdit()
    {
        curTabpanel.editRecord(true);
        toolbar.enableIgnore(true);
        toolbar.enabledNew(false);
        toolbar.enableDelete(false);
        toolbar.enableNavigation(false);
        toolbar.enableTabNavigation(false);
        toolbar.enablePrint(true);
        toolbar.enableReport(true);
    }
    
    public void onSave()
    {
    	changesOccured = true;
        
    	boolean retValue = curTab.dataSave(true);
        
        if (!retValue)
        {
            FDialog.error(curWindowNo, this, "SaveIgnored");
            statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), "SaveIgnored"), true);
        }
        curTabpanel.dynamicDisplay(0);
    }
    
    public void onDelete()
    {
        if (curTab.isReadOnly())
        {
            return;
        }
        
        if (FDialog.ask(curWindowNo, this, "DeleteRecord?"))
        {
            if (!curTab.dataDelete())
            {
                FDialog.error(curWindowNo, "Could not delete record", "Error");
            }
        }
        curTabpanel.dynamicDisplay(0);
    }

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
		
		ProcessModalDialog dialog = new ProcessModalDialog(null,this.getTitle(),null,0,
				AD_Process_ID,table_ID, record_ID, true);
		if (dialog.isValid()) {
			dialog.setPosition("center");
			try {
				dialog.doModal();
			} 
			catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	public void onReport() {
		if (!MRole.getDefault().isCanReport(curTab.getAD_Table_ID()))
		{
			FDialog.error(curWindowNo, this, "AccessCannotReport");
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

		new WReport (curTab.getAD_Table_ID(), query, null, curWindowNo);
		
	}
    
	/**************************************************************************
	 *	Start Button Process
	 *  @param vButton button
	 */
	private void actionButton (WButtonEditor wButton)
	{
		logger.info(wButton.toString());

		boolean startWOasking = false;
		boolean batch = false;
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
			FDialog.error(curWindowNo, this, "SaveErrorRowNotFound");
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
			//if (win.getNumberOfOptions() == 0)
			//{
			//	vda.dispose ();
			//	log.info("DocAction - No Options");
			//	return;
			//}
			//else
			{
				win.setVisible(true);
				AEnv.showWindow(win);
				
				if (!win.isStartProcess())
					return;
				
				//batch = win.isBatch();
				startWOasking = true;
				//vda.dispose();
			}		} // DocAction

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
					FDialog.error(curWindowNo, this, "PostDocNotComplete");
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
				if (FDialog.ask(curWindowNo, this, "PostImmediate?"))
				{
					boolean force = ps != null && !ps.equals ("N");		//	force when problems
					
					String error = AEnv.postImmediate (curWindowNo, Env.getAD_Client_ID(ctx),
						curTab.getAD_Table_ID(), curTab.getRecord_ID(), force);
					
					curTab.dataRefresh();
					
					if (error != null)
						FDialog.error(curWindowNo, this, "PostingError-N", error);
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

		ProcessModalDialog dialog = new ProcessModalDialog(null, 
				Env.getHeader(ctx, curWindowNo), null, curWindowNo,  
				wButton.getProcess_ID(), table_ID, record_ID, startWOasking);
		
		if (dialog.isValid()) 
		{
			dialog.setWidth("500px");
			dialog.setVisible(true);
			dialog.setPosition("center");
			AEnv.showWindow(dialog);           
		}
         curTab.dataRefresh();
         curTabpanel.dynamicDisplay(0);
	} // actionButton
   
	public void actionPerformed(ActionEvent event) 
	{
		 if (event.getSource() instanceof WButtonEditor)
	     {
	      	actionButton((WButtonEditor)event.getSource());
	     }
	}
}
