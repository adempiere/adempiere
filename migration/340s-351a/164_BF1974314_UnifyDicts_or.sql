-- May 27, 2008 10:09:08 PM COT
-- [ 1974314 ] Difference in oracle and postgres seeds
UPDATE AD_Element SET Help='Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter', Name='Configuration Level', PrintName='Configuration Level for this parameter',Updated=TO_DATE('2008-05-27 22:09:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Element_ID=53229
;

-- May 27, 2008 10:09:08 PM COT
UPDATE AD_Element_Trl SET IsTranslated='N' WHERE AD_Element_ID=53229
;

-- May 27, 2008 10:09:08 PM COT
UPDATE AD_Column SET ColumnName='ConfigurationLevel', Name='Configuration Level', Description='Configuration Level for this parameter', Help='Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter' WHERE AD_Element_ID=53229
;

-- May 27, 2008 10:09:08 PM COT
UPDATE AD_Field SET Name='Configuration Level', Description='Configuration Level for this parameter', Help='Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter' WHERE AD_Column_ID IN (SELECT AD_Column_ID FROM AD_Column WHERE AD_Element_ID=53229) AND IsCentrallyMaintained='Y'
;

-- May 27, 2008 10:09:09 PM COT
UPDATE AD_Process_Para SET ColumnName='ConfigurationLevel', Name='Configuration Level', Description='Configuration Level for this parameter', Help='Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter', AD_Element_ID=53229 WHERE UPPER(ColumnName)='CONFIGURATIONLEVEL' AND IsCentrallyMaintained='Y' AND AD_Element_ID IS NULL
;

-- May 27, 2008 10:09:09 PM COT
UPDATE AD_Process_Para SET ColumnName='ConfigurationLevel', Name='Configuration Level', Description='Configuration Level for this parameter', Help='Configuration Level for this parameter
S - just allowed system configuration
C - client configurable parameter
O - org configurable parameter' WHERE AD_Element_ID=53229 AND IsCentrallyMaintained='Y'
;

-- May 27, 2008 10:09:09 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Configuration Level for this parameter', Name='Configuration Level' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53229)
;

-- May 27, 2008 10:09:09 PM COT
UPDATE AD_PrintFormatItem pi SET PrintName='Configuration Level for this parameter', Name='Configuration Level' WHERE IsCentrallyMaintained='Y' AND EXISTS (SELECT * FROM AD_Column c WHERE c.AD_Column_ID=pi.AD_Column_ID AND c.AD_Element_ID=53229)
;

-- May 27, 2008 10:13:38 PM COT
UPDATE AD_Message SET MsgTip='The program assumes build version {0}, but database has build version {1}. 
This is likely to cause hard to fix errors.
Please contact administrator.',Updated=TO_DATE('2008-05-27 22:13:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Message_ID=53025
;

-- May 27, 2008 10:13:38 PM COT
UPDATE AD_Message_Trl SET IsTranslated='N' WHERE AD_Message_ID=53025
;

UPDATE AD_PROCESS_PARA
   SET name = 'Purchase Price List Version', iscentrallymaintained='Y'
 WHERE ad_process_para_id = 53025;

UPDATE AD_PROCESS_PARA
   SET name = 'Generate Fields', iscentrallymaintained='Y'
 WHERE ad_process_para_id = 53127;

UPDATE AD_PROCESS_PARA
   SET name = 'Scripts Path', iscentrallymaintained='Y'
 WHERE ad_process_para_id = 53131;

UPDATE AD_PROCESS SET procedurename = null, classname = 'org.compiere.process.M_PriceList_Create'
where ad_process_id = 103;

UPDATE AD_PROCESS SET procedurename = null, classname = 'org.compiere.process.M_Product_BOM_Check'
where ad_process_id = 136;

UPDATE AD_PROCESS SET procedurename = null, classname = 'org.compiere.process.M_Production_Run'
where ad_process_id = 137;

UPDATE AD_PROCESS SET procedurename = null, classname = 'org.compiere.process.AD_PrintPaper_Default'
where ad_process_id = 191;

DROP PROCEDURE M_PriceList_Create;

DROP PROCEDURE M_Product_BOM_Check;

DROP PROCEDURE M_Production_Run;

DROP PROCEDURE AD_PrintPaper_Default;