-- globalqss: bug [ 1618570 ] ORA-00936 in Project window
-- wrong value in AD_Tab.WhereClause  -> old value  
-->>  C_ProjectPhase_ID > 0 AND AND COALESCE(C_ProjectTask_ID,0)=0

UPDATE ad_tab
   SET whereclause =
                'C_ProjectPhase_ID > 0 AND COALESCE(C_ProjectTask_ID,0)=0'
 WHERE ad_tab_id = 799;

COMMIT;