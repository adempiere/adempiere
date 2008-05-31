-- [ 1951222 ] Can't select jasper reports as formats

UPDATE ad_val_rule
   SET code = 'AD_Process.IsReport=''Y'''
 WHERE ad_val_rule_id = 128
   AND code = 'AD_Process.IsReport=''Y'' AND AD_Process.JasperReport IS NULL';
