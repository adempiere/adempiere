-- Please review before apply
-- if you have any of this columns REALLY encrypted you must not apply this patch:
-- AD_USER.EMailUserPW
-- AD_USER.Password
-- C_PAYMENTPROCESSOR.Password
-- C_PAYMENTPROCESSOR.ProxyPassword

-- defining columns non encrypted by default as discussed here
-- [ 1722235 ] ENCRYPTION FOR PASSWORD wrongly MANAGED
-- https://sourceforge.net/tracker/?func=detail&atid=879332&aid=1722235&group_id=176962

UPDATE AD_COLUMN
   SET isencrypted = 'N',
   updated = TO_DATE ('07/10/2007 00:00:01', 'MM/DD/YYYY HH24:MI:SS')
 WHERE ad_column_id IN (417, 5059, 5065, 7794);

COMMIT ;