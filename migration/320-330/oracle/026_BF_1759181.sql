/*
   Fix bug in Application Dictionary 
   [ 1759181 ] AD_Color.ColorType is defined as Color and must be List
*/

UPDATE AD_COLUMN
   SET ad_reference_id = 17
 WHERE ad_column_id = 6232 AND ad_reference_id = 27;

COMMIT;