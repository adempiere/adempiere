ALTER TABLE ad_process_para
MODIFY(defaultvalue NVARCHAR2(2000));

ALTER TABLE ad_process_para
MODIFY(defaultvalue2 NVARCHAR2(2000));

UPDATE ad_column
   SET updated = TO_DATE ('01/24/2007 00:40:17', 'MM/DD/YYYY HH24:MI:SS'),
       fieldlength = 2000
 WHERE ad_column_id IN (3739, 5593);

COMMIT;