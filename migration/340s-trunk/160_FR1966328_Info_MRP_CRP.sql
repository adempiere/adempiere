-- May 17, 2008 8:46:15 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53468,0,'Allow_Info_MRP',TO_DATE('2008-05-17 20:46:13','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Allow Info MRP','Allow Info MRP',TO_DATE('2008-05-17 20:46:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 17, 2008 8:46:15 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53468 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 17, 2008 8:46:44 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53469,0,'Allow_Info_CRP',TO_DATE('2008-05-17 20:46:44','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','Allow Info CRP','Allow Info CRP',TO_DATE('2008-05-17 20:46:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 17, 2008 8:46:44 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53469 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- May 17, 2008 8:47:14 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,55332,53468,0,20,156,'Allow_Info_MRP',TO_DATE('2008-05-17 20:47:12','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','N','Y','Allow Info MRP',TO_DATE('2008-05-17 20:47:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 17, 2008 8:47:14 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55332 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 17, 2008 8:47:21 PM CDT
-- Info MRP in Menu
ALTER TABLE AD_Role ADD Allow_Info_MRP CHAR(1) CHECK (Allow_Info_MRP IN ('Y','N'))
;

-- May 17, 2008 8:47:50 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,55333,53469,0,20,156,'Allow_Info_CRP',TO_DATE('2008-05-17 20:47:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01',1,'Y','N','N','N','N','N','N','N','N','N','Y','Allow Info CRP',TO_DATE('2008-05-17 20:47:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- May 17, 2008 8:47:50 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=55333 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- May 17, 2008 8:48:14 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55333,55432,0,119,TO_DATE('2008-05-17 20:48:12','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Allow Info CRP',TO_DATE('2008-05-17 20:48:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 17, 2008 8:48:14 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55432 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- May 17, 2008 8:48:16 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,55332,55433,0,119,TO_DATE('2008-05-17 20:48:14','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Allow Info MRP',TO_DATE('2008-05-17 20:48:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 17, 2008 8:48:16 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=55433 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=52018
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=8740
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=5227
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=11006
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=11003
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=11002
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=8311
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=10813
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=11256
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=11257
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=8313
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=8314
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=8312
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=8310
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=12367
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=12368
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=12641
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=50168
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=290,IsDisplayed='Y' WHERE AD_Field_ID=50169
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=300,IsDisplayed='Y' WHERE AD_Field_ID=50170
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=310,IsDisplayed='Y' WHERE AD_Field_ID=50171
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=320,IsDisplayed='Y' WHERE AD_Field_ID=50172
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=330,IsDisplayed='Y' WHERE AD_Field_ID=50173
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=340,IsDisplayed='Y' WHERE AD_Field_ID=50174
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=350,IsDisplayed='Y' WHERE AD_Field_ID=50175
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=360,IsDisplayed='Y' WHERE AD_Field_ID=50176
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=370,IsDisplayed='Y' WHERE AD_Field_ID=50177
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=380,IsDisplayed='Y' WHERE AD_Field_ID=50178
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=390,IsDisplayed='Y' WHERE AD_Field_ID=55432
;

-- May 17, 2008 8:48:45 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET SeqNo=400,IsDisplayed='Y' WHERE AD_Field_ID=55433
;

-- May 17, 2008 8:49:11 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-17 20:49:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=55433
;

-- May 17, 2008 8:49:12 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2008-05-17 20:49:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=55432
;

-- May 17, 2008 8:49:19 PM CDT
-- Info MRP in Menu
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2008-05-17 20:49:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=55433
;

-- May 17, 2008 9:05:38 PM CDT
-- Info MRP in Menu
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2008-05-17 21:05:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55333
;

-- May 17, 2008 9:05:41 PM CDT
-- Info MRP in Menu
ALTER TABLE AD_Role ADD Allow_Info_CRP CHAR(1) DEFAULT 'Y' CHECK (Allow_Info_CRP IN ('Y','N'))
;

-- May 17, 2008 9:05:59 PM CDT
-- Info MRP in Menu
UPDATE AD_Column SET DefaultValue='Y',Updated=TO_DATE('2008-05-17 21:05:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55332
;

-- May 17, 2008 9:06:00 PM CDT
-- Info MRP in Menu
ALTER TABLE AD_Role MODIFY Allow_Info_MRP CHAR(1) DEFAULT 'Y'
;

-- May 17, 2008 9:58:29 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53031,0,TO_DATE('2008-05-17 21:58:27','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','MRP Info','M',TO_DATE('2008-05-17 21:58:27','YYYY-MM-DD HH24:MI:SS'),0,'InfoMRP')
;

-- May 17, 2008 9:58:29 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53031 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 17, 2008 9:59:36 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53033,0,TO_DATE('2008-05-17 21:59:35','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','CRP Info','M',TO_DATE('2008-05-17 21:59:35','YYYY-MM-DD HH24:MI:SS'),0,'InfoCRP')
;

-- May 17, 2008 9:59:36 PM CDT
-- Info MRP in Menu
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53033 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- May 17, 2008 11:59:14 PM CDT
-- Info MRP in Menu
UPDATE AD_Menu SET Description='It shows graphically of the required and available time for each manufacturing resource.nfo', Name='CRP Info',Updated=TO_DATE('2008-05-17 23:59:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=53044
;

-- May 17, 2008 11:59:14 PM CDT
-- Info MRP in Menu
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53044
;

-- May 18, 2008 12:01:08 AM CDT
-- Info MRP in Menu
UPDATE AD_Form SET Name='CRP Info',Updated=TO_DATE('2008-05-18 00:01:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Form_ID=53005
;

-- May 18, 2008 12:01:08 AM CDT
-- Info MRP in Menu
UPDATE AD_Form_Trl SET IsTranslated='N' WHERE AD_Form_ID=53005
;

