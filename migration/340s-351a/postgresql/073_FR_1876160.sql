-- Jan 20, 2008 8:25:27 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET DisplayLogic=NULL,Updated=TO_TIMESTAMP('2008-01-20 20:25:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54221
;

-- Jan 20, 2008 8:28:39 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,DefaultValue,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('In Transit','N','N','Movement is in transit','N',0,'N','Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.',0,'Y',190,54240,'IsInTransit',0,0,1,'N',TO_TIMESTAMP('2008-01-20 20:28:37','YYYY-MM-DD HH24:MI:SS'),'N',20,0,TO_TIMESTAMP('2008-01-20 20:28:37','YYYY-MM-DD HH24:MI:SS'),2397,'Y','N','N',0,'N','N','EE01')
;

-- Jan 20, 2008 8:28:39 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54240 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 20, 2008 8:28:44 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
ALTER TABLE M_Warehouse ADD COLUMN IsInTransit CHAR(1) DEFAULT 'N' CHECK (IsInTransit IN ('Y','N'))
;

-- Jan 20, 2008 8:29:13 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
INSERT INTO AD_Field (IsEncrypted,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,IsReadOnly,EntityType) VALUES ('N',0,0,'Y','Y','Y',TO_TIMESTAMP('2008-01-20 20:29:10','YYYY-MM-DD HH24:MI:SS'),0,54334,'Movement is in transit',1,54240,'N',0,'Material Movement is in transit - shipped, but not received.
The transaction is completed, if confirmed.',TO_TIMESTAMP('2008-01-20 20:29:10','YYYY-MM-DD HH24:MI:SS'),'In Transit',177,'N','N','N','EE01')
;

-- Jan 20, 2008 8:29:13 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54334 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 20, 2008 8:30:44 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=54334
;

-- Jan 20, 2008 8:30:44 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=12132
;

-- Jan 20, 2008 8:30:44 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=12131
;

-- Jan 20, 2008 8:30:57 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-01-20 20:30:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54334
;

-- Jan 20, 2008 8:33:07 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=54334
;

-- Jan 20, 2008 8:33:07 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=995
;

-- Jan 20, 2008 8:33:07 PM CST
-- [ 1875623 ] Add the reference in Replenish Report to Distribution Order
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=3781
;

