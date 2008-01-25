-- Jan 24, 2008 5:45:28 PM GMT-03:00
-- BUG 1871567  -  Wrong value in Payment document
INSERT INTO AD_ELEMENT (AD_Org_ID,UpdatedBy,Updated,PrintName,NAME,IsActive,EntityType,CreatedBy,Created,ColumnName,AD_Element_ID,AD_Client_ID) VALUES (0,100,TO_DATE('2008-01-24 17:45:27','YYYY-MM-DD HH24:MI:SS'),'Generated Draft','Generated Draft','Y','D',100,TO_DATE('2008-01-24 17:45:27','YYYY-MM-DD HH24:MI:SS'),'IsGeneratedDraft',53334,0)
;

INSERT INTO AD_ELEMENT_TRL (AD_LANGUAGE,AD_Element_ID, PrintName,PO_PrintName,PO_Name,PO_Help,PO_Description,NAME,Help,Description, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Element_ID, t.PrintName,t.PO_PrintName,t.PO_Name,t.PO_Help,t.PO_Description,t.NAME,t.Help,t.Description, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_ELEMENT t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=53334 AND EXISTS (SELECT * FROM AD_ELEMENT_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Element_ID!=t.AD_Element_ID)
;

INSERT INTO AD_COLUMN (AD_Org_ID,VERSION,UpdatedBy,Updated,SeqNo,NAME,IsUpdateable,IsTranslated,IsSyncDatabase,IsSelectionColumn,IsParent,IsMandatory,IsKey,IsIdentifier,IsEncrypted,IsAlwaysUpdateable,IsActive,FieldLength,EntityType,DefaultValue,CreatedBy,Created,ColumnName,AD_Table_ID,AD_Reference_ID,AD_Element_ID,AD_Column_ID,AD_Client_ID) VALUES (0,0,100,TO_DATE('2008-01-24 17:46:47','YYYY-MM-DD HH24:MI:SS'),0,'Generated Draft','Y','N','N','N','N','Y','N','N','N','N','Y',1,'D','N',100,TO_DATE('2008-01-24 17:46:47','YYYY-MM-DD HH24:MI:SS'),'IsGeneratedDraft',525,20,53334,54258,0)
;

INSERT INTO AD_COLUMN_TRL (AD_LANGUAGE,AD_Column_ID, NAME, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_LANGUAGE,t.AD_Column_ID, t.NAME, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_LANGUAGE l, AD_COLUMN t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54258 AND EXISTS (SELECT * FROM AD_COLUMN_TRL tt WHERE tt.AD_LANGUAGE!=l.AD_LANGUAGE OR tt.AD_Column_ID!=t.AD_Column_ID)
;

ALTER TABLE C_PAYSELECTIONCHECK ADD IsGeneratedDraft CHAR(1) DEFAULT 'N' CHECK (IsGeneratedDraft IN ('Y','N')) NOT NULL
;
