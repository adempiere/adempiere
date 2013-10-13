-- Link AD_Migration tables to Migration window
-- 26.06.2012 22:48:39 OESZ
UPDATE AD_Table SET AD_Window_ID=53084,Updated=TO_DATE('2012-06-26 22:48:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53217
;
UPDATE AD_Table SET AD_Window_ID=53084,Updated=TO_DATE('2012-06-26 22:48:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53219
;
UPDATE AD_Table SET AD_Window_ID=53019,Updated=TO_DATE('2012-06-26 22:48:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53064
;


-- Set AD_Process.Statistic_Count and Statistic_Seconds to IsCalculated=Y
-- 26.06.2012 23:09:52 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
--UPDATE AD_Column SET IsCalculated='Y',Updated=TO_DATE('2012-06-26 23:09:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6652
--;
--UPDATE AD_Column SET IsCalculated='Y',Updated=TO_DATE('2012-06-26 23:10:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=6653
--;

-- Missing FKs
ALTER TABLE AD_MigrationStep ADD CONSTRAINT ADMigration_ADMigrationStep FOREIGN KEY (AD_Migration_ID) REFERENCES AD_Migration DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE AD_MigrationData ADD CONSTRAINT ADMigrationStep_ADMigrationDat FOREIGN KEY (AD_MigrationStep_ID) REFERENCES AD_MigrationStep DEFERRABLE INITIALLY DEFERRED;
--ALTER TABLE AD_MigrationData ADD CONSTRAINT ADColumn_ADMigrationData FOREIGN KEY (AD_Column_ID) REFERENCES AD_Column DEFERRABLE INITIALLY DEFERRED;







-- Default column identifiers (need for multi-delete):
-- 26.06.2012 23:52:38 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=10,Updated=TO_DATE('2012-06-26 23:52:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57881
;

-- 26.06.2012 23:52:58 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=20,Updated=TO_DATE('2012-06-26 23:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57893
;

-- 26.06.2012 23:53:29 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=10,Updated=TO_DATE('2012-06-26 23:53:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57861
;

-- 26.06.2012 23:53:42 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=20,Updated=TO_DATE('2012-06-26 23:53:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57875
;

-- 26.06.2012 23:54:02 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=1,Updated=TO_DATE('2012-06-26 23:54:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57853
;

-- 26.06.2012 23:54:22 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=20,Updated=TO_DATE('2012-06-26 23:54:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57850
;

-- 26.06.2012 23:56:43 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='N',Updated=TO_DATE('2012-06-26 23:56:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57850
;

-- 26.06.2012 23:57:42 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET SeqNo=30,Updated=TO_DATE('2012-06-26 23:57:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57893
;

-- 26.06.2012 23:57:48 OESZ
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
UPDATE AD_Column SET IsIdentifier='Y', SeqNo=20,Updated=TO_DATE('2012-06-26 23:57:48','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=57891
;

