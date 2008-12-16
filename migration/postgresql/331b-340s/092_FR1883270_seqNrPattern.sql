-- Feb 11, 2008 7:27:37 PM CET
-- [FR1883270 ] Enhance Document No Formatting
INSERT INTO AD_Element (AD_Org_ID,AD_Element_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,AD_Client_ID,UpdatedBy) VALUES (0,53342,'DecimalPattern',TO_TIMESTAMP('2008-02-11 19:27:36','YYYY-MM-DD HH24:MI:SS'),100,NULL,'U','Y','Decimal Pattern','Decimal Pattern',TO_TIMESTAMP('2008-02-11 19:27:36','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- Feb 11, 2008 7:27:37 PM CET
-- [FR1883270 ] Enhance Document No Formatting
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53342 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element SET Description=NULL,Updated=TO_TIMESTAMP('2008-02-11 19:27:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description=NULL, Help=NULL WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Decimal Pattern', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53342) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description=NULL, Help=NULL, AD_Element_ID=53342 WHERE UPPER(ColumnName)='DECIMALPATTERN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description=NULL, Help=NULL WHERE AD_Element_ID=53342 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Decimal Pattern', Name='Decimal Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53342)
;

-- Feb 11, 2008 7:27:38 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Decimal Pattern', Name='Decimal Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53342)
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element SET Description='Java Decimal Pattern',Updated=TO_TIMESTAMP('2008-02-11 19:27:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description='Java Decimal Pattern', Help=NULL WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Decimal Pattern', Description='Java Decimal Pattern', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53342) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description='Java Decimal Pattern', Help=NULL, AD_Element_ID=53342 WHERE UPPER(ColumnName)='DECIMALPATTERN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description='Java Decimal Pattern', Help=NULL WHERE AD_Element_ID=53342 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Decimal Pattern', Name='Decimal Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53342)
;

-- Feb 11, 2008 7:27:49 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Decimal Pattern', Name='Decimal Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53342)
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element SET Help='Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy If the pattern for your language is not correct, please create a Adempiere support request with the correct information',Updated=TO_TIMESTAMP('2008-02-11 19:27:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2673
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2673
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET ColumnName='DatePattern', Name='Date Pattern', Description='Java Date Pattern', Help='Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy If the pattern for your language is not correct, please create a Adempiere support request with the correct information' WHERE AD_Element_ID=2673
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Date Pattern', Description='Java Date Pattern', Help='Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy If the pattern for your language is not correct, please create a Adempiere support request with the correct information' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2673) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DatePattern', Name='Date Pattern', Description='Java Date Pattern', Help='Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy If the pattern for your language is not correct, please create a Adempiere support request with the correct information', AD_Element_ID=2673 WHERE UPPER(ColumnName)='DATEPATTERN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DatePattern', Name='Date Pattern', Description='Java Date Pattern', Help='Option Date pattern in Java notation. Examples: dd.MM.yyyy - dd/MM/yyyy If the pattern for your language is not correct, please create a Adempiere support request with the correct information' WHERE AD_Element_ID=2673 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Date Pattern', Name='Date Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=2673)
;

-- Feb 11, 2008 7:27:53 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Date Pattern', Name='Date Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=2673)
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element SET EntityType='D', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023',Updated=TO_TIMESTAMP('2008-02-11 19:29:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description='Java Decimal Pattern', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023' WHERE AD_Element_ID=53342
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Decimal Pattern', Description='Java Decimal Pattern', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53342) AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description='Java Decimal Pattern', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023', AD_Element_ID=53342 WHERE UPPER(ColumnName)='DECIMALPATTERN' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Process_Para SET ColumnName='DecimalPattern', Name='Decimal Pattern', Description='Java Decimal Pattern', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023' WHERE AD_Element_ID=53342 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Decimal Pattern', Name='Decimal Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53342)
;

-- Feb 11, 2008 7:29:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_PrintFormatItem SET PrintName='Decimal Pattern', Name='Decimal Pattern' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=AD_PrintFormatItem.AD_Column_ID AND c.AD_Element_ID=53342)
;

-- Feb 11, 2008 7:30:34 PM CET
-- [FR1883270 ] Enhance Document No Formatting
INSERT INTO AD_Column (AD_Org_ID,AD_Element_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,AD_Client_ID,Version,AD_Column_ID) VALUES (0,53342,10,115,'DecimalPattern',TO_TIMESTAMP('2008-02-11 19:30:30','YYYY-MM-DD HH24:MI:SS'),100,'Java Decimal Pattern','D',40,'Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023','Y','N','N','N','N','N','N','N','N','N','Y','Decimal Pattern',0,TO_TIMESTAMP('2008-02-11 19:30:30','YYYY-MM-DD HH24:MI:SS'),100,0,0,54309)
;

-- Feb 11, 2008 7:30:34 PM CET
-- [FR1883270 ] Enhance Document No Formatting
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54309 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 11, 2008 7:30:46 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET Name='DecimalPattern',Updated=TO_TIMESTAMP('2008-02-11 19:30:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54309
;

-- Feb 11, 2008 7:30:46 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54309
;

-- Feb 11, 2008 7:30:46 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='DecimalPattern', Description='Java Decimal Pattern', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023' WHERE AD_Column_ID=54309 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:30:59 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column SET Name='Decimal Pattern',Updated=TO_TIMESTAMP('2008-02-11 19:30:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=54309
;

-- Feb 11, 2008 7:31:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=54309
;

-- Feb 11, 2008 7:31:00 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET Name='Decimal Pattern', Description='Java Decimal Pattern', Help='Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023' WHERE AD_Column_ID=54309 AND IsCentrallyMaintained='Y'
;

-- Feb 11, 2008 7:31:06 PM CET
-- [FR1883270 ] Enhance Document No Formatting
ALTER TABLE AD_Sequence ADD COLUMN DecimalPattern VARCHAR(40)
;

-- Feb 11, 2008 7:36:44 PM CET
-- [FR1883270 ] Enhance Document No Formatting
INSERT INTO AD_Field (AD_Column_ID,AD_Org_ID,AD_Field_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,AD_Client_ID,UpdatedBy) VALUES (54309,0,54391,146,TO_TIMESTAMP('2008-02-11 19:36:43','YYYY-MM-DD HH24:MI:SS'),100,'Java Decimal Pattern',0,'D','Option Decimal pattern in Java notation. Examples: 0000 will format 23 to 0023','Y','Y','Y','N','N','N','N','N','Decimal Pattern',105,0,TO_TIMESTAMP('2008-02-11 19:36:43','YYYY-MM-DD HH24:MI:SS'),0,100)
;

-- Feb 11, 2008 7:36:44 PM CET
-- [FR1883270 ] Enhance Document No Formatting
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54391 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 11, 2008 7:37:13 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET DisplayLength=14,Updated=TO_TIMESTAMP('2008-02-11 19:37:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54391
;

-- Feb 11, 2008 7:37:28 PM CET
-- [FR1883270 ] Enhance Document No Formatting
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-02-11 19:37:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=54391
;

