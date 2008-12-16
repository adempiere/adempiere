-- Jan 21, 2008 11:13:10 PM COT
-- 1876984 - Make payment numbering configurable
INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50011,'C',TO_TIMESTAMP('2008-01-21 23:13:07','YYYY-MM-DD HH24:MI:SS'),100,'Y/N - Define if the payment document number must be overwritten with the check number','D','Y','PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CHECK_ON_PAYMENT',TO_TIMESTAMP('2008-01-21 23:13:07','YYYY-MM-DD HH24:MI:SS'),100,'Y')
;

INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50012,'C',TO_TIMESTAMP('2008-01-21 23:13:30','YYYY-MM-DD HH24:MI:SS'),100,'Y/N - Define if the payment document number must be overwritten with the credit card','D','Y','PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CREDIT_CARD',TO_TIMESTAMP('2008-01-21 23:13:30','YYYY-MM-DD HH24:MI:SS'),100,'Y')
;

INSERT INTO AD_SysConfig (AD_Client_ID,AD_Org_ID,AD_SysConfig_ID,ConfigurationLevel,Created,CreatedBy,Description,EntityType,IsActive,Name,Updated,UpdatedBy,Value) VALUES (0,0,50013,'C',TO_TIMESTAMP('2008-01-21 23:19:48','YYYY-MM-DD HH24:MI:SS'),100,'Y/N - Define if the payment (receipt) document number must be overwritten with the check number','D','Y','PAYMENT_OVERWRITE_DOCUMENTNO_WITH_CHECK_ON_RECEIPT',TO_TIMESTAMP('2008-01-21 23:19:48','YYYY-MM-DD HH24:MI:SS'),100,'Y')
;
