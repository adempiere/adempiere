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
package org.adempiere.webui.panel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.adempiere.controller.SortTabController;
import org.adempiere.util.ListElement;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.CWindowToolbar;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.SimpleListModel;
import org.adempiere.webui.panel.ADTabPanel.EmbeddedPanel;
import org.adempiere.webui.panel.ADTabPanel.HorizontalEmbeddedPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zhtml.Span;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.event.ListDataEvent;

/**
 *	Tab to maintain Order/Sequence
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VSortTab.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				FR [ 1779410 ] VSortTab: display ID for not visible columns
 *
 * @author victor.perez@e-evolution.com, e-Evolution
 * 				FR [ 2826406 ] The Tab Sort without parent column
 *				<li> https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2826406&group_id=176962 </li>
 *              <li>Implement embedded or horizontal tab panel https://adempiere.atlassian.net/browse/ADEMPIERE-319</li>
 *              <li>New ADempiere 3.8.0 ZK Theme Light  https://adempiere.atlassian.net/browse/ADEMPIERE-320</li>
 * Zk Port
 * @author Low Heng Sin
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/990">
 * 		@see FR [ 990 ] Sort Tab is not MVC</a>
 *	@author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 1027 ] NPE when try navigate in tabs for new Table and Column Window https://github.com/adempiere/adempiere/issues/1027 </li>
 */
public class WSortTab extends Panel implements IADTabPanel {

	private static final long serialVersionUID = 4289328613547509587L;

	/**
	 *	Sort Tab Constructor
	 *
	 *  @param WindowNo Window No
	 *  @param GridTab
	 */
	public WSortTab(int WindowNo, GridTab gridTab) {
		log.config("SortOrder=" + gridTab.getAD_ColumnSortOrder_ID() + ", SortYesNo=" + gridTab.getAD_ColumnSortYesNo_ID());
		windowNo = WindowNo;
		this.gridTab = gridTab;
		this.setHeight("100%");
	}	//	VSortTab

	/**	Logger			*/
	static CLogger log = CLogger.getCLogger(WSortTab.class);
	private int			windowNo;

	private SortTabController	sortTabController;
	private AbstractADWindowPanel adWindowPanel = null;

	//	UI variables
	private Label noLabel = new Label();
	private Label yesLabel = new Label();
	private Button bAdd = new Button();
	private Button bRemove = new Button();
	private Button bUp = new Button();
	private Button bDown = new Button();
	
	private CWindowToolbar globalToolbar;

    private boolean isEmbedded = false;
	//
	SimpleListModel noModel = new SimpleListModel() {
		/**
		 *
		 */
		private static final long serialVersionUID = -8261235952902938774L;

		@Override
		public void addElement(Object obj) {
			Object[] elements = list.toArray();
			Arrays.sort(elements);
			int index = Arrays.binarySearch(elements, obj);
			if (index < 0)
				index = -1 * index - 1;
			if (index >= elements.length)
				list.add(obj);
			else
				list.add(index, obj);
			fireEvent(ListDataEvent.INTERVAL_ADDED, index, index);
		}
	};
	SimpleListModel yesModel = new SimpleListModel();
	Listbox noList = new Listbox();
	Listbox yesList = new Listbox();

	private GridTab gridTab;
	private boolean uiCreated;

	/**
	 * 	Static Layout
	 * 	@throws Exception
	 */
	private void init() throws Exception
	{
		this.setStyle("height: 100%; width: 100%;");
		//
		noLabel.setValue(Msg.getMsg(Env.getCtx(), "Available"));
		yesLabel.setValue(Msg.getMsg(Env.getCtx(), "Sequence"));
		noLabel.setStyle("text-align:center; display: block;");
		yesLabel.setStyle("text-align:center; display: block;");
		//	For List
		yesList.setHeight("100%");
		noList.setHeight("100%");
		yesList.setVflex(true);
		noList.setVflex(true);

		EventListener mouseListener = new EventListener()
		{

			public void onEvent(Event event) throws Exception
			{
				if (Events.ON_DOUBLE_CLICK.equals(event.getName()))
				{
					migrateValueAcrossLists(event);
				}
			}
		};
		yesList.addDoubleClickListener(mouseListener);
		noList.addDoubleClickListener(mouseListener);
		//
		EventListener actionListener = new EventListener()
		{
			public void onEvent(Event event) throws Exception {
				migrateValueAcrossLists(event);
			}
		};
		yesList.setSeltype("multiple");
		noList.setSeltype("multiple");

		bAdd.setImage("images/Detail16.png");
		bAdd.addEventListener(Events.ON_CLICK, actionListener);

		bRemove.setImage("images/Parent16.png");
		bRemove.addEventListener(Events.ON_CLICK, actionListener);

		EventListener crossListMouseListener = new DragListener();
		yesList.addOnDropListener(crossListMouseListener);
		noList.addOnDropListener(crossListMouseListener);
		yesList.setItemDraggable(true);
		noList.setItemDraggable(true);

		actionListener = new EventListener()
		{
			public void onEvent(Event event) throws Exception {
				migrateValueWithinYesList(event);
			}
		};

		bUp.setImage("images/Previous16.png");
		bUp.addEventListener(Events.ON_CLICK, actionListener);

		bDown.setImage("images/Next16.png");
		bDown.addEventListener(Events.ON_CLICK, actionListener);

		EventListener yesListMouseMotionListener = new EventListener()
		{
			public void onEvent(Event event) throws Exception {
				if (event instanceof DropEvent)
				{
					DropEvent me = (DropEvent) event;
					ListItem startItem = (ListItem) me.getDragged();
					ListItem endItem = (ListItem) me.getTarget();
					if (startItem.getListbox() == endItem.getListbox() && startItem.getListbox() == yesList)
					{
						int startIndex = yesList.getIndexOfItem(startItem);
						int endIndex = yesList.getIndexOfItem(endItem);
						Object endElement = yesModel.getElementAt(endIndex);
						Object element = yesModel.getElementAt(startIndex);
						yesModel.removeElement(element);
						endIndex = yesModel.indexOf(endElement);
						yesModel.add(endIndex, element);
						yesList.setSelectedIndex(endIndex);
						if ( yesList.getSelectedItem() != null)
						{
							AuFocus focus = new AuFocus(yesList.getSelectedItem());
							Clients.response(focus);
						}
						setIsChanged(true);
					}
				}
			}
		};
		yesList.addOnDropListener(yesListMouseMotionListener);

		ListHead listHead = new ListHead();
		listHead.setParent(yesList);
		ListHeader listHeader = new ListHeader();
		listHeader.appendChild(yesLabel);
		listHeader.setParent(listHead);

		listHead = new ListHead();
		listHead.setParent(noList);
		listHeader = new ListHeader();
		listHeader.appendChild(noLabel);
		listHeader.setParent(listHead);

		Span span = new Span();
		span.setParent(this);
		span.setStyle("height: 99%; display: inline-block; width: 40%;");
		span.appendChild(noList);
		Vbox vbox = new Vbox();
		vbox.appendChild(bAdd);
		vbox.appendChild(bRemove);
		span = new Span();
		span.setParent(this);
		span.setStyle("height: 99%; display: inline-block; width: 46px");
		span.appendChild(vbox);

		span = new Span();
		span.setParent(this);
		span.setStyle("height: 99%; display: inline-block; width: 40%");
		span.appendChild(yesList);
		vbox = new Vbox();
		vbox.appendChild(bUp);
		vbox.appendChild(bDown);
		span = new Span();
		span.setParent(this);
		span.setStyle("height: 99%; display: inline-block; width: 46px");
		span.appendChild(vbox);
	}	//	Init

	/**
	 * Load data for view
	 */
	public void loadData() {
		yesModel.removeAllElements();
		noModel.removeAllElements();
		sortTabController.loadData();
		//	
		setIsChanged(false);
		bAdd.setEnabled(sortTabController.isReadWrite());
		bRemove.setEnabled(sortTabController.isReadWrite());
		bUp.setEnabled(sortTabController.isReadWrite());
		bDown.setEnabled(sortTabController.isReadWrite());
		yesList.setEnabled(sortTabController.isReadWrite());
		noList.setEnabled(sortTabController.isReadWrite());

		yesList.setItemRenderer(yesModel);
		yesList.setModel(yesModel);
		noList.setItemRenderer(noModel);
		noList.setModel(noModel);
	}	//	loadData

	/**
	 * Set tab change status.
	 * @param value
	 */
	private void setIsChanged(boolean value) {
		if (adWindowPanel != null) {
			adWindowPanel.getToolbar().enableSave(value);
			adWindowPanel.getToolbar().enableIgnore(value);
		}
	}

	/**
	 * @param event
	 */
	void migrateValueAcrossLists (Event event)
	{
		Object source = event.getTarget();
		if (source instanceof ListItem) {
			source = ((ListItem)source).getListbox();
		}
		Listbox listFrom = (source == bAdd || source == noList) ? noList : yesList;
		Listbox listTo =  (source == bAdd || source == noList) ? yesList : noList;
		SimpleListModel lmFrom = (source == bAdd || source == noList) ?
				noModel : yesModel;
		SimpleListModel lmTo = (lmFrom == yesModel) ? noModel : yesModel;
		Set selectedItems = listFrom.getSelectedItems();
		List<ListElement> selObjects = new ArrayList<ListElement>();
		for (Object obj : selectedItems) {
			ListItem listItem = (ListItem) obj;
			int index = listFrom.getIndexOfItem(listItem);
			ListElement selObject = (ListElement)lmFrom.getElementAt(index);
			selObjects.add(selObject);
		}
		for (ListElement selObject : selObjects)
		{
			if (selObject == null || !selObject.isUpdateable())
				continue;

			lmFrom.removeElement(selObject);
			lmTo.addElement(selObject);

			//  Enable explicit Save
			setIsChanged(true);
		}

		for (ListElement selObject : selObjects)
		{
			int index = lmTo.indexOf(selObject);
			listTo.setSelectedIndex(index);
		}
		if ( listTo.getSelectedItem() != null)
		{
			AuFocus focus = new AuFocus(listTo.getSelectedItem());
			Clients.response(focus);
		}
	}	//	migrateValueAcrossLists

	/**
	 * 	Move within Yes List
	 *	@param event event
	 */
	void migrateValueWithinYesList (Event event)
	{
		Object[] selObjects = yesList.getSelectedItems().toArray();
		if (selObjects == null)
			return;
		int length = selObjects.length;
		if (length == 0)
			return;
		//
		int[] indices = yesList.getSelectedIndices();
		//
		boolean change = false;
		//
		Object source = event.getTarget();
		if (source == bUp)
		{
			for (int i = 0; i < length; i++) {
				int index = indices[i];
				if (index == 0)
					break;
				ListElement selObject = (ListElement) yesModel.getElementAt(index);
				ListElement newObject = (ListElement)yesModel.getElementAt(index - 1);
				if (!selObject.isUpdateable() || !newObject.isUpdateable())
					break;
				yesModel.setElementAt(newObject, index);
				yesModel.setElementAt(selObject, index - 1);
				indices[i] = index - 1;
				change = true;
			}
		}	//	up

		else if (source == bDown)
		{
			for (int i = length - 1; i >= 0; i--) {
				int index = indices[i];
				if (index  >= yesModel.getSize() - 1)
					break;
				ListElement selObject = (ListElement) yesModel.getElementAt(index);
				ListElement newObject = (ListElement)yesModel.getElementAt(index + 1);
				if (!selObject.isUpdateable() || !newObject.isUpdateable())
					break;
				yesModel.setElementAt(newObject, index);
				yesModel.setElementAt(selObject, index + 1);
				yesList.setSelectedIndex(index + 1);
				indices[i] = index + 1;
				change = true;
			}
		}	//	down

		//
		if (change) {
			yesList.setSelectedIndices(indices);
			setIsChanged(true);
			if ( yesList.getSelectedItem() != null)
			{
				AuFocus focus = new AuFocus(yesList.getSelectedItem());
				Clients.response(focus);
			}
		}
	}	//	migrateValueWithinYesList

	/* (non-Javadoc)
	 * @see org.compiere.grid.APanelTab#registerAPanel(APanel)
	 */
	public void registerAPanel (AbstractADWindowPanel panel)
	{
		adWindowPanel = panel;
	}	//	registerAPanel


	/** (non-Javadoc)
	 * @see org.compiere.grid.APanelTab#saveData()
	 */
	public void saveData()
	{
		//	BR [ 1027 ]
		if(adWindowPanel != null) {
			if (!adWindowPanel.getToolbar().isSaveEnable())
				return;
		} else {
			return;
		}
			
		log.fine("");
		String info = sortTabController.saveData(noModel.getElements(), yesModel.getElements());
		//
		if (info == null) {
			setIsChanged(false);
		} else {
			FDialog.error(windowNo, null, "SaveError", info.toString());
		}
	}	//	saveData

	/**
	 * 
	 */
	public void unregisterPanel ()
	{
		saveData();
		adWindowPanel = null;
	}	//	dispose

	/**
	 * @author eslatis
	 *
	 */
	private class DragListener implements EventListener
	{

		/**
		 * Creates a ADSortTab.DragListener.
		 */
		public DragListener()
		{
		}

		public void onEvent(Event event) throws Exception {
			if (event instanceof DropEvent)
			{
				DropEvent me = (DropEvent) event;

				ListItem endItem = (ListItem) me.getTarget();
				if (!(endItem.getListbox() == yesList))
				{
					return;		//	move within noList
				}

				ListItem startItem = (ListItem) me.getDragged();
				if (startItem.getListbox() == endItem.getListbox())
				{
					return; //move within same list
				}
				int startIndex = noList.getIndexOfItem(startItem);
				Object element = noModel.getElementAt(startIndex);
				noModel.removeElement(element);
				int endIndex = yesList.getIndexOfItem(endItem);
				yesModel.add(endIndex, element);
				//
				noList.clearSelection();
				yesList.clearSelection();

				yesList.setSelectedIndex(endIndex);
				//
				setIsChanged(true);
			}
		}
	}

	public void activate(boolean b) {
		if (b && !uiCreated) createUI();
	}

	public void createUI() {
		if (uiCreated) return;
		try
		{
			init();
			sortTabController = new SortTabController(windowNo, gridTab.getAD_Table_ID(), 
					gridTab.getAD_ColumnSortOrder_ID(), gridTab.getAD_ColumnSortYesNo_ID()) {
				
				@Override
				public void addItem(ListElement item) {
					if (item.isYes()) {
						yesModel.addElement(item);
					} else {
						noModel.addElement(item);
					}
				}
			};
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		uiCreated = true;
	}

	public void dynamicDisplay(int i) {
	}

	public void editRecord(boolean b) {
	}

	public String getDisplayLogic() {
		return gridTab.getDisplayLogic();
	}

	public GridTab getGridTab() {
		return gridTab;
	}

	public int getTabLevel() {
		return gridTab.getTabLevel();
	}

	public String getTitle() {
		return gridTab.getName();
	}

	public boolean isCurrent() {
		return gridTab != null ? gridTab.isCurrent() : false;
	}

	public void query() {
		loadData();
	}

	public void query(boolean currentRows, int currentDays, int i) {
		loadData();
	}

	public void refresh() {
		loadData();
	}

	public void switchRowPresentation() {
	}

	public String get_ValueAsString(String variableName) {
		return Env.getContext(Env.getCtx(), windowNo, variableName);
	}

	public void afterSave(boolean onSaveEvent) {
	}

	public boolean onEnterKey() {
		return false;
	}

	public CWindowToolbar getGlobalToolbar()
	{
		return globalToolbar;
	}
	
	public void setGlobalToolbar(CWindowToolbar globalToolbar) {
		this.globalToolbar = globalToolbar;
	}

	public void setUnselected(IADTabPanel panel)
    {
    	((HtmlBasedComponent)this).setStyle("border:none;");
		
		this.setWidth("100%");
		this.setHeight("100%");
		
    }
    
    public void setSelected(IADTabPanel panel)
    {
    	getGlobalToolbar().setCurrentPanel(panel);
    	((HtmlBasedComponent)this).setStyle("border-left: 7px solid #fa962f; border-top: 1px solid #fa962f; border-bottom: 1px solid #fa962f; border-right: 1px solid #fa962f;");		
		this.setWidth("99%");
		this.setHeight("98%");
    }

	@Override
	public Grid getGrid() {
		return null;
	}

	@Override
	public List<EmbeddedPanel> getIncludedPanel() {
		return null;
	}

	public List<HorizontalEmbeddedPanel> getHorizontalIncludedPanel() {
		return null;
	}
	@Override
	public int getWindowNo() {
		return windowNo;
	}

	@Override
	public GridPanel getListPanel() {
		return null;
	}

    public void setIsEmbedded(boolean isEmbedded)
    {
        this.isEmbedded=isEmbedded;
    }

    public boolean isEmbedded()
    {
        return isEmbedded;
    }
    
    public boolean isGridView() {return false;}
}	//ADSortTab

