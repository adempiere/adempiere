-- only needed for oracle

UPDATE AD_PROCESS
   SET classname = 'org.compiere.process.SynchronizeTerminology',
       procedurename = NULL,
       updated = TO_DATE ('2007-05-07 21:20:30', 'YYYY-MM-DD HH24:MI:SS'),
       updatedby = 100
 WHERE ad_process_id = 172;

COMMIT ;

DROP PROCEDURE Ad_Synchronize;
