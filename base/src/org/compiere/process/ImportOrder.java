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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.model.X_I_Order;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;

/**
 *	Import Order from I_Order
 *  @author Oscar Gomez
 * 			<li>BF [ 2936629 ] Error when creating bpartner in the importation order
 * 			<li>https://sourceforge.net/tracker/?func=detail&aid=2936629&group_id=176962&atid=879332
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ImportOrder.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 1436 ] Add User Parameter for import orders
 *		@see https://github.com/adempiere/adempiere/issues/1436
 * @author Victor Pérez, victor.perez@e-evolution.com , e-Evolution http://www.e-evolution.com
 * 		<li>#3712 [Bug Report] Incorrect handling of DB transactions in the order import process</>
 * 		<li>https://github.com/adempiere/adempiere/issues/3712</li>
 */
public class ImportOrder extends ImportOrderAbstract
{
	/** Effective						*/
	private Timestamp		dateValue = null;
	String clientCheck = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare() {
		super.prepare();
		if (dateValue == null) {
			dateValue = new Timestamp (System.currentTimeMillis());
		}
		//	Validate Document Action
		if(getDocAction() == null) {
			setDocAction(MOrder.DOCACTION_Prepare);
		}
	}	//	prepare

	private String getClientCheck() {
		if (clientCheck != null)
			return clientCheck;

		clientCheck = " AND AD_Client_ID=" + getClientId();
		//	for user
		if (getUserId() != 0) {
			clientCheck += " AND CreatedBy = " + getUserId();
		}
		return clientCheck;
	}

	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		//	****	Prepare	****
		//	Delete Old Imported
		if (isDeleteOldImported()) {
			Trx.run(trxName -> {
				int no = 0;
				StringBuilder sql = new StringBuilder ("DELETE I_Order "
						+ "WHERE I_IsImported='Y'").append (getClientCheck());
				no = DB.executeUpdate(sql.toString(), trxName);
				log.fine("Delete Old Impored =" + no);
			});
		}

		//	Set Client, Org, IsActive, Created/Updated
		updateClientInfo();
		//Update Invalid Org
		updateErrorWithInvalidOrg();
		//	Document Type - PO - SO
		updateDocumentType();
		// Error Invalid Doc Type Name
		updateErrorWithInvalidDocumentTypeName();
		//	DocType Default
		updateDocumentTypeDefault();
		//	Set IsSOTrx
		updateIsSOTrx();
		//	Price List
		updatePriceList();
		//No PriceList
		updateErrorWithNotExistsPriceList();
		//Update Order Source
		updateOrderSource();
		// Set proper error message
		updateErrorWithNotFoundOrderSource();
		//	Payment Term
		updatePaymentTerm();
		//Error No Payment Term
		updateErrorWithNotExistsPaymentTerm();
		//	Warehouse
		updateWarehouse();
		//ERR=No Warehouse
		updateErrorNotExistsWarehouse();
		// Update partner from email
		updatePartnerFromEmail();
		//	BP from ContactName
		updatePartnerFromContactName();
		//	BP from Value
		updatePartnerFromValue();
		//	Existing Location ? Exact Match
		updateExistingLocationWithExactMatch();
		//ERR=No BP Location
		updateErrorWithNotExistsPartnerLocation();
		//	Set Country
		updateCountry();
		// Invalid Country
		updateErrorWithInvalidCountry();
		//	Set Region
		updateRegion();
		//Invalid Region
		updateErrorWithExistsInvalidRegion();
		//	Product
		updateProduct();
		// Invalid Product
		updateErrorWithInvalidProduct();
		//	Charge
		updateCharge();
		// Invalid Charge
		updateErrorWithInvalidCharge();
		//Error Invaild Product And Charge(
		updateErrorWithInvalidProductAndCharge();
		//Update Tax
		updateTax();
		//Invalid Tax
		updateErrorWithInvalidTax();
		//Create Business Partner
		createPartner();
		//Update with error that not exists Partner
		updateErrorWithThatNotExistsPartner();
		//Crate new Order
		AtomicInteger noInsert = new AtomicInteger(0);
		AtomicInteger noInsertLine =  new AtomicInteger(0);
		Arrays.stream(getImportOrderIdsOrderByPartner()).forEach(importOrderId -> Trx.run(trxName -> {
				X_I_Order importOrder = new X_I_Order(getCtx(), importOrderId, trxName);
				try {
					int currentPartnerId = 0;
					int currentBillToId = 0;
					int currentPartnerLocationId = 0;
					String currentDocumentNo = "";
					MOrder order = null;
					int lineNo = 0;
					String cmpDocumentNo = importOrder.getDocumentNo();
					if (cmpDocumentNo == null)
						cmpDocumentNo = "";
					//	New Order
					if (currentPartnerId != importOrder.getC_BPartner_ID()
							|| currentPartnerLocationId != importOrder.getC_BPartner_Location_ID()
							|| currentBillToId != importOrder.getBillTo_ID()
							|| !currentDocumentNo.equals(cmpDocumentNo)) {
						if (order != null) {
							if (getDocAction() != null && getDocAction().length() > 0) {
								order.setDocAction(getDocAction());
								order.processIt(getDocAction());
							}
							order.saveEx();
						}
						currentPartnerId = importOrder.getC_BPartner_ID();
						currentPartnerLocationId = importOrder.getC_BPartner_Location_ID();
						currentBillToId = importOrder.getBillTo_ID();
						currentDocumentNo = importOrder.getDocumentNo();
						if (currentDocumentNo == null)
							currentDocumentNo = "";
						//
						order = new MOrder(getCtx(), 0, trxName);
						order.setClientOrg(importOrder.getAD_Client_ID(), importOrder.getAD_Org_ID());
						order.setC_DocTypeTarget_ID(importOrder.getC_DocType_ID());
						order.setIsSOTrx(importOrder.isSOTrx());
						if (importOrder.getDeliveryRule() != null) {
							order.setDeliveryRule(importOrder.getDeliveryRule());
						}
						if (importOrder.getDocumentNo() != null)
							order.setDocumentNo(importOrder.getDocumentNo());
						//	Ship Partner
						order.setC_BPartner_ID(importOrder.getC_BPartner_ID());
						order.setC_BPartner_Location_ID(importOrder.getC_BPartner_Location_ID());
						if (importOrder.getAD_User_ID() != 0)
							order.setAD_User_ID(importOrder.getAD_User_ID());
						//	Bill Partner
						order.setBill_BPartner_ID(importOrder.getC_BPartner_ID());
						order.setBill_Location_ID(importOrder.getBillTo_ID());
						//
						if (importOrder.getDescription() != null)
							order.setDescription(importOrder.getDescription());
						order.setC_PaymentTerm_ID(importOrder.getC_PaymentTerm_ID());
						order.setM_PriceList_ID(importOrder.getM_PriceList_ID());
						order.setM_Warehouse_ID(importOrder.getM_Warehouse_ID());
						if (importOrder.getM_Shipper_ID() != 0)
							order.setM_Shipper_ID(importOrder.getM_Shipper_ID());
						//	SalesRep from Import or the person running the import
						if (importOrder.getSalesRep_ID() != 0)
							order.setSalesRep_ID(importOrder.getSalesRep_ID());
						if (order.getSalesRep_ID() == 0)
							order.setSalesRep_ID(getAD_User_ID());
						//
						if (importOrder.getAD_OrgTrx_ID() != 0)
							order.setAD_OrgTrx_ID(importOrder.getAD_OrgTrx_ID());
						if (importOrder.getC_Activity_ID() != 0)
							order.setC_Activity_ID(importOrder.getC_Activity_ID());
						if (importOrder.getC_Campaign_ID() != 0)
							order.setC_Campaign_ID(importOrder.getC_Campaign_ID());
						if (importOrder.getC_Project_ID() != 0)
							order.setC_Project_ID(importOrder.getC_Project_ID());
						//
						if (importOrder.getDateOrdered() != null)
							order.setDateOrdered(importOrder.getDateOrdered());
						if (importOrder.getDateAcct() != null)
							order.setDateAcct(importOrder.getDateAcct());

						// Set Order Source
						if (importOrder.getC_OrderSource() != null)
							order.setC_OrderSource_ID(importOrder.getC_OrderSource_ID());
						//
						order.saveEx();
						noInsert.getAndUpdate(count -> count + 1);
						lineNo = 10;
					}
					importOrder.setC_Order_ID(order.getC_Order_ID());
					//	New OrderLine
					MOrderLine line = new MOrderLine(order);
					line.setLine(lineNo);
					lineNo += 10;
					if (importOrder.getM_Product_ID() != 0)
						line.setM_Product_ID(importOrder.getM_Product_ID(), true);
					if (importOrder.getC_Charge_ID() != 0)
						line.setC_Charge_ID(importOrder.getC_Charge_ID());
					line.setQty(importOrder.getQtyOrdered());
					line.setPrice();
					if (importOrder.getPriceActual().compareTo(Env.ZERO) != 0)
						line.setPrice(importOrder.getPriceActual());
					if (importOrder.getC_Tax_ID() != 0)
						line.setC_Tax_ID(importOrder.getC_Tax_ID());
					else {
						line.setTax();
						importOrder.setC_Tax_ID(line.getC_Tax_ID());
					}
					if (importOrder.getFreightAmt() != null)
						line.setFreightAmt(importOrder.getFreightAmt());
					if (importOrder.getLineDescription() != null)
						line.setDescription(importOrder.getLineDescription());
					line.saveEx();
					importOrder.setC_OrderLine_ID(line.getC_OrderLine_ID());
					importOrder.setI_IsImported(true);
					importOrder.setProcessed(true);
					importOrder.saveEx();
					noInsertLine.getAndUpdate(insertLine -> insertLine + 1);
				} catch (Exception exception) {
					importOrder.setI_IsImported(false);
					importOrder.setI_ErrorMsg(exception.getMessage());
					importOrder.saveEx();
				}
			}));

		//Update not import Order
		updateNotImportOrder();
		//
		addLog (0, null, new BigDecimal (noInsert.get()), "@C_Order_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noInsertLine.get()), "@C_OrderLine_ID@: @Inserted@");
		return "#" + noInsert.get() + "/" + noInsertLine.get();
	}	//	doIt

	private int[] getImportOrderIdsForPartnerNotExists() {
			StringBuilder whereClause = new StringBuilder("I_IsImported='N' AND C_BPartner_ID IS NULL").append(getClientCheck());
			return new Query(getCtx() , X_I_Order.Table_Name , whereClause.toString() , get_TrxName()).getIDs();
	}

	private int[] getImportOrderIdsOrderByPartner() {
		StringBuilder whereClause = new StringBuilder("I_IsImported='N'").append(getClientCheck());
		return new Query(getCtx() , X_I_Order.Table_Name , whereClause.toString() , get_TrxName())
				.setOrderBy("C_BPartner_ID, BillTo_ID, C_BPartner_Location_ID, I_Order_ID")
				.getIDs();
	}

	private Optional<X_I_Order> getValidImportOrder(Properties ctx , int importOrderId, String trxName) {
		X_I_Order importOrder = new X_I_Order (ctx, importOrderId , trxName);
		if (importOrder.getBPartnerValue () == null)
		{
			if (importOrder.getEMail () != null)
				importOrder.setBPartnerValue (importOrder.getEMail ());
			else if (importOrder.getName () != null)
				importOrder.setBPartnerValue (importOrder.getName ());
			else
				return Optional.empty();
		}
		if (importOrder.getName () == null)
		{
			if (importOrder.getContactName () != null)
				importOrder.setName (importOrder.getContactName ());
			else
				importOrder.setName (importOrder.getBPartnerValue ());
		}
		return Optional.of(importOrder);
	}

	private void createPartner() {
		//	-- New BPartner ---------------------------------------------------
		//	Go through Order Records w/o C_BPartner_ID
		Arrays.stream(getImportOrderIdsForPartnerNotExists()).forEach(importOrderId ->  {
			Trx.run(trxName -> {
				getValidImportOrder(getCtx(),importOrderId, trxName).ifPresent(importOrder -> {
					//	BPartner
					MBPartner partner = MBPartner.get (getCtx(), importOrder.getBPartnerValue());
					try {
						if (partner == null) {
							partner = new MBPartner(getCtx(), -1, trxName);
							partner.setClientOrg(importOrder.getAD_Client_ID(), importOrder.getAD_Org_ID());
							partner.setValue(importOrder.getBPartnerValue());
							partner.setName(importOrder.getName());
							partner.saveEx();
						}
						importOrder.setC_BPartner_ID(partner.getC_BPartner_ID());

						//	BP Location
						MBPartnerLocation partnerLocation = null;
						MBPartnerLocation[] partnerLocations = partner.getLocations(true);
						for (int i = 0; partnerLocation == null && i < partnerLocations.length; i++) {
							if (importOrder.getC_BPartner_Location_ID() == partnerLocations[i].getC_BPartner_Location_ID())
								partnerLocation = partnerLocations[i];
								//	Same Location ID
							else if (importOrder.getC_Location_ID() == partnerLocations[i].getC_Location_ID())
								partnerLocation = partnerLocations[i];
								//	Same Location Info
							else if (importOrder.getC_Location_ID() == 0) {
								MLocation loc = partnerLocations[i].getLocation(false);
								if (loc.equals(importOrder.getC_Country_ID(), importOrder.getC_Region_ID(),
										importOrder.getPostal(), "", importOrder.getCity(),
										importOrder.getAddress1(), importOrder.getAddress2()))
									partnerLocation = partnerLocations[i];
							}
						}
						if (partnerLocation == null) {
							//	New Location
							MLocation location = new MLocation(getCtx(), 0, trxName);
							location.setAddress1(importOrder.getAddress1());
							location.setAddress2(importOrder.getAddress2());
							location.setCity(importOrder.getCity());
							location.setPostal(importOrder.getPostal());
							if (importOrder.getC_Region_ID() != 0)
								location.setC_Region_ID(importOrder.getC_Region_ID());
							location.setC_Country_ID(importOrder.getC_Country_ID());
							location.saveEx();
							//
							partnerLocation = new MBPartnerLocation(partner);
							partnerLocation.setC_Location_ID(location.getC_Location_ID());
							partnerLocation.saveEx();
						}
						importOrder.setC_Location_ID(partnerLocation.getC_Location_ID());
						importOrder.setBillTo_ID(partnerLocation.getC_BPartner_Location_ID());
						importOrder.setC_BPartner_Location_ID(partnerLocation.getC_BPartner_Location_ID());

						//	User/Contact
						if (importOrder.getContactName() != null
								|| importOrder.getEMail() != null
								|| importOrder.getPhone() != null) {
							MUser[] users = partner.getContacts(true);
							MUser user = null;
							for (int i = 0; user == null && i < users.length; i++) {
								String name = users[i].getName();
								if (name.equals(importOrder.getContactName())
										|| name.equals(importOrder.getName())) {
									user = users[i];
									importOrder.setAD_User_ID(user.getAD_User_ID());
								}
							}
							if (user == null) {
								user = new MUser(partner);
								if (importOrder.getContactName() == null)
									user.setName(importOrder.getName());
								else
									user.setName(importOrder.getContactName());
								user.setEMail(importOrder.getEMail());
								user.setPhone(importOrder.getPhone());
								if (user.save())
									importOrder.setAD_User_ID(user.getAD_User_ID());
							}
						}
						importOrder.saveEx();
					} catch (Exception exception) {
						importOrder.setI_IsImported(false);
						importOrder.setI_ErrorMsg(exception.getMessage());
						importOrder.saveEx();
					}
				});
			});
		});
	}

	private void updateClientInfo() {
		//	Set Client, Org, IsActive, Created/Updated
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (getClientId()).append ("),"
					+ " AD_Org_ID = COALESCE (AD_Org_ID,").append (getOrgId()).append ("),"
					+ " IsActive = COALESCE (IsActive, 'Y'),"
					+ " Created = COALESCE (Created, SysDate),"
					+ " CreatedBy = COALESCE (CreatedBy, 0),"
					+ " Updated = COALESCE (Updated, SysDate),"
					+ " UpdatedBy = COALESCE (UpdatedBy, 0),"
					+ " I_ErrorMsg = ' ',"
					+ " I_IsImported = 'N' "
					+ "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
			no = DB.executeUpdate(sql.toString(), trxName);
			log.info ("Reset=" + no);
		});
	}

	private void updateErrorWithInvalidOrg() {
		//Update Invalid Org
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
					+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
					+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("Invalid Org=" + no);
		});
	}

	private void updateDocumentType() {
		//	Document Type - PO - SO
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "	//	PO Document Type Name
					+ "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
					+ " AND d.DocBaseType='POO' AND o.AD_Client_ID=d.AD_Client_ID) "
					+ "WHERE C_DocType_ID IS NULL AND IsSOTrx='N' AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(),trxName);
			log.fine("Set PO DocType=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "	//	SO Document Type Name
					+ "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
					+ " AND d.DocBaseType='SOO' AND o.AD_Client_ID=d.AD_Client_ID) "
					+ "WHERE C_DocType_ID IS NULL AND IsSOTrx='Y' AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set SO DocType=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
					+ " AND d.DocBaseType IN ('SOO','POO') AND o.AD_Client_ID=d.AD_Client_ID) "
					//+ "WHERE C_DocType_ID IS NULL AND IsSOTrx IS NULL AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
					+ "WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(),trxName);
			log.fine("Set DocType=" + no);
		});
	}

	private void updateErrorWithInvalidDocumentTypeName() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "	//	Error Invalid Doc Type Name
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid DocTypeName, ' "
					+ "WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("Invalid DocTypeName=" + no);
		});
	}

	private void updateDocumentTypeDefault() {
		//	DocType Default
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "	//	Default PO
					+ "SET C_DocType_ID=(SELECT MAX(C_DocType_ID) FROM C_DocType d WHERE d.IsDefault='Y'"
					+ " AND d.DocBaseType='POO' AND o.AD_Client_ID=d.AD_Client_ID) "
					+ "WHERE C_DocType_ID IS NULL AND IsSOTrx='N' AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set PO Default DocType=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "	//	Default SO
					+ "SET C_DocType_ID=(SELECT MAX(C_DocType_ID) FROM C_DocType d WHERE d.IsDefault='Y'"
					+ " AND d.DocBaseType='SOO' AND o.AD_Client_ID=d.AD_Client_ID) "
					+ "WHERE C_DocType_ID IS NULL AND IsSOTrx='Y' AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set SO Default DocType=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_DocType_ID=(SELECT MAX(C_DocType_ID) FROM C_DocType d WHERE d.IsDefault='Y'"
					+ " AND d.DocBaseType IN('SOO','POO') AND o.AD_Client_ID=d.AD_Client_ID) "
					+ "WHERE C_DocType_ID IS NULL AND IsSOTrx IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Default DocType=" + no);
			sql = new StringBuilder ("UPDATE I_Order "	// No DocType
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No DocType, ' "
					+ "WHERE C_DocType_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("No DocType=" + no);
		});
	}

	private void updateIsSOTrx() {
		//	Set IsSOTrx
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o SET IsSOTrx='Y' "
					+ "WHERE EXISTS (SELECT * FROM C_DocType d WHERE o.C_DocType_ID=d.C_DocType_ID AND d.DocBaseType='SOO' AND o.AD_Client_ID=d.AD_Client_ID)"
					+ " AND C_DocType_ID IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set IsSOTrx=Y=" + no);
			sql = new StringBuilder ("UPDATE I_Order o SET IsSOTrx='N' "
					+ "WHERE EXISTS (SELECT * FROM C_DocType d WHERE o.C_DocType_ID=d.C_DocType_ID AND d.DocBaseType='POO' AND o.AD_Client_ID=d.AD_Client_ID)"
					+ " AND C_DocType_ID IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set IsSOTrx=N=" + no);
		});
	}

	private void updatePriceList() {
		//	Price List
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p WHERE p.IsDefault='Y'"
					+ " AND p.C_Currency_ID=o.C_Currency_ID AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_PriceList_ID IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Default Currency PriceList=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p WHERE p.IsDefault='Y'"
					+ " AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_PriceList_ID IS NULL AND C_Currency_ID IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Default PriceList=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p "
					+ " WHERE p.C_Currency_ID=o.C_Currency_ID AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_PriceList_ID IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Currency PriceList=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p "
					+ " WHERE p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_PriceList_ID IS NULL AND C_Currency_ID IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set PriceList=" + no);
		});
	}

	private void updateErrorWithNotExistsPriceList() {
		//No PriceList
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No PriceList, ' "
					+ "WHERE M_PriceList_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(),trxName);
			if (no != 0)
				log.warning("No PriceList=" + no);
		});
	}

	private void updateOrderSource() {
		//Update Order Source
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_OrderSource_ID=(SELECT C_OrderSource_ID FROM C_OrderSource p"
					+ " WHERE o.C_OrderSourceValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE C_OrderSource_ID IS NULL AND C_OrderSourceValue IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Order Source=" + no);
		});
	}

	private void updateErrorWithNotFoundOrderSource() {
		// Set proper error message
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Not Found Order Source, ' "
					+ "WHERE C_OrderSource_ID IS NULL AND C_OrderSourceValue IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning("No OrderSource=" + no);
		});
	}

	private void updatePaymentTerm() {
		//	Payment Term
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_PaymentTerm_ID=(SELECT C_PaymentTerm_ID FROM C_PaymentTerm p"
					+ " WHERE o.PaymentTermValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE C_PaymentTerm_ID IS NULL AND PaymentTermValue IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set PaymentTerm=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_PaymentTerm_ID=(SELECT MAX(C_PaymentTerm_ID) FROM C_PaymentTerm p"
					+ " WHERE p.IsDefault='Y' AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE C_PaymentTerm_ID IS NULL AND o.PaymentTermValue IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Default PaymentTerm=" + no);
		});
	}

	private void updateErrorWithNotExistsPaymentTerm() {
		//Error No Payment Term
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No PaymentTerm, ' "
					+ "WHERE C_PaymentTerm_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("No PaymentTerm=" + no);
		});
	}

	private void updateWarehouse() {
		//	Warehouse
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_Warehouse_ID=(SELECT MAX(M_Warehouse_ID) FROM M_Warehouse w"
					+ " WHERE o.AD_Client_ID=w.AD_Client_ID AND o.AD_Org_ID=w.AD_Org_ID) "
					+ "WHERE M_Warehouse_ID IS NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);	//	Warehouse for Org
			if (no != 0)
				log.fine("Set Warehouse=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Warehouse w"
					+ " WHERE o.AD_Client_ID=w.AD_Client_ID) "
					+ "WHERE M_Warehouse_ID IS NULL"
					+ " AND EXISTS (SELECT AD_Client_ID FROM M_Warehouse w WHERE w.AD_Client_ID=o.AD_Client_ID GROUP BY AD_Client_ID HAVING COUNT(*)=1)"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.fine("Set Only Client Warehouse=" + no);
		});
	}

	private void updateErrorNotExistsWarehouse() {
		//ERR=No Warehouse
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Warehouse, ' "
					+ "WHERE M_Warehouse_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("No Warehouse=" + no);
		});
	}

	private void updatePartnerFromEmail() {
		Trx.run(trxName -> {
			int no = 0;
			//	BP from EMail
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u"
					+ " WHERE o.EMail=u.EMail AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) "
					+ "WHERE C_BPartner_ID IS NULL AND EMail IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set BP from EMail=" + no);
		});
	}

	private void updatePartnerFromContactName() {
		//	BP from ContactName
		Trx.run(trxName -> {
			StringBuilder sql  = new StringBuilder ("UPDATE I_Order o "
					+ "SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u"
					+ " WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) "
					+ "WHERE C_BPartner_ID IS NULL AND ContactName IS NOT NULL"
					+ " AND EXISTS (SELECT Name FROM AD_User u WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL GROUP BY Name HAVING COUNT(*)=1)"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			int no = 0;
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set BP from ContactName=" + no);
		});
	}
	private void updatePartnerFromValue() {
		//	BP from Value
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_BPartner_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner bp"
					+ " WHERE o.BPartnerValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID) "
					+ "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set BP from Value=" + no);
			//	Default BP
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_BPartner_ID=(SELECT C_BPartnerCashTrx_ID FROM AD_ClientInfo c"
					+ " WHERE o.AD_Client_ID=c.AD_Client_ID) "
					+ "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NULL AND Name IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Default BP=" + no);

		});
	}

	private void updateExistingLocationWithExactMatch() {
		//	Existing Location ? Exact Match
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET (BillTo_ID,C_BPartner_Location_ID)=(SELECT C_BPartner_Location_ID,C_BPartner_Location_ID"
					+ " FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)"
					+ " WHERE o.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=o.AD_Client_ID"
					+ " AND DUMP(o.Address1)=DUMP(l.Address1) AND DUMP(o.Address2)=DUMP(l.Address2)"
					+ " AND DUMP(o.City)=DUMP(l.City) AND DUMP(o.Postal)=DUMP(l.Postal)"
					+ " AND o.C_Region_ID=l.C_Region_ID AND o.C_Country_ID=l.C_Country_ID) "
					+ "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
					+ " AND I_IsImported='N'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Found Location=" + no);
			//	Set Bill Location from BPartner
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET BillTo_ID=(SELECT MAX(C_BPartner_Location_ID) FROM C_BPartner_Location l"
					+ " WHERE l.C_BPartner_ID=o.C_BPartner_ID AND o.AD_Client_ID=l.AD_Client_ID"
					+ " AND ((l.IsBillTo='Y' AND o.IsSOTrx='Y') OR (l.IsPayFrom='Y' AND o.IsSOTrx='N'))"
					+ ") "
					+ "WHERE C_BPartner_ID IS NOT NULL AND BillTo_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set BP BillTo from BP=" + no);
			//	Set Location from BPartner
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_BPartner_Location_ID=(SELECT MAX(C_BPartner_Location_ID) FROM C_BPartner_Location l"
					+ " WHERE l.C_BPartner_ID=o.C_BPartner_ID AND o.AD_Client_ID=l.AD_Client_ID"
					+ " AND ((l.IsShipTo='Y' AND o.IsSOTrx='Y') OR o.IsSOTrx='N')"
					+ ") "
					+ "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(),trxName);
			log.fine("Set BP Location from BP=" + no);
		});
	}

	private void updateErrorWithNotExistsPartnerLocation() {
		//ERR=No BP Location
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No BP Location, ' "
					+ "WHERE C_BPartner_ID IS NOT NULL AND (BillTo_ID IS NULL OR C_BPartner_Location_ID IS NULL)"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("No BP Location=" + no);
		});
	}

	private void updateCountry() {
		//	Set Country
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c"
					+ " WHERE o.CountryCode=c.CountryCode AND c.AD_Client_ID IN (0, o.AD_Client_ID)) "
					+ "WHERE C_BPartner_ID IS NULL AND C_Country_ID IS NULL AND CountryCode IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Country=" + no);
		});
	}

	private void updateErrorWithInvalidCountry() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Country, ' "
					+ "WHERE C_BPartner_ID IS NULL AND C_Country_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append(getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning("Invalid Country=" + no);
		});
	}

	private void updateRegion() {
		//	Set Region
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "Set RegionName=(SELECT MAX(Name) FROM C_Region r"
					+ " WHERE r.IsDefault='Y' AND r.C_Country_ID=o.C_Country_ID"
					+ " AND r.AD_Client_ID IN (0, o.AD_Client_ID)) "
					+ "WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL AND RegionName IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Region Default=" + no);
			//
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r"
					+ " WHERE r.Name=o.RegionName AND r.C_Country_ID=o.C_Country_ID"
					+ " AND r.AD_Client_ID IN (0, o.AD_Client_ID)) "
					+ "WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL AND RegionName IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Region=" + no);
		});
	}

	private void updateErrorWithExistsInvalidRegion() {
		//Invalid Region
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Region, ' "
					+ "WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL "
					+ " AND EXISTS (SELECT * FROM C_Country c"
					+ " WHERE c.C_Country_ID=o.C_Country_ID AND c.HasRegion='Y')"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("Invalid Region=" + no);
		});
	}

	private void updateProduct() {
		//	Product
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
					+ " WHERE o.ProductValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Product from Value=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
					+ " WHERE o.UPC=p.UPC AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_Product_ID IS NULL AND UPC IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Product from UPC=" + no);
			sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
					+ " WHERE o.SKU=p.SKU AND o.AD_Client_ID=p.AD_Client_ID) "
					+ "WHERE M_Product_ID IS NULL AND SKU IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Product fom SKU=" + no);

		});
	}

	private void updateErrorWithInvalidProduct() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, ' "
					+ "WHERE M_Product_ID IS NULL AND (ProductValue IS NOT NULL OR UPC IS NOT NULL OR SKU IS NOT NULL)"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("Invalid Product=" + no);
		});
	}
	private void updateCharge() {
		//	Charge
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_Charge_ID=(SELECT C_Charge_ID FROM C_Charge c"
					+ " WHERE o.ChargeName=c.Name AND o.AD_Client_ID=c.AD_Client_ID) "
					+ "WHERE C_Charge_ID IS NULL AND ChargeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Charge=" + no);
		});
	}

	private void updateErrorWithInvalidCharge() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Charge, ' "
					+ "WHERE C_Charge_ID IS NULL AND (ChargeName IS NOT NULL)"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("Invalid Charge=" + no);
		});
	}

	private void updateErrorWithInvalidProductAndCharge() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Product and Charge, ' "
					+ "WHERE M_Product_ID IS NOT NULL AND C_Charge_ID IS NOT NULL "
					+ " AND I_IsImported<>'Y'").append(getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning("Invalid Product and Charge exclusive=" + no);
		});
	}

	private void updateTax() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order o "
					+ "SET C_Tax_ID=(SELECT MAX(C_Tax_ID) FROM C_Tax t"
					+ " WHERE o.TaxIndicator=t.TaxIndicator AND o.AD_Client_ID=t.AD_Client_ID) "
					+ "WHERE C_Tax_ID IS NULL AND TaxIndicator IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			log.fine("Set Tax=" + no);
		});
	}

	private void updateErrorWithInvalidTax() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Tax, ' "
					+ "WHERE C_Tax_ID IS NULL AND TaxIndicator IS NOT NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("Invalid Tax=" + no);
		});
	}

	public void updateErrorWithThatNotExistsPartner() {
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No BPartner, ' "
					+ "WHERE C_BPartner_ID IS NULL"
					+ " AND I_IsImported<>'Y'").append (getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0)
				log.warning ("No BPartner=" + no);
		});
	}

	private void updateNotImportOrder() {
		//	Set Error to indicator to not imported
		Trx.run(trxName -> {
			int no = 0;
			StringBuilder sql = new StringBuilder ("UPDATE I_Order "
					+ "SET I_IsImported='N', Updated=SysDate "
					+ "WHERE I_IsImported<>'Y'").append(getClientCheck());
			no = DB.executeUpdate(sql.toString(), trxName);
			if (no != 0) {
				log.warning("Order No Import =" + no);
				addLog(0, null, new BigDecimal(no), "@Errors@");
			}
		});
	}

	/**
	 sql = new StringBuilder ("UPDATE I_Order o "
	 + "SET CountryCode=(SELECT MAX(CountryCode) FROM C_Country c WHERE c.IsDefault='Y'"
	 + " AND c.AD_Client_ID IN (0, o.AD_Client_ID)) "
	 + "WHERE C_BPartner_ID IS NULL AND CountryCode IS NULL AND C_Country_ID IS NULL"
	 + " AND I_IsImported<>'Y'").append (getClientCheck());
	 no = DB.executeUpdate(sql.toString(), get_TrxName());
	 log.fine("Set Country Default=" + no);
	 **/
}	//	ImportOrder
