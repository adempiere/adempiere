-- equivalent cleaner code

UPDATE AD_VAL_RULE
   SET code = 'M_PriceList.IsSOPriceList = ''@IsSOTrx@'''
 WHERE ad_val_rule_id = 271;

COMMIT;
 