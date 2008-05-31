-- Jan 16, 2008 8:33:22 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Bank Account','N','N','Account at the Bank','N',0,'The Bank Account identifies an account at this Bank.',0,'Y',748,54236,'C_BankAccount_ID',0,0,22,'N',TO_TIMESTAMP('2008-01-16 20:33:21','YYYY-MM-DD HH24:MI:SS'),'N',19,0,TO_TIMESTAMP('2008-01-16 20:33:21','YYYY-MM-DD HH24:MI:SS'),836,'Y','N','N',0,'N','N','D')
;

-- Jan 16, 2008 8:33:22 PM CST
--[+1871641+]+Account+Bank+into+POS+Terminal
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54236 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 16, 2008 8:33:52 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
INSERT INTO AD_Field (IsEncrypted,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,IsReadOnly,EntityType) VALUES ('N',0,0,'Y','Y','Y',TO_TIMESTAMP('2008-01-16 20:33:52','YYYY-MM-DD HH24:MI:SS'),0,54330,'Account at the Bank',22,54236,'N',0,'The Bank Account identifies an account at this Bank.',TO_TIMESTAMP('2008-01-16 20:33:52','YYYY-MM-DD HH24:MI:SS'),'Bank Account',676,'N','N','N','D')
;

-- Jan 16, 2008 8:33:52 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54330 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jan 16, 2008 8:34:08 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=54330
;

-- Jan 16, 2008 8:34:08 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=10811
;

-- Jan 16, 2008 8:34:08 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=10810
;

-- Jan 16, 2008 8:34:08 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=10798
;

-- Jan 16, 2008 8:34:08 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=10803
;

-- Jan 16, 2008 8:34:08 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=52017
;

-- Jan 16, 2008 8:34:16 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-01-16 20:34:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54330
;

-- Jan 16, 2008 9:23:14 PM CST
-- [+1871641+]+Account+Bank+into+POS+Terminal
ALTER TABLE C_POS ADD COLUMN  C_BankAccount_ID NUMERIC(10) DEFAULT  NULL 
;




