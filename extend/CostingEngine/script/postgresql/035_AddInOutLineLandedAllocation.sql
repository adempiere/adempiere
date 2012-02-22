-- Feb 7, 2011 12:21:50 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,60320,1026,0,19,760,'M_InOutLine_ID',TO_TIMESTAMP('2011-02-07 12:19:30','YYYY-MM-DD HH24:MI:SS'),0,'Line on Shipment or Receipt document','D',10,'The Shipment/Receipt Line indicates a unique line in a Shipment/Receipt document','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Shipment/Receipt Line',0,TO_TIMESTAMP('2011-02-07 12:19:30','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Feb 7, 2011 12:21:53 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=60320 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Feb 7, 2011 12:22:11 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
ALTER TABLE C_LandedCostAllocation ADD COLUMN M_InOutLine_ID NUMERIC(10) DEFAULT NULL 
;

-- Feb 7, 2011 12:24:46 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,59611,61144,0,698,TO_TIMESTAMP('2011-02-07 12:22:41','YYYY-MM-DD HH24:MI:SS'),0,'The record is active in the system',1,'D','There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.
There are two reasons for de-activating and not deleting records:
(1) The system requires the record for audit purposes.
(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.','Y','Y','Y','N','N','N','N','N','Active',TO_TIMESTAMP('2011-02-07 12:22:41','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 7, 2011 12:24:46 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61144 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 7, 2011 12:26:13 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,60320,61145,0,698,TO_TIMESTAMP('2011-02-07 12:24:46','YYYY-MM-DD HH24:MI:SS'),0,'Line on Shipment or Receipt document',10,'D','The Shipment/Receipt Line indicates a unique line in a Shipment/Receipt document','Y','Y','Y','N','N','N','N','N','Shipment/Receipt Line',TO_TIMESTAMP('2011-02-07 12:24:46','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Feb 7, 2011 12:26:13 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=61145 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=0,IsDisplayed='N' WHERE AD_Field_ID=61144
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=40,IsDisplayed='Y' WHERE AD_Field_ID=61145
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=11282
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=12161
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=11278
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=80,IsDisplayed='Y' WHERE AD_Field_ID=11283
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=90,IsDisplayed='Y' WHERE AD_Field_ID=12162
;

-- Feb 7, 2011 12:29:41 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET SeqNo=100,IsDisplayed='Y' WHERE AD_Field_ID=12178
;

-- Feb 7, 2011 12:29:51 PM CST
-- Add M_InOutLine_ID Landed Cost Allocation
UPDATE AD_Field SET IsReadOnly='Y', IsSameLine='Y',Updated=TO_TIMESTAMP('2011-02-07 12:29:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=61145
;

