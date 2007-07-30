DELETE FROM AD_Ref_List
 WHERE AD_Ref_List_ID=638;

UPDATE AD_Ref_List
   SET IsActive='N'
 WHERE AD_Ref_List_ID IN (634,611);

COMMIT;