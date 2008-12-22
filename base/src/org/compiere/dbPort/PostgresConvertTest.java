/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.dbPort;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Login;


/**
 *	Order Test Example
 *	
 *  @author Jorg Janke
 *  @version $Id: PostgresConvertTest.java,v 1.2 2005/03/11 20:30:16 jjanke Exp $
 */
public class PostgresConvertTest
{
	/**
	 * 	PostgresConvertTest
	 */
	public PostgresConvertTest () 
	{
	}	//	PostgresConvertTest
	
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (PostgresConvertTest.class);
	
	/**
	 * 	Test
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		Adempiere.startup(true);
		CLogMgt.setLoggerLevel(Level.INFO, null);
		CLogMgt.setLevel(Level.INFO);
		//
		Ini.setProperty(Ini.P_UID,"SuperUser");
		Ini.setProperty(Ini.P_PWD,"System");
		Ini.setProperty(Ini.P_ROLE,"GardenWorld Admin");
		Ini.setProperty(Ini.P_CLIENT, "GardenWorld");
		Ini.setProperty(Ini.P_ORG,"HQ");
		Ini.setProperty(Ini.P_WAREHOUSE,"HQ Warehouse");
		Ini.setProperty(Ini.P_LANGUAGE,"English");
		Login login = new Login(Env.getCtx());
		if (!login.batchLogin(null))
			System.exit(1);
		
		log.info("start test");
		StringBuffer sql = new StringBuffer (
				"SELECT 'un DATE en una constante'," +
				" 'ropa de cache para damas' FROM DUAL");
        try
        {
        	PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
        	ResultSet rs = pstmt.executeQuery();
        }
        catch(SQLException e) 
        {
        	log.log(Level.SEVERE, sql.toString(), e);
        }
		
                
               
                
//		 From org.compiere.acct.Doc_Order
		//  update Product PO info
		//  should only be once, but here for every AcctSchema
		//  ignores multiple lines with same product - just uses first
		/*sql = new StringBuffer (
			"UPDATE M_Product_PO po "
			+ "SET PriceLastPO = (SELECT currencyConvert(ol.PriceActual,ol.C_Currency_ID,po.C_Currency_ID,o.DateOrdered,o.C_ConversionType_ID,o.AD_Client_ID,o.AD_Org_ID) "
			+ "FROM C_Order o, C_OrderLine ol "
			+ "WHERE o.C_Order_ID=ol.C_Order_ID"
			+ " AND po.M_Product_ID=ol.M_Product_ID AND po.C_BPartner_ID=o.C_BPartner_ID"
			+ " AND ROWNUM=1 AND o.C_Order_ID=").append(getRecord_ID()).append(") ")
			.append("WHERE EXISTS (SELECT * "
			+ "FROM C_Order o, C_OrderLine ol "
			+ "WHERE o.C_Order_ID=ol.C_Order_ID"
			+ " AND po.M_Product_ID=ol.M_Product_ID AND po.C_BPartner_ID=o.C_BPartner_ID"
			+ " AND o.C_Order_ID=").append(getRecord_ID()).append(")");
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_PO - Updated=" + no);	
                */
                
                //sql = new StringBuffer ("DELETE T_MRP WHERE AD_Client_ID<>0");
		//no = DB.executeUpdate(sql.toString(), getTrxName());
                
		/*
//		 From org.compiere.acct.Doc_Invoice
		//  update Product PO info
		//  should only be once, but here for every AcctSchema
		//  ignores multiple lines with same product - just uses first
		StringBuffer sql = new StringBuffer (
			"UPDATE M_Product_PO po "
			+ "SET PriceLastInv = "
			//	select
			+ "(SELECT currencyConvert(il.PriceActual,i.C_Currency_ID,po.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID) "
			+ "FROM C_Invoice i, C_InvoiceLine il "
			+ "WHERE i.C_Invoice_ID=il.C_Invoice_ID"
			+ " AND po.M_Product_ID=il.M_Product_ID AND po.C_BPartner_ID=i.C_BPartner_ID"
			+ " AND ROWNUM=1 AND i.C_Invoice_ID=").append(getRecord_ID()).append(") ")
			//	update
			.append("WHERE EXISTS (SELECT * "
			+ "FROM C_Invoice i, C_InvoiceLine il "
			+ "WHERE i.C_Invoice_ID=il.C_Invoice_ID"
			+ " AND po.M_Product_ID=il.M_Product_ID AND po.C_BPartner_ID=i.C_BPartner_ID"
			+ " AND i.C_Invoice_ID=").append(getRecord_ID()).append(")");
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_PO - Updated=" + no);
		*/
		sql = new StringBuffer ("UPDATE I_Order SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Warehouse w WHERE ROWNUM=1 AND I_Order.AD_Client_ID=w.AD_Client_ID AND I_Order.AD_Org_ID=w.AD_Org_ID) WHERE M_Warehouse_ID IS NULL AND I_IsImported<>'Y' AND AD_Client_ID=11");
		int no = DB.executeUpdate(sql.toString(), getTrxName());
                /*sql = new StringBuffer ("UPDATE I_Order o SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, ' WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0 OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N'))) AND I_IsImported<>'Y'");
		no = DB.executeUpdate(sql.toString(), getTrxName());
                sql = new StringBuffer ("UPDATE I_Order o SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName AND d.DocBaseType='POO' AND o.AD_Client_ID=d.AD_Client_ID) WHERE C_DocType_ID IS NULL AND IsSOTrx='N' AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'");                 
		no = DB.executeUpdate(sql.toString(), getTrxName());*/
                sql = new StringBuffer ("UPDATE I_Order o SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) WHERE C_BPartner_ID IS NULL AND ContactName IS NOT NULL AND EXISTS (SELECT Name FROM AD_User u WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL GROUP BY Name HAVING COUNT(*)=1) AND I_IsImported<>'Y' AND AD_Client_ID=11");                 
		no = DB.executeUpdate(sql.toString(), getTrxName());
                
                
                //sql = new StringBuffer ("SELECT M_PriceList_ID, pl.Name, pl.Description, pl.IsTaxIncluded, c.ISO_Code, c.CurSymbol, cc.AD_Language FROM M_PriceList pl INNER JOIN C_Currency c ON (pl.C_Currency_ID=c.C_Currency_ID) LEFT OUTER JOIN C_Country cc ON (c.C_Currency_ID=cc.C_Currency_ID AND ROWNUM=1) WHERE pl.IsActive='Y' AND pl.AD_Client_ID=? AND pl.M_PriceList_ID=100000");
		//no = DB.executeUpdate(sql.toString(), getTrxName());
                
              






                

                
		log.info("final test");

	}	//	main

	public static int getRecord_ID()
	{
		return 10000000;
	}	//	getRecord_ID

	protected static String getTrxName()
	{
		return "Test";
	}	//	getTrxName
	
}	//	PostgresConvertTest