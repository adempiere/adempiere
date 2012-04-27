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
package org.compiere.apps.search;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.ColorUIResource;


import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.plaf.AdempiereTaskPaneUI;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VCheckBox;
import org.compiere.grid.ed.VLookup;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.MiniTable;
import org.compiere.model.MLocation;
import org.compiere.model.MQuery;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTabbedPane;
import org.compiere.swing.CTextField;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.jdesktop.swingx.JXTaskPane;

/**
 *	Search Business Partner and return selection
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: InfoBPartner.java,v 1.3 2006/07/30 00:51:27 jjanke Exp $
 *
 * @author Michael McKay, 
 * 				<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoBPartner extends Info implements ChangeListener, PropertyChangeListener, ActionListener, ListSelectionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5550733934685665946L;

	/**
	 *	Standard Constructor
	 *  @param frame frame
	 *  @param modal modal
	 *  @param WindowNo WindowNo
	 *  @param  value   Query value Name or Value if contains numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param multiSelection multiple selection
	 *  @param whereClause where clause
	 */
	@Deprecated
	public InfoBPartner(Frame frame, boolean modal, int WindowNo,
		String value, boolean isSOTrx,
		boolean multiSelection, String whereClause)
	{
		this(frame, modal, WindowNo, 0,
				value, isSOTrx, true,
				multiSelection, true, whereClause);
	}
	
	/**
	 *	Standard Constructor
	 *  @param frame frame
	 *  @param modal modal
	 *  @param WindowNo WindowNo
	 *  @param record_id  The record_id of the calling field
	 *  @param  value   Query value Name or Value if contains numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param isSOMatch true if the record_id status matches isSOTrx
	 *  @param multiSelection multiple selection
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 */
	public InfoBPartner(Frame frame, boolean modal, int WindowNo, int record_id,
		String value, boolean isSOTrx, boolean isSOMatch,
		boolean multiSelection, boolean saveResults, String whereClause)
	{
		super (frame, modal, WindowNo, "C_BPartner", "C_BPartner_ID", multiSelection, saveResults, whereClause);
			log.info(value);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoBPartner"));
		m_isSOTrx = isSOTrx;
		m_isSOMatch = isSOMatch;
		//
		statInit();
		initInfo (record_id, value, whereClause);
		//
		int no = p_table.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));

		//	AutoQuery
		if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
		{
			executeQuery();
			refresh();
		}

		p_loadedOK = true;

		//  To get the focus after the table update
		m_heldLastFocus = fieldValue;

		AEnv.positionCenterWindow(frame, this);
	}	//	InfoBPartner

	private Object m_heldLastFocus = null;

	/** SalesOrder Trx          */
	private boolean 		m_isSOTrx = false;
	private boolean			m_isSOMatch = true;

	private int m_AD_User_ID_index = -1;
	private int m_C_BPartner_Location_ID_index = -1;

	/** From Clause             */
	private static String s_partnerFROM = "C_BPartner";

	/**  Array of Column Info    */
	private static Info_Column[] s_partnerLayout = {
		new Info_Column(" ", "C_BPartner.C_BPartner_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Value"), "C_BPartner.Value", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Name"), "C_BPartner.Name", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "C_BP_Group_ID"), "(SELECT bpg.Name FROM C_BP_Group bpg WHERE bpg.C_BP_Group_ID = C_BPartner.C_BP_Group_ID)", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "TotalOpenBalance"), "C_BPartner.TotalOpenBalance", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "SO_CreditAvailable"), "C_BPartner.SO_CreditLimit-C_BPartner.SO_CreditUsed AS SO_CreditAvailable", BigDecimal.class, true, true, null),
		new Info_Column(Msg.translate(Env.getCtx(), "SO_CreditUsed"), "C_BPartner.SO_CreditUsed", BigDecimal.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Revenue"), "C_BPartner.ActualLifetimeValue", BigDecimal.class)
	};

	//
	private int fieldID = 0;
	private CLabel labelValue = new CLabel();
	private CTextField fieldValue = new CTextField(10);
	private CLabel labelName = new CLabel();
	private CTextField fieldName = new CTextField(10);
	private CLabel labelContact = new CLabel();
	private CTextField fieldContact = new CTextField(10);
	private CLabel labelEMail = new CLabel();
	private CTextField fieldEMail = new CTextField(10);
	private CLabel labelPostal = new CLabel();
	private CTextField fieldPostal = new CTextField(10);
	private CLabel labelPhone = new CLabel();
	private CTextField fieldPhone = new CTextField(10);
	private VCheckBox checkAND = new VCheckBox();
	private VCheckBox checkCustomer = new VCheckBox();
	//
	private JXTaskPane detailPanel = new JXTaskPane();
	private CPanel tablePanel = new CPanel();
	private CTabbedPane jTab  = new CTabbedPane();
	private MiniTable contactTbl = new MiniTable();
	private MiniTable addressTbl = new MiniTable();
	private String m_sqlContact;
	private String m_sqlAddress;

	private int m_C_BPartner_ID = 0;
	private static int ADDRESS_INDEX;

	/**
	 *	Static Setup - add fields to parameterPanel
	 */
	private void statInit()
	{
		// Listen to changes in the table
		p_table.addPropertyChangeListener("p_table_update", this);
		((ListSelectionModel) p_table.getSelectionModel()).addListSelectionListener(this);

		labelValue.setText(Msg.getMsg(Env.getCtx(), "Value"));
		fieldValue.setBackground(AdempierePLAF.getInfoBackground());
		fieldValue.addActionListener(this);

		labelName.setText(Msg.getMsg(Env.getCtx(), "Name"));
		fieldName.setBackground(AdempierePLAF.getInfoBackground());
		fieldName.addActionListener(this);

		labelContact.setText(Msg.getMsg(Env.getCtx(), "Contact"));
		fieldContact.setBackground(AdempierePLAF.getInfoBackground());
		fieldContact.addActionListener(this);
		
		labelEMail.setText(Msg.getMsg(Env.getCtx(), "EMail"));
		fieldEMail.setBackground(AdempierePLAF.getInfoBackground());
		fieldEMail.addActionListener(this);

		labelPostal.setText(Msg.getMsg(Env.getCtx(), "Postal"));
		fieldPostal.setBackground(AdempierePLAF.getInfoBackground());
		fieldPostal.addActionListener(this);

		labelPhone.setText(Msg.translate(Env.getCtx(), "Phone"));
		fieldPhone.setBackground(AdempierePLAF.getInfoBackground());
		fieldPhone.addActionListener(this);

		checkAND.setText(Msg.getMsg(Env.getCtx(), "SearchAND"));
		checkAND.setToolTipText(Msg.getMsg(Env.getCtx(), "SearchANDInfo"));
		checkAND.setSelected(true);
		checkAND.addActionListener(this);
		if (m_isSOTrx)
			checkCustomer.setText(Msg.getMsg(Env.getCtx(), "OnlyCustomers"));
		else
			checkCustomer.setText(Msg.getMsg(Env.getCtx(), "OnlyVendors"));
		checkCustomer.setSelected(m_isSOMatch);
		//checkCustomer.setFocusable(false);
		//checkCustomer.setRequestFocusEnabled(false);
		checkCustomer.addActionListener(this);
		//
		parameterPanel.setLayout(new ALayout());
		//
		parameterPanel.add(labelValue, new ALayoutConstraint(0,0));
		parameterPanel.add(fieldValue, null);
		parameterPanel.add(labelContact, null);
		parameterPanel.add(fieldContact, null);
		parameterPanel.add(labelPhone, null);
		parameterPanel.add(fieldPhone, null);
		parameterPanel.add(checkCustomer, null);
		//		
		parameterPanel.add(labelName, new ALayoutConstraint(1,0));
		parameterPanel.add(fieldName, null);
		parameterPanel.add(labelEMail, null);
		parameterPanel.add(fieldEMail, null);
		parameterPanel.add(labelPostal, null);
		parameterPanel.add(fieldPostal, null);
		parameterPanel.add(checkAND, null);
		
		//  Details Panel
        detailPanel.setTitle(Msg.translate(Env.getCtx(), "ContactAndAddress"));
        detailPanel.setUI(new AdempiereTaskPaneUI());
        detailPanel.getContentPane().setBackground(new ColorUIResource(251,248,241));
        detailPanel.getContentPane().setForeground(new ColorUIResource(251,0,0));
        
        //  Contact Tab
        ColumnInfo[] s_layoutContact = new ColumnInfo[]{
        		new ColumnInfo(" ", "AD_User_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Greeting_ID"), "(SELECT g.Greeting from C_Greeting g WHERE g.C_Greeting_ID = AD_User.C_Greeting_ID)", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Title"), "Title", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Location_ID"), "(SELECT a.Name from C_BPartner_Location a WHERE AD_User.C_BPartner_Location_ID=a.C_BPartner_Location_ID)", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "Phone", String.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone2"), "Phone2", String.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "Fax"), "Fax", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "EMail"), "EMail", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "LastContact"), "LastContact", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "LastResult"), "LastResult", String.class)};
        //  From Clause
        String s_sqlFrom = "AD_User";
        //  Where Clause					
        String s_sqlWhere = "C_BPartner_ID = ?  and IsActive = 'Y'";
        m_sqlContact = contactTbl.prepareTable(s_layoutContact, s_sqlFrom, s_sqlWhere, false, "AD_User");
		contactTbl.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 255 : 110));
		contactTbl.setRowSelectionAllowed(true);
		contactTbl.setMultiSelection(false);
		contactTbl.addMouseListener(this);
		contactTbl.getSelectionModel().addListSelectionListener(this);
		contactTbl.setShowTotals(false);
		contactTbl.autoSize();
        contactTbl.setBackground(new ColorUIResource(251,248,241));
        contactTbl.setForeground(new ColorUIResource(251,0,0));

        //  Location Tab
        ColumnInfo[] s_layoutAddress = new ColumnInfo[]{
        		new ColumnInfo(" ", "l.C_BPartner_Location_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "l.Name", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone"), "l.Phone", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Phone2"), "l.Phone2", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Fax"), "l.Fax", String.class),        		
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Address"), "a.Address1", String.class), // Replaced with parsed value
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsShipTo"), "l.IsShipTo", Boolean.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsBillTo"), "l.IsBillTo", Boolean.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsRemitTo"), "l.IsRemitTo", Boolean.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "IsPayFrom"), "l.IsPayFrom", Boolean.class)};
        ADDRESS_INDEX = 5;
        /**	From Clause							*/
        String s_locationFrom = "C_BPartner_Location l" 
    		+ " LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)";
        /** Where Clause						*/
        String s_locationWhere = "l.C_BPartner_ID = ? and l.IsActive = 'Y'";
        m_sqlAddress = addressTbl.prepareTable(s_layoutAddress, s_locationFrom, s_locationWhere, false, "l");
		addressTbl.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 255 : 110));
		addressTbl.setRowSelectionAllowed(true);
		addressTbl.setMultiSelection(false);
		addressTbl.addMouseListener(this);
		addressTbl.getSelectionModel().addListSelectionListener(this);
		addressTbl.setShowTotals(false);
		addressTbl.autoSize();
		addressTbl.setBackground(new ColorUIResource(251,248,241));
		addressTbl.setForeground(new ColorUIResource(251,0,0));
        
        jTab.addTab(Msg.translate(Env.getCtx(), "Contact"), new JScrollPane(contactTbl));
        jTab.addTab(Msg.translate(Env.getCtx(), "Location"), new JScrollPane(addressTbl));
        jTab.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 250 : 105));
		jTab.addChangeListener(this);  // needed if other tabs are added
        tablePanel.setPreferredSize(new Dimension(INFO_WIDTH, SCREEN_HEIGHT > 600 ? 255 : 110));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(jTab, BorderLayout.CENTER);
        
        detailPanel.setCollapsed(true);
        detailPanel.setLayout(new BorderLayout());
        detailPanel.add(tablePanel, BorderLayout.CENTER);
        this.addonPanel.setLayout(new BorderLayout());
        this.addonPanel.add(detailPanel, BorderLayout.CENTER);
        
        this.p_table.getSelectionModel().addListSelectionListener(this);
	}	//	statInit

	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
	private void initInfo(int record_id, String value, String whereClause)
	{
		/**	From
			C_BPartner
		**/

		//	Create Grid
		StringBuffer where = new StringBuffer();
		where.append("C_BPartner.IsSummary='N' AND C_BPartner.IsActive='Y'");
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		
		prepareTable(s_partnerLayout, s_partnerFROM,
			where.toString(),
			"C_BPartner.Value");
		p_table.setShowTotals(true);

		//  Get indexes
		for (int i = 0; i < p_layout.length; i++)
		{
			if (p_layout[i].getIDcolSQL().indexOf("AD_User_ID") != -1)
				m_AD_User_ID_index = i;
			if (p_layout[i].getIDcolSQL().indexOf("C_BPartner_Location_ID") != -1)
				m_C_BPartner_Location_ID_index = i;
		}

		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        }
        else
        {
			if (value != null && value.length() > 0)
			{
				//	Put query string in Name if not fully numeric
	    		if (!value.matches(".*\\D+.*")) // If value has no non-digit characters, use the Value
	    			fieldValue.setText(value);
	    		else
	    			fieldName.setText(value);  // A few non-digit characters might be in the name. E.g. 451Group, 1st Choice, ...
			}
			else
			{
				//  Try to find the fieldID from the context
	        	String bp = Env.getContext(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
				if (bp != null && bp.length() != 0)
				{
					fieldID = new Integer(bp).intValue();
				}
			}
        }
	}	//	initInfo

	/**
	 * 	Refresh Query
	 */
	private void refresh()
	{
		//  Invoke later to not delay events.
		SwingUtilities.invokeLater(new Runnable(){public void run()
		{
					
			PreparedStatement pstmt = null;
			ResultSet rs = null;
	    	
			//  Contact tab	
			log.finest(m_sqlContact);
			try
			{
				pstmt = DB.prepareStatement(m_sqlContact, null);
				pstmt.setInt(1, m_C_BPartner_ID);
				rs = pstmt.executeQuery();
				contactTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, m_sqlContact, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
			
			//  Address tab	
			log.finest(m_sqlAddress);
			try
			{
				pstmt = DB.prepareStatement(m_sqlAddress, null);
				pstmt.setInt(1, m_C_BPartner_ID);
				rs = pstmt.executeQuery();
				addressTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, m_sqlContact, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}

			String trxName = Trx.createTrxName();
            for (int row=0; row < addressTbl.getRowCount(); row++)
			{
				int loc_id = 0;
				Object loc_data = addressTbl.getValueAt(row, addressTbl.getKeyColumnIndex());
	            if (loc_data != null && loc_data instanceof IDColumn)
	            {
	            	IDColumn dataColumn = (IDColumn) loc_data;
	        		loc_id = dataColumn.getRecord_ID();
	            }

				MLocation loc = MLocation.getBPLocation(Env.getCtx(), loc_id, trxName);
				addressTbl.setValueAt(loc.toString(), row, ADDRESS_INDEX);
			}
			Trx.get(trxName, false).close();
			addressTbl.autoSize();
		}});
		
	}	//	refresh
	/**************************************************************************
	 *  Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		// Handle actions if possible or pass the event to the parent class

		String cmd = e.getActionCommand();
		
		if(e.getSource() != null)
			m_heldLastFocus = e.getSource();

		if (cmd.equals(ConfirmPanel.A_REFRESH) || autoQuery())
		{
			//  A requery will be performed
			//  Find what is currently selected
			//  Re-selection of the column happens in the propertyChange listener
			Integer selectedKey = (Integer) getSelectedRowKey();
	        if(selectedKey != null && selectedKey.intValue() != 0)
	        	p_selectedRecordKey = selectedKey.intValue();  
		}
		//
		super.actionPerformed(e);  //  Info class executes the query.
		
	}   //  actionPerformed
	/**
	 * Property Change Listener for lookup fields
	 * @param e event
	 */
	public void propertyChange(PropertyChangeEvent e)
	{		
		// Respond to updates to the table
		if (e.getPropertyName() == "p_table_update")
		{
			//  Try to reselect the record
			if(!setSelectedRow(p_selectedRecordKey))		
			{
				//  Nothing was selected, or the query is empty
				//  - close the panel
				m_C_BPartner_ID = 0;
				detailPanel.setCollapsed(true);
			}
			else  //  Found and selected the same record or selected the first record
			{
				m_C_BPartner_ID = getSelectedRowKey();
				refresh();
				detailPanel.setCollapsed(false);
				}
			refresh(); 
			detailPanel.setCollapsed(!(p_table.getRowCount() > 0));
			p_selectedRecordKey = 0;
			p_refreshRequired = false;
			
			//  Return focus to where it is expected to be
			parameterPanel.requestFocus();
			if (m_heldLastFocus instanceof CTextField)
				((CTextField) m_heldLastFocus).requestFocus();
			if (m_heldLastFocus instanceof VLookup)
				((VLookup) m_heldLastFocus).requestFocus();
			if (m_heldLastFocus instanceof VCheckBox)
				((VCheckBox) m_heldLastFocus).requestFocus();
		}
	}

	/**
	 * 	Tab Changed
	 * 	@param e event
	 */
	public void stateChanged(ChangeEvent e)
	{		
	}	//	stateChanged

	/**
	 * Watch for changes in the selection of the main table and update the detail table accordingly.
	 */
	public void valueChanged(ListSelectionEvent lse)
	{
    	int leadRowKey = p_table.getLeadRowKey();
    	if (m_C_BPartner_ID != leadRowKey)
    	{
    		m_C_BPartner_ID = leadRowKey;
    		refresh();  //  Update the detailPanel with the current selected record
    		enableButtons();	//  Set the buttons according
    	}
		detailPanel.setCollapsed(false);
	}
	/*************************************************************************/

	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return WHERE clause
	 */
	protected String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
			list.add("C_BPartner.C_BPartner_ID = ?");
		//	=> Value
		if (isValidSQLText(fieldValue))
			list.add ("UPPER(C_BPartner.Value) LIKE ?");
		//	=> Name
		if (isValidSQLText(fieldName))
			list.add ("UPPER(C_BPartner.Name) LIKE ?");
		//	=> Contact
		if (isValidSQLText(fieldContact))
			list.add ("C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID from AD_User c WHERE UPPER(c.Name) LIKE ?)");
		//	=> EMail
		if (isValidSQLText(fieldEMail))
			list.add ("C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID from AD_User c WHERE UPPER(c.EMail) LIKE ?)");
		//	=> Phone
		if (isValidSQLText(fieldPhone))
			list.add ("C_BPartner.C_BPartner_ID IN (SELECT C_BPartner_ID from AD_User c WHERE UPPER(c.Phone) LIKE ?)");
		//	=> Postal
		if (isValidSQLText(fieldPostal))
			list.add ("C_BPartner_ID IN (Select C_BPartner_ID FROM C_BPartner_Location bpl, "
					+ "C_Location l where l.C_Location_ID = bpl.C_Location_ID AND UPPER(Postal) LIKE ?)");

		StringBuffer sql = new StringBuffer();
		int size = list.size();
		//	Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		else if (size > 1)
		{
			boolean AND = checkAND.isSelected();
			sql.append(" AND ");
			if (!AND)
				sql.append("(");
			for (int i = 0; i < size; i++)
			{
				if (i > 0)
					sql.append(AND ? " AND " : " OR ");
				sql.append(list.get(i));
			}
			if (!AND)
				sql.append(")");
		}

		//	Static SQL
		if (checkCustomer.isSelected())
		{
			sql.append(" AND ");
			if (m_isSOTrx)
				sql.append ("C_BPartner.IsCustomer='Y'");
			else
				sql.append ("C_BPartner.IsVendor='Y'");
		}
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt pstmt
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		
		int index = 1;
		//  => ID
		if (!(fieldID == 0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record ID: " + fieldID);
		}
		//	=> Value
		if (isValidSQLText(fieldValue))
		{
			pstmt.setString(index++, getSQLText(fieldValue));
			log.fine("Value: " + fieldValue.getText());
		}
		//	=> Name
		if (isValidSQLText(fieldName))
		{
			pstmt.setString(index++, getSQLText(fieldName));
			log.fine("Name: " + fieldName.getText());
		}
		//	=> Contact
		if (isValidSQLText(fieldContact))
		{
			pstmt.setString(index++, getSQLText(fieldContact));
			log.fine("Contact: " + fieldContact.getText());
		}
		//	=> EMail
		if (isValidSQLText(fieldEMail))
		{
			pstmt.setString(index++, getSQLText(fieldEMail));
			log.fine("EMail: " + fieldEMail.getText());
		}
		//	=> Phone
		if (isValidSQLText(fieldPhone))
		{
			pstmt.setString(index++, getSQLText(fieldPhone));
			log.fine("Phone: " + fieldPhone.getText());
		}
		//	=> Postal
		if (isValidSQLText(fieldPostal))
		{
			pstmt.setString(index++, getSQLText(fieldPostal));
			log.fine("Postal: " + fieldPostal);
		}
	}   //  setParameters

	/*************************************************************************/

	/**
	 *  Save Selection Details
	 *  Get Location/Partner Info
	 */
	public void saveSelectionDetail()
	{
		int row = p_table.getSelectedRow();
		if (row == -1)
			return;

		int AD_User_ID = 0;
		int C_BPartner_Location_ID = 0;

		if (m_AD_User_ID_index != -1)
		{
			Object data = p_table.getModel().getValueAt(row, m_AD_User_ID_index);
			if (data instanceof KeyNamePair)
				AD_User_ID = ((KeyNamePair)data).getKey();
		}
		if (m_C_BPartner_Location_ID_index != -1)
		{
			Object data = p_table.getModel().getValueAt(row, m_C_BPartner_Location_ID_index);
			if (data instanceof KeyNamePair)
				C_BPartner_Location_ID = ((KeyNamePair)data).getKey();
		}
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_BPartner_ID", ID == null ? "0" : ID.toString());
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "AD_User_ID", String.valueOf(AD_User_ID));
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "C_BPartner_Location_ID", String.valueOf(C_BPartner_Location_ID));
	}   //  saveSelectionDetail


	/**************************************************************************
	 *	Show History
	 */
	protected void showHistory(int record_id)
	{
		log.info("");
		int C_BPartner_ID = record_id;
		if (C_BPartner_ID <= 0)
			return;
		InvoiceHistory ih = new InvoiceHistory (this, C_BPartner_ID, 0, 0, 0);
		ih.setVisible(true);
		ih = null;
	}	//	showHistory

	/**
	 *	Has History
	 *  @return true
	 */
	protected boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	/**
	 *	Zoom
	 */
	protected void zoom(int record_ID)
	{
		log.info( "InfoBPartner.zoom");
		Integer C_BPartner_ID = record_ID;
		if (C_BPartner_ID == null)
			return;
	//	AEnv.zoom(MBPartner.Table_ID, C_BPartner_ID.intValue(), true);	//	SO

		MQuery query = new MQuery("C_BPartner");
		query.addRestriction("C_BPartner_ID", MQuery.EQUAL, C_BPartner_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_BPartner", true);	//	SO
		zoom (AD_WindowNo, query);
	}	//	zoom

	/**
	 *	Has Zoom
	 *  @return true
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**
	 *	Customize
	 */
	protected void customize()
	{
		log.info( "InfoBPartner.customize");
	}	//	customize

	/**
	 *	Has Customize
	 *  @return false
	 */
	protected boolean hasCustomize()
	{
		return false;	//	for now
	}	//	hasCustomize

}	//	InfoBPartner
