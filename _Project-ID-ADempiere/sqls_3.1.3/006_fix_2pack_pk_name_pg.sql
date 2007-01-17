--Delete field pk_name in window Pack Out Tab Pakage Details _> Reason: do not is nessesary becouse this field are in header
DELETE FROM AD_Field_Trl WHERE AD_Field_ID=50094;
DELETE FROM AD_Field WHERE AD_Field_ID=50094;
--SET ID Export Package AS Reference vs Pk_Name
UPDATE AD_Field SET SeqNo=30, isdisplayed = 'Y' WHERE AD_Field_ID=50091;
--Delete Pk_Name in the Table and History of Changes
DELETE FROM ad_changelog WHERE AD_Column_ID=50126 AND AD_TABLE_ID=50006;
DELETE FROM AD_Column_Trl WHERE AD_Column_ID=50126;
DELETE FROM AD_Column WHERE AD_Column_ID=50126 AND AD_TABLE_ID=50006;
--Set PK_Name AS REference to Table Header 
UPDATE AD_Column SET isidentifier='Y', seqno= 1 WHERE AD_Column_ID=50086
ALTER TABLE ad_package_exp_detail DROP COLUMN pk_name;
ALTER TABLE ad_package_exp RENAME COLUMN pk_name TO name;
UPDATE AD_Column SET AD_Element_ID=469, COLUMNNAME='Name' ,Name='Name' WHERE AD_Column_ID=50086;
ALTER TABLE ad_package_exp_common RENAME COLUMN pk_name TO name;
UPDATE Ad_column SET ad_Element_ID=469, columnname='Name' ,name='Name' WHERE AD_Column_ID=50146;
DELETE FROM ad_element_trl WHERE ad_element_ID=50021;
DELETE FROM ad_element WHERE ad_element_ID=50021;
