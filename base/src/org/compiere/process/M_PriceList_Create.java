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
 * Contributor(s): Layda Salas (globalqss)
 * Contributor(s): Carlos Ruiz (globalqss)
 *****************************************************************************/

package org.compiere.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.util.AdempiereSystemError;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.CPreparedStatement;
import org.compiere.util.DB;
import org.compiere.util.ValueNamePair;

/**
 * Create PriceList by copying purchase prices (M_Product_PO) 
 *		and applying product category discounts (M_CategoryDiscount)
 * 
 * @author Layda Salas (globalqss)
 * @version $Id: M_PriceList_Create,v 1.0 2005/10/09 22:19:00
 *          globalqss Exp $
 * @author Carlos Ruiz (globalqss)
 *         Make T_Selection tables permanent         
 */
public class M_PriceList_Create extends SvrProcess {

	/** The Record */
	private int p_PriceList_Version_ID = 0;

	private String p_DeleteOld;
	
	private int m_AD_PInstance_ID = 0; 

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DeleteOld"))
				p_DeleteOld = (String) para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_PriceList_Version_ID = getRecord_ID();
		m_AD_PInstance_ID = getAD_PInstance_ID();
	} //*prepare*/

	/**
	 * Process
	 * 
	 * @return message
	 * @throws Exception
	 */

	protected String doIt() throws Exception {
		String sql;
		String sqlupd;
		String sqldel;
		String sqlins;
		int cntu = 0;
		int cntd = 0;
		int cnti = 0;
		int totu = 0;
		int toti = 0;
		int totd = 0;
		int V_temp;
		int v_NextNo = 0;
		String Message = " ";
		//
		//Checking Prerequisites
		//PO Prices must exists
		//
		sqlupd = "UPDATE M_Product_PO " + " SET	PriceList = 0  "
				+ " WHERE	PriceList IS NULL ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update The PriceList to zero of M_Product_PO WHERE	PriceList IS NULL",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		sqlupd = "UPDATE M_Product_PO " + " SET	PriceLastPO = 0  "
				+ " WHERE	PriceLastPO IS NULL ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update  The PriceListPO to zero of  M_Product_PO WHERE	PriceLastPO IS NULL",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		sqlupd = "UPDATE M_Product_PO "
				+ " SET	    PricePO = PriceLastPO  "
				+ " WHERE	(PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0 ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update  The PricePO to PriceLastPO of  M_Product_PO WHERE	(PricePO IS NULL OR PricePO = 0) AND PriceLastPO <> 0 ",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		sqlupd = "UPDATE M_Product_PO " + " SET	    PricePO = 0  "
				+ " WHERE	PricePO IS NULL ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError(
					"Update  The PricePO to Zero of  M_Product_PO WHERE	PricePO IS NULL",
					sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);
		//
		//  Set default current vendor
		//
		sqlupd = "UPDATE M_Product_PO " + " SET	     IsCurrentVendor = 'Y'  "
				+ " WHERE	 IsCurrentVendor = 'N' " + " AND NOT   EXISTS "
				+ " (SELECT   pp.M_Product_ID " + "  FROM     M_Product_PO pp "
				+ "  WHERE    pp.M_Product_ID = M_Product_PO.M_Product_ID"
				+ "  GROUP BY pp.M_Product_ID HAVING COUNT(*) > 1) ";

		cntu = DB.executeUpdate(sqlupd, get_TrxName());
		if (cntu == -1)
			raiseError("Update  IsCurrentVendor to Y of  M_Product_PO ", sqlupd);
		totu += cntu;
		log.fine("Updated " + cntu);

		// let the commit for SvrProcess
		// DB.commit(true, get_TrxName());

		//
		//	Make sure that we have only one active product
		//
		sql = "SELECT DISTINCT M_Product_ID FROM M_Product_PO po "
				+ " WHERE	 IsCurrentVendor='Y' AND IsActive='Y' "
				+ "   AND    EXISTS (SELECT   M_Product_ID "
				                   + " FROM     M_Product_PO x  "
				                   + " WHERE    x.M_Product_ID=po.M_Product_ID "
				                   + "   AND    IsCurrentVendor='Y' AND IsActive='Y' "
				                   + " GROUP BY M_Product_ID " + " HAVING COUNT(*) > 1 ) ";

		PreparedStatement Cur_Duplicates = null;
		Cur_Duplicates = DB.prepareStatement(sql, get_TrxName());
		ResultSet dupl = Cur_Duplicates.executeQuery();
		while (dupl.next()) {
			sql = "SELECT	M_Product_ID " + "        ,C_BPartner_ID "
					+ " FROM	M_Product_PO " + " WHERE	IsCurrentVendor = 'Y'  "
					+ " AND     IsActive        = 'Y' "
					+ " AND	M_Product_ID    = " + dupl.getInt("M_Product_ID")
					+ " ORDER BY PriceList DESC";

			PreparedStatement Cur_Vendors = null;
			Cur_Vendors = DB.prepareStatement(sql, get_TrxName());
			ResultSet Vend = Cur_Vendors.executeQuery();

			//
			//	Leave First
			//
			Vend.next();
			
			while (Vend.next()) {
				sqlupd = "UPDATE M_Product_PO "
						+ " SET	IsCurrentVendor = 'N'  "
						+ " WHERE	M_Product_ID= " + Vend.getInt("M_Product_ID")
						+ " AND     C_BPartner_ID= "
						+ Vend.getInt("C_BPartner_ID");

				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				if (cntu == -1)
					raiseError(
							"Update  IsCurrentVendor to N of  M_Product_PO for a M_Product_ID and C_BPartner_ID ingresed",
							sqlupd);
				totu += cntu;
				log.fine("Updated " + cntu);

			}
			Vend.close();
			Cur_Vendors.close();
			Cur_Vendors = null;

		}
		dupl.close();
		Cur_Duplicates.close();
		Cur_Duplicates = null;

		// DB.commit(true, get_TrxName());

		//
		//	Delete Old Data	
		//
		if (p_DeleteOld.equals("Y")) {
			sqldel = "DELETE M_ProductPrice "
					+ " WHERE	M_PriceList_Version_ID = "
					+ p_PriceList_Version_ID;
			cntd = DB.executeUpdate(sqldel, get_TrxName());
			if (cntd == -1)
				raiseError(" DELETE	M_ProductPrice ", sqldel);
			totd += cntd;
			Message = "@Deleted@=" + cntd + " - ";
			log.fine("Deleted " + cntd);
		}
		//
		// Get PriceList Info
		//
		sql = "SELECT p.C_Currency_ID " + " , c.StdPrecision "
				+ " , v.AD_Client_ID " + " , v.AD_Org_ID " + " , v.UpdatedBy "
				+ " , v.M_DiscountSchema_ID "
				+ " , M_PriceList_Version_Base_ID " + " FROM	M_PriceList p "
				+ "     ,M_PriceList_Version v " + "     ,C_Currency c "
				+ " WHERE	p.M_PriceList_ID = v.M_PriceList_ID "
				+ " AND	    p.C_Currency_ID  = c.C_Currency_ID"
				+ " AND	v.M_PriceList_Version_ID = " + p_PriceList_Version_ID;
		PreparedStatement curgen = null;
		curgen = DB.prepareStatement(sql, get_TrxName());
		ResultSet v = curgen.executeQuery();
		while (v.next()) {
			//
			// For All Discount Lines in Sequence
			//
			sql = "SELECT m_discountschemaline_id"
					+ ",ad_client_id,ad_org_id,isactive,created,createdby,updated,updatedby" 
					+ ",m_discountschema_id,seqno,m_product_category_id,c_bpartner_id,m_product_id"
					+ ",conversiondate,list_base,list_addamt,list_discount,list_rounding,list_minamt"
					+ ",list_maxamt,list_fixed,std_base,std_addamt,std_discount,std_rounding"
					+ ",std_minamt,std_maxamt,std_fixed,limit_base,limit_addamt,limit_discount"
					+ ",limit_rounding,limit_minamt,limit_maxamt,limit_fixed,group1,group2,c_conversiontype_id"
					+ " FROM  M_DiscountSchemaLine"
					+ " WHERE M_DiscountSchema_ID="
					+ v.getInt("M_DiscountSchema_ID")
					+ " AND IsActive='Y' ORDER BY SeqNo";
			PreparedStatement Cur_DiscountLine = null;
			Cur_DiscountLine = DB.prepareStatement(sql, get_TrxName());
			ResultSet dl = Cur_DiscountLine.executeQuery();
			while (dl.next()) {
				//
				//Clear Temporary Table
				//
				sqldel = "DELETE FROM T_Selection WHERE AD_PInstance_ID="+ m_AD_PInstance_ID;
				cntd = DB.executeUpdate(sqldel, get_TrxName());
				if (cntd == -1)
					raiseError(" DELETE	T_Selection ", sqldel);
				totd += cntd;
				log.fine("Deleted " + cntd);
				//
				//Create Selection in temporary table
				//
				V_temp = v.getInt("M_PriceList_Version_Base_ID");
				String dl_Group1 = dl.getString("Group1");
				String dl_Group2 = dl.getString("Group2");
				if (v.wasNull()) {
					//
					//Create Selection from M_Product_PO
					//
					sqlins = "INSERT INTO T_Selection (AD_PInstance_ID, T_Selection_ID) "
							+ " SELECT DISTINCT " + m_AD_PInstance_ID +", po.M_Product_ID "
							+ " FROM M_Product p, M_Product_PO po"
							+ " WHERE p.M_Product_ID=po.M_Product_ID "
							+ " AND	(p.AD_Client_ID=" + v.getInt("AD_Client_ID") + " OR p.AD_Client_ID=0)"
							+ " AND	p.IsActive='Y' AND po.IsActive='Y' AND po.IsCurrentVendor='Y' "
							//
							//Optional Restrictions
							//
							// globalqss - detected bug, JDBC returns zero for null values
							// so we're going to use NULLIF(value, 0)
							+ " AND (NULLIF(" + dl.getInt("M_Product_Category_ID") + ",0) IS NULL"
							+ "   OR p.M_Product_Category_ID IN (" + getSubCategoryWhereClause(dl.getInt("M_Product_Category_ID")) + "))";
							if(dl_Group1 != null)
								sqlins = sqlins + " AND (p.Group1=?)";
							if (dl_Group2 != null)
								sqlins = sqlins + " AND (p.Group2=?)";
							sqlins = sqlins + " AND (NULLIF(" + dl.getInt("C_BPartner_ID") + ",0) IS NULL "
							+ "   OR po.C_BPartner_ID=" + dl.getInt("C_BPartner_ID") + ")"
							+ " AND (NULLIF(" + dl.getInt("M_Product_ID") + ",0) IS NULL "
							+ "   OR p.M_Product_ID=" + dl.getInt("M_Product_ID") + ")";
					
					CPreparedStatement stmt = DB.prepareStatement(sqlins, get_TrxName());
					
					int i = 1;

					if (dl_Group1!=null)
						stmt.setString(i++, dl_Group1);
					if (dl_Group2!=null)
						stmt.setString(i++, dl_Group2);		
					
					cnti = stmt.executeUpdate();
					
					if (cnti == -1)
						raiseError(" INSERT INTO T_Selection ", sqlins);
					toti += cnti;
					log.fine("Inserted " + cnti);

				} else {
					//
					// Create Selection from existing PriceList
					//
					sqlins = "INSERT INTO T_Selection (AD_PInstance_ID, T_Selection_ID)"
							+ " SELECT DISTINCT " + m_AD_PInstance_ID +", p.M_Product_ID"
							+ " FROM M_Product p, M_ProductPrice pp"
							+ " WHERE p.M_Product_ID=pp.M_Product_ID"
							+ " AND pp.M_PriceList_Version_ID = " + v.getInt("M_PriceList_Version_Base_ID")
							+ " AND p.IsActive='Y' AND pp.IsActive='Y'"
							//
							//Optional Restrictions
							//
							+ " AND (NULLIF(" + dl.getInt("M_Product_Category_ID") + ",0) IS NULL"
							+ " OR p.M_Product_Category_ID IN (" + getSubCategoryWhereClause(dl.getInt("M_Product_Category_ID")) + "))";
							if(dl_Group1 != null)
								sqlins = sqlins + " AND (p.Group1=?)";
							if (dl_Group2 != null)
								sqlins = sqlins + " AND (p.Group2=?)";
							sqlins = sqlins + " AND (NULLIF(" + dl.getInt("C_BPartner_ID") + ",0) IS NULL OR EXISTS "
							+ "(SELECT m_product_id,c_bpartner_id,ad_client_id,ad_org_id,isactive"
							+ ",created,createdby,updated,updatedby,iscurrentvendor,c_uom_id"
							+ ",c_currency_id,pricelist,pricepo,priceeffective,pricelastpo"
							+ ",pricelastinv,vendorproductno,upc,vendorcategory,discontinued"
							+ ",discontinuedby,order_min,order_pack,costperorder"
							+ ",deliverytime_promised,deliverytime_actual,qualityrating"
							+ ",royaltyamt,group1,group2"
							+ ",manufacturer FROM M_Product_PO po WHERE po.M_Product_ID=p.M_Product_ID"
							+ " AND po.C_BPartner_ID=" + dl.getInt("C_BPartner_ID") + "))" 
							+ " AND	(NULLIF(" + dl.getInt("M_Product_ID") + ",0) IS NULL "
							+ "   OR p.M_Product_ID=" + dl.getInt("M_Product_ID") + ")";
					
					
					CPreparedStatement stmt = DB.prepareStatement(sqlins, get_TrxName());
					int i = 1;
					
					if (dl_Group1!=null)
						stmt.setString(i++, dl_Group1);
					if (dl_Group2!=null)
						stmt.setString(i++, dl_Group2);		
					
					cnti = stmt.executeUpdate();
					if (cnti == -1)
						raiseError(
								" INSERT INTO T_Selection from existing PriceList",
								sqlins);
					toti += cnti;
					log.fine("Inserted " + cnti);

				}

				Message = Message + "@Selected@=" + cnti;

				//
				//Delete Prices in Selection, so that we can insert
				//
				V_temp = v.getInt("M_PriceList_Version_Base_ID");
				if (v.wasNull() || V_temp != p_PriceList_Version_ID) {

					sqldel = "DELETE M_ProductPrice pp"
							+ " WHERE pp.M_PriceList_Version_ID = "
							+ p_PriceList_Version_ID
							+ " AND EXISTS (SELECT t_selection_id FROM T_Selection s WHERE pp.M_Product_ID=s.T_Selection_ID" 
								+ " AND s.AD_PInstance_ID=" + m_AD_PInstance_ID + ")";

					cntd = DB.executeUpdate(sqldel, get_TrxName());
					if (cntd == -1)
						raiseError(" DELETE	M_ProductPrice ", sqldel);
					totd += cntd;
					Message = Message + ", @Deleted@=" + cntd;
					log.fine("Deleted " + cntd);
				}

				//
				//	Copy (Insert) Prices
				//
				V_temp = v.getInt("M_PriceList_Version_Base_ID");
				if (V_temp == p_PriceList_Version_ID)
					//
					// We have Prices already
					//
					;
				else if (v.wasNull())
				//			
				//Copy and Convert from Product_PO
				//	
				{
					sqlins = "INSERT INTO M_ProductPrice "
							+ "(M_PriceList_Version_ID"
							+ " ,M_Product_ID "
							+ " ,AD_Client_ID"
							+ " , AD_Org_ID"
							+ " , IsActive"
							+ " , Created"
							+ " , CreatedBy"
							+ " , Updated"
							+ " , UpdatedBy"
							+ " , PriceList"
							+ " , PriceStd"
							+ " , PriceLimit) "
							+ "SELECT "
							+ p_PriceList_Version_ID
							+ "      ,po.M_Product_ID "
							+ "      ,"
							+ v.getInt("AD_Client_ID")
							+ "      ,"
							+ v.getInt("AD_Org_ID")
							+ "      ,'Y'"
							+ "      ,SysDate,"
							+ v.getInt("UpdatedBy") 
							+ "      ,SysDate,"
							+ v.getInt("UpdatedBy")
							//
							//Price List
							//
							+ " ,COALESCE(currencyConvert(po.PriceList, po.C_Currency_ID, "
							+ v.getInt("C_Currency_ID")
							+ ",  ? , "
							+ dl.getInt("C_ConversionType_ID")
							+ ", "
							+ v.getInt("AD_Client_ID")
							+ ", "
							+ v.getInt("AD_Org_ID")
							+ "),0)"
														
							//	Price Std
							+ " ,COALESCE(currencyConvert(po.PriceList, po.C_Currency_ID, "
							+ v.getInt("C_Currency_ID")
							+ ", ? , "
							+ dl.getInt("C_ConversionType_ID")
							+ ", "
							+ v.getInt("AD_Client_ID")
                            + ", "
							+ v.getInt("AD_Org_ID")
							+ "),0)"
							
							//	Price Limit
							+ " ,COALESCE(currencyConvert(po.PricePO ,po.C_Currency_ID, "
							+ v.getInt("C_Currency_ID")
							+ ",? , "
							+ dl.getInt("C_ConversionType_ID")
							+ ", "
							+ v.getInt("AD_Client_ID")
							+ ", "
							+ v.getInt("AD_Org_ID")
							+ "),0)"
							+ " FROM	M_Product_PO po "
							+ " WHERE EXISTS (SELECT * FROM T_Selection s WHERE po.M_Product_ID=s.T_Selection_ID" 
								+ " AND s.AD_PInstance_ID=" + m_AD_PInstance_ID + ") "
							+ " AND	po.IsCurrentVendor='Y' AND po.IsActive='Y'";

					PreparedStatement pstmt = DB.prepareStatement(sqlins,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE, get_TrxName());
					pstmt.setTimestamp(1, dl.getTimestamp("ConversionDate"));
					pstmt.setTimestamp(2, dl.getTimestamp("ConversionDate"));
					pstmt.setTimestamp(3, dl.getTimestamp("ConversionDate"));

					cnti = pstmt.executeUpdate();
					if (cnti == -1)
						raiseError(
								" INSERT INTO T_Selection from existing PriceList",
								sqlins);
					toti += cnti;
					log.fine("Inserted " + cnti);
				} else {
					//
					//Copy and Convert from other PriceList_Version
					//
					sqlins = "INSERT INTO M_ProductPrice "
							+ " (M_PriceList_Version_ID, M_Product_ID,"
							+ " AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,"
							+ " PriceList, PriceStd, PriceLimit)"
							+ " SELECT "
							+ p_PriceList_Version_ID
							+ ", pp.M_Product_ID,"
							+ v.getInt("AD_Client_ID")
							+ ", "
							+ v.getInt("AD_Org_ID")
							+ ", 'Y', SysDate,  "
							+ v.getInt("UpdatedBy")
							+ ", SysDate, "
							+ v.getInt("UpdatedBy")
							+ " ,"
							// Price List
							+ "COALESCE(currencyConvert(pp.PriceList, pl.C_Currency_ID, "
							+ v.getInt("C_Currency_ID")
							+ ", ?, "
							+ dl.getInt("C_ConversionType_ID")
							+ ", "
							+ v.getInt("AD_Client_ID")
							+ ", "
							+ v.getInt("AD_Org_ID")
							+ "),0),"
							// Price Std
							+ "COALESCE(currencyConvert(pp.PriceStd,pl.C_Currency_ID, "
							+ v.getInt("C_Currency_ID")
							+ " , ? ,  "
							+ dl.getInt("C_ConversionType_ID")
							+ ", "
							+ v.getInt("AD_Client_ID")
							+ ", "
							+ v.getInt("AD_Org_ID")
							+ "),0),"
							//Price Limit						   
							+ " COALESCE(currencyConvert(pp.PriceLimit,pl.C_Currency_ID, "
							+ v.getInt("C_Currency_ID")
							+ " , ? , "
							+ dl.getInt("C_ConversionType_ID")
							+ ", "
							+ v.getInt("AD_Client_ID")
							+ ", "
							+ v.getInt("AD_Org_ID")
							+ "),0)"
							+ " FROM M_ProductPrice pp"
							+ " INNER JOIN M_PriceList_Version plv ON (pp.M_PriceList_Version_ID=plv.M_PriceList_Version_ID)"
							+ " INNER JOIN M_PriceList pl ON (plv.M_PriceList_ID=pl.M_PriceList_ID)"
							+ " WHERE	pp.M_PriceList_Version_ID="
							+ v.getInt("M_PriceList_Version_Base_ID")
							+ " AND EXISTS (SELECT * FROM T_Selection s WHERE pp.M_Product_ID=s.T_Selection_ID" 
								+ " AND s.AD_PInstance_ID=" + m_AD_PInstance_ID + ")"
							+ "AND	pp.IsActive='Y'";

					PreparedStatement pstmt = DB.prepareStatement(sqlins,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE, get_TrxName());
					pstmt.setTimestamp(1, dl.getTimestamp("ConversionDate"));
					pstmt.setTimestamp(2, dl.getTimestamp("ConversionDate"));
					pstmt.setTimestamp(3, dl.getTimestamp("ConversionDate"));

					cnti = pstmt.executeUpdate();

					if (cnti == -1)
						raiseError(
								" INSERT INTO T_Selection from existing PriceList",
								sqlins);
					toti += cnti;
					log.fine("Inserted " + cnti);

				}
				Message = Message + ", @Inserted@=" + cnti;
				//
				// Calculation
				//
				sqlupd = "UPDATE M_ProductPrice p "
						+ " SET	PriceList = (DECODE( '"
						+ dl.getString("List_Base")
						+ "', 'S', PriceStd, 'X', PriceLimit, PriceList)"
						+ " + ?) * (1 - ?/100)," + " PriceStd = (DECODE('"
						+ dl.getString("Std_Base")
						+ "', 'L', PriceList, 'X', PriceLimit, PriceStd) "
						+ " + ?) * (1 - ?/100), " + " PriceLimit = (DECODE('"
						+ dl.getString("Limit_Base")
						+ "', 'L', PriceList, 'S', PriceStd, PriceLimit) "
						+ " + ?) * (1 - ? /100) "
						+ " WHERE	M_PriceList_Version_ID = "
						+ p_PriceList_Version_ID
						+ " AND EXISTS	(SELECT * FROM T_Selection s "
						+ " WHERE s.T_Selection_ID = p.M_Product_ID" 
						+ " AND s.AD_PInstance_ID=" + m_AD_PInstance_ID + ")";
				PreparedStatement pstmu = DB.prepareStatement(sqlupd,
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE, get_TrxName());

				pstmu.setDouble(1, dl.getDouble("List_AddAmt"));
				pstmu.setDouble(2, dl.getDouble("List_Discount"));
				pstmu.setDouble(3, dl.getDouble("Std_AddAmt"));
				pstmu.setDouble(4, dl.getDouble("Std_Discount"));
				pstmu.setDouble(5, dl.getDouble("Limit_AddAmt"));
				pstmu.setDouble(6, dl.getDouble("Limit_Discount"));

				cntu = pstmu.executeUpdate();

				if (cntu == -1)
					raiseError("Update  M_ProductPrice ", sqlupd);
				totu += cntu;
				log.fine("Updated " + cntu);

				//
				//Rounding	(AD_Reference_ID=155)
				//
				sqlupd = "UPDATE	M_ProductPrice p "
						+ " SET	PriceList = DECODE('"
						+ dl.getString("List_Rounding") + "',"
						+ " 'N', PriceList, " 
						+ " '0', ROUND(PriceList, 0)," //Even .00
						+ " 'D', ROUND(PriceList, 1)," //Dime .10
						+ " 'T', ROUND(PriceList, -1), " //Ten 10.00
						+ " '5', ROUND(PriceList*20,0)/20," //Nickle .05
						+ " 'Q', ROUND(PriceList*4,0)/4," //Quarter .25
						+ " '9', CASE"  //Whole 9 or 5
							+ " WHEN MOD(ROUND(PriceList),10)<=5 THEN ROUND(PriceList)+(5-MOD(ROUND(PriceList),10))"
							+ " WHEN MOD(ROUND(PriceList),10)>5 THEN ROUND(PriceList)+(9-MOD(ROUND(PriceList),10)) END,"
						+ " ROUND(PriceList, " + v.getInt("StdPrecision")
						+ "))," //Currency
						+ " PriceStd = DECODE('" + dl.getString("Std_Rounding")
						+ "'," + " 'N', PriceStd, "
						+ " '0', ROUND(PriceStd, 0), " //Even .00
						+ " 'D', ROUND(PriceStd, 1), " //Dime .10
						+ "'T', ROUND(PriceStd, -1)," //Ten 10.00
						+ "'5', ROUND(PriceStd*20,0)/20," //Nickle .05
						+ "'Q', ROUND(PriceStd*4,0)/4," //Quarter .25	
						+ " '9', CASE"  //Whole 9 or 5
							+ " WHEN MOD(ROUND(PriceStd),10)<=5 THEN ROUND(PriceStd)+(5-MOD(ROUND(PriceStd),10))"
							+ " WHEN MOD(ROUND(PriceStd),10)>5 THEN ROUND(PriceStd)+(9-MOD(ROUND(PriceStd),10)) END,"
						+ "ROUND(PriceStd, " + v.getInt("StdPrecision") + "))," //Currency
						+ "PriceLimit = DECODE('"
						+ dl.getString("Limit_Rounding") + "', "
						+ "			'N', PriceLimit, "
						+ "		'0', ROUND(PriceLimit, 0),	" //	Even .00					
						+ "		'D', ROUND(PriceLimit, 1),	" //	Dime .10
						+ "		'T', ROUND(PriceLimit, -1),	" //	Ten 10.00
						+ "		'5', ROUND(PriceLimit*20,0)/20,	" //	Nickle .05
						+ "		'Q', ROUND(PriceLimit*4,0)/4,		" //Quarter .25
						+ "     '9', CASE"  //Whole 9 or 5
								+ " WHEN MOD(ROUND(PriceLimit),10)<=5 THEN ROUND(PriceLimit)+(5-MOD(ROUND(PriceLimit),10))"
								+ " WHEN MOD(ROUND(PriceLimit),10)>5 THEN ROUND(PriceLimit)+(9-MOD(ROUND(PriceLimit),10)) END,"
						+ "		ROUND(PriceLimit, " + v.getInt("StdPrecision")
						+ ")) " //	Currency
						+ " WHERE	M_PriceList_Version_ID="
						+ p_PriceList_Version_ID
						+ " AND EXISTS	(SELECT * FROM T_Selection s "
						+ " WHERE s.T_Selection_ID=p.M_Product_ID" 
						+ " AND s.AD_PInstance_ID=" + m_AD_PInstance_ID + ")";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				if (cntu == -1)
					raiseError("Update  M_ProductPrice ", sqlupd);
				totu += cntu;
				log.fine("Updated " + cntu);

				Message = Message + ", @Updated@=" + cntu;
				//
				//Fixed Price overwrite
				//
				sqlupd = "UPDATE	M_ProductPrice p "
						+ " SET	PriceList  = DECODE('"
						+ dl.getString("List_Base") + "', 'F', "
						+ dl.getDouble("List_Fixed") + ", PriceList), "
						+ "      PriceStd   = DECODE('"
						+ dl.getString("Std_Base") + "', 'F', "
						+ dl.getDouble("Std_Fixed") + ", PriceStd),"
						+ "      PriceLimit = DECODE('"
						+ dl.getString("Limit_Base") + "', 'F', "
						+ dl.getDouble("Limit_Fixed") + ", PriceLimit)"
						+ " WHERE	M_PriceList_Version_ID="
						+ p_PriceList_Version_ID
						+ " AND EXISTS	(SELECT * FROM T_Selection s"
						+ " WHERE s.T_Selection_ID=p.M_Product_ID" 
						+ " AND s.AD_PInstance_ID=" + m_AD_PInstance_ID + ")";
				cntu = DB.executeUpdate(sqlupd, get_TrxName());
				if (cntu == -1)
					raiseError("Update  M_ProductPrice ", sqlupd);
				totu += cntu;
				log.fine("Updated " + cntu);

				v_NextNo = v_NextNo + 1;
				addLog(0, null, null, Message);
				Message = "";
			}
			dl.close();
			Cur_DiscountLine.close();
			Cur_DiscountLine = null;

			//
			//	Delete Temporary Selection
			//
			sqldel = "DELETE FROM T_Selection ";
			cntd = DB.executeUpdate(sqldel, get_TrxName());
			if (cntd == -1)
				raiseError(" DELETE	T_Selection ", sqldel);
			totd += cntd;
			log.fine("Deleted " + cntd);
			
			//
			//commit;
			//
			// log.fine("Committing ...");
			// DB.commit(true, get_TrxName());

		}
		v.close();
		curgen.close();
		curgen = null;

		return "OK";

	} // del doIt

	private void raiseError(String string, String sql) throws Exception {
		
		// DB.rollback(false, get_TrxName());
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null)
			msg = pp.getName() + " - ";
		msg += sql;
		throw new AdempiereUserError(msg);
	}
	
	/**
	 * Returns a sql where string with the given category id and all of its subcategory ids.
	 * It is used as restriction in MQuery.
	 * @param productCategoryId
	 * @return
	 */
	private String getSubCategoryWhereClause(int productCategoryId) throws SQLException, AdempiereSystemError{
		//if a node with this id is found later in the search we have a loop in the tree
		int subTreeRootParentId = 0;
		String retString = " ";
		String sql = " SELECT M_Product_Category_ID, M_Product_Category_Parent_ID FROM M_Product_Category";
		final Vector<SimpleTreeNode> categories = new Vector<SimpleTreeNode>(100);
		Statement stmt = DB.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			if(rs.getInt(1)==productCategoryId) {
				subTreeRootParentId = rs.getInt(2);
			}
			categories.add(new SimpleTreeNode(rs.getInt(1), rs.getInt(2)));
		}
		retString += getSubCategoriesString(productCategoryId, categories, subTreeRootParentId);
		rs.close();
		stmt.close();
		return retString;
	}

	/**
	 * Recursive search for subcategories with loop detection.
	 * @param productCategoryId
	 * @param categories
	 * @param loopIndicatorId
	 * @return comma seperated list of category ids
	 * @throws AdempiereSystemError if a loop is detected
	 */
	private String getSubCategoriesString(int productCategoryId, Vector<SimpleTreeNode> categories, int loopIndicatorId) throws AdempiereSystemError {
		String ret = "";
		final Iterator iter = categories.iterator();
		while (iter.hasNext()) {
			SimpleTreeNode node = (SimpleTreeNode) iter.next();
			if (node.getParentId() == productCategoryId) {
				if (node.getNodeId() == loopIndicatorId) {
					throw new AdempiereSystemError("The product category tree contains a loop on categoryId: " + loopIndicatorId);
				}
				ret = ret + getSubCategoriesString(node.getNodeId(), categories, loopIndicatorId) + ",";
			}
		}
		log.fine(ret);
		return ret + productCategoryId;
	}

	/**
	 * Simple tree node class for product category tree search.
	 * @author Karsten Thiemann, kthiemann@adempiere.org
	 *
	 */
	private class SimpleTreeNode {

		private int nodeId;

		private int parentId;

		public SimpleTreeNode(int nodeId, int parentId) {
			this.nodeId = nodeId;
			this.parentId = parentId;
		}

		public int getNodeId() {
			return nodeId;
		}

		public int getParentId() {
			return parentId;
		}
	}



} // M_PriceList_Create