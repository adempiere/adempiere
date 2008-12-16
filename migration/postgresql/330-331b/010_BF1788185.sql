ALTER TABLE i_invoice ALTER COLUMN taxindicator TYPE varchar(10);

ALTER TABLE i_order ALTER COLUMN taxindicator TYPE varchar(10);

UPDATE AD_COLUMN
   SET fieldlength = 10
 WHERE ad_column_id IN (8991, 9186);

COMMIT;