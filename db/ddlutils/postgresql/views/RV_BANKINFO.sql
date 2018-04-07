-- View: adempiere.rv_bankinfo

-- DROP VIEW adempiere.rv_bankinfo;

CREATE OR REPLACE VIEW adempiere.rv_bankinfo AS
 SELECT b.ad_client_id,
    b.ad_org_id,
    b.name,
    ba.accountno,
    bd.paymentrule,
    ba.c_bankaccount_id,
    ba.created,
    ba.createdby,
    ba.updated,
    ba.updatedby,
    ba.uuid,
    ba.isactive,
    b.banktype,
    ba.c_bank_id,
    bd.currentnext
   FROM adempiere.c_bankaccount ba
     JOIN adempiere.c_bank b ON ba.c_bank_id = b.c_bank_id
     LEFT JOIN adempiere.c_bankaccountdoc bd ON ba.c_bankaccount_id = bd.c_bankaccount_id;


