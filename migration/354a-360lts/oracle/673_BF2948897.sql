-- Mar 4, 2010 1:54:35 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET AD_Reference_ID=20,Updated=TO_DATE('2010-03-04 13:54:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55347
;

-- Mar 4, 2010 1:55:33 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET AD_Process_ID=NULL, AD_Reference_ID=20,Updated=TO_DATE('2010-03-04 13:55:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55557
;

-- Mar 4, 2010 1:56:07 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation MODIFY Processed CHAR(1) DEFAULT 'Y'
;

-- Mar 4, 2010 1:56:08 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE A_Depreciation SET Processed='Y' WHERE Processed IS NULL
;

-- Mar 4, 2010 1:56:28 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2010-03-04 13:56:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55557
;

-- Mar 4, 2010 1:56:30 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation_Entry MODIFY Processed CHAR(1) DEFAULT 'N'
;

-- Mar 4, 2010 1:56:31 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE A_Depreciation_Entry SET Processed='N' WHERE Processed IS NULL
;

-- Mar 4, 2010 1:56:58 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET AD_Process_ID=53111, AD_Reference_ID=28,Updated=TO_DATE('2010-03-04 13:56:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55703
;

-- Mar 4, 2010 1:57:00 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation_Build MODIFY Processing CHAR(1) DEFAULT NULL 
;

-- Mar 4, 2010 1:57:15 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET AD_Process_ID=NULL, AD_Reference_ID=20, DefaultValue='N',Updated=TO_DATE('2010-03-04 13:57:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55700
;

-- Mar 4, 2010 1:57:19 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation_Build MODIFY Processed CHAR(1) DEFAULT 'N'
;

-- Mar 4, 2010 1:58:11 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,59065,524,0,53123,28,53125,'Processing',TO_DATE('2010-03-04 13:58:09','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','N','N','N','Y','N','N','Y','N','Y','Process Now',TO_DATE('2010-03-04 13:58:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Mar 4, 2010 1:58:11 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59065 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Mar 4, 2010 1:58:36 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET IsMandatory='N',Updated=TO_DATE('2010-03-04 13:58:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59065
;

-- Mar 4, 2010 1:58:38 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation_Convention ADD Processing CHAR(1) DEFAULT NULL 
;

-- Mar 4, 2010 1:58:51 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET AD_Process_ID=NULL, AD_Reference_ID=20, DefaultValue='N',Updated=TO_DATE('2010-03-04 13:58:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55736
;

-- Mar 4, 2010 1:58:54 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation_Convention MODIFY Processed CHAR(1) DEFAULT 'N'
;

-- Mar 4, 2010 1:58:54 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE A_Depreciation_Convention SET Processed='N' WHERE Processed IS NULL
;

-- Mar 4, 2010 1:59:46 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE AD_Column SET AD_Process_ID=NULL, AD_Reference_ID=20,Updated=TO_DATE('2010-03-04 13:59:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=55747
;

-- Mar 4, 2010 1:59:49 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
ALTER TABLE A_Depreciation_Method MODIFY Processed CHAR(1) DEFAULT 'Y'
;

-- Mar 4, 2010 1:59:49 PM COT
-- BF2948897_Fixed Assets Dictionary Errors
UPDATE A_Depreciation_Method SET Processed='Y' WHERE Processed IS NULL
;

