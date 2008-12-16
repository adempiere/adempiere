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
	l.QtyOrdered-l.QtyDelivered AS QtyToDeliver
FROM DD_Order o
  INNER JOIN DD_OrderLine l ON (l.DD_Order_ID=o.DD_Order_ID)
  INNER JOIN C_BPartner bp ON (bp.C_BPartner_ID=o.C_BPartner_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasi ON (l.M_AttributeSetInstance_ID=pasi.M_AttributeSetInstance_ID)
  LEFT OUTER JOIN M_AttributeSetInstance pasito ON (l.M_AttributeSetInstanceTo_ID=pasito.M_AttributeSetInstance_ID);

-- Dec 10, 2008 10:54:34 PM ECT
-- Adding BP Group in Distribution Order Detail
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,1383,0,53151,53262,19,'C_BP_Group_ID',TO_DATE('2008-12-10 22:54:33','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE01',10,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','Business Partner Group',30,TO_DATE('2008-12-10 22:54:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 10, 2008 10:54:35 PM ECT
-- Adding BP Group in Distribution Order Detail
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53262 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Dec 10, 2008 10:54:54 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-12-10 22:54:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53262
;

-- Dec 10, 2008 10:54:57 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=50,Updated=TO_DATE('2008-12-10 22:54:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53227
;

-- Dec 10, 2008 10:54:59 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=60,Updated=TO_DATE('2008-12-10 22:54:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53228
;

-- Dec 10, 2008 10:55:02 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=70,Updated=TO_DATE('2008-12-10 22:55:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53230
;

-- Dec 10, 2008 10:55:07 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=80,Updated=TO_DATE('2008-12-10 22:55:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53231
;

-- Dec 10, 2008 10:55:16 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=90,Updated=TO_DATE('2008-12-10 22:55:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53229
;

-- Dec 10, 2008 11:01:58 PM ECT
-- Adding BP Group in Distribution Order Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56523,187,0,19,53144,'C_BPartner_ID',TO_DATE('2008-12-10 23:01:55','YYYY-MM-DD HH24:MI:SS'),0,'Identifies a Business Partner','EE01',10,'A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson','Y','N','N','N','N','N','N','N','N','N','Business Partner ',TO_DATE('2008-12-10 23:01:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 10, 2008 11:01:58 PM ECT
-- Adding BP Group in Distribution Order Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56523 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 10, 2008 11:01:58 PM ECT
-- Adding BP Group in Distribution Order Detail
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56524,1383,0,19,53144,'C_BP_Group_ID',TO_DATE('2008-12-10 23:01:58','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE01',10,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','N','N','N','N','N','N','N','N','N','Business Partner Group',TO_DATE('2008-12-10 23:01:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 10, 2008 11:01:58 PM ECT
-- Adding BP Group in Distribution Order Detail
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56524 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 10, 2008 11:04:31 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=40,Updated=TO_DATE('2008-12-10 23:04:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53226
;

-- Dec 10, 2008 11:04:37 PM ECT
-- Adding BP Group in Distribution Order Detail
UPDATE AD_Process_Para SET SeqNo=30,Updated=TO_DATE('2008-12-10 23:04:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53262
;

