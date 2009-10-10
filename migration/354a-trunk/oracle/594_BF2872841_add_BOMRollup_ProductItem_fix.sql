--
-- If you already run script 592, this script will fix issues introduced, else this script does not have any effect
delete from AD_process_para_trl t
where exists (
	select 1 from AD_Process_para pp
	where pp.AD_process_ID=53062 and pp.ColumnName='ProductType' and pp.AD_Process_para_ID=t.AD_Process_para_ID
	and pp.AD_Process_para_ID=1000005
)
;
update AD_process_para set AD_process_para_ID=53361
where AD_process_ID=53062 and ColumnName='ProductType' and AD_Process_para_ID=1000005
;
INSERT INTO AD_Process_Para_Trl (AD_Language,AD_Process_Para_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy)
SELECT l.AD_Language,t.AD_Process_Para_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy
FROM AD_Language l, AD_Process_Para t
WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Process_Para_ID=53361
AND NOT EXISTS (SELECT 1 FROM AD_Process_Para_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Process_Para_ID=t.AD_Process_Para_ID)
;
