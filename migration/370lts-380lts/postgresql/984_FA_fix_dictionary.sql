-- Apr 21, 2014 3:10:01 PM ICT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Column SET DefaultValue='N',Updated=TO_TIMESTAMP('2014-04-21 15:10:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=26303
;

-- Apr 20, 2014 1:21:07 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET IsDisplayed='N',Updated=TO_TIMESTAMP('2014-04-20 01:21:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56254
;

-- Apr 20, 2014 1:24:16 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET IsReadOnly='N', DisplayLogic='@A_CreateAsset@=''Y''&@IsCollectiveAsset@=''N''',Updated=TO_TIMESTAMP('2014-04-20 01:24:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=5825
;

-- Apr 20, 2014 1:25:18 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Field SET DisplayLogic='@A_CreateAsset@=''Y''&@A_CapvsExp@=''Cap''&@A_Asset_ID@<1',Updated=TO_TIMESTAMP('2014-04-20 01:25:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=26002
;

-- Apr 20, 2014 4:56:15 AM WIT
-- DICTIONARY_ID_COMMENTS
INSERT INTO AD_Val_Rule (Code,Type,EntityType,Name,Description,AD_Val_Rule_ID,CreatedBy,Updated,UpdatedBy,AD_Org_ID,AD_Client_ID,IsActive,Created) VALUES ('CASE WHEN ''@A_CapvsExp@''=''Cap'' THEN A_Asset_Status = ''AC'' ELSE A_Asset_Status IN (''AC'',''DP'') END','S','D','Asset - on invoice line','Asset on invoice line',26002,0,TO_TIMESTAMP('2014-04-20 04:56:15','YYYY-MM-DD HH24:MI:SS'),0,0,0,'Y',TO_TIMESTAMP('2014-04-20 04:56:15','YYYY-MM-DD HH24:MI:SS'))
;

-- Apr 20, 2014 4:56:35 AM WIT
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Column SET AD_Val_Rule_ID=26002,Updated=TO_TIMESTAMP('2014-04-20 04:56:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=7734
;

-- May 30, 2014 4:17:42 PM ICT
-- fix asset group acct
UPDATE AD_Column SET DefaultValue='0', ValueMin='0',Updated=TO_TIMESTAMP('2014-05-30 16:17:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55772
;

-- May 30, 2014 4:17:45 PM ICT
-- fix asset group acct
INSERT INTO t_alter_column values('a_asset_group_acct','UseLifeYears','NUMERIC(10)',null,'0')
;

-- 18 Agu 14 9:05:51
-- DICTIONARY_ID_COMMENTS
UPDATE AD_Column SET DefaultValue='''MAN''',Updated=TO_TIMESTAMP('2014-08-18 09:05:51','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55969
;


-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=59255
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field WHERE AD_Field_ID=59255
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=59254
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field WHERE AD_Field_ID=59254
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=56073
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field WHERE AD_Field_ID=56073
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=56074
;

-- Sep 5, 2014 2:29:39 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Field WHERE AD_Field_ID=56074
;

-- Sep 5, 2014 2:29:56 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=55783
;

-- Sep 5, 2014 2:29:56 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column WHERE AD_Column_ID=55783
;

-- Sep 5, 2014 2:29:56 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=59401
;

-- Sep 5, 2014 2:29:56 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column WHERE AD_Column_ID=59401
;

-- Sep 5, 2014 2:29:56 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=55771
;

-- Sep 5, 2014 2:29:56 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column WHERE AD_Column_ID=55771
;


-- Sep 5, 2014 2:34:59 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=55762
;

-- Sep 5, 2014 2:34:59 PM ICT
-- Removing Duplicate column
DELETE FROM AD_Column WHERE AD_Column_ID=55762
;

ALTER TABLE A_ASSET_GROUP_ACCT DROP COLUMN a_disposal_gain_acct;

ALTER TABLE A_ASSET_GROUP_ACCT DROP COLUMN a_disposal_gain;

ALTER TABLE A_ASSET_GROUP_ACCT DROP COLUMN a_disposal_loss;

ALTER TABLE A_ASSET_GROUP_ACCT DROP COLUMN a_disposal_revenue;

-- Fix GardenWorld Samples
UPDATE A_Asset_Group SET isdefault='Y' WHERE A_Asset_Group_ID=50006;

INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50014, 11, 11, 'Y', '2014-09-05 14:23:07.0', 101, '2014-09-05 14:23:07.0', 101, NULL, 'HQ-17400-_-_-_-_', 'HQ-Vehicles-_-_-_-_', 'Y', 101, 564, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50015, 11, 11, 'Y', '2014-09-05 14:50:07.0', 101, '2014-09-05 14:50:07.0', 101, NULL, 'HQ-67240-_-_-_-_', 'HQ-Vehicles Depreciation-_-_-_-_', 'Y', 101, 647, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50016, 11, 11, 'Y', '2014-09-05 14:22:55.0', 101, '2014-09-05 14:22:55.0', 101, NULL, 'HQ-18240-_-_-_-_', 'HQ-Vehicles Accumulated Depreciation-_-_-_-_', 'Y', 101, 575, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50017, 11, 11, 'Y', '2014-09-05 14:43:16.0', 101, '2014-09-05 14:43:16.0', 101, NULL, 'HQ-80700-_-_-_-_', 'HQ-Capital Gains Income-_-_-_-_', 'Y', 101, 713, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50018, 11, 11, 'Y', '2014-09-05 14:43:30.0', 101, '2014-09-05 14:43:30.0', 101, NULL, 'HQ-82700-_-_-_-_', 'HQ-Capital Gains Loss-_-_-_-_', 'Y', 101, 725, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50019, 11, 11, 'Y', '2014-09-05 16:13:25.0', 101, '2014-09-05 16:13:25.0', 101, NULL, 'HQ-17600-_-_-_-_', 'HQ-Software-_-_-_-_', 'Y', 101, 566, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50020, 11, 11, 'Y', '2014-09-05 16:17:46.0', 101, '2014-09-05 16:17:46.0', 101, NULL, 'HQ-18260-_-_-_-_', 'HQ-Software Accumulated Depreciation-_-_-_-_', 'Y', 101, 746, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50021, 11, 11, 'Y', '2014-09-05 16:18:03.0', 101, '2014-09-05 16:18:03.0', 101, NULL, 'HQ-67260-_-_-_-_', 'HQ-Software Depreciation-_-_-_-_', 'Y', 101, 503, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50022, 11, 11, 'Y', '2014-09-05 16:47:44.0', 101, '2014-09-05 16:47:44.0', 101, NULL, 'HQ-17100-_-_-_-_', 'HQ-Furniture-_-_-_-_', 'Y', 101, 561, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50023, 11, 11, 'Y', '2014-09-05 16:48:00.0', 101, '2014-09-05 16:48:00.0', 101, NULL, 'HQ-18210-_-_-_-_', 'HQ-Furniture Accumulated Depreciation-_-_-_-_', 'Y', 101, 572, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50024, 11, 11, 'Y', '2014-09-05 16:48:11.0', 101, '2014-09-05 16:48:11.0', 101, NULL, 'HQ-67210-_-_-_-_', 'HQ-Furniture Depreciation-_-_-_-_', 'Y', 101, 499, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50025, 11, 11, 'Y', '2014-09-05 16:48:59.0', 101, '2014-09-05 16:48:59.0', 101, NULL, 'HQ-17200-_-_-_-_', 'HQ-Fixtures-_-_-_-_', 'Y', 101, 562, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50026, 11, 11, 'Y', '2014-09-05 16:49:20.0', 101, '2014-09-05 16:49:20.0', 101, NULL, 'HQ-18220-_-_-_-_', 'HQ-Fixtures Accumulated Depreciation-_-_-_-_', 'Y', 101, 573, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50027, 11, 11, 'Y', '2014-09-05 16:49:31.0', 101, '2014-09-05 16:49:31.0', 101, NULL, 'HQ-67220-_-_-_-_', 'HQ-Fixtures Depreciation-_-_-_-_', 'Y', 101, 500, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50028, 11, 11, 'Y', '2014-09-05 16:51:24.0', 101, '2014-09-05 16:51:24.0', 101, NULL, 'HQ-17300-_-_-_-_', 'HQ-Equipment-_-_-_-_', 'Y', 101, 563, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50029, 11, 11, 'Y', '2014-09-05 16:51:36.0', 101, '2014-09-05 16:51:36.0', 101, NULL, 'HQ-18230-_-_-_-_', 'HQ-Equipment Accumulated Depreciation-_-_-_-_', 'Y', 101, 574, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50030, 11, 11, 'Y', '2014-09-05 16:52:20.0', 101, '2014-09-05 16:52:20.0', 101, NULL, 'HQ-67230-_-_-_-_', 'HQ-Equipment Depreciation-_-_-_-_', 'Y', 101, 501, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50031, 11, 11, 'Y', '2014-09-05 16:52:52.0', 101, '2014-09-05 16:52:52.0', 101, NULL, 'HQ-80800-_-_-_-_', 'HQ-Fixed Asset Sale Gain-_-_-_-_', 'Y', 101, 714, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50032, 11, 11, 'Y', '2014-09-05 16:53:04.0', 101, '2014-09-05 16:53:04.0', 101, NULL, 'HQ-82800-_-_-_-_', 'HQ-Fixed Asset Sale Loss-_-_-_-_', 'Y', 101, 726, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50033, 11, 11, 'Y', '2014-09-05 16:54:28.0', 101, '2014-09-05 16:54:28.0', 101, NULL, 'HQ-17500-_-_-_-_', 'HQ-Data Processing Equipment-_-_-_-_', 'Y', 101, 565, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50034, 11, 11, 'Y', '2014-09-05 16:54:41.0', 101, '2014-09-05 16:54:41.0', 101, NULL, 'HQ-18250-_-_-_-_', 'HQ-Data Processing Equipment Accumulated Depreciation-_-_-_-_', 'Y', 101, 576, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50035, 11, 11, 'Y', '2014-09-05 16:54:59.0', 101, '2014-09-05 16:54:59.0', 101, NULL, 'HQ-67250-_-_-_-_', 'HQ-Data Processing Equipment Depreciation-_-_-_-_', 'Y', 101, 502, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50036, 11, 11, 'Y', '2014-09-05 16:55:56.0', 101, '2014-09-05 16:55:56.0', 101, NULL, 'HQ-16200-_-_-_-_', 'HQ-Building-_-_-_-_', 'Y', 101, 556, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50037, 11, 11, 'Y', '2014-09-05 16:56:08.0', 101, '2014-09-05 16:56:08.0', 101, NULL, 'HQ-18120-_-_-_-_', 'HQ-Building Accumulated Depreciation-_-_-_-_', 'Y', 101, 569, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO c_validcombination(c_validcombination_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, alias, combination, description, isfullyqualified, c_acctschema_id, account_id, m_product_id, c_bpartner_id, ad_orgtrx_id, c_locfrom_id, c_locto_id, c_salesregion_id, c_project_id, c_campaign_id, c_activity_id, user1_id, user2_id, c_subacct_id, userelement1_id, userelement2_id)
  VALUES(50038, 11, 11, 'Y', '2014-09-05 16:56:18.0', 101, '2014-09-05 16:56:18.0', 101, NULL, 'HQ-67120-_-_-_-_', 'HQ-Building Depreciation-_-_-_-_', 'Y', 101, 497, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);  
  
INSERT INTO a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50006, 101, 11, 11, 'Y', '2014-09-04 09:22:19.0', 101, '2014-09-05 14:44:02.0', 101, 50014, 50015, 50016, 50003, 50006, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 5, 60, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 5, 60, 50031, 50032);
INSERT INTO adempiere.a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50001, 101, 11, 50001, 'Y', '2014-09-05 16:18:35.0', 101, '2014-09-05 16:18:35.0', 101, 50019, 50021, 50020, 50003, 50001, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 5, 60, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 5, 60, 50017, 50018);
INSERT INTO adempiere.a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50004, 101, 11, 50001, 'Y', '2014-09-05 16:48:41.0', 101, '2014-09-05 16:48:41.0', 101, 50022, 50024, 50023, 50003, 50004, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 5, 60, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 5, 60, 50017, 50018);
INSERT INTO adempiere.a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50005, 101, 11, 50001, 'Y', '2014-09-05 16:50:54.0', 101, '2014-09-05 16:50:54.0', 101, 50025, 50027, 50026, 50003, 50005, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 5, 60, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 5, 60, 50017, 50018);
INSERT INTO adempiere.a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50007, 101, 11, 50001, 'Y', '2014-09-05 16:53:06.0', 101, '2014-09-05 16:53:06.0', 101, 50028, 50030, 50029, 50003, 50007, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 5, 60, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 5, 60, 50031, 50032);
INSERT INTO adempiere.a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50003, 101, 11, 50001, 'Y', '2014-09-05 16:55:26.0', 101, '2014-09-05 16:55:26.0', 101, 50033, 50035, 50034, 50003, 50003, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 5, 60, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 5, 60, 50031, 50032);
INSERT INTO adempiere.a_asset_group_acct(a_asset_group_id, c_acctschema_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, a_asset_acct, a_depreciation_acct, a_accumdepreciation_acct, a_depreciation_id, a_asset_group_acct_id, a_depreciation_manual_period, a_depreciation_variable_perc, a_split_percent, a_reval_depexp_offset, a_reval_cost_offset_prior, a_reval_cost_offset, a_reval_cal_method, a_reval_accumdep_offset_prior, a_reval_accumdep_offset_cur, uselifeyears, uselifemonths, processing, postingtype, depreciationtype, conventiontype, a_depreciation_table_header_id, a_depreciation_manual_amount, a_depreciation_calc_type, a_asset_spread_type, a_reval_depexp_offset_acct, a_reval_cost_offset_prior_acct, a_reval_cost_offset_acct, a_reval_adep_offset_prior_acct, a_reval_adep_offset_cur_acct, a_depreciation_method_id, a_depreciation_conv_f_id, a_depreciation_conv_id, a_depreciation_method_f_id, a_depreciation_variable_perc_f, a_depreciation_f_id, uselifeyears_f, uselifemonths_f, a_disposal_revenue_acct, a_disposal_loss_acct)
  VALUES(50000, 101, 11, 50001, 'Y', '2014-09-05 16:56:40.0', 101, '2014-09-05 16:56:40.0', 101, 50036, 50038, 50037, 50003, 50000, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 10, 120, 'N', 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 50003, 10, 120, 50031, 50032);
  

Update a_asset_group_acct set conventiontype='50000', a_depreciation_calc_type='50000' where ad_client_id = 11;

-- Sep 5, 2014 2:04:24 PM ICT
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=0
;

-- Sep 5, 2014 2:04:24 PM ICT
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 0,0,'Y', CURRENT_TIMESTAMP,0, CURRENT_TIMESTAMP,0, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=0) )
;

-- Sep 5, 2014 2:04:25 PM ICT
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=102
;

-- Sep 5, 2014 2:04:25 PM ICT
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=102) )
;

-- Sep 5, 2014 2:04:25 PM ICT
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
DELETE FROM AD_Document_Action_Access WHERE AD_Role_ID=103
;

-- Sep 5, 2014 2:04:25 PM ICT
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_Document_Action_Access (AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,C_DocType_ID , AD_Ref_List_ID, AD_Role_ID) (SELECT 11,0,'Y', CURRENT_TIMESTAMP,100, CURRENT_TIMESTAMP,100, doctype.C_DocType_ID, "action".AD_Ref_List_ID, rol.AD_Role_ID FROM AD_Client client INNER JOIN C_DocType doctype ON (doctype.AD_Client_ID=client.AD_Client_ID) INNER JOIN AD_Ref_List "action" ON ("action".AD_Reference_ID=135) INNER JOIN AD_Role rol ON (rol.AD_Client_ID=client.AD_Client_ID AND rol.AD_Role_ID=103) )
;

