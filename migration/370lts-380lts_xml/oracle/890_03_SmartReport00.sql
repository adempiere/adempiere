ALTER TABLE pa_reportline ADD ReportLineStyle CHAR(1);

ALTER TABLE pa_reportline ADD TabLevel number(10,0);

ALTER TABLE I_ReportLine ADD ReportLineStyle CHAR(1);

ALTER TABLE I_ReportLine ADD TabLevel number(10,0);

ALTER TABLE T_Report ADD TabLevel number(10,0);

ALTER TABLE T_Report ADD ReportLineStyle CHAR(1);

ALTER TABLE T_Report ADD FixedPercentage number(10,0);

ALTER TABLE PA_ReportLine ADD FixedPercentage number(10,0);

ALTER TABLE PA_Report ADD prePeriodName VARCHAR2(20);

ALTER TABLE PA_Report ADD posPeriodName VARCHAR2(20);

-- Oct 3, 2013 5:14:48 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54103,241,TO_DATE('2013-10-03 17:14:46','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Tabbed Text',TO_DATE('2013-10-03 17:14:46','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Oct 3, 2013 5:14:48 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54103 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:15:22 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54104,241,TO_DATE('2013-10-03 17:15:21','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Customized Line',TO_DATE('2013-10-03 17:15:21','YYYY-MM-DD HH24:MI:SS'),0,'CL')
;

-- Oct 3, 2013 5:15:22 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54104 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:25:33 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56433,0,'reportline',TO_DATE('2013-10-03 17:25:32','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','reportline','reportline',TO_DATE('2013-10-03 17:25:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 5:25:33 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56433 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 3, 2013 5:25:35 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68435,56433,0,20,448,'reportline',TO_DATE('2013-10-03 17:25:32','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','reportline',TO_DATE('2013-10-03 17:25:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 5:25:35 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68435 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4794
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4795
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=10,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4786
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=20,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4787
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=30,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4789
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=40,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4790
;

-- Oct 3, 2013 5:27:18 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=50,Updated=TO_DATE('2013-10-03 17:27:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4792
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=60,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4788
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=70,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4793
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=80,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4791
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=90,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4796
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=100,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4797
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=110,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4798
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=120,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4799
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=130,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4800
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4801
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58043
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5807
;

-- Oct 3, 2013 5:27:19 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_DATE('2013-10-03 17:27:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58044
;

-- Oct 3, 2013 5:27:20 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_DATE('2013-10-03 17:27:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4785
;

-- Oct 3, 2013 5:27:40 PM IST
-- SmartJS Report functionality in ADempiere380
DELETE  FROM  AD_Column_Trl WHERE AD_Column_ID=68435
;

-- Oct 3, 2013 5:27:40 PM IST
-- SmartJS Report functionality in ADempiere380
DELETE FROM AD_Column WHERE AD_Column_ID=68435
;

-- Oct 3, 2013 5:29:06 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column SET IsUpdateable='N',Updated=TO_DATE('2013-10-03 17:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=6045
;

-- Oct 3, 2013 5:33:53 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56434,0,'reportlinestyle',TO_DATE('2013-10-03 17:33:52','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','reportlinestyle','reportlinestyle',TO_DATE('2013-10-03 17:33:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 5:33:53 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56434 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 3, 2013 5:33:54 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68436,56434,0,20,448,'reportlinestyle',TO_DATE('2013-10-03 17:33:52','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','reportlinestyle',TO_DATE('2013-10-03 17:33:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 5:33:54 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68436 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 5:34:24 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column SET Name='Report Line Style',Updated=TO_DATE('2013-10-03 17:34:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68436
;

-- Oct 3, 2013 5:34:24 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=68436
;

-- Oct 3, 2013 5:34:24 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='Report Line Style', Description=NULL, Help=NULL WHERE AD_Column_ID=68436 AND IsCentrallyMaintained='Y'
;

-- Oct 3, 2013 5:35:24 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68436,69731,0,376,TO_DATE('2013-10-03 17:35:23','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','reportlinestyle',TO_DATE('2013-10-03 17:35:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 5:35:24 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69731 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 5:35:46 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='Report Line Style',Updated=TO_DATE('2013-10-03 17:35:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69731
;

-- Oct 3, 2013 5:35:46 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69731
;

-- Oct 3, 2013 5:36:16 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET DisplayLogic='@LineType@=''CL''',Updated=TO_DATE('2013-10-03 17:36:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69731
;

-- Oct 3, 2013 5:36:48 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 17:36:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4785
;

-- Oct 3, 2013 5:36:48 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_DATE('2013-10-03 17:36:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69731
;

-- Oct 3, 2013 5:38:51 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53564,TO_DATE('2013-10-03 17:38:50','YYYY-MM-DD HH24:MI:SS'),0,'The styles for a report are defined','D','Y','N','_ReportStyle',TO_DATE('2013-10-03 17:38:50','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Oct 3, 2013 5:38:51 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53564 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- Oct 3, 2013 5:40:06 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54105,53564,TO_DATE('2013-10-03 17:40:05','YYYY-MM-DD HH24:MI:SS'),0,'Leaves a complete blank line','D','Y','Blank Line',TO_DATE('2013-10-03 17:40:05','YYYY-MM-DD HH24:MI:SS'),0,'B')
;

-- Oct 3, 2013 5:40:06 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54105 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:40:39 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54106,53564,TO_DATE('2013-10-03 17:40:38','YYYY-MM-DD HH24:MI:SS'),0,'Adds a underline to the current description field','D','Y','Underlined Descriptions',TO_DATE('2013-10-03 17:40:38','YYYY-MM-DD HH24:MI:SS'),0,'U')
;

-- Oct 3, 2013 5:40:39 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54106 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:41:25 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54107,53564,TO_DATE('2013-10-03 17:41:25','YYYY-MM-DD HH24:MI:SS'),0,'Generates a double line belows the total colums','D','Y','Double Line for Total',TO_DATE('2013-10-03 17:41:25','YYYY-MM-DD HH24:MI:SS'),0,'D')
;

-- Oct 3, 2013 5:41:25 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54107 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:41:58 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54108,53564,TO_DATE('2013-10-03 17:41:57','YYYY-MM-DD HH24:MI:SS'),0,'Generates a Line(All Row)','D','Y','Line',TO_DATE('2013-10-03 17:41:57','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Oct 3, 2013 5:41:58 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54108 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:42:26 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54109,53564,TO_DATE('2013-10-03 17:42:25','YYYY-MM-DD HH24:MI:SS'),0,'Line below the total (Column)','D','Y','Total Line',TO_DATE('2013-10-03 17:42:25','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Oct 3, 2013 5:42:26 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54109 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:43:24 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,54110,53564,TO_DATE('2013-10-03 17:43:23','YYYY-MM-DD HH24:MI:SS'),0,'New text (bold) and centered','D','Y','Centered Title',TO_DATE('2013-10-03 17:43:23','YYYY-MM-DD HH24:MI:SS'),0,'C')
;

-- Oct 3, 2013 5:43:24 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=54110 AND NOT EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Ref_List_ID=t.AD_Ref_List_ID)
;

-- Oct 3, 2013 5:44:27 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=53564,Updated=TO_DATE('2013-10-03 17:44:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68436
;

-- Oct 3, 2013 5:50:40 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68437,1725,0,11,448,'TabLevel',TO_DATE('2013-10-03 17:50:39','YYYY-MM-DD HH24:MI:SS'),0,'Hierarchical Tab Level (0 = top)','D',10,'Hierarchical level of the tab. If the level is 0, it is the top entity. Level 1 entries are dependent on level 0, etc.','Y','N','N','N','N','N','N','N','N','Y','Tab Level',TO_DATE('2013-10-03 17:50:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 5:50:40 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68437 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 5:51:02 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68437,69732,0,376,TO_DATE('2013-10-03 17:51:01','YYYY-MM-DD HH24:MI:SS'),0,'Hierarchical Tab Level (0 = top)',10,'D','Hierarchical level of the tab. If the level is 0, it is the top entity. Level 1 entries are dependent on level 0, etc.','Y','Y','Y','N','N','N','N','N','Tab Level',TO_DATE('2013-10-03 17:51:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 5:51:02 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69732 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 5:51:33 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET DefaultValue='@LineType@=T',Updated=TO_DATE('2013-10-03 17:51:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69732
;

-- Oct 3, 2013 5:59:38 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET DisplayLogic='@LineType@=T',Updated=TO_DATE('2013-10-03 17:59:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69732
;

-- Oct 3, 2013 6:04:56 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68438,56434,0,20,535,'reportlinestyle',TO_DATE('2013-10-03 18:04:55','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','reportlinestyle',TO_DATE('2013-10-03 18:04:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 6:04:56 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68438 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 6:04:58 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68439,1725,0,11,535,'TabLevel',TO_DATE('2013-10-03 18:04:56','YYYY-MM-DD HH24:MI:SS'),0,'Hierarchical Tab Level (0 = top)','D',10,'Hierarchical level of the tab. If the level is 0, it is the top entity. Level 1 entries are dependent on level 0, etc.','Y','N','N','N','N','N','N','N','N','Y','Tab Level',TO_DATE('2013-10-03 18:04:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 6:04:58 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68439 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 6:05:36 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column SET Name='ReportLineStyle',Updated=TO_DATE('2013-10-03 18:05:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68438
;

-- Oct 3, 2013 6:05:36 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=68438
;

-- Oct 3, 2013 6:05:36 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='ReportLineStyle', Description=NULL, Help=NULL WHERE AD_Column_ID=68438 AND IsCentrallyMaintained='Y'
;

-- Oct 3, 2013 6:05:52 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=53564,Updated=TO_DATE('2013-10-03 18:05:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68438
;

-- Oct 3, 2013 6:06:02 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68438,69733,0,444,TO_DATE('2013-10-03 18:06:01','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','reportlinestyle',TO_DATE('2013-10-03 18:06:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 6:06:02 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69733 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 6:06:03 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68439,69734,0,444,TO_DATE('2013-10-03 18:06:02','YYYY-MM-DD HH24:MI:SS'),0,'Hierarchical Tab Level (0 = top)',10,'D','Hierarchical level of the tab. If the level is 0, it is the top entity. Level 1 entries are dependent on level 0, etc.','Y','Y','Y','N','N','N','N','N','Tab Level',TO_DATE('2013-10-03 18:06:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 6:06:03 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69734 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 6:06:27 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='Report Line Style',Updated=TO_DATE('2013-10-03 18:06:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69733
;

-- Oct 3, 2013 6:06:27 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69733
;

-- Oct 3, 2013 6:56:14 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET DisplayLogic='@LineType@=L',Updated=TO_DATE('2013-10-03 18:56:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69731
;

-- Oct 3, 2013 6:56:44 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='L',Updated=TO_DATE('2013-10-03 18:56:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54104
;

-- Oct 3, 2013 7:19:56 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='X',Updated=TO_DATE('2013-10-03 19:19:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54109
;

-- Oct 3, 2013 7:21:15 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='Z',Updated=TO_DATE('2013-10-03 19:21:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54107
;

-- Oct 3, 2013 7:21:22 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='D',Updated=TO_DATE('2013-10-03 19:21:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54106
;

-- Oct 3, 2013 7:21:36 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='S',Updated=TO_DATE('2013-10-03 19:21:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54108
;

-- Oct 3, 2013 7:22:21 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='T',Updated=TO_DATE('2013-10-03 19:22:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54110
;

-- Oct 3, 2013 7:22:44 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='L',Updated=TO_DATE('2013-10-03 19:22:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54108
;

-- Oct 3, 2013 7:23:04 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Ref_List SET Value='S',Updated=TO_DATE('2013-10-03 19:23:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=54105
;

-- Oct 3, 2013 8:13:07 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68440,1725,0,11,544,'TabLevel',TO_DATE('2013-10-03 20:13:06','YYYY-MM-DD HH24:MI:SS'),0,'Hierarchical Tab Level (0 = top)','D',10,'Hierarchical level of the tab. If the level is 0, it is the top entity. Level 1 entries are dependent on level 0, etc.','Y','N','N','N','N','N','N','N','N','Y','Tab Level',TO_DATE('2013-10-03 20:13:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 8:13:07 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68440 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 8:13:08 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68441,56434,0,20,544,'reportlinestyle',TO_DATE('2013-10-03 20:13:07','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','reportlinestyle',TO_DATE('2013-10-03 20:13:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 8:13:08 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68441 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 8:13:25 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column SET Name='ReportLineStyle',Updated=TO_DATE('2013-10-03 20:13:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=68441
;

-- Oct 3, 2013 8:13:25 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=68441
;

-- Oct 3, 2013 8:13:25 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='ReportLineStyle', Description=NULL, Help=NULL WHERE AD_Column_ID=68441 AND IsCentrallyMaintained='Y'
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=140,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4800
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=150,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4801
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=160,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58043
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=170,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5807
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=180,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=58044
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=190,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69731
;

-- Oct 3, 2013 8:16:07 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=200,Updated=TO_DATE('2013-10-03 20:16:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69732
;

-- Oct 3, 2013 8:30:54 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56435,0,'PrePeriodName',TO_DATE('2013-10-03 20:30:51','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Pre Period Name','Pre Period Name',TO_DATE('2013-10-03 20:30:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 8:30:55 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56435 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 3, 2013 8:31:00 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68444,56435,0,10,445,'PrePeriodName',TO_DATE('2013-10-03 20:30:51','YYYY-MM-DD HH24:MI:SS'),0,'D',20,'Y','N','N','N','N','N','N','Y','N','Y','Pre Period Name',TO_DATE('2013-10-03 20:30:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 8:31:00 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68444 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 8:31:03 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56436,0,'PosPeriodName',TO_DATE('2013-10-03 20:31:00','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Pos Period Name','Pos Period Name',TO_DATE('2013-10-03 20:31:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 8:31:03 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56436 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 3, 2013 8:31:05 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68445,56436,0,10,445,'PosPeriodName',TO_DATE('2013-10-03 20:31:00','YYYY-MM-DD HH24:MI:SS'),0,'D',20,'Y','N','N','N','N','N','N','Y','N','Y','Pos Period Name',TO_DATE('2013-10-03 20:31:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 8:31:05 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68445 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 8:31:43 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68445,69736,0,372,TO_DATE('2013-10-03 20:31:39','YYYY-MM-DD HH24:MI:SS'),0,20,'D','Y','Y','Y','N','N','N','N','N','Pos Period Name',TO_DATE('2013-10-03 20:31:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 8:31:43 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69736 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 8:31:45 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68444,69737,0,372,TO_DATE('2013-10-03 20:31:43','YYYY-MM-DD HH24:MI:SS'),0,20,'D','Y','Y','Y','N','N','N','N','N','Pre Period Name',TO_DATE('2013-10-03 20:31:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 8:31:45 PM IST
-- SmartJS Report functionality in ADempiere380
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69737 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 8:32:10 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='Pre Period Name',Updated=TO_DATE('2013-10-03 20:32:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69737
;

-- Oct 3, 2013 8:32:10 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69737
;

-- Oct 3, 2013 8:32:25 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field SET Name='Pos Period Name',Updated=TO_DATE('2013-10-03 20:32:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69736
;

-- Oct 3, 2013 8:32:25 PM IST
-- SmartJS Report functionality in ADempiere380
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=69736
;

