/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
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
package org.compiere.dbPort;

/**
 * Unit testing for Convert_PostgreSQL. Not using junit now as I do not want to
 * add more dependency to the project at this moment.
 * @author Low Heng Sin
 * @version 20061225
 */
public final class Convert_PostgreSQLTest {
	public Convert_PostgreSQLTest() {}
	
	public void doTest() {
		Convert_PostgreSQL convert = new Convert_PostgreSQL();
		
		//financial report, bug [ 1580231 ]
		String sql = "UPDATE t_report"
				+ " SET (NAME, description) = (SELECT VALUE, NAME "
				+ " FROM c_elementvalue"
				+ " WHERE c_elementvalue_id = t_report.record_id) "
				+ " WHERE record_id <> 0 " + " AND ad_pinstance_id = 1000024 "
				+ " AND pa_reportline_id = 101 " + " AND fact_acct_id = 0 ";
		
		String[] r = convert.convert(sql);
		verify(sql, r, "UPDATE t_report SET NAME=c_elementvalue.VALUE,description=c_elementvalue.NAME FROM c_elementvalue WHERE c_elementvalue.c_elementvalue_id = t_report.record_id AND t_report.record_id <> 0 AND t_report.ad_pinstance_id = 1000024 AND t_report.pa_reportline_id = 101 AND t_report.fact_acct_id = 0");
		
		//from victor's test
		
		//test limit
		sql = "UPDATE I_Order SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Warehouse w WHERE ROWNUM=1 AND I_Order.AD_Client_ID=w.AD_Client_ID AND I_Order.AD_Org_ID=w.AD_Org_ID) WHERE M_Warehouse_ID IS NULL AND I_IsImported<>'Y' AND AD_Client_ID=11";
		r = convert.convert(sql);
		verify(sql, r, "UPDATE I_Order SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Warehouse w WHERE  I_Order.AD_Client_ID=w.AD_Client_ID AND I_Order.AD_Org_ID=w.AD_Org_ID LIMIT 1 ) WHERE M_Warehouse_ID IS NULL AND I_IsImported<>'Y' AND AD_Client_ID=11");
		
		//test alias and column list update
		sql = "UPDATE I_Order o SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) WHERE C_BPartner_ID IS NULL AND ContactName IS NOT NULL AND EXISTS (SELECT Name FROM AD_User u WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL GROUP BY Name HAVING COUNT(*)=1) AND I_IsImported<>'Y' AND AD_Client_ID=11";
		r = convert.convert(sql);
		verify(sql, r, "UPDATE I_Order SET C_BPartner_ID=u.C_BPartner_ID,AD_User_ID=u.AD_User_ID FROM AD_User u WHERE I_Order.ContactName=u.Name AND I_Order.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL AND I_Order.C_BPartner_ID IS NULL AND I_Order.ContactName IS NOT NULL AND EXISTS (SELECT Name FROM AD_User u WHERE I_Order.ContactName=u.Name AND I_Order.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL GROUP BY Name HAVING COUNT(*)=1) AND I_Order.I_IsImported<>'Y' AND I_Order.AD_Client_ID=11");

		//from bug [ 1580226 ] - test alias and trunc
		sql = "INSERT INTO Fact_Acct_Balance ab "
		+ "(AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, DateAcct,"
		+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
		+ "	C_Project_ID, AD_OrgTrx_ID,	C_SalesRegion_ID,C_Activity_ID,"
		+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID,"
		+ " AmtAcctDr, AmtAcctCr, Qty) "
		+ "SELECT AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, TRUNC(DateAcct),"
		+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
		+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID,C_Activity_ID,"
		+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID,"
		+ " COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0) "
		+ "FROM Fact_Acct a "
		+ "WHERE C_AcctSchema_ID=0" 
		+ " GROUP BY AD_Client_ID,AD_Org_ID, C_AcctSchema_ID, TRUNC(DateAcct),"
		+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
		+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID, C_Activity_ID,"
		+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID";
		String expected = "INSERT INTO Fact_Acct_Balance "
			+ "(AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, DateAcct,"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID,C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID,"
			+ " AmtAcctDr, AmtAcctCr, Qty) "
			+ "SELECT AD_Client_ID, AD_Org_ID, C_AcctSchema_ID, DATE_Trunc('day',DateAcct),"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID,C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID,"
			+ " COALESCE(SUM(AmtAcctDr),0), COALESCE(SUM(AmtAcctCr),0), COALESCE(SUM(Qty),0) "
			+ "FROM Fact_Acct a "
			+ "WHERE C_AcctSchema_ID=0" 
			+ " GROUP BY AD_Client_ID,AD_Org_ID, C_AcctSchema_ID, DATE_Trunc('day',DateAcct),"
			+ " Account_ID, PostingType, M_Product_ID, C_BPartner_ID,"
			+ " C_Project_ID, AD_OrgTrx_ID, C_SalesRegion_ID, C_Activity_ID,"
			+ " C_Campaign_ID, C_LocTo_ID, C_LocFrom_ID, User1_ID, User2_ID, GL_Budget_ID";
		r = convert.convert(sql);
		verify(sql, r, expected);
		
		//Doc_Invoice
		sql = "UPDATE M_Product_PO po "
		+ "SET PriceLastInv = "
		+ "(SELECT currencyConvert(il.PriceActual,i.C_Currency_ID,po.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID) "
		+ "FROM C_Invoice i, C_InvoiceLine il "
		+ "WHERE i.C_Invoice_ID=il.C_Invoice_ID"
		+ " AND po.M_Product_ID=il.M_Product_ID AND po.C_BPartner_ID=i.C_BPartner_ID"
		+ " AND ROWNUM=1 AND i.C_Invoice_ID=0) "
		+ "WHERE EXISTS (SELECT * "
		+ "FROM C_Invoice i, C_InvoiceLine il "
		+ "WHERE i.C_Invoice_ID=il.C_Invoice_ID"
		+ " AND po.M_Product_ID=il.M_Product_ID AND po.C_BPartner_ID=i.C_BPartner_ID"
		+ " AND i.C_Invoice_ID=0)";
		r = convert.convert(sql);
		verify(sql,r,"UPDATE M_Product_PO SET PriceLastInv = (SELECT currencyConvert(il.PriceActual,i.C_Currency_ID,M_Product_PO.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID) FROM C_Invoice i, C_InvoiceLine il WHERE i.C_Invoice_ID=il.C_Invoice_ID AND M_Product_PO.M_Product_ID=il.M_Product_ID AND M_Product_PO.C_BPartner_ID=i.C_BPartner_ID  AND i.C_Invoice_ID=0 LIMIT 1 ) WHERE EXISTS (SELECT * FROM C_Invoice i, C_InvoiceLine il WHERE i.C_Invoice_ID=il.C_Invoice_ID AND M_Product_PO.M_Product_ID=il.M_Product_ID AND M_Product_PO.C_BPartner_ID=i.C_BPartner_ID AND i.C_Invoice_ID=0)");
		
		//From bug [ 1576358 ] and [ 1577055 ]
		sql = "SELECT TRUNC(TO_DATE('2006-10-13','YYYY-MM-DD'),'Q') FROM DUAL";
		r = convert.convert(sql);
		verify(sql, r, "SELECT DATE_Trunc('quarter',TO_TIMESTAMP('2006-10-13','YYYY-MM-DD'))");
		
		//FinReport, test inner join in subquery
		sql = "UPDATE T_Report r SET (Name,Description)=("
			+ "SELECT e.Name, fa.Description "
			+ "FROM Fact_Acct fa"
			+ " INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID)"
			+ " INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) "
			+ "WHERE r.Fact_Acct_ID=fa.Fact_Acct_ID) "
			+ "WHERE Fact_Acct_ID <> 0 AND AD_PInstance_ID=0";
		r = convert.convert(sql);
		verify(sql, r, "UPDATE T_Report SET Name=e.Name,Description=fa.Description FROM Fact_Acct fa INNER JOIN AD_Table t ON (fa.AD_Table_ID=t.AD_Table_ID) INNER JOIN AD_Element e ON (t.TableName||'_ID'=e.ColumnName) WHERE T_Report.Fact_Acct_ID=fa.Fact_Acct_ID AND T_Report.Fact_Acct_ID <> 0 AND T_Report.AD_PInstance_ID=0");
		
		//MInOutLineMa bug [ 1622302 ] 
		sql = "DELETE FROM M_InOutLineMA ma WHERE EXISTS "
			+ "(SELECT * FROM M_InOutLine l WHERE l.M_InOutLine_ID=ma.M_InOutLine_ID"
			+ " AND M_InOut_ID=0)";
		r = convert.convert(sql);
		verify(sql, r, "DELETE FROM M_InOutLineMA WHERE EXISTS (SELECT * FROM M_InOutLine l WHERE l.M_InOutLine_ID=M_InOutLineMA.M_InOutLine_ID AND M_InOut_ID=0)");
		
		//MLanguage.addTable
		sql = "INSERT INTO " + "AD_Column_Trl"
		+ "(AD_Language,IsTranslated, AD_Client_ID,AD_Org_ID, "
		+ "Createdby,UpdatedBy, "
		+ "AD_Column_ID,Name) "
		+ "SELECT '" + "es_MX" + "','N', AD_Client_ID,AD_Org_ID, "
		+ 100 + "," + 100 + ", "
		+ "AD_Column_ID,Name"
		+ " FROM " + "AD_Column"
		+ " WHERE " + "AD_Column_ID" + " NOT IN (SELECT " + "AD_Column_ID"
			+ " FROM " + "AD_Column_Trl"
			+ " WHERE AD_Language='" + "es_MX" + "')";
		r = convert.convert(sql);
		verify(sql, r, "INSERT INTO AD_Column_Trl(AD_Language,IsTranslated, AD_Client_ID,AD_Org_ID, Createdby,UpdatedBy, AD_Column_ID,Name) SELECT 'es_MX','N', AD_Client_ID,AD_Org_ID, 100,100, AD_Column_ID,Name FROM AD_Column WHERE AD_Column_ID NOT IN (SELECT AD_Column_ID FROM AD_Column_Trl WHERE AD_Language='es_MX')");
		
		//https://sourceforge.net/forum/message.php?msg_id=4083672
		sql=" 	UPDATE AD_COLUMN c"
			+" 		SET	(ColumnName, Name, Description, Help) =" 
			+" 	           (SELECT ColumnName, Name, Description, Help" 
			+" 	            FROM AD_ELEMENT e WHERE c.AD_Element_ID=e.AD_Element_ID),"
			+" 			Updated = SYSDATE"
			+" 	WHERE EXISTS (SELECT 1 FROM AD_ELEMENT e "
			+" 				WHERE c.AD_Element_ID=e.AD_Element_ID"
			+" 				  AND (c.ColumnName <> e.ColumnName OR c.Name <> e.Name "
			+" 					OR NVL(c.Description,' ') <> NVL(e.Description,' ') OR NVL(c.Help,' ') <> NVL(e.Help,' ')))";
		r = convert.convert(sql);
		verify(sql, r, "UPDATE AD_COLUMN SET ColumnName=e.ColumnName,Name=e.Name,Description=e.Description,Help=e.Help, Updated = CURRENT_TIMESTAMP FROM AD_ELEMENT e WHERE AD_COLUMN.AD_Element_ID=e.AD_Element_ID AND EXISTS (SELECT 1 FROM AD_ELEMENT e WHERE AD_COLUMN.AD_Element_ID=e.AD_Element_ID AND (AD_COLUMN.ColumnName <> e.ColumnName OR AD_COLUMN.Name <> e.Name OR COALESCE(AD_COLUMN.Description,' ') <> COALESCE(e.Description,' ') OR COALESCE(AD_COLUMN.Help,' ') <> COALESCE(e.Help,' ')))");
		
		sql="UPDATE AD_WF_NODE n"
			+" SET (Name, Description, Help) = (SELECT f.Name, f.Description, f.Help" 
			+" 		FROM AD_PROCESS f"
			+" 		WHERE f.AD_Process_ID=n.AD_Process_ID)"
			+" WHERE n.IsCentrallyMaintained = 'Y'"
			+" AND EXISTS  (SELECT 1 FROM AD_PROCESS f"
			+" 		WHERE f.AD_Process_ID=n.AD_Process_ID"
			+" 		  AND (f.Name <> n.Name OR NVL(f.Description,' ') <> NVL(n.Description,' ') OR NVL(f.Help,' ') <> NVL(n.Help,' ')))";
		r = convert.convert(sql);
		verify(sql, r, "UPDATE AD_WF_NODE SET Name=f.Name,Description=f.Description,Help=f.Help FROM AD_PROCESS f WHERE f.AD_Process_ID=AD_WF_NODE.AD_Process_ID AND AD_WF_NODE.IsCentrallyMaintained = 'Y' AND EXISTS (SELECT 1 FROM AD_PROCESS f WHERE f.AD_Process_ID=AD_WF_NODE.AD_Process_ID AND (f.Name <> AD_WF_NODE.Name OR COALESCE(f.Description,' ') <> COALESCE(AD_WF_NODE.Description,' ') OR COALESCE(f.Help,' ') <> COALESCE(AD_WF_NODE.Help,' ')))");
	}
	
	private void verify(String original, String[] converted, String expected) {
		if (converted == null || converted.length != 1) {
			System.out.println("Convert test failed for: ");
			System.out.println(original);
			System.out.println("Reason: Null or empty result.");
		} else if (!(converted[0].equals(expected))) {
			System.out.println("Convert test failed for: ");
			System.out.println(original);
			System.out.println("Result: ");
			System.out.println(converted[0]);
			System.out.println("Expected: ");
			System.out.println(expected);
			System.out.println("Reason: Actual result does not match with expected result.");
		} else {
			System.out.println("Pass.");
		}
	}
	
	public static void main(String[] args) {
		new Convert_PostgreSQLTest().doTest();	
	}
	
}
