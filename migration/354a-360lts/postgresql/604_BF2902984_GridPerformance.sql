-- Nov 24, 2009 4:20:31 PM MYT
-- https://sourceforge.net/tracker/?func=detail&aid=2902984&group_id=176962&atid=955896 [Make grid edit mode not always on to make the UI appear more responsive]
UPDATE AD_SysConfig SET Value='N',Updated=TO_TIMESTAMP('2009-11-24 16:20:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_SysConfig_ID=50019
;

COMMIT;


