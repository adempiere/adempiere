-- Sep 1, 2009 9:37:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_EntityType (AD_Client_ID,AD_EntityType_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,ModelPackage,Name,Processing,Updated,UpdatedBy,Version) VALUES (0,50006,0,TO_TIMESTAMP('2009-09-01 21:37:53','YYYY-MM-DD HH24:MI:SS'),0,'View & Smart Browse','EE07','License GPL2, Developer Victor Perez, Sponsor e-Evolution www.e-evolution.com

http://www.adempiere.com/index.php/Sponsored_Development:_Libero_Smart_Browse
','Y','org.adempiere.model','e-Evolution View & Smart Browse','N',TO_TIMESTAMP('2009-09-01 21:37:53','YYYY-MM-DD HH24:MI:SS'),0,'1')
;

-- Sep 1, 2009 9:40:05 PM ECT
-- View & Smat Browse
UPDATE AD_Table SET AD_Window_ID=105, AccessLevel='6', Description='Identifies a Menu', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Menu', ReplicationType='L', TableName='AD_Menu',Updated=TO_TIMESTAMP('2009-09-01 21:40:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=116
;

-- Sep 1, 2009 9:40:05 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Menu_ID', Description='Identifies a Menu', EntityType='D', Help='The Menu identifies a unique Menu.  Menus are used to control the display of those screens a user has access to.', IsActive='Y', Name='Menu', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Menu',Updated=TO_TIMESTAMP('2009-09-01 21:40:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=110
;

-- Sep 1, 2009 9:40:05 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=110
;

-- Sep 1, 2009 9:40:05 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='10 Digit Identifier', EntityType='D', Help=NULL, IsActive='Y', Name='ID', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=13
;

-- Sep 1, 2009 9:40:05 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=13
;

-- Sep 1, 2009 9:40:06 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=110, AD_Process_ID=NULL, AD_Reference_ID=13, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Menu_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a Menu', EntityType='D', FieldLength=22, Help='The Menu identifies a unique Menu.  Menus are used to control the display of those screens a user has access to.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='Y', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Menu', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=228
;

-- Sep 1, 2009 9:40:06 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Name', Description='Alphanumeric identifier of the entity', EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', IsActive='Y', Name='Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Name',Updated=TO_TIMESTAMP('2009-09-01 21:40:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=469
;

-- Sep 1, 2009 9:40:06 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=469
;

-- Sep 1, 2009 9:40:06 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Character String', EntityType='D', Help=NULL, IsActive='Y', Name='String', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=10
;

-- Sep 1, 2009 9:40:06 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=10
;

-- Sep 1, 2009 9:40:07 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, SeqNo=1, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=229
;

-- Sep 1, 2009 9:40:07 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Description', Description='Optional short description of the record', EntityType='D', Help='A description is limited to 255 characters.', IsActive='Y', Name='Description', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Description',Updated=TO_TIMESTAMP('2009-09-01 21:40:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=275
;

-- Sep 1, 2009 9:40:07 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=275
;

-- Sep 1, 2009 9:40:07 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='Y', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=230
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Action', Description='Indicates the Action to be performed', EntityType='D', Help='The Action field is a drop down list box which indicates the Action to be performed for this Item.', IsActive='Y', Name='Action', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Action',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=152
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=152
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Reference List', EntityType='D', Help=NULL, IsActive='Y', Name='List', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=17
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=17
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Menu Action list', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Menu Action', ValidationType='L',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=104
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=104
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List SET AD_Reference_ID=104, Description=NULL, EntityType='D', IsActive='Y', Name='Process', Value='P',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=106
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=106
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List SET AD_Reference_ID=104, Description=NULL, EntityType='D', IsActive='Y', Name='Report', Value='R',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=351
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=351
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List SET AD_Reference_ID=104, Description=NULL, EntityType='D', IsActive='Y', Name='Workbench', Value='B',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=486
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=486
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List SET AD_Reference_ID=104, Description=NULL, EntityType='D', IsActive='Y', Name='Window', Value='W',Updated=TO_TIMESTAMP('2009-09-01 21:40:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=103
;

-- Sep 1, 2009 9:40:08 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=103
;

-- Sep 1, 2009 9:40:09 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List SET AD_Reference_ID=104, Description=NULL, EntityType='D', IsActive='Y', Name='Task', Value='T',Updated=TO_TIMESTAMP('2009-09-01 21:40:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=104
;

-- Sep 1, 2009 9:40:09 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=104
;

-- Sep 1, 2009 9:40:09 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List SET AD_Reference_ID=104, Description=NULL, EntityType='D', IsActive='Y', Name='WorkFlow', Value='F',Updated=TO_TIMESTAMP('2009-09-01 21:40:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Ref_List_ID=105
;

-- Sep 1, 2009 9:40:09 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_List_Trl SET IsTranslated='N' WHERE AD_Ref_List_ID=105
;

-- Sep 1, 2009 9:40:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53506,104,TO_TIMESTAMP('2009-09-01 21:40:09','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Smart Browse',TO_TIMESTAMP('2009-09-01 21:40:09','YYYY-MM-DD HH24:MI:SS'),0,'S')
;

-- Sep 1, 2009 9:40:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53506 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Sep 1, 2009 9:40:10 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=152, AD_Process_ID=NULL, AD_Reference_ID=17, AD_Reference_Value_ID=104, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Action', ColumnSQL=NULL, DefaultValue=NULL, Description='Indicates the Action to be performed', EntityType='D', FieldLength=1, Help='The Action field is a drop down list box which indicates the Action to be performed for this Item.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Action', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=232
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Window_ID', Description='Data entry or display window', EntityType='D', Help='The Window field identifies a unique Window in the system.', IsActive='Y', Name='Window', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Window',Updated=TO_TIMESTAMP('2009-09-01 21:40:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=143
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=143
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Direct Table Access', EntityType='D', Help=NULL, IsActive='Y', Name='Table Direct', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=19
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=19
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=143, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Window_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Data entry or display window', EntityType='D', FieldLength=22, Help='The Window field identifies a unique Window in the system.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Window', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=233
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Workflow_ID', Description='Workflow or combination of tasks', EntityType='D', Help='The Workflow field identifies a unique Workflow in the system.', IsActive='Y', Name='Workflow', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Workflow',Updated=TO_TIMESTAMP('2009-09-01 21:40:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=144
;

-- Sep 1, 2009 9:40:11 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=144
;

-- Sep 1, 2009 9:40:12 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=144, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Workflow_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Workflow or combination of tasks', EntityType='D', FieldLength=22, Help='The Workflow field identifies a unique Workflow in the system.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Workflow', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=234
;

-- Sep 1, 2009 9:40:12 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Task_ID', Description='Operation System Task', EntityType='D', Help='The Task field identifies a Operation System Task in the system.', IsActive='Y', Name='OS Task', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='OS Task',Updated=TO_TIMESTAMP('2009-09-01 21:40:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=128
;

-- Sep 1, 2009 9:40:12 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=128
;

-- Sep 1, 2009 9:40:12 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=128, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Task_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Operation System Task', EntityType='D', FieldLength=22, Help='The Task field identifies a Operation System Task in the system.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='OS Task', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=235
;

-- Sep 1, 2009 9:40:12 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Client_ID', Description='Client/Tenant for this installation.', EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', IsActive='Y', Name='Client', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Client',Updated=TO_TIMESTAMP('2009-09-01 21:40:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=102
;

-- Sep 1, 2009 9:40:12 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=102
;

-- Sep 1, 2009 9:40:13 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@#AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=399
;

-- Sep 1, 2009 9:40:13 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Org_ID', Description='Organizational entity within client', EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', IsActive='Y', Name='Organization', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Organization',Updated=TO_TIMESTAMP('2009-09-01 21:40:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=113
;

-- Sep 1, 2009 9:40:13 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=113
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@#AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=400
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsActive', Description='The record is active in the system', EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', IsActive='Y', Name='Active', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Active',Updated=TO_TIMESTAMP('2009-09-01 21:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=348
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=348
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='CheckBox', EntityType='D', Help=NULL, IsActive='Y', Name='Yes-No', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=20
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=20
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=598
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Created', Description='Date this record was created', EntityType='D', Help='The Created field indicates the date that this record was created.', IsActive='Y', Name='Created', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created',Updated=TO_TIMESTAMP('2009-09-01 21:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=245
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=245
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Date with time', EntityType='D', Help=NULL, IsActive='Y', Name='Date+Time', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=16
;

-- Sep 1, 2009 9:40:14 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=16
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=599
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='CreatedBy', Description='User who created this records', EntityType='D', Help='The Created By field indicates the user who created this record.', IsActive='Y', Name='Created By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Created By',Updated=TO_TIMESTAMP('2009-09-01 21:40:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=246
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=246
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Table List', EntityType='D', Help=NULL, IsActive='Y', Name='Table', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=18
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=18
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='User selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_User', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-01 21:40:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=110
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=110
;

-- Sep 1, 2009 9:40:15 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_Table SET AD_Table_ID = 114, AD_Display = 213, AD_Key = 212, isValueDisplayed = 'N', OrderByClause = 'AD_User.Name', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 110
;

-- Sep 1, 2009 9:40:16 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=600
;

-- Sep 1, 2009 9:40:16 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Updated', Description='Date this record was updated', EntityType='D', Help='The Updated field indicates the date that this record was updated.', IsActive='Y', Name='Updated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated',Updated=TO_TIMESTAMP('2009-09-01 21:40:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=607
;

-- Sep 1, 2009 9:40:16 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=607
;

-- Sep 1, 2009 9:40:16 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=601
;

-- Sep 1, 2009 9:40:16 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='UpdatedBy', Description='User who updated this records', EntityType='D', Help='The Updated By field indicates the user who updated this record.', IsActive='Y', Name='Updated By', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Updated By',Updated=TO_TIMESTAMP('2009-09-01 21:40:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=608
;

-- Sep 1, 2009 9:40:16 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=608
;

-- Sep 1, 2009 9:40:17 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=602
;

-- Sep 1, 2009 9:40:17 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsSummary', Description='This is a summary entity', EntityType='D', Help='A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.', IsActive='Y', Name='Summary Level', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Summary Level',Updated=TO_TIMESTAMP('2009-09-01 21:40:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=416
;

-- Sep 1, 2009 9:40:17 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=416
;

-- Sep 1, 2009 9:40:17 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=416, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsSummary', ColumnSQL=NULL, DefaultValue=NULL, Description='This is a summary entity', EntityType='D', FieldLength=1, Help='A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Summary Level', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1169
;

-- Sep 1, 2009 9:40:17 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Process_ID', Description='Process or Report', EntityType='D', Help='The Process field identifies a unique Process or Report in the system.', IsActive='Y', Name='Process', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process',Updated=TO_TIMESTAMP('2009-09-01 21:40:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=117
;

-- Sep 1, 2009 9:40:17 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=117
;

-- Sep 1, 2009 9:40:18 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=117, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Process_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Process or Report', EntityType='D', FieldLength=22, Help='The Process field identifies a unique Process or Report in the system.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Process', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=3375
;

-- Sep 1, 2009 9:40:18 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsSOTrx', Description='This is a Sales Transaction', EntityType='D', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', IsActive='Y', Name='Sales Transaction', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sales Transaction',Updated=TO_TIMESTAMP('2009-09-01 21:40:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1106
;

-- Sep 1, 2009 9:40:18 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1106
;

-- Sep 1, 2009 9:40:18 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=1106, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsSOTrx', ColumnSQL=NULL, DefaultValue=NULL, Description='This is a Sales Transaction', EntityType='D', FieldLength=1, Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Sales Transaction', ReadOnlyLogic=NULL, VFormat=NULL, Version=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4426
;

-- Sep 1, 2009 9:40:18 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Form_ID', Description='Special Form', EntityType='D', Help='The Special Form field identifies a unique Special Form in the system.', IsActive='Y', Name='Special Form', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Special Form',Updated=TO_TIMESTAMP('2009-09-01 21:40:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1298
;

-- Sep 1, 2009 9:40:18 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1298
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=1298, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Form_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Special Form', EntityType='D', FieldLength=22, Help='The Special Form field identifies a unique Special Form in the system.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Special Form', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=4621
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Workbench_ID', Description='Collection of windows, reports', EntityType='D', Help=NULL, IsActive='Y', Name='Workbench', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Workbench',Updated=TO_TIMESTAMP('2009-09-01 21:40:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1646
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1646
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=1646, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Workbench_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Collection of windows, reports', EntityType='D', FieldLength=22, Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Workbench', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=6297
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET Name='Workbench', Description='Collection of windows, reports', Help=NULL WHERE AD_Column_ID=6297 AND IsCentrallyMaintained='Y'
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsReadOnly', Description='Field is read only', EntityType='D', Help='The Read Only indicates that this field may only be Read.  It may not be updated.', IsActive='Y', Name='Read Only', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Read Only',Updated=TO_TIMESTAMP('2009-09-01 21:40:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=405
;

-- Sep 1, 2009 9:40:19 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=405
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=405, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsReadOnly', ColumnSQL=NULL, DefaultValue='N', Description='Field is read only', EntityType='D', FieldLength=1, Help='The Read Only indicates that this field may only be Read.  It may not be updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Read Only', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=6651
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='EntityType', Description='Dictionary Entity Type; Determines ownership and synchronization', EntityType='D', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', IsActive='Y', Name='Entity Type', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Entity Type',Updated=TO_TIMESTAMP('2009-09-01 21:40:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=1682
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=1682
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='_EntityTypeNew', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-01 21:40:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=389
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=389
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_Table SET AD_Table_ID = 882, AD_Display = 15601, AD_Key = 15592, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 389
;

-- Sep 1, 2009 9:40:20 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=1682, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=389, AD_Table_ID=116, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='EntityType', ColumnSQL=NULL, DefaultValue='U', Description='Dictionary Entity Type; Determines ownership and synchronization', EntityType='D', FieldLength=40, Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Entity Type', ReadOnlyLogic='@EntityType@=D', VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=7721
;

-- Sep 1, 2009 9:40:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53902,0,'AD_Browse_ID',TO_TIMESTAMP('2009-09-01 21:40:20','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Smart Browse','Smart Browse',TO_TIMESTAMP('2009-09-01 21:40:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:40:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53902 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:40:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57960,53902,0,19,116,'AD_Browse_ID',TO_TIMESTAMP('2009-09-01 21:40:21','YYYY-MM-DD HH24:MI:SS'),0,'D',22,'Y','N','N','N','N','N','N','N','Y','N','Y','Smart Browse',TO_TIMESTAMP('2009-09-01 21:40:21','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:40:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57960 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:22 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Menu ADD COLUMN AD_Browse_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:40:23 PM ECT
-- View & Smat Browse
UPDATE AD_Tab SET AD_Table_ID=116, AD_Window_ID=105, CommitWarning=NULL, Description='Maintain Menu', EntityType='D', HasTree='Y', Help='The Menu Tab defines the tree structured menu which will be used for the selected Organization. Note that the name and description will be automatically synchronized (overwritten), if not a summary node.', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='Y', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='N', Name='Menu', OrderByClause=NULL, Processing='N', SeqNo=10, TabLevel=0, WhereClause=NULL,Updated=TO_TIMESTAMP('2009-09-01 21:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=110
;

-- Sep 1, 2009 9:40:23 PM ECT
-- View & Smat Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=110
;

-- Sep 1, 2009 9:40:23 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=228, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Identifies a Menu', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Menu identifies a unique Menu.  Menus are used to control the display of those screens a user has access to.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='N', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Menu', SeqNo=0, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=202
;

-- Sep 1, 2009 9:40:23 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=399, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=284
;

-- Sep 1, 2009 9:40:24 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=400, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1993
;

-- Sep 1, 2009 9:40:24 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=229, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=30, SortNo=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=201
;

-- Sep 1, 2009 9:40:24 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=230, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=40, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=203
;

-- Sep 1, 2009 9:40:24 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=598, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=283
;

-- Sep 1, 2009 9:40:25 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=7721, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Dictionary Entity Type; Determines ownership and synchronization', DisplayLength=20, DisplayLogic=NULL, EntityType='D', Help='The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Entity Type', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5827
;

-- Sep 1, 2009 9:40:25 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=6651, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Field is read only', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Read Only indicates that this field may only be Read.  It may not be updated.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Read Only', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5321
;

-- Sep 1, 2009 9:40:25 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=1169, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='This is a summary entity', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='A summary entity represents a branch in a tree rather than an end-node. Summary entities are used for reporting and do not have own values.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Summary Level', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=304
;

-- Sep 1, 2009 9:40:26 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=232, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Indicates the Action to be performed', DisplayLength=14, DisplayLogic='@IsSummary@=N', EntityType='D', Help='The Action field is a drop down list box which indicates the Action to be performed for this Item.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Action', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=205
;

-- Sep 1, 2009 9:40:26 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=4426, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='This is a Sales Transaction', DisplayLength=1, DisplayLogic='@IsSummary@=N', EntityType='D', Help='The Sales Transaction checkbox indicates if this item is a Sales Transaction.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Sales Transaction', SeqNo=100, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3315
;

-- Sep 1, 2009 9:40:26 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=233, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Data entry or display window', DisplayLength=14, DisplayLogic='@Action@=W', EntityType='D', Help='The Window field identifies a unique Window in the system.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Window', SeqNo=110, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=206
;

-- Sep 1, 2009 9:40:26 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=234, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Workflow or combination of tasks', DisplayLength=14, DisplayLogic='@Action@=F', EntityType='D', Help='The Workflow field identifies a unique Workflow in the system.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Workflow', SeqNo=120, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=207
;

-- Sep 1, 2009 9:40:27 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=235, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Operation System Task', DisplayLength=14, DisplayLogic='@Action@=T', EntityType='D', Help='The Task field identifies a Operation System Task in the system.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='OS Task', SeqNo=130, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=208
;

-- Sep 1, 2009 9:40:27 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=3375, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Process or Report', DisplayLength=14, DisplayLogic='@Action@=P | @Action@=R', EntityType='D', Help='The Process field identifies a unique Process or Report in the system.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Process', SeqNo=140, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=2576
;

-- Sep 1, 2009 9:40:27 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=4621, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Special Form', DisplayLength=14, DisplayLogic='@Action@=X', EntityType='D', Help='The Special Form field identifies a unique Special Form in the system.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Special Form', SeqNo=150, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=3612
;

-- Sep 1, 2009 9:40:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57960,57420,0,110,TO_TIMESTAMP('2009-09-01 21:40:27','YYYY-MM-DD HH24:MI:SS'),0,22,'@Action@=S','D','Y','Y','Y','N','N','N','N','Smart Browse',160,0,TO_TIMESTAMP('2009-09-01 21:40:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:40:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57420 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:40:28 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=6297, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=110, AD_Val_Rule_ID=NULL, Description='Collection of windows, reports', DisplayLength=14, DisplayLogic='@Action@=B', EntityType='D', Help=NULL, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Workbench', SeqNo=170, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=4944
;

-- Sep 1, 2009 9:40:28 PM ECT
-- View & Smat Browse
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=4944
;

-- Sep 1, 2009 9:40:28 PM ECT
-- View & Smat Browse
UPDATE AD_Table SET AD_Window_ID=105, AccessLevel='6', Description='Identifies a Menu', EntityType='D', Help=NULL, ImportTable=NULL, IsActive='Y', IsChangeLog='N', IsDeleteable='Y', IsHighVolume='N', IsSecurityEnabled='N', IsView='N', Name='Menu Trl', ReplicationType='L', TableName='AD_Menu_Trl',Updated=TO_TIMESTAMP('2009-09-01 21:40:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=120
;

-- Sep 1, 2009 9:40:29 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=110, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Menu_ID', ColumnSQL=NULL, DefaultValue=NULL, Description='Identifies a Menu', EntityType='D', FieldLength=22, Help='The Menu identifies a unique Menu.  Menus are used to control the display of those screens a user has access to.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Menu', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=245
;

-- Sep 1, 2009 9:40:29 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Language', Description='Language for this entity', EntityType='D', Help='The Language identifies the language to use for display and formatting', IsActive='Y', Name='Language', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Language',Updated=TO_TIMESTAMP('2009-09-01 21:40:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=109
;

-- Sep 1, 2009 9:40:29 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=109
;

-- Sep 1, 2009 9:40:29 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Language selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Language', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-01 21:40:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=106
;

-- Sep 1, 2009 9:40:29 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=106
;

-- Sep 1, 2009 9:40:29 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_Table SET AD_Table_ID = 111, AD_Display = 204, AD_Key = 203, isValueDisplayed = 'N', OrderByClause = '', EntityType ='D', WhereClause = '' WHERE AD_Reference_ID = 106
;

-- Sep 1, 2009 9:40:30 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=109, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=106, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Language', ColumnSQL=NULL, DefaultValue=NULL, Description='Language for this entity', EntityType='D', FieldLength=6, Help='The Language identifies the language to use for display and formatting', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='Y', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Language', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=246
;

-- Sep 1, 2009 9:40:30 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=469, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Name', ColumnSQL=NULL, DefaultValue=NULL, Description='Alphanumeric identifier of the entity', EntityType='D', FieldLength=60, Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='Y', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Name', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=247
;

-- Sep 1, 2009 9:40:30 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=275, AD_Process_ID=NULL, AD_Reference_ID=10, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Description', ColumnSQL=NULL, DefaultValue=NULL, Description='Optional short description of the record', EntityType='D', FieldLength=255, Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='N', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Description', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=248
;

-- Sep 1, 2009 9:40:31 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsTranslated', Description='This column is translated', EntityType='D', Help='The Translated checkbox indicates if this column is translated.', IsActive='Y', Name='Translated', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Translated',Updated=TO_TIMESTAMP('2009-09-01 21:40:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=420
;

-- Sep 1, 2009 9:40:31 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=420
;

-- Sep 1, 2009 9:40:31 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=420, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsTranslated', ColumnSQL=NULL, DefaultValue=NULL, Description='This column is translated', EntityType='D', FieldLength=1, Help='The Translated checkbox indicates if this column is translated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Translated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=249
;

-- Sep 1, 2009 9:40:31 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=348, AD_Process_ID=NULL, AD_Reference_ID=20, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='IsActive', ColumnSQL=NULL, DefaultValue='Y', Description='The record is active in the system', EntityType='D', FieldLength=1, Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='Y', Name='Active', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=636
;

-- Sep 1, 2009 9:40:32 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=245, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Created', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was created', EntityType='D', FieldLength=7, Help='The Created field indicates the date that this record was created.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=637
;

-- Sep 1, 2009 9:40:32 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=246, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='CreatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who created this records', EntityType='D', FieldLength=22, Help='The Created By field indicates the user who created this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Created By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=638
;

-- Sep 1, 2009 9:40:33 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=607, AD_Process_ID=NULL, AD_Reference_ID=16, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='Updated', ColumnSQL=NULL, DefaultValue=NULL, Description='Date this record was updated', EntityType='D', FieldLength=7, Help='The Updated field indicates the date that this record was updated.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=639
;

-- Sep 1, 2009 9:40:33 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=608, AD_Process_ID=NULL, AD_Reference_ID=18, AD_Reference_Value_ID=110, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='UpdatedBy', ColumnSQL=NULL, DefaultValue=NULL, Description='User who updated this records', EntityType='D', FieldLength=22, Help='The Updated By field indicates the user who updated this record.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Updated By', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:33','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=640
;

-- Sep 1, 2009 9:40:34 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=102, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=NULL, Callout=NULL, ColumnName='AD_Client_ID', ColumnSQL=NULL, DefaultValue='@AD_Client_ID@', Description='Client/Tenant for this installation.', EntityType='D', FieldLength=22, Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Client', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1194
;

-- Sep 1, 2009 9:40:34 PM ECT
-- View & Smat Browse
UPDATE AD_Column SET AD_Element_ID=113, AD_Process_ID=NULL, AD_Reference_ID=19, AD_Reference_Value_ID=NULL, AD_Table_ID=120, AD_Val_Rule_ID=104, Callout=NULL, ColumnName='AD_Org_ID', ColumnSQL=NULL, DefaultValue='@AD_Org_ID@', Description='Organizational entity within client', EntityType='D', FieldLength=22, Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsAlwaysUpdateable='N', IsIdentifier='N', IsKey='N', IsMandatory='Y', IsParent='N', IsSelectionColumn='N', IsSyncDatabase='Y', IsTranslated='N', IsUpdateable='N', Name='Organization', ReadOnlyLogic=NULL, VFormat=NULL, Version=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=1195
;

-- Sep 1, 2009 9:40:34 PM ECT
-- View & Smat Browse
UPDATE AD_Tab SET AD_Table_ID=120, AD_Window_ID=105, CommitWarning=NULL, Description='Menu Translation - May not need to be translated', EntityType='D', HasTree='N', Help='Menu translations are derived from Window,  Process, etc. You only need to translate Summary level items all others will be translated automatically (overwritten)', ImportFields=NULL, IsActive='Y', IsAdvancedTab='N', IsInfoTab='N', IsInsertRecord='N', IsReadOnly='N', IsSingleRow='Y', IsSortTab='N', IsTranslationTab='Y', Name='Translation', OrderByClause=NULL, Processing='N', SeqNo=20, TabLevel=1, WhereClause=NULL,Updated=TO_TIMESTAMP('2009-09-01 21:40:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=111
;

-- Sep 1, 2009 9:40:34 PM ECT
-- View & Smat Browse
UPDATE AD_Tab_Trl SET IsTranslated='N' WHERE AD_Tab_ID=111
;

-- Sep 1, 2009 9:40:34 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=1194, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='Client/Tenant for this installation.', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Client', SeqNo=10, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=592
;

-- Sep 1, 2009 9:40:35 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=1195, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='Organizational entity within client', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='Y', Name='Organization', SeqNo=20, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=1994
;

-- Sep 1, 2009 9:40:35 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=245, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='Identifies a Menu', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Menu identifies a unique Menu.  Menus are used to control the display of those screens a user has access to.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Menu', SeqNo=30, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=211
;

-- Sep 1, 2009 9:40:35 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=246, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='Language for this entity', DisplayLength=14, DisplayLogic=NULL, EntityType='D', Help='The Language identifies the language to use for display and formatting', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='Y', IsSameLine='N', Name='Language', SeqNo=40, SortNo=1,Updated=TO_TIMESTAMP('2009-09-01 21:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=212
;

-- Sep 1, 2009 9:40:35 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=636, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='The record is active in the system', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Active', SeqNo=50, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=286
;

-- Sep 1, 2009 9:40:36 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=249, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='This column is translated', DisplayLength=1, DisplayLogic=NULL, EntityType='D', Help='The Translated checkbox indicates if this column is translated.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Translated', SeqNo=60, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=214
;

-- Sep 1, 2009 9:40:36 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=247, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='Alphanumeric identifier of the entity', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Name', SeqNo=70, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=213
;

-- Sep 1, 2009 9:40:36 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=248, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=111, AD_Val_Rule_ID=NULL, Description='Optional short description of the record', DisplayLength=60, DisplayLogic=NULL, EntityType='D', Help='A description is limited to 255 characters.', InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='Description', SeqNo=80, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:40:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=215
;

-- Sep 1, 2009 9:40:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53088,TO_TIMESTAMP('2009-09-01 21:40:36','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Smart Browse.','EE07','Smart Browse creates a quick way to generate a browse of information for end users easily and quickly based on a view and browser.','Y','N','N','N','Smart Browse','N',TO_TIMESTAMP('2009-09-01 21:40:36','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 1, 2009 9:40:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53088 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 1, 2009 9:40:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53223,'6',TO_TIMESTAMP('2009-09-01 21:40:37','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Smart Browse Fields','L','AD_Browse_Field',TO_TIMESTAMP('2009-09-01 21:40:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:40:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53223 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:40:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53331,TO_TIMESTAMP('2009-09-01 21:40:38','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_Browse_Field',1,'Y','N','Y','Y','AD_Browse_Field','N',1000000,TO_TIMESTAMP('2009-09-01 21:40:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:40:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57961,102,0,19,53223,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:40:42','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:40:42','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57961 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57962,113,0,19,53223,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:40:43','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:40:43','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57962 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57963,348,0,20,53223,'IsActive',TO_TIMESTAMP('2009-09-01 21:40:44','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:40:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57963 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57964,245,0,16,53223,'Created',TO_TIMESTAMP('2009-09-01 21:40:44','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:40:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57964 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57965,607,0,16,53223,'Updated',TO_TIMESTAMP('2009-09-01 21:40:45','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:40:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57965 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57966,246,0,19,110,53223,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:40:45','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:40:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57966 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57967,608,0,19,110,53223,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:40:46','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:40:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57967 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53903,0,'AD_Browse_Field_ID',TO_TIMESTAMP('2009-09-01 21:40:46','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','Smart Browse Fields ID','Smart Browse Fields ID',TO_TIMESTAMP('2009-09-01 21:40:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:40:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53903 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:40:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57968,53903,0,13,53223,'AD_Browse_Field_ID',TO_TIMESTAMP('2009-09-01 21:40:47','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE07',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','Smart Browse Fields ID',TO_TIMESTAMP('2009-09-01 21:40:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57968 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:47 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Browse_Field_ID', Description=NULL, EntityType='EE07', Help=NULL, IsActive='Y', Name='Browse Field', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Browse Field',Updated=TO_TIMESTAMP('2009-09-01 21:40:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53903
;

-- Sep 1, 2009 9:40:47 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53903
;

-- Sep 1, 2009 9:40:48 PM ECT
-- View & Smat Browse
CREATE TABLE AD_Browse_Field (AD_Browse_Field_ID NUMERIC(10) NOT NULL, AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT AD_Browse_Field_Key PRIMARY KEY (AD_Browse_Field_ID))
;

-- Sep 1, 2009 9:40:50 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Element_ID', Description='System Element enables the central maintenance of column description and help.', EntityType='D', Help='The System Element allows for the central maintenance of help, descriptions and terminology for a database column.', IsActive='Y', Name='System Element', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Element',Updated=TO_TIMESTAMP('2009-09-01 21:40:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=106
;

-- Sep 1, 2009 9:40:50 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=106
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57969,106,0,19,53223,'AD_Element_ID',TO_TIMESTAMP('2009-09-01 21:40:50','YYYY-MM-DD HH24:MI:SS'),0,'System Element enables the central maintenance of column description and help.','EE07',22,'The System Element allows for the central maintenance of help, descriptions and terminology for a database column.','Y','N','N','N','N','Y','N','N','Y','N','Y','System Element',TO_TIMESTAMP('2009-09-01 21:40:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57969 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN AD_Element_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Reference_ID', Description='System Reference and Validation', EntityType='D', Help='The Reference could be a display type, list or table validation.', IsActive='Y', Name='Reference', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Reference',Updated=TO_TIMESTAMP('2009-09-01 21:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=120
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=120
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Data Type selection', EntityType='D', Help=NULL, IsActive='Y', Name='AD_Reference Data Types', ValidationType='T',Updated=TO_TIMESTAMP('2009-09-01 21:40:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=1
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=1
;

-- Sep 1, 2009 9:40:51 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_Table SET AD_Table_ID = 102, AD_Display = 130, AD_Key = 129, isValueDisplayed = 'N', OrderByClause = 'AD_Reference.Name', EntityType ='D', WhereClause = 'AD_Reference.ValidationType=''D''' WHERE AD_Reference_ID = 1
;

-- Sep 1, 2009 9:40:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57970,120,0,18,1,53223,'AD_Reference_ID',TO_TIMESTAMP('2009-09-01 21:40:51','YYYY-MM-DD HH24:MI:SS'),0,'System Reference and Validation','EE07',22,'The Reference could be a display type, list or table validation.','Y','N','N','N','N','Y','N','N','Y','N','Y','Reference',TO_TIMESTAMP('2009-09-01 21:40:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57970 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:52 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN AD_Reference_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:40:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53904,0,'AD_View_Column_ID',TO_TIMESTAMP('2009-09-01 21:40:52','YYYY-MM-DD HH24:MI:SS'),0,'Column of View','EE07','Y','View Column','View Column',TO_TIMESTAMP('2009-09-01 21:40:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:40:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53904 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:40:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57971,53904,0,19,53223,'AD_View_Column_ID',TO_TIMESTAMP('2009-09-01 21:40:53','YYYY-MM-DD HH24:MI:SS'),0,'Column of View','EE07',22,'Y','N','N','N','N','Y','N','N','Y','N','Y','View Column',TO_TIMESTAMP('2009-09-01 21:40:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57971 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:54 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN AD_View_Column_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:40:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57972,275,0,10,53223,'Description',TO_TIMESTAMP('2009-09-01 21:40:54','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','Y','Y','Description',TO_TIMESTAMP('2009-09-01 21:40:54','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57972 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:54 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:40:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57973,1682,0,18,389,53223,'EntityType',TO_TIMESTAMP('2009-09-01 21:40:54','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization','EE07',40,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','N','N','N','N','Y','N','N','Y','N','Y','Entity Type',TO_TIMESTAMP('2009-09-01 21:40:54','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57973 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:55 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN EntityType VARCHAR(40) NOT NULL
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Help', Description='Comment or Hint', EntityType='D', Help='The Help field contains a hint, comment or help about the use of this item.', IsActive='Y', Name='Comment/Help', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Comment',Updated=TO_TIMESTAMP('2009-09-01 21:40:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=326
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=326
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Character String up to 2000 characters', EntityType='D', Help=NULL, IsActive='Y', Name='Text', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:40:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=14
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=14
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57974,326,0,14,53223,'Help',TO_TIMESTAMP('2009-09-01 21:40:56','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','Y','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:40:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57974 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:40:56 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsCentrallyMaintained', Description='Information maintained in System Element table', EntityType='D', Help='The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.', IsActive='Y', Name='Centrally maintained', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Centrally maintained',Updated=TO_TIMESTAMP('2009-09-01 21:40:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=362
;

-- Sep 1, 2009 9:40:57 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=362
;

-- Sep 1, 2009 9:40:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57975,362,0,20,53223,'IsCentrallyMaintained',TO_TIMESTAMP('2009-09-01 21:40:57','YYYY-MM-DD HH24:MI:SS'),0,'Information maintained in System Element table','EE07',1,'The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','N','N','N','N','N','N','N','Y','N','Y','Centrally maintained',TO_TIMESTAMP('2009-09-01 21:40:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57975 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:57 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsCentrallyMaintained CHAR(1) DEFAULT NULL CHECK (IsCentrallyMaintained IN ('Y','N'))
;

-- Sep 1, 2009 9:40:57 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsDisplayed', Description='Determines, if this field is displayed', EntityType='D', Help='If the field is displayed, the field Display Logic will determine at runtime, if it is actually displayed', IsActive='Y', Name='Displayed', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Displayed',Updated=TO_TIMESTAMP('2009-09-01 21:40:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=368
;

-- Sep 1, 2009 9:40:57 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=368
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57976,368,0,20,53223,'IsDisplayed',TO_TIMESTAMP('2009-09-01 21:40:57','YYYY-MM-DD HH24:MI:SS'),0,'Y','Determines, if this field is displayed','EE07',1,'If the field is displayed, the field Display Logic will determine at runtime, if it is actually displayed','Y','N','N','N','N','N','N','N','Y','N','Y','Displayed',TO_TIMESTAMP('2009-09-01 21:40:57','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57976 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsDisplayed CHAR(1) DEFAULT 'Y' CHECK (IsDisplayed IN ('Y','N'))
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsIdentifier', Description='This column is part of the record identifier', EntityType='D', Help='The Identifier checkbox indicates that this column is part of the identifier or key for this table.  ', IsActive='Y', Name='Identifier', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Identifier',Updated=TO_TIMESTAMP('2009-09-01 21:40:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=382
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=382
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57977,382,0,20,53223,'IsIdentifier',TO_TIMESTAMP('2009-09-01 21:40:59','YYYY-MM-DD HH24:MI:SS'),0,'This column is part of the record identifier','EE07',1,'The Identifier checkbox indicates that this column is part of the identifier or key for this table.  ','Y','N','N','N','N','N','N','N','Y','N','Y','Identifier',TO_TIMESTAMP('2009-09-01 21:40:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57977 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:40:59 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsIdentifier CHAR(1) DEFAULT NULL CHECK (IsIdentifier IN ('Y','N'))
;

-- Sep 1, 2009 9:41:00 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsKey', Description='This column is the key in this table', EntityType='D', Help='The key column must also be display sequence 0 in the field definition and may be hidden.', IsActive='Y', Name='Key column', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Key column',Updated=TO_TIMESTAMP('2009-09-01 21:41:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=389
;

-- Sep 1, 2009 9:41:00 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=389
;

-- Sep 1, 2009 9:41:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57978,389,0,20,53223,'IsKey',TO_TIMESTAMP('2009-09-01 21:41:00','YYYY-MM-DD HH24:MI:SS'),0,'This column is the key in this table','EE07',1,'The key column must also be display sequence 0 in the field definition and may be hidden.','Y','N','N','N','N','N','N','N','Y','N','Y','Key column',TO_TIMESTAMP('2009-09-01 21:41:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57978 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:00 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsKey CHAR(1) DEFAULT NULL CHECK (IsKey IN ('Y','N'))
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsQueryCriteria', Description='The column is also used as a query criteria', EntityType='D', Help='The column is used to enter queries - the SQL cannot be an expression', IsActive='Y', Name='Query Criteria', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Query Criteria',Updated=TO_TIMESTAMP('2009-09-01 21:41:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=3070
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=3070
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57979,3070,0,20,53223,'IsQueryCriteria',TO_TIMESTAMP('2009-09-01 21:41:01','YYYY-MM-DD HH24:MI:SS'),0,'N','The column is also used as a query criteria','EE07',1,'The column is used to enter queries - the SQL cannot be an expression','Y','N','N','N','N','N','N','N','Y','N','Y','Query Criteria',TO_TIMESTAMP('2009-09-01 21:41:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57979 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsQueryCriteria CHAR(1) DEFAULT 'N' CHECK (IsQueryCriteria IN ('Y','N'))
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsRange', Description='The parameter is a range of values', EntityType='D', Help='The Range checkbox indicates that this parameter is a range of values.', IsActive='Y', Name='Range', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Range',Updated=TO_TIMESTAMP('2009-09-01 21:41:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=404
;

-- Sep 1, 2009 9:41:01 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=404
;

-- Sep 1, 2009 9:41:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57980,404,0,20,53223,'IsRange',TO_TIMESTAMP('2009-09-01 21:41:01','YYYY-MM-DD HH24:MI:SS'),0,'The parameter is a range of values','EE07',1,'The Range checkbox indicates that this parameter is a range of values.','Y','N','N','N','N','N','N','N','Y','N','Y','Range',TO_TIMESTAMP('2009-09-01 21:41:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57980 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:02 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsRange CHAR(1) DEFAULT NULL CHECK (IsRange IN ('Y','N'))
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57981,469,0,10,53223,'Name',TO_TIMESTAMP('2009-09-01 21:41:02','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',255,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','Y','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:41:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57981 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN Name VARCHAR(255) NOT NULL
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='SeqNo', Description='Method of ordering records; lowest number comes first', EntityType='D', Help='The Sequence indicates the order of records', IsActive='Y', Name='Sequence', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Sequence',Updated=TO_TIMESTAMP('2009-09-01 21:41:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=566
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=566
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='10 Digit numeric', EntityType='D', Help=NULL, IsActive='Y', Name='Integer', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:41:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=11
;

-- Sep 1, 2009 9:41:03 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=11
;

-- Sep 1, 2009 9:41:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57982,566,0,11,53223,'SeqNo',TO_TIMESTAMP('2009-09-01 21:41:03','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE07',22,'The Sequence indicates the order of records','Y','N','N','N','N','N','N','N','Y','N','Y','Sequence',TO_TIMESTAMP('2009-09-01 21:41:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57982 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:04 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN SeqNo NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57983,53902,0,19,53223,'AD_Browse_ID',TO_TIMESTAMP('2009-09-01 21:41:04','YYYY-MM-DD HH24:MI:SS'),0,'EE07',22,'Y','N','N','N','N','N','Y','N','Y','N','N','Smart Browse',TO_TIMESTAMP('2009-09-01 21:41:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57983 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:05 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN AD_Browse_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:05 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Reference_Value_ID', Description='Required to specify, if data type is Table or List', EntityType='D', Help='The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ', IsActive='Y', Name='Reference Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Reference Key',Updated=TO_TIMESTAMP('2009-09-01 21:41:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=121
;

-- Sep 1, 2009 9:41:05 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=121
;

-- Sep 1, 2009 9:41:05 PM ECT
-- View & Smat Browse
UPDATE AD_Ref_Table SET AD_Table_ID = 102, AD_Display = 130, AD_Key = 129, isValueDisplayed = 'N', OrderByClause = 'AD_Reference.ValidationType, AD_Reference.Name', EntityType ='D', WhereClause = 'AD_Reference.ValidationType in (''T'',''L'')' WHERE AD_Reference_ID = 4
;

-- Sep 1, 2009 9:41:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57984,121,0,18,4,53223,115,'AD_Reference_Value_ID',TO_TIMESTAMP('2009-09-01 21:41:05','YYYY-MM-DD HH24:MI:SS'),0,'Required to specify, if data type is Table or List','EE07',22,'The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ','Y','N','N','N','N','N','N','N','Y','N','Y','Reference Key',TO_TIMESTAMP('2009-09-01 21:41:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57984 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:06 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN AD_Reference_Value_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:06 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsMandatory', Description='Data entry is required in this column', EntityType='D', Help='The field must have a value for the record to be saved to the database.', IsActive='Y', Name='Mandatory', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Mandatory',Updated=TO_TIMESTAMP('2009-09-01 21:41:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=392
;

-- Sep 1, 2009 9:41:06 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=392
;

-- Sep 1, 2009 9:41:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57985,392,0,20,53223,'IsMandatory',TO_TIMESTAMP('2009-09-01 21:41:06','YYYY-MM-DD HH24:MI:SS'),0,'Y','Data entry is required in this column','EE07',1,'The field must have a value for the record to be saved to the database.','Y','N','N','N','N','N','N','N','Y','N','Y','Mandatory',TO_TIMESTAMP('2009-09-01 21:41:06','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57985 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:07 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field ADD COLUMN IsMandatory CHAR(1) DEFAULT 'Y' CHECK (IsMandatory IN ('Y','N'))
;

-- Sep 1, 2009 9:41:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy,WhereClause) VALUES (0,0,53247,53223,53088,NULL,TO_TIMESTAMP('2009-09-01 21:41:07','YYYY-MM-DD HH24:MI:SS'),0,'Define Smart Browse Fields','EE07','N','You can define for each field will be displayed as the reference , system element , also you can define if the field is used as query criteria and if this is a range of information.','Y','N','N','Y','N','N','N','N','Smart Browse Field','N',50,1,TO_TIMESTAMP('2009-09-01 21:41:07','YYYY-MM-DD HH24:MI:SS'),0,'IsDisplayed=''Y''')
;

-- Sep 1, 2009 9:41:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53247 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:41:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57962,57421,0,53247,TO_TIMESTAMP('2009-09-01 21:41:08','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','Organization',0,0,TO_TIMESTAMP('2009-09-01 21:41:08','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57421 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57983,57422,0,53247,TO_TIMESTAMP('2009-09-01 21:41:09','YYYY-MM-DD HH24:MI:SS'),0,22,'EE07','Y','Y','N','N','N','N','N','Smart Browse',0,0,TO_TIMESTAMP('2009-09-01 21:41:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57422 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57968,57423,0,53247,TO_TIMESTAMP('2009-09-01 21:41:09','YYYY-MM-DD HH24:MI:SS'),0,22,'EE07','Y','Y','N','N','N','N','N','Browse Field',0,0,TO_TIMESTAMP('2009-09-01 21:41:09','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57423 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57961,57424,0,53247,TO_TIMESTAMP('2009-09-01 21:41:10','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Client',0,0,TO_TIMESTAMP('2009-09-01 21:41:10','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57424 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57981,57425,0,53247,TO_TIMESTAMP('2009-09-01 21:41:11','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',10,0,TO_TIMESTAMP('2009-09-01 21:41:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57425 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:13 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57972,57426,0,53247,TO_TIMESTAMP('2009-09-01 21:41:12','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',20,0,TO_TIMESTAMP('2009-09-01 21:41:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:13 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57426 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57974,57427,0,53247,TO_TIMESTAMP('2009-09-01 21:41:13','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',30,0,TO_TIMESTAMP('2009-09-01 21:41:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57427 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57963,57428,0,53247,TO_TIMESTAMP('2009-09-01 21:41:14','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',40,0,TO_TIMESTAMP('2009-09-01 21:41:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57428 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57982,57429,0,53247,TO_TIMESTAMP('2009-09-01 21:41:15','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first',22,'EE07','The Sequence indicates the order of records','Y','Y','Y','N','N','N','N','Sequence',50,0,TO_TIMESTAMP('2009-09-01 21:41:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57429 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57971,57430,0,53247,TO_TIMESTAMP('2009-09-01 21:41:16','YYYY-MM-DD HH24:MI:SS'),0,'Column of View',22,'EE07','Y','Y','Y','N','N','N','N','View Column',60,0,TO_TIMESTAMP('2009-09-01 21:41:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57430 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57969,57431,0,53247,TO_TIMESTAMP('2009-09-01 21:41:17','YYYY-MM-DD HH24:MI:SS'),0,'System Element enables the central maintenance of column description and help.',22,'EE07','The System Element allows for the central maintenance of help, descriptions and terminology for a database column.','Y','Y','Y','N','N','N','Y','System Element',70,0,TO_TIMESTAMP('2009-09-01 21:41:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57431 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57970,57432,0,53247,TO_TIMESTAMP('2009-09-01 21:41:18','YYYY-MM-DD HH24:MI:SS'),0,'System Reference and Validation',22,'EE07','The Reference could be a display type, list or table validation.','Y','Y','Y','N','N','N','N','Reference',80,0,TO_TIMESTAMP('2009-09-01 21:41:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57432 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57984,57433,0,53247,TO_TIMESTAMP('2009-09-01 21:41:18','YYYY-MM-DD HH24:MI:SS'),0,'Required to specify, if data type is Table or List',22,'@AD_Reference_ID@=17 | @AD_Reference_ID@=18 | @AD_Reference_ID@=30 | @AD_Reference_ID@=28','EE07','The Reference Value indicates where the reference values are stored.  It must be specified if the data type is Table or List.  ','Y','Y','Y','N','N','N','Y','Reference Key',90,0,TO_TIMESTAMP('2009-09-01 21:41:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57433 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57976,57434,0,53247,TO_TIMESTAMP('2009-09-01 21:41:19','YYYY-MM-DD HH24:MI:SS'),0,'Determines, if this field is displayed',1,'EE07','If the field is displayed, the field Display Logic will determine at runtime, if it is actually displayed','Y','Y','Y','N','N','N','N','Displayed',100,0,TO_TIMESTAMP('2009-09-01 21:41:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57434 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57975,57435,0,53247,TO_TIMESTAMP('2009-09-01 21:41:19','YYYY-MM-DD HH24:MI:SS'),0,'Information maintained in System Element table',1,'EE07','The Centrally Maintained checkbox indicates if the Name, Description and Help maintained in ''System Element'' table  or ''Window'' table.','Y','Y','Y','N','N','N','Y','Centrally maintained',110,0,TO_TIMESTAMP('2009-09-01 21:41:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57435 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57978,57436,0,53247,TO_TIMESTAMP('2009-09-01 21:41:20','YYYY-MM-DD HH24:MI:SS'),0,'This column is the key in this table',1,'EE07','The key column must also be display sequence 0 in the field definition and may be hidden.','Y','Y','Y','N','N','N','N','Key column',120,0,TO_TIMESTAMP('2009-09-01 21:41:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57436 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57977,57437,0,53247,TO_TIMESTAMP('2009-09-01 21:41:21','YYYY-MM-DD HH24:MI:SS'),0,'This column is part of the record identifier',1,'EE07','The Identifier checkbox indicates that this column is part of the identifier or key for this table.  ','Y','Y','Y','N','N','N','Y','Identifier',130,0,TO_TIMESTAMP('2009-09-01 21:41:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57437 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57985,57438,0,53247,TO_TIMESTAMP('2009-09-01 21:41:21','YYYY-MM-DD HH24:MI:SS'),0,'Data entry is required in this column',1,'EE07','The field must have a value for the record to be saved to the database.','Y','Y','Y','N','N','N','N','Mandatory',140,0,TO_TIMESTAMP('2009-09-01 21:41:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57438 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57979,57439,0,53247,TO_TIMESTAMP('2009-09-01 21:41:22','YYYY-MM-DD HH24:MI:SS'),0,'The column is also used as a query criteria',1,'EE07','The column is used to enter queries - the SQL cannot be an expression','Y','Y','Y','N','N','N','N','Query Criteria',150,0,TO_TIMESTAMP('2009-09-01 21:41:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57439 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57980,57440,0,53247,TO_TIMESTAMP('2009-09-01 21:41:23','YYYY-MM-DD HH24:MI:SS'),0,'The parameter is a range of values',1,'@IsQueryCriteria@=''Y''','EE07','The Range checkbox indicates that this parameter is a range of values.','Y','Y','Y','N','N','N','Y','Range',160,0,TO_TIMESTAMP('2009-09-01 21:41:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57440 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57973,57441,0,53247,TO_TIMESTAMP('2009-09-01 21:41:24','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization',20,'EE07','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','Y','Y','N','N','N','N','Entity Type',170,0,TO_TIMESTAMP('2009-09-01 21:41:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57441 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53224,'6',TO_TIMESTAMP('2009-09-01 21:41:24','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Smart Browse','L','AD_Browse',TO_TIMESTAMP('2009-09-01 21:41:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53224 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:41:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53332,TO_TIMESTAMP('2009-09-01 21:41:25','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_Browse',1,'Y','N','Y','Y','AD_Browse','N',1000000,TO_TIMESTAMP('2009-09-01 21:41:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57986,102,0,19,53224,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:41:26','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:41:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57986 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57987,113,0,19,53224,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:41:26','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:41:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57987 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57988,348,0,20,53224,'IsActive',TO_TIMESTAMP('2009-09-01 21:41:27','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:41:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57988 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57989,245,0,16,53224,'Created',TO_TIMESTAMP('2009-09-01 21:41:28','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:41:28','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57989 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57990,607,0,16,53224,'Updated',TO_TIMESTAMP('2009-09-01 21:41:28','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:41:28','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57990 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57991,246,0,19,110,53224,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:41:29','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:41:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57991 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57992,608,0,19,110,53224,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:41:29','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:41:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57992 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57993,53902,0,13,53224,'AD_Browse_ID',TO_TIMESTAMP('2009-09-01 21:41:30','YYYY-MM-DD HH24:MI:SS'),0,'EE07',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Smart Browse',TO_TIMESTAMP('2009-09-01 21:41:30','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57993 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:30 PM ECT
-- View & Smat Browse
CREATE TABLE AD_Browse (AD_Browse_ID NUMERIC(10) NOT NULL, AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT AD_Browse_Key PRIMARY KEY (AD_Browse_ID))
;

-- Sep 1, 2009 9:41:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57994,275,0,10,53224,'Description',TO_TIMESTAMP('2009-09-01 21:41:32','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','Y','Y','Description',TO_TIMESTAMP('2009-09-01 21:41:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57994 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:33 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57995,1682,0,18,389,53224,'EntityType',TO_TIMESTAMP('2009-09-01 21:41:33','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization','EE07',40,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','N','N','N','N','Y','N','N','Y','N','Y','Entity Type',TO_TIMESTAMP('2009-09-01 21:41:33','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57995 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:34 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN EntityType VARCHAR(40) NOT NULL
;

-- Sep 1, 2009 9:41:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57996,326,0,14,53224,'Help',TO_TIMESTAMP('2009-09-01 21:41:34','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','Y','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:41:34','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57996 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:34 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57997,469,0,10,53224,'Name',TO_TIMESTAMP('2009-09-01 21:41:34','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',255,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','Y','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:41:34','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57997 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:35 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN Name VARCHAR(255) NOT NULL
;

-- Sep 1, 2009 9:41:35 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Value', Description='Search key for the record in the format required - must be unique', EntityType='D', Help='A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).', IsActive='Y', Name='Search Key', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Search Key',Updated=TO_TIMESTAMP('2009-09-01 21:41:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=620
;

-- Sep 1, 2009 9:41:35 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=620
;

-- Sep 1, 2009 9:41:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57998,620,0,10,53224,'Value',TO_TIMESTAMP('2009-09-01 21:41:35','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE07',60,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2009-09-01 21:41:35','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57998 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:36 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN Value VARCHAR(60) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53905,0,'AD_View_ID',TO_TIMESTAMP('2009-09-01 21:41:36','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application','EE07','These views can be based on tables and views of the dictionary application.','Y','View','View',TO_TIMESTAMP('2009-09-01 21:41:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53905 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,57999,53905,0,19,53224,'AD_View_ID',TO_TIMESTAMP('2009-09-01 21:41:37','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','N','N','N','N','Y','N','N','Y','N','N','View',TO_TIMESTAMP('2009-09-01 21:41:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57999 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN AD_View_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='WhereClause', Description='Fully qualified SQL WHERE clause', EntityType='D', Help='The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".', IsActive='Y', Name='Sql WHERE', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Where clause',Updated=TO_TIMESTAMP('2009-09-01 21:41:37','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=630
;

-- Sep 1, 2009 9:41:37 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=630
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58000,630,0,14,53224,'WhereClause',TO_TIMESTAMP('2009-09-01 21:41:37','YYYY-MM-DD HH24:MI:SS'),0,'Fully qualified SQL WHERE clause','EE07',2000,'The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".','Y','N','N','N','N','N','N','N','Y','N','Y','Sql WHERE',TO_TIMESTAMP('2009-09-01 21:41:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58000 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN WhereClause VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='Processing', Description=NULL, EntityType='D', Help=NULL, IsActive='Y', Name='Process Now', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Process Now',Updated=TO_TIMESTAMP('2009-09-01 21:41:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=524
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=524
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
UPDATE AD_Reference SET Description='Command Button - starts a process', EntityType='D', Help=NULL, IsActive='Y', Name='Button', ValidationType='D',Updated=TO_TIMESTAMP('2009-09-01 21:41:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Reference_ID=28
;

-- Sep 1, 2009 9:41:38 PM ECT
-- View & Smat Browse
UPDATE AD_Reference_Trl SET IsTranslated='N' WHERE AD_Reference_ID=28
;

-- Sep 1, 2009 9:41:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53180,'6','org.eevolution.process.CreateBrowseField',TO_TIMESTAMP('2009-09-01 21:41:38','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','N','N','N','Smart Browse Create Fields','Y',0,0,TO_TIMESTAMP('2009-09-01 21:41:38','YYYY-MM-DD HH24:MI:SS'),0,'AD_SmartBrowse_CreateField',NULL)
;

-- Sep 1, 2009 9:41:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53180 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 1, 2009 9:41:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58001,524,0,53180,28,53224,'Processing',TO_TIMESTAMP('2009-09-01 21:41:39','YYYY-MM-DD HH24:MI:SS'),0,'EE07',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_TIMESTAMP('2009-09-01 21:41:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58001 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:40 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN Processing CHAR(1) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58002,117,0,19,53224,'AD_Process_ID',TO_TIMESTAMP('2009-09-01 21:41:40','YYYY-MM-DD HH24:MI:SS'),0,'Process or Report','EE07',22,'The Process field identifies a unique Process or Report in the system.','Y','N','N','N','N','N','N','N','Y','N','Y','Process',TO_TIMESTAMP('2009-09-01 21:41:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58002 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:42 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN AD_Process_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:41:42 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsBetaFunctionality', Description='This functionality is considered Beta', EntityType='D', Help='Beta functionality is not fully tested or completed.', IsActive='Y', Name='Beta Functionality', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Beta Functionality',Updated=TO_TIMESTAMP('2009-09-01 21:41:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2554
;

-- Sep 1, 2009 9:41:42 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2554
;

-- Sep 1, 2009 9:41:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58003,2554,0,20,53224,'IsBetaFunctionality',TO_TIMESTAMP('2009-09-01 21:41:42','YYYY-MM-DD HH24:MI:SS'),0,'This functionality is considered Beta','EE07',1,'Beta functionality is not fully tested or completed.','Y','N','N','N','N','N','N','N','Y','N','Y','Beta Functionality',TO_TIMESTAMP('2009-09-01 21:41:42','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:41:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58003 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:43 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse ADD COLUMN IsBetaFunctionality CHAR(1) DEFAULT NULL CHECK (IsBetaFunctionality IN ('Y','N'))
;

-- Sep 1, 2009 9:41:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53248,53224,53088,NULL,TO_TIMESTAMP('2009-09-01 21:41:43','YYYY-MM-DD HH24:MI:SS'),0,'Define Smart Browse','EE07','N','The Smart Browse  is used to search and select records as well as display information relevant to the selection.','Y','N','N','Y','N','Y','N','N','Smart Browse','N',10,0,TO_TIMESTAMP('2009-09-01 21:41:43','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53248 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:41:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57986,57442,0,53248,TO_TIMESTAMP('2009-09-01 21:41:44','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57442 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57987,57443,0,53248,TO_TIMESTAMP('2009-09-01 21:41:44','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:41:44','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57443 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57998,57444,0,53248,TO_TIMESTAMP('2009-09-01 21:41:45','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',60,'EE07','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_TIMESTAMP('2009-09-01 21:41:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57444 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57997,57445,0,53248,TO_TIMESTAMP('2009-09-01 21:41:45','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2009-09-01 21:41:45','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57445 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57994,57446,0,53248,TO_TIMESTAMP('2009-09-01 21:41:46','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-01 21:41:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57446 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57996,57447,0,53248,TO_TIMESTAMP('2009-09-01 21:41:46','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_TIMESTAMP('2009-09-01 21:41:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57447 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57988,57448,0,53248,TO_TIMESTAMP('2009-09-01 21:41:47','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2009-09-01 21:41:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57448 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:48 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58003,57449,0,53248,TO_TIMESTAMP('2009-09-01 21:41:47','YYYY-MM-DD HH24:MI:SS'),0,'This functionality is considered Beta',1,'EE07','Beta functionality is not fully tested or completed.','Y','Y','Y','N','N','N','N','Beta Functionality',80,0,TO_TIMESTAMP('2009-09-01 21:41:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:48 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57449 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:49 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57999,57450,0,53248,TO_TIMESTAMP('2009-09-01 21:41:48','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','Y','N','N','N','N','View',90,0,TO_TIMESTAMP('2009-09-01 21:41:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:49 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57450 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:49 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58002,57451,0,53248,TO_TIMESTAMP('2009-09-01 21:41:49','YYYY-MM-DD HH24:MI:SS'),0,'Process or Report',22,'EE07','The Process field identifies a unique Process or Report in the system.','Y','Y','Y','N','N','N','Y','Process',100,0,TO_TIMESTAMP('2009-09-01 21:41:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:49 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57451 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58000,57452,0,53248,TO_TIMESTAMP('2009-09-01 21:41:49','YYYY-MM-DD HH24:MI:SS'),0,'Fully qualified SQL WHERE clause',2000,'EE07','The Where Clause indicates the SQL WHERE clause to use for record selection. The WHERE clause is added to the query. Fully qualified means "tablename.columnname".','Y','Y','Y','N','N','N','N','Sql WHERE',110,0,TO_TIMESTAMP('2009-09-01 21:41:49','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57452 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,Included_Tab_ID,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57993,57453,0,53248,TO_TIMESTAMP('2009-09-01 21:41:50','YYYY-MM-DD HH24:MI:SS'),0,22,'EE07',53247,'Y','Y','Y','N','N','N','N','Smart Browse',120,0,TO_TIMESTAMP('2009-09-01 21:41:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57453 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57995,57454,0,53248,TO_TIMESTAMP('2009-09-01 21:41:51','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization',20,'EE07','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','Y','Y','N','N','N','N','Entity Type',130,0,TO_TIMESTAMP('2009-09-01 21:41:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57454 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58001,57455,0,53248,TO_TIMESTAMP('2009-09-01 21:41:51','YYYY-MM-DD HH24:MI:SS'),0,1,'EE07','Y','N','Y','N','N','N','N','Create Fields',140,0,TO_TIMESTAMP('2009-09-01 21:41:51','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57455 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:41:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AD_Window_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53225,268,'6',TO_TIMESTAMP('2009-09-01 21:41:52','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Browse Access','L','AD_Browse_Access',TO_TIMESTAMP('2009-09-01 21:41:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53225 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:41:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53333,TO_TIMESTAMP('2009-09-01 21:41:52','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_Browse_Access',1,'Y','N','Y','Y','AD_Browse_Access','N',1000000,TO_TIMESTAMP('2009-09-01 21:41:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:41:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58004,102,0,19,53225,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:41:53','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:41:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58004 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58005,113,0,19,53225,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:41:53','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:41:53','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58005 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58006,348,0,20,53225,'IsActive',TO_TIMESTAMP('2009-09-01 21:41:54','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:41:54','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58006 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58007,245,0,16,53225,'Created',TO_TIMESTAMP('2009-09-01 21:41:55','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:41:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58007 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58008,607,0,16,53225,'Updated',TO_TIMESTAMP('2009-09-01 21:41:55','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:41:55','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58008 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58009,246,0,19,110,53225,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:41:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:41:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58009 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58010,608,0,19,110,53225,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:41:56','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:41:56','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:41:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58010 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:41:57 PM ECT
-- View & Smat Browse
CREATE TABLE AD_Browse_Access (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL)
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58011,53902,0,19,53225,'AD_Browse_ID',TO_TIMESTAMP('2009-09-01 21:41:59','YYYY-MM-DD HH24:MI:SS'),0,'EE07',22,'Y','N','N','N','N','Y','N','N','Y','N','N','Smart Browse',TO_TIMESTAMP('2009-09-01 21:41:59','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58011 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Access ADD COLUMN AD_Browse_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Role_ID', Description='Responsibility Role', EntityType='D', Help='The Role determines security and access a user who has this Role will have in the System.', IsActive='Y', Name='Role', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Role',Updated=TO_TIMESTAMP('2009-09-01 21:42:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=123
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=123
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58012,123,0,19,53225,'AD_Role_ID',TO_TIMESTAMP('2009-09-01 21:42:00','YYYY-MM-DD HH24:MI:SS'),0,'Responsibility Role','EE07',22,'The Role determines security and access a user who has this Role will have in the System.','Y','N','N','N','N','Y','Y','N','Y','N','N','Role',TO_TIMESTAMP('2009-09-01 21:42:00','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58012 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:01 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Access ADD COLUMN AD_Role_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:42:01 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='IsReadWrite', Description='Field is read / write', EntityType='D', Help='The Read Write indicates that this field may be read and updated.', IsActive='Y', Name='Read Write', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Read Write',Updated=TO_TIMESTAMP('2009-09-01 21:42:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=406
;

-- Sep 1, 2009 9:42:01 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=406
;

-- Sep 1, 2009 9:42:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58013,406,0,20,53225,'IsReadWrite',TO_TIMESTAMP('2009-09-01 21:42:01','YYYY-MM-DD HH24:MI:SS'),0,'Field is read / write','EE07',1,'The Read Write indicates that this field may be read and updated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Read Write',TO_TIMESTAMP('2009-09-01 21:42:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58013 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:01 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Access ADD COLUMN IsReadWrite CHAR(1) CHECK (IsReadWrite IN ('Y','N')) NOT NULL
;

-- Sep 1, 2009 9:42:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53249,53225,53088,NULL,TO_TIMESTAMP('2009-09-01 21:42:01','YYYY-MM-DD HH24:MI:SS'),0,'Smart Browse Access','EE07','N','The Smart Browse Access Tab defines the Roles which have access to this Smart Browse.','Y','N','N','Y','N','N','N','N','Access','N',20,1,TO_TIMESTAMP('2009-09-01 21:42:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53249 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:42:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58004,57456,0,53249,TO_TIMESTAMP('2009-09-01 21:42:02','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:42:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57456 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58005,57457,0,53249,TO_TIMESTAMP('2009-09-01 21:42:03','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:42:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57457 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58011,57458,0,53249,TO_TIMESTAMP('2009-09-01 21:42:03','YYYY-MM-DD HH24:MI:SS'),0,22,'EE07','Y','Y','Y','N','N','Y','N','Smart Browse',30,0,TO_TIMESTAMP('2009-09-01 21:42:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57458 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58012,57459,0,53249,TO_TIMESTAMP('2009-09-01 21:42:04','YYYY-MM-DD HH24:MI:SS'),0,'Responsibility Role',22,'EE07','The Role determines security and access a user who has this Role will have in the System.','Y','Y','Y','N','N','N','N','Role',40,0,TO_TIMESTAMP('2009-09-01 21:42:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57459 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58006,57460,0,53249,TO_TIMESTAMP('2009-09-01 21:42:04','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',50,0,TO_TIMESTAMP('2009-09-01 21:42:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57460 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58013,57461,0,53249,TO_TIMESTAMP('2009-09-01 21:42:05','YYYY-MM-DD HH24:MI:SS'),0,'Field is read / write',1,'EE07','The Read Write indicates that this field may be read and updated.','Y','Y','Y','N','N','N','N','Read Write',60,0,TO_TIMESTAMP('2009-09-01 21:42:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57461 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53226,'6',TO_TIMESTAMP('2009-09-01 21:42:06','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Smart Browse Trl','L','AD_Browse_Trl',TO_TIMESTAMP('2009-09-01 21:42:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53226 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:42:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53334,TO_TIMESTAMP('2009-09-01 21:42:06','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_Browse_Trl',1,'Y','N','Y','Y','AD_Browse_Trl','N',1000000,TO_TIMESTAMP('2009-09-01 21:42:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58014,102,0,19,53226,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:42:07','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:42:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58014 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58015,113,0,19,53226,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:42:07','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:42:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58015 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58016,348,0,20,53226,'IsActive',TO_TIMESTAMP('2009-09-01 21:42:08','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:42:08','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58016 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58017,245,0,16,53226,'Created',TO_TIMESTAMP('2009-09-01 21:42:08','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:42:08','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58017 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58018,607,0,16,53226,'Updated',TO_TIMESTAMP('2009-09-01 21:42:09','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:42:09','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58018 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58019,246,0,19,110,53226,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:42:09','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:42:09','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58019 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58020,608,0,19,110,53226,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:42:10','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:42:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58020 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:11 PM ECT
-- View & Smat Browse
CREATE TABLE AD_Browse_Trl (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL)
;

-- Sep 1, 2009 9:42:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58021,53902,0,13,53226,'AD_Browse_ID',TO_TIMESTAMP('2009-09-01 21:42:13','YYYY-MM-DD HH24:MI:SS'),0,'EE07',22,'Y','N','N','N','N','Y','Y','N','Y','N','N','Smart Browse',TO_TIMESTAMP('2009-09-01 21:42:13','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58021 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:14 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Trl ADD COLUMN AD_Browse_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:42:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58022,109,0,18,106,53226,'AD_Language',TO_TIMESTAMP('2009-09-01 21:42:14','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE07',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','Y','N','N','Language',TO_TIMESTAMP('2009-09-01 21:42:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:42:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58022 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:14 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Trl ADD COLUMN AD_Language VARCHAR(6) NOT NULL
;

-- Sep 1, 2009 9:42:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58023,275,0,10,53226,'Description',TO_TIMESTAMP('2009-09-01 21:42:14','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-01 21:42:14','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:42:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58023 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:15 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Trl ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:42:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58024,326,0,14,53226,'Help',TO_TIMESTAMP('2009-09-01 21:42:15','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:42:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:42:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58024 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:15 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Trl ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:42:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58025,420,0,20,53226,'IsTranslated',TO_TIMESTAMP('2009-09-01 21:42:15','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE07',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Translated',TO_TIMESTAMP('2009-09-01 21:42:15','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:42:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58025 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:16 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Trl ADD COLUMN IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL
;

-- Sep 1, 2009 9:42:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58026,469,0,10,53226,'Name',TO_TIMESTAMP('2009-09-01 21:42:16','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:42:16','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Sep 1, 2009 9:42:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58026 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:17 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Trl ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 1, 2009 9:42:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53250,53226,53088,NULL,TO_TIMESTAMP('2009-09-01 21:42:17','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','N','Y','N','N','N','Y','Smart Browse Translation','N',30,0,TO_TIMESTAMP('2009-09-01 21:42:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53250 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:42:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58014,57462,0,53250,TO_TIMESTAMP('2009-09-01 21:42:17','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','Y','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:42:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57462 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58015,57463,0,53250,TO_TIMESTAMP('2009-09-01 21:42:18','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:42:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57463 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58021,57464,0,53250,TO_TIMESTAMP('2009-09-01 21:42:18','YYYY-MM-DD HH24:MI:SS'),0,22,'EE07','Y','Y','Y','N','N','N','N','Smart Browse',30,0,TO_TIMESTAMP('2009-09-01 21:42:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57464 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58022,57465,0,53250,TO_TIMESTAMP('2009-09-01 21:42:19','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE07','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','Language',40,0,TO_TIMESTAMP('2009-09-01 21:42:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57465 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58026,57466,0,53250,TO_TIMESTAMP('2009-09-01 21:42:19','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_TIMESTAMP('2009-09-01 21:42:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57466 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58023,57467,0,53250,TO_TIMESTAMP('2009-09-01 21:42:20','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_TIMESTAMP('2009-09-01 21:42:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57467 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58024,57468,0,53250,TO_TIMESTAMP('2009-09-01 21:42:21','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_TIMESTAMP('2009-09-01 21:42:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57468 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58016,57469,0,53250,TO_TIMESTAMP('2009-09-01 21:42:21','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',80,0,TO_TIMESTAMP('2009-09-01 21:42:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57469 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58025,57470,0,53250,TO_TIMESTAMP('2009-09-01 21:42:22','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE07','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','Translated',90,0,TO_TIMESTAMP('2009-09-01 21:42:22','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57470 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53227,'6',TO_TIMESTAMP('2009-09-01 21:42:23','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Browse Fields','L','AD_Browse_Field_Trl',TO_TIMESTAMP('2009-09-01 21:42:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53227 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:42:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53335,TO_TIMESTAMP('2009-09-01 21:42:23','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_Browse_Field_Trl',1,'Y','N','Y','Y','AD_Browse_Field_Trl','N',1000000,TO_TIMESTAMP('2009-09-01 21:42:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58027,102,0,19,53227,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:42:24','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:42:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58027 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58028,113,0,19,53227,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:42:24','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:42:24','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58028 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58029,348,0,20,53227,'IsActive',TO_TIMESTAMP('2009-09-01 21:42:25','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:42:25','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58029 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58030,245,0,16,53227,'Created',TO_TIMESTAMP('2009-09-01 21:42:25','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:42:25','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58030 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58031,607,0,16,53227,'Updated',TO_TIMESTAMP('2009-09-01 21:42:26','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:42:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58031 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58032,246,0,19,110,53227,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:42:26','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:42:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58032 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58033,608,0,19,110,53227,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:42:27','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:42:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58033 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58034,53903,0,19,53227,'AD_Browse_Field_ID',TO_TIMESTAMP('2009-09-01 21:42:27','YYYY-MM-DD HH24:MI:SS'),0,'EE07',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','Browse Field',TO_TIMESTAMP('2009-09-01 21:42:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58034 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:28 PM ECT
-- View & Smat Browse
CREATE TABLE AD_Browse_Field_Trl (AD_Browse_Field_ID NUMERIC(10) NOT NULL, AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT AD_Browse_Field_Trl_Key PRIMARY KEY (AD_Browse_Field_ID))
;

-- Sep 1, 2009 9:42:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58035,109,0,18,106,53227,'AD_Language',TO_TIMESTAMP('2009-09-01 21:42:30','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE07',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','Y','N','N','Language',TO_TIMESTAMP('2009-09-01 21:42:30','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58035 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:31 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field_Trl ADD COLUMN AD_Language VARCHAR(6) NOT NULL
;

-- Sep 1, 2009 9:42:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58036,275,0,10,53227,'Description',TO_TIMESTAMP('2009-09-01 21:42:31','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-01 21:42:31','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58036 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:32 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field_Trl ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:42:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58037,326,0,14,53227,'Help',TO_TIMESTAMP('2009-09-01 21:42:32','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:42:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58037 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:32 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field_Trl ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:42:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58038,420,0,20,53227,'IsTranslated',TO_TIMESTAMP('2009-09-01 21:42:32','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE07',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Translated',TO_TIMESTAMP('2009-09-01 21:42:32','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58038 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:33 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field_Trl ADD COLUMN IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL
;

-- Sep 1, 2009 9:42:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58039,469,0,10,53227,'Name',TO_TIMESTAMP('2009-09-01 21:42:33','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:42:33','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58039 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:34 PM ECT
-- View & Smat Browse
ALTER TABLE AD_Browse_Field_Trl ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 1, 2009 9:42:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53251,53227,53088,NULL,TO_TIMESTAMP('2009-09-01 21:42:34','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','N','Y','N','N','N','Y','Smart Browse Fields Tranlation','N',60,2,TO_TIMESTAMP('2009-09-01 21:42:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53251 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:42:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58027,57471,0,53251,TO_TIMESTAMP('2009-09-01 21:42:34','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:42:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57471 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58028,57472,0,53251,TO_TIMESTAMP('2009-09-01 21:42:35','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:42:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57472 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58034,57473,0,53251,TO_TIMESTAMP('2009-09-01 21:42:36','YYYY-MM-DD HH24:MI:SS'),0,22,'EE07','Y','Y','Y','N','N','N','N','Browse Field',30,0,TO_TIMESTAMP('2009-09-01 21:42:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57473 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58035,57474,0,53251,TO_TIMESTAMP('2009-09-01 21:42:36','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE07','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','Language',40,0,TO_TIMESTAMP('2009-09-01 21:42:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57474 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58039,57475,0,53251,TO_TIMESTAMP('2009-09-01 21:42:37','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_TIMESTAMP('2009-09-01 21:42:37','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57475 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58036,57476,0,53251,TO_TIMESTAMP('2009-09-01 21:42:38','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_TIMESTAMP('2009-09-01 21:42:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57476 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58037,57477,0,53251,TO_TIMESTAMP('2009-09-01 21:42:38','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_TIMESTAMP('2009-09-01 21:42:38','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57477 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58029,57478,0,53251,TO_TIMESTAMP('2009-09-01 21:42:39','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',80,0,TO_TIMESTAMP('2009-09-01 21:42:39','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57478 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58038,57479,0,53251,TO_TIMESTAMP('2009-09-01 21:42:40','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE07','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','Translated',90,0,TO_TIMESTAMP('2009-09-01 21:42:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57479 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_ColumnSortOrder_ID,AD_ColumnSortYesNo_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,57982,57976,0,53252,53223,53088,NULL,TO_TIMESTAMP('2009-09-01 21:42:40','YYYY-MM-DD HH24:MI:SS'),0,'Define Smart Browse Fields Sequence','EE07','N','The tab field sequence allows you to define fields that will be displayed in Smart Browser and the order they are displayed, you can easily include or exclud','Y','N','N','Y','N','N','Y','N','Smart Browse Fields Sequence','N',40,1,TO_TIMESTAMP('2009-09-01 21:42:40','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53252 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:42:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Window (AD_Client_ID,AD_Org_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,IsBetaFunctionality,IsDefault,IsSOTrx,Name,Processing,Updated,UpdatedBy,WindowType) VALUES (0,0,53089,TO_TIMESTAMP('2009-09-01 21:42:41','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Report Views.','EE07','View window allows you to create dynamic views of information from the dictionary application, These views can be based on tables and views of the dictionary application.','Y','N','N','N','View','N',TO_TIMESTAMP('2009-09-01 21:42:41','YYYY-MM-DD HH24:MI:SS'),0,'M')
;

-- Sep 1, 2009 9:42:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Window_Trl (AD_Language,AD_Window_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Window_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Window t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Window_ID=53089 AND EXISTS (SELECT * FROM AD_Window_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Window_ID!=t.AD_Window_ID)
;

-- Sep 1, 2009 9:42:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53228,'6',TO_TIMESTAMP('2009-09-01 21:42:41','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Views Column Trl','L','AD_View_Column_Trl',TO_TIMESTAMP('2009-09-01 21:42:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53228 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:42:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53336,TO_TIMESTAMP('2009-09-01 21:42:42','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_View_Column_Trl',1,'Y','N','Y','Y','AD_View_Column_Trl','N',1000000,TO_TIMESTAMP('2009-09-01 21:42:42','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58040,102,0,19,53228,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:42:43','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:42:43','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:43 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58040 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58041,113,0,19,53228,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:42:43','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:42:43','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58041 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58042,348,0,20,53228,'IsActive',TO_TIMESTAMP('2009-09-01 21:42:44','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:42:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:44 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58042 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58043,245,0,16,53228,'Created',TO_TIMESTAMP('2009-09-01 21:42:44','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:42:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58043 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58044,607,0,16,53228,'Updated',TO_TIMESTAMP('2009-09-01 21:42:45','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:42:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58044 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58045,246,0,19,110,53228,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:42:45','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:42:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58045 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58046,608,0,19,110,53228,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:42:46','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:42:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58046 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:47 PM ECT
-- View & Smat Browse
CREATE TABLE AD_View_Column_Trl (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL)
;

-- Sep 1, 2009 9:42:49 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58047,53904,0,19,53228,'AD_View_Column_ID',TO_TIMESTAMP('2009-09-01 21:42:49','YYYY-MM-DD HH24:MI:SS'),0,'Column of View','EE07',22,'Y','N','N','N','N','Y','Y','N','Y','N','N','View Column',TO_TIMESTAMP('2009-09-01 21:42:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:49 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58047 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:49 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column_Trl ADD COLUMN AD_View_Column_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:42:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58048,109,0,18,106,53228,'AD_Language',TO_TIMESTAMP('2009-09-01 21:42:49','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE07',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','Y','N','Y','N','N','Language',TO_TIMESTAMP('2009-09-01 21:42:49','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58048 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:50 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column_Trl ADD COLUMN AD_Language VARCHAR(6) NOT NULL
;

-- Sep 1, 2009 9:42:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58049,275,0,10,53228,'Description',TO_TIMESTAMP('2009-09-01 21:42:50','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-01 21:42:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58049 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:50 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column_Trl ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:42:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58050,326,0,14,53228,'Help',TO_TIMESTAMP('2009-09-01 21:42:50','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:42:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58050 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:51 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column_Trl ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:42:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58051,420,0,20,53228,'IsTranslated',TO_TIMESTAMP('2009-09-01 21:42:51','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE07',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Translated',TO_TIMESTAMP('2009-09-01 21:42:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58051 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:51 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column_Trl ADD COLUMN IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL
;

-- Sep 1, 2009 9:42:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58052,469,0,10,53228,'Name',TO_TIMESTAMP('2009-09-01 21:42:51','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:42:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:42:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58052 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:42:52 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column_Trl ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 1, 2009 9:42:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53253,53228,53089,NULL,TO_TIMESTAMP('2009-09-01 21:42:53','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','N','Y','N','N','N','Y','View Columns Translation','N',50,2,TO_TIMESTAMP('2009-09-01 21:42:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53253 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:42:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58040,57480,0,53253,TO_TIMESTAMP('2009-09-01 21:42:54','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:42:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57480 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58041,57481,0,53253,TO_TIMESTAMP('2009-09-01 21:42:55','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:42:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57481 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58042,57482,0,53253,TO_TIMESTAMP('2009-09-01 21:42:55','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',30,0,TO_TIMESTAMP('2009-09-01 21:42:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57482 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58047,57483,0,53253,TO_TIMESTAMP('2009-09-01 21:42:57','YYYY-MM-DD HH24:MI:SS'),0,'Column of View',22,'EE07','Y','Y','Y','N','N','N','N','View Column',40,0,TO_TIMESTAMP('2009-09-01 21:42:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57483 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:58 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58048,57484,0,53253,TO_TIMESTAMP('2009-09-01 21:42:57','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE07','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','Language',50,0,TO_TIMESTAMP('2009-09-01 21:42:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:58 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57484 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:58 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58052,57485,0,53253,TO_TIMESTAMP('2009-09-01 21:42:58','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',60,0,TO_TIMESTAMP('2009-09-01 21:42:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:58 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57485 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:42:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58049,57486,0,53253,TO_TIMESTAMP('2009-09-01 21:42:58','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',70,0,TO_TIMESTAMP('2009-09-01 21:42:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:42:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57486 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58050,57487,0,53253,TO_TIMESTAMP('2009-09-01 21:42:59','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',80,0,TO_TIMESTAMP('2009-09-01 21:42:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57487 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58051,57488,0,53253,TO_TIMESTAMP('2009-09-01 21:43:00','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE07','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','Translated',90,0,TO_TIMESTAMP('2009-09-01 21:43:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57488 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53229,'6',TO_TIMESTAMP('2009-09-01 21:43:00','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','Smart Views Translation','L','AD_View_Trl',TO_TIMESTAMP('2009-09-01 21:43:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53229 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:43:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53337,TO_TIMESTAMP('2009-09-01 21:43:01','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_View_Trl',1,'Y','N','Y','Y','AD_View_Trl','N',1000000,TO_TIMESTAMP('2009-09-01 21:43:01','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58053,102,0,19,53229,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:43:01','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:43:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58053 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58054,113,0,19,53229,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:43:02','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:43:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58054 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58055,348,0,20,53229,'IsActive',TO_TIMESTAMP('2009-09-01 21:43:02','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:43:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58055 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58056,245,0,16,53229,'Created',TO_TIMESTAMP('2009-09-01 21:43:03','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:43:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:03 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58056 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58057,607,0,16,53229,'Updated',TO_TIMESTAMP('2009-09-01 21:43:03','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:43:03','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58057 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58058,246,0,19,110,53229,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:43:04','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:43:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58058 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58059,608,0,19,110,53229,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:43:05','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:43:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58059 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:05 PM ECT
-- View & Smat Browse
CREATE TABLE AD_View_Trl (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL)
;

-- Sep 1, 2009 9:43:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58060,53905,0,19,53229,'AD_View_ID',TO_TIMESTAMP('2009-09-01 21:43:07','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','N','N','N','N','Y','Y','N','Y','N','N','View',TO_TIMESTAMP('2009-09-01 21:43:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58060 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:08 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Trl ADD COLUMN AD_View_ID NUMERIC(10) NOT NULL
;

-- Sep 1, 2009 9:43:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58061,109,0,18,106,53229,'AD_Language',TO_TIMESTAMP('2009-09-01 21:43:08','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity','EE07',6,'The Language identifies the language to use for display and formatting','Y','N','N','N','N','Y','N','N','Y','N','N','Language',TO_TIMESTAMP('2009-09-01 21:43:08','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58061 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:08 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Trl ADD COLUMN AD_Language VARCHAR(6) NOT NULL
;

-- Sep 1, 2009 9:43:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58062,275,0,10,53229,'Description',TO_TIMESTAMP('2009-09-01 21:43:09','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-01 21:43:09','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:09 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58062 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:09 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Trl ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58063,420,0,20,53229,'IsTranslated',TO_TIMESTAMP('2009-09-01 21:43:09','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated','EE07',1,'The Translated checkbox indicates if this column is translated.','Y','N','N','N','N','Y','N','N','Y','N','Y','Translated',TO_TIMESTAMP('2009-09-01 21:43:09','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58063 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:10 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Trl ADD COLUMN IsTranslated CHAR(1) CHECK (IsTranslated IN ('Y','N')) NOT NULL
;

-- Sep 1, 2009 9:43:10 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58064,469,0,10,53229,'Name',TO_TIMESTAMP('2009-09-01 21:43:10','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','N','Name',1,TO_TIMESTAMP('2009-09-01 21:43:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58064 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:11 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Trl ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 1, 2009 9:43:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58065,326,0,14,53229,'Help',TO_TIMESTAMP('2009-09-01 21:43:11','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:43:11','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58065 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:11 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Trl ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,EntityType,HasTree,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53254,53229,53089,NULL,TO_TIMESTAMP('2009-09-01 21:43:11','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','N','Y','N','N','N','Y','View Translation','N',20,1,TO_TIMESTAMP('2009-09-01 21:43:11','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53254 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:43:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58053,57489,0,53254,TO_TIMESTAMP('2009-09-01 21:43:12','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:43:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57489 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:13 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58054,57490,0,53254,TO_TIMESTAMP('2009-09-01 21:43:12','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:43:12','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:13 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57490 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58060,57491,0,53254,TO_TIMESTAMP('2009-09-01 21:43:13','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','Y','N','N','N','N','View',30,0,TO_TIMESTAMP('2009-09-01 21:43:13','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57491 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58061,57492,0,53254,TO_TIMESTAMP('2009-09-01 21:43:14','YYYY-MM-DD HH24:MI:SS'),0,'Language for this entity',6,'EE07','The Language identifies the language to use for display and formatting','Y','Y','Y','N','N','N','N','Language',40,0,TO_TIMESTAMP('2009-09-01 21:43:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57492 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58064,57493,0,53254,TO_TIMESTAMP('2009-09-01 21:43:14','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',50,0,TO_TIMESTAMP('2009-09-01 21:43:14','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57493 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58062,57494,0,53254,TO_TIMESTAMP('2009-09-01 21:43:15','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',60,0,TO_TIMESTAMP('2009-09-01 21:43:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57494 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58065,57495,0,53254,TO_TIMESTAMP('2009-09-01 21:43:15','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',70,0,TO_TIMESTAMP('2009-09-01 21:43:15','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57495 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58055,57496,0,53254,TO_TIMESTAMP('2009-09-01 21:43:16','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',80,0,TO_TIMESTAMP('2009-09-01 21:43:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:16 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57496 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58063,57497,0,53254,TO_TIMESTAMP('2009-09-01 21:43:16','YYYY-MM-DD HH24:MI:SS'),0,'This column is translated',1,'EE07','The Translated checkbox indicates if this column is translated.','Y','Y','Y','N','N','N','N','Translated',90,0,TO_TIMESTAMP('2009-09-01 21:43:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57497 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53230,'6',TO_TIMESTAMP('2009-09-01 21:43:17','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','Y','Y','N','N','N','View','L','AD_View',TO_TIMESTAMP('2009-09-01 21:43:17','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53230 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:43:18 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53338,TO_TIMESTAMP('2009-09-01 21:43:18','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_View',1,'Y','N','Y','Y','AD_View','N',1000000,TO_TIMESTAMP('2009-09-01 21:43:18','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58066,102,0,19,53230,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:43:18','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:43:18','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58066 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58067,113,0,19,53230,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:43:19','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:43:19','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58067 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58068,348,0,20,53230,'IsActive',TO_TIMESTAMP('2009-09-01 21:43:19','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:43:19','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58068 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58069,245,0,16,53230,'Created',TO_TIMESTAMP('2009-09-01 21:43:20','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:43:20','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58069 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58070,607,0,16,53230,'Updated',TO_TIMESTAMP('2009-09-01 21:43:20','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:43:20','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58070 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58071,246,0,19,110,53230,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:43:21','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:43:21','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58071 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58072,608,0,19,110,53230,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:43:22','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:43:22','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58072 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58073,53905,0,13,53230,'AD_View_ID',TO_TIMESTAMP('2009-09-01 21:43:22','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','N','N','N','Y','Y','N','N','Y','N','N','View',TO_TIMESTAMP('2009-09-01 21:43:22','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:23 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58073 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:23 PM ECT
-- View & Smat Browse
CREATE TABLE AD_View (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, AD_View_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT AD_View_Key PRIMARY KEY (AD_View_ID))
;

-- Sep 1, 2009 9:43:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58074,620,0,10,53230,'Value',TO_TIMESTAMP('2009-09-01 21:43:25','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique','EE07',60,'A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','N','N','N','N','N','N','N','Y','N','Y','Search Key',TO_TIMESTAMP('2009-09-01 21:43:25','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58074 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:26 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View ADD COLUMN Value VARCHAR(60) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58075,469,0,10,53230,'Name',TO_TIMESTAMP('2009-09-01 21:43:26','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','Y','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:43:26','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58075 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:27 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 1, 2009 9:43:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58076,275,0,10,53230,'Description',TO_TIMESTAMP('2009-09-01 21:43:27','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','Y','Y','Description',TO_TIMESTAMP('2009-09-01 21:43:27','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58076 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:29 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58077,1682,0,18,389,53230,'EntityType',TO_TIMESTAMP('2009-09-01 21:43:29','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization','EE07',40,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','N','N','N','N','Y','N','N','Y','N','Y','Entity Type',TO_TIMESTAMP('2009-09-01 21:43:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58077 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:29 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View ADD COLUMN EntityType VARCHAR(40) NOT NULL
;

-- Sep 1, 2009 9:43:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58078,326,0,14,53230,'Help',TO_TIMESTAMP('2009-09-01 21:43:29','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','Y','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:43:29','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58078 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:30 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53255,53230,53089,NULL,TO_TIMESTAMP('2009-09-01 21:43:30','YYYY-MM-DD HH24:MI:SS'),0,'Define View','EE07','N','View window allows you to create dynamic views of information from the dictionary application, These views can be based on tables and views of the dictionary application.','Y','N','N','Y','N','Y','N','N','View','N',10,0,TO_TIMESTAMP('2009-09-01 21:43:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53255 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:43:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58066,57498,0,53255,TO_TIMESTAMP('2009-09-01 21:43:31','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:43:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57498 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58067,57499,0,53255,TO_TIMESTAMP('2009-09-01 21:43:31','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:43:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57499 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58074,57500,0,53255,TO_TIMESTAMP('2009-09-01 21:43:32','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',60,'D','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','Search Key',30,0,TO_TIMESTAMP('2009-09-01 21:43:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57500 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58075,57501,0,53255,TO_TIMESTAMP('2009-09-01 21:43:32','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'D','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',40,0,TO_TIMESTAMP('2009-09-01 21:43:32','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57501 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58076,57502,0,53255,TO_TIMESTAMP('2009-09-01 21:43:33','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',50,0,TO_TIMESTAMP('2009-09-01 21:43:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:33 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57502 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58078,57503,0,53255,TO_TIMESTAMP('2009-09-01 21:43:33','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',60,0,TO_TIMESTAMP('2009-09-01 21:43:33','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57503 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58068,57504,0,53255,TO_TIMESTAMP('2009-09-01 21:43:34','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2009-09-01 21:43:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:34 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57504 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58077,57505,0,53255,TO_TIMESTAMP('2009-09-01 21:43:34','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization',20,'EE07','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','Y','Y','N','N','N','N','Entity Type',100,0,TO_TIMESTAMP('2009-09-01 21:43:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57505 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58073,57506,0,53255,TO_TIMESTAMP('2009-09-01 21:43:35','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','N','N','N','N','N','View',0,0,TO_TIMESTAMP('2009-09-01 21:43:35','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:35 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57506 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53231,'6',TO_TIMESTAMP('2009-09-01 21:43:36','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','View Definition','L','AD_View_Definition',TO_TIMESTAMP('2009-09-01 21:43:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:36 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53231 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:43:37 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53339,TO_TIMESTAMP('2009-09-01 21:43:36','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_View_Definition',1,'Y','N','Y','Y','AD_View_Definition','N',1000000,TO_TIMESTAMP('2009-09-01 21:43:36','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58079,102,0,19,53231,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:43:37','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:43:37','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58079 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58080,113,0,19,53231,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:43:38','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:43:38','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:38 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58080 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58081,348,0,20,53231,'IsActive',TO_TIMESTAMP('2009-09-01 21:43:38','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:43:38','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58081 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58082,245,0,16,53231,'Created',TO_TIMESTAMP('2009-09-01 21:43:39','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:43:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:39 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58082 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58083,607,0,16,53231,'Updated',TO_TIMESTAMP('2009-09-01 21:43:39','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:43:39','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58083 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58084,246,0,19,110,53231,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:43:40','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:43:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:40 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58084 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58085,608,0,19,110,53231,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:43:40','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:43:40','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58085 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53906,0,'AD_View_Definition_ID',TO_TIMESTAMP('2009-09-01 21:43:41','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','View Definition ID','View Definition ID',TO_TIMESTAMP('2009-09-01 21:43:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:41 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53906 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:43:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58086,53906,0,13,53231,'AD_View_Definition_ID',TO_TIMESTAMP('2009-09-01 21:43:41','YYYY-MM-DD HH24:MI:SS'),0,NULL,'EE07',22,'Y','Y','N','N','N','Y','Y','N','N','N','N','View Definition ID',TO_TIMESTAMP('2009-09-01 21:43:41','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:42 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58086 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:42 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_View_Definition_ID', Description='The View Definition allow defined the tables for a view.', EntityType='EE07', Help=NULL, IsActive='Y', Name='View Definition', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='View Definition',Updated=TO_TIMESTAMP('2009-09-01 21:43:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53906
;

-- Sep 1, 2009 9:43:42 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53906
;

-- Sep 1, 2009 9:43:42 PM ECT
-- View & Smat Browse
CREATE TABLE AD_View_Definition (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, AD_View_Definition_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT AD_View_Definition_Key PRIMARY KEY (AD_View_Definition_ID))
;

-- Sep 1, 2009 9:43:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58087,53905,0,19,53231,'AD_View_ID',TO_TIMESTAMP('2009-09-01 21:43:44','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','N','N','N','N','N','Y','N','Y','N','N','View',TO_TIMESTAMP('2009-09-01 21:43:44','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:45 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58087 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:45 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Definition ADD COLUMN AD_View_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58088,566,0,11,53231,'SeqNo',TO_TIMESTAMP('2009-09-01 21:43:45','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first','EE07',22,'The Sequence indicates the order of records','Y','N','N','N','N','N','N','N','Y','N','Y','Sequence',TO_TIMESTAMP('2009-09-01 21:43:45','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:46 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58088 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:46 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Definition ADD COLUMN SeqNo NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:46 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Table_ID', Description='Database Table information', EntityType='D', Help='The Database Table provides the information of the table definition', IsActive='Y', Name='Table', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Table',Updated=TO_TIMESTAMP('2009-09-01 21:43:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=126
;

-- Sep 1, 2009 9:43:46 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=126
;

-- Sep 1, 2009 9:43:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58089,126,0,19,53231,'AD_Table_ID',TO_TIMESTAMP('2009-09-01 21:43:46','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information','EE07',22,'The Database Table provides the information of the table definition','Y','N','N','Y','N','N','N','N','Y','N','Y','Table',1,TO_TIMESTAMP('2009-09-01 21:43:46','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58089 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:47 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Definition ADD COLUMN AD_Table_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53907,0,'TableAlias',TO_TIMESTAMP('2009-09-01 21:43:47','YYYY-MM-DD HH24:MI:SS'),0,'Alias of the table in the view','EE07','The DB Table Alias indicates the name of the alias in view.','Y','DB Table Alias','DB Table Alias',TO_TIMESTAMP('2009-09-01 21:43:47','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:47 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53907 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:43:48 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58090,53907,0,10,53231,'TableAlias',TO_TIMESTAMP('2009-09-01 21:43:47','YYYY-MM-DD HH24:MI:SS'),0,'Alias of the table in the view','EE07',20,'The DB Table Alias indicates the name of the alias in view.','Y','N','N','N','N','Y','N','N','Y','N','Y','DB Table Alias',TO_TIMESTAMP('2009-09-01 21:43:47','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:48 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58090 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:48 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Definition ADD COLUMN TableAlias VARCHAR(20) NOT NULL
;

-- Sep 1, 2009 9:43:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53908,0,'JoinClause',TO_TIMESTAMP('2009-09-01 21:43:48','YYYY-MM-DD HH24:MI:SS'),0,'Defined the Join Clause between Tables','EE07','Y','Join Clause','Join Caluse',TO_TIMESTAMP('2009-09-01 21:43:48','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53908 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Sep 1, 2009 9:43:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58091,53908,0,14,53231,'JoinClause',TO_TIMESTAMP('2009-09-01 21:43:50','YYYY-MM-DD HH24:MI:SS'),0,'Defined the Join Clause between Tables','EE07',255,'Y','N','N','N','N','N','N','N','Y','N','Y','Join Clause',TO_TIMESTAMP('2009-09-01 21:43:50','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:50 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58091 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:50 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Definition ADD COLUMN JoinClause VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Process (AD_Client_ID,AD_Org_ID,AD_Process_ID,AccessLevel,Classname,Created,CreatedBy,EntityType,IsActive,IsBetaFunctionality,IsDirectPrint,IsReport,Name,ShowHelp,Statistic_Count,Statistic_Seconds,Updated,UpdatedBy,Value,WorkflowValue) VALUES (0,0,53181,'6','org.eevolution.process.CreateViewColumn',TO_TIMESTAMP('2009-09-01 21:43:51','YYYY-MM-DD HH24:MI:SS'),0,'EE07','Y','N','N','N','Create Column','Y',0,0,TO_TIMESTAMP('2009-09-01 21:43:51','YYYY-MM-DD HH24:MI:SS'),0,'AD_View_CreateColumn',NULL)
;

-- Sep 1, 2009 9:43:51 PM ECT
-- View & Smat Browse
INSERT INTO AD_Process_Trl (AD_Language,AD_Process_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Process_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Process t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_ID=53181 AND EXISTS (SELECT * FROM AD_Process_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Process_ID!=t.AD_Process_ID)
;

-- Sep 1, 2009 9:43:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Process_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58092,524,0,53181,28,53231,'Processing',TO_TIMESTAMP('2009-09-01 21:43:51','YYYY-MM-DD HH24:MI:SS'),0,'EE07',1,'Y','N','N','N','N','N','N','N','Y','N','Y','Process Now',TO_TIMESTAMP('2009-09-01 21:43:51','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:43:52 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58092 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:43:52 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Definition ADD COLUMN Processing CHAR(1) DEFAULT NULL 
;

-- Sep 1, 2009 9:43:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,OrderByClause,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53256,53231,53089,NULL,TO_TIMESTAMP('2009-09-01 21:43:52','YYYY-MM-DD HH24:MI:SS'),0,'Define View Definition','EE07','N','The View Definition allow add each table and its alias for this view, to define a parent table do not put any value in Join Clause to join several tables you must use the correct SQL sentence for example:

INNER JOIN M_Product p ON (p.M_Product_ID = ol.M_Product_ID).
','Y','N','N','Y','N','Y','N','N','View Definition','SeqNo','N',30,1,TO_TIMESTAMP('2009-09-01 21:43:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:53 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53256 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:43:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58079,57507,0,53256,TO_TIMESTAMP('2009-09-01 21:43:53','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','Client',10,0,TO_TIMESTAMP('2009-09-01 21:43:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57507 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58080,57508,0,53256,TO_TIMESTAMP('2009-09-01 21:43:54','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','Y','Organization',20,0,TO_TIMESTAMP('2009-09-01 21:43:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:54 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57508 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58087,57509,0,53256,TO_TIMESTAMP('2009-09-01 21:43:54','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','Y','N','N','Y','N','View',30,0,TO_TIMESTAMP('2009-09-01 21:43:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:55 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57509 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58088,57510,0,53256,TO_TIMESTAMP('2009-09-01 21:43:55','YYYY-MM-DD HH24:MI:SS'),0,'Method of ordering records; lowest number comes first',22,'EE07','The Sequence indicates the order of records','Y','Y','Y','N','N','N','N','Sequence',40,0,TO_TIMESTAMP('2009-09-01 21:43:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57510 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58089,57511,0,53256,TO_TIMESTAMP('2009-09-01 21:43:56','YYYY-MM-DD HH24:MI:SS'),0,'Database Table information',22,'EE07','The Database Table provides the information of the table definition','Y','Y','Y','N','N','N','N','Table',50,0,TO_TIMESTAMP('2009-09-01 21:43:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:56 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57511 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58090,57512,0,53256,TO_TIMESTAMP('2009-09-01 21:43:56','YYYY-MM-DD HH24:MI:SS'),0,'Alias of the table in the view',20,'EE07','The DB Table Alias indicates the name of the alias in view.','Y','Y','Y','N','N','N','Y','DB Table Alias',60,0,TO_TIMESTAMP('2009-09-01 21:43:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57512 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58081,57513,0,53256,TO_TIMESTAMP('2009-09-01 21:43:57','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',70,0,TO_TIMESTAMP('2009-09-01 21:43:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:57 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57513 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:58 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58091,57514,0,53256,TO_TIMESTAMP('2009-09-01 21:43:57','YYYY-MM-DD HH24:MI:SS'),0,'Defined the Join Clause between Tables',255,'EE07','Y','Y','Y','N','N','N','N','Join Clause',80,0,TO_TIMESTAMP('2009-09-01 21:43:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:58 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57514 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:43:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58086,57515,0,53256,TO_TIMESTAMP('2009-09-01 21:43:58','YYYY-MM-DD HH24:MI:SS'),0,'The View Definition allow defined the tables for a view.',22,'EE07','Y','Y','Y','N','N','N','N','View Definition',90,0,TO_TIMESTAMP('2009-09-01 21:43:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:43:59 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57515 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58092,57516,0,53256,TO_TIMESTAMP('2009-09-01 21:43:59','YYYY-MM-DD HH24:MI:SS'),0,1,'EE07','Y','N','Y','N','N','N','N','Validate Column',100,0,TO_TIMESTAMP('2009-09-01 21:43:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57516 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table (AD_Client_ID,AD_Org_ID,AD_Table_ID,AccessLevel,Created,CreatedBy,EntityType,ImportTable,IsActive,IsChangeLog,IsDeleteable,IsHighVolume,IsSecurityEnabled,IsView,Name,ReplicationType,TableName,Updated,UpdatedBy) VALUES (0,0,53232,'6',TO_TIMESTAMP('2009-09-01 21:44:00','YYYY-MM-DD HH24:MI:SS'),0,'EE07','N','Y','N','Y','N','N','N','View Columns','L','AD_View_Column',TO_TIMESTAMP('2009-09-01 21:44:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:00 PM ECT
-- View & Smat Browse
INSERT INTO AD_Table_Trl (AD_Language,AD_Table_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Table_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Table t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Table_ID=53232 AND EXISTS (SELECT * FROM AD_Table_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Table_ID!=t.AD_Table_ID)
;

-- Sep 1, 2009 9:44:01 PM ECT
-- View & Smat Browse
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53340,TO_TIMESTAMP('2009-09-01 21:44:00','YYYY-MM-DD HH24:MI:SS'),0,1000000,50000,'Table AD_View_Column',1,'Y','N','Y','Y','AD_View_Column','N',1000000,TO_TIMESTAMP('2009-09-01 21:44:00','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58093,102,0,19,53232,129,'AD_Client_ID',TO_TIMESTAMP('2009-09-01 21:44:01','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Client_ID@','Client/Tenant for this installation.','EE07',22,'A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','Y','N','N','N','Y','Client',TO_TIMESTAMP('2009-09-01 21:44:01','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58093 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,AD_Val_Rule_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58094,113,0,19,53232,104,'AD_Org_ID',TO_TIMESTAMP('2009-09-01 21:44:02','YYYY-MM-DD HH24:MI:SS'),0,'@#AD_Org_ID@','Organizational entity within client','EE07',22,'An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','N','N','N','Y','Organization',TO_TIMESTAMP('2009-09-01 21:44:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:02 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58094 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58095,348,0,20,53232,'IsActive',TO_TIMESTAMP('2009-09-01 21:44:02','YYYY-MM-DD HH24:MI:SS'),0,'Y','The record is active in the system','EE07',1,'There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','N','N','N','N','Y','N','N','N','Y','Active',TO_TIMESTAMP('2009-09-01 21:44:02','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:04 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58095 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58096,245,0,16,53232,'Created',TO_TIMESTAMP('2009-09-01 21:44:04','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was created','EE07',7,'The Created field indicates the date that this record was created.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created',TO_TIMESTAMP('2009-09-01 21:44:04','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58096 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:05 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58097,607,0,16,53232,'Updated',TO_TIMESTAMP('2009-09-01 21:44:05','YYYY-MM-DD HH24:MI:SS'),0,NULL,'Date this record was updated','EE07',7,'The Updated field indicates the date that this record was updated.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated',TO_TIMESTAMP('2009-09-01 21:44:05','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58097 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58098,246,0,19,110,53232,'CreatedBy',TO_TIMESTAMP('2009-09-01 21:44:06','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who created this records','EE07',22,'The Created By field indicates the user who created this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Created By',TO_TIMESTAMP('2009-09-01 21:44:06','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:06 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58098 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58099,608,0,19,110,53232,'UpdatedBy',TO_TIMESTAMP('2009-09-01 21:44:06','YYYY-MM-DD HH24:MI:SS'),0,NULL,'User who updated this records','EE07',22,'The Updated By field indicates the user who updated this record.','Y','Y','N','N','N','N','Y','N','N','N','Y','Updated By',TO_TIMESTAMP('2009-09-01 21:44:06','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:07 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58099 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58100,53904,0,13,53232,'AD_View_Column_ID',TO_TIMESTAMP('2009-09-01 21:44:07','YYYY-MM-DD HH24:MI:SS'),0,'Column of View','EE07',22,'Y','N','N','N','Y','Y','N','N','Y','N','N','View Column',TO_TIMESTAMP('2009-09-01 21:44:07','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:08 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58100 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:08 PM ECT
-- View & Smat Browse
CREATE TABLE AD_View_Column (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, AD_View_Column_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT AD_View_Column_Key PRIMARY KEY (AD_View_Column_ID))
;

-- Sep 1, 2009 9:44:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58101,53906,0,19,53232,'AD_View_Definition_ID',TO_TIMESTAMP('2009-09-01 21:44:10','YYYY-MM-DD HH24:MI:SS'),0,'The View Definition allow defined the tables for a view.','EE07',22,'Y','N','N','N','N','N','Y','N','Y','N','N','View Definition',TO_TIMESTAMP('2009-09-01 21:44:10','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:11 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58101 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:11 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN AD_View_Definition_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58102,275,0,10,53232,'Description',TO_TIMESTAMP('2009-09-01 21:44:11','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record','EE07',255,'A description is limited to 255 characters.','Y','N','N','N','N','N','N','N','Y','N','Y','Description',TO_TIMESTAMP('2009-09-01 21:44:11','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:12 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58102 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:12 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN Description VARCHAR(255) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:13 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58103,1682,0,18,389,53232,'EntityType',TO_TIMESTAMP('2009-09-01 21:44:12','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization','EE07',40,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','N','N','N','N','Y','N','N','Y','N','Y','Entity Type',TO_TIMESTAMP('2009-09-01 21:44:12','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:13 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58103 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:13 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN EntityType VARCHAR(40) NOT NULL
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58104,326,0,14,53232,'Help',TO_TIMESTAMP('2009-09-01 21:44:13','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint','EE07',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','N','N','N','N','N','N','N','Y','N','Y','Comment/Help',TO_TIMESTAMP('2009-09-01 21:44:13','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58104 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,58105,469,0,10,53232,'Name',TO_TIMESTAMP('2009-09-01 21:44:14','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity','EE07',60,'The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','N','N','Y','N','Y','N','N','Y','N','Y','Name',1,TO_TIMESTAMP('2009-09-01 21:44:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58105 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN Name VARCHAR(60) NOT NULL
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='ColumnSQL', Description='Virtual Column (r/o)', EntityType='D', Help='You can define virtual columns (not stored in the database). If defined, the Column name is the synonym of the SQL expression defined here. The SQL expression must be valid.<br>
Example: "Updated-Created" would list the age of the entry in days', IsActive='Y', Name='Column SQL', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Column SQL',Updated=TO_TIMESTAMP('2009-09-01 21:44:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=2699
;

-- Sep 1, 2009 9:44:14 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2699
;

-- Sep 1, 2009 9:44:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58106,2699,0,10,53232,'ColumnSQL',TO_TIMESTAMP('2009-09-01 21:44:14','YYYY-MM-DD HH24:MI:SS'),0,'Virtual Column (r/o)','EE07',2000,'You can define virtual columns (not stored in the database). If defined, the Column name is the synonym of the SQL expression defined here. The SQL expression must be valid.<br>
Example: "Updated-Created" would list the age of the entry in days','Y','N','N','N','N','N','N','N','Y','N','Y','Column SQL',TO_TIMESTAMP('2009-09-01 21:44:14','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:15 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58106 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:15 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN ColumnSQL VARCHAR(2000) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:16 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='ColumnName', Description='Name of the column in the database', EntityType='D', Help='The Column Name indicates the name of a column on a table as defined in the database.', IsActive='Y', Name='DB Column Name', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='DB Column Name',Updated=TO_TIMESTAMP('2009-09-01 21:44:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=228
;

-- Sep 1, 2009 9:44:16 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=228
;

-- Sep 1, 2009 9:44:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58107,228,0,10,53232,'ColumnName',TO_TIMESTAMP('2009-09-01 21:44:16','YYYY-MM-DD HH24:MI:SS'),0,'Name of the column in the database','EE07',30,'The Column Name indicates the name of a column on a table as defined in the database.','Y','N','N','N','N','N','N','N','Y','N','Y','DB Column Name',TO_TIMESTAMP('2009-09-01 21:44:16','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58107 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:17 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN ColumnName VARCHAR(30) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58108,53905,0,19,53232,'AD_View_ID',TO_TIMESTAMP('2009-09-01 21:44:17','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application','EE07',22,'These views can be based on tables and views of the dictionary application.','Y','N','N','N','N','N','N','N','Y','N','Y','View',TO_TIMESTAMP('2009-09-01 21:44:17','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:17 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58108 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:18 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN AD_View_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:18 PM ECT
-- View & Smat Browse
UPDATE AD_Element SET ColumnName='AD_Column_ID', Description='Column in the table', EntityType='D', Help='Link to the database column of the table', IsActive='Y', Name='Column', PO_Description=NULL, PO_Help=NULL, PO_Name=NULL, PO_PrintName=NULL, PrintName='Column',Updated=TO_TIMESTAMP('2009-09-01 21:44:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=104
;

-- Sep 1, 2009 9:44:18 PM ECT
-- View & Smat Browse
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=104
;

-- Sep 1, 2009 9:44:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,58109,104,0,19,53232,'AD_Column_ID',TO_TIMESTAMP('2009-09-01 21:44:18','YYYY-MM-DD HH24:MI:SS'),0,'Column in the table','EE07',22,'Link to the database column of the table','Y','N','N','N','N','N','N','N','Y','N','N','Column',TO_TIMESTAMP('2009-09-01 21:44:18','YYYY-MM-DD HH24:MI:SS'),0,1)
;

-- Sep 1, 2009 9:44:19 PM ECT
-- View & Smat Browse
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=58109 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Sep 1, 2009 9:44:19 PM ECT
-- View & Smat Browse
ALTER TABLE AD_View_Column ADD COLUMN AD_Column_ID NUMERIC(10) DEFAULT NULL 
;

-- Sep 1, 2009 9:44:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab (AD_Client_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,CommitWarning,Created,CreatedBy,Description,EntityType,HasTree,Help,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,0,53257,53232,53089,NULL,TO_TIMESTAMP('2009-09-01 21:44:19','YYYY-MM-DD HH24:MI:SS'),0,'View Columns definitions','EE07','N','The View Column Tab defines the column that are include this view.  Changes made to the Column View Tab become visible after restart due to caching. If the Sequence is negative, the record are ordered descending. Note that the name, description and help is automatically synchronized if centrally maintained.','Y','N','N','Y','N','N','N','N','View Columns','N',40,1,TO_TIMESTAMP('2009-09-01 21:44:19','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:20 PM ECT
-- View & Smat Browse
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53257 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- Sep 1, 2009 9:44:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58108,57517,0,53257,TO_TIMESTAMP('2009-09-01 21:44:20','YYYY-MM-DD HH24:MI:SS'),0,'View allows you to create dynamic views of information from the dictionary application',22,'EE07','These views can be based on tables and views of the dictionary application.','Y','Y','N','N','N','N','N','View',0,0,TO_TIMESTAMP('2009-09-01 21:44:20','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:21 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57517 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58094,57518,0,53257,TO_TIMESTAMP('2009-09-01 21:44:21','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE07','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','N','N','N','N','Y','Organization',0,0,TO_TIMESTAMP('2009-09-01 21:44:21','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:22 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57518 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58100,57519,0,53257,TO_TIMESTAMP('2009-09-01 21:44:23','YYYY-MM-DD HH24:MI:SS'),0,'Column of View',22,'EE07','Y','Y','N','N','N','N','N','View Column',0,0,TO_TIMESTAMP('2009-09-01 21:44:23','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57519 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58101,57520,0,53257,TO_TIMESTAMP('2009-09-01 21:44:24','YYYY-MM-DD HH24:MI:SS'),0,'The View Definition allow defined the tables for a view.',22,'EE07','Y','Y','N','N','N','Y','N','View Definition',0,0,TO_TIMESTAMP('2009-09-01 21:44:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:24 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57520 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58093,57521,0,53257,TO_TIMESTAMP('2009-09-01 21:44:24','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE07','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','N','N','N','N','N','Client',0,0,TO_TIMESTAMP('2009-09-01 21:44:24','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:25 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57521 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58105,57522,0,53257,TO_TIMESTAMP('2009-09-01 21:44:25','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE07','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','Name',10,0,TO_TIMESTAMP('2009-09-01 21:44:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:26 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57522 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58102,57523,0,53257,TO_TIMESTAMP('2009-09-01 21:44:26','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE07','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','Description',20,0,TO_TIMESTAMP('2009-09-01 21:44:26','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57523 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58104,57524,0,53257,TO_TIMESTAMP('2009-09-01 21:44:27','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE07','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','Comment/Help',30,0,TO_TIMESTAMP('2009-09-01 21:44:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:27 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57524 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58095,57525,0,53257,TO_TIMESTAMP('2009-09-01 21:44:27','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE07','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','Active',40,0,TO_TIMESTAMP('2009-09-01 21:44:27','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57525 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58109,57526,0,53257,TO_TIMESTAMP('2009-09-01 21:44:28','YYYY-MM-DD HH24:MI:SS'),0,'Column in the table',22,'EE07','Link to the database column of the table','Y','Y','Y','N','N','N','N','Column',60,0,TO_TIMESTAMP('2009-09-01 21:44:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:28 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57526 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58106,57527,0,53257,TO_TIMESTAMP('2009-09-01 21:44:28','YYYY-MM-DD HH24:MI:SS'),0,'Virtual Column (r/o)',20,'EE07','You can define virtual columns (not stored in the database). If defined, the Column name is the synonym of the SQL expression defined here. The SQL expression must be valid.<br>
Example: "Updated-Created" would list the age of the entry in days','Y','Y','Y','N','N','N','N','Column SQL',70,0,TO_TIMESTAMP('2009-09-01 21:44:28','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57527 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58107,57528,0,53257,TO_TIMESTAMP('2009-09-01 21:44:29','YYYY-MM-DD HH24:MI:SS'),0,'Name of the column in the database',30,'EE07','The Column Name indicates the name of a column on a table as defined in the database.','Y','Y','Y','N','N','N','Y','DB Column Name',80,0,TO_TIMESTAMP('2009-09-01 21:44:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:29 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57528 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,58103,57529,0,53257,TO_TIMESTAMP('2009-09-01 21:44:29','YYYY-MM-DD HH24:MI:SS'),0,'Dictionary Entity Type; Determines ownership and synchronization',20,'EE07','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','Y','Y','N','N','N','N','Entity Type',90,0,TO_TIMESTAMP('2009-09-01 21:44:29','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:30 PM ECT
-- View & Smat Browse
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57529 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Sep 1, 2009 9:44:30 PM ECT
-- View & Smat Browse
UPDATE AD_Field SET AD_Column_ID=58086, AD_FieldGroup_ID=NULL, AD_Reference_ID=NULL, AD_Reference_Value_ID=NULL, AD_Tab_ID=53256, AD_Val_Rule_ID=NULL, Description='The View Definition allow defined the tables for a view.', DisplayLength=22, DisplayLogic=NULL, EntityType='EE07', Help=NULL, Included_Tab_ID=53257, InfoFactoryClass=NULL, IsActive='Y', IsCentrallyMaintained='Y', IsDisplayed='Y', IsFieldOnly='N', IsHeading='N', IsReadOnly='N', IsSameLine='N', Name='View Definition', SeqNo=90, SortNo=0,Updated=TO_TIMESTAMP('2009-09-01 21:44:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57515
;

-- Sep 1, 2009 9:44:30 PM ECT
-- View & Smat Browse
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=57515
;

-- Sep 1, 2009 9:44:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53227,0,53088,'W',TO_TIMESTAMP('2009-09-01 21:44:30','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Smart Browse.','EE07','Y','N','N','N','Smart Browse',TO_TIMESTAMP('2009-09-01 21:44:30','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:31 PM ECT
-- View & Smat Browse
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53227 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 1, 2009 9:44:31 PM ECT
-- View & Smat Browse
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 153,9, 10, 53227)
;

-- Sep 1, 2009 9:44:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Menu (AD_Client_ID,AD_Menu_ID,AD_Org_ID,AD_Window_ID,"action",Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53228,0,53089,'W',TO_TIMESTAMP('2009-09-01 21:44:31','YYYY-MM-DD HH24:MI:SS'),0,'Maintain Report Views.','EE07','Y','N','N','N','View',TO_TIMESTAMP('2009-09-01 21:44:31','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Sep 1, 2009 9:44:32 PM ECT
-- View & Smat Browse
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53228 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Sep 1, 2009 9:44:32 PM ECT
-- View & Smat Browse
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 153,8, 10, 53228)
;

