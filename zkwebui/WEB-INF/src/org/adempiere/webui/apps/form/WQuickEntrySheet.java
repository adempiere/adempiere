package org.adempiere.webui.apps.form;


import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.panel.ADTabPanel;
import org.adempiere.webui.panel.AbstractADWindowPanel;
import org.adempiere.webui.panel.StatusBarPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Separator;

/**
 * Quick sheet window
 * 
 * @author Jobrian Trinidad
 * @author <a href="mailto:sachin.bhimani89@gmail.com">Sachin Bhimani</a>
 * @since 2016-06-30
 */
public class WQuickEntrySheet extends Window implements EventListener, DataStatusListener
{

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 6566077952951760414L;
	private static CLogger			log					= CLogger.getCLogger(WQuickEntrySheet.class);

	public Trx						trx					= null;

	private Borderlayout			selPanel			= new Borderlayout();
	private Grid					selNorthPanel		= GridFactory.newGridLayout();
	private ConfirmPanel			selSouthPanel		= new ConfirmPanel(true, true, false, false, false, false);
	private Button					bDelete				= selSouthPanel.createButton(ConfirmPanel.A_DELETE);
	private Button					bSave				= selSouthPanel.createButton(ConfirmPanel.A_SAVE);
	private Button					bIgnore				= selSouthPanel.createButton(ConfirmPanel.A_IGNORE);

	private GridPanel			gridPanel;
	private GridTab					gridTab;
	private ADTabPanel				tabPanel;
	private AbstractADWindowPanel	abstractADWindowPanel;
	/** store object of GridTab for Grid view form */
	private GridTab					formGridTab;
	private StatusBarPanel statusBar = new StatusBarPanel();
	private Panel southPanel = new Panel();
	
	public static final String		CNTRL_KEYS				= "^q^#enter";

	private static final int KEYBOARD_KEY_RETURN = 13;
	private static final int KEYBOARD_KEY_Q = 81;
	private Keylistener	keyListener;
	

	public WQuickEntrySheet(GridPanel grid, GridTab gTab, ADTabPanel tPanel, AbstractADWindowPanel abstractPanel,
			int onlyCurrentDays, boolean onlyCurrentRows)
	{
		super();
		gridPanel = grid;
		gridTab = gTab;
		tabPanel = tPanel;
		abstractADWindowPanel = abstractPanel;

		gridTab.addDataStatusListener(this);
		gridTab.addDataStatusListener(tabPanel);
		gridTab.enableEvents();
		gridPanel.setADTabPanel(tPanel);
		gridPanel.setADWindowPanel(abstractADWindowPanel);
		gridPanel.addKeyListener();
		gridTab.setQuickEntry(true);

		formGridTab = tabPanel.getGridTab();
		tabPanel.setGridTab(gridTab);
		tabPanel.query(onlyCurrentRows, onlyCurrentDays, MRole.getDefault().getMaxQueryRecords());
//		statusBar = ;
		trx = Trx.get(Trx.createTrxName("QuickEntry"), true);
		gridTab.getMTable().setTrxName(trx.getTrxName());

		gridPanel.init(gridTab);

		keyListener = new Keylistener();
			appendChild(keyListener);
		keyListener.setCtrlKeys(CNTRL_KEYS);
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);
		
		initForm();
		setWidth("70%");
		setHeight("80%");
	}

	protected void initForm()
	{
		initZk();
		createNewRow();
		AEnv.showCenterScreen(this);
		//gridPanel.refresh(gridTab);
	}

	/**
	 * if no any row(s) present then it will create new one.
	 */
	private void createNewRow()
	{
		int row = gridTab.getRowCount();
		if (row <= 0)
		{
			gridTab.dataIgnore();
			if (gridTab.isInsertRecord())
				gridPanel.createNewLine();
			else
			{
//				gridPanel.setStatusLine("Cannot insert records on the tab.", true, true);
//				gridPanel.dispose();
//				SessionManager.closeTab(gridTab.getAD_Tab_ID());

				trx.rollback();
				trx.close();
				trx = null;
				gridTab.getMTable().setTrxName(null);

				throw new AdempiereException("Cannot insert records on the tab.");
			}
		}
	}

	private void initZk()
	{
		selPanel.setWidth("99%");
		selPanel.setHeight("99%");

		North north = new North();
		north.setFlex(true);
		north.setStyle("border: none");
		north.appendChild(selNorthPanel);
		selPanel.appendChild(north);

		Center center = new Center();

		center.appendChild(gridPanel);
		center.setFlex(true);
		selPanel.appendChild(center);

		selSouthPanel.addActionListener(this);

		South south = new South();
		south.appendChild(southPanel);
		southPanel.appendChild(selSouthPanel);
		selPanel.appendChild(south);

		bSave.setEnabled(!gridTab.isReadOnly());
		bDelete.setEnabled(!gridTab.isReadOnly());
		bIgnore.setEnabled(!gridTab.isReadOnly());

		bSave.addEventListener(Events.ON_CLICK, this);
		bDelete.addEventListener(Events.ON_CLICK, this);
		bIgnore.addEventListener(Events.ON_CLICK, this);
		Keylistener keylistener = new Keylistener();
		selSouthPanel.getButton(ConfirmPanel.A_CANCEL).addEventListener(Events.ON_CANCEL, this);
		appendChild(keylistener);
		selSouthPanel.addComponentsLeft(bSave);
		selSouthPanel.addComponentsLeft(bDelete);
		selSouthPanel.addComponentsLeft(bIgnore);
		
		southPanel.appendChild(new Separator());
		statusBar = new StatusBarPanel();
		southPanel.appendChild(statusBar);
		
		setTitle(gridTab.getName());
		setMaximizable(true);
		setMaximized(false);
		setClosable(true);
		this.appendChild(selPanel);
		setWidth("70%");
		setHeight("80%");
		setPosition("center");
	}

	public void onEvent(Event event) throws Exception
	{
		boolean dispose = false;
		boolean isIgnore = false;
		if (event.getName().equals(Events.ON_CTRL_KEY) && event.getTarget() == keyListener) {
				
				KeyEvent keyEvent = (KeyEvent) event;
				int code = keyEvent.getKeyCode();
				boolean isCtrl = keyEvent.isCtrlKey();
				if (code == KEYBOARD_KEY_RETURN && isCtrl) {
					dispose = true; 
				}
				if(code == KEYBOARD_KEY_Q && isCtrl)
					isIgnore = true;
		 }
			if (event.getTarget() == selSouthPanel.getButton(ConfirmPanel.A_OK) || dispose)
			{
				super.dispose();
			}
			else if (event.getTarget() == selSouthPanel.getButton(ConfirmPanel.A_CANCEL) 
					|| isIgnore )
			{
				super.dispose();
			}
			else if (event.getTarget() == selSouthPanel.getButton(ConfirmPanel.A_SAVE))
			{
				onSave(true);
			}
			else if (event.getTarget() == selSouthPanel.getButton(ConfirmPanel.A_DELETE))
			{
				onDelete();
			}
			else if (event.getTarget() == selSouthPanel.getButton(ConfirmPanel.A_REFRESH))
			{
				onRefresh();
			}
			else if (event.getTarget() == selSouthPanel.getButton(ConfirmPanel.A_IGNORE))
			{
				onIgnore();
			}
		
		
	}

	

	private boolean onSave(boolean isShowError)
	{
		boolean save = false;
		ArrayList<Integer> rows = gridTab.getMTable().getRowChanged();
		if (rows.size() > 0)
		{
			//if (gridPanel.isNecessaryDataFill(rows.get(0), isShowError))
			//{
			save = gridTab.dataSave(true);
			if (!save)
			{
				String msg = CLogger.retrieveErrorString(null);
				if (msg != null)
				{
					statusBar.setStatusLine(Msg.getMsg(Env.getCtx(), msg), true, true);
				}
	        }  
		}
		abstractADWindowPanel.getToolbar().getCurrentPanel().afterSave(true);
		save = gridTab.needSave(true, true);
		if(!gridTab.isNew()) {
			updateToolbar(true);
		}
		trx.commit();
		
		return save;
	}

	private void onRefresh()
	{
		gridPanel.updateListIndex();
	}

	private void onIgnore()
	{
		if(trx != null) 
			trx.rollback();
//		gridPanel.setStatusLine("Changes rolled back", false, true);
		gridTab.dataIgnore();
		if (gridTab.getRowCount() <= 0)
			gridPanel.createNewLine();
		gridPanel.addKeyListener();
		gridPanel.updateListIndex();
		
		updateToolbar(true);
	}

	private void onDelete()
	{
		if (gridTab == null)
			return;

		if (gridTab.getCurrentRow() >= 0)
		{
		
			boolean istrue = FDialog.ask(gridTab.getWindowNo(), null, "DeleteRecord?");
			if (istrue)
			{
				gridTab.dataDelete();
				gridTab.dataRefresh(true);
//				gridPanel.setStatusLine(count + " Record(s) deleted.", false, true);
			}
		
			// if all records is deleted then it will show default with new
			// record.
			if (gridTab.getRowCount() <= 0)
				gridPanel.createNewLine();
			gridPanel.updateListIndex();

		}
	}

	@Override
	public void dispose()
	{
		onIgnore();
		
		gridTab.setQuickEntry(false);
		formGridTab.setQuickEntry(false);
		gridTab.removeDataStatusListener(this);
		gridTab.removeDataStatusListener(tabPanel);
		tabPanel.getGridTab().setQuickEntry(false);
		gridPanel.addKeyListener();
		tabPanel.getListPanel().addKeyListener();
		keyListener = null;
		
		tabPanel.setGridTab(formGridTab);
//		abstractADWindowPanel.getADTab().getSelectedTabpanel().refresh();
//		SessionManager.closeTab(gridTab.getAD_Tab_ID());

		trx.rollback();
		trx.close();
		trx = null;
		gridTab.getMTable().setTrxName(null);
		
//		String tabInfo = abstractADWindowPanel.getToolbar().getQuickHrchyTabInfo();
//		abstractADWindowPanel.setGridTab(formGridTab);
//		if (tabInfo.length() > 0)
//		{
//			abstractADWindowPanel.getToolbar().setQuickHrchyTabInfo(tabInfo.substring(0, tabInfo.length() - 1));
//			abstractADWindowPanel.onParentRecord();
//		}
//		abstractADWindowPanel.onRefreshFromQuickForm();
		
	}

	@Override
	public void dataStatusChanged(DataStatusEvent e) {
		abstractADWindowPanel.dataStatusChanged(e);
		// 
		log.info(e.getMessage());
        String dbInfo = e.getMessage();
        if (gridTab != null && gridTab.isQueryActive())
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
            e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
        }
        
        //  Confirm Warning
        else if (e.isWarning() && !e.isConfirmed())
        {
        	gridPanel.addKeyListener();
        	FDialog.warn(0, null, e.getAD_Message(), e.getInfo());

        	e.setConfirmed(true);   //  show just once - if MTable.setCurrentRow is involved the status event is re-issued
//            (gridPanel).focusCurrentCol();
            gridPanel.addKeyListener();
        }
        if(gridTab.isNew()) {
			updateToolbar(false);
		}
        else {
			updateToolbar(true);
		}
	}
	public void updateToolbar(boolean enabled) {
    	bDelete.setEnabled(enabled);
	}
	
}