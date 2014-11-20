-- Dec 18, 2012 7:08:09 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Element (AD_Client_ID,AD_Element_ID,AD_Org_ID,ColumnName,Created,CreatedBy,Description,EntityType,IsActive,Name,PrintName,Updated,UpdatedBy) VALUES (0,55778,0,'ToBeInvoiced',TO_TIMESTAMP('2012-12-18 19:08:07','YYYY-MM-DD HH24:MI:SS'),100,'If Shipment line is marked as ToBeInvoiced=''N'' then it is included in the generated Invoice line with price 0.0','D','Y','To be Invoiced','To be Invoiced',TO_TIMESTAMP('2012-12-18 19:08:07','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 18, 2012 7:08:09 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Element_Trl (AD_Language,AD_Element_ID, Description,Help,Name,PO_Description,PO_Help,PO_Name,PO_PrintName,PrintName, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Element_ID, t.Description,t.Help,t.Name,t.PO_Description,t.PO_Help,t.PO_Name,t.PO_PrintName,t.PrintName, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Element t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Element_ID=55778 AND NOT EXISTS (SELECT * FROM AD_Element_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Element_ID=t.AD_Element_ID)
;

-- Dec 18, 2012 7:08:58 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,DefaultValue,Description,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,64663,55778,0,20,320,'ToBeInvoiced',TO_TIMESTAMP('2012-12-18 19:08:57','YYYY-MM-DD HH24:MI:SS'),100,'Y','If Shipment line is marked as ToBeInvoiced=''N'' then it is included in the generated Invoice line with price 0.0','D',1,'Y','Y','N','N','N','N','N','N','N','N','N','N','Y','To be Invoiced',0,TO_TIMESTAMP('2012-12-18 19:08:57','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 18, 2012 7:08:58 PM CET
-- Generate Invoices from Shipments
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=64663 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 18, 2012 7:09:00 PM CET
-- Generate Invoices from Shipments
ALTER TABLE M_InOutLine ADD COLUMN ToBeInvoiced CHAR(1) DEFAULT 'Y' CHECK (ToBeInvoiced IN ('Y','N'))
;

