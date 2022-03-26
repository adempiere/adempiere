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

import io.vavr.Tuple;
import io.vavr.Tuple6;
import io.vavr.Tuple7;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
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
import org.compiere.util.ResultSetIterable;
import org.compiere.util.Trx;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static java.util.stream.Collectors.groupingByConcurrent;
import static java.util.stream.Collectors.mapping;

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
 *  @author Victor Pérez, victor.perez@e-evolution.com , e-Evolution http://www.e-evolution.com
 * 		<li>#3712 [Bug Report] Incorrect handling of DB transactions in the order import process</>
 * 		<li>https://github.com/adempiere/adempiere/issues/3712</li>
 * 		<li> Add support the Import Order in Parallel and Change the approach to import
 * 	    <li> https://github.com/adempiere/adempiere/issues/3742
 */
public class ImportOrder extends ImportOrderAbstract {
    /**
     * Effective
     */
    private Timestamp dateValue = null;
    private String clientCheck = null;
    private final java.util.List<Object> clientParameters = new ArrayList<>();
    private final HashMap<Integer, Boolean> ordersImported = new HashMap<>();


    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
        if (dateValue == null) {
            dateValue = new Timestamp(System.currentTimeMillis());
        }
        //	Validate Document Action
        if (getDocAction() == null) {
            setDocAction(MOrder.DOCACTION_Prepare);
        }
    }    //	prepare

    private String getClientCheck() {
        if (clientCheck != null)
            return clientCheck;

        clientCheck = " AND AD_Client_ID = ? ";
        //	for user
        if (getUserId() != 0) {
            clientCheck += " AND CreatedBy = ? ";
        }
        return clientCheck;
    }

    private java.util.List<Object> getClientCheckIds() {
        if (!clientParameters.isEmpty())
            return clientParameters;

        clientParameters.add(getClientId());
        //	for user
        if (getUserId() != 0) {
            clientParameters.add(getUserId());
        }
        return clientParameters;
    }

    /**
     * Perform process.
     *
     * @return Message
     * @throws Exception
     */
    protected String doIt() throws java.lang.Exception {
        //	****	Prepare	****
        //	Delete Old Imported
        if (isDeleteOldImported()) {
            Trx.run(trxName -> {
                int no = 0;
                final String sql = "DELETE I_Order "
                        + "WHERE I_IsImported = ? " +
                        getClientCheck();
                List<Object> withParameters = List.of(true);
                no = DB.executeUpdateEx(sql, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
                log.fine("Delete Old Imported =" + no);
            });
        }

        //	Set Client, Org, IsActive, Created/Updated
        setClientInfo();
        //Update Invalid Org
        setErrorWithInvalidOrg();
        //	Document Type - PO - SO
        setDocumentType();
        // Error Invalid Doc Type Name
        setErrorWithInvalidDocumentTypeName();
        //	DocType Default
        setDocumentTypeDefault();
        // Error Document Type Default
        setErrorDocumentTypeDefault();
        //	Set IsSOTrx
        setIsSOTrx();
        //	Price List
        setPriceList();
        //No PriceList
        setErrorWithNotExistsPriceList();
        //Update Order Source
        setOrderSource();
        // Set proper error message
        setErrorWithNotFoundOrderSource();
        //	Payment Term
        setPaymentTerm();
        //Error No Payment Term
        setErrorWithNotExistsPaymentTerm();
        //	Warehouse
        setWarehouse();
        //ERR=No Warehouse
        setErrorNotExistsWarehouse();
        // Update partner from email
        setPartnerFromEmail();
        //	BP from ContactName
        setPartnerFromContactName();
        //	BP from Value
        setPartnerFromValue();
        //	Existing Location ? Exact Match
        setExistingLocationWithExactMatch();
        //ERR=No BP Location
        setErrorWithNotExistsPartnerLocation();
        //	Set Country
        setCountry();
        // Invalid Country
        setErrorWithInvalidCountry();
        //	Set Region
        setRegion();
        //Invalid Region
        setErrorWithExistsInvalidRegion();
        //	Product
        setProduct();
        // Invalid Product
        setErrorWithInvalidProduct();
        //	Charge
        setCharge();
        // Invalid Charge
        setErrorWithInvalidCharge();
        //Error Invalid Product And Charge(
        setErrorWithInvalidProductAndCharge();
        //Update Tax
        setTax();
        //Invalid Tax
        setErrorWithInvalidTax();
        //Create Business Partner
        createPartner();
        //Update with error that not exists Partner
        setErrorWithThatNotExistsPartner();
        //Crate new Order
        AtomicInteger noInsertOrdersReference = new AtomicInteger(0);
        AtomicInteger noInsertOrderLinesReference = new AtomicInteger(0);
        // Get a map with a tuple by  AD_Org_ID , C_BPartner_ID , C_BPartner_Location_ID , BillTo_ID , C_DocType_ID , DocumentNo
        // with a list the lines to import into an Order
        Map<Tuple6<Integer, Integer, Integer, Integer, Integer, String>, java.util.List<Integer>> ordersImportGroup = getOrderImportByDocumentNo()
                .toJavaParallelStream()
                .collect(//  C_BPartner_ID , C_BPartner_Location_ID , BillTo_ID , C_DocType_ID , DocumentNo
                        groupingByConcurrent(tuple -> Tuple.of(tuple._2, tuple._3, tuple._4, tuple._5, tuple._6, tuple._7),
                                //Create a List with ImportOrderId by combination
                                mapping(tupleGrouping -> tupleGrouping._1, Collectors.toList())
                        )
                );
        // Marked as Processing the Import Orders
        setProcessing(ordersImportGroup, true);
        // Executes in parallel the creation of orders and their lines
        ordersImportGroup.entrySet()
                //.stream()
                .parallelStream()
                .forEach(groupEntry -> {
                    Tuple6<Integer, Integer, Integer, Integer, Integer, String> ordersGroup = groupEntry.getKey();
                    final Integer orgId = ordersGroup._1;
                    final Integer partnerId = ordersGroup._2;
                    final Integer partnerLocationId = ordersGroup._3;
                    final Integer billToId = ordersGroup._4;
                    final Integer documentTypeId = ordersGroup._5;
                    final String documentNo = ordersGroup._6;
                    Try.of(() -> {
                        //Create new transaction by order for each group lines
                        Trx.run(trxName -> {
                            //Get or create Order
                            getOrCreateOrder(getCtx(), orgId, documentNo, documentTypeId, partnerId, partnerLocationId, billToId, trxName)
                                    .peek(order -> {
                                        noInsertOrdersReference.getAndUpdate(count -> count + 1);
                                        int firstImportOrderId = groupEntry.getValue().stream().findFirst().orElse(0);
                                        //for each Order Import group lines
                                        groupEntry.getValue().forEach(importOrderId -> {
                                            //Get Import Order Model
                                            X_I_Order importOrder = new X_I_Order(getCtx(), importOrderId, trxName);
                                            if (importOrder.getC_BPartner_ID() == partnerId
                                                    && importOrder.getC_BPartner_Location_ID() == partnerLocationId
                                                    && importOrder.getBillTo_ID() == billToId
                                                    && importOrder.getC_DocType_ID() == documentTypeId
                                                    && importOrder.getDocumentNo().equals(documentNo)) {
                                                //Update the Attributes Order with first import Order
                                                if (firstImportOrderId > 0 && importOrderId == firstImportOrderId)
                                                    updateOrder(order, importOrder);
                                                //	New OrderLine
                                                Try.of(() -> createOrderLine(importOrder, order))
                                                        .onSuccess(orderLine -> {
                                                            importOrder.setC_Order_ID(order.getC_Order_ID());
                                                            importOrder.setC_Tax_ID(orderLine.getC_Tax_ID());
                                                            importOrder.setC_OrderLine_ID(orderLine.getC_OrderLine_ID());
                                                            importOrder.setI_IsImported(true);
                                                            importOrder.setProcessed(true);
                                                            importOrder.saveEx();
                                                            noInsertOrderLinesReference.getAndUpdate(insertLine -> insertLine + 1);
                                                        }).onFailure(throwable -> {
                                                            importOrder.setI_IsImported(false);
                                                            importOrder.setI_ErrorMsg(throwable.getMessage());
                                                            importOrder.saveEx();
                                                            ordersImported.put(order.getC_Order_ID(), false);
                                                        });
                                            } else {
                                                importOrder.setI_IsImported(false);
                                                importOrder.setI_ErrorMsg("@I_Order_ID@ @NotValid@");
                                                importOrder.saveEx();
                                                ordersImported.put(order.getC_Order_ID(), false);
                                            }
                                        });
                                    });
                        });
                        return ordersGroup;
                    }).onFailure(throwable -> {
                        addLog(throwable.getMessage());
                    });
                });
        //Set not import Order
        setNotImportOrder();
        // Apply Document Action to Orders without errors
        applyDocumentAction();
        // Unmarked as Processing the Import Orders
        setProcessing(ordersImportGroup, false);
        addLog(0, null, new BigDecimal(noInsertOrdersReference.get()), "@C_Order_ID@: @Inserted@");
        addLog(0, null, new BigDecimal(noInsertOrderLinesReference.get()), "@C_OrderLine_ID@: @Inserted@");
        return "#" + noInsertOrdersReference.get() + "/" + noInsertOrderLinesReference.get();
    }    //	doIt

    /**
     * setProcessing
     * @param ordersImportGroup
     * @param processing
     */
    private void setProcessing(Map<Tuple6<Integer, Integer, Integer, Integer, Integer, String>, java.util.List<Integer>> ordersImportGroup, Boolean processing) {
        String processingValue = processing ? "'Y'" : "'N'";
        ordersImportGroup.forEach((key, value) -> value.parallelStream().forEach(importOrderId -> {
            Trx.run(trxName -> {
                String setProcessingFlagForImportOrders = "UPDATE I_Order SET Processing = " + processingValue + " WHERE I_Order_ID = ? " + getClientCheck();
                List<Object> parameters = List.of(importOrderId);
                DB.executeUpdateEx(setProcessingFlagForImportOrders, parameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            });
        }));
    }

    /**
     * Update Order
     * @param order Order
     * @param importOrder Import Order
     */
    private void updateOrder(MOrder order, X_I_Order importOrder) {
        order.setClientOrg(importOrder.getAD_Client_ID(), importOrder.getAD_Org_ID());
        order.setC_DocTypeTarget_ID(importOrder.getC_DocType_ID());
        order.setC_DocType_ID(importOrder.getC_DocType_ID());
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
        if (order.is_Changed())
            order.saveEx();

    }

    /**
     * Get or Create Order
     * @param ctx
     * @param organizationId
     * @param documentNo
     * @param documentTypeId
     * @param partnerId
     * @param partnerLocationId
     * @param billToId
     * @param trxName
     * @return
     */
    private Option<MOrder> getOrCreateOrder(
            Properties ctx,
            Integer organizationId,
            String documentNo,
            Integer documentTypeId,
            Integer partnerId,
            Integer partnerLocationId,
            Integer billToId,
            String trxName) {
        return Match(getOrder(ctx, organizationId, documentNo, documentTypeId, partnerId, partnerLocationId, billToId, trxName)).option(
                Case($Some($()), order -> order),
                Case($None(), () -> createOrder(ctx, organizationId, documentNo, documentTypeId, partnerId, partnerLocationId, billToId, trxName))
        );
    }

    /**
     * Get Order
     * @param ctx Context
     * @param organizationId Organization ID
     * @param documentNo DocumentNo
     * @param documentTypeId DocumentType ID
     * @param partnerId PartnerId
     * @param partnerLocationId Partner Location ID
     * @param billToId Bill To ID
     * @param trxName
     * @return Option<MOrder>
     */
    private Option<MOrder> getOrder(
            Properties ctx,
            Integer organizationId,
            String documentNo,
            Integer documentTypeId,
            Integer partnerId,
            Integer partnerLocationId,
            Integer billToId,
            String trxName) {
        final String whereClause =
                MOrder.COLUMNNAME_AD_Org_ID + " = ? AND " +
                        MOrder.COLUMNNAME_DocumentNo + " = ? AND " +
                        MOrder.COLUMNNAME_C_DocType_ID + " = ? AND " +
                        MOrder.COLUMNNAME_C_BPartner_ID + " = ? AND " +
                        MOrder.COLUMNNAME_C_BPartner_Location_ID + " = ? AND " +
                        MOrder.COLUMNNAME_Bill_BPartner_ID + " = ? AND " +
                        MOrder.COLUMNNAME_DocStatus + " IN ( ? , ? ) ";
        return Option.of(new Query(ctx, MOrder.Table_Name, whereClause, trxName)
                .setClient_ID()
                .setParameters(
                        organizationId,
                        documentNo,
                        documentTypeId,
                        partnerId,
                        partnerLocationId,
                        billToId,
                        DocAction.STATUS_Drafted,
                        DocAction.STATUS_InProgress
                )
                .first());
    }

    /**
     * Create Order
     * @param ctx Context
     * @param organizationId Organization ID
     * @param documentNo DocumentNo
     * @param documentTypeId DocumentType ID
     * @param partnerId Partner ID
     * @param partnerLocationId Partner Location ID
     * @param billToId Bill To ID
     * @param trxName trxName
     * @return Order
     */
    private MOrder createOrder(
            Properties ctx,
            Integer organizationId,
            String documentNo,
            Integer documentTypeId,
            Integer partnerId,
            Integer partnerLocationId,
            Integer billToId,
            String trxName
    ) {
        MOrder order = new MOrder(ctx, 0, trxName);
        order.setAD_Org_ID(organizationId);
        order.setC_DocTypeTarget_ID(documentTypeId);
        order.setC_DocType_ID(documentTypeId);
        if (documentNo != null)
            order.setDocumentNo(documentNo);
        //	Ship Partner
        order.setC_BPartner_ID(partnerId);
        order.setC_BPartner_Location_ID(partnerLocationId);
        order.setBill_BPartner_ID(billToId);
        order.saveEx();
        ordersImported.put(order.getC_Order_ID(), true);
        return order;
    }

    /**
     * Create Order Line
     * @param importOrder Import Order
     * @param order Order
     * @return Order Line
     */
    private MOrderLine createOrderLine(X_I_Order importOrder, MOrder order) {
        MOrderLine line = new MOrderLine(order);
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
        }
        if (importOrder.getFreightAmt() != null)
            line.setFreightAmt(importOrder.getFreightAmt());
        if (importOrder.getLineDescription() != null)
            line.setDescription(importOrder.getLineDescription());
        line.saveEx();
        return line;
    }

    /**
     * Apply Document Action in parallel execution
     */
    private void applyDocumentAction() {
        if (getDocAction().isEmpty())
            return;

        Set<Map.Entry<Integer, Boolean>> orders = ordersImported.entrySet();
        orders.parallelStream()
                .filter(Map.Entry::getValue)
                .forEach(entryOrder -> {
                    Trx.run(trxName -> {
                        MOrder order = new MOrder(getCtx(), entryOrder.getKey(), trxName);
                        order.setDocAction(getDocAction());
                        order.processIt(getDocAction());
                        order.saveEx();
                    });
                });
    }

    /**
     * get Order Import By DocumentNo
     * @return List with Order Import
     */
    private List<Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, String>> getOrderImportByDocumentNo() {
        final String whereClause = "I_IsImported = ? AND (Processing = ? OR Processing IS NULL ) " + getClientCheck();
        final String orderBy = "ORDER BY AD_Org_Id , C_BPartner_ID, BillTo_ID, C_BPartner_Location_ID , C_DocType_ID , DocumentNo , I_Order_ID";
        AtomicReference<List<Tuple7<Integer, Integer, Integer, Integer, Integer, Integer, String>>> importOrderSelect = new AtomicReference<>();
        final String getImportOrderSelect = "SELECT  I_Order_ID , AD_Org_ID , C_BPartner_ID , C_BPartner_Location_ID , BillTo_ID  , C_DocType_ID , DocumentNo FROM " + X_I_Order.Table_Name + " WHERE " + whereClause + orderBy;
        List<Object> withParameters = List.of(false, false);
        Try<Void> tryGetImportOrder = DB.runResultSetFunction.apply(null, getImportOrderSelect, withParameters.appendAll(getClientCheckIds()), rows -> {
            importOrderSelect.set(new ResultSetIterable<>(rows, row -> {
                        return Tuple.of(
                                row.getInt(1), // I_Order_ID
                                row.getInt(2), // C_BPartner_ID
                                row.getInt(3), // C_BPartner_ID
                                row.getInt(4), // C_BPartner_Location_ID
                                row.getInt(5), // BillTo_ID
                                row.getInt(6), // C_Doc_Type_ID
                                row.getString(7)); // DocumentNo

                    }).toList()
            );
        });
        return importOrderSelect.get();
    }

    /**
     * Get Import OrderIds For Partner Not Exists
     * @return I_Order_ID[]
     */
    private int[] getImportOrderIdsForPartnerNotExists() {
        final String whereClause = "I_IsImported = ? AND C_BPartner_ID IS NULL " + getClientCheck();
        List<Object> withParameters = List.of(false);
        return new Query(getCtx(), X_I_Order.Table_Name, whereClause, null)
                .setParameters(withParameters.appendAll(getClientCheckIds()).toJavaArray())
                .getIDs();
    }

    /**
     * Get Valid Import Order
     * @param ctx Context
     * @param importOrderId Import Order Id
     * @param trxName
     * @return Option<X_I_Order>
     */
    private Option<X_I_Order> getValidImportOrder(Properties ctx, Integer importOrderId, String trxName) {
        X_I_Order importOrder = new X_I_Order(ctx, importOrderId, trxName);
        if (importOrder.getBPartnerValue() == null) {
            if (importOrder.getEMail() != null)
                importOrder.setBPartnerValue(importOrder.getEMail());
            else if (importOrder.getName() != null)
                importOrder.setBPartnerValue(importOrder.getName());
            else
                return Option.none();
        }
        if (importOrder.getName() == null) {
            if (importOrder.getContactName() != null)
                importOrder.setName(importOrder.getContactName());
            else
                importOrder.setName(importOrder.getBPartnerValue());
        }
        return Option.of(importOrder);
    }

    /**
     * Create Partner
     */
    private void createPartner() {
        //	-- New BPartner ---------------------------------------------------
        //	Go through Order Records w/o C_BPartner_ID
        Arrays.stream(getImportOrderIdsForPartnerNotExists()).forEach(importOrderId -> {
            Trx.run(trxName -> {
                getValidImportOrder(getCtx(), importOrderId, trxName).peek(importOrder -> {
                    //	BPartner
                    MBPartner partner = MBPartner.get(getCtx(), importOrder.getBPartnerValue());
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

    /**
     * Set Client Info
     */
    private void setClientInfo() {
        //	Set Client, Org, IsActive, Created/Updated
        Trx.run(trxName -> {
            int no = 0;
            String setClientInfo = "UPDATE I_Order "
                    + "SET AD_Client_ID = COALESCE (AD_Client_ID," +
                    getClientId() + "),"
                    + " AD_Org_ID = COALESCE (AD_Org_ID," +
                    getOrgId() + "),"
                    + " IsActive = COALESCE (IsActive, 'Y'),"
                    + " Created = COALESCE (Created, SysDate),"
                    + " CreatedBy = COALESCE (CreatedBy, 0),"
                    + " Updated = COALESCE (Updated, SysDate),"
                    + " UpdatedBy = COALESCE (UpdatedBy, 0),"
                    + " I_ErrorMsg = ' ',"
                    + " I_IsImported = 'N' "
                    + "WHERE I_IsImported <> ?  OR I_IsImported IS NULL ";
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setClientInfo, withParameters.toJavaArray(), trxName);
            log.info("Reset=" + no);
        });
    }

    /**
     *  Set Error With Invalid Org
     */
    private void setErrorWithInvalidOrg() {
        //Update Invalid Org
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithInvalidOrg = "UPDATE I_Order o "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
                    + "WHERE (AD_Org_ID IS NULL OR AD_Org_ID = ? "
                    + " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary= ?  OR oo.IsActive = ? )))"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(0, true, false, true);
            no = DB.executeUpdateEx(setErrorWithInvalidOrg, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Org=" + no);
        });
    }

    /**
     * Set DocumentType
     */
    private void setDocumentType() {
        //	Document Type - PO - SO
        Trx.run(trxName -> {
            int no = 0;
            //	PO Document Type Name
            final String setPODocumentTypeId = "UPDATE I_Order o "    //	PO Document Type Name
                    + "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
                    + " AND d.DocBaseType = ?  AND o.AD_Client_ID=d.AD_Client_ID) "
                    + "WHERE C_DocType_ID IS NULL AND IsSOTrx = ?  AND DocTypeName IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withPOParameters = List.of("POO", false, true);
            no = DB.executeUpdateEx(setPODocumentTypeId, withPOParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set PO DocType=" + no);
            //	SO Document Type Name
            final String setSODocumentTypeId = "UPDATE I_Order o "    //	SO Document Type Name
                    + "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
                    + " AND d.DocBaseType = ? AND o.AD_Client_ID=d.AD_Client_ID) "
                    + "WHERE C_DocType_ID IS NULL AND IsSOTrx = ? AND DocTypeName IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withSOParameters = List.of("SOO", true, true);
            no = DB.executeUpdateEx(setSODocumentTypeId, withSOParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);

            log.fine("Set SO DocType=" + no);
            //+ "WHERE C_DocType_ID IS NULL AND IsSOTrx IS NULL AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
            final String setDocumentTypeId = "UPDATE I_Order o "
                    + "SET C_DocType_ID=(SELECT C_DocType_ID FROM C_DocType d WHERE d.Name=o.DocTypeName"
                    + " AND d.DocBaseType IN ( ? , ? ) AND o.AD_Client_ID=d.AD_Client_ID) "
                    //+ "WHERE C_DocType_ID IS NULL AND IsSOTrx IS NULL AND DocTypeName IS NOT NULL AND I_IsImported<>'Y'").append (getClientCheck());
                    + "WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of("SOO", "POO", true);
            no = DB.executeUpdateEx(setDocumentTypeId, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set DocType=" + no);
        });
    }

    /**
     * Set Error With Invalid DocumentType Name
     */
    private void setErrorWithInvalidDocumentTypeName() {
        Trx.run(trxName -> {
            int no = 0;
            //	Error Invalid Doc Type Name
            final String setErrorWithInvalidDocumentTypeName = "UPDATE I_Order "    //	Error Invalid Doc Type Name
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid DocTypeName, ' "
                    + "WHERE C_DocType_ID IS NULL AND DocTypeName IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithInvalidDocumentTypeName, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid DocTypeName=" + no);
        });
    }

    /**
     * Set DocumentType Default
     */
    private void setDocumentTypeDefault() {
        //	DocType Default
        Trx.run(trxName -> {
            int no = 0;
            //	Default PO
            final String setPODocumentTypeDefault = "UPDATE I_Order o "    //	Default PO
                    + "SET C_DocType_ID=(SELECT MAX(C_DocType_ID) FROM C_DocType d WHERE d.IsDefault = ? "
                    + " AND d.DocBaseType = ? AND o.AD_Client_ID=d.AD_Client_ID) "
                    + "WHERE C_DocType_ID IS NULL AND IsSOTrx = ? AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withPOParameters = List.of(true, "POO", false, true);
            no = DB.executeUpdateEx(setPODocumentTypeDefault, withPOParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set PO Default DocType=" + no);
            //	Default SO
            final String setSODocumentTypeDefault = "UPDATE I_Order o "    //	Default SO
                    + "SET C_DocType_ID=(SELECT MAX(C_DocType_ID) FROM C_DocType d WHERE d.IsDefault = ? "
                    + " AND d.DocBaseType = ? AND o.AD_Client_ID=d.AD_Client_ID) "
                    + "WHERE C_DocType_ID IS NULL AND IsSOTrx = ? AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withSOParameters = List.of(true, "SOO", true, true);
            no = DB.executeUpdateEx(setSODocumentTypeDefault, withSOParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set SO Default DocType=" + no);
            final String setDocumentTypeDefault = "UPDATE I_Order o "
                    + "SET C_DocType_ID=(SELECT MAX(C_DocType_ID) FROM C_DocType d WHERE d.IsDefault = ? "
                    + " AND d.DocBaseType IN( ? , ? ) AND o.AD_Client_ID=d.AD_Client_ID) "
                    + "WHERE C_DocType_ID IS NULL AND IsSOTrx IS NULL AND I_IsImported<> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true, "SOO", "POO", true);
            no = DB.executeUpdateEx(setDocumentTypeDefault, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Default DocType=" + no);
        });
    }

    /**
     * Set Error DocumentType Default
     */
    private void setErrorDocumentTypeDefault() {
        Trx.run(trxName -> {
            int no = 0;
            // No DocType
            String setErrorDocumentTypeDefault = "UPDATE I_Order "    // No DocType
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No DocType, ' "
                    + "WHERE C_DocType_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withErrorParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorDocumentTypeDefault, withErrorParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No DocType=" + no);
        });
    }

    /**
     * Set Is SOTrx
     */
    private void setIsSOTrx() {
        //	Set IsSOTrx
        Trx.run(trxName -> {
            int no = 0;
            final String setIsSOTrx = "UPDATE I_Order o SET IsSOTrx='Y' "
                    + "WHERE EXISTS (SELECT * FROM C_DocType d WHERE o.C_DocType_ID=d.C_DocType_ID AND d.DocBaseType='SOO' AND o.AD_Client_ID=d.AD_Client_ID)"
                    + " AND C_DocType_ID IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withSOParameters = List.of(true);
            no = DB.executeUpdateEx(setIsSOTrx, withSOParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set IsSOTrx=Y=" + no);
            final String setPOTrx = "UPDATE I_Order o SET IsSOTrx='N' "
                    + "WHERE EXISTS (SELECT * FROM C_DocType d WHERE o.C_DocType_ID=d.C_DocType_ID AND d.DocBaseType='POO' AND o.AD_Client_ID=d.AD_Client_ID)"
                    + " AND C_DocType_ID IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withPOParameters = List.of(true);
            no = DB.executeUpdateEx(setPOTrx, withPOParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set IsSOTrx=N=" + no);
        });
    }

    /**
     * Set Price List
     */
    private void setPriceList() {
        //	Price List
        Trx.run(trxName -> {
            int no = 0;
            final String setDefaultCurrencyPriceListId = "UPDATE I_Order o "
                    + "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p WHERE p.IsDefault = ?"
                    + " AND p.C_Currency_ID=o.C_Currency_ID AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_PriceList_ID IS NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withDefaultCurrencyParameters = List.of(true, true);
            no = DB.executeUpdateEx(setDefaultCurrencyPriceListId, withDefaultCurrencyParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Default Currency PriceList=" + no);
            final String setDefaultPriceListId = "UPDATE I_Order o "
                    + "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p WHERE p.IsDefault = ? "
                    + " AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_PriceList_ID IS NULL AND C_Currency_ID IS NULL AND I_IsImported<> ? " +
                    getClientCheck();
            List<Object> withDefaultParameters = List.of(true, true);
            no = DB.executeUpdateEx(setDefaultPriceListId, withDefaultParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Default PriceList=" + no);
            final String setCurrencyPriceList = "UPDATE I_Order o "
                    + "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p "
                    + " WHERE p.C_Currency_ID=o.C_Currency_ID AND p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_PriceList_ID IS NULL AND I_IsImported <> ?" +
                    getClientCheck();
            List<Object> withCurrencyParameters = List.of(true);
            no = DB.executeUpdateEx(setCurrencyPriceList, withCurrencyParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Currency PriceList=" + no);
            final String setPriceList = "UPDATE I_Order o "
                    + "SET M_PriceList_ID=(SELECT MAX(M_PriceList_ID) FROM M_PriceList p "
                    + " WHERE p.IsSOPriceList=o.IsSOTrx AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_PriceList_ID IS NULL AND C_Currency_ID IS NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withPriceListParameters = List.of(true);
            no = DB.executeUpdateEx(setPriceList, withPriceListParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set PriceList=" + no);
        });
    }

    /**
     * Set Error With Not Exists Price List
     */
    private void setErrorWithNotExistsPriceList() {
        //No PriceList
        Trx.run(trxName -> {
            int no = 0;
            final String updateErrorWithNotExistsPriceList = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No PriceList, ' "
                    + "WHERE M_PriceList_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(updateErrorWithNotExistsPriceList, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No PriceList=" + no);
        });
    }

    /**
     * Set Order Source
     */
    private void setOrderSource() {
        //Update Order Source
        Trx.run(trxName -> {
            int no = 0;
            final String setOrderSource = "UPDATE I_Order o "
                    + "SET C_OrderSource_ID=(SELECT C_OrderSource_ID FROM C_OrderSource p"
                    + " WHERE o.C_OrderSourceValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE C_OrderSource_ID IS NULL AND C_OrderSourceValue IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setOrderSource, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Order Source=" + no);
        });
    }

    /**
     * Set Error With Not Found Order Source
     */
    private void setErrorWithNotFoundOrderSource() {
        // Set proper error message
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithNotFoundOrderSource = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Not Found Order Source, ' "
                    + "WHERE C_OrderSource_ID IS NULL AND C_OrderSourceValue IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithNotFoundOrderSource, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No OrderSource=" + no);
        });
    }

    /**
     * Set Payment Term
     */
    private void setPaymentTerm() {
        //	Payment Term
        Trx.run(trxName -> {
            int no = 0;
            final String setPaymentTerm = "UPDATE I_Order o "
                    + "SET C_PaymentTerm_ID=(SELECT C_PaymentTerm_ID FROM C_PaymentTerm p"
                    + " WHERE o.PaymentTermValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE C_PaymentTerm_ID IS NULL AND PaymentTermValue IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setPaymentTerm, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set PaymentTerm=" + no);
            final String setDefaultPaymentTerm = "UPDATE I_Order o "
                    + "SET C_PaymentTerm_ID=(SELECT MAX(C_PaymentTerm_ID) FROM C_PaymentTerm p"
                    + " WHERE p.IsDefault = ? AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE C_PaymentTerm_ID IS NULL AND o.PaymentTermValue IS NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withDefaultParameters = List.of(true, true);
            no = DB.executeUpdateEx(setDefaultPaymentTerm, withDefaultParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Default PaymentTerm=" + no);
        });
    }

    /**
     * Set Error With Not Exists Payment Term
     */
    private void setErrorWithNotExistsPaymentTerm() {
        //Error No Payment Term
        Trx.run(trxName -> {
            int no = 0;
            String setErrorWithNotExistsPaymentTerm = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No PaymentTerm, ' "
                    + "WHERE C_PaymentTerm_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithNotExistsPaymentTerm, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No PaymentTerm=" + no);
        });
    }

    /**
     * Set Warehouse
     */
    private void setWarehouse() {
        //	Warehouse
        Trx.run(trxName -> {
            int no = 0;
            final String setWarehouse = "UPDATE I_Order o "
                    + "SET M_Warehouse_ID=(SELECT MAX(M_Warehouse_ID) FROM M_Warehouse w"
                    + " WHERE o.AD_Client_ID=w.AD_Client_ID AND o.AD_Org_ID=w.AD_Org_ID) "
                    + "WHERE M_Warehouse_ID IS NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setWarehouse, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);    //	Warehouse for Org
            if (no != 0)
                log.fine("Set Warehouse=" + no);
            final String setClientWarehouse = "UPDATE I_Order o "
                    + "SET M_Warehouse_ID=(SELECT M_Warehouse_ID FROM M_Warehouse w"
                    + " WHERE o.AD_Client_ID=w.AD_Client_ID) "
                    + "WHERE M_Warehouse_ID IS NULL"
                    + " AND EXISTS (SELECT AD_Client_ID FROM M_Warehouse w WHERE w.AD_Client_ID=o.AD_Client_ID GROUP BY AD_Client_ID HAVING COUNT(*)=1)"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> clientwithParameters = List.of(true);
            no = DB.executeUpdateEx(setClientWarehouse, clientwithParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.fine("Set Only Client Warehouse=" + no);
        });
    }

    /**
     * Set Error Not Exists Warehouse
     */
    private void setErrorNotExistsWarehouse() {
        //ERR=No Warehouse
        Trx.run(trxName -> {
            int no = 0;
            String setErrorNotExistsWarehouse = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Warehouse, ' "
                    + "WHERE M_Warehouse_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorNotExistsWarehouse, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No Warehouse=" + no);
        });
    }

    /**
     * Set Partner From Email
     */
    private void setPartnerFromEmail() {
        Trx.run(trxName -> {
            int no = 0;
            //	BP from EMail
            final String setPartnerFromEmail = "UPDATE I_Order o "
                    + "SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u"
                    + " WHERE o.EMail=u.EMail AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) "
                    + "WHERE C_BPartner_ID IS NULL AND EMail IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setPartnerFromEmail, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set BP from EMail=" + no);
        });
    }

    /**
     * Set Partner From Contact Name
     */
    private void setPartnerFromContactName() {
        //	BP from ContactName
        Trx.run(trxName -> {
            final String setPartnerFromContactName = "UPDATE I_Order o "
                    + "SET (C_BPartner_ID,AD_User_ID)=(SELECT C_BPartner_ID,AD_User_ID FROM AD_User u"
                    + " WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL) "
                    + "WHERE C_BPartner_ID IS NULL AND ContactName IS NOT NULL"
                    + " AND EXISTS (SELECT Name FROM AD_User u WHERE o.ContactName=u.Name AND o.AD_Client_ID=u.AD_Client_ID AND u.C_BPartner_ID IS NOT NULL GROUP BY Name HAVING COUNT(*)=1)"
                    + " AND I_IsImported <> ?" +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            int no = 0;
            no = DB.executeUpdateEx(setPartnerFromContactName, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set BP from ContactName=" + no);
        });
    }

    /**
     * Set Partner From Value
     */
    private void setPartnerFromValue() {
        //	BP from Value
        Trx.run(trxName -> {
            int no = 0;
            final String setPartnerFromValue = "UPDATE I_Order o "
                    + "SET C_BPartner_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner bp"
                    + " WHERE o.BPartnerValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID) "
                    + "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setPartnerFromValue, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set BP from Value=" + no);
            //	Default BP
            final String setDefaultPartnerFromValue = "UPDATE I_Order o "
                    + "SET C_BPartner_ID=(SELECT C_BPartnerCashTrx_ID FROM AD_ClientInfo c"
                    + " WHERE o.AD_Client_ID=c.AD_Client_ID) "
                    + "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NULL AND Name IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withDefaultParameters = List.of(true);
            no = DB.executeUpdateEx(setDefaultPartnerFromValue, withDefaultParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Default BP=" + no);

        });
    }

    /**
     * Set Existing Location With Exact Match
     */
    private void setExistingLocationWithExactMatch() {
        //	Existing Location ? Exact Match
        Trx.run(trxName -> {
            int no = 0;
            final String setExistingLocationWithExactMatch = "UPDATE I_Order o "
                    + "SET (BillTo_ID,C_BPartner_Location_ID)=(SELECT C_BPartner_Location_ID,C_BPartner_Location_ID"
                    + " FROM C_BPartner_Location bpl INNER JOIN C_Location l ON (bpl.C_Location_ID=l.C_Location_ID)"
                    + " WHERE o.C_BPartner_ID=bpl.C_BPartner_ID AND bpl.AD_Client_ID=o.AD_Client_ID"
                    + " AND DUMP(o.Address1)=DUMP(l.Address1) AND DUMP(o.Address2)=DUMP(l.Address2)"
                    + " AND DUMP(o.City)=DUMP(l.City) AND DUMP(o.Postal)=DUMP(l.Postal)"
                    + " AND o.C_Region_ID=l.C_Region_ID AND o.C_Country_ID=l.C_Country_ID) "
                    + "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
                    + " AND I_IsImported = ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(false);
            no = DB.executeUpdateEx(setExistingLocationWithExactMatch, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Found Location=" + no);
            //	Set Bill Location from BPartner
            final String setBillToFromPartner = "UPDATE I_Order o "
                    + "SET BillTo_ID=(SELECT MAX(C_BPartner_Location_ID) FROM C_BPartner_Location l"
                    + " WHERE l.C_BPartner_ID=o.C_BPartner_ID AND o.AD_Client_ID=l.AD_Client_ID"
                    + " AND ((l.IsBillTo = ?  AND o.IsSOTrx = ? ) OR (l.IsPayFrom = ? AND o.IsSOTrx = ? ))"
                    + ") "
                    + "WHERE C_BPartner_ID IS NOT NULL AND BillTo_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withBillToParameters = List.of(true, true, true, false, true);
            no = DB.executeUpdateEx(setBillToFromPartner, withBillToParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set BP BillTo from BP=" + no);
            //	Set Location from BPartner
            final String setPartnerLocationFromPartner = "UPDATE I_Order o "
                    + "SET C_BPartner_Location_ID=(SELECT MAX(C_BPartner_Location_ID) FROM C_BPartner_Location l"
                    + " WHERE l.C_BPartner_ID=o.C_BPartner_ID AND o.AD_Client_ID=l.AD_Client_ID"
                    + " AND ((l.IsShipTo = ? AND o.IsSOTrx = ? ) OR o.IsSOTrx = ? )"
                    + ") "
                    + "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withLocationParameters = List.of(true, true, false, true);
            no = DB.executeUpdateEx(setPartnerLocationFromPartner, withLocationParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set BP Location from BP=" + no);
        });
    }

    /**
     * Set Error With Not Exists Partner Location
     */
    private void setErrorWithNotExistsPartnerLocation() {
        //ERR=No BP Location
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithNotExistsPartnerLocation = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No BP Location, ' "
                    + "WHERE C_BPartner_ID IS NOT NULL AND (BillTo_ID IS NULL OR C_BPartner_Location_ID IS NULL)"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithNotExistsPartnerLocation, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No BP Location=" + no);
        });
    }

    /**
     * Set Country
     */
    private void setCountry() {
        //	Set Country
        Trx.run(trxName -> {
            int no = 0;
            final String setCountry = "UPDATE I_Order o "
                    + "SET C_Country_ID=(SELECT C_Country_ID FROM C_Country c"
                    + " WHERE o.CountryCode=c.CountryCode AND c.AD_Client_ID IN (0, o.AD_Client_ID)) "
                    + "WHERE C_BPartner_ID IS NULL AND C_Country_ID IS NULL AND CountryCode IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setCountry, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Country=" + no);
        });
    }

    /**
     * Set Error With Invalid Country
     */
    private void setErrorWithInvalidCountry() {
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithInvalidCountry = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Country, ' "
                    + "WHERE C_BPartner_ID IS NULL AND C_Country_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithInvalidCountry, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Country=" + no);
        });
    }

    /**
     * Set Region
     */
    private void setRegion() {
        //	Set Region
        Trx.run(trxName -> {
            int no = 0;
            final String setDefaultRegion = "UPDATE I_Order o "
                    + "Set RegionName=(SELECT MAX(Name) FROM C_Region r"
                    + " WHERE r.IsDefault='Y' AND r.C_Country_ID=o.C_Country_ID"
                    + " AND r.AD_Client_ID IN (0, o.AD_Client_ID)) "
                    + "WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL AND RegionName IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withDefaultParameters = List.of(true);
            no = DB.executeUpdateEx(setDefaultRegion, withDefaultParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Region Default=" + no);
            //
            final String setRegion = "UPDATE I_Order o "
                    + "Set C_Region_ID=(SELECT C_Region_ID FROM C_Region r"
                    + " WHERE r.Name=o.RegionName AND r.C_Country_ID=o.C_Country_ID"
                    + " AND r.AD_Client_ID IN (0, o.AD_Client_ID)) "
                    + "WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL AND RegionName IS NOT NULL"
                    + " AND I_IsImported <> ?" +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setRegion, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Region=" + no);
        });
    }

    /**
     * Set Error With Exists Invalid Region
     */
    private void setErrorWithExistsInvalidRegion() {
        //Invalid Region
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithExistsInvalidRegion = "UPDATE I_Order o "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Region, ' "
                    + "WHERE C_BPartner_ID IS NULL AND C_Region_ID IS NULL "
                    + " AND EXISTS (SELECT * FROM C_Country c"
                    + " WHERE c.C_Country_ID=o.C_Country_ID AND c.HasRegion = ? )"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true, true);
            no = DB.executeUpdateEx(setErrorWithExistsInvalidRegion, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Region=" + no);
        });
    }

    /**
     * Set Product
     */
    private void setProduct() {
        //	Product
        Trx.run(trxName -> {
            int no = 0;
            final String setProductFromValue = "UPDATE I_Order o "
                    + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
                    + " WHERE o.ProductValue=p.Value AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_Product_ID IS NULL AND ProductValue IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setProductFromValue, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Product from Value=" + no);
            final String setProductIdFromUPC = "UPDATE I_Order o "
                    + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
                    + " WHERE o.UPC=p.UPC AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_Product_ID IS NULL AND UPC IS NOT NULL"
                    + " AND I_IsImported <> ?" +
                    getClientCheck();
            List<Object> withParametersFromUPC = List.of(true);
            no = DB.executeUpdateEx(setProductIdFromUPC, withParametersFromUPC.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Product from UPC=" + no);
            final String setProductIdFromSKU = "UPDATE I_Order o "
                    + "SET M_Product_ID=(SELECT MAX(M_Product_ID) FROM M_Product p"
                    + " WHERE o.SKU=p.SKU AND o.AD_Client_ID=p.AD_Client_ID) "
                    + "WHERE M_Product_ID IS NULL AND SKU IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParametersProductIdFromSKU = List.of(true);
            no = DB.executeUpdateEx(setProductIdFromSKU, withParametersProductIdFromSKU.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Product fom SKU=" + no);

        });
    }

    /**
     * Set Error With Invalid Product
     */
    private void setErrorWithInvalidProduct() {
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithInvalidProduct = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Product, ' "
                    + "WHERE M_Product_ID IS NULL AND (ProductValue IS NOT NULL OR UPC IS NOT NULL OR SKU IS NOT NULL)"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithInvalidProduct, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Product=" + no);
        });
    }

    /**
     * Set Charge
     */
    private void setCharge() {
        //	Charge
        Trx.run(trxName -> {
            int no = 0;
            final String setCharge = "UPDATE I_Order o "
                    + "SET C_Charge_ID=(SELECT C_Charge_ID FROM C_Charge c"
                    + " WHERE o.ChargeName=c.Name AND o.AD_Client_ID=c.AD_Client_ID) "
                    + "WHERE C_Charge_ID IS NULL AND ChargeName IS NOT NULL AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setCharge, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Charge=" + no);
        });
    }

    /**
     * Set Error With Invalid Charge
     */
    private void setErrorWithInvalidCharge() {
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithInvalidCharge = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Charge, ' "
                    + "WHERE C_Charge_ID IS NULL AND (ChargeName IS NOT NULL)"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithInvalidCharge, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Charge=" + no);
        });
    }

    /**
     * Set Error With Invalid Product And Charge
     */
    private void setErrorWithInvalidProductAndCharge() {
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithInvalidProductAndCharge = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Product and Charge, ' "
                    + "WHERE M_Product_ID IS NOT NULL AND C_Charge_ID IS NOT NULL "
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithInvalidProductAndCharge, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Product and Charge exclusive=" + no);
        });
    }

    /**
     * Set Tax
     */
    private void setTax() {
        Trx.run(trxName -> {
            int no = 0;
            final String setTax = "UPDATE I_Order o "
                    + "SET C_Tax_ID=(SELECT MAX(C_Tax_ID) FROM C_Tax t"
                    + " WHERE o.TaxIndicator=t.TaxIndicator AND o.AD_Client_ID=t.AD_Client_ID) "
                    + "WHERE C_Tax_ID IS NULL AND TaxIndicator IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setTax, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            log.fine("Set Tax=" + no);
        });
    }

    /**
     * Set Error With Invalid Tax
     */
    private void setErrorWithInvalidTax() {
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithInvalidTax = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Tax, ' "
                    + "WHERE C_Tax_ID IS NULL AND TaxIndicator IS NOT NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithInvalidTax, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("Invalid Tax=" + no);
        });
    }

    /**
     * Set Error With That Not Exists Partner
     */
    public void setErrorWithThatNotExistsPartner() {
        Trx.run(trxName -> {
            int no = 0;
            final String setErrorWithThatNotExistsPartner = "UPDATE I_Order "
                    + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No BPartner, ' "
                    + "WHERE C_BPartner_ID IS NULL"
                    + " AND I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setErrorWithThatNotExistsPartner, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
            if (no != 0)
                log.warning("No BPartner=" + no);
        });
    }

    /**
     * Set Not ImportOrder
     */
    private void setNotImportOrder() {
        //	Set Error to indicator to not imported
        Trx.run(trxName -> {
            int no = 0;
            final String setNotImportOrder = "UPDATE I_Order "
                    + "SET I_IsImported = 'N' , Updated=SysDate "
                    + "WHERE I_IsImported <> ? " +
                    getClientCheck();
            List<Object> withParameters = List.of(true);
            no = DB.executeUpdateEx(setNotImportOrder, withParameters.appendAll(getClientCheckIds()).toJavaArray(), trxName);
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
}    //	ImportOrder
