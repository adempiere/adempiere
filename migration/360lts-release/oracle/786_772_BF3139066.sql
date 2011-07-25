-- Dec 17, 2010 2:44:56 PM COT
-- BF3139066-False Mandary Logic for C_BPartner_Product/IsManufacturer
UPDATE AD_Column SET MandatoryLogic=NULL, DefaultValue='N',Updated=TO_DATE('2010-12-17 14:44:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=58799
;

-- Dec 17, 2010 2:44:59 PM COT
ALTER TABLE C_BPartner_Product MODIFY IsManufacturer CHAR(1) DEFAULT 'N'
;

-- Dec 17, 2010 2:44:59 PM COT
UPDATE C_BPartner_Product SET IsManufacturer='N' WHERE IsManufacturer IS NULL
;

