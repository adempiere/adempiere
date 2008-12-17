-- Set Proper Where clause
UPDATE AD_Tab SET WhereClause='C_BPartner_ID IS NOT null',Updated=now(),UpdatedBy=100 WHERE AD_Tab_ID=330
;