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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.event.WTableModelEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
* Based on InfoCashLine written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug 03, 2007
*/

public class InfoCashLinePanel extends InfoPanel implements ValueChangeListener, EventListener
{
	private static final long serialVersionUID = 1L;
	
	/** list of query columns           */
	private ArrayList m_queryColumns = new ArrayList();
	
	/** Table Name              */
	private String m_tableName;
	
	/** Key Column Name         */
	private String m_keyColumn;
	
	private Textbox fName = new Textbox();
	private Textbox fAmtTo = new Textbox();
	private Textbox fAmtFrom = new Textbox();
	
	private WEditor fCashBook_ID;
	private WEditor fInvoice_ID;
	private WEditor fBankAccount_ID;
	
	private Datebox fDateFrom = new Datebox();
	private Datebox fDateTo = new Datebox();

	private Checkbox cbAbsolute = new Checkbox();

	private Label lName = new Label(Msg.translate(Env.getCtx(), "Name"));
	private Label lDateFrom = new Label(Msg.translate(Env.getCtx(), "StatementDate"));
	private Label lDateTo = new Label("-");
	private Label lAmtFrom = new Label(Msg.translate(Env.getCtx(), "Amount")); 
	private Label lAmtTo = new Label("-");

	/**  Array of Column Info    */
	private static final ColumnInfo[] s_cashLayout = {
		new ColumnInfo(" ", "cl.C_CashLine_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_CashBook_ID"),
			"(SELECT cb.Name FROM C_CashBook cb WHERE cb.C_CashBook_ID=c.C_CashBook_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"),
			"c.Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "StatementDate"),
			"c.StatementDate", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Line"),
			"cl.Line", Integer.class),
		//	new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"),
		//		"(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=cl.C_Currency_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Amount"),
			"cl.Amount",  BigDecimal.class, true, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Invoice_ID"),
			"(SELECT i.DocumentNo||'_'||" + DB.TO_CHAR("i.DateInvoiced",DisplayType.Date,Env.getAD_Language(Env.getCtx()))
				+ "||'_'||" + DB.TO_CHAR("i.GrandTotal",DisplayType.Amount,Env.getAD_Language(Env.getCtx()))
				+ " FROM C_Invoice i WHERE i.C_Invoice_ID=cl.C_Invoice_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name||' '||ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=cl.C_BankAccount_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Charge_ID"),
			"(SELECT ca.Name FROM C_Charge ca WHERE ca.C_Charge_ID=cl.C_Charge_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DiscountAmt"),
			"cl.DiscountAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "WriteOffAmt"),
			"cl.WriteOffAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Description"),
			"cl.Description", String.class)
	};

	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	
	protected InfoCashLinePanel(	int WindowNo, String value,
									boolean multiSelection, String whereClause)
	{
		super (WindowNo, "cl", "C_CashLine_ID", multiSelection, whereClause);
		log.info( "InfoCashLine");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoCashLine"));

		try
		{
			statInit();
			p_loadedOK = initInfo ();
		}
		catch (Exception e)
		{
			return;
		}

		int no = contentPanel.getRowCount();
		setStatusLine(Integer.toString(no) + " " + Msg.getMsg(Env.getCtx(), "SearchRows_EnterQuery"), false);
		setStatusDB(Integer.toString(no));
		
		if (value != null && value.length() > 0)
		{
			fName .setValue(value);
			executeQuery();
		}

		//pack();

		// Focus
		// fName.requestFocus();
	} // InfoCashLinePanel
	
	/**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be created
	 */
	
	private void statInit() throws Exception
	{
		Hbox boxName = new Hbox();
		
		fName.addEventListener(Events.ON_CHANGE, this);
		
		boxName.setWidth("100%");
		boxName.setWidths("40%, 60%");
		boxName.appendChild(lName );
		boxName.appendChild(fName);
		
		//	fOrg_ID = new VLookup("AD_Org_ID", false, false, true,
		//	MLookupFactory.create(Env.getCtx(), 3486, m_WindowNo, DisplayType.TableDir, false),
		//	DisplayType.TableDir, m_WindowNo);
		//	lOrg_ID.setLabelFor(fOrg_ID);
		//	fOrg_ID.setBackground(AdempierePLAF.getInfoBackground());
		//	5249 - C_Cash.C_CashBook_ID
		
		Hbox boxCashBook = new Hbox();
		
		fCashBook_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 5249, DisplayType.TableDir), 
				Msg.translate(Env.getCtx(), "C_CashBook_ID"), "", false, false, true);
		fCashBook_ID.addValueChangeListner(this);
		
		boxCashBook.appendChild(fCashBook_ID.getLabel());
		boxCashBook.appendChild(fCashBook_ID.getComponent());

		Hbox boxInvoice = new Hbox();
		
		// 5354 - C_CashLine.C_Invoice_ID
		fInvoice_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 5354, DisplayType.Search), 
				Msg.translate(Env.getCtx(), "C_Invoice_ID"), "", false, false, true);
		fInvoice_ID.addValueChangeListner(this);
		
		boxInvoice.appendChild(fInvoice_ID.getLabel());
		boxInvoice.appendChild(fInvoice_ID.getComponent());

		Hbox boxBankAcct = new Hbox();
		
		//	5295 - C_CashLine.C_BankAccount_ID
		fBankAccount_ID = new WSearchEditor(
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 5295, DisplayType.TableDir), 
				Msg.translate(Env.getCtx(), "C_BankAccount_ID"), "", false, false, true);
		fBankAccount_ID.addValueChangeListner(this);
		
		boxBankAcct.appendChild(fBankAccount_ID.getLabel());
		boxBankAcct.appendChild(fBankAccount_ID.getComponent());
		
		//	5296 - C_CashLine.C_Charge_ID
		//	5291 - C_CashLine.C_Cash_ID

		cbAbsolute.setLabel(Msg.translate(Env.getCtx(), "AbsoluteAmt"));
		cbAbsolute.addEventListener(Events.ON_CHECK, this);
		
		Hbox boxDateFrom = new Hbox();
		boxDateFrom.setWidth("100%");
		boxDateFrom.setWidths("40%, 60%");
		boxDateFrom.appendChild(lDateFrom);
		boxDateFrom.appendChild(fDateFrom);

		Hbox boxDateTo = new Hbox();
		boxDateTo.setWidth("100%");
		boxDateTo.setWidths("10%, 90%");
		boxDateTo.appendChild(lDateTo);
		boxDateTo.appendChild(fDateTo);

		Hbox boxAmtFrom = new Hbox();
		boxAmtFrom.setWidth("100%");
		boxAmtFrom.setWidths("40%, 60%");
		boxAmtFrom.appendChild(lAmtFrom);
		boxAmtFrom.appendChild(fAmtFrom);

		Hbox boxAmtTo = new Hbox();
		boxAmtTo.setWidth("100%");
		boxAmtTo.setWidths("10%, 90%");
		boxAmtTo.appendChild(lAmtTo);
		boxAmtTo.appendChild(fAmtTo);

		VerticalBox boxCol1 = new VerticalBox();
		boxCol1.appendChild(boxCashBook);
		boxCol1.appendChild(new Separator());
		boxCol1.appendChild(boxInvoice);
		boxCol1.appendChild(new Separator());
		boxCol1.appendChild(boxBankAcct);
		
		VerticalBox boxCol2 = new VerticalBox();
		boxCol2.appendChild(boxName);
		boxCol2.appendChild(new Separator());
		boxCol2.appendChild(boxDateFrom);
		boxCol2.appendChild(new Separator());
		boxCol2.appendChild(boxAmtFrom);
		
		VerticalBox boxCol3 = new VerticalBox();
		boxCol3.appendChild(cbAbsolute);
		boxCol3.appendChild(new Separator());
		boxCol3.appendChild(boxDateTo);
		boxCol3.appendChild(new Separator());
		boxCol3.appendChild(boxAmtTo);
		
		//	parameterPanel.add(lOrg_ID, null);
		//	parameterPanel.add(fOrg_ID, null);
		
		Hbox mainBox = new Hbox();
		mainBox.setWidth("100%");
		mainBox.setWidths("40%, 50%, 10%");
		mainBox.appendChild(boxCol1);
		mainBox.appendChild(boxCol2);
		mainBox.appendChild(boxCol3);
		
		this.setWidth("850px");
		this.setTitle("CashLine Info");
		this.setClosable(true);
		this.setBorder("normal");
		this.appendChild(mainBox);
		this.appendChild(new Separator());
		this.appendChild(contentPanel);
		this.appendChild(new Separator());
		this.appendChild(confirmPanel);
		this.appendChild(new Separator());
		this.appendChild(statusBar);
	}
	
	/**
	 *	General Init
	 *	@return true, if success
	 */
	private boolean initInfo ()
	{
		// Prepare table
		StringBuffer where = new StringBuffer("cl.IsActive='Y'");
		
		if (p_whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(p_whereClause, "C_CashLine.", "cl."));
		
		prepareTable (	s_cashLayout,	"C_CashLine cl INNER JOIN C_Cash c ON (cl.C_Cash_ID=c.C_Cash_ID)",
						where.toString(), "2,3,cl.Line");

		return true;
	} // initInfo

	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return sql where clause
	 */
	
	String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
	
		if (fName.getText().length() > 0)
			sql.append(" AND UPPER(c.Name) LIKE ?");

		if (fCashBook_ID.getDisplay() != "")
			sql.append(" AND c.C_CashBook_ID=?");
		
		if (fInvoice_ID.getDisplay() != "")
			sql.append(" AND cl.C_Invoice_ID=?");
		
		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Date f = fDateFrom.getValue();
			Timestamp from = new Timestamp(f.getTime());
			
			Date t = fDateTo.getValue();
			Timestamp to = new Timestamp(t.getTime());

			if (from == null && to != null)
				sql.append(" AND TRUNC(c.StatementDate) <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(c.StatementDate) >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(c.StatementDate) BETWEEN ? AND ?");
		}

		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = new BigDecimal(fAmtFrom.getValue());
			BigDecimal to = new BigDecimal(fAmtTo.getValue());
			
			if (cbAbsolute .isChecked())
				sql.append(" AND ABS(cl.Amount)");
			else
				sql.append(" AND cl.Amount");

			if (from == null && to != null)
				sql.append(" <=?");
			else if (from != null && to == null)
				sql.append(" >=?");
			else if (from != null && to != null)
			{
				if (from.compareTo(to) == 0)
					sql.append(" =?");
				else
					sql.append(" BETWEEN ? AND ?");
			}
		}

		log.fine(sql.toString());
		return sql.toString();
	} // getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt statement
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	
	void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;
		if (fName.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fName));

		if (fCashBook_ID.getValue() != null)
		{
			Integer cb = (Integer)fCashBook_ID.getValue();
			pstmt.setInt(index++, cb.intValue());
			log.fine("CashBook=" + cb);
		}

		if (fInvoice_ID.getValue() != null)
		{
			Integer i = (Integer)fInvoice_ID.getValue();
			pstmt.setInt(index++, i.intValue());
			log.fine("Invoice=" + i);
		}

		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Date f = fDateFrom.getValue();
			Timestamp from = new Timestamp(f.getTime());
			
			Date t = fDateTo.getValue();
			Timestamp to = new Timestamp(t.getTime());

			log.fine("Date From=" + from + ", To=" + to);
			
			if (from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if (from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}

		if (fAmtFrom.getValue() != null || fAmtTo.getValue() != null)
		{
			BigDecimal from = new BigDecimal(fAmtFrom.getValue());
			BigDecimal to = new BigDecimal(fAmtTo.getValue());

			if (cbAbsolute.isChecked())
			{
				if (from != null)
					from = from.abs();
				if (to != null)
					to = to.abs();
			}
			
			log.fine("Amt From=" + from + ", To=" + to + ", Absolute=" + cbAbsolute.isChecked());
			
			if (from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if (from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if (from != null && to != null)
			{
				if (from.compareTo(to) == 0)
					pstmt.setBigDecimal(index++, from);
				else
				{
					pstmt.setBigDecimal(index++, from);
					pstmt.setBigDecimal(index++, to);
				}
			}
		}
	} // setParameters

	/**
	 *  Get SQL WHERE parameter
	 *  @param f field
	 *  @return Upper case text with % at the end
	 */
	
	private String getSQLText (Textbox f)
	{
		String s = f.getText().toUpperCase();
		
		if (!s.endsWith("%"))
			s += "%";
		
		log.fine( "String=" + s);
		
		return s;
	} // getSQLText

	public void valueChange(ValueChangeEvent evt) 
	{
		
	}

	public void tableChanged(WTableModelEvent event) 
	{
		
	}
	
}
