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
package org.compiere.grid;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import org.adempiere.controller.SortTabController;
import org.adempiere.util.ListElement;
import org.compiere.apps.ADialog;
import org.compiere.apps.APanel;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Tab to maintain Order/Sequence
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VSortTab.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				FR [ 1779410 ] VSortTab: display ID for not visible columns
 * @author victor.perez@e-evolution.com, e-Evolution
 * 				FR [ 2826406 ] The Tab Sort without parent column
 *				<li> https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2826406&group_id=176962
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 93 ] 
 *		@see https://github.com/adempiere/adempiere/issues/93
 *		<a href="https://github.com/adempiere/adempiere/issues/990">
 * 		@see FR [ 990 ] Sort Tab is not MVC</a>
 */
public class VSortTab extends CPanel implements APanelTab {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2133358506913610514L;

	/**
	 * Overload Constructor
	 * @param windowNo
	 * @param tableId
	 * @param columnSortOrderId
	 * @param columnSortYesNoId
	 */
	public VSortTab(int windowNo, int tableId, int columnSortOrderId, int columnSortYesNoId) {
		this(windowNo, tableId, columnSortOrderId, columnSortYesNoId, 0);
	}
	/**
	 *	Tab Order Constructor
	 *
	 *  @param WindowNo Window No
	 *  @param AD_Table_ID Table No
	 *  @param AD_ColumnSortOrder_ID Sort Column
	 *  @param AD_ColumnSortYesNo_ID YesNo Column
	 */
	public VSortTab(int windowNo, int tableId, int columnSortOrderId, int columnSortYesNoId, int parentColumnId) {
		log.config("SortOrder=" + columnSortOrderId + ", SortYesNo=" + columnSortYesNoId);
		this.windowNo = windowNo;
		try {
			sortTabController = new SortTabController(windowNo, tableId, columnSortOrderId, columnSortYesNoId, parentColumnId) {
				
				@Override
				public void addItem(ListElement item) {
					if (item.isYes()) {
						yesModel.addElement(item);
					} else {
						noModel.addElement(item);
					}
				}
			};
			jbInit();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	VSortTab

	/**	Logger			*/
	static CLogger log = CLogger.getCLogger(VSortTab.class);
	private int			windowNo;

	private SortTabController	sortTabController;
	private APanel		m_aPanel = null;

	//	UI variables
	private GridBagLayout mainLayout = new GridBagLayout();
	private CLabel noLabel = new CLabel();
	private CLabel yesLabel = new CLabel();
	private CButton bAdd = new CButton();
	private CButton bRemove = new CButton();
	private CButton bUp = new CButton();
	private CButton bDown = new CButton();
	//
	private DefaultListModel<ListElement> noModel = new DefaultListModel<ListElement>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2171680655634744697L;
		@Override
		public void addElement(ListElement obj) {
			Object[] elements = toArray();
			Arrays.sort(elements);
			int index = Arrays.binarySearch(elements, obj);
			if (index < 0)
				index = -1 * index - 1;
			if (index > elements.length)
				super.addElement(obj);
			else
				super.add(index, obj);
		}
		@Override
		public void add(int index, ListElement obj) {
			addElement(obj);
		}
	};
	private DefaultListModel<ListElement> yesModel = new DefaultListModel<ListElement>();
	private DefaultListCellRenderer listRenderer = new DefaultListCellRenderer() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -101524191283634472L;

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value != null && value instanceof ListElement && !((ListElement)value).isUpdateable()) {
				Font f = c.getFont();
				c.setFont(f.deriveFont(f.getStyle() | Font.ITALIC));
				c.setFocusable(false);
			}
			return c;
		}
		
	};
	private JList noList = new JList(noModel);
	private JList yesList = new JList(yesModel);
	private JScrollPane noPane = new JScrollPane(noList);
	private JScrollPane yesPane = new JScrollPane(yesList);

	/**
	 * 	Static Layout
	 * 	@throws Exception
	 */
	private void jbInit() throws Exception
	{
		setLayout(mainLayout);
		//
		noLabel.setText(Msg.getMsg(Env.getCtx(), "Available"));
		yesLabel.setText(Msg.getMsg(Env.getCtx(), "Sequence"));

		for (MouseMotionListener mml : noList.getMouseMotionListeners())
			noList.removeMouseMotionListener(mml);

		for (MouseMotionListener mml : yesList.getMouseMotionListeners())
			yesList.removeMouseMotionListener(mml);

		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				if (me.getClickCount() > 1)
				{
					JList list = (JList)me.getComponent();
					Point p = me.getPoint();
					int index = list.locationToIndex(p);
					if (index > -1 && list.getCellBounds(index, index).contains(p))
						migrateValueAcrossLists(me);
				}
			}
		};
		yesList.addMouseListener(mouseListener);
		noList.addMouseListener(mouseListener);
		//
		yesList.setCellRenderer(listRenderer);
		noList.setCellRenderer(listRenderer);

		ActionListener actionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				migrateValueAcrossLists(ae);
			}
		};

		bAdd.setIcon(Env.getImageIcon("Detail24.gif"));
		bAdd.setMargin(new Insets(2, 2, 2, 2));
		bAdd.addActionListener(actionListener);

		bRemove.setIcon(Env.getImageIcon("Parent24.gif"));
		bRemove.setMargin(new Insets(2, 2, 2, 2));
		bRemove.addActionListener(actionListener);

		MouseInputListener crossListMouseListener = new DragListener();
		yesList.addMouseListener(crossListMouseListener);
		yesList.addMouseMotionListener(crossListMouseListener);
		noList.addMouseListener(crossListMouseListener);
		noList.addMouseMotionListener(crossListMouseListener);

		actionListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				migrateValueWithinYesList(ae);
			}
		};

		bUp.setIcon(Env.getImageIcon("Previous24.gif"));
		bUp.setMargin(new Insets(2, 2, 2, 2));
		bUp.addActionListener(actionListener);

		bDown.setIcon(Env.getImageIcon("Next24.gif"));
		bDown.setMargin(new Insets(2, 2, 2, 2));
		bDown.addActionListener(actionListener);

		MouseMotionListener yesListMouseMotionListener = new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent me)
			{
				JList list = (JList)me.getComponent();
				Point p = me.getPoint();
				int index = list.locationToIndex(p);
				if (index > -1 && list.getCellBounds(index, index).contains(p))
					migrateValueWithinYesList(me);
			}
		};
		yesList.addMouseMotionListener(yesListMouseMotionListener);

		//	Yamel Senih, 2015-11-13
		//	Maximize Panel when the window is resized
		yesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		noList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		add(noLabel,    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(yesLabel,    new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		add(bDown,         new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		add(noPane,      new GridBagConstraints(0, 1, 1, 3, 1, 1
				,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 4, 4), 0, 0));
		add(yesPane,      new GridBagConstraints(2, 1, 1, 3, 1, 1
				,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(4, 4, 4, 4), 0, 0));
		add(bUp,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		add(bAdd,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		add(bRemove,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		//	End Yamel Senih
	}	//	jbInit

	/* (non-Javadoc)
	 * @see org.compiere.grid.APanelTab#loadData()
	 */
	public void loadData()
	{
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
	}	//	loadData

	/**
	 * Set tab change status.
	 * @param value
	 */
	private void setIsChanged(boolean value) {
		if (m_aPanel != null) {
			m_aPanel.aSave.setEnabled(value);
			m_aPanel.aIgnore.setEnabled(value);
		}
	}

	/**
	 * @param event
	 */
	void migrateValueAcrossLists (AWTEvent event)
	{
		Object source = event.getSource();
		@SuppressWarnings("unchecked")
		List<ListElement> selObjects = (source == bAdd || source == noList) ?
				noList.getSelectedValuesList() : yesList.getSelectedValuesList();
		for (ListElement itemSelected : selObjects) {
			if (itemSelected == null || !itemSelected.isUpdateable())
				continue;
			//	
			if(source == bAdd || source == noList) {
				noModel.removeElement(itemSelected);
				yesModel.addElement(itemSelected);
				yesList.setSelectedValue(itemSelected, true);
			} else {
				yesModel.removeElement(itemSelected);
				noModel.addElement(itemSelected);
				noList.setSelectedValue(itemSelected, true);
			}

			//  Enable explicit Save
			setIsChanged(true);
		}
	}	//	migrateValueAcrossLists

	/**
	 * 	Move within Yes List
	 *	@param event event
	 */
	void migrateValueWithinYesList (AWTEvent event)
	{
		Object[] selObjects = yesList.getSelectedValues();
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
		Object source = event.getSource();
		if (source == bUp)
		{
			for (int i = 0; i < length; i++) {
				ListElement selObject = (ListElement)selObjects[i];
				int index = indices[i];
				if (index == 0)
					break;
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
				ListElement selObject = (ListElement)selObjects[i];
				int index = indices[i];
				if (index  >= yesModel.size () - 1)
					break;
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

		else if (source == yesList)
		{
			// see org.compiere.grid.VSortTab.DragListener#mouseReleased(MouseEvent)
		}
		else
			log.severe("Unknown source: " + source);
		//
		if (change) {
			yesList.setSelectedIndices(indices);
			setIsChanged(true);
		}
	}	//	migrateValueWithinYesList

	/* (non-Javadoc)
	 * @see org.compiere.grid.APanelTab#registerAPanel(APanel)
	 */
	public void registerAPanel (APanel panel)
	{
		m_aPanel = panel;
	}	//	registerAPanel


	/** (non-Javadoc)
	 * @see org.compiere.grid.APanelTab#saveData()
	 */
	public void saveData() {
		if (!m_aPanel.aSave.isEnabled())
			return;
		log.fine("");
		//	
		String info = sortTabController.saveData(Collections.list(noModel.elements()), 
				Collections.list(yesModel.elements()));
		//
		if (info == null) {
			setIsChanged(false);
		} else {
			ADialog.error(windowNo, null, "SaveError", info);
		}
	}	//	saveData

	@Override
	public void unregisterPanel() {
		saveData();
		m_aPanel = null;
	}	//	dispoase

	/**
	 * @author eslatis
	 *
	 */
	private class DragListener extends MouseInputAdapter {

		/**
		 * Creates a VSortTab.DragListener.
		 */
		public DragListener()
		{
			URL url = VSortTab.class.getResource(cursorName);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage(url);
			customCursor = toolkit.createCustomCursor(image, new Point(0, 0), "Howdy");
		}

		/** The cursorName. */
		private String cursorName = "/org/compiere/images/DragCursor32.gif";

		/** StartList	*/
		private JList 				startList = null;

		/** The startModel. */
		private DefaultListModel	startModel = null;

		/** The selObject. */
		private Object 				selObject = null;

		private boolean				moved = false;

		/** The customCursor. */
		private Cursor customCursor;

		@Override
		public void mousePressed(MouseEvent me)
		{
			JList list = (JList)me.getComponent();
			Point p = me.getPoint();
			int index = list.locationToIndex(p);
			if (index > -1 && list.getCellBounds(index, index).contains(p))
			{
				startList = list;
				startModel = (list == noList) ? noModel : yesModel;
				selObject = list.getModel().getElementAt(index);
			}
			if (list == noList)
				yesList.clearSelection();
			else
				noList.clearSelection();
			moved = false;
		}	//	mousePressed

		@Override
		public void mouseDragged(MouseEvent me)
		{
			if (selObject == null || !((ListElement)selObject).isUpdateable()) {
				moved = false;
				return;
			}
			moved = true;
			if (getCursor() != customCursor)
				setCursor(customCursor);
		}	//	mouseDragged

		/* (non-Javadoc)
		 * @see javax.swing.event.MouseInputAdapter#mouseReleased(java.awt.event.MouseEvent)
		 */
		public void mouseReleased(MouseEvent me)
		{
			if (startModel != null && moved)
			{
				Point p = me.getPoint();

				JList endList = yesList;
				DefaultListModel endModel = yesModel;

				if (me.getComponent() == yesList)
				{
					if (!yesList.contains (p))
					{
						endList = noList;
						endModel = noModel;
					}
				}
				else
				{
					if (noList.contains (p))
					{
						setCursor(Cursor.getDefaultCursor());
						moved = false;
						return;		//	move within noList
					}
					p = SwingUtilities.convertPoint (noList, p, yesList);
				}
				int index = endList.locationToIndex(p);
				if (index > -1)	// && endList.getCellBounds(index, index).contains(p))
				{
					startModel.removeElement(selObject);
					endModel.add(index, selObject);
					//
					startList.clearSelection();
					endList.clearSelection();
					endList.setSelectedValue(selObject, true);
					//
					setIsChanged(true);
				}
			}
			//	
			startList = null;
			startModel = null;
			selObject = null;
			moved = false;
			setCursor(Cursor.getDefaultCursor());
		}	//	mouseReleased
	}
}	//	VSortTab