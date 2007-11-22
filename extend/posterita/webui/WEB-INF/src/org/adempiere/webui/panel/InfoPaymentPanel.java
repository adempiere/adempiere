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
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

/**
* Based on InfoPayment written by Jorg Janke
*  
* @author 	Niraj Sohun
* 			Aug, 02, 2007
*/

public class InfoPaymentPanel extends InfoPanel implements ValueChangeListener, EventListener
{
	private static final long serialVersionUID = 1L;
	
	/**  String Array of Column Info    */
	private ColumnInfo[] m_generalLayout;
	
	/** list of query columns           */
	private ArrayList 	m_queryColumns = new ArrayList();
	
	/** Table Name              */
	private String m_tableName;
	
	/** Key Column Name         */
	private String m_keyColumn;

	//private WListbox p_table = new WListbox();

	private Textbox fDocumentNo = new Textbox();
	private Textbox fAmtTo = new Textbox();
	private Textbox fAmtFrom = new Textbox();

	private WEditor fBPartner_ID;

	private Datebox fDateTo = new Datebox();
	private Datebox fDateFrom = new Datebox();

	private Checkbox fIsReceipt = new Checkbox();
	
	private Label lDocumentNo = new Label(Msg.translate(Env.getCtx(), "DocumentNo"));
	private Label lDateFrom = new Label(Msg.translate(Env.getCtx(), "DateTrx"));
	private Label lDateTo = new Label("-");
	private Label lAmtFrom = new Label(Msg.translate(Env.getCtx(), "PayAmt"));
	private Label lAmtTo = new Label("-");

	/**  Array of Column Info    */
	private static final ColumnInfo[] s_paymentLayout = {
		new ColumnInfo(" ", "p.C_Payment_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BankAccount_ID"),
			"(SELECT b.Name || ' ' || ba.AccountNo FROM C_Bank b, C_BankAccount ba WHERE b.C_Bank_ID=ba.C_Bank_ID AND ba.C_BankAccount_ID=p.C_BankAccount_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_BPartner_ID"),
			"(SELECT Name FROM C_BPartner bp WHERE bp.C_BPartner_ID=p.C_BPartner_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DateTrx"),
			"p.DateTrx", Timestamp.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNo"),
			"p.DocumentNo", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsReceipt"),
			"p.IsReceipt", Boolean.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Currency_ID"),
			"(SELECT ISO_Code FROM C_Currency c WHERE c.C_Currency_ID=p.C_Currency_ID)", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "PayAmt"),
			"p.PayAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "ConvertedAmount"),
			"currencyBase(p.PayAmt,p.C_Currency_ID,p.DateTrx, p.AD_Client_ID,p.AD_Org_ID)", BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "DiscountAmt"),
			"p.DiscountAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "WriteOffAmt"),
			"p.WriteOffAmt",  BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "IsAllocated"),
			"p.IsAllocated",  Boolean.class)
	};
	
	/**
	 *  Detail Protected Constructor
	 *  
	 *  @param modal modal
	 *  @param WindowNo window no
	 *  @param value query value
	 *  @param multiSelection multiple selections
	 *  @param whereClause where clause
	 */
	
	protected InfoPaymentPanel(int WindowNo, String value,
			boolean multiSelection, String whereClause)
	{
		super(WindowNo, "p", "C_Payment_ID", multiSelection, whereClause);
		
		log.info( "InfoPaymentPanel");
		setTitle(Msg.getMsg(Env.getCtx(), "InfoPayment"));

		try
		{
			statInit();
			p_loadedOK = initInfo();
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
			fDocumentNo .setValue(value);
			executeQuery();
		}

		//pack();
		
		//	Focus
		//fDocumentNo.requestFocus();
	} // InfoPaymentPanel

	/**
	 *	Static Setup - add fields to parameterPanel
	 *  @throws Exception if Lookups cannot be created
	 */
	
	private void statInit() throws Exception
	{
		Hbox boxDocumentNo = new Hbox();
		
		fDocumentNo.addEventListener(Events.ON_CHANGE, this);
		
		boxDocumentNo.setWidth("100%");
		boxDocumentNo.setWidths("40%, 60%");
		boxDocumentNo.appendChild(lDocumentNo);
		boxDocumentNo.appendChild(fDocumentNo);
		
		fIsReceipt.setLabel(Msg.translate(Env.getCtx(), "IsReceipt"));
		fIsReceipt.addEventListener(Events.ON_CHECK, this);
		fIsReceipt.setChecked(!"N".equals(Env.getContext(Env.getCtx(), p_WindowNo, "IsSOTrx")));
		
		Hbox boxBPartner = new Hbox();
		
		fBPartner_ID = new WSearchEditor(	
				MLookupFactory.get(Env.getCtx(), p_WindowNo, 0, 3499, DisplayType.Search), 
				Msg.translate(Env.getCtx(), "C_BPartner_ID"), "", false, false, true);
		fBPartner_ID.addValueChangeListner(this);
		
		boxBPartner.appendChild(fBPartner_ID.getLabel());
		boxBPartner.appendChild(fBPartner_ID.getComponent());
		
		Hbox boxDateFrom = new Hbox();
		
		//fDateFrom.setValue(new Date(System.currentTimeMillis()));
		
		boxDateFrom.setWidth("100%");
		boxDateFrom.setWidths("40%, 60%");
		boxDateFrom.appendChild(lDateFrom);
		boxDateFrom.appendChild(fDateFrom);
		
		Hbox boxDateTo = new Hbox();
		
		//fDateTo.setValue(new Date(System.currentTimeMillis()));
		
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
		//boxCol1.setWidth("100%");
		boxCol1.appendChild(boxDocumentNo);
		
		VerticalBox boxCol2 = new VerticalBox();
		//boxCol2.setWidth("100%");
		boxCol2.appendChild(boxBPartner);
		boxCol2.appendChild(new Separator());
		boxCol2.appendChild(boxDateFrom);
		boxCol2.appendChild(new Separator());
		boxCol2.appendChild(boxAmtFrom);
		
		VerticalBox boxCol3 = new VerticalBox();
		//boxCol3.setWidth("100%");
		boxCol3.appendChild(fIsReceipt);
		boxCol3.appendChild(new Separator());
		boxCol3.appendChild(boxDateTo);
		boxCol3.appendChild(new Separator());
		boxCol3.appendChild(boxAmtTo);
	
		Hbox mainBox = new Hbox();
		mainBox.setWidth("100%");
		mainBox.setWidths("42%, 50%, 8%");
		mainBox.appendChild(boxCol1);
		mainBox.appendChild(boxCol2);
		mainBox.appendChild(boxCol3);
		
		this.setWidth("850px");
		this.setTitle("Payment Info");
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
		//  Set Defaults
		String bp = Env.getContext(Env.getCtx(), p_WindowNo, "C_BPartner_ID");
		
		if (bp != null && bp.length() != 0)
			fBPartner_ID.setValue(new Integer(bp));

		// Prepare table
		StringBuffer where = new StringBuffer("p.IsActive='Y'");
		
		if (p_whereClause.length() > 0)
			where.append(" AND ").append(Util.replace(p_whereClause, "C_Payment.", "p."));
		
		prepareTable(s_paymentLayout, " C_Payment_v p", where.toString(), "2,3,4");
		
		//	MPayment.setIsAllocated(Env.getCtx(), 0, null);
		
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
		
		if (fDocumentNo.getText().length() > 0)
			sql.append(" AND UPPER(p.DocumentNo) LIKE ?");

		if (fBPartner_ID.getDisplay() != "")
			sql.append(" AND p.C_BPartner_ID=?");

		if (fDateFrom.getValue() != null || fDateTo.getValue() != null)
		{
			Date f = fDateFrom.getValue();
			Timestamp from = new Timestamp(f.getTime());
			
			Date t = fDateTo.getValue();
			Timestamp to = new Timestamp(t.getTime());

			if (from == null && to != null)
				sql.append(" AND TRUNC(p.DateTrx) <= ?");
			else if (from != null && to == null)
				sql.append(" AND TRUNC(p.DateTrx) >= ?");
			else if (from != null && to != null)
				sql.append(" AND TRUNC(p.DateTrx) BETWEEN ? AND ?");
		}

		if (fAmtFrom.getText() != "" || fAmtTo.getText() != "")
		{
			BigDecimal from = new BigDecimal(fAmtFrom.getValue());
			BigDecimal to = new BigDecimal(fAmtTo.getValue());
			
			if (from == null && to != null)
				sql.append(" AND p.PayAmt <= ?");
			else if (from != null && to == null)
				sql.append(" AND p.PayAmt >= ?");
			else if (from != null && to != null)
				sql.append(" AND p.PayAmt BETWEEN ? AND ?");
		}
		
		sql.append(" AND p.IsReceipt=?");

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
	
		if (fDocumentNo.getText().length() > 0)
			pstmt.setString(index++, getSQLText(fDocumentNo));

		if (fBPartner_ID.getDisplay() != "")
		{
			Integer bp = (Integer)fBPartner_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("BPartner=" + bp);
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

		if (fAmtFrom.getText() != "" || fAmtTo.getText() != "")
		{
			BigDecimal from = new BigDecimal(fAmtFrom.getValue());
			BigDecimal to = new BigDecimal(fAmtTo.getValue());
			log.fine("Amt From=" + from + ", To=" + to);
			
			if (from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if (from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if (from != null && to != null)
			{
				pstmt.setBigDecimal(index++, from);
				pstmt.setBigDecimal(index++, to);
			}
		}
		
		pstmt.setString(index++, fIsReceipt.isChecked() ? "Y" : "N");
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

	/**
	 *	Zoom
	 */
	
/*	void zoom()
	{
		log.info( "InfoPayment.zoom");
		Integer C_Payment_ID = getSelectedRowKey();
		if (C_Payment_ID == null)
			return;
		MQuery query = new MQuery("C_Payment");
		query.addRestriction("C_Payment_ID", MQuery.EQUAL, C_Payment_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("C_Payment", fIsReceipt.isSelected());
		zoom (AD_WindowNo, query);
	}	//	zoom
*/
	/**
	 *	Has Zoom
	 *  @return true
	 */
	
	boolean hasZoom()
	{
		return true;
	} // hasZoom
	
	public void valueChange(ValueChangeEvent evt)
	{
		if (fBPartner_ID.equals(evt.getSource()))
		{
	    	fBPartner_ID.setValue(evt.getNewValue());
		}
	}

	public void tableChanged(WTableModelEvent event) {
		// TODO Auto-generated method stub
		
	}

}
