-- Jun 17, 2013 3:54:41 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2013-06-17 15:54:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=50195
;

-- Jun 17, 2013 3:54:46 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE AD_SysConfig MODIFY Name NVARCHAR2(255)
;

