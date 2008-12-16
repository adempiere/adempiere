UPDATE ad_column
   SET defaultvalue = 'N'
 WHERE ad_column_id = 50169;

UPDATE ad_column
   SET ad_reference_id = 38
 WHERE ad_column_id = 50170;
 
COMMIT;
