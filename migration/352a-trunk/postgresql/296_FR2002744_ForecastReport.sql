-- Jun 25, 2008 10:50:47 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Table SET AD_Window_ID=328, AccessLevel='3', Description='Material Forecast', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Forecast', ReplicationType='L', TableName='M_Forecast',Updated=TO_TIMESTAMP('2008-06-25 22:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=720
;

-- Jun 25, 2008 10:50:47 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2008-06-25 22:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- Jun 25, 2008 10:50:47 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Jun 25, 2008 10:50:47 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Jun 25, 2008 10:50:47 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Jun 25, 2008 10:50:48 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2008-06-25 22:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Jun 25, 2008 10:50:48 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Jun 25, 2008 10:50:48 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Jun 25, 2008 10:50:48 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11904
;

-- Jun 25, 2008 10:50:48 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2008-06-25 22:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- Jun 25, 2008 10:50:48 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Jun 25, 2008 10:50:49 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:50:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Jun 25, 2008 10:50:49 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Jun 25, 2008 10:50:49 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11917
;

-- Jun 25, 2008 10:50:52 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='C_Calendar_ID', Description='Accounting Calendar Name', EntityType='D', Help='The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.', IsActive='Y', Name='Calendar', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Calendar',Updated=TO_TIMESTAMP('2008-06-25 22:50:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=190
;

-- Jun 25, 2008 10:50:52 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=190
;

-- Jun 25, 2008 10:50:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=190, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='C_Calendar_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Accounting Calendar Name', EntityType='D', FieldLength=22, Help='The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Calendar', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11912
;

-- Jun 25, 2008 10:50:55 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='C_Year_ID', Description='Calendar Year', EntityType='D', Help='The Year uniquely identifies an accounting year for a calendar.', IsActive='Y', Name='Year', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Year',Updated=TO_TIMESTAMP('2008-06-25 22:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=223
;

-- Jun 25, 2008 10:50:55 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=223
;

-- Jun 25, 2008 10:50:56 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Val_Rule SET Code='C_Year.C_Calendar_ID=@C_Calendar_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='C_Year of Calendar', Type='S',Updated=TO_TIMESTAMP('2008-06-25 22:50:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=198
;

-- Jun 25, 2008 10:50:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=223, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=198, Callout=NULL, ColumnName='C_Year_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Calendar Year', EntityType='D', FieldLength=22, Help='The Year uniquely identifies an accounting year for a calendar.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Year', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11909
;

-- Jun 25, 2008 10:50:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2008-06-25 22:50:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- Jun 25, 2008 10:50:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Jun 25, 2008 10:50:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:50:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Jun 25, 2008 10:50:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Jun 25, 2008 10:50:58 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11907
;

-- Jun 25, 2008 10:50:58 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2008-06-25 22:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- Jun 25, 2008 10:50:58 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Jun 25, 2008 10:50:58 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11906
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2008-06-25 22:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=275
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11911
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Help', Description='Comment or Hint', EntityType='D', Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', Name='Comment/Help', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Comment',Updated=TO_TIMESTAMP('2008-06-25 22:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=326
;

-- Jun 25, 2008 10:50:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=326
;

-- Jun 25, 2008 10:51:00 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=326, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Help', ColumnSQL=NULL, DefaultValue=NULL, Description='Comment or Hint', EntityType='D', FieldLength=2000, Help='The Help field contains a hint, comment or help about the use of this item.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Comment/Help', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11905
;

-- Jun 25, 2008 10:51:00 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2008-06-25 22:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- Jun 25, 2008 10:51:00 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Jun 25, 2008 10:51:00 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Jun 25, 2008 10:51:00 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Jun 25, 2008 10:51:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11913
;

-- Jun 25, 2008 10:51:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='IsDefault', Description='Default value', EntityType='D', Help='The Default Checkbox indicates if this record will be used as a default value.', IsActive='Y', Name='Default', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Default',Updated=TO_TIMESTAMP('2008-06-25 22:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1103
;

-- Jun 25, 2008 10:51:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1103
;

-- Jun 25, 2008 10:51:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=1103, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsDefault', ColumnSQL=NULL, DefaultValue=NULL, Description='Default value', EntityType='D', FieldLength=1, Help='The Default Checkbox indicates if this record will be used as a default value.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Default', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11915
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='M_Forecast_ID', Description='Material Forecast', EntityType='D', Help='Material Forecast', IsActive='Y', Name='Forecast', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast',Updated=TO_TIMESTAMP('2008-06-25 22:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2498
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2498
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=2498, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Forecast_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Material Forecast', EntityType='D', FieldLength=22, Help='Material Forecast', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11918
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='M_PriceList_ID', Description='Unique identifier of a Price List', EntityType='D', Help='Price Lists are used to determine the pricing, margin and cost of items purchased or sold.', IsActive='Y', Name='Price List', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Price List',Updated=TO_TIMESTAMP('2008-06-25 22:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=449
;

-- Jun 25, 2008 10:51:02 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=449
;

-- Jun 25, 2008 10:51:11 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56112,449,0,19,720,'M_PriceList_ID',TO_TIMESTAMP('2008-06-25 22:51:02','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List','EE01',22,'Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','N','N','N','N','N','N','N','Y','N','Y','Price List',TO_TIMESTAMP('2008-06-25 22:51:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:11 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56112 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:11 PM CDT
-- Default comment for updating dictionary
ALTER TABLE M_Forecast ADD COLUMN M_PriceList_ID NUMERIC(10)
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2008-06-25 22:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11910
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2008-06-25 22:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=524
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Jun 25, 2008 10:51:12 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Jun 25, 2008 10:51:13 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=524, AD_Process_ID=NULL, AD_Reference_ID=28, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processing', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=1, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Now', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11916
;

-- Jun 25, 2008 10:51:13 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET Name='Process Now', Description=NULL, Help=NULL WHERE AD_Column_ID=11916 AND IsCentrallyMaintained='Y'
;

-- Jun 25, 2008 10:51:13 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2008-06-25 22:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- Jun 25, 2008 10:51:13 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Jun 25, 2008 10:51:14 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11914
;

-- Jun 25, 2008 10:51:14 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_TIMESTAMP('2008-06-25 22:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=469
;

-- Jun 25, 2008 10:51:14 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Jun 25, 2008 10:51:14 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=720, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11908
;

-- Jun 25, 2008 10:51:15 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Tab SET AD_Table_ID=720, AD_Window_ID=328, CommitWarning=NULL, Description='Material Forecast', EntityType='D', HasTree='N', Help='Material Forecast', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Forecast', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-06-25 22:51:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=653
;

-- Jun 25, 2008 10:51:15 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=653
;

-- Jun 25, 2008 10:51:15 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11918, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Material Forecast', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Material Forecast', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Forecast', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10300
;

-- Jun 25, 2008 10:51:15 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11910, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10293
;

-- Jun 25, 2008 10:51:16 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11917, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10299
;

-- Jun 25, 2008 10:51:16 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11908, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=40, SortNo=1,Updated=TO_TIMESTAMP('2008-06-25 22:51:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10291
;

-- Jun 25, 2008 10:51:16 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11911, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10294
;

-- Jun 25, 2008 10:51:17 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11905, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Comment or Hint', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The Help field contains a hint, comment or help about the use of this item.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Comment/Help', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10290
;

-- Jun 25, 2008 10:51:17 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11913, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10296
;

-- Jun 25, 2008 10:51:17 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11915, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Default value', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Default Checkbox indicates if this record will be used as a default value.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Default', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10297
;

-- Jun 25, 2008 10:51:18 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11912, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Accounting Calendar Name', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Calendar', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10295
;

-- Jun 25, 2008 10:51:18 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11909, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description='Calendar Year', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Year uniquely identifies an accounting year for a calendar.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Year', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10292
;

-- Jun 25, 2008 10:51:18 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11916, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=653, AD_Val_Rule_ID=NULL, Description=NULL, DisplayLength=23, DisplayLogic=NULL, EntityType='D', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Process Now', SeqNo=110, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10298
;

-- Jun 25, 2008 10:51:18 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=10298
;

-- Jun 25, 2008 10:51:19 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Table SET AD_Window_ID=328, AccessLevel='3', Description='Forecast Line', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Forecast Line', ReplicationType='L', TableName='M_ForecastLine',Updated=TO_TIMESTAMP('2008-06-25 22:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=722
;

-- Jun 25, 2008 10:51:19 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11933
;

-- Jun 25, 2008 10:51:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11937
;

-- Jun 25, 2008 10:51:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='QtyCalculated', Description='Calculated Quantity', EntityType='D', Help=NULL, IsActive='Y', Name='Calculated Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Calculated Qty',Updated=TO_TIMESTAMP('2008-06-25 22:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2500
;

-- Jun 25, 2008 10:51:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2500
;

-- Jun 25, 2008 10:51:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Quantity data type', EntityType='D', Help=NULL, IsActive='Y', Name='Quantity', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=29
;

-- Jun 25, 2008 10:51:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=29
;

-- Jun 25, 2008 10:51:21 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=2500, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='QtyCalculated', ColumnSQL=NULL, DefaultValue=NULL, Description='Calculated Quantity', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Calculated Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11959
;

-- Jun 25, 2008 10:51:21 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET Name='Calculated Quantity', Description='Calculated Quantity', Help=NULL WHERE AD_Column_ID=11959 AND IsCentrallyMaintained='Y'
;

-- Jun 25, 2008 10:51:22 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56113,1063,0,18,722,'SalesRep_ID',TO_TIMESTAMP('2008-06-25 22:51:21','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE01',22,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','Y','Sales Representative',TO_TIMESTAMP('2008-06-25 22:51:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:22 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56113 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:22 PM CDT
-- Default comment for updating dictionary
ALTER TABLE M_ForecastLine ADD COLUMN SalesRep_ID NUMERIC(10)
;

-- Jun 25, 2008 10:51:22 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11931
;

-- Jun 25, 2008 10:51:23 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11935
;

-- Jun 25, 2008 10:51:23 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11932
;

-- Jun 25, 2008 10:51:24 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11936
;

-- Jun 25, 2008 10:51:24 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11940
;

-- Jun 25, 2008 10:51:24 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='M_ForecastLine_ID', Description='Forecast Line', EntityType='D', Help='Forecast of Product Qyantity by Period', IsActive='Y', Name='Forecast Line', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Forecast Line',Updated=TO_TIMESTAMP('2008-06-25 22:51:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2499
;

-- Jun 25, 2008 10:51:24 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2499
;

-- Jun 25, 2008 10:51:25 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=2499, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_ForecastLine_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Forecast Line', EntityType='D', FieldLength=22, Help='Forecast of Product Qyantity by Period', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast Line', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11942
;

-- Jun 25, 2008 10:51:25 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=2498, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Forecast_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Material Forecast', EntityType='D', FieldLength=22, Help='Material Forecast', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Forecast', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11939
;

-- Jun 25, 2008 10:51:25 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Qty', Description='Quantity', EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', IsActive='Y', Name='Quantity', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Qty',Updated=TO_TIMESTAMP('2008-06-25 22:51:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=526
;

-- Jun 25, 2008 10:51:25 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=526
;

-- Jun 25, 2008 10:51:26 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=526, AD_Process_ID=NULL, AD_Reference_ID=29, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Qty', ColumnSQL=NULL, DefaultValue=NULL, Description='Quantity', EntityType='D', FieldLength=22, Help='The Quantity indicates the number of a specific product or item for this document.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Quantity', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11938
;

-- Jun 25, 2008 10:51:26 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='C_Period_ID', Description='Period of the Calendar', EntityType='D', Help='The Period indicates an exclusive range of dates for a calendar.', IsActive='Y', Name='Period', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Period',Updated=TO_TIMESTAMP('2008-06-25 22:51:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=206
;

-- Jun 25, 2008 10:51:26 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=206
;

-- Jun 25, 2008 10:51:26 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Val_Rule SET Code='C_Period.C_Year_ID=@C_Year_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='C_Period of Year', Type='S',Updated=TO_TIMESTAMP('2008-06-25 22:51:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=199
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=206, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=199, Callout=NULL, ColumnName='C_Period_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Period of the Calendar', EntityType='D', FieldLength=22, Help='The Period indicates an exclusive range of dates for a calendar.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Period', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11934
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='M_Product_ID', Description='Product, Service, Item', EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', IsActive='Y', Name='Product', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product',Updated=TO_TIMESTAMP('2008-06-25 22:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=454
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=454
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Val_Rule SET Code='M_Product.IsSummary=''N'' AND M_Product.IsActive=''Y''', Description=NULL, EntityType='D', IsActive='Y', Name='M_Product (Trx)', Type='S',Updated=TO_TIMESTAMP('2008-06-25 22:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=231
;

-- Jun 25, 2008 10:51:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=454, AD_Process_ID=NULL, AD_Reference_ID=30, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=231, Callout=NULL, ColumnName='M_Product_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Product, Service, Item', EntityType='D', FieldLength=22, Help='Identifies an item which is either purchased or sold in this organization.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Product', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=11941
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='M_Warehouse_ID', Description='Storage Warehouse and Service Point', EntityType='D', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', IsActive='Y', Name='Warehouse', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Warehouse',Updated=TO_TIMESTAMP('2008-06-25 22:51:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=459
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=459
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=459, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='M_Warehouse_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Storage Warehouse and Service Point', EntityType='EE01', FieldLength=22, Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Warehouse', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53411
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='DatePromised', Description='Date Order was promised', EntityType='D', Help='The Date Promised indicates the date, if any, that an Order was promised for.', IsActive='Y', Name='Date Promised', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Date Promised',Updated=TO_TIMESTAMP('2008-06-25 22:51:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=269
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=269
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Date mm/dd/yyyy', EntityType='D', Help=NULL, IsActive='Y', Name='Date', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=15
;

-- Jun 25, 2008 10:51:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=15
;

-- Jun 25, 2008 10:51:29 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Column SET AD_Element_ID=269, AD_Process_ID=NULL, AD_Reference_ID=15, AD_Reference_Value_ID=NULL, AD_Table_ID=722, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DatePromised', ColumnSQL=NULL, DefaultValue=NULL, Description='Date Order was promised', EntityType='EE01', FieldLength=7, Help='The Date Promised indicates the date, if any, that an Order was promised for.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Date Promised', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53412
;

-- Jun 25, 2008 10:51:29 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Tab SET AD_Table_ID=722, AD_Window_ID=328, CommitWarning=NULL, Description='Forecast Line', EntityType='D', HasTree='N', Help='Forecast of Product Qyantity by Period', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Line', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2008-06-25 22:51:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=654
;

-- Jun 25, 2008 10:51:29 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=654
;

-- Jun 25, 2008 10:51:30 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11942, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Forecast Line', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Forecast of Product Qyantity by Period', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Forecast Line', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10302
;

-- Jun 25, 2008 10:51:30 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11935, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10304
;

-- Jun 25, 2008 10:51:30 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11937, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10305
;

-- Jun 25, 2008 10:51:31 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11939, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Material Forecast', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Material Forecast', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Forecast', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10307
;

-- Jun 25, 2008 10:51:31 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11940, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10308
;

-- Jun 25, 2008 10:51:31 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11934, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Period of the Calendar', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Period indicates an exclusive range of dates for a calendar.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Period', SeqNo=50, SortNo=1,Updated=TO_TIMESTAMP('2008-06-25 22:51:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10303
;

-- Jun 25, 2008 10:51:32 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=53411, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Storage Warehouse and Service Point', DisplayLength=22, DisplayLogic=NULL, EntityType='EE01', Help='The Warehouse identifies a unique Warehouse where products are stored or Services are provided.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Warehouse', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53638
;

-- Jun 25, 2008 10:51:32 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=53412, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Date Order was promised', DisplayLength=7, DisplayLogic=NULL, EntityType='EE01', Help='The Date Promised indicates the date, if any, that an Order was promised for.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Date Promised', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=53639
;

-- Jun 25, 2008 10:51:32 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11941, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Product, Service, Item', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='Identifies an item which is either purchased or sold in this organization.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Product', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10301
;

-- Jun 25, 2008 10:51:32 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11938, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Quantity', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The Quantity indicates the number of a specific product or item for this document.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Quantity', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10306
;

-- Jun 25, 2008 10:51:33 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET AD_Column_ID=11959, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=654, AD_Val_Rule_ID=NULL, Description='Calculated Quantity', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Calculated Quantity', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2008-06-25 22:51:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=10310
;

-- Jun 25, 2008 10:51:33 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=10310
;

-- Jun 25, 2008 10:51:34 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53142,'3',TO_TIMESTAMP('2008-06-25 22:51:33','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','N','N','N','N','N','RV_M_Forecast','L','RV_M_Forecast',TO_TIMESTAMP('2008-06-25 22:51:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:51:34 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53142 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Jun 25, 2008 10:51:34 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53163,TO_TIMESTAMP('2008-06-25 22:51:34','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table RV_M_Forecast',1,'Y','N','Y','Y','RV_M_Forecast','N',1000000,TO_TIMESTAMP('2008-06-25 22:51:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:51:35 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53021,53142,TO_TIMESTAMP('2008-06-25 22:51:33','YYYY-MM-DD HH24:MI:SS'),0,'Forecast View','EE01','Y','RV_M_Forecast',TO_TIMESTAMP('2008-06-25 22:51:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:51:35 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Table SET AD_Window_ID=NULL, AccessLevel='3', Description=NULL, EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='Forecast Report', ReplicationType='L', TableName='RV_M_Forecast',Updated=TO_TIMESTAMP('2008-06-25 22:51:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53142
;

-- Jun 25, 2008 10:51:35 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53142
;

-- Jun 25, 2008 10:51:36 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,56114,469,0,10,53142,'Name',TO_TIMESTAMP('2008-06-25 22:51:35','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE01',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','N','N','N','Y','N','N','Name',1,TO_TIMESTAMP('2008-06-25 22:51:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:36 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56114 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:36 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56115,113,0,19,53142,'AD_Org_ID',TO_TIMESTAMP('2008-06-25 22:51:36','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-06-25 22:51:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:36 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56115 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:37 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56116,190,0,19,53142,'C_Calendar_ID',TO_TIMESTAMP('2008-06-25 22:51:36','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Calendar Name','EE01',10,'The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.','Y','N','N','N','N','N','N','N','Y','N','N','Calendar',TO_TIMESTAMP('2008-06-25 22:51:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:37 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56116 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:37 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56117,206,0,19,53142,'C_Period_ID',TO_TIMESTAMP('2008-06-25 22:51:37','YYYY-MM-DD HH24:MI:SS'),0,'Period of the Calendar','EE01',10,'The Period indicates an exclusive range of dates for a calendar.','Y','N','N','N','N','N','N','N','Y','N','N','Period',TO_TIMESTAMP('2008-06-25 22:51:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:37 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56117 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:38 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='C_UOM_ID', Description='Unit of Measure', EntityType='D', Help='The UOM defines a unique non monetary Unit of Measure', IsActive='Y', Name='UOM', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='UOM',Updated=TO_TIMESTAMP('2008-06-25 22:51:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=215
;

-- Jun 25, 2008 10:51:38 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=215
;

-- Jun 25, 2008 10:51:38 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56118,215,0,19,53142,'C_UOM_ID',TO_TIMESTAMP('2008-06-25 22:51:38','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','N','UOM',TO_TIMESTAMP('2008-06-25 22:51:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:38 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56118 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:39 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56119,223,0,19,53142,'C_Year_ID',TO_TIMESTAMP('2008-06-25 22:51:38','YYYY-MM-DD HH24:MI:SS'),0,'Calendar Year','EE01',10,'The Year uniquely identifies an accounting year for a calendar.','Y','N','N','N','N','N','N','N','Y','N','N','Year',TO_TIMESTAMP('2008-06-25 22:51:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:39 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56119 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:39 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Classification', Description='Classification for grouping', EntityType='D', Help='The Classification can be used to optionally group products.', IsActive='Y', Name='Classification', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Classification',Updated=TO_TIMESTAMP('2008-06-25 22:51:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=852
;

-- Jun 25, 2008 10:51:39 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=852
;

-- Jun 25, 2008 10:51:39 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56120,852,0,10,53142,'Classification',TO_TIMESTAMP('2008-06-25 22:51:39','YYYY-MM-DD HH24:MI:SS'),0,'Classification for grouping','EE01',12,'The Classification can be used to optionally group products.','Y','N','N','N','N','N','N','N','Y','N','N','Classification',TO_TIMESTAMP('2008-06-25 22:51:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:39 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56120 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:40 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56121,269,0,16,53142,'DatePromised',TO_TIMESTAMP('2008-06-25 22:51:39','YYYY-MM-DD HH24:MI:SS'),0,'Date Order was promised','EE01',29,'The Date Promised indicates the date, if any, that an Order was promised for.','Y','N','N','N','N','N','N','N','Y','N','N','Date Promised',TO_TIMESTAMP('2008-06-25 22:51:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:40 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56121 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:40 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Group1', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Group1', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Group1',Updated=TO_TIMESTAMP('2008-06-25 22:51:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52018
;

-- Jun 25, 2008 10:51:40 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52018
;

-- Jun 25, 2008 10:51:41 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56122,52018,0,10,53142,'Group1',TO_TIMESTAMP('2008-06-25 22:51:40','YYYY-MM-DD HH24:MI:SS'),0,'EE01',255,'Y','N','N','N','N','N','N','N','Y','N','N','Group1',TO_TIMESTAMP('2008-06-25 22:51:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:41 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56122 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:41 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='Group2', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Group2', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Group2',Updated=TO_TIMESTAMP('2008-06-25 22:51:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=52019
;

-- Jun 25, 2008 10:51:41 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=52019
;

-- Jun 25, 2008 10:51:41 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56123,52019,0,10,53142,'Group2',TO_TIMESTAMP('2008-06-25 22:51:41','YYYY-MM-DD HH24:MI:SS'),0,'EE01',255,'Y','N','N','N','N','N','N','N','Y','N','N','Group2',TO_TIMESTAMP('2008-06-25 22:51:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:41 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56123 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:42 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56124,2498,0,19,53142,'M_Forecast_ID',TO_TIMESTAMP('2008-06-25 22:51:41','YYYY-MM-DD HH24:MI:SS'),0,'Material Forecast','EE01',10,'Material Forecast','Y','N','N','N','N','N','N','N','Y','N','N','Forecast',TO_TIMESTAMP('2008-06-25 22:51:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:42 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56124 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:42 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='M_Product_Category_ID', Description='Category of a Product', EntityType='D', Help='Identifies the category which this product belongs to.  Product categories are used for pricing and selection.', IsActive='Y', Name='Product Category', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Product Category',Updated=TO_TIMESTAMP('2008-06-25 22:51:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=453
;

-- Jun 25, 2008 10:51:42 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=453
;

-- Jun 25, 2008 10:51:42 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56125,453,0,19,53142,'M_Product_Category_ID',TO_TIMESTAMP('2008-06-25 22:51:42','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','N','Product Category',TO_TIMESTAMP('2008-06-25 22:51:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:42 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56125 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:43 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56126,454,0,19,53142,'M_Product_ID',TO_TIMESTAMP('2008-06-25 22:51:42','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Product',TO_TIMESTAMP('2008-06-25 22:51:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:43 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56126 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='PriceLimit', Description='Lowest price for a product', EntityType='D', Help='The Price Limit indicates the lowest price for a product stated in the Price List Currency.', IsActive='Y', Name='Limit Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Limit Price',Updated=TO_TIMESTAMP('2008-06-25 22:51:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=955
;

-- Jun 25, 2008 10:51:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=955
;

-- Jun 25, 2008 10:51:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:51:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=22
;

-- Jun 25, 2008 10:51:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56127,955,0,22,53142,'PriceLimit',TO_TIMESTAMP('2008-06-25 22:51:43','YYYY-MM-DD HH24:MI:SS'),0,'Lowest price for a product','EE01',131089,'The Price Limit indicates the lowest price for a product stated in the Price List Currency.','Y','N','N','N','N','N','N','N','Y','N','N','Limit Price',TO_TIMESTAMP('2008-06-25 22:51:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56127 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='PriceList', Description='List Price', EntityType='D', Help='The List Price is the official List Price in the document currency.', IsActive='Y', Name='List Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='List Price',Updated=TO_TIMESTAMP('2008-06-25 22:51:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=520
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=520
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56128,520,0,22,53142,'PriceList',TO_TIMESTAMP('2008-06-25 22:51:44','YYYY-MM-DD HH24:MI:SS'),0,'List Price','EE01',131089,'The List Price is the official List Price in the document currency.','Y','N','N','N','N','N','N','N','Y','N','N','List Price',TO_TIMESTAMP('2008-06-25 22:51:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56128 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:44 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element SET ColumnName='PriceStd', Description='Standard Price', EntityType='D', Help='The Standard Price indicates the standard or normal price for a product on this price list', IsActive='Y', Name='Standard Price', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Std Price',Updated=TO_TIMESTAMP('2008-06-25 22:51:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=957
;

-- Jun 25, 2008 10:51:45 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=957
;

-- Jun 25, 2008 10:51:45 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56129,957,0,22,53142,'PriceStd',TO_TIMESTAMP('2008-06-25 22:51:45','YYYY-MM-DD HH24:MI:SS'),0,'Standard Price','EE01',131089,'The Standard Price indicates the standard or normal price for a product on this price list','Y','N','N','N','N','N','N','N','Y','N','N','Standard Price',TO_TIMESTAMP('2008-06-25 22:51:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:45 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56129 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:45 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56130,526,0,29,53142,'Qty',TO_TIMESTAMP('2008-06-25 22:51:45','YYYY-MM-DD HH24:MI:SS'),0,'Quantity','EE01',131089,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_TIMESTAMP('2008-06-25 22:51:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:46 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56130 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:46 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56131,2500,0,29,53142,'QtyCalculated',TO_TIMESTAMP('2008-06-25 22:51:46','YYYY-MM-DD HH24:MI:SS'),0,'Calculated Quantity','EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','Calculated Quantity',TO_TIMESTAMP('2008-06-25 22:51:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:46 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56131 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:46 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Sales Representative', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User - SalesRep', ValidationType='T',Updated=TO_TIMESTAMP('2008-06-25 22:51:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=190
;

-- Jun 25, 2008 10:51:46 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=190
;

-- Jun 25, 2008 10:51:46 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = 'EXISTS (SELECT * FROM C_BPartner bp WHERE AD_User.C_BPartner_ID=bp.C_BPartner_ID AND bp.IsSalesRep=''Y'')
' WHERE AD_Reference_ID = 190
;

-- Jun 25, 2008 10:51:47 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56132,1063,0,18,190,53142,'SalesRep_ID',TO_TIMESTAMP('2008-06-25 22:51:46','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE01',10,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','N','Sales Representative',TO_TIMESTAMP('2008-06-25 22:51:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:47 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56132 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:48 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56133,102,0,19,53142,'AD_Client_ID',TO_TIMESTAMP('2008-06-25 22:51:47','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-06-25 22:51:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:48 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56133 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:49 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53653,0,'total',TO_TIMESTAMP('2008-06-25 22:51:48','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','total','total',TO_TIMESTAMP('2008-06-25 22:51:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:51:49 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53653 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 25, 2008 10:51:49 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56134,53653,0,22,53142,'total',TO_TIMESTAMP('2008-06-25 22:51:49','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','total',TO_TIMESTAMP('2008-06-25 22:51:49','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:51:49 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56134 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:51:50 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,130,50032,100,53021,53142,NULL,TO_TIMESTAMP('2008-06-25 22:51:49','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y','N','N','Y','Y','Forecast Report',TO_TIMESTAMP('2008-06-25 22:51:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:51:50 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56133,0,50788,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:50','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Compañía','C','F','Compañía',0,0,'N',0,TO_TIMESTAMP('2008-06-25 22:51:50','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:51 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50788 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:51 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56115,0,50789,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:51','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organización','C','F','Organización',0,0,'N',0,TO_TIMESTAMP('2008-06-25 22:51:51','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:51 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50789 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:52 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56114,0,50790,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:51','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','Y','N','Y','Y','N','N','N','N','N','X',1,0,0,'Nombre','C','F','Nombre',0,1,'N',1,TO_TIMESTAMP('2008-06-25 22:51:51','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:52 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50790 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:53 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56131,0,50791,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:52','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Cantidad Calculada','C','F','Cantidad Calculada',0,2,'N',0,TO_TIMESTAMP('2008-06-25 22:51:52','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:53 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50791 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:53 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56116,0,50792,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:53','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Calendario','C','F','Calendario',0,3,'N',0,TO_TIMESTAMP('2008-06-25 22:51:53','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:53 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50792 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:53 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56120,0,50793,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:53','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Línea de Producto','C','F','Línea de Producto',0,4,'N',0,TO_TIMESTAMP('2008-06-25 22:51:53','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:53 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50793 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:54 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56121,0,50794,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:54','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Fecha Prometida','C','F','F. Prometida',0,6,'N',0,TO_TIMESTAMP('2008-06-25 22:51:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:54 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50794 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:54 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56124,0,50795,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:54','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Pronóstico','C','F','Pronóstico',0,7,'N',0,TO_TIMESTAMP('2008-06-25 22:51:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:54 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50795 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56122,0,50796,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:54','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Group1','C','F','Group1',0,8,'N',0,TO_TIMESTAMP('2008-06-25 22:51:54','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50796 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56123,0,50797,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:55','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Group2','C','F','Group2',0,9,'N',0,TO_TIMESTAMP('2008-06-25 22:51:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50797 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:56 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56127,0,50798,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:55','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Precio Límite','C','F','Precio Límite',0,10,'N',0,TO_TIMESTAMP('2008-06-25 22:51:55','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:56 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50798 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:56 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56128,0,50799,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:56','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Precio de Lista','C','F','Precio de Lista',0,11,'N',0,TO_TIMESTAMP('2008-06-25 22:51:56','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:56 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50799 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:57 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56117,0,50800,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:56','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Período','C','F','Período',0,13,'N',0,TO_TIMESTAMP('2008-06-25 22:51:56','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:57 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50800 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:57 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56126,0,50801,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:57','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Producto','C','F','Producto',0,14,'N',0,TO_TIMESTAMP('2008-06-25 22:51:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:57 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50801 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:58 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56125,0,50802,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:57','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Categoría del Producto','C','F','Categoría del Producto',0,15,'N',0,TO_TIMESTAMP('2008-06-25 22:51:57','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:58 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50802 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:58 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56130,0,50803,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:58','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Cantidad','C','F','Cantidad',0,16,'N',0,TO_TIMESTAMP('2008-06-25 22:51:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:58 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50803 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:59 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56129,0,50804,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:58','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Precio Estándar','C','F','Precio Estándar',0,17,'N',0,TO_TIMESTAMP('2008-06-25 22:51:58','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:59 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50804 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:51:59 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56118,0,50805,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:59','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UM','C','F','UM',0,18,'N',0,TO_TIMESTAMP('2008-06-25 22:51:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:51:59 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50805 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:00 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56119,0,50806,50032,0,0,TO_TIMESTAMP('2008-06-25 22:51:59','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Año','C','F','Año',0,19,'N',0,TO_TIMESTAMP('2008-06-25 22:51:59','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:00 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50806 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:00 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56134,0,50807,50032,0,0,TO_TIMESTAMP('2008-06-25 22:52:00','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'total','C','F','total',0,20,'N',0,TO_TIMESTAMP('2008-06-25 22:52:00','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:00 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50807 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:01 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53144,53021,'3',TO_TIMESTAMP('2008-06-25 22:52:00','YYYY-MM-DD HH24:MI:SS'),0,'Forecast Report','EE01','Forecast Report','Y','N','N','Y','Forecast Report','Y',0,0,TO_TIMESTAMP('2008-06-25 22:52:00','YYYY-MM-DD HH24:MI:SS'),0,'RV_M_Forecast',NULL)
;

-- Jun 25, 2008 10:52:01 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53144 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jun 25, 2008 10:52:02 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,409,0,53144,53186,18,190,'SalesRep_ID',TO_TIMESTAMP('2008-06-25 22:52:01','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','SalesRep_ID',60,TO_TIMESTAMP('2008-06-25 22:52:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:02 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53186 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:02 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,269,0,53144,53187,15,'DatePromised',TO_TIMESTAMP('2008-06-25 22:52:02','YYYY-MM-DD HH24:MI:SS'),0,'EE01',8,'Y','Y','N','Y','DatePromised',80,TO_TIMESTAMP('2008-06-25 22:52:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:02 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53187 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:03 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53144,53188,19,'M_Product_Category_ID',TO_TIMESTAMP('2008-06-25 22:52:02','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','M_Product_Category_ID',50,TO_TIMESTAMP('2008-06-25 22:52:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:03 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53188 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:03 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53144,53189,19,'M_Product_ID',TO_TIMESTAMP('2008-06-25 22:52:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','M_Product_ID',70,TO_TIMESTAMP('2008-06-25 22:52:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:03 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53189 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:03 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Val_Rule SET Code='EXISTS (SELECT * FROM C_Year y WHERE C_Period.C_Year_ID=y.C_Year_ID AND y.C_Calendar_ID=@C_Calendar_ID@)', Description=NULL, EntityType='D', IsActive='Y', Name='C_Period of Calendar', Type='S',Updated=TO_TIMESTAMP('2008-06-25 22:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=217
;

-- Jun 25, 2008 10:52:04 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,206,0,53144,53190,19,217,'C_Period_ID',TO_TIMESTAMP('2008-06-25 22:52:03','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','C_Period_ID',40,TO_TIMESTAMP('2008-06-25 22:52:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:04 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53190 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:04 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,223,0,53144,53191,19,198,'C_Year_ID',TO_TIMESTAMP('2008-06-25 22:52:04','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','C_Year_ID',30,TO_TIMESTAMP('2008-06-25 22:52:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:04 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53191 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:05 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,190,0,53144,53192,19,'C_Calendar_ID',TO_TIMESTAMP('2008-06-25 22:52:04','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','C_Calendar_ID',20,TO_TIMESTAMP('2008-06-25 22:52:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:05 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53192 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:05 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2498,0,53144,53193,19,'M_Forecast_ID',TO_TIMESTAMP('2008-06-25 22:52:05','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','M_Forecast_ID',10,TO_TIMESTAMP('2008-06-25 22:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:05 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53193 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:06 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53143,'3',TO_TIMESTAMP('2008-06-25 22:52:05','YYYY-MM-DD HH24:MI:SS'),0,'U','Y','N','N','N','N','N','RV_M_Forecast_Period','L','RV_M_Forecast_Period',TO_TIMESTAMP('2008-06-25 22:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:06 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53143 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Jun 25, 2008 10:52:07 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53164,TO_TIMESTAMP('2008-06-25 22:52:06','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table RV_M_Forecast_Period',1,'Y','N','Y','Y','RV_M_Forecast_Period','N',1000000,TO_TIMESTAMP('2008-06-25 22:52:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:07 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_ReportView (AD_Client_ID,AD_Org_ID,AD_ReportView_ID,AD_Table_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy) VALUES (0,0,53022,53143,TO_TIMESTAMP('2008-06-25 22:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Forecast for Period View','EE01','Y','RV_M_Forecast_Period',TO_TIMESTAMP('2008-06-25 22:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:07 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Table SET AD_Window_ID=NULL, AccessLevel='3', Description=NULL, EntityType='EE01', Help=NULL, ImportTable='N', IsActive='Y', IsChangeLog='N', IsDeleteable='N', IsHighVolume='N', IsSecurityEnabled='N', IsView='Y', Name='Forecast Report for Period', ReplicationType='L', TableName='RV_M_Forecast_Period',Updated=TO_TIMESTAMP('2008-06-25 22:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53143
;

-- Jun 25, 2008 10:52:07 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Table_Trl SET IsTranslated='N' WHERE AD_Table_ID=53143
;

-- Jun 25, 2008 10:52:08 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56135,102,0,19,53143,'AD_Client_ID',TO_TIMESTAMP('2008-06-25 22:52:07','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.','EE01',10,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','N','N','N','Y','N','N','Client',TO_TIMESTAMP('2008-06-25 22:52:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:08 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56135 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:09 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56136,113,0,19,53143,'AD_Org_ID',TO_TIMESTAMP('2008-06-25 22:52:08','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client','EE01',10,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','N','N','N','Y','N','N','Organization',TO_TIMESTAMP('2008-06-25 22:52:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:09 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56136 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:09 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56137,190,0,19,53143,'C_Calendar_ID',TO_TIMESTAMP('2008-06-25 22:52:09','YYYY-MM-DD HH24:MI:SS'),0,'Accounting Calendar Name','EE01',10,'The Calendar uniquely identifies an accounting calendar.  Multiple calendars can be used.  For example you may need a standard calendar that runs from Jan 1 to Dec 31 and a fiscal calendar that runs from July 1 to June 30.','Y','N','N','N','N','N','N','N','Y','N','N','Calendar',TO_TIMESTAMP('2008-06-25 22:52:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:09 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56137 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:10 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56138,206,0,19,53143,'C_Period_ID',TO_TIMESTAMP('2008-06-25 22:52:09','YYYY-MM-DD HH24:MI:SS'),0,'Period of the Calendar','EE01',10,'The Period indicates an exclusive range of dates for a calendar.','Y','N','N','N','N','N','N','N','Y','N','N','Period',TO_TIMESTAMP('2008-06-25 22:52:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:10 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56138 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:10 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56139,215,0,19,53143,'C_UOM_ID',TO_TIMESTAMP('2008-06-25 22:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure','EE01',10,'The UOM defines a unique non monetary Unit of Measure','Y','N','N','N','N','N','N','N','Y','N','N','UOM',TO_TIMESTAMP('2008-06-25 22:52:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:10 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56139 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:11 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56140,223,0,19,53143,'C_Year_ID',TO_TIMESTAMP('2008-06-25 22:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Calendar Year','EE01',10,'The Year uniquely identifies an accounting year for a calendar.','Y','N','N','N','N','N','N','N','Y','N','N','Year',TO_TIMESTAMP('2008-06-25 22:52:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:11 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56140 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:11 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56141,2498,0,19,53143,'M_Forecast_ID',TO_TIMESTAMP('2008-06-25 22:52:11','YYYY-MM-DD HH24:MI:SS'),0,'Material Forecast','EE01',10,'Material Forecast','Y','N','N','N','N','N','N','N','Y','N','N','Forecast',TO_TIMESTAMP('2008-06-25 22:52:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:11 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56141 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:12 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56142,453,0,19,53143,'M_Product_Category_ID',TO_TIMESTAMP('2008-06-25 22:52:11','YYYY-MM-DD HH24:MI:SS'),0,'Category of a Product','EE01',10,'Identifies the category which this product belongs to.  Product categories are used for pricing and selection.','Y','N','N','N','N','N','N','N','Y','N','N','Product Category',TO_TIMESTAMP('2008-06-25 22:52:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:12 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56142 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:13 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56143,454,0,19,53143,'M_Product_ID',TO_TIMESTAMP('2008-06-25 22:52:12','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item','EE01',10,'Identifies an item which is either purchased or sold in this organization.','Y','N','N','N','N','N','N','N','Y','N','N','Product',TO_TIMESTAMP('2008-06-25 22:52:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:13 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56143 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:13 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56144,526,0,29,53143,'Qty',TO_TIMESTAMP('2008-06-25 22:52:13','YYYY-MM-DD HH24:MI:SS'),0,'Quantity','EE01',131089,'The Quantity indicates the number of a specific product or item for this document.','Y','N','N','N','N','N','N','N','Y','N','N','Quantity',TO_TIMESTAMP('2008-06-25 22:52:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:13 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56144 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:13 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56145,2500,0,29,53143,'QtyCalculated',TO_TIMESTAMP('2008-06-25 22:52:13','YYYY-MM-DD HH24:MI:SS'),0,'Calculated Quantity','EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','Calculated Quantity',TO_TIMESTAMP('2008-06-25 22:52:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:13 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56145 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:14 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56146,1063,0,18,190,53143,'SalesRep_ID',TO_TIMESTAMP('2008-06-25 22:52:14','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent','EE01',10,'The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','N','N','N','N','N','N','N','Y','N','N','Sales Representative',TO_TIMESTAMP('2008-06-25 22:52:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:14 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56146 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:15 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53654,0,'max',TO_TIMESTAMP('2008-06-25 22:52:14','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','max','max',TO_TIMESTAMP('2008-06-25 22:52:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:15 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53654 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Jun 25, 2008 10:52:15 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2008-06-25 22:52:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Jun 25, 2008 10:52:15 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Jun 25, 2008 10:52:16 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56147,53654,0,14,53143,'max',TO_TIMESTAMP('2008-06-25 22:52:15','YYYY-MM-DD HH24:MI:SS'),0,'EE01',2147483647,'Y','N','N','N','N','N','N','N','Y','N','N','max',TO_TIMESTAMP('2008-06-25 22:52:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:16 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56147 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:16 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56148,53653,0,22,53143,'total',TO_TIMESTAMP('2008-06-25 22:52:16','YYYY-MM-DD HH24:MI:SS'),0,'EE01',131089,'Y','N','N','N','N','N','N','N','Y','N','N','total',TO_TIMESTAMP('2008-06-25 22:52:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jun 25, 2008 10:52:16 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56148 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jun 25, 2008 10:52:17 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormat (AD_Client_ID,AD_Org_ID,AD_PrintColor_ID,AD_PrintFont_ID,AD_PrintFormat_ID,AD_PrintPaper_ID,AD_ReportView_ID,AD_Table_ID,CreateCopy,Created,CreatedBy,FooterMargin,HeaderMargin,IsActive,IsDefault,IsForm,IsStandardHeaderFooter,IsTableBased,Name,Updated,UpdatedBy) VALUES (0,0,100,130,50033,100,53022,53143,NULL,TO_TIMESTAMP('2008-06-25 22:52:16','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y','N','N','Y','Y','Forecast Report by Period',TO_TIMESTAMP('2008-06-25 22:52:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:17 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56135,0,50808,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:17','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Client','C','F','Client',0,0,'N',0,TO_TIMESTAMP('2008-06-25 22:52:17','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:17 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50808 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:18 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56136,0,50809,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:17','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','N','Y','N','N','N','N','N','X',1,0,0,'Organization','C','F','Organization',0,0,'N',0,TO_TIMESTAMP('2008-06-25 22:52:17','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:18 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50809 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:18 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56145,0,50810,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:18','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Calculated Quantity','C','F','Calculated Qty',0,1,'N',0,TO_TIMESTAMP('2008-06-25 22:52:18','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:18 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50810 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:19 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56137,0,50811,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:18','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Calendar','C','F','Calendar',0,2,'N',0,TO_TIMESTAMP('2008-06-25 22:52:18','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:19 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50811 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:19 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56141,0,50812,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:19','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Forecast','C','F','Forecast',0,4,'N',0,TO_TIMESTAMP('2008-06-25 22:52:19','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:20 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50812 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:20 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56138,0,50813,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:20','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Period','C','F','Period',0,6,'N',0,TO_TIMESTAMP('2008-06-25 22:52:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:20 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50813 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:21 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56143,0,50814,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:20','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product','C','F','Product',0,7,'N',0,TO_TIMESTAMP('2008-06-25 22:52:20','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:21 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50814 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:21 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56142,0,50815,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:21','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Product Category','C','F','Product Category',0,8,'N',0,TO_TIMESTAMP('2008-06-25 22:52:21','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:21 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50815 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:22 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56144,0,50816,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:21','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Quantity','C','F','Qty',0,9,'N',0,TO_TIMESTAMP('2008-06-25 22:52:21','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:22 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50816 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:22 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56139,0,50817,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:22','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'UOM','C','F','UOM',0,10,'N',0,TO_TIMESTAMP('2008-06-25 22:52:22','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:22 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50817 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:23 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56140,0,50818,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:22','YYYY-MM-DD HH24:MI:SS'),0,'L','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'Year','C','F','Year',0,11,'N',0,TO_TIMESTAMP('2008-06-25 22:52:22','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:23 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50818 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:23 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56147,0,50819,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:23','YYYY-MM-DD HH24:MI:SS'),0,'B','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'max','C','F','max',0,12,'N',0,TO_TIMESTAMP('2008-06-25 22:52:23','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:23 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50819 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:24 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_PrintFormatItem_ID,AD_PrintFormat_ID,ArcDiameter,BelowColumn,Created,CreatedBy,FieldAlignmentType,ImageIsAttached,IsActive,IsAveraged,IsCounted,IsDeviationCalc,IsFixedWidth,IsGroupBy,IsHeightOneLine,IsMaxCalc,IsMinCalc,IsNextLine,IsNextPage,IsOrderBy,IsPageBreak,IsPrinted,IsRelativePosition,IsRunningTotal,IsSetNLPosition,IsSummarized,IsSuppressNull,IsVarianceCalc,LineAlignmentType,LineWidth,MaxHeight,MaxWidth,Name,PrintAreaType,PrintFormatType,PrintName,RunningTotalLines,SeqNo,ShapeType,SortNo,Updated,UpdatedBy,XPosition,XSpace,YPosition,YSpace) VALUES (0,56148,0,50820,50033,0,0,TO_TIMESTAMP('2008-06-25 22:52:23','YYYY-MM-DD HH24:MI:SS'),0,'T','N','Y','N','N','N','N','N','N','N','N','N','N','N','N','Y','Y','N','N','N','N','N','X',1,0,0,'total','C','F','total',0,13,'N',0,TO_TIMESTAMP('2008-06-25 22:52:23','YYYY-MM-DD HH24:MI:SS'),0,0,0,0,0)
;

-- Jun 25, 2008 10:52:24 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_PrintFormatItem_Trl (AD_Language,AD_PrintFormatItem_ID, PrintName,PrintNameSuffix, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_PrintFormatItem_ID, t.PrintName,t.PrintNameSuffix, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_PrintFormatItem t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_PrintFormatItem_ID=50820 AND EXISTS (SELECT * FROM AD_PrintFormatItem_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_PrintFormatItem_ID!=t.AD_PrintFormatItem_ID)
;

-- Jun 25, 2008 10:52:25 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AD_ReportView_ID,AccessLevel,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53146,53022,'3',TO_TIMESTAMP('2008-06-25 22:52:24','YYYY-MM-DD HH24:MI:SS'),0,'Forecast Report by Period','EE01','Forecast Report by Period','Y','N','N','Y','Forecast Report by Period','Y',0,0,TO_TIMESTAMP('2008-06-25 22:52:24','YYYY-MM-DD HH24:MI:SS'),0,'RV_M_Forecast_Period',NULL)
;

-- Jun 25, 2008 10:52:25 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53146 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jun 25, 2008 10:52:26 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Reference_Value_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,409,0,53146,53194,18,190,'SalesRep_ID',TO_TIMESTAMP('2008-06-25 22:52:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','SalesRep_ID',70,TO_TIMESTAMP('2008-06-25 22:52:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:26 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53194 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:27 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,454,0,53146,53195,19,'M_Product_ID',TO_TIMESTAMP('2008-06-25 22:52:26','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','M_Product_ID',60,TO_TIMESTAMP('2008-06-25 22:52:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:27 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53195 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:27 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,453,0,53146,53196,19,'M_Product_Category_ID',TO_TIMESTAMP('2008-06-25 22:52:27','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','M_Product_Category_ID',50,TO_TIMESTAMP('2008-06-25 22:52:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:27 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53196 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:28 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,206,0,53146,53197,19,217,'C_Period_ID',TO_TIMESTAMP('2008-06-25 22:52:27','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','C_Period_ID',40,TO_TIMESTAMP('2008-06-25 22:52:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:28 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53197 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:28 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,223,0,53146,53198,19,198,'C_Year_ID',TO_TIMESTAMP('2008-06-25 22:52:28','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','N','N','C_Year_ID',30,TO_TIMESTAMP('2008-06-25 22:52:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:28 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53198 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:28 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,190,0,53146,53199,19,'C_Calendar_ID',TO_TIMESTAMP('2008-06-25 22:52:28','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','C_Calendar_ID',20,TO_TIMESTAMP('2008-06-25 22:52:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:28 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53199 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:29 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2498,0,53146,53200,19,'M_Forecast_ID',TO_TIMESTAMP('2008-06-25 22:52:29','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','M_Forecast_ID',10,TO_TIMESTAMP('2008-06-25 22:52:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:29 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53200 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:30 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53148,'3','@script:beanshell:',TO_TIMESTAMP('2008-06-25 22:52:29','YYYY-MM-DD HH24:MI:SS'),0,'Calculate Forecast','EE01','Calculate Forecast','Y','N','N','Y','Calculate Forecast','Y',0,0,TO_TIMESTAMP('2008-06-25 22:52:29','YYYY-MM-DD HH24:MI:SS'),0,'M_Forecast Calculate',NULL)
;

-- Jun 25, 2008 10:52:30 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53148 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Jun 25, 2008 10:52:30 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Val_Rule (AD_Client_ID,AD_Org_ID,AD_Val_Rule_ID,Code,Created,CreatedBy,EntityType,IsActive,Name,Type,Updated,UpdatedBy) VALUES (0,0,52028,'EventType=''P''',TO_TIMESTAMP('2008-06-25 22:52:30','YYYY-MM-DD HH24:MI:SS'),0,'D','Y','AD_Rule EventType is Process','S',TO_TIMESTAMP('2008-06-25 22:52:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:31 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,53332,0,53148,53201,19,52028,'AD_Rule_ID',TO_TIMESTAMP('2008-06-25 22:52:30','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','AD_Rule',20,TO_TIMESTAMP('2008-06-25 22:52:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:31 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53201 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:31 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para (AD_Client_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Process_Para_ID,AD_Reference_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsCentrallyMaintained,IsMandatory,IsRange,Name,SeqNo,Updated,UpdatedBy) VALUES (0,2498,0,53148,53202,19,'M_Forecast_ID',TO_TIMESTAMP('2008-06-25 22:52:31','YYYY-MM-DD HH24:MI:SS'),0,'EE01',22,'Y','Y','Y','N','M_Forecast_ID',10,TO_TIMESTAMP('2008-06-25 22:52:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:31 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process_Para t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53202 AND EXISTS (SELECT * FROM AD_Process_Para_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_Para_ID!=t.AD_Process_Para_ID)
;

-- Jun 25, 2008 10:52:32 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53180,0,TO_TIMESTAMP('2008-06-25 22:52:31','YYYY-MM-DD HH24:MI:SS'),0,'EE01','Y','N','N','Y','Forecast Management',TO_TIMESTAMP('2008-06-25 22:52:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:32 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53180 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jun 25, 2008 10:52:32 PM CDT
-- Default comment for updating dictionary
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53029,2, 10, 53180)
;

-- Jun 25, 2008 10:52:32 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TREENODEMM SET Parent_ID = 53180 , SeqNo = 1 WHERE AD_Tree_ID = 10 AND Node_ID = 478
;

-- Jun 25, 2008 10:52:33 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Menu SET AD_Process_ID=53144, "action"='R', Description='Forecast Report', EntityType='EE01', IsActive='Y', IsReadOnly='N', IsSOTrx='N', IsSummary='N', Name='Forecast',Updated=TO_TIMESTAMP('2008-06-25 22:52:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=478
;

-- Jun 25, 2008 10:52:33 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=478
;

-- Jun 25, 2008 10:52:33 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TREENODEMM SET Parent_ID = 53180 , SeqNo = 2 WHERE AD_Tree_ID = 10 AND Node_ID = 478
;

-- Jun 25, 2008 10:52:33 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53181,0,53146,'R',TO_TIMESTAMP('2008-06-25 22:52:33','YYYY-MM-DD HH24:MI:SS'),0,'Forecast per Period','EE01','Y','N','N','N','Forecast per Period',TO_TIMESTAMP('2008-06-25 22:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:33 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53181 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jun 25, 2008 10:52:33 PM CDT
-- Default comment for updating dictionary
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53180,3, 10, 53181)
;

-- Jun 25, 2008 10:52:34 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53182,0,53148,'P',TO_TIMESTAMP('2008-06-25 22:52:33','YYYY-MM-DD HH24:MI:SS'),0,'Calculate Forecast','EE01','Y','N','N','N','Calculate Forecast',TO_TIMESTAMP('2008-06-25 22:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:52:34 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53182 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jun 25, 2008 10:52:34 PM CDT
-- Default comment for updating dictionary
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53180,0, 10, 53182)
;

-- Jun 25, 2008 10:53:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53030
;

-- Jun 25, 2008 10:53:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53048
;

-- Jun 25, 2008 10:53:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53031
;

-- Jun 25, 2008 10:53:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53034
;

-- Jun 25, 2008 10:53:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53180
;

-- Jun 25, 2008 10:53:54 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53043
;

-- Jun 25, 2008 10:53:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53030
;

-- Jun 25, 2008 10:53:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53043
;

-- Jun 25, 2008 10:53:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53048
;

-- Jun 25, 2008 10:53:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53031
;

-- Jun 25, 2008 10:53:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53034
;

-- Jun 25, 2008 10:53:57 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53180
;

-- Jun 25, 2008 10:54:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53030
;

-- Jun 25, 2008 10:54:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53034
;

-- Jun 25, 2008 10:54:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53043
;

-- Jun 25, 2008 10:54:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53048
;

-- Jun 25, 2008 10:54:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53031
;

-- Jun 25, 2008 10:54:01 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53180
;

-- Jun 25, 2008 10:54:04 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53030
;

-- Jun 25, 2008 10:54:04 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53180
;

-- Jun 25, 2008 10:54:04 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53034
;

-- Jun 25, 2008 10:54:04 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53043
;

-- Jun 25, 2008 10:54:04 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53048
;

-- Jun 25, 2008 10:54:04 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53031
;

-- Jun 25, 2008 10:54:06 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53030
;

-- Jun 25, 2008 10:54:06 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53031
;

-- Jun 25, 2008 10:54:06 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53180
;

-- Jun 25, 2008 10:54:06 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53034
;

-- Jun 25, 2008 10:54:06 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53043
;

-- Jun 25, 2008 10:54:06 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53029, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53048
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53182
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=478
;

-- Jun 25, 2008 10:54:43 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53181
;

-- Jun 25, 2008 10:56:40 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Menu SET AD_Process_ID=NULL, "action"='W', Description='Forecast',Updated=TO_TIMESTAMP('2008-06-25 22:56:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=478
;

-- Jun 25, 2008 10:56:40 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Menu_Trl SET IsTranslated='N' WHERE AD_Menu_ID=478
;

-- Jun 25, 2008 10:57:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Process_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53183,0,53144,'R',TO_TIMESTAMP('2008-06-25 22:57:55','YYYY-MM-DD HH24:MI:SS'),0,'Forecast Report','EE01','Y','N','N','N','Forecast Report',TO_TIMESTAMP('2008-06-25 22:57:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:57:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53183 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Jun 25, 2008 10:57:55 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_TreeNodeMM (AD_Client_ID,AD_Org_ID, IsActive,Created,CreatedBy,Updated,UpdatedBy, AD_Tree_ID, Node_ID, Parent_ID, SeqNo) SELECT t.AD_Client_ID,0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0,t.AD_Tree_ID, 53183, 0, 999 FROM AD_Tree t WHERE t.AD_Client_ID=0 AND t.IsActive='Y' AND t.IsAllNodes='Y' AND t.TreeType='MM' AND NOT EXISTS (SELECT * FROM AD_TreeNodeMM e WHERE e.AD_Tree_ID=t.AD_Tree_ID AND Node_ID=53183)
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=218
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=153
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=263
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=166
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=4, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=203
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=5, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=236
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=6, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=183
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=7, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=160
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=8, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=278
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=9, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=345
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=10, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53014
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=11, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53108
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=0, SeqNo=12, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53083
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=0, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53182
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=1, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=478
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=2, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53183
;

-- Jun 25, 2008 10:57:59 PM CDT
-- Default comment for updating dictionary
UPDATE AD_TreeNodeMM SET Parent_ID=53180, SeqNo=3, Updated=CURRENT_TIMESTAMP WHERE AD_Tree_ID=10 AND Node_ID=53181
;

-- Jun 25, 2008 10:59:06 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56113,56277,0,654,TO_TIMESTAMP('2008-06-25 22:59:05','YYYY-MM-DD HH24:MI:SS'),0,'Sales Representative or Company Agent',22,'EE01','The Sales Representative indicates the Sales Rep for this Region.  Any Sales Rep must be a valid internal user.','Y','Y','Y','N','N','N','N','N','Sales Representative',TO_TIMESTAMP('2008-06-25 22:59:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 10:59:06 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56277 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jun 25, 2008 11:01:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=56277
;

-- Jun 25, 2008 11:01:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=10306
;

-- Jun 25, 2008 11:01:20 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10310
;

-- Jun 25, 2008 11:01:27 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-06-25 23:01:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56277
;

-- Jun 25, 2008 11:01:48 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,56112,56278,0,653,TO_TIMESTAMP('2008-06-25 23:01:48','YYYY-MM-DD HH24:MI:SS'),0,'Unique identifier of a Price List',22,'EE01','Price Lists are used to determine the pricing, margin and cost of items purchased or sold.','Y','Y','Y','N','N','N','N','N','Price List',TO_TIMESTAMP('2008-06-25 23:01:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jun 25, 2008 11:01:48 PM CDT
-- Default comment for updating dictionary
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56278 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=10300
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=10,IsDisplayed='Y' WHERE AD_Field_ID=10293
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=20,IsDisplayed='Y' WHERE AD_Field_ID=10299
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=10291
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=10294
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=10290
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=10296
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=10297
;

-- Jun 25, 2008 11:02:11 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=56278
;

-- Jun 25, 2008 11:02:28 PM CDT
-- Default comment for updating dictionary
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2008-06-25 23:02:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56278
;
