SET DEFINE OFF
SET SQLBLANKLINES ON
-- Mar 5, 2008 12:50:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_EntityType (AD_Client_ID,AD_EntityType_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,ModelPackage,Name,Processing,Updated,UpdatedBy,Version) VALUES (0,50003,0,TO_DATE('2008-03-05 00:50:30','YYYY-MM-DD HH24:MI:SS'),0,'Replication Data','EE05','License GPL2, Developer Trifon Trifonov, Coordinator Victor Perez, Sponsor e-Evolution','Y','org.eevolution.model','e-Evolution Replication Data','N',TO_DATE('2008-03-05 00:50:30','YYYY-MM-DD HH24:MI:SS'),0,'1')
;

-- Mar 5, 2008 12:50:47 AM CST
-- Replication Data Functionality
UPDATE AD_Window SET Description='Maintain Data Replication Targets', EntityType='D', Help='Data Replication Target Details. Set up your system completely on the central system, before setting up the replication. Define the Replication target here and export the database and import it on the remote system.<p>
Before(!) entering transactions, Start the Replication Run to set up the remote system.', IsActive='Y', IsBetaFunctionality='N', IsDefault='N', IsSOTrx='Y', Name='Replication', Processing='N', WindowType='M',Updated=TO_DATE('2008-03-05 00:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=284
;

-- Mar 5, 2008 12:50:47 AM CST
-- Replication Data Functionality
UPDATE AD_Table SET AD_Window_ID=284, AccessLevel='6', Description='Data Replication Target', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Replication', ReplicationType='L', TableName='AD_Replication',Updated=TO_DATE('2008-03-05 00:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=605
;

-- Mar 5, 2008 12:50:47 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Mar 5, 2008 12:50:47 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_DATE('2008-03-05 00:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9400
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=9400 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Mar 5, 2008 12:50:48 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Mar 5, 2008 12:50:49 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9398
;

-- Mar 5, 2008 12:50:49 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=9398 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:49 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2133, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_ReplicationStrategy_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Strategy', EntityType='D', FieldLength=22, Help='The Data Replication Strategy determines what and how tables are replicated ', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Replication Strategy', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9396
;

-- Mar 5, 2008 12:50:49 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Strategy', Description='Data Replication Strategy', Help='The Data Replication Strategy determines what and how tables are replicated ' WHERE AD_Column_ID=9396 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2130, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Replication_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Target', EntityType='D', FieldLength=22, Help='Data Replication Target Details. Maintained on the central server.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9391
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication', Description='Data Replication Target', Help='Data Replication Target Details. Maintained on the central server.' WHERE AD_Column_ID=9391 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9395
;

-- Mar 5, 2008 12:50:50 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=9395 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:51 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9403
;

-- Mar 5, 2008 12:50:51 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=9403 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:51 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=1089, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='DateLastRun', ColumnSQL=NULL, DefaultValue=NULL, Description='Date the process was last run.', EntityType='D', FieldLength=7, Help='The Date Last Run indicates the last time that a process was run.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Date last run', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-03-05 00:50:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9620
;

-- Mar 5, 2008 12:50:51 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Date last run', Description='Date the process was last run.', Help='The Date Last Run indicates the last time that a process was run.' WHERE AD_Column_ID=9620 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:51 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Mar 5, 2008 12:50:51 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Mar 5, 2008 12:50:52 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9394
;

-- Mar 5, 2008 12:50:52 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=9394 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:52 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Mar 5, 2008 12:50:52 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Mar 5, 2008 12:50:53 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=326, AD_Process_ID=NULL, AD_Reference_ID=14, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Help', ColumnSQL=NULL, DefaultValue=NULL, Description='Comment or Hint', EntityType='D', FieldLength=2000, Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Comment/Help', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9393
;

-- Mar 5, 2008 12:50:53 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Comment/Help', Description='Comment or Hint', Help='The Help field contains a hint, comment or help about the use of this item.' WHERE AD_Column_ID=9393 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:53 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=1398, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='HostAddress', ColumnSQL=NULL, DefaultValue=NULL, Description='Host Address URL or DNS', EntityType='D', FieldLength=60, Help='The Host Address identifies the URL or DNS of the target host', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Host Address', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9392
;

-- Mar 5, 2008 12:50:53 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Host Address', Description='Host Address URL or DNS', Help='The Host Address identifies the URL or DNS of the target host' WHERE AD_Column_ID=9392 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:53 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Mar 5, 2008 12:50:53 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Mar 5, 2008 12:50:54 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=1399, AD_Process_ID=NULL, AD_Reference_ID=11, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='HostPort', ColumnSQL=NULL, DefaultValue='80', Description='Host Communication Port', EntityType='D', FieldLength=22, Help='The Host Port identifies the port to communicate with the host.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Host port', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9404
;

-- Mar 5, 2008 12:50:54 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Host port', Description='Host Communication Port', Help='The Host Port identifies the port to communicate with the host.' WHERE AD_Column_ID=9404 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:54 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Float Number', EntityType='D', Help=NULL, IsActive='Y', Name='Number', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=22
;

-- Mar 5, 2008 12:50:54 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=22
;

-- Mar 5, 2008 12:50:54 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2155, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IDRangeEnd', ColumnSQL=NULL, DefaultValue='19999999', Description='End if the ID Range used', EntityType='D', FieldLength=22, Help='The ID Range allows to restrict the range of the internally used IDs. Please note that the ID range is NOT enforced.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='ID Range End', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9615
;

-- Mar 5, 2008 12:50:54 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='ID Range End', Description='End if the ID Range used', Help='The ID Range allows to restrict the range of the internally used IDs. Please note that the ID range is NOT enforced.' WHERE AD_Column_ID=9615 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:55 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2156, AD_Process_ID=NULL, AD_Reference_ID=22, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IDRangeStart', ColumnSQL=NULL, DefaultValue='10000000', Description='Start of the ID Range used', EntityType='D', FieldLength=22, Help='The ID Range allows to restrict the range of the internally used IDs. The standard rages are 0-899,999 for the Application Dictionary 900,000-999,999 for Application Dictionary customizations/extensions and > 1,000,000 for client data. The standard system limit is 9,999,999,999 but can easily be extended.  The ID range is on a per table basis.
Please note that the ID range is NOT enforced.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='ID Range Start', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9612
;

-- Mar 5, 2008 12:50:55 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='ID Range Start', Description='Start of the ID Range used', Help='The ID Range allows to restrict the range of the internally used IDs. The standard rages are 0-899,999 for the Application Dictionary 900,000-999,999 for Application Dictionary customizations/extensions and > 1,000,000 for client data. The standard system limit is 9,999,999,999 but can easily be extended.  The ID range is on a per table basis.
Please note that the ID range is NOT enforced.' WHERE AD_Column_ID=9612 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:55 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Mar 5, 2008 12:50:55 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Mar 5, 2008 12:50:55 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9402
;

-- Mar 5, 2008 12:50:55 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=9402 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2152, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsRMIoverHTTP', ColumnSQL=NULL, DefaultValue='Y', Description='Connect to Server via HTTP Tunnel', EntityType='D', FieldLength=1, Help='If selected, the connection to the server is via a HTTP tunnel, otherwise it uses an RMI/JNP connection', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Tunnel via HTTP', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9594
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Tunnel via HTTP', Description='Connect to Server via HTTP Tunnel', Help='If selected, the connection to the server is via a HTTP tunnel, otherwise it uses an RMI/JNP connection' WHERE AD_Column_ID=9594 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9401
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=9401 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=516, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Prefix', ColumnSQL=NULL, DefaultValue=NULL, Description='Prefix before the sequence number', EntityType='D', FieldLength=10, Help='The Prefix indicates the characters to print in front of the document number.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Prefix', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9617
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Prefix', Description='Prefix before the sequence number', Help='The Prefix indicates the characters to print in front of the document number.' WHERE AD_Column_ID=9617 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_DATE('2008-03-05 00:50:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Mar 5, 2008 12:50:56 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Mar 5, 2008 12:50:57 AM CST
-- Replication Data Functionality
UPDATE AD_Process SET AccessLevel='6', Classname='org.compiere.process.ReplicationLocal', Description='Start Replication with Remote Host', EntityType='D', Help=NULL, IsActive='Y', IsBetaFunctionality='Y', IsDirectPrint='N', IsReport='N', JasperReport=NULL, Name='Start Replication Run', ProcedureName=NULL, ShowHelp='Y', Statistic_Count=0, Statistic_Seconds=0, Value='AD_Replication Run', WorkflowValue=NULL,Updated=TO_DATE('2008-03-05 00:50:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=208
;

-- Mar 5, 2008 12:50:57 AM CST
-- Replication Data Functionality
UPDATE AD_Process_Trl SET IsTranslated='N' WHERE AD_Process_ID=208
;

-- Mar 5, 2008 12:50:57 AM CST
-- Replication Data Functionality
UPDATE AD_Process_Para SET AD_Element_ID=2153, AD_Process_ID=208, AD_Reference_ID=20, ColumnName='IsTest', DefaultValue=NULL, DefaultValue2=NULL, Description='Execute in Test Mode', EntityType='D', FieldLength=0, Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsMandatory='N', IsRange='N', Name='Test', SeqNo=20, VFormat=NULL, ValueMax=NULL, ValueMin=NULL,Updated=TO_DATE('2008-03-05 00:50:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_Para_ID=312
;

-- Mar 5, 2008 12:50:57 AM CST
-- Replication Data Functionality
UPDATE AD_Process_Para_Trl SET IsTranslated='N' WHERE AD_Process_Para_ID=312
;

-- Mar 5, 2008 12:50:57 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=524, AD_Process_ID=208, AD_Reference_ID=28, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Processing', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=1, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Now', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9595
;

-- Mar 5, 2008 12:50:57 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Process Now', Description=NULL, Help=NULL WHERE AD_Column_ID=9595 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Client selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Client', ValidationType='T',Updated=TO_DATE('2008-03-05 00:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=129
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=129
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_Table SET AD_Table_ID = 112, AD_Display = 208, AD_Key = 207, isValueDisplayed = 'N', OrderByClause = 'AD_Client.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 129
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2157, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=129, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Remote_Client_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Remote Client to be used to replicate / synchronize data with.', EntityType='D', FieldLength=22, Help='The remote client used for data replication.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Remote Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9616
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Remote Client', Description='Remote Client to be used to replicate / synchronize data with.', Help='The remote client used for data replication.' WHERE AD_Column_ID=9616 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Organization selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Org (all)', ValidationType='T',Updated=TO_DATE('2008-03-05 00:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=276
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=276
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_Table SET AD_Table_ID = 155, AD_Display = 522, AD_Key = 528, isValueDisplayed = 'Y', OrderByClause = 'AD_Org.Value', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 276
;

-- Mar 5, 2008 12:50:58 AM CST
-- Replication Data Functionality
UPDATE AD_Val_Rule SET Code='AD_Org.AD_Client_ID=@Remote_Client_ID@', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Org of Remote Client', Type='S',Updated=TO_DATE('2008-03-05 00:50:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=170
;

-- Mar 5, 2008 12:50:59 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2158, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=276, AD_Table_ID=605, AD_Val_Rule_ID=170, Callout=NULL, ColumnName='Remote_Org_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Remote Organization to be used to replicate / synchronize data with.', EntityType='D', FieldLength=22, Help='The remote organization used for data replication. If not selected, all organizations are replicated/synchronized.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Remote Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9614
;

-- Mar 5, 2008 12:50:59 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Remote Organization', Description='Remote Organization to be used to replicate / synchronize data with.', Help='The remote organization used for data replication. If not selected, all organizations are replicated/synchronized.' WHERE AD_Column_ID=9614 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:50:59 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=579, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Suffix', ColumnSQL=NULL, DefaultValue=NULL, Description='Suffix after the number', EntityType='D', FieldLength=10, Help='The Suffix indicates the characters to append to the document number.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Suffix', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:50:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9613
;

-- Mar 5, 2008 12:50:59 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Suffix', Description='Suffix after the number', Help='The Suffix indicates the characters to append to the document number.' WHERE AD_Column_ID=9613 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:00 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9390
;

-- Mar 5, 2008 12:51:00 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=9390 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:00 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=605, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9397
;

-- Mar 5, 2008 12:51:00 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=9397 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:00 AM CST
-- Replication Data Functionality
UPDATE AD_Tab SET AD_Table_ID=605, AD_Window_ID=284, CommitWarning=NULL, Description='Data Replication Target', EntityType='D', HasTree='N', Help='Data Replication Target Details. Maintained on the central server. Make sure that the IP range is unique for every remote system - Otherwise you will loose data!!', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='N', Name='Replication Target', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_DATE('2008-03-05 00:51:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=521
;

-- Mar 5, 2008 12:51:00 AM CST
-- Replication Data Functionality
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=521
;

-- Mar 5, 2008 12:51:01 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9391, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Data Replication Target', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Data Replication Target Details. Maintained on the central server.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication', SeqNo=10, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7487
;

-- Mar 5, 2008 12:51:02 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9401, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7495
;

-- Mar 5, 2008 12:51:02 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9398, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7493
;

-- Mar 5, 2008 12:51:02 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9397, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=40, SortNo=1,Updated=TO_DATE('2008-03-05 00:51:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7492
;

-- Mar 5, 2008 12:51:03 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9394, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=50, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7490
;

-- Mar 5, 2008 12:51:03 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9393, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Comment or Hint', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Comment/Help', SeqNo=60, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7489
;

-- Mar 5, 2008 12:51:03 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9402, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=70, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7496
;

-- Mar 5, 2008 12:51:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9396, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Data Replication Strategy', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Data Replication Strategy determines what and how tables are replicated ', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication Strategy', SeqNo=80, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7491
;

-- Mar 5, 2008 12:51:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9392, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Host Address URL or DNS', DisplayLength=20, DisplayLogic=NULL, EntityType='D', Help='The Host Address identifies the URL or DNS of the target host', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Host Address', SeqNo=90, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7488
;

-- Mar 5, 2008 12:51:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9594, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Connect to Server via HTTP Tunnel', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='If selected, the connection to the server is via a HTTP tunnel, otherwise it uses an RMI/JNP connection', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Tunnel via HTTP', SeqNo=100, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7835
;

-- Mar 5, 2008 12:51:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9404, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Host Communication Port', DisplayLength=11, DisplayLogic=NULL, EntityType='D', Help='The Host Port identifies the port to communicate with the host.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Host port', SeqNo=110, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7497
;

-- Mar 5, 2008 12:51:05 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9616, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Remote Client to be used to replicate / synchronize data with.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The remote client used for data replication.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Remote Client', SeqNo=120, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8165
;

-- Mar 5, 2008 12:51:05 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9614, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Remote Organization to be used to replicate / synchronize data with.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The remote organization used for data replication. If not selected, all organizations are replicated/synchronized.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Remote Organization', SeqNo=130, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8163
;

-- Mar 5, 2008 12:51:05 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9612, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Start of the ID Range used', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The ID Range allows to restrict the range of the internally used IDs. The standard rages are 0-899,999 for the Application Dictionary 900,000-999,999 for Application Dictionary customizations/extensions and > 1,000,000 for client data. The standard system limit is 9,999,999,999 but can easily be extended.  The ID range is on a per table basis.
Please note that the ID range is NOT enforced.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='ID Range Start', SeqNo=140, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8161
;

-- Mar 5, 2008 12:51:06 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9615, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='End if the ID Range used', DisplayLength=26, DisplayLogic=NULL, EntityType='D', Help='The ID Range allows to restrict the range of the internally used IDs. Please note that the ID range is NOT enforced.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='ID Range End', SeqNo=150, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8164
;

-- Mar 5, 2008 12:51:06 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9617, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Prefix before the sequence number', DisplayLength=20, DisplayLogic=NULL, EntityType='D', Help='The Prefix indicates the characters to print in front of the document number.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Prefix', SeqNo=160, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8166
;

-- Mar 5, 2008 12:51:06 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9613, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Suffix after the number', DisplayLength=20, DisplayLogic=NULL, EntityType='D', Help='The Suffix indicates the characters to append to the document number.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Suffix', SeqNo=170, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8162
;

-- Mar 5, 2008 12:51:07 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9595, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Start Replication with Remote Host', DisplayLength=23, DisplayLogic=NULL, EntityType='D', Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Start Replication Run', SeqNo=180, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7836
;

-- Mar 5, 2008 12:51:07 AM CST
-- Replication Data Functionality
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=7836
;

-- Mar 5, 2008 12:51:07 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9620, AD_FieldGroup_ID=NULL, AD_Tab_ID=521, Description='Date the process was last run.', DisplayLength=20, DisplayLogic=NULL, EntityType='D', Help='The Date Last Run indicates the last time that a process was run.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Date last run', SeqNo=190, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=8169
;

-- Mar 5, 2008 12:51:07 AM CST
-- Replication Data Functionality
UPDATE AD_Table SET AD_Window_ID=284, AccessLevel='6', Description='Data Replication Run', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Replication Run', ReplicationType='L', TableName='AD_Replication_Run',Updated=TO_DATE('2008-03-05 00:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=603
;

-- Mar 5, 2008 12:51:07 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9376
;

-- Mar 5, 2008 12:51:07 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=9376 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:08 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9368
;

-- Mar 5, 2008 12:51:08 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=9368 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:08 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2130, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Replication_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Target', EntityType='D', FieldLength=22, Help='Data Replication Target Details. Maintained on the central server.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9372
;

-- Mar 5, 2008 12:51:08 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication', Description='Data Replication Target', Help='Data Replication Target Details. Maintained on the central server.' WHERE AD_Column_ID=9372 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:09 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2132, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Replication_Run_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Run', EntityType='D', FieldLength=22, Help='Data Replication Run information', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication Run', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9373
;

-- Mar 5, 2008 12:51:09 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Run', Description='Data Replication Run', Help='Data Replication Run information' WHERE AD_Column_ID=9373 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:10 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9369
;

-- Mar 5, 2008 12:51:10 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=9369 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:10 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9374
;

-- Mar 5, 2008 12:51:10 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=9374 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:10 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_DATE('2008-03-05 00:51:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9597
;

-- Mar 5, 2008 12:51:10 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=9597 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:11 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9375
;

-- Mar 5, 2008 12:51:11 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=9375 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:11 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2135, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsReplicated', ColumnSQL=NULL, DefaultValue='N', Description='The data is successfully replicated', EntityType='D', FieldLength=1, Help='The data replication was successful.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replicated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9367
;

-- Mar 5, 2008 12:51:11 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replicated', Description='The data is successfully replicated', Help='The data replication was successful.' WHERE AD_Column_ID=9367 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:12 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9370
;

-- Mar 5, 2008 12:51:12 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=9370 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:12 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9371
;

-- Mar 5, 2008 12:51:12 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=9371 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:13 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=603, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9377
;

-- Mar 5, 2008 12:51:13 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=9377 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:13 AM CST
-- Replication Data Functionality
UPDATE AD_Tab SET AD_Table_ID=603, AD_Window_ID=284, CommitWarning=NULL, Description='Data Replication Run', EntityType='D', HasTree='N', Help='Historic Info', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='N', IsReadOnly='Y', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Replication Run', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_DATE('2008-03-05 00:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=522
;

-- Mar 5, 2008 12:51:13 AM CST
-- Replication Data Functionality
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=522
;

-- Mar 5, 2008 12:51:13 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9373, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='Data Replication Run', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Data Replication Run information', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication Run', SeqNo=10, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7502
;

-- Mar 5, 2008 12:51:13 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9370, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7500
;

-- Mar 5, 2008 12:51:14 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9368, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7499
;

-- Mar 5, 2008 12:51:14 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9372, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='Data Replication Target', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Data Replication Target Details. Maintained on the central server.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Replication', SeqNo=40, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7501
;

-- Mar 5, 2008 12:51:14 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9377, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Name', SeqNo=50, SortNo=1,Updated=TO_DATE('2008-03-05 00:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7504
;

-- Mar 5, 2008 12:51:14 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9597, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=60, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7847
;

-- Mar 5, 2008 12:51:15 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9375, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Active', SeqNo=70, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7503
;

-- Mar 5, 2008 12:51:15 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9367, AD_FieldGroup_ID=NULL, AD_Tab_ID=522, Description='The data is successfully replicated', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The data replication was successful.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Replicated', SeqNo=80, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7498
;

-- Mar 5, 2008 12:51:15 AM CST
-- Replication Data Functionality
UPDATE AD_Table SET AD_Window_ID=284, AccessLevel='6', Description='Data Replication Log Details', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Replication Log', ReplicationType='L', TableName='AD_Replication_Log',Updated=TO_DATE('2008-03-05 00:51:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=604
;

-- Mar 5, 2008 12:51:16 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9378
;

-- Mar 5, 2008 12:51:16 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=9378 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:16 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9382
;

-- Mar 5, 2008 12:51:16 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=9382 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:17 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2134, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_ReplicationTable_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Strategy Table Info', EntityType='D', FieldLength=22, Help='Determines how the table is replicated', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Replication Table', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9383
;

-- Mar 5, 2008 12:51:17 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Table', Description='Data Replication Strategy Table Info', Help='Determines how the table is replicated' WHERE AD_Column_ID=9383 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:17 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9381
;

-- Mar 5, 2008 12:51:17 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=9381 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:18 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9385
;

-- Mar 5, 2008 12:51:18 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=9385 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:18 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9380
;

-- Mar 5, 2008 12:51:18 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=9380 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:18 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9388
;

-- Mar 5, 2008 12:51:18 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=9388 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:19 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9384
;

-- Mar 5, 2008 12:51:19 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=9384 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:19 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2135, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsReplicated', ColumnSQL=NULL, DefaultValue='N', Description='The data is successfully replicated', EntityType='D', FieldLength=1, Help='The data replication was successful.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Replicated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9379
;

-- Mar 5, 2008 12:51:19 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replicated', Description='The data is successfully replicated', Help='The data replication was successful.' WHERE AD_Column_ID=9379 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:19 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2068, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='P_Msg', ColumnSQL=NULL, DefaultValue=NULL, Description=NULL, EntityType='D', FieldLength=2000, Help=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process Message', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9389
;

-- Mar 5, 2008 12:51:19 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Process Message', Description=NULL, Help=NULL WHERE AD_Column_ID=9389 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:20 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2132, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Replication_Run_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Run', EntityType='D', FieldLength=22, Help='Data Replication Run information', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication Run', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9386
;

-- Mar 5, 2008 12:51:20 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Run', Description='Data Replication Run', Help='Data Replication Run information' WHERE AD_Column_ID=9386 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:20 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2131, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=604, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Replication_Log_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Log Details', EntityType='D', FieldLength=22, Help='Data Replication Run Log', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication Log', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9387
;

-- Mar 5, 2008 12:51:20 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Log', Description='Data Replication Log Details', Help='Data Replication Run Log' WHERE AD_Column_ID=9387 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:20 AM CST
-- Replication Data Functionality
UPDATE AD_Tab SET AD_Table_ID=604, AD_Window_ID=284, CommitWarning=NULL, Description='Data Replication Run Log', EntityType='D', HasTree='N', Help='Detail Info', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='N', IsReadOnly='Y', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Run Log', OrderByClause=NULL, Processing='N', SeqNo=30, TabLevel=2, WhereClause=NULL,Updated=TO_DATE('2008-03-05 00:51:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=523
;

-- Mar 5, 2008 12:51:20 AM CST
-- Replication Data Functionality
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=523
;

-- Mar 5, 2008 12:51:21 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9387, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='Data Replication Log Details', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Data Replication Run Log', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication Log', SeqNo=10, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7511
;

-- Mar 5, 2008 12:51:21 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9385, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7509
;

-- Mar 5, 2008 12:51:21 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9382, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7506
;

-- Mar 5, 2008 12:51:22 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9386, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='Data Replication Run', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Data Replication Run information', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Replication Run', SeqNo=40, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7510
;

-- Mar 5, 2008 12:51:22 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9383, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='Data Replication Strategy Table Info', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='Determines how the table is replicated', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Replication Table', SeqNo=50, SortNo=1,Updated=TO_DATE('2008-03-05 00:51:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7507
;

-- Mar 5, 2008 12:51:22 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9384, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Active', SeqNo=60, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7508
;

-- Mar 5, 2008 12:51:22 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9379, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description='The data is successfully replicated', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The data replication was successful.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Replicated', SeqNo=70, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7505
;

-- Mar 5, 2008 12:51:23 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9389, AD_FieldGroup_ID=NULL, AD_Tab_ID=523, Description=NULL, DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Process Message', SeqNo=80, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7512
;

-- Mar 5, 2008 12:51:23 AM CST
-- Replication Data Functionality
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=7512
;

-- Mar 5, 2008 12:51:23 AM CST
-- Replication Data Functionality
UPDATE AD_Window SET Description='Maintain Data Replication Strategy', EntityType='EE05', Help='The Data Replication Strategy determines which tables and how they are replicated. Note that the migration does not syncronize Application Dictionary items.', IsActive='Y', IsBetaFunctionality='Y', IsDefault='N', IsSOTrx='Y', Name='Replication Strategy', Processing='N', WindowType='M',Updated=TO_DATE('2008-03-05 00:51:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Window_ID=285
;

-- Mar 5, 2008 12:51:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53071,'3',TO_DATE('2008-03-05 00:51:23','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Replication Document','L','AD_ReplicationDocument',TO_DATE('2008-03-05 00:51:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53071 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:51:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53088,TO_DATE('2008-03-05 00:51:24','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_ReplicationDocument',1,'Y','N','Y','Y','AD_ReplicationDocument','N',1000000,TO_DATE('2008-03-05 00:51:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53366,0,'AD_ReplicationDocument_ID',TO_DATE('2008-03-05 00:51:24','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','AD_ReplicationDocument_ID','AD_ReplicationDocument_ID',TO_DATE('2008-03-05 00:51:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53366 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:51:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54473,53366,0,13,53071,'AD_ReplicationDocument_ID',TO_DATE('2008-03-05 00:51:24','YYYY-MM-DD HH24:MI:SS'),0,'EE05',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','AD_ReplicationDocument_ID',10,TO_DATE('2008-03-05 00:51:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54473 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:26 AM CST
-- Replication Data Functionality
CREATE TABLE AD_ReplicationDocument (AD_ReplicationDocument_ID NUMBER(10) NOT NULL, CONSTRAINT AD_ReplicationDocument_Key PRIMARY KEY (AD_ReplicationDocument_ID))
;

-- Mar 5, 2008 12:51:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54474,2133,0,13,53071,'AD_ReplicationStrategy_ID',TO_DATE('2008-03-05 00:51:26','YYYY-MM-DD HH24:MI:SS'),0,'Data Replication Strategy','EE05',10,'The Data Replication Strategy determines what and how tables are replicated ','Y','N','N','N','N','Y','Y','N','Y','N','N','Replication Strategy',20,TO_DATE('2008-03-05 00:51:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54474 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:26 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD AD_ReplicationStrategy_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:51:27 AM CST
-- Replication Data Functionality
UPDATE AD_Val_Rule SET Code='AD_Client.AD_Client_ID <> 0', Description=NULL, EntityType='D', IsActive='Y', Name='AD_Client Trx Security validation', Type='S',Updated=TO_DATE('2008-03-05 00:51:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Val_Rule_ID=129
;

-- Mar 5, 2008 12:51:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54475,102,0,19,53071,129,'AD_Client_ID',TO_DATE('2008-03-05 00:51:27','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',30,TO_DATE('2008-03-05 00:51:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54475 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:27 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:51:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54476,113,0,19,53071,104,'AD_Org_ID',TO_DATE('2008-03-05 00:51:27','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',40,TO_DATE('2008-03-05 00:51:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54476 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:28 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:51:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54477,348,0,20,53071,'IsActive',TO_DATE('2008-03-05 00:51:28','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',50,TO_DATE('2008-03-05 00:51:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54477 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:29 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:51:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54478,245,0,16,53071,'Created',TO_DATE('2008-03-05 00:51:29','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',60,TO_DATE('2008-03-05 00:51:29','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54478 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:32 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:51:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54479,246,0,18,110,53071,'CreatedBy',TO_DATE('2008-03-05 00:51:32','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',70,TO_DATE('2008-03-05 00:51:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54479 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:32 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:51:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54480,607,0,16,53071,'Updated',TO_DATE('2008-03-05 00:51:32','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',80,TO_DATE('2008-03-05 00:51:32','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54480 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:33 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:51:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54481,608,0,18,110,53071,'UpdatedBy',TO_DATE('2008-03-05 00:51:33','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',90,TO_DATE('2008-03-05 00:51:33','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54481 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:34 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:51:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54482,275,0,10,53071,'Description',TO_DATE('2008-03-05 00:51:34','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',100,TO_DATE('2008-03-05 00:51:34','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54482 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:35 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:51:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54483,196,0,19,53071,'C_DocType_ID',TO_DATE('2008-03-05 00:51:35','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules','EE05',10,'The Document Type determines document sequence and processing rules','Y','N','N','N','N','Y','N','N','Y','N','Y','Document Type',110,TO_DATE('2008-03-05 00:51:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54483 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:35 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD C_DocType_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:51:35 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_DATE('2008-03-05 00:51:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Mar 5, 2008 12:51:35 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Mar 5, 2008 12:51:36 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Replication Type', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Table Replication Type', ValidationType='L',Updated=TO_DATE('2008-03-05 00:51:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=126
;

-- Mar 5, 2008 12:51:36 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=126
;

-- Mar 5, 2008 12:51:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54484,2137,0,17,126,53071,'ReplicationType',TO_DATE('2008-03-05 00:51:36','YYYY-MM-DD HH24:MI:SS'),0,'Type of Data Replication','EE05',1,'The Type of data Replication determines the directon of the data replication.  <br>
Reference means that the data in this system is read only -> <br>
Local means that the data in this system is not replicated to other systems - <br>
Merge means that the data in this system is synchronized with the other system <-> <br>','Y','N','N','N','N','Y','N','N','Y','N','Y','Replication Type',120,TO_DATE('2008-03-05 00:51:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54484 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:37 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD ReplicationType CHAR(1) NOT NULL
;

-- Mar 5, 2008 12:51:37 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Search Field', EntityType='D', Help=NULL, IsActive='Y', Name='Search', ValidationType='D',Updated=TO_DATE('2008-03-05 00:51:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=30
;

-- Mar 5, 2008 12:51:37 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=30
;

-- Mar 5, 2008 12:51:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54485,126,0,30,53071,'AD_Table_ID',TO_DATE('2008-03-05 00:51:37','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information','EE05',10,'The Database Table provides the information of the table definition','Y','N','N','N','N','Y','N','N','Y','N','Y','Table',130,TO_DATE('2008-03-05 00:51:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54485 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:38 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationDocument ADD AD_Table_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:51:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53084,53071,285,NULL,TO_DATE('2008-03-05 00:51:38','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Replication Document','N',30,1,TO_DATE('2008-03-05 00:51:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53084 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:51:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54473,54560,0,53084,TO_DATE('2008-03-05 00:51:38','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','AD_ReplicationDocument_ID',0,0,TO_DATE('2008-03-05 00:51:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54560 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54475,54561,0,53084,TO_DATE('2008-03-05 00:51:39','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:51:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54561 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54476,54562,0,53084,TO_DATE('2008-03-05 00:51:40','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:51:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54562 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54474,54563,0,53084,TO_DATE('2008-03-05 00:51:40','YYYY-MM-DD HH24:MI:SS'),0,'Data Replication Strategy',0,'EE05','The Data Replication Strategy determines what and how tables are replicated ','Y','Y','Y','N','N','N','N','Replication Strategy',30,0,TO_DATE('2008-03-05 00:51:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54563 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54483,54564,0,53084,TO_DATE('2008-03-05 00:51:41','YYYY-MM-DD HH24:MI:SS'),0,'Document type or rules',22,'EE05','The Document Type determines document sequence and processing rules','Y','Y','Y','N','N','N','N','Document Type',40,0,TO_DATE('2008-03-05 00:51:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54564 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54485,54565,0,53084,TO_DATE('2008-03-05 00:51:42','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information',22,'EE05','The Database Table provides the information of the table definition','Y','Y','Y','N','N','N','Y','Table',44,0,TO_DATE('2008-03-05 00:51:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54565 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54477,54566,0,53084,TO_DATE('2008-03-05 00:51:46','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_DATE('2008-03-05 00:51:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54566 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54482,54567,0,53084,TO_DATE('2008-03-05 00:51:46','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_DATE('2008-03-05 00:51:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54567 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54484,54568,0,53084,TO_DATE('2008-03-05 00:51:47','YYYY-MM-DD HH24:MI:SS'),0,'Type of Data Replication',20,'EE05','The Type of data Replication determines the directon of the data replication.  <br>
Reference means that the data in this system is read only -> <br>
Local means that the data in this system is not replicated to other systems - <br>
Merge means that the data in this system is synchronized with the other system <-> <br>','Y','Y','Y','N','N','N','N','Replication Type',70,0,TO_DATE('2008-03-05 00:51:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54568 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:48 AM CST
-- Replication Data Functionality
UPDATE AD_Table SET AD_Window_ID=285, AccessLevel='6', Description='Data Replication Strategy', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Replication Strategy', ReplicationType='L', TableName='AD_ReplicationStrategy',Updated=TO_DATE('2008-03-05 00:51:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=602
;

-- Mar 5, 2008 12:51:48 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9362
;

-- Mar 5, 2008 12:51:48 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=9362 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:48 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9364
;

-- Mar 5, 2008 12:51:48 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=9364 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:49 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2133, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_ReplicationStrategy_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Strategy', EntityType='D', FieldLength=22, Help='The Data Replication Strategy determines what and how tables are replicated ', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication Strategy', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9361
;

-- Mar 5, 2008 12:51:49 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Strategy', Description='Data Replication Strategy', Help='The Data Replication Strategy determines what and how tables are replicated ' WHERE AD_Column_ID=9361 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:49 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9355
;

-- Mar 5, 2008 12:51:49 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=9355 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:50 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9363
;

-- Mar 5, 2008 12:51:50 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=9363 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:50 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9366
;

-- Mar 5, 2008 12:51:50 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Description', Description='Optional short description of the record', Help='A description is limited to 255 characters.' WHERE AD_Column_ID=9366 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:50 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9356
;

-- Mar 5, 2008 12:51:50 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=9356 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9359
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=9359 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='_EntityTypeNew', ValidationType='T',Updated=TO_DATE('2008-03-05 00:51:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=389
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=389
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_Table SET AD_Table_ID = 882, AD_Display = 15601, AD_Key = 15592, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 389
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=1682, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=389, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='EntityType', ColumnSQL=NULL, DefaultValue='U', Description='Dictionary Entity Type; Determines ownership and synchronization', EntityType='D', FieldLength=40, Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Entity Type', ReadOnlyLogic='@EntityType@=D', VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9365
;

-- Mar 5, 2008 12:51:51 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Entity Type', Description='Dictionary Entity Type; Determines ownership and synchronization', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!' WHERE AD_Column_ID=9365 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:52 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=326, AD_Process_ID=NULL, AD_Reference_ID=14, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Help', ColumnSQL=NULL, DefaultValue=NULL, Description='Comment or Hint', EntityType='D', FieldLength=2000, Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Comment/Help', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9358
;

-- Mar 5, 2008 12:51:52 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Comment/Help', Description='Comment or Hint', Help='The Help field contains a hint, comment or help about the use of this item.' WHERE AD_Column_ID=9358 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:52 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9357
;

-- Mar 5, 2008 12:51:52 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=9357 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:53 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=602, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9360
;

-- Mar 5, 2008 12:51:53 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Name', Description='Alphanumeric identifier of the entity', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.' WHERE AD_Column_ID=9360 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53367,0,'EXP_Processor_ID',TO_DATE('2008-03-05 00:51:53','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','EXP_Processor_ID','EXP_Processor_ID',TO_DATE('2008-03-05 00:51:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53367 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:51:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54486,53367,0,19,602,'EXP_Processor_ID',TO_DATE('2008-03-05 00:51:53','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','N','N','N','Y','N','Y','EXP_Processor_ID',10,TO_DATE('2008-03-05 00:51:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:51:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54486 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:51:54 AM CST
-- Replication Data Functionality
ALTER TABLE AD_ReplicationStrategy ADD EXP_Processor_ID NUMBER(10)
;

-- Mar 5, 2008 12:51:54 AM CST
-- Replication Data Functionality
UPDATE AD_Tab SET AD_Table_ID=602, AD_Window_ID=285, CommitWarning=NULL, Description='Data Replication Strategy', EntityType='EE05', HasTree='N', Help='The Data Replication Strategy determines which tables and how they are replicated. Note that the migration does not syncronize Application Dictionary items.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Replication Strategy', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_DATE('2008-03-05 00:51:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=524
;

-- Mar 5, 2008 12:51:54 AM CST
-- Replication Data Functionality
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=524
;

-- Mar 5, 2008 12:51:54 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9361, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Data Replication Strategy', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='The Data Replication Strategy determines what and how tables are replicated ', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication Strategy', SeqNo=10, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7516
;

-- Mar 5, 2008 12:51:55 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9362, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7517
;

-- Mar 5, 2008 12:51:55 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9364, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7518
;

-- Mar 5, 2008 12:51:55 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9360, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='EE05', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=40, SortNo=1,Updated=TO_DATE('2008-03-05 00:51:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7515
;

-- Mar 5, 2008 12:51:55 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9366, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='EE05', Help='A description is limited to 255 characters.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=50, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7520
;

-- Mar 5, 2008 12:51:56 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9358, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Comment or Hint', DisplayLength=60, DisplayLogic=NULL, EntityType='EE05', Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Comment/Help', SeqNo=60, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7514
;

-- Mar 5, 2008 12:51:56 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9357, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='EE05', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=70, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7513
;

-- Mar 5, 2008 12:51:56 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9365, AD_FieldGroup_ID=NULL, AD_Tab_ID=524, Description='Dictionary Entity Type; Determines ownership and synchronization', DisplayLength=20, DisplayLogic=NULL, EntityType='EE05', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Entity Type', SeqNo=80, SortNo=0,Updated=TO_DATE('2008-03-05 00:51:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7519
;

-- Mar 5, 2008 12:51:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54486,54569,0,524,TO_DATE('2008-03-05 00:51:56','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','Y','N','N','N','N','EXP_Processor_ID',84,0,TO_DATE('2008-03-05 00:51:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:51:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54569 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:51:57 AM CST
-- Replication Data Functionality
UPDATE AD_Table SET AD_Window_ID=285, AccessLevel='6', Description='Data Replication Strategy Table Info', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Replication Table', ReplicationType='L', TableName='AD_ReplicationTable',Updated=TO_DATE('2008-03-05 00:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=601
;

-- Mar 5, 2008 12:51:57 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9354
;

-- Mar 5, 2008 12:51:57 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated By', Description='User who updated this records', Help='The Updated By field indicates the user who updated this record.' WHERE AD_Column_ID=9354 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:58 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9352
;

-- Mar 5, 2008 12:51:58 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Organization', Description='Organizational entity within client', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.' WHERE AD_Column_ID=9352 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:58 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9345
;

-- Mar 5, 2008 12:51:58 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Updated', Description='Date this record was updated', Help='The Updated field indicates the date that this record was updated.' WHERE AD_Column_ID=9345 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:59 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9350
;

-- Mar 5, 2008 12:51:59 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Client', Description='Client/Tenant for this installation.', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.' WHERE AD_Column_ID=9350 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:59 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2134, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_ReplicationTable_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Strategy Table Info', EntityType='D', FieldLength=22, Help='Determines how the table is replicated', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication Table', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9349
;

-- Mar 5, 2008 12:51:59 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Table', Description='Data Replication Strategy Table Info', Help='Determines how the table is replicated' WHERE AD_Column_ID=9349 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:51:59 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:51:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9347
;

-- Mar 5, 2008 12:51:59 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created', Description='Date this record was created', Help='The Created field indicates the date that this record was created.' WHERE AD_Column_ID=9347 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:00 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:52:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9346
;

-- Mar 5, 2008 12:52:00 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Created By', Description='User who created this records', Help='The Created By field indicates the user who created this record.' WHERE AD_Column_ID=9346 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:00 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=1682, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=389, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='EntityType', ColumnSQL=NULL, DefaultValue='U', Description='Dictionary Entity Type; Determines ownership and synchronization', EntityType='D', FieldLength=40, Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Entity Type', ReadOnlyLogic='@EntityType@=D', VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:52:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9343
;

-- Mar 5, 2008 12:52:00 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Entity Type', Description='Dictionary Entity Type; Determines ownership and synchronization', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!' WHERE AD_Column_ID=9343 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:00 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:52:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9353
;

-- Mar 5, 2008 12:52:00 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Active', Description='The record is active in the system', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.' WHERE AD_Column_ID=9353 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:01 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2137, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=126, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='ReplicationType', ColumnSQL=NULL, DefaultValue=NULL, Description='Type of Data Replication', EntityType='D', FieldLength=1, Help='The Type of data Replication determines the directon of the data replication.  <br>
Reference means that the data in this system is read only -> <br>
Local means that the data in this system is not replicated to other systems - <br>
Merge means that the data in this system is synchronized with the other system <-> <br>', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Replication Type', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:52:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9351
;

-- Mar 5, 2008 12:52:01 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Type', Description='Type of Data Replication', Help='The Type of data Replication determines the directon of the data replication.  <br>
Reference means that the data in this system is read only -> <br>
Local means that the data in this system is not replicated to other systems - <br>
Merge means that the data in this system is synchronized with the other system <-> <br>' WHERE AD_Column_ID=9351 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:01 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=2133, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_ReplicationStrategy_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data Replication Strategy', EntityType='D', FieldLength=22, Help='The Data Replication Strategy determines what and how tables are replicated ', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Replication Strategy', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:52:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9348
;

-- Mar 5, 2008 12:52:01 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Strategy', Description='Data Replication Strategy', Help='The Data Replication Strategy determines what and how tables are replicated ' WHERE AD_Column_ID=9348 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:02 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET AD_Element_ID=126, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=601, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Table_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Database Table information', EntityType='D', FieldLength=22, Help='The Database Table provides the information of the table definition', IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Table', ReadOnlyLogic=NULL, SeqNo=2, VFormat=NULL, Version=1,Updated=TO_DATE('2008-03-05 00:52:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=9344
;

-- Mar 5, 2008 12:52:02 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Table', Description='Database Table information', Help='The Database Table provides the information of the table definition' WHERE AD_Column_ID=9344 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 12:52:02 AM CST
-- Replication Data Functionality
UPDATE AD_Tab SET AD_Table_ID=601, AD_Window_ID=285, CommitWarning=NULL, Description='Data Replication Strategy Table Info', EntityType='EE05', HasTree='N', Help='Determines how the table is replicated. You have full access to Local tables, Reference tables are on Remote systems and are read-only. The data of Merge tables on Remote systems is copied to the central system.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='N', IsSortTab='N', IsTranslationTab='N', Name='Replication Table', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_DATE('2008-03-05 00:52:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=525
;

-- Mar 5, 2008 12:52:02 AM CST
-- Replication Data Functionality
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=525
;

-- Mar 5, 2008 12:52:02 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9349, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Data Replication Strategy Table Info', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='Determines how the table is replicated', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication Table', SeqNo=10, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7524
;

-- Mar 5, 2008 12:52:03 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9350, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=20, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7525
;

-- Mar 5, 2008 12:52:03 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9352, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='Y', Name='Organization', SeqNo=30, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7527
;

-- Mar 5, 2008 12:52:03 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9348, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Data Replication Strategy', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='The Data Replication Strategy determines what and how tables are replicated ', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Replication Strategy', SeqNo=40, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7523
;

-- Mar 5, 2008 12:52:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9344, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Database Table information', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='The Database Table provides the information of the table definition', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Table', SeqNo=50, SortNo=1,Updated=TO_DATE('2008-03-05 00:52:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7522
;

-- Mar 5, 2008 12:52:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9353, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='EE05', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=60, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7528
;

-- Mar 5, 2008 12:52:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9351, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Type of Data Replication', DisplayLength=14, DisplayLogic=NULL, EntityType='EE05', Help='The Type of data Replication determines the directon of the data replication.  <br>
Reference means that the data in this system is read only -> <br>
Local means that the data in this system is not replicated to other systems - <br>
Merge means that the data in this system is synchronized with the other system <-> <br>', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Replication Type', SeqNo=70, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7526
;

-- Mar 5, 2008 12:52:04 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET AD_Column_ID=9343, AD_FieldGroup_ID=NULL, AD_Tab_ID=525, Description='Dictionary Entity Type; Determines ownership and synchronization', DisplayLength=20, DisplayLogic=NULL, EntityType='EE05', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Entity Type', SeqNo=80, SortNo=0,Updated=TO_DATE('2008-03-05 00:52:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=7521
;

-- Mar 5, 2008 12:52:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Workflow (AD_Client_ID,AD_Org_ID,AD_Workflow_ID,AccessLevel,Author,Cost,Created,CreatedBy,Description,DocumentNo,Duration,EntityType,Help,IsActive,IsDefault,IsValid,Limit,Name,Priority,PublishStatus,Updated,UpdatedBy,Value,Version,WaitingTime,WorkflowType,WorkingTime) VALUES (0,0,50012,'6','Adempiere',0,TO_DATE('2008-03-05 00:52:04','YYYY-MM-DD HH24:MI:SS'),0,'Setup of data replication','10000000',0,'EE05','Data Replication allows you to synchronize data of remote instances with a central office.  All actions are initialized from the central office.<p>
<b>Central System:</b><br>
- setup system with all organizations, roles, etc.<br>
- run the migration to make sure that it is up-to-date<br>
- export the central system<br>
<p>
<b>Remote System:</b>
- install the exact version as Central System<br>
- import data from Central System in the remote locations<br>
<p>
<b>Central System:</b>
- Replication Strategy<br>
- Define Export Format<br>
- Config Export Processor<br>
- Start JMS Server<br>
<p>
** Enter Transactions **
<p>
<b>Remote System:</b><br>
- Config Import Processor<br>','Y','N','N',0,'Setup Replication',0,'R',TO_DATE('2008-03-05 00:52:04','YYYY-MM-DD HH24:MI:SS'),0,'Setup Replication',1,0,'G',0)
;

-- Mar 5, 2008 12:52:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Workflow_Trl (AD_Language,AD_Workflow_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Workflow_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Workflow t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Workflow_ID=50012 AND EXISTS (SELECT * FROM AD_Workflow_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Workflow_ID!=t.AD_Workflow_ID)
;

-- Mar 5, 2008 12:52:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Workflow_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Workflow_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,50012,TO_DATE('2008-03-05 00:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:52:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_WF_Node (AD_Client_ID,AD_Org_ID,AD_WF_Node_ID,AD_Window_ID,AD_Workflow_ID,Action,Cost,Created,CreatedBy,Description,Duration,DynPriorityChange,EntityType,IsActive,IsCentrallyMaintained,JoinElement,Name,Priority,SplitElement,Updated,UpdatedBy,Value,WaitTime,WaitingTime,WorkingTime,XPosition,YPosition) VALUES (0,0,50064,285,50012,'W',0,TO_DATE('2008-03-05 00:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Setup of data replication',0,0,'EE05','Y','N','X','Setup Replication',0,'X',TO_DATE('2008-03-05 00:52:05','YYYY-MM-DD HH24:MI:SS'),0,'Export Format',0,0,0,5,5)
;

-- Mar 5, 2008 12:52:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_WF_Node_Trl (AD_Language,AD_WF_Node_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_WF_Node_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_WF_Node t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_WF_Node_ID=50064 AND EXISTS (SELECT * FROM AD_WF_Node_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_WF_Node_ID!=t.AD_WF_Node_ID)
;

-- Mar 5, 2008 12:52:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_WF_NodeNext (AD_Client_ID,AD_Org_ID,AD_WF_Next_ID,AD_WF_NodeNext_ID,AD_WF_Node_ID,Created,CreatedBy,EntityType,IsActive,IsStdUserWorkflow,SeqNo,Updated,UpdatedBy) VALUES (0,0,50064,50045,50064,TO_DATE('2008-03-05 00:52:06','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N',1,TO_DATE('2008-03-05 00:52:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:07 AM CST
-- Replication Data Functionality
UPDATE AD_WF_NodeNext SET AD_WF_Next_ID=50064, AD_WF_Node_ID=50064, EntityType='EE05', IsActive='Y', IsStdUserWorkflow='N', SeqNo=10,Updated=TO_DATE('2008-03-05 00:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_NodeNext_ID=50045
;

-- Mar 5, 2008 12:52:08 AM CST
-- Replication Data Functionality
UPDATE AD_WF_Node SET AD_Window_ID=285, AD_Workflow_ID=50012, Action='W', Cost=0, Description='Setup of data replication', DocAction=NULL, Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='EE05', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Setup Replication', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Replication Strategy', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=170, YPosition=5,Updated=TO_DATE('2008-03-05 00:52:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 12:52:09 AM CST
-- Replication Data Functionality
UPDATE AD_Workflow SET AD_WF_Node_ID=50064, IsValid='Y',Updated=TO_DATE('2008-03-05 00:52:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Workflow_ID=50012
;

-- Mar 5, 2008 12:52:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53025,TO_DATE('2008-03-05 00:52:09','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','Y','Export Format','N',TO_DATE('2008-03-05 00:52:09','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 5, 2008 12:52:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53025 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 5, 2008 12:52:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53025,TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53025,TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53025,TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53025,TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53072,53025,'3',TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Export Format','L','EXP_Format',TO_DATE('2008-03-05 00:52:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53072 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:52:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53089,TO_DATE('2008-03-05 00:52:11','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table EXP_Format',1,'Y','N','Y','Y','EXP_Format','N',1000000,TO_DATE('2008-03-05 00:52:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53368,0,'EXP_Format_ID',TO_DATE('2008-03-05 00:52:11','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','EXP_Format_ID','EXP_Format_ID',TO_DATE('2008-03-05 00:52:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53368 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:52:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54487,53368,0,13,53072,'EXP_Format_ID',TO_DATE('2008-03-05 00:52:11','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','EXP_Format_ID',10,TO_DATE('2008-03-05 00:52:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54487 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:13 AM CST
-- Replication Data Functionality
CREATE TABLE EXP_Format (EXP_Format_ID NUMBER(10) NOT NULL, CONSTRAINT EXP_Format_Key PRIMARY KEY (EXP_Format_ID))
;

-- Mar 5, 2008 12:52:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54488,102,0,19,53072,129,'AD_Client_ID',TO_DATE('2008-03-05 00:52:13','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',20,TO_DATE('2008-03-05 00:52:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54488 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:13 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:52:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54489,113,0,19,53072,104,'AD_Org_ID',TO_DATE('2008-03-05 00:52:13','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',30,TO_DATE('2008-03-05 00:52:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54489 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:15 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:52:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54490,348,0,20,53072,'IsActive',TO_DATE('2008-03-05 00:52:15','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',40,TO_DATE('2008-03-05 00:52:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54490 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:16 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:52:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54491,245,0,16,53072,'Created',TO_DATE('2008-03-05 00:52:16','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',50,TO_DATE('2008-03-05 00:52:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54491 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:16 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:52:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54492,246,0,18,110,53072,'CreatedBy',TO_DATE('2008-03-05 00:52:16','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',60,TO_DATE('2008-03-05 00:52:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54492 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:17 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:52:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54493,607,0,16,53072,'Updated',TO_DATE('2008-03-05 00:52:17','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',70,TO_DATE('2008-03-05 00:52:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54493 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:18 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:52:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54494,608,0,18,110,53072,'UpdatedBy',TO_DATE('2008-03-05 00:52:18','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',80,TO_DATE('2008-03-05 00:52:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54494 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:19 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:52:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54495,620,0,10,53072,'Value',TO_DATE('2008-03-05 00:52:19','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',90,TO_DATE('2008-03-05 00:52:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54495 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:20 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:52:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54496,469,0,10,53072,'Name',TO_DATE('2008-03-05 00:52:20','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',100,TO_DATE('2008-03-05 00:52:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54496 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:20 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:52:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54497,275,0,10,53072,'Description',TO_DATE('2008-03-05 00:52:20','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',110,TO_DATE('2008-03-05 00:52:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54497 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:21 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:52:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54498,326,0,14,53072,'Help',TO_DATE('2008-03-05 00:52:21','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',120,TO_DATE('2008-03-05 00:52:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54498 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:22 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:52:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54499,126,0,30,53072,'AD_Table_ID',TO_DATE('2008-03-05 00:52:22','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information','EE05',10,'The Database Table provides the information of the table definition','Y','N','N','N','N','Y','N','N','Y','N','Y','Table',130,TO_DATE('2008-03-05 00:52:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54499 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:23 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD AD_Table_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:52:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54500,630,0,14,53072,'WhereClause',TO_DATE('2008-03-05 00:52:23','YYYY-MM-DD HH24:MI:SS'),0,'Fully qualified SQL WHERE clause','EE05',255,'The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".','Y','N','N','N','N','N','N','N','Y','N','Y','Sql WHERE',140,TO_DATE('2008-03-05 00:52:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54500 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:23 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD WhereClause NVARCHAR2(255)
;

-- Mar 5, 2008 12:52:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53074,'3','org.adempiere.server.rpl.imp.ModelImporter',TO_DATE('2008-03-05 00:52:23','YYYY-MM-DD HH24:MI:SS'),0,'Test Import of XML files','EE05','Y','N','N','N','Test Import Model','Y',0,0,TO_DATE('2008-03-05 00:52:23','YYYY-MM-DD HH24:MI:SS'),0,'TestImportModel',NULL)
;

-- Mar 5, 2008 12:52:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53074 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Mar 5, 2008 12:52:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53369,0,'TestImportModel',TO_DATE('2008-03-05 00:52:24','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','TestImportModel','TestImportModel',TO_DATE('2008-03-05 00:52:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53369 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:52:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54501,53369,0,53074,28,53072,'TestImportModel',TO_DATE('2008-03-05 00:52:24','YYYY-MM-DD HH24:MI:SS'),0,'EE05',1,'Y','Y','N','N','N','N','N','N','Y','N','Y','TestImportModel',150,TO_DATE('2008-03-05 00:52:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54501 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:26 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD TestImportModel CHAR(1)
;

-- Mar 5, 2008 12:52:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54502,624,0,10,53072,'Version',TO_DATE('2008-03-05 00:52:26','YYYY-MM-DD HH24:MI:SS'),0,'Version of the table definition','EE05',40,'The Version indicates the version of this table definition.','Y','N','N','N','N','Y','N','N','Y','N','Y','Version',160,TO_DATE('2008-03-05 00:52:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54502 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:26 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Format ADD Version NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:52:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53085,53072,53025,NULL,TO_DATE('2008-03-05 00:52:26','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','Y','N','N','Export Format','N',10,0,TO_DATE('2008-03-05 00:52:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53085 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:52:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54487,54570,0,53085,TO_DATE('2008-03-05 00:52:27','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','EXP_Format_ID',0,0,TO_DATE('2008-03-05 00:52:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54570 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54488,54571,0,53085,TO_DATE('2008-03-05 00:52:28','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:52:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54571 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54489,54572,0,53085,TO_DATE('2008-03-05 00:52:29','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:52:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54572 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:30 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54495,54573,0,53085,TO_DATE('2008-03-05 00:52:29','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2008-03-05 00:52:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:30 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54573 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54502,54574,0,53085,TO_DATE('2008-03-05 00:52:30','YYYY-MM-DD HH24:MI:SS'),0,'Version of the table definition',0,'EE05','The Version indicates the version of this table definition.','Y','Y','Y','N','N','N','Y','Version',34,0,TO_DATE('2008-03-05 00:52:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54574 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54496,54575,0,53085,TO_DATE('2008-03-05 00:52:31','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2008-03-05 00:52:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54575 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54497,54576,0,53085,TO_DATE('2008-03-05 00:52:31','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2008-03-05 00:52:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54576 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54490,54577,0,53085,TO_DATE('2008-03-05 00:52:32','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_DATE('2008-03-05 00:52:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54577 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54498,54578,0,53085,TO_DATE('2008-03-05 00:52:33','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_DATE('2008-03-05 00:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54578 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54499,54579,0,53085,TO_DATE('2008-03-05 00:52:33','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information',22,'EE05','The Database Table provides the information of the table definition','Y','Y','Y','N','N','N','N','Table',80,0,TO_DATE('2008-03-05 00:52:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54579 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54500,54580,0,53085,TO_DATE('2008-03-05 00:52:34','YYYY-MM-DD HH24:MI:SS'),0,'Fully qualified SQL WHERE clause',60,'EE05','The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".','Y','Y','Y','N','N','N','N','Sql WHERE',90,0,TO_DATE('2008-03-05 00:52:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54580 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54501,54581,0,53085,TO_DATE('2008-03-05 00:52:35','YYYY-MM-DD HH24:MI:SS'),0,1,'EE05','Y','Y','Y','N','N','N','N','TestImportModel',100,0,TO_DATE('2008-03-05 00:52:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54581 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:52:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53073,'3',TO_DATE('2008-03-05 00:52:35','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Export Format Line','L','EXP_FormatLine',TO_DATE('2008-03-05 00:52:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53073 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:52:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53090,TO_DATE('2008-03-05 00:52:36','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table EXP_FormatLine',1,'Y','N','Y','Y','EXP_FormatLine','N',1000000,TO_DATE('2008-03-05 00:52:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53370,0,'EXP_FormatLine_ID',TO_DATE('2008-03-05 00:52:36','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','EXP_FormatLine_ID','EXP_FormatLine_ID',TO_DATE('2008-03-05 00:52:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53370 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:52:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54503,53370,0,13,53073,'EXP_FormatLine_ID',TO_DATE('2008-03-05 00:52:36','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','EXP_FormatLine_ID',10,TO_DATE('2008-03-05 00:52:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54503 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:38 AM CST
-- Replication Data Functionality
CREATE TABLE EXP_FormatLine (EXP_FormatLine_ID NUMBER(10) NOT NULL, CONSTRAINT EXP_FormatLine_Key PRIMARY KEY (EXP_FormatLine_ID))
;

-- Mar 5, 2008 12:52:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54504,102,0,19,53073,129,'AD_Client_ID',TO_DATE('2008-03-05 00:52:38','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',20,TO_DATE('2008-03-05 00:52:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54504 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:39 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:52:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54505,113,0,19,53073,104,'AD_Org_ID',TO_DATE('2008-03-05 00:52:39','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',30,TO_DATE('2008-03-05 00:52:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54505 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:40 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:52:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54506,348,0,20,53073,'IsActive',TO_DATE('2008-03-05 00:52:40','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',40,TO_DATE('2008-03-05 00:52:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54506 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:40 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:52:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54507,245,0,16,53073,'Created',TO_DATE('2008-03-05 00:52:40','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',50,TO_DATE('2008-03-05 00:52:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54507 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:41 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:52:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54508,246,0,18,110,53073,'CreatedBy',TO_DATE('2008-03-05 00:52:41','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',60,TO_DATE('2008-03-05 00:52:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54508 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:42 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:52:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54509,607,0,16,53073,'Updated',TO_DATE('2008-03-05 00:52:42','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',70,TO_DATE('2008-03-05 00:52:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54509 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:42 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:52:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54510,608,0,18,110,53073,'UpdatedBy',TO_DATE('2008-03-05 00:52:42','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',80,TO_DATE('2008-03-05 00:52:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54510 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:43 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:52:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54511,620,0,10,53073,'Value',TO_DATE('2008-03-05 00:52:43','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',90,TO_DATE('2008-03-05 00:52:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54511 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:44 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:52:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54512,469,0,10,53073,'Name',TO_DATE('2008-03-05 00:52:44','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',100,TO_DATE('2008-03-05 00:52:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54512 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:45 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:52:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54513,275,0,10,53073,'Description',TO_DATE('2008-03-05 00:52:45','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',110,TO_DATE('2008-03-05 00:52:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54513 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:45 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:52:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54514,326,0,14,53073,'Help',TO_DATE('2008-03-05 00:52:45','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',120,TO_DATE('2008-03-05 00:52:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54514 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:46 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:52:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54515,53368,0,19,53073,'EXP_Format_ID',TO_DATE('2008-03-05 00:52:46','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','Y','N','N','Y','N','Y','N','N','EXP_Format_ID',130,TO_DATE('2008-03-05 00:52:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54515 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:46 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD EXP_Format_ID NUMBER(10)
;

-- Mar 5, 2008 12:52:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54516,52014,0,11,53073,'Position',TO_DATE('2008-03-05 00:52:46','YYYY-MM-DD HH24:MI:SS'),0,'@SQL=SELECT COALESCE(MAX(Position),0)+10 AS DefaultValue FROM EXP_FormatLine WHERE EXP_Format_ID=@EXP_Format_ID@','EE05',14,'Y','N','N','N','N','N','N','N','Y','N','Y','Position',140,TO_DATE('2008-03-05 00:52:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54516 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:47 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Position NUMBER(10) DEFAULT  NULL 
;

-- Mar 5, 2008 12:52:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54517,392,0,20,53073,'IsMandatory',TO_DATE('2008-03-05 00:52:47','YYYY-MM-DD HH24:MI:SS'),0,'Data entry is required in this column','EE05',1,'The field must have a value for the record to be saved to the database.','Y','N','N','N','N','N','N','N','Y','N','Y','Mandatory',150,TO_DATE('2008-03-05 00:52:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54517 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:48 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD IsMandatory CHAR(1) CHECK (IsMandatory IN ('Y','N'))
;

-- Mar 5, 2008 12:52:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53241,TO_DATE('2008-03-05 00:52:48','YYYY-MM-DD HH24:MI:SS'),0,'List with Export Line types','EE05','Y','EXP_Line_Type',TO_DATE('2008-03-05 00:52:48','YYYY-MM-DD HH24:MI:SS'),0,'L')
;

-- Mar 5, 2008 12:52:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53241 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Mar 5, 2008 12:52:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53333,53241,TO_DATE('2008-03-05 00:52:48','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','XML Element',TO_DATE('2008-03-05 00:52:48','YYYY-MM-DD HH24:MI:SS'),0,'E')
;

-- Mar 5, 2008 12:52:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53333 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 5, 2008 12:52:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53334,53241,TO_DATE('2008-03-05 00:52:49','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','XML Attribute',TO_DATE('2008-03-05 00:52:49','YYYY-MM-DD HH24:MI:SS'),0,'A')
;

-- Mar 5, 2008 12:52:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53334 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 5, 2008 12:52:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53335,53241,TO_DATE('2008-03-05 00:52:50','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','Embedded EXP Format',TO_DATE('2008-03-05 00:52:50','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 5, 2008 12:52:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53335 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 5, 2008 12:52:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53336,53241,TO_DATE('2008-03-05 00:52:50','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','Referenced EXP Format',TO_DATE('2008-03-05 00:52:50','YYYY-MM-DD HH24:MI:SS'),0,'R')
;

-- Mar 5, 2008 12:52:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53336 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Mar 5, 2008 12:52:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54518,600,0,17,53241,53073,'Type',TO_DATE('2008-03-05 00:52:51','YYYY-MM-DD HH24:MI:SS'),0,'E','Type of Validation (SQL, Java Script, Java Language)','EE05',1,'The Type indicates the type of validation that will occur.  This can be SQL, Java Script or Java Language.','Y','N','N','N','N','Y','N','N','Y','N','Y','Type',160,TO_DATE('2008-03-05 00:52:51','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54518 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:51 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD Type CHAR(1) DEFAULT 'E' NOT NULL
;

-- Mar 5, 2008 12:52:52 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54519,104,0,30,53073,100,'AD_Column_ID',TO_DATE('2008-03-05 00:52:52','YYYY-MM-DD HH24:MI:SS'),0,'Column in the table','EE05',10,'Link to the database column of the table','Y','N','N','N','N','Y','N','N','Y','N','Y','Column',170,TO_DATE('2008-03-05 00:52:52','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:52 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54519 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:52 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD AD_Column_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:52:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53242,TO_DATE('2008-03-05 00:52:52','YYYY-MM-DD HH24:MI:SS'),0,'Embedded Export Format','EE05','Y','EXP_Format',TO_DATE('2008-03-05 00:52:52','YYYY-MM-DD HH24:MI:SS'),0,'T')
;

-- Mar 5, 2008 12:52:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53242 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Mar 5, 2008 12:52:53 AM CST
-- Replication Data Functionality
Insert INTO AD_Ref_Table(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, AD_Reference_ID, AD_Table_ID, AD_Display, AD_Key ,entityType, isValueDisplayed, OrderByClause,  WhereClause )VALUES(0, 0, 0, 0, 53242, 53072, 54495, 54487, 'EE05', 'N', '', '')
;

-- Mar 5, 2008 12:52:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53371,0,'EXP_EmbeddedFormat_ID',TO_DATE('2008-03-05 00:52:53','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','EXP_EmbeddedFormat_ID','EXP_EmbeddedFormat_ID',TO_DATE('2008-03-05 00:52:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53371 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:52:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54520,53371,0,18,53242,53073,'EXP_EmbeddedFormat_ID',TO_DATE('2008-03-05 00:52:53','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','N','N','N','Y','N','Y','EXP_EmbeddedFormat_ID',180,TO_DATE('2008-03-05 00:52:53','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54520 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:54 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD EXP_EmbeddedFormat_ID NUMBER(10)
;

-- Mar 5, 2008 12:52:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53372,0,'IsPartUniqueIndex',TO_DATE('2008-03-05 00:52:54','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','IsPartUniqueIndex','IsPartUniqueIndex',TO_DATE('2008-03-05 00:52:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53372 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:52:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54521,53372,0,20,53073,'IsPartUniqueIndex',TO_DATE('2008-03-05 00:52:54','YYYY-MM-DD HH24:MI:SS'),0,'N','EE05',1,'Y','N','N','N','N','N','N','N','Y','N','Y','IsPartUniqueIndex',190,TO_DATE('2008-03-05 00:52:54','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54521 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:55 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD IsPartUniqueIndex CHAR(1) DEFAULT 'N' CHECK (IsPartUniqueIndex IN ('Y','N'))
;

-- Mar 5, 2008 12:52:55 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Data Type selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Reference Data Types', ValidationType='T',Updated=TO_DATE('2008-03-05 00:52:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=1
;

-- Mar 5, 2008 12:52:55 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=1
;

-- Mar 5, 2008 12:52:55 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_Table SET AD_Table_ID = 102, AD_Display = 130, AD_Key = 129, isValueDisplayed = 'N', OrderByClause = 'AD_Reference.Name', EntityType ='D', WhereClause = 'AD_Reference.ValidationType=''D''' WHERE AD_Reference_ID = 1
;

-- Mar 5, 2008 12:52:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,ColumnSQL,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54522,120,0,18,1,53073,'AD_Reference_ID','(SELECT AD_Reference_ID FROM AD_Column WHERE AD_Column.AD_Column_ID=EXP_FormatLine.AD_Column_ID)',TO_DATE('2008-03-05 00:52:55','YYYY-MM-DD HH24:MI:SS'),0,'System Reference and Validation','EE05',10,'The Reference could be a display type, list or table validation.','Y','N','N','N','N','N','N','N','Y','N','N','Reference',200,TO_DATE('2008-03-05 00:52:55','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54522 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,ReadOnlyLogic,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54523,2286,0,10,53073,'DateFormat',TO_DATE('2008-03-05 00:52:56','YYYY-MM-DD HH24:MI:SS'),0,'Date format used in the imput format','EE05',40,'The date format is usually detected, but sometimes need to be defined.','Y','N','N','N','N','N','N','N','Y','N','Y','Date Format','@AD_Reference_ID@!15 & @AD_Reference_ID@!16',210,TO_DATE('2008-03-05 00:52:56','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:52:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54523 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:52:58 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_FormatLine ADD DateFormat NVARCHAR2(40)
;

-- Mar 5, 2008 12:52:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,OrderByClause,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53086,53073,53025,NULL,TO_DATE('2008-03-05 00:52:58','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','Y','N','N','Export Format Line','Position','N',20,1,TO_DATE('2008-03-05 00:52:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53086 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:52:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54503,54582,0,53086,TO_DATE('2008-03-05 00:52:59','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','EXP_FormatLine_ID',0,0,TO_DATE('2008-03-05 00:52:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:52:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54582 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54504,54583,0,53086,TO_DATE('2008-03-05 00:52:59','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:52:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54583 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54505,54584,0,53086,TO_DATE('2008-03-05 00:53:00','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:53:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54584 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54515,54585,0,53086,TO_DATE('2008-03-05 00:53:01','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','Y','N','N','Y','N','EXP_Format_ID',30,0,TO_DATE('2008-03-05 00:53:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54585 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54511,54586,0,53086,TO_DATE('2008-03-05 00:53:01','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',40,0,TO_DATE('2008-03-05 00:53:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54586 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54512,54587,0,53086,TO_DATE('2008-03-05 00:53:02','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_DATE('2008-03-05 00:53:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54587 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54513,54588,0,53086,TO_DATE('2008-03-05 00:53:03','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_DATE('2008-03-05 00:53:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54588 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54506,54589,0,53086,TO_DATE('2008-03-05 00:53:03','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_DATE('2008-03-05 00:53:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54589 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54514,54590,0,53086,TO_DATE('2008-03-05 00:53:04','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_DATE('2008-03-05 00:53:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54590 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54516,54591,0,53086,TO_DATE('2008-03-05 00:53:05','YYYY-MM-DD HH24:MI:SS'),0,22,'EE05','Y','Y','Y','N','N','N','N','Position',90,0,TO_DATE('2008-03-05 00:53:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54591 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54517,54592,0,53086,TO_DATE('2008-03-05 00:53:05','YYYY-MM-DD HH24:MI:SS'),0,'Data entry is required in this column',1,'EE05','The field must have a value for the record to be saved to the database.','Y','Y','Y','N','N','N','N','Mandatory',100,0,TO_DATE('2008-03-05 00:53:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54592 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54518,54593,0,53086,TO_DATE('2008-03-05 00:53:06','YYYY-MM-DD HH24:MI:SS'),0,'Type of Validation (SQL, Java Script, Java Language)',0,'EE05','The Type indicates the type of validation that will occur.  This can be SQL, Java Script or Java Language.','Y','Y','Y','N','N','N','N','Type',110,0,TO_DATE('2008-03-05 00:53:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54593 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54519,54594,0,53086,TO_DATE('2008-03-05 00:53:06','YYYY-MM-DD HH24:MI:SS'),0,'Column in the table',20,'@Type@=''E'' | @Type@=''A'' | @Type@=''R''','EE05','Link to the database column of the table','Y','Y','Y','N','N','N','N','Column',130,0,TO_DATE('2008-03-05 00:53:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54594 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54521,54595,0,53086,TO_DATE('2008-03-05 00:53:07','YYYY-MM-DD HH24:MI:SS'),0,1,'@Type@=''E'' | @Type@=''R''','EE05','Y','Y','Y','N','N','N','Y','IsPartUniqueIndex',134,0,TO_DATE('2008-03-05 00:53:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54595 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54520,54596,0,53086,TO_DATE('2008-03-05 00:53:08','YYYY-MM-DD HH24:MI:SS'),0,22,'@Type@=''M'' | @Type@=''R''','EE05','Y','Y','Y','N','N','N','N','EXP_EmbeddedFormat_ID',140,0,TO_DATE('2008-03-05 00:53:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54596 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54522,54597,0,53086,TO_DATE('2008-03-05 00:53:08','YYYY-MM-DD HH24:MI:SS'),0,'System Reference and Validation',10,'EE05','The Reference could be a display type, list or table validation.','Y','Y','Y','N','N','Y','N','Reference',150,0,TO_DATE('2008-03-05 00:53:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54597 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54523,54598,0,53086,TO_DATE('2008-03-05 00:53:09','YYYY-MM-DD HH24:MI:SS'),0,'Date format used in the imput format',20,'EE05','The date format is usually detected, but sometimes need to be defined.','Y','Y','Y','N','N','N','Y','Date Format',160,0,TO_DATE('2008-03-05 00:53:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54598 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53026,TO_DATE('2008-03-05 00:53:10','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','Y','Export Processor','N',TO_DATE('2008-03-05 00:53:10','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 5, 2008 12:53:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53026 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 5, 2008 12:53:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53026,TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53026,TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53026,TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53026,TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53074,53026,'7',TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Export Processor','L','EXP_Processor',TO_DATE('2008-03-05 00:53:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53074 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:53:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53091,TO_DATE('2008-03-05 00:53:12','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table EXP_Processor',1,'Y','N','Y','Y','EXP_Processor','N',1000000,TO_DATE('2008-03-05 00:53:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54524,53367,0,13,53074,'EXP_Processor_ID',TO_DATE('2008-03-05 00:53:12','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','EXP_Processor_ID',10,TO_DATE('2008-03-05 00:53:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54524 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:13 AM CST
-- Replication Data Functionality
CREATE TABLE EXP_Processor (EXP_Processor_ID NUMBER(10) NOT NULL, CONSTRAINT EXP_Processor_Key PRIMARY KEY (EXP_Processor_ID))
;

-- Mar 5, 2008 12:53:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53373,0,'EXP_Processor_Type_ID',TO_DATE('2008-03-05 00:53:13','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','EXP_Processor_Type_ID','EXP_Processor_Type_ID',TO_DATE('2008-03-05 00:53:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53373 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54525,53373,0,19,53074,'EXP_Processor_Type_ID',TO_DATE('2008-03-05 00:53:13','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','Y','N','N','Y','N','Y','EXP_Processor_Type_ID',20,TO_DATE('2008-03-05 00:53:13','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54525 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:15 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD EXP_Processor_Type_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:53:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54526,102,0,19,53074,129,'AD_Client_ID',TO_DATE('2008-03-05 00:53:15','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',30,TO_DATE('2008-03-05 00:53:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54526 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:16 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:53:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54527,113,0,19,53074,104,'AD_Org_ID',TO_DATE('2008-03-05 00:53:16','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',40,TO_DATE('2008-03-05 00:53:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54527 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:16 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:53:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54528,348,0,20,53074,'IsActive',TO_DATE('2008-03-05 00:53:16','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',50,TO_DATE('2008-03-05 00:53:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54528 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:17 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:53:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54529,245,0,16,53074,'Created',TO_DATE('2008-03-05 00:53:17','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',60,TO_DATE('2008-03-05 00:53:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54529 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:18 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:53:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54530,246,0,18,110,53074,'CreatedBy',TO_DATE('2008-03-05 00:53:18','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',70,TO_DATE('2008-03-05 00:53:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54530 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:18 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:53:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54531,607,0,16,53074,'Updated',TO_DATE('2008-03-05 00:53:18','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',80,TO_DATE('2008-03-05 00:53:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54531 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:19 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:53:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54532,608,0,18,110,53074,'UpdatedBy',TO_DATE('2008-03-05 00:53:19','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',90,TO_DATE('2008-03-05 00:53:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54532 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:20 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:53:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54533,620,0,10,53074,'Value',TO_DATE('2008-03-05 00:53:20','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',100,TO_DATE('2008-03-05 00:53:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54533 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:20 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:53:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54534,469,0,10,53074,'Name',TO_DATE('2008-03-05 00:53:20','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',110,TO_DATE('2008-03-05 00:53:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54534 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:21 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:53:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54535,275,0,10,53074,'Description',TO_DATE('2008-03-05 00:53:21','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',120,TO_DATE('2008-03-05 00:53:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54535 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:22 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:53:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54536,326,0,14,53074,'Help',TO_DATE('2008-03-05 00:53:22','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',130,TO_DATE('2008-03-05 00:53:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54536 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:22 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:53:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53374,0,'Host',TO_DATE('2008-03-05 00:53:22','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','Host','Host',TO_DATE('2008-03-05 00:53:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53374 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54537,53374,0,10,53074,'Host',TO_DATE('2008-03-05 00:53:22','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Host',140,TO_DATE('2008-03-05 00:53:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54537 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:23 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Host NVARCHAR2(255)
;

-- Mar 5, 2008 12:53:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53375,0,'Port',TO_DATE('2008-03-05 00:53:23','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','Port','Port',TO_DATE('2008-03-05 00:53:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53375 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54538,53375,0,11,53074,'Port',TO_DATE('2008-03-05 00:53:23','YYYY-MM-DD HH24:MI:SS'),0,'EE05',14,'Y','N','N','N','N','N','N','N','Y','N','Y','Port',150,TO_DATE('2008-03-05 00:53:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54538 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:25 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Port NUMBER(10)
;

-- Mar 5, 2008 12:53:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53376,0,'Account',TO_DATE('2008-03-05 00:53:25','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','Account','Account',TO_DATE('2008-03-05 00:53:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53376 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54539,53376,0,10,53074,'Account',TO_DATE('2008-03-05 00:53:25','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Account',160,TO_DATE('2008-03-05 00:53:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54539 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:26 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD Account NVARCHAR2(255)
;

-- Mar 5, 2008 12:53:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53377,0,'PasswordInfo',TO_DATE('2008-03-05 00:53:26','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','PasswordInfo','PasswordInfo',TO_DATE('2008-03-05 00:53:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53377 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54540,53377,0,10,53074,'PasswordInfo',TO_DATE('2008-03-05 00:53:26','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','N','N','N','Y','N','Y','PasswordInfo',170,TO_DATE('2008-03-05 00:53:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54540 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:27 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor ADD PasswordInfo NVARCHAR2(255)
;

-- Mar 5, 2008 12:53:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53087,53074,53026,NULL,TO_DATE('2008-03-05 00:53:27','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Export Processor','N',10,0,TO_DATE('2008-03-05 00:53:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53087 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:53:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54524,54599,0,53087,TO_DATE('2008-03-05 00:53:28','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','EXP_Processor_ID',0,0,TO_DATE('2008-03-05 00:53:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54599 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54526,54600,0,53087,TO_DATE('2008-03-05 00:53:28','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:53:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54600 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54527,54601,0,53087,TO_DATE('2008-03-05 00:53:29','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:53:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54601 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:30 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54533,54602,0,53087,TO_DATE('2008-03-05 00:53:29','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2008-03-05 00:53:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:30 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54602 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54534,54603,0,53087,TO_DATE('2008-03-05 00:53:30','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2008-03-05 00:53:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54603 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54525,54604,0,53087,TO_DATE('2008-03-05 00:53:31','YYYY-MM-DD HH24:MI:SS'),0,22,'EE05','Y','Y','Y','N','N','N','N','EXP_Processor_Type_ID',50,0,TO_DATE('2008-03-05 00:53:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:31 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54604 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54535,54605,0,53087,TO_DATE('2008-03-05 00:53:31','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_DATE('2008-03-05 00:53:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:32 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54605 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54528,54606,0,53087,TO_DATE('2008-03-05 00:53:32','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_DATE('2008-03-05 00:53:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:33 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54606 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54536,54607,0,53087,TO_DATE('2008-03-05 00:53:33','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_DATE('2008-03-05 00:53:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54607 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54537,54608,0,53087,TO_DATE('2008-03-05 00:53:34','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','N','Host',90,0,TO_DATE('2008-03-05 00:53:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:34 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54608 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54538,54609,0,53087,TO_DATE('2008-03-05 00:53:34','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','Y','Port',100,0,TO_DATE('2008-03-05 00:53:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54609 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54539,54610,0,53087,TO_DATE('2008-03-05 00:53:35','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','N','Account',110,0,TO_DATE('2008-03-05 00:53:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54610 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54540,54611,0,53087,TO_DATE('2008-03-05 00:53:36','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','Y','PasswordInfo',120,0,TO_DATE('2008-03-05 00:53:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54611 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53075,'7',TO_DATE('2008-03-05 00:53:36','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Export Processor Parameter','L','EXP_ProcessorParameter',TO_DATE('2008-03-05 00:53:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53075 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:53:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53092,TO_DATE('2008-03-05 00:53:37','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table EXP_ProcessorParameter',1,'Y','N','Y','Y','EXP_ProcessorParameter','N',1000000,TO_DATE('2008-03-05 00:53:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53378,0,'EXP_ProcessorParameter_ID',TO_DATE('2008-03-05 00:53:37','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','EXP_ProcessorParameter_ID','EXP_ProcessorParameter_ID',TO_DATE('2008-03-05 00:53:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53378 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54541,53378,0,13,53075,'EXP_ProcessorParameter_ID',TO_DATE('2008-03-05 00:53:37','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','EXP_ProcessorParameter_ID',10,TO_DATE('2008-03-05 00:53:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54541 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:38 AM CST
-- Replication Data Functionality
CREATE TABLE EXP_ProcessorParameter (EXP_ProcessorParameter_ID NUMBER(10) NOT NULL, CONSTRAINT EXP_ProcessorParameter_Key PRIMARY KEY (EXP_ProcessorParameter_ID))
;

-- Mar 5, 2008 12:53:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54542,53367,0,19,53075,'EXP_Processor_ID',TO_DATE('2008-03-05 00:53:38','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','Y','Y','N','Y','N','N','EXP_Processor_ID',20,TO_DATE('2008-03-05 00:53:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54542 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:39 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD EXP_Processor_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:53:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54543,102,0,19,53075,129,'AD_Client_ID',TO_DATE('2008-03-05 00:53:39','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',30,TO_DATE('2008-03-05 00:53:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54543 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:41 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:53:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54544,113,0,19,53075,104,'AD_Org_ID',TO_DATE('2008-03-05 00:53:41','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',40,TO_DATE('2008-03-05 00:53:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54544 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:41 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:53:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54545,348,0,20,53075,'IsActive',TO_DATE('2008-03-05 00:53:41','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',50,TO_DATE('2008-03-05 00:53:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54545 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:42 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:53:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54546,245,0,16,53075,'Created',TO_DATE('2008-03-05 00:53:42','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',60,TO_DATE('2008-03-05 00:53:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54546 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:43 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:53:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54547,246,0,18,110,53075,'CreatedBy',TO_DATE('2008-03-05 00:53:43','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',70,TO_DATE('2008-03-05 00:53:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54547 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:43 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:53:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54548,607,0,16,53075,'Updated',TO_DATE('2008-03-05 00:53:43','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',80,TO_DATE('2008-03-05 00:53:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54548 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:44 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:53:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54549,608,0,18,110,53075,'UpdatedBy',TO_DATE('2008-03-05 00:53:44','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',90,TO_DATE('2008-03-05 00:53:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54549 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:45 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:53:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54550,620,0,10,53075,'Value',TO_DATE('2008-03-05 00:53:45','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',100,TO_DATE('2008-03-05 00:53:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54550 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:46 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:53:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54551,469,0,10,53075,'Name',TO_DATE('2008-03-05 00:53:46','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',110,TO_DATE('2008-03-05 00:53:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54551 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:46 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:53:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54552,275,0,10,53075,'Description',TO_DATE('2008-03-05 00:53:46','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',120,TO_DATE('2008-03-05 00:53:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54552 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:47 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:53:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54553,326,0,14,53075,'Help',TO_DATE('2008-03-05 00:53:47','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',130,TO_DATE('2008-03-05 00:53:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54553 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:48 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:53:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53379,0,'ParameterValue',TO_DATE('2008-03-05 00:53:48','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','ParameterValue','ParameterValue',TO_DATE('2008-03-05 00:53:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53379 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:53:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54554,53379,0,10,53075,'ParameterValue',TO_DATE('2008-03-05 00:53:48','YYYY-MM-DD HH24:MI:SS'),0,'EE05',60,'Y','N','N','Y','N','N','N','N','Y','N','Y','ParameterValue',140,TO_DATE('2008-03-05 00:53:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54554 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:49 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_ProcessorParameter ADD ParameterValue NVARCHAR2(60)
;

-- Mar 5, 2008 12:53:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53088,53075,53026,NULL,TO_DATE('2008-03-05 00:53:49','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','Y','N','N','Export Processor Parameter','N',20,1,TO_DATE('2008-03-05 00:53:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53088 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:53:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54541,54612,0,53088,TO_DATE('2008-03-05 00:53:50','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','EXP_ProcessorParameter_ID',0,0,TO_DATE('2008-03-05 00:53:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54612 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54543,54613,0,53088,TO_DATE('2008-03-05 00:53:50','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:53:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54613 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54544,54614,0,53088,TO_DATE('2008-03-05 00:53:51','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:53:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54614 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54542,54615,0,53088,TO_DATE('2008-03-05 00:53:51','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','Y','N','N','Y','N','EXP_Processor_ID',30,0,TO_DATE('2008-03-05 00:53:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54615 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54550,54616,0,53088,TO_DATE('2008-03-05 00:53:53','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',40,0,TO_DATE('2008-03-05 00:53:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54616 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54551,54617,0,53088,TO_DATE('2008-03-05 00:53:53','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_DATE('2008-03-05 00:53:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54617 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54552,54618,0,53088,TO_DATE('2008-03-05 00:53:54','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_DATE('2008-03-05 00:53:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54618 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54545,54619,0,53088,TO_DATE('2008-03-05 00:53:54','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_DATE('2008-03-05 00:53:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54619 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54553,54620,0,53088,TO_DATE('2008-03-05 00:53:55','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_DATE('2008-03-05 00:53:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54620 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54554,54621,0,53088,TO_DATE('2008-03-05 00:53:56','YYYY-MM-DD HH24:MI:SS'),0,20,'EE05','Y','Y','Y','N','N','N','N','ParameterValue',90,0,TO_DATE('2008-03-05 00:53:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54621 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53027,TO_DATE('2008-03-05 00:53:56','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','Y','Export Processor Type','N',TO_DATE('2008-03-05 00:53:56','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53027 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53027,TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53027,TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53027,TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53027,TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53076,53027,'7',TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Export Processor Type','L','EXP_Processor_Type',TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53076 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:53:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53093,TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table EXP_Processor_Type',1,'Y','N','Y','Y','EXP_Processor_Type','N',1000000,TO_DATE('2008-03-05 00:53:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:53:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54555,53373,0,13,53076,'EXP_Processor_Type_ID',TO_DATE('2008-03-05 00:53:58','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','EXP_Processor_Type_ID',10,TO_DATE('2008-03-05 00:53:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54555 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:58 AM CST
-- Replication Data Functionality
CREATE TABLE EXP_Processor_Type (EXP_Processor_Type_ID NUMBER(10) NOT NULL, CONSTRAINT EXP_Processor_Type_Key PRIMARY KEY (EXP_Processor_Type_ID))
;

-- Mar 5, 2008 12:53:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54556,102,0,19,53076,129,'AD_Client_ID',TO_DATE('2008-03-05 00:53:58','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',20,TO_DATE('2008-03-05 00:53:58','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:53:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54556 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:53:59 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:54:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54557,113,0,19,53076,104,'AD_Org_ID',TO_DATE('2008-03-05 00:53:59','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',30,TO_DATE('2008-03-05 00:53:59','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54557 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:00 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:54:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54558,348,0,20,53076,'IsActive',TO_DATE('2008-03-05 00:54:00','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',40,TO_DATE('2008-03-05 00:54:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54558 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:00 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:54:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54559,245,0,16,53076,'Created',TO_DATE('2008-03-05 00:54:00','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',50,TO_DATE('2008-03-05 00:54:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54559 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:01 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:54:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54560,246,0,18,110,53076,'CreatedBy',TO_DATE('2008-03-05 00:54:01','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',60,TO_DATE('2008-03-05 00:54:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54560 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:02 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:54:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54561,607,0,16,53076,'Updated',TO_DATE('2008-03-05 00:54:02','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',70,TO_DATE('2008-03-05 00:54:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54561 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:02 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:54:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54562,608,0,18,110,53076,'UpdatedBy',TO_DATE('2008-03-05 00:54:02','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',80,TO_DATE('2008-03-05 00:54:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54562 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:03 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:54:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54563,620,0,10,53076,'Value',TO_DATE('2008-03-05 00:54:03','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',90,TO_DATE('2008-03-05 00:54:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54563 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:04 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:54:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54564,469,0,10,53076,'Name',TO_DATE('2008-03-05 00:54:04','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',100,TO_DATE('2008-03-05 00:54:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54564 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:04 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:54:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54565,275,0,10,53076,'Description',TO_DATE('2008-03-05 00:54:04','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',110,TO_DATE('2008-03-05 00:54:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54565 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:05 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:54:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54566,326,0,14,53076,'Help',TO_DATE('2008-03-05 00:54:05','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',120,TO_DATE('2008-03-05 00:54:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54566 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:06 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:54:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53380,0,'JavaClass',TO_DATE('2008-03-05 00:54:06','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','JavaClass','JavaClass',TO_DATE('2008-03-05 00:54:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53380 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:54:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54567,53380,0,10,53076,'JavaClass',TO_DATE('2008-03-05 00:54:06','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','Y','N','N','Y','N','Y','JavaClass',130,TO_DATE('2008-03-05 00:54:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54567 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:07 AM CST
-- Replication Data Functionality
ALTER TABLE EXP_Processor_Type ADD JavaClass NVARCHAR2(255) NOT NULL
;

-- Mar 5, 2008 12:54:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53089,53076,53027,NULL,TO_DATE('2008-03-05 00:54:07','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Export Processor Type','N',10,0,TO_DATE('2008-03-05 00:54:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53089 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:54:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54555,54622,0,53089,TO_DATE('2008-03-05 00:54:07','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','EXP_Processor_Type_ID',0,0,TO_DATE('2008-03-05 00:54:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54622 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54556,54623,0,53089,TO_DATE('2008-03-05 00:54:08','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:54:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54623 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54557,54624,0,53089,TO_DATE('2008-03-05 00:54:09','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:54:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54624 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54563,54625,0,53089,TO_DATE('2008-03-05 00:54:09','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2008-03-05 00:54:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54625 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54564,54626,0,53089,TO_DATE('2008-03-05 00:54:10','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2008-03-05 00:54:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54626 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54565,54627,0,53089,TO_DATE('2008-03-05 00:54:11','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2008-03-05 00:54:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54627 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54558,54628,0,53089,TO_DATE('2008-03-05 00:54:13','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_DATE('2008-03-05 00:54:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54628 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54566,54629,0,53089,TO_DATE('2008-03-05 00:54:13','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_DATE('2008-03-05 00:54:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54629 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54567,54630,0,53089,TO_DATE('2008-03-05 00:54:14','YYYY-MM-DD HH24:MI:SS'),0,60,'EE05','Y','Y','Y','N','N','N','N','JavaClass',80,0,TO_DATE('2008-03-05 00:54:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54630 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53028,TO_DATE('2008-03-05 00:54:14','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','Y','Import Processor','N',TO_DATE('2008-03-05 00:54:14','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 5, 2008 12:54:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53028 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 5, 2008 12:54:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53028,TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53028,TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53028,TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53028,TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53077,53028,'3',TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Import Processor','L','IMP_Processor',TO_DATE('2008-03-05 00:54:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53077 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:54:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53094,TO_DATE('2008-03-05 00:54:16','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table IMP_Processor',1,'Y','N','Y','Y','IMP_Processor','N',1000000,TO_DATE('2008-03-05 00:54:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53381,0,'IMP_Processor_ID',TO_DATE('2008-03-05 00:54:17','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','IMP_Processor_ID','IMP_Processor_ID',TO_DATE('2008-03-05 00:54:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53381 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:54:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54568,53381,0,13,53077,'IMP_Processor_ID',TO_DATE('2008-03-05 00:54:17','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','IMP_Processor_ID',10,TO_DATE('2008-03-05 00:54:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54568 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:18 AM CST
-- Replication Data Functionality
CREATE TABLE IMP_Processor (IMP_Processor_ID NUMBER(10) NOT NULL, CONSTRAINT IMP_Processor_Key PRIMARY KEY (IMP_Processor_ID))
;

-- Mar 5, 2008 12:54:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53382,0,'IMP_Processor_Type_ID',TO_DATE('2008-03-05 00:54:18','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','IMP_Processor_Type_ID','IMP_Processor_Type_ID',TO_DATE('2008-03-05 00:54:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53382 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:54:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54569,53382,0,19,53077,'IMP_Processor_Type_ID',TO_DATE('2008-03-05 00:54:18','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','Y','N','N','Y','N','Y','IMP_Processor_Type_ID',20,TO_DATE('2008-03-05 00:54:18','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54569 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:19 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD IMP_Processor_Type_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:54:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54570,102,0,19,53077,129,'AD_Client_ID',TO_DATE('2008-03-05 00:54:19','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',30,TO_DATE('2008-03-05 00:54:19','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54570 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:20 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:54:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54571,113,0,19,53077,104,'AD_Org_ID',TO_DATE('2008-03-05 00:54:20','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',40,TO_DATE('2008-03-05 00:54:20','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:20 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54571 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:21 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:54:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54572,348,0,20,53077,'IsActive',TO_DATE('2008-03-05 00:54:21','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',50,TO_DATE('2008-03-05 00:54:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54572 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:21 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:54:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54573,245,0,16,53077,'Created',TO_DATE('2008-03-05 00:54:21','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',60,TO_DATE('2008-03-05 00:54:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54573 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:22 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:54:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54574,246,0,18,110,53077,'CreatedBy',TO_DATE('2008-03-05 00:54:22','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',70,TO_DATE('2008-03-05 00:54:22','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54574 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:23 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:54:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54575,607,0,16,53077,'Updated',TO_DATE('2008-03-05 00:54:23','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',80,TO_DATE('2008-03-05 00:54:23','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54575 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:24 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:54:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54576,608,0,18,110,53077,'UpdatedBy',TO_DATE('2008-03-05 00:54:24','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',90,TO_DATE('2008-03-05 00:54:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54576 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:24 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:54:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54577,620,0,10,53077,'Value',TO_DATE('2008-03-05 00:54:24','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',100,TO_DATE('2008-03-05 00:54:24','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54577 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:25 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:54:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54578,469,0,10,53077,'Name',TO_DATE('2008-03-05 00:54:25','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',110,TO_DATE('2008-03-05 00:54:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54578 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:26 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:54:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54579,275,0,10,53077,'Description',TO_DATE('2008-03-05 00:54:26','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',120,TO_DATE('2008-03-05 00:54:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54579 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:26 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54580,326,0,14,53077,'Help',TO_DATE('2008-03-05 00:54:26','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',130,TO_DATE('2008-03-05 00:54:26','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54580 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Processor Frequency Type', EntityType='D', Help=NULL, IsActive='Y', Name='_Frequency Type', ValidationType='L',Updated=TO_DATE('2008-03-05 00:54:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=221
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=221
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_List SET AD_Reference_ID=221, Description=NULL, EntityType='D', IsActive='Y', Name='Minute', Value='M',Updated=TO_DATE('2008-03-05 00:54:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=439
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=439
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_List SET AD_Reference_ID=221, Description=NULL, EntityType='D', IsActive='Y', Name='Hour', Value='H',Updated=TO_DATE('2008-03-05 00:54:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=440
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=440
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_List SET AD_Reference_ID=221, Description=NULL, EntityType='D', IsActive='Y', Name='Day', Value='D',Updated=TO_DATE('2008-03-05 00:54:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=441
;

-- Mar 5, 2008 12:54:27 AM CST
-- Replication Data Functionality
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=441
;

-- Mar 5, 2008 12:54:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54581,1507,0,17,221,53077,'FrequencyType',TO_DATE('2008-03-05 00:54:27','YYYY-MM-DD HH24:MI:SS'),0,'Frequency of event','EE05',1,'The frequency type is used for calculating the date of the next event.','Y','N','N','N','N','Y','N','N','Y','N','Y','Frequency Type',140,TO_DATE('2008-03-05 00:54:27','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54581 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:28 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD FrequencyType CHAR(1) NOT NULL
;

-- Mar 5, 2008 12:54:30 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54582,1506,0,11,53077,'Frequency',TO_DATE('2008-03-05 00:54:28','YYYY-MM-DD HH24:MI:SS'),0,'Frequency of events','EE05',14,'The frequency is used in conjunction with the frequency type in determining an event. Example: If the Frequency Type is Week and the Frequency is 2 - it is every two weeks.','Y','N','N','N','N','Y','N','N','Y','N','Y','Frequency',150,TO_DATE('2008-03-05 00:54:28','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:30 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54582 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:30 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Frequency NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:54:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54583,1089,0,16,53077,'DateLastRun',TO_DATE('2008-03-05 00:54:30','YYYY-MM-DD HH24:MI:SS'),0,'Date the process was last run.','EE05',7,'The Date Last Run indicates the last time that a process was run.','Y','N','N','N','N','N','N','N','Y','N','Y','Date last run',160,TO_DATE('2008-03-05 00:54:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54583 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:38 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD DateLastRun DATE
;

-- Mar 5, 2008 12:54:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54584,1090,0,16,53077,'DateNextRun',TO_DATE('2008-03-05 00:54:38','YYYY-MM-DD HH24:MI:SS'),0,'Date the process will run next','EE05',7,'The Date Next Run indicates the next time this process will run.','Y','N','N','N','N','N','N','N','Y','N','Y','Date next run',170,TO_DATE('2008-03-05 00:54:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54584 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:39 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD DateNextRun DATE
;

-- Mar 5, 2008 12:54:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54585,2407,0,11,53077,'KeepLogDays',TO_DATE('2008-03-05 00:54:39','YYYY-MM-DD HH24:MI:SS'),0,'7','Number of days to keep the log entries','EE05',14,'Older Log entries may be deleted','Y','N','N','N','N','Y','N','N','Y','N','Y','Days to keep Log',180,TO_DATE('2008-03-05 00:54:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54585 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:40 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD KeepLogDays NUMBER(10) DEFAULT 7 NOT NULL
;

-- Mar 5, 2008 12:54:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54586,524,0,28,53077,'Processing',TO_DATE('2008-03-05 00:54:40','YYYY-MM-DD HH24:MI:SS'),0,'EE05',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',190,TO_DATE('2008-03-05 00:54:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:40 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54586 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:40 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Processing CHAR(1)
;

-- Mar 5, 2008 12:54:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54587,53374,0,10,53077,'Host',TO_DATE('2008-03-05 00:54:40','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Host',200,TO_DATE('2008-03-05 00:54:40','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54587 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:41 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Host NVARCHAR2(255)
;

-- Mar 5, 2008 12:54:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54588,53375,0,11,53077,'Port',TO_DATE('2008-03-05 00:54:41','YYYY-MM-DD HH24:MI:SS'),0,'EE05',14,'Y','N','N','N','N','N','N','N','Y','N','Y','Port',210,TO_DATE('2008-03-05 00:54:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54588 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:42 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Port NUMBER(10)
;

-- Mar 5, 2008 12:54:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54589,53376,0,10,53077,'Account',TO_DATE('2008-03-05 00:54:42','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Account',220,TO_DATE('2008-03-05 00:54:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54589 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:43 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD Account NVARCHAR2(255)
;

-- Mar 5, 2008 12:54:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54590,53377,0,10,53077,'PasswordInfo',TO_DATE('2008-03-05 00:54:43','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','N','N','N','Y','N','Y','PasswordInfo',230,TO_DATE('2008-03-05 00:54:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:54:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54590 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:54:44 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor ADD PasswordInfo NVARCHAR2(255)
;

-- Mar 5, 2008 12:54:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53090,53077,53028,NULL,TO_DATE('2008-03-05 00:54:44','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Import Processor','N',10,0,TO_DATE('2008-03-05 00:54:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53090 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:54:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54568,54631,0,53090,TO_DATE('2008-03-05 00:54:44','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','IMP_Processor_ID',0,0,TO_DATE('2008-03-05 00:54:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54631 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54570,54632,0,53090,TO_DATE('2008-03-05 00:54:45','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:54:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54632 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54571,54633,0,53090,TO_DATE('2008-03-05 00:54:46','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:54:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54633 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54577,54634,0,53090,TO_DATE('2008-03-05 00:54:47','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2008-03-05 00:54:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54634 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54578,54635,0,53090,TO_DATE('2008-03-05 00:54:49','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2008-03-05 00:54:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54635 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54572,54636,0,53090,TO_DATE('2008-03-05 00:54:55','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_DATE('2008-03-05 00:54:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54636 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54579,54637,0,53090,TO_DATE('2008-03-05 00:54:55','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',70,0,TO_DATE('2008-03-05 00:54:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54637 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54580,54638,0,53090,TO_DATE('2008-03-05 00:54:56','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_DATE('2008-03-05 00:54:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54638 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54569,54639,0,53090,TO_DATE('2008-03-05 00:54:57','YYYY-MM-DD HH24:MI:SS'),0,20,'EE05','Y','Y','Y','N','N','N','N','IMP_Processor_Type_ID',84,0,TO_DATE('2008-03-05 00:54:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54639 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54581,54640,0,53090,TO_DATE('2008-03-05 00:54:57','YYYY-MM-DD HH24:MI:SS'),0,'Frequency of event',10,'EE05','The frequency type is used for calculating the date of the next event.','Y','Y','Y','N','N','N','N','Frequency Type',90,0,TO_DATE('2008-03-05 00:54:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54640 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54582,54641,0,53090,TO_DATE('2008-03-05 00:54:58','YYYY-MM-DD HH24:MI:SS'),0,'Frequency of events',10,'EE05','The frequency is used in conjunction with the frequency type in determining an event. Example: If the Frequency Type is Week and the Frequency is 2 - it is every two weeks.','Y','Y','Y','N','N','N','Y','Frequency',100,0,TO_DATE('2008-03-05 00:54:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54641 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:54:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54585,54642,0,53090,TO_DATE('2008-03-05 00:54:59','YYYY-MM-DD HH24:MI:SS'),0,'Number of days to keep the log entries',10,'EE05','Older Log entries may be deleted','Y','Y','Y','N','N','N','N','Days to keep Log',110,0,TO_DATE('2008-03-05 00:54:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:54:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54642 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54583,54643,0,53090,TO_DATE('2008-03-05 00:54:59','YYYY-MM-DD HH24:MI:SS'),0,'Date the process was last run.',10,'EE05','The Date Last Run indicates the last time that a process was run.','Y','Y','Y','N','N','Y','N','Date last run',120,0,TO_DATE('2008-03-05 00:54:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54643 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54584,54644,0,53090,TO_DATE('2008-03-05 00:55:00','YYYY-MM-DD HH24:MI:SS'),0,'Date the process will run next',10,'EE05','The Date Next Run indicates the next time this process will run.','Y','Y','Y','N','N','Y','Y','Date next run',130,0,TO_DATE('2008-03-05 00:55:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54644 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54587,54645,0,53090,TO_DATE('2008-03-05 00:55:01','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','N','Host',140,0,TO_DATE('2008-03-05 00:55:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54645 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54588,54646,0,53090,TO_DATE('2008-03-05 00:55:02','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','Y','Port',150,0,TO_DATE('2008-03-05 00:55:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54646 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54589,54647,0,53090,TO_DATE('2008-03-05 00:55:03','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','N','Account',160,0,TO_DATE('2008-03-05 00:55:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54647 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54590,54648,0,53090,TO_DATE('2008-03-05 00:55:03','YYYY-MM-DD HH24:MI:SS'),0,10,'EE05','Y','Y','Y','N','N','N','Y','PasswordInfo',170,0,TO_DATE('2008-03-05 00:55:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54648 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53078,'7',TO_DATE('2008-03-05 00:55:04','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','Export Processor Parameter','L','IMP_ProcessorParameter',TO_DATE('2008-03-05 00:55:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53078 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:55:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53095,TO_DATE('2008-03-05 00:55:05','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table IMP_ProcessorParameter',1,'Y','N','Y','Y','IMP_ProcessorParameter','N',1000000,TO_DATE('2008-03-05 00:55:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53383,0,'IMP_ProcessorParameter_ID',TO_DATE('2008-03-05 00:55:05','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','IMP_ProcessorParameter_ID','IMP_ProcessorParameter_ID',TO_DATE('2008-03-05 00:55:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53383 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:55:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54591,53383,0,13,53078,'IMP_ProcessorParameter_ID',TO_DATE('2008-03-05 00:55:05','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','IMP_ProcessorParameter_ID',10,TO_DATE('2008-03-05 00:55:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54591 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:06 AM CST
-- Replication Data Functionality
CREATE TABLE IMP_ProcessorParameter (IMP_ProcessorParameter_ID NUMBER(10) NOT NULL, CONSTRAINT IMP_ProcessorParameter_Key PRIMARY KEY (IMP_ProcessorParameter_ID))
;

-- Mar 5, 2008 12:55:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54592,53381,0,19,53078,'IMP_Processor_ID',TO_DATE('2008-03-05 00:55:06','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','Y','Y','N','Y','N','N','IMP_Processor_ID',20,TO_DATE('2008-03-05 00:55:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54592 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:08 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD IMP_Processor_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:55:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54593,102,0,19,53078,129,'AD_Client_ID',TO_DATE('2008-03-05 00:55:08','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',30,TO_DATE('2008-03-05 00:55:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54593 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:08 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:55:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54594,113,0,19,53078,104,'AD_Org_ID',TO_DATE('2008-03-05 00:55:08','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',40,TO_DATE('2008-03-05 00:55:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54594 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:09 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:55:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54595,348,0,20,53078,'IsActive',TO_DATE('2008-03-05 00:55:09','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',50,TO_DATE('2008-03-05 00:55:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54595 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:10 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:55:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54596,245,0,16,53078,'Created',TO_DATE('2008-03-05 00:55:10','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',60,TO_DATE('2008-03-05 00:55:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:10 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54596 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:10 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:55:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54597,246,0,18,110,53078,'CreatedBy',TO_DATE('2008-03-05 00:55:10','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',70,TO_DATE('2008-03-05 00:55:10','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54597 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:11 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:55:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54598,607,0,16,53078,'Updated',TO_DATE('2008-03-05 00:55:12','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',80,TO_DATE('2008-03-05 00:55:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54598 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:12 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:55:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54599,608,0,18,110,53078,'UpdatedBy',TO_DATE('2008-03-05 00:55:12','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',90,TO_DATE('2008-03-05 00:55:12','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54599 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:14 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:55:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54600,620,0,10,53078,'Value',TO_DATE('2008-03-05 00:55:14','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',100,TO_DATE('2008-03-05 00:55:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54600 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:14 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:55:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54601,469,0,10,53078,'Name',TO_DATE('2008-03-05 00:55:14','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',110,TO_DATE('2008-03-05 00:55:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54601 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:15 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:55:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54602,275,0,10,53078,'Description',TO_DATE('2008-03-05 00:55:15','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',120,TO_DATE('2008-03-05 00:55:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54602 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:16 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:55:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54603,326,0,14,53078,'Help',TO_DATE('2008-03-05 00:55:16','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',130,TO_DATE('2008-03-05 00:55:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54603 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:17 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:55:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54604,53379,0,10,53078,'ParameterValue',TO_DATE('2008-03-05 00:55:17','YYYY-MM-DD HH24:MI:SS'),0,'EE05',60,'Y','N','N','Y','N','N','N','N','Y','N','Y','ParameterValue',140,TO_DATE('2008-03-05 00:55:17','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54604 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:18 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorParameter ADD ParameterValue NVARCHAR2(60)
;

-- Mar 5, 2008 12:55:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53091,53078,53028,NULL,TO_DATE('2008-03-05 00:55:18','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Parameter','N',20,1,TO_DATE('2008-03-05 00:55:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:19 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53091 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:55:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54591,54649,0,53091,TO_DATE('2008-03-05 00:55:19','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','Y','N','IMP_ProcessorParameter_ID',0,0,TO_DATE('2008-03-05 00:55:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54649 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54593,54650,0,53091,TO_DATE('2008-03-05 00:55:21','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_DATE('2008-03-05 00:55:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54650 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54594,54651,0,53091,TO_DATE('2008-03-05 00:55:23','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',20,0,TO_DATE('2008-03-05 00:55:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54651 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54592,54652,0,53091,TO_DATE('2008-03-05 00:55:24','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','Y','N','N','Y','N','IMP_Processor_ID',30,0,TO_DATE('2008-03-05 00:55:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54652 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54600,54653,0,53091,TO_DATE('2008-03-05 00:55:24','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',40,0,TO_DATE('2008-03-05 00:55:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54653 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54601,54654,0,53091,TO_DATE('2008-03-05 00:55:25','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_DATE('2008-03-05 00:55:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54654 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54602,54655,0,53091,TO_DATE('2008-03-05 00:55:25','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_DATE('2008-03-05 00:55:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:26 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54655 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54595,54656,0,53091,TO_DATE('2008-03-05 00:55:26','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_DATE('2008-03-05 00:55:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:27 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54656 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54603,54657,0,53091,TO_DATE('2008-03-05 00:55:27','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_DATE('2008-03-05 00:55:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:28 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54657 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54604,54658,0,53091,TO_DATE('2008-03-05 00:55:28','YYYY-MM-DD HH24:MI:SS'),0,20,'EE05','Y','Y','Y','N','N','N','N','ParameterValue',90,0,TO_DATE('2008-03-05 00:55:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54658 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53079,'3',TO_DATE('2008-03-05 00:55:29','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','IMP_ProcessorLog','L','IMP_ProcessorLog',TO_DATE('2008-03-05 00:55:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:29 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53079 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:55:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53096,TO_DATE('2008-03-05 00:55:29','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table IMP_ProcessorLog',1,'Y','N','Y','Y','IMP_ProcessorLog','N',1000000,TO_DATE('2008-03-05 00:55:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53384,0,'IMP_ProcessorLog_ID',TO_DATE('2008-03-05 00:55:35','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','IMP_ProcessorLog_ID','IMP_ProcessorLog_ID',TO_DATE('2008-03-05 00:55:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:35 AM CST
-- Replication Data Functionality
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53384 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Mar 5, 2008 12:55:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54605,53384,0,13,53079,'IMP_ProcessorLog_ID',TO_DATE('2008-03-05 00:55:35','YYYY-MM-DD HH24:MI:SS'),0,'EE05',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','IMP_ProcessorLog_ID',10,TO_DATE('2008-03-05 00:55:35','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54605 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:36 AM CST
-- Replication Data Functionality
CREATE TABLE IMP_ProcessorLog (IMP_ProcessorLog_ID NUMBER(10) NOT NULL, CONSTRAINT IMP_ProcessorLog_Key PRIMARY KEY (IMP_ProcessorLog_ID))
;

-- Mar 5, 2008 12:55:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54606,53381,0,19,53079,'IMP_Processor_ID',TO_DATE('2008-03-05 00:55:36','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','N','Y','Y','N','Y','N','N','IMP_Processor_ID',20,TO_DATE('2008-03-05 00:55:36','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:36 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54606 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:37 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD IMP_Processor_ID NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:55:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54607,102,0,19,53079,129,'AD_Client_ID',TO_DATE('2008-03-05 00:55:37','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',30,TO_DATE('2008-03-05 00:55:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:37 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54607 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:37 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:55:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54608,113,0,19,53079,104,'AD_Org_ID',TO_DATE('2008-03-05 00:55:37','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',40,TO_DATE('2008-03-05 00:55:37','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:38 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54608 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:38 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:55:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54609,348,0,20,53079,'IsActive',TO_DATE('2008-03-05 00:55:38','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',50,TO_DATE('2008-03-05 00:55:38','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:39 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54609 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:39 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:55:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54610,245,0,16,53079,'Created',TO_DATE('2008-03-05 00:55:39','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',60,TO_DATE('2008-03-05 00:55:39','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:41 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54610 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:41 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:55:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54611,246,0,18,110,53079,'CreatedBy',TO_DATE('2008-03-05 00:55:41','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',70,TO_DATE('2008-03-05 00:55:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54611 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:42 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:55:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54612,607,0,16,53079,'Updated',TO_DATE('2008-03-05 00:55:42','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',80,TO_DATE('2008-03-05 00:55:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:43 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54612 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:43 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:55:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54613,608,0,18,110,53079,'UpdatedBy',TO_DATE('2008-03-05 00:55:43','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',90,TO_DATE('2008-03-05 00:55:43','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:44 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54613 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:44 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:55:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54614,275,0,10,53079,'Description',TO_DATE('2008-03-05 00:55:44','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',100,TO_DATE('2008-03-05 00:55:44','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54614 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:45 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:55:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54615,326,0,14,53079,'Help',TO_DATE('2008-03-05 00:55:45','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',110,TO_DATE('2008-03-05 00:55:45','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:45 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54615 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:46 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:55:46 AM CST
-- Replication Data Functionality
UPDATE AD_Reference SET Description='Binary Data', EntityType='D', Help=NULL, IsActive='Y', Name='Binary', ValidationType='D',Updated=TO_DATE('2008-03-05 00:55:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=23
;

-- Mar 5, 2008 12:55:46 AM CST
-- Replication Data Functionality
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=23
;

-- Mar 5, 2008 12:55:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54616,174,0,23,53079,'BinaryData',TO_DATE('2008-03-05 00:55:46','YYYY-MM-DD HH24:MI:SS'),0,'Binary Data','EE05','The Binary field stores binary data.','Y','N','N','N','N','N','N','N','Y','N','Y','BinaryData',120,TO_DATE('2008-03-05 00:55:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:46 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54616 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:46 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD BinaryData BLOB
;

-- Mar 5, 2008 12:55:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54617,2395,0,20,53079,'IsError',TO_DATE('2008-03-05 00:55:46','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','An Error occured in the execution','EE05',1,'Y','N','N','N','N','Y','N','N','Y','N','Y','Error',130,TO_DATE('2008-03-05 00:55:46','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:47 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54617 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:47 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD IsError CHAR(1) DEFAULT 'Y' CHECK (IsError IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:55:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54618,1521,0,14,53079,'Summary',TO_DATE('2008-03-05 00:55:47','YYYY-MM-DD HH24:MI:SS'),0,'Textual summary of this request','EE05',2000,'The Summary allows free form text entry of a recap of this request.','Y','N','N','N','N','N','N','N','Y','N','Y','Summary',140,TO_DATE('2008-03-05 00:55:47','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54618 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:48 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD Summary NVARCHAR2(2000)
;

-- Mar 5, 2008 12:55:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54619,2438,0,14,53079,'TextMsg',TO_DATE('2008-03-05 00:55:48','YYYY-MM-DD HH24:MI:SS'),0,'Text Message','EE05',2000,'Y','N','N','N','N','N','N','N','Y','N','Y','Text Message',150,TO_DATE('2008-03-05 00:55:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:48 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54619 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:48 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD TextMsg NVARCHAR2(2000)
;

-- Mar 5, 2008 12:55:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54620,539,0,10,53079,'Reference',TO_DATE('2008-03-05 00:55:48','YYYY-MM-DD HH24:MI:SS'),0,'Reference for this record','EE05',60,'The Reference displays the source document number.','Y','N','N','N','N','N','N','N','Y','N','Y','Reference',160,TO_DATE('2008-03-05 00:55:48','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:55:49 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54620 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:55:49 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_ProcessorLog ADD Reference NVARCHAR2(60)
;

-- Mar 5, 2008 12:55:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53092,53079,53028,NULL,TO_DATE('2008-03-05 00:55:49','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Log','N',30,1,TO_DATE('2008-03-05 00:55:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53092 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:55:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54605,54659,0,53092,TO_DATE('2008-03-05 00:55:50','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','Y','N','IMP_ProcessorLog_ID',0,0,TO_DATE('2008-03-05 00:55:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:50 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54659 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54607,54660,0,53092,TO_DATE('2008-03-05 00:55:50','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_DATE('2008-03-05 00:55:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:51 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54660 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:52 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54608,54661,0,53092,TO_DATE('2008-03-05 00:55:51','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','Y','Y','Organization',20,0,TO_DATE('2008-03-05 00:55:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:52 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54661 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54606,54662,0,53092,TO_DATE('2008-03-05 00:55:52','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','Y','N','N','Y','N','IMP_Processor_ID',30,0,TO_DATE('2008-03-05 00:55:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:53 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54662 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54609,54663,0,53092,TO_DATE('2008-03-05 00:55:53','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','Y','N','Active',40,0,TO_DATE('2008-03-05 00:55:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54663 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54614,54664,0,53092,TO_DATE('2008-03-05 00:55:54','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2008-03-05 00:55:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:54 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54664 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54615,54665,0,53092,TO_DATE('2008-03-05 00:55:54','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_DATE('2008-03-05 00:55:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54665 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54616,54666,0,53092,TO_DATE('2008-03-05 00:55:55','YYYY-MM-DD HH24:MI:SS'),0,'Binary Data',60,'EE05','The Binary field stores binary data.','Y','Y','Y','N','N','Y','N','BinaryData',70,0,TO_DATE('2008-03-05 00:55:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:55 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54666 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54617,54667,0,53092,TO_DATE('2008-03-05 00:55:55','YYYY-MM-DD HH24:MI:SS'),0,'An Error occured in the execution',10,'EE05','Y','Y','Y','N','N','Y','N','Error',80,0,TO_DATE('2008-03-05 00:55:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:56 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54667 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54620,54668,0,53092,TO_DATE('2008-03-05 00:55:56','YYYY-MM-DD HH24:MI:SS'),0,'Reference for this record',100,'EE05','The Reference displays the source document number.','Y','Y','Y','N','N','Y','N','Reference',90,0,TO_DATE('2008-03-05 00:55:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54668 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54618,54669,0,53092,TO_DATE('2008-03-05 00:55:57','YYYY-MM-DD HH24:MI:SS'),0,'Textual summary of this request',100,'EE05','The Summary allows free form text entry of a recap of this request.','Y','Y','Y','N','N','Y','N','Summary',100,0,TO_DATE('2008-03-05 00:55:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:57 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54669 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54619,54670,0,53092,TO_DATE('2008-03-05 00:55:57','YYYY-MM-DD HH24:MI:SS'),0,'Text Message',100,'EE05','Y','Y','Y','N','N','N','N','Text Message',110,0,TO_DATE('2008-03-05 00:55:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54670 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53029,TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','Y','Import Processor Type','N',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53029 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,0,53029,TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,102,53029,TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,103,53029,TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:58 AM CST
-- Replication Data Functionality
INSERT INTO AD_Window_Access (AD_Client_ID,AD_Org_ID,AD_Role_ID,AD_Window_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,0,50001,53029,TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53080,53029,'7',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','Y','N','N','N','ImportProcessor Type','L','IMP_Processor_Type',TO_DATE('2008-03-05 00:55:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:55:59 AM CST
-- Replication Data Functionality
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53080 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Mar 5, 2008 12:56:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53097,TO_DATE('2008-03-05 00:55:59','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table IMP_Processor_Type',1,'Y','N','Y','Y','IMP_Processor_Type','N',1000000,TO_DATE('2008-03-05 00:55:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54621,53382,0,13,53080,'IMP_Processor_Type_ID',TO_DATE('2008-03-05 00:56:00','YYYY-MM-DD HH24:MI:SS'),0,'EE05',10,'Y','N','N','N','Y','Y','N','N','Y','N','N','IMP_Processor_Type_ID',10,TO_DATE('2008-03-05 00:56:00','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:01 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54621 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:01 AM CST
-- Replication Data Functionality
CREATE TABLE IMP_Processor_Type (IMP_Processor_Type_ID NUMBER(10) NOT NULL, CONSTRAINT IMP_Processor_Type_Key PRIMARY KEY (IMP_Processor_Type_ID))
;

-- Mar 5, 2008 12:56:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54622,102,0,19,53080,129,'AD_Client_ID',TO_DATE('2008-03-05 00:56:01','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Client_ID@','Client/Tenant for this installation.','EE05',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','N','N','N','N','Y','N','N','Y','N','N','Client',20,TO_DATE('2008-03-05 00:56:01','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:02 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54622 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:02 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD AD_Client_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:56:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54623,113,0,19,53080,104,'AD_Org_ID',TO_DATE('2008-03-05 00:56:02','YYYY-MM-DD HH24:MI:SS'),0,'@AD_Org_ID@','Organizational entity within client','EE05',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','N','N','N','N','Y','N','N','Y','N','N','Organization',30,TO_DATE('2008-03-05 00:56:02','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:03 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54623 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:03 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD AD_Org_ID NUMBER(10) DEFAULT  NULL  NOT NULL
;

-- Mar 5, 2008 12:56:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54624,348,0,20,53080,'IsActive',TO_DATE('2008-03-05 00:56:03','YYYY-MM-DD HH24:MI:SS'),0,'''Y''','The record is active in the system','EE05',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','N','N','N','N','Y','N','N','Y','N','Y','Active',40,TO_DATE('2008-03-05 00:56:03','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54624 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:04 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL
;

-- Mar 5, 2008 12:56:04 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54625,245,0,16,53080,'Created',TO_DATE('2008-03-05 00:56:04','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was created','EE05',7,'The Created field indicates the date that this record was created.','Y','N','N','N','N','Y','N','N','Y','N','N','Created',50,TO_DATE('2008-03-05 00:56:04','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:05 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54625 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:05 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD Created DATE NOT NULL
;

-- Mar 5, 2008 12:56:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54626,246,0,18,110,53080,'CreatedBy',TO_DATE('2008-03-05 00:56:05','YYYY-MM-DD HH24:MI:SS'),0,'User who created this records','EE05',10,'The Created By field indicates the user who created this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Created By',60,TO_DATE('2008-03-05 00:56:05','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:06 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54626 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:06 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD CreatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:56:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54627,607,0,16,53080,'Updated',TO_DATE('2008-03-05 00:56:06','YYYY-MM-DD HH24:MI:SS'),0,'Date this record was updated','EE05',7,'The Updated field indicates the date that this record was updated.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated',70,TO_DATE('2008-03-05 00:56:06','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54627 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:07 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD Updated DATE NOT NULL
;

-- Mar 5, 2008 12:56:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54628,608,0,18,110,53080,'UpdatedBy',TO_DATE('2008-03-05 00:56:07','YYYY-MM-DD HH24:MI:SS'),0,'User who updated this records','EE05',10,'The Updated By field indicates the user who updated this record.','Y','N','N','N','N','Y','N','N','Y','N','N','Updated By',80,TO_DATE('2008-03-05 00:56:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:07 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54628 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:07 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD UpdatedBy NUMBER(10) NOT NULL
;

-- Mar 5, 2008 12:56:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54629,620,0,10,53080,'Value',TO_DATE('2008-03-05 00:56:07','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE05',40,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','Y','N','N','Y','N','Y','Search Key',90,TO_DATE('2008-03-05 00:56:07','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:08 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54629 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:08 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD Value NVARCHAR2(40) NOT NULL
;

-- Mar 5, 2008 12:56:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54630,469,0,10,53080,'Name',TO_DATE('2008-03-05 00:56:08','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE05',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',100,TO_DATE('2008-03-05 00:56:08','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:09 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54630 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:09 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD Name NVARCHAR2(60) NOT NULL
;

-- Mar 5, 2008 12:56:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54631,275,0,10,53080,'Description',TO_DATE('2008-03-05 00:56:09','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE05',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',110,TO_DATE('2008-03-05 00:56:09','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54631 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:11 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD Description NVARCHAR2(255)
;

-- Mar 5, 2008 12:56:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54632,326,0,14,53080,'Help',TO_DATE('2008-03-05 00:56:11','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE05',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',120,TO_DATE('2008-03-05 00:56:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:11 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54632 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:11 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD Help NVARCHAR2(2000)
;

-- Mar 5, 2008 12:56:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54633,53380,0,10,53080,'JavaClass',TO_DATE('2008-03-05 00:56:11','YYYY-MM-DD HH24:MI:SS'),0,'EE05',255,'Y','N','N','N','N','Y','N','N','Y','N','Y','JavaClass',130,TO_DATE('2008-03-05 00:56:11','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 12:56:12 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54633 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 12:56:12 AM CST
-- Replication Data Functionality
ALTER TABLE IMP_Processor_Type ADD JavaClass NVARCHAR2(255) NOT NULL
;

-- Mar 5, 2008 12:56:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53093,53080,53029,NULL,TO_DATE('2008-03-05 00:56:12','YYYY-MM-DD HH24:MI:SS'),0,'EE05','N','Y','N','N','Y','N','N','N','N','Import Processor Type','N',10,0,TO_DATE('2008-03-05 00:56:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53093 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Mar 5, 2008 12:56:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54621,54671,0,53093,TO_DATE('2008-03-05 00:56:13','YYYY-MM-DD HH24:MI:SS'),0,0,'EE05','Y','Y','N','N','N','N','N','IMP_Processor_Type_ID',0,0,TO_DATE('2008-03-05 00:56:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:13 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54671 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54622,54672,0,53093,TO_DATE('2008-03-05 00:56:13','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',0,'EE05','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_DATE('2008-03-05 00:56:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:14 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54672 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54623,54673,0,53093,TO_DATE('2008-03-05 00:56:14','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',0,'EE05','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_DATE('2008-03-05 00:56:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54673 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54629,54674,0,53093,TO_DATE('2008-03-05 00:56:15','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',0,'EE05','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_DATE('2008-03-05 00:56:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:15 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54674 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54630,54675,0,53093,TO_DATE('2008-03-05 00:56:15','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',0,'EE05','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_DATE('2008-03-05 00:56:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54675 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54631,54676,0,53093,TO_DATE('2008-03-05 00:56:16','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',60,'EE05','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_DATE('2008-03-05 00:56:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:16 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54676 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54624,54677,0,53093,TO_DATE('2008-03-05 00:56:16','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE05','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',60,0,TO_DATE('2008-03-05 00:56:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:17 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54677 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54632,54678,0,53093,TO_DATE('2008-03-05 00:56:17','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',60,'EE05','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_DATE('2008-03-05 00:56:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54678 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,54633,54679,0,53093,TO_DATE('2008-03-05 00:56:18','YYYY-MM-DD HH24:MI:SS'),0,60,'EE05','Y','Y','Y','N','N','N','N','JavaClass',80,0,TO_DATE('2008-03-05 00:56:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:18 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54679 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Mar 5, 2008 12:56:19 AM CST
-- Replication Data Functionality
UPDATE AD_WF_Node SET AD_Window_ID=53026, AD_Workflow_ID=50012, Action='W', Cost=0, Description='Setup of data replication', DocAction='CO', Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='EE05', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Setup Replication', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Export Processor', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=0, YPosition=0,Updated=TO_DATE('2008-03-05 00:56:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 12:56:19 AM CST
-- Replication Data Functionality
UPDATE AD_WF_Node SET AD_Window_ID=53029, AD_Workflow_ID=50012, Action='W', Cost=0, Description='Setup of data replication', DocAction='CO', Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='EE05', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Setup Replication', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Import Proccesor Type', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=0, YPosition=0,Updated=TO_DATE('2008-03-05 00:56:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 12:56:20 AM CST
-- Replication Data Functionality
UPDATE AD_WF_Node SET AD_Window_ID=53027, AD_Workflow_ID=50012, Action='W', Cost=0, Description='Setup of data replication', DocAction='CO', Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='EE05', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Setup Replication', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Export Proccesor Type', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=0, YPosition=0,Updated=TO_DATE('2008-03-05 00:56:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 12:56:20 AM CST
-- Replication Data Functionality
UPDATE AD_WF_Node SET AD_Window_ID=53028, AD_Workflow_ID=50012, Action='W', Cost=0, Description='Setup of data replication', DocAction='CO', Duration=0, DynPriorityChange=0, DynPriorityUnit=NULL, EntityType='EE05', IsActive='Y', IsCentrallyMaintained='N', JoinElement='X', Name='Setup Replication', Priority=0, SplitElement='X', StartMode=NULL, SubflowExecution=NULL, Value='Import Proccesor', WaitTime=0, WaitingTime=0, WorkingTime=0, XPosition=0, YPosition=0,Updated=TO_DATE('2008-03-05 00:56:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_WF_Node_ID=50064
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53098,0,TO_DATE('2008-03-05 00:56:20','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','Y','Replication Data',TO_DATE('2008-03-05 00:56:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53098 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 157,2, 10, 53098)
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
UPDATE AD_Menu SET AD_Window_ID=284, Action='W', Description='Maintain Data Replication Targets', EntityType='D', IsActive='N', IsReadOnly='N', IsSOTrx='Y', IsSummary='N', Name='Replication',Updated=TO_DATE('2008-03-05 00:56:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=385
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
UPDATE AD_TREENODEMM SET Parent_ID = 53098 , SeqNo = 1 WHERE AD_Tree_ID = 10 AND Node_ID = 385
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
UPDATE AD_Menu SET AD_Window_ID=285, Action='W', Description='Maintain Data Replication Strategy', EntityType='EE05', IsActive='Y', IsReadOnly='N', IsSOTrx='N', IsSummary='N', Name='Replication Strategy',Updated=TO_DATE('2008-03-05 00:56:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Menu_ID=386
;

-- Mar 5, 2008 12:56:21 AM CST
-- Replication Data Functionality
UPDATE AD_TREENODEMM SET Parent_ID = 53098 , SeqNo = 2 WHERE AD_Tree_ID = 10 AND Node_ID = 386
;

-- Mar 5, 2008 12:56:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Workflow_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53099,0,50012,'F',TO_DATE('2008-03-05 00:56:21','YYYY-MM-DD HH24:MI:SS'),0,'Setup of data replication','EE05','Y','N','N','N','Setup Replication',TO_DATE('2008-03-05 00:56:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:22 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53099 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:22 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53098,0, 10, 53099)
;

-- Mar 5, 2008 12:56:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53100,0,53025,'W',TO_DATE('2008-03-05 00:56:22','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Export Format',TO_DATE('2008-03-05 00:56:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53100 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:23 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53098,3, 10, 53100)
;

-- Mar 5, 2008 12:56:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53101,0,53026,'W',TO_DATE('2008-03-05 00:56:23','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Export Processor',TO_DATE('2008-03-05 00:56:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:23 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53101 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:23 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53098,5, 10, 53101)
;

-- Mar 5, 2008 12:56:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53102,0,53027,'W',TO_DATE('2008-03-05 00:56:23','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Export Processor Type',TO_DATE('2008-03-05 00:56:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53102 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:24 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53098,4, 10, 53102)
;

-- Mar 5, 2008 12:56:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53103,0,53028,'W',TO_DATE('2008-03-05 00:56:24','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Import Processor',TO_DATE('2008-03-05 00:56:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:24 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53103 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:24 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53098,7, 10, 53103)
;

-- Mar 5, 2008 12:56:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,Action,Created,CreatedBy,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53104,0,53029,'W',TO_DATE('2008-03-05 00:56:24','YYYY-MM-DD HH24:MI:SS'),0,'EE05','Y','N','N','N','Import Processor Type',TO_DATE('2008-03-05 00:56:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 12:56:25 AM CST
-- Replication Data Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53104 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Mar 5, 2008 12:56:25 AM CST
-- Replication Data Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53098,6, 10, 53104)
;

-- Mar 5, 2008 1:00:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,54635,2133,0,19,112,'AD_ReplicationStrategy_ID',TO_DATE('2008-03-05 01:00:41','YYYY-MM-DD HH24:MI:SS'),0,'Data Replication Strategy','EE05',10,'The Data Replication Strategy determines what and how tables are replicated ','Y','N','N','N','N','N','N','N','N','N','Y','Replication Strategy',0,TO_DATE('2008-03-05 01:00:41','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Mar 5, 2008 1:00:42 AM CST
-- Replication Data Functionality
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54635 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Mar 5, 2008 1:01:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,54635,54680,0,145,TO_DATE('2008-03-05 01:00:59','YYYY-MM-DD HH24:MI:SS'),0,'Data Replication Strategy',10,'EE05','The Data Replication Strategy determines what and how tables are replicated ','Y','Y','Y','N','N','N','N','N','Replication Strategy',TO_DATE('2008-03-05 01:00:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Mar 5, 2008 1:01:00 AM CST
-- Replication Data Functionality
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=54680 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;
-- Mar 5, 2008 1:23:35 AM CST
-- Replication Data Functionality
UPDATE AD_Column SET ColumnSQL=NULL,Updated=TO_DATE('2008-03-05 01:23:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54635
;

-- Mar 5, 2008 1:23:35 AM CST
-- Replication Data Functionality
UPDATE AD_Field SET Name='Replication Strategy', Description='Data Replication Strategy', Help='The Data Replication Strategy determines what and how tables are replicated ' WHERE AD_Column_ID=54635 AND IsCentrallyMaintained='Y'
;

-- Mar 5, 2008 1:23:37 AM CST
-- Replication Data Functionality
ALTER TABLE AD_Client ADD AD_ReplicationStrategy_ID NUMBER(10)
;

