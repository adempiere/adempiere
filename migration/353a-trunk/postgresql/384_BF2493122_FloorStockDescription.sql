-- Jan 7, 2009 1:32:53 PM ECT
-- Manufacturing Cost
UPDATE AD_Element SET Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ',Updated=TO_TIMESTAMP('2009-01-07 13:32:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53726
;

-- Jan 7, 2009 1:32:53 PM ECT
-- Manufacturing Cost
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53726
;

-- Jan 7, 2009 1:32:53 PM ECT
-- Manufacturing Cost
UPDATE AD_Column SET ColumnName='P_FloorStock_Acct', Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ' WHERE AD_Element_ID=53726
;

-- Jan 7, 2009 1:32:53 PM ECT
-- Manufacturing Cost
UPDATE AD_Process_Para SET ColumnName='P_FloorStock_Acct', Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ', AD_Element_ID=53726 WHERE UPPER(ColumnName)='P_FLOORSTOCK_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 7, 2009 1:32:53 PM ECT
-- Manufacturing Cost
UPDATE AD_Process_Para SET ColumnName='P_FloorStock_Acct', Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ' WHERE AD_Element_ID=53726 AND IsCentrallyMaintained='Y'
;

-- Jan 7, 2009 1:32:53 PM ECT
-- Manufacturing Cost
UPDATE AD_Field SET Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Policy  defined as No are acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53726) AND IsCentrallyMaintained='Y'
;

-- Jan 7, 2009 1:35:02 PM ECT
-- Manufacturing Cost
UPDATE AD_Field SET Help='The Floor Stock is used for accounting the component with Issue Method  is set Floor Stock  into Bill of Material & Formula Window.

The components with Issue Method  defined as Floor stock is acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ',Updated=TO_TIMESTAMP('2009-01-07 13:35:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Field_ID=56532
;

-- Jan 7, 2009 1:35:02 PM ECT
-- Manufacturing Cost
UPDATE AD_Field_Trl SET IsTranslated='N' WHERE AD_Field_ID=56532
;

-- Jan 7, 2009 1:35:43 PM ECT
-- Manufacturing Cost
UPDATE AD_Element SET Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Method  defined as Floor stock is acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ',Updated=TO_TIMESTAMP('2009-01-07 13:35:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Element_ID=53726
;

-- Jan 7, 2009 1:35:43 PM ECT
-- Manufacturing Cost
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53726
;

-- Jan 7, 2009 1:35:43 PM ECT
-- Manufacturing Cost
UPDATE AD_Column SET ColumnName='P_FloorStock_Acct', Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Method  defined as Floor stock is acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ' WHERE AD_Element_ID=53726
;

-- Jan 7, 2009 1:35:43 PM ECT
-- Manufacturing Cost
UPDATE AD_Process_Para SET ColumnName='P_FloorStock_Acct', Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Method  defined as Floor stock is acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ', AD_Element_ID=53726 WHERE UPPER(ColumnName)='P_FLOORSTOCK_ACCT' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- Jan 7, 2009 1:35:43 PM ECT
-- Manufacturing Cost
UPDATE AD_Process_Para SET ColumnName='P_FloorStock_Acct', Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Method  defined as Floor stock is acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ' WHERE AD_Element_ID=53726 AND IsCentrallyMaintained='Y'
;

-- Jan 7, 2009 1:35:43 PM ECT
-- Manufacturing Cost
UPDATE AD_Field SET Name='Floor Stock', Description='The Floor Stock account is the account used Manufacturing Order', Help='The Floor Stock is used for accounting the component with Issue method  is set Floor stock  into Bill of Material & Formula Window.

The components with Issue Method  defined as Floor stock is acounting next way:

Debit Floor Stock Account
Credit Work in Process Account ' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53726) AND IsCentrallyMaintained='Y'
;

