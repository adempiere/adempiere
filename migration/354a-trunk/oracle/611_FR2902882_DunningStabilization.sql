-- Nov 23, 2009 9:06:59 PM COT
-- FR2902882-Dunning Stabilization
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58591,3092,0,17,394,331,'InvoiceCollectionType',TO_DATE('2009-11-23 21:06:55','YYYY-MM-DD HH24:MI:SS'),100,'Invoice Collection Status','D',1,'Status of the invoice collection process','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Collection Status',0,TO_DATE('2009-11-23 21:06:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 23, 2009 9:06:59 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58591 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 23, 2009 9:07:09 PM COT
ALTER TABLE C_DunningLevel ADD InvoiceCollectionType CHAR(1) DEFAULT NULL 
;

-- Nov 23, 2009 9:08:03 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58591,58079,0,268,TO_DATE('2009-11-23 21:08:00','YYYY-MM-DD HH24:MI:SS'),100,'Invoice Collection Status',0,'D','Status of the invoice collection process','Y','Y','Y','N','N','N','N','N','Collection Status',210,0,TO_DATE('2009-11-23 21:08:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 23, 2009 9:08:03 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58079 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Nov 23, 2009 9:09:31 PM COT
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54076,0,'IsStatement',TO_DATE('2009-11-23 21:09:31','YYYY-MM-DD HH24:MI:SS'),100,'Dunning Level is a definition of a statement','D','Y','Is Statement','Is Statement',TO_DATE('2009-11-23 21:09:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 23, 2009 9:09:32 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54076 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Nov 23, 2009 9:11:56 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58593,54076,0,20,331,'IsStatement',TO_DATE('2009-11-23 21:11:53','YYYY-MM-DD HH24:MI:SS'),100,'N','Dunning Level is a definition of a statement','D',1,'Y','Y','N','N','N','N','N','Y','N','N','N','N','Y','Is Statement',0,TO_DATE('2009-11-23 21:11:53','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Nov 23, 2009 9:11:56 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58593 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Nov 23, 2009 9:11:58 PM COT
ALTER TABLE C_DunningLevel ADD IsStatement CHAR(1) DEFAULT 'N' CHECK (IsStatement IN ('Y','N')) NOT NULL
;

-- Nov 23, 2009 9:12:23 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58593,58080,0,268,TO_DATE('2009-11-23 21:12:22','YYYY-MM-DD HH24:MI:SS'),100,'Dunning Level is a definition of a statement',1,'D','Y','Y','Y','N','N','N','N','N','Is Statement',220,0,TO_DATE('2009-11-23 21:12:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Nov 23, 2009 9:12:23 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58080 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Nov 23, 2009 9:12:29 PM COT
UPDATE AD_Field SET DisplayLength=1,Updated=TO_DATE('2009-11-23 21:12:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58079
;

-- Nov 23, 2009 9:13:19 PM COT
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-11-23 21:13:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58080
;

-- Nov 23, 2009 9:14:17 PM COT
UPDATE AD_Element SET Name='Dunning Grace Date', PrintName='Dunning Grace Date',Updated=TO_DATE('2009-11-23 21:14:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53223
;

-- Nov 23, 2009 9:14:17 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53223
;

-- Nov 23, 2009 9:14:17 PM COT
UPDATE AD_Column SET ColumnName='DunningGrace', Name='Dunning Grace Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53223
;

-- Nov 23, 2009 9:14:17 PM COT
UPDATE AD_Process_Para SET ColumnName='DunningGrace', Name='Dunning Grace Date', Description=NULL, Help=NULL, AD_Element_ID=53223 WHERE UPPER(ColumnName)='DUNNINGGRACE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 23, 2009 9:14:17 PM COT
UPDATE AD_Process_Para SET ColumnName='DunningGrace', Name='Dunning Grace Date', Description=NULL, Help=NULL WHERE AD_Element_ID=53223 AND IsCentrallyMaintained='Y'
;

-- Nov 23, 2009 9:14:17 PM COT
UPDATE AD_Field SET Name='Dunning Grace Date', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53223) AND IsCentrallyMaintained='Y'
;

-- Nov 23, 2009 9:14:18 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Dunning Grace Date', Name='Dunning Grace Date' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53223)
;

-- Nov 23, 2009 9:14:40 PM COT
UPDATE AD_Field SET DisplayLogic='@Processed@=Y',Updated=TO_DATE('2009-11-23 21:14:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53257
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=2241
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=2251
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=2239
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=2240
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=2250
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=2245
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=2249
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=3221
;

-- Nov 23, 2009 9:15:24 PM COT
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=13702
;

-- Nov 23, 2009 9:15:34 PM COT
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2009-11-23 21:15:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=13702
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=2914
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=2918
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=2921
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=2920
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=5738
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=13703
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=13705
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=13704
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=58079
;

-- Nov 23, 2009 9:15:56 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=58080
;

-- Nov 23, 2009 9:19:59 PM COT
UPDATE AD_Element SET Help='The Days After Due Date indicates the number of days after the payment due date to initiate dunning. If the number is negative, it includes the not due invoices.',Updated=TO_DATE('2009-11-23 21:19:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1092
;

-- Nov 23, 2009 9:19:59 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1092
;

-- Nov 23, 2009 9:19:59 PM COT
UPDATE AD_Column SET ColumnName='DaysAfterDue', Name='Days after due date', Description='Days after due date to dun (if negative days until due)', Help='The Days After Due Date indicates the number of days after the payment due date to initiate dunning. If the number is negative, it includes the not due invoices.' WHERE AD_Element_ID=1092
;

-- Nov 23, 2009 9:19:59 PM COT
UPDATE AD_Process_Para SET ColumnName='DaysAfterDue', Name='Days after due date', Description='Days after due date to dun (if negative days until due)', Help='The Days After Due Date indicates the number of days after the payment due date to initiate dunning. If the number is negative, it includes the not due invoices.', AD_Element_ID=1092 WHERE UPPER(ColumnName)='DAYSAFTERDUE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Nov 23, 2009 9:19:59 PM COT
UPDATE AD_Process_Para SET ColumnName='DaysAfterDue', Name='Days after due date', Description='Days after due date to dun (if negative days until due)', Help='The Days After Due Date indicates the number of days after the payment due date to initiate dunning. If the number is negative, it includes the not due invoices.' WHERE AD_Element_ID=1092 AND IsCentrallyMaintained='Y'
;

-- Nov 23, 2009 9:19:59 PM COT
UPDATE AD_Field SET Name='Days after due date', Description='Days after due date to dun (if negative days until due)', Help='The Days After Due Date indicates the number of days after the payment due date to initiate dunning. If the number is negative, it includes the not due invoices.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=1092) AND IsCentrallyMaintained='Y'
;

-- Nov 23, 2009
-- FR2902882-Dunning Stabilization
UPDATE C_DunningLevel SET IsStatement='Y' WHERE daysafterdue=-9999
;

-- Nov 23, 2009 10:20:48 PM COT
UPDATE C_DunningLevel SET IsShowAllDue='Y', IsShowNotDue='Y',Updated=TO_DATE('2009-11-23 22:20:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DunningLevel_ID=100
;

-- Nov 23, 2009 10:20:52 PM COT
UPDATE C_DunningLevel SET IsShowAllDue='Y',Updated=TO_DATE('2009-11-23 22:20:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_DunningLevel_ID=101
;

