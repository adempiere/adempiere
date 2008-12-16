-- [ 1869844 ] CLIENT_ID not ReadOnly any more ?!?
UPDATE AD_COLUMN SET ad_val_rule_id = 129
WHERE ad_column_id IN (
SELECT ad_column_id
  FROM AD_TABLE t, AD_COLUMN c
 WHERE t.ad_table_id = c.ad_table_id
   AND columnname = 'AD_Client_ID'
   AND t.accesslevel IN (1, 2, 3)
   AND c.ad_val_rule_id IS NULL);