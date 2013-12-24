-- Dec 14, 2010 9:22:48 PM CST
-- Cost Engine
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60306,1394,0,12,322,'CurrentCostPrice',TO_TIMESTAMP('2010-12-14 21:22:46','YYYY-MM-DD HH24:MI:SS'),100,'The currently used cost price','D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Current Cost Price',0,TO_TIMESTAMP('2010-12-14 21:22:46','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 14, 2010 9:22:49 PM CST
-- Cost Engine
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60306 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 14, 2010 9:23:07 PM CST
-- Cost Engine
ALTER TABLE M_InventoryLine ADD COLUMN CurrentCostPrice NUMERIC DEFAULT NULL 
;

