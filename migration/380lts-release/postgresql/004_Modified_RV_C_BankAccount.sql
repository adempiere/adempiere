DROP VIEW rv_c_bankaccount;
CREATE OR REPLACE VIEW rv_c_bankaccount AS 
 SELECT bac.c_bankaccount_id, bac.ad_client_id, 
    bac.ad_org_id, bac.isactive, bac.created, 
    bac.createdby, bac.updated, bac.updatedby, 
    bac.c_bank_id, bac.c_currency_id, 
    bac.bankaccounttype, bac.accountno, 
    bac.currentbalance, bac.creditlimit, 
    bac.isdefault, bac.iban, bac.description, 
    bac.bban, bac.paymentexportclass, 
    114 AS c_conversiontype_id, getdate() AS datetrx,
    bp.c_bpartner_id
    FROM c_bankaccount bac 
    JOIN C_Bank bk on (bac.c_bank_id = bk.c_bank_id)
    JOIN C_bp_bankaccount bp on (bp.c_bank_id = bk.c_bank_id);