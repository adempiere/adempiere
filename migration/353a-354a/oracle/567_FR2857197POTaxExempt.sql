-- Sep 11, 2009 4:52:30 PM COT
-- FR2857197-Make BP Tax Exempt application configurable
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53926,0,'IsPOTaxExempt',TO_DATE('2009-09-11 16:52:29','YYYY-MM-DD HH24:MI:SS'),100,'Business partner is exempt from tax on purchases','D','If a business partner is exempt from tax on purchases, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','PO Tax exempt','PO Tax exempt',TO_DATE('2009-09-11 16:52:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 11, 2009 4:52:30 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53926 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 11, 2009 4:52:53 PM COT
UPDATE AD_Element SET Description='Business partner is exempt from tax on sales', Help='If a business partner is exempt from tax on sales, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.', Name='SO Tax exempt', PrintName='SO Tax exempt',Updated=TO_DATE('2009-09-11 16:52:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=930
;

-- Sep 11, 2009 4:52:53 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=930
;

-- Sep 11, 2009 4:52:53 PM COT
UPDATE AD_Column SET ColumnName='IsTaxExempt', Name='SO Tax exempt', Description='Business partner is exempt from tax on sales', Help='If a business partner is exempt from tax on sales, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.' WHERE AD_Element_ID=930
;

-- Sep 11, 2009 4:52:53 PM COT
UPDATE AD_Process_Para SET ColumnName='IsTaxExempt', Name='SO Tax exempt', Description='Business partner is exempt from tax on sales', Help='If a business partner is exempt from tax on sales, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.', AD_Element_ID=930 WHERE UPPER(ColumnName)='ISTAXEXEMPT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 11, 2009 4:52:53 PM COT
UPDATE AD_Process_Para SET ColumnName='IsTaxExempt', Name='SO Tax exempt', Description='Business partner is exempt from tax on sales', Help='If a business partner is exempt from tax on sales, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.' WHERE AD_Element_ID=930 AND IsCentrallyMaintained='Y'
;

-- Sep 11, 2009 4:52:53 PM COT
UPDATE AD_Field SET Name='SO Tax exempt', Description='Business partner is exempt from tax on sales', Help='If a business partner is exempt from tax on sales, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=930) AND IsCentrallyMaintained='Y'
;

-- Sep 11, 2009 4:52:56 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='SO Tax exempt', Name='SO Tax exempt' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=930)
;

-- Sep 11, 2009 4:53:40 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58381,53926,0,20,291,'IsPOTaxExempt',TO_DATE('2009-09-11 16:53:39','YYYY-MM-DD HH24:MI:SS'),100,'N','Business partner is exempt from tax on purchases','D',1,'If a business partner is exempt from tax on purchases, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','N','N','N','N','N','Y','N','N','Y','N','Y','PO Tax exempt',TO_DATE('2009-09-11 16:53:39','YYYY-MM-DD HH24:MI:SS'),100,1)
;

-- Sep 11, 2009 4:53:40 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58381 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 11, 2009 4:53:44 PM COT
ALTER TABLE C_BPartner ADD IsPOTaxExempt CHAR(1) DEFAULT 'N' CHECK (IsPOTaxExempt IN ('Y','N')) NOT NULL
;

-- Sep 11, 2009 4:55:29 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,Updated,UpdatedBy) VALUES (0,58381,57981,0,220,TO_DATE('2009-09-11 16:55:28','YYYY-MM-DD HH24:MI:SS'),100,'Business partner is exempt from tax on purchases',1,'D','If a business partner is exempt from tax on purchases, the exempt tax rate is used. For this, you need to set up a tax rate with a 0% rate and indicate that this is your tax exempt rate.  This is required for tax reporting, so that you can track tax exempt transactions.','Y','Y','Y','N','N','N','N','Y','PO Tax exempt',330,TO_DATE('2009-09-11 16:55:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 11, 2009 4:55:29 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57981 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 11, 2009 4:55:36 PM COT
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2009-09-11 16:55:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=2160
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=57981
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=54555
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=2132
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=2149
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=2144
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=2162
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=3955
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=2124
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=2164
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=2139
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=9620
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=2148
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=2128
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=2127
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=2146
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=2154
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=2153
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=2135
;

-- Sep 11, 2009 4:55:58 PM COT
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=57533
;

update c_bpartner set ispotaxexempt = istaxexempt where ispotaxexempt <> istaxexempt and istaxexempt is not null;
