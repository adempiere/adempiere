-- DROP VIEW rv_c_bankaccount;

CREATE OR REPLACE VIEW rv_c_bankaccount AS 
 SELECT c_bankaccount.c_bankaccount_id, c_bankaccount.ad_client_id, 
    c_bankaccount.ad_org_id, c_bankaccount.isactive, c_bankaccount.created, 
    c_bankaccount.createdby, c_bankaccount.updated, c_bankaccount.updatedby, 
    c_bankaccount.c_bank_id, c_bankaccount.c_currency_id, 
    c_bankaccount.bankaccounttype, c_bankaccount.accountno, 
    c_bankaccount.currentbalance, c_bankaccount.creditlimit, 
    c_bankaccount.isdefault, c_bankaccount.iban, c_bankaccount.description, 
    c_bankaccount.bban, c_bankaccount.paymentexportclass, 
    114 AS c_conversiontype_id, getdate() AS datetrx
   FROM c_bankaccount;