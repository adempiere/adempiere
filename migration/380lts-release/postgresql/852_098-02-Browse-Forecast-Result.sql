-- Jul 13, 2012 1:28:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View (AD_Client_ID,AD_Org_ID,AD_View_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50002,TO_TIMESTAMP('2012-07-13 13:28:33','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Run Result',TO_TIMESTAMP('2012-07-13 13:28:33','YYYY-MM-DD HH24:MI:SS'),100,'Forecast_Run_Result')
;

-- Jul 13, 2012 1:28:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Trl (AD_Language,AD_View_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_View_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_View t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_View_ID=50002 AND NOT EXISTS (SELECT * FROM AD_View_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_View_ID=t.AD_View_ID)
;

-- Jul 13, 2012 1:28:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Table SET AccessLevel='3', Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Forecast Run', ReplicationType='L', TableName='PP_ForecastRun',Updated=TO_TIMESTAMP('2012-07-13 13:28:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53391
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRun_ID', Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Run', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Run',Updated=TO_TIMESTAMP('2012-07-13 13:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55515
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55515
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=13
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55515, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRun_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run', ReadOnlyLogic=NULL, SeqNo=3, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63358
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=63358
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run', Description='Create the forecast simulation based on the forecast definition', Help=NULL WHERE AD_Column_ID=63358 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2012-07-13 13:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=102
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=19
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Jul 13, 2012 1:28:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_TIMESTAMP('2012-07-13 13:28:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=129
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63351
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2012-07-13 13:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=113
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='EE01', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63352
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2012-07-13 13:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=348
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=20
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Jul 13, 2012 1:28:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63353
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=245
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=16
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63354
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=607
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63355
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=246
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2012-07-13 13:28:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=110
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Jul 13, 2012 1:28:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63356
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=608
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63357
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastDefinition_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Definition',Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55516
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55516
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55516, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastDefinition_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Forecast Definition', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63359
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Definition', Description=NULL, Help=NULL WHERE AD_Column_ID=63359 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_PeriodDefinition_ID', Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', Help=NULL, IsActive='Y', Name='Period Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period Definition',Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55512
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55512
;

-- Jul 13, 2012 1:28:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Val_Rule SET Code='PP_PeriodDefinition_ID=@PP_PeriodDefinition_ID@', Description=NULL, EntityType='EE01', IsActive='Y', Name='PP_Period of Period Definition', Type='S',Updated=TO_TIMESTAMP('2012-07-13 13:28:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=52124
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55512, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=52124, Callout=NULL, ColumnName='PP_PeriodDefinition_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', FieldLength=11, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Period Definition', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63360
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Period Definition', Description='Period Definition, allows to define time cycles for the Operational Calendar', Help=NULL WHERE AD_Column_ID=63360 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_Calendar_ID', Description='Operational Period, allows to define the periods for the Operational Calendar', EntityType='EE01', Help=NULL, IsActive='Y', Name='Operational Calendar', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Operational Calendar',Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55511
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55511
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55511, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Calendar_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Operational Period, allows to define the periods for the Operational Calendar', EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Operational Calendar', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63361
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Operational Calendar', Description='Operational Period, allows to define the periods for the Operational Calendar', Help=NULL WHERE AD_Column_ID=63361 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PeriodHistory', Description='Number Period of History', EntityType='EE01', Help=NULL, IsActive='Y', Name='Periods of History', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Periods of History',Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55517
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55517
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=11
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55517, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PeriodHistory', ColumnSQL=NULL, DefaultValue=NULL, Description='Number Period of History', EntityType='EE01', FieldLength=10, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Periods of History', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63362
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Periods of History', Description='Number Period of History', Help=NULL WHERE AD_Column_ID=63362 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='DocumentNo', Description='Document sequence number of the document', EntityType='D', Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Document No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Document No',Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=290
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=290
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=10
;

-- Jul 13, 2012 1:28:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=290, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DocumentNo', ColumnSQL=NULL, DefaultValue=NULL, Description='Document sequence number of the document', EntityType='EE01', FieldLength=30, Help='The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='Y', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Document No', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63363
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=275
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='EE01', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='Y', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63364
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Processed', Description='The document has been processed', EntityType='D', Help='The Processed checkbox indicates that a document has been processed.', IsActive='Y', Name='Processed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Processed',Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=1047
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1047
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=1047, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processed', ColumnSQL=NULL, DefaultValue=NULL, Description='The document has been processed', EntityType='EE01', FieldLength=1, Help='The Processed checkbox indicates that a document has been processed.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Processed', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63365
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=524
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=28
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process SET AccessLevel='3', Classname='org.eevolution.process.ForecastRunCreate', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', IsBetaFunctionality='N', IsDirectPrint='N', IsReport='N', JasperReport=NULL, Name='PP_Calculate Forecast', ProcedureName=NULL, ShowHelp='Y', Statistic_Count=0, Statistic_Seconds=0, Value='PP_Calculate Forecast', WorkflowValue=NULL,Updated=TO_TIMESTAMP('2012-07-13 13:28:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=53301
;

-- Jul 13, 2012 1:28:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=53301
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=524, AD_Process_ID=53301, AD_Reference_ID=28, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processing', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=1, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Now', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63366
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Process Now', Description=NULL, Help=NULL WHERE AD_Column_ID=63366 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='Ref_DefinitionPeriod_ID', Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', Help=NULL, IsActive='Y', Name='Period Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period Definition',Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55518
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55518
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=18
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='PP_DefinitionPeriod', ValidationType='T',Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53438
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=53438
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 53388, AD_Display = 63299, AD_Key = 63298, isValueDisplayed = 'N', OrderByClause = '', EntityType ='EE01', WhereClause = '' WHERE AD_Reference_ID = 53438
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55518, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=53438, AD_Table_ID=53391, AD_Val_Rule_ID=52124, Callout=NULL, ColumnName='Ref_DefinitionPeriod_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Period Definition, allows to define time cycles for the Operational Calendar', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Period Definition', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63367
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Period Definition', Description='Period Definition, allows to define time cycles for the Operational Calendar', Help=NULL WHERE AD_Column_ID=63367 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=459
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=459, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Warehouse_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Storage Warehouse and Service Point', EntityType='EE01', FieldLength=22, Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Warehouse', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63368
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_WarehouseSource_ID', Description='Optional Warehouse to replenish from', EntityType='D', Help='If defined, the warehouse selected is used to replenish the product(s)', IsActive='Y', Name='Source Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Source Warehouse',Updated=TO_TIMESTAMP('2012-07-13 13:28:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2814
;

-- Jul 13, 2012 1:28:41 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2814
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='M_Warehouse of Client', ValidationType='T',Updated=TO_TIMESTAMP('2012-07-13 13:28:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=197
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=197
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Ref_Table SET AD_Table_ID = 190, AD_Display = 1152, AD_Key = 1151, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'M_Warehouse.AD_Client_ID=@#AD_Client_ID@' WHERE AD_Reference_ID = 197
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=2814, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=197, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_WarehouseSource_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional Warehouse to replenish from', EntityType='EE01', FieldLength=10, Help='If defined, the warehouse selected is used to replenish the product(s)', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Source Warehouse', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63369
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRule_ID', Description='Forecast Rules define the business logic according to a previously implemented algorithm.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Rule', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Rule',Updated=TO_TIMESTAMP('2012-07-13 13:28:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55519
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55519
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55519, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53391, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRule_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Forecast Rules define the business logic according to a previously implemented algorithm.', EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Forecast Rule', ReadOnlyLogic=NULL, SeqNo=3, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63370
;

-- Jul 13, 2012 1:28:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Rule', Description='Forecast Rules define the business logic according to a previously implemented algorithm.', Help=NULL WHERE AD_Column_ID=63370 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53391,50012,50002,TO_TIMESTAMP('2012-07-13 13:28:42','YYYY-MM-DD HH24:MI:SS'),100,'Y','N',10,'run',TO_TIMESTAMP('2012-07-13 13:28:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63366,0,50606,50012,50002,'RUN_Processing','run.Processing',TO_TIMESTAMP('2012-07-13 13:28:43','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Process Now',TO_TIMESTAMP('2012-07-13 13:28:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:44 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63367,0,50607,50012,50002,'RUN_Ref_DefinitionPeriod_ID','run.Ref_DefinitionPeriod_ID',TO_TIMESTAMP('2012-07-13 13:28:43','YYYY-MM-DD HH24:MI:SS'),100,'Period Definition, allows to define time cycles for the Operational Calendar','EE01','Y','Period Definition',TO_TIMESTAMP('2012-07-13 13:28:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63361,0,50608,50012,50002,'RUN_PP_Calendar_ID','run.PP_Calendar_ID',TO_TIMESTAMP('2012-07-13 13:28:44','YYYY-MM-DD HH24:MI:SS'),100,'Operational Period, allows to define the periods for the Operational Calendar','EE01','Y','Operational Calendar',TO_TIMESTAMP('2012-07-13 13:28:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63359,0,50609,50012,50002,'RUN_PP_ForecastDefinition_ID','run.PP_ForecastDefinition_ID',TO_TIMESTAMP('2012-07-13 13:28:45','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Definition',TO_TIMESTAMP('2012-07-13 13:28:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63370,0,50610,50012,50002,'RUN_PP_ForecastRule_ID','run.PP_ForecastRule_ID',TO_TIMESTAMP('2012-07-13 13:28:46','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Rules define the business logic according to a previously implemented algorithm.','EE01','Y','Forecast Rule',TO_TIMESTAMP('2012-07-13 13:28:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:47 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63358,0,50611,50012,50002,'RUN_PP_ForecastRun_ID','run.PP_ForecastRun_ID',TO_TIMESTAMP('2012-07-13 13:28:46','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','Forecast Run',TO_TIMESTAMP('2012-07-13 13:28:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:48 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63360,0,50612,50012,50002,'RUN_PP_PeriodDefinition_ID','run.PP_PeriodDefinition_ID',TO_TIMESTAMP('2012-07-13 13:28:47','YYYY-MM-DD HH24:MI:SS'),100,'Period Definition, allows to define time cycles for the Operational Calendar','EE01','Y','Period Definition',TO_TIMESTAMP('2012-07-13 13:28:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:48 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63362,0,50613,50012,50002,'RUN_PeriodHistory','run.PeriodHistory',TO_TIMESTAMP('2012-07-13 13:28:48','YYYY-MM-DD HH24:MI:SS'),100,'Number Period of History','EE01','Y','Periods of History',TO_TIMESTAMP('2012-07-13 13:28:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63355,0,50614,50012,50002,'RUN_Updated','run.Updated',TO_TIMESTAMP('2012-07-13 13:28:48','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2012-07-13 13:28:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63357,0,50615,50012,50002,'RUN_UpdatedBy','run.UpdatedBy',TO_TIMESTAMP('2012-07-13 13:28:49','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2012-07-13 13:28:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:50 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63351,0,50616,50012,50002,'RUN_AD_Client_ID','run.AD_Client_ID',TO_TIMESTAMP('2012-07-13 13:28:50','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2012-07-13 13:28:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:50 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63352,0,50617,50012,50002,'RUN_AD_Org_ID','run.AD_Org_ID',TO_TIMESTAMP('2012-07-13 13:28:50','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2012-07-13 13:28:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63354,0,50618,50012,50002,'RUN_Created','run.Created',TO_TIMESTAMP('2012-07-13 13:28:50','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2012-07-13 13:28:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63356,0,50619,50012,50002,'RUN_CreatedBy','run.CreatedBy',TO_TIMESTAMP('2012-07-13 13:28:51','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2012-07-13 13:28:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:52 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63364,0,50620,50012,50002,'RUN_Description','run.Description',TO_TIMESTAMP('2012-07-13 13:28:51','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE01','A description is limited to 255 characters.','Y','Description',TO_TIMESTAMP('2012-07-13 13:28:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:52 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63363,0,50621,50012,50002,'RUN_DocumentNo','run.DocumentNo',TO_TIMESTAMP('2012-07-13 13:28:52','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Document No',TO_TIMESTAMP('2012-07-13 13:28:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:53 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63353,0,50622,50012,50002,'RUN_IsActive','run.IsActive',TO_TIMESTAMP('2012-07-13 13:28:52','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2012-07-13 13:28:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:53 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63369,0,50623,50012,50002,'RUN_M_WarehouseSource_ID','run.M_WarehouseSource_ID',TO_TIMESTAMP('2012-07-13 13:28:53','YYYY-MM-DD HH24:MI:SS'),100,'Optional Warehouse to replenish from','EE01','If defined, the warehouse selected is used to replenish the product(s)','Y','Source Warehouse',TO_TIMESTAMP('2012-07-13 13:28:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:53 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63368,0,50624,50012,50002,'RUN_M_Warehouse_ID','run.M_Warehouse_ID',TO_TIMESTAMP('2012-07-13 13:28:53','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_TIMESTAMP('2012-07-13 13:28:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63365,0,50625,50012,50002,'RUN_Processed','run.Processed',TO_TIMESTAMP('2012-07-13 13:28:53','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','EE01','The Processed checkbox indicates that a document has been processed.','Y','Processed',TO_TIMESTAMP('2012-07-13 13:28:53','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Table SET AccessLevel='3', Description='Contains the products for this forecast simulation.', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Forecast Run Master', ReplicationType='L', TableName='PP_ForecastRunMaster',Updated=TO_TIMESTAMP('2012-07-13 13:28:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53392
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRunMaster_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Run Master', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Run Master',Updated=TO_TIMESTAMP('2012-07-13 13:28:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55520
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55520
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55520, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRunMaster_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run Master', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63378
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=63378
;

-- Jul 13, 2012 1:28:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run Master', Description=NULL, Help=NULL WHERE AD_Column_ID=63378 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63371
;

-- Jul 13, 2012 1:28:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='EE01', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63372
;

-- Jul 13, 2012 1:28:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63373
;

-- Jul 13, 2012 1:28:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63374
;

-- Jul 13, 2012 1:28:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63375
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63376
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63377
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55515, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRun_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Forecast Run', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63379
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run', Description='Create the forecast simulation based on the forecast definition', Help=NULL WHERE AD_Column_ID=63379 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=454
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=454, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Product_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product, Service, Item', EntityType='EE01', FieldLength=22, Help='Identifies an item which is either purchased or sold in this organization.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Product', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63380
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=459, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Warehouse_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Storage Warehouse and Service Point', EntityType='EE01', FieldLength=22, Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Warehouse', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63381
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorAlpha', Description='Identifies an Factor Alpha', EntityType='EE01', Help='The Factor Alpha is smoothing constant used in this exponential smoothing model.', IsActive='Y', Name='Factor Alpha', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Alpha',Updated=TO_TIMESTAMP('2012-07-13 13:28:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55521
;

-- Jul 13, 2012 1:28:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55521
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=22
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55521, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='FactorAlpha', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies an Factor Alpha', EntityType='EE01', FieldLength=22, Help='The Factor Alpha is smoothing constant used in this exponential smoothing model.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Factor Alpha', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63382
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorGamma', Description='Identifies a Factor Gamma', EntityType='EE01', Help='Factor Gamma is the second smoothing constant (gamma) used in this exponential smoothing model This is used to smooth the trend.', IsActive='Y', Name='Factor Gamma', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Gamma',Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55522
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55522
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55522, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='FactorGamma', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a Factor Gamma', EntityType='EE01', FieldLength=22, Help='Factor Gamma is the second smoothing constant (gamma) used in this exponential smoothing model This is used to smooth the trend.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Factor Gamma', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63383
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorMultiplier', Description='Identifies a Factor Multiplier', EntityType='EE01', Help='Factor Multiplier defines the increase or decrease in percentage for the forecast quantity, A negative percentage indicates that the amount is reduced.', IsActive='Y', Name='Factor Multiplier', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Multiplier',Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55523
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55523
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55523, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='FactorMultiplier', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a Factor Multiplier', EntityType='EE01', FieldLength=22, Help='Factor Multiplier defines the increase or decrease in percentage for the forecast quantity, A negative percentage indicates that the amount is reduced.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Factor Multiplier', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63384
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='FactorScale', Description='Identifies a Factor Scale', EntityType='EE01', Help='Factor Scale defines the scale in percentage applied for the forecast quantity, this value cannot be negative.', IsActive='Y', Name='Factor Scale', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Factor Scale',Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55524
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55524
;

-- Jul 13, 2012 1:28:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55524, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='FactorScale', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a Factor Scale', EntityType='EE01', FieldLength=22, Help='Factor Scale defines the scale in percentage applied for the forecast quantity, this value cannot be negative.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Factor Scale', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63385
;

-- Jul 13, 2012 1:28:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastDefinitionLine_ID', Description=NULL, EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Definition Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Definition Line',Updated=TO_TIMESTAMP('2012-07-13 13:28:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55525
;

-- Jul 13, 2012 1:28:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55525
;

-- Jul 13, 2012 1:28:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55525, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53392, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastDefinitionLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Forecast Definition Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:28:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63386
;

-- Jul 13, 2012 1:28:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Definition Line', Description=NULL, Help=NULL WHERE AD_Column_ID=63386 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:28:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53392,50013,50002,TO_TIMESTAMP('2012-07-13 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,'Y','INNER JOIN PP_ForecastRunMaster master ON (master.PP_ForecastRun_ID = run.PP_ForecastRun_ID)','N',20,'master',TO_TIMESTAMP('2012-07-13 13:28:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63371,0,50626,50013,50002,'MASTER_AD_Client_ID','master.AD_Client_ID',TO_TIMESTAMP('2012-07-13 13:28:58','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2012-07-13 13:28:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:28:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63372,0,50627,50013,50002,'MASTER_AD_Org_ID','master.AD_Org_ID',TO_TIMESTAMP('2012-07-13 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2012-07-13 13:28:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63374,0,50628,50013,50002,'MASTER_Created','master.Created',TO_TIMESTAMP('2012-07-13 13:28:59','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2012-07-13 13:28:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63376,0,50629,50013,50002,'MASTER_CreatedBy','master.CreatedBy',TO_TIMESTAMP('2012-07-13 13:29:00','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2012-07-13 13:29:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63382,0,50630,50013,50002,'MASTER_FactorAlpha','master.FactorAlpha',TO_TIMESTAMP('2012-07-13 13:29:00','YYYY-MM-DD HH24:MI:SS'),100,'Identifies an Factor Alpha','EE01','The Factor Alpha is smoothing constant used in this exponential smoothing model.','Y','Factor Alpha',TO_TIMESTAMP('2012-07-13 13:29:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63383,0,50631,50013,50002,'MASTER_FactorGamma','master.FactorGamma',TO_TIMESTAMP('2012-07-13 13:29:00','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Gamma','EE01','Factor Gamma is the second smoothing constant (gamma) used in this exponential smoothing model This is used to smooth the trend.','Y','Factor Gamma',TO_TIMESTAMP('2012-07-13 13:29:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63384,0,50632,50013,50002,'MASTER_FactorMultiplier','master.FactorMultiplier',TO_TIMESTAMP('2012-07-13 13:29:01','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Multiplier','EE01','Factor Multiplier defines the increase or decrease in percentage for the forecast quantity, A negative percentage indicates that the amount is reduced.','Y','Factor Multiplier',TO_TIMESTAMP('2012-07-13 13:29:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63385,0,50633,50013,50002,'MASTER_FactorScale','master.FactorScale',TO_TIMESTAMP('2012-07-13 13:29:01','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Scale','EE01','Factor Scale defines the scale in percentage applied for the forecast quantity, this value cannot be negative.','Y','Factor Scale',TO_TIMESTAMP('2012-07-13 13:29:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63373,0,50634,50013,50002,'MASTER_IsActive','master.IsActive',TO_TIMESTAMP('2012-07-13 13:29:02','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2012-07-13 13:29:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63380,0,50635,50013,50002,'MASTER_M_Product_ID','master.M_Product_ID',TO_TIMESTAMP('2012-07-13 13:29:02','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01','Identifies an item which is either purchased or sold in this organization.','Y','Product',TO_TIMESTAMP('2012-07-13 13:29:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63381,0,50636,50013,50002,'MASTER_M_Warehouse_ID','master.M_Warehouse_ID',TO_TIMESTAMP('2012-07-13 13:29:03','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Warehouse',TO_TIMESTAMP('2012-07-13 13:29:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63375,0,50637,50013,50002,'MASTER_Updated','master.Updated',TO_TIMESTAMP('2012-07-13 13:29:03','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2012-07-13 13:29:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63377,0,50638,50013,50002,'MASTER_UpdatedBy','master.UpdatedBy',TO_TIMESTAMP('2012-07-13 13:29:03','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2012-07-13 13:29:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:04 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63386,0,50639,50013,50002,'MASTER_PP_ForecastDefinitionLi','master.PP_ForecastDefinitionLine_ID',TO_TIMESTAMP('2012-07-13 13:29:04','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Definition Line',TO_TIMESTAMP('2012-07-13 13:29:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63378,0,50640,50013,50002,'MASTER_PP_ForecastRunMaster_ID','master.PP_ForecastRunMaster_ID',TO_TIMESTAMP('2012-07-13 13:29:04','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Run Master',TO_TIMESTAMP('2012-07-13 13:29:04','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63379,0,50641,50013,50002,'MASTER_PP_ForecastRun_ID','master.PP_ForecastRun_ID',TO_TIMESTAMP('2012-07-13 13:29:05','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','Forecast Run',TO_TIMESTAMP('2012-07-13 13:29:05','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Table SET AccessLevel='3', Description='Contains the forecasting calculation results based on forecast definition.', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Forecast Run Detail', ReplicationType='L', TableName='PP_ForecastRunDetail',Updated=TO_TIMESTAMP('2012-07-13 13:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53393
;

-- Jul 13, 2012 1:29:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRunDetail_ID', Description='Contains the forecasting calculation results based on forecast definition.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Run Detail', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Run Detail',Updated=TO_TIMESTAMP('2012-07-13 13:29:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55526
;

-- Jul 13, 2012 1:29:05 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55526
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55526, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRunDetail_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Contains the forecasting calculation results based on forecast definition.', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run Detail', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63394
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=63394
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run Detail', Description='Contains the forecasting calculation results based on forecast definition.', Help=NULL WHERE AD_Column_ID=63394 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63387
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='EE01', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63388
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63389
;

-- Jul 13, 2012 1:29:06 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63390
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63391
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63392
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63393
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_Period_ID', Description='Forecast Definition Periods.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Operational Period', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Operational Period',Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55513
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55513
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55513, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Period_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Forecast Definition Periods.', EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Operational Period', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63395
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Operational Period', Description='Forecast Definition Periods.', Help=NULL WHERE AD_Column_ID=63395 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyCalculated', Description='Calculated Quantity', EntityType='D', Help=NULL, IsActive='Y', Name='Calculated Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Calculated Qty',Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2500
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2500
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2012-07-13 13:29:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=29
;

-- Jul 13, 2012 1:29:07 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=2500, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyCalculated', ColumnSQL=NULL, DefaultValue=NULL, Description='Calculated Quantity', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Calculated Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63396
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Calculated Quantity', Description='Calculated Quantity', Help=NULL WHERE AD_Column_ID=63396 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55515, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRun_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63397
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run', Description='Create the forecast simulation based on the forecast definition', Help=NULL WHERE AD_Column_ID=63397 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PeriodNo', Description='Unique Period Number', EntityType='D', Help='The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.', IsActive='Y', Name='Period No', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period No',Updated=TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=500
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=500
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=500, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PeriodNo', ColumnSQL=NULL, DefaultValue=NULL, Description='Unique Period Number', EntityType='EE01', FieldLength=22, Help='The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Period No', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63398
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55520, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53393, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRunMaster_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run Master', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63399
;

-- Jul 13, 2012 1:29:08 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run Master', Description=NULL, Help=NULL WHERE AD_Column_ID=63399 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:09 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53393,50014,50002,TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),100,'Y','INNER JOIN PP_ForecastRunDetail detail ON (detail.PP_ForecastRunMaster_ID = master.PP_ForecastRunMaster_ID)','N',30,'detail',TO_TIMESTAMP('2012-07-13 13:29:08','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:09 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63387,0,50642,50014,50002,'DETAIL_AD_Client_ID','detail.AD_Client_ID',TO_TIMESTAMP('2012-07-13 13:29:09','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2012-07-13 13:29:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63388,0,50643,50014,50002,'DETAIL_AD_Org_ID','detail.AD_Org_ID',TO_TIMESTAMP('2012-07-13 13:29:09','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2012-07-13 13:29:09','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63394,0,50644,50014,50002,'DETAIL_PP_ForecastRunDetail_ID','detail.PP_ForecastRunDetail_ID',TO_TIMESTAMP('2012-07-13 13:29:10','YYYY-MM-DD HH24:MI:SS'),100,'Contains the forecasting calculation results based on forecast definition.','EE01','Y','Forecast Run Detail',TO_TIMESTAMP('2012-07-13 13:29:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:10 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63399,0,50645,50014,50002,'DETAIL_PP_ForecastRunMaster_ID','detail.PP_ForecastRunMaster_ID',TO_TIMESTAMP('2012-07-13 13:29:10','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Run Master',TO_TIMESTAMP('2012-07-13 13:29:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63397,0,50646,50014,50002,'DETAIL_PP_ForecastRun_ID','detail.PP_ForecastRun_ID',TO_TIMESTAMP('2012-07-13 13:29:10','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','Forecast Run',TO_TIMESTAMP('2012-07-13 13:29:10','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:11 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63395,0,50647,50014,50002,'DETAIL_PP_Period_ID','detail.PP_Period_ID',TO_TIMESTAMP('2012-07-13 13:29:11','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Definition Periods.','EE01','Y','Operational Period',TO_TIMESTAMP('2012-07-13 13:29:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:12 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63396,0,50648,50014,50002,'DETAIL_QtyCalculated','detail.QtyCalculated',TO_TIMESTAMP('2012-07-13 13:29:11','YYYY-MM-DD HH24:MI:SS'),100,'Calculated Quantity','EE01','Y','Calculated Quantity',TO_TIMESTAMP('2012-07-13 13:29:11','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:12 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63390,0,50649,50014,50002,'DETAIL_Created','detail.Created',TO_TIMESTAMP('2012-07-13 13:29:12','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2012-07-13 13:29:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63392,0,50650,50014,50002,'DETAIL_CreatedBy','detail.CreatedBy',TO_TIMESTAMP('2012-07-13 13:29:12','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2012-07-13 13:29:12','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63389,0,50651,50014,50002,'DETAIL_IsActive','detail.IsActive',TO_TIMESTAMP('2012-07-13 13:29:13','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2012-07-13 13:29:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:13 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63398,0,50652,50014,50002,'DETAIL_PeriodNo','detail.PeriodNo',TO_TIMESTAMP('2012-07-13 13:29:13','YYYY-MM-DD HH24:MI:SS'),100,'Unique Period Number','EE01','The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.','Y','Period No',TO_TIMESTAMP('2012-07-13 13:29:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:14 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63391,0,50653,50014,50002,'DETAIL_Updated','detail.Updated',TO_TIMESTAMP('2012-07-13 13:29:13','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2012-07-13 13:29:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:14 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63393,0,50654,50014,50002,'DETAIL_UpdatedBy','detail.UpdatedBy',TO_TIMESTAMP('2012-07-13 13:29:14','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2012-07-13 13:29:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:14 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Table SET AccessLevel='3', Description='Containts  the forecast summary calculation results.', EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Forecast Run Result', ReplicationType='L', TableName='PP_ForecastRunResult',Updated=TO_TIMESTAMP('2012-07-13 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53395
;

-- Jul 13, 2012 1:29:14 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='PP_ForecastRunResult_ID', Description='Containts  the forecast calculation results.', EntityType='EE01', Help=NULL, IsActive='Y', Name='Forecast Run Result', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Run Result',Updated=TO_TIMESTAMP('2012-07-13 13:29:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55528
;

-- Jul 13, 2012 1:29:14 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55528
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55528, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRunResult_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Containts  the forecast calculation results.', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run Result', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63420
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=63420
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run Result', Description='Containts  the forecast calculation results.', Help=NULL WHERE AD_Column_ID=63420 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=129, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='EE01', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63413
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='EE01', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63414
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='EE01', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='Y', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63415
;

-- Jul 13, 2012 1:29:15 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='EE01', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63416
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='EE01', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63417
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='EE01', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63418
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=110, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='EE01', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2012-07-13 13:29:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63419
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=2500, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyCalculated', ColumnSQL=NULL, DefaultValue=NULL, Description='Calculated Quantity', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Calculated Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63421
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Calculated Quantity', Description='Calculated Quantity', Help=NULL WHERE AD_Column_ID=63421 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyPlan', Description='Planned Quantity', EntityType='D', Help='Planned Quantity', IsActive='Y', Name='Quantity Plan', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Quantity Plan',Updated=TO_TIMESTAMP('2012-07-13 13:29:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2900
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2900
;

-- Jul 13, 2012 1:29:16 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=2900, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyPlan', ColumnSQL=NULL, DefaultValue=NULL, Description='Planned Quantity', EntityType='EE01', FieldLength=22, Help='Planned Quantity', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='Y', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Quantity Plan', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63422
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55513, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_Period_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Forecast Definition Periods.', EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Operational Period', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63423
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Operational Period', Description='Forecast Definition Periods.', Help=NULL WHERE AD_Column_ID=63423 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element SET ColumnName='QtyAbnormal', Description='Abnormal Demand Quantity', EntityType='EE01', Help=NULL, IsActive='Y', Name='Abnormal Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Abnormal Quantity',Updated=TO_TIMESTAMP('2012-07-13 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=55529
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=55529
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55529, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyAbnormal', ColumnSQL=NULL, DefaultValue=NULL, Description='Abnormal Demand Quantity', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='Y', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Abnormal Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63424
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Abnormal Quantity', Description='Abnormal Demand Quantity', Help=NULL WHERE AD_Column_ID=63424 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55515, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRun_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Create the forecast simulation based on the forecast definition', EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='N', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63425
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run', Description='Create the forecast simulation based on the forecast definition', Help=NULL WHERE AD_Column_ID=63425 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=500, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PeriodNo', ColumnSQL=NULL, DefaultValue=NULL, Description='Unique Period Number', EntityType='EE01', FieldLength=22, Help='The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Period No', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63426
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55520, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRunMaster_ID', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='EE01', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Run Master', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63427
;

-- Jul 13, 2012 1:29:17 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Run Master', Description=NULL, Help=NULL WHERE AD_Column_ID=63427 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:18 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='EE01', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='Y', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63428
;

-- Jul 13, 2012 1:29:18 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Column SET AD_Element_ID=55519, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=53395, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='PP_ForecastRule_ID', ColumnSQL='(SELECT PP_ForecastRule_ID FROM PP_ForecastRun WHERE PP_ForecastRun_ID=PP_ForecastRunResult.PP_ForecastRun_ID)', DefaultValue=NULL, Description='Forecast Rules define the business logic according to a previously implemented algorithm.', EntityType='EE01', FieldLength=12, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Rule', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2012-07-13 13:29:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63429
;

-- Jul 13, 2012 1:29:18 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Field SET Name='Forecast Rule', Description='Forecast Rules define the business logic according to a previously implemented algorithm.', Help=NULL WHERE AD_Column_ID=63429 AND IsCentrallyMaintained='Y'
;

-- Jul 13, 2012 1:29:18 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Definition (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_View_Definition_ID,AD_View_ID,Created,CreatedBy,IsActive,JoinClause,Processing,SeqNo,TableAlias,Updated,UpdatedBy) VALUES (0,0,53395,50015,50002,TO_TIMESTAMP('2012-07-13 13:29:18','YYYY-MM-DD HH24:MI:SS'),100,'Y','INNER JOIN PP_ForecastRunResult result ON (result.PP_ForecastRunMaster_ID = master.PP_ForecastRunMaster_ID)','N',40,'result',TO_TIMESTAMP('2012-07-13 13:29:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:19 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63413,0,50655,50015,50002,'RESULT_AD_Client_ID','result.AD_Client_ID',TO_TIMESTAMP('2012-07-13 13:29:18','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Client',TO_TIMESTAMP('2012-07-13 13:29:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:19 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63414,0,50656,50015,50002,'RESULT_AD_Org_ID','result.AD_Org_ID',TO_TIMESTAMP('2012-07-13 13:29:19','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Organization',TO_TIMESTAMP('2012-07-13 13:29:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:20 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63416,0,50657,50015,50002,'RESULT_Created','result.Created',TO_TIMESTAMP('2012-07-13 13:29:19','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','Created',TO_TIMESTAMP('2012-07-13 13:29:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:20 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63418,0,50658,50015,50002,'RESULT_CreatedBy','result.CreatedBy',TO_TIMESTAMP('2012-07-13 13:29:20','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','Created By',TO_TIMESTAMP('2012-07-13 13:29:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:21 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63415,0,50659,50015,50002,'RESULT_IsActive','result.IsActive',TO_TIMESTAMP('2012-07-13 13:29:20','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Active',TO_TIMESTAMP('2012-07-13 13:29:20','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:21 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63426,0,50660,50015,50002,'RESULT_PeriodNo','result.PeriodNo',TO_TIMESTAMP('2012-07-13 13:29:21','YYYY-MM-DD HH24:MI:SS'),100,'Unique Period Number','EE01','The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.','Y','Period No',TO_TIMESTAMP('2012-07-13 13:29:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:22 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63422,0,50661,50015,50002,'RESULT_QtyPlan','result.QtyPlan',TO_TIMESTAMP('2012-07-13 13:29:21','YYYY-MM-DD HH24:MI:SS'),100,'Planned Quantity','EE01','Planned Quantity','Y','Quantity Plan',TO_TIMESTAMP('2012-07-13 13:29:21','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:22 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63417,0,50662,50015,50002,'RESULT_Updated','result.Updated',TO_TIMESTAMP('2012-07-13 13:29:22','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','Updated',TO_TIMESTAMP('2012-07-13 13:29:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:23 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,Updated,UpdatedBy) VALUES (0,63419,0,50663,50015,50002,'RESULT_UpdatedBy','result.UpdatedBy',TO_TIMESTAMP('2012-07-13 13:29:22','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','Updated By',TO_TIMESTAMP('2012-07-13 13:29:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:23 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63427,0,50664,50015,50002,'RESULT_PP_ForecastRunMaster_ID','result.PP_ForecastRunMaster_ID',TO_TIMESTAMP('2012-07-13 13:29:23','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Run Master',TO_TIMESTAMP('2012-07-13 13:29:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:24 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63420,0,50665,50015,50002,'RESULT_PP_ForecastRunResult_ID','result.PP_ForecastRunResult_ID',TO_TIMESTAMP('2012-07-13 13:29:23','YYYY-MM-DD HH24:MI:SS'),100,'Containts  the forecast calculation results.','EE01','Y','Forecast Run Result',TO_TIMESTAMP('2012-07-13 13:29:23','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:24 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63425,0,50666,50015,50002,'RESULT_PP_ForecastRun_ID','result.PP_ForecastRun_ID',TO_TIMESTAMP('2012-07-13 13:29:24','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','Forecast Run',TO_TIMESTAMP('2012-07-13 13:29:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:25 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63423,0,50667,50015,50002,'RESULT_PP_Period_ID','result.PP_Period_ID',TO_TIMESTAMP('2012-07-13 13:29:24','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Definition Periods.','EE01','Y','Operational Period',TO_TIMESTAMP('2012-07-13 13:29:24','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:25 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63424,0,50668,50015,50002,'RESULT_QtyAbnormal','result.QtyAbnormal',TO_TIMESTAMP('2012-07-13 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,'Abnormal Demand Quantity','EE01','Y','Abnormal Quantity',TO_TIMESTAMP('2012-07-13 13:29:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:25 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_View_Column (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_View_Column_ID,AD_View_Definition_ID,AD_View_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,63421,0,50669,50015,50002,'RESULT_QtyCalculated','result.QtyCalculated',TO_TIMESTAMP('2012-07-13 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,'Calculated Quantity','EE01','Y','Calculated Quantity',TO_TIMESTAMP('2012-07-13 13:29:25','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse (AD_Browse_ID,AD_Client_ID,AD_Org_ID,AD_View_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,Name,Processing,Updated,UpdatedBy,Value,WhereClause) VALUES (50004,0,0,50002,'3',TO_TIMESTAMP('2012-07-13 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','Forecast Run Result','N',TO_TIMESTAMP('2012-07-13 13:29:25','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Run Result',NULL)
;

-- Jul 13, 2012 1:29:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Trl (AD_Language,AD_Browse_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_ID=50004 AND NOT EXISTS (SELECT * FROM AD_Browse_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_ID=t.AD_Browse_ID)
;

-- Jul 13, 2012 1:29:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50880,50004,0,524,0,28,50606,TO_TIMESTAMP('2012-07-13 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','N','Y','N','N','Y','Process Now',0,TO_TIMESTAMP('2012-07-13 13:29:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:26 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50880 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:27 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50881,50004,0,246,0,19,110,50619,TO_TIMESTAMP('2012-07-13 13:29:26','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','Created By',0,TO_TIMESTAMP('2012-07-13 13:29:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:27 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50881 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:27 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50882,50004,0,1047,0,20,50625,TO_TIMESTAMP('2012-07-13 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,'The document has been processed','EE01','The Processed checkbox indicates that a document has been processed.','Y','N','N','N','N','Y','N','N','Y','Processed',0,TO_TIMESTAMP('2012-07-13 13:29:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:27 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50882 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50883,50004,0,608,0,19,110,50615,TO_TIMESTAMP('2012-07-13 13:29:27','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','Updated By',0,TO_TIMESTAMP('2012-07-13 13:29:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50883 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50884,50004,0,500,0,11,50652,TO_TIMESTAMP('2012-07-13 13:29:28','YYYY-MM-DD HH24:MI:SS'),100,'Unique Period Number','EE01','The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.','Y','N','N','N','N','Y','N','N','Y','Period No',0,TO_TIMESTAMP('2012-07-13 13:29:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:28 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50884 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:29 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50885,50004,0,607,0,16,50653,TO_TIMESTAMP('2012-07-13 13:29:28','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','Updated',0,TO_TIMESTAMP('2012-07-13 13:29:28','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:29 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50885 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:29 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50886,50004,0,608,0,19,110,50654,TO_TIMESTAMP('2012-07-13 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','Updated By',0,TO_TIMESTAMP('2012-07-13 13:29:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:29 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50886 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:30 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50887,50004,0,113,0,19,50627,TO_TIMESTAMP('2012-07-13 13:29:29','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','Organization',0,TO_TIMESTAMP('2012-07-13 13:29:29','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:30 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50887 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:30 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50888,50004,0,55512,0,19,50612,TO_TIMESTAMP('2012-07-13 13:29:30','YYYY-MM-DD HH24:MI:SS'),100,'Period Definition, allows to define time cycles for the Operational Calendar','EE01','Y','Y','Y','N','N','Y','Y','N','Y','Period Definition',70,TO_TIMESTAMP('2012-07-13 13:29:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:30 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50888 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50889,50004,0,245,0,16,50628,TO_TIMESTAMP('2012-07-13 13:29:30','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','Created',0,TO_TIMESTAMP('2012-07-13 13:29:30','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50889 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50890,50004,0,246,0,19,110,50629,TO_TIMESTAMP('2012-07-13 13:29:31','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','Created By',0,TO_TIMESTAMP('2012-07-13 13:29:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:31 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50890 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:32 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50891,50004,0,245,0,16,50618,TO_TIMESTAMP('2012-07-13 13:29:31','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','Created',0,TO_TIMESTAMP('2012-07-13 13:29:31','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:32 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50891 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50892,50004,0,55515,0,19,50611,TO_TIMESTAMP('2012-07-13 13:29:32','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','Y','Y','N','Y','Y','Y','N','Y','Forecast Run',10,TO_TIMESTAMP('2012-07-13 13:29:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50892 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50893,50004,0,275,0,10,50620,TO_TIMESTAMP('2012-07-13 13:29:33','YYYY-MM-DD HH24:MI:SS'),100,'Optional short description of the record','EE01','A description is limited to 255 characters.','Y','Y','Y','Y','N','Y','N','N','Y','Description',40,TO_TIMESTAMP('2012-07-13 13:29:33','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:33 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50893 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50894,50004,0,55511,0,19,50608,TO_TIMESTAMP('2012-07-13 13:29:34','YYYY-MM-DD HH24:MI:SS'),100,'Operational Period, allows to define the periods for the Operational Calendar','EE01','Y','Y','Y','N','N','Y','N','N','Y','Operational Calendar',50,TO_TIMESTAMP('2012-07-13 13:29:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50894 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50895,50004,0,102,0,19,50616,TO_TIMESTAMP('2012-07-13 13:29:34','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','Client',0,TO_TIMESTAMP('2012-07-13 13:29:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:34 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50895 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50896,50004,0,245,0,16,50649,TO_TIMESTAMP('2012-07-13 13:29:34','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','Created',0,TO_TIMESTAMP('2012-07-13 13:29:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50896 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50897,50004,0,113,0,19,50643,TO_TIMESTAMP('2012-07-13 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','Organization',0,TO_TIMESTAMP('2012-07-13 13:29:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:35 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50897 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50898,50004,0,246,0,19,110,50650,TO_TIMESTAMP('2012-07-13 13:29:35','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','Created By',0,TO_TIMESTAMP('2012-07-13 13:29:35','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50898 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50899,50004,0,55526,0,13,50644,TO_TIMESTAMP('2012-07-13 13:29:36','YYYY-MM-DD HH24:MI:SS'),100,'Contains the forecasting calculation results based on forecast definition.','EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run Detail',0,TO_TIMESTAMP('2012-07-13 13:29:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:36 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50899 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50900,50004,0,348,0,20,50634,TO_TIMESTAMP('2012-07-13 13:29:36','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','Active',0,TO_TIMESTAMP('2012-07-13 13:29:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50900 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50901,50004,0,459,0,19,50636,TO_TIMESTAMP('2012-07-13 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','N','N','Y','N','Y','N','N','Y','Warehouse',0,TO_TIMESTAMP('2012-07-13 13:29:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50901 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50902,50004,0,55525,0,19,50639,TO_TIMESTAMP('2012-07-13 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Definition Line',0,TO_TIMESTAMP('2012-07-13 13:29:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:37 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50902 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50903,50004,0,55520,0,13,50640,TO_TIMESTAMP('2012-07-13 13:29:37','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run Master',0,TO_TIMESTAMP('2012-07-13 13:29:37','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50903 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50904,50004,0,55515,0,19,50641,TO_TIMESTAMP('2012-07-13 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run',0,TO_TIMESTAMP('2012-07-13 13:29:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:38 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50904 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50905,50004,0,607,0,16,50637,TO_TIMESTAMP('2012-07-13 13:29:38','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','Updated',0,TO_TIMESTAMP('2012-07-13 13:29:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50905 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50906,50004,0,608,0,19,110,50638,TO_TIMESTAMP('2012-07-13 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','Updated By',0,TO_TIMESTAMP('2012-07-13 13:29:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:39 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50906 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50907,50004,0,102,0,19,50642,TO_TIMESTAMP('2012-07-13 13:29:39','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','Client',0,TO_TIMESTAMP('2012-07-13 13:29:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50907 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50908,50004,0,348,0,20,50651,TO_TIMESTAMP('2012-07-13 13:29:40','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','Active',0,TO_TIMESTAMP('2012-07-13 13:29:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:40 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50908 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50909,50004,0,102,0,19,50626,TO_TIMESTAMP('2012-07-13 13:29:40','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','Client',0,TO_TIMESTAMP('2012-07-13 13:29:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:42 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50909 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50910,50004,0,55528,0,13,50665,TO_TIMESTAMP('2012-07-13 13:29:42','YYYY-MM-DD HH24:MI:SS'),100,'Containts  the forecast calculation results.','EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run Result',0,TO_TIMESTAMP('2012-07-13 13:29:42','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:43 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50910 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:44 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50911,50004,0,55515,0,19,50666,TO_TIMESTAMP('2012-07-13 13:29:43','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','N','N','Y','N','Y','N','N','Y','Forecast Run',0,TO_TIMESTAMP('2012-07-13 13:29:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:44 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50911 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:44 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50912,50004,0,55529,0,29,50668,TO_TIMESTAMP('2012-07-13 13:29:44','YYYY-MM-DD HH24:MI:SS'),100,'Abnormal Demand Quantity','EE01','Y','N','N','N','N','Y','N','N','Y','Abnormal Quantity',0,TO_TIMESTAMP('2012-07-13 13:29:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:44 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50912 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50913,50004,0,2500,0,29,50669,TO_TIMESTAMP('2012-07-13 13:29:44','YYYY-MM-DD HH24:MI:SS'),100,'Calculated Quantity','EE01','Y','N','N','N','N','Y','N','N','Y','Calculated Quantity',0,TO_TIMESTAMP('2012-07-13 13:29:44','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50913 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50914,50004,0,113,0,19,50617,TO_TIMESTAMP('2012-07-13 13:29:45','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','N','N','Y','Organization',20,TO_TIMESTAMP('2012-07-13 13:29:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:45 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50914 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Axis_Column_ID,Axis_Parent_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50915,50004,0,55513,0,19,50647,50648,50607,TO_TIMESTAMP('2012-07-13 13:29:45','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Definition Periods.','EE01','Y','Y','Y','N','N','Y','N','N','Y','Operational Period',170,TO_TIMESTAMP('2012-07-13 13:29:45','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50915 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50916,50004,0,459,0,19,50624,TO_TIMESTAMP('2012-07-13 13:29:46','YYYY-MM-DD HH24:MI:SS'),100,'Storage Warehouse and Service Point','EE01','The Warehouse identifies a unique Warehouse where products are stored or Services are provided.','Y','Y','Y','N','N','Y','N','N','Y','Warehouse',100,TO_TIMESTAMP('2012-07-13 13:29:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50916 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50917,50004,0,55519,0,19,50610,TO_TIMESTAMP('2012-07-13 13:29:46','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Rules define the business logic according to a previously implemented algorithm.','EE01','Y','Y','Y','Y','N','Y','N','N','Y','Forecast Rule',110,TO_TIMESTAMP('2012-07-13 13:29:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:46 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50917 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:47 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50918,50004,0,454,0,19,50635,TO_TIMESTAMP('2012-07-13 13:29:46','YYYY-MM-DD HH24:MI:SS'),100,'Product, Service, Item','EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','Y','N','Y','N','N','Y','Product',120,TO_TIMESTAMP('2012-07-13 13:29:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:47 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50918 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:47 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50919,50004,0,55521,0,22,50630,TO_TIMESTAMP('2012-07-13 13:29:47','YYYY-MM-DD HH24:MI:SS'),100,'Identifies an Factor Alpha','EE01','The Factor Alpha is smoothing constant used in this exponential smoothing model.','Y','Y','Y','N','N','Y','N','N','Y','Factor Alpha',130,TO_TIMESTAMP('2012-07-13 13:29:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:47 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50919 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:48 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50920,50004,0,55522,0,22,50631,TO_TIMESTAMP('2012-07-13 13:29:47','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Gamma','EE01','Factor Gamma is the second smoothing constant (gamma) used in this exponential smoothing model This is used to smooth the trend.','Y','Y','Y','N','N','Y','N','N','Y','Factor Gamma',140,TO_TIMESTAMP('2012-07-13 13:29:47','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:48 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50920 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:48 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50921,50004,0,55523,0,22,50632,TO_TIMESTAMP('2012-07-13 13:29:48','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Multiplier','EE01','Factor Multiplier defines the increase or decrease in percentage for the forecast quantity, A negative percentage indicates that the amount is reduced.','Y','Y','Y','N','N','Y','N','N','Y','Factor Multiplier',150,TO_TIMESTAMP('2012-07-13 13:29:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:48 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50921 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50922,50004,0,55516,0,19,50609,TO_TIMESTAMP('2012-07-13 13:29:48','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Definition',0,TO_TIMESTAMP('2012-07-13 13:29:48','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50922 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50923,50004,0,55515,0,19,50646,TO_TIMESTAMP('2012-07-13 13:29:49','YYYY-MM-DD HH24:MI:SS'),100,'Create the forecast simulation based on the forecast definition','EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run',0,TO_TIMESTAMP('2012-07-13 13:29:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50923 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50924,50004,0,2500,0,29,50648,TO_TIMESTAMP('2012-07-13 13:29:49','YYYY-MM-DD HH24:MI:SS'),100,'Calculated Quantity','EE01','Y','N','N','N','N','Y','N','N','Y','Calculated Quantity',0,TO_TIMESTAMP('2012-07-13 13:29:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:49 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50924 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:50 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50925,50004,0,102,0,19,50655,TO_TIMESTAMP('2012-07-13 13:29:49','YYYY-MM-DD HH24:MI:SS'),100,'Client/Tenant for this installation.','EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','Client',0,TO_TIMESTAMP('2012-07-13 13:29:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:50 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50925 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50926,50004,0,113,0,19,50656,TO_TIMESTAMP('2012-07-13 13:29:50','YYYY-MM-DD HH24:MI:SS'),100,'Organizational entity within client','EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','Organization',0,TO_TIMESTAMP('2012-07-13 13:29:50','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:51 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50926 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:52 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50927,50004,0,245,0,16,50657,TO_TIMESTAMP('2012-07-13 13:29:51','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was created','EE01','The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','Created',0,TO_TIMESTAMP('2012-07-13 13:29:51','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:52 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50927 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:52 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50928,50004,0,246,0,19,110,50658,TO_TIMESTAMP('2012-07-13 13:29:52','YYYY-MM-DD HH24:MI:SS'),100,'User who created this records','EE01','The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','Created By',0,TO_TIMESTAMP('2012-07-13 13:29:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:52 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50928 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50929,50004,0,348,0,20,50659,TO_TIMESTAMP('2012-07-13 13:29:52','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','Active',0,TO_TIMESTAMP('2012-07-13 13:29:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50929 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50930,50004,0,290,0,10,50621,TO_TIMESTAMP('2012-07-13 13:29:54','YYYY-MM-DD HH24:MI:SS'),100,'Document sequence number of the document','EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','Y','N','Y','N','N','Y','Document No',30,TO_TIMESTAMP('2012-07-13 13:29:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:54 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50930 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50931,50004,0,2814,0,18,197,50623,TO_TIMESTAMP('2012-07-13 13:29:54','YYYY-MM-DD HH24:MI:SS'),100,'Optional Warehouse to replenish from','EE01','If defined, the warehouse selected is used to replenish the product(s)','Y','Y','Y','N','N','Y','N','N','Y','Source Warehouse',80,TO_TIMESTAMP('2012-07-13 13:29:54','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:55 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50931 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50932,50004,0,55524,0,22,50633,TO_TIMESTAMP('2012-07-13 13:29:55','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a Factor Scale','EE01','Factor Scale defines the scale in percentage applied for the forecast quantity, this value cannot be negative.','Y','Y','Y','N','N','Y','N','N','Y','Factor Scale',160,TO_TIMESTAMP('2012-07-13 13:29:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50932 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50933,50004,0,55518,0,18,53438,50607,TO_TIMESTAMP('2012-07-13 13:29:56','YYYY-MM-DD HH24:MI:SS'),100,'Period Definition, allows to define time cycles for the Operational Calendar','EE01','Y','Y','Y','N','N','Y','Y','N','Y','Period Definition',60,TO_TIMESTAMP('2012-07-13 13:29:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:56 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50933 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50934,50004,0,55517,0,11,50613,TO_TIMESTAMP('2012-07-13 13:29:56','YYYY-MM-DD HH24:MI:SS'),100,'Number Period of History','EE01','Y','Y','Y','N','N','Y','N','N','Y','Periods of History',90,TO_TIMESTAMP('2012-07-13 13:29:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50934 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Axis_Column_ID,Axis_Parent_Column_ID,Created,CreatedBy,Description,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50935,50004,0,55513,0,19,50667,50661,50612,TO_TIMESTAMP('2012-07-13 13:29:57','YYYY-MM-DD HH24:MI:SS'),100,'Forecast Definition Periods.','EE01','Y','Y','Y','N','N','Y','N','N','Y','Operational Period',180,TO_TIMESTAMP('2012-07-13 13:29:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:57 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50935 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50936,50004,0,55520,0,19,50664,TO_TIMESTAMP('2012-07-13 13:29:57','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run Master',0,TO_TIMESTAMP('2012-07-13 13:29:57','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:58 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50936 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50937,50004,0,2900,0,29,50661,TO_TIMESTAMP('2012-07-13 13:29:58','YYYY-MM-DD HH24:MI:SS'),100,'Planned Quantity','EE01','Planned Quantity','Y','N','N','N','N','Y','N','N','Y','Quantity Plan',0,TO_TIMESTAMP('2012-07-13 13:29:58','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50937 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:29:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50938,50004,0,607,0,16,50662,TO_TIMESTAMP('2012-07-13 13:29:59','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','Updated',0,TO_TIMESTAMP('2012-07-13 13:29:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:29:59 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50938 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:30:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50939,50004,0,608,0,19,110,50663,TO_TIMESTAMP('2012-07-13 13:29:59','YYYY-MM-DD HH24:MI:SS'),100,'User who updated this records','EE01','The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','Updated By',0,TO_TIMESTAMP('2012-07-13 13:29:59','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:30:00 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50939 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:30:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50940,50004,0,348,0,20,50622,TO_TIMESTAMP('2012-07-13 13:30:00','YYYY-MM-DD HH24:MI:SS'),100,'The record is active in the system','EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','Active',0,TO_TIMESTAMP('2012-07-13 13:30:00','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:30:01 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50940 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:30:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50941,50004,0,607,0,16,50614,TO_TIMESTAMP('2012-07-13 13:30:01','YYYY-MM-DD HH24:MI:SS'),100,'Date this record was updated','EE01','The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','Updated',0,TO_TIMESTAMP('2012-07-13 13:30:01','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:30:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50941 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:30:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50942,50004,0,500,0,11,50660,TO_TIMESTAMP('2012-07-13 13:30:02','YYYY-MM-DD HH24:MI:SS'),100,'Unique Period Number','EE01','The Period No identifies a specific period for this year. Each period is defined by a start and end date.  Date ranges for a calendar and year cannot overlap.','Y','N','N','N','N','Y','N','Y','Y','Period No',0,TO_TIMESTAMP('2012-07-13 13:30:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:30:02 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50942 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:30:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field (AD_Browse_Field_ID,AD_Browse_ID,AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_View_Column_ID,Created,CreatedBy,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsIdentifier,IsKey,IsMandatory,IsQueryCriteria,IsRange,IsReadOnly,Name,SeqNo,Updated,UpdatedBy) VALUES (50943,50004,0,55520,0,19,50645,TO_TIMESTAMP('2012-07-13 13:30:02','YYYY-MM-DD HH24:MI:SS'),100,'EE01','Y','N','N','N','N','Y','N','N','Y','Forecast Run Master',0,TO_TIMESTAMP('2012-07-13 13:30:02','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 13, 2012 1:30:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
INSERT INTO AD_Browse_Field_Trl (AD_Language,AD_Browse_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Browse_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Browse_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Browse_Field_ID=50943 AND NOT EXISTS (SELECT * FROM AD_Browse_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Browse_Field_ID=t.AD_Browse_Field_ID)
;

-- Jul 13, 2012 1:30:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu SET AD_Browse_ID=50004, "action"='S', Description=NULL, EntityType='EE01', IsActive='Y', IsReadOnly='N', IsSOTrx='N', IsSummary='N', Name='Forecast Run Result ',Updated=TO_TIMESTAMP('2012-07-13 13:30:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=53427
;

-- Jul 13, 2012 1:30:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=53427
;

-- Jul 13, 2012 1:30:03 PM CDT
-- https://adempiere.atlassian.net/browse/ADEMPIERE-98
UPDATE AD_TREENODEMM SET Parent_ID = 53180 , SeqNo = 6 WHERE AD_Tree_ID = 10 AND Node_ID = 53427
;

