-- Jun 13, 2010 10:20:08 PM CDT
-- Human Resource Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59228,1383,0,19,53102,'C_BP_Group_ID',TO_TIMESTAMP('2010-06-13 22:20:05','YYYY-MM-DD HH24:MI:SS'),0,'Business Partner Group','EE02',10,'The Business Partner Group provides a method of defining defaults to be used for individual Business Partners.','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Business Partner Group',0,TO_TIMESTAMP('2010-06-13 22:20:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 13, 2010 10:20:08 PM CDT
-- Human Resource Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59228 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 13, 2010 10:20:17 PM CDT
-- Human Resource Management
ALTER TABLE HR_Movement ADD COLUMN C_BP_Group_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 13, 2010 10:20:57 PM CDT
-- Human Resource Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59229,837,0,19,53102,'C_BP_BankAccount_ID',TO_TIMESTAMP('2010-06-13 22:20:55','YYYY-MM-DD HH24:MI:SS'),0,'Bank Account of the Business Partner','EE02',22,'The Partner Bank Account identifies the bank account to be used for this Business Partner','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Partner Bank Account',0,TO_TIMESTAMP('2010-06-13 22:20:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 13, 2010 10:20:57 PM CDT
-- Human Resource Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59229 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 13, 2010 10:21:10 PM CDT
-- Human Resource Management
ALTER TABLE HR_Movement ADD COLUMN C_BP_BankAccount_ID NUMERIC(10) DEFAULT NULL 
;

-- Jun 13, 2010 10:21:21 PM CDT
-- Human Resource Management
UPDATE AD_Column SET FieldLength=22,Updated=TO_TIMESTAMP('2010-06-13 22:21:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59228
;

-- Jun 13, 2010 10:25:06 PM CDT
-- Human Resource Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59230,146,0,17,118,53102,'AccountSign',TO_TIMESTAMP('2010-06-13 22:25:05','YYYY-MM-DD HH24:MI:SS'),0,'Indicates the Natural Sign of the Account as a Debit or Credit','EE02',1,'Indicates if the expected balance for this account should be a Debit or a Credit. If set to Natural, the account sign for an asset or expense account is Debit Sign (i.e. negative if a credit balance).','Y','Y','N','N','N','N','N','N','N','N','N','N','N','Account Sign',0,TO_TIMESTAMP('2010-06-13 22:25:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 13, 2010 10:25:06 PM CDT
-- Human Resource Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59230 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jun 13, 2010 10:25:13 PM CDT
-- Human Resource Management
ALTER TABLE HR_Movement ADD COLUMN AccountSign CHAR(1) DEFAULT NULL 
;

