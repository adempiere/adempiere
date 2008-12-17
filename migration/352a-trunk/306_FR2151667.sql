SET DEFINE OFF
SET SQLBLANKLINES ON
-- 07.10.2008 17:27:16 EEST
-- -
UPDATE AD_Column SET AD_Element_ID=53245, ColumnName='PP_Product_BOM_ID', Description='BOM & Formula', EntityType='EE01', FieldLength=10, Help=NULL, Name='BOM & Formula',Updated=TO_DATE('2008-10-07 17:27:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=13977
;

-- 07.10.2008 17:27:16 EEST
-- -
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=13977
;

-- 07.10.2008 17:27:16 EEST
-- -
UPDATE AD_Field SET Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Column_ID=13977 AND IsCentrallyMaintained='Y'
;

-- 07.10.2008 17:27:23 EEST
-- -
ALTER TABLE R_Group ADD PP_Product_BOM_ID NUMBER(10)
;

alter table R_Group add constraint PPProductBOM_RGroup foreign key (PP_Product_BOM_ID) references PP_Product_BOM (PP_Product_BOM_ID) validate
;

-- 07.10.2008 17:42:36 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Tab SET IsActive='N',Updated=TO_DATE('2008-10-07 17:42:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=734
;

-- 07.10.2008 17:44:51 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Tab (AD_Client_ID,AD_Column_ID,AD_Org_ID,AD_Tab_ID,AD_Table_ID,AD_Window_ID,Created,CreatedBy,Description,EntityType,HasTree,ImportFields,IsActive,IsAdvancedTab,IsInfoTab,IsInsertRecord,IsReadOnly,IsSingleRow,IsSortTab,IsTranslationTab,Name,Processing,SeqNo,TabLevel,Updated,UpdatedBy) VALUES (0,53332,0,53179,53018,355,TO_DATE('2008-10-07 17:44:50','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula','EE01','N','N','Y','N','N','Y','N','N','N','N','BOM & Formula','N',30,1,TO_DATE('2008-10-07 17:44:50','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:44:51 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Tab_Trl (AD_Language,AD_Tab_ID, CommitWarning,Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Tab_ID, t.CommitWarning,t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Tab t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Tab_ID=53179 AND EXISTS (SELECT * FROM AD_Tab_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Tab_ID!=t.AD_Tab_ID)
;

-- 07.10.2008 17:47:53 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53330,56379,0,53179,TO_DATE('2008-10-07 17:47:52','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'EE01','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports. 
There are two reasons for de-activating and not deleting records: 
(1) The system requires the record for audit purposes. 
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_DATE('2008-10-07 17:47:52','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:53 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56379 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:54 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53340,56380,0,53179,TO_DATE('2008-10-07 17:47:53','YYYY-MM-DD HH24:MI:SS'),0,'Product Attribute Set Instance',22,'EE01','The values of the actual Product Attribute Instances.  The product level attributes are defined on Product level.','Y','Y','Y','N','N','N','N','N','Attribute Set Instance',TO_DATE('2008-10-07 17:47:53','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:54 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56380 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:54 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53334,56381,0,53179,TO_DATE('2008-10-07 17:47:54','YYYY-MM-DD HH24:MI:SS'),0,'BOM & Formula',22,'EE01','Y','Y','N','N','N','N','N','N','BOM & Formula',TO_DATE('2008-10-07 17:47:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:54 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56381 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:55 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53342,56382,0,53179,TO_DATE('2008-10-07 17:47:54','YYYY-MM-DD HH24:MI:SS'),0,'Type of BOM',1,'EE01','The type of Bills of Materials determines the state','Y','Y','Y','N','N','N','N','N','BOM Type',TO_DATE('2008-10-07 17:47:54','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:55 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56382 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:55 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53343,56383,0,53179,TO_DATE('2008-10-07 17:47:55','YYYY-MM-DD HH24:MI:SS'),0,'The use of the Bill of Material',1,'EE01','By default the Master BOM is used, if the alternatives are not defined','Y','Y','Y','N','N','N','N','N','BOM Use',TO_DATE('2008-10-07 17:47:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:55 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56383 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:56 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53332,56384,0,53179,TO_DATE('2008-10-07 17:47:55','YYYY-MM-DD HH24:MI:SS'),0,'Bill of Materials (Engineering) Change Notice (Version)',10,'EE01','Y','Y','Y','N','N','N','N','N','Change Notice',TO_DATE('2008-10-07 17:47:55','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:56 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56384 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:57 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53331,56385,0,53179,TO_DATE('2008-10-07 17:47:56','YYYY-MM-DD HH24:MI:SS'),0,'Client/Tenant for this installation.',22,'EE01','A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.','Y','Y','Y','N','N','N','N','N','Client',TO_DATE('2008-10-07 17:47:56','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:57 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56385 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:57 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53329,56386,0,53179,TO_DATE('2008-10-07 17:47:57','YYYY-MM-DD HH24:MI:SS'),0,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_DATE('2008-10-07 17:47:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:57 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56386 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:58 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53326,56387,0,53179,TO_DATE('2008-10-07 17:47:57','YYYY-MM-DD HH24:MI:SS'),0,'Copy From Record',1,'EE01','Copy From Record','Y','Y','Y','N','N','N','N','N','Copy From',TO_DATE('2008-10-07 17:47:57','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:58 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56387 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:58 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53325,56388,0,53179,TO_DATE('2008-10-07 17:47:58','YYYY-MM-DD HH24:MI:SS'),0,'Optional short description of the record',255,'EE01','A description is limited to 255 characters.','Y','Y','Y','N','N','N','N','N','Description',TO_DATE('2008-10-07 17:47:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:58 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56388 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:59 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53323,56389,0,53179,TO_DATE('2008-10-07 17:47:58','YYYY-MM-DD HH24:MI:SS'),0,'Document sequence number of the document',22,'EE01','The document number is usually automatically generated by the system and determined by the document type of the document. If the document is not saved, the preliminary number is displayed in "<>".

If the document type of your document has no automatic document sequence defined, the field is empty if you create a new document. This is for documents which usually have an external number (like vendor invoice).  If you leave the field empty, the system will generate a document number for you. The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Document No',TO_DATE('2008-10-07 17:47:58','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:59 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56389 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:47:59 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53322,56390,0,53179,TO_DATE('2008-10-07 17:47:59','YYYY-MM-DD HH24:MI:SS'),0,'Alphanumeric identifier of the entity',60,'EE01','The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.','Y','Y','Y','N','N','N','N','N','Name',TO_DATE('2008-10-07 17:47:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:47:59 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56390 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:02 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53341,56391,0,53179,TO_DATE('2008-10-07 17:47:59','YYYY-MM-DD HH24:MI:SS'),0,'Organizational entity within client',22,'EE01','An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.','Y','Y','Y','N','N','N','N','N','Organization',TO_DATE('2008-10-07 17:47:59','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:02 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56391 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53335,56392,0,53179,TO_DATE('2008-10-07 17:48:02','YYYY-MM-DD HH24:MI:SS'),0,1,'EE01','Y','Y','Y','N','N','N','N','N','Process Now',TO_DATE('2008-10-07 17:48:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56392 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53333,56393,0,53179,TO_DATE('2008-10-07 17:48:03','YYYY-MM-DD HH24:MI:SS'),0,'Product, Service, Item',22,'EE01','Identifies an item which is either purchased or sold in this organization.','Y','Y','Y','N','N','N','N','N','Product',TO_DATE('2008-10-07 17:48:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56393 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:04 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53324,56394,0,53179,TO_DATE('2008-10-07 17:48:03','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Revision',TO_DATE('2008-10-07 17:48:03','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:04 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56394 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:04 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53321,56395,0,53179,TO_DATE('2008-10-07 17:48:04','YYYY-MM-DD HH24:MI:SS'),0,'Search key for the record in the format required - must be unique',80,'EE01','A search key allows you a fast method of finding a particular record.
If you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).','Y','Y','Y','N','N','N','N','N','Search Key',TO_DATE('2008-10-07 17:48:04','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:04 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56395 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:06 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53344,56396,0,53179,TO_DATE('2008-10-07 17:48:05','YYYY-MM-DD HH24:MI:SS'),0,'Unit of Measure',22,'EE01','The UOM defines a unique non monetary Unit of Measure','Y','Y','Y','N','N','N','N','N','UOM',TO_DATE('2008-10-07 17:48:05','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:06 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56396 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53338,56397,0,53179,TO_DATE('2008-10-07 17:48:06','YYYY-MM-DD HH24:MI:SS'),0,'Valid from including this date (first day)',7,'EE01','The Valid From date indicates the first day of a date range','Y','Y','Y','N','N','N','N','N','Valid from',TO_DATE('2008-10-07 17:48:06','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56397 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:48:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53339,56398,0,53179,TO_DATE('2008-10-07 17:48:07','YYYY-MM-DD HH24:MI:SS'),0,'Valid to including this date (last day)',7,'EE01','The Valid To date indicates the last day of a date range','Y','Y','Y','N','N','N','N','N','Valid to',TO_DATE('2008-10-07 17:48:07','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 07.10.2008 17:48:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56398 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 07.10.2008 17:50:00 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Tab SET IsActive='Y',Updated=TO_DATE('2008-10-07 17:50:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=734
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56395
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56396
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56389
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56394
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=60,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56388
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56387
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=70,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56386
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=1, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=80,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56379
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='Y', IsSameLine='N', SeqNo=10,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56385
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='Y', IsSameLine='N', SeqNo=30,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56384
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='Y', IsSameLine='N', SeqNo=40,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56393
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=23, IsDisplayed='N', IsReadOnly='N', IsSameLine='N', SeqNo=0,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56392
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56397
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56398
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56380
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='Y', IsSameLine='Y', SeqNo=20,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56391
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=90,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56382
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=14, IsDisplayed='Y', IsReadOnly='N', IsSameLine='Y', SeqNo=100,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56383
;

-- 07.10.2008 17:52:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET DisplayLength=60, IsDisplayed='Y', IsReadOnly='N', IsSameLine='N', SeqNo=50,Updated=TO_DATE('2008-10-07 17:52:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56390
;

-- 07.10.2008 17:55:04 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Tab SET IsInsertRecord='N', IsReadOnly='Y',Updated=TO_DATE('2008-10-07 17:55:04','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53179
;

-- 07.10.2008 17:55:13 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Tab SET IsActive='N',Updated=TO_DATE('2008-10-07 17:55:13','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=734
;

-- 07.10.2008 18:04:58 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Tab SET SeqNo=20,Updated=TO_DATE('2008-10-07 18:04:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Tab_ID=53179
;

-- 07.10.2008 18:10:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Column SET AD_Element_ID=53245, AD_Reference_ID=30, ColumnName='PP_Product_BOM_ID', Description='BOM & Formula', EntityType='EE01', Help=NULL, IsUpdateable='N', Name='BOM & Formula',Updated=TO_DATE('2008-10-07 18:10:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=13945
;

-- 07.10.2008 18:10:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=13945
;

-- 07.10.2008 18:10:03 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
UPDATE AD_Field SET Name='BOM & Formula', Description='BOM & Formula', Help=NULL WHERE AD_Column_ID=13945 AND IsCentrallyMaintained='Y'
;

-- 07.10.2008 18:10:07 EEST
-- FR [ 2151667 ] BOM Change Request is not supporting the new BOM
ALTER TABLE M_ChangeRequest ADD PP_Product_BOM_ID NUMBER(10)
;
alter table M_changerequest drop column M_BOM_ID;

