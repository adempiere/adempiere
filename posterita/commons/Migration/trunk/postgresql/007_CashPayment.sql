-- Alter table -- postgres

--- Table: C_Payment -----------------------------------------------------------
ALTER TABLE C_Payment 
ALTER COLUMN C_BankAccount_ID DROP NOT NULL;


ALTER TABLE C_Payment 
ADD C_CashBook_ID NUMERIC(10);

ALTER TABLE C_Payment 
ADD CONSTRAINT C_Payment__C_CashBo_C_CashBook 
FOREIGN KEY(C_CashBook_ID)  REFERENCES C_CashBook(C_CashBook_ID);

--------------------------------------------------------------------------------
ALTER TABLE C_CashLine 
ADD C_Payment_ID NUMERIC(10);

--- Migration scripts ----------------------------------------------------------
-- Aug 26, 2008 11:49:31 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) 
VALUES (0,52117,1463,0,19,335,'C_CashBook_ID',now(),100,'Cash Book for recording petty cash transactions','U',10,'The Cash Book identifies a unique cash book.  The cash book is used to record cash transactions.','Y','N','N','N','N','N','N','N','N','Y','Cash Book',now(),100,0)
;

-- Aug 26, 2008 11:49:31 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=52117 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Aug 26, 2008 11:50:27 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Column SET IsMandatory='N',Updated=now(),UpdatedBy=100 WHERE AD_Column_ID=3880
;

-- Aug 26, 2008 11:50:27 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET Name='Bank Account', Description='Account at the Bank', Help='The Bank Account identifies an account at this Bank.' WHERE AD_Column_ID=3880 AND IsCentrallyMaintained='Y'
;

-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) 
VALUES (0,52117,52052,0,330,now(),100,'Cash Book for recording petty cash transactions',10,'U','The Cash Book identifies a unique cash book.  The cash book is used to record cash transactions.','Y','Y','Y','N','N','N','N','N','Cash Book',now(),100)
;

-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=52052 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) 
VALUES (0,13705,52053,0,330,now(),100,10,'D','Y','Y','Y','N','N','N','N','N','Referenced Payment',now(),100)
;

-- Aug 26, 2008 11:51:40 AM MUT
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=52053 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Aug 26, 2008 11:52:11 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=52053
;

-- Aug 26, 2008 11:52:11 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=660,IsDisplayed='Y' WHERE AD_Field_ID=52052
;

-- Aug 26, 2008 11:52:56 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET DisplayLogic='@TenderType@ !''X''',Updated=now(),UpdatedBy=100 WHERE AD_Field_ID=4030
;

-- Aug 26, 2008 11:54:17 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Field SET DisplayLength=14, DisplayLogic='@TenderType@=''X''', IsSameLine='Y',Updated=now(),UpdatedBy=100 WHERE AD_Field_ID=52052
;

-- Aug 26, 2008 11:54:42 AM MUT
-- Default comment for updating dictionary
UPDATE AD_Tab SET WhereClause=NULL,Updated=now(),UpdatedBy=100 WHERE AD_Tab_ID=330
;

