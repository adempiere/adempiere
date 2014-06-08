-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Table SET AccessLevel='3', Description='This process generates a report containing the result of the forecast calculation, parameters can be used to filter the result of the report. ', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='Simulation of Forecast ', ReplicationType='L', TableName='RV_PP_ForecastRun',Updated=TO_DATE('2012-07-14 00:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53399
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53399
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_DATE('2012-07-14 00:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2012-07-14 00:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2012-07-14 00:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=129
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53399, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63476
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_DATE('2012-07-14 00:18:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- Jul 14, 2012 12:18:58 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53399, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='EE01', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63477
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_DATE('2012-07-14 00:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=348
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2012-07-14 00:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63478
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_DATE('2012-07-14 00:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=245
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2012-07-14 00:18:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- Jul 14, 2012 12:18:59 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63479
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=607
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63480
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=246
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=110
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63481
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_DATE('2012-07-14 00:19:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=608
;

-- Jul 14, 2012 12:19:00 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Jul 14, 2012 12:19:01 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53399, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2012-07-14 00:19:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63482
;

-- Jul 14, 2012 12:19:01 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRun_ID', Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Run', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Run',Updated=TO_DATE('2012-07-14 00:19:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55515
;

-- Jul 14, 2012 12:19:01 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55515
;

-- Jul 14, 2012 12:19:02 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63651,55515,0,19,53399,'PP_ForecastRun_ID',TO_DATE('2012-07-14 00:19:01','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Forecast Run',TO_DATE('2012-07-14 00:19:01','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:02 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63651 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:02 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_DATE('2012-07-14 00:19:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=290
;

-- Jul 14, 2012 12:19:02 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Jul 14, 2012 12:19:02 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- Jul 14, 2012 12:19:02 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63652,290,0,10,53399,'DocumentNo',TO_DATE('2012-07-14 00:19:02','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01',30,'The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','Y','Y','N','N','Document No',TO_DATE('2012-07-14 00:19:02','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63652 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_DATE('2012-07-14 00:19:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=275
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63653,275,0,10,53399,'Description',TO_DATE('2012-07-14 00:19:03','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE01',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','Y','Y','N','N','Description',TO_DATE('2012-07-14 00:19:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63653 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRule_ID', Description='Forecast Rules define the business logic according to a previously implemented algorithm.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Rule',Updated=TO_DATE('2012-07-14 00:19:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55519
;

-- Jul 14, 2012 12:19:03 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55519
;

-- Jul 14, 2012 12:19:04 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63654,55519,0,19,53399,'PP_ForecastRule_ID',TO_DATE('2012-07-14 00:19:03','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Rules define the business logic according to a previously implemented algorithm.','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Forecast Rule',TO_DATE('2012-07-14 00:19:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:04 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63654 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:04 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_Calendar_ID', Description='Operational Period, allows to define the periods for the Operational Calendar', EntityType='EE01', Help=NULL, IsActive='Y', Name='Operational Calendar', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Operational Calendar',Updated=TO_DATE('2012-07-14 00:19:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55511
;

-- Jul 14, 2012 12:19:04 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55511
;

-- Jul 14, 2012 12:19:05 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63655,55511,0,19,53399,'PP_Calendar_ID',TO_DATE('2012-07-14 00:19:04','YYYY-MM-DD HH24:MI:SS'),100,'Operational Period, allows to define the periods for the Operational Calendar','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Operational Calendar',TO_DATE('2012-07-14 00:19:04','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:05 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63655 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:05 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_PeriodDefinition_ID', Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', Help=NULL, IsActive='Y', Name='Period Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period Definition',Updated=TO_DATE('2012-07-14 00:19:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55512
;

-- Jul 14, 2012 12:19:05 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55512
;

-- Jul 14, 2012 12:19:06 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63656,55512,0,19,53399,'PP_PeriodDefinition_ID',TO_DATE('2012-07-14 00:19:05','YYYY-MM-DD HH24:MI:SS'),100,'Period Definition, allows to define time cycles for the Operational Calendar','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Period Definition',TO_DATE('2012-07-14 00:19:05','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:06 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63656 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:06 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Ref_DefinitionPeriod_ID', Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', Help=NULL, IsActive='Y', Name='Period Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period Definition',Updated=TO_DATE('2012-07-14 00:19:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55518
;

-- Jul 14, 2012 12:19:06 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55518
;

-- Jul 14, 2012 12:19:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- Jul 14, 2012 12:19:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Jul 14, 2012 12:19:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_DefinitionPeriod', ValidationType='T',Updated=TO_DATE('2012-07-14 00:19:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53438
;

-- Jul 14, 2012 12:19:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53438
;

-- Jul 14, 2012 12:19:07 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 53388, AD_Display = 63299, AD_Key = 63298, isValueDisplayed = 'N', OrderByClause = '', EntityType ='EE01', WhereClause = '' WHERE AD_Reference_ID = 53438
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63657,55518,0,18,53438,53399,'Ref_DefinitionPeriod_ID',TO_DATE('2012-07-14 00:19:07','YYYY-MM-DD HH24:MI:SS'),100,'Period Definition, allows to define time cycles for the Operational Calendar','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Period Definition',TO_DATE('2012-07-14 00:19:07','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63657 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PeriodHistory', Description='Number Period of History', EntityType='EE01', Help=NULL, IsActive='Y', Name='Periods of History', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Periods of History',Updated=TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55517
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55517
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=11
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63658,55517,0,11,53399,'PeriodHistory',TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),100,'Number Period of History','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Periods of History',TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63658 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=454
;

-- Jul 14, 2012 12:19:08 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Jul 14, 2012 12:19:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63659,454,0,19,53399,'M_Product_ID',TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Product',TO_DATE('2012-07-14 00:19:08','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63659 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_DATE('2012-07-14 00:19:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=620
;

-- Jul 14, 2012 12:19:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- Jul 14, 2012 12:19:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=14
;

-- Jul 14, 2012 12:19:09 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63660,620,0,14,53399,'Value',TO_DATE('2012-07-14 00:19:09','YYYY-MM-DD HH24:MI:SS'),100,'Search key for the record in the format required - must be unique','EE01',510,'A search key allows you a fast method of finding a particular record.If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','Y','N','N','N','Y','Y','N','N','Search Key',1,TO_DATE('2012-07-14 00:19:09','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63660 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=469
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63661,469,0,10,53399,'Name',TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),100,'Alphanumeric identifier of the entity','EE01',255,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','Y','Y','N','N','Name',2,TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63661 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_Category_ID', Description='Category of a Product', EntityType='D', Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', IsActive='Y', Name='Product Category', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Category',Updated=TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=453
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=453
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Product Category ', ValidationType='T',Updated=TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=163
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=163
;

-- Jul 14, 2012 12:19:10 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 209, AD_Display = 1776, AD_Key = 2020, isValueDisplayed = 'Y', OrderByClause = 'M_Product_Category.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 163
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63662,453,0,19,163,53399,'M_Product_Category_ID',TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),100,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','N','Product Category',TO_DATE('2012-07-14 00:19:10','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63662 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_Classification_ID', Description='Classification of a Product', EntityType='D', Help='Identifies the classification which this product belongs to.', IsActive='Y', Name='Product Classification', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Classification',Updated=TO_DATE('2012-07-14 00:19:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55490
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55490
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63663,55490,0,19,53399,'M_Product_Classification_ID',TO_DATE('2012-07-14 00:19:11','YYYY-MM-DD HH24:MI:SS'),100,'Classification of a Product','EE01',10,'Identifies the classification which this product belongs to.','Y','N','N','N','N','N','N','N','Y','N','N','Product Classification',TO_DATE('2012-07-14 00:19:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63663 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_Class_ID', Description='Class of a Product', EntityType='D', Help='Identifies the Class which this product belongs to', IsActive='Y', Name='Product Class', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Class',Updated=TO_DATE('2012-07-14 00:19:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55492
;

-- Jul 14, 2012 12:19:11 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55492
;

-- Jul 14, 2012 12:19:12 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63664,55492,0,19,53399,'M_Product_Class_ID',TO_DATE('2012-07-14 00:19:11','YYYY-MM-DD HH24:MI:SS'),100,'Class of a Product','EE01',10,'Identifies the Class which this product belongs to','Y','N','N','N','N','N','N','N','Y','N','N','Product Class',TO_DATE('2012-07-14 00:19:11','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:12 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63664 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:12 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_Group_ID', Description='Group of a Product', EntityType='D', Help='Identifies the Group which this product belongs to.', IsActive='Y', Name='Product Group', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Group',Updated=TO_DATE('2012-07-14 00:19:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55494
;

-- Jul 14, 2012 12:19:12 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55494
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63665,55494,0,19,53399,'M_Product_Group_ID',TO_DATE('2012-07-14 00:19:12','YYYY-MM-DD HH24:MI:SS'),100,'Group of a Product','EE01',10,'Identifies the Group which this product belongs to.','Y','N','N','N','N','N','N','N','Y','N','N','Product Group',TO_DATE('2012-07-14 00:19:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63665 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_WarehouseSource_ID', Description='Optional Warehouse to replenish from', EntityType='D', Help='If defined, the warehouse selected is used to replenish the product(s)', IsActive='Y', Name='Source Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Source Warehouse',Updated=TO_DATE('2012-07-14 00:19:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2814
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2814
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Warehouse of Client', ValidationType='T',Updated=TO_DATE('2012-07-14 00:19:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=197
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=197
;

-- Jul 14, 2012 12:19:13 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 190, AD_Display = 1152, AD_Key = 1151, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'M_Warehouse.AD_Client_ID=@#AD_Client_ID@' WHERE AD_Reference_ID = 197
;

-- Jul 14, 2012 12:19:14 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63666,2814,0,18,197,53399,'M_WarehouseSource_ID',TO_DATE('2012-07-14 00:19:13','YYYY-MM-DD HH24:MI:SS'),100,'Optional Warehouse to replenish from','EE01',10,'If defined, the warehouse selected is used to replenish the product(s)','Y','N','N','N','N','N','N','N','Y','N','N','Source Warehouse',TO_DATE('2012-07-14 00:19:13','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:14 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63666 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:14 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorAlpha', Description='Identifies an Factor Alpha', EntityType='EE01', Help='The Factor Alpha is smoothing constant used in this exponential smoothing model.', IsActive='Y', Name='Factor Alpha', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Alpha',Updated=TO_DATE('2012-07-14 00:19:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55521
;

-- Jul 14, 2012 12:19:14 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55521
;

-- Jul 14, 2012 12:19:14 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=22
;

-- Jul 14, 2012 12:19:14 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63667,55521,0,22,53399,'FactorAlpha',TO_DATE('2012-07-14 00:19:14','YYYY-MM-DD HH24:MI:SS'),100,'Identifies an Factor Alpha','EE01',131089,'The Factor Alpha is smoothing constant used in this exponential smoothing model.','Y','N','N','N','N','N','N','N','Y','N','N','Factor Alpha',TO_DATE('2012-07-14 00:19:14','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63667 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorGamma', Description='Identifies a Factor Gamma', EntityType='EE01', Help='Factor Gamma is the second smoothing constant (gamma) used in this exponential smoothing model This is used to smooth the trend.', IsActive='Y', Name='Factor Gamma', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Gamma',Updated=TO_DATE('2012-07-14 00:19:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55522
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55522
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63668,55522,0,22,53399,'FactorGamma',TO_DATE('2012-07-14 00:19:15','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Gamma','EE01',131089,'Factor Gamma is the second smoothing constant (gamma) used in this exponential smoothing model This is used to smooth the trend.','Y','N','N','N','N','N','N','N','Y','N','N','Factor Gamma',TO_DATE('2012-07-14 00:19:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63668 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorMultiplier', Description='Identifies a Factor Multiplier', EntityType='EE01', Help='Factor Multiplier defines the increase or decrease in percentage for the forecast quantity, A negative percentage indicates that the amount is reduced.', IsActive='Y', Name='Factor Multiplier', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Multiplier',Updated=TO_DATE('2012-07-14 00:19:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55523
;

-- Jul 14, 2012 12:19:15 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55523
;

-- Jul 14, 2012 12:19:16 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63669,55523,0,22,53399,'FactorMultiplier',TO_DATE('2012-07-14 00:19:15','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Multiplier','EE01',131089,'Factor Multiplier defines the increase or decrease in percentage for the forecast quantity, A negative percentage indicates that the amount is reduced.','Y','N','N','N','N','N','N','N','Y','N','N','Factor Multiplier',TO_DATE('2012-07-14 00:19:15','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:16 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63669 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:16 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorScale', Description='Identifies a Factor Scale', EntityType='EE01', Help='Factor Scale defines the scale in percentage applied for the forecast quantity, this value cannot be negative.', IsActive='Y', Name='Factor Scale', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Scale',Updated=TO_DATE('2012-07-14 00:19:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55524
;

-- Jul 14, 2012 12:19:16 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55524
;

-- Jul 14, 2012 12:19:17 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63670,55524,0,22,53399,'FactorScale',TO_DATE('2012-07-14 00:19:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Scale','EE01',131089,'Factor Scale defines the scale in percentage applied for the forecast quantity, this value cannot be negative.','Y','N','N','N','N','N','N','N','Y','N','N','Factor Scale',TO_DATE('2012-07-14 00:19:16','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:17 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63670 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:17 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55543,0,'PeriodName',TO_DATE('2012-07-14 00:19:17','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Period Name','Period Name',TO_DATE('2012-07-14 00:19:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 14, 2012 12:19:17 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55543 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63671,55543,0,10,53399,'PeriodName',TO_DATE('2012-07-14 00:19:17','YYYY-MM-DD HH24:MI:SS'),100,'EE01',60,'Y','N','N','N','N','N','N','Y','Y','N','N','Period Name',TO_DATE('2012-07-14 00:19:17','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63671 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=459
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63672,459,0,19,53399,'M_Warehouse_ID',TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01',10,'The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','N','N','N','N','N','Y','N','N','Warehouse',TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63672 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyInvoiced', Description='Invoiced Quantity', EntityType='D', Help='The Invoiced Quantity indicates the quantity of a product that have been invoiced.', IsActive='Y', Name='Quantity Invoiced', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Invoiced',Updated=TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=529
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=529
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=29
;

-- Jul 14, 2012 12:19:18 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63673,529,0,29,53399,'QtyInvoiced',TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),100,'Invoiced Quantity','EE01',131089,'The Invoiced Quantity indicates the quantity of a product that have been invoiced.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity Invoiced',TO_DATE('2012-07-14 00:19:18','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63673 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_Period_ID', Description='Forecast Definition Periods.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Operational Period', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Operational Period',Updated=TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55513
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55513
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63674,55513,0,19,53399,'PP_Period_ID',TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Definition Periods.','EE01',10,'Y','N','N','N','N','N','N','N','Y','N','N','Operational Period',TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63674 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='StartDate', Description='First effective day (inclusive)', EntityType='D', Help='The Start Date indicates the first or starting date', IsActive='Y', Name='Start Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Start Date',Updated=TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=574
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=574
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=15
;

-- Jul 14, 2012 12:19:20 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Jul 14, 2012 12:19:21 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63675,574,0,15,53399,'StartDate',TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),100,'First effective day (inclusive)','EE01',29,'The Start Date indicates the first or starting date','Y','N','N','N','N','N','N','N','Y','N','N','Start Date',TO_DATE('2012-07-14 00:19:20','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:21 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63675 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:21 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='EndDate', Description='Last effective date (inclusive)', EntityType='D', Help='The End Date indicates the last date in this range.', IsActive='Y', Name='End Date', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='End Date',Updated=TO_DATE('2012-07-14 00:19:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=294
;

-- Jul 14, 2012 12:19:21 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=294
;

-- Jul 14, 2012 12:19:22 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63676,294,0,15,53399,'EndDate',TO_DATE('2012-07-14 00:19:21','YYYY-MM-DD HH24:MI:SS'),100,'Last effective date (inclusive)','EE01',29,'The End Date indicates the last date in this range.','Y','N','N','N','N','N','N','N','Y','N','N','End Date',TO_DATE('2012-07-14 00:19:21','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:22 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63676 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:22 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PeriodNo', Description='Unique Period Number', EntityType='D', Help='The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.', IsActive='Y', Name='Period No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period No',Updated=TO_DATE('2012-07-14 00:19:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=500
;

-- Jul 14, 2012 12:19:22 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=500
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63677,500,0,11,53399,'PeriodNo',TO_DATE('2012-07-14 00:19:22','YYYY-MM-DD HH24:MI:SS'),100,'Unique Period Number','EE01',10,'The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.','Y','N','N','N','N','N','N','N','Y','N','N','Period No',TO_DATE('2012-07-14 00:19:22','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63677 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='LineDescription', Description='Description of the Line', EntityType='D', Help=NULL, IsActive='Y', Name='Line Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Line Description',Updated=TO_DATE('2012-07-14 00:19:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2108
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2108
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63678,2108,0,10,53399,'LineDescription',TO_DATE('2012-07-14 00:19:23','YYYY-MM-DD HH24:MI:SS'),100,'Description of the Line','EE01',255,'Y','N','N','N','N','N','N','N','Y','N','N','Line Description',TO_DATE('2012-07-14 00:19:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63678 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyCalculated', Description='Calculated Quantity', EntityType='D', Help=NULL, IsActive='Y', Name='Calculated Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Calculated Qty',Updated=TO_DATE('2012-07-14 00:19:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2500
;

-- Jul 14, 2012 12:19:23 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2500
;

-- Jul 14, 2012 12:19:24 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63679,2500,0,29,53399,'QtyCalculated',TO_DATE('2012-07-14 00:19:23','YYYY-MM-DD HH24:MI:SS'),100,'Calculated Quantity','EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','Calculated Quantity',TO_DATE('2012-07-14 00:19:23','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:24 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63679 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:24 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyPlan', Description='Planned Quantity', EntityType='D', Help='Planned Quantity', IsActive='Y', Name='Quantity Plan', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity Plan',Updated=TO_DATE('2012-07-14 00:19:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2900
;

-- Jul 14, 2012 12:19:24 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2900
;

-- Jul 14, 2012 12:19:25 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63680,2900,0,29,53399,'QtyPlan',TO_DATE('2012-07-14 00:19:24','YYYY-MM-DD HH24:MI:SS'),100,'Planned Quantity','EE01',131089,'Planned Quantity','Y','N','N','N','N','N','N','N','Y','N','N','Quantity Plan',TO_DATE('2012-07-14 00:19:24','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:25 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63680 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:25 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyAbnormal', Description='Abnormal Demand Quantity', EntityType='EE01', Help=NULL, IsActive='Y', Name='Abnormal Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Abnormal Quantity',Updated=TO_DATE('2012-07-14 00:19:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55529
;

-- Jul 14, 2012 12:19:25 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55529
;

-- Jul 14, 2012 12:19:26 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,63681,55529,0,29,53399,'QtyAbnormal',TO_DATE('2012-07-14 00:19:25','YYYY-MM-DD HH24:MI:SS'),100,'Abnormal Demand Quantity','EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','Abnormal Quantity',TO_DATE('2012-07-14 00:19:25','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 14, 2012 12:19:26 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63681 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 14, 2012 12:19:26 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_ReportView SET AD_Table_ID=53399, Description='Simulation of Forecast ', EntityType='EE01', IsActive='Y', Name='RV_PP_ForecastRun', OrderByClause=NULL, WhereClause=NULL,Updated=TO_DATE('2012-07-14 00:19:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_ReportView_ID=53047
;

-- Jul 14, 2012 12:19:26 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET AD_ReportView_ID=53047, AccessLevel='3', Classname=NULL, Description='This process generates a report containing the result of the forecast calculation, parameters can be used to filter the result of the report. ', EntityType='EE01', Help='The main goal of this report is to evaluate and analyze the result of multiple simulations to determine the most adequate, to be able to generate the forecast.', IsActive='Y', IsBetaFunctionality='N', IsDirectPrint='N', IsReport='Y', JasperReport=NULL, Name='Forecast Simulation Report', ProcedureName=NULL, ShowHelp='Y', Statistic_Count=0, Statistic_Seconds=0, Value='RV_PP_ForecastRun', WorkflowValue=NULL,Updated=TO_DATE('2012-07-14 00:19:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53303
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55515, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='PP_ForecastRun_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', FieldLength=22, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Forecast Run', SeqNo=10, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53697
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53697
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55511, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='PP_Calendar_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Operational Period, allows to define the periods for the Operational Calendar', EntityType='EE01', FieldLength=12, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Operational Calendar', SeqNo=20, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53698
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53698
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Val_Rule SET Code='PP_PeriodDefinition.PP_Calendar_ID=@PP_Calendar_ID@', Description=NULL, EntityType='EE01', IsActive='Y', Name='PP_PeriodDefinition of Calendar', Type='S',Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52122
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55518, AD_Process_ID=53303, AD_Reference_ID=18, AD_Reference_Value_ID=53438, AD_Val_Rule_ID=52122, ColumnName='Ref_DefinitionPeriod_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', FieldLength=22, Help=NULL, IsActive='Y', IsCentrallyMaintained='N', IsMandatory='N', IsRange='N', Name='Base Period Definition', SeqNo=30, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53699
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53699
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55512, AD_Process_ID=53303, AD_Reference_ID=19, AD_Val_Rule_ID=52122, ColumnName='PP_PeriodDefinition_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', FieldLength=11, Help=NULL, IsActive='Y', IsCentrallyMaintained='N', IsMandatory='N', IsRange='N', Name='Target Period Definition', SeqNo=40, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53700
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53700
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=500, AD_Process_ID=53303, AD_Reference_ID=11, ColumnName='PeriodNo', DefaultValue=NULL, DefaultValue2=NULL, Description='Unique Period Number', EntityType='EE01', FieldLength=22, Help='The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Period No', SeqNo=50, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53701
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55519, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='PP_ForecastRule_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Forecast Rules define the business logic according to a previously implemented algorithm.', EntityType='EE01', FieldLength=12, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Forecast Rule', SeqNo=60, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53702
;

-- Jul 14, 2012 12:19:27 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=53702
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=2814, AD_Process_ID=53303, AD_Reference_ID=18, AD_Reference_Value_ID=197, ColumnName='M_WarehouseSource_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Optional Warehouse to replenish from', EntityType='EE01', FieldLength=10, Help='If defined, the warehouse selected is used to replenish the product(s)', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Source Warehouse', SeqNo=70, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53703
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=459, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='M_Warehouse_ID', DefaultValue='-1', DefaultValue2=NULL, Description='Storage Warehouse and Service Point', EntityType='EE01', FieldLength=22, Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Warehouse', SeqNo=80, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53704
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=454, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='M_Product_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Product, Service, Item', EntityType='EE01', FieldLength=22, Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Product', SeqNo=90, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53705
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=453, AD_Process_ID=53303, AD_Reference_ID=19, AD_Reference_Value_ID=163, ColumnName='M_Product_Category_ID', DefaultValue='-1', DefaultValue2=NULL, Description='Category of a Product', EntityType='EE01', FieldLength=22, Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Product Category', SeqNo=100, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53706
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55490, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='M_Product_Classification_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Classification of a Product', EntityType='EE01', FieldLength=22, Help='Identifies the classification which this product belongs to.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Product Classification', SeqNo=110, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53707
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55492, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='M_Product_Class_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Class of a Product', EntityType='EE01', FieldLength=22, Help='Identifies the Class which this product belongs to', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Product Class', SeqNo=120, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53708
;

-- Jul 14, 2012 12:19:28 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Para SET AD_Element_ID=55494, AD_Process_ID=53303, AD_Reference_ID=19, ColumnName='M_Product_Group_ID', DefaultValue=NULL, DefaultValue2=NULL, Description='Group of a Product', EntityType='EE01', FieldLength=22, Help='Identifies the Group which this product belongs to.', IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Product Group', SeqNo=130, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2012-07-14 00:19:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53709
;

-- Jul 14, 2012 12:19:29 AM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_TREENODEMM SET Parent_ID = 53180 , SeqNo = 7 WHERE AD_Tree_ID = 10 AND Node_ID = 53428
;

