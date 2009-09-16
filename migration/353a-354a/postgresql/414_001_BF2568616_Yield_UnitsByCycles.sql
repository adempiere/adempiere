-- Feb 5, 2009 11:23:51 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56776,53272,0,11,129,'Yield',TO_TIMESTAMP('2009-02-05 23:23:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Yield',0,TO_TIMESTAMP('2009-02-05 23:23:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:23:51 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56776 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:25:08 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56777,53272,0,11,117,'Yield',TO_TIMESTAMP('2009-02-05 23:25:06','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Yield',0,TO_TIMESTAMP('2009-02-05 23:25:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:25:08 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56777 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:25:12 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE AD_Workflow ADD COLUMN Yield NUMERIC(10) DEFAULT NULL 
;

-- Feb 5, 2009 11:25:21 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE AD_WF_Node ADD COLUMN Yield NUMERIC(10) DEFAULT NULL 
;

-- Feb 5, 2009 11:26:14 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56778,53239,0,22,117,'UnitsCycles',TO_TIMESTAMP('2009-02-05 23:26:13','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Units Cycles',0,TO_TIMESTAMP('2009-02-05 23:26:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:26:14 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56778 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:26:27 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE AD_Workflow ADD COLUMN UnitsCycles NUMERIC DEFAULT NULL 
;

-- Feb 5, 2009 11:28:04 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56779,53272,0,11,53029,'Yield',TO_TIMESTAMP('2009-02-05 23:28:02','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Yield',0,TO_TIMESTAMP('2009-02-05 23:28:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:28:04 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56779 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:28:07 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE PP_Order_Workflow ADD COLUMN Yield NUMERIC(10) DEFAULT NULL 
;

-- Feb 5, 2009 11:28:59 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56780,53239,0,22,53029,'UnitsCycles',TO_TIMESTAMP('2009-02-05 23:28:57','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Units Cycles',0,TO_TIMESTAMP('2009-02-05 23:28:57','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:28:59 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56780 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:29:02 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE PP_Order_Workflow ADD COLUMN UnitsCycles NUMERIC DEFAULT NULL 
;

-- Feb 5, 2009 11:29:59 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56781,53272,0,11,53022,'Yield',TO_TIMESTAMP('2009-02-05 23:29:58','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Yield',0,TO_TIMESTAMP('2009-02-05 23:29:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:29:59 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56781 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:30:02 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE PP_Order_Node ADD COLUMN Yield NUMERIC(10) DEFAULT NULL 
;

-- Feb 5, 2009 11:30:20 PM ECT
-- Implement Yield and Units Cycle
insert into t_alter_column values('pp_order_node','Yield','NUMERIC(10)',null,'NULL')
;

-- Feb 5, 2009 11:30:51 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56519,56682,0,53027,TO_TIMESTAMP('2009-02-05 23:30:49','YYYY-MM-DD HH24:MI:SS'),0,'This functionality is considered Beta',1,'D','Beta functionality is not fully tested or completed.','Y','Y','Y','N','N','N','N','N','Beta Functionality',TO_TIMESTAMP('2009-02-05 23:30:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:30:51 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56682 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:30:52 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56778,56683,0,53027,TO_TIMESTAMP('2009-02-05 23:30:51','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Units Cycles',TO_TIMESTAMP('2009-02-05 23:30:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:30:52 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56683 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:30:54 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,12938,56684,0,53027,TO_TIMESTAMP('2009-02-05 23:30:52','YYYY-MM-DD HH24:MI:SS'),0,'Element is valid',1,'D','The element passed the validation check','Y','Y','Y','N','N','N','N','N','Valid',TO_TIMESTAMP('2009-02-05 23:30:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:30:54 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56684 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:30:55 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56777,56685,0,53027,TO_TIMESTAMP('2009-02-05 23:30:54','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Yield',TO_TIMESTAMP('2009-02-05 23:30:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:30:55 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56685 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:31:18 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,14601,56686,0,53025,TO_TIMESTAMP('2009-02-05 23:31:15','YYYY-MM-DD HH24:MI:SS'),0,'Electronic Mail Address',60,'D','The Email Address is the Electronic Mail ID for this User and should be fully qualified (e.g. joe.smith@company.com). The Email Address is used to access the self service application functionality from the web.','Y','Y','Y','N','N','N','N','N','EMail Address',TO_TIMESTAMP('2009-02-05 23:31:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:31:18 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56686 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:31:21 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,14600,56687,0,53025,TO_TIMESTAMP('2009-02-05 23:31:18','YYYY-MM-DD HH24:MI:SS'),0,'Recipient of the EMail',1,'D','Y','Y','Y','N','N','N','N','N','EMail Recipient',TO_TIMESTAMP('2009-02-05 23:31:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:31:21 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56687 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:31:23 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,14602,56688,0,53025,TO_TIMESTAMP('2009-02-05 23:31:21','YYYY-MM-DD HH24:MI:SS'),0,'Text templates for mailings',10,'D','The Mail Template indicates the mail template for return messages. Mail text can include variables.  The priority of parsing is User/Contact, Business Partner and then the underlying business object (like Request, Dunning, Workflow object).<br>
So, @Name@ would resolve into the User name (if user is defined defined), then Business Partner name (if business partner is defined) and then the Name of the business object if it has a Name.<br>
For Multi-Lingual systems, the template is translated based on the Business Partner''s language selection.','Y','Y','Y','N','N','N','N','N','Mail Template',TO_TIMESTAMP('2009-02-05 23:31:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:31:23 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56688 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:31:24 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,10556,56689,0,53025,TO_TIMESTAMP('2009-02-05 23:31:23','YYYY-MM-DD HH24:MI:SS'),0,'Workflow Transaction Execution Block',22,'D','A workflow execution block is optional and allows all work to be performed in a single transaction. If one step (node activity) fails, the entire work is rolled back.','Y','Y','Y','N','N','N','N','N','Workflow Block',TO_TIMESTAMP('2009-02-05 23:31:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:31:24 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56689 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:31:25 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56776,56690,0,53025,TO_TIMESTAMP('2009-02-05 23:31:24','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Yield',TO_TIMESTAMP('2009-02-05 23:31:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:31:25 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56690 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56686
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56687
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56688
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56689
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56690
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53409
;

-- Feb 5, 2009 11:33:31 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53410
;

-- Feb 5, 2009 11:33:40 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:33:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56690
;

-- Feb 5, 2009 11:35:17 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56782,53241,0,22,53029,'OverlapUnits',TO_TIMESTAMP('2009-02-05 23:35:16','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Overlap Units',0,TO_TIMESTAMP('2009-02-05 23:35:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:35:17 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56782 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:35:24 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE PP_Order_Workflow ADD COLUMN OverlapUnits NUMERIC DEFAULT NULL 
;

-- Feb 5, 2009 11:36:07 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56783,53241,0,22,117,'OverlapUnits',TO_TIMESTAMP('2009-02-05 23:36:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Overlap Units',0,TO_TIMESTAMP('2009-02-05 23:36:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 5, 2009 11:36:07 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56783 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 5, 2009 11:36:09 PM ECT
-- Implement Yield and Units Cycle
ALTER TABLE AD_Workflow ADD COLUMN OverlapUnits NUMERIC DEFAULT NULL 
;

-- Feb 5, 2009 11:36:28 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56783,56691,0,53027,TO_TIMESTAMP('2009-02-05 23:36:27','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Overlap Units',TO_TIMESTAMP('2009-02-05 23:36:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:36:28 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56691 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56684
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=56683
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56691
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=53452
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=53453
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53454
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53455
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53456
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53457
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56685
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=53458
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53459
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53460
;

-- Feb 5, 2009 11:37:26 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=56682
;

-- Feb 5, 2009 11:37:40 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:37:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56691
;

-- Feb 5, 2009 11:37:50 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:37:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56685
;

-- Feb 5, 2009 11:39:27 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53452
;

-- Feb 5, 2009 11:39:27 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56683
;

-- Feb 5, 2009 11:39:27 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56691
;

-- Feb 5, 2009 11:41:46 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56682
;

-- Feb 5, 2009 11:42:45 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56782,56692,0,53041,TO_TIMESTAMP('2009-02-05 23:42:43','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Overlap Units',TO_TIMESTAMP('2009-02-05 23:42:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:42:45 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56692 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:42:46 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56780,56693,0,53041,TO_TIMESTAMP('2009-02-05 23:42:45','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Units Cycles',TO_TIMESTAMP('2009-02-05 23:42:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:42:46 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56693 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:42:47 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56779,56694,0,53041,TO_TIMESTAMP('2009-02-05 23:42:46','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Yield',TO_TIMESTAMP('2009-02-05 23:42:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 5, 2009 11:42:47 PM ECT
-- Implement Yield and Units Cycle
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56694 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 5, 2009 11:43:21 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56693
;

-- Feb 5, 2009 11:43:21 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56692
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=53835
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=53836
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=53837
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=53838
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=53839
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=56694
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=53840
;

-- Feb 5, 2009 11:43:22 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=53841
;

-- Feb 5, 2009 11:43:53 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:43:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56692
;

-- Feb 5, 2009 11:43:57 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:43:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56694
;

-- Feb 5, 2009 11:44:42 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56692
;

-- Feb 5, 2009 11:44:42 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56693
;

-- Feb 5, 2009 11:44:51 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-02-05 23:44:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56692
;

-- Feb 5, 2009 11:44:54 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:44:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56693
;

-- Feb 5, 2009 11:46:05 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET DisplayLogic='@ProcessType@=''DR''',Updated=TO_TIMESTAMP('2009-02-05 23:46:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56693
;

-- Feb 5, 2009 11:46:43 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=53699
;

-- Feb 5, 2009 11:46:43 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=53698
;

-- Feb 5, 2009 11:46:59 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-02-05 23:46:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53699
;

-- Feb 5, 2009 11:47:01 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:47:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53698
;

-- Feb 5, 2009 11:47:06 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET DisplayLogic='@ProcessType@=''DR''',Updated=TO_TIMESTAMP('2009-02-05 23:47:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53698
;

-- Feb 5, 2009 11:47:54 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56691
;

-- Feb 5, 2009 11:47:54 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56683
;

-- Feb 5, 2009 11:48:02 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-02-05 23:48:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56691
;

-- Feb 5, 2009 11:48:05 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:48:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56683
;

-- Feb 5, 2009 11:48:10 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET DisplayLogic='@ProcessType@=''DR''',Updated=TO_TIMESTAMP('2009-02-05 23:48:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56683
;

-- Feb 5, 2009 11:48:23 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=53403
;

-- Feb 5, 2009 11:48:23 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=53402
;

-- Feb 5, 2009 11:48:33 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='N',Updated=TO_TIMESTAMP('2009-02-05 23:48:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53403
;

-- Feb 5, 2009 11:48:36 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2009-02-05 23:48:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53402
;

-- Feb 5, 2009 11:49:37 PM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET DisplayLogic='@ProcessType@=''DR''',Updated=TO_TIMESTAMP('2009-02-05 23:49:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53402
;

-- Feb 5, 2009 11:50:59 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=56509
;

-- Feb 5, 2009 11:50:59 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Field WHERE AD_Field_ID=56509
;

-- Feb 5, 2009 11:51:27 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=53851
;

-- Feb 5, 2009 11:51:27 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Field WHERE AD_Field_ID=53851
;

-- Feb 5, 2009 11:51:58 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=53740
;

-- Feb 5, 2009 11:51:58 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Column WHERE AD_Column_ID=53740
;

-- Feb 5, 2009 11:52:44 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=56527
;

-- Feb 5, 2009 11:52:44 PM ECT
-- Implement Yield and Units Cycle
DELETE FROM AD_Column WHERE AD_Column_ID=56527
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When units for cycles is defined the duration time is the total time for units', Name='Units for Cycles', PrintName='Units for Cycles',Updated=TO_TIMESTAMP('2009-02-06 00:21:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='UnitsCycles', Name='Units for Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When units for cycles is defined the duration time is the total time for units' WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='UnitsCycles', Name='Units for Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When units for cycles is defined the duration time is the total time for units', AD_Element_ID=53239 WHERE UPPER(ColumnName)='UNITSCYCLES' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='UnitsCycles', Name='Units for Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When units for cycles is defined the duration time is the total time for units' WHERE AD_Element_ID=53239 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='Units for Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When units for cycles is defined the duration time is the total time for units' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53239) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:21:00 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_PrintFormatItem SET PrintName='Units for Cycles', Name='Units for Cycles' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53239)
;

-- Feb 6, 2009 12:23:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Help='When Units by Cycles is defined the duration time is the total time for units', Name='Units by Cycles', PrintName='Units by Cycles',Updated=TO_TIMESTAMP('2009-02-06 00:23:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:23:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:23:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='UnitsCycles', Name='Units by Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles is defined the duration time is the total time for units' WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:23:10 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='UnitsCycles', Name='Units by Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles is defined the duration time is the total time for units', AD_Element_ID=53239 WHERE UPPER(ColumnName)='UNITSCYCLES' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:23:10 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='UnitsCycles', Name='Units by Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles is defined the duration time is the total time for units' WHERE AD_Element_ID=53239 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:23:10 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='Units by Cycles', Description='The Units for Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles is defined the duration time is the total time for units' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53239) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:23:10 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_PrintFormatItem SET PrintName='Units by Cycles', Name='Units by Cycles' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53239)
;

-- Feb 6, 2009 12:32:48 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Description='The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles are defined the duration time is the total of time to manufactured the units',Updated=TO_TIMESTAMP('2009-02-06 00:32:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:32:48 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:32:48 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='UnitsCycles', Name='Units by Cycles', Description='The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles are defined the duration time is the total of time to manufactured the units' WHERE AD_Element_ID=53239
;

-- Feb 6, 2009 12:32:48 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='UnitsCycles', Name='Units by Cycles', Description='The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles are defined the duration time is the total of time to manufactured the units', AD_Element_ID=53239 WHERE UPPER(ColumnName)='UNITSCYCLES' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:32:48 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='UnitsCycles', Name='Units by Cycles', Description='The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles are defined the duration time is the total of time to manufactured the units' WHERE AD_Element_ID=53239 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:32:48 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='Units by Cycles', Description='The Units by Cycles are defined for process type  Flow Repetitive Dedicated and  indicated the product to be manufactured on a production line for duration unit.', Help='When Units by Cycles are defined the duration time is the total of time to manufactured the units' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53239) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:36:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Description='Overlap Units are number of units that must be completed before they are moved the next activity', Help='When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.',Updated=TO_TIMESTAMP('2009-02-06 00:36:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53241
;

-- Feb 6, 2009 12:36:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53241
;

-- Feb 6, 2009 12:36:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='OverlapUnits', Name='Overlap Units', Description='Overlap Units are number of units that must be completed before they are moved the next activity', Help='When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.' WHERE AD_Element_ID=53241
;

-- Feb 6, 2009 12:36:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='OverlapUnits', Name='Overlap Units', Description='Overlap Units are number of units that must be completed before they are moved the next activity', Help='When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.', AD_Element_ID=53241 WHERE UPPER(ColumnName)='OVERLAPUNITS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:36:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='OverlapUnits', Name='Overlap Units', Description='Overlap Units are number of units that must be completed before they are moved the next activity', Help='When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.' WHERE AD_Element_ID=53241 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:36:09 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='Overlap Units', Description='Overlap Units are number of units that must be completed before they are moved the next activity', Help='When there are two consecutive avtivity, you can sometimes save time by moving partial quantites from one activity to the next before the first activity as been completed.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53241) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:49:41 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

',Updated=TO_TIMESTAMP('2009-02-06 00:49:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:49:41 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:49:41 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='Yield', Name='Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:49:41 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='Yield', Name='Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

', AD_Element_ID=53272 WHERE UPPER(ColumnName)='YIELD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:49:41 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='Yield', Name='Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Element_ID=53272 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:49:41 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53272) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:49:58 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Name='% Yield', PrintName='% Yield',Updated=TO_TIMESTAMP('2009-02-06 00:49:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:49:58 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:49:58 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='Yield', Name='% Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:49:58 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='Yield', Name='% Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

', AD_Element_ID=53272 WHERE UPPER(ColumnName)='YIELD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:49:58 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='Yield', Name='% Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Element_ID=53272 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:49:58 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='% Yield', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53272) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:49:59 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_PrintFormatItem SET PrintName='% Yield', Name='% Yield' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53272)
;

-- Feb 6, 2009 12:50:22 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element SET Name='Yield %', PrintName='Yield %',Updated=TO_TIMESTAMP('2009-02-06 00:50:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:50:22 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:50:22 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET ColumnName='Yield', Name='Yield %', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Element_ID=53272
;

-- Feb 6, 2009 12:50:23 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='Yield', Name='Yield %', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

', AD_Element_ID=53272 WHERE UPPER(ColumnName)='YIELD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 6, 2009 12:50:23 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Process_Para SET ColumnName='Yield', Name='Yield %', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Element_ID=53272 AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:50:23 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Field SET Name='Yield %', Description='The Yield is the percentage of a lot that is expected to be of acceptable wuality may fall below 100 percent', Help='ADempiere Calculate the total yield for a product from the yield for each activity when the process Workflow Cost Roll-Up is executed.

The expected yield for an Activity can be expressed as:

Yield = Acceptable Units at Activity End x 100

The Total manufacturing yield for a product is determined by multiplying the yied percentage for each activity.

Manufacturing Yield = Yield % for Activity 10 x Yied % for Activity 20 , etc 

Take care when setting yield to anything but 100% particularly when yied is used for multiples activities

' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53272) AND IsCentrallyMaintained='Y'
;

-- Feb 6, 2009 12:50:23 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_PrintFormatItem SET PrintName='Yield %', Name='Yield %' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53272)
;

-- Feb 6, 2009 1:46:34 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET DefaultValue='100',Updated=TO_TIMESTAMP('2009-02-06 01:46:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56777
;

-- Feb 6, 2009 1:46:38 AM ECT
-- Implement Yield and Units Cycle
insert into t_alter_column values('ad_workflow','Yield','NUMERIC(10)',null,'100')
;

-- Feb 6, 2009 1:47:29 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET DefaultValue='100',Updated=TO_TIMESTAMP('2009-02-06 01:47:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56776
;

-- Feb 6, 2009 1:47:31 AM ECT
-- Implement Yield and Units Cycle
insert into t_alter_column values('ad_wf_node','Yield','NUMERIC(10)',null,'100')
;

-- Feb 6, 2009 1:48:04 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET DefaultValue='100',Updated=TO_TIMESTAMP('2009-02-06 01:48:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56779
;

-- Feb 6, 2009 1:48:08 AM ECT
-- Implement Yield and Units Cycle
insert into t_alter_column values('pp_order_workflow','Yield','NUMERIC(10)',null,'100')
;

-- Feb 6, 2009 1:48:28 AM ECT
-- Implement Yield and Units Cycle
UPDATE AD_Column SET DefaultValue='100',Updated=TO_TIMESTAMP('2009-02-06 01:48:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56781
;

-- Feb 6, 2009 1:48:31 AM ECT
-- Implement Yield and Units Cycle
insert into t_alter_column values('pp_order_node','Yield','NUMERIC(10)',null,'100')
;

