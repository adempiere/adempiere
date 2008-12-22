SET SQLBLANKLINES ON

-- Dec 15, 2008 1:14:17 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56525,53238,0,20,53030,'IsSubcontracting',TO_DATE('2008-12-15 13:14:07','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Is Subcontracting',0,TO_DATE('2008-12-15 13:14:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 15, 2008 1:14:17 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56525 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2008 1:14:22 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE PP_Order_Node_Product ADD IsSubcontracting CHAR(1) CHECK (IsSubcontracting IN ('Y','N'))
;

-- Dec 15, 2008 1:18:28 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53279,56505,0,53016,TO_DATE('2008-12-15 13:18:16','YYYY-MM-DD HH24:MI:SS'),0,'Configuration Level for this parameter',1,'D','Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter','Y','Y','Y','N','N','N','N','N','Configuration Level',TO_DATE('2008-12-15 13:18:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:18:28 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56505 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:18:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53278,56506,0,53016,TO_DATE('2008-12-15 13:18:28','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization',40,'D','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','Y','Y','N','N','N','N','N','Entity Type',TO_DATE('2008-12-15 13:18:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:18:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56506 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:21:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56526,53238,0,20,53016,'IsSubcontracting',TO_DATE('2008-12-15 13:21:26','YYYY-MM-DD HH24:MI:SS'),0,'N','EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Is Subcontracting',0,TO_DATE('2008-12-15 13:21:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 15, 2008 1:21:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56526 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2008 1:21:29 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE PP_WF_Node_Product ADD IsSubcontracting CHAR(1) DEFAULT 'N' CHECK (IsSubcontracting IN ('Y','N'))
;

-- Dec 15, 2008 1:21:40 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2008-12-15 13:21:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56525
;

-- Dec 15, 2008 1:21:51 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56526,56507,0,53016,TO_DATE('2008-12-15 13:21:50','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Is Subcontracting',TO_DATE('2008-12-15 13:21:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:21:51 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56507 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56505
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56506
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53308
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53309
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53310
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53311
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53312
;

-- Dec 15, 2008 1:22:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56507
;

-- Dec 15, 2008 1:22:38 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56525,56508,0,53042,TO_DATE('2008-12-15 13:22:36','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Is Subcontracting',TO_DATE('2008-12-15 13:22:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:22:38 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56508 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:25:39 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56527,53272,0,11,53016,'Yield',TO_DATE('2008-12-15 13:25:38','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Yield',0,TO_DATE('2008-12-15 13:25:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 15, 2008 1:25:39 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56527 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2008 1:25:42 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE PP_WF_Node_Product ADD Yield NUMBER(10)
;

-- Dec 15, 2008 1:25:59 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-15 13:25:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56508
;

-- Dec 15, 2008 1:26:42 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56527,56509,0,53016,TO_DATE('2008-12-15 13:26:35','YYYY-MM-DD HH24:MI:SS'),0,22,'EE01','Y','Y','Y','N','N','N','N','N','Yield',TO_DATE('2008-12-15 13:26:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:26:42 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56509 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:27:36 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56509
;

-- Dec 15, 2008 1:27:36 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56507
;

-- Dec 15, 2008 1:35:24 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-12-15 13:35:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53311
;

-- Dec 15, 2008 1:35:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-15 13:35:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53312
;

-- Dec 15, 2008 1:35:33 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-15 13:35:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56507
;

-- Dec 15, 2008 1:37:00 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56528,566,0,11,53030,'SeqNo',TO_DATE('2008-12-15 13:36:52','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE01',10,'The Sequence indicates the order of records','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Sequence',0,TO_DATE('2008-12-15 13:36:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 15, 2008 1:37:00 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56528 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2008 1:37:03 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE PP_Order_Node_Product ADD SeqNo NUMBER(10)
;

-- Dec 15, 2008 1:37:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56528,56510,0,53042,TO_DATE('2008-12-15 13:37:15','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first',10,'EE01','The Sequence indicates the order of records','Y','Y','Y','N','N','N','N','N','Sequence',TO_DATE('2008-12-15 13:37:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:37:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56510 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:37:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=56510
;

-- Dec 15, 2008 1:37:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53850
;

-- Dec 15, 2008 1:37:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53851
;

-- Dec 15, 2008 1:37:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56508
;

-- Dec 15, 2008 1:38:07 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56529,526,0,29,53030,'Qty',TO_DATE('2008-12-15 13:38:07','YYYY-MM-DD HH24:MI:SS'),0,'Quantity','EE01',22,'The Quantity indicates the number of a specific product or item for this document.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Quantity',0,TO_DATE('2008-12-15 13:38:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 15, 2008 1:38:07 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56529 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2008 1:38:10 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE PP_Order_Node_Product ADD Qty NUMBER
;

-- Dec 15, 2008 1:38:20 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56529,56511,0,53042,TO_DATE('2008-12-15 13:38:19','YYYY-MM-DD HH24:MI:SS'),0,'Quantity',22,'EE01','The Quantity indicates the number of a specific product or item for this document.','Y','Y','Y','N','N','N','N','N','Quantity',TO_DATE('2008-12-15 13:38:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 1:38:20 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56511 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 1:38:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56511
;

-- Dec 15, 2008 1:38:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53851
;

-- Dec 15, 2008 1:38:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=56508
;

-- Dec 15, 2008 1:38:34 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-15 13:38:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56511
;

-- Dec 15, 2008 1:38:42 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-12-15 13:38:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53851
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element SET Name='% Scrap', PrintName='% Scrap',Updated=TO_DATE('2008-12-15 13:43:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='QtyScrap', Name='% Scrap', Description=NULL, Help=NULL WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='% Scrap', Description=NULL, Help=NULL, AD_Element_ID=53289 WHERE UPPER(ColumnName)='QTYSCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='% Scrap', Description=NULL, Help=NULL WHERE AD_Element_ID=53289 AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET Name='% Scrap', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53289) AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:43:25 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_PrintFormatItem pi SET PrintName='% Scrap', Name='% Scrap' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53289)
;

-- Dec 15, 2008 1:50:52 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element SET Description='% of Scrap let calculate the Scrap Quantity', Help='% of Scrap let calculate the Scrap Quantity for Costing and Supply Chain Management',Updated=TO_DATE('2008-12-15 13:50:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:50:52 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:50:52 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='QtyScrap', Name='% Scrap', Description='% of Scrap let calculate the Scrap Quantity', Help='% of Scrap let calculate the Scrap Quantity for Costing and Supply Chain Management' WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:50:52 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='% Scrap', Description='% of Scrap let calculate the Scrap Quantity', Help='% of Scrap let calculate the Scrap Quantity for Costing and Supply Chain Management', AD_Element_ID=53289 WHERE UPPER(ColumnName)='QTYSCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 15, 2008 1:50:52 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='% Scrap', Description='% of Scrap let calculate the Scrap Quantity', Help='% of Scrap let calculate the Scrap Quantity for Costing and Supply Chain Management' WHERE AD_Element_ID=53289 AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:50:52 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET Name='% Scrap', Description='% of Scrap let calculate the Scrap Quantity', Help='% of Scrap let calculate the Scrap Quantity for Costing and Supply Chain Management' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53289) AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:53:01 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element SET Description='Indicate the % Scrap  that is generate in a manufacturing process', Name='% Scrap', PrintName='% Scrap',Updated=TO_DATE('2008-12-15 13:53:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53257
;

-- Dec 15, 2008 1:53:01 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53257
;

-- Dec 15, 2008 1:53:02 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='Scrap', Name='% Scrap', Description='Indicate the % Scrap  that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257
;

-- Dec 15, 2008 1:53:02 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='% Scrap', Description='Indicate the % Scrap  that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', AD_Element_ID=53257 WHERE UPPER(ColumnName)='SCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 15, 2008 1:53:02 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='% Scrap', Description='Indicate the % Scrap  that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257 AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:53:02 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET Name='% Scrap', Description='Indicate the % Scrap  that is generate in a manufacturing process', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53257) AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:53:02 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_PrintFormatItem pi SET PrintName='% Scrap', Name='% Scrap' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53257)
;

-- Dec 15, 2008 1:54:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element SET Description='Indicate the % Scrap  for calculate the Scrap Quantity',Updated=TO_DATE('2008-12-15 13:54:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53257
;

-- Dec 15, 2008 1:54:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53257
;

-- Dec 15, 2008 1:54:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='Scrap', Name='% Scrap', Description='Indicate the % Scrap  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257
;

-- Dec 15, 2008 1:54:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='% Scrap', Description='Indicate the % Scrap  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.', AD_Element_ID=53257 WHERE UPPER(ColumnName)='SCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 15, 2008 1:54:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='Scrap', Name='% Scrap', Description='Indicate the % Scrap  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Element_ID=53257 AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:54:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET Name='% Scrap', Description='Indicate the % Scrap  for calculate the Scrap Quantity', Help='Scrap is useful to determinate a rigth Standard Cost and management a good supply.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53257) AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:55:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='QtyScraped',Updated=TO_DATE('2008-12-15 13:55:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53586
;

-- Dec 15, 2008 1:55:44 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='QtyScrap',Updated=TO_DATE('2008-12-15 13:55:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53586
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element SET Description='Scrap Quantity for this componet', Help='Scrap Quantity for this componet', Name='QtyScrap',Updated=TO_DATE('2008-12-15 13:56:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET ColumnName='QtyScrap', Name='QtyScrap', Description='Scrap Quantity for this componet', Help='Scrap Quantity for this componet' WHERE AD_Element_ID=53289
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='QtyScrap', Description='Scrap Quantity for this componet', Help='Scrap Quantity for this componet', AD_Element_ID=53289 WHERE UPPER(ColumnName)='QTYSCRAP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Process_Para SET ColumnName='QtyScrap', Name='QtyScrap', Description='Scrap Quantity for this componet', Help='Scrap Quantity for this componet' WHERE AD_Element_ID=53289 AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET Name='QtyScrap', Description='Scrap Quantity for this componet', Help='Scrap Quantity for this componet' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53289) AND IsCentrallyMaintained='Y'
;

-- Dec 15, 2008 1:56:18 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_PrintFormatItem pi SET PrintName='% Scrap', Name='QtyScrap' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53289)
;


-- Dec 15, 2008 3:09:17 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@IsSubcontracting@=''Y''',Updated=TO_DATE('2008-12-15 15:09:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53378
;


-- Dec 15, 2008 9:35:07 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55311,56513,0,53049,TO_DATE('2008-12-15 21:35:06','YYYY-MM-DD HH24:MI:SS'),0,'ID of document reversal',22,'EE01','Y','Y','Y','N','N','N','N','N','Reversal ID',TO_DATE('2008-12-15 21:35:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 9:35:07 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56513 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56513
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53992
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53993
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53994
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53995
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53996
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53997
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53998
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53973
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53971
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53972
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53967
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53968
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53966
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=54001
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53999
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53980
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53975
;

-- Dec 15, 2008 9:35:35 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=54002
;

-- Dec 15, 2008 9:45:54 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56531,53238,0,20,53035,'IsSubcontracting',TO_DATE('2008-12-15 21:45:54','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Is Subcontracting',0,TO_DATE('2008-12-15 21:45:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 15, 2008 9:45:54 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56531 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2008 9:45:57 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE PP_Cost_Collector ADD IsSubcontracting CHAR(1) CHECK (IsSubcontracting IN ('Y','N'))
;

-- Dec 15, 2008 9:46:06 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56531,56514,0,53049,TO_DATE('2008-12-15 21:46:06','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Is Subcontracting',TO_DATE('2008-12-15 21:46:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 9:46:06 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56514 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=56514
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53992
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53993
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53994
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53995
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53996
;

-- Dec 15, 2008 9:46:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53997
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53998
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53973
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53971
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53972
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53967
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53968
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53966
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=54001
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53999
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53980
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53975
;

-- Dec 15, 2008 9:46:27 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=54002
;

-- Dec 15, 2008 9:50:21 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2008-12-15 21:50:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56531
;

-- Dec 15, 2008 9:50:40 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-12-15 21:50:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56514
;

-- Dec 15, 2008 9:59:04 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52038,'PP_Order.DocStatus=''CO''',TO_DATE('2008-12-15 21:58:58','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','PP_Order Completed','S',TO_DATE('2008-12-15 21:58:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 15, 2008 9:59:20 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Column SET AD_Val_Rule_ID=52038,Updated=TO_DATE('2008-12-15 21:59:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53830
;

-- Dec 16, 2008 5:05:39 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56532,53310,0,19,260,'PP_Cost_Collector_ID',TO_DATE('2008-12-16 17:05:36','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Manufacturing Cost Collector',0,TO_DATE('2008-12-16 17:05:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 16, 2008 5:05:39 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56532 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 16, 2008 5:05:43 PM ECT
-- Implementing Subcontract in Manufacturing Management
ALTER TABLE C_OrderLine ADD PP_Cost_Collector_ID NUMBER(10)
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53823
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53824
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53832
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53819
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53820
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53821
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53822
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53825
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53826
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53827
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53828
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53829
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53830
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53831
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53833
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53834
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53835
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53836
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53837
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53838
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53839
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53840
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53841
;

-- Dec 17, 2008 6:28:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53842
;

-- Dec 17, 2008 6:28:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53842
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53683
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53693
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53694
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53673
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53660
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53692
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53695
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53674
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53680
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53684
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53662
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53687
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53661
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53690
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53686
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53679
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53689
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53685
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53688
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53663
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53691
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53677
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=53664
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=53665
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=53666
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=53667
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=53668
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53669
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53670
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=53671
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=53672
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=53675
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=53676
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=53678
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=53681
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=53682
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=53696
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=53697
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53698
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53699
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53700
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=53701
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53702
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53703
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=53704
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53705
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53706
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53707
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53708
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53709
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53710
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53711
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53712
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53713
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53714
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=53715
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=53716
;

-- Dec 17, 2008 6:29:31 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=53717
;

-- Dec 17, 2008 6:30:00 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=53695
;

-- Dec 17, 2008 6:30:13 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53470,56519,0,53036,TO_DATE('2008-12-17 18:30:09','YYYY-MM-DD HH24:MI:SS'),0,'The current status of the document',2,'EE01','The Document Status indicates the status of a document at this time.  If you want to change the document status, use the Document Action field','Y','Y','Y','N','N','N','N','N','Document Status',TO_DATE('2008-12-17 18:30:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 17, 2008 6:30:13 PM ECT
-- Implementing Subcontract in Manufacturing Management
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56519 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 17, 2008 6:30:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=56519
;

-- Dec 17, 2008 6:30:26 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=53695
;

-- Dec 17, 2008 6:30:38 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsReadOnly='Y',Updated=TO_DATE('2008-12-17 18:30:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56519
;

-- Dec 17, 2008 6:30:45 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-17 18:30:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53695
;

-- Dec 17, 2008 6:32:01 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET Included_Tab_ID=53039,Updated=TO_DATE('2008-12-17 18:32:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53803
;

-- Dec 17, 2008 6:32:51 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53802
;

-- Dec 17, 2008 6:32:51 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=53801
;

-- Dec 17, 2008 6:32:51 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=53803
;

-- Dec 17, 2008 6:34:15 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-17 18:34:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53834
;

-- Dec 17, 2008 6:34:53 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-17 18:34:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53697
;

-- Dec 17, 2008 6:35:16 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@IsSubcontrating@=''Y'' ',Updated=TO_DATE('2008-12-17 18:35:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53672
;

-- Dec 17, 2008 6:36:20 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-12-17 18:36:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53841
;

-- Dec 17, 2008 6:36:36 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-12-17 18:36:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53820
;

-- Dec 17, 2008 6:37:20 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-12-17 18:37:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53678
;

-- Dec 17, 2008 6:39:53 PM ECT
-- Implementing Subcontract in Manufacturing Management
UPDATE AD_Field SET DisplayLogic='@IsSubcontracting@=''Y''',Updated=TO_DATE('2008-12-17 18:39:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53672
;


