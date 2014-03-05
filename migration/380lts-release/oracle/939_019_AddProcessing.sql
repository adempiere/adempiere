SET SQLBLANKLINES ON
SET DEFINE OFF

-- Sep 9, 2010 10:40:06 PM CDT
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59789,524,0,20,808,'Processing',TO_DATE('2010-09-09 22:40:03','YYYY-MM-DD HH24:MI:SS'),100,'D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Process Now',0,TO_DATE('2010-09-09 22:40:03','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Sep 9, 2010 10:40:06 PM CDT
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59789 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Sep 9, 2010 10:40:10 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail ADD Processing CHAR(1) DEFAULT NULL 
;

-- Sep 9, 2010 10:50:07 PM CDT
-- Cost Engine
UPDATE AD_Column SET DefaultValue='N',Updated=TO_DATE('2010-09-09 22:50:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=59789
;

-- Sep 9, 2010 10:50:11 PM CDT
-- Cost Engine
ALTER TABLE M_CostDetail MODIFY Processing CHAR(1) DEFAULT 'N'
;

