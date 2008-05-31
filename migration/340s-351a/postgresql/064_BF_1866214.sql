-- Jan 7, 2008 7:03:41 PM CST
-- Void Journal Cash
INSERT INTO AD_Column (Name,IsMandatory,IsTranslated,Description,IsIdentifier,SeqNo,Help,Version,IsActive,AD_Table_ID,AD_Column_ID,ColumnName,AD_Client_ID,AD_Org_ID,FieldLength,IsParent,Created,IsSyncDatabase,AD_Reference_ID,CreatedBy,Updated,AD_Element_ID,IsUpdateable,IsKey,IsSelectionColumn,UpdatedBy,IsAlwaysUpdateable,IsEncrypted,EntityType) VALUES ('Payment','N','N','Payment identifier','N',0,'The Payment is a unique identifier of this payment.',0,'Y',410,54090,'C_Payment_ID',0,0,10,'N',TO_TIMESTAMP('2008-01-07 19:03:34','YYYY-MM-DD HH24:MI:SS'),'N',19,0,TO_TIMESTAMP('2008-01-07 19:03:34','YYYY-MM-DD HH24:MI:SS'),1384,'Y','N','N',0,'N','N','D')
;

-- Jan 7, 2008 7:03:42 PM CST
-- Void Journal Cash
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=54090 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 7, 2008 7:04:25 PM CST
-- Void Journal Cash
ALTER TABLE C_CashLine ADD COLUMN C_Payment_ID NUMERIC(10)
;

