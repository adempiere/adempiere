SET SQLBLANKLINES ON
-- Dec 15, 2007 12:31:13 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,ReadOnlyLogic,SeqNo,Updated,UpdatedBy,Version) VALUES (0,53270,1682,0,18,389,50009,'EntityType',TO_DATE('2007-12-15 12:31:11','YYYY-MM-DD HH24:MI:SS'),100,'U','Dictionary Entity Type; Determines ownership and synchronization','D',40,'The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','N','N','N','N','Y','N','N','N','N','Y','Entity Type','@EntityType@=D',0,TO_DATE('2007-12-15 12:31:11','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Dec 15, 2007 12:31:13 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=53270 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2007 12:31:24 PM COT
-- FR 1800371 System Configurator Enhancements
ALTER TABLE AD_SysConfig ADD EntityType VARCHAR2(40) DEFAULT 'U' NOT NULL
;

-- Dec 15, 2007 12:33:53 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,Help,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,53229,0,'ConfigurationLevel',TO_DATE('2007-12-15 12:33:52','YYYY-MM-DD HH24:MI:SS'),100,'Configuration Level for this parameter','D','Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter','Y','Configuration Level','Configuration Level for this parameter',TO_DATE('2007-12-15 12:33:52','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2007 12:33:53 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53229 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- Dec 15, 2007 12:34:46 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Reference (AD_Client_ID,AD_Org_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,ValidationType) VALUES (0,0,53222,TO_DATE('2007-12-15 12:34:44','YYYY-MM-DD HH24:MI:SS'),100,'Configuration Level','D','Y','AD_SysConfig ConfigurationLevel',TO_DATE('2007-12-15 12:34:44','YYYY-MM-DD HH24:MI:SS'),100,'L')
;

-- Dec 15, 2007 12:34:46 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Reference_Trl (AD_Language,AD_Reference_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Reference_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Reference t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Reference_ID=53222 AND EXISTS (SELECT * FROM AD_Reference_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Reference_ID!=t.AD_Reference_ID)
;

-- Dec 15, 2007 12:35:24 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53228,53222,TO_DATE('2007-12-15 12:35:20','YYYY-MM-DD HH24:MI:SS'),100,'Just allowed system configuration','D','Y','System',TO_DATE('2007-12-15 12:35:20','YYYY-MM-DD HH24:MI:SS'),100,'S')
;

-- Dec 15, 2007 12:35:24 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53228 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 15, 2007 12:35:43 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53229,53222,TO_DATE('2007-12-15 12:35:42','YYYY-MM-DD HH24:MI:SS'),100,'Allowed system and client configuration','D','Y','Client',TO_DATE('2007-12-15 12:35:42','YYYY-MM-DD HH24:MI:SS'),100,'C')
;

-- Dec 15, 2007 12:35:43 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53229 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 15, 2007 12:36:00 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Ref_List (AD_Client_ID,AD_Org_ID,AD_Ref_List_ID,AD_Reference_ID,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,53230,53222,TO_DATE('2007-12-15 12:36:00','YYYY-MM-DD HH24:MI:SS'),100,'Allowed system, client and organization configuration','D','Y','Organization',TO_DATE('2007-12-15 12:36:00','YYYY-MM-DD HH24:MI:SS'),100,'O')
;

-- Dec 15, 2007 12:36:01 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Ref_List_Trl (AD_Language,AD_Ref_List_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Ref_List_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Ref_List t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Ref_List_ID=53230 AND EXISTS (SELECT * FROM AD_Ref_List_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Ref_List_ID!=t.AD_Ref_List_ID)
;

-- Dec 15, 2007 12:36:39 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAlwaysUpdateable,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,53271,53229,0,17,53222,50009,'ConfigurationLevel',TO_DATE('2007-12-15 12:36:38','YYYY-MM-DD HH24:MI:SS'),100,'S','Configuration Level for this parameter','D',1,'Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter','Y','N','N','N','N','N','N','N','N','N','Y','Configuration Level',0,TO_DATE('2007-12-15 12:36:38','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 15, 2007 12:36:39 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=53271 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Dec 15, 2007 12:36:53 PM COT
-- FR 1800371 System Configurator Enhancements
ALTER TABLE AD_SysConfig ADD ConfigurationLevel CHAR(1) DEFAULT 'S'
;

-- Dec 15, 2007 12:41:18 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53271,53285,0,50009,TO_DATE('2007-12-15 12:41:14','YYYY-MM-DD HH24:MI:SS'),100,'Configuration Level for this parameter',1,'D','Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter','Y','Y','Y','N','N','N','N','N','Configuration Level',TO_DATE('2007-12-15 12:41:14','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2007 12:41:18 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=53285 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2007 12:41:25 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53270,53286,0,50009,TO_DATE('2007-12-15 12:41:18','YYYY-MM-DD HH24:MI:SS'),100,'Dictionary Entity Type; Determines ownership and synchronization',40,'D','The Entity Types "Dictionary", "Adempiere" and "Application" might be automatically synchronized and customizations deleted or overwritten.  

For customizations, copy the entity and select "User"!','Y','Y','Y','N','N','N','N','N','Entity Type',TO_DATE('2007-12-15 12:41:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 15, 2007 12:41:26 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=53286 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- Dec 15, 2007 12:41:38 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET SeqNo=30,IsDisplayed='Y' WHERE AD_Field_ID=50164
;

-- Dec 15, 2007 12:41:39 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=50163
;

-- Dec 15, 2007 12:41:39 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=50166
;

-- Dec 15, 2007 12:41:39 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=53286
;

-- Dec 15, 2007 12:41:39 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=53285
;

-- Dec 15, 2007 12:41:39 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=50161
;

-- Dec 15, 2007 12:42:07 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET DisplayLength=20,Updated=TO_DATE('2007-12-15 12:42:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53286
;

-- Dec 15, 2007 12:42:18 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Field SET DisplayLength=20, IsSameLine='Y',Updated=TO_DATE('2007-12-15 12:42:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=53285
;

-- Dec 15, 2007 12:49:08 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50000
;

-- Dec 15, 2007 12:49:16 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50005
;

-- Dec 15, 2007 12:49:26 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50009
;

-- Dec 15, 2007 12:49:33 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:32','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50010
;

-- Dec 15, 2007 12:49:42 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50004
;

-- Dec 15, 2007 12:49:49 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50006
;

-- Dec 15, 2007 12:49:58 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:49:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50001
;

-- Dec 15, 2007 12:50:06 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:50:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50007
;

-- Dec 15, 2007 12:50:14 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:50:14','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50002
;

-- Dec 15, 2007 12:50:29 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:50:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50008
;

-- Dec 15, 2007 12:50:51 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_SysConfig SET EntityType='D',Updated=TO_DATE('2007-12-15 12:50:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50003
;

-- Dec 15, 2007 12:51:28 PM COT
-- FR 1800371 System Configurator Enhancements
UPDATE AD_Tab SET OrderByClause='Name',Updated=TO_DATE('2007-12-15 12:51:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Tab_ID=50009
;

-- Dec 15, 2007 1:24:58 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53009,0,TO_DATE('2007-12-15 13:24:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','This is a system or client parameter, you can''t save it as organization parameter','E',TO_DATE('2007-12-15 13:24:57','YYYY-MM-DD HH24:MI:SS'),100,'Can''t Save Org Level')
;

-- Dec 15, 2007 1:24:58 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53009 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- Dec 15, 2007 1:25:17 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgType,Updated,UpdatedBy,Value) VALUES (0,53010,0,TO_DATE('2007-12-15 13:25:16','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','This is a system parameter, you can''t save it as client parameter','E',TO_DATE('2007-12-15 13:25:16','YYYY-MM-DD HH24:MI:SS'),100,'Can''t Save Client Level')
;

-- Dec 15, 2007 1:25:17 PM COT
-- FR 1800371 System Configurator Enhancements
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53010 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;
