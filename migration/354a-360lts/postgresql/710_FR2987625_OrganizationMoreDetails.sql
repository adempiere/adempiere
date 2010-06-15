-- Apr 15, 2010 11:59:46 AM CEST
-- Add more details to Organization
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59142,505,0,10,228,'Phone',TO_TIMESTAMP('2010-04-15 11:59:45','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a telephone number','D',40,'The Phone field identifies a telephone number','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Phone',0,TO_TIMESTAMP('2010-04-15 11:59:45','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 15, 2010 11:59:46 AM CEST
-- Add more details to Organization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59142 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 15, 2010 12:00:04 PM CEST
-- Add more details to Organization
ALTER TABLE AD_OrgInfo ADD COLUMN Phone VARCHAR(40) DEFAULT NULL 
;

-- Apr 15, 2010 12:00:51 PM CEST
-- Add more details to Organization
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59143,506,0,10,228,'Phone2',TO_TIMESTAMP('2010-04-15 12:00:50','YYYY-MM-DD HH24:MI:SS'),100,'Identifies an alternate telephone number.','D',40,'The 2nd Phone field identifies an alternate telephone number.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','2nd Phone',0,TO_TIMESTAMP('2010-04-15 12:00:50','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 15, 2010 12:00:51 PM CEST
-- Add more details to Organization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59143 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 15, 2010 12:00:54 PM CEST
-- Add more details to Organization
ALTER TABLE AD_OrgInfo ADD COLUMN Phone2 VARCHAR(40) DEFAULT NULL 
;

-- Apr 15, 2010 12:01:36 PM CEST
-- Add more details to Organization
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59144,301,0,10,228,'Fax',TO_TIMESTAMP('2010-04-15 12:01:31','YYYY-MM-DD HH24:MI:SS'),100,'Facsimile number','D',40,'The Fax identifies a facsimile number for this Business Partner or  Location','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Fax',0,TO_TIMESTAMP('2010-04-15 12:01:31','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 15, 2010 12:01:36 PM CEST
-- Add more details to Organization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59144 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 15, 2010 12:01:38 PM CEST
-- Add more details to Organization
ALTER TABLE AD_OrgInfo ADD COLUMN Fax VARCHAR(40) DEFAULT NULL 
;

-- Apr 15, 2010 12:02:34 PM CEST
-- Add more details to Organization
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59145,881,0,10,228,'EMail',TO_TIMESTAMP('2010-04-15 12:02:27','YYYY-MM-DD HH24:MI:SS'),100,'Electronic Mail Address','D',60,'The Email Address is the Electronic Mail ID for this User and should be fully qualified (e.g. joe.smith@company.com). The Email Address is used to access the self service application functionality from the web.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','EMail Address',0,TO_TIMESTAMP('2010-04-15 12:02:27','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Apr 15, 2010 12:02:34 PM CEST
-- Add more details to Organization
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59145 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Apr 15, 2010 12:02:36 PM CEST
-- Add more details to Organization
ALTER TABLE AD_OrgInfo ADD COLUMN EMail VARCHAR(60) DEFAULT NULL 
;

-- Apr 15, 2010 12:03:17 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59143,58852,0,170,TO_TIMESTAMP('2010-04-15 12:03:16','YYYY-MM-DD HH24:MI:SS'),100,'Identifies an alternate telephone number.',40,'D','The 2nd Phone field identifies an alternate telephone number.','Y','Y','Y','N','N','N','N','N','2nd Phone',TO_TIMESTAMP('2010-04-15 12:03:16','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 15, 2010 12:03:17 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58852 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 15, 2010 12:03:18 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59145,58853,0,170,TO_TIMESTAMP('2010-04-15 12:03:17','YYYY-MM-DD HH24:MI:SS'),100,'Electronic Mail Address',60,'D','The Email Address is the Electronic Mail ID for this User and should be fully qualified (e.g. joe.smith@company.com). The Email Address is used to access the self service application functionality from the web.','Y','Y','Y','N','N','N','N','N','EMail Address',TO_TIMESTAMP('2010-04-15 12:03:17','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 15, 2010 12:03:18 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58853 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 15, 2010 12:03:19 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59144,58854,0,170,TO_TIMESTAMP('2010-04-15 12:03:18','YYYY-MM-DD HH24:MI:SS'),100,'Facsimile number',40,'D','The Fax identifies a facsimile number for this Business Partner or  Location','Y','Y','Y','N','N','N','N','N','Fax',TO_TIMESTAMP('2010-04-15 12:03:18','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 15, 2010 12:03:19 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58854 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 15, 2010 12:03:20 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59142,58855,0,170,TO_TIMESTAMP('2010-04-15 12:03:19','YYYY-MM-DD HH24:MI:SS'),100,'Identifies a telephone number',40,'D','The Phone field identifies a telephone number','Y','Y','Y','N','N','N','N','N','Phone',TO_TIMESTAMP('2010-04-15 12:03:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Apr 15, 2010 12:03:20 PM CEST
-- Add more details to Organization
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=58855 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Apr 15, 2010 12:04:01 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58855
;

-- Apr 15, 2010 12:04:01 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58852
;

-- Apr 15, 2010 12:04:01 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=58854
;

-- Apr 15, 2010 12:04:01 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=58853
;

-- Apr 15, 2010 12:04:33 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=130,IsDisplayed='Y' WHERE AD_Field_ID=58855
;

-- Apr 15, 2010 12:04:33 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=140,IsDisplayed='Y' WHERE AD_Field_ID=58852
;

-- Apr 15, 2010 12:04:33 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=150,IsDisplayed='Y' WHERE AD_Field_ID=58854
;

-- Apr 15, 2010 12:04:33 PM CEST
-- Add more details to Organization
UPDATE AD_Field SET SeqNo=160,IsDisplayed='Y' WHERE AD_Field_ID=58853
;

-- Apr 15, 2010 12:04:33 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=170,IsDisplayed='Y' WHERE AD_Field_ID=57532
;

-- Apr 15, 2010 12:05:17 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-04-15 12:05:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=1315
;

-- Apr 15, 2010 12:06:46 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-04-15 12:06:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=55416
;

-- Apr 15, 2010 12:07:13 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=8729
;

-- Apr 15, 2010 12:07:13 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=9245
;

-- Apr 15, 2010 12:07:13 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=55416
;

-- Apr 15, 2010 12:07:13 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=1314
;

-- Apr 15, 2010 12:07:13 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=1315
;

-- Apr 15, 2010 12:07:25 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-04-15 12:07:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=8729
;

-- Apr 15, 2010 12:07:59 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET DisplayLength=20,Updated=TO_TIMESTAMP('2010-04-15 12:07:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58855
;

-- Apr 15, 2010 12:08:03 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET DisplayLength=20, IsSameLine='Y',Updated=TO_TIMESTAMP('2010-04-15 12:08:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58852
;

-- Apr 15, 2010 12:08:10 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET DisplayLength=20,Updated=TO_TIMESTAMP('2010-04-15 12:08:10','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58854
;

-- Apr 15, 2010 12:08:17 PM CEST
--FR [2987625] - Add more details to Organization
--https://sourceforge.net/tracker/?func=detail&aid=2987625&group_id=176962&atid=879335
UPDATE AD_Field SET DisplayLength=20, IsSameLine='Y',Updated=TO_TIMESTAMP('2010-04-15 12:08:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=58853
;

