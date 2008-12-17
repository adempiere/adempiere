--Delete field pk_name in window Pack Out Tab Pakage Details _> Reason: do not is nessesary becouse this field are in header

DELETE FROM ad_field_trl
      WHERE ad_field_id = 50094;

DELETE FROM ad_field
      WHERE ad_field_id = 50094;

--SET ID Export Package AS Reference vs Pk_Name

UPDATE ad_field
   SET seqno = 30,
       isdisplayed = 'Y'
 WHERE ad_field_id = 50091;

--Delete Pk_Name in the Table and History of Changes

DELETE FROM ad_changelog
      WHERE ad_column_id = 50126 AND ad_table_id = 50006;

DELETE FROM ad_column_trl
      WHERE ad_column_id = 50126;

DELETE FROM ad_column
      WHERE ad_column_id = 50126 AND ad_table_id = 50006;

--Set PK_Name AS REference to Table Header 

UPDATE ad_column
   SET isidentifier = 'Y',
       seqno = 1
 WHERE ad_column_id = 50086;

ALTER TABLE ad_package_exp_detail DROP COLUMN pk_name;

ALTER TABLE ad_package_exp RENAME COLUMN pk_name TO name;

UPDATE ad_column
   SET ad_element_id = 469,
       columnname = 'Name',
       name = 'Name'
 WHERE ad_column_id = 50086;

ALTER TABLE ad_package_exp_common RENAME COLUMN pk_name TO NAME;

UPDATE ad_column
   SET ad_element_id = 469,
       columnname = 'Name',
       name = 'Name'
 WHERE ad_column_id = 50146;

DELETE FROM ad_element_trl
      WHERE ad_element_id = 50021;

DELETE FROM ad_element
      WHERE ad_element_id = 50021;

COMMIT ;
