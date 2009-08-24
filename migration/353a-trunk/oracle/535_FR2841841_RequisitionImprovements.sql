-- 21.08.2009 13:12:04 EEST
-- Requisition Improvements
INSERT INTO AD_Column (AD_Column_ID,IsParent,AD_Client_ID,AD_Org_ID,IsAutocomplete,AD_Table_ID,Help,FieldLength,Created,CreatedBy,Updated,Version,IsActive,Description,ColumnName,IsKey,AD_Reference_ID,IsTranslated,IsMandatory,IsIdentifier,SeqNo,IsSelectionColumn,IsSyncDatabase,AD_Element_ID,IsUpdateable,EntityType,Name,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES (57955,'N',0,0,'N',703,'The UOM defines a unique non monetary Unit of Measure',10,TO_DATE('2009-08-21 13:12:02','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-08-21 13:12:02','YYYY-MM-DD HH24:MI:SS'),0,'Y','Unit of Measure','C_UOM_ID','N',30,'N','N','N',0,'N','N',215,'N','D','UOM',0,'N','N','Y')
;

-- 21.08.2009 13:12:04 EEST
-- Requisition Improvements
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57955 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 21.08.2009 13:12:06 EEST
-- Requisition Improvements
INSERT INTO AD_Field (IsFieldOnly,DisplayLength,AD_Field_ID,Help,IsEncrypted,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,UpdatedBy,IsActive,Created,IsSameLine,IsHeading,CreatedBy,AD_Client_ID,Updated,Description,AD_Org_ID,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES ('N',10,57388,'The UOM defines a unique non monetary Unit of Measure','N','UOM',642,57955,'N',0,'Y',TO_DATE('2009-08-21 13:12:04','YYYY-MM-DD HH24:MI:SS'),'N','N',0,0,TO_DATE('2009-08-21 13:12:04','YYYY-MM-DD HH24:MI:SS'),'Unit of Measure',0,'N','Y','D')
;

-- 21.08.2009 13:12:06 EEST
-- Requisition Improvements
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57388 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 21.08.2009 13:12:07 EEST
-- Requisition Improvements
INSERT INTO AD_Field (IsFieldOnly,DisplayLength,AD_Field_ID,Help,IsEncrypted,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,UpdatedBy,IsActive,Created,IsSameLine,IsHeading,CreatedBy,AD_Client_ID,Updated,Description,AD_Org_ID,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES ('N',10,57389,'The UOM defines a unique non monetary Unit of Measure','N','UOM',768,57955,'N',0,'Y',TO_DATE('2009-08-21 13:12:06','YYYY-MM-DD HH24:MI:SS'),'N','N',0,0,TO_DATE('2009-08-21 13:12:06','YYYY-MM-DD HH24:MI:SS'),'Unit of Measure',0,'N','Y','D')
;

-- 21.08.2009 13:12:07 EEST
-- Requisition Improvements
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57389 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 21.08.2009 13:12:14 EEST
-- Requisition Improvements
ALTER TABLE M_RequisitionLine ADD C_UOM_ID NUMBER(10) DEFAULT NULL 
;

-- 21.08.2009 13:13:59 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57388
;

-- 21.08.2009 13:14:00 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=13014
;

-- 21.08.2009 13:14:00 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=10037
;

-- 21.08.2009 13:14:00 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=10034
;

-- 21.08.2009 13:14:00 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10036
;

-- 21.08.2009 13:14:00 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=10035
;

-- 21.08.2009 13:14:00 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=10196
;

-- 21.08.2009 13:15:19 EEST
-- Requisition Improvements
UPDATE AD_Field SET IsSameLine='Y', IsReadOnly='Y', DisplayLogic='@C_Charge_ID@!0',Updated=TO_DATE('2009-08-21 13:15:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57388
;

-- 21.08.2009 13:15:27 EEST
-- Requisition Improvements
UPDATE AD_Field SET DisplayLogic='@C_Charge_ID@=0',Updated=TO_DATE('2009-08-21 13:15:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57388
;

-- 21.08.2009 13:15:42 EEST
-- Requisition Improvements
UPDATE AD_Field SET DisplayLogic='@C_Charge_ID@=0',Updated=TO_DATE('2009-08-21 13:15:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10043
;

-- 21.08.2009 13:38:07 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=57389
;

-- 21.08.2009 13:38:07 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=12483
;

-- 21.08.2009 13:38:07 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=12487
;

-- 21.08.2009 13:38:07 EEST
-- Requisition Improvements
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=12479
;

-- 21.08.2009 13:38:21 EEST
-- Requisition Improvements
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-08-21 13:38:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57389
;

-- 21.08.2009 13:38:30 EEST
-- Requisition Improvements
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2009-08-21 13:38:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57389
;

-- 21.08.2009 13:40:49 EEST
-- Requisition Improvements
INSERT INTO AD_Val_Rule (Name,Type,Code,CreatedBy,Updated,UpdatedBy,Created,AD_Val_Rule_ID,AD_Client_ID,IsActive,AD_Org_ID,EntityType) VALUES ('M_Product(Purchased)','S','M_Product.IsSummary=''N'' AND M_Product.IsActive=''Y'' AND M_Product.IsPurchased=''Y''',0,TO_DATE('2009-08-21 13:40:48','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-08-21 13:40:48','YYYY-MM-DD HH24:MI:SS'),52058,0,'Y',0,'D')
;

-- 21.08.2009 13:40:49 EEST
-- Requisition Improvements
-- INSERT INTO AD_Val_Rule_Trl (AD_Language,AD_Val_Rule_ID, Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Val_Rule_ID, t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Val_Rule t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Val_Rule_ID=52058 AND EXISTS (SELECT * FROM AD_Val_Rule_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Val_Rule_ID!=t.AD_Val_Rule_ID)
-- ;

-- 21.08.2009 13:42:15 EEST
-- Requisition Improvements
UPDATE AD_Column SET AD_Val_Rule_ID=52058,Updated=TO_DATE('2009-08-21 13:42:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11501
;

update M_RequisitionLine rl set
C_UOM_ID=(select p.C_UOM_ID from M_Product p where p.M_Product_ID=rl.M_Product_ID)
where M_Product_ID is not null and C_UOM_ID is null;

-- Update M_Product.IsPurchased:
update M_Product p set IsPurchased='Y'
where p.IsPurchased='N' and p.IsActive='Y' and p.IsSummary='N'
and (
	exists (select 1 from M_RequisitionLine rl where rl.M_Product_ID=p.M_Product_ID)
	or exists (select 1 from C_OrderLine ol
				inner join C_Order o on (o.C_Order_ID=ol.C_Order_ID)
				where ol.M_Product_ID=p.M_Product_ID and o.IsSOTrx='N')
	or exists (select 1 from C_InvoiceLine il
				inner join C_Invoice i on (i.C_Invoice_ID=il.C_Invoice_ID)
				where il.M_Product_ID=p.M_Product_ID and i.IsSOTrx='N')
)
;

