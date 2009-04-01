DROP VIEW RV_DD_ORDERDETAIL;
CREATE OR REPLACE VIEW RV_DD_ORDERDETAIL AS
SELECT l.AD_Client_ID, l.AD_Org_ID, 
	l.IsActive, l.Created, l.CreatedBy, l.Updated, l.UpdatedBy,o.DD_Order_ID,
	o.C_Order_ID, o.DocStatus, o.DocAction, o.C_DocType_ID, o.IsApproved, --o.IsCreditApproved,
	o.SalesRep_ID, 
	o.IsDropShip, 
    o.C_BPartner_ID,
    bp.C_BP_Group_ID,
	o.AD_User_ID,
	o.POReference, 
	o.IsSOTrx,
	l.C_Campaign_ID, l.C_Project_ID, l.C_Activity_ID, 
	--l.C_ProjectPhase_ID, l.C_ProjectTask_ID,
	l.DD_OrderLine_ID, l.DateOrdered, l.DatePromised, l.M_Product_ID, l.M_Locator_ID,l.M_LocatorTo_ID,
	l.M_AttributeSetInstance_ID, productAttribute(l.M_AttributeSetInstance_ID) AS ProductAttribute,
	l.M_AttributeSetInstanceTo_ID, productAttribute(l.M_AttributeSetInstanceTo_ID) AS ProductAttributeTo,
	pasi.M_AttributeSet_ID, pasi.M_Lot_ID, pasi.GuaranteeDate, pasi.Lot, pasi.SerNo,
	l.C_UOM_ID, l.QtyEntered, l.QtyOrdered, l.QtyReserved, l.QtyDelivered, l.Confirmedqty,  l.Qtyintransit, l.TargetQty,
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver,
	l.Description
FROM DD_Order o
  INNER JOIN DD_OrderLine l ON (l.DD_Order_ID=o.DD_Order_ID)
  INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=o.C_BPartner_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasito ON (l.M_AttributeSetInstanceTo_ID=pasito.M_AttributeSetInstance_ID);

-- 1/04/2009 12:50:37 PM CST
-- Adding Description to RV_DD_ORDERDETAIL
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57017,275,0,10,53144,'Description',TO_TIMESTAMP('2009-04-01 12:50:35','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','N','N','Description',TO_TIMESTAMP('2009-04-01 12:50:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 1/04/2009 12:50:37 PM CST
-- Adding Description to RV_DD_ORDERDETAIL
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57017 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 1/04/2009 12:56:18 PM CST
-- Adding Description to RV_DD_ORDERDETAIL
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,275,0,53151,53311,17,131,'DocStatus',TO_TIMESTAMP('2009-04-01 12:56:17','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document','EE01',0,'The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','N','N','Document Status',100,TO_TIMESTAMP('2009-04-01 12:56:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 1/04/2009 12:56:18 PM CST
-- Adding Description to RV_DD_ORDERDETAIL
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53311 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- 1/04/2009 12:57:41 PM CST
-- Adding Description to RV_DD_ORDERDETAIL
UPDATE AD_Process_Para SET AD_Reference_ID=10, AD_Reference_Value_ID=NULL, ColumnName='Description', Description='Description', FieldLength=30, Help='Optional short description of the record', Name='Description',Updated=TO_TIMESTAMP('2009-04-01 12:57:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53311
;

-- 1/04/2009 12:57:41 PM CST
-- Adding Description to RV_DD_ORDERDETAIL
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53311
;

