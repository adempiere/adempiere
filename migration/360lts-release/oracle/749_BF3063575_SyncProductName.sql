ALTER TABLE I_Product MODIFY Name NVARCHAR2(255) DEFAULT NULL;

UPDATE AD_Column SET fieldlength=255, help='The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 255 characters in length.' 
 WHERE AD_Column_ID = 7819;