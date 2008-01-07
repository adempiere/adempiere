-- [ 1866222 ] Wrong default dictionary entry for C_CashBook.C_Currency_ID
UPDATE AD_COLUMN SET defaultvalue = NULL
WHERE ad_column_id = 5521;