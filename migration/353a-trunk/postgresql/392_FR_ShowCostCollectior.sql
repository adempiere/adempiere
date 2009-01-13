

-- Jan 9, 2009 8:30:36 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,EntityType,FieldLength,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,Updated,UpdatedBy,Version) VALUES (0,56574,53310,0,30,808,'PP_Cost_Collector_ID',TO_TIMESTAMP('2009-01-09 20:30:25','YYYY-MM-DD HH24:MI:SS'),0,'EE01',10,'Y','Y','N','N','N','N','N','N','N','N','N','N','N','Manufacturing Cost Collector',TO_TIMESTAMP('2009-01-09 20:30:25','YYYY-MM-DD HH24:MI:SS'),0,0)
;

-- Jan 9, 2009 8:30:36 PM ECT
-- Manufacturing Cost Management
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=56574 AND EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Column_ID!=t.AD_Column_ID)
;

-- Jan 9, 2009 8:30:41 PM ECT
-- Manufacturing Cost Management
ALTER TABLE M_CostDetail ADD COLUMN PP_Cost_Collector_ID NUMERIC(10)
;

-- Jan 9, 2009 8:34:20 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Column SET AD_Element_ID=53310, ColumnName='PP_Cost_Collector_ID', Description=NULL, Help=NULL, Name='Manufacturing Cost Collector',Updated=TO_TIMESTAMP('2009-01-09 20:34:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=53409
;

-- Jan 9, 2009 8:34:20 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Column_Trl SET IsTranslated='N' WHERE AD_Column_ID=53409
;

-- Jan 9, 2009 8:34:20 PM ECT
-- Manufacturing Cost Management
UPDATE AD_Field SET Name='Manufacturing Cost Collector', Description=NULL, Help=NULL WHERE AD_Column_ID=53409 AND IsCentrallyMaintained='Y'
;

-- Jan 9, 2009 8:34:26 PM ECT
-- Manufacturing Cost Management
ALTER TABLE M_Transaction ADD COLUMN PP_Cost_Collector_ID NUMERIC(10)
;

-- Jan 12, 2009 11:57:36 AM ECT
-- Standard Cost for Manufacturing
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=53635
;

-- Jan 12, 2009 11:57:37 AM ECT
-- Standard Cost for Manufacturing
DELETE FROM AD_Field WHERE AD_Field_ID=53635
;

-- Jan 12, 2009 11:58:36 AM ECT
-- Standard Cost for Manufacturing
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=53410
;

-- Jan 12, 2009 11:58:36 AM ECT
-- Standard Cost for Manufacturing
DELETE FROM AD_Column WHERE AD_Column_ID=53410
;

-- Jan 12, 2009 12:00:03 PM ECT
-- Standard Cost for Manufacturing
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,DisplayLength,EntityType,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,53409,56606,0,289,TO_TIMESTAMP('2009-01-12 12:00:02','YYYY-MM-DD HH24:MI:SS'),0,10,'EE01','Y','Y','Y','N','N','N','N','N','Manufacturing Cost Collector',TO_TIMESTAMP('2009-01-12 12:00:02','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Jan 12, 2009 12:00:03 PM ECT
-- Standard Cost for Manufacturing
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=56606 AND EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Field_ID!=t.AD_Field_ID)
;

DROP VIEW rv_pp_order_transactions;
CREATE OR REPLACE VIEW rv_pp_order_transactions AS 
SELECT DISTINCT 
o.ad_client_id, 
o.ad_org_id, 
o.isactive, 
o.created, 
o.createdby, 
o.updatedby, 
o.updated, 
o.documentno, 
ol.m_product_id,
mt.m_locator_id,
mt.movementdate,
o.pp_order_id,
o.qtydelivered,
o.qtyscrap,
ol.qtydelivered AS qtydeliveredline,
o.qtydelivered * ol.qtybatch / 100 AS qtyissueshouldbe,
ol.qtyscrap AS qtyscrapline,
o.qtyscrap * ol.qtybatch / 100 AS qtyissuescrapshouldbe,
mt.createdby AS createdbyissue,
mt.updatedby AS updatedbyissue,
( SELECT sum(t.movementqty) AS sum FROM m_transaction t WHERE t.pp_cost_collector_id = cc.pp_cost_collector_id) AS qtytodeliver,
(o.qtydelivered + o.qtyscrap) * ol.qtybatch / 100 + (( SELECT sum(t.movementqty) AS sum FROM m_transaction t WHERE t.pp_cost_collector_id = cc.pp_cost_collector_id)) AS differenceqty,
o.issotrx,
o.dateordered
FROM pp_order o
JOIN pp_order_bomline ol ON ol.pp_order_id = o.pp_order_id
JOIN pp_cost_collector cc ON cc.pp_order_bomline_id = ol.pp_order_bomline_id
LEFT JOIN m_transaction mt ON mt.pp_cost_collector_id = cc.pp_cost_collector_id
;

ALTER TABLE m_transaction DROP COLUMN pp_order_id;
ALTER TABLE m_transaction DROP COLUMN pp_order_bomline_id;