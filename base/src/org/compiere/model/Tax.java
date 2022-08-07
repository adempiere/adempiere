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
package org.compiere.model;

import io.vavr.Tuple;
import io.vavr.Tuple3;
import io.vavr.collection.List;
import org.adempiere.exceptions.TaxCriteriaNotFoundException;
import org.adempiere.exceptions.TaxForChangeNotFoundException;
import org.adempiere.exceptions.TaxNoExemptFoundException;
import org.adempiere.exceptions.TaxNotFoundException;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.ResultSetIterable;
import org.compiere.util.Env;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 *	Tax Handling
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: Tax.java,v 1.3 2006/07/30 00:51:02 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2758097 ] Implement TaxNotFoundException
 */
public class Tax {
	/**
	 * Logger
	 */
	static private CLogger log = CLogger.getCLogger(Tax.class);


	/**************************************************************************
	 *	Get Tax ID - converts parameters to call Get Tax.
	 *  <pre>
	 *		M_Product_ID/C_Charge_ID	->	C_TaxCategory_ID
	 *		billDate, shipDate			->	billDate, shipDate
	 *		AD_Org_ID					->	billFromC_Location_ID
	 *		M_Warehouse_ID				->	shipFromC_Location_ID
	 *		billC_BPartner_Location_ID  ->	billToC_Location_ID
	 *		shipC_BPartner_Location_ID 	->	shipToC_Location_ID
	 *
	 *  if IsSOTrx is false, bill and ship are reversed
	 *  </pre>
	 *    @param ctx context
	 *    @param M_Product_ID product
	 *    @param C_Charge_ID product
	 *    @param billDate invoice date
	 *    @param shipDate ship date (ignored)
	 *    @param AD_Org_ID org
	 *    @param M_Warehouse_ID warehouse (ignored)
	 *    @param billC_BPartner_Location_ID invoice location
	 *    @param shipC_BPartner_Location_ID ship location (ignored)
	 *    @param IsSOTrx is a sales trx
	 *    @return C_Tax_ID
	 *  @throws TaxCriteriaNotFoundException if a criteria was not found
	 */
	@Deprecated
	public static int get(Properties ctx, int M_Product_ID, int C_Charge_ID,
						  Timestamp billDate, Timestamp shipDate,
						  int AD_Org_ID, int M_Warehouse_ID,
						  int billC_BPartner_Location_ID, int shipC_BPartner_Location_ID,
						  boolean IsSOTrx) {
		return get(ctx, M_Product_ID, C_Charge_ID,
				billDate, shipDate,
				AD_Org_ID, M_Warehouse_ID,
				billC_BPartner_Location_ID, shipC_BPartner_Location_ID,
				IsSOTrx, null);
	}    //	get

	public static int get (Properties ctx, int M_Product_ID, int C_Charge_ID,
		Timestamp billDate, Timestamp shipDate,
		int AD_Org_ID, int M_Warehouse_ID,
		int billC_BPartner_Location_ID, int shipC_BPartner_Location_ID,
		boolean IsSOTrx, String trxName)
	{
		if (M_Product_ID != 0)
			return getProduct (ctx, M_Product_ID, billDate, shipDate, AD_Org_ID, M_Warehouse_ID,
				billC_BPartner_Location_ID, shipC_BPartner_Location_ID, IsSOTrx
				, trxName);
		else if (C_Charge_ID != 0)
			return getCharge (ctx, C_Charge_ID, billDate, shipDate, AD_Org_ID, M_Warehouse_ID,
				billC_BPartner_Location_ID, shipC_BPartner_Location_ID, IsSOTrx, trxName);
		else
			return getExemptTax (ctx, AD_Org_ID);
	}	//	get

	/**
	 * Get Tax ID - converts parameters to call Get Tax.
	 * <pre>
	 * 		C_Charge_ID					->	C_TaxCategory_ID
	 * 		billDate					->	billDate
	 * 		shipDate					->	shipDate (ignored)
	 * 		AD_Org_ID					->	billFromC_Location_ID
	 * 		M_Warehouse_ID				->	shipFromC_Location_ID (ignored)
	 * 		billC_BPartner_Location_ID  ->	billToC_Location_ID
	 * 		shipPartnerLocationId 	->	shipToC_Location_ID (ignored)
	 *
	 *  if IsSOTrx is false, bill and ship are reversed
	 *  </pre>
	 *
	 * @param ctx                        context
	 * @param chargeId                product
	 * @param billDate                   invoice date
	 * @param shipDate                   ship date (ignored)
	 * @param orgId                  org
	 * @param warehouseId             warehouse (ignored)
	 * @param billPartnerLocationId invoice location
	 * @param shipPartnerLocationId ship location (ignored)
	 * @param isSOTrx                    is a sales trx
	 * @return C_Tax_ID
	 * @throws TaxForChangeNotFoundException if criteria not found for given change
	 * @throws TaxCriteriaNotFoundException  if a criteria was not found
	 */
	@Deprecated
	public static int getCharge (Properties ctx, int chargeId,
								 Timestamp billDate, Timestamp shipDate,
								 int orgId, int warehouseId,
								 int billPartnerLocationId, int shipPartnerLocationId,
								 boolean isSOTrx) {
		return getCharge(ctx,chargeId, billDate, shipDate, orgId, warehouseId, billPartnerLocationId, shipPartnerLocationId,isSOTrx,null);
	}

	/**
	 *	Get Tax ID - converts parameters to call Get Tax.
	 *  <pre>
	 *		C_Charge_ID					->	C_TaxCategory_ID
	 *		billDate					->	billDate
	 *		shipDate					->	shipDate (ignored)
	 *		AD_Org_ID					->	billFromC_Location_ID
	 *		M_Warehouse_ID				->	shipFromC_Location_ID (ignored)
	 *		billC_BPartner_Location_ID  ->	billToC_Location_ID
	 *		shipC_BPartner_Location_ID 	->	shipToC_Location_ID (ignored)
	 *
	 *  if IsSOTrx is false, bill and ship are reversed
	 *  </pre>
	 * 	@param ctx	context
	 * 	@param chargeId product
	 * 	@param billDate invoice date
	 * 	@param shipDate ship date (ignored)
	 * 	@param orgId org
	 * 	@param warehouseId warehouse (ignored)
	 * 	@param billPartnerLocationId invoice location
	 * 	@param shipPartnerLocationId ship location (ignored)
	 * 	@param isSOTrx is a sales trx
	 *  @param trxName Transaction name
	 * 	@return C_Tax_ID
	 *  @throws TaxForChangeNotFoundException if criteria not found for given change
	 *  @throws TaxCriteriaNotFoundException if a criteria was not found
	 */
	public static int getCharge (Properties ctx, int chargeId,
		Timestamp billDate, Timestamp shipDate,
		int orgId, int warehouseId,
		int billPartnerLocationId, int shipPartnerLocationId,
		boolean isSOTrx, String trxName) {
		//	Get all at once
		String sql = "SELECT c.C_TaxCategory_ID, o.C_Location_ID AS BillFromLocation_ID, il.C_Location_ID AS BillToLocation_ID, b.IsTaxExempt, "
				+ "b.IsPOTaxExempt, w.C_Location_ID AS ShipFromLocation_ID, sl.C_Location_ID AS ShipToLocation_ID, linkBP.C_BPartner_ID, "
				+ "CASE WHEN linkBP.C_BPartner_ID IS NOT NULL THEN linkBP.IsTaxExempt ELSE NULL END AS linkBP_TaxExempt, "
				+ "CASE WHEN linkBP.C_BPartner_ID IS NOT NULL THEN linkBP.IsPOTaxExempt ELSE NULL END AS linkBP_POTaxEXempt "
				+ "FROM C_Charge c, AD_OrgInfo o, "
				+ "C_BPartner_Location il INNER JOIN C_BPartner b ON (il.C_BPartner_ID=b.C_BPartner_ID) "
				+ "LEFT OUTER JOIN M_Warehouse w ON (w.M_Warehouse_ID=?), C_BPartner_Location sl "
				+ "LEFT JOIN C_BPartner linkBP ON (linkBP.AD_OrgBP_ID=?) "
				+ "WHERE c.C_Charge_ID=? "
				+ "AND o.AD_Org_ID=? "
				+ "AND il.C_BPartner_Location_ID=? "
				+ "AND sl.C_BPartner_Location_ID=?";

		AtomicBoolean found = new AtomicBoolean(false);
		TaxDefinition taxDefinition = new TaxDefinition();
		DB.runResultSetFunction.apply(trxName, sql, List.of(warehouseId, orgId, chargeId, orgId, billPartnerLocationId, shipPartnerLocationId), resultSet -> {
			TaxDefinition td = new TaxDefinition();
			List<TaxDefinition> taxes = new ResultSetIterable<>(resultSet, row -> {
				td.taxCategoryId = resultSet.getInt("C_TaxCategory_ID");
				td.billFromLocationId = resultSet.getInt("BillFromLocation_ID");
				td.billToLocationId = resultSet.getInt("BillToLocation_ID");
				td.isSOTaxExempt = resultSet.getString("IsTaxExempt");
				td.isPOTaxExempt = resultSet.getString("IsPOTaxExempt");
				td.isTaxExempt = isSOTrx ? td.isSOTaxExempt : td.isPOTaxExempt;
				td.shipFromLocationId = resultSet.getInt("ShipFromLocation_ID");
				td.shipToLocationId = resultSet.getInt("ShipToLocation_ID");
				td.linkBPartnerId = resultSet.getInt("C_BPartner_ID");
				td.linkBPartnerTaxExempt = resultSet.getString("linkBP_TaxExempt");
				td.linkBPartnerPOTaxExempt = resultSet.getString("linkBP_POTaxExempt");
				return td;
			}).toList();

			taxes.forEach(row -> {
				taxDefinition.taxCategoryId = row.taxCategoryId;
				taxDefinition.billFromLocationId = row.billFromLocationId;
				taxDefinition.billToLocationId = row.billToLocationId;
				taxDefinition.isSOTaxExempt = row.isSOTaxExempt;
				taxDefinition.isPOTaxExempt = row.isPOTaxExempt;
				taxDefinition.isTaxExempt = isSOTrx ? taxDefinition.isSOTaxExempt : taxDefinition.isPOTaxExempt;
				taxDefinition.shipFromLocationId = row.shipFromLocationId;
				taxDefinition.shipToLocationId = row.shipToLocationId;
				taxDefinition.linkBPartnerId = row.linkBPartnerId;
				taxDefinition.linkBPartnerTaxExempt = row.linkBPartnerTaxExempt;
				taxDefinition.linkBPartnerPOTaxExempt = row.linkBPartnerPOTaxExempt;
				found.set(true);
			});

			if (!found.get()) {
				throw new TaxForChangeNotFoundException(chargeId, orgId, warehouseId,
						billPartnerLocationId, shipPartnerLocationId,
						null);
			}
		});

		if ("Y".equals(taxDefinition.isTaxExempt)) {
			return getExemptTax(ctx, orgId);
		} else if ("N".equals(taxDefinition.isTaxExempt)) {

			Boolean useTaxExemptOrg = MSysConfig.getBooleanValue("VALIDATE_EXEMPT_TAXES_BASED_ON_ORGANIZATION",
					false, Env.getAD_Client_ID(ctx));

			if (useTaxExemptOrg) {
				if (taxDefinition.linkBPartnerId > 0) {
					if (isSOTrx && taxDefinition.linkBPartnerTaxExempt.equalsIgnoreCase("Y")
							|| !isSOTrx && taxDefinition.linkBPartnerTaxExempt.equalsIgnoreCase("Y")) {
						log.fine("getProduct - Business Partner is Tax exempt");
						return getExemptTax(ctx, orgId);
					}
				}
			}
		}

		//	Reverese for PO
		if (!isSOTrx)
		{
			int temp = taxDefinition.billFromLocationId;
			taxDefinition.billFromLocationId = taxDefinition.billToLocationId;
			taxDefinition.billToLocationId= temp;
			temp = taxDefinition.shipFromLocationId;
			taxDefinition.shipFromLocationId= taxDefinition.shipToLocationId;
			taxDefinition.shipToLocationId = temp;
		}
		//
		log.fine("getCharge - C_TaxCategory_ID=" + taxDefinition.taxCategoryId
		  + ", billFromC_Location_ID=" + taxDefinition.billFromLocationId
		  + ", billToC_Location_ID=" + taxDefinition.billToLocationId
		  + ", shipFromC_Location_ID=" + taxDefinition.shipFromLocationId
		  + ", shipToC_Location_ID=" + taxDefinition.shipToLocationId);
		return get (ctx, taxDefinition.taxCategoryId, isSOTrx,
		  shipDate, taxDefinition.shipFromLocationId, taxDefinition.shipToLocationId,
		  billDate, taxDefinition.billFromLocationId, taxDefinition.billToLocationId, trxName);
	}	//	getCharge

	/**
	 *	Get Tax ID - converts parameters to call Get Tax.
	 *  <pre>
	 *		M_Product_ID				->	C_TaxCategory_ID
	 *		billDate					->	billDate
	 *		shipDate					->	shipDate (ignored)
	 *		AD_Org_ID					->	billFromC_Location_ID
	 *		M_Warehouse_ID				->	shipFromC_Location_ID (ignored)
	 *		billC_BPartner_Location_ID  ->	billToC_Location_ID
	 *		shipC_BPartner_Location_ID 	->	shipToC_Location_ID (ignored)
	 *
	 *  if IsSOTrx is false, bill and ship are reversed
	 *  </pre>
	 * 	@param ctx	context
	 * 	@param M_Product_ID product
	 * 	@param billDate invoice date
	 * 	@param shipDate ship date (ignored)
	 * 	@param AD_Org_ID org
	 * 	@param M_Warehouse_ID warehouse (ignored)
	 * 	@param billC_BPartner_Location_ID invoice location
	 * 	@param shipC_BPartner_Location_ID ship location (ignored)
	 * 	@param IsSOTrx is a sales trx
	 * 	@return C_Tax_ID
	 *  If error it returns 0 and sets error log (TaxCriteriaNotFound)
	 */
	@Deprecated
	public static int getProduct (Properties ctx, int M_Product_ID,
			Timestamp billDate, Timestamp shipDate,
			int AD_Org_ID, int M_Warehouse_ID,
			int billC_BPartner_Location_ID, int shipC_BPartner_Location_ID,
			boolean IsSOTrx)
	{
		return getProduct(ctx, M_Product_ID,
				billDate, shipDate,
				AD_Org_ID, M_Warehouse_ID,
				billC_BPartner_Location_ID, shipC_BPartner_Location_ID,
				IsSOTrx, null);
	}

	/**
	 *	Get Tax ID - converts parameters to call Get Tax.
	 *  <pre>
	 *		M_Product_ID				->	C_TaxCategory_ID
	 *		billDate					->	billDate
	 *		shipDate					->	shipDate (ignored)
	 *		AD_Org_ID					->	billFromC_Location_ID
	 *		M_Warehouse_ID				->	shipFromC_Location_ID (ignored)
	 *		billC_BPartner_Location_ID  ->	billToC_Location_ID
	 *		shipC_BPartner_Location_ID 	->	shipToC_Location_ID (ignored)
	 *
	 *  if IsSOTrx is false, bill and ship are reversed
	 *  </pre>
	 * 	@param ctx	context
	 * 	@param productId product
	 * 	@param billDate invoice date
	 * 	@param shipDate ship date (ignored)
	 * 	@param organizationId org
	 * 	@param warehouseId warehouse (ignored)
	 * 	@param billBPartnerLocationId invoice location
	 * 	@param shipBPartnerLocationId ship location (ignored)
	 * 	@param isSOTrx is a sales trx
	 *  @param trxName Transaction Name
	 * 	@return C_Tax_ID
	 *  If error it returns 0 and sets error log (TaxCriteriaNotFound)
	 */
	public static int getProduct (Properties ctx, int productId,
		Timestamp billDate, Timestamp shipDate,
		int organizationId, int warehouseId,
		int billBPartnerLocationId, int shipBPartnerLocationId,
		boolean isSOTrx, String trxName)
	{
		String variable = "";
		String sql;
		//	Get all at once
		sql = "SELECT p.C_TaxCategory_ID, o.C_Location_ID AS BillFromLocation_ID, il.C_Location_ID AS BillToLocation_ID, b.IsTaxExempt, b.IsPOTaxExempt, "
			+ "w.C_Location_ID AS ShipFromLocation_ID, sl.C_Location_ID AS ShipToLocation_ID, linkBP.C_BPartner_ID, "
			+ "CASE WHEN linkBP.C_BPartner_ID IS NOT NULL THEN linkBP.IsTaxExempt ELSE NULL END AS linkBP_TaxExempt, "
			+ "CASE WHEN linkBP.C_BPartner_ID IS NOT NULL THEN linkBP.IsPOTaxExempt ELSE NULL END AS linkBP_POTaxEXempt "
			+ "FROM M_Product p, AD_OrgInfo o, "
			+ "C_BPartner_Location il INNER JOIN C_BPartner b ON (il.C_BPartner_ID=b.C_BPartner_ID) "
			+ "LEFT OUTER JOIN M_Warehouse w ON (w.M_Warehouse_ID=?), C_BPartner_Location sl "
			+ "LEFT JOIN C_BPartner linkBP ON (linkBP.AD_OrgBP_ID=?) "
			+ "WHERE p.M_Product_ID=? "
			+ "AND o.AD_Org_ID=? "
			+ "AND il.C_BPartner_Location_ID=? "
			+ "AND sl.C_BPartner_Location_ID=?";

		AtomicBoolean found = new AtomicBoolean(false);
		TaxDefinition taxDefinition = new TaxDefinition();
		DB.runResultSetFunction.apply(trxName, sql, List.of(warehouseId, organizationId, productId, organizationId, billBPartnerLocationId, shipBPartnerLocationId), resultSet -> {
			List<TaxDefinition> taxes = new ResultSetIterable<>(resultSet, row -> {
				TaxDefinition td = new TaxDefinition();
				td.taxCategoryId = resultSet.getInt("C_TaxCategory_ID");
				td.billFromLocationId = resultSet.getInt("BillFromLocation_ID");
				td.billToLocationId = resultSet.getInt("BillToLocation_ID");
				td.isSOTaxExempt = resultSet.getString("IsTaxExempt");
				td.isPOTaxExempt = resultSet.getString("IsPOTaxExempt");
				td.isTaxExempt = isSOTrx ? td.isSOTaxExempt : td.isPOTaxExempt;
				td.shipFromLocationId = resultSet.getInt("ShipFromLocation_ID");
				td.shipToLocationId = resultSet.getInt("ShipToLocation_ID");
				td.linkBPartnerId = resultSet.getInt("C_BPartner_ID");
				td.linkBPartnerTaxExempt = resultSet.getString("linkBP_TaxExempt");
				td.linkBPartnerPOTaxExempt = resultSet.getString("linkBP_POTaxExempt");
				return td;
			}).toList();
			taxes.forEach(row -> {
				taxDefinition.taxCategoryId = row.taxCategoryId;
				taxDefinition.billFromLocationId = row.billFromLocationId;
				taxDefinition.billToLocationId = row.billToLocationId;
				taxDefinition.isSOTaxExempt = row.isSOTaxExempt;
				taxDefinition.isPOTaxExempt = row.isPOTaxExempt;
				taxDefinition.isTaxExempt = isSOTrx ? taxDefinition.isSOTaxExempt : taxDefinition.isPOTaxExempt;
				taxDefinition.shipFromLocationId = row.shipFromLocationId;
				taxDefinition.shipToLocationId = row.shipToLocationId;
				taxDefinition.linkBPartnerId = row.linkBPartnerId;
				taxDefinition.linkBPartnerTaxExempt = row.linkBPartnerTaxExempt;
				taxDefinition.linkBPartnerPOTaxExempt = row.linkBPartnerPOTaxExempt;
				found.set(true);
			});
		});

		//
		if (found.get() && "Y".equals(taxDefinition.isTaxExempt)) {
			log.fine("getProduct - Business Partner is Tax exempt");
			return getExemptTax(ctx, organizationId);
		} else if (found.get() && "N".equals(taxDefinition.isTaxExempt)) {

			Boolean useTaxExemptOrg = MSysConfig.getBooleanValue("VALIDATE_EXEMPT_TAXES_BASED_ON_ORGANIZATION",
					false, Env.getAD_Client_ID(ctx));

			if (useTaxExemptOrg) {
				if (taxDefinition.linkBPartnerId > 0) {
					if (isSOTrx && taxDefinition.linkBPartnerTaxExempt.equalsIgnoreCase("Y")
							|| !isSOTrx && taxDefinition.linkBPartnerPOTaxExempt.equalsIgnoreCase("Y")) {
						log.fine("getProduct - Business Partner is Tax exempt");
						return getExemptTax(ctx, organizationId);
					}
				}
			}

			if (!isSOTrx) {
				int temp = taxDefinition.billFromLocationId;
				taxDefinition.billFromLocationId = taxDefinition.billToLocationId;
				taxDefinition.billToLocationId = temp;
				temp = taxDefinition.shipFromLocationId;
				taxDefinition.shipFromLocationId = taxDefinition.shipToLocationId;
				taxDefinition.shipToLocationId = temp;
			}
			log.fine("getProduct - C_TaxCategory_ID=" + taxDefinition.taxCategoryId
					+ ", billFromC_Location_ID=" + taxDefinition.billFromLocationId
					+ ", billToC_Location_ID=" + taxDefinition.billToLocationId
					+ ", shipFromC_Location_ID=" + taxDefinition.shipFromLocationId
					+ ", shipToC_Location_ID=" + taxDefinition.shipToLocationId);
			return get(ctx, taxDefinition.taxCategoryId, isSOTrx,
					shipDate, taxDefinition.shipFromLocationId, taxDefinition.shipToLocationId,
					billDate, taxDefinition.billFromLocationId, taxDefinition.billToLocationId, trxName);

		}

		// ----------------------------------------------------------------

		//	Detail for error isolation

	//	M_Product_ID				->	C_TaxCategory_ID
		variable = "M_Product_ID";
		sql = "SELECT C_TaxCategory_ID FROM M_Product WHERE M_Product_ID=?";
		taxDefinition.taxCategoryId = DB.getSQLValueEx(null, sql, productId);
		found.set(taxDefinition.taxCategoryId != -1);
		if (taxDefinition.taxCategoryId <= 0)
		{
			throw new TaxCriteriaNotFoundException(variable, productId);
		}
		log.fine("getProduct - C_TaxCategory_ID=" + taxDefinition.taxCategoryId);

	//	AD_Org_ID					->	billFromC_Location_ID
		variable = "AD_Org_ID";
		sql = "SELECT C_Location_ID FROM AD_OrgInfo WHERE AD_Org_ID=?";
		taxDefinition.billFromLocationId = DB.getSQLValueEx(null, sql, organizationId);
		found.set(taxDefinition.billFromLocationId != -1);
		if (taxDefinition.billFromLocationId <= 0)
		{
			throw new TaxCriteriaNotFoundException(variable, organizationId);
		}

	//	billC_BPartner_Location_ID  ->	billToC_Location_ID
		variable = "BillTo_ID";
		sql = "SELECT l.C_Location_ID, b.IsTaxExempt, b.IsPOTaxExempt "
			+ " FROM C_BPartner_Location l"
			+ " INNER JOIN C_BPartner b ON (l.C_BPartner_ID=b.C_BPartner_ID) "
			+ " WHERE C_BPartner_Location_ID=?";
		found.set(false);
		DB.runResultSetFunction.apply(trxName, sql, List.of(billBPartnerLocationId), resultSet -> {
			List<Tuple3<Integer, String, String>> rows = new ResultSetIterable<>(resultSet, row -> Tuple.of(
					row.getInt(1),
					row.getString(2),
					row.getString(3)
			)).toList();

			rows.forEach(row -> {
				taxDefinition.billToLocationId = row._1;
				taxDefinition.isSOTaxExempt = row._2;
				taxDefinition.isPOTaxExempt = row._3;
				taxDefinition.isTaxExempt = isSOTrx ? taxDefinition.isSOTaxExempt : taxDefinition.isPOTaxExempt;
			});
		});

		if (taxDefinition.billToLocationId <= 0)
		{
			throw new TaxCriteriaNotFoundException(variable, billBPartnerLocationId);
		}
		if ("Y".equals(taxDefinition.isTaxExempt))
			return getExemptTax(ctx, organizationId);

		//  Reverse for PO
		if (!isSOTrx)
		{
			int temp = taxDefinition.billFromLocationId;
			taxDefinition.billFromLocationId = taxDefinition.billToLocationId;
			taxDefinition.billToLocationId = temp;
		}
		log.fine("getProduct - billFromC_Location_ID = " + taxDefinition.billFromLocationId);
		log.fine("getProduct - billToC_Location_ID = " + taxDefinition.billToLocationId);

		//-----------------------------------------------------------------

	//	M_Warehouse_ID				->	shipFromC_Location_ID
		variable = "M_Warehouse_ID";
		sql = "SELECT C_Location_ID FROM M_Warehouse WHERE M_Warehouse_ID=?";
		taxDefinition.shipFromLocationId = DB.getSQLValueEx(null, sql, warehouseId);
		found.set(taxDefinition.shipFromLocationId != -1);
		if (taxDefinition.shipFromLocationId <= 0)
		{
			throw new TaxCriteriaNotFoundException(variable, warehouseId);
		}

	//	shipC_BPartner_Location_ID 	->	shipToC_Location_ID
		variable = "C_BPartner_Location_ID";
		sql = "SELECT C_Location_ID FROM C_BPartner_Location WHERE C_BPartner_Location_ID=?";
		taxDefinition.shipToLocationId= DB.getSQLValueEx(trxName, sql, shipBPartnerLocationId);
		found.set(taxDefinition.shipToLocationId != -1);
		if (taxDefinition.shipToLocationId <= 0)
		{
			throw new TaxCriteriaNotFoundException(variable, shipBPartnerLocationId);
		}

		//  Reverse for PO
		if (!isSOTrx)
		{
			int temp = taxDefinition.shipFromLocationId;
			taxDefinition.shipFromLocationId = taxDefinition.shipToLocationId;
			taxDefinition.shipToLocationId = temp;
		}
		log.fine("getProduct - shipFromC_Location_ID = " + taxDefinition.shipFromLocationId);
		log.fine("getProduct - shipToC_Location_ID = " + taxDefinition.shipToLocationId);


		return get (ctx, taxDefinition.taxCategoryId, isSOTrx,
			shipDate, taxDefinition.shipFromLocationId, taxDefinition.shipToLocationId,
			billDate, taxDefinition.billFromLocationId, taxDefinition.billToLocationId, trxName);
	}	//	getProduct

	/**
	 * Get Exempt Tax Code
	 * @param ctx context
	 * @param AD_Org_ID org to find client
	 * @return C_Tax_ID
	 * @throws TaxNoExemptFoundException if no tax exempt found
	 */
	private static int getExemptTax (Properties ctx, int AD_Org_ID)
	{
		final String sql = "SELECT t.C_Tax_ID "
			+ "FROM C_Tax t"
			+ " INNER JOIN AD_Org o ON (t.AD_Client_ID=o.AD_Client_ID) "
			+ "WHERE t.IsTaxExempt='Y' AND o.AD_Org_ID=? "
			+ "ORDER BY t.Rate DESC";
		int C_Tax_ID = DB.getSQLValueEx(null, sql, AD_Org_ID);
		log.fine("getExemptTax - TaxExempt=Y - C_Tax_ID=" + C_Tax_ID);
		if (C_Tax_ID <= 0)
		{
			throw new TaxNoExemptFoundException(AD_Org_ID);
		}
		else
		{
			return C_Tax_ID;
		}
	}	//	getExemptTax

	
	/**************************************************************************
	 *	Get Tax ID (Detail).
	 *  @param ctx context
	 *	@param C_TaxCategory_ID tax category
	 * 	@param IsSOTrx Sales Order Trx
	 *	@param shipDate ship date (ignored)
	 *	@param shipFromC_Location_ID ship from (ignored)
	 *	@param shipToC_Location_ID ship to (ignored)
	 *	@param billDate invoice date
	 *	@param billFromC_Location_ID invoice from
	 *	@param billToC_Location_ID invoice to
	 *	@return C_Tax_ID
	 *  @throws TaxNotFoundException if no tax found for given criteria
	 */
	@Deprecated
	protected static int get (Properties ctx,
		int C_TaxCategory_ID, boolean IsSOTrx,
		Timestamp shipDate, int shipFromC_Location_ID, int shipToC_Location_ID,
		Timestamp billDate, int billFromC_Location_ID, int billToC_Location_ID)
	{
		return get(ctx,
			C_TaxCategory_ID, IsSOTrx,
			shipDate, shipFromC_Location_ID, shipToC_Location_ID,
			billDate, billFromC_Location_ID, billToC_Location_ID
			, null);
	}

	protected static int get (Properties ctx,
		int C_TaxCategory_ID, boolean IsSOTrx,
		Timestamp shipDate, int shipFromC_Location_ID, int shipToC_Location_ID,
		Timestamp billDate, int billFromC_Location_ID, int billToC_Location_ID
		, String trxName)
	{
		//	C_TaxCategory contains CommodityCode
		//	API to Tax Vendor comes here
		if (CLogMgt.isLevelFine())
		{
			log.info("get(Detail) - Category=" + C_TaxCategory_ID 
				+ ", SOTrx=" + IsSOTrx);
			log.config("get(Detail) - BillFrom=" + billFromC_Location_ID 
				+ ", BillTo=" + billToC_Location_ID + ", BillDate=" + billDate);
		}

		MTax[] taxes = MTax.getAll (ctx);
		MLocation lFrom = new MLocation (ctx, billFromC_Location_ID, trxName); 
		MLocation lTo = new MLocation (ctx, billToC_Location_ID, trxName); 
		log.finer("From=" + lFrom);
		log.finer("To=" + lTo);
		
		for (int i = 0; i < taxes.length; i++)
		{
			MTax tax = taxes[i];
			log.finest(tax.toString());
			//
			if (tax.getC_TaxCategory_ID() != C_TaxCategory_ID
				|| !tax.isActive() 
				|| tax.getParent_Tax_ID() != 0)	//	user parent tax
				continue;
			if (IsSOTrx && MTax.SOPOTYPE_PurchaseTax.equals(tax.getSOPOType()))
				continue;
			if (!IsSOTrx && MTax.SOPOTYPE_SalesTax.equals(tax.getSOPOType()))
				continue;
			
			log.finest("From Country - " + (tax.getC_Country_ID() == lFrom.getC_Country_ID() 
				|| tax.getC_Country_ID() == 0));
			log.finest("From Region - " + (tax.getC_Region_ID() == lFrom.getC_Region_ID() 
				|| tax.getC_Region_ID() == 0));
			log.finest("To Country - " + (tax.getTo_Country_ID() == lTo.getC_Country_ID() 
				|| tax.getTo_Country_ID() == 0));
			log.finest("To Region - " + (tax.getTo_Region_ID() == lTo.getC_Region_ID() 
				|| tax.getTo_Region_ID() == 0));
			log.finest("Date valid - " + (!tax.getValidFrom().after(billDate)));
			
				//	From Country
			if ((tax.getC_Country_ID() == lFrom.getC_Country_ID() 
					|| tax.getC_Country_ID() == 0)
				//	From Region
				&& (tax.getC_Region_ID() == lFrom.getC_Region_ID() 
					|| tax.getC_Region_ID() == 0)
				//	To Country
				&& (tax.getTo_Country_ID() == lTo.getC_Country_ID() 
					|| tax.getTo_Country_ID() == 0)
				//	To Region
				&& (tax.getTo_Region_ID() == lTo.getC_Region_ID() 
					|| tax.getTo_Region_ID() == 0)
				//	Date
				&& !tax.getValidFrom().after(billDate)
				)
			{
				if (!tax.isPostal())
					return tax.getC_Tax_ID();
				//
				MTaxPostal[] postals = tax.getPostals(false);
				for (int j = 0; j < postals.length; j++)
				{
					MTaxPostal postal = postals[j];
					if (postal.isActive()
						//	Postal From is mandatory
						&& postal.getPostal().startsWith(lFrom.getPostal())
						//	Postal To is optional
						&& (postal.getPostal_To() == null 
							|| postal.getPostal_To().startsWith(lTo.getPostal()))
						)
						return tax.getC_Tax_ID();
				}	//	for all postals
			}
		}	//	for all taxes

		//	Default Tax
		for (int i = 0; i < taxes.length; i++)
		{
			MTax tax = taxes[i];
			if (!tax.isDefault() || !tax.isActive()
				|| tax.getParent_Tax_ID() != 0)	//	user parent tax
				continue;
			if (IsSOTrx && MTax.SOPOTYPE_PurchaseTax.equals(tax.getSOPOType()))
				continue;
			if (!IsSOTrx && MTax.SOPOTYPE_SalesTax.equals(tax.getSOPOType()))
				continue;
			log.fine("get (default) - " + tax);
			return tax.getC_Tax_ID();
		}	//	for all taxes
		
		throw new TaxNotFoundException(C_TaxCategory_ID, IsSOTrx,
				shipDate, shipFromC_Location_ID, shipToC_Location_ID,
				billDate, billFromC_Location_ID, billToC_Location_ID);
	}	//	get

	private static class TaxDefinition {
		private int taxCategoryId = 0;
		private int billFromLocationId = 0;
		private int billToLocationId = 0;
		private String isSOTaxExempt = null;
		private String isPOTaxExempt = null;
		private String isTaxExempt = null;
		private int shipFromLocationId = 0;
		private int shipToLocationId = 0;
		private int linkBPartnerId = 0;
		private String linkBPartnerTaxExempt = null;
		private String linkBPartnerPOTaxExempt = null;
	}
	
}	//	Tax
