-- Sep 7, 2010 6:51:00 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59788,1476,0,20,808,'IsReversal',TO_TIMESTAMP('2010-09-07 18:50:55','YYYY-MM-DD HH24:MI:SS'),100,'N','This is a reversing transaction','D',1,'The Reversal check box indicates if this is a reversal of a prior transaction.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Reversal',0,TO_TIMESTAMP('2010-09-07 18:50:55','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 7, 2010 6:51:00 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59788 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 7, 2010 6:51:11 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD COLUMN IsReversal CHAR(1) DEFAULT 'N' CHECK (IsReversal IN ('Y','N'))
;

-- Sep 7, 2010 6:53:42 PM CDT
-- Cost Engine
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59788,59632,0,748,TO_TIMESTAMP('2010-09-07 18:53:40','YYYY-MM-DD HH24:MI:SS'),100,'This is a reversing transaction',1,'D','The Reversal check box indicates if this is a reversal of a prior transaction.','Y','Y','Y','N','N','N','N','N','Reversal',TO_TIMESTAMP('2010-09-07 18:53:40','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Sep 7, 2010 6:53:42 PM CDT
-- Cost Engine
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59632 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Sep 7, 2010 7:00:09 PM CDT
-- Cost Engine
UPDATE AD_Field SET IsSameLine='Y',Updated=TO_TIMESTAMP('2010-09-07 19:00:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Field_ID=59632
;

