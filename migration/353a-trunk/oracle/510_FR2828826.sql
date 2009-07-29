-- Jul 29, 2009 3:33:32 PM MYT
-- FR[2828826] Add optional marketing campaign to promotion
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,57951,550,0,19,53178,'C_Campaign_ID',TO_DATE('2009-07-29 15:33:30','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign','D',22,'The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Campaign',0,TO_DATE('2009-07-29 15:33:30','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 29, 2009 3:33:32 PM MYT
-- FR[2828826] Add optional marketing campaign to promotion
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=57951 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jul 29, 2009 3:33:38 PM MYT
-- FR[2828826] Add optional marketing campaign to promotion
ALTER TABLE M_Promotion ADD C_Campaign_ID NUMBER(10) DEFAULT NULL 
;

-- Jul 29, 2009 3:35:03 PM MYT
-- FR[2828826] Add optional marketing campaign to promotion
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,57951,57377,0,53203,TO_DATE('2009-07-29 15:34:56','YYYY-MM-DD HH24:MI:SS'),100,'Marketing Campaign',14,'D','The Campaign defines a unique marketing program.  Projects can be associated with a pre defined Marketing Campaign.  You can then report based on a specific Campaign.','Y','Y','Y','N','N','N','N','N','Campaign',70,0,TO_DATE('2009-07-29 15:34:56','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 29, 2009 3:35:03 PM MYT
-- FR[2828826] Add optional marketing campaign to promotion
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=57377 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

COMMIT;

