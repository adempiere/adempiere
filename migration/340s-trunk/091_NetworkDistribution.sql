SET DEFINE OFF
SET SQLBLANKLINES ON
-- Feb 4, 2008 10:45:42 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Window (IsActive,Created,CreatedBy,Updated,UpdatedBy,Name,AD_Window_ID,WindowType,Help,AD_Client_ID,AD_Org_ID,Description,IsSOTrx,IsDefault,Processing,IsBetaFunctionality,EntityType) VALUES ('Y',TO_DATE('2008-02-04 22:45:41','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2008-02-04 22:45:41','YYYY-MM-DD HH24:MI:SS'),0,'Distribution Network',53018,'M','Each realtionship in the network contains a receiving warehouse and a warehouse source and a supply percentage. A relationship is one-way, warehouse to warehouse relationship.',0,0,'Distribution Network define the supply relationships','N','N','N','N','EE01')
;

-- Feb 4, 2008 10:45:42 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Name,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Name,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53018 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Feb 4, 2008 10:45:42 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Window_Access (Updated,UpdatedBy,CreatedBy,Created,AD_Window_ID,IsActive,AD_Client_ID,AD_Org_ID,AD_Role_ID) VALUES (TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),0,0,TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),53018,'Y',0,0,0)
;

-- Feb 4, 2008 10:45:42 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Window_Access (Updated,UpdatedBy,CreatedBy,Created,AD_Window_ID,IsActive,AD_Client_ID,AD_Org_ID,AD_Role_ID) VALUES (TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),0,0,TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),53018,'Y',0,0,102)
;

-- Feb 4, 2008 10:45:42 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Window_Access (Updated,UpdatedBy,CreatedBy,Created,AD_Window_ID,IsActive,AD_Client_ID,AD_Org_ID,AD_Role_ID) VALUES (TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),0,0,TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),53018,'Y',0,0,103)
;

-- Feb 4, 2008 10:45:42 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Window_Access (Updated,UpdatedBy,CreatedBy,Created,AD_Window_ID,IsActive,AD_Client_ID,AD_Org_ID,AD_Role_ID) VALUES (TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),0,0,TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),53018,'Y',0,0,50001)
;

-- Feb 4, 2008 10:45:43 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Table (AD_Org_ID,AD_Client_ID,Name,AccessLevel,IsActive,IsSecurityEnabled,IsDeleteable,TableName,Created,Description,AD_Table_ID,CreatedBy,Updated,UpdatedBy,IsHighVolume,ImportTable,IsView,IsChangeLog,ReplicationType,EntityType) VALUES (0,0,'Distribution Network','3','Y','N','Y','DD_NetworkDistribution',TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),'Distribution Network',53060,0,TO_DATE('2008-02-04 22:45:42','YYYY-MM-DD HH24:MI:SS'),0,'N','N','N','N','L','EE01')
;

-- Feb 4, 2008 10:45:43 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53060 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Feb 4, 2008 10:45:43 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Sequence (IncrementNo,StartNewYear,AD_Org_ID,StartNo,IsAutoSequence,Description,AD_Client_ID,CreatedBy,Name,CurrentNextSys,CurrentNext,IsTableID,AD_Sequence_ID,Updated,UpdatedBy,Created,IsAudited,IsActive) VALUES (1,'N',0,1000000,'Y','Table DD_NetworkDistribution',0,0,'DD_NetworkDistribution',50000,1000000,'Y',53077,TO_DATE('2008-02-04 22:45:43','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2008-02-04 22:45:43','YYYY-MM-DD HH24:MI:SS'),'N','Y')
;

-- Feb 4, 2008 10:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Date', Description='Date mm/dd/yyyy', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Feb 4, 2008 10:45:43 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Feb 4, 2008 10:45:44 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Valid from','N','N','Valid from including this date (first day)','N','The Valid From date indicates the first day of a date range',0,'Y',53060,54273,'ValidFrom',0,0,7,'N',TO_DATE('2008-02-04 22:45:43','YYYY-MM-DD HH24:MI:SS'),'Y',15,0,TO_DATE('2008-02-04 22:45:43','YYYY-MM-DD HH24:MI:SS'),617,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:44 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54273 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:44 PM CST
-- Implementing Distribution Network
CREATE TABLE DD_NetworkDistribution (ValidFrom DATE)
;

-- Feb 4, 2008 10:45:45 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Table Direct', Description='Direct Table Access', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:45','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Feb 4, 2008 10:45:45 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,DefaultValue,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,AD_Val_Rule_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Organization','Y','N','Organizational entity within client','N','@#AD_Org_ID@','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',0,'Y',53060,54274,'AD_Org_ID',0,0,22,'N',TO_DATE('2008-02-04 22:45:45','YYYY-MM-DD HH24:MI:SS'),'Y',19,104,0,TO_DATE('2008-02-04 22:45:45','YYYY-MM-DD HH24:MI:SS'),113,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54274 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Button', Description='Command Button - starts a process', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
UPDATE AD_Process SET Name='PP_Product_BOM CopyFrom', Statistic_Seconds=0, Description=NULL, Classname='org.eevolution.process.CopyFromBOM', ProcedureName=NULL, IsActive='Y', Help=NULL, IsReport='N', IsDirectPrint='N', AccessLevel='1', Statistic_Count=0, Value='PP_Product_BOM CopyFrom', IsBetaFunctionality='N', WorkflowValue=NULL, ShowHelp='Y', JasperReport=NULL, EntityType='EE01',Updated=TO_DATE('2008-02-04 22:45:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53004
;

-- Feb 4, 2008 10:45:46 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53004
;

-- Feb 4, 2008 10:45:47 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Table', Description='Table List', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Feb 4, 2008 10:45:47 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Feb 4, 2008 10:45:48 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET Name='BOM & Formaula', IsActive='Y', FieldLength=0, ValueMax=NULL, Description='Define the Parent Product to this BOM & Formula', Help='Define the Parent Product to this BOM & Formula', AD_Process_ID=53004, ColumnName='PP_Product_BOM_ID', IsCentrallyMaintained='Y', IsRange='N', DefaultValue2=NULL, VFormat=NULL, AD_Reference_ID=18, DefaultValue=NULL, IsMandatory='Y', SeqNo=10, ValueMin=NULL, AD_Element_ID=53245, EntityType='EE01',Updated=TO_DATE('2008-02-04 22:45:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=53027
;

-- Feb 4, 2008 10:45:48 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,AD_Process_ID,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Copy From','N','N','Copy From Record','N','Copy From Record',0,'Y',53060,54275,'CopyFrom',0,0,1,'N',TO_DATE('2008-02-04 22:45:48','YYYY-MM-DD HH24:MI:SS'),'Y',28,0,TO_DATE('2008-02-04 22:45:48','YYYY-MM-DD HH24:MI:SS'),2037,'Y','N','N',53004,0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:48 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54275 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:48 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD CopyFrom CHAR(1)
;

-- Feb 4, 2008 10:45:49 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Date+Time', Description='Date with time', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Feb 4, 2008 10:45:49 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Feb 4, 2008 10:45:49 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Created','Y','N','Date this record was created','N','The Created field indicates the date that this record was created.',0,'Y',53060,54276,'Created',0,0,7,'N',TO_DATE('2008-02-04 22:45:49','YYYY-MM-DD HH24:MI:SS'),'Y',16,0,TO_DATE('2008-02-04 22:45:49','YYYY-MM-DD HH24:MI:SS'),245,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:49 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54276 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:49 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Created DATE NOT NULL
;

-- Feb 4, 2008 10:45:49 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='T', Name='AD_User', Description='User selection', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Feb 4, 2008 10:45:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Feb 4, 2008 10:45:50 PM CST
-- Implementing Distribution Network
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Feb 4, 2008 10:45:50 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Created By','Y','N','User who created this records','N','The Created By field indicates the user who created this record.',110,0,'Y',53060,54277,'CreatedBy',0,0,22,'N',TO_DATE('2008-02-04 22:45:50','YYYY-MM-DD HH24:MI:SS'),'Y',18,0,TO_DATE('2008-02-04 22:45:50','YYYY-MM-DD HH24:MI:SS'),246,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:50 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54277 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:50 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD CreatedBy NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:45:51 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Updated','Y','N','Date this record was updated','N','The Updated field indicates the date that this record was updated.',0,'Y',53060,54278,'Updated',0,0,7,'N',TO_DATE('2008-02-04 22:45:50','YYYY-MM-DD HH24:MI:SS'),'Y',16,0,TO_DATE('2008-02-04 22:45:50','YYYY-MM-DD HH24:MI:SS'),607,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:51 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54278 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:51 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Updated DATE NOT NULL
;

-- Feb 4, 2008 10:45:51 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Updated By','Y','N','User who updated this records','N','The Updated By field indicates the user who updated this record.',110,0,'Y',53060,54279,'UpdatedBy',0,0,22,'N',TO_DATE('2008-02-04 22:45:51','YYYY-MM-DD HH24:MI:SS'),'Y',18,0,TO_DATE('2008-02-04 22:45:51','YYYY-MM-DD HH24:MI:SS'),608,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:51 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54279 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:51 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:45:52 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Valid to','N','N','Valid to including this date (last day)','N','The Valid To date indicates the last day of a date range',0,'Y',53060,54280,'ValidTo',0,0,7,'N',TO_DATE('2008-02-04 22:45:51','YYYY-MM-DD HH24:MI:SS'),'Y',15,0,TO_DATE('2008-02-04 22:45:51','YYYY-MM-DD HH24:MI:SS'),618,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:52 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54280 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:52 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD ValidTo DATE
;

-- Feb 4, 2008 10:45:52 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='ID', Description='10 Digit Identifier', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Feb 4, 2008 10:45:52 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Feb 4, 2008 10:45:53 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Element (AD_Element_ID,Name,ColumnName,AD_Client_ID,AD_Org_ID,UpdatedBy,CreatedBy,PrintName,Created,Updated,IsActive,EntityType) VALUES (53340,'DD_NetworkDistribution_ID','DD_NetworkDistribution_ID',0,0,0,0,'DD_NetworkDistribution_ID',TO_DATE('2008-02-04 22:45:52','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2008-02-04 22:45:52','YYYY-MM-DD HH24:MI:SS'),'Y','EE01')
;

-- Feb 4, 2008 10:45:53 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Name,Description,PrintName,Help,PO_PrintName,PO_Description,PO_Help,PO_Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Name,t.Description,t.PrintName,t.Help,t.PO_PrintName,t.PO_Description,t.PO_Help,t.PO_Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53340 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 4, 2008 10:45:53 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('DD_NetworkDistribution_ID','Y','N','N',0,'Y',53060,54281,'DD_NetworkDistribution_ID',0,0,22,'N',TO_DATE('2008-02-04 22:45:52','YYYY-MM-DD HH24:MI:SS'),'Y',13,0,TO_DATE('2008-02-04 22:45:52','YYYY-MM-DD HH24:MI:SS'),53340,'N','Y','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:53 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54281 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:53 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD DD_NetworkDistribution_ID NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:45:53 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD CONSTRAINT DD_NetworkDistribution_Key PRIMARY KEY (DD_NetworkDistribution_ID)
;

-- Feb 4, 2008 10:45:54 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,DefaultValue,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Client','Y','N','Client/Tenant for this installation.','N','@#AD_Client_ID@','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',0,'Y',53060,54282,'AD_Client_ID',0,0,22,'N',TO_DATE('2008-02-04 22:45:54','YYYY-MM-DD HH24:MI:SS'),'Y',19,0,TO_DATE('2008-02-04 22:45:54','YYYY-MM-DD HH24:MI:SS'),102,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:54 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54282 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:54 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Feb 4, 2008 10:45:54 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Text', Description='Character String up to 2000 characters', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Feb 4, 2008 10:45:54 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Feb 4, 2008 10:45:55 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Comment/Help','N','N','Comment or Hint','N','The Help field contains a hint, comment or help about the use of this item.',0,'Y',53060,54283,'Help',0,0,2000,'N',TO_DATE('2008-02-04 22:45:54','YYYY-MM-DD HH24:MI:SS'),'Y',14,0,TO_DATE('2008-02-04 22:45:54','YYYY-MM-DD HH24:MI:SS'),326,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:55 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54283 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:55 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Help NVARCHAR2(2000)
;

-- Feb 4, 2008 10:45:56 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Yes-No', Description='CheckBox', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Feb 4, 2008 10:45:56 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Feb 4, 2008 10:45:56 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Active','Y','N','The record is active in the system','N','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',0,'Y',53060,54284,'IsActive',0,0,1,'N',TO_DATE('2008-02-04 22:45:56','YYYY-MM-DD HH24:MI:SS'),'Y',20,0,TO_DATE('2008-02-04 22:45:56','YYYY-MM-DD HH24:MI:SS'),348,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:56 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54284 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:56 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Feb 4, 2008 10:45:57 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Change Notice','N','N','Bill of Materials (Engineering) Change Notice (Version)','N',0,'Y',53060,54285,'M_ChangeNotice_ID',0,0,10,'N',TO_DATE('2008-02-04 22:45:56','YYYY-MM-DD HH24:MI:SS'),'Y',19,0,TO_DATE('2008-02-04 22:45:56','YYYY-MM-DD HH24:MI:SS'),2783,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:57 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54285 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:57 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD M_ChangeNotice_ID NUMBER(10)
;

-- Feb 4, 2008 10:45:58 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Process Now','N','N','N',0,'Y',53060,54286,'Processing',0,0,1,'N',TO_DATE('2008-02-04 22:45:57','YYYY-MM-DD HH24:MI:SS'),'Y',28,0,TO_DATE('2008-02-04 22:45:57','YYYY-MM-DD HH24:MI:SS'),524,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:58 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54286 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:58 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Processing CHAR(1)
;

-- Feb 4, 2008 10:45:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='String', Description='Character String', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:45:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Feb 4, 2008 10:45:58 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Feb 4, 2008 10:45:59 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Search Key','Y','N','Search key for the record in the format required - must be unique','Y',1,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',0,'Y',53060,54287,'Value',0,0,80,'N',TO_DATE('2008-02-04 22:45:58','YYYY-MM-DD HH24:MI:SS'),'Y',10,0,TO_DATE('2008-02-04 22:45:58','YYYY-MM-DD HH24:MI:SS'),620,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:59 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54287 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:59 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Value NVARCHAR2(80) NOT NULL
;

-- Feb 4, 2008 10:45:59 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Name','Y','N','Alphanumeric identifier of the entity','Y',2,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',0,'Y',53060,54288,'Name',0,0,60,'N',TO_DATE('2008-02-04 22:45:59','YYYY-MM-DD HH24:MI:SS'),'Y',10,0,TO_DATE('2008-02-04 22:45:59','YYYY-MM-DD HH24:MI:SS'),469,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:45:59 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54288 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:45:59 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Name NVARCHAR2(60) NOT NULL
;

-- Feb 4, 2008 10:46:00 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Document No','N','N','Document sequence number of the document','Y',3,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',0,'Y',53060,54289,'DocumentNo',0,0,22,'N',TO_DATE('2008-02-04 22:45:59','YYYY-MM-DD HH24:MI:SS'),'Y',10,0,TO_DATE('2008-02-04 22:45:59','YYYY-MM-DD HH24:MI:SS'),290,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:00 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54289 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:00 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD DocumentNo NVARCHAR2(22)
;

-- Feb 4, 2008 10:46:01 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,SeqNo,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Revision','N','N','N',4,0,'Y',53060,54290,'Revision',0,0,10,'N',TO_DATE('2008-02-04 22:46:00','YYYY-MM-DD HH24:MI:SS'),'Y',10,0,TO_DATE('2008-02-04 22:46:00','YYYY-MM-DD HH24:MI:SS'),53244,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:01 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54290 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:01 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Revision NVARCHAR2(10)
;

-- Feb 4, 2008 10:46:01 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Description','N','N','Optional short description of the record','N',5,'A description is limited to 255 characters.',0,'Y',53060,54291,'Description',0,0,255,'N',TO_DATE('2008-02-04 22:46:01','YYYY-MM-DD HH24:MI:SS'),'Y',10,0,TO_DATE('2008-02-04 22:46:01','YYYY-MM-DD HH24:MI:SS'),275,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:01 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54291 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:01 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistribution ADD Description NVARCHAR2(255)
;

-- Feb 4, 2008 10:46:02 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Tab (Created,CreatedBy,Updated,AD_Client_ID,Description,Help,AD_Org_ID,IsTranslationTab,Name,SeqNo,AD_Table_ID,AD_Tab_ID,AD_Window_ID,HasTree,IsSortTab,IsSingleRow,IsActive,IsInfoTab,CommitWarning,IsReadOnly,Processing,UpdatedBy,TabLevel,IsInsertRecord,IsAdvancedTab,EntityType) VALUES (TO_DATE('2008-02-04 22:46:01','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2008-02-04 22:46:01','YYYY-MM-DD HH24:MI:SS'),0,'Distribution Network define the supply relationships','Each realtionship in the network contains a receiving warehouse and a warehouse source and a supply percentage. A relationship is one-way, warehouse to warehouse relationship.',0,'N','Distribution Network',10,53060,53071,53018,'N','N','Y','Y','N',NULL,'N','N',0,0,'Y','N','EE01')
;

-- Feb 4, 2008 10:46:02 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, Description,Help,Name,CommitWarning, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.Description,t.Help,t.Name,t.CommitWarning, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53071 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Feb 4, 2008 10:46:02 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'N','Y','Y',TO_DATE('2008-02-04 22:46:02','YYYY-MM-DD HH24:MI:SS'),0,54364,'Copy From Record',1,54275,'N',0,'Copy From Record',TO_DATE('2008-02-04 22:46:02','YYYY-MM-DD HH24:MI:SS'),'Copy From',53071,'N','N',0,'N','EE01')
;

-- Feb 4, 2008 10:46:03 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54364 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:03 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:03','YYYY-MM-DD HH24:MI:SS'),0,54365,'Client/Tenant for this installation.',22,54282,'N',0,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',TO_DATE('2008-02-04 22:46:03','YYYY-MM-DD HH24:MI:SS'),'Client',53071,'N','N',10,'N','EE01')
;

-- Feb 4, 2008 10:46:03 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54365 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:04 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:03','YYYY-MM-DD HH24:MI:SS'),0,54366,'Organizational entity within client',22,54274,'N',0,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',TO_DATE('2008-02-04 22:46:03','YYYY-MM-DD HH24:MI:SS'),'Organization',53071,'Y','N',20,'N','EE01')
;

-- Feb 4, 2008 10:46:04 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54366 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:04 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:04','YYYY-MM-DD HH24:MI:SS'),0,54367,'Search key for the record in the format required - must be unique',22,54287,'N',0,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',TO_DATE('2008-02-04 22:46:04','YYYY-MM-DD HH24:MI:SS'),'Search Key',53071,'N','N',30,'N','EE01')
;

-- Feb 4, 2008 10:46:04 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54367 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:05 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:04','YYYY-MM-DD HH24:MI:SS'),0,54368,'Alphanumeric identifier of the entity',60,54288,'N',0,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.',TO_DATE('2008-02-04 22:46:04','YYYY-MM-DD HH24:MI:SS'),'Name',53071,'N','N',40,'N','EE01')
;

-- Feb 4, 2008 10:46:05 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54368 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:05 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:05','YYYY-MM-DD HH24:MI:SS'),0,54369,'Optional short description of the record',255,54291,'N',0,'A description is limited to 255 characters.',TO_DATE('2008-02-04 22:46:05','YYYY-MM-DD HH24:MI:SS'),'Description',53071,'N','N',50,'N','EE01')
;

-- Feb 4, 2008 10:46:05 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54369 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:06 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:05','YYYY-MM-DD HH24:MI:SS'),0,54370,'Comment or Hint',2000,54283,'N',0,'The Help field contains a hint, comment or help about the use of this item.',TO_DATE('2008-02-04 22:46:05','YYYY-MM-DD HH24:MI:SS'),'Comment/Help',53071,'N','N',60,'N','EE01')
;

-- Feb 4, 2008 10:46:06 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54370 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:06 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:06','YYYY-MM-DD HH24:MI:SS'),0,54371,'Document sequence number of the document',22,54289,'N',0,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).',TO_DATE('2008-02-04 22:46:06','YYYY-MM-DD HH24:MI:SS'),'Document No',53071,'N','N',70,'N','EE01')
;

-- Feb 4, 2008 10:46:06 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54371 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:07 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:06','YYYY-MM-DD HH24:MI:SS'),0,54372,'Bill of Materials (Engineering) Change Notice (Version)',10,54285,'N',0,TO_DATE('2008-02-04 22:46:06','YYYY-MM-DD HH24:MI:SS'),'Change Notice',53071,'Y','N',80,'N','EE01')
;

-- Feb 4, 2008 10:46:07 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54372 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:07 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:07','YYYY-MM-DD HH24:MI:SS'),0,54373,10,54290,'N',0,TO_DATE('2008-02-04 22:46:07','YYYY-MM-DD HH24:MI:SS'),'Revision',53071,'N','N',90,'N','EE01')
;

-- Feb 4, 2008 10:46:07 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54373 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:08 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:07','YYYY-MM-DD HH24:MI:SS'),0,54374,'The record is active in the system',1,54284,'N',0,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',TO_DATE('2008-02-04 22:46:07','YYYY-MM-DD HH24:MI:SS'),'Active',53071,'N','N',100,'N','EE01')
;

-- Feb 4, 2008 10:46:08 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54374 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:09 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:08','YYYY-MM-DD HH24:MI:SS'),0,54375,'Valid from including this date (first day)',7,54273,'N',0,'The Valid From date indicates the first day of a date range',TO_DATE('2008-02-04 22:46:08','YYYY-MM-DD HH24:MI:SS'),'Valid from',53071,'N','N',110,'N','EE01')
;

-- Feb 4, 2008 10:46:09 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54375 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:09 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:09','YYYY-MM-DD HH24:MI:SS'),0,54376,'Valid to including this date (last day)',7,54280,'N',0,'The Valid To date indicates the last day of a date range',TO_DATE('2008-02-04 22:46:09','YYYY-MM-DD HH24:MI:SS'),'Valid to',53071,'Y','N',120,'N','EE01')
;

-- Feb 4, 2008 10:46:09 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54376 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:10 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:09','YYYY-MM-DD HH24:MI:SS'),0,54377,22,54281,'N',0,TO_DATE('2008-02-04 22:46:09','YYYY-MM-DD HH24:MI:SS'),'DD_NetworkDistribution_ID',53071,'N','N',130,'N','EE01')
;

-- Feb 4, 2008 10:46:10 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54377 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:10 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Table (AD_Org_ID,AD_Client_ID,Name,AccessLevel,IsActive,IsSecurityEnabled,IsDeleteable,TableName,Created,Description,AD_Table_ID,CreatedBy,Updated,UpdatedBy,IsHighVolume,ImportTable,IsView,IsChangeLog,ReplicationType,EntityType) VALUES (0,0,'Distribution Network Line','3','Y','N','Y','DD_NetworkDistributionLine',TO_DATE('2008-02-04 22:46:10','YYYY-MM-DD HH24:MI:SS'),'Distribution Network Line',53061,0,TO_DATE('2008-02-04 22:46:10','YYYY-MM-DD HH24:MI:SS'),0,'N','N','N','N','L','EE01')
;

-- Feb 4, 2008 10:46:10 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53061 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Feb 4, 2008 10:46:11 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Sequence (IncrementNo,StartNewYear,AD_Org_ID,StartNo,IsAutoSequence,Description,AD_Client_ID,CreatedBy,Name,CurrentNextSys,CurrentNext,IsTableID,AD_Sequence_ID,Updated,UpdatedBy,Created,IsAudited,IsActive) VALUES (1,'N',0,1000000,'Y','Table DD_NetworkDistributionLine',0,0,'DD_NetworkDistributionLine',50000,1000000,'Y',53078,TO_DATE('2008-02-04 22:46:10','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2008-02-04 22:46:10','YYYY-MM-DD HH24:MI:SS'),'N','Y')
;

-- Feb 4, 2008 10:46:11 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,DefaultValue,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Client','Y','N','Client/Tenant for this installation.','N','@#AD_Client_ID@','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',0,'Y',53061,54292,'AD_Client_ID',0,0,22,'N',TO_DATE('2008-02-04 22:46:11','YYYY-MM-DD HH24:MI:SS'),'Y',19,0,TO_DATE('2008-02-04 22:46:11','YYYY-MM-DD HH24:MI:SS'),102,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:11 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54292 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:11 PM CST
-- Implementing Distribution Network
CREATE TABLE DD_NetworkDistributionLine (AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL)
;

-- Feb 4, 2008 10:46:12 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,DefaultValue,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,AD_Val_Rule_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Organization','Y','N','Organizational entity within client','N','@#AD_Org_ID@','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',0,'Y',53061,54293,'AD_Org_ID',0,0,22,'N',TO_DATE('2008-02-04 22:46:11','YYYY-MM-DD HH24:MI:SS'),'Y',19,104,0,TO_DATE('2008-02-04 22:46:11','YYYY-MM-DD HH24:MI:SS'),113,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:12 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54293 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:12 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Feb 4, 2008 10:46:12 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Created','Y','N','Date this record was created','N','The Created field indicates the date that this record was created.',0,'Y',53061,54294,'Created',0,0,7,'N',TO_DATE('2008-02-04 22:46:12','YYYY-MM-DD HH24:MI:SS'),'Y',16,0,TO_DATE('2008-02-04 22:46:12','YYYY-MM-DD HH24:MI:SS'),245,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:12 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54294 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:12 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD Created DATE NOT NULL
;

-- Feb 4, 2008 10:46:13 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Created By','Y','N','User who created this records','N','The Created By field indicates the user who created this record.',110,0,'Y',53061,54295,'CreatedBy',0,0,22,'N',TO_DATE('2008-02-04 22:46:12','YYYY-MM-DD HH24:MI:SS'),'Y',18,0,TO_DATE('2008-02-04 22:46:12','YYYY-MM-DD HH24:MI:SS'),246,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:13 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54295 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:13 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD CreatedBy NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:46:13 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Element (AD_Element_ID,Name,ColumnName,AD_Client_ID,AD_Org_ID,UpdatedBy,CreatedBy,PrintName,Created,Updated,IsActive,EntityType) VALUES (53341,'DD_NetworkDistributionLine_ID','DD_NetworkDistributionLine_ID',0,0,0,0,'DD_NetworkDistributionLine_ID',TO_DATE('2008-02-04 22:46:13','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2008-02-04 22:46:13','YYYY-MM-DD HH24:MI:SS'),'Y','EE01')
;

-- Feb 4, 2008 10:46:13 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Name,Description,PrintName,Help,PO_PrintName,PO_Description,PO_Help,PO_Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Name,t.Description,t.PrintName,t.Help,t.PO_PrintName,t.PO_Description,t.PO_Help,t.PO_Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53341 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Feb 4, 2008 10:46:14 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('DD_NetworkDistributionLine_ID','Y','N','N',0,'Y',53061,54296,'DD_NetworkDistributionLine_ID',0,0,22,'N',TO_DATE('2008-02-04 22:46:13','YYYY-MM-DD HH24:MI:SS'),'Y',13,0,TO_DATE('2008-02-04 22:46:13','YYYY-MM-DD HH24:MI:SS'),53341,'N','Y','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:14 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54296 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:14 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD DD_NetworkDistributionLine_ID NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:46:14 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD CONSTRAINT DD_NetworkDistributionLine_Key PRIMARY KEY (DD_NetworkDistributionLine_ID)
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Active','Y','N','The record is active in the system','N','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',0,'Y',53061,54297,'IsActive',0,0,1,'N',TO_DATE('2008-02-04 22:46:14','YYYY-MM-DD HH24:MI:SS'),'Y',20,0,TO_DATE('2008-02-04 22:46:14','YYYY-MM-DD HH24:MI:SS'),348,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54297 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Shipper','N','N','Method or manner of product delivery','N','The Shipper indicates the method of delivering product',0,'Y',53061,54298,'M_Shipper_ID',0,0,22,'N',TO_DATE('2008-02-04 22:46:15','YYYY-MM-DD HH24:MI:SS'),'Y',19,0,TO_DATE('2008-02-04 22:46:15','YYYY-MM-DD HH24:MI:SS'),455,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54298 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD M_Shipper_ID NUMBER(10)
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='T', Name='M_Warehouse of Client', Description=NULL, IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:46:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=197
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=197
;

-- Feb 4, 2008 10:46:15 PM CST
-- Implementing Distribution Network
UPDATE AD_Ref_Table SET AD_Table_ID = 190, AD_Display = 1152, AD_Key = 1151, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'M_Warehouse.AD_Client_ID=@#AD_Client_ID@' WHERE AD_Reference_ID = 197
;

-- Feb 4, 2008 10:46:16 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Source Warehouse','Y','N','Optional Warehouse to replenish from','N','If defined, the warehouse selected is used to replenish the product(s)',197,0,'Y',53061,54299,'M_WarehouseSource_ID',0,0,22,'N',TO_DATE('2008-02-04 22:46:15','YYYY-MM-DD HH24:MI:SS'),'Y',18,0,TO_DATE('2008-02-04 22:46:15','YYYY-MM-DD HH24:MI:SS'),2814,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:16 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54299 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:16 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD M_WarehouseSource_ID NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:46:16 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Number', Description='Float Number', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:46:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=22
;

-- Feb 4, 2008 10:46:16 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Percent','N','N','Percentage','N','The Percent indicates the percentage used.',0,'Y',53061,54300,'Percent',0,0,10,'N',TO_DATE('2008-02-04 22:46:16','YYYY-MM-DD HH24:MI:SS'),'Y',22,0,TO_DATE('2008-02-04 22:46:16','YYYY-MM-DD HH24:MI:SS'),951,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54300 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD Percent NUMBER
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference SET Help=NULL, ValidationType='D', Name='Integer', Description='10 Digit numeric', IsActive='Y', EntityType='D',Updated=TO_DATE('2008-02-04 22:46:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('TransfertTime','N','N','N',0,'Y',53061,54301,'TransfertTime',0,0,10,'N',TO_DATE('2008-02-04 22:46:17','YYYY-MM-DD HH24:MI:SS'),'Y',11,0,TO_DATE('2008-02-04 22:46:17','YYYY-MM-DD HH24:MI:SS'),53271,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:17 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54301 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:18 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD TransfertTime NUMBER(10)
;

-- Feb 4, 2008 10:46:19 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Updated','Y','N','Date this record was updated','N','The Updated field indicates the date that this record was updated.',0,'Y',53061,54302,'Updated',0,0,7,'N',TO_DATE('2008-02-04 22:46:18','YYYY-MM-DD HH24:MI:SS'),'Y',16,0,TO_DATE('2008-02-04 22:46:18','YYYY-MM-DD HH24:MI:SS'),607,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:19 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54302 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:19 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD Updated DATE NOT NULL
;

-- Feb 4, 2008 10:46:20 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Updated By','Y','N','User who updated this records','N','The Updated By field indicates the user who updated this record.',110,0,'Y',53061,54303,'UpdatedBy',0,0,22,'N',TO_DATE('2008-02-04 22:46:19','YYYY-MM-DD HH24:MI:SS'),'Y',18,0,TO_DATE('2008-02-04 22:46:19','YYYY-MM-DD HH24:MI:SS'),608,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:20 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54303 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:20 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:46:20 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Valid to','N','N','Valid to including this date (last day)','N','The Valid To date indicates the last day of a date range',0,'Y',53061,54304,'ValidTo',0,0,7,'N',TO_DATE('2008-02-04 22:46:20','YYYY-MM-DD HH24:MI:SS'),'Y',15,0,TO_DATE('2008-02-04 22:46:20','YYYY-MM-DD HH24:MI:SS'),618,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:20 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54304 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:20 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD ValidTo DATE
;

-- Feb 4, 2008 10:46:21 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Relative Priority','N','N','Where inventory should be picked from first','N','The Relative Priority indicates the location to pick from first if an product is stored in more than one location.  (100 = highest priority, 0 = lowest).  For outgoing shipments, the location is picked with the highest priority where the entire quantity can be shipped from.  If there is no location, the location with the highest priority is used.
The Priority is ignored for products with Guarantee Date (always the oldest first) or if a speific instance is selected.
Incoming receipts are stored at the location with the hoghest priority, if not explicitly selected.',0,'Y',53061,54305,'PriorityNo',0,0,10,'N',TO_DATE('2008-02-04 22:46:20','YYYY-MM-DD HH24:MI:SS'),'Y',11,0,TO_DATE('2008-02-04 22:46:20','YYYY-MM-DD HH24:MI:SS'),1145,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:21 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54305 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:21 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD PriorityNo NUMBER(10)
;

-- Feb 4, 2008 10:46:22 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,IsIdentifier,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('DD_NetworkDistribution_ID','Y','N','N',0,'Y',53061,54306,'DD_NetworkDistribution_ID',0,0,22,'Y',TO_DATE('2008-02-04 22:46:21','YYYY-MM-DD HH24:MI:SS'),'Y',19,0,TO_DATE('2008-02-04 22:46:21','YYYY-MM-DD HH24:MI:SS'),53340,'N','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:22 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54306 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:22 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD DD_NetworkDistribution_ID NUMBER(10) NOT NULL
;

-- Feb 4, 2008 10:46:22 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,DefaultValue,Help,AD_Reference_Value_ID,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Warehouse','Y','N','Storage Warehouse and Service Point','N','@M_Warehouse_ID@','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.',197,0,'Y',53061,54307,'M_Warehouse_ID',0,0,22,'N',TO_DATE('2008-02-04 22:46:22','YYYY-MM-DD HH24:MI:SS'),'Y',18,0,TO_DATE('2008-02-04 22:46:22','YYYY-MM-DD HH24:MI:SS'),459,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:22 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54307 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:22 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD M_Warehouse_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Feb 4, 2008 10:46:23 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Valid from','N','N','Valid from including this date (first day)','N','The Valid From date indicates the first day of a date range',0,'Y',53061,54308,'ValidFrom',0,0,7,'N',TO_DATE('2008-02-04 22:46:22','YYYY-MM-DD HH24:MI:SS'),'Y',15,0,TO_DATE('2008-02-04 22:46:22','YYYY-MM-DD HH24:MI:SS'),617,'Y','N','N',0,'N','N','EE01')
;

-- Feb 4, 2008 10:46:23 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54308 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Feb 4, 2008 10:46:23 PM CST
-- Implementing Distribution Network
ALTER TABLE DD_NetworkDistributionLine ADD ValidFrom DATE
;

-- Feb 4, 2008 10:46:23 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Tab (Created,CreatedBy,Updated,AD_Client_ID,AD_Org_ID,IsTranslationTab,Name,SeqNo,AD_Table_ID,AD_Tab_ID,AD_Window_ID,HasTree,IsSortTab,IsSingleRow,IsActive,IsInfoTab,CommitWarning,IsReadOnly,Processing,UpdatedBy,TabLevel,IsInsertRecord,IsAdvancedTab,EntityType) VALUES (TO_DATE('2008-02-04 22:46:23','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2008-02-04 22:46:23','YYYY-MM-DD HH24:MI:SS'),0,0,'N','Network Line',20,53061,53072,53018,'N','N','N','Y','N',NULL,'N','N',0,0,'Y','N','EE01')
;

-- Feb 4, 2008 10:46:23 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, Description,Help,Name,CommitWarning, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.Description,t.Help,t.Name,t.CommitWarning, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53072 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Feb 4, 2008 10:46:24 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'N','Y','Y',TO_DATE('2008-02-04 22:46:23','YYYY-MM-DD HH24:MI:SS'),0,54378,'Client/Tenant for this installation.',22,54292,'N',0,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.',TO_DATE('2008-02-04 22:46:23','YYYY-MM-DD HH24:MI:SS'),'Client',53072,'N','N',0,'N','EE01')
;

-- Feb 4, 2008 10:46:24 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54378 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:25 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'N','Y','Y',TO_DATE('2008-02-04 22:46:24','YYYY-MM-DD HH24:MI:SS'),0,54379,'Organizational entity within client',22,54293,'N',0,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.',TO_DATE('2008-02-04 22:46:24','YYYY-MM-DD HH24:MI:SS'),'Organization',53072,'N','N',0,'N','EE01')
;

-- Feb 4, 2008 10:46:25 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54379 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:25 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:25','YYYY-MM-DD HH24:MI:SS'),0,54380,'Optional Warehouse to replenish from',22,54299,'N',0,'If defined, the warehouse selected is used to replenish the product(s)',TO_DATE('2008-02-04 22:46:25','YYYY-MM-DD HH24:MI:SS'),'Source Warehouse',53072,'N','N',10,'N','EE01')
;

-- Feb 4, 2008 10:46:25 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54380 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:26 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','N','Y',TO_DATE('2008-02-04 22:46:25','YYYY-MM-DD HH24:MI:SS'),0,54381,'Target Warehouse and Service Point',22,54307,'N',0,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.',TO_DATE('2008-02-04 22:46:25','YYYY-MM-DD HH24:MI:SS'),'Target Warehouse',53072,'Y','N',20,'N','EE01')
;

-- Feb 4, 2008 10:46:26 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54381 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:26 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:26','YYYY-MM-DD HH24:MI:SS'),0,54382,'The record is active in the system',1,54297,'N',0,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.',TO_DATE('2008-02-04 22:46:26','YYYY-MM-DD HH24:MI:SS'),'Active',53072,'N','N',30,'N','EE01')
;

-- Feb 4, 2008 10:46:26 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54382 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:27 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:26','YYYY-MM-DD HH24:MI:SS'),0,54383,'Valid from including this date (first day)',7,54308,'N',0,'The Valid From date indicates the first day of a date range',TO_DATE('2008-02-04 22:46:26','YYYY-MM-DD HH24:MI:SS'),'Valid from',53072,'N','N',40,'N','EE01')
;

-- Feb 4, 2008 10:46:27 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54383 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:27 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:27','YYYY-MM-DD HH24:MI:SS'),0,54384,'Valid to including this date (last day)',7,54304,'N',0,'The Valid To date indicates the last day of a date range',TO_DATE('2008-02-04 22:46:27','YYYY-MM-DD HH24:MI:SS'),'Valid to',53072,'Y','N',50,'N','EE01')
;

-- Feb 4, 2008 10:46:27 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54384 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:28 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:27','YYYY-MM-DD HH24:MI:SS'),0,54385,'Method or manner of product delivery',22,54298,'N',0,'The Shipper indicates the method of delivering product',TO_DATE('2008-02-04 22:46:27','YYYY-MM-DD HH24:MI:SS'),'Shipper',53072,'N','N',60,'N','EE01')
;

-- Feb 4, 2008 10:46:28 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54385 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:28 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:28','YYYY-MM-DD HH24:MI:SS'),0,54386,10,54301,'N',0,TO_DATE('2008-02-04 22:46:28','YYYY-MM-DD HH24:MI:SS'),'TransfertTime',53072,'Y','N',70,'N','EE01')
;

-- Feb 4, 2008 10:46:28 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54386 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:29 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:28','YYYY-MM-DD HH24:MI:SS'),0,54387,'Percentage',10,54300,'N',0,'The Percent indicates the percentage used.',TO_DATE('2008-02-04 22:46:28','YYYY-MM-DD HH24:MI:SS'),'Percent',53072,'N','N',80,'N','EE01')
;

-- Feb 4, 2008 10:46:29 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54387 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:29 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,Description,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Help,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'Y','Y','Y',TO_DATE('2008-02-04 22:46:29','YYYY-MM-DD HH24:MI:SS'),0,54388,'Where inventory should be picked from first',10,54305,'N',0,'The Relative Priority indicates the location to pick from first if an product is stored in more than one location.  (100 = highest priority, 0 = lowest).  For outgoing shipments, the location is picked with the highest priority where the entire quantity can be shipped from.  If there is no location, the location with the highest priority is used.
The Priority is ignored for products with Guarantee Date (always the oldest first) or if a speific instance is selected.
Incoming receipts are stored at the location with the hoghest priority, if not explicitly selected.',TO_DATE('2008-02-04 22:46:29','YYYY-MM-DD HH24:MI:SS'),'Relative Priority',53072,'Y','N',90,'N','EE01')
;

-- Feb 4, 2008 10:46:29 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54388 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:30 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'N','Y','Y',TO_DATE('2008-02-04 22:46:29','YYYY-MM-DD HH24:MI:SS'),0,54389,22,54306,'N',0,TO_DATE('2008-02-04 22:46:29','YYYY-MM-DD HH24:MI:SS'),'DD_NetworkDistribution_ID',53072,'N','N',0,'N','EE01')
;

-- Feb 4, 2008 10:46:30 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54389 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:30 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field (SortNo,AD_Org_ID,UpdatedBy,IsDisplayed,IsCentrallyMaintained,IsActive,Created,AD_Client_ID,AD_Field_ID,DisplayLength,AD_Column_ID,IsFieldOnly,CreatedBy,Updated,Name,AD_Tab_ID,IsSameLine,IsHeading,SeqNo,IsReadOnly,EntityType) VALUES (0,0,0,'N','Y','Y',TO_DATE('2008-02-04 22:46:30','YYYY-MM-DD HH24:MI:SS'),0,54390,22,54296,'N',0,TO_DATE('2008-02-04 22:46:30','YYYY-MM-DD HH24:MI:SS'),'DD_NetworkDistributionLine_ID',53072,'N','N',0,'N','EE01')
;

-- Feb 4, 2008 10:46:30 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54390 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Feb 4, 2008 10:46:31 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Menu (IsSummary,IsActive,Name,UpdatedBy,AD_Menu_ID,AD_Client_ID,CreatedBy,Updated,Action,AD_Window_ID,IsSOTrx,Created,AD_Org_ID,IsReadOnly,EntityType) VALUES ('N','Y','Distribution Network',0,53088,0,0,TO_DATE('2008-02-04 22:46:30','YYYY-MM-DD HH24:MI:SS'),'W',53018,'N',TO_DATE('2008-02-04 22:46:30','YYYY-MM-DD HH24:MI:SS'),0,'N','EE01')
;

-- Feb 4, 2008 10:46:31 PM CST
-- Implementing Distribution Network
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53088 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Feb 4, 2008 10:46:31 PM CST
-- Implementing Distribution Network
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53066,1, 10, 53088)
;

-- Feb 4, 2008 10:53:30 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Network Distribution', Included_Tab_ID=53072,Updated=TO_DATE('2008-02-04 22:53:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=54377
;

-- Feb 4, 2008 10:53:30 PM CST
-- Implementing Distribution Network
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=54377
;

-- Feb 4, 2008 10:53:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Element SET Name='Network Distribution', PrintName='Network Distribution',Updated=TO_DATE('2008-02-04 22:53:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53340
;

-- Feb 4, 2008 10:53:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53340
;

-- Feb 4, 2008 10:53:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description=NULL, Help=NULL WHERE AD_Element_ID=53340
;

-- Feb 4, 2008 10:53:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Network Distribution', Description=NULL, Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53340) AND IsCentrallyMaintained='Y'
;

-- Feb 4, 2008 10:53:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description=NULL, Help=NULL, AD_Element_ID=53340 WHERE UPPER(ColumnName)='DD_NETWORKDISTRIBUTION_ID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Feb 4, 2008 10:53:57 PM CST
-- Implementing Distribution Network
UPDATE AD_Process_Para SET ColumnName='DD_NetworkDistribution_ID', Name='Network Distribution', Description=NULL, Help=NULL WHERE AD_Element_ID=53340 AND IsCentrallyMaintained='Y'
;

-- Feb 4, 2008 10:53:58 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Network Distribution', Name='Network Distribution' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53340)
;

-- Feb 4, 2008 10:53:58 PM CST
-- Implementing Distribution Network
UPDATE AD_PrintFormatItem pi SET PrintName='Network Distribution', Name='Network Distribution' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53340)
;

-- Feb 4, 2008 10:54:10 PM CST
-- Implementing Distribution Network
UPDATE AD_Column SET Name='Network Distribution', Description='Network Distribution', Help=NULL, IsUpdateable='N',Updated=TO_DATE('2008-02-04 22:54:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54281
;

-- Feb 4, 2008 10:54:10 PM CST
-- Implementing Distribution Network
UPDATE AD_Field SET Name='Network Distribution', Description='Network Distribution', Help=NULL WHERE AD_Column_ID=54281 AND IsCentrallyMaintained='Y'
;

