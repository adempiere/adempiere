CREATE OR REPLACE VIEW RV_M_REQUISITION
(M_REQUISITION_ID, AD_CLIENT_ID, AD_ORG_ID, ISACTIVE, CREATED, 
 CREATEDBY, UPDATED, UPDATEDBY, DOCUMENTNO, DESCRIPTION, 
 HELP, AD_USER_ID, M_PRICELIST_ID, M_WAREHOUSE_ID, ISAPPROVED, 
 PRIORITYRULE, DATEREQUIRED, TOTALLINES, DOCACTION, DOCSTATUS, 
 PROCESSED, M_REQUISITIONLINE_ID, LINE, QTY, QTYORDERED,
 M_PRODUCT_ID, LINEDESCRIPTION, PRICEACTUAL, LINENETAMT)
AS 
SELECT r.M_Requisition_ID,
    r.AD_Client_ID, r.AD_Org_ID, r.IsActive, r.Created, r.CreatedBy, r.Updated, r.UpdatedBy,
    r.DocumentNo, r.Description, r.Help,
    r.AD_User_ID, r.M_PriceList_ID, r.M_Warehouse_ID, r.IsApproved, r.PriorityRule,
    r.DateRequired, r.TotalLines, r.DocAction, r.DocStatus, r.Processed,
    l.M_RequisitionLine_ID, l.Line,
    l.Qty,
	(CASE WHEN l.C_OrderLine_ID IS NOT NULL THEN l.Qty ELSE 0 END) AS QtyOrdered,
	l.M_Product_ID,
    l.Description AS LineDescription,
    l.PriceActual, l.LineNetAmt
FROM M_Requisition r
  INNER JOIN M_RequisitionLine l ON (r.M_Requisition_ID=l.M_Requisition_ID);



-- 24.08.2009 15:49:41 EEST
-- Requisition Improvements
INSERT INTO AD_Column (AD_Column_ID,IsParent,AD_Client_ID,AD_Org_ID,AD_Table_ID,Help,FieldLength,Created,CreatedBy,Updated,Version,IsActive,Description,ColumnName,IsKey,AD_Reference_ID,IsTranslated,IsMandatory,IsIdentifier,IsSelectionColumn,AD_Element_ID,IsUpdateable,EntityType,Name,UpdatedBy,IsAlwaysUpdateable,IsEncrypted) VALUES (57956,'N',0,0,711,'The Ordered Quantity indicates the quantity of a product that was ordered.',22,TO_DATE('2009-08-24 15:49:39','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-08-24 15:49:39','YYYY-MM-DD HH24:MI:SS'),0,'Y','Ordered Quantity','QtyOrdered','N',29,'N','N','N','N',531,'N','D','Ordered Quantity',0,'N','N')
;

-- 24.08.2009 15:49:42 EEST
-- Requisition Improvements
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57956 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 24.08.2009 15:50:36 EEST
-- Requisition Improvements
UPDATE AD_ReportView SET WhereClause='Qty<>QtyOrdered',Updated=TO_DATE('2009-08-24 15:50:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ReportView_ID=146
;

