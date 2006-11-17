/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
package org.compiere.grid.ed;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import org.compiere.apps.*;
import org.compiere.grid.*;
import org.compiere.model.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Dialog to enter Account Info
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VAccountDialog.java,v 1.3 2006/07/30 00:51:28 jjanke Exp $
 */
public final class VAccountDialog extends CDialog
	implements ActionListener, DataStatusListener, VetoableChangeListener
{
	/**
	 * 	Constructor
	 *  @param frame frame
	 *  @param title title
	 *  @param mAccount account info
	 *  @param C_AcctSchema_ID as
	 */
	public VAccountDialog (Frame frame, String title, 
		MAccountLookup mAccount, int C_AcctSchema_ID)
	{
		super (frame, title, true);
		log.config("C_AcctSchema_ID=" + C_AcctSchema_ID 
			+ ", C_ValidCombination_ID=" + mAccount.C_ValidCombination_ID);
		m_mAccount = mAccount;
		m_C_AcctSchema_ID = C_AcctSchema_ID;
		m_WindowNo = Env.createWindowNo (this);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, ex.toString());
		}
		if (initAccount())
			AEnv.showCenterWindow(frame, this);
		else
			dispose();
	}	//	VLocationDialog

	/** Window No					*/
	private int					m_WindowNo;
	/**	Journal Entry				*
	private boolean				m_onlyNonDocControlled = false;
	/** Selection changed			*/
	protected boolean			m_changed = false;

	/** Accounting Schema           */
	private static MAcctSchema	s_AcctSchema = null;
	/** MWindow for AccountCombination  */
	private GridWindow             m_mWindow = null;
	/** MTab for AccountCombination     */
	private GridTab                m_mTab = null;
	/** GridController                  */
	private GridController      m_gridController = null;

	/** Account used                */
	private MAccountLookup		m_mAccount = null;
	/** Result                      */
	private int 				m_C_ValidCombination_ID;
	/** Acct Schema					*/
	private int					m_C_AcctSchema_ID = 0;
	/** Client                      */
	private int                 m_AD_Client_ID;
	/** Where clause for combination search */
	private MQuery				m_query;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VAccountDialog.class);

	//  Editors for Query
	private VEditor 			f_Alias, f_Combination,
		f_AD_Org_ID, f_Account_ID, f_SubAcct_ID,
		f_M_Product_ID, f_C_BPartner_ID, f_C_Campaign_ID, f_C_LocFrom_ID, f_C_LocTo_ID,
		f_C_Project_ID, f_C_SalesRegion_ID, f_AD_OrgTrx_ID, f_C_Activity_ID,
		f_User1_ID, f_User2_ID;
	//
	private JLabel f_Description = new JLabel ("", JLabel.CENTER);

	private GridBagConstraints 	m_gbc = new GridBagConstraints();
	private Insets				m_labelInsets = new Insets(2,15,2,0);		// 	top,left,bottom,right
	private Insets 				m_fieldInsets = new Insets(2,5,2,10);
	private int					m_line = 0;
	private boolean				m_newRow = true;
	//
	private CPanel panel = new CPanel();
	private BorderLayout panelLayout = new BorderLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private TitledBorder titledBorder;
	private StatusBar statusBar = new StatusBar();
	private CPanel northPanel = new CPanel();
	private CPanel parameterPanel = new CPanel();
	private GridBagLayout parameterLayout = new GridBagLayout();
	private BorderLayout northLayout = new BorderLayout();
	private JToolBar toolBar = new JToolBar();
	private CButton bRefresh = new CButton();
	private CButton bSave = new CButton();
	private CButton bIgnore = new CButton();

	/**
	 *	Static component init.
	 *  <pre>
	 *  - panel
	 *      - northPanel
	 *          - parameterPanel
	 *          - toolBar
	 *      - gridController
	 *      - confirmPanel
	 *  - statusBar
	 *  </pre>
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		titledBorder = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(134, 134, 134)), "Parameter");
		//
		panelLayout.setHgap(5);
		panelLayout.setVgap(5);
		northLayout.setHgap(5);
		northLayout.setVgap(5);
		//
		parameterPanel.setLayout(parameterLayout);
		parameterPanel.setBorder(titledBorder);
		northPanel.setLayout(northLayout);
		toolBar.setOrientation(JToolBar.VERTICAL);
		toolBar.setBorder(null);
		toolBar.setRequestFocusEnabled(false);
		toolBar.setBorderPainted(false);
		toolBar.setMargin(new Insets(5, 5, 5, 5));
		bSave.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Save24.gif")));
		bSave.setMargin(new Insets(2, 2, 2, 2));
		bSave.setToolTipText(Msg.getMsg(Env.getCtx(),"AccountNewUpdate"));
		bSave.addActionListener(this);
		bRefresh.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Refresh24.gif")));
		bRefresh.setMargin(new Insets(2, 2, 2, 2));
		bRefresh.setToolTipText(Msg.getMsg(Env.getCtx(),"Refresh"));
		bRefresh.addActionListener(this);
		bIgnore.setIcon(new ImageIcon(org.compiere.Adempiere.class.getResource("images/Ignore24.gif")));
		bIgnore.setMargin(new Insets(2, 2, 2, 2));
		bIgnore.setToolTipText(Msg.getMsg(Env.getCtx(),"Ignore"));
		bIgnore.addActionListener(this);
		//
		toolBar.addSeparator();
		toolBar.add(bRefresh, null);
		toolBar.add(bIgnore, null);
		toolBar.add(bSave, null);
		//
		getContentPane().add(panel);
		panel.setLayout(panelLayout);
		panel.add(confirmPanel, BorderLayout.SOUTH);
		panel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(parameterPanel, BorderLayout.CENTER);
		northPanel.add(toolBar, BorderLayout.EAST);
		//
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dyanmic Init.
	 *  When a row is selected, the editor values are set
	 *  (editors do not change grid)
	 *  @return true if initialized
	 */
	private boolean initAccount()
	{
		m_AD_Client_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "AD_Client_ID");
		//	Get AcctSchema Info
		if (s_AcctSchema == null || s_AcctSchema.getC_AcctSchema_ID() != m_C_AcctSchema_ID)
			s_AcctSchema = new MAcctSchema (Env.getCtx(), m_C_AcctSchema_ID, null);
		log.config(s_AcctSchema.toString()
			+ ", #" + s_AcctSchema.getAcctSchemaElements().length);
		Env.setContext(Env.getCtx(), m_WindowNo, "C_AcctSchema_ID", m_C_AcctSchema_ID);

		//  Model
		int AD_Window_ID = 153;		//	Maintain Account Combinations 
		GridWindowVO wVO = AEnv.getMWindowVO (m_WindowNo, AD_Window_ID, 0);     
		if (wVO == null)
			return false;
		m_mWindow = new GridWindow (wVO);
		m_mTab = m_mWindow.getTab(0);
		//  ParameterPanel restrictions
		m_mTab.getField("Alias").setDisplayLength(15);
		m_mTab.getField("Combination").setDisplayLength(15);
		//  Grid restrictions
		m_mTab.getField("AD_Client_ID").setDisplayed(false);
		m_mTab.getField("C_AcctSchema_ID").setDisplayed(false);
		m_mTab.getField("IsActive").setDisplayed(false);
		m_mTab.getField("IsFullyQualified").setDisplayed(false);
		//  don't show fields not being displayed in this environment
		for (int i = 0; i < m_mTab.getFieldCount(); i++)
		{
			GridField field = m_mTab.getField(i);
			if (!field.isDisplayed (true))      //  check context
				field.setDisplayed (false);
		}

		//  GridController
		m_gridController = new GridController();
		m_gridController.initGrid(m_mTab, true, m_WindowNo, null, null);
		m_gridController.setPreferredSize(new Dimension(300,100));
		panel.add(m_gridController, BorderLayout.CENTER);

		//  Prepare Parameter
		m_gbc.anchor = GridBagConstraints.NORTHWEST;
		m_gbc.gridy = 0;			//	line
		m_gbc.gridx = 0;
		m_gbc.gridwidth = 1;
		m_gbc.insets = m_fieldInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		m_gbc.weightx = 0;
		m_gbc.weighty = 0;

		int TabNo = 0;

		//	Alias
		if (s_AcctSchema.isHasAlias())
		{
			GridField alias = m_mTab.getField("Alias");
			f_Alias = VEditorFactory.getEditor(m_mTab, alias, false);
			addLine(alias, f_Alias, false);
		}	//	Alias

		//	Combination
		GridField combination = m_mTab.getField("Combination");
		f_Combination = VEditorFactory.getEditor(m_mTab, combination, false);
		addLine(combination, f_Combination, false);
		m_newRow = true;

		/**
		 *	Create Fields in Element Order
		 */
		MAcctSchemaElement[] elements = s_AcctSchema.getAcctSchemaElements();
		for (int i = 0; i < elements.length; i++)
		{
			MAcctSchemaElement ase = elements[i];
			String type = ase.getElementType();
			boolean isMandatory = ase.isMandatory();
			//
			if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Organization))
			{
				GridField field = m_mTab.getField("AD_Org_ID");
				f_AD_Org_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_AD_Org_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Account))
			{
				GridField field = m_mTab.getField("Account_ID");
				f_Account_ID = VEditorFactory.getEditor(m_mTab, field, false);
			//	((VLookup)f_Account_ID).setWidth(400);
				addLine(field, f_Account_ID, isMandatory);
				f_Account_ID.addVetoableChangeListener(this);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_SubAccount))
			{
				GridField field = m_mTab.getField("C_SubAcct_ID");
				f_SubAcct_ID = VEditorFactory.getEditor(m_mTab, field, false);
			//	((VLookup)f_SubAcct_ID).setWidth(400);
				addLine(field, f_SubAcct_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Product))
			{
				GridField field = m_mTab.getField("M_Product_ID");
				f_M_Product_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_M_Product_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_BPartner))
			{
				GridField field = m_mTab.getField("C_BPartner_ID");
				f_C_BPartner_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_BPartner_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Campaign))
			{
				GridField field = m_mTab.getField("C_Campaign_ID");
				f_C_Campaign_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_Campaign_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_LocationFrom))
			{
				GridField field = m_mTab.getField("C_LocFrom_ID");
				f_C_LocFrom_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_LocFrom_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_LocationTo))
			{
				GridField field = m_mTab.getField("C_LocTo_ID");
				f_C_LocTo_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_LocTo_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Project))
			{
				GridField field = m_mTab.getField("C_Project_ID");
				f_C_Project_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_Project_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_SalesRegion))
			{
				GridField field = m_mTab.getField("C_SalesRegion_ID");
				f_C_SalesRegion_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_SalesRegion_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_OrgTrx))
			{
				GridField field = m_mTab.getField("AD_OrgTrx_ID");
				f_AD_OrgTrx_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_AD_OrgTrx_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Activity))
			{
				GridField field = m_mTab.getField("C_Activity_ID");
				f_C_Activity_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_C_Activity_ID, isMandatory);
			}
			//	User1
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_UserList1))
			{
				GridField field = m_mTab.getField("User1_ID");
				f_User1_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_User1_ID, isMandatory);
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_UserList2))
			{
				GridField field = m_mTab.getField("User2_ID");
				f_User2_ID = VEditorFactory.getEditor(m_mTab, field, false);
				addLine(field, f_User2_ID, isMandatory);
			}
		}	//	Create Fields in Element Order

		//	Add description
		m_newRow = true;
		m_gbc.gridy = m_line++;
		m_gbc.gridx = 0;
		m_gbc.gridwidth = 4;
		m_gbc.insets = new Insets(5,15,2,0);		// 	top,left,bottom,right
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		f_Description.setFont(f_Description.getFont().deriveFont(Font.ITALIC));
		parameterPanel.add(f_Description, m_gbc);

		//	Finish
		m_query = new MQuery();
		m_query.addRestriction("C_AcctSchema_ID", MQuery.EQUAL, m_C_AcctSchema_ID);
		m_query.addRestriction("IsFullyQualified", MQuery.EQUAL, "Y");
		if (m_mAccount.C_ValidCombination_ID == 0)
			m_mTab.setQuery(MQuery.getEqualQuery("1", "2"));
		else
		{
			MQuery query = new MQuery();
			query.addRestriction("C_AcctSchema_ID", MQuery.EQUAL, m_C_AcctSchema_ID);
			query.addRestriction("C_ValidCombination_ID", MQuery.EQUAL, m_mAccount.C_ValidCombination_ID);
			m_mTab.setQuery(query);
		}
		m_mTab.query(false);
		m_gridController.getTable().addMouseListener(new VAccountDialog_mouseAdapter(this));
		m_gridController.addDataStatusListener(this);

		statusBar.setStatusLine(s_AcctSchema.toString());
		statusBar.setStatusDB("?");

		//	Initial value
		if (m_mAccount.C_ValidCombination_ID != 0)
			m_mTab.navigate(0);

		log.config("fini");
		return true;
	}	//	initAccount

	/**
	 *	Add Editor to parameterPanel alernative right/left depending on m_newRow.
	 *  Field Value changes update Editors
	 *  @param field field
	 *  @param editor editor
	 *  @param mandatory mandatory
	 */
	private void addLine (GridField field, VEditor editor, boolean mandatory)
	{
		log.fine("Field=" + field);
		JLabel label = VEditorFactory.getLabel(field);
		label.setLabelFor((Component)editor);
		editor.setReadWrite(true);
		editor.setMandatory(mandatory);
		//  MField => VEditor
		field.addPropertyChangeListener(editor);

		//	label
		if (m_newRow)
		{
			m_gbc.gridy = m_line++;
			m_gbc.gridx = 0;
		}
		else
			m_gbc.gridx = 2;
		m_gbc.insets = m_labelInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		m_gbc.weightx = 0;
		parameterPanel.add(label, m_gbc);

		//	Field
		if (m_newRow)
			m_gbc.gridx = 1;
		else
			m_gbc.gridx = 3;
		m_gbc.insets = m_fieldInsets;
		m_gbc.fill = GridBagConstraints.HORIZONTAL;
		m_gbc.weightx = 1;
		parameterPanel.add((Component)editor, m_gbc);
		//
		m_newRow = !m_newRow;
	}	//	addLine

	/**
	 *	Load Information
	 *  @param C_ValidCombination_ID valid combination
	 *  @param C_AcctSchema_ID acct schema
	 */
	private void loadInfo (int C_ValidCombination_ID, int C_AcctSchema_ID)
	{
		log.fine("C_ValidCombination_ID=" + C_ValidCombination_ID);
		String sql = "SELECT * FROM C_ValidCombination WHERE C_ValidCombination_ID=? AND C_AcctSchema_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_ValidCombination_ID);
			pstmt.setInt(2, C_AcctSchema_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				if (f_Alias != null)
					f_Alias.setValue(rs.getString("Alias"));
				f_Combination.setValue(rs.getString("Combination"));
				//
				loadInfoOf (rs, f_AD_Org_ID, "AD_Org_ID");
				loadInfoOf (rs, f_Account_ID, "Account_ID");
				loadInfoOf (rs, f_SubAcct_ID, "C_SubAcct_ID");
				//
				loadInfoOf (rs, f_M_Product_ID, "M_Product_ID");
				loadInfoOf (rs, f_C_BPartner_ID, "C_BPartner_ID");
				loadInfoOf (rs, f_C_Campaign_ID, "C_Campaign_ID");
				loadInfoOf (rs, f_C_LocFrom_ID, "C_LocFrom_ID");
				loadInfoOf (rs, f_C_LocTo_ID, "C_LocTo_ID");
				loadInfoOf (rs, f_C_Project_ID, "C_Project_ID");
				loadInfoOf (rs, f_C_SalesRegion_ID, "C_SalesRegion_ID");
				loadInfoOf (rs, f_AD_OrgTrx_ID, "AD_OrgTrx_ID");
				loadInfoOf (rs, f_C_Activity_ID, "C_Activity_ID");
				loadInfoOf (rs, f_User1_ID, "User1_ID");
				loadInfoOf (rs, f_User2_ID, "User2_ID");
				//
				f_Description.setText (rs.getString("Description"));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
	}	//	loadInfo

	/**
	 *	Set Value of Editor
	 *  @param rs result set
	 *  @param editor editor
	 *  @param name name
	 *  @throws SQLException
	 */
	private void loadInfoOf (ResultSet rs, VEditor editor, String name) throws SQLException
	{
		if (editor == null)
			return;
		int intValue = rs.getInt(name);
		if (rs.wasNull())
			editor.setValue(null);
		else
			editor.setValue(new Integer (intValue));
	}	//	loadInfoOf


	/**
	 *	dispose
	 */
	public void dispose()
	{
		saveSelection();
		//  GridController
		if (m_gridController != null)
			m_gridController.dispose();
		m_gridController = null;
		//  Model
		m_mTab = null;
		if (m_mWindow != null)
			m_mWindow.dispose();
		m_mWindow = null;

		removeAll();
		Env.clearWinContext(m_WindowNo);
		super.dispose();
	}	//	dispose

	/**
	 *	Save Selection
	 */
	private void saveSelection()
	{
		if (m_changed && m_gridController != null)
		{
			int row = m_gridController.getTable().getSelectedRow();
			if (row >= 0)
				m_C_ValidCombination_ID = ((Integer)m_mTab.getValue(row, "C_ValidCombination_ID")).intValue();
			log.config("(" + row + ") - " + m_C_ValidCombination_ID);
		}
	}	//	saveSelection

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			m_changed = true;
			dispose();
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			m_changed = false;
			dispose();
		}
		//
		else if (e.getSource() == bSave)
			action_Save();
		else if (e.getSource() == bIgnore)
			action_Ignore();
		//	all other
		else
			action_Find (true);

	}	//	actionPerformed


	/**
	 *	Status Change Listener
	 *  @param e event
	 */
	public void dataStatusChanged (DataStatusEvent e)
	{
		log.config(e.toString());
		String info = (String)m_mTab.getValue("Description");
		f_Description.setText (info);
		//
	//	log.info( ">> Field=" + m_mTab.getValue("AD_Org_ID"),
	//		"Editor=" + f_AD_Org_ID.getValue());
	//	if (f_AD_Org_ID.getValue() == null)
	//		f_AD_Org_ID.setValue(m_mTab.getValue("AD_Org_ID"));
	}	//	statusChanged


	/**
	 *	Action Find.
	 *	- create where clause
	 *	- query database
	 *  @param includeAliasCombination include alias combination
	 */
	private void action_Find (boolean includeAliasCombination)
	{
		log.info("");
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		//	Create where Clause
		MQuery query = null;
		if (m_query != null)
			query = m_query.deepCopy();
		else
			query = new MQuery();
		//	Alias
		if (includeAliasCombination && f_Alias != null && f_Alias.getValue().toString().length() > 0)
		{
			String value = f_Alias.getValue().toString().toUpperCase();
			if (!value.endsWith("%"))
				value += "%";
			query.addRestriction("UPPER(Alias)", MQuery.LIKE, value);
		}
		//	Combination (mandatory)
		if (includeAliasCombination && f_Combination.getValue().toString().length() > 0)
		{
			String value = f_Combination.getValue().toString().toUpperCase();
			if (!value.endsWith("%"))
				value += "%";
			query.addRestriction("UPPER(Combination)", MQuery.LIKE, value);
		}
		//	Org (mandatory)
		if (f_AD_Org_ID != null && f_AD_Org_ID.getValue() != null)
			query.addRestriction("AD_Org_ID", MQuery.EQUAL, f_AD_Org_ID.getValue());
		//	Account (mandatory)
		if (f_Account_ID != null && f_Account_ID.getValue() != null)
			query.addRestriction("Account_ID", MQuery.EQUAL, f_Account_ID.getValue());
		if (f_SubAcct_ID != null && f_SubAcct_ID.getValue() != null)
			query.addRestriction("C_SubAcct_ID", MQuery.EQUAL, f_SubAcct_ID.getValue());

		//	Product
		if (f_M_Product_ID != null && f_M_Product_ID.getValue() != null)
			query.addRestriction("M_Product_ID", MQuery.EQUAL, f_M_Product_ID.getValue());
		//	BPartner
		if (f_C_BPartner_ID != null && f_C_BPartner_ID.getValue() != null)
			query.addRestriction("C_BPartner_ID", MQuery.EQUAL, f_C_BPartner_ID.getValue());
		//	Campaign
		if (f_C_Campaign_ID != null && f_C_Campaign_ID.getValue() != null)
			query.addRestriction("C_Campaign_ID", MQuery.EQUAL, f_C_Campaign_ID.getValue());
		//	Loc From
		if (f_C_LocFrom_ID != null && f_C_LocFrom_ID.getValue() != null)
			query.addRestriction("C_LocFrom_ID", MQuery.EQUAL, f_C_LocFrom_ID.getValue());
		//	Loc To
		if (f_C_LocTo_ID != null && f_C_LocTo_ID.getValue() != null)
			query.addRestriction("C_LocTo_ID", MQuery.EQUAL, f_C_LocTo_ID.getValue());
		//	Project
		if (f_C_Project_ID != null && f_C_Project_ID.getValue() != null)
			query.addRestriction("C_Project_ID", MQuery.EQUAL, f_C_Project_ID.getValue());
		//	SRegion
		if (f_C_SalesRegion_ID != null && f_C_SalesRegion_ID.getValue() != null)
			query.addRestriction("C_SalesRegion_ID", MQuery.EQUAL, f_C_SalesRegion_ID.getValue());
		//	Org Trx
		if (f_AD_OrgTrx_ID != null && f_AD_OrgTrx_ID.getValue() != null)
			query.addRestriction("AD_OrgTrx_ID", MQuery.EQUAL, f_AD_OrgTrx_ID.getValue());
		//	Activity
		if (f_C_Activity_ID != null && f_C_Activity_ID.getValue() != null)
			query.addRestriction("C_Activity_ID", MQuery.EQUAL, f_C_Activity_ID.getValue());
		//	User 1
		if (f_User1_ID != null && f_User1_ID.getValue() != null)
			query.addRestriction("User1_ID", MQuery.EQUAL, f_User1_ID.getValue());
		//	User 2
		if (f_User2_ID != null && f_User2_ID.getValue() != null)
			query.addRestriction("User2_ID", MQuery.EQUAL, f_User2_ID.getValue());

		//	Query
		m_mTab.setQuery(query);
		m_mTab.query(false);
		statusBar.setStatusDB(String.valueOf(m_mTab.getRowCount()));
		setCursor (Cursor.getDefaultCursor());
	}	//	action_Find


	/**
	 *	Create/Save Account
	 */
	private void action_Save()
	{
		log.info("");
		/**
		 *	Check completeness (mandatory fields) ... and for duplicates
		 */
		StringBuffer sb = new StringBuffer();
		StringBuffer sql = new StringBuffer ("SELECT C_ValidCombination_ID, Alias FROM C_ValidCombination WHERE ");
		Object value = null;
		if (s_AcctSchema.isHasAlias())
		{
			value = f_Alias.getValue().toString();
			if (value == null)
				sb.append(Msg.translate(Env.getCtx(), "Alias")).append(", ");
		}
		MAcctSchemaElement[] elements = s_AcctSchema.getAcctSchemaElements();
		for (int i = 0; i < elements.length; i++)
		{
			MAcctSchemaElement ase = elements[i];
			String type = ase.getElementType();
			//
			if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Organization))
			{
				value = f_AD_Org_ID.getValue();
				sql.append("AD_Org_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Account))
			{
				value = f_Account_ID.getValue();
				sql.append("Account_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_SubAccount))
			{
				value = f_SubAcct_ID.getValue();
				sql.append("C_SubAcct_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Product))
			{
				value = f_M_Product_ID.getValue();
				sql.append("M_Product_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_BPartner))
			{
				value = f_C_BPartner_ID.getValue();
				sql.append("C_BPartner_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Campaign))
			{
				value = f_C_Campaign_ID.getValue();
				sql.append("C_Campaign_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_LocationFrom))
			{
				value = f_C_LocFrom_ID.getValue();
				sql.append("C_LocFrom_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_LocationTo))
			{
				value = f_C_LocTo_ID.getValue();
				sql.append("C_LocTo_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Project))
			{
				value = f_C_Project_ID.getValue();
				sql.append("C_Project_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_SalesRegion))
			{
				value = f_C_SalesRegion_ID.getValue();
				sql.append("C_SalesRegion_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_OrgTrx))
			{
				value = f_AD_OrgTrx_ID.getValue();
				sql.append("AD_OrgTrx_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_Activity))
			{
				value = f_C_Activity_ID.getValue();
				sql.append("C_Activity_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_UserList1))
			{
				value = f_User1_ID.getValue();
				sql.append("User1_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			else if (type.equals(MAcctSchemaElement.ELEMENTTYPE_UserList2))
			{
				value = f_User2_ID.getValue();
				sql.append("User2_ID");
				if (value == null)
					sql.append(" IS NULL AND ");
				else
					sql.append("=").append(value).append(" AND ");
			}
			//
			if (ase.isMandatory() && value == null)
				sb.append(ase.getName()).append(", ");
		}	//	Fields in Element Order

		if (sb.length() != 0)
		{
			ADialog.error(m_WindowNo, this, "FillMandatory", sb.substring(0, sb.length()-2));
			return;
		}
		if (f_AD_Org_ID == null || f_AD_Org_ID.getValue() == null)
		{
			ADialog.error(m_WindowNo, this, "FillMandatory", Msg.getElement(Env.getCtx(), "AD_Org_ID"));
			return;
		}
		if (f_Account_ID == null || f_Account_ID.getValue() == null)
		{
			ADialog.error(m_WindowNo, this, "FillMandatory", Msg.getElement(Env.getCtx(), "Account_ID"));
			return;
		}

		
		/**
		 *	Check if already exists
		 */
		sql.append("AD_Client_ID=? AND C_AcctSchema_ID=?");
		log.fine("Check = " + sql.toString());
		int IDvalue = 0;
		String Alias = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, m_AD_Client_ID);
			pstmt.setInt(2, s_AcctSchema.getC_AcctSchema_ID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				IDvalue = rs.getInt(1);
				Alias = rs.getString(2);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
			IDvalue = 0;
		}
		log.fine("ID=" + IDvalue + ", Alias=" + Alias);

		if (Alias == null)
			Alias = "";

		//	We have an account like this already - check alias
		if (IDvalue != 0 && s_AcctSchema.isHasAlias()
			&& !f_Alias.getValue().toString().equals(Alias))
		{
			sql = new StringBuffer("UPDATE C_ValidCombination SET Alias=");
			if (f_Alias.getValue().toString().length() == 0)
				sql.append("NULL");
			else
				sql.append("'").append(f_Alias.getValue()).append("'");
			sql.append(" WHERE C_ValidCombination_ID=").append(IDvalue);
			int i = 0;
			try
			{
				java.sql.Statement stmt = DB.createStatement();
				i = stmt.executeUpdate(sql.toString());
				stmt.close();
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql.toString(), e);
			}
			if (i == 0)
				ADialog.error(m_WindowNo, this, "AccountNotUpdated");
		}

		//	load and display
		if (IDvalue != 0)
		{
			loadInfo (IDvalue, s_AcctSchema.getC_AcctSchema_ID());
			action_Find (false);
			return;
		}

		log.config("New");
		Alias = null;
		if (f_Alias != null)
			Alias = f_Alias.getValue().toString();
		int C_SubAcct_ID = 0;
		if (f_SubAcct_ID != null && f_SubAcct_ID.getValue() != null)
			C_SubAcct_ID = ((Integer)f_SubAcct_ID.getValue()).intValue();
		int M_Product_ID = 0;
		if (f_M_Product_ID != null && f_M_Product_ID.getValue() != null)
			M_Product_ID = ((Integer)f_M_Product_ID.getValue()).intValue();
		int C_BPartner_ID = 0;
		if (f_C_BPartner_ID != null && f_C_BPartner_ID.getValue() != null)
			C_BPartner_ID = ((Integer)f_C_BPartner_ID.getValue()).intValue();
		int AD_OrgTrx_ID = 0;
		if (f_AD_OrgTrx_ID != null && f_AD_OrgTrx_ID.getValue() != null)
			AD_OrgTrx_ID = ((Integer)f_AD_OrgTrx_ID.getValue()).intValue();
		int C_LocFrom_ID = 0;
		if (f_C_LocFrom_ID != null && f_C_LocFrom_ID.getValue() != null)
			C_LocFrom_ID = ((Integer)f_C_LocFrom_ID.getValue()).intValue();
		int C_LocTo_ID = 0;
		if (f_C_LocTo_ID != null && f_C_LocTo_ID.getValue() != null)
			C_LocTo_ID = ((Integer)f_C_LocTo_ID.getValue()).intValue();
		int C_SRegion_ID = 0;
		if (f_C_SalesRegion_ID != null && f_C_SalesRegion_ID.getValue() != null)
			C_SRegion_ID = ((Integer)f_C_SalesRegion_ID.getValue()).intValue();
		int C_Project_ID = 0;
		if (f_C_Project_ID != null && f_C_Project_ID.getValue() != null)
			C_Project_ID=  ((Integer)f_C_Project_ID.getValue()).intValue();
		int C_Campaign_ID = 0;
		if (f_C_Campaign_ID != null && f_C_Campaign_ID.getValue() != null)
			C_Campaign_ID = ((Integer)f_C_Campaign_ID.getValue()).intValue();
		int C_Activity_ID = 0;
		if (f_C_Activity_ID != null && f_C_Activity_ID.getValue() != null)
			C_Activity_ID = ((Integer)f_C_Activity_ID.getValue()).intValue();
		int User1_ID = 0;
		if (f_User1_ID != null && f_User1_ID.getValue() != null)
			User1_ID = ((Integer)f_User1_ID.getValue()).intValue();
		int User2_ID = 0;
		if (f_User2_ID != null && f_User2_ID.getValue() != null)
			User2_ID = ((Integer)f_User2_ID.getValue()).intValue();

		MAccount acct = MAccount.get (Env.getCtx(), m_AD_Client_ID, 
			((Integer)f_AD_Org_ID.getValue()).intValue(),
			s_AcctSchema.getC_AcctSchema_ID(), 
			((Integer)f_Account_ID.getValue()).intValue(), C_SubAcct_ID,
			M_Product_ID, C_BPartner_ID, AD_OrgTrx_ID,
			C_LocFrom_ID, C_LocTo_ID, C_SRegion_ID, 
			C_Project_ID, C_Campaign_ID, C_Activity_ID, 
			User1_ID, User2_ID, 0, 0);
		if (acct != null && acct.get_ID() == 0)
			acct.save();

		//  Show Info
		if (acct == null || acct.get_ID() == 0)
			loadInfo (0, 0);
		else
		{
			//	Update Account with optional Alias
			if (Alias != null && Alias.length() > 0)
			{
				acct.setAlias(Alias);
				acct.save();
			}
			loadInfo (acct.get_ID(), s_AcctSchema.getC_AcctSchema_ID());
		}
		action_Find (false);
	}	//	action_Save


	/**
	 *	Ignore
	 */
	private void action_Ignore()
	{
		if (f_Alias != null)
			f_Alias.setValue("");
		f_Combination.setValue("");
		f_Description.setText("");
		//
		//	Org (mandatory)
		f_AD_Org_ID.setValue(null);
		//	Account (mandatory)
		f_Account_ID.setValue(null);
		f_SubAcct_ID.setValue(null);

		//	Product
		if (f_M_Product_ID != null)
			f_M_Product_ID.setValue(null);
		//	BPartner
		if (f_C_BPartner_ID != null)
			f_C_BPartner_ID.setValue(null);
		//	Campaign
		if (f_C_Campaign_ID != null)
			f_C_Campaign_ID.setValue(null);
		//	Loc From
		if (f_C_LocFrom_ID != null)
			f_C_LocFrom_ID.setValue(null);
		//	Loc To
		if (f_C_LocTo_ID != null)
			f_C_LocTo_ID.setValue(null);
		//	Project
		if (f_C_Project_ID != null)
			f_C_Project_ID.setValue(null);
		//	SRegion
		if (f_C_SalesRegion_ID != null)
			f_C_SalesRegion_ID.setValue(null);
		//	Org Trx
		if (f_AD_OrgTrx_ID != null)
			f_AD_OrgTrx_ID.setValue(null);
		//	Activity
		if (f_C_Activity_ID != null)
			f_C_Activity_ID.setValue(null);
		//	User 1
		if (f_User1_ID != null)
			f_User1_ID.setValue(null);
		//	User 2
		if (f_User2_ID != null)
			f_User2_ID.setValue(null);
	}	//	action_Ignore

	/**
	 *	Get selected account
	 *  @return account
	 */
	public Integer getValue()
	{
		log.config("C_ValidCombination_ID=" + m_C_ValidCombination_ID + ", Changed=" + m_changed);
		if (!m_changed || m_C_ValidCombination_ID == 0)
			return null;
		return new Integer(m_C_ValidCombination_ID);
	}

	/**
	 * 	VetoableChange - Account Changed
	 *	@param evt event
	 *	@throws PropertyVetoException
	 */
	public void vetoableChange (PropertyChangeEvent evt)
		throws PropertyVetoException
	{
		Object newValue = evt.getNewValue();
		if (newValue instanceof Integer)
			Env.setContext(Env.getCtx(), m_WindowNo, "Account_ID", ((Integer)newValue).intValue());
	}	//	vetoableChange

}	//	VAccountDialog

/**
 *	Mouse Listener
 */
class VAccountDialog_mouseAdapter extends java.awt.event.MouseAdapter
{
	VAccountDialog_mouseAdapter(VAccountDialog adaptee)
	{
		this.adaptee = adaptee;
	}

	VAccountDialog adaptee;

	public void mouseClicked(MouseEvent e)
	{
		//	Table => select
		if (e.getSource() instanceof JTable && e.getClickCount() > 1)
		{
			adaptee.m_changed = true;
			adaptee.dispose();
		}
	}
}	//	VAccountDialog_mouseListener
