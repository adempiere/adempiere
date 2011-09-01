-- Dec 7, 2010 11:24:40 PM COT
-- FR3132033-Make payment export class configurable per bank
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,54389,0,'PaymentExportClass',TO_DATE('2010-12-07 23:24:39','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Payment Export Class','Payment Export Class',TO_DATE('2010-12-07 23:24:39','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 7, 2010 11:24:40 PM COT
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=54389 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Dec 7, 2010 11:24:56 PM COT
UPDATE AD_Element_Trl SET IsTranslated='Y',Name='Clase Exporta Pagos',PrintName='Clase Exporta Pagos',Updated=TO_DATE('2010-12-07 23:24:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=54389 AND AD_Language LIKE 'es_%'
;

-- Dec 7, 2010 11:27:52 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60133,54389,0,10,297,'PaymentExportClass',TO_DATE('2010-12-07 23:27:51','YYYY-MM-DD HH24:MI:SS'),100,'D',60,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Payment Export Class',0,TO_DATE('2010-12-07 23:27:51','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 7, 2010 11:27:52 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60133 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 7, 2010 11:27:55 PM COT
ALTER TABLE C_BankAccount ADD PaymentExportClass NVARCHAR2(60) DEFAULT NULL 
;

-- Dec 7, 2010 11:28:27 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60133,60881,0,228,TO_DATE('2010-12-07 23:28:27','YYYY-MM-DD HH24:MI:SS'),100,60,'U','Y','Y','Y','N','N','N','N','N','Payment Export Class',140,0,TO_DATE('2010-12-07 23:28:27','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 7, 2010 11:28:27 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60881 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

