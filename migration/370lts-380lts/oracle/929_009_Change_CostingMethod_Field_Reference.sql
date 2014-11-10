SET DEFINE OFF
SET SQLBLANKLINES ON
SET SCAN OFF

-- Jul 2, 2010 4:57:59 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET AD_Reference_ID=17, AD_Reference_Value_ID=122,Updated=TO_DATE('2010-07-02 16:57:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=59583
;

-- Jul 2, 2010 4:58:08 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE M_CostDetail MODIFY CostingMethod CHAR(1) DEFAULT NULL 
;

