/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Contributor(s): Oscar Gomez & Victor Perez www.e-evolution.com             *
 * Copyright (C) 2003-2011 e-Evolution,SC. All Rights Reserved.               *
 *****************************************************************************/
package org.eevolution.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MLookupInfo;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.*;

/**
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @author alberto.juarez@e-evolution.com, www.e-evolution.com
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/779">
 * 		@see FR [ 779 ] Payroll Action Notice is slow</a>
 */
public class HRActionNotice
{

	
	/**	Logger			*/
	protected static CLogger log = CLogger.getCLogger(HRActionNotice.class);
	//
	/**	Window No			*/
	public int         	m_WindowNo = 0;

	protected I_HR_Process payrollProcess = null;
	protected int payrollProcessId = 0;
	protected int payrollId = 0;
	protected int conceptId = 0;
	protected int partnerId = 0;
	protected int movementId = 0; // // initial not exist record in Movement to actual date
	protected boolean isLookupTextMsg = false;

	protected Timestamp dateStart = null;
	protected Timestamp dateEnd = null;
	protected BigDecimal quantity = Env.ZERO;
	protected BigDecimal amount = Env.ZERO;
	protected String text = null;
	protected Timestamp serviceDate = null;
	protected String description;
	protected Timestamp validFrom = null;
	protected Timestamp validTo = null;


	public void dynInit() throws Exception
	{
		log.info("HRActionNotice");
	}

	/**
	 * Configure Mini table
	 * @param miniTable
	 */
	public void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.addColumn("HR_Movement_ID"); 		// 0
		miniTable.addColumn("AD_Org_ID");			// 1
		miniTable.addColumn("HR_Concept_ID");		// 2
		miniTable.addColumn("ValidFrom");			// 3
		miniTable.addColumn("ColumnType");			// 4
		miniTable.addColumn("Qty");					// 5
		miniTable.addColumn("Amount");				// 6
		miniTable.addColumn("ServiceDate");			// 7
		miniTable.addColumn("TextMsg");				// 8
		miniTable.addColumn("Description");			// 10
		//  set details
		miniTable.setColumnClass(0, IDColumn.class, false, " ");
		miniTable.setColumnClass(1, String.class, true, Msg.translate(Env.getCtx(), "AD_Org_ID"));
		miniTable.setColumnClass(2, String.class, true, Msg.translate(Env.getCtx(), "HR_Concept_ID"));
		miniTable.setColumnClass(3, Timestamp.class, true, Msg.translate(Env.getCtx(), "ValidFrom"));
		miniTable.setColumnClass(4, String.class, true, Msg.translate(Env.getCtx(), "ColumnType"));
		miniTable.setColumnClass(5, BigDecimal.class, true, Msg.translate(Env.getCtx(), "Qty"));
		miniTable.setColumnClass(6, BigDecimal.class, true, Msg.translate(Env.getCtx(), "Amount"));
		miniTable.setColumnClass(7, Timestamp.class, true, Msg.translate(Env.getCtx(), "ServiceDate"));
		miniTable.setColumnClass(8, String.class, true, Msg.translate(Env.getCtx(), "TextMsg"));
		miniTable.setColumnClass(9, String.class, true, Msg.translate(Env.getCtx(), "Description"));
		//
		miniTable.autoSize();
	}
	
	/**
	 *  Query Info
	 */
	public void executeQuery(Properties ctx ,IMiniTable miniTable, int beging) {
		//	Validate criteria
		if(payrollProcessId == 0
				|| partnerId == 0)
			return;
		//	
		StringBuffer sqlQuery = new StringBuffer("SELECT o.Name, hc.Name, "				//	Organization Name, Concept Name
				+ "hm.ValidFrom, cr.ColumnType, "										//	Valid From, Column Type
				+ "hm.Qty, hm.Amount, hm.ServiceDate, hm.TextMsg, hm.Description, "		//	Quantity, Amount, Service Date, Text Message, Description
				+ "hm.HR_Movement_ID, hm.AD_Org_ID, hm.HR_Process_ID,hm.HR_Concept_ID ");	//	References
		//	From Clause
		sqlQuery.append("FROM HR_Movement hm "
				+ "INNER JOIN AD_Org o ON(hm.AD_Org_ID = o.AD_Org_ID) "
				+ "INNER JOIN HR_Concept hc ON(hm.HR_Concept_ID = hc.HR_Concept_ID) "
				+ "LEFT JOIN (SELECT r.Value, COALESCE(rt.Name, r.Name) ColumnType "
				+ "				FROM AD_Ref_List r "
				+ "				LEFT JOIN AD_Ref_List_Trl rt ON(rt.AD_Ref_List_ID = r.AD_Ref_List_ID AND rt.AD_Language = ?) "
				+ "				WHERE r.AD_Reference_ID = ?) cr ON(cr.Value = hc.ColumnType) ");
		//	Where Clause
		sqlQuery.append("WHERE hm.Processed = 'N' "
				+ "AND hm.HR_Process_ID = ? "
				+ "AND hm.C_BPartner_ID = ? ");
		//	Order By
		sqlQuery.append("ORDER BY o.AD_Org_ID, hm.HR_Process_ID, hm.ValidFrom, hm.HR_Concept_ID");
		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sqlQuery.toString(), null);
			//	Language
			pstmt.setString(1, Env.getAD_Language(Env.getCtx()));
			//	Reference for Column Type
			pstmt.setInt(2, MHRConcept.COLUMNTYPE_AD_Reference_ID);
			//	HR Process
			pstmt.setInt(3, payrollProcessId);
			//	Business Partner
			pstmt.setInt(4, partnerId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int column = beging;
				//  extend table
				miniTable.setRowCount(row+1);
				//  set values
				miniTable.setColumnClass(0, IDColumn.class, false, " ");
				miniTable.setValueAt(rs.getString(1), row, column++);		// AD_Org_ID
				miniTable.setValueAt(rs.getString(2), row, column++);		// HR_Concept_ID
				miniTable.setValueAt(rs.getTimestamp(3), row, column++);	// ValidFrom
				miniTable.setValueAt(rs.getString(4), row, column++);		// ColumnType
				miniTable.setValueAt(rs.getObject(5) != null ? rs.getBigDecimal(5) : Env.ZERO, row, column++);	// Qty
				miniTable.setValueAt(rs.getObject(6) != null ? rs.getBigDecimal(6) : Env.ZERO, row, column++);	// Amount
				miniTable.setValueAt(rs.getTimestamp(7), row, column++);	// ServiceDate
				miniTable.setValueAt(rs.getString(8), row, column++);		// TextMsg
				miniTable.setValueAt(rs.getString(9), row, column++);	// Description
				//  prepare next
				row++;
			}
		}
		catch (SQLException e) {
			log.log(Level.SEVERE, sqlQuery.toString(), e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		miniTable.autoSize();
	}   //  executeQuery	
	
	/**
	 *  get Process
	 *  parameter: MHRProcess
	 */
	public static KeyNamePair[] getProcess() {
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT hrp.HR_Process_ID,hrp.DocumentNo ||'-'|| hrp.Name, hrp.DocumentNo, hrp.Name "
				+ "FROM HR_Process hrp",
				"hrp",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) 
				+ " AND hrp.IsActive = 'Y'"
				+ " AND hrp.DocStatus IN('DR', 'IP') ";
		sql += " ORDER BY hrp.DocumentNo, hrp.Name";

		return DB.getKeyNamePairs(sql, true);
	} //getProcess



	/**
	 *  get Employee 
	 *  to Valid  Payroll-Department-Employee of Process Actual 
	 *  parameter: MHRProcess
	 */
	public static KeyNamePair[] getEmployeeValid(I_HR_Process process) {
		List<KeyNamePair> list = new ArrayList<KeyNamePair>();
		if (process == null)
			return new KeyNamePair[]{new KeyNamePair(0, "")};
		//	Get Payroll attribute
		MHRPayroll payroll = MHRPayroll.getById(Env.getCtx(), process.getHR_Payroll_ID());
		
		KeyNamePair pp = new KeyNamePair(0, "");
		list.add(pp);
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT bp.C_BPartner_ID, bp.Value || ' - ' || bp.Name || COALESCE(' ' || bp.Name2, '') "
				+ "FROM C_BPartner bp",
				"bp",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) + " "
					+ "AND bp.IsActive = 'Y' "
					+ "AND EXISTS(SELECT 1 FROM HR_Employee hrpe "
					+ "				WHERE hrpe.C_BPartner_ID = bp.C_BPartner_ID "
					+ "				AND hrpe.IsActive = 'Y'";
		
		if (process.getHR_Payroll_ID() != 0
				&& process.getHR_Payroll_ID() != 0
				&& !payroll.isIgnoreDefaultPayroll()){
			sql += " AND (hrpe.HR_Payroll_ID =" +process.getHR_Payroll_ID()+ " OR hrpe.HR_Payroll_ID is NULL)";
			if (process.getHR_Department_ID() > 0)
 				sql += " AND (hrpe.HR_Department_ID =" +process.getHR_Department_ID()+" OR hrpe.HR_Department_ID is NULL)";
			if (process.getHR_Job_ID() > 0)
 				sql += " AND (hrpe.HR_Job_ID =" +process.getHR_Job_ID()+" OR hrpe.HR_Job_ID is NULL)" ;
 			if (process.getHR_Employee_ID() > 0)
 				sql += " AND (hrpe.HR_Employee_ID =" + process.getHR_Employee_ID()+" OR hrpe.HR_Employee_ID is NULL)";
		}
		sql += ") ORDER BY 2 ";
		//	Get from DB
		return DB.getKeyNamePairs(sql, true);
	} //getEmployeeValid
	
	/**
	 * Get Concept for Payroll
	 * @param process
	 * @param isFieldProcessNull
	 * @return
	 */
	public KeyNamePair[] getConcept(I_HR_Process process, boolean isFieldProcessNull) {
		if( process == null )
			return null;
		//	Prepare SQL
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT hrpc.HR_Concept_ID, hrpc.Value || ' - ' || hrpc.Name, hrpc.Value "
					+ "FROM HR_Concept hrpc ",
					"hrpc",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) + " "
			+ "AND hrpc.AD_Client_ID = " + process.getAD_Client_ID() + " "
			+ "AND hrpc.IsActive = 'Y' "
			+ "AND hrpc.IsManual = 'Y' "
			+ "AND hrpc.Type != 'E' "
			+ "AND EXISTS(SELECT 1 FROM HR_Attribute a "
			+ "						WHERE a.HR_Concept_ID = hrpc.HR_Concept_ID";
		if (!isFieldProcessNull) { // Process
			if (process.getHR_Payroll_ID() != 0) // Process & Payroll
 				sql = sql + " AND (a.HR_Payroll_ID = " +process.getHR_Payroll_ID()+ " OR a.HR_Payroll_ID is NULL)";
 			if (process.getHR_Department_ID() != 0 ); // Process & Department
 				sql = sql + " AND (a.HR_Department_ID = " +process.getHR_Department_ID()+" OR a.HR_Department_ID is NULL)";
 			if (process.getHR_Job_ID() != 0 ); // Process & Job
 				sql = sql + " AND (a.HR_Job_ID = " +process.getHR_Job_ID()+" OR a.HR_Job_ID is NULL)" ;
 			if (process.getHR_Department_ID() != 0 ); // Process & Employee
 				sql = sql + " AND (a.HR_Employee_ID = " + process.getHR_Employee_ID()+" OR a.HR_Employee_ID is NULL)";	
		}
		sql = sql +") ORDER BY 2";
		//	Get from DB
		return DB.getKeyNamePairs(sql, true);
	}


	/**
	 *  get record Found to Movement Payroll
	 *  parameter: 
	 */
	public int seekMovement(Timestamp dt) {
		if(conceptId <= 0 )
			return 0;
		int HR_Movement_ID = 0;
		String date = DB.TO_DATE(dt);
		int Process_ID = payrollProcessId;
		int Employee_ID = partnerId;
		int Concept_ID = conceptId;
		//
		if ((Process_ID + Employee_ID + Concept_ID) > 0 ){
			HR_Movement_ID = DB.getSQLValue(null,"SELECT HR_Movement_ID "
					+" FROM HR_Movement WHERE HR_Process_ID = "+Process_ID
					+" AND C_BPartner_ID =" +Employee_ID+ " AND HR_Concept_ID = "+Concept_ID
					+" AND TRUNC(ValidFrom) = TRUNC(" + date +")");
		}
		return HR_Movement_ID;
	} //seekMovement
	
	/**
	 * Get Column Type Lookup
	 * @return
	 */
	public MLookup getColumnTypeLookup() {
		MLookupInfo columnTypeLookup = MLookupFactory.getLookup_List(
				Env.getLanguage(Env.getCtx()), MHRConcept.COLUMNTYPE_AD_Reference_ID);
		return new MLookup(columnTypeLookup, 0);
	}
	
	/**
	 * Get Reference Concept
	 * @param referenceId
	 * @return
	 * @return ArrayList<ValueNamePair>
	 */
	public ArrayList<ValueNamePair> getConceptReference(int referenceId) {
		ArrayList<ValueNamePair> data = new ArrayList<ValueNamePair>();

		//	Get Lookup Info
		MLookupInfo infoLookup = MLookupFactory.getLookup_List(Env.getLanguage(Env.getCtx()), referenceId);
		//	Valid Lookup
		if(infoLookup == null)
			return data;
		//	Get SQL
		String sql = infoLookup.Query;
		//	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			ValueNamePair vp = null;
			data.add(new ValueNamePair("",""));
			while (rs.next()) {
				vp = new ValueNamePair(rs.getString(2), rs.getString(3));
				data.add(vp);
			}
		} catch (SQLException e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs);
			DB.close(pstmt);
		}
		return data;
	}

	protected Integer getConceptId() {
		return conceptId;
	}

	protected Integer getPartnerId() {
		return partnerId;
	}

	protected I_HR_Process getPayrollProcess() {
		return payrollProcess;
	}

	protected BigDecimal getQuantity() {
		return quantity;
	}

	protected BigDecimal getAmount() {
		return amount;
	}

	protected String getText() {
		return text;
	}

	protected String getDescription() {
		return description;
	}

	protected Timestamp getValidFrom() {
		return validFrom;
	}

	protected Timestamp getValidTo() {
		return validTo;
	}
	
	protected Timestamp getServiceDate() {
		return serviceDate;
	}
	
	/**
	 * Save movements
	 * @return
	 */
	public MHRMovement saveMovement() {
		MHRConcept concept = MHRConcept.get(Env.getCtx(), getConceptId());
		MHRMovement movement = new MHRMovement(Env.getCtx(), movementId, null);
		I_HR_Period payrollPeriod = getPayrollProcess().getHR_Period();
		movement.setSeqNo(concept.getSeqNo());
		Optional.ofNullable(getDescription()).ifPresent(description -> movement.setDescription((description.toString())));
		movement.setHR_Process_ID(getPayrollProcess().getHR_Process_ID());
		Optional.ofNullable(payrollPeriod).ifPresent(period -> movement.setPeriodNo(period.getPeriodNo()));
		movement.setC_BPartner_ID(getPartnerId());
		movement.setHR_Concept_ID(getConceptId());
		movement.setHR_Concept_Category_ID(concept.getHR_Concept_Category_ID());
		movement.setColumnType(concept.getColumnType());
		Optional.ofNullable(getQuantity()).ifPresent(qty -> movement.setQty((BigDecimal) qty));
		Optional.ofNullable(getAmount()).ifPresent(amount -> movement.setAmount((BigDecimal) amount));
		Optional.ofNullable(getText()).ifPresent(msg -> movement.setTextMsg((String) msg.toString()));
		Optional.ofNullable(getServiceDate()).ifPresent(date -> movement.setServiceDate((Timestamp) date));
		movement.setValidFrom(getValidFrom());
		movement.setValidTo(getValidTo());
		MHREmployee employee = MHREmployee.getActiveEmployee(Env.getCtx(), movement.getC_BPartner_ID(), null);
		if (employee != null) {
			MHRPayroll payroll = MHRPayroll.getById(Env.getCtx(), payrollProcess.getHR_Payroll_ID());
			movement.setAD_Org_ID(employee.getAD_Org_ID());
			movement.setHR_Department_ID(employee.getHR_Department_ID());
			movement.setHR_Job_ID(employee.getHR_Job_ID());
			movement.setHR_SkillType_ID(employee.getHR_SkillType_ID());
			movement.setC_Activity_ID(employee.getC_Activity_ID() > 0 ? employee.getC_Activity_ID() : employee.getHR_Department().getC_Activity_ID());
			movement.setHR_Payroll_ID(payrollProcess.getHR_Payroll_ID());
			movement.setHR_Contract_ID(payroll.getHR_Contract_ID());
			movement.setHR_Employee_ID(employee.getHR_Employee_ID());
			movement.setHR_EmployeeType_ID(employee.getHR_EmployeeType_ID());
		}
		movement.setIsManual(true);
		movement.saveEx();
		// check if user saved an empty record and delete it
		if ((movement.getAmount() == null || Env.ZERO.compareTo(movement.getAmount()) == 0)
				&& (movement.getQty() == null || Env.ZERO.compareTo(movement.getQty()) == 0)
				&& (movement.getServiceDate() == null)
				&& (movement.getTextMsg() == null || movement.getTextMsg().trim().length() == 0)) {
			movement.deleteEx(false);
		}
		//	Duplicate movement when is saved on first
		movementId = movement.getHR_Movement_ID();
		return movement;
	}

}   //  VHRActionNotice
