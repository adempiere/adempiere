-- 1642765 - problem with Facc_act_balance slow financial report

DROP TABLE FACT_ACCT_BALANCE
;

CREATE OR REPLACE VIEW fact_acct_balance
AS
   SELECT   ad_client_id, ad_org_id, c_acctschema_id,
            TRUNC (dateacct) as dateacct, account_id, postingtype,
            m_product_id, c_bpartner_id, c_project_id, ad_orgtrx_id,
            c_salesregion_id, c_activity_id, c_campaign_id, c_locto_id,
            c_locfrom_id, user1_id, user2_id, gl_budget_id,
            COALESCE (SUM (amtacctdr), 0) as amtacctdr,
            COALESCE (SUM (amtacctcr), 0) as amtacctcr,
            COALESCE (SUM (qty), 0) as qty, MAX (createdby) as createdby,
            MAX (created) as created, MAX (updatedby) as updatedby,
            MAX (updated) as updated, MAX (isactive) as isactive,
            MAX (c_subacct_id) as c_subacct_id, userelement1_id,
            userelement2_id, MAX (c_projectphase_id) as c_projectphase_id,
            MAX (c_projecttask_id) as c_projecttask_id
       FROM fact_acct a
   GROUP BY ad_client_id, ad_org_id, c_acctschema_id,
            TRUNC (dateacct), account_id, postingtype,
            m_product_id, c_bpartner_id, c_project_id, ad_orgtrx_id,
            c_salesregion_id, c_activity_id, c_campaign_id, c_locto_id,
            c_locfrom_id, user1_id, user2_id,
            userelement1_id, userelement2_id,
            gl_budget_id
;

CREATE INDEX FACT_ACCT_TRUNC_DATEACCT ON FACT_ACCT (TRUNC(dateacct))
;

-- Jul 15, 2008 1:27:48 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Table SET IsView='Y',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=547
;

-- Jul 15, 2008 1:31:01 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Process_Para SET IsActive = 'N',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=283
;

-- Jul 15, 2008 1:32:35 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Menu SET IsActive = 'N',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Menu_ID=393
;

-- Jul 15, 2008 1:32:44 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Process SET IsActive = 'N',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_ID=203
;

-- Jul 15, 2008 1:34:15 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Process_Para SET IsActive = 'N',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=453
;

-- Jul 15, 2008 1:34:26 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Process_Para SET IsActive = 'N',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=53123
;

-- Jul 15, 2008 1:35:05 AM COT
-- 1642765 - problem with Facc_act_balance slow financial report
UPDATE AD_Process_Para SET IsActive = 'N',Updated=TO_DATE('2008-07-15 01:27:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Process_Para_ID=454
;