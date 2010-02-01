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
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]                  *
 * @contributor fer_luck @ centuryon                                          *
 *****************************************************************************/
package org.compiere.grid;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.adempiere.plaf.AdempiereLookAndFeel;
import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.APanel;
import org.compiere.apps.AppsAction;
import org.compiere.grid.ed.VCellEditor;
import org.compiere.grid.ed.VCellRenderer;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.grid.ed.VHeaderRenderer;
import org.compiere.grid.ed.VManagedEditor;
import org.compiere.grid.ed.VRowIDEditor;
import org.compiere.grid.ed.VRowIDRenderer;
import org.compiere.grid.ed.VString;
import org.compiere.grid.tree.VTreePanel;
import org.compiere.model.DataStatusEvent;
import org.compiere.model.DataStatusListener;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.GridTable;
import org.compiere.model.GridWindow;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.swing.CollapsiblePanel;
import org.compiere.swing.TableCellNone;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Evaluatee;
import org.compiere.util.Trx;

/**
 *  The Grid Controller is the panel for single and multi-row presentation
 *  and links to the Model Tab.
 *
 *  <pre>
 *  UI Structure:
 *  this    (BorderLayout)
 *      splitPane (JSplitPane)
 *          left
 *              graphicPanel
 *          right
 *              cardPanel   JPanel  (CardLayout)
 *                  srPane  JSplitPane
 * 						vPane	JScrollPane
 *              	        vPanel  VPanel (GridBagLayout)
 * 						vIncludedGC	GridController
 *                  mrPane  JScrollPane
 *                      vTable  VTable
 *
 *  <B>DataBinding:<B>
 *  - MultiRow - is automatic between VTable and MTable
 *  - SingleRow
 *		- from VEditors via fireVetoableChange(m_columnName, null, getText());
 *			(vetoableChange)
 *		- to VEditors via updateSingleRow -> Editor.setValue(object)
 *
 *  Event Chains
 *  -- Navigation --
 *  (VTable selection -> GridController.valueChanged)
 *  (APanel selection)
 *      + MTab.navivate
 *          + MTab.setCurrentRow
 *              + Update all MFields
 *                  + MField.setValue
 *                      + setContext
 *                      + fire PropertyChange "Value"
 *                          + VEditor.propertyChange
 *                              + VEditor.setValue
 *              + MTab.fireProperyChange "CurrentRow"
 *                  + VTable.propertyChange (setRowSelectionInterval)
 *                      + GridController.valueChange
 *                          + GridController.dynamicDisplay(complete)
 *              + MTab.fireDataStatusChanged
 *                  + APanel.statusChanged
 *
 *  -- ValueChanges --
 *  VEditor.fireVetoableChange
 *      + (VCellEditor.vetoableChange/getCellEditorValue)   -- multi-row source
 *      + (GridController.vetoableChange)                   -- single-row source
 *          + MTable.setValueAt
 *              + MField.setValue
 *                  + setContext
 *                  + fire PropertyChange "Value"
 *                      + VEditor.setValue
 *              + MTable.fireDataStatusChanged
 *                  + MTab.dataStatusChanged
 *                      + MTab.fireDataStatusChanged
 *                          + APanel.statusChanged
 *                  + GridController.dataStatusChanged
 *                      + GridController.dynamicDisplay(selective)
 *  </pre>
 * @author  Jorg Janke
 * @version $Id: GridController.java,v 1.8 2006/09/25 00:59:52 jjanke Exp $
 *
 * @author Teo Sarca - BF [ 1742159 ], BF [ 1707876 ]
 * @contributor Victor Perez , e-Evolution.SC FR [ 1757088 ]
 * @contributor fer_luck @ centuryon  FR [ 1757088 ]
 */
public class GridController extends CPanel
	implements DataStatusListener, ListSelectionListener, Evaluatee,
		VetoableChangeListener,	PropertyChangeListener, MouseListener
{
	/**
	 *
	 */
	private static final long serialVersionUID = 7308782933999556880L;

	/**
	 *  Constructor - you need to call initGrid for instanciation
	 */
	public GridController()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}   //  GridController

	/**
	 *  toString
	 *  @return string representation
	 */
	public String toString()
	{
		return "GridController for " + m_mTab;
	}   //  toString

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(GridController.class);

	/**
	 *  The Layout
	 */
	private BorderLayout mainLayout = new BorderLayout();
	private JSplitPane splitPane = new JSplitPane();
	private CPanel graphPanel = new CPanel();
	private BorderLayout graphLayout = new BorderLayout();
	private CPanel cardPanel = new CPanel();
	private CardLayout cardLayout = new CardLayout();
	//private JSplitPane srPane = new JSplitPane();

	private JScrollPane vPane = new JScrollPane();
	private CScrollPane mrPane = new CScrollPane();
	private CPanel xPanel = new CPanel();
	private BorderLayout xLayout = new BorderLayout();
	private VTable vTable = new VTable();
    //FR [ 1757088 ]
	private VPanel vPanel = null;
	private boolean detailGrid = false;

	/**
	 *  Static Layout init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setLayout(mainLayout);
		this.add(splitPane, BorderLayout.CENTER);
		splitPane.setOpaque(false);
		graphPanel.setLayout(graphLayout);
		//
		splitPane.add(graphPanel, JSplitPane.LEFT);
		splitPane.add(cardPanel, JSplitPane.RIGHT);
		splitPane.setBorder(null);
		splitPane.setName("gc_splitPane");
		//
		cardPanel.setLayout(cardLayout);
		cardPanel.add(vPane, "vPane");	//	Sequence Important!
		cardPanel.add(mrPane, "mrPane");
		cardPanel.setBorder(null);
		cardPanel.setName("gc_cardPanel");
		//  single row (w/o xPane it would be centered)
		/*
		srPane.setBorder(null);
		srPane.setName("gc_srPane");
		srPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		srPane.add(vPane, JSplitPane.TOP);
		srPane.setTopComponent(vPane);
		srPane.setBottomComponent(null);	//	otherwise a button is created/displayed
		*/
		//FR [ 1757088 ] vPane.getViewport().add(xPanel, null);
		//FR [ 1757088 ] xPanel.add(vPanel);
		xPanel.setLayout(xLayout);
		xPanel.setName("gc_xPanel");
		xPanel.setBorder(BorderFactory.createEmptyBorder());
		//xLayout.setAlignment(FlowLayout.LEFT);
		xLayout.setHgap(0);
		xLayout.setVgap(0);
		//  multi-row
		mrPane.setBorder(null);
		mrPane.getViewport().add(vTable, null);
		mrPane.setName("gc_mrPane");
		//
		graphPanel.setBorder(null);
		graphPanel.setName("gc_graphPanel");
		//srPane.setDividerLocation(200);

		vPane.setBorder(BorderFactory.createEmptyBorder());
	}   //  jbInit

	/**
	 *  Displose
	 */
	public void dispose()
	{
		log.config( "(" + m_mTab.toString() + ")");
		//  clear info
		stopEditor(false);
		if (m_mTab.isLoadComplete())
		{
			if (m_mTab.needSave(true, false))
				m_mTab.dataIgnore();
		}
		//FR [ 1757088 ] vIncludedGC = null;

		//  Listeners
		if (m_mTab.isLoadComplete())
		{
			m_mTab.getTableModel().removeDataStatusListener(this);
			m_mTab.getTableModel().removeVetoableChangeListener(this);
		}
		vTable.getSelectionModel().removeListSelectionListener(this);
		m_mTab.removePropertyChangeListener(vTable);

		//  editors
		Component[] comp = vPanel.getComponentsRecursive();
		for (int i = 0; i < comp.length; i++)
		{
			if (comp[i] instanceof VEditor)
			{
				VEditor vEditor = (VEditor)comp[i];
				vEditor.removeVetoableChangeListener(this);
				String columnName = comp[i].getName();
				GridField mField = m_mTab.getField(columnName);
				if (mField != null)
					mField.removePropertyChangeListener(vEditor);
				vEditor.dispose();
			}
		}
		/** @todo Remove APanel Button listeners */

		vTable.removeAll();
		vTable.setModel(new DefaultTableModel());   //  remove reference
		vTable = null;
		vPanel.removeAll();
		vPanel = null;
		//srPane.removeAll();
		//srPane = null;
		splitPane.removeAll();
		splitPane = null;
		m_mTab = null;
		m_tree = null;
		this.removeAll();
	}   //  dispose

	/** Model Tab                   */
	private GridTab		m_mTab = null;
	/** Window                      */
	private int         m_WindowNo;
	/** Only Multi-Row exist        */
	private boolean     m_onlyMultiRow = false;
	/** Single/Multi Row indicator  */
	private boolean     m_singleRow = true;
	/** Veto Active                 */
	private boolean     m_vetoActive = false;
	/** Tree Panel (optional)       */
	private VTreePanel  m_tree;

	private APanel m_aPanel;

	private boolean init;

	private ArrayList<GridSynchronizer> synchronizerList = new ArrayList<GridSynchronizer>();

	public boolean initGrid (GridTab mTab, boolean onlyMultiRow,
			int WindowNo, APanel aPanel, GridWindow mWindow)
	{
		return initGrid(mTab, onlyMultiRow, WindowNo, aPanel, mWindow, false);
	}

	/**************************************************************************
	 *  Init Grid.
	 *  <pre>
	 *  - Map table to model
	 *  - Update (multi-row) table info with renderers/editors
	 *  - build single-row panel
	 *  - initialize display
	 *  </pre>
	 *  @param mTab tab
	 *  @param onlyMultiRow only table
	 *  @param WindowNo window no
	 *  @param aPanel optional Application Panel for adding button listeners
	 * 	@param mWindow parent Window Model
	 *  @return true if initialized
	 */
	public boolean initGrid (GridTab mTab, boolean onlyMultiRow,
		int WindowNo, APanel aPanel, GridWindow mWindow, boolean lazy)
	{
		log.config( "(" + mTab.toString() + ")");
		m_mTab = mTab;
		m_WindowNo = WindowNo;
		m_onlyMultiRow = onlyMultiRow;
		m_aPanel = aPanel;
		setName("GC-" + mTab);
		//FR [ 1757088 ]
		vPanel = new VPanel(mTab.getName(), m_WindowNo);
		vPanel.putClientProperty(AdempiereLookAndFeel.HIDE_IF_ONE_TAB, Boolean.TRUE);
		if (this.isDetailGrid())
		{
			vPanel.setBorder(BorderFactory.createLineBorder(AdempierePLAF.getPrimary2()));
		}
		vPane.getViewport().add(xPanel, null);
		xPanel.add(vPanel, BorderLayout.CENTER);

		setTabLevel(m_mTab.getTabLevel());

		if (!lazy)
			init();
		else
		{
			//Load tab meta data, needed for includeTab to work
			m_mTab.initTab(false);
		}


	//	log.config( "GridController.dynInit (" + mTab.toString() + ") - fini");
		return true;
	}   //  initGrid

	private void init()
	{
		//  Set up Multi Row Table
		vTable.setModel(m_mTab.getTableModel());

		//  Update Table Info -------------------------------------------------
		int size = setupVTable (m_aPanel, m_mTab, vTable);

		//  Set Color on Tab Level
		//  this.setBackgroundColor (mTab.getColor());

		//  Single Row  -------------------------------------------------------
		if (!m_onlyMultiRow)
		{
			//	Set Softcoded Mnemonic &x
			for (int i = 0; i < size; i++)
			{
				GridField mField = m_mTab.getField(i);
				if (mField.isDisplayed())
					vPanel.setMnemonic(mField);
			}   //  for all fields

			//	Add Fields
			for (int i = 0; i < size; i++)
			{
				GridField mField = m_mTab.getField(i);
				if (mField.isDisplayed())
				{
					VEditor vEditor = VEditorFactory.getEditor(m_mTab, mField, false);
					if (vEditor == null)
					{
						log.warning("Editor not created for " + mField.getColumnName());
						continue;
					}
					//  MField => VEditor - New Field value to be updated to editor
					mField.addPropertyChangeListener(vEditor);
					//  VEditor => this - New Editor value to be updated here (MTable)
					vEditor.addVetoableChangeListener(this);
					//  Add to VPanel
					vPanel.addFieldBuffered(vEditor, mField);
					//  APanel Listen to buttons
					if (mField.getDisplayType() == DisplayType.Button && m_aPanel != null)
						((JButton)vEditor).addActionListener (m_aPanel);
				}
			}   //  for all fields
			vPanel.addFieldBuffered(null, null);  // flush the last one through

			//	No Included Grid Controller
			/*
			srPane.setResizeWeight(1);	//	top part gets all
			srPane.setDividerSize (0);
			srPane.setDividerLocation (9999);
			*/
			//  Use SR to size MR
			mrPane.setPreferredSize(vPanel.getPreferredSize());
		}   //  Single-Row

		//  Tree Graphics Layout
		int AD_Tree_ID = 0;
		if (m_mTab.isTreeTab())
			AD_Tree_ID = MTree.getDefaultAD_Tree_ID (
				Env.getAD_Client_ID(Env.getCtx()), m_mTab.getKeyColumnName());
		if (m_mTab.isTreeTab() && AD_Tree_ID != 0)
		{
			m_tree = new VTreePanel(m_WindowNo, false, true);
			if (m_mTab.getTabNo() == 0)	//	initialize other tabs later
				m_tree.initTree(AD_Tree_ID);
			m_tree.addPropertyChangeListener(VTreePanel.NODE_SELECTION, this);
			graphPanel.add(m_tree, BorderLayout.CENTER);
			splitPane.setDividerLocation(250);
		//	splitPane.resetToPreferredSizes();
		}
		else    //  No Graphics - hide
		{
			graphPanel.setPreferredSize(new Dimension(0,0));
			splitPane.setDividerSize(0);
			splitPane.setDividerLocation(0);
		}

		//  Receive DataStatusChanged info from MTab
		m_mTab.addDataStatusListener(this);
		//  Receive vetoableChange info from MTable when saving
		m_mTab.getTableModel().addVetoableChangeListener(this);
		//	Selection Listener -> valueChanged
		vTable.getSelectionModel().addListSelectionListener(this);
		//  Navigation (RowChanged)
		m_mTab.addPropertyChangeListener(vTable);

		//  Update UI
		vTable.autoSize(true);

				//  Set initial presentation
		if (m_onlyMultiRow || !m_mTab.isSingleRow())
			switchMultiRow();
		else
			switchSingleRow();

		init = true;
	}

	/**
	 *
	 * @return boolean
	 */
	public boolean isInit()
	{
		return init;
	}

	/**
	 * 	Include Tab
	 * 	@param gc grid controller to add
	 * 	@return GridSynchronizer
	 */

	//FR [ 1757088 ]
	public boolean includeTab (GridController gc , APanel aPanel, GridSynchronizer sync)
	{
		GridController detail = gc;
		detail.setDetailGrid(true);
		detail.addMouseListener(detail);
		detail.enableEvents(AWTEvent.HIERARCHY_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK);

		vPanel.includeTab(detail);
		//BEGIN - [FR 1953734]
		gc.setGCParent(this);
		//END - [FR 1953734]
		gc.getGCParent().setPreferredSize(vPanel.getPreferredSize());
		synchronizerList.add(sync);
		return true;
	}	//	IncludeTab

	//FR [ 1757088 ]
	public void setDetailGrid(boolean value){
		detailGrid = value;
		if (detailGrid && vPanel != null)
			vPanel.setBorder(BorderFactory.createLineBorder(AdempierePLAF.getPrimary2()));
	}

	public boolean isDetailGrid(){
		return detailGrid;
	}
	/**
	 * 	Get Title
	 *	@return title
	 */
	public String getTitle ()
	{
		return m_mTab.getName();
	}	//	getTitle

	/**
	 *	Setup Multi-Row Table (add fields)
	 * 	@param aPanel Panel
	 * 	@param mTab Model Tab
	 * 	@param table JTable
	 * 	@return size
	 */
	private int setupVTable (APanel aPanel, GridTab mTab, VTable table)
	{
		if (!mTab.isDisplayed())
			return 0;
		int size = mTab.getFieldCount ();
		TableColumnModel tcm = table.getColumnModel();
		if (size != tcm.getColumnCount())
			throw new IllegalStateException("TableColumn Size <> TableModel");

		for (int i = 0; i < size; i++)
		{
			GridField mField = mTab.getField (i);
			TableColumn tc = tcm.getColumn(i);
			tc.setMinWidth(30);
			//
			if (mField.getColumnName().equals(tc.getIdentifier().toString()))
			{
				//don't show included tab field in grid
				if (mField.getIncluded_Tab_ID() > 0)
				{
					TableCellNone tcn = new TableCellNone(mField.getColumnName());
					tc.setCellRenderer (tcn);
					tc.setCellEditor (tcn);
					tc.setHeaderValue (null);
					tc.setMinWidth (0);
					tc.setMaxWidth (0);
					tc.setPreferredWidth (0);
				}
				else if (mField.getDisplayType () == DisplayType.RowID)
				{
					tc.setCellRenderer (new VRowIDRenderer (false));
					tc.setCellEditor (new VRowIDEditor (false));
					tc.setHeaderValue ("");
					tc.setMaxWidth (2);
				}
				else
				{
					//  need to set CellEditor explicitly as default editor based on class causes problem (YesNo-> Boolean)
					if (mField.isDisplayed ())
					{
						tc.setCellRenderer (new VCellRenderer (mField));
						VCellEditor ce = new VCellEditor (mField);
						tc.setCellEditor (ce);
						//
						tc.setHeaderValue (mField.getHeader ());
						tc.setPreferredWidth (Math.max (mField.getDisplayLength (), 30));
						tc.setHeaderRenderer (new VHeaderRenderer (mField.getDisplayType ()));

						//  Enable Button actions in grid
						if (mField.getDisplayType () == DisplayType.Button)
						{
							ce.setActionListener(aPanel);
						}
					}
					else //  column not displayed
					{
						TableCellNone tcn = new TableCellNone(mField.getColumnName());
						tc.setCellRenderer (tcn);
						tc.setCellEditor (tcn);
						tc.setHeaderValue (null);
						tc.setMinWidth (0);
						tc.setMaxWidth (0);
						tc.setPreferredWidth (0);
					}
				}
			//	System.out.println ("TableColumnID " + tc.getIdentifier ()
			//	  + "  Renderer=" + tc.getCellRenderer ()
			//	  + mField.getHeader ());

			}	//	found field
			else
				log.log(Level.SEVERE, "TableColumn " + tc.getIdentifier ()
				  + " <> MField " + mField.getColumnName() + mField.getHeader());
		} 	//  for all fields
		return size;
	}	//	setupVTable

	/**
	 * 	Activate Grid Controller.
	 * 	Called by APanel when GridController is displayed (foreground)
	 */
	public void activate ()
	{
		if (!init) init();

		//	Tree to be initiated on second/.. tab
		if (m_mTab.isTreeTab() && m_mTab.getTabNo() != 0)
		{
			String keyColumnName = m_mTab.getKeyColumnName();
			String treeName = "AD_Tree_ID";
			if (keyColumnName.startsWith("CM"))
			{
				if (keyColumnName.equals("CM_Container_ID"))
					treeName = "AD_TreeCMC_ID";
				else if (keyColumnName.equals("CM_CStage_ID"))
					treeName = "AD_TreeCMS_ID";
				else if (keyColumnName.equals("CM_Template_ID"))
					treeName = "AD_TreeCMT_ID";
				else if (keyColumnName.equals("CM_Media_ID"))
					treeName = "AD_TreeCMM_ID";
			}
			int AD_Tree_ID = Env.getContextAsInt (Env.getCtx(), m_WindowNo, treeName);
			log.config(keyColumnName + " -> " + treeName + " = " + AD_Tree_ID);
			if (AD_Tree_ID == 0)
				AD_Tree_ID = MTree.getDefaultAD_Tree_ID (
					Env.getAD_Client_ID(Env.getCtx()), m_mTab.getKeyColumnName());
			if (m_tree != null)
				m_tree.initTree (AD_Tree_ID);
		}

		activateChilds();
	}	//	activate

	/**
	 * activate child grid controller ( included tab )
	 */
	private void activateChilds()
	{
		for (GridSynchronizer s : synchronizerList )
		{
			s.activateChild();
		}
	}

	public GridController findChild(GridTab gTab)
	{
		GridController gc = null;
		for (GridSynchronizer s : synchronizerList )
		{
			if (s.getChild().getMTab().equals(gTab))
			{
				gc = s.getChild();
				break;
			}
		}
		return gc;
	}

	/**
	 *  Register ESC Actions
	 *  - overwrite VTable's Keystrokes assignment for ESC
	 *  @param aIgnore ignore
	 */
	public void registerESCAction (AppsAction aIgnore)
	{
		int c = VTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
		vTable.getInputMap(c).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), aIgnore.getName());
		vTable.getActionMap().put(aIgnore.getName(), aIgnore);

	//	AEnv.printActionInputMap(vTable);
	}   //  registerESCAction

	/**
	 *  Query Tab and resize Table
	 *  (called from APanel)
	 *  @param onlyCurrentRows only current rows
	 *  @param onlyCurrentDays how many days back
	 *  @param maxRows maximim rows or 0 for all
	 */
	public void query (boolean onlyCurrentRows, int onlyCurrentDays, int maxRows)
	{
		//  start loading while building screen
		m_mTab.query(onlyCurrentRows, onlyCurrentDays, maxRows);
		//  Update UI
		if (!isSingleRow())
			vTable.autoSize(true);
		activateChilds();
	}   //  query

	/*
	public boolean isNeedToSaveParent()
	{
		return m_mTab.isNeedToSaveParent();
	}*/

	/**************************************************************************
	 *  Switch from single to multi & vice versa
	 */
	public void switchRowPresentation()
	{
		stopEditor(true);
		if (m_singleRow)
			switchMultiRow();
		else
			switchSingleRow();
	}   //  switchRowPresentation

	/**
	 *  Switch to SingleRow Presentation
	 */
	public void switchSingleRow()
	{
		if (m_onlyMultiRow)
			return;
		cardLayout.first(cardPanel);
		m_singleRow = true;
		dynamicDisplay(0);
	//	vPanel.requestFocus();
	}   //  switchSingleRow

	/**
	 *  Switch to MultiRow Presentation
	 */
	public void switchMultiRow()
	{
		cardLayout.last(cardPanel);
		m_singleRow = false;
		vTable.autoSize(true);	//	resizes
	//	vTable.requestFocus();
	}   //  switchSingleRow

	/**
	 *  Is Single Row presentation
	 *  @return true if Single Row is displayed
	 */
	public boolean isSingleRow()
	{
		return m_singleRow;
	}   //  isSingleRow


	/**************************************************************************
	 *  Remove Listener - pass on to MTab
	 *  @param l listener
	 */
	public synchronized void removeDataStatusListener(DataStatusListener l)
	{
		m_mTab.removeDataStatusListener(l);
	}   //  removeDataStatusListener

	/**
	 *  Add Data Status Listener - pass on to MTab
	 *  @param l listener
	 */
	public synchronized void addDataStatusListener(DataStatusListener l)
	{
		m_mTab.addDataStatusListener(l);
	}

	/**
	 *  Data Status Listener - for MTab events.
	 *  <p>
	 *  Callouts are processed here for GUI changes
	 *  - same as in MTab.setValue for batch changes
	 *  <p>
	 *  calls dynamicDisplay
	 *  @param e event
	 */
	public void dataStatusChanged(DataStatusEvent e)
	{
	//	if (e.getChangedColumn() == 0)
	//		return;
		int col = e.getChangedColumn();
		log.config("(" + m_mTab + ") Col=" + col + ": " + e.toString());

		//  Process Callout
		GridField mField = m_mTab.getField(col);
		if (mField != null
			&& (mField.getCallout().length() > 0 || m_mTab.hasDependants(mField.getColumnName())))
		{
			String msg = m_mTab.processFieldChange(mField);     //  Dependencies & Callout
			if (msg.length() > 0)
				ADialog.error(m_WindowNo, this, msg);
		}
		//if (col >= 0)
		dynamicDisplay(col);
	}   //  dataStatusChanged


	/**************************************************************************
	 *  List Selection Listener (VTable) - row changed
	 *  @param e event
	 */
	public void valueChanged(ListSelectionEvent e)
	{
		//  no rows
		if (m_mTab.getRowCount() == 0)
			return;

	//	vTable.stopEditor(graphPanel);
		int rowTable = vTable.getSelectedRow();
		int rowCurrent = m_mTab.getCurrentRow();
		log.config("(" + m_mTab.toString() + ") Row in Table=" + rowTable + ", in Model=" + rowCurrent);
		/* BT [ 1972495 ] Multirow Automatic New Record loses context
		// FR [ 1757088 ]
		if(rowCurrent + 1 == vTable.getRowCount() && !isSingleRow() && Env.isAutoNew(Env.getCtx()) && m_mTab.getRecord_ID() != -1)
		{
		  //stopEditor(true);
		  vTable.getSelectionModel().removeListSelectionListener(this);
		  m_mTab.dataNew(false);
		  dynamicDisplay(0);
		  vTable.getSelectionModel().addListSelectionListener(this);
		  return;
		 } */
		if (rowTable == -1)  //  nothing selected
		{
			if (rowCurrent >= 0)
			{
				vTable.setRowSelectionInterval(rowCurrent, rowCurrent); //  causes this method to be called again
				return;
			}
		}
		else
		{
			if (rowTable != rowCurrent) {
				//make sure table selection is consistent with model
				int t = m_mTab.navigate(rowTable);
				if (t != rowTable) {
					rowTable = t;
					vTable.setRowSelectionInterval(rowTable, rowTable);
				}
			}
			dynamicDisplay(0);
		}

		//	TreeNavigation - Synchronize 	-- select node in tree
		if (m_tree != null)
			m_tree.setSelectedNode (m_mTab.getRecord_ID());	//	ignores new (-1)

	//	log.config( "GridController.valueChanged (" + m_mTab.toString() + ") - fini",
	//		"Row in Table=" + rowTable + ", in Model=" + rowCurrent);

	}   //  valueChanged

	/**
	 *  PropertyChange Listener - Tree Panel - node selection
	 *  @param e event
	 */
	public void propertyChange(PropertyChangeEvent e)
	{
	//	System.out.println("propertyChange");
	//	System.out.println(e);
		if (e == null)
			return;
		Object value = e.getNewValue();
		if (value == null)
			return;
		log.config(e.getPropertyName() + "=" + value
			+ " - " + value.getClass().toString());
		if (!(value instanceof MTreeNode))
			return;

		//  We Have a TreeNode
		int nodeID = ((MTreeNode)value).getNode_ID();
		//  root of tree selected - ignore
		//if (nodeID == 0)
			//return;

		//  Search all rows for mode id
		int size = m_mTab.getRowCount();
		int row = -1;
		for (int i = 0; i < size; i++)
		{
			if (m_mTab.getKeyID(i) == nodeID)
			{
				row = i;
				break;
			}
		}
		if (row == -1)
		{
			if (nodeID > 0)
				log.log(Level.WARNING, "Tab does not have ID with Node_ID=" + nodeID);
			return;
		}

		//  Navigate to node row
		m_mTab.navigate(row);
	}   //  propertyChange

	/**
	 *  Dynamic Display.
	 *  - Single Row Screen layout and update of dynamic Lookups
	 *  <p>
	 *  Single Row layout:
	 *  the components's name is the ColumnName; if it matches, the
	 *  MField.isDisplayed(true) is used to determine if it is visible
	 *  if the component is a VEditor, setEnabled is set from the MField
	 *  <p>
	 *  Multi Row layout is not changed:
	 *  VCellRenderer calls JTable.isCellEditable -> checks MField.isEditable (Active, isDisplayed)
	 *  VCellEditor.isCellEditable calls MField.isEditable(true) <br>
	 *  If a column is not displayed, the width is set to 0 in dynInit
	 *  <p>
	 *  Dynamic update of data is handeled in VLookup.focusGained/Lost.
	 *  When focus is gained the model is temporarily updated with the
	 *  specific validated data, if lost, it is switched back to the
	 *  unvalidated data (i.e. everything). This allows that the display
	 *  methods have a lookup to display. <br>
	 *  Here: if the changed field has dependents and the dependent
	 *  is a Lookup and this lookup has a dynamic dependence of the changed field,
	 *  the value of that field is set to null (in MTab.processDependencies -
	 *  otherwise it would show an invalid value).
	 *  As Editors listen for value changed of their MField, the display is updated.
	 *  <p>
	 *  Called from GridController.valueChanged/dataStatusChanged, APane;.stateChanged/unlock/cmd_...
	 *  @param col selective column number or 0 if all
	 */
	public void dynamicDisplay (int col)
	{
	//	log.config( "GridController.dynamicDisplay (" + m_mTab.toString() + ") SingleRow=" + isSingleRow() + ", OnlyMultiRow=" + m_onlyMultiRow);
		//	Don't update if multi-row
		if (!isSingleRow() || m_onlyMultiRow)
			return;
		if (!m_mTab.isOpen())
			return;
		//  Selective
		if (col > 0)
		{
			GridField changedField = m_mTab.getField(col);
			String columnName = changedField.getColumnName();
			ArrayList<GridField> dependants = m_mTab.getDependantFields(columnName);
			log.config("(" + m_mTab.toString() + ") "
				+ columnName + " - Dependents=" + dependants.size());
			//	No Dependents and no Callout - Set just Background
			if (dependants.size() == 0 && changedField.getCallout().length() > 0)
			{
				Component[] comp = vPanel.getComponentsRecursive();
				for (int i = 0; i < comp.length; i++)
				{
					if (columnName.equals(comp[i].getName ()) && comp[i] instanceof VEditor)
					{
						VEditor ve = (VEditor)comp[i];
						boolean manMissing = false;
						boolean noValue = changedField.getValue() == null || changedField.getValue().toString().length() == 0;
						if (noValue && changedField.isEditable(true) && changedField.isMandatory(true))    //  check context
							manMissing = true;
						ve.setBackground(manMissing || changedField.isError());
						break;
					}
				}
				return;
			}
		}   //  selective


		//  complete single row re-display
		boolean noData = m_mTab.getRowCount() == 0;
		log.config(m_mTab.toString() + " - Rows=" + m_mTab.getRowCount());
		//  All Components in vPanel (Single Row)

		Set<String> hiddens = new HashSet<String>();
		Component[] comps = vPanel.getComponentsRecursive();
		for (int i = 0; i < comps.length; i++)
		{
			Component comp = comps[i];
			String columnName = comp.getName();

			if (columnName != null && columnName.length() > 0)
			{
				GridField mField = m_mTab.getField(columnName);
				if (mField != null)
				{
					if (mField.isDisplayed(true))		//  check context
					{
						if (!comp.isVisible())
							comp.setVisible(true);		//  visibility
						/**
						 * Feature Request [1707462]
						 * Enable runtime change of VFormat
						 * @author fer_luck
						 */
						if (comp instanceof VString){
							VString vs = (VString)comp;
							if ((vs.getVFormat() != null && vs.getVFormat().length() > 0 && mField.getVFormat() == null)
									|| (vs.getVFormat() == null && mField.getVFormat() != null && mField.getVFormat().length() > 0)
									|| (vs.getVFormat() != null && mField.getVFormat() != null && !vs.getVFormat().equals(mField.getVFormat()))) {
								vs.setVFormat(mField.getVFormat());
							}
						}
						//End Feature Request [1707462]
						if (comp instanceof VEditor)
						{
							VEditor ve = (VEditor)comp;
							if (noData)
								ve.setReadWrite(false);
							else
							{
								boolean rw = mField.isEditable(true);	//  r/w - check Context
								ve.setReadWrite(rw);
							//	log.log(Level.FINEST, "RW=" + rw + " " + mField);
								boolean manMissing = false;
							//  least expensive operations first        //  missing mandatory
								if (rw && mField.getValue() == null && mField.isMandatory(true))    //  check context
									manMissing = true;
								ve.setBackground(manMissing || mField.isError());
							}
						}
					}
					else
					{
						if (comp.isVisible())
							comp.setVisible(false);
						hiddens.add(columnName);
					}
				}
			}
		}   //  all components

		// hide empty field group based on the environment
		for (int i = 0; i < comps.length; i++) {
			Component comp = comps[i];

			if (comp instanceof CollapsiblePanel)
			{
				if (comp.getName() == null || comp.getName().startsWith("IncludedTab#"))
					continue;
				else
				{
					boolean hasVisible = false;
					Component[] childs = ((CollapsiblePanel)comp).getCollapsiblePane().getContentPane().getComponents();
					for (int j = 0; j < childs.length; j++) {
						if (childs[j].isVisible())
						{
							String columnName = childs[j].getName();
							if (columnName != null && columnName.length() > 0)
							{
								GridField mField = m_mTab.getField(columnName);
								if (mField != null)
								{
									hasVisible = true;
									break;
								}
							}
						}
					}
					if (comp.isVisible() != hasVisible)
						comp.setVisible(hasVisible);
				}
			}
		}

		//

		log.config(m_mTab.toString() + " - fini - "
				+ (col <= 0 ? "complete" : "seletive"));
	}   //  dynamicDisplay

	/**
	 *  Row Changed - synchronize with Tree
	 *
	 *  @param  save    true the row was saved (changed/added), false if the row was deleted
	 *  @param  keyID   the ID of the row changed
	 */
	public void rowChanged (boolean save, int keyID)
	{
		if (m_tree == null || keyID <= 0)
			return;
		String name = (String)m_mTab.getValue("Name");
		String description = (String)m_mTab.getValue("Description");
		Boolean IsSummary = (Boolean)m_mTab.getValue("IsSummary");
		boolean summary = IsSummary != null && IsSummary.booleanValue();
		String imageIndicator = (String)m_mTab.getValue("Action");  //  Menu - Action
		//
		m_tree.nodeChanged(save, keyID, name, description,
			summary, imageIndicator);
	}   //  rowChanged


	/**************************************************************************
	 * Save Multiple records - Clone a record and assign new values to each
	 * clone for a specific column.
	 * @param ctx context
	 * @param tableName Table Name
	 * @param columnName Column for which value need to be changed
	 * @param recordId Record to clone
	 * @param values Values to be assigned to clones for the specified column
	 * @param trxName Transaction
	 * @throws Exception If error is occured when loading the PO or saving clones
	 *
	 * @author ashley
	 */
	protected void saveMultipleRecords(Properties ctx, String tableName,
			String columnName, int recordId, Integer[] values,
			String trxName) throws Exception
	{
		if (values == null)
		{
			return ;
		}

		int oldRow = m_mTab.getCurrentRow();
		GridField lineField = m_mTab.getField("Line");

		for (int i = 0; i < values.length; i++)
		{
			if (!m_mTab.dataNew(true))
			{
				throw new IllegalStateException("Could not clone tab");
			}

			m_mTab.setValue(columnName, values[i]);

			if (lineField != null)
			{
				m_mTab.setValue(lineField, 0);
			}

			if (!m_mTab.dataSave(false))
			{
				throw new IllegalStateException("Could not update tab");
			}

			m_mTab.setCurrentRow(oldRow);
		}
	}

	/**************************************************************************
	 *  Vetoable Change Listener.
	 * 	Called from VEditor
	 *  <pre>
	 *  - for Save Confirmation dialog
	 *  - for Single Row from VEditor: Update MTable
	 *  </pre>
	 *  @param e event
	 *  @throws PropertyVetoException
	 */
	public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException
	{
		if (m_mTab.isProcessed() || !m_mTab.isActive())		//	only active records
		{
			Object source = e.getSource();
			if (source instanceof VEditor)
			{
				if (!((VEditor)source).isReadWrite())
				{
					log.config("(" + m_mTab.toString() + ") " + e.getPropertyName());
					return;
				}
			}
			else
			{
				log.config("(" + m_mTab.toString() + ") " + e.getPropertyName());
				return;
			}
		}	//	processed
		log.config("(" + m_mTab.toString() + ") "
			+ e.getPropertyName() + "=" + e.getNewValue() + " (" + e.getOldValue() + ") "
			+ (e.getOldValue() == null ? "" : e.getOldValue().getClass().getName()));


		//  Save Confirmation dialog    MTable-RowSave
		if (e.getPropertyName().equals(GridTable.PROPERTY))
		{
			//  throw new PropertyVetoException will call this listener again to revert to old value
			if (m_vetoActive)
			{
				//ignore
				m_vetoActive = false;
				return;
			}
			if (!Env.isAutoCommit(Env.getCtx(), m_WindowNo) || m_mTab.getCommitWarning().length() > 0)
			{
				if (!ADialog.ask(m_WindowNo, this, "SaveChanges?", m_mTab.getCommitWarning()))
				{
					m_vetoActive = true;
					throw new PropertyVetoException ("UserDeniedSave", e);
				}
			}
			return;
		}   //  saveConfirmation


		//  Get Row/Col Info
		GridTable mTable = m_mTab.getTableModel();
		int row = m_mTab.getCurrentRow();
		int col = mTable.findColumn(e.getPropertyName());
		//
		if (e.getNewValue() == null && e.getOldValue() != null
			&& e.getOldValue().toString().length() > 0)		//	some editors return "" instead of null
			mTable.setChanged (true);
		else
		{
		//	mTable.setValueAt (e.getNewValue(), row, col, true);
			/*
         	 * Changes: Added the logic below to handle multiple values for a single field
         	 *          due to multiple selection in Lookup (Info).
         	 * @author ashley
         	 */
			Object newValue = e.getNewValue();
			Integer newValues[] = null;

			if (newValue instanceof Integer[])
			{
				newValues = ((Integer[])newValue);
				newValue = newValues[0];

				if (newValues.length > 1)
				{
					Integer valuesCopy[] = new Integer[newValues.length - 1];
					System.arraycopy(newValues, 1, valuesCopy, 0, valuesCopy.length);
					newValues = valuesCopy;
				}
				else
				{
					newValues = null;
				}
			}
			else if (newValue instanceof Object[])
			{
				log.severe("Multiple values can only be processed for IDs (Integer)");
				throw new PropertyVetoException("Multiple Selection values not available for this field", e);
			}

			mTable.setValueAt (newValue, row, col);	//	-> dataStatusChanged -> dynamicDisplay

			//	Force Callout
			if (e.getPropertyName().equals("S_ResourceAssignment_ID"))
			{
				GridField mField = m_mTab.getField(col);
				if (mField != null && mField.getCallout().length() > 0)
					m_mTab.processFieldChange(mField);     //  Dependencies & Callout
			}

			if (newValues != null && newValues.length > 0)
			{
				// Save data, since record need to be used for generating clones.
				if (!m_mTab.dataSave(false))
				{
					throw new PropertyVetoException("SaveError", e);
				}

				// Retrieve the current record ID
				int recordId = m_mTab.getKeyID(m_mTab.getCurrentRow());

				Trx trx = Trx.get(Trx.createTrxName(), true);
				trx.start();
				try
				{
					saveMultipleRecords(Env.getCtx(), mTable.getTableName(), e.getPropertyName(), recordId, newValues, trx.getTrxName());
					trx.commit();
					m_mTab.dataRefreshAll();
				}
				catch(Exception ex)
				{
					trx.rollback();
					log.severe(ex.getMessage());
					throw new PropertyVetoException("SaveError", e);
				}
				finally
				{
					trx.close();
				}
			}
		}

	//	log.config( "GridController.vetoableChange (" + m_mTab.toString() + ") - fini", e.getPropertyName() + "=" + e.getNewValue());
	}   //  vetoableChange


	/**************************************************************************
	 *  Get Model Tab
	 *  @return Model Tab
	 */
	public GridTab getMTab()
	{
		return m_mTab;
	}   //  getMTab

	/**
	 * 	Get Display Logic
	 *	@return Display Logic
	 */
	public String getDisplayLogic()
	{
		return m_mTab.getDisplayLogic();
	}	//	getDisplayLogic

	/**
	 *  Get VTable
	 *  @return VTable
	 */
	public VTable getTable()
	{
		return vTable;
	}   //  getTable


	/**
	 * 	Set Window level Mnemonics
	 *	@param set true if set otherwise unregiser
	 */
	public void setMnemonics (boolean set)
	{
		if (vPanel != null)
			vPanel.setMnemonics(set);
	}	//	setMnemonics

	/**
	 *  Stop Table & SR Editors and move focus to graphPanel
	 *  @param saveValue save value
	 */
	public void stopEditor (boolean saveValue)
	{
		log.config("(" + m_mTab.toString() + ") TableEditing=" + vTable.isEditing());

		//  MultiRow - remove editors
		vTable.stopEditor(saveValue);

		//  SingleRow - stop editors by changing focus
		if (m_singleRow)
			vPanel.transferFocus();
		//	graphPanel.requestFocus();
		//
	//	log.config( "GridController.stopEditor (" + m_mTab.toString() + ") - fini",
	//		"Editing=" + vTable.isEditing());
	}   //  stopEditors

	/**
	 * 	Mouse Clicked
	 *	@param e event
	 */
	public void mouseClicked(MouseEvent e)
	{
		if (CLogMgt.isLevelFinest())
			log.finest("" + this + " - " + e);
	}
	/**
	 * 	Mouse Pressed
	 *	@param e event
	 */
	public void mousePressed(MouseEvent e)
	{
		if (CLogMgt.isLevelFinest())
			log.finest("" + this + " - " + e);
	}
	/**
	 * 	Mouse Released
	 *	@param e event
	 */
	public void mouseReleased(MouseEvent e)
	{
		if (CLogMgt.isLevelFinest())
			log.finest("" + this + " - " + e);
	}
	/**
	 * 	Mouse Entered
	 *	@param e event
	 */
	public void mouseEntered(MouseEvent e)
	{
		if (CLogMgt.isLevelFinest())
			log.finest("" + this + " - " + e);
	}
	/**
	 * 	Mouse Exited
	 *	@param e event
	 */
	public void mouseExited(MouseEvent e)
	{
		if (CLogMgt.isLevelFinest())
			log.finest("" + this + " - " + e);
	}

	/**
	 * 	Get Variable Value
	 *	@param variableName name
	 *	@return value
	 */
	public String get_ValueAsString (String variableName)
	{
		return Env.getContext(Env.getCtx(), m_WindowNo, variableName);
	}	//	get_ValueAsString

	/**
	 * Is controller data not stale
	 * @return boolean
	 */
	public boolean isCurrent()
	{
		return m_mTab != null ? m_mTab.isCurrent() : false;
	}

     //FR [ 1757088 ]
	public VPanel getvPanel()
	{
		return vPanel;
	}

	//BEGIN - [FR 1953734]
	GridController m_Parent;
	public void setGCParent(GridController gc){
		m_Parent = gc;
	}
	public GridController getGCParent(){
		return m_Parent;
	}
	public void refreshMTab(GridController includedTab){
		int m_CurrentRowBeforeSave = includedTab.m_mTab.getCurrentRow();
		m_mTab.dataRefresh(m_mTab.getCurrentRow());
		includedTab.m_mTab.setCurrentRow(m_CurrentRowBeforeSave);
	}
	//END - [FR 1953734]

	/**
	 * Accept pending editor changes.
	 */
	public void acceptEditorChanges()
	{
		if (isSingleRow())
		{
			Component c = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
			if (c != null && this.isAncestorOf(c))
			{
				Component t = c;
				while (t != null && t != this)
				{
					if (t instanceof VManagedEditor)
					{
						((VManagedEditor)t).commitChanges();
						return;
					}
					t = t.getParent();
				}
			}
		}
	}
}   //  GridController
