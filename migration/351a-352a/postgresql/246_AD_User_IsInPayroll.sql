
-- Jul 29, 2008 11:53:38 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53676,0,'IsInPayroll',TO_TIMESTAMP('2008-07-29 11:53:37','YYYY-MM-DD HH24:MI:SS'),0,'Defined if any User Contact will be used for Calculate Payroll','EE02','Y','Is In Payroll','Is In Payroll',TO_TIMESTAMP('2008-07-29 11:53:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 29, 2008 11:53:38 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53676 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jul 29, 2008 11:54:29 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,14619,56325,0,53108,TO_TIMESTAMP('2008-07-29 11:54:27','YYYY-MM-DD HH24:MI:SS'),0,'How a Java Client connects to the server(s)',1,'D','Depending on the connection profile, different protocols are used and tasks are performed on the server rather then the client. Usually the user can select different profiles, unless it is enforced by the User or Role definition. The User level profile overwrites the Role based profile.','Y','Y','Y','N','N','N','N','N','Connection Profile',TO_TIMESTAMP('2008-07-29 11:54:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 29, 2008 11:54:29 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56325 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 29, 2008 11:54:30 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,15975,56326,0,53108,TO_TIMESTAMP('2008-07-29 11:54:29','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',TO_TIMESTAMP('2008-07-29 11:54:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 29, 2008 11:54:30 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56326 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 29, 2008 11:54:31 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,52066,56327,0,53108,TO_TIMESTAMP('2008-07-29 11:54:30','YYYY-MM-DD HH24:MI:SS'),0,20,'D','Y','Y','Y','N','N','N','N','N','UserPIN',TO_TIMESTAMP('2008-07-29 11:54:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 29, 2008 11:54:31 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56327 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 29, 2008 11:55:25 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56294,53676,0,20,114,'IsInPayroll',TO_TIMESTAMP('2008-07-29 11:55:24','YYYY-MM-DD HH24:MI:SS'),0,'Defined if any User Contact will be used for Calculate Payroll','EE02',1,'Y','Y','N','N','N','N','N','N','N','N','N','Y','Is In Payroll',0,TO_TIMESTAMP('2008-07-29 11:55:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jul 29, 2008 11:55:26 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56294 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 29, 2008 11:55:30 AM CDT
-- Fix Payroll bug
ALTER TABLE AD_User ADD COLUMN IsInPayroll CHAR(1) CHECK (IsInPayroll IN ('Y','N'))
;

-- Jul 29, 2008 11:55:40 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56294,56328,0,53108,TO_TIMESTAMP('2008-07-29 11:55:40','YYYY-MM-DD HH24:MI:SS'),0,'Defined if any User Contact will be used for Calculate Payroll',1,'EE02','Y','Y','Y','N','N','N','N','N','Is In Payroll',TO_TIMESTAMP('2008-07-29 11:55:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jul 29, 2008 11:55:40 AM CDT
-- Fix Payroll bug
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56328 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jul 29, 2008 11:56:01 AM CDT
-- Fix Payroll bug
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56325
;

-- Jul 29, 2008 11:56:01 AM CDT
-- Fix Payroll bug
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56326
;

-- Jul 29, 2008 11:56:01 AM CDT
-- Fix Payroll bug
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=56327
;

-- Jul 29, 2008 11:56:01 AM CDT
-- Fix Payroll bug
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=56328
;

