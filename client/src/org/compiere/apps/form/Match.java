/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.compiere.apps.form;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MClient;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MRole;
import org.compiere.model.MStorage;
import org.compiere.model.MSysConfig;
import org.compiere.process.DocumentEngine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 *  @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 *			<li>Implement Reverse Accrual for all document https://github.com/adempiere/adempiere/issues/1348</>
 *
 *  @author Systemhaus Westfalia SusanneCalderon <susanne.de.calderon@westfalia-it.com>
 *  <li>Implement Reverse Accrual, ommit createCostDetail and implement reverseIt for match_Inv of cancelled documents
 *  https://github.com/adempiere/adempiere/issues/1918
 */
public class Match
{

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(Match.class);

	/** Match Options           */
	private String[] m_matchOptions = new String[] {
		Msg.getElement(Env.getCtx(), "C_Invoice_ID", false),
		Msg.getElement(Env.getCtx(), "M_InOut_ID", false),
		Msg.getElement(Env.getCtx(), "C_Order_ID", false) };
	private static final int		MATCH_INVOICE = 0;
	private static final int		MATCH_SHIPMENT = 1;
	private static final int		MATCH_ORDER = 2;

	private static final int		MODE_NOTMATCHED = 0;
	private static final int		MODE_MATCHED = 1;

	/**	Indexes in Table			*/
	private static final int		I_BPartner = 3;
	private static final int		I_Line = 4;
	private static final int		I_Product = 5;
	private static final int		I_QTY = 6;
	private static final int		I_MATCHED = 7;
	private static final int        I_Org = 8; //JAVIER 
	


	private StringBuffer    m_sql = null;
	private String          m_dateColumn = "";
	private String          m_qtyColumn = "";
	private String          m_groupBy = "";
	private StringBuffer	m_linetype = null;
	//private BigDecimal      m_xMatched = Env.ZERO;
	//private BigDecimal      m_xMatchedTo = Env.ZERO;

	
	/**
	 *  Match From Changed - Fill Match To
	 */
	protected Vector<String> cmd_matchFrom(String selection)
	{
	//	log.fine( "VMatch.cmd_matchFrom");
		//String selection = (String)matchFrom.getSelectedItem();
		Vector<String> vector = new Vector<String>(2);
		if (selection.equals(m_matchOptions[MATCH_INVOICE]))
			vector.add(m_matchOptions[MATCH_SHIPMENT]);
		else if (selection.equals(m_matchOptions[MATCH_ORDER]))
			vector.add(m_matchOptions[MATCH_SHIPMENT]);
		else    //  shipment
		{
			vector.add(m_matchOptions[MATCH_INVOICE]);
			vector.add(m_matchOptions[MATCH_ORDER]);
		}
		return vector;
	}   //  cmd_matchFrom

	
	/**
	 *  Search Button Pressed - Fill xMatched
	 */
	protected IMiniTable cmd_search(IMiniTable xMatchedTable, int display, String matchToString, Integer Product, Integer Vendor, Timestamp from, Timestamp to, boolean matched)
	{
		//  ** Create SQL **
		//int display = matchFrom.getSelectedIndex();
		//String matchToString = (String)matchTo.getSelectedItem();
		int matchToType = MATCH_INVOICE;
		if (matchToString.equals(m_matchOptions[MATCH_SHIPMENT]))
			matchToType = MATCH_SHIPMENT;
		else if (matchToString.equals(m_matchOptions[MATCH_ORDER]))
			matchToType = MATCH_ORDER;
		//
		tableInit(display, matchToType, matched);	//	sets m_sql

		//  ** Add Where Clause **
		//  Product
		if (Product != null)
		{
			//Integer Product = (Integer)onlyProduct.getValue();
			m_sql.append(" AND lin.M_Product_ID=").append(Product);
		}
		//  BPartner
		if (Vendor != null)
		{
			//Integer Vendor = (Integer)onlyVendor.getValue();
			m_sql.append(" AND hdr.C_BPartner_ID=").append(Vendor);
		}
		//  Date
		//Timestamp from = (Timestamp)dateFrom.getValue();
		//Timestamp to = (Timestamp)dateTo.getValue();
		if (from != null && to != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" BETWEEN ")
				.append(DB.TO_DATE(from)).append(" AND ").append(DB.TO_DATE(to));
		else if (from != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" >= ").append(DB.TO_DATE(from));
		else if (to != null)
			m_sql.append(" AND ").append(m_dateColumn).append(" <= ").append(DB.TO_DATE(to));
		
		//  ** Load Table **
		tableLoad (xMatchedTable);
		return xMatchedTable;

	}   //  cmd_search


	/**
	 *  Process Button Pressed - Process Matching
	 */
	protected void cmd_process(IMiniTable xMatchedTable, IMiniTable xMatchedToTable, int matchMode, int matchFrom, Object matchTo, BigDecimal m_xMatched)
	{
		log.config("");
		//  Matched From
		int matchedRow = xMatchedTable.getSelectedRow();
		if (matchedRow < 0)
			return;
	//	KeyNamePair BPartner = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_BPartner);
		KeyNamePair lineMatched = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_Line);
		KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(matchedRow, I_Product);

		Optional<BigDecimal> totalQty = Optional.ofNullable(m_xMatched);

		//  Matched To
		for (int row = 0; row < xMatchedToTable.getRowCount(); row++)
		{
			IDColumn id = (IDColumn)xMatchedToTable.getValueAt(row, 0);
			if (id != null && id.isSelected())
			{
				//  need to be the same product
				KeyNamePair ProductCompare = (KeyNamePair)xMatchedToTable.getValueAt(row, I_Product);
				if (Product.getKey() != ProductCompare.getKey())
					continue;

				KeyNamePair lineMatchedTo = (KeyNamePair)xMatchedToTable.getValueAt(row, I_Line);

				//	Qty
				Optional<BigDecimal> qty = Optional.empty();
				Optional<BigDecimal> docQty = Optional.ofNullable((BigDecimal)xMatchedToTable.getValueAt(row, I_QTY));
				Optional<BigDecimal> matchedQty = Optional.ofNullable((BigDecimal)xMatchedToTable.getValueAt(row, I_MATCHED));
				
				if (matchMode == MODE_NOTMATCHED)
					qty = docQty;	//  doc
				
				qty = Optional.ofNullable(qty.orElse(Env.ZERO).subtract(matchedQty.orElse(Env.ZERO)));

				if (qty.isPresent()
						&& qty.get().compareTo(totalQty.orElse(Env.ZERO)) > 0)
					qty = totalQty;
				if (totalQty.isPresent())
					totalQty = Optional.ofNullable(totalQty.get().subtract(qty.orElse(Env.ZERO)));

				//  Invoice or PO
				boolean invoice = true;
				if (matchFrom == MATCH_ORDER ||
						matchTo.equals(m_matchOptions[MATCH_ORDER]))
					invoice = false;
				//  Get Shipment_ID
				int M_InOutLine_ID = 0;
				int Line_ID = 0;
				if (matchFrom == MATCH_SHIPMENT)
				{
					M_InOutLine_ID = lineMatched.getKey();      //  upper table
					Line_ID = lineMatchedTo.getKey();
				}
				else
				{
					M_InOutLine_ID = lineMatchedTo.getKey();    //  lower table
					Line_ID = lineMatched.getKey();
				}

				//  Create it
				String innerTrxName = Trx.createTrxName("Match");
				Trx innerTrx = Trx.get(innerTrxName, true);
				if (createMatchRecord(invoice, M_InOutLine_ID, Line_ID, qty.orElse(Env.ZERO), innerTrxName))
					innerTrx.commit();
				else
					innerTrx.rollback();
				innerTrx.close();
				innerTrx = null;
			}
		}
		//  requery
		//cmd_search();
	}   //  cmd_process
	

	/**
	 *  Fill xMatchedTo
	 */
	protected IMiniTable cmd_searchTo(IMiniTable xMatchedTable, IMiniTable xMatchedToTable, String displayString, int matchToType, boolean sameBPartner, boolean sameProduct, boolean sameQty, boolean matched)
	{
		int row = xMatchedTable.getSelectedRow();
		log.config("Row=" + row);

		//  ** Create SQL **
		//String displayString = (String)matchTo.getSelectedItem();
		int display = MATCH_INVOICE;
		if (displayString.equals(m_matchOptions[MATCH_SHIPMENT]))
			display = MATCH_SHIPMENT;
		else if (displayString.equals(m_matchOptions[MATCH_ORDER]))
			display = MATCH_ORDER;
		//int matchToType = matchFrom.getSelectedIndex();
		tableInit (display, matchToType, matched);	//	sets m_sql
		//  ** Add Where Clause **
		KeyNamePair BPartner = (KeyNamePair)xMatchedTable.getValueAt(row, I_BPartner);
		//KeyNamePair Org = (KeyNamePair)xMatchedTable.getValueAt(row, I_Org); //JAVIER
		KeyNamePair Product = (KeyNamePair)xMatchedTable.getValueAt(row, I_Product);
		log.fine("BPartner=" + BPartner + " - Product=" + Product);
		//
		if (sameBPartner)
			m_sql.append(" AND hdr.C_BPartner_ID=").append(BPartner.getKey());
		if (sameProduct)
			m_sql.append(" AND lin.M_Product_ID=").append(Product.getKey());

		//  calculate qty
		BigDecimal docQty = (BigDecimal)xMatchedTable.getValueAt(row, I_QTY);
		if (sameQty)
			m_sql.append(" AND ").append(m_qtyColumn).append("=").append(docQty);
		//  ** Load Table **
		tableLoad (xMatchedToTable);

		return xMatchedToTable;
	}   //  cmd_seachTo
	
	/**************************************************************************
	 *  Initialize Table access - create SQL, dateColumn.
	 *  <br>
	 *  The driving table is "hdr", e.g. for hdr.C_BPartner_ID=..
	 *  The line table is "lin", e.g. for lin.M_Product_ID=..
	 *  You use the dateColumn/qtyColumn variable directly as it is table specific.
	 *  <br>
	 *  The sql is dependent on MatchMode:
	 *  - If Matched - all (fully or partially) matched records are listed
	 *  - If Not Matched - all not fully matched records are listed
	 *  @param display (Invoice, Shipment, Order) see MATCH_*
	 *  @param matchToType (Invoice, Shipment, Order) see MATCH_*
	 */
	protected void tableInit (int display, int matchToType, boolean matched)
	{
		//boolean matched = matchMode.getSelectedIndex() == MODE_MATCHED;
		log.config("Display=" + m_matchOptions[display]
			+ ", MatchTo=" + m_matchOptions[matchToType]
			+ ", Matched=" + matched);

		m_sql = new StringBuffer ();
		if (display == MATCH_INVOICE)
		{
			m_dateColumn = "hdr.DateInvoiced";
			m_qtyColumn = "lin.QtyInvoiced";
			m_sql.append("SELECT hdr.C_Invoice_ID,hdr.DocumentNo, hdr.DateInvoiced, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_InvoiceLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.QtyInvoiced,SUM(NVL(mi.Qty,0)), org.Name, hdr.AD_Org_ID "  //JAVIER
				+ "FROM C_Invoice hdr"
				+ " INNER JOIN AD_Org org ON (hdr.AD_Org_ID=org.AD_Org_ID)" //JAVIER
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_InvoiceLine lin ON (hdr.C_Invoice_ID=lin.C_Invoice_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType IN ('API','APC'))"
				+ " FULL JOIN M_MatchInv mi ON (lin.C_InvoiceLine_ID=mi.C_InvoiceLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL')");
			m_groupBy = " GROUP BY hdr.C_Invoice_ID,hdr.DocumentNo,hdr.DateInvoiced,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_InvoiceLine_ID,p.Name,lin.M_Product_ID,lin.QtyInvoiced, org.Name, hdr.AD_Org_ID " //JAVIER
				+ "HAVING "
				+ (matched ? "0" : "lin.QtyInvoiced")
				+ "<>SUM(NVL(mi.Qty,0))";
		}
		else if (display == MATCH_ORDER)
		{
			m_dateColumn = "hdr.DateOrdered";
			m_qtyColumn = "lin.QtyOrdered";
			m_sql.append("SELECT hdr.C_Order_ID,hdr.DocumentNo, hdr.DateOrdered, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_OrderLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.QtyOrdered,SUM(COALESCE(mo.Qty,0)), org.Name, hdr.AD_Org_ID " //JAVIER
				+ "FROM C_Order hdr"
				+ " INNER JOIN AD_Org org ON (hdr.AD_Org_ID=org.AD_Org_ID)" //JAVIER
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN C_OrderLine lin ON (hdr.C_Order_ID=lin.C_Order_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID=dt.C_DocType_ID AND dt.DocBaseType='POO')"
				+ " FULL JOIN M_MatchPO mo ON (lin.C_OrderLine_ID=mo.C_OrderLine_ID) "
				+ " WHERE " ) ; //[ 1876972 ] Can't match partially matched PO with an unmatched receipt SOLVED BY BOJANA, AGENDA_GM
			m_linetype = new StringBuffer();
			m_linetype.append( matchToType == MATCH_SHIPMENT ? "M_InOutLine_ID" : "C_InvoiceLine_ID") ;
			if ( matched ) {
				m_sql.append( " mo." + m_linetype + " IS NOT NULL " ) ; 
			} else {
 				m_sql.append( " ( mo." + m_linetype + " IS NULL OR "
				+ " (lin.QtyOrdered <>  (SELECT sum(mo1.Qty) AS Qty" 
				+ " FROM m_matchpo mo1 WHERE "
				+ " mo1.C_ORDERLINE_ID=lin.C_ORDERLINE_ID AND "
				+ " hdr.C_ORDER_ID=lin.C_ORDER_ID AND "
				+ " mo1." + m_linetype
				+ " IS NOT NULL group by mo1.C_ORDERLINE_ID))) " );	
			}
			m_sql.append( " AND hdr.DocStatus IN ('CO','CL')" );
			m_groupBy = " GROUP BY hdr.C_Order_ID,hdr.DocumentNo,hdr.DateOrdered,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.C_OrderLine_ID,p.Name,lin.M_Product_ID,lin.QtyOrdered, org.Name, hdr.AD_Org_ID " //JAVIER
				+ "HAVING "
				+ (matched ? "0" : "lin.QtyOrdered")
				+ "<>SUM(COALESCE(mo.Qty,0))";
		}
		else    //  Shipment
		{
			m_dateColumn = "hdr.MovementDate";
			m_qtyColumn = "lin.MovementQty";
			m_sql.append("SELECT hdr.M_InOut_ID,hdr.DocumentNo, hdr.MovementDate, bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.M_InOutLine_ID, p.Name,lin.M_Product_ID,"
				+ " lin.MovementQty,SUM(NVL(m.Qty,0)),org.Name, hdr.AD_Org_ID " //JAVIER
				+ "FROM M_InOut hdr"
				+ " INNER JOIN AD_Org org ON (hdr.AD_Org_ID=org.AD_Org_ID)" //JAVIER
				+ " INNER JOIN C_BPartner bp ON (hdr.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN M_InOutLine lin ON (hdr.M_InOut_ID=lin.M_InOut_ID)"
				+ " INNER JOIN M_Product p ON (lin.M_Product_ID=p.M_Product_ID)"
				+ " INNER JOIN C_DocType dt ON (hdr.C_DocType_ID = dt.C_DocType_ID AND dt.DocBaseType IN ('MMR','MMS'))"
				+ " FULL JOIN ")
				.append(matchToType == MATCH_ORDER ? "M_MatchPO" : "M_MatchInv")
				.append(" m ON (lin.M_InOutLine_ID=m.M_InOutLine_ID) "
				+ "WHERE hdr.DocStatus IN ('CO','CL') and dt.issotrx = 'N' ");
			m_groupBy = " GROUP BY hdr.M_InOut_ID,hdr.DocumentNo,hdr.MovementDate,bp.Name,hdr.C_BPartner_ID,"
				+ " lin.Line,lin.M_InOutLine_ID,p.Name,lin.M_Product_ID,lin.MovementQty, org.Name, hdr.AD_Org_ID " //JAVIER
				+ "HAVING "
				+ (matched ? "0" : "lin.MovementQty")
				+ "<>SUM(NVL(m.Qty,0))";
		}
	//	Log.trace(7, "VMatch.tableInit", m_sql + "\n" + m_groupBy);
	}   //  tableInit


	/**
	 *  Fill the table using m_sql
	 *  @param table table
	 */
	protected void tableLoad (IMiniTable table)
	{
		//	log.finest(m_sql + " - " +  m_groupBy);
		String sql = MRole.getDefault().addAccessSQL(
			m_sql.toString(), "hdr", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO)
			+ m_groupBy;
		log.finest(sql);
		try
		{
			Statement stmt = DB.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			table.loadTable(rs);
			stmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
	}   //  tableLoad

	/**
	 *  Create Matching Record
	 *  @param invoice true if matching invoice false if matching PO
	 *  @param M_InOutLine_ID shipment line
	 *  @param Line_ID C_InvoiceLine_ID or C_OrderLine_ID
	 *  @param qty quantity
	 *  @param trxName 
	 *  @return true if created
	 */
	protected boolean createMatchRecord (boolean invoice, int M_InOutLine_ID, int Line_ID,
		BigDecimal qty, String trxName)
	{
		if (qty.compareTo(Env.ZERO) == 0)
			return true;
		log.fine("IsInvoice=" + invoice
			+ ", M_InOutLine_ID=" + M_InOutLine_ID + ", Line_ID=" + Line_ID
			+ ", Qty=" + qty);
		//
		boolean success = false;
		MInOutLine sLine = new MInOutLine (Env.getCtx(), M_InOutLine_ID, trxName);
		if (invoice)	//	Shipment - Invoice
		{
			//	Update Invoice Line
			MInvoiceLine iLine = new MInvoiceLine (Env.getCtx(), Line_ID, trxName);
			iLine.setM_InOutLine_ID(M_InOutLine_ID);
			if (sLine.getC_OrderLine_ID() != 0)
				iLine.setC_OrderLine_ID(sLine.getC_OrderLine_ID());
			iLine.saveEx();
			//	Create Shipment - Invoice Link
			if (iLine.getM_Product_ID() != 0)
			{
				Boolean useReceiptDateAcct = MSysConfig.getBooleanValue("MATCHINV_USE_DATEACCT_FROM_RECEIPT",
						false, iLine.getAD_Client_ID());
				MMatchInv match = null;
				Boolean isreceiptPeriodOpen = MPeriod.isOpen(Env.getCtx(), sLine.getParent().getDateAcct(),
						sLine.getParent().getC_DocType().getDocBaseType(), sLine.getParent().getAD_Org_ID());
				Boolean isInvoicePeriodOpen = MPeriod.isOpen(Env.getCtx(), iLine.getParent().getDateAcct(),
						iLine.getParent().getC_DocType().getDocBaseType(), iLine.getParent().getAD_Org_ID());

				if (useReceiptDateAcct & isreceiptPeriodOpen) {
					match= new MMatchInv(iLine,sLine.getParent().getDateAcct() , qty);
				}
				else if (isInvoicePeriodOpen){
					match = new MMatchInv(iLine, iLine.getParent().getDateAcct(), qty);
				}
				else {
					match = new MMatchInv(iLine, null, qty);
				}
				match.setM_InOutLine_ID(M_InOutLine_ID);
				match.saveEx();
				if (match.save()) {
					success = true;
				}
				else
					log.log(Level.SEVERE, "Inv Match not created: " + match);

				if (match.getC_InvoiceLine().getC_Invoice().getDocStatus().equals("VO") ||
						match.getC_InvoiceLine().getC_Invoice().getDocStatus().equals("RE") ||
						match.getM_InOutLine().getM_InOut().getDocStatus().equals("VO") ||
						match.getM_InOutLine().getM_InOut().getDocStatus().equals("RE"))
					match.reverseIt(match.getDateAcct());
			}
			else
				success = true;
			//	Create PO - Invoice Link = corrects PO
			if (iLine.getC_OrderLine_ID() != 0 && iLine.getM_Product_ID() != 0)
			{
				MMatchPO matchPO = new MMatchPO(iLine, iLine.getParent().getDateAcct() , qty);
				matchPO.setC_InvoiceLine_ID(iLine);
				if (!matchPO.save())
					log.log(Level.SEVERE, "PO(Inv) Match not created: " + matchPO);
				if (MClient.isClientAccountingImmediate()) {
					String ignoreError = DocumentEngine.postImmediate(matchPO.getCtx(), matchPO.getAD_Client_ID(), matchPO.get_Table_ID(), matchPO.get_ID(), true, matchPO.get_TrxName());						
				}
			}
		}
		else	//	Shipment - Order
		{
			//	Update Shipment Line
			sLine.setC_OrderLine_ID(Line_ID);
			sLine.saveEx();
			//	Update Order Line
			MOrderLine oLine = new MOrderLine(Env.getCtx(), Line_ID, trxName);
			if (oLine.get_ID() != 0)	//	other in MInOut.completeIt
			{
				oLine.setQtyReserved(oLine.getQtyReserved().subtract(qty));
				if(!oLine.save())
					log.severe("QtyReserved not updated - C_OrderLine_ID=" + Line_ID);
			}

			//	Create PO - Shipment Link
			if (sLine.getM_Product_ID() != 0)
			{
				MMatchPO match = new MMatchPO (sLine, null, qty);
				if (!match.save())
					log.log(Level.SEVERE, "PO Match not created: " + match);
				else
				{
					success = true;
					//	Correct Ordered Qty for Stocked Products (see MOrder.reserveStock / MInOut.processIt)
					if (sLine.getProduct() != null && sLine.getProduct().isStocked())
						success = MStorage.add (Env.getCtx(), sLine.getM_Warehouse_ID(), 
							sLine.getM_Locator_ID(), 
							sLine.getM_Product_ID(), 
							sLine.getM_AttributeSetInstance_ID(), oLine.getM_AttributeSetInstance_ID(), 
							null, null, qty.negate(), trxName);
				}
			}
			else
				success = true;
		}
		return success;
	}   //  createMatchRecord
}
