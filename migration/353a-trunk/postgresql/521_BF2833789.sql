-- Aug 9, 2009 1:36:21 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Element SET Description='The user/contact has full access to Business Partner information and resources',Updated=TO_TIMESTAMP('2009-08-09 13:36:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2835
;

-- Aug 9, 2009 1:36:22 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=2835
;

-- Aug 9, 2009 1:36:22 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Column SET ColumnName='IsFullBPAccess', Name='Full BP Access', Description='The user/contact has full access to Business Partner information and resources', Help='If selected, the user has full access to the Business Partner (BP) information (Business Documents like Orders, Invoices - Requests) or resources (Assets, Downloads). If you deselet it, the user has no access rights unless, you explicitly grant it in tab "BP Access"' WHERE AD_Element_ID=2835
;

-- Aug 9, 2009 1:36:22 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Process_Para SET ColumnName='IsFullBPAccess', Name='Full BP Access', Description='The user/contact has full access to Business Partner information and resources', Help='If selected, the user has full access to the Business Partner (BP) information (Business Documents like Orders, Invoices - Requests) or resources (Assets, Downloads). If you deselet it, the user has no access rights unless, you explicitly grant it in tab "BP Access"', AD_Element_ID=2835 WHERE UPPER(ColumnName)='ISFULLBPACCESS' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Aug 9, 2009 1:36:22 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Process_Para SET ColumnName='IsFullBPAccess', Name='Full BP Access', Description='The user/contact has full access to Business Partner information and resources', Help='If selected, the user has full access to the Business Partner (BP) information (Business Documents like Orders, Invoices - Requests) or resources (Assets, Downloads). If you deselet it, the user has no access rights unless, you explicitly grant it in tab "BP Access"' WHERE AD_Element_ID=2835 AND IsCentrallyMaintained='Y'
;

-- Aug 9, 2009 1:36:22 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Field SET Name='Full BP Access', Description='The user/contact has full access to Business Partner information and resources', Help='If selected, the user has full access to the Business Partner (BP) information (Business Documents like Orders, Invoices - Requests) or resources (Assets, Downloads). If you deselet it, the user has no access rights unless, you explicitly grant it in tab "BP Access"' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=2835) AND IsCentrallyMaintained='Y'
;

-- Aug 9, 2009 1:38:22 PM EEST
-- [2833789] - concat instead of contac
UPDATE AD_Element_Trl SET IsTranslated='Y',Updated=TO_TIMESTAMP('2009-08-09 13:38:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=2835 AND AD_Language='es_MX'
;

