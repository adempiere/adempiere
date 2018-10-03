/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.impexp.BankStatementMatchInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MBankStatementLine;
import org.compiere.model.MBankStatementMatcher;
import org.compiere.model.MPayment;
import org.compiere.model.MRole;
import org.compiere.model.X_I_BankStatement;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 * Controller class for handle all method of Bank Statement Matcher
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1699 ] Add support view for Bank Statement
 * @see https://github.com/adempiere/adempiere/issues/1699
 */
public class BankStatementMatchController {
	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public CLogger log = CLogger.getCLogger(BankStatementMatchController.class);
	
	/**	Bank Account	*/
	private int bankAccountId;
	/**	Window No			*/
	private int	windowNo = 0;
	/**	Bank Statement	*/
	private MBankStatement bankStatement = null;
	/**	Language value	*/
	private String languageValue = null;
	/**	Imported Bank Statement to match	*/
	private Map<Integer, X_I_BankStatement> importedPaymentHashMap = new HashMap<Integer, X_I_BankStatement>();
	/**	Payment Bank Statement to match	*/
	private Map<Integer, MPayment> currentPaymentHashMap = new HashMap<Integer, MPayment>();
	/**	Imported Bank Statement matched	*/
	private Map<Integer, X_I_BankStatement> matchedPaymentHashMap = new HashMap<Integer, X_I_BankStatement>();
	/**	Payment list	*/
	private Vector<Vector<Object>> paymentData = new Vector<Vector<Object>>();
	/**	Account PO	*/
	private MBankAccount account = null;
	/**	is available for save	*/
	private boolean isAvailableForSave = false;
	/**	Amount From	*/
	private BigDecimal amtFrom = null;
	/**	Amount To	*/
	private BigDecimal amtTo = null;
	/**	Date from */
	private Timestamp dateFrom = null;
	/**	Date To	*/
	private Timestamp dateTo = null;
	/** Business Partner	*/
	private int bpartnerId = 0;
	/** Matched	*/
	private int matchMode = -1;
	/**	Current Selected Payment	*/
	private int currentSelectedPaymentId = 0;
	/**	Current Selected Imported Payment	*/
	private int currentSelectedImportedPaymentId = 0;
	/**	Has matched selected	*/
	private boolean hasSelection = false;
	/** Match Mode              	*/
	public String[] m_matchMode = new String[] {
		Msg.translate(Env.getCtx(), "NotMatched"),
		Msg.translate(Env.getCtx(), "Matched")};
	//	
	public static final int		MODE_NOTMATCHED = 0;
	public static final int		MODE_MATCHED = 1;
	
	/**
	 * Init this
	 * @param windowNo
	 * @param processInfo
	 */
	public void init(int windowNo, ProcessInfo processInfo) {
		setFromPO(processInfo);
		//	
		this.windowNo = windowNo;
		languageValue = Env.getAD_Language(Env.getCtx());
	}
	
	/**
	 * Clear values
	 */
	public void clear() {
		matchedPaymentHashMap = new HashMap<Integer, X_I_BankStatement>();
		resetManualMatch();
	}
	
	/**
	 * Is created from Statement
	 * @return
	 */
	public boolean isFromStatement() {
		return bankStatement != null;
	}
	
	/**
	 * Get Statement Date from Parent Bank Statement
	 * @return
	 */
	public Timestamp getStatementDate() {
		if(isFromStatement()) {
			return bankStatement.getStatementDate();
		}
		//	default
		return null;
	}
	
	/**
	 * Message for match button
	 * @param isMatchMode
	 * @return
	 */
	public String getButtonMatchMessage(boolean isMatchMode) {
		if(isMatchMode) {
			return Msg.translate(Env.getCtx(), "BankStatementMatch.SimulateUnMatch");
		}
		//	Default
		return Msg.translate(Env.getCtx(), "BankStatementMatch.SimulateMatch");
	}
	
	/**
	 * Get Button Message
	 * @return
	 */
	public String getButtonMatchMessage() {
		return getButtonMatchMessage(isMatchedMode());
	}
	/**
	 * Message for match button
	 * @return
	 */
	public String getAskMatchMessage() {
		if(isMatchedMode()) {
			return Msg.translate(Env.getCtx(), "BankStatementMatch.AskUnMatch");
		}
		//	Default
		return Msg.translate(Env.getCtx(), "BankStatementMatch.AskMatch");
	}
	
	/**
	 * Is Matched
	 * @return
	 */
	public boolean isMatchedMode() {
		return getMatchedMode() == MODE_MATCHED;
	}
	
	/**
	 * Set if is available for save
	 * @param isAvailableForSave
	 */
	public void setIsAvailableForSave(boolean isAvailableForSave) {
		this.isAvailableForSave = isAvailableForSave;
	}
	
	/**
	 * Is Available for save
	 * @return
	 */
	public boolean isAvailableForSave() {
		return isAvailableForSave;
	}
	
	/**
	 * Get Window No
	 * @return
	 */
	public int getWindowNo() {
		return windowNo;
	}
	
	/**
	 * Set Window No
	 * @param windowNo
	 */
	public void setWindowNo(int windowNo) {
		this.windowNo = windowNo;
	}
	
	/**
	 * 
	 * @param tableId
	 * @param recordId
	 * @throws Exception
	 */
	public void dynInit() throws Exception {
		setFromPO(null);
		//
	}	
	
	/**
	 * Set from PO
	 * @param processInfo
	 */
	public void setFromPO(ProcessInfo processInfo) {
		if(processInfo != null
				&& processInfo.getTable_ID() > 0
				&& processInfo.getRecord_ID() > 0) {
			bankStatement = new MBankStatement(Env.getCtx(), processInfo.getRecord_ID(), processInfo.getTransactionName());
			bankAccountId = bankStatement.getC_BankAccount_ID();
			setBankAccountId(bankAccountId);
		}
	}
	
	/**
	 * Set Account
	 * @param bankAccountId
	 */
	public void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
		if(bankAccountId > 0) {
			account = MBankAccount.get(Env.getCtx(), bankAccountId);
		}
	}
	
	/**
	 * Get Payment Data for show on two tables
	 * @return
	 */
	public Vector<Vector<Object>> getPaymentData() {
		paymentData = new Vector<Vector<Object>>();
		//	
		StringBuffer sql = new StringBuffer("SELECT p.C_Payment_ID, p.DateTrx, p.IsReceipt, p.DocumentNo, "
				+ "p.C_BPartner_ID, bp.Name BPName, tt.TenderTypeName AS TenderType, "
				+ "c.ISO_Code, (p.PayAmt * CASE WHEN p.IsReceipt = 'Y' THEN 1 ELSE -1 END) AS PayAmt, p.Description, p.* "
				+ "FROM C_Payment p "
				+ "INNER JOIN C_BPartner bp ON(bp.C_BPartner_ID = p.C_BPartner_ID) "
				+ "INNER JOIN C_Currency c ON(c.C_Currency_ID = p.C_Currency_ID) "
				+ "INNER JOIN (SELECT tt.Value AS TenderType, COALESCE(ttr.Name, tt.Name) AS TenderTypeName"
				+ "				FROM AD_Ref_List tt"
				+ "				LEFT JOIN AD_Ref_List_Trl ttr ON(ttr.AD_Ref_List_ID = tt.AD_Ref_List_ID AND AD_Language = '" + languageValue + "')"
				+ "				WHERE tt.AD_Reference_ID=214) tt ON(tt.TenderType = p.TenderType) ");
		//	Where Clause
		sql.append("WHERE p.C_BankAccount_ID = ? ");
		sql.append(" AND p.DocStatus NOT IN('IP', 'DR') ");
		sql.append(" AND p.IsReconciled = 'N' ");
		if(bankStatement != null) {
			sql.append("AND NOT EXISTS(SELECT 1 FROM C_BankStatement bs "
					+ "INNER JOIN C_BankStatementLine bsl ON(bsl.C_BankStatement_ID = bs.C_BankStatement_ID) "
					+ "WHERE bsl.C_Payment_ID = p.C_Payment_ID "
					+ "AND bs.DocStatus IN('CO', 'CL') "
					+ "AND bsl.C_BankStatement_ID <> ").append(bankStatement.getC_BankStatement_ID()).append(") ");
		}
		//	Match
		if(isMatchedMode()) {
			sql.append("AND EXISTS(SELECT 1 FROM I_BankStatement ibs "
					+ "WHERE (ibs.C_Payment_ID = p.C_Payment_ID))");
		} else {
			sql.append("AND NOT EXISTS(SELECT 1 FROM I_BankStatement ibs "
					+ "WHERE ibs.C_Payment_ID = p.C_Payment_ID)");
		}
		//	For parameters
		//	Date Trx
		if(getDateFrom() != null) {
			sql.append("AND p.DateTrx >= ? ");
		}
		if(getDateTo() != null) {
			sql.append("AND p.DateTrx <= ? ");
		}
		//	Amount
		if(getAmtFrom() != null) {
			sql.append("AND p.PayAmt >= ? ");
		}
		if(getAmtTo() != null) {
			sql.append("AND p.PayAmt <= ? ");
		}
		//	for BP
		if(getBpartnerId() > 0) {
			sql.append("AND p.C_BPartner_ID = ? ");
		}
		// role security
		sql = new StringBuffer(MRole.getDefault(Env.getCtx(), false)
				.addAccessSQL(sql.toString(), "p", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO));
		//	Order by
		sql.append("ORDER BY p.DateTrx");
		//	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	fill hash
		currentPaymentHashMap = new HashMap<Integer, MPayment>();
		try {
			int i= 1;
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(i++, bankAccountId);
			if(getDateFrom() != null) {
				pstmt.setTimestamp(i++, getDateFrom());
			}
			if(getDateTo() != null) {
				pstmt.setTimestamp(i++, getDateTo());
			}
			if(getAmtFrom() != null) {
				pstmt.setBigDecimal(i++, getAmtFrom());
			}
			if(getAmtTo() != null) {
				pstmt.setBigDecimal(i++, getAmtTo());
			}
			if(getBpartnerId() > 0) {
				pstmt.setInt(i++, getBpartnerId());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector<Object> line = new Vector<Object>();
				line.add(new IDColumn(rs.getInt("C_Payment_ID")));      //  0-Selection
				line.add(rs.getTimestamp("DateTrx"));       			//  1-DateTrx
				line.add(rs.getString("IsReceipt").equals("Y"));      	//  2-IsReceipt
				line.add(rs.getString("DocumentNo"));      				//  3-DocumentNo
				KeyNamePair pp = new KeyNamePair(rs.getInt("C_BPartner_ID"), rs.getString("BPName"));
				line.add(pp); 											//	4-BPName
				line.add(rs.getString("TenderType"));      				//  5-TenderType
				line.add(rs.getString("ISO_Code"));      				//  6-Currency
				line.add(rs.getBigDecimal("PayAmt"));					//  7-PayAmt
				line.add(rs.getString("Description")); 					// 	8-Description
				//	Add model class
				currentPaymentHashMap.put(rs.getInt("C_Payment_ID"), new MPayment(Env.getCtx(), rs, null));
				paymentData.add(line);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	
		return paymentData;
	}
	
	/**
	 * Get imported columns
	 * @return
	 */
	public Vector<String> getImportedPaymentColumnNames() {
		//  Header Info
		Vector<String> columnNames = new Vector<String>(8);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateTrx"));
		columnNames.add(Msg.translate(Env.getCtx(), "IsReceipt"));
		columnNames.add(Msg.translate(Env.getCtx(), "ReferenceNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "Memo"));
		return columnNames;
	}
	
	/**
	 * Configure imported payment table
	 * @param miniTable
	 */
	public void configureImportedPaymentTable(IMiniTable miniTable) {
		miniTable.setKeyColumnIndex(0);
		miniTable.setColumnClass(0, IDColumn.class, true, getImportedPaymentColumnNames().get(0));     //  0-Selection
		miniTable.setColumnClass(1, Timestamp.class, true);     //  1-TrxDate
		miniTable.setColumnClass(2, Boolean.class, true);       //  2-IsReceipt
		miniTable.setColumnClass(3, String.class, true);    	//  3-ReferenceNo
		miniTable.setColumnClass(4, String.class, true);    	//  4-C_BPartner_ID
		miniTable.setColumnClass(5, String.class, true);    	//  4-C_Currency_ID
		miniTable.setColumnClass(6, BigDecimal.class, true);    //  5-Amount
		miniTable.setColumnClass(7, String.class, true);        //  6-Description
		//  Table UI
		miniTable.autoSize();
	}
	
	/**
	 * Get payments columns
	 * @return
	 */
	public Vector<String> getCurrentPaymentColumnNames() {
		//  Header Info
		Vector<String> columnNames = new Vector<String>(9);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateTrx"));
		columnNames.add(Msg.translate(Env.getCtx(), "IsReceipt"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "TenderType"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "Description"));
		return columnNames;
	}
	
	/**
	 * Configure payment table
	 * @param miniTable
	 */
	public void configureCurrentPaymentTable(IMiniTable miniTable) {
		miniTable.setKeyColumnIndex(0);
		miniTable.setColumnClass(0, IDColumn.class, true, getCurrentPaymentColumnNames().get(0));     //  0-Selection
		miniTable.setColumnClass(1, Timestamp.class, true);     //  1-TrxDate
		miniTable.setColumnClass(2, Boolean.class, true);       //  2-IsCollect
		miniTable.setColumnClass(3, String.class, true);    	//  3-DocumentNo
		miniTable.setColumnClass(4, String.class, true);    	//  4-C_BPartner_ID
		miniTable.setColumnClass(5, String.class, true);    	//  5-TenderType
		miniTable.setColumnClass(6, String.class, true);    	//  4-C_Currency_ID
		miniTable.setColumnClass(7, BigDecimal.class, true);    //  6-Amount
		miniTable.setColumnClass(8, String.class, true);        //  7-Description
		//  Table UI
		miniTable.autoSize();
	}
	
	/**
	 * Get matched columns
	 * @return
	 */
	public Vector<String> getMatchedPaymentColumnNames() {
		//  Header Info
		Vector<String> columnNames = new Vector<String>(11);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "DateTrx"));
		columnNames.add(Msg.translate(Env.getCtx(), "IsReceipt"));
		columnNames.add(Msg.translate(Env.getCtx(), "DocumentNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "TenderType"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "Description"));
		columnNames.add(Msg.translate(Env.getCtx(), "ReferenceNo"));
		columnNames.add(Msg.translate(Env.getCtx(), "Memo"));
		return columnNames;
	}
	
	/**
	 * Configure matched payments table
	 * @param miniTable
	 */
	public void configureMatchedPaymentTable(IMiniTable miniTable) {
		miniTable.setKeyColumnIndex(0);
		miniTable.setColumnClass(0, IDColumn.class, true, getMatchedPaymentColumnNames().get(0));     //  0-Selection
		miniTable.setColumnClass(1, Timestamp.class, true);     //  1-TrxDate
		miniTable.setColumnClass(2, Boolean.class, true);       //  2-IsCollect
		miniTable.setColumnClass(3, String.class, true);    	//  3-DocumentNo
		miniTable.setColumnClass(4, String.class, true);    	//  4-C_BPartner_ID
		miniTable.setColumnClass(5, String.class, true);    	//  5-TenderType
		miniTable.setColumnClass(6, String.class, true);        //  6-C_Currency_ID
		miniTable.setColumnClass(7, BigDecimal.class, true);    //  7-Amount
		miniTable.setColumnClass(8, String.class, true);        //  8-Description
		miniTable.setColumnClass(9, String.class, true);        //  9-Description
		miniTable.setColumnClass(10, String.class, true);       //  10-Memo
		//  Table UI
		miniTable.autoSize();
	}
	
	/**
	 * Get Imported Payment Data for show on two tables
	 * @return
	 */
	public Vector<Vector<Object>> getImportedPaymentData() {
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		//	
		StringBuffer sql = new StringBuffer("SELECT p.I_BankStatement_ID, p.StatementLineDate, (CASE WHEN p.TrxAmt < 0 THEN 'N' ELSE 'Y' END) AS IsReceipt, "
				+ "p.ReferenceNo, p.C_BPartner_ID, (CASE WHEN p.C_BPartner_ID IS NULL THEN BPartnerValue ELSE bp.Name END) BPName, "
				+ "COALESCE(p.ISO_Code, c.ISO_Code) AS ISO_Code, p.TrxAmt, p.Memo, p.* "
				+ "FROM I_BankStatement p "
				+ "LEFT JOIN C_BPartner bp ON(bp.C_BPartner_ID = p.C_BPartner_ID) "
				+ "LEFT JOIN C_Currency c ON(c.C_Currency_ID = p.C_Currency_ID) ");
		//	Where Clause
		sql.append("WHERE p.C_BankAccount_ID = ? ");
		if(bankStatement == null
				|| (!bankStatement.isProcessed() && !isMatchedMode())) {
			sql.append("AND p.I_IsImported = 'N' ");
		}
		//	Match
		if(isMatchedMode()) {
			sql.append("AND (p.C_Payment_ID IS NOT NULL OR p.C_BPartner_ID IS NOT NULL OR p.C_Invoice_ID IS NOT NULL) ");
		} else {
			sql.append("AND (p.C_Payment_ID IS NULL AND p.C_BPartner_ID IS NULL AND p.C_Invoice_ID IS NULL) ");
		}
		
		//	For parameters
		//	Date Trx
		if(getDateFrom() != null) {
			sql.append("AND p.StatementLineDate >= ? ");
		}
		if(getDateTo() != null) {
			sql.append("AND p.StatementLineDate <= ? ");
		}
		//	Amount
		if(getAmtFrom() != null) {
			sql.append("AND p.TrxAmt >= ? ");
		}
		if(getAmtTo() != null) {
			sql.append("AND p.TrxAmt <= ? ");
		}
		// role security
		sql = new StringBuffer(MRole.getDefault(Env.getCtx(), false)
				.addAccessSQL(sql.toString(), "p", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO));
		//	Order by
		sql.append("ORDER BY p.StatementLineDate");
		//	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//	fill hash
		importedPaymentHashMap = new HashMap<Integer, X_I_BankStatement>();
		try {
			int i = 1;
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(i++, bankAccountId);
			if(getDateFrom() != null) {
				pstmt.setTimestamp(i++, getDateFrom());
			}
			if(getDateTo() != null) {
				pstmt.setTimestamp(i++, getDateTo());
			}
			if(getAmtFrom() != null) {
				pstmt.setBigDecimal(i++, getAmtFrom());
			}
			if(getAmtTo() != null) {
				pstmt.setBigDecimal(i++, getAmtTo());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector<Object> line = new Vector<Object>();
				line.add(new IDColumn(rs.getInt("I_BankStatement_ID")));//  0-Selection
				line.add(rs.getTimestamp("StatementLineDate"));       	//  1-StatementLineDate
				line.add(rs.getString("IsReceipt").equals("Y"));      	//  2-IsReceipt
				line.add(rs.getString("ReferenceNo"));      			//  3-ReferenceNo
				KeyNamePair pp = new KeyNamePair(rs.getInt("C_BPartner_ID"), rs.getString("BPName"));
				line.add(pp); 											//	4-BPName
				line.add(rs.getString("ISO_Code"));      				//  5-Currency
				line.add(rs.getBigDecimal("TrxAmt"));					//  6-TrxAmt
				line.add(rs.getString("Memo")); 						// 	7-Memo
				data.add(line);
				//	Add model class
				importedPaymentHashMap.put(rs.getInt("I_BankStatement_ID"), new X_I_BankStatement(Env.getCtx(), rs, null));
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	
		return data;
	}
	
	/**
	 * Action for button
	 * @return
	 */
	public int actionMatchUnMatch() {
		int processed = 0;
		//	Instance
		matchedPaymentHashMap = new HashMap<Integer, X_I_BankStatement>();
		//	For all
		if(importedPaymentHashMap.isEmpty()) {
			return 0;
		}
		//	Apply
		if(!isMatchedMode()) {
			processed = findMatch();
		} else {
			processed = unMatch();
		}
		//	
		return processed;
	}
	
	/**
	 * Action for button
	 * @return
	 */
	public int actionUnMatchSelected(IMiniTable matched) {
		int processed = 0;
		int rows = matched.getRowCount();
		for (int i = 0; i < rows; i++) {
			IDColumn selection = (IDColumn) matched.getValueAt(i, 0);
			if (selection.isSelected()) {
				matchedPaymentHashMap.remove(selection.getRecord_ID());
				processed++;
			}
		}
		//	
		return processed;
	}
	
	/**
	 * Match two records manual
	 * @param paymentId
	 * @param bankStatementId
	 * @return
	 */
	public boolean actionFindManualMatch(int paymentId, int bankStatementId) {
		boolean processed = false;
		//	For all
		if(importedPaymentHashMap.isEmpty()) {
			return processed;
		}
		//	Apply
		if(!isMatchedMode()) {
			processed = findManualMatch(0,0);
		} else {
			processed = findManualUnMatch(paymentId, bankStatementId);
		}
		//	
		return processed;
	}
	
	/**
	 * Unmatch records
	 * @return
	 */
	private int unMatch() {
		int matched = 0;
		//	
		for(Map.Entry<Integer, X_I_BankStatement> entry : importedPaymentHashMap.entrySet()) {
			X_I_BankStatement currentpayment = entry.getValue();
			if(currentpayment.getC_Payment_ID() == 0
					&& currentpayment.getC_BPartner_ID() == 0
					&& currentpayment.getC_Invoice_ID() == 0) {
				continue;
			}
			//	put on hash
			matchedPaymentHashMap.put(entry.getValue().getC_Payment_ID(), currentpayment);
			//	remove payment
			if(currentpayment.getC_Payment_ID() != 0) {
				currentpayment.setC_Payment_ID(-1);
			}
			//	Remove BPartner
			if(currentpayment.getC_BPartner_ID() != 0) {
				currentpayment.setC_BPartner_ID(-1);
			}
			//	Invoice
			if(currentpayment.getC_Invoice_ID() != 0) {
				currentpayment.setC_Invoice_ID(-1);
			}
			matched++;
		}
		//	Return
		return matched;
	}
	
	/**
	 * Find match between two records (Manual)
	 * @param paymentId
	 * @param bankStatementId
	 * @return true if exist a possible match
	 */
	public boolean findManualMatch(int paymentId, int bankStatementId) {
		X_I_BankStatement importedPayment = importedPaymentHashMap.get(bankStatementId);
		MPayment currentPayment = currentPaymentHashMap.get(paymentId);
		boolean processed = false;
		if(importedPayment != null
				&& currentPayment != null) {
			//	Validate amount
			if(currentPayment.getPayAmt().doubleValue() == importedPayment.getTrxAmt().doubleValue()
					&& currentPayment.getC_Currency_ID() == importedPayment.getC_Currency_ID()) {
				importedPayment.setC_Payment_ID(paymentId);
				matchedPaymentHashMap.put(paymentId, importedPayment);
				processed = true;
			}
			resetManualMatch();
		}
		//	
		if(processed) {
			deleteOldMatchedImportedPayment(paymentId, bankStatementId);
		}
		//	
		return processed;
	}
	
	/**
	 * Delete Old Imported
	 * @param paymentId
	 * @param importedPaymentId
	 */
	private void deleteOldMatchedImportedPayment(int paymentId, int importedPaymentId) {
		int oldpaymentId = 0;
		for(Map.Entry<Integer, X_I_BankStatement> entry : matchedPaymentHashMap.entrySet()) {
			X_I_BankStatement currentPayment = entry.getValue();
			if(currentPayment.getI_BankStatement_ID() == importedPaymentId) {
				oldpaymentId = entry.getKey();
				if(oldpaymentId != paymentId) {
					break;
				}
			}
		}
		//	Validate
		if(oldpaymentId > 0
				&& paymentId != oldpaymentId) {
			matchedPaymentHashMap.remove(oldpaymentId);
		}
	}
	
	/**
	 * Add Payment to apply match
	 * @param paymentId
	 * @return truen if match is done
	 */
	public boolean paymentApplyForMatch(int paymentId) {
		currentSelectedPaymentId = paymentId;
		return applyManualMatch();
	}
	
	/**
	 * Apply Imported Payment to Match
	 * @param importedPaymentId
	 * @return truen if match is done
	 */
	public boolean importedPaymentApplyForMatch(int importedPaymentId) {
		currentSelectedImportedPaymentId = importedPaymentId;
		return applyManualMatch();
	}
	
	/**
	 * Apply a manual match from selected
	 * @return true when match is done
	 */
	private boolean applyManualMatch() {
		boolean ok = false;
		if(currentSelectedImportedPaymentId > 0
				&& currentSelectedPaymentId > 0) {
			ok = findManualMatch(currentSelectedPaymentId, currentSelectedImportedPaymentId);
		}
		return ok;
	}
	
	/**
	 * Get imported payment from matched payments
	 * @param paymentId
	 * @return
	 */
	public int getImportedPaymentId(int paymentId) {
		X_I_BankStatement currentPayment = matchedPaymentHashMap.get(paymentId);
		if(currentPayment != null) {
			return currentPayment.getI_BankStatement_ID();
		}
		//	
		return -1;
	}
	
	/**
	 * Reset current index for math
	 */
	public void resetManualMatch() {
		currentSelectedPaymentId = 0;
		currentSelectedImportedPaymentId = 0;
	}
	
	/**
	 * Unmatch payment (Manual)
	 * @param paymentId
	 * @param bankStatementId
	 * @return
	 */
	private boolean findManualUnMatch(int paymentId, int bankStatementId) {
		X_I_BankStatement currentPayment = importedPaymentHashMap.get(bankStatementId);
		if(currentPayment != null) {
			//	remove payment
			if(currentPayment.getC_Payment_ID() != 0) {
				currentPayment.setC_Payment_ID(-1);
			}
			//	Remove BPartner
			if(currentPayment.getC_BPartner_ID() != 0) {
				currentPayment.setC_BPartner_ID(-1);
			}
			//	Invoice
			if(currentPayment.getC_Invoice_ID() != 0) {
				currentPayment.setC_Invoice_ID(-1);
			}
			matchedPaymentHashMap.put(paymentId, currentPayment);
			return true;
		}
		//	Return
		return false;
	}
	
	/**
	 * Find a match between imported payment and ADempiere payments
	 * @return matched payment count
	 */
	private int findMatch() {
		int matched = 0;
		int bankId = 0;
		if(account != null) {
			bankId = account.getC_Bank_ID();
		}
		List<MBankStatementMatcher> matchersList = MBankStatementMatcher.getMatchersList(Env.getCtx(), bankId);
		if(matchersList == null) {
			return 0;
		}
		//	
		for(Map.Entry<Integer, X_I_BankStatement> entry : importedPaymentHashMap.entrySet()) {
			X_I_BankStatement currentPayment = entry.getValue();
			if(currentPayment.getC_Payment_ID() != 0
					|| currentPayment.getC_BPartner_ID() != 0
					|| currentPayment.getC_Invoice_ID() != 0) {
				//	put on hash
				matchedPaymentHashMap.put(entry.getValue().getC_Payment_ID(), currentPayment);
				matched++;
				continue;
			}
			for (MBankStatementMatcher matcher : matchersList) {
				if (matcher.isMatcherValid()) {
					BankStatementMatchInfo info = matcher.getMatcher().findMatch(currentPayment);
					if (info != null && info.isMatched()) {
						if (info.getC_Payment_ID() > 0) {
							currentPayment.setC_Payment_ID(info.getC_Payment_ID());
						}
						if (info.getC_Invoice_ID() > 0) {
							currentPayment.setC_Invoice_ID(info.getC_Invoice_ID());
						}
						if (info.getC_BPartner_ID() > 0) {
							currentPayment.setC_BPartner_ID(info.getC_BPartner_ID());
						}
						//	put on hash
						matchedPaymentHashMap.put(entry.getValue().getC_Payment_ID(), currentPayment);
						matched++;
						break;
					}
				}
			}	//	for all matchers
		}
		//	Return
		return matched;
	}
	
	/**
	 * Add matched payments to table
	 * @return row for table
	 */
	public Vector<Vector<Object>> getMatchedPayments() {
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		//	
		for(Vector<Object> row : paymentData) {
			IDColumn key = (IDColumn) row.get(0);
			X_I_BankStatement matched = matchedPaymentHashMap.get(key.getRecord_ID());
			if(matched != null
					&& matched.is_Changed()) {
				//	Add Reference No
				row.add(matched.getReferenceNo());
				//	Add Memo
				row.add(matched.getMemo());
				data.add(row);
			}
		}
		//	
		return data;
	}
	
	/**
	 * Clear selection
	 * @param table
	 */
	public void clearSelection(IMiniTable table) {
		for (int row = 0; row < table.getRowCount(); row++) {
			IDColumn id = (IDColumn) table.getValueAt(row, 0);
			id.setSelected(false);
		}
	}
	
	
	/**************************************************************************
	 *  Save Data
	 */
	public String saveData(int m_WindowNo, String trxName) {
		if(matchedPaymentHashMap.isEmpty()) {
			return Msg.translate(Env.getCtx(), "BankStatementMatch.NoMatchedFound");
		}
		int processed = 0;
		int lineNo = 10;
		int defaultChargeId = DB.getSQLValue(null, "SELECT MAX(C_Charge_ID) FROM C_Charge WHERE AD_Client_ID = ?", Env.getAD_Client_ID(Env.getCtx()));
		if(defaultChargeId <= 0) {
			return Msg.parseTranslation(Env.getCtx(), "@C_Charge_ID@ @NotFound@");
		}
		//	
		for(Vector<Object> row : paymentData) {
			IDColumn key = (IDColumn) row.get(0);	
			X_I_BankStatement currentBankStatementImport = matchedPaymentHashMap.get(key.getRecord_ID());
			//	Validate if it have a change
			if(currentBankStatementImport == null
					|| !currentBankStatementImport.is_Changed()) {
				continue;
			}
			//	Set trx
			currentBankStatementImport.set_TrxName(trxName);
			//	Save It
			currentBankStatementImport.saveEx();
			//	For Bank Statement
			if(bankStatement != null
					&& !bankStatement.isProcessed()) {
				if(!isMatchedMode()) {
					if(currentBankStatementImport.getC_Payment_ID() <= 0
							&& currentBankStatementImport.getC_Charge_ID() <= 0) {
						currentBankStatementImport.setC_Charge_ID(defaultChargeId);
					}
					importMatched(currentBankStatementImport, lineNo);
					lineNo += 10;
				} else if(currentBankStatementImport.getC_BankStatementLine_ID() > 0) {
					MBankStatementLine lineToDelete = new MBankStatementLine(Env.getCtx(), currentBankStatementImport.getC_BankStatementLine_ID(), trxName);
					lineToDelete.deleteEx(true);
					//	Change Imported
					currentBankStatementImport.setC_BankStatement_ID(-1);
					currentBankStatementImport.setC_BankStatementLine_ID(-1);
					currentBankStatementImport.setI_IsImported(false);
					currentBankStatementImport.setProcessed(false);
					currentBankStatementImport.saveEx();
				}
			}
			processed++;
		}
		//	Return processed
		return Msg.translate(Env.getCtx(), "BankStatementMatch.MatchedProcessed") + ": " + processed;
	}   //  saveData
	
	/**
	 * Import Matched Line
	 * @param toBeImport
	 * @param lineNo
	 */
	private void importMatched(X_I_BankStatement toBeImport, int lineNo) {
		if(!isFromStatement()) {
			return;
		}
		//	
		MBankStatementLine lineToImport = new MBankStatementLine(bankStatement, toBeImport, lineNo);
		lineToImport.saveEx();
		toBeImport.setC_BankStatement_ID(bankStatement.getC_BankStatement_ID());
		toBeImport.setC_BankStatementLine_ID(lineToImport.getC_BankStatementLine_ID());
		toBeImport.setI_IsImported(true);
		toBeImport.setProcessed(true);
		toBeImport.saveEx();
	}
	
	/**
	 * Select source from match
	 * @param currentPaymentTable
	 * @param importedPaymentTable
	 * @param paymentId
	 */
	public void selectFromMatch(IMiniTable currentPaymentTable, IMiniTable importedPaymentTable, int paymentId) {
		for (int row = 0; row < currentPaymentTable.getRowCount(); row++) {
			IDColumn record = (IDColumn) currentPaymentTable.getValueAt(row, 0);
			if(record != null
					&& record.getRecord_ID() == paymentId) {
				currentPaymentTable.setRowChecked(row, true);
			}
		}
		int importedPaymentId = getImportedPaymentId(paymentId);
		if(importedPaymentId > 0) {
			for (int row = 0; row < importedPaymentTable.getRowCount(); row++) {
				IDColumn record = (IDColumn) importedPaymentTable.getValueAt(row, 0);
				if(record != null
						&& record.getRecord_ID() == importedPaymentId) {
					importedPaymentTable.setRowChecked(row, true);
				}
			}
		}
	}
	
	/**
	 * Validate parameters, it return null is nothing happens, else return a translated message
	 * @return
	 */
	public String validateParameters() {
		StringBuffer message = new StringBuffer();
		if(bankAccountId <= 0) {
			message.append("@C_BankAccount_ID@ @NotFound@");
		}
		//	
		if(message.length() > 0) {
			return message.toString();
		}
		//	Match Mode
		if(matchMode < 0) {
			if(message.length() > 0) {
				message.append(Env.NL);
			}
			//	
			message.append("@MatchMode@ @NotFound@");
		}
		//	Default
		return null;
	}

	public MBankAccount getAccount() {
		return account;
	}

	public void setAccount(MBankAccount account) {
		this.account = account;
	}

	public BigDecimal getAmtFrom() {
		return amtFrom;
	}

	public void setAmtFrom(BigDecimal amtFrom) {
		this.amtFrom = amtFrom;
	}

	public BigDecimal getAmtTo() {
		return amtTo;
	}

	public void setAmtTo(BigDecimal amtTo) {
		this.amtTo = amtTo;
	}

	public Timestamp getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Timestamp dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Timestamp getDateTo() {
		return dateTo;
	}

	public void setDateTo(Timestamp dateTo) {
		this.dateTo = dateTo;
	}

	public int getBpartnerId() {
		return bpartnerId;
	}

	public void setBpartnerId(int bpartnerId) {
		this.bpartnerId = bpartnerId;
	}
	
	public int getMatchedMode() {
		return matchMode;
	}

	public void setMatchMode(int matchMode) {
		this.matchMode = matchMode;
	}
	
	public int getBankAccountId() {
		return bankAccountId;
	}

	public boolean isHasSelection() {
		return hasSelection;
	}

	public void setHasSelection(boolean hasSelection) {
		this.hasSelection = hasSelection;
	}
}