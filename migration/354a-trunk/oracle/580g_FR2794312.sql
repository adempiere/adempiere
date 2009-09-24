-- 22.05.2009 10:25:55 EEST
-- Location AutoComplete -  http://sourceforge.net/tracker/?func=detail&aid=2794312&group_id=176962&atid=879335
INSERT INTO AD_Element (UpdatedBy,AD_Element_ID,IsActive,CreatedBy,ColumnName,Name,Description,Updated,Created,AD_Client_ID,AD_Org_ID,PrintName,EntityType) VALUES (0,53838,'Y',0,'AllowCitiesOutOfList','AllowCitiesOutOfList','A flag which tells if a country accept or not new cities',TO_DATE('2009-05-22 10:25:53','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-05-22 10:25:53','YYYY-MM-DD HH24:MI:SS'),0,0,'Allow Cities Out Of List','D')
;

-- 22.05.2009 10:25:55 EEST
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Name,Description,Help,PrintName,PO_PrintName,PO_Name,PO_Description,PO_Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Name,t.Description,t.Help,t.PrintName,t.PO_PrintName,t.PO_Name,t.PO_Description,t.PO_Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53838 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 22.05.2009 10:28:40 EEST
INSERT INTO AD_Column (AD_Column_ID,IsParent,AD_Client_ID,AD_Org_ID,IsAutocomplete,AD_Table_ID,FieldLength,Created,CreatedBy,Updated,Version,DefaultValue,IsActive,Description,ColumnName,IsKey,AD_Reference_ID,IsTranslated,IsMandatory,IsIdentifier,SeqNo,IsSelectionColumn,IsSyncDatabase,AD_Element_ID,IsUpdateable,EntityType,Name,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES (57650,'N',0,0,'N',170,1,TO_DATE('2009-05-22 10:28:37','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-05-22 10:28:37','YYYY-MM-DD HH24:MI:SS'),0,'Y','Y','A flag which tells if a country accept or not new cities','AllowCitiesOutOfList','N',20,'N','N','N',0,'N','N',53838,'Y','U','AllowCitiesOutOfList',0,'N','N','Y')
;

-- 22.05.2009 10:28:40 EEST
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57650 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22.05.2009 10:28:41 EEST
INSERT INTO AD_Field (IsFieldOnly,DisplayLength,AD_Field_ID,IsEncrypted,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,UpdatedBy,IsActive,Created,IsSameLine,IsHeading,CreatedBy,AD_Client_ID,Updated,Description,AD_Org_ID,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES ('N',1,57035,'N','AllowCitiesOutOfList',135,57650,'N',0,'Y',TO_DATE('2009-05-22 10:28:40','YYYY-MM-DD HH24:MI:SS'),'N','N',0,0,TO_DATE('2009-05-22 10:28:40','YYYY-MM-DD HH24:MI:SS'),'A flag which tells if a country accept or not new cities',0,'N','Y','U')
;

-- 22.05.2009 10:28:41 EEST
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57035 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 22.05.2009 10:28:42 EEST
ALTER TABLE C_Country ADD AllowCitiesOutOfList CHAR(1) DEFAULT 'Y' CHECK (AllowCitiesOutOfList IN ('Y','N'))
;

-- 22.05.2009 10:29:08 EEST
UPDATE AD_Column SET EntityType='D',Updated=TO_DATE('2009-05-22 10:29:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=57650
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=342
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=343
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=345
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=10893
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=10892
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=10891
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=346
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=347
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=348
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=344
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=11184
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=10895
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=10894
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=5753
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=5752
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=51000
;

-- 22.05.2009 10:30:52 EEST
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=57035
;

-- 22.05.2009 10:32:17 EEST
INSERT INTO AD_Element (UpdatedBy,AD_Element_ID,IsActive,CreatedBy,ColumnName,Name,Updated,Created,AD_Client_ID,AD_Org_ID,PrintName,EntityType) VALUES (0,53839,'Y',0,'CaptureSequence','CaptureSequence',TO_DATE('2009-05-22 10:32:16','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-05-22 10:32:16','YYYY-MM-DD HH24:MI:SS'),0,0,'Capture Sequence','D')
;

-- 22.05.2009 10:32:17 EEST
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Name,Description,Help,PrintName,PO_PrintName,PO_Name,PO_Description,PO_Help, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Name,t.Description,t.Help,t.PrintName,t.PO_PrintName,t.PO_Name,t.PO_Description,t.PO_Help, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53839 AND EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Element_ID!=t.AD_Element_ID)
;

-- 22.05.2009 10:32:49 EEST
INSERT INTO AD_Column (AD_Column_ID,IsParent,AD_Client_ID,AD_Org_ID,IsAutocomplete,AD_Table_ID,FieldLength,Created,CreatedBy,Updated,Version,IsActive,ColumnName,IsKey,AD_Reference_ID,IsTranslated,IsMandatory,IsIdentifier,SeqNo,IsSelectionColumn,IsSyncDatabase,AD_Element_ID,IsUpdateable,EntityType,Name,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,IsAllowLogging) VALUES (57651,'N',0,0,'N',170,20,TO_DATE('2009-05-22 10:32:48','YYYY-MM-DD HH24:MI:SS'),0,TO_DATE('2009-05-22 10:32:48','YYYY-MM-DD HH24:MI:SS'),1,'Y','CaptureSequence','N',10,'N','N','N',0,'N','N',53839,'Y','D','CaptureSequence',0,'N','N','Y')
;

-- 22.05.2009 10:32:49 EEST
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57651 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- 22.05.2009 10:32:50 EEST
INSERT INTO AD_Field (IsFieldOnly,DisplayLength,AD_Field_ID,IsEncrypted,Name,AD_Tab_ID,AD_Column_ID,IsDisplayed,UpdatedBy,IsActive,Created,IsSameLine,IsHeading,CreatedBy,AD_Client_ID,Updated,AD_Org_ID,IsReadOnly,IsCentrallyMaintained,EntityType) VALUES ('N',20,57036,'N','CaptureSequence',135,57651,'N',0,'Y',TO_DATE('2009-05-22 10:32:49','YYYY-MM-DD HH24:MI:SS'),'N','N',0,0,TO_DATE('2009-05-22 10:32:49','YYYY-MM-DD HH24:MI:SS'),0,'N','Y','D')
;

-- 22.05.2009 10:32:50 EEST
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Help,Name,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Help,t.Name,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57036 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

-- 22.05.2009 10:32:51 EEST
ALTER TABLE C_Country ADD CaptureSequence NVARCHAR2(20) DEFAULT NULL 
;

-- 22.05.2009 10:33:44 EEST
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=57036
;

-- 22.05.2009 10:35:10 EEST
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_DATE('2009-05-22 10:35:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57035
;

-- 22.05.2009 10:35:47 EEST
UPDATE AD_Field SET IsSameLine='N',Updated=TO_DATE('2009-05-22 10:35:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57035
;

-- 22.05.2009 10:36:41 EEST
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=57035
;

-- 22.05.2009 10:36:41 EEST
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=51003
;

-- 22.05.2009 10:36:41 EEST
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=51002
;

-- 22.05.2009 10:36:41 EEST
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=51004
;

-- 22.05.2009 10:36:41 EEST
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=51001
;

-- 22.05.2009 10:37:10 EEST
UPDATE AD_Field SET IsSameLine='Y', EntityType='D',Updated=TO_DATE('2009-05-22 10:37:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=57035
;



-- 27.05.2009 11:21:49 EEST
INSERT INTO AD_Message (MsgType,MsgText,CreatedBy,IsActive,Created,Updated,UpdatedBy,AD_Client_ID,AD_Org_ID,EntityType,Value,AD_Message_ID) VALUES ('E','The city does not exist and can not be created from here.',0,'Y',TO_DATE('2009-05-27 11:21:48','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2009-05-27 11:21:48','YYYY-MM-DD HH24:MI:SS'),0,0,0,'D','CityNotFound',53078)
;

-- 27.05.2009 11:21:49 EEST
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53078 AND EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Message_ID!=t.AD_Message_ID)
;

-- 27.05.2009 11:22:10 EEST
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Orasul nu exista si nu paote fi creat de aici.',Updated=TO_DATE('2009-05-27 11:22:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53078 AND AD_Language='ro_RO'
;

-- 27.05.2009 11:22:16 EEST
UPDATE AD_Message_Trl SET MsgText='Orasul nu exista si nu poate fi creat de aici.',Updated=TO_DATE('2009-05-27 11:22:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Message_ID=53078 AND AD_Language='ro_RO'
;


-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=110,IsDisplayed='Y' WHERE AD_Field_ID=57036
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=120,IsDisplayed='Y' WHERE AD_Field_ID=10892
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=10891
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=346
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=347
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=348
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=344
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=180,IsDisplayed='Y' WHERE AD_Field_ID=11184
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=190,IsDisplayed='Y' WHERE AD_Field_ID=10895
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=200,IsDisplayed='Y' WHERE AD_Field_ID=10894
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=210,IsDisplayed='Y' WHERE AD_Field_ID=5753
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=220,IsDisplayed='Y' WHERE AD_Field_ID=5752
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=230,IsDisplayed='Y' WHERE AD_Field_ID=51000
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=240,IsDisplayed='Y' WHERE AD_Field_ID=57035
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=250,IsDisplayed='Y' WHERE AD_Field_ID=51003
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=260,IsDisplayed='Y' WHERE AD_Field_ID=51002
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=270,IsDisplayed='Y' WHERE AD_Field_ID=51004
;

-- Sep 17, 2009 9:35:45 PM COT
UPDATE AD_Field SET SeqNo=280,IsDisplayed='Y' WHERE AD_Field_ID=51001
;

-- Sep 17, 2009 9:37:05 PM COT
UPDATE AD_Element SET Name='Use Postcode Lookup', PrintName='Use Postcode Lookup',Updated=TO_DATE('2009-09-17 21:37:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=51000
;

-- Sep 17, 2009 9:37:05 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=51000
;

-- Sep 17, 2009 9:37:05 PM COT
UPDATE AD_Column SET ColumnName='IsPostcodeLookup', Name='Use Postcode Lookup', Description='Does this country have a post code web service', Help='Enable the IsPostcodeLookup if you wish to configure a post code lookup web service' WHERE AD_Element_ID=51000
;

-- Sep 17, 2009 9:37:05 PM COT
UPDATE AD_Process_Para SET ColumnName='IsPostcodeLookup', Name='Use Postcode Lookup', Description='Does this country have a post code web service', Help='Enable the IsPostcodeLookup if you wish to configure a post code lookup web service', AD_Element_ID=51000 WHERE UPPER(ColumnName)='ISPOSTCODELOOKUP' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:37:05 PM COT
UPDATE AD_Process_Para SET ColumnName='IsPostcodeLookup', Name='Use Postcode Lookup', Description='Does this country have a post code web service', Help='Enable the IsPostcodeLookup if you wish to configure a post code lookup web service' WHERE AD_Element_ID=51000 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:05 PM COT
UPDATE AD_Field SET Name='Use Postcode Lookup', Description='Does this country have a post code web service', Help='Enable the IsPostcodeLookup if you wish to configure a post code lookup web service' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=51000) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:06 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Use Postcode Lookup', Name='Use Postcode Lookup' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=51000)
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_Element SET Name='Lookup ClassName', PrintName='Lookup ClassName',Updated=TO_DATE('2009-09-17 21:37:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=51001
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=51001
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_Column SET ColumnName='LookupClassName', Name='Lookup ClassName', Description='The class name of the postcode lookup plugin', Help='Enter the class name of the post code lookup plugin for your postcode web service provider' WHERE AD_Element_ID=51001
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupClassName', Name='Lookup ClassName', Description='The class name of the postcode lookup plugin', Help='Enter the class name of the post code lookup plugin for your postcode web service provider', AD_Element_ID=51001 WHERE UPPER(ColumnName)='LOOKUPCLASSNAME' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupClassName', Name='Lookup ClassName', Description='The class name of the postcode lookup plugin', Help='Enter the class name of the post code lookup plugin for your postcode web service provider' WHERE AD_Element_ID=51001 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_Field SET Name='Lookup ClassName', Description='The class name of the postcode lookup plugin', Help='Enter the class name of the post code lookup plugin for your postcode web service provider' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=51001) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:19 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Lookup ClassName', Name='Lookup ClassName' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=51001)
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_Element SET Name='Lookup Client ID', PrintName='Lookup Client ID',Updated=TO_DATE('2009-09-17 21:37:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=51002
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=51002
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_Column SET ColumnName='LookupClientID', Name='Lookup Client ID', Description='The ClientID or Login submitted to the Lookup URL', Help='Enter the ClientID or Login for your account provided by the post code web service provider' WHERE AD_Element_ID=51002
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupClientID', Name='Lookup Client ID', Description='The ClientID or Login submitted to the Lookup URL', Help='Enter the ClientID or Login for your account provided by the post code web service provider', AD_Element_ID=51002 WHERE UPPER(ColumnName)='LOOKUPCLIENTID' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupClientID', Name='Lookup Client ID', Description='The ClientID or Login submitted to the Lookup URL', Help='Enter the ClientID or Login for your account provided by the post code web service provider' WHERE AD_Element_ID=51002 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_Field SET Name='Lookup Client ID', Description='The ClientID or Login submitted to the Lookup URL', Help='Enter the ClientID or Login for your account provided by the post code web service provider' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=51002) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:31 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Lookup Client ID', Name='Lookup Client ID' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=51002)
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_Element SET Name='Lookup Password', PrintName='Lookup Password',Updated=TO_DATE('2009-09-17 21:37:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=51004
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=51004
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_Column SET ColumnName='LookupPassword', Name='Lookup Password', Description='The password submitted to the Lookup URL', Help='Enter the password for your account provided by the post code web service provider' WHERE AD_Element_ID=51004
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupPassword', Name='Lookup Password', Description='The password submitted to the Lookup URL', Help='Enter the password for your account provided by the post code web service provider', AD_Element_ID=51004 WHERE UPPER(ColumnName)='LOOKUPPASSWORD' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupPassword', Name='Lookup Password', Description='The password submitted to the Lookup URL', Help='Enter the password for your account provided by the post code web service provider' WHERE AD_Element_ID=51004 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_Field SET Name='Lookup Password', Description='The password submitted to the Lookup URL', Help='Enter the password for your account provided by the post code web service provider' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=51004) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:38 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Lookup Password', Name='Lookup Password' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=51004)
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_Element SET Name='Lookup URL', PrintName='Lookup URL',Updated=TO_DATE('2009-09-17 21:37:50','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=51003
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=51003
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_Column SET ColumnName='LookupUrl', Name='Lookup URL', Description='The URL of the web service that the plugin connects to in order to retrieve postcode data', Help='Enter the URL of the web service that the plugin connects to in order to retrieve postcode data' WHERE AD_Element_ID=51003
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupUrl', Name='Lookup URL', Description='The URL of the web service that the plugin connects to in order to retrieve postcode data', Help='Enter the URL of the web service that the plugin connects to in order to retrieve postcode data', AD_Element_ID=51003 WHERE UPPER(ColumnName)='LOOKUPURL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_Process_Para SET ColumnName='LookupUrl', Name='Lookup URL', Description='The URL of the web service that the plugin connects to in order to retrieve postcode data', Help='Enter the URL of the web service that the plugin connects to in order to retrieve postcode data' WHERE AD_Element_ID=51003 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_Field SET Name='Lookup URL', Description='The URL of the web service that the plugin connects to in order to retrieve postcode data', Help='Enter the URL of the web service that the plugin connects to in order to retrieve postcode data' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=51003) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:37:50 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Lookup URL', Name='Lookup URL' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=51003)
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_Element SET Description='A flag which tells if a country accept or not new cities when capturing locations', Name='Allow Cities out of List', PrintName='Allow Cities out of List',Updated=TO_DATE('2009-09-17 21:38:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53838
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53838
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_Column SET ColumnName='AllowCitiesOutOfList', Name='Allow Cities out of List', Description='A flag which tells if a country accept or not new cities when capturing locations', Help=NULL WHERE AD_Element_ID=53838
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_Process_Para SET ColumnName='AllowCitiesOutOfList', Name='Allow Cities out of List', Description='A flag which tells if a country accept or not new cities when capturing locations', Help=NULL, AD_Element_ID=53838 WHERE UPPER(ColumnName)='ALLOWCITIESOUTOFLIST' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_Process_Para SET ColumnName='AllowCitiesOutOfList', Name='Allow Cities out of List', Description='A flag which tells if a country accept or not new cities when capturing locations', Help=NULL WHERE AD_Element_ID=53838 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_Field SET Name='Allow Cities out of List', Description='A flag which tells if a country accept or not new cities when capturing locations', Help=NULL WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53838) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:38:21 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Allow Cities out of List', Name='Allow Cities out of List' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53838)
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_Element SET Help='The Capture Sequence defines the fields to be used when capturing an address on this country.  The following notations are used: @CO@=Country, @C@=City, @P@=Postal, @A@=PostalAdd, @R@=Region, @A1@=Address 1 to @A4@=Address 4.  Country is always mandatory, add a bang ! to make another field mandatory, for example @C!@ makes city mandatory, @A1!@ makes Address 1 mandatory.', Name='Capture Sequence',Updated=TO_DATE('2009-09-17 21:45:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53839
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53839
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_Column SET ColumnName='CaptureSequence', Name='Capture Sequence', Description=NULL, Help='The Capture Sequence defines the fields to be used when capturing an address on this country.  The following notations are used: @CO@=Country, @C@=City, @P@=Postal, @A@=PostalAdd, @R@=Region, @A1@=Address 1 to @A4@=Address 4.  Country is always mandatory, add a bang ! to make another field mandatory, for example @C!@ makes city mandatory, @A1!@ makes Address 1 mandatory.' WHERE AD_Element_ID=53839
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_Process_Para SET ColumnName='CaptureSequence', Name='Capture Sequence', Description=NULL, Help='The Capture Sequence defines the fields to be used when capturing an address on this country.  The following notations are used: @CO@=Country, @C@=City, @P@=Postal, @A@=PostalAdd, @R@=Region, @A1@=Address 1 to @A4@=Address 4.  Country is always mandatory, add a bang ! to make another field mandatory, for example @C!@ makes city mandatory, @A1!@ makes Address 1 mandatory.', AD_Element_ID=53839 WHERE UPPER(ColumnName)='CAPTURESEQUENCE' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_Process_Para SET ColumnName='CaptureSequence', Name='Capture Sequence', Description=NULL, Help='The Capture Sequence defines the fields to be used when capturing an address on this country.  The following notations are used: @CO@=Country, @C@=City, @P@=Postal, @A@=PostalAdd, @R@=Region, @A1@=Address 1 to @A4@=Address 4.  Country is always mandatory, add a bang ! to make another field mandatory, for example @C!@ makes city mandatory, @A1!@ makes Address 1 mandatory.' WHERE AD_Element_ID=53839 AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_Field SET Name='Capture Sequence', Description=NULL, Help='The Capture Sequence defines the fields to be used when capturing an address on this country.  The following notations are used: @CO@=Country, @C@=City, @P@=Postal, @A@=PostalAdd, @R@=Region, @A1@=Address 1 to @A4@=Address 4.  Country is always mandatory, add a bang ! to make another field mandatory, for example @C!@ makes city mandatory, @A1!@ makes Address 1 mandatory.' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53839) AND IsCentrallyMaintained='Y'
;

-- Sep 17, 2009 9:45:01 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Capture Sequence', Name='Capture Sequence' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53839)
;

-- Sep 17, 2009 9:50:35 PM COT
UPDATE AD_Column SET FieldLength=60,Updated=TO_DATE('2009-09-17 21:50:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57651
;

-- Sep 17, 2009 9:50:41 PM COT
ALTER TABLE C_Country MODIFY CaptureSequence NVARCHAR2(60) DEFAULT NULL 
;

-- Sep 17, 2009 9:51:38 PM COT
UPDATE AD_Field SET DisplayLength=40,Updated=TO_DATE('2009-09-17 21:51:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=57036
;

update c_country set capturesequence = '@A1@ @A2@ @A3@ @A4@ ' || displaysequence || ' @CO@'
;

-- Sep 17, 2009 9:56:09 PM COT
UPDATE C_Country SET CaptureSequence='@CO@ @R!@ @C!@ @A1!@ @A2@ @A3@ @A4@ @P@',Updated=TO_DATE('2009-09-17 21:56:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=156
;

-- Sep 17, 2009 9:57:12 PM COT
UPDATE C_Country SET HasRegion='Y', RegionName='Department',Updated=TO_DATE('2009-09-17 21:57:12','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=156
;

-- Sep 17, 2009 9:57:21 PM COT
UPDATE C_Country SET AllowCitiesOutOfList='Y',Updated=TO_DATE('2009-09-17 21:57:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=156
;

-- Sep 17, 2009 10:00:20 PM COT
UPDATE C_Country_Trl SET IsTranslated='Y',RegionName='Departamento',Updated=TO_DATE('2009-09-17 22:00:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE C_Country_ID=156 AND AD_Language LIKE 'es_%'
;

-- 22-sep-2009 14:02:27 COT
-- FR [2794312] - Location AutoComplete
-- Deprecate form Initial Client Setup
UPDATE AD_Form SET IsActive='N',Updated=TO_DATE('2009-09-22 14:02:27','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Form_ID=102
;

-- 22-sep-2009 14:02:30 COT
UPDATE AD_Menu SET IsActive='N',Updated=TO_DATE('2009-09-22 14:02:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=225
;

-- 22-sep-2009 14:07:20 COT
-- FR [2794312] - Location AutoComplete
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50035,'S',TO_DATE('2009-09-22 14:07:19','YYYY-MM-DD HH24:MI:SS'),100,'Number of rows to show on city autocomplete feature on Location window','D','Y','LOCATION_MAX_CITY_ROWS',TO_DATE('2009-09-22 14:07:19','YYYY-MM-DD HH24:MI:SS'),100,'7')
;

