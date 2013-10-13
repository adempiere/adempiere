-- Jun 17, 2013 4:59:51 PM EEST
-- I forgot to set the DICTIONARY_ID_COMMENTS System Configurator
INSERT INTO AD_EntityType (AD_Client_ID,AD_EntityType_ID,AD_Org_ID,Created,CreatedBy,Description,EntityType,Help,IsActive,ModelPackage,Name,Processing,Updated,UpdatedBy) VALUES (0,50020,0,TO_DATE('2013-06-17 16:59:50','YYYY-MM-DD HH24:MI:SS'),100,'Adempiere XML Migration module','org.adempiere.ad.migration','See http://www.adempiere.com/AD_Migration','Y','org.adempiere.ad.migration.model','org.adempiere.ad.migration','N',TO_DATE('2013-06-17 16:59:50','YYYY-MM-DD HH24:MI:SS'),100)
;





update AD_Table set EntityType='org.adempiere.ad.migration'
where TableName in ('AD_Migration', 'AD_MigrationStep', 'AD_MigrationData')
;

update AD_Column c set EntityType='org.adempiere.ad.migration'
where exists (
	select 1 from AD_Table t
	where t.TableName in ('AD_Migration', 'AD_MigrationStep', 'AD_MigrationData')
		and t.AD_Table_ID=c.AD_Table_ID)
;


update AD_Window set EntityType='org.adempiere.ad.migration'
where AD_Window_ID=53084
;

update AD_Menu set EntityType='org.adempiere.ad.migration'
where AD_Window_ID=53084
;

update AD_Tab set EntityType='org.adempiere.ad.migration'
where AD_Window_ID=53084
;

update AD_Field f set EntityType='org.adempiere.ad.migration'
where exists (select 1 from AD_Tab tt where tt.AD_Tab_ID=f.AD_Tab_ID and tt.AD_Window_ID=53084)
;

update AD_Process set EntityType='org.adempiere.ad.migration'
where AD_Process_ID in (53173, 53172, 53175, 53388, 53174)
;

update AD_Process_Para set EntityType='org.adempiere.ad.migration'
where AD_Process_ID in (53173, 53172, 53175, 53388, 53174)
;

update AD_Menu set EntityType='org.adempiere.ad.migration'
where AD_Process_ID in (53173, 53172, 53175, 53388, 53174)
;
