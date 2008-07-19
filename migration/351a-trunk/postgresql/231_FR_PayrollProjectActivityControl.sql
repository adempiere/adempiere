-- 18-jul-2008 19:14:17 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56177,1005,0,19,53088,'C_Activity_ID',TO_TIMESTAMP('2008-07-18 19:14:05','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity','EE02',10,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','N','N','N','N','N','N','N','N','N','Y','Activity',0,TO_TIMESTAMP('2008-07-18 19:14:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:14:17 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56177 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:16:36 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56178,550,0,19,53102,'C_Campaign_ID',TO_TIMESTAMP('2008-07-18 19:16:34','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign','EE02',10,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_TIMESTAMP('2008-07-18 19:16:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:16:36 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56178 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:17:37 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56179,112,0,19,53102,'AD_OrgTrx_ID',TO_TIMESTAMP('2008-07-18 19:17:34','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization','EE02',10,'The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','N','N','N','N','N','N','N','N','N','Y','Trx Organization',0,TO_TIMESTAMP('2008-07-18 19:17:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:17:37 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56179 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:17:58 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN C_Campaign_ID NUMERIC(10)
;

-- 18-jul-2008 19:18:06 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN AD_OrgTrx_ID NUMERIC(10)
;

-- 18-jul-2008 19:19:32 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56180,2073,0,19,53102,262,'C_ProjectPhase_ID',TO_TIMESTAMP('2008-07-18 19:19:31','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project','EE02',10,'Y','N','N','N','N','N','N','N','N','N','Y','Project Phase',0,TO_TIMESTAMP('2008-07-18 19:19:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:19:32 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56180 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:19:36 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN C_ProjectPhase_ID NUMERIC(10)
;

-- 18-jul-2008 19:20:40 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56181,2074,0,19,53102,263,'C_ProjectTask_ID',TO_TIMESTAMP('2008-07-18 19:20:39','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase','EE02',10,'A Project Task in a Project Phase represents the actual work.','Y','N','N','N','N','N','N','N','N','N','Y','Project Task',0,TO_TIMESTAMP('2008-07-18 19:20:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:20:40 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56181 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:20:43 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN C_ProjectTask_ID NUMERIC(10)
;

-- 18-jul-2008 19:21:55 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56182,208,0,19,53102,'C_Project_ID',TO_TIMESTAMP('2008-07-18 19:21:54','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project','EE02',10,'A Project allows you to track and control internal or external activities.','Y','N','N','N','N','N','N','N','N','N','Y','Project',0,TO_TIMESTAMP('2008-07-18 19:21:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:21:55 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56182 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:21:57 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN C_Project_ID NUMERIC(10)
;

-- 18-jul-2008 19:23:32 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56183,613,0,18,134,53102,'User1_ID',TO_TIMESTAMP('2008-07-18 19:23:31','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE02',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','N','N','Y','User List 1',0,TO_TIMESTAMP('2008-07-18 19:23:31','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:23:32 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56183 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:23:35 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN User1_ID NUMERIC(10)
;

-- 18-jul-2008 19:24:01 CDT
-- Improve in Payroll
UPDATE AD_Column SET AD_Element_ID=614, AD_Reference_Value_ID=137, ColumnName='User2_ID', Description='User defined list element #2', Help='The user defined element displays the optional elements that have been defined for this account combination.', Name='User List 2',Updated=TO_TIMESTAMP('2008-07-18 19:24:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56183
;

-- 18-jul-2008 19:24:01 CDT
-- Improve in Payroll
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=56183
;

-- 18-jul-2008 19:24:01 CDT
-- Improve in Payroll
UPDATE AD_Field SET Name='User List 2', Description='User defined list element #2', Help='The user defined element displays the optional elements that have been defined for this account combination.' WHERE AD_Column_ID=56183 AND IsCentrallyMaintained='Y'
;

-- 18-jul-2008 19:24:06 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN User2_ID NUMERIC(10)
;

-- 18-jul-2008 19:25:08 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56184,613,0,18,134,53102,'User1_ID',TO_TIMESTAMP('2008-07-18 19:25:07','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1','EE02',10,'The user defined element displays the optional elements that have been defined for this account combination.','Y','N','N','N','N','N','N','N','N','N','Y','User List 1',0,TO_TIMESTAMP('2008-07-18 19:25:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:25:08 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56184 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:25:11 CDT
-- Improve in Payroll
insert into t_alter_column values('hr_movement','User1_ID','NUMERIC(10)',null,'NULL')
;

-- 18-jul-2008 19:27:08 CDT
-- Improve in Payroll
UPDATE AD_Element SET Name='Manufacturing Order Activity', PrintName='Manufacturing Order Activity',Updated=TO_TIMESTAMP('2008-07-18 19:27:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53285
;

-- 18-jul-2008 19:27:08 CDT
-- Improve in Payroll
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53285
;

-- 18-jul-2008 19:27:08 CDT
-- Improve in Payroll
UPDATE AD_Column SET ColumnName='PP_Order_Node_ID', Name='Manufacturing Order Activity', Description=NULL, Help=NULL WHERE AD_Element_ID=53285
;

-- 18-jul-2008 19:27:08 CDT
-- Improve in Payroll
UPDATE AD_Process_Para SET ColumnName='PP_Order_Node_ID', Name='Manufacturing Order Activity', Description=NULL, Help=NULL, AD_Element_ID=53285 WHERE UPPER(ColumnName)='PP_ORDER_NODE_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 18-jul-2008 19:27:08 CDT
-- Improve in Payroll
UPDATE AD_Process_Para SET ColumnName='PP_Order_Node_ID', Name='Manufacturing Order Activity', Description=NULL, Help=NULL WHERE AD_Element_ID=53285 AND IsCentrallyMaintained='Y'
;

-- 18-jul-2008 19:27:08 CDT
-- Improve in Payroll
UPDATE AD_Field SET Name='Manufacturing Order Activity', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53285) AND IsCentrallyMaintained='Y'
;

-- 18-jul-2008 19:27:09 CDT
-- Improve in Payroll
UPDATE AD_PrintFormatItem SET PrintName='Manufacturing Order Activity', Name='Manufacturing Order Activity' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53285)
;

-- 18-jul-2008 19:27:36 CDT
-- Improve in Payroll
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56185,53285,0,19,53102,'PP_Order_Node_ID',TO_TIMESTAMP('2008-07-18 19:27:35','YYYY-MM-DD HH24:MI:SS'),0,'EE02',10,'Y','N','N','N','N','N','N','N','N','N','Y','Manufacturing Order Activity',0,TO_TIMESTAMP('2008-07-18 19:27:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- 18-jul-2008 19:27:36 CDT
-- Improve in Payroll
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56185 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 18-jul-2008 19:27:39 CDT
-- Improve in Payroll
ALTER TABLE HR_Movement ADD COLUMN PP_Order_Node_ID NUMERIC(10)
;

-- 18-jul-2008 19:30:21 CDT
-- Improve in Payroll
ALTER TABLE HR_Department ADD COLUMN C_Activity_ID NUMERIC(10)
;

-- 18-jul-2008 19:31:05 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56177,56307,0,53109,TO_TIMESTAMP('2008-07-18 19:31:04','YYYY-MM-DD HH24:MI:SS'),0,'Business Activity',10,'EE02','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','Y','N','N','N','N','N','Activity',TO_TIMESTAMP('2008-07-18 19:31:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:06 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56307 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:38 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56178,56308,0,53124,TO_TIMESTAMP('2008-07-18 19:31:36','YYYY-MM-DD HH24:MI:SS'),0,'Marketing Campaign',10,'EE02','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','N','N','Campaign',TO_TIMESTAMP('2008-07-18 19:31:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:38 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56308 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:39 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56185,56309,0,53124,TO_TIMESTAMP('2008-07-18 19:31:38','YYYY-MM-DD HH24:MI:SS'),0,10,'EE02','Y','Y','Y','N','N','N','N','N','Manufacturing Order Activity',TO_TIMESTAMP('2008-07-18 19:31:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:39 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56309 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:40 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56182,56310,0,53124,TO_TIMESTAMP('2008-07-18 19:31:39','YYYY-MM-DD HH24:MI:SS'),0,'Financial Project',10,'EE02','A Project allows you to track and control internal or external activities.','Y','Y','Y','N','N','N','N','N','Project',TO_TIMESTAMP('2008-07-18 19:31:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:40 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56310 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:41 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56180,56311,0,53124,TO_TIMESTAMP('2008-07-18 19:31:40','YYYY-MM-DD HH24:MI:SS'),0,'Phase of a Project',10,'EE02','Y','Y','Y','N','N','N','N','N','Project Phase',TO_TIMESTAMP('2008-07-18 19:31:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:41 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56311 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:42 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56181,56312,0,53124,TO_TIMESTAMP('2008-07-18 19:31:41','YYYY-MM-DD HH24:MI:SS'),0,'Actual Project Task in a Phase',10,'EE02','A Project Task in a Project Phase represents the actual work.','Y','Y','Y','N','N','N','N','N','Project Task',TO_TIMESTAMP('2008-07-18 19:31:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:42 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56312 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:43 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56179,56313,0,53124,TO_TIMESTAMP('2008-07-18 19:31:42','YYYY-MM-DD HH24:MI:SS'),0,'Performing or initiating organization',10,'EE02','The organization which performs or initiates this transaction (for another organization).  The owning Organization may not be the transaction organization in a service bureau environment, with centralized services, and inter-organization transactions.','Y','Y','Y','N','N','N','N','N','Trx Organization',TO_TIMESTAMP('2008-07-18 19:31:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:43 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56313 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:44 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56184,56314,0,53124,TO_TIMESTAMP('2008-07-18 19:31:43','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #1',10,'EE02','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','N','N','User List 1',TO_TIMESTAMP('2008-07-18 19:31:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:44 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56314 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:31:45 CDT
-- Improve in Payroll
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56183,56315,0,53124,TO_TIMESTAMP('2008-07-18 19:31:44','YYYY-MM-DD HH24:MI:SS'),0,'User defined list element #2',10,'EE02','The user defined element displays the optional elements that have been defined for this account combination.','Y','Y','Y','N','N','N','N','N','User List 2',TO_TIMESTAMP('2008-07-18 19:31:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 18-jul-2008 19:31:45 CDT
-- Improve in Payroll
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56315 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=56313
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=56308
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=56314
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=56315
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=56311
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=56312
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=56310
;

-- 18-jul-2008 19:32:39 CDT
-- Improve in Payroll
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=56309
;

-- 18-jul-2008 19:33:53 CDT
-- Improve in Payroll
UPDATE AD_Tab SET Description='Introduce the Name to identify the operations from the manufacturing routing. If desired give a Description for activity.', Help='Introduce the Name to identify the operations from the manufacturing routing. If desired give a Description for activity.', Name='Activity',Updated=TO_TIMESTAMP('2008-07-18 19:33:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53025
;

-- 18-jul-2008 19:33:53 CDT
-- Improve in Payroll
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53025
;

-- 18-jul-2008 19:35:01 CDT
-- Improve in Payroll
UPDATE AD_Tab SET Description='Introduce the Name to identify the operations from the manufacturing routing. If desired give a Description for activity.', Help='Introduce the Name to identify the operations from the manufacturing routing. If desired give a Description for activity.', Name='Order Activity',Updated=TO_TIMESTAMP('2008-07-18 19:35:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53036
;

-- 18-jul-2008 19:35:01 CDT
-- Improve in Payroll
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=53036
;

-- 18-jul-2008 19:36:05 CDT
-- Improve in Payroll
UPDATE AD_Menu SET Description='Activity Control', Name='Activity Control',Updated=TO_TIMESTAMP('2008-07-18 19:36:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53063
;

-- 18-jul-2008 19:36:05 CDT
-- Improve in Payroll
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53063
;

-- 18-jul-2008 19:36:38 CDT
-- Improve in Payroll
UPDATE AD_Menu SET Description='Show the Activity Control Transaction Details', Name='Activity Control Transaction Details',Updated=TO_TIMESTAMP('2008-07-18 19:36:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53065
;

-- 18-jul-2008 19:36:38 CDT
-- Improve in Payroll
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53065
;

