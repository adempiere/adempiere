SET CLIENT_ENCODING TO 'UTF8';

-- Dec 11, 2010 4:53:42 PM COT
-- schedule with record id
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60264,126,0,19,688,'AD_Table_ID',TO_TIMESTAMP('2010-12-11 16:53:41','YYYY-MM-DD HH24:MI:SS'),100,'Database Table information','D',10,'The Database Table provides the information of the table definition','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Table',0,TO_TIMESTAMP('2010-12-11 16:53:41','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 11, 2010 4:53:42 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60264 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 11, 2010 4:54:37 PM COT
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60265,538,0,28,688,'Record_ID',TO_TIMESTAMP('2010-12-11 16:54:36','YYYY-MM-DD HH24:MI:SS'),100,'Direct internal record ID','D',10,'The Record ID is the internal unique identifier of a record. Please note that zooming to the record may not be successful for Orders, Invoices and Shipment/Receipts as sometimes the Sales Order type is not known.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Record ID',0,TO_TIMESTAMP('2010-12-11 16:54:36','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Dec 11, 2010 4:54:37 PM COT
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60265 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Dec 11, 2010 4:55:04 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60264,60991,0,589,TO_TIMESTAMP('2010-12-11 16:55:03','YYYY-MM-DD HH24:MI:SS'),100,'Database Table information',10,'D','The Database Table provides the information of the table definition','Y','Y','Y','N','N','N','N','N','Table',160,0,TO_TIMESTAMP('2010-12-11 16:55:03','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 11, 2010 4:55:04 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60991 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 11, 2010 4:55:14 PM COT
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,SeqNo,SortNo,Updated,UpdatedBy) VALUES (0,60265,60992,0,589,TO_TIMESTAMP('2010-12-11 16:55:13','YYYY-MM-DD HH24:MI:SS'),100,'Direct internal record ID',10,'D','The Record ID is the internal unique identifier of a record. Please note that zooming to the record may not be successful for Orders, Invoices and Shipment/Receipts as sometimes the Sales Order type is not known.','Y','Y','Y','N','N','N','N','Y','Record ID',170,0,TO_TIMESTAMP('2010-12-11 16:55:13','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Dec 11, 2010 4:55:14 PM COT
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=60992 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Dec 11, 2010 4:56:27 PM COT
ALTER TABLE AD_Scheduler ADD COLUMN AD_Table_ID NUMERIC(10) DEFAULT NULL 
;

-- Dec 11, 2010 4:56:33 PM COT
ALTER TABLE AD_Scheduler ADD COLUMN Record_ID NUMERIC(10) DEFAULT NULL 
;

-- Dec 11, 2010 5:00:09 PM COT
UPDATE AD_Column SET AD_Reference_ID=11,Updated=TO_TIMESTAMP('2010-12-11 17:00:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=60265
;

-- Dec 11, 2010 5:53:00 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53122,0,TO_TIMESTAMP('2010-12-11 17:52:59','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','The table doesn''t have associated button for the process','Choose a table with a column associated to the process','E',TO_TIMESTAMP('2010-12-11 17:52:59','YYYY-MM-DD HH24:MI:SS'),100,'TableMustHaveProcessButton')
;

-- Dec 11, 2010 5:53:00 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53122 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 11, 2010 5:54:08 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53123,0,TO_TIMESTAMP('2010-12-11 17:54:07','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','To use Record ID you need to choose a table','Fill the table field first','E',TO_TIMESTAMP('2010-12-11 17:54:07','YYYY-MM-DD HH24:MI:SS'),100,'MustFillTable')
;

-- Dec 11, 2010 5:54:08 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53123 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 11, 2010 5:54:58 PM COT
INSERT INTO AD_Message (AD_Client_ID,AD_Message_ID,AD_Org_ID,Created,CreatedBy,EntityType,IsActive,MsgText,MsgTip,MsgType,Updated,UpdatedBy,Value) VALUES (0,53124,0,TO_TIMESTAMP('2010-12-11 17:54:57','YYYY-MM-DD HH24:MI:SS'),100,'D','Y','Record ID doesn''t exist in the table','Fill properly the Record ID field with a valid ID on the chosen table','E',TO_TIMESTAMP('2010-12-11 17:54:57','YYYY-MM-DD HH24:MI:SS'),100,'NoRecordID')
;

-- Dec 11, 2010 5:54:58 PM COT
INSERT INTO AD_Message_Trl (AD_Language,AD_Message_ID, MsgText,MsgTip, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Message_ID, t.MsgText,t.MsgTip, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Message t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Message_ID=53124 AND NOT EXISTS (SELECT * FROM AD_Message_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Message_ID=t.AD_Message_ID)
;

-- Dec 11, 2010 5:55:22 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='La tabla no tiene asociado un botón para el proceso',MsgTip='Escoja una tabla con una columna asociada al proceso',Updated=TO_TIMESTAMP('2010-12-11 17:55:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53122 AND AD_Language LIKE 'es_%'
;

-- Dec 11, 2010 5:55:48 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='Debe seleccionar una tabla para utilizar el ID de Registro',MsgTip='Llene primero el campo tabla',Updated=TO_TIMESTAMP('2010-12-11 17:55:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53123 AND AD_Language LIKE 'es_%'
;

-- Dec 11, 2010 5:56:17 PM COT
UPDATE AD_Message_Trl SET IsTranslated='Y',MsgText='ID de Registro no existe en la tabla',MsgTip='Llene correctamente el campo ID de Registro con un ID válido en la tabla seleccionada',Updated=TO_TIMESTAMP('2010-12-11 17:56:17','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53124 AND AD_Language LIKE 'es_%'
;

