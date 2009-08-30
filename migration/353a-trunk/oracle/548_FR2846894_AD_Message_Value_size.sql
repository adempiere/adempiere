-- Aug 30, 2009 11:39:23 AM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET FieldLength=255,Updated=TO_DATE('2009-08-30 11:39:23','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=6766
;

-- Aug 30, 2009 11:39:38 AM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE AD_Message MODIFY Value NVARCHAR2(255)
;

