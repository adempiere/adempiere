-- Jan 20, 2008 7:12:48 PM EST
-- Default comment for updating dictionary
UPDATE AD_Column SET IsUpdateable='Y',Updated=TO_TIMESTAMP('2008-01-20 19:12:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=53408
;

-- Jan 20, 2008 7:12:48 PM EST
-- Default comment for updating dictionary
UPDATE AD_Field SET Name='LowLevel', Description=NULL, Help=NULL WHERE AD_Column_ID=53408 AND IsCentrallyMaintained='Y'
;

