-- May 12, 2012 6:39:10 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53176,TO_DATE('2012-05-12 18:39:09','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Classifications','D','The Product Classification allows you to define different classification of products.','Y','N','N','N','Product Classification','N',TO_DATE('2012-05-12 18:39:09','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- May 12, 2012 6:39:10 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53176 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- May 12, 2012 6:39:11 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53381,53176,'3',TO_DATE('2012-05-12 18:39:10','YYYY-MM-DD HH24:MI:SS'),0,'Classification of a Product','D','N','Y','N','Y','N','N','N','Product Classification','L','M_Product_Classification',TO_DATE('2012-05-12 18:39:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:39:11 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53381 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- May 12, 2012 6:39:11 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53530,TO_DATE('2012-05-12 18:39:11','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table M_Product_Classification',1,'Y','N','Y','Y','M_Product_Classification','N',1000000,TO_DATE('2012-05-12 18:39:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:39:12 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55490,0,'M_Product_Classification_ID',TO_DATE('2012-05-12 18:39:11','YYYY-MM-DD HH24:MI:SS'),0,'Classification of a Product','D','Identifies the classification which this product belongs to.','Y','Product Classification','Product Classification',TO_DATE('2012-05-12 18:39:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:39:12 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55490 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 6:39:12 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_DATE('2012-05-12 18:39:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- May 12, 2012 6:39:12 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- May 12, 2012 6:39:13 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63127,55490,0,13,53381,'M_Product_Classification_ID',TO_DATE('2012-05-12 18:39:12','YYYY-MM-DD HH24:MI:SS'),0,'Classification of a Product','D',22,'Identifies the classification which this product belongs to.','Y','N','N','N','Y','Y','N','N','Y','N','N','Product Classification',TO_DATE('2012-05-12 18:39:12','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:13 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63127 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:13 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
CREATE TABLE M_Product_Classification (M_Product_Classification_ID NUMBER(10) NOT NULL, CONSTRAINT M_Product_Classification_Key PRIMARY KEY (M_Product_Classification_ID))
;

-- May 12, 2012 6:39:14 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_DATE('2012-05-12 18:39:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- May 12, 2012 6:39:14 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- May 12, 2012 6:39:14 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2012-05-12 18:39:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- May 12, 2012 6:39:14 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- May 12, 2012 6:39:14 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2012-05-12 18:39:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=129
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63128,102,0,19,53381,129,'AD_Client_ID',TO_DATE('2012-05-12 18:39:14','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_DATE('2012-05-12 18:39:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63128 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63129,113,0,19,53381,104,'AD_Org_ID',TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63129 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- May 12, 2012 6:39:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63130,245,0,16,53381,'Created',TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_DATE('2012-05-12 18:39:15','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63130 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD Created DATE NOT NULL
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_DATE('2012-05-12 18:39:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2012-05-12 18:39:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2012-05-12 18:39:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- May 12, 2012 6:39:16 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63131,246,0,18,110,53381,'CreatedBy',TO_DATE('2012-05-12 18:39:16','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_DATE('2012-05-12 18:39:16','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63131 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD CreatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_DATE('2012-05-12 18:39:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=275
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2012-05-12 18:39:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63132,275,0,10,53381,'Description',TO_DATE('2012-05-12 18:39:17','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','Y','Y','N','Y','Description',TO_DATE('2012-05-12 18:39:17','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63132 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_DATE('2012-05-12 18:39:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- May 12, 2012 6:39:17 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2012-05-12 18:39:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63133,348,0,20,53381,'IsActive',TO_DATE('2012-05-12 18:39:18','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2012-05-12 18:39:18','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63133 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='IsDefault', Description='Default value', EntityType='D', Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', Name='Default', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Default',Updated=TO_DATE('2012-05-12 18:39:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1103
;

-- May 12, 2012 6:39:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1103
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63134,1103,0,20,53381,'IsDefault',TO_DATE('2012-05-12 18:39:18','YYYY-MM-DD HH24:MI:SS'),0,'Default value','D',1,'The Default Checkbox indicates if this record will be used as a default value.','Y','N','N','N','N','Y','N','N','Y','N','Y','Default',TO_DATE('2012-05-12 18:39:18','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63134 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD IsDefault CHAR(1) CHECK (IsDefault IN ('Y','N')) NOT NULL
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55491,0,'M_Classification_Parent_ID',TO_DATE('2012-05-12 18:39:19','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Product Classification Parent','Product Classification Parent',TO_DATE('2012-05-12 18:39:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55491 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63135,55491,0,18,53381,'M_Classification_Parent_ID',TO_DATE('2012-05-12 18:39:19','YYYY-MM-DD HH24:MI:SS'),0,'D',22,'Y','N','N','Y','N','N','N','N','Y','N','Y','Product Classification Parent',1,TO_DATE('2012-05-12 18:39:19','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63135 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD M_Classification_Parent_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 6:39:19 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_DATE('2012-05-12 18:39:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=469
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63136,469,0,10,53381,'Name',TO_DATE('2012-05-12 18:39:20','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','Y','Y','N','Y','Name',2,TO_DATE('2012-05-12 18:39:20','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63136 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD Name NVARCHAR2(60) NOT NULL
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_DATE('2012-05-12 18:39:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63137,607,0,16,53381,'Updated',TO_DATE('2012-05-12 18:39:20','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_DATE('2012-05-12 18:39:20','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63137 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD Updated DATE NOT NULL
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_DATE('2012-05-12 18:39:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63138,608,0,18,110,53381,'UpdatedBy',TO_DATE('2012-05-12 18:39:21','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_DATE('2012-05-12 18:39:21','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63138 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD UpdatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_DATE('2012-05-12 18:39:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=620
;

-- May 12, 2012 6:39:21 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- May 12, 2012 6:39:22 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63139,620,0,10,53381,'Value',TO_DATE('2012-05-12 18:39:21','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D',12,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','Y','Y','N','Y','Search Key',TO_DATE('2012-05-12 18:39:21','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:39:22 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63139 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:39:22 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Classification ADD Value NVARCHAR2(12) NOT NULL
;
-- May 12, 2012 6:41:33 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Table SET AD_Window_ID=53176, AccessLevel='3', Description='Classification of a Product', EntityType='D', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Product Classification', ReplicationType='L', TableName='M_Product_Classification',Updated=TO_DATE('2012-05-12 18:41:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53381
;

-- May 12, 2012 6:41:33 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='M_Product_Classification_ID', Description='Classification of a Product', EntityType='D', Help='Identifies the classification which this product belongs to.', IsActive='Y', Name='Product Classification', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Classification',Updated=TO_DATE('2012-05-12 18:41:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=55490
;

-- May 12, 2012 6:41:33 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55490
;

-- May 12, 2012 6:41:33 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_DATE('2012-05-12 18:41:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- May 12, 2012 6:41:33 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=55490, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Product_Classification_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Classification of a Product', EntityType='D', FieldLength=22, Help='Identifies the classification which this product belongs to.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Product Classification', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63127
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_DATE('2012-05-12 18:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2012-05-12 18:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2012-05-12 18:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=129
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63128
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_DATE('2012-05-12 18:41:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- May 12, 2012 6:41:34 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63129
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63130
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63131
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=275
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2012-05-12 18:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- May 12, 2012 6:41:35 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='Y', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63132
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63133
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='IsDefault', Description='Default value', EntityType='D', Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', Name='Default', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Default',Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1103
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1103
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=1103, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsDefault', ColumnSQL=NULL, DefaultValue=NULL, Description='Default value', EntityType='D', FieldLength=1, Help='The Default Checkbox indicates if this record will be used as a default value.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Default', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63134
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='M_Classification_Parent_ID', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Product Classification Parent', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Classification Parent',Updated=TO_DATE('2012-05-12 18:41:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=55491
;

-- May 12, 2012 6:41:36 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55491
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=55491, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Classification_Parent_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product Classification Parent', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63135
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Field SET Name='Product Classification Parent', Description=NULL, Help=NULL WHERE AD_Column_ID=63135 AND IsCentrallyMaintained='Y'
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=469
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='Y', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63136
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63137
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- May 12, 2012 6:41:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63138
;

-- May 12, 2012 6:41:38 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_DATE('2012-05-12 18:41:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=620
;

-- May 12, 2012 6:41:38 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- May 12, 2012 6:41:38 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Element_ID=620, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53381, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Value', ColumnSQL=NULL, DefaultValue=NULL, Description='Search key for the record in the format required - must be unique', EntityType='D', FieldLength=40, Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='Y', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Search Key', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-05-12 18:41:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63139
;

-- May 12, 2012 6:41:39 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53511,53381,53176,NULL,TO_DATE('2012-05-12 18:41:38','YYYY-MM-DD HH24:MI:SS'),0,'Define Product Classification','D','N','The Product Classification defines unique groupings of products.','Y','N','N','Y','N','Y','N','N','Product Classification','N',10,0,TO_DATE('2012-05-12 18:41:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:39 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53511 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- May 12, 2012 6:41:39 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63127,64311,0,53511,TO_DATE('2012-05-12 18:41:39','YYYY-MM-DD HH24:MI:SS'),0,'Classification of a Product',22,'D','Identifies the classification which this product belongs to.','Y','Y','N','N','N','N','N','Product Classification',0,0,TO_DATE('2012-05-12 18:41:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:39 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64311 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:40 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63128,64312,0,53511,TO_DATE('2012-05-12 18:41:39','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2012-05-12 18:41:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:40 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64312 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:40 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63129,64313,0,53511,TO_DATE('2012-05-12 18:41:40','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2012-05-12 18:41:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:40 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64313 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:41 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63139,64314,0,53511,TO_DATE('2012-05-12 18:41:40','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',12,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2012-05-12 18:41:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:41 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64314 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:41 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63136,64315,0,53511,TO_DATE('2012-05-12 18:41:41','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2012-05-12 18:41:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:41 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64315 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:41 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63132,64316,0,53511,TO_DATE('2012-05-12 18:41:41','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2012-05-12 18:41:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:41 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64316 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:42 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63133,64317,0,53511,TO_DATE('2012-05-12 18:41:41','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_DATE('2012-05-12 18:41:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:42 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64317 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:42 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63134,64318,0,53511,TO_DATE('2012-05-12 18:41:42','YYYY-MM-DD HH24:MI:SS'),0,'Default value',1,'D','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','Y','Default',70,0,TO_DATE('2012-05-12 18:41:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:42 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64318 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:43 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63135,64319,0,53511,TO_DATE('2012-05-12 18:41:42','YYYY-MM-DD HH24:MI:SS'),0,22,'D','Y','Y','Y','N','N','N','N','Product Classification Parent',80,0,TO_DATE('2012-05-12 18:41:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:43 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64319 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:43 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53177,TO_DATE('2012-05-12 18:41:43','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Class','D','The Product Class allows you to define different groups of products.','Y','N','N','N','Product Class','N',TO_DATE('2012-05-12 18:41:43','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- May 12, 2012 6:41:43 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53177 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- May 12, 2012 6:41:44 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53382,53177,'3',TO_DATE('2012-05-12 18:41:43','YYYY-MM-DD HH24:MI:SS'),0,'Class of a Product','D','N','Y','N','Y','N','N','N','Product Class','L','M_Product_Class',TO_DATE('2012-05-12 18:41:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:44 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53382 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- May 12, 2012 6:41:44 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53531,TO_DATE('2012-05-12 18:41:44','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table M_Product_Class',1,'Y','N','Y','Y','M_Product_Class','N',1000000,TO_DATE('2012-05-12 18:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55492,0,'M_Product_Class_ID',TO_DATE('2012-05-12 18:41:44','YYYY-MM-DD HH24:MI:SS'),0,'Class of a Product','D','Identifies the Class which this product belongs to','Y','Product Class','Product Class',TO_DATE('2012-05-12 18:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55492 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63140,55492,0,13,53382,'M_Product_Class_ID',TO_DATE('2012-05-12 18:41:45','YYYY-MM-DD HH24:MI:SS'),0,'Class of a Product','D',22,'Identifies the Class which this product belongs to','Y','N','N','N','Y','Y','N','N','Y','N','N','Product Class',TO_DATE('2012-05-12 18:41:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63140 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
CREATE TABLE M_Product_Class (M_Product_Class_ID NUMBER(10) NOT NULL, CONSTRAINT M_Product_Class_Key PRIMARY KEY (M_Product_Class_ID))
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63141,102,0,19,53382,129,'AD_Client_ID',TO_DATE('2012-05-12 18:41:45','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_DATE('2012-05-12 18:41:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63141 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:46 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63142,113,0,19,53382,104,'AD_Org_ID',TO_DATE('2012-05-12 18:41:45','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_DATE('2012-05-12 18:41:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:46 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63142 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:46 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:46 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63143,245,0,16,53382,'Created',TO_DATE('2012-05-12 18:41:46','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_DATE('2012-05-12 18:41:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:46 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63143 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:46 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD Created DATE NOT NULL
;

-- May 12, 2012 6:41:47 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63144,246,0,18,110,53382,'CreatedBy',TO_DATE('2012-05-12 18:41:46','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_DATE('2012-05-12 18:41:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:47 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63144 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:47 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD CreatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:47 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63145,275,0,10,53382,'Description',TO_DATE('2012-05-12 18:41:47','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','Y','Y','N','Y','Description',TO_DATE('2012-05-12 18:41:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:47 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63145 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:47 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63146,348,0,20,53382,'IsActive',TO_DATE('2012-05-12 18:41:47','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2012-05-12 18:41:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63146 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63147,1103,0,20,53382,'IsDefault',TO_DATE('2012-05-12 18:41:48','YYYY-MM-DD HH24:MI:SS'),0,'Default value','D',1,'The Default Checkbox indicates if this record will be used as a default value.','Y','N','N','N','N','Y','N','N','Y','N','Y','Default',TO_DATE('2012-05-12 18:41:48','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63147 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD IsDefault CHAR(1) CHECK (IsDefault IN ('Y','N')) NOT NULL
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55493,0,'M_Product_Class_Parent_ID',TO_DATE('2012-05-12 18:41:48','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Product Class Parent','Product Class Parent',TO_DATE('2012-05-12 18:41:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:48 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55493 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 6:41:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63148,55493,0,18,53382,'M_Product_Class_Parent_ID',TO_DATE('2012-05-12 18:41:48','YYYY-MM-DD HH24:MI:SS'),0,'D',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Product Class Parent',TO_DATE('2012-05-12 18:41:48','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63148 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD M_Product_Class_Parent_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 6:41:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63149,469,0,10,53382,'Name',TO_DATE('2012-05-12 18:41:49','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','Y','Y','N','Y','Name',1,TO_DATE('2012-05-12 18:41:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63149 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD Name NVARCHAR2(60) NOT NULL
;

-- May 12, 2012 6:41:50 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63150,607,0,16,53382,'Updated',TO_DATE('2012-05-12 18:41:49','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_DATE('2012-05-12 18:41:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:50 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63150 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:50 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD Updated DATE NOT NULL
;

-- May 12, 2012 6:41:50 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63151,608,0,18,110,53382,'UpdatedBy',TO_DATE('2012-05-12 18:41:50','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_DATE('2012-05-12 18:41:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:50 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63151 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:50 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD UpdatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63152,620,0,10,53382,'Value',TO_DATE('2012-05-12 18:41:50','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D',255,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','Y','Y','N','Y','Search Key',TO_DATE('2012-05-12 18:41:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63152 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Class ADD Value NVARCHAR2(255) NOT NULL
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53512,53382,53177,NULL,TO_DATE('2012-05-12 18:41:51','YYYY-MM-DD HH24:MI:SS'),0,'Define Product Class','D','N','The Product Class defines unique groupings of products.','Y','N','N','Y','N','Y','N','N','Product Class','N',10,0,TO_DATE('2012-05-12 18:41:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53512 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63140,64320,0,53512,TO_DATE('2012-05-12 18:41:51','YYYY-MM-DD HH24:MI:SS'),0,'Class of a Product',22,'D','Identifies the Class which this product belongs to','Y','Y','N','N','N','N','N','Product Class',0,0,TO_DATE('2012-05-12 18:41:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:51 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64320 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:52 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63141,64321,0,53512,TO_DATE('2012-05-12 18:41:51','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2012-05-12 18:41:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:52 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64321 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:52 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63142,64322,0,53512,TO_DATE('2012-05-12 18:41:52','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2012-05-12 18:41:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:52 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64322 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63152,64323,0,53512,TO_DATE('2012-05-12 18:41:52','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2012-05-12 18:41:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64323 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63149,64324,0,53512,TO_DATE('2012-05-12 18:41:53','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2012-05-12 18:41:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64324 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63145,64325,0,53512,TO_DATE('2012-05-12 18:41:53','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2012-05-12 18:41:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64325 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63146,64326,0,53512,TO_DATE('2012-05-12 18:41:54','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_DATE('2012-05-12 18:41:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64326 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63147,64327,0,53512,TO_DATE('2012-05-12 18:41:54','YYYY-MM-DD HH24:MI:SS'),0,'Default value',1,'D','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','Y','Default',70,0,TO_DATE('2012-05-12 18:41:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64327 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:55 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63148,64328,0,53512,TO_DATE('2012-05-12 18:41:54','YYYY-MM-DD HH24:MI:SS'),0,22,'D','Y','Y','Y','N','N','N','N','Product Class Parent',80,0,TO_DATE('2012-05-12 18:41:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:55 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64328 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:41:55 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53178,TO_DATE('2012-05-12 18:41:55','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Group','D','The Product Group allows you to define different groups of products.','Y','N','N','Y','Product Group','N',TO_DATE('2012-05-12 18:41:55','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- May 12, 2012 6:41:55 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53178 AND NOT EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Window_ID=t.AD_Window_ID)
;

-- May 12, 2012 6:41:56 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,Description,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53383,53178,'3',TO_DATE('2012-05-12 18:41:55','YYYY-MM-DD HH24:MI:SS'),0,'Group of a Product','D','N','Y','N','Y','N','N','N','Product Group','L','M_Product_Group',TO_DATE('2012-05-12 18:41:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:56 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53383 AND NOT EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Table_ID=t.AD_Table_ID)
;

-- May 12, 2012 6:41:56 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53532,TO_DATE('2012-05-12 18:41:56','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table M_Product_Group',1,'Y','N','Y','Y','M_Product_Group','N',1000000,TO_DATE('2012-05-12 18:41:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:56 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55494,0,'M_Product_Group_ID',TO_DATE('2012-05-12 18:41:56','YYYY-MM-DD HH24:MI:SS'),0,'Group of a Product','D','Identifies the Group which this product belongs to.','Y','Product Group','Product Group',TO_DATE('2012-05-12 18:41:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:41:56 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55494 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 6:41:57 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63153,55494,0,13,53383,'M_Product_Group_ID',TO_DATE('2012-05-12 18:41:56','YYYY-MM-DD HH24:MI:SS'),0,'Group of a Product','D',22,'Identifies the Group which this product belongs to.','Y','N','N','N','Y','Y','N','N','Y','N','N','Product Group',TO_DATE('2012-05-12 18:41:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:57 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63153 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:57 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
CREATE TABLE M_Product_Group (M_Product_Group_ID NUMBER(10) NOT NULL, CONSTRAINT M_Product_Group_Key PRIMARY KEY (M_Product_Group_ID))
;

-- May 12, 2012 6:41:57 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63154,102,0,19,53383,129,'AD_Client_ID',TO_DATE('2012-05-12 18:41:57','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','D',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',TO_DATE('2012-05-12 18:41:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:57 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63154 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:57 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD AD_Client_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:58 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63155,113,0,19,53383,104,'AD_Org_ID',TO_DATE('2012-05-12 18:41:57','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','D',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',TO_DATE('2012-05-12 18:41:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:58 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63155 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:58 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD AD_Org_ID NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:58 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63156,245,0,16,53383,'Created',TO_DATE('2012-05-12 18:41:58','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','D',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',TO_DATE('2012-05-12 18:41:58','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:58 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63156 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:58 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD Created DATE NOT NULL
;

-- May 12, 2012 6:41:59 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63157,246,0,18,110,53383,'CreatedBy',TO_DATE('2012-05-12 18:41:58','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','D',22,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',TO_DATE('2012-05-12 18:41:58','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:59 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63157 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:59 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD CreatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 6:41:59 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63158,275,0,10,53383,'Description',TO_DATE('2012-05-12 18:41:59','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','D',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','Y','Y','N','Y','Description',TO_DATE('2012-05-12 18:41:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:41:59 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63158 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:41:59 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD Description NVARCHAR2(255) DEFAULT NULL 
;

-- May 12, 2012 6:42:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63159,348,0,20,53383,'IsActive',TO_DATE('2012-05-12 18:41:59','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','D',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',TO_DATE('2012-05-12 18:41:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63159 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- May 12, 2012 6:42:01 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63160,1103,0,20,53383,'IsDefault',TO_DATE('2012-05-12 18:42:00','YYYY-MM-DD HH24:MI:SS'),0,'Default value','D',1,'The Default Checkbox indicates if this record will be used as a default value.','Y','N','N','N','N','Y','N','N','Y','N','Y','Default',TO_DATE('2012-05-12 18:42:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:01 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63160 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:01 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD IsDefault CHAR(1) CHECK (IsDefault IN ('Y','N')) NOT NULL
;

-- May 12, 2012 6:42:01 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55495,0,'M_Product_Group_Parent_ID',TO_DATE('2012-05-12 18:42:01','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','Product Group Parent','Product Group Parent',TO_DATE('2012-05-12 18:42:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:01 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55495 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63161,55495,0,18,53383,'M_Product_Group_Parent_ID',TO_DATE('2012-05-12 18:42:01','YYYY-MM-DD HH24:MI:SS'),0,'D',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Product Group Parent',TO_DATE('2012-05-12 18:42:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63161 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD M_Product_Group_Parent_ID NUMBER(10) DEFAULT NULL 
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63162,469,0,10,53383,'Name',TO_DATE('2012-05-12 18:42:02','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','D',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','Y','Y','N','Y','Name',1,TO_DATE('2012-05-12 18:42:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63162 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD Name NVARCHAR2(60) NOT NULL
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63163,607,0,16,53383,'Updated',TO_DATE('2012-05-12 18:42:02','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','D',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',TO_DATE('2012-05-12 18:42:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63163 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:02 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD Updated DATE NOT NULL
;

-- May 12, 2012 6:42:03 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63164,608,0,18,110,53383,'UpdatedBy',TO_DATE('2012-05-12 18:42:02','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','D',22,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',TO_DATE('2012-05-12 18:42:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:03 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63164 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:03 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD UpdatedBy NUMBER(10) NOT NULL
;

-- May 12, 2012 6:42:03 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63165,620,0,10,53383,'Value',TO_DATE('2012-05-12 18:42:03','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','D',255,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','Y','Y','N','Y','Search Key',TO_DATE('2012-05-12 18:42:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- May 12, 2012 6:42:03 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63165 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- May 12, 2012 6:42:03 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product_Group ADD Value NVARCHAR2(255) NOT NULL
;

-- May 12, 2012 6:42:04 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53513,53383,53178,NULL,TO_DATE('2012-05-12 18:42:03','YYYY-MM-DD HH24:MI:SS'),0,'Define Product Group','D','N','The Product Group defines unique groupings of products.','Y','N','N','Y','N','Y','N','N','Product Group','N',10,0,TO_DATE('2012-05-12 18:42:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:04 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53513 AND NOT EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Tab_ID=t.AD_Tab_ID)
;

-- May 12, 2012 6:42:04 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63153,64329,0,53513,TO_DATE('2012-05-12 18:42:04','YYYY-MM-DD HH24:MI:SS'),0,'Group of a Product',22,'D','Identifies the Group which this product belongs to.','Y','Y','N','N','N','N','N','Product Group',0,0,TO_DATE('2012-05-12 18:42:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:04 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64329 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:05 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63154,64330,0,53513,TO_DATE('2012-05-12 18:42:04','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'D','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2012-05-12 18:42:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:05 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64330 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:05 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63155,64331,0,53513,TO_DATE('2012-05-12 18:42:05','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'D','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2012-05-12 18:42:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:05 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64331 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:05 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63165,64332,0,53513,TO_DATE('2012-05-12 18:42:05','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',40,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2012-05-12 18:42:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:05 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64332 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:06 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63162,64333,0,53513,TO_DATE('2012-05-12 18:42:05','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2012-05-12 18:42:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:06 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64333 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:06 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63158,64334,0,53513,TO_DATE('2012-05-12 18:42:06','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'D','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2012-05-12 18:42:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:06 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64334 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:07 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63159,64335,0,53513,TO_DATE('2012-05-12 18:42:06','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_DATE('2012-05-12 18:42:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:07 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64335 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:07 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63160,64336,0,53513,TO_DATE('2012-05-12 18:42:07','YYYY-MM-DD HH24:MI:SS'),0,'Default value',1,'D','The Default Checkbox indicates if this record will be used as a default value.','Y','Y','Y','N','N','N','Y','Default',80,0,TO_DATE('2012-05-12 18:42:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:07 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64336 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:07 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,63161,64337,0,53513,TO_DATE('2012-05-12 18:42:07','YYYY-MM-DD HH24:MI:SS'),0,22,'D','Y','Y','Y','N','N','N','N','Product Group Parent',90,0,TO_DATE('2012-05-12 18:42:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:07 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64337 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- May 12, 2012 6:42:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53416,0,53176,'W',TO_DATE('2012-05-12 18:42:07','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Classifications','D','Y','N','N','N','Product Classification',TO_DATE('2012-05-12 18:42:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53416 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- May 12, 2012 6:42:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 167,5, 10, 53416)
;

-- May 12, 2012 6:42:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53417,0,53177,'W',TO_DATE('2012-05-12 18:42:08','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Class','D','Y','N','N','N','Product Class',TO_DATE('2012-05-12 18:42:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53417 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- May 12, 2012 6:42:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 167,7, 10, 53417)
;

-- May 12, 2012 6:42:09 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53418,0,53178,'W',TO_DATE('2012-05-12 18:42:08','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Product Group','D','Y','N','N','N','Product Group',TO_DATE('2012-05-12 18:42:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:42:09 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53418 AND NOT EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Menu_ID=t.AD_Menu_ID)
;

-- May 12, 2012 6:42:09 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 167,8, 10, 53418)
;

-- May 12, 2012 6:44:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53431,TO_DATE('2012-05-12 18:43:59','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Product Classification Parent',TO_DATE('2012-05-12 18:43:59','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- May 12, 2012 6:44:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53431 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- May 12, 2012 6:47:37 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy,WhereClause) VALUES (0,63136,63127,0,53431,53381,TO_DATE('2012-05-12 18:47:37','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2012-05-12 18:47:37','YYYY-MM-DD HH24:MI:SS'),0,NULL)
;

-- May 12, 2012 6:47:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Reference_Value_ID=53431,Updated=TO_DATE('2012-05-12 18:47:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63135
;

-- May 12, 2012 6:48:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53432,TO_DATE('2012-05-12 18:48:44','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Product Class Parent',TO_DATE('2012-05-12 18:48:44','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- May 12, 2012 6:48:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53432 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- May 12, 2012 6:49:55 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,63149,63140,0,53432,53382,TO_DATE('2012-05-12 18:49:55','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2012-05-12 18:49:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:50:10 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Reference_Value_ID=53432,Updated=TO_DATE('2012-05-12 18:50:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63148
;

-- May 12, 2012 6:51:10 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53433,TO_DATE('2012-05-12 18:51:10','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Product Group Parent',TO_DATE('2012-05-12 18:51:10','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- May 12, 2012 6:51:10 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53433 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- May 12, 2012 6:52:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,63162,63153,0,53433,53383,TO_DATE('2012-05-12 18:52:08','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2012-05-12 18:52:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 6:52:18 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Reference_Value_ID=53433,Updated=TO_DATE('2012-05-12 18:52:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=63161
;

-- May 12, 2012 6:57:06 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET FieldLength=60,Updated=TO_DATE('2012-05-12 18:57:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3016
;

-- May 12, 2012 6:57:10 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
ALTER TABLE M_Product MODIFY Classification NVARCHAR2(60) DEFAULT NULL 
;

-- May 12, 2012 6:58:42 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53434,TO_DATE('2012-05-12 18:58:41','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Product Classification Value',TO_DATE('2012-05-12 18:58:41','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- May 12, 2012 6:58:42 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53434 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- May 12, 2012 7:04:15 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,63136,63139,0,53434,53381,TO_DATE('2012-05-12 19:04:15','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2012-05-12 19:04:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 7:04:30 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53434,Updated=TO_DATE('2012-05-12 19:04:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3016
;

-- May 12, 2012 7:05:38 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53435,TO_DATE('2012-05-12 19:05:37','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Product_Class Value',TO_DATE('2012-05-12 19:05:37','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- May 12, 2012 7:05:38 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53435 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- May 12, 2012 7:06:13 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,63149,63152,0,53435,53382,TO_DATE('2012-05-12 19:06:13','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2012-05-12 19:06:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 7:06:26 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Name='M_Product_Classification Value',Updated=TO_DATE('2012-05-12 19:06:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53434
;

-- May 12, 2012 7:06:26 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53434
;

-- May 12, 2012 7:06:39 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53435,Updated=TO_DATE('2012-05-12 19:06:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52061
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET Name='Product Class', PrintName='Product Class',Updated=TO_DATE('2012-05-12 19:07:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52018
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52018
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET ColumnName='Group1', Name='Product Class', Description=NULL, Help=NULL WHERE AD_Element_ID=52018
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Process_Para SET ColumnName='Group1', Name='Product Class', Description=NULL, Help=NULL, AD_Element_ID=52018 WHERE UPPER(ColumnName)='GROUP1' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Process_Para SET ColumnName='Group1', Name='Product Class', Description=NULL, Help=NULL WHERE AD_Element_ID=52018 AND IsCentrallyMaintained='Y'
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Field SET Name='Product Class', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=52018) AND IsCentrallyMaintained='Y'
;

-- May 12, 2012 7:07:00 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_PrintFormatItem pi SET PrintName='Product Class', Name='Product Class' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=52018)
;

-- May 12, 2012 7:07:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,IsOrderByValue,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53436,TO_DATE('2012-05-12 19:07:52','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N','M_Product_Group Value',TO_DATE('2012-05-12 19:07:52','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- May 12, 2012 7:07:53 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53436 AND NOT EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Reference_ID=t.AD_Reference_ID)
;

-- May 12, 2012 7:08:20 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
INSERT INTO AD_Ref_Table (AD_Client_ID,AD_Display,AD_Key,AD_Org_ID,AD_Reference_ID,AD_Table_ID,Created,CreatedBy,EntityType,IsActive,IsValueDisplayed,Updated,UpdatedBy) VALUES (0,63162,63165,0,53436,53383,TO_DATE('2012-05-12 19:08:20','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','N',TO_DATE('2012-05-12 19:08:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- May 12, 2012 7:08:30 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET AD_Reference_ID=18, AD_Reference_Value_ID=53436,Updated=TO_DATE('2012-05-12 19:08:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=52062
;

-- May 12, 2012 7:08:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Name='M_Product_Class Parent',Updated=TO_DATE('2012-05-12 19:08:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53432
;

-- May 12, 2012 7:08:49 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53432
;

-- May 12, 2012 7:09:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Name='M_Product_Group Parent',Updated=TO_DATE('2012-05-12 19:09:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53433
;

-- May 12, 2012 7:09:08 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53433
;

-- May 12, 2012 7:09:26 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference SET Name='M_Product_Classification Parent',Updated=TO_DATE('2012-05-12 19:09:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=53431
;

-- May 12, 2012 7:09:26 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53431
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element SET Name='Product Group', PrintName='Product Group',Updated=TO_DATE('2012-05-12 19:09:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52019
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52019
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Column SET ColumnName='Group2', Name='Product Group', Description=NULL, Help=NULL WHERE AD_Element_ID=52019
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Process_Para SET ColumnName='Group2', Name='Product Group', Description=NULL, Help=NULL, AD_Element_ID=52019 WHERE UPPER(ColumnName)='GROUP2' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Process_Para SET ColumnName='Group2', Name='Product Group', Description=NULL, Help=NULL WHERE AD_Element_ID=52019 AND IsCentrallyMaintained='Y'
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_Field SET Name='Product Group', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=52019) AND IsCentrallyMaintained='Y'
;

-- May 12, 2012 7:09:54 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_PrintFormatItem pi SET PrintName='Product Group', Name='Product Group' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=52019)
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=0, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=268
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=1, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=125
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=2, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=422
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=3, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=107
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=4, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=130
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=5, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53416
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=6, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53417
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=7, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=53418
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=8, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=188
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=9, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=227
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=10, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=381
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=12, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=126
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=13, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=421
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=14, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=534
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=15, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=267
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=16, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=490
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=17, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=132
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=18, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=310
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=19, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=128
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=20, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=585
;

-- May 12, 2012 7:11:45 PM CDT
-- Data Entity for Classification, Class , Group http://adempiere.atlassian.net/browse/ADEMPIERE-101
UPDATE AD_TreeNodeMM SET Parent_ID=167, SeqNo=21, Updated=SysDate WHERE AD_Tree_ID=10 AND Node_ID=187
;
