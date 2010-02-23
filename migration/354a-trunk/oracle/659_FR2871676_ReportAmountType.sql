SET DEFINE OFF;
-- 02/10/2009 11:33:15 AM
-- Add signed amount type to fin report
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53327,TO_DATE('2009-10-02 11:33:12','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','PA_Report Period Type',TO_DATE('2009-10-02 11:33:12','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- 02/10/2009 11:33:15 AM
-- Add signed amount type to fin report
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53327 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 02/10/2009 11:33:29 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53327,53536,TO_DATE('2009-10-02 11:33:28','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Total',TO_DATE('2009-10-02 11:33:28','YYYY-MM-DD HH24:MI:SS'),100,'T')
;

-- 02/10/2009 11:33:29 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53536 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:33:38 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53327,53537,TO_DATE('2009-10-02 11:33:37','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Year',TO_DATE('2009-10-02 11:33:37','YYYY-MM-DD HH24:MI:SS'),100,'Y')
;

-- 02/10/2009 11:33:38 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53537 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:33:51 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53327,53538,TO_DATE('2009-10-02 11:33:50','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Period',TO_DATE('2009-10-02 11:33:50','YYYY-MM-DD HH24:MI:SS'),100,'P')
;

-- 02/10/2009 11:33:51 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53538 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:40:15 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53327,53540,TO_DATE('2009-10-02 11:40:14','YYYY-MM-DD HH24:MI:SS'),100,'Year for P & L account, Total for Balance Sheet account','D','Y','Natural',TO_DATE('2009-10-02 11:40:14','YYYY-MM-DD HH24:MI:SS'),100,'N')
;

-- 02/10/2009 11:40:15 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53540 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:42:57 AM
-- Add signed amount type to fin report
UPDATE AD_Reference SET IsActive='N', Name='PA_Report AmountType (deprecated)',Updated=TO_DATE('2009-10-02 11:42:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=235
;

-- 02/10/2009 11:42:57 AM
-- Add signed amount type to fin report
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=235
;

-- 02/10/2009 11:43:16 AM
-- Add signed amount type to fin report
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53328,TO_DATE('2009-10-02 11:43:15','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','N','PA_Report Amount Type',TO_DATE('2009-10-02 11:43:15','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- 02/10/2009 11:43:16 AM
-- Add signed amount type to fin report
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53328 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- 02/10/2009 11:44:10 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53328,53541,TO_DATE('2009-10-02 11:44:09','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Balance (expected sign)',TO_DATE('2009-10-02 11:44:09','YYYY-MM-DD HH24:MI:SS'),100,'B')
;

-- 02/10/2009 11:44:10 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53541 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:44:32 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53328,53542,TO_DATE('2009-10-02 11:44:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Credit Only',TO_DATE('2009-10-02 11:44:30','YYYY-MM-DD HH24:MI:SS'),100,'C')
;

-- 02/10/2009 11:44:32 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53542 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:44:42 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53328,53543,TO_DATE('2009-10-02 11:44:41','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Debit Only',TO_DATE('2009-10-02 11:44:41','YYYY-MM-DD HH24:MI:SS'),100,'D')
;

-- 02/10/2009 11:44:42 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53543 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:45:01 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53328,53544,TO_DATE('2009-10-02 11:45:00','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Quantity',TO_DATE('2009-10-02 11:45:00','YYYY-MM-DD HH24:MI:SS'),100,'Q')
;

-- 02/10/2009 11:45:01 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53544 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:48:32 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53328,53545,TO_DATE('2009-10-02 11:48:31','YYYY-MM-DD HH24:MI:SS'),100,'DR - CR','D','Y','Balance (accounted sign)',TO_DATE('2009-10-02 11:48:31','YYYY-MM-DD HH24:MI:SS'),100,'S')
;

-- 02/10/2009 11:48:32 AM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53545 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 11:52:38 AM
-- Add signed amount type to fin report
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54061,0,'PAPeriodType',TO_DATE('2009-10-02 11:52:35','YYYY-MM-DD HH24:MI:SS'),100,'PA Period Type','D','The Period Type to report on: Period, Year, Total or Natural; where Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Period Type','Period Type',TO_DATE('2009-10-02 11:52:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 11:52:38 AM
-- Add signed amount type to fin report
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54061 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 02/10/2009 11:56:47 AM
-- Add signed amount type to fin report
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54062,0,'PAAmountType',TO_DATE('2009-10-02 11:56:46','YYYY-MM-DD HH24:MI:SS'),100,'PA Amount Type for reporting','D','The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (DR-CR). Balance (expected sign) adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element.','Y','Amount Type','Amount Type',TO_DATE('2009-10-02 11:56:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 11:56:47 AM
-- Add signed amount type to fin report
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54062 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 02/10/2009 11:56:58 AM
-- Add signed amount type to fin report
UPDATE AD_Element SET Help='The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.',Updated=TO_DATE('2009-10-02 11:56:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54061
;

-- 02/10/2009 11:56:58 AM
-- Add signed amount type to fin report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54061
;

-- 02/10/2009 11:56:58 AM
-- Add signed amount type to fin report
UPDATE AD_Column SET ColumnName='PAPeriodType', Name='Period Type', Description='PA Period Type', Help='The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.' WHERE AD_Element_ID=54061
;

-- 02/10/2009 11:56:58 AM
-- Add signed amount type to fin report
UPDATE AD_Process_Para SET ColumnName='PAPeriodType', Name='Period Type', Description='PA Period Type', Help='The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.', AD_Element_ID=54061 WHERE UPPER(ColumnName)='PAPERIODTYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 02/10/2009 11:56:58 AM
-- Add signed amount type to fin report
UPDATE AD_Process_Para SET ColumnName='PAPeriodType', Name='Period Type', Description='PA Period Type', Help='The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.' WHERE AD_Element_ID=54061 AND IsCentrallyMaintained='Y'
;

-- 02/10/2009 11:56:58 AM
-- Add signed amount type to fin report
UPDATE AD_Field SET Name='Period Type', Description='PA Period Type', Help='The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54061) AND IsCentrallyMaintained='Y'
;

-- 02/10/2009 11:58:09 AM
-- Add signed amount type to fin report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58553,54061,0,17,53327,446,'PAPeriodType',TO_DATE('2009-10-02 11:58:08','YYYY-MM-DD HH24:MI:SS'),100,'P','PA Period Type','D',1,'The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Period Type',0,TO_DATE('2009-10-02 11:58:08','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 02/10/2009 11:58:09 AM
-- Add signed amount type to fin report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58553 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 02/10/2009 11:58:17 AM
-- Add signed amount type to fin report
ALTER TABLE PA_ReportColumn ADD PAPeriodType CHAR(1) DEFAULT 'P'
;

-- 02/10/2009 11:59:04 AM
-- Add signed amount type to fin report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58554,54062,0,17,53328,446,'PAAmountType',TO_DATE('2009-10-02 11:59:03','YYYY-MM-DD HH24:MI:SS'),100,'B','PA Amount Type for reporting','D',1,'The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (DR-CR). Balance (expected sign) adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Amount Type',0,TO_DATE('2009-10-02 11:59:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 02/10/2009 11:59:04 AM
-- Add signed amount type to fin report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58554 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 02/10/2009 12:01:36 PM
-- Add signed amount type to fin report
UPDATE AD_Element SET Help='The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.',Updated=TO_DATE('2009-10-02 12:01:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54062
;

-- 02/10/2009 12:01:36 PM
-- Add signed amount type to fin report
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=54062
;

-- 02/10/2009 12:01:36 PM
-- Add signed amount type to fin report
UPDATE AD_Column SET ColumnName='PAAmountType', Name='Amount Type', Description='PA Amount Type for reporting', Help='The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.' WHERE AD_Element_ID=54062
;

-- 02/10/2009 12:01:36 PM
-- Add signed amount type to fin report
UPDATE AD_Process_Para SET ColumnName='PAAmountType', Name='Amount Type', Description='PA Amount Type for reporting', Help='The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.', AD_Element_ID=54062 WHERE UPPER(ColumnName)='PAAMOUNTTYPE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- 02/10/2009 12:01:36 PM
-- Add signed amount type to fin report
UPDATE AD_Process_Para SET ColumnName='PAAmountType', Name='Amount Type', Description='PA Amount Type for reporting', Help='The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.' WHERE AD_Element_ID=54062 AND IsCentrallyMaintained='Y'
;

-- 02/10/2009 12:01:36 PM
-- Add signed amount type to fin report
UPDATE AD_Field SET Name='Amount Type', Description='PA Amount Type for reporting', Help='The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=54062) AND IsCentrallyMaintained='Y'
;

-- 02/10/2009 12:03:11 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58555,54062,0,17,53328,448,'PAAmountType',TO_DATE('2009-10-02 12:03:10','YYYY-MM-DD HH24:MI:SS'),100,'PA Amount Type for reporting','D',2,'The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Amount Type',0,TO_DATE('2009-10-02 12:03:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 02/10/2009 12:03:11 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58555 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 02/10/2009 12:03:20 PM
-- Add signed amount type to fin report
UPDATE AD_Column SET FieldLength=1,Updated=TO_DATE('2009-10-02 12:03:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58555
;

-- 02/10/2009 12:03:23 PM
-- Add signed amount type to fin report
ALTER TABLE PA_ReportLine ADD PAAmountType CHAR(1) DEFAULT NULL 
;

-- 02/10/2009 12:03:48 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58556,54061,0,17,53327,448,'PAPeriodType',TO_DATE('2009-10-02 12:03:47','YYYY-MM-DD HH24:MI:SS'),100,'PA Period Type','D',1,'The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Period Type',0,TO_DATE('2009-10-02 12:03:47','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 02/10/2009 12:03:48 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58556 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 02/10/2009 12:03:50 PM
-- Add signed amount type to fin report
ALTER TABLE PA_ReportLine ADD PAPeriodType CHAR(1) DEFAULT NULL 
;

-- 02/10/2009 12:04:03 PM
-- Add signed amount type to fin report
ALTER TABLE PA_ReportColumn ADD PAAmountType CHAR(1) DEFAULT 'B'
;

-- 02/10/2009 12:04:29 PM
-- Add signed amount type to fin report
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2009-10-02 12:04:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6019
;

-- 02/10/2009 12:04:39 PM
-- Add signed amount type to fin report
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2009-10-02 12:04:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7707
;

-- 02/10/2009 12:09:57 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58554,58041,0,374,TO_DATE('2009-10-02 12:09:56','YYYY-MM-DD HH24:MI:SS'),100,'PA Amount Type for reporting',14,'D','The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.','Y','Y','Y','N','N','N','N','N','Amount Type',110,TO_DATE('2009-10-02 12:09:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 12:09:57 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58041 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02/10/2009 12:11:17 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58553,58042,0,374,TO_DATE('2009-10-02 12:11:16','YYYY-MM-DD HH24:MI:SS'),100,'PA Period Type',14,'D','The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Y','Y','N','N','N','N','Y','Period Type',115,TO_DATE('2009-10-02 12:11:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 12:11:17 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58042 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02/10/2009 12:11:26 PM
-- Add signed amount type to fin report
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2009-10-02 12:11:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=4760
;

-- 02/10/2009 12:13:14 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58555,58043,0,376,TO_DATE('2009-10-02 12:13:13','YYYY-MM-DD HH24:MI:SS'),100,'PA Amount Type for reporting',14,'@LineType@=S','D','The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.','Y','Y','Y','N','N','N','N','N','Amount Type',180,TO_DATE('2009-10-02 12:13:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 12:13:14 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58043 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02/10/2009 12:13:46 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58556,58044,0,376,TO_DATE('2009-10-02 12:13:45','YYYY-MM-DD HH24:MI:SS'),100,'PA Period Type',14,'@LineType@=S','D','The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Y','Y','N','N','N','N','Y','Period Type',185,TO_DATE('2009-10-02 12:13:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 12:13:46 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58044 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02/10/2009 12:13:57 PM
-- Add signed amount type to fin report
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2009-10-02 12:13:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=5807
;

-- 02/10/2009 12:16:06 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58557,54062,0,17,53328,535,'PAAmountType',TO_DATE('2009-10-02 12:16:05','YYYY-MM-DD HH24:MI:SS'),100,'PA Amount Type for reporting','D',1,'The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Amount Type',0,TO_DATE('2009-10-02 12:16:05','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 02/10/2009 12:16:06 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58557 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 02/10/2009 12:16:10 PM
-- Add signed amount type to fin report
ALTER TABLE I_ReportLine ADD PAAmountType CHAR(1) DEFAULT NULL 
;

-- 02/10/2009 12:16:40 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58558,54061,0,17,53327,535,'PAPeriodType',TO_DATE('2009-10-02 12:16:34','YYYY-MM-DD HH24:MI:SS'),100,'PA Period Type','D',1,'The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Period Type',0,TO_DATE('2009-10-02 12:16:34','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- 02/10/2009 12:16:40 PM
-- Add signed amount type to fin report
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58558 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 02/10/2009 12:16:43 PM
-- Add signed amount type to fin report
ALTER TABLE I_ReportLine ADD PAPeriodType CHAR(1) DEFAULT NULL 
;

-- 02/10/2009 12:16:52 PM
-- Add signed amount type to fin report
UPDATE AD_Column SET IsActive='N',Updated=TO_DATE('2009-10-02 12:16:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=7954
;

-- 02/10/2009 12:18:35 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58557,58045,0,444,TO_DATE('2009-10-02 12:18:35','YYYY-MM-DD HH24:MI:SS'),100,'PA Amount Type for reporting',14,'D','The amount type to report on: Quantity, Credit Only, Debit Only, Balance (expected sign) or Balance (accounted sign). "Expected sign" adjusts the sign of the result based on the Account Type and Expected Sign of each Account Element, whereas "accounted sign" always returns DR-CR.','Y','Y','Y','N','N','N','N','N','Amount Type',160,TO_DATE('2009-10-02 12:18:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 12:18:35 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58045 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02/10/2009 12:18:56 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58558,58046,0,444,TO_DATE('2009-10-02 12:18:55','YYYY-MM-DD HH24:MI:SS'),100,'PA Period Type',14,'D','The Period Type to report on: Period, Year, Total or Natural. Natural = Year for P & L accounts, Total for Balance Sheet accounts.','Y','Y','Y','N','N','N','N','N','Period Type',165,TO_DATE('2009-10-02 12:18:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- 02/10/2009 12:18:56 PM
-- Add signed amount type to fin report
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58046 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 02/10/2009 12:19:04 PM
-- Add signed amount type to fin report
UPDATE AD_Field SET IsActive='N',Updated=TO_DATE('2009-10-02 12:19:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=6043
;

-- 02/10/2009 1:48:32 PM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Reference_ID,AD_Ref_List_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53328,53546,TO_DATE('2009-10-02 13:48:30','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Quantity (accounted sign)',TO_DATE('2009-10-02 13:48:30','YYYY-MM-DD HH24:MI:SS'),100,'R')
;

-- 02/10/2009 1:48:32 PM
-- Add signed amount type to fin report
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53546 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- 02/10/2009 1:48:43 PM
-- Add signed amount type to fin report
UPDATE AD_Ref_List SET Name='Quantity (expected sign)',Updated=TO_DATE('2009-10-02 13:48:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Ref_List_ID=53544
;

-- 02/10/2009 1:48:43 PM
-- Add signed amount type to fin report
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=53544
;

update pa_reportline set paamounttype = substr(amounttype, 1,1), paperiodtype = substr(amounttype,2,1);

update pa_reportcolumn set paamounttype = substr(amounttype, 1,1), paperiodtype = substr(amounttype,2,1);



