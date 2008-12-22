-- Dec 16, 2008 5:51:46 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53716,0,'IsIncludeNullsOrgTrx',TO_DATE('2008-12-16 17:51:45','YYYY-MM-DD HH24:MI:SS'),0,'Include nulls in the selection of the organization transaction','D','Y','Include Nulls in Org Trx','Include Nulls in Org Trx',TO_DATE('2008-12-16 17:51:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 16, 2008 5:51:46 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53716 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 16, 2008 5:52:08 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56533,53716,0,20,446,'IsIncludeNullsOrgTrx',TO_DATE('2008-12-16 17:52:07','YYYY-MM-DD HH24:MI:SS'),0,'N','Include nulls in the selection of the organization transaction','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Include Nulls in Org Trx',0,TO_DATE('2008-12-16 17:52:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 16, 2008 5:52:08 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56533 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 16, 2008 5:52:12 PM ECT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD IsIncludeNullsOrgTrx CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsOrgTrx IN ('Y','N')) NOT NULL
;

-- Dec 16, 2008 5:53:01 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56534,112,0,19,446,104,'AD_OrgTrx_ID',TO_DATE('2008-12-16 17:53:00','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Performing or initiating organization','D',22,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','N','Y','N','N','N','N','N','Trx Organization',0,TO_DATE('2008-12-16 17:53:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 16, 2008 5:53:01 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56534 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 16, 2008 5:53:12 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Val_Rule_ID=130, DefaultValue=NULL,Updated=TO_DATE('2008-12-16 17:53:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56534
;


-- Dec 16, 2008 5:53:31 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2008-12-16 17:53:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56534
;

-- Dec 16, 2008 5:53:33 PM ECT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportColumn ADD AD_OrgTrx_ID NUMBER(10)
;

-- Dec 16, 2008 5:55:16 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56535,53716,0,20,450,'IsIncludeNullsOrgTrx',TO_DATE('2008-12-16 17:55:16','YYYY-MM-DD HH24:MI:SS'),0,'N','Include nulls in the selection of the organization transaction','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Include Nulls in Org Trx',0,TO_DATE('2008-12-16 17:55:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Dec 16, 2008 5:55:16 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56535 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 16, 2008 5:55:19 PM ECT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD IsIncludeNullsOrgTrx CHAR(1) DEFAULT 'N' CHECK (IsIncludeNullsOrgTrx IN ('Y','N')) NOT NULL
;

-- Dec 16, 2008 5:55:54 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56536,112,0,19,450,130,'AD_OrgTrx_ID',TO_DATE('2008-12-16 17:55:54','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Performing or initiating organization','D',22,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Trx Organization',0,TO_DATE('2008-12-16 17:55:54','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Dec 16, 2008 5:55:54 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56536 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 16, 2008 5:55:58 PM ECT
-- Financial Report Source with Type Combination
ALTER TABLE PA_ReportSource ADD AD_OrgTrx_ID NUMBER(10)
;

-- Dec 16, 2008 5:56:42 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56535,56515,0,377,TO_DATE('2008-12-16 17:56:41','YYYY-MM-DD HH24:MI:SS'),0,'Include nulls in the selection of the organization transaction',1,'D','Y','Y','Y','N','N','N','N','N','Include Nulls in Org Trx',TO_DATE('2008-12-16 17:56:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 16, 2008 5:56:42 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56515 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 16, 2008 5:56:43 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56536,56516,0,377,TO_DATE('2008-12-16 17:56:42','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization',22,'D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','N','N','N','N','N','Trx Organization',TO_DATE('2008-12-16 17:56:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 16, 2008 5:56:43 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56516 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56516
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=56515
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=4805
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56285
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=4816
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=56287
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=4807
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=56291
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=4808
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=56289
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=4806
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=56292
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=4809
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56293
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=4810
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56286
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=4817
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56288
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56283
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56294
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56284
;

-- Dec 16, 2008 5:57:13 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56295
;

-- Dec 16, 2008 5:57:42 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=OO | @ElementType@=OT | @ElementType@=CO',Updated=TO_DATE('2008-12-16 17:57:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56516
;

-- Dec 16, 2008 5:57:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=CO', IsSameLine='Y',Updated=TO_DATE('2008-12-16 17:57:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56515
;

-- Dec 16, 2008 5:58:32 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56533,56517,0,374,TO_DATE('2008-12-16 17:58:29','YYYY-MM-DD HH24:MI:SS'),0,'Include nulls in the selection of the organization transaction',1,'D','Y','Y','Y','N','N','N','N','N','Include Nulls in Org Trx',TO_DATE('2008-12-16 17:58:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 16, 2008 5:58:32 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56517 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 16, 2008 5:58:34 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56534,56518,0,374,TO_DATE('2008-12-16 17:58:32','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization',22,'D','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','N','N','N','N','N','Trx Organization',TO_DATE('2008-12-16 17:58:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Dec 16, 2008 5:58:34 PM ECT
-- Financial Report Source with Type Combination
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56518 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56518
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56517
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=4765
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56296
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=4766
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56302
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=4777
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56299
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=4776
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=56298
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=4775
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56303
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=4767
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=56300
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=4768
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=56304
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=4769
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=56297
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=410,IsDisplayed='Y' WHERE AD_Field_ID=56281
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=420,IsDisplayed='Y' WHERE AD_Field_ID=56305
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=430,IsDisplayed='Y' WHERE AD_Field_ID=56282
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=440,IsDisplayed='Y' WHERE AD_Field_ID=56306
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=450,IsDisplayed='Y' WHERE AD_Field_ID=56375
;

-- Dec 16, 2008 5:58:54 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET SeqNo=460,IsDisplayed='Y' WHERE AD_Field_ID=56376
;

-- Dec 16, 2008 5:59:23 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=OO | @ElementType@=OT | @ElementType@=CO',Updated=TO_DATE('2008-12-16 17:59:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56518
;

-- Dec 16, 2008 5:59:33 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Field SET DisplayLogic='@ElementType@=CO', IsSameLine='Y',Updated=TO_DATE('2008-12-16 17:59:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56517
;

-- Dec 16, 2008 6:02:22 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=130,Updated=TO_DATE('2008-12-16 18:02:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56536
;

-- Dec 16, 2008 6:02:57 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=130,Updated=TO_DATE('2008-12-16 18:02:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56534
;

-- Dec 16, 2008 6:06:27 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_DATE('2008-12-16 18:06:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56534
;

-- Dec 16, 2008 6:06:44 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET AD_Val_Rule_ID=NULL,Updated=TO_DATE('2008-12-16 18:06:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56536
;

-- Dec 16, 2008 6:19:23 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_DATE('2008-12-16 18:19:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56536
;

-- Dec 16, 2008 6:19:56 PM ECT
-- Financial Report Source with Type Combination
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_DATE('2008-12-16 18:19:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56534
;
