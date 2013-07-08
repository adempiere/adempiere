-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
CREATE TABLE C_Dunning_Candidate (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, AD_Table_ID NUMERIC(10) NOT NULL, C_BPartner_ID NUMERIC(10) NOT NULL, C_BPartner_Location_ID NUMERIC(10) NOT NULL, C_Currency_ID NUMERIC(10) NOT NULL, C_Dunning_Candidate_ID NUMERIC(10) NOT NULL, C_Dunning_Contact_ID NUMERIC(10) DEFAULT NULL , C_DunningLevel_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, DaysDue NUMERIC(10) DEFAULT NULL , DueDate TIMESTAMP NOT NULL, DunningDate TIMESTAMP NOT NULL, DunningDateEffective TIMESTAMP DEFAULT NULL , DunningGrace TIMESTAMP DEFAULT NULL , DunningInterestAmt NUMERIC DEFAULT NULL , FeeAmt NUMERIC DEFAULT NULL , IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, IsDunningDocProcessed CHAR(1) DEFAULT NULL CHECK (IsDunningDocProcessed IN ('Y','N')), IsWriteOff CHAR(1) DEFAULT 'N' CHECK (IsWriteOff IN ('Y','N')) NOT NULL, OpenAmt NUMERIC DEFAULT NULL , Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N')) NOT NULL, Record_ID NUMERIC(10) NOT NULL, TotalAmt NUMERIC DEFAULT NULL , Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT C_Dunning_Candidate_Key PRIMARY KEY (C_Dunning_Candidate_ID))
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','Created','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','AD_Client_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','UpdatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','IsActive','CHAR(1)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','Updated','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','CreatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','C_Dunning_Candidate_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','AD_Table_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','Record_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','C_BPartner_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','DaysDue','NUMERIC(10)',null,'NULL')
;

-- Jul 5, 2013 3:27:56 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','OpenAmt','NUMERIC',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','TotalAmt','NUMERIC',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','FeeAmt','NUMERIC',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','DunningInterestAmt','NUMERIC',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','C_Dunning_Contact_ID','NUMERIC(10)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','Processed','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_Dunning_Candidate SET Processed='N' WHERE Processed IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_Dunning ADD COLUMN C_Currency_ID NUMERIC(10) DEFAULT NULL 
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','C_DunningLevel_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','DunningDate','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','DueDate','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
CREATE TABLE C_DunningDoc (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, BPartnerAddress VARCHAR(360) DEFAULT NULL , C_BPartner_ID NUMERIC(10) NOT NULL, C_BPartner_Location_ID NUMERIC(10) NOT NULL, C_Dunning_Contact_ID NUMERIC(10) DEFAULT NULL , C_DunningDoc_ID NUMERIC(10) NOT NULL, C_DunningLevel_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, Description VARCHAR(2000) DEFAULT NULL , DocumentNo VARCHAR(255) NOT NULL, DunningDate TIMESTAMP NOT NULL, IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, IsUseBPartnerAddress CHAR(1) DEFAULT 'N' CHECK (IsUseBPartnerAddress IN ('Y','N')) NOT NULL, Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N')) NOT NULL, Processing CHAR(1) DEFAULT NULL , Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT C_DunningDoc_Key PRIMARY KEY (C_DunningDoc_ID))
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','Created','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','AD_Org_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','IsActive','CHAR(1)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','CreatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','Updated','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','UpdatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','C_DunningDoc_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','DocumentNo','VARCHAR(255)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','C_DunningLevel_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','DunningDate','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','Processed','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_DunningDoc SET Processed='N' WHERE Processed IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','Description','VARCHAR(2000)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
CREATE TABLE C_DunningDoc_Line (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, Amt NUMERIC NOT NULL, C_BPartner_ID NUMERIC(10) NOT NULL, C_Currency_ID NUMERIC(10) NOT NULL, C_Dunning_Contact_ID NUMERIC(10) DEFAULT NULL , C_DunningDoc_ID NUMERIC(10) NOT NULL, C_DunningDoc_Line_ID NUMERIC(10) NOT NULL, C_DunningLevel_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) DEFAULT 'Y' CHECK (IsActive IN ('Y','N')) NOT NULL, Note VARCHAR(2000) DEFAULT NULL , Processed CHAR(1) CHECK (Processed IN ('Y','N')) NOT NULL, SalesRep_ID NUMERIC(10) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT C_DunningDoc_Line_Key PRIMARY KEY (C_DunningDoc_Line_ID))
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','AD_Org_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','Amt','NUMERIC',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','C_BPartner_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','C_Currency_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','C_DunningDoc_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','C_DunningLevel_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','C_DunningDoc_Line_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','CreatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','Created','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','IsActive','CHAR(1)',null,'Y')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_DunningDoc_Line SET IsActive='Y' WHERE IsActive IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','Note','VARCHAR(2000)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','Processed','CHAR(1)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','SalesRep_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','UpdatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','Updated','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line','C_Dunning_Contact_ID','NUMERIC(10)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
CREATE TABLE C_DunningDoc_Line_Source (AD_Client_ID NUMERIC(10) NOT NULL, AD_Org_ID NUMERIC(10) NOT NULL, C_Dunning_Candidate_ID NUMERIC(10) NOT NULL, C_DunningDoc_Line_ID NUMERIC(10) NOT NULL, C_DunningDoc_Line_Source_ID NUMERIC(10) NOT NULL, Created TIMESTAMP NOT NULL, CreatedBy NUMERIC(10) NOT NULL, IsActive CHAR(1) CHECK (IsActive IN ('Y','N')) NOT NULL, IsWriteOff CHAR(1) DEFAULT 'N' CHECK (IsWriteOff IN ('Y','N')) NOT NULL, IsWriteOffApplied CHAR(1) DEFAULT 'N' CHECK (IsWriteOffApplied IN ('Y','N')) NOT NULL, Processed CHAR(1) DEFAULT 'N' CHECK (Processed IN ('Y','N')) NOT NULL, Updated TIMESTAMP NOT NULL, UpdatedBy NUMERIC(10) NOT NULL, CONSTRAINT C_DunningDoc_Line_Source_Key PRIMARY KEY (C_DunningDoc_Line_Source_ID))
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','AD_Client_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','CreatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','Created','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','IsActive','CHAR(1)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','Updated','TIMESTAMP',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','C_DunningDoc_Line_Source_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','UpdatedBy','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','C_DunningDoc_Line_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','C_Dunning_Candidate_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','C_Currency_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','C_BPartner_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','C_BPartner_Location_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','BPartnerAddress','VARCHAR(360)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','C_Dunning_Contact_ID','NUMERIC(10)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','C_BPartner_Location_ID','NUMERIC(10)',null,null)
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','DunningGrace','TIMESTAMP',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','Processing','CHAR(1)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','IsDunningDocProcessed','CHAR(1)',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_Dunning ADD COLUMN DunningTimer CHAR(1) DEFAULT 'S' NOT NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_Dunning ADD COLUMN GraceDays NUMERIC(10) DEFAULT NULL 
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
ALTER TABLE C_DunningLevel ADD COLUMN IsWriteOff CHAR(1) DEFAULT 'N' CHECK (IsWriteOff IN ('Y','N')) NOT NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','IsWriteOff','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_Dunning_Candidate SET IsWriteOff='N' WHERE IsWriteOff IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunning_candidate','DunningDateEffective','TIMESTAMP',null,'NULL')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','IsWriteOff','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_DunningDoc_Line_Source SET IsWriteOff='N' WHERE IsWriteOff IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','IsWriteOffApplied','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_DunningDoc_Line_Source SET IsWriteOffApplied='N' WHERE IsWriteOffApplied IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc_line_source','Processed','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_DunningDoc_Line_Source SET Processed='N' WHERE Processed IS NULL
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO t_alter_column values('c_dunningdoc','IsUseBPartnerAddress','CHAR(1)',null,'N')
;

-- Jul 5, 2013 3:27:57 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE C_DunningDoc SET IsUseBPartnerAddress='N' WHERE IsUseBPartnerAddress IS NULL
;

