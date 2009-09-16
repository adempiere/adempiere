-- Jun 1, 2009 4:30:09 PM MYT
-- RMA Feature - ID: 1756793
UPDATE AD_Val_Rule SET Code='C_DocType.DocBaseType IN (''SOO'', ''POO'') AND C_DocType.IsSOTrx=''@IsSOTrx@'' AND COALESCE(C_DocType.DocSubTypeSO,'' '')<>''RM''',Updated=TO_DATE('2009-06-01 16:30:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Val_Rule_ID=133
;

COMMIT;


