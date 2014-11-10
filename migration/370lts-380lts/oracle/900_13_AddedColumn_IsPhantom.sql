SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF
-- Oct 3, 2013 1:38:22 PM IST
-- isphantom column is added in M_Product Table
ALTER TABLE M_PRODUCT ADD isphantom CHAR(1) DEFAULT 'N' NOT NULL ENABLE CHECK (isphantom IN ('Y','N')) ENABLE
;

-- Oct 3, 2013 1:38:22 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68430,279,0,16,208,'DiscontinuedBy',TO_DATE('2013-10-03 13:38:18','YYYY-MM-DD HH24:MI:SS'),0,'Discontinued By','D',7,'The Discontinued By indicates the individual who discontinued this product','Y','N','N','N','N','N','N','N','N','Y','Discontinued by',TO_DATE('2013-10-03 13:38:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 1:38:22 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68430 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 1:38:23 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68431,2038,0,10,208,'DownloadURL',TO_DATE('2013-10-03 13:38:22','YYYY-MM-DD HH24:MI:SS'),0,'URL of the Download files','D',60,'Semicolon separated list of URLs to be downloaded or distributed','Y','N','N','N','N','N','N','N','N','Y','Download URL',TO_DATE('2013-10-03 13:38:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 1:38:23 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68431 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 1:38:24 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,56431,0,'ISTOFORMULE',TO_DATE('2013-10-03 13:38:23','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','ISTOFORMULE','ISTOFORMULE',TO_DATE('2013-10-03 13:38:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:38:24 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=56431 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Oct 3, 2013 1:38:25 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68432,56431,0,20,208,'ISTOFORMULE',TO_DATE('2013-10-03 13:38:23','YYYY-MM-DD HH24:MI:SS'),0,'D',1,'Y','N','N','N','N','N','N','N','N','Y','ISTOFORMULE',TO_DATE('2013-10-03 13:38:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 1:38:25 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68432 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 1:38:26 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,68433,2788,0,20,208,'IsPhantom',TO_DATE('2013-10-03 13:38:25','YYYY-MM-DD HH24:MI:SS'),0,'Phantom Component','D',1,'Phantom Component are not stored and produced with the product. This is an option to avild maintaining an Engineering and Manufacturing Bill of Materials.','Y','N','N','N','N','Y','N','N','N','Y','Phantom',TO_DATE('2013-10-03 13:38:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Oct 3, 2013 1:38:26 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=68433 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Oct 3, 2013 1:39:50 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59231,69722,0,53346,TO_DATE('2013-10-03 13:39:48','YYYY-MM-DD HH24:MI:SS'),0,'Copy From Record',1,'D','Copy From Record','Y','Y','Y','N','N','N','N','N','Copy From',TO_DATE('2013-10-03 13:39:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:39:50 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69722 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:39:51 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68430,69723,0,53346,TO_DATE('2013-10-03 13:39:50','YYYY-MM-DD HH24:MI:SS'),0,'Discontinued By',7,'D','The Discontinued By indicates the individual who discontinued this product','Y','Y','Y','N','N','N','N','N','Discontinued by',TO_DATE('2013-10-03 13:39:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:39:51 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69723 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:39:52 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68431,69724,0,53346,TO_DATE('2013-10-03 13:39:51','YYYY-MM-DD HH24:MI:SS'),0,'URL of the Download files',60,'D','Semicolon separated list of URLs to be downloaded or distributed','Y','Y','Y','N','N','N','N','N','Download URL',TO_DATE('2013-10-03 13:39:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:39:52 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69724 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:39:54 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68432,69725,0,53346,TO_DATE('2013-10-03 13:39:52','YYYY-MM-DD HH24:MI:SS'),0,1,'D','Y','Y','Y','N','N','N','N','N','ISTOFORMULE',TO_DATE('2013-10-03 13:39:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:39:54 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69725 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:39:55 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,68433,69726,0,53346,TO_DATE('2013-10-03 13:39:54','YYYY-MM-DD HH24:MI:SS'),0,'Phantom Component',1,'D','Phantom Component are not stored and produced with the product. This is an option to avild maintaining an Engineering and Manufacturing Bill of Materials.','Y','Y','Y','N','N','N','N','N','Phantom',TO_DATE('2013-10-03 13:39:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:39:55 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69726 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:40:00 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,67104,69727,0,53346,TO_DATE('2013-10-03 13:39:55','YYYY-MM-DD HH24:MI:SS'),0,'Class of a Product',22,'D','Identifies the Class which this product belongs to','Y','Y','Y','N','N','N','N','N','Product Class',TO_DATE('2013-10-03 13:39:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:40:00 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69727 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:40:01 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,67106,69728,0,53346,TO_DATE('2013-10-03 13:40:00','YYYY-MM-DD HH24:MI:SS'),0,'Classification of a Product',22,'D','Identifies the classification which this product belongs to.','Y','Y','Y','N','N','N','N','N','Product Classification',TO_DATE('2013-10-03 13:40:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:40:01 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69728 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:40:02 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,67105,69729,0,53346,TO_DATE('2013-10-03 13:40:01','YYYY-MM-DD HH24:MI:SS'),0,'Group of a Product',22,'D','Identifies the Group which this product belongs to.','Y','Y','Y','N','N','N','N','N','Product Group',TO_DATE('2013-10-03 13:40:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Oct 3, 2013 1:40:02 PM IST
-- isphantom column is added in M_Product Table
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=69729 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69722
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69723
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69724
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69725
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=280,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69726
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=290,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59809
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=300,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59810
;

-- Oct 3, 2013 1:41:06 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=310,Updated=TO_DATE('2013-10-03 13:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59811
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=320,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59812
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=330,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59813
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=340,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59814
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=350,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59815
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=360,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59816
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=370,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59817
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=380,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59818
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=390,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59819
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=400,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59820
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=410,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59821
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=420,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=59822
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=430,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69727
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=440,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69728
;

-- Oct 3, 2013 1:41:07 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='Y', SeqNo=450,Updated=TO_DATE('2013-10-03 13:41:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69729
;

-- Oct 3, 2013 1:41:55 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2013-10-03 13:41:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69726
;

-- Oct 3, 2013 1:42:42 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:42:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69727
;

-- Oct 3, 2013 1:42:42 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:42:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69728
;

-- Oct 3, 2013 1:42:42 PM IST
-- isphantom column is added in M_Product Table
UPDATE AD_Field SET IsDisplayed='N', SeqNo=0,Updated=TO_DATE('2013-10-03 13:42:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=69729
;

