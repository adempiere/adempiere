-- Feb 13, 2009 11:10:45 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Table SET Name='Manufacturing Order MA', TableName='PP_Cost_CollectorMA',Updated=TO_TIMESTAMP('2009-02-13 11:10:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53062
;

-- Feb 13, 2009 11:10:45 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53062
;

-- Feb 13, 2009 11:10:54 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53271,TO_TIMESTAMP('2009-02-13 11:10:45','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table PP_Cost_CollectorMA',1,'Y','N','Y','Y','PP_Cost_CollectorMA','N',1000000,TO_TIMESTAMP('2009-02-13 11:10:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Element SET ColumnName='PP_Cost_CollectorMA_ID', Name='Manufacturing Order MA',Updated=TO_TIMESTAMP('2009-02-13 11:12:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53343
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53343
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Column SET ColumnName='PP_Cost_CollectorMA_ID', Name='Manufacturing Order MA', Description=NULL, Help=NULL WHERE AD_Element_ID=53343
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Process_Para SET ColumnName='PP_Cost_CollectorMA_ID', Name='Manufacturing Order MA', Description=NULL, Help=NULL, AD_Element_ID=53343 WHERE UPPER(ColumnName)='PP_COST_COLLECTORMA_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Process_Para SET ColumnName='PP_Cost_CollectorMA_ID', Name='Manufacturing Order MA', Description=NULL, Help=NULL WHERE AD_Element_ID=53343 AND IsCentrallyMaintained='Y'
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET Name='Manufacturing Order MA', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53343) AND IsCentrallyMaintained='Y'
;

-- Feb 13, 2009 11:12:11 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_PrintFormatItem SET PrintName='Manufacturing Cost Collector', Name='Manufacturing Order MA' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53343)
;

-- Feb 13, 2009 11:12:43 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Column SET AD_Element_ID=53310, ColumnName='PP_Cost_Collector_ID', Description=NULL, Help=NULL, Name='Manufacturing Cost Collector',Updated=TO_TIMESTAMP('2009-02-13 11:12:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54319
;

-- Feb 13, 2009 11:12:43 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54319
;

-- Feb 13, 2009 11:12:43 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET Name='Manufacturing Cost Collector', Description=NULL, Help=NULL WHERE AD_Column_ID=54319 AND IsCentrallyMaintained='Y'
;

-- Feb 13, 2009 11:14:29 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53192,53062,53014,TO_TIMESTAMP('2009-02-13 11:14:29','YYYY-MM-DD HH24:MI:SS'),0,'EE01','N','N','Y','N','N','Y','N','N','N','N','Cost Collector MA','N',20,1,TO_TIMESTAMP('2009-02-13 11:14:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:29 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53192 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Feb 13, 2009 11:14:33 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54315,56695,0,53192,TO_TIMESTAMP('2009-02-13 11:14:33','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2009-02-13 11:14:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:33 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56695 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:14:34 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54316,56696,0,53192,TO_TIMESTAMP('2009-02-13 11:14:33','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance',10,'EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','N','Attribute Set Instance',TO_TIMESTAMP('2009-02-13 11:14:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:34 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56696 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:14:36 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54311,56697,0,53192,TO_TIMESTAMP('2009-02-13 11:14:34','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',10,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_TIMESTAMP('2009-02-13 11:14:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:36 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56697 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:14:37 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54319,56698,0,53192,TO_TIMESTAMP('2009-02-13 11:14:36','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Cost Collector',TO_TIMESTAMP('2009-02-13 11:14:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:37 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56698 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:14:37 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54318,56699,0,53192,TO_TIMESTAMP('2009-02-13 11:14:37','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','N','N','N','N','N','N','Manufacturing Order MA',TO_TIMESTAMP('2009-02-13 11:14:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:37 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56699 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:14:38 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54317,56700,0,53192,TO_TIMESTAMP('2009-02-13 11:14:37','YYYY-MM-DD HH24:MI:SS'),0,'Quantity of a product moved.',22,'EE01','The Movement Quantity indicates the quantity of a product that has been moved.','Y','Y','Y','N','N','N','N','N','Movement Quantity',TO_TIMESTAMP('2009-02-13 11:14:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:38 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56700 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:14:39 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54312,56701,0,53192,TO_TIMESTAMP('2009-02-13 11:14:38','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',10,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_TIMESTAMP('2009-02-13 11:14:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 13, 2009 11:14:39 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56701 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 13, 2009 11:15:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=56697
;

-- Feb 13, 2009 11:15:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=56701
;

-- Feb 13, 2009 11:15:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=56695
;

-- Feb 13, 2009 11:15:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=56698
;

-- Feb 13, 2009 11:15:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=56700
;

-- Feb 13, 2009 11:15:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=56696
;

-- Feb 13, 2009 11:15:48 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-13 11:15:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56701
;

-- Feb 13, 2009 11:15:58 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-13 11:15:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56696
;

-- Feb 13, 2009 11:17:49 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Tab SET IsInsertRecord='N', IsReadOnly='Y', Name='Attributes',Updated=TO_TIMESTAMP('2009-02-13 11:17:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53192
;

-- Feb 13, 2009 11:17:49 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53192
;

-- Feb 13, 2009 11:22:09 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53453,183,TO_TIMESTAMP('2009-02-13 11:22:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Manufacturing Cost Collector',TO_TIMESTAMP('2009-02-13 11:22:06','YYYY-MM-DD HH24:MI:SS'),0,'MCC')
;

-- Feb 13, 2009 11:22:09 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53453 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Feb 13, 2009 11:22:51 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53289,TO_TIMESTAMP('2009-02-13 11:22:47','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','C_DocType MFG Cost Collector',TO_TIMESTAMP('2009-02-13 11:22:47','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Feb 13, 2009 11:22:51 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53289 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Feb 13, 2009 11:23:59 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,1509,1501,0,53289,217,TO_TIMESTAMP('2009-02-13 11:23:59','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N',TO_TIMESTAMP('2009-02-13 11:23:59','YYYY-MM-DD HH24:MI:SS'),0,'C_DocType.AD_Client_ID=@#AD_Client_ID@  AND C_DocType.DocBaseType IN  (''MCC'') ')
;

-- Feb 13, 2009 11:24:14 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Column SET AD_Reference_Value_ID=53289,Updated=TO_TIMESTAMP('2009-02-13 11:24:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53809
;

-- Feb 14, 2009 12:33:36 AM ECT
-- Fix Allocation with Collector Cost
UPDATE AD_Tab SET AD_Column_ID=54319,Updated=TO_TIMESTAMP('2009-02-14 00:33:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53192
;

-- Feb 13, 2009 11:29:01 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (11,0,53272,TO_TIMESTAMP('2009-02-13 11:29:00','YYYY-MM-DD HH24:MI:SS'),100,1000000,100,'Manufacturing Cost Collector',1,'Y','N','N','N','Manufacturing Cost Collector','N',1000000,TO_TIMESTAMP('2009-02-13 11:29:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2009 11:30:27 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO C_DocType (AD_Client_ID,AD_Org_ID,C_DocType_ID,Created,CreatedBy,DocBaseType,DocNoSequence_ID,DocumentCopies,GL_Category_ID,HasCharges,HasProforma,IsActive,IsCreateCounter,IsDefault,IsDefaultCounterDoc,IsDocNoControlled,IsInTransit,IsIndexed,IsOverwriteDateOnComplete,IsOverwriteSeqOnComplete,IsPickQAConfirm,IsSOTrx,IsShipConfirm,IsSplitWhenDifference,Name,PrintName,Updated,UpdatedBy) VALUES (11,0,50013,TO_TIMESTAMP('2009-02-13 11:30:24','YYYY-MM-DD HH24:MI:SS'),100,'MCC',53174,1,108,'N','N','Y','Y','N','N','Y','N','Y','N','N','N','N','N','N','Manufacturing Cost Collector','Manufacturing Cost Collector',TO_TIMESTAMP('2009-02-13 11:30:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Feb 13, 2009 11:30:27 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO C_DocType_Trl (AD_Language,C_DocType_ID, DocumentNote,Name,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.C_DocType_ID, t.DocumentNote,t.Name,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, C_DocType t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.C_DocType_ID=50013 AND EXISTS (SELECT * FROM C_DocType_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.C_DocType_ID!=t.C_DocType_ID)
;

-- Feb 13, 2009 11:30:27 AM ECT
-- Fix Allocation with Collector Cost
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID) WHERE client.AD_Client_ID=11 AND doctype.C_DocType_ID=50013 AND rol.IsManual='N')
;

-- Feb 13, 2009 11:30:49 AM ECT
-- Fix Allocation with Collector Cost
UPDATE C_DocType SET DocNoSequence_ID=53272,Updated=TO_TIMESTAMP('2009-02-13 11:30:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=50013
;

-- Feb 13, 2009 11:44:28 AM ECT
-- Fix Allocation with Collector Cost
UPDATE C_DocType SET DocNoSequence_ID=53174,Updated=TO_TIMESTAMP('2009-02-13 11:44:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=50013
;

UPDATE C_DocType SET DocNoSequence_ID=53272,Updated=TO_TIMESTAMP('2009-02-16 14:26:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DocType_ID=50013
;


ALTER TABLE PP_Order_BOMLineMA RENAME TO PP_Cost_CollectorMA;
ALTER TABLE PP_Cost_CollectorMA RENAME COLUMN pp_order_bomlinema_id TO pp_cost_collectorma_id;
ALTER TABLE pp_cost_collectorma DROP CONSTRAINT pporderbomline_pporderbomlinem;
ALTER TABLE pp_cost_collectorma DROP CONSTRAINT pp_order_bomlinema_key;
ALTER TABLE pp_cost_collectorma ADD CONSTRAINT pp_cost_collectorma_key PRIMARY KEY(pp_cost_collectorma_id);
ALTER TABLE PP_Cost_CollectorMA RENAME COLUMN pp_order_bomline_id TO pp_cost_collector_id;
ALTER TABLE pp_cost_collectorma ADD CONSTRAINT ppcostcollector_ppcostcollectorma FOREIGN KEY (pp_cost_collector_id)
      REFERENCES pp_cost_collector (pp_cost_collector_id);      
ALTER TABLE pp_cost_collectorma DROP CONSTRAINT pp_order_bomlinema_isactive_check;
ALTER TABLE pp_cost_collectorma ADD CONSTRAINT pp_cost_collectorma_isactive_check CHECK (isactive = ANY (ARRAY['Y'::bpchar, 'N'::bpchar]));
  
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53170;
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53171;
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53172;
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53173;
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53174;
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53175;
DELETE FROM AD_Sequence WHERE AD_Sequence_ID=53176;



