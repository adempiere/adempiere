-- Fix problem, a table must not have two identifiers with same seqno 

UPDATE ad_column
   SET isidentifier = 'Y',
       seqno = 2
 WHERE ad_column_id = 50086;

COMMIT;