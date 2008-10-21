-- 2008-10-21 11:47:06 EEST
-- Posterita intergation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56366,52038,0,18,53283,52004,'Card_BankAccount_ID',TO_DATE('2008-10-21 11:47:05','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account on which card transactions will be processed','C',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Card Bank Account',0,TO_DATE('2008-10-21 11:47:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2008-10-21 11:47:06 EEST
-- Posterita intergation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56366 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2008-10-21 11:51:11 EEST
-- Posterita intergation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56367,52039,0,18,53283,52004,'CardTransferBankAccount_ID',TO_DATE('2008-10-21 11:51:10','YYYY-MM-DD HH24:MI:SS'),100,'Bank account on which to transfer Card transactions','C',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Transfer Card trx to',0,TO_DATE('2008-10-21 11:51:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2008-10-21 11:51:11 EEST
-- Posterita intergation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56367 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2008-10-21 12:01:37 EEST
-- Posterita intergation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56368,52032,0,18,53283,52004,'CashTransferBankAccount_ID',TO_DATE('2008-10-21 12:01:37','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account on which to transfer all Cash transactions','C',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Transfer Cash trx to',0,TO_DATE('2008-10-21 12:01:37','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2008-10-21 12:01:37 EEST
-- Posterita intergation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56368 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2008-10-21 12:03:17 EEST
-- Posterita intergation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56369,52035,0,18,53283,52004,'Check_BankAccount_ID',TO_DATE('2008-10-21 12:03:17','YYYY-MM-DD HH24:MI:SS'),100,'Bank Account to be used for processing Check transactions','C',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Check Bank Account',0,TO_DATE('2008-10-21 12:03:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2008-10-21 12:03:18 EEST
-- Posterita intergation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56369 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 2008-10-21 12:13:00 EEST
-- Posterita intergation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56370,52036,0,18,53283,52004,'CheckTransferBankAccount_ID',TO_DATE('2008-10-21 12:12:57','YYYY-MM-DD HH24:MI:SS'),100,'Bank account on which to transfer Check transactions','C',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Tranfer Check trx to',0,TO_DATE('2008-10-21 12:12:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- 2008-10-21 12:13:00 EEST
-- Posterita intergation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56370 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;
