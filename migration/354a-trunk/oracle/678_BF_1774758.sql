--
-- BF [ 1774758 ] No default Sales Representant in request window
--

UPDATE AD_COLUMN
	SET DefaultValue = '@#AD_User_ID@'
	WHERE DefaultValue = '@AD_User_ID@' AND AD_Column_ID = 5432;

COMMIT;
