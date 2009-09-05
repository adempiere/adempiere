-- Sep 4, 2009 6:51:08 PM COT
-- FR-2851987-Add company logo
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53909,0,'Logo_ID',TO_DATE('2009-09-04 18:51:07','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Logo','Logo',TO_DATE('2009-09-04 18:51:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 6:51:08 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53909 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 4, 2009 6:51:47 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58111,53909,0,32,227,'Logo_ID',TO_DATE('2009-09-04 18:51:46','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Logo',0,TO_DATE('2009-09-04 18:51:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 6:51:47 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58111 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 6:51:50 PM COT
ALTER TABLE AD_ClientInfo ADD Logo_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 4, 2009 6:52:20 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58112,53909,0,32,228,'Logo_ID',TO_DATE('2009-09-04 18:52:19','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Logo',0,TO_DATE('2009-09-04 18:52:19','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 6:52:20 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58112 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 6:52:22 PM COT
ALTER TABLE AD_OrgInfo ADD Logo_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 4, 2009 6:52:45 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58113,53909,0,32,291,'Logo_ID',TO_DATE('2009-09-04 18:52:44','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Logo',0,TO_DATE('2009-09-04 18:52:44','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 6:52:45 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58113 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 6:52:47 PM COT
ALTER TABLE C_BPartner ADD Logo_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 4, 2009 6:54:06 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58111,57531,0,169,TO_DATE('2009-09-04 18:54:05','YYYY-MM-DD HH24:MI:SS'),100,40,'D','Y','Y','Y','N','N','N','N','N','Logo',280,0,TO_DATE('2009-09-04 18:54:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 6:54:06 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57531 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 4, 2009 6:54:40 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58112,57532,0,170,TO_DATE('2009-09-04 18:54:39','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Logo',130,0,TO_DATE('2009-09-04 18:54:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 6:54:40 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57532 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 4, 2009 7:21:21 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58113,57533,0,220,TO_DATE('2009-09-04 19:21:20','YYYY-MM-DD HH24:MI:SS'),100,10,'D','Y','Y','Y','N','N','N','N','N','Logo',320,0,TO_DATE('2009-09-04 19:21:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 7:21:21 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57533 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 4, 2009 7:22:07 PM COT
UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-09-04 19:22:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57533
;

-- Sep 4, 2009 7:23:43 PM COT
UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-09-04 19:23:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57532
;

-- Sep 4, 2009 7:50:06 PM COT
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53910,0,'LogoReport_ID',TO_DATE('2009-09-04 19:50:05','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Logo Report','Logo Report',TO_DATE('2009-09-04 19:50:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 7:50:06 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53910 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 4, 2009 7:50:18 PM COT
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53911,0,'LogoWeb_ID',TO_DATE('2009-09-04 19:50:18','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Logo Web','Logo Web',TO_DATE('2009-09-04 19:50:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 7:50:18 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53911 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 4, 2009 7:50:41 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58114,53910,0,32,227,'LogoReport_ID',TO_DATE('2009-09-04 19:50:41','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Logo Report',0,TO_DATE('2009-09-04 19:50:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 7:50:41 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58114 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 7:50:44 PM COT
ALTER TABLE AD_ClientInfo ADD LogoReport_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 4, 2009 7:51:01 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58115,53911,0,32,227,'LogoWeb_ID',TO_DATE('2009-09-04 19:51:00','YYYY-MM-DD HH24:MI:SS'),100,'D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Logo Web',0,TO_DATE('2009-09-04 19:51:00','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 4, 2009 7:51:01 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58115 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 4, 2009 7:51:04 PM COT
ALTER TABLE AD_ClientInfo ADD LogoWeb_ID NUMBER(10) DEFAULT NULL 
;

-- Sep 4, 2009 7:51:30 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58114,57534,0,169,TO_DATE('2009-09-04 19:51:30','YYYY-MM-DD HH24:MI:SS'),100,40,'D','Y','Y','Y','N','N','N','N','N','Logo Report',290,0,TO_DATE('2009-09-04 19:51:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 7:51:30 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57534 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 4, 2009 7:51:45 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58115,57535,0,169,TO_DATE('2009-09-04 19:51:45','YYYY-MM-DD HH24:MI:SS'),100,40,'D','Y','Y','Y','N','N','N','N','N','Logo Web',300,0,TO_DATE('2009-09-04 19:51:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 7:51:45 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57535 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 4, 2009 7:53:18 PM COT
INSERT INTO AD_FieldGroup (AD_Client_ID,AD_FieldGroup_ID,AD_Org_ID,Created,CreatedBy,EntityType,FieldGroupType,IsActive,IsCollapsedByDefault,Name,Updated,UpdatedBy) VALUES (0,50012,0,TO_DATE('2009-09-04 19:53:17','YYYY-MM-DD HH24:MI:SS'),100,'D','T','Y','Y','Logo',TO_DATE('2009-09-04 19:53:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 4, 2009 7:53:18 PM COT
INSERT INTO AD_FieldGroup_Trl (AD_Language,AD_FieldGroup_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_FieldGroup_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_FieldGroup t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_FieldGroup_ID=50012 AND EXISTS (SELECT * FROM AD_FieldGroup_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_FieldGroup_ID!=t.AD_FieldGroup_ID)
;

-- Sep 4, 2009 7:53:25 PM COT
UPDATE AD_FieldGroup SET IsCollapsedByDefault='N',Updated=TO_DATE('2009-09-04 19:53:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_FieldGroup_ID=50012
;

-- Sep 4, 2009 7:53:37 PM COT
UPDATE AD_Field SET AD_FieldGroup_ID=50012,Updated=TO_DATE('2009-09-04 19:53:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57535
;

-- Sep 4, 2009 7:53:39 PM COT
UPDATE AD_Field SET AD_FieldGroup_ID=50012,Updated=TO_DATE('2009-09-04 19:53:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57534
;

-- Sep 4, 2009 7:53:42 PM COT
UPDATE AD_Field SET AD_FieldGroup_ID=50012,Updated=TO_DATE('2009-09-04 19:53:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57531
;

-- Sep 4, 2009 7:53:58 PM COT
UPDATE AD_Field SET AD_FieldGroup_ID=50012,Updated=TO_DATE('2009-09-04 19:53:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57532
;

-- Sep 4, 2009 7:54:14 PM COT
UPDATE AD_Field SET AD_FieldGroup_ID=50012,Updated=TO_DATE('2009-09-04 19:54:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57533
;

