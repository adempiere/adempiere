-- 2008-dec-05 09:18:11 CET
-- [ 2350428 ] Add default locator to replenishment line
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56518,448,0,19,249,127,'M_Locator_ID',TO_DATE('2008-12-05 09:18:10','YYYY-MM-DD HH24:MI:SS'),100,'Warehouse Locator','D',1,'The Locator indicates where in a Warehouse a product is located.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Locator',0,TO_DATE('2008-12-05 09:18:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2008-dec-05 09:18:11 CET
-- [ 2350428 ] Add default locator to replenishment line
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56518 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2008-dec-05 09:18:34 CET
-- [ 2350428 ] Add default locator to replenishment line
ALTER TABLE M_Replenish ADD M_Locator_ID NUMBER(10)
;

-- 2008-dec-05 09:19:11 CET
-- [ 2350428 ] Add default locator to replenishment line
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56518,56500,0,182,TO_DATE('2008-12-05 09:19:10','YYYY-MM-DD HH24:MI:SS'),100,'Warehouse Locator',1,'D','The Locator indicates where in a Warehouse a product is located.','Y','Y','Y','N','N','N','N','N','Locator',TO_DATE('2008-12-05 09:19:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 2008-dec-05 09:19:11 CET
-- [ 2350428 ] Add default locator to replenishment line
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56500 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 2008-dec-05 09:19:21 CET
-- [ 2350428 ] Add default locator to replenishment line
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56500
;

-- 2008-dec-05 09:19:21 CET
-- [ 2350428 ] Add default locator to replenishment line
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=1051
;

-- 2008-dec-05 09:19:21 CET
-- [ 2350428 ] Add default locator to replenishment line
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=1052
;

-- 2008-dec-05 09:19:21 CET
-- [ 2350428 ] Add default locator to replenishment line
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=1053
;

-- 2008-dec-05 09:19:21 CET
-- [ 2350428 ] Add default locator to replenishment line
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=1054
;

-- 2008-dec-05 09:19:21 CET
-- [ 2350428 ] Add default locator to replenishment line
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=12133
;

