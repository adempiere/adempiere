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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.WConfirmPanel;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.model.X_M_Cost;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

/**
 * Merge Entities : Based on VMerge
 *
 * @author  Niraj Sohun
 * @date    Jul 28, 2007
 */

public class WMerge extends ADForm implements EventListener, ValueChangeListener
{
	private static final long serialVersionUID = 1L;

	private static CLogger log = CLogger.getCLogger(WInvoiceGen.class);

	private Grid grdAll;
	private Rows rows;
	private Row row;

	/** Confirmation panel containing Ok and Cancel button. */
    private WConfirmPanel m_pnlConfirm;

	private WEditor[] from = new WEditor[4];
	private WEditor[] to = new WEditor[4];

	private int[] fromIDs = new int[4];
	private int[] toIDs = new int[4];

	private int[] AD_Column_ID = new int[] {2163, 2762, 971, 2221};
	private String[] text = new String[] {"AD_Org_ID", "C_BPartner_ID", "AD_User_ID", "M_Product_ID"};

	private int m_totalCount;

	private StringBuffer m_errorLog;

	private Trx m_trx;

	static private String	AD_ORG_ID = "AD_Org_ID";
	static private String	C_BPARTNER_ID = "C_BPartner_ID";
	static private String	AD_USER_ID = "AD_User_ID";
	static private String	M_PRODUCT_ID = "M_Product_ID";

	/** Tables to delete (not update) for AD_Org */
	static private String[]	s_delete_Org = new String[]	{"AD_OrgInfo"};

	/** Tables to delete (not update) for AD_User */
	static private String[]	s_delete_User = new String[] {"AD_User_Roles"};

	/** Tables to delete (not update) for C_BPartner	*/
	static private String[]	s_delete_BPartner = new String[] {"C_BP_Employee_Acct",
													"C_BP_Vendor_Acct", "C_BP_Customer_Acct", "T_Aging"};

	/** Tables to delete (not update) for M_Product	*/
	static private String[]	s_delete_Product = new String[]	{"M_Product_PO", "M_Replenish", "T_Replenish",
															"M_ProductPrice", "M_Product_Costing",
															"M_Cost", // teo_sarca [ 1704554 ]
															"M_Product_Trl", "M_Product_Acct"};	// M_Storage

	private String[] m_columnName = new String[]{"AD_Org_ID", "C_BPartner_ID", "AD_User_ID", "M_Product_ID"};
	private String[] m_deleteTables = null;

	public WMerge()
	{
		init();
		initComponents();
	}

	public void init()
	{
		grdAll = new Grid();
		grdAll.setWidth("700px");

		/*btnCancel = new Button();
		btnCancel.setImage("/images/Cancel24.gif");
		btnCancel.addEventListener(Events.ON_CLICK, this);

		btnOk = new Button();
		btnOk.setImage("/images/Ok24.gif");
		btnOk.addEventListener(Events.ON_CLICK, this);*/

        m_pnlConfirm = new WConfirmPanel(true);
        m_pnlConfirm.addEventListener(this);

	}

	public void initComponents()
	{
		this.setWidth("710px");
		this.setBorder("normal");

		components();

		rows = new Rows();

		// Row 1
		row = new Row();
		row.appendChild(new Label(""));
		row.appendChild(new Label("Merge From (Deleted)"));
		row.appendChild(new Label("Merge To (Surviving)"));
		rows.appendChild(row);

		for (int i = 0; i < 4; i++)
		{
			row = new Row();
			row.appendChild(from[i].getLabel());
			row.appendChild(from[i].getComponent());
			row.appendChild(to[i].getComponent());
			rows.appendChild(row);
		}

		grdAll.appendChild(rows);
		this.appendChild(grdAll);

		// Row 6
		this.appendChild(m_pnlConfirm);
	}

	private void components()
	{
		MLookup lookup = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
				0, AD_Column_ID[0], DisplayType.TableDir);

		from[0] = new WTableDirEditor(lookup, Msg.translate(
				Env.getCtx(), text[0]), "from", true, false, true);

		from[0].addValueChangeListner(this);

		to[0] = new WTableDirEditor(lookup, Msg.translate(
				Env.getCtx(), text[0]), "to", true, false, true);

		to[0].addValueChangeListner(this);


		// Search Editors

		for (int i = 1; i < AD_Column_ID.length; i++)
		{
			lookup = MLookupFactory.get(Env.getCtx(), super.m_windowNo,
					0, AD_Column_ID[i], DisplayType.Search);

			from[i] = new WSearchEditor(lookup, Msg.translate(
					Env.getCtx(), text[i]), "from", true, false, true);

			from[i].addValueChangeListner(this);

			to[i] = new WSearchEditor(lookup, Msg.translate(
					Env.getCtx(), text[i]), "to", true, false, true);

			to[i].addValueChangeListner(this);
		}
	}

	public void valueChange(ValueChangeEvent evt)
	{
		if (evt == null)
		{
			return;
		}

		WEditor edit = (WEditor)evt.getSource();
		String des = edit.getDescription();

		String name = evt.getPropertyName();
		Object value = evt.getNewValue();


		if (name.equals("AD_Org_ID"))
		{
			if (des == "from")
			{
				from[0].setValue(value);
				fromIDs[0] = ((Integer)value).intValue();
			}
			else
			{
				to[0].setValue(value);
				toIDs[0] = ((Integer)value).intValue();
			}
		}
		else if (name.equals("C_BPartner_ID"))
		{
			if (des == "from")
			{
				from[1].setValue(value);
				fromIDs[1] = ((Integer)value).intValue();
			}
			else
			{
				to[1].setValue(value);
				toIDs[1] = ((Integer)value).intValue();
			}
		}
		else if (name.equals("M_Product_ID"))
		{
			if (des == "from")
			{
				from[3].setValue(value);
				fromIDs[3] = ((Integer)value).intValue();
			}
			else
			{
				to[3].setValue(value);
				toIDs[3] = ((Integer)value).intValue();
			}
		}
		else if (name.equals("AD_User_ID"))
		{
			if (des == "from")
			{
				from[2].setValue(value);
				fromIDs[2] = ((Integer)value).intValue();
			}
			else
			{
				to[2].setValue(value);
				toIDs[2] = ((Integer)value).intValue();
			}
		}
	}

	private boolean merge (String ColumnName, int from_ID, int to_ID)
	{
		String TableName = ColumnName.substring(0, ColumnName.length()-3);

		//log.config(ColumnName + " - From=" + from_ID + ",To=" + to_ID);

		boolean success = true;
		m_totalCount = 0;
		m_errorLog = new StringBuffer();

		String sql = "SELECT t.TableName, c.ColumnName "
			+ "FROM AD_Table t"
			+ " INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID) "
			+ "WHERE t.IsView='N'"
				+ " AND t.TableName NOT IN ('C_TaxDeclarationAcct')"
				+ " AND ("
				+ "(c.ColumnName=? AND c.IsKey='N')"		//	#1 - direct
			+ " OR "
				+ "c.AD_Reference_Value_ID IN "				//	Table Reference
					+ "(SELECT rt.AD_Reference_ID FROM AD_Ref_Table rt"
					+ " INNER JOIN AD_Column cc ON (rt.AD_Table_ID=cc.AD_Table_ID AND rt.AD_Key=cc.AD_Column_ID) "
					+ "WHERE cc.IsKey='Y' AND cc.ColumnName=?)"	//	#2
			+ ") "
			+ "ORDER BY t.LoadSeq DESC";

		PreparedStatement pstmt = null;

		try
		{

			m_trx = Trx.get(Trx.createTrxName("merge"), true);

			pstmt = DB.prepareStatement(sql, Trx.createTrxName());
			pstmt.setString(1, ColumnName);
			pstmt.setString(2, ColumnName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				String tName = rs.getString(1);
				String cName = rs.getString(2);

				if (!TableName.equals(tName))	//	to be sure - sql should prevent it
				{
					int count = mergeTable (tName, cName, from_ID, to_ID);

					if (count < 0)
						success = false;
					else
						m_totalCount += count;
				}
			}
			rs.close();
			pstmt.close();
			pstmt = null;

			//log.config("Success=" + success	+ " - " + ColumnName + " - From=" + from_ID + ",To=" + to_ID);

			if (success)
			{
				sql = "DELETE " + TableName + " WHERE " + ColumnName + "=" + from_ID;

				if ( DB.executeUpdate(sql, m_trx.getTrxName()) < 0 )
				{
					m_errorLog.append(Env.NL).append("DELETE ").append(TableName).append(" - ");
				    success = false;
					//log.config(m_errorLog.toString());
					m_trx.rollback();
					return false;
				}

			}

			if (success)
				m_trx.commit();
			else
				m_trx.rollback();

			m_trx.close();

		}
		catch (Exception ex)
		{
			// log.log(Level.SEVERE, ColumnName, ex);
		}

		//	Cleanup
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (Exception ex)
		{
		}

		pstmt = null;
		return success;
	}

	/**
	 * 	Merge Table
	 * 	@param TableName table
	 * 	@param ColumnName column
	 * 	@param from_ID from
	 * 	@param to_ID to
	 * 	@return -1 for error or number of changes
	 */

	private int mergeTable (String TableName, String ColumnName, int from_ID, int to_ID)
	{
		// log.fine(TableName + "." + ColumnName + " - From=" + from_ID + ",To=" + to_ID);

		String sql = "UPDATE " + TableName
			+ " SET " + ColumnName + "=" + to_ID
			+ " WHERE " + ColumnName + "=" + from_ID;

		boolean delete = false;

		for (int i = 0; i < m_deleteTables.length; i++)
		{
			if (m_deleteTables[i].equals(TableName))
			{
				delete = true;
				sql = "DELETE " + TableName + " WHERE " + ColumnName + "=" + from_ID;
			}
		}

		// Delete newly created MCost records - teo_sarca [ 1704554 ]
		if (delete && X_M_Cost.Table_Name.equals(TableName) && M_PRODUCT_ID.equals(ColumnName))
		{
			sql += " AND " + X_M_Cost.COLUMNNAME_CurrentCostPrice + "=0"
				+ " AND " + X_M_Cost.COLUMNNAME_CurrentQty + "=0"
				+ " AND " + X_M_Cost.COLUMNNAME_CumulatedAmt + "=0"
				+ " AND " + X_M_Cost.COLUMNNAME_CumulatedQty + "=0";
		}

		int count = DB.executeUpdate(sql, m_trx.getTrxName());


		if (  count < 0 )
		{
			count = -1;
			m_errorLog.append(Env.NL).append(delete ? "DELETE " : "UPDATE ").append(TableName).append(" - ").append(" - ").append(sql);
			//log.config(m_errorLog.toString());
			m_trx.rollback();

		}
		//log.fine(count + (delete ? " -Delete- " : " -Update- ") + TableName);

		return count;
	}

	/**
	 * 	Post Merge
	 *	@param ColumnName column name
	 *	@param to_ID ID
	 */

	private void postMerge (String ColumnName, int to_ID)
	{
		if (ColumnName.equals(AD_ORG_ID))
		{

		}
		else if (ColumnName.equals(AD_USER_ID))
		{

		}
		else if (ColumnName.equals(C_BPARTNER_ID))
		{
			MBPartner bp = new MBPartner (Env.getCtx(), to_ID, null);
			if (bp.get_ID() != 0)
			{
				MPayment[] payments = MPayment.getOfBPartner(Env.getCtx(), bp.getC_BPartner_ID(), null);
				for (int i = 0; i < payments.length; i++)
				{
					MPayment payment = payments[i];
					if (payment.testAllocation())
						payment.save();
				}
				MInvoice[] invoices = MInvoice.getOfBPartner(Env.getCtx(), bp.getC_BPartner_ID(), null);
				for (int i = 0; i < invoices.length; i++)
				{
					MInvoice invoice = invoices[i];
					if (invoice.testAllocation())
						invoice.save();
				}
				bp.setTotalOpenBalance();
				bp.setActualLifeTimeValue();
				bp.save();
			}
		}
		else if (ColumnName.equals(M_PRODUCT_ID))
		{

		}
	}

	private void process()
	{
		String columnName = null;
		String from_Info = null;
		String to_Info = null;
		int from_ID = 0;
		int to_ID = 0;

		for (int i = 0; (i < m_columnName.length && from_ID == 0 && to_ID == 0); i++)
		{
			Object value;

			value = from[i].getValue();

			if (value != null)
			{
				if (value instanceof Integer)
					from_ID = ((Integer)value).intValue();
				else
					continue;

				value = to[i].getValue();

				if (value != null && value instanceof Integer)
					to_ID = ((Integer)value).intValue();
				else
					from_ID = 0;

				if (from_ID != 0)
				{
					columnName = m_columnName[i];

					from_Info = from[i].getDisplay ();
					to_Info = to[i].getDisplay ();
				}
			}
		}	//	get first merge pair

		if (from_ID == 0 || from_ID == to_ID)
			return;

		String msg = Msg.getMsg(Env.getCtx(), "MergeFrom") + " = " + from_Info
			+ "\n" + Msg.getMsg(Env.getCtx(), "MergeTo") + " = " + to_Info;

		//if (!FDialog.ask(super.m_windowNo, this, msg))
		//	return;

		//	** Update **

		if (columnName.equals(AD_ORG_ID))
			m_deleteTables = s_delete_Org;
		else if (columnName.equals(AD_USER_ID))
			m_deleteTables = s_delete_User;
		else if (columnName.equals(C_BPARTNER_ID))
			m_deleteTables = s_delete_BPartner;
		else if (columnName.equals(M_PRODUCT_ID))
			m_deleteTables = s_delete_Product;

		//setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//confirmPanel.getOKButton().setEnabled(false);

		boolean success = merge (columnName, from_ID, to_ID);
		postMerge(columnName, to_ID);

		//confirmPanel.getOKButton().setEnabled(true);
		//setCursor(Cursor.getDefaultCursor());
		//

		if (success)
		{
			FDialog.info (super.m_windowNo, this, msg);
		}
		else
		{
			FDialog.error(super.m_windowNo, this, "MergeError", m_errorLog.toString());
			return;
		}
	}

    /**
     * React to Ok and Cancel buttons being triggered.
     *
     * @param event the event to which to respond
     * @throws Exception if an exception occurred
     */
	public void onEvent(Event event) throws Exception
	{
		if (event != null)
		{
            if (event.getName().equals(WConfirmPanel.A_CANCEL))
			{
				SessionManager.getAppDesktop().removeWindow();
				return;
			}
            else if (event.getName().equals(WConfirmPanel.A_OK))
			{
				process();
			}
		}
	}
}
