-- Jul 12, 2010 9:22:15 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54253,0,'RoundingRule',TO_TIMESTAMP('2010-07-12 21:22:13','YYYY-MM-DD HH24:MI:SS'),100,'Determines how and if an amount is rounded','D','Y','Rounding Rule','Rounding Rule',TO_TIMESTAMP('2010-07-12 21:22:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 12, 2010 9:22:15 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54253 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jul 12, 2010 9:23:20 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54254,0,'RoundingCharge',TO_TIMESTAMP('2010-07-12 21:23:19','YYYY-MM-DD HH24:MI:SS'),100,'This charge specifies how rounding is accounted.','D','Y','Rounding Charge','Rounding Charge',TO_TIMESTAMP('2010-07-12 21:23:19','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 12, 2010 9:23:20 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54254 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Jul 12, 2010 9:25:29 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59629,54253,0,17,155,255,'RoundingRule',TO_TIMESTAMP('2010-07-12 21:25:29','YYYY-MM-DD HH24:MI:SS'),100,'Determines how and if an amount is rounded','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Rounding Rule',0,TO_TIMESTAMP('2010-07-12 21:25:29','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 12, 2010 9:25:29 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59629 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 12, 2010 9:26:42 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Reference_Value_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,MandatoryLogic,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,59630,54254,0,30,200,255,'RoundingCharge',TO_TIMESTAMP('2010-07-12 21:26:42','YYYY-MM-DD HH24:MI:SS'),100,'This charge specifies how rounding is accounted.','D',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','@RoundingRule@!''''','Rounding Charge',0,TO_TIMESTAMP('2010-07-12 21:26:42','YYYY-MM-DD HH24:MI:SS'),100,1.000000000000)
;

-- Jul 12, 2010 9:26:42 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=59630 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 12, 2010 9:27:49 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59629,59536,0,191,TO_TIMESTAMP('2010-07-12 21:27:49','YYYY-MM-DD HH24:MI:SS'),100,'Determines how and if an amount is rounded',0,'D','Y','Y','Y','N','N','N','N','N','Rounding Rule',120,0,TO_TIMESTAMP('2010-07-12 21:27:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 12, 2010 9:27:49 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59536 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 12, 2010 9:28:27 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,DisplayLogic,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,59630,59537,0,191,TO_TIMESTAMP('2010-07-12 21:28:26','YYYY-MM-DD HH24:MI:SS'),100,'This charge specifies how rounding is accounted.',0,'@RoundingRule@!''''','D','Y','Y','Y','N','N','N','N','Y','Rounding Charge',130,0,TO_TIMESTAMP('2010-07-12 21:28:26','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 12, 2010 9:28:27 PM CEST
-- FR 3028588 - Document rounding
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=59537 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 12, 2010 9:33:49 PM CEST
-- FR 3028588 - Document rounding
ALTER TABLE M_PriceList ADD COLUMN RoundingCharge VARCHAR(10) DEFAULT NULL 
;

-- Jul 12, 2010 9:33:58 PM CEST
-- FR 3028588 - Document rounding
ALTER TABLE M_PriceList ADD COLUMN RoundingRule CHAR(1) DEFAULT NULL 
;

