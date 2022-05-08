/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.MMDocument;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

/**
 * @author Teo Sarca, www.arhipac.ro
 */
public final class InventoryUtil {

    public static MProduct getCreateProduct(Properties ctx, String value, String MMPolicy,
            String trxName) {

        MProduct product = new Query(ctx, I_M_Product.Table_Name, "Value=?", trxName)
                .setParameters(value)
                .setOnlyActiveRecords(true)
                .setClient_ID()
                .firstOnly();
        if (product == null) {
            product = new MProduct(ctx, 0, trxName);
        }
        product.setValue(value);
        product.setName(value);
        setGeneratedTag(product);
        product.setProductType(MProduct.PRODUCTTYPE_Item);
        product.setIsStocked(true);
        product.setC_UOM_ID(100); // Each
        product.setC_TaxCategory_ID(getDefault_TaxCategory_ID(ctx, trxName));
        product.setM_Product_Category_ID(
                getCreateProductCategory(ctx, value, MMPolicy, trxName).get_ID());
        product.saveEx();
        //
        getCreateProductPrice(ctx, "#PO_PriceList_ID", product.get_ID(), 10, trxName);
        getCreateProductPrice(ctx, "#SO_PriceList_ID", product.get_ID(), 20, trxName);
        //
        return product;

    }

    public static MProductPrice getCreateProductPrice(Properties ctx, String ctxPriceList,
            int M_Product_ID, int price, String trxName) {

        int M_PriceList_ID = Env.getContextAsInt(ctx, ctxPriceList);
        MPriceList pl = MPriceList.get(ctx, M_PriceList_ID, trxName);
        MPriceListVersion plv = pl.getPriceListVersion(null);
        //
        BigDecimal priceBD = BigDecimal.valueOf(price);
        MProductPrice pp = MProductPrice.get(ctx, plv.get_ID(), M_Product_ID, trxName);
        if (pp == null) {
            pp = new MProductPrice(plv, M_Product_ID, priceBD, priceBD, priceBD);
        }
        pp.setPrices(priceBD, priceBD, priceBD);
        pp.saveEx();
        return pp;

    }

    public static MProductCategory getCreateProductCategory(Properties ctx, String value,
            String MMPolicy, String trxName) {

        if (MMPolicy == null)
            MMPolicy = MProductCategory.MMPOLICY_FiFo;
        final String whereClause = I_M_Product_Category.COLUMNNAME_Value + "=?";
        MProductCategory pc = new Query(ctx, I_M_Product_Category.Table_Name, whereClause, trxName)
                .setParameters(value)
                .setOnlyActiveRecords(true)
                .setClient_ID()
                .firstOnly();
        if (pc == null) {
            pc = new MProductCategory(ctx, 0, trxName);
        }
        pc.setValue(value);
        pc.setName(value);
        setGeneratedTag(pc);
        pc.setMMPolicy(MMPolicy);
        //
        pc.saveEx();
        return pc;

    }

    public static int getDefault_TaxCategory_ID(Properties ctx, String trxName) {

        MTaxCategory taxCategory = new Query(ctx, MTaxCategory.Table_Name, "IsDefault='Y'", trxName)
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .firstOnly();
        return taxCategory != null ? taxCategory.get_ID() : 0;

    }

    public static MBPartner getCreatePartner(Properties ctx, String value, String trxName) {

        final String whereClause = MBPartner.COLUMNNAME_Value + "=?";
        MBPartner bp = new Query(ctx, I_C_BPartner.Table_Name, whereClause, trxName)
                .setParameters(value)
                .setClient_ID()
                .firstOnly();
        if (bp == null) {
            bp = new MBPartner(ctx, 0, null);
        }
        bp.setValue(value);
        bp.setName(value);
        setGeneratedTag(bp);
        bp.setIsCustomer(true);
        bp.setIsVendor(true);
        bp.setC_BP_Group_ID(MBPGroup.getDefault(ctx).get_ID());
        bp.saveEx();
        //
        if (bp.getLocations(false).length == 0) {
            MLocation loc = new MLocation(ctx, 0, null);
            loc.saveEx();
            //
            MBPartnerLocation bpl = new MBPartnerLocation(bp);
            bpl.setC_Location_ID(loc.get_ID());
            bpl.saveEx();
        }
        return bp;

    }

    public static MOrder createOrder(Properties ctx, MMDocument doc, String trxName) {

        int M_PriceList_ID;
        boolean isSOTrx;
        if (MDocType.DOCBASETYPE_SalesOrder.equals(doc.DocBaseType)) {
            M_PriceList_ID = Env.getContextAsInt(ctx, "SO_PriceList_ID");
            isSOTrx = true;
        } else if (MDocType.DOCBASETYPE_PurchaseOrder.equals(doc.DocBaseType)) {
            M_PriceList_ID = Env.getContextAsInt(ctx, "PO_PriceList_ID");
            isSOTrx = false;
        } else {
            throw new IllegalArgumentException("DocBaseType not supported - " + doc);
        }
        //
        int AD_Org_ID = getFirst_Org_ID(ctx, trxName);
        MLocator locator = getCreateLocator(ctx, AD_Org_ID, doc.LocatorValue, doc.LocatorValue,
                trxName);
        //
        MOrder order = new MOrder(ctx, 0, trxName);
        order.setAD_Org_ID(AD_Org_ID);
        order.setIsSOTrx(isSOTrx);
        if (!doc.DocSubBaseType.isEmpty())
            order.setC_DocTypeTarget_ID(doc.DocSubBaseType);
        else
            order.setC_DocTypeTarget_ID();
        order.setDateOrdered(doc.Date);
        order.setDateAcct(doc.Date);
        order.setDatePromised(doc.Date);
        order.setBPartner(getCreatePartner(ctx, doc.BPValue, trxName));
        order.setM_PriceList_ID(M_PriceList_ID);
        order.setM_Warehouse_ID(locator.getM_Warehouse_ID());
        setGeneratedTag(order);
        order.saveEx();
        //
        MProduct product = getCreateProduct(ctx, doc.ProductValue, null, trxName);
        MOrderLine line = new MOrderLine(order);
        line.setProduct(product);
        line.setQty(doc.Qty);
        line.saveEx();
        //
        doc.document = order;
        processDocument(doc, MOrder.DOCACTION_Complete, MOrder.DOCSTATUS_Completed);
        return order;

    }

    public static MInOut createInOut(Properties ctx, MMDocument doc, String trxName) {

        MOrder order;
        if (MDocType.DOCBASETYPE_MaterialReceipt.equals(doc.DocBaseType)) {
            order = (MOrder) doc.scenario.get(MDocType.DOCBASETYPE_PurchaseOrder,
                    doc.PODocumentNo).document;
        } else if (MDocType.DOCBASETYPE_MaterialDelivery.equals(doc.DocBaseType)) {
            order = (MOrder) doc.scenario.get(MDocType.DOCBASETYPE_SalesOrder,
                    doc.PODocumentNo).document;
        } else {
            throw new IllegalArgumentException("DocBaseType not supported - " + doc);
        }

//		if (trxName != null && trxName.equals(order.get_TrxName()))
//			throw new AdempiereException("Internal exception - not same trxName");

        int docTypeId = MDocType.getDocType(doc.DocBaseType, order.getAD_Org_ID());
        MInOut io = new MInOut(order, docTypeId, doc.Date);
        setGeneratedTag(io);
        io.saveEx();
        //
        MInOutLine iol = null;
        for (MOrderLine oline : order.getLines(true, null)) {
            iol = new MInOutLine(io);
            iol.setOrderLine(oline, 0, doc.Qty);
            iol.setQty(doc.Qty);
            iol.saveEx();
            break;
        }
        //
        doc.document = io;
        processDocument(doc, MInOut.DOCACTION_Complete, MInOut.DOCSTATUS_Completed);

        if (!Util.isEmpty(doc.ASI)) {
            iol.load(trxName);
            doc.scenario.registerASICode(doc.ASI, iol.getM_AttributeSetInstance_ID(),
                    !io.isSOTrx());
        }

        return io;

    }

    public static MMovement createMovement(Properties ctx, MMDocument doc, String trxName) {

        int AD_Org_ID = getFirst_Org_ID(ctx, trxName);
        MProduct product = getCreateProduct(ctx, doc.ProductValue, null, trxName);
        MLocator locator = getCreateLocator(ctx, AD_Org_ID, doc.LocatorValue, doc.LocatorValue,
                trxName);
        MLocator locatorTo = getCreateLocator(ctx, AD_Org_ID, doc.LocatorValueTo,
                doc.LocatorValueTo, trxName);
        //
        MMovement m = new MMovement(ctx, 0, trxName);
        m.setAD_Org_ID(AD_Org_ID);
        m.setMovementDate(doc.Date);
        m.saveEx();
        //
        MMovementLine line = new MMovementLine(m);
        line.setM_Product_ID(product.get_ID());
        line.setM_Locator_ID(locator.get_ID());
        line.setM_LocatorTo_ID(locatorTo.get_ID());
        line.setMovementQty(doc.Qty);
        line.saveEx();
        //
        doc.document = m;
        processDocument(doc, MMovement.DOCACTION_Complete, MMovement.DOCSTATUS_Completed);
        return m;

    }

    public static MInventory createInventory(Properties ctx, MMDocument doc, String trxName) {

        int AD_Org_ID = getFirst_Org_ID(ctx, trxName);
        MProduct product = getCreateProduct(ctx, doc.ProductValue, null, trxName);
        MLocator locator = getCreateLocator(ctx, AD_Org_ID, doc.LocatorValue, doc.LocatorValue,
                trxName);
        //
        MInventory inv = new MInventory(ctx, 0, trxName);
        inv.setAD_Org_ID(AD_Org_ID);
        // inv.setIsInternalUseInventory(true);
        inv.setMovementDate(doc.Date);
        inv.setM_Warehouse_ID(locator.getM_Warehouse_ID());
        setGeneratedTag(inv);
        inv.saveEx();
        //
        MInventoryLine line = new MInventoryLine(inv,
                locator.get_ID(), product.get_ID(), 0,
                null, null);
        line.setQtyInternalUse(doc.Qty);
        line.setC_Charge_ID(getCreateCharge(ctx, "junit-charge", trxName).get_ID());
        line.saveEx();
        //
        doc.document = inv;
        processDocument(doc, MInventory.DOCACTION_Complete, MInventory.DOCSTATUS_Completed);

        if (!Util.isEmpty(doc.ASI)) {
            line.load(trxName);
            doc.scenario.registerASICode(doc.ASI, line.getM_AttributeSetInstance_ID(),
                    line.getQtyInternalUse().signum() <= 0);
        }

        return inv;

    }

    public static int getFirst_Org_ID(Properties ctx, String trxName) {

        int AD_Org_ID = Env.getAD_Org_ID(ctx);
        if (AD_Org_ID > 0)
            return AD_Org_ID;
        String sql = "SELECT MIN(AD_Org_ID) FROM AD_Org WHERE AD_Client_ID=?";
        return DB.getSQLValueEx(trxName, sql, Env.getAD_Client_ID(ctx));

    }

    /**
     * Helper Method : Create Warehouse
     */
    public static MWarehouse getCreateWarehouse(Properties ctx, int AD_Org_ID, String value,
            String trxName) {

        if (AD_Org_ID <= 0)
            AD_Org_ID = getFirst_Org_ID(ctx, trxName);
        final String whereClause = "AD_Org_ID=? AND Value=?";
        MWarehouse wh = new Query(ctx, I_M_Warehouse.Table_Name, whereClause, trxName)
                .setParameters(AD_Org_ID, value)
                .setClient_ID()
                .firstOnly();
        if (wh != null)
            return wh;
        wh = new MWarehouse(ctx, 0, null);
        wh.setAD_Org_ID(AD_Org_ID);
        wh.setValue(value);
        wh.setName(value);

        MLocation loc = new MLocation(ctx, 0, null);
        loc.saveEx();
        wh.setC_Location_ID(loc.get_ID());
        wh.saveEx();
        return wh;

    }

    /**
     * Helper Method : Create Locator
     */
    public static MLocator getCreateLocator(Properties ctx, int AD_Org_ID, String whValue,
            String value, String trxName) {

        if (AD_Org_ID <= 0)
            AD_Org_ID = getFirst_Org_ID(ctx, trxName);
        MWarehouse wh = getCreateWarehouse(ctx, AD_Org_ID, whValue, trxName);
        MLocator locator = null;
        for (MLocator loc : wh.getLocators(false)) {
            if (loc.getValue().equals(value)) {
                locator = loc;
                break;
            }
        }
        if (locator == null) {
            locator = new MLocator(wh, value);
            locator.setXYZ(value, value, value);
        }
        if (wh.getLocators(false).length == 0) {
            locator.setIsDefault(true);
        }
        locator.saveEx();
        //
        return locator;

    }

    public static MPriceList getCreatePriceList(Properties ctx, String value, boolean IsSOPriceList,
            String trxName) {

        int C_Currency_ID = Env.getContextAsInt(ctx, "$C_Currency_ID");
        String whereClause = MPriceList.COLUMNNAME_Name + "=?"
                + " AND " + MPriceList.COLUMNNAME_IsSOPriceList + "=?"
                + " AND " + MPriceList.COLUMNNAME_C_Currency_ID + "=?";
        MPriceList pl = new Query(ctx, MPriceList.Table_Name, whereClause, trxName)
                .setParameters(new Object[] { value, IsSOPriceList, C_Currency_ID })
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .firstOnly();
        if (pl == null) {
            pl = new MPriceList(ctx, 0, trxName);
            pl.setName(value);
            pl.setIsSOPriceList(IsSOPriceList);
            pl.setC_Currency_ID(C_Currency_ID);
        }
        setGeneratedTag(pl);
        pl.setIsTaxIncluded(false);
        pl.saveEx();
        MPriceList.get(ctx, pl.get_ID(), trxName);
        
        Timestamp ValidFrom = TimeUtil.getDay(1970, 1, 1);
        MPriceListVersion plv = pl.getPriceListVersion(ValidFrom);
        if (plv == null) {
            plv = new MPriceListVersion(pl);
            plv.setValidFrom(ValidFrom);
            plv.setM_DiscountSchema_ID(getM_DiscountSchema_ID(ctx, trxName));
            setGeneratedTag(plv);
            plv.saveEx();
        }
        //
        return pl;

    }

    public static int getM_DiscountSchema_ID(Properties ctx, String trxName) {

        int AD_Client_ID = Env.getAD_Client_ID(ctx);
        final String sql = "SELECT MIN(M_DiscountSchema_ID) FROM M_DiscountSchema" +
                " WHERE AD_Client_ID=?";
        return DB.getSQLValueEx(trxName, sql, AD_Client_ID);

    }

    public static void setGeneratedTag(PO po) {

        String desc = "Generated by " + InventoryUtil.class
                + " on " + new Timestamp(System.currentTimeMillis());
        po.set_ValueOfColumn("Description", desc);

    }

    public static void processDocument(MMDocument doc,
            String docAction, String targetDocStatus) {

        PO po = (PO) doc.document;
        po.load(po.get_TrxName());
        po.set_ValueOfColumn("DocAction", docAction);

        try {
            if (!doc.document.processIt(docAction))
                throw new AdempiereException(doc.document.getProcessMsg());
        } catch (Exception e) {
            throw (e instanceof AdempiereException ? (AdempiereException) e
                    : new AdempiereException(e));
        }
        po.saveEx();

        if (targetDocStatus != null && !targetDocStatus.equals(doc.document.getDocStatus())) {
            throw new AdempiereException("Doc process error " + doc
                    + " (TargetDocStatus=" + targetDocStatus
                    + ", DocStatus=" + doc.document.getDocStatus()
                    + ")");
        }
        if (DocAction.STATUS_Completed.equals(doc.document.getDocStatus())) {
            // Track ASI:
            if (!Util.isEmpty(doc.ASI)) {

            }
        }

    }

    public static MCharge getCreateCharge(Properties ctx, String value, String trxName) {

        String whereClause = MCharge.COLUMNNAME_Name + "=?";
        MCharge charge = new Query(ctx, MCharge.Table_Name, whereClause, trxName)
                .setParameters(new Object[] { value })
                .setOnlyActiveRecords(true)
                .setClient_ID()
                .firstOnly();
        if (charge == null) {
            charge = new MCharge(ctx, 0, trxName);
            charge.setName(value);
            setGeneratedTag(charge);
            charge.saveEx();
        }
        return charge;

    }

}
