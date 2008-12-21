-- 16/10/2008 15:11:55
-- Add charge to order import
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56364,968,0,19,591,'C_Charge_ID',TO_TIMESTAMP('2008-10-16 15:11:52','YYYY-MM-DD HH24:MI:SS'),100,'Additional document charges','D',10,'The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Charge',0,TO_TIMESTAMP('2008-10-16 15:11:52','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 16/10/2008 15:11:55
-- Add charge to order import
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56364 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 16/10/2008 15:12:04
-- Add charge to order import
ALTER TABLE I_Order ADD COLUMN C_Charge_ID NUMERIC(10)
;

-- 16/10/2008 15:13:12
-- Add charge to order import
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56365,2096,0,10,591,'ChargeName',TO_TIMESTAMP('2008-10-16 15:13:10','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Charge','D',60,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Charge Name',0,TO_TIMESTAMP('2008-10-16 15:13:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 16/10/2008 15:13:12
-- Add charge to order import
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56365 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 16/10/2008 15:13:15
-- Add charge to order import
ALTER TABLE I_Order ADD COLUMN ChargeName VARCHAR(60)
;

-- 16/10/2008 15:17:33
-- Add charge to order import
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56364,56402,0,512,TO_TIMESTAMP('2008-10-16 15:17:32','YYYY-MM-DD HH24:MI:SS'),100,'Additional document charges',10,'D','The Charge indicates a type of Charge (Handling, Shipping, Restocking)','Y','Y','Y','N','N','N','N','N','Charge',434,0,TO_TIMESTAMP('2008-10-16 15:17:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 16/10/2008 15:17:33
-- Add charge to order import
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56402 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 16/10/2008 15:18:50
-- Add charge to order import
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,56365,56403,0,512,TO_TIMESTAMP('2008-10-16 15:18:49','YYYY-MM-DD HH24:MI:SS'),100,'Name of the Charge',40,'D','Y','Y','Y','N','N','N','N','Y','Charge Name',436,0,TO_TIMESTAMP('2008-10-16 15:18:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 16/10/2008 15:18:50
-- Add charge to order import
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56403 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

