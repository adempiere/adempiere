UPDATE AD_PROCESS_PARA
   SET defaultvalue = NULL,
       ismandatory = 'N',
       updated = TO_TIMESTAMP ('2007-05-07 20:55:59', 'YYYY-MM-DD HH24:MI:SS'),
       updatedby = 100
 WHERE ad_process_para_id = 50019;

COMMIT ;
