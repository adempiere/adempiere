CREATE OR REPLACE VIEW RV_COMMISSIONRUNDETAIL
(AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, CREATEDBY, 
 UPDATED, UPDATEDBY, C_COMMISSIONRUN_ID, DOCUMENTNO, DESCRIPTION, 
 STARTDATE, GRANDTOTAL, PROCESSED, C_COMMISSION_ID, COMMISSION_BPARTNER_ID, 
 C_COMMISSIONAMT_ID, COMMISSIONCONVERTEDAMT, COMMISSIONQTY, COMMISSIONAMT, C_COMMISSIONDETAIL_ID, 
 REFERENCE, C_ORDERLINE_ID, C_INVOICELINE_ID, INFO, C_CURRENCY_ID, 
 ACTUALAMT, CONVERTEDAMT, ACTUALQTY, INVOICEDOCUMENTNO, DATEDOC, 
 M_PRODUCT_ID, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, AD_USER_ID, C_DOCTYPE_ID)
AS 
SELECT cr.AD_Client_ID, cr.AD_Org_ID, cr.IsActive, cr.Created,cr.CreatedBy, cr.Updated,cr.UpdatedBy,
    -- Run
    cr.C_CommissionRun_ID, cr.DocumentNo, cr.Description, 
    cr.StartDate, cr.GrandTotal, cr.Processed,
    -- Commission
    c.C_Commission_ID, c.C_BPartner_ID AS Commission_BPartner_ID,
    --  Commission Amount
    ca.C_CommissionAmt_ID, 
    ca.ConvertedAmt AS CommissionConvertedAmt, ca.ActualQty AS CommissionQty, 
    ca.CommissionAmt,
    --  Commission Detail
    cd.C_CommissionDetail_ID,
    cd.Reference,
    cd.C_OrderLine_ID,
    cd.C_InvoiceLine_ID,
    cd.Info,
    cd.C_Currency_ID, cd.ActualAmt, cd.ConvertedAmt,
    cd.ActualQty,
    -- Invoice/Order
    i.DocumentNo AS InvoiceDocumentNo,
    COALESCE (i.DateInvoiced, o.DateOrdered) AS DateDoc,
    COALESCE (il.M_Product_ID,ol.M_Product_ID) AS M_Product_ID,
    COALESCE (i.C_BPartner_ID,o.C_BPartner_ID) AS C_BPartner_ID,
    COALESCE (i.C_BPartner_Location_ID,o.C_BPartner_Location_ID) AS C_BPartner_Location_ID,
    COALESCE (i.AD_User_ID,o.AD_User_ID) AS AD_User_ID,
    COALESCE (i.C_DocType_ID,o.C_DocType_ID) AS C_DocType_ID
FROM C_CommissionRun cr
    INNER JOIN C_Commission c ON (cr.C_Commission_ID=c.C_Commission_ID)
    INNER JOIN C_CommissionAmt ca ON (cr.C_CommissionRun_ID=ca.C_CommissionRun_ID)
    INNER JOIN C_CommissionDetail cd ON (ca.C_CommissionAmt_ID=cd.C_CommissionAmt_ID)
    LEFT OUTER JOIN C_OrderLine ol ON (cd.C_OrderLine_ID=ol.C_OrderLine_ID)
    LEFT OUTER JOIN C_InvoiceLine il ON (cd.C_InvoiceLine_ID=il.C_InvoiceLine_ID)
    LEFT OUTER JOIN C_Order o ON (ol.C_Order_ID=o.C_Order_ID)
    LEFT OUTER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID);



