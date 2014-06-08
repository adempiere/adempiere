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
package org.eevolution.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.I_AD_Ref_List;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.I_HR_Concept;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRProcess;

/**
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  @author alberto.juarez@e-evolution.com, www.e-evolution.com
 */
public class HRActionNotice
{

	
	/**	Logger			*/
	protected static CLogger log = CLogger.getCLogger(HRActionNotice.class);
	//
	/**	Window No			*/
	public int         	m_WindowNo = 0;

	public static  MHRProcess m_process = null;
	public static int m_HR_Process_ID = 0;
	public static int m_HR_Payroll_ID = 0;
	public static int m_HR_Concept_ID = 0;
	public static int m_C_BPartner_ID = 0;
	public static int sHR_Movement_ID = 0; // // initial not exist record in Movement to actual date

	public Timestamp m_dateStart = null;
	public static Timestamp m_dateEnd = null;
	public int m_qty = 0;
	public BigDecimal m_amount = Env.ZERO;
	public String m_text = null;
	public Timestamp m_date = null;


	public void dynInit() throws Exception
	{
		log.info("HRActionNotice");
	}

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
		miniTable.addColumn("Text");				// 8
		//miniTable.addColumn("AD_Rule_ID");			// 9
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
		miniTable.setColumnClass(8, String.class, true, Msg.translate(Env.getCtx(), "Text"));		
		//miniTable.setColumnClass(9, String.class, true, Msg.translate(Env.getCtx(), "AD_Rule_ID"));
		miniTable.setColumnClass(9, String.class, true, Msg.translate(Env.getCtx(), "Description"));
		//
		miniTable.autoSize();
	}
	
	
	/**
	 *  Query Info
	 */
	public static void executeQuery(Properties ctx ,IMiniTable miniTable)
	{	
		StringBuffer sqlQuery = new StringBuffer("SELECT DISTINCT o.Name,hp.Name,"   // AD_Org_ID, HR_Process_ID -- 1,2
				+ " bp.Name,hc.Name,hm.ValidFrom,"		// HR_Employee_ID,HR_Concept_ID,ValidFrom,ColumnType -- 3,4,5
				+ "("+getSQL_ColumnType(ctx, "hc.ColumnType")+") AS ColumnType,"	// 6 ColumnType(Reference Name)
				+ " hm.Qty,hm.Amount,hm.ServiceDate,hm.TextMsg,er.Name,hm.Description "	// Qty,Amount,ServiceDate,Text,AD_Rule_ID,Description -- 7,8,9,10,11,12
				+ " , HR_Movement_id, hm.AD_Org_ID,hp.HR_Process_ID,hm.HR_Concept_ID "  // to make the distinct useful
				+ " FROM HR_Movement hm "
				+ " INNER JOIN AD_Org o ON (hm.AD_Org_ID=o.AD_Org_ID)"
				+ " INNER JOIN HR_Process hp ON (hm.HR_Process_ID=hp.HR_Process_ID)"
				+ " INNER JOIN C_BPartner bp ON (hm.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN HR_Employee e ON (e.C_BPartner_ID=bp.C_BPartner_ID)"
				+ " INNER JOIN HR_Concept hc ON (hm.HR_Concept_ID=hc.HR_Concept_ID)"
				+ " LEFT OUTER JOIN AD_Rule er ON (hm.AD_Rule_ID=er.AD_Rule_ID)"
				+ " WHERE hm.Processed='N' AND hp.HR_Process_ID = " +m_HR_Process_ID
				//+ " AND IsStatic = 'Y' " // Just apply incidences [add 30Dic2006 to see everything]
				+ " AND hm.C_BPartner_ID = " + m_C_BPartner_ID
				//	+ " AND (Qty > 0 OR Amount > 0 OR hm.TextMsg IS NOT NULL OR ServiceDate IS NOT NULL) "
				);
		//if (fieldValidFrom.getValue() != null ) {
		//	sqlQuery.append(" AND " +DB.TO_DATE(m_dateStart)+" >= hm.ValidFrom  AND "+DB.TO_DATE(m_dateEnd)+" <=  hm.ValidTo ");
		//}
		sqlQuery.append(" ORDER BY hm.AD_Org_ID,hp.HR_Process_ID,bp.Name,hm.ValidFrom,hm.HR_Concept_ID");
		//  reset table
		int row = 0;
		miniTable.setRowCount(row);
		//  Execute
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sqlQuery.toString(), null);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//  extend table
				miniTable.setRowCount(row+1);
				//  set values
				miniTable.setColumnClass(0, IDColumn.class, false, " ");
				miniTable.setValueAt(rs.getString(1), row, 1);		// AD_Org_ID
				miniTable.setValueAt(rs.getString(4), row, 2);		// HR_Concept_ID
				miniTable.setValueAt(rs.getTimestamp(5), row, 3);	// ValidFrom
				miniTable.setValueAt(rs.getString(6), row, 4);		// ColumnType
				miniTable.setValueAt(rs.getObject(7) != null ? rs.getBigDecimal(7) : Env.ZERO, row, 5);	// Qty
				miniTable.setValueAt(rs.getObject(8) != null ? rs.getBigDecimal(8) : Env.ZERO, row, 6);	// Amount
				miniTable.setValueAt(rs.getTimestamp(9), row, 7);	// ServiceDate
				miniTable.setValueAt(rs.getString(10), row, 8);		// TextMsg
				//miniTable.setValueAt(rs.getString(11), row, 9);		// AD_Rule_ID
				miniTable.setValueAt(rs.getString(12), row, 9);	// Description
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
	public static KeyNamePair[] getProcess()
	{
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT hrp.HR_Process_ID,hrp.DocumentNo ||'-'|| hrp.Name,hrp.DocumentNo,hrp.Name FROM HR_Process hrp",
				"hrp",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) + " AND hrp.IsActive = 'Y' ";
		sql += " ORDER BY hrp.DocumentNo, hrp.Name";

		return DB.getKeyNamePairs(sql, true);
	} //getProcess



	/**
	 *  get Employee 
	 *  to Valid  Payroll-Department-Employee of Process Actual 
	 *  parameter: MHRProcess
	 */
	public static List<KeyNamePair> getEmployeeValid(MHRProcess process)
	{
		List<KeyNamePair> list = new ArrayList<KeyNamePair>();
		if (process == null)
			return list;

		
		KeyNamePair pp = new KeyNamePair(0, "");
		list.add(pp);
		String sql = MRole.getDefault().addAccessSQL(
				"SELECT DISTINCT bp.C_BPartner_ID,bp.Name FROM HR_Employee hrpe INNER JOIN C_BPartner bp ON(bp.C_BPartner_ID=hrpe.C_BPartner_ID)",
				"hrpe",MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO) + " AND hrpe.IsActive = 'Y' ";
		if ( process.getHR_Payroll_ID() != 0){
			sql += " AND (hrpe.HR_Payroll_ID =" +process.getHR_Payroll_ID()+ " OR hrpe.HR_Payroll_ID is NULL)" ;
			if ( process.getHR_Department_ID() > 0 )
 				sql += " AND (hrpe.HR_Department_ID =" +process.getHR_Department_ID()+" OR hrpe.HR_Department_ID is NULL)" ;
 			if ( process.getHR_Employee_ID() > 0 )
 				sql += " AND (hrpe.HR_Employee_ID =" + process.getHR_Employee_ID()+" OR hrpe.HR_Employee_ID is NULL)" ;
		}
		sql += " ORDER BY bp.Name ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				list.add(pp);
			}
			return list;
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return list;
		}
		finally
		{
			DB.close(rs, pstmt);
		    rs = null; pstmt = null;
		}
		
	} //getEmployeeValid
	
	

	

	public ArrayList<ValueNamePair> getConcept(MHRProcess process, boolean isFieldProcessNull)
	{
		if( process == null )
			return null;

		ArrayList<ValueNamePair> data = new ArrayList<ValueNamePair>();

		String sql = "SELECT DISTINCT hrpc.HR_Concept_ID,hrpc.Name,hrpc.Value "
			+ " FROM HR_Concept hrpc "
			+ " INNER JOIN HR_Attribute hrpa ON (hrpa.HR_Concept_ID=hrpc.HR_Concept_ID)"
			+ " WHERE hrpc.AD_Client_ID = " +Env.getAD_Client_ID(Env.getCtx())
			+ " AND hrpc.IsActive = 'Y' AND hrpc.IsManual = 'Y' AND hrpc.Type != 'E'"
			+ " AND (hrpa.HR_Payroll_ID = " +m_HR_Payroll_ID+ " OR hrpa.HR_Payroll_ID IS NULL)";
		if (!isFieldProcessNull)    
		{ // Process
			if (process.getHR_Payroll_ID() != 0) // Process & Payroll
 				sql = sql + " AND (hrpa.HR_Payroll_ID = " +process.getHR_Payroll_ID()+ " OR hrpa.HR_Payroll_ID is NULL)" ;
 			if (process.getHR_Department_ID() != 0 ); // Process & Department
 				sql = sql + " AND (hrpa.HR_Department_ID = " +process.getHR_Department_ID()+" OR hrpa.HR_Department_ID is NULL)" ;
 			if (process.getHR_Department_ID() != 0 ); // Process & Employee
 				sql = sql + " AND (hrpa.HR_Employee_ID = " + process.getHR_Employee_ID()+" OR hrpa.HR_Employee_ID is NULL)" ;	
		}
		sql = sql +" ORDER BY hrpc.Name";
		
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			//pstmt.setInt(1, bi.C_BankAccount_ID);
			ResultSet rs = pstmt.executeQuery();
			ValueNamePair vp = null;
			data.add(new ValueNamePair("",""));
			while (rs.next())
			{
				vp = new ValueNamePair(rs.getString(1), rs.getString(2));   //  returns also not active
				data.add(vp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		return data;
	}


	/**
	 *  get record Found to Movement Payroll
	 *  parameter: 
	 */
	public int seekMovement(Timestamp dt)
	{
		if(m_HR_Concept_ID <= 0 )
			return 0;
		int HR_Movement_ID = 0;
		String date = DB.TO_DATE(dt);
		int Process_ID = m_HR_Process_ID;
		int Employee_ID = m_C_BPartner_ID;
		int Concept_ID = m_HR_Concept_ID;
		
		MHRConcept concept = MHRConcept.get(Env.getCtx(),Concept_ID);
		//

		if ( (Process_ID+Employee_ID+Concept_ID) > 0 ){
			HR_Movement_ID = DB.getSQLValue(null,"SELECT HR_Movement_ID "
					+" FROM HR_Movement WHERE HR_Process_ID = "+Process_ID
					+" AND C_BPartner_ID =" +Employee_ID+ " AND HR_Concept_ID = "+Concept_ID
					+" AND TRUNC(ValidFrom) = TRUNC(" + date +")");
		}
		return HR_Movement_ID;
	} //seekMovement
	
	
	/**
	 * Get SQL Code of ColumnType for given sqlValue
	 * @param sqlValue
	 * @return sql select code
	 */
	public static  String getSQL_ColumnType(Properties ctx,String sqlValue) {
		int columnType_Ref_ID = MTable.get(ctx, I_HR_Concept.Table_ID)
		.getColumn(I_HR_Concept.COLUMNNAME_ColumnType)
		.getAD_Reference_Value_ID();
		String sql;
		if (Env.isBaseLanguage(Env.getCtx(), I_AD_Ref_List.Table_Name)) {
			sql = "SELECT zz.Name FROM AD_Ref_List zz WHERE zz.AD_Reference_ID="+columnType_Ref_ID; 
		}
		else {
			sql = "SELECT zz.Name FROM AD_Ref_List zz, AD_Ref_List_Trl zzt"
				+" WHERE zz.AD_Reference_ID="+columnType_Ref_ID
				+" AND zzt.AD_Ref_List_ID=zz.AD_Ref_List_ID"
				+" AND zzt.AD_Language="+DB.TO_STRING(Env.getAD_Language(Env.getCtx()));
		}
		sql += " AND zz.Value = "+sqlValue;
		return sql;
	} // getSQL_ColumnType

}   //  VHRActionNotice
