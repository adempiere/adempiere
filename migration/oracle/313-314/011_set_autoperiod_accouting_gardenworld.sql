UPDATE c_acctschema
   SET updated = TO_DATE ('01/24/2007 00:23:21', 'MM/DD/YYYY HH24:MI:SS'),
       autoperiodcontrol = 'Y'
 WHERE c_acctschema_id = 101;

COMMIT ;
