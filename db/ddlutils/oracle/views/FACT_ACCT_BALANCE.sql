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
