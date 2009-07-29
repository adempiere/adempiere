-- Jul 29, 2009 5:19:30 PM MYT
-- FR[2828878] Add optional activity filter to Promotion
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57952,1005,0,19,53180,'C_Activity_ID',TO_DATE('2009-07-29 17:19:28','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity','D',22,'Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Activity',0,TO_DATE('2009-07-29 17:19:28','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 29, 2009 5:19:30 PM MYT
-- FR[2828878] Add optional activity filter to Promotion
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57952 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 29, 2009 5:19:35 PM MYT
-- FR[2828878] Add optional activity filter to Promotion
ALTER TABLE M_PromotionPreCondition ADD C_Activity_ID NUMBER(10) DEFAULT NULL 
;

-- Jul 29, 2009 5:20:23 PM MYT
-- FR[2828878] Add optional activity filter to Promotion
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57952,57378,0,53204,TO_DATE('2009-07-29 17:20:22','YYYY-MM-DD HH24:MI:SS'),100,'Business Activity',14,'D','Activities indicate tasks that are performed and used to utilize Activity based Costing','Y','Y','Y','N','N','N','N','N','Activity',150,0,TO_DATE('2009-07-29 17:20:22','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 29, 2009 5:20:24 PM MYT
-- FR[2828878] Add optional activity filter to Promotion
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57378 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

COMMIT;

