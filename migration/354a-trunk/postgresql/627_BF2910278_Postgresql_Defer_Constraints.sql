-- drop constraints defined as non-deferred

ALTER TABLE a_asset DROP CONSTRAINT aparentasset_aasset CASCADE;

ALTER TABLE a_asset DROP CONSTRAINT cbpartnersr_aasset CASCADE;

ALTER TABLE a_asset DROP CONSTRAINT cproject_aasset CASCADE;

ALTER TABLE a_asset DROP CONSTRAINT leasebpartner_aasset CASCADE;

ALTER TABLE a_asset DROP CONSTRAINT minoutline_aasset CASCADE;

ALTER TABLE a_asset_acct DROP CONSTRAINT aassetspread_aassetacct CASCADE;

ALTER TABLE a_asset_acct DROP CONSTRAINT adepreciationconv_aassetacct CASCADE;

ALTER TABLE a_asset_acct DROP CONSTRAINT adepreciationmethod_aassetacct CASCADE;

ALTER TABLE a_asset_acct DROP CONSTRAINT adepreciationtableheader_aasse CASCADE;

ALTER TABLE a_asset_addition DROP CONSTRAINT cinvoice_aassetaddition CASCADE;

ALTER TABLE a_asset_addition DROP CONSTRAINT gljournalbatch_aassetaddition CASCADE;

ALTER TABLE a_asset_change DROP CONSTRAINT adepreciationtableheader_aass2 CASCADE;

ALTER TABLE a_asset_change DROP CONSTRAINT aduser_aassetchange CASCADE;

ALTER TABLE a_asset_change DROP CONSTRAINT aparentasset_aassetchange CASCADE;

ALTER TABLE a_asset_change DROP CONSTRAINT cacctschema_aassetchange CASCADE;

ALTER TABLE a_asset_change DROP CONSTRAINT cbpartnerlocation_aassetchange CASCADE;

ALTER TABLE a_asset_change DROP CONSTRAINT clocation_aassetchange CASCADE;

ALTER TABLE a_asset_disposed DROP CONSTRAINT aassettrade_aassetdisposed CASCADE;

ALTER TABLE a_asset_disposed DROP CONSTRAINT cperiod_aassetdisposed CASCADE;

ALTER TABLE a_asset_group_acct DROP CONSTRAINT adepreciationtableheader_aass3 CASCADE;

ALTER TABLE a_asset_reval_entry DROP CONSTRAINT cacctschema_aassetrevalentry CASCADE;

ALTER TABLE a_asset_reval_entry DROP CONSTRAINT ccurrency_aassetrevalentry CASCADE;

ALTER TABLE a_asset_reval_entry DROP CONSTRAINT cdoctype_aassetrevalentry CASCADE;

ALTER TABLE a_asset_reval_entry DROP CONSTRAINT cperiod_aassetrevalentry CASCADE;

ALTER TABLE a_asset_reval_entry DROP CONSTRAINT glcategory_aassetrevalentry CASCADE;

ALTER TABLE a_asset_split DROP CONSTRAINT cperiod_aassetsplit CASCADE;

ALTER TABLE a_asset_transfer DROP CONSTRAINT cacctschema_aassettransfer CASCADE;

ALTER TABLE a_asset_transfer DROP CONSTRAINT cperiod_aassettransfer CASCADE;

ALTER TABLE a_depreciation_build DROP CONSTRAINT aendasset_adepreciationbuild CASCADE;

ALTER TABLE a_depreciation_build DROP CONSTRAINT astartasset_adepreciationbuild CASCADE;

ALTER TABLE a_depreciation_build DROP CONSTRAINT cperiod_adepreciationbuild CASCADE;

ALTER TABLE a_depreciation_entry DROP CONSTRAINT cacctschema_adepreciationentry CASCADE;

ALTER TABLE a_depreciation_entry DROP CONSTRAINT ccurrency_adepreciationentry CASCADE;

ALTER TABLE a_depreciation_entry DROP CONSTRAINT cdoctype_adepreciationentry CASCADE;

ALTER TABLE a_depreciation_entry DROP CONSTRAINT cperiod_adepreciationentry CASCADE;

ALTER TABLE a_depreciation_entry DROP CONSTRAINT glcategory_adepreciationentry CASCADE;

ALTER TABLE a_depreciation_forecast DROP CONSTRAINT aendasset_adepreciationforecas CASCADE;

ALTER TABLE a_depreciation_forecast DROP CONSTRAINT astartasset_adepreciationforec CASCADE;

ALTER TABLE ad_archive DROP CONSTRAINT cbpartner_adarchive CASCADE;

ALTER TABLE ad_client DROP CONSTRAINT adlangu_adclient CASCADE;

ALTER TABLE ad_client DROP CONSTRAINT adreplicationstrategy_adclient CASCADE;

ALTER TABLE ad_clientinfo DROP CONSTRAINT adtreeactivity_adclientinfo CASCADE;

ALTER TABLE ad_clientinfo DROP CONSTRAINT adtreecampaign_adclientinfo CASCADE;

ALTER TABLE ad_clientinfo DROP CONSTRAINT cbpartnercashtrx_adclientinfo CASCADE;

ALTER TABLE ad_clientinfo DROP CONSTRAINT mproductfreight_adclientinfo CASCADE;

ALTER TABLE ad_column DROP CONSTRAINT entityt_adcolumn CASCADE;

ALTER TABLE ad_document_action_access DROP CONSTRAINT adreflist_addocumentactionacce CASCADE;

ALTER TABLE ad_document_action_access DROP CONSTRAINT adrole_addocumentactionaccess CASCADE;

ALTER TABLE ad_document_action_access DROP CONSTRAINT cdoctype_addocumentactionacces CASCADE;

ALTER TABLE ad_element DROP CONSTRAINT entityt_adelement CASCADE;

ALTER TABLE ad_error DROP CONSTRAINT adlangu_aderror CASCADE;

ALTER TABLE ad_field DROP CONSTRAINT adreference_adfield CASCADE;

ALTER TABLE ad_field DROP CONSTRAINT adreferencevalue_adfield CASCADE;

ALTER TABLE ad_field DROP CONSTRAINT advalrule_adfield CASCADE;

ALTER TABLE ad_field DROP CONSTRAINT entityt_adfield CASCADE;

ALTER TABLE ad_field DROP CONSTRAINT includedtab_adfield CASCADE;

ALTER TABLE ad_fieldgroup DROP CONSTRAINT entityt_adfieldgroup CASCADE;

ALTER TABLE ad_form DROP CONSTRAINT entityt_adform CASCADE;

ALTER TABLE ad_housekeeping DROP CONSTRAINT adtable_adhousekeeping CASCADE;

ALTER TABLE ad_image DROP CONSTRAINT entityt_adimage CASCADE;

ALTER TABLE ad_infocolumn DROP CONSTRAINT entityt_adinfocolumn CASCADE;

ALTER TABLE ad_infowindow DROP CONSTRAINT entityt_adinfowindow CASCADE;

ALTER TABLE ad_menu DROP CONSTRAINT entityt_admenu CASCADE;

ALTER TABLE ad_message DROP CONSTRAINT entityt_admessage CASCADE;

ALTER TABLE ad_modelvalidator DROP CONSTRAINT entityt_admodelvalidator CASCADE;

ALTER TABLE ad_org DROP CONSTRAINT ad_org__ad_repli_ad_replica CASCADE;

ALTER TABLE ad_orginfo DROP CONSTRAINT cbank_adorginfo CASCADE;

ALTER TABLE ad_orginfo DROP CONSTRAINT ccalendar_adorginfo CASCADE;

ALTER TABLE ad_orginfo DROP CONSTRAINT ccashbook_adorginfo CASCADE;

ALTER TABLE ad_orginfo DROP CONSTRAINT dropshipwarehouse_adorginfo CASCADE;

ALTER TABLE ad_orgtype DROP CONSTRAINT adprintcolor_adorgtype CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adform_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adimpformat_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT admenu_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adprocess_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adreportview_adpackageexpcommo CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adrole_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adtable_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adwindow_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adworkbench_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_common DROP CONSTRAINT adworkflow_adpackageexpcommon CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adform_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adimpformat_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT admenu_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT admessage_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT admodval_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adprintformat_adpackageexpdeta CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adprocess_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adreference_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adreportview_adpackageexpdetai CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adrole_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adtable_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adwindow_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adworkbench_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_exp_detail DROP CONSTRAINT adworkflow_adpackageexpdetail CASCADE;

ALTER TABLE ad_package_imp_backup DROP CONSTRAINT adcolumn_adpackageimpbackup CASCADE;

ALTER TABLE ad_package_imp_backup DROP CONSTRAINT adreference_adpackageimpbackup CASCADE;

ALTER TABLE ad_printform DROP CONSTRAINT distribordermailtext_adprintfo CASCADE;

ALTER TABLE ad_printform DROP CONSTRAINT distriborderprintformat_adprin CASCADE;

ALTER TABLE ad_printform DROP CONSTRAINT manufordermailtext_adprintform CASCADE;

ALTER TABLE ad_printform DROP CONSTRAINT manuforderprintformat_adprintf CASCADE;

ALTER TABLE ad_printformat DROP CONSTRAINT jasperprocess_adprintformat CASCADE;

ALTER TABLE ad_printtableformat DROP CONSTRAINT adimage_adprinttableformat CASCADE;

ALTER TABLE ad_process DROP CONSTRAINT adform_adprocess CASCADE;

ALTER TABLE ad_process DROP CONSTRAINT adworkflow_adprocess CASCADE;

ALTER TABLE ad_process DROP CONSTRAINT entityt_adprocess CASCADE;

ALTER TABLE ad_process_para DROP CONSTRAINT entityt_adprocesspara CASCADE;

ALTER TABLE ad_ref_list DROP CONSTRAINT entityt_adreflist CASCADE;

ALTER TABLE ad_ref_table DROP CONSTRAINT adwindow_adreftable CASCADE;

ALTER TABLE ad_ref_table DROP CONSTRAINT entityt_adreftable CASCADE;

ALTER TABLE ad_reference DROP CONSTRAINT entityt_adreference CASCADE;

ALTER TABLE ad_registration DROP CONSTRAINT ccurrency_adregistration CASCADE;

ALTER TABLE ad_replication DROP CONSTRAINT remoteclient_adreplication CASCADE;

ALTER TABLE ad_replication DROP CONSTRAINT remoteorg_adreplication CASCADE;

ALTER TABLE ad_replicationdocument DROP CONSTRAINT adtable_adreplicationdocument CASCADE;

ALTER TABLE ad_replicationdocument DROP CONSTRAINT cdoctype_adreplicationdocument CASCADE;

ALTER TABLE ad_replicationstrategy DROP CONSTRAINT entityt_adreplicationstrategy CASCADE;

ALTER TABLE ad_replicationstrategy DROP CONSTRAINT expprocessor_adreplicationstra CASCADE;

ALTER TABLE ad_replicationtable DROP CONSTRAINT entityt_adreplicationtable CASCADE;

ALTER TABLE ad_reportview DROP CONSTRAINT entityt_adreportview CASCADE;

ALTER TABLE ad_role DROP CONSTRAINT adtreeorg_adrole CASCADE;

ALTER TABLE ad_rule DROP CONSTRAINT entityt_adrule CASCADE;

ALTER TABLE ad_searchdefinition DROP CONSTRAINT adcolumn_adsearchdefinition CASCADE;

ALTER TABLE ad_searchdefinition DROP CONSTRAINT adtable_adsearchdefinition CASCADE;

ALTER TABLE ad_searchdefinition DROP CONSTRAINT adwindow_adsearchdefinition CASCADE;

ALTER TABLE ad_searchdefinition DROP CONSTRAINT powindow_adsearchdefinition CASCADE;

ALTER TABLE ad_session DROP CONSTRAINT adrole_adsession CASCADE;

ALTER TABLE ad_sysconfig DROP CONSTRAINT entityt_adsysconfig CASCADE;

ALTER TABLE ad_tab DROP CONSTRAINT entityt_adtab CASCADE;

ALTER TABLE ad_tab DROP CONSTRAINT parentcolumn_adtab CASCADE;

ALTER TABLE ad_table DROP CONSTRAINT entityt_adtable CASCADE;

ALTER TABLE ad_table_scriptvalidator DROP CONSTRAINT adrule_adtablescriptvalidator CASCADE;

ALTER TABLE ad_table_scriptvalidator DROP CONSTRAINT adtable_adtablescriptvalidator CASCADE;

ALTER TABLE ad_task DROP CONSTRAINT entityt_adtask CASCADE;

ALTER TABLE ad_user DROP CONSTRAINT cjob_aduser CASCADE;

ALTER TABLE ad_userdef_win DROP CONSTRAINT adlangu_aduserdefwin CASCADE;

ALTER TABLE ad_userquery DROP CONSTRAINT adtab_aduserquery CASCADE;

ALTER TABLE ad_val_rule DROP CONSTRAINT entityt_advalrule CASCADE;

ALTER TABLE ad_wf_activity DROP CONSTRAINT adtable_adwfactivity CASCADE;

ALTER TABLE ad_wf_activity DROP CONSTRAINT adworkflow_adwfactivity CASCADE;

ALTER TABLE ad_wf_nextcondition DROP CONSTRAINT entityt_adwfnextcondition CASCADE;

ALTER TABLE ad_wf_node DROP CONSTRAINT adcolumn_adwfnode CASCADE;

ALTER TABLE ad_wf_node DROP CONSTRAINT cbpartner_adwfnode CASCADE;

ALTER TABLE ad_wf_node DROP CONSTRAINT entityt_adwfnode CASCADE;

ALTER TABLE ad_wf_node DROP CONSTRAINT rmailtext_adwfnode CASCADE;

ALTER TABLE ad_wf_node DROP CONSTRAINT sresource_adwfnode CASCADE;

ALTER TABLE ad_wf_node_para DROP CONSTRAINT entityt_adwfnodepara CASCADE;

ALTER TABLE ad_wf_nodenext DROP CONSTRAINT entityt_adwfnodenext CASCADE;

ALTER TABLE ad_wf_process DROP CONSTRAINT adtable_adwfprocess CASCADE;

ALTER TABLE ad_wf_process DROP CONSTRAINT adwfprocess_adwfprocess CASCADE;

ALTER TABLE ad_wf_responsible DROP CONSTRAINT entityt_adwfresponsible CASCADE;

ALTER TABLE ad_window DROP CONSTRAINT entityt_adwindow CASCADE;

ALTER TABLE ad_workbench DROP CONSTRAINT adcolumn_adworkbench CASCADE;

ALTER TABLE ad_workbench DROP CONSTRAINT entityt_adworkbench CASCADE;

ALTER TABLE ad_workbenchwindow DROP CONSTRAINT entityt_adworkbenchwindow CASCADE;

ALTER TABLE ad_workflow DROP CONSTRAINT adtable_adworkflow CASCADE;

ALTER TABLE ad_workflow DROP CONSTRAINT adwfnode_adworkflow CASCADE;

ALTER TABLE ad_workflow DROP CONSTRAINT entityt_adworkflow CASCADE;

ALTER TABLE ad_workflow DROP CONSTRAINT sresource_adworkflow CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adfield_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adform_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adprocess_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adprocesspara_aspclientexcepti CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adtab_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adtask_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adwfnode_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adwindow_aspclientexception CASCADE;

ALTER TABLE asp_clientexception DROP CONSTRAINT adworkflow_aspclientexception CASCADE;

ALTER TABLE asp_clientlevel DROP CONSTRAINT asplevel_aspclientlevel CASCADE;

ALTER TABLE asp_clientlevel DROP CONSTRAINT aspmodule_aspclientlevel CASCADE;

ALTER TABLE asp_field DROP CONSTRAINT adfield_aspfield CASCADE;

ALTER TABLE asp_field DROP CONSTRAINT asptab_aspfield CASCADE;

ALTER TABLE asp_form DROP CONSTRAINT adform_aspform CASCADE;

ALTER TABLE asp_form DROP CONSTRAINT asplevel_aspform CASCADE;

ALTER TABLE asp_level DROP CONSTRAINT aspmodule_asplevel CASCADE;

ALTER TABLE asp_process DROP CONSTRAINT adprocess_aspprocess CASCADE;

ALTER TABLE asp_process DROP CONSTRAINT asplevel_aspprocess CASCADE;

ALTER TABLE asp_process_para DROP CONSTRAINT adprocesspara_aspprocesspara CASCADE;

ALTER TABLE asp_process_para DROP CONSTRAINT aspprocess_aspprocesspara CASCADE;

ALTER TABLE asp_tab DROP CONSTRAINT adtab_asptab CASCADE;

ALTER TABLE asp_tab DROP CONSTRAINT aspwindow_asptab CASCADE;

ALTER TABLE asp_task DROP CONSTRAINT adtask_asptask CASCADE;

ALTER TABLE asp_task DROP CONSTRAINT asplevel_asptask CASCADE;

ALTER TABLE asp_window DROP CONSTRAINT adwindow_aspwindow CASCADE;

ALTER TABLE asp_window DROP CONSTRAINT asplevel_aspwindow CASCADE;

ALTER TABLE asp_workflow DROP CONSTRAINT adworkflow_aspworkflow CASCADE;

ALTER TABLE asp_workflow DROP CONSTRAINT asplevel_aspworkflow CASCADE;

ALTER TABLE b_bid DROP CONSTRAINT aduser_bbid CASCADE;

ALTER TABLE b_buyerfunds DROP CONSTRAINT aduser_bbuyerfunds CASCADE;

ALTER TABLE b_offer DROP CONSTRAINT aduser_boffer CASCADE;

ALTER TABLE b_sellerfunds DROP CONSTRAINT aduser_bsellerfunds CASCADE;

ALTER TABLE c_acctprocessor DROP CONSTRAINT adtable_cacctprocessor CASCADE;

ALTER TABLE c_acctprocessor DROP CONSTRAINT cacctschema_cacctprocessor CASCADE;

ALTER TABLE c_acctschema DROP CONSTRAINT adorgonly_cacctschema CASCADE;

ALTER TABLE c_acctschema_element DROP CONSTRAINT adcolumn_cacctschemaelement CASCADE;

ALTER TABLE c_bp_group DROP CONSTRAINT adprintcolor_cbpgroup CASCADE;

ALTER TABLE c_bp_group DROP CONSTRAINT cdunning_cbpgroup CASCADE;

ALTER TABLE c_bpartner DROP CONSTRAINT ctaxgroup_cbpartner CASCADE;

ALTER TABLE c_cashline DROP CONSTRAINT cpayment_ccashline CASCADE;

ALTER TABLE c_channel DROP CONSTRAINT adprintcolor_cchannel CASCADE;

ALTER TABLE c_charge DROP CONSTRAINT cbpartner_ccharge CASCADE;

ALTER TABLE c_charge DROP CONSTRAINT cchargetype_ccharge CASCADE;

ALTER TABLE c_charge_trl DROP CONSTRAINT adlangu_cchargetrl CASCADE;

ALTER TABLE c_charge_trl DROP CONSTRAINT ccharge_cchargetrl CASCADE;

ALTER TABLE c_chargetype_doctype DROP CONSTRAINT cchargetype_cchargetypedoctype CASCADE;

ALTER TABLE c_chargetype_doctype DROP CONSTRAINT cdoctype_cchargetypedoctype CASCADE;

ALTER TABLE c_country DROP CONSTRAINT ccountry_ccountry CASCADE;

ALTER TABLE c_doctype DROP CONSTRAINT cdoctypedifference_cdoctype CASCADE;

ALTER TABLE c_doctype DROP CONSTRAINT definitesequence_cdoctype CASCADE;

ALTER TABLE c_dunningrunentry DROP CONSTRAINT aduser_cdunningrunentry CASCADE;

ALTER TABLE c_dunningrunentry DROP CONSTRAINT cbpartnerlocation_cdunningrune CASCADE;

ALTER TABLE c_dunningrunentry DROP CONSTRAINT salesrep_cdunningrunentry CASCADE;

ALTER TABLE c_dunningrunline DROP CONSTRAINT cinvoicepayschedule_cdunningru CASCADE;

ALTER TABLE c_invoice DROP CONSTRAINT cdunninglevel_cinvoice CASCADE;

ALTER TABLE c_invoice DROP CONSTRAINT mrma_cinvoice CASCADE;

ALTER TABLE c_invoice DROP CONSTRAINT reversal_cinvoice CASCADE;

ALTER TABLE c_invoiceline DROP CONSTRAINT aassetgroup_cinvoiceline CASCADE;

ALTER TABLE c_invoiceline DROP CONSTRAINT mrmaline_cinvoiceline CASCADE;

ALTER TABLE c_order DROP CONSTRAINT cpos_corder CASCADE;

ALTER TABLE c_order DROP CONSTRAINT dropshipbpartner_corder CASCADE;

ALTER TABLE c_order DROP CONSTRAINT dropshiplocation_corder CASCADE;

ALTER TABLE c_order DROP CONSTRAINT dropshipuser_corder CASCADE;

ALTER TABLE c_order DROP CONSTRAINT linkorder_corder CASCADE;

ALTER TABLE c_order DROP CONSTRAINT mfreightcategory_order CASCADE;

ALTER TABLE c_orderline DROP CONSTRAINT linkorderline_corderline CASCADE;

ALTER TABLE c_orderline DROP CONSTRAINT mpromotion_corderline CASCADE;

ALTER TABLE c_orderline DROP CONSTRAINT ppcostcollector_corderline CASCADE;

ALTER TABLE c_payment DROP CONSTRAINT c_payment__c_cashbo_c_cashbook CASCADE;

ALTER TABLE c_payment DROP CONSTRAINT cinvoice_cpayment CASCADE;

ALTER TABLE c_payment DROP CONSTRAINT corder_cpayment CASCADE;

ALTER TABLE c_payment DROP CONSTRAINT refpayment_cpayment CASCADE;

ALTER TABLE c_payment DROP CONSTRAINT reversal_cpayment CASCADE;

ALTER TABLE c_payselectioncheck DROP CONSTRAINT cbpbankaccount_cpayselectionch CASCADE;

ALTER TABLE c_pos DROP CONSTRAINT cbankaccount_cpos CASCADE;

ALTER TABLE c_pos DROP CONSTRAINT cbpartnercashtrx_cpos CASCADE;

ALTER TABLE c_pos DROP CONSTRAINT cdoctype_cpos CASCADE;

ALTER TABLE c_poskey DROP CONSTRAINT adprintcolor_cposkey CASCADE;

ALTER TABLE c_project DROP CONSTRAINT cbpartnersr_cproject CASCADE;

ALTER TABLE c_salesregion DROP CONSTRAINT salesrep_csalesregion CASCADE;

ALTER TABLE c_tax DROP CONSTRAINT adrule_ctax CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT adorgtype_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT cbpartner_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT cbpgroup_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT ctax_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT ctaxbase_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT ctaxcategory_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT ctaxgroup_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT ctaxtype_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT mproduct_ctaxdefinition CASCADE;

ALTER TABLE c_taxdefinition DROP CONSTRAINT mproductcategory_ctaxdefinitio CASCADE;

ALTER TABLE c_uom_conversion DROP CONSTRAINT mproduct_cuomconversion CASCADE;

ALTER TABLE c_validcombination DROP CONSTRAINT csubacct_cvalidcombination CASCADE;

ALTER TABLE c_withholding DROP CONSTRAINT benefici_cwithholding CASCADE;

ALTER TABLE cm_newschannel DROP CONSTRAINT adlangu_cmnewschannel CASCADE;

ALTER TABLE dd_networkdistribution DROP CONSTRAINT mchangenotice_ddnetworkdistrib CASCADE;

ALTER TABLE dd_networkdistributionline DROP CONSTRAINT ddnetworkdistribution_ddnetwor CASCADE;

ALTER TABLE dd_networkdistributionline DROP CONSTRAINT mshipper_ddnetworkdistribution CASCADE;

ALTER TABLE dd_networkdistributionline DROP CONSTRAINT mwarehouse_ddnetworkdistributi CASCADE;

ALTER TABLE dd_networkdistributionline DROP CONSTRAINT mwarehousesource_ddnetworkdist CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT adorgtrx_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT aduser_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT cactivity_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT cbpartner_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT cbpartnerlocation_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT ccampaign_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT ccharge_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT cdoctype_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT cinvoice_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT corder_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT cproject_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT mshipper_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT mwarehouse_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT salesrep_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT user1_ddorder CASCADE;

ALTER TABLE dd_order DROP CONSTRAINT user2_ddorder CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT adorgtrx_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT cactivity_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT ccampaign_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT ccharge_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT cproject_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT cuom_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT ddorder_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT mlocator_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT mlocatorto_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT mproduct_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT user1_ddorderline CASCADE;

ALTER TABLE dd_orderline DROP CONSTRAINT user2_ddorderline CASCADE;

ALTER TABLE exp_format DROP CONSTRAINT adtable_expformat CASCADE;

ALTER TABLE exp_formatline DROP CONSTRAINT adcolumn_expformatline CASCADE;

ALTER TABLE exp_formatline DROP CONSTRAINT expembeddedformat_expformatlin CASCADE;

ALTER TABLE exp_formatline DROP CONSTRAINT expformat_expformatline CASCADE;

ALTER TABLE exp_processor DROP CONSTRAINT expprocessortype_expprocessor CASCADE;

ALTER TABLE exp_processorparameter DROP CONSTRAINT expprocessor_expprocessorparam CASCADE;

ALTER TABLE fact_acct DROP CONSTRAINT csubacct_factacct CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT account_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cacctschema_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cactivity_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cbpartner_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT ccampaign_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cperiod_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cproject_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cprojectphase_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT cprojecttask_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT csalesregion_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT csubacct_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT glbudget_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT mproduct_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT pareportcube_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT user1_factacctsummary CASCADE;

ALTER TABLE fact_acct_summary DROP CONSTRAINT user2_factacctsummary CASCADE;

ALTER TABLE gl_journal DROP CONSTRAINT reversal_gljournal CASCADE;

ALTER TABLE gl_journalbatch DROP CONSTRAINT reversal_gljournalbatch CASCADE;

ALTER TABLE gl_journalline DROP CONSTRAINT aasset_gljournalline CASCADE;

ALTER TABLE gl_journalline DROP CONSTRAINT aassetgroup_gljournalline CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT adrule_hrattribute CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT cbpartner_hrattribute CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT hrconcept_hrattribute CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT hrdepartment_hrattribute CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT hremployee_hrattribute CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT hrjob_hrattribute CASCADE;

ALTER TABLE hr_attribute DROP CONSTRAINT hrpayroll_hrattribute CASCADE;

ALTER TABLE hr_concept DROP CONSTRAINT adreference_hrconcept CASCADE;

ALTER TABLE hr_concept DROP CONSTRAINT hrconceptcategory_hrconcept CASCADE;

ALTER TABLE hr_concept DROP CONSTRAINT hrdepartment_hrconcept CASCADE;

ALTER TABLE hr_concept DROP CONSTRAINT hrjob_hrconcept CASCADE;

ALTER TABLE hr_concept DROP CONSTRAINT hrpayroll_hrconcept CASCADE;

ALTER TABLE hr_concept_acct DROP CONSTRAINT cacctschema_hrconceptacct CASCADE;

ALTER TABLE hr_concept_acct DROP CONSTRAINT cbpgroup_hrconceptacct CASCADE;

ALTER TABLE hr_concept_acct DROP CONSTRAINT hrconcept_hrconceptacct CASCADE;

ALTER TABLE hr_concept_acct DROP CONSTRAINT user1_hrconceptacct CASCADE;

ALTER TABLE hr_contract DROP CONSTRAINT cbpartner_hrcontract CASCADE;

ALTER TABLE hr_contract DROP CONSTRAINT ccampaign_hrcontract CASCADE;

ALTER TABLE hr_contract DROP CONSTRAINT cproject_hrcontract CASCADE;

ALTER TABLE hr_department DROP CONSTRAINT activity_hrdepartment CASCADE;

ALTER TABLE hr_employee DROP CONSTRAINT cactivity_hremployee CASCADE;

ALTER TABLE hr_employee DROP CONSTRAINT cbpartner_hremployee CASCADE;

ALTER TABLE hr_employee DROP CONSTRAINT hrdepartment_hremployee CASCADE;

ALTER TABLE hr_employee DROP CONSTRAINT hrjob_hremployee CASCADE;

ALTER TABLE hr_employee DROP CONSTRAINT hrpayroll_hremployee CASCADE;

ALTER TABLE hr_job DROP CONSTRAINT hrdepartment_hrjob CASCADE;

ALTER TABLE hr_job DROP CONSTRAINT nextjob_hrjob CASCADE;

ALTER TABLE hr_job DROP CONSTRAINT supervisor_hrjob CASCADE;

ALTER TABLE hr_list DROP CONSTRAINT hrdepartment_hrlist CASCADE;

ALTER TABLE hr_list DROP CONSTRAINT hremployee_hrlist CASCADE;

ALTER TABLE hr_list DROP CONSTRAINT hrlisttype_hrlist CASCADE;

ALTER TABLE hr_list DROP CONSTRAINT hrpayroll_hrlist CASCADE;

ALTER TABLE hr_listline DROP CONSTRAINT hrlistversion_hrlistline CASCADE;

ALTER TABLE hr_listversion DROP CONSTRAINT hrlist_hrlistversion CASCADE;

ALTER TABLE hr_listversion DROP CONSTRAINT hrlistbase_hrlistversion CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT adorgtrx_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT adrule_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT cactivity_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT cbpartner_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT ccampaign_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT cproject_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT cprojectphase_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT cprojecttask_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT hrconcept_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT hrconceptcategory_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT hrdepartment_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT hrjob_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT hrprocess_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT ppcostcollector_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT user1_hrmovement CASCADE;

ALTER TABLE hr_movement DROP CONSTRAINT user2_hrmovement CASCADE;

ALTER TABLE hr_payroll DROP CONSTRAINT adprintformat_hrpayroll CASCADE;

ALTER TABLE hr_payroll DROP CONSTRAINT ccharge_hrpayroll CASCADE;

ALTER TABLE hr_payroll DROP CONSTRAINT hrcontract_hrpayroll CASCADE;

ALTER TABLE hr_payrollconcept DROP CONSTRAINT adrule_hrpayrollconcept CASCADE;

ALTER TABLE hr_payrollconcept DROP CONSTRAINT hrconcept_hrpayrollconcept CASCADE;

ALTER TABLE hr_payrollconcept DROP CONSTRAINT hrpayroll_hrpayrollconcept CASCADE;

ALTER TABLE hr_period DROP CONSTRAINT cperiod_hrperiod CASCADE;

ALTER TABLE hr_period DROP CONSTRAINT cyear_hrperiod CASCADE;

ALTER TABLE hr_period DROP CONSTRAINT hrpayroll_hrperiod CASCADE;

ALTER TABLE hr_period DROP CONSTRAINT hryear_hrperiod CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT adprintformat_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT adworkflow_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT cbpartner_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT ccharge_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT cdoctype_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT cdoctypetarget_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT cpayselection_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT hrdepartment_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT hremployee_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT hrjob_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT hrpayroll_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT hrperiod_hrprocess CASCADE;

ALTER TABLE hr_process DROP CONSTRAINT reversal_hrprocess CASCADE;

ALTER TABLE hr_year DROP CONSTRAINT cyear_hryear CASCADE;

ALTER TABLE hr_year DROP CONSTRAINT hrpayroll_hryear CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT aassetgroup_iasset CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT adepreciationtableheader_iasse CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT cacctschema_iasset CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT cbpartner_iasset CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT cbpartnerlocation_iasset CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT clocation_iasset CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT mlocator_iasset CASCADE;

ALTER TABLE i_asset DROP CONSTRAINT mproduct_iasset CASCADE;

ALTER TABLE i_bpartner DROP CONSTRAINT rinterestarea_ibpartner CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT account_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT adorgdoc_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT adorgtrx_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cacctschema_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cactivity_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cbpartner_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT ccampaign_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT ccurrency_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cdoctype_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cperiod_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cproject_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT csalesregion_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cuom_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT cvalidcombination_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT glbudget_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT glcategory_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT gljournal_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT gljournalbatch_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT gljournalline_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT mproduct_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT user1_ifajournal CASCADE;

ALTER TABLE i_fajournal DROP CONSTRAINT user2_ifajournal CASCADE;

ALTER TABLE i_gljournal DROP CONSTRAINT cuom_igljournal CASCADE;

ALTER TABLE i_invoice DROP CONSTRAINT ccharge_iinvoice CASCADE;

ALTER TABLE i_order DROP CONSTRAINT ccharge_iorder CASCADE;

ALTER TABLE i_payment DROP CONSTRAINT ccurrency_ipayment CASCADE;

ALTER TABLE i_pricelist DROP CONSTRAINT cbpartner_ipricelist CASCADE;

ALTER TABLE i_pricelist DROP CONSTRAINT ccurrency_ipricelist CASCADE;

ALTER TABLE i_pricelist DROP CONSTRAINT cuom_ipricelist CASCADE;

ALTER TABLE i_pricelist DROP CONSTRAINT mpricelist_ipricelist CASCADE;

ALTER TABLE i_pricelist DROP CONSTRAINT mpricelistversion_ipricelist CASCADE;

ALTER TABLE i_pricelist DROP CONSTRAINT mproduct_ipricelist CASCADE;

ALTER TABLE imp_processor DROP CONSTRAINT impprocessortype_impprocessor CASCADE;

ALTER TABLE imp_processorlog DROP CONSTRAINT impprocessor_impprocessorlog CASCADE;

ALTER TABLE imp_processorparameter DROP CONSTRAINT impprocessor_impprocessorparam CASCADE;

ALTER TABLE k_synonym DROP CONSTRAINT adlangu_ksynonym CASCADE;

ALTER TABLE m_changerequest DROP CONSTRAINT mfixchangenotice_mchangereques CASCADE;

ALTER TABLE m_changerequest DROP CONSTRAINT ppproductbom_mchangerequest CASCADE;

ALTER TABLE m_costdetail DROP CONSTRAINT ppcostcollector_mcostdetail CASCADE;

ALTER TABLE m_forecast DROP CONSTRAINT mpricelist_mforecast CASCADE;

ALTER TABLE m_forecastline DROP CONSTRAINT mwarehouse_mforecastline CASCADE;

ALTER TABLE m_inout DROP CONSTRAINT cinvoice_minout CASCADE;

ALTER TABLE m_inout DROP CONSTRAINT dropshipbpartner_minout CASCADE;

ALTER TABLE m_inout DROP CONSTRAINT dropshiplocation_minout CASCADE;

ALTER TABLE m_inout DROP CONSTRAINT dropshipuser_minout CASCADE;

ALTER TABLE m_inout DROP CONSTRAINT mrma_minout CASCADE;

ALTER TABLE m_inout DROP CONSTRAINT reversal_minout CASCADE;

ALTER TABLE m_inoutconfirm DROP CONSTRAINT cinvoice_minoutconfirm CASCADE;

ALTER TABLE m_inoutconfirm DROP CONSTRAINT minventory_minoutconfirm CASCADE;

ALTER TABLE m_inoutline DROP CONSTRAINT mrmaline_minoutline CASCADE;

ALTER TABLE m_inoutline DROP CONSTRAINT reversalline_minoutline CASCADE;

ALTER TABLE m_inoutlineconfirm DROP CONSTRAINT cinvoiceline_minoutlineconfirm CASCADE;

ALTER TABLE m_inoutlineconfirm DROP CONSTRAINT minventoryline_minoutlineconfi CASCADE;

ALTER TABLE m_inventory DROP CONSTRAINT cdoctype_minventory CASCADE;

ALTER TABLE m_inventory DROP CONSTRAINT reversal_minventory CASCADE;

ALTER TABLE m_inventoryline DROP CONSTRAINT reversalline_minventoryline CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT aduser_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT cbpartner_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT ccharge_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT cdoctype_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT ddorder_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT mshipper_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT reversal_mmovement CASCADE;

ALTER TABLE m_movement DROP CONSTRAINT salesrep_mmovement CASCADE;

ALTER TABLE m_movementline DROP CONSTRAINT ddorderline_mmovementline CASCADE;

ALTER TABLE m_movementline DROP CONSTRAINT reversalline_mmovementline CASCADE;

ALTER TABLE m_pricelist_version DROP CONSTRAINT mpricelistversionbase_mpriceli CASCADE;

ALTER TABLE m_product DROP CONSTRAINT salesrep_mproduct CASCADE;

ALTER TABLE m_product_category DROP CONSTRAINT adprintcolor_mproductcategory CASCADE;

ALTER TABLE m_product_category DROP CONSTRAINT mproductcat_parentcat CASCADE;

ALTER TABLE m_productpricevendorbreak DROP CONSTRAINT cbpartner_mproductpricevendorb CASCADE;

ALTER TABLE m_productpricevendorbreak DROP CONSTRAINT mpricelistversion_mproductpric CASCADE;

ALTER TABLE m_productpricevendorbreak DROP CONSTRAINT mproduct_mproductpricevendorbr CASCADE;

ALTER TABLE m_promotion DROP CONSTRAINT ccampaign_mpromotion CASCADE;

ALTER TABLE m_promotiondistribution DROP CONSTRAINT mpromotion_mpromotiondistribut CASCADE;

ALTER TABLE m_promotiondistribution DROP CONSTRAINT mpromotionline_mpromotiondistr CASCADE;

ALTER TABLE m_promotiongroupline DROP CONSTRAINT mproduct_mpromotiongroupline CASCADE;

ALTER TABLE m_promotiongroupline DROP CONSTRAINT mpromotiongroup_mpromotiongrou CASCADE;

ALTER TABLE m_promotionline DROP CONSTRAINT mpromotion_mpromotionline CASCADE;

ALTER TABLE m_promotionline DROP CONSTRAINT mpromotiongroup_mpromotionline CASCADE;

ALTER TABLE m_promotionprecondition DROP CONSTRAINT cactivity_mpromotionpreconditi CASCADE;

ALTER TABLE m_promotionprecondition DROP CONSTRAINT cbpartner_mpromotionpreconditi CASCADE;

ALTER TABLE m_promotionprecondition DROP CONSTRAINT cbpgroup_mpromotionpreconditio CASCADE;

ALTER TABLE m_promotionprecondition DROP CONSTRAINT mpricelist_mpromotionprecondit CASCADE;

ALTER TABLE m_promotionprecondition DROP CONSTRAINT mpromotion_mpromotionprecondit CASCADE;

ALTER TABLE m_promotionprecondition DROP CONSTRAINT mwarehouse_mpromotionprecondit CASCADE;

ALTER TABLE m_promotionreward DROP CONSTRAINT ccharge_mpromotionreward CASCADE;

ALTER TABLE m_promotionreward DROP CONSTRAINT mpromotion_mpromotionreward CASCADE;

ALTER TABLE m_promotionreward DROP CONSTRAINT mpromotiondistribution_mpromot CASCADE;

ALTER TABLE m_promotionreward DROP CONSTRAINT mtargetdistribution_mpromotion CASCADE;

ALTER TABLE m_replenish DROP CONSTRAINT mlocator_mreplenish CASCADE;

ALTER TABLE m_replenish DROP CONSTRAINT mwarehousesource_mreplenish CASCADE;

ALTER TABLE m_requisition DROP CONSTRAINT cdoctype_mrequisition CASCADE;

ALTER TABLE m_requisitionline DROP CONSTRAINT cbpartner_mrequisitionline CASCADE;

ALTER TABLE m_requisitionline DROP CONSTRAINT ccharge_mrequisitionline CASCADE;

ALTER TABLE m_requisitionline DROP CONSTRAINT corderline_mrequisitionline CASCADE;

ALTER TABLE m_requisitionline DROP CONSTRAINT cuom_mrequisitionline CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT cbpartner_mrma CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT ccurrency_mrma CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT cdoctype_mrma CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT corder_mrma CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT mrmatype_mrma CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT refrma_mrma CASCADE;

ALTER TABLE m_rma DROP CONSTRAINT salesrep_mrma CASCADE;

ALTER TABLE m_rmaline DROP CONSTRAINT ccharge_mrmaline CASCADE;

ALTER TABLE m_rmaline DROP CONSTRAINT refrmaline_mrmaline CASCADE;

ALTER TABLE m_transaction DROP CONSTRAINT ppcostcollector_mtransaction CASCADE;

ALTER TABLE m_warehouse DROP CONSTRAINT mwarehousesource_mwarehouse CASCADE;

ALTER TABLE pa_colorschema DROP CONSTRAINT entityt_pacolorschema CASCADE;

ALTER TABLE pa_dashboardcontent DROP CONSTRAINT adwindow_padashboardcontent CASCADE;

ALTER TABLE pa_dashboardcontent DROP CONSTRAINT pagoal_padashboardcontent CASCADE;

ALTER TABLE pa_goal DROP CONSTRAINT adrole_pagoal CASCADE;

ALTER TABLE pa_measure DROP CONSTRAINT cprojecttype_pameasure CASCADE;

ALTER TABLE pa_measure DROP CONSTRAINT rrequesttype_pameasure CASCADE;

ALTER TABLE pa_measurecalc DROP CONSTRAINT adtable_pameasurecalc CASCADE;

ALTER TABLE pa_measurecalc DROP CONSTRAINT entityt_pameasurecalc CASCADE;

ALTER TABLE pa_report DROP CONSTRAINT jasperprocess_pareport CASCADE;

ALTER TABLE pa_report DROP CONSTRAINT pareportcube_pareport CASCADE;

ALTER TABLE pa_reportcolumn DROP CONSTRAINT adorgtrx_pareportcolumn CASCADE;

ALTER TABLE pa_reportcube DROP CONSTRAINT ccalendar_pareportcube CASCADE;

ALTER TABLE pa_reportsource DROP CONSTRAINT adorgtrx_pareportsource CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT adorgtrx_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT aduser_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT cactivity_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT ccampaign_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT cdoctype_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT cdoctypetarget_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT cproject_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT cuom_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT mproduct_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT mwarehouse_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT pporder_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT pporderbomline_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT ppordernode_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT pporderworkflow_ppcostcollecto CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT reversal_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT sresource_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT user1_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collector DROP CONSTRAINT user2_ppcostcollector CASCADE;

ALTER TABLE pp_cost_collectorma DROP CONSTRAINT ppcostcollector_ppcostcollectorma CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT cbpartner_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT corder_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT corderline_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT ddorder_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT ddorderline_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT mforecast_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT mforecastline_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT mproduct_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT mrequisition_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT mrequisitionline_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT mwarehouse_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT planner_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT pporder_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT pporderbomline_ppmrp CASCADE;

ALTER TABLE pp_mrp DROP CONSTRAINT sresource_ppmrp CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT adorgtrx_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT adworkflow_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT cactivity_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT ccampaign_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT cdoctype_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT cdoctypetarget_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT corderline_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT cproject_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT cuom_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT mproduct_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT mwarehouse_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT planner_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT ppproductbom_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT sresource_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT user1_pporder CASCADE;

ALTER TABLE pp_order DROP CONSTRAINT user2_pporder CASCADE;

ALTER TABLE pp_order_bom DROP CONSTRAINT cuom_pporderbom CASCADE;

ALTER TABLE pp_order_bom DROP CONSTRAINT mchangenotice_pporderbom CASCADE;

ALTER TABLE pp_order_bom DROP CONSTRAINT mproduct_pporderbom CASCADE;

ALTER TABLE pp_order_bom DROP CONSTRAINT pporder_pporderbom CASCADE;

ALTER TABLE pp_order_bom_trl DROP CONSTRAINT adlangu_pporderbomtrl CASCADE;

ALTER TABLE pp_order_bom_trl DROP CONSTRAINT pporderbom_pporderbomtrl CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT aduser_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT cuom_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT mchangenotice_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT mproduct_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT mwarehouse_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT pporder_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline DROP CONSTRAINT pporderbom_pporderbomline CASCADE;

ALTER TABLE pp_order_bomline_trl DROP CONSTRAINT adlangu_pporderbomlinetrl CASCADE;

ALTER TABLE pp_order_bomline_trl DROP CONSTRAINT pporderbomline_pporderbomlinet CASCADE;

ALTER TABLE pp_order_cost DROP CONSTRAINT adworkflow_ppordercost CASCADE;

ALTER TABLE pp_order_cost DROP CONSTRAINT cacctschema_ppordercost CASCADE;

ALTER TABLE pp_order_cost DROP CONSTRAINT mcostelement_ppordercost CASCADE;

ALTER TABLE pp_order_cost DROP CONSTRAINT mcosttype_ppordercost CASCADE;

ALTER TABLE pp_order_cost DROP CONSTRAINT mproduct_ppordercost CASCADE;

ALTER TABLE pp_order_cost DROP CONSTRAINT pporder_ppordercost CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adcolumn_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adform_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adimage_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adprocess_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adtask_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adwfblock_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adwfnode_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adwfresponsible_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adwindow_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT adworkflow_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT cbpartner_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT entityt_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT pporder_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT pporderworkflow_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT sresource_ppordernode CASCADE;

ALTER TABLE pp_order_node DROP CONSTRAINT workflow_ppordernode CASCADE;

ALTER TABLE pp_order_node_asset DROP CONSTRAINT aasset_ppordernodeasset CASCADE;

ALTER TABLE pp_order_node_asset DROP CONSTRAINT pporder_ppordernodeasset CASCADE;

ALTER TABLE pp_order_node_asset DROP CONSTRAINT ppordernode_ppordernodeasset CASCADE;

ALTER TABLE pp_order_node_asset DROP CONSTRAINT pporderworkflow_ppordernodeass CASCADE;

ALTER TABLE pp_order_node_product DROP CONSTRAINT mproduct_ppordernodeproduct CASCADE;

ALTER TABLE pp_order_node_product DROP CONSTRAINT pporder_ppordernodeproduct CASCADE;

ALTER TABLE pp_order_node_product DROP CONSTRAINT ppordernode_ppordernodeproduct CASCADE;

ALTER TABLE pp_order_node_product DROP CONSTRAINT pporderworkflow_ppordernodepro CASCADE;

ALTER TABLE pp_order_node_trl DROP CONSTRAINT adlangu_ppordernodetrl CASCADE;

ALTER TABLE pp_order_nodenext DROP CONSTRAINT adwfnext_ppordernodenext CASCADE;

ALTER TABLE pp_order_nodenext DROP CONSTRAINT adwfnode_ppordernodenext CASCADE;

ALTER TABLE pp_order_nodenext DROP CONSTRAINT entityt_ppordernodenext CASCADE;

ALTER TABLE pp_order_nodenext DROP CONSTRAINT pporder_ppordernodenext CASCADE;

ALTER TABLE pp_order_nodenext DROP CONSTRAINT ppordernext_ppordernodenext CASCADE;

ALTER TABLE pp_order_nodenext DROP CONSTRAINT ppordernode_ppordernodenext CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT adtable_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT adwfnode_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT adwfresponsible_pporderworkflo CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT adworkflow_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT adworkflowprocessor_pporderwor CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT entityt_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT pporder_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT ppordernode_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow DROP CONSTRAINT sresource_pporderworkflow CASCADE;

ALTER TABLE pp_order_workflow_trl DROP CONSTRAINT adlangu_pporderworkflowtrl CASCADE;

ALTER TABLE pp_order_workflow_trl DROP CONSTRAINT pporderworkflow_pporderworkflo CASCADE;

ALTER TABLE pp_product_bom DROP CONSTRAINT cuom_ppproductbom CASCADE;

ALTER TABLE pp_product_bom DROP CONSTRAINT mchangenotice_ppproductbom CASCADE;

ALTER TABLE pp_product_bom DROP CONSTRAINT mproduct_ppproductbom CASCADE;

ALTER TABLE pp_product_bom_trl DROP CONSTRAINT adlangu_ppproductbomtrl CASCADE;

ALTER TABLE pp_product_bom_trl DROP CONSTRAINT ppproductbom_ppproductbomtrl CASCADE;

ALTER TABLE pp_product_bomline DROP CONSTRAINT cuom_ppproductbomline CASCADE;

ALTER TABLE pp_product_bomline DROP CONSTRAINT mchangenotice_ppproductbomline CASCADE;

ALTER TABLE pp_product_bomline DROP CONSTRAINT mproduct_ppproductbomline CASCADE;

ALTER TABLE pp_product_bomline DROP CONSTRAINT ppproductbom_ppproductbomline CASCADE;

ALTER TABLE pp_product_bomline_trl DROP CONSTRAINT adlangu_ppproductbomlinetrl CASCADE;

ALTER TABLE pp_product_bomline_trl DROP CONSTRAINT ppproductbomline_ppproductboml CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT adworkflow_ppproductplanning CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT ddnetworkdistribution_ppproduc CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT mproduct_ppproductplanning CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT mwarehouse_ppproductplanning CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT planner_ppproductplanning CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT ppproductbom_ppproductplanning CASCADE;

ALTER TABLE pp_product_planning DROP CONSTRAINT sresource_ppproductplanning CASCADE;

ALTER TABLE pp_wf_node_asset DROP CONSTRAINT aasset_ppwfnodeasset CASCADE;

ALTER TABLE pp_wf_node_asset DROP CONSTRAINT adwfnode_ppwfnodeasset CASCADE;

ALTER TABLE pp_wf_node_product DROP CONSTRAINT adwfnode_ppwfnodeproduct CASCADE;

ALTER TABLE pp_wf_node_product DROP CONSTRAINT entityt_ppwfnodeproduct CASCADE;

ALTER TABLE pp_wf_node_product DROP CONSTRAINT mproduct_ppwfnodeproduct CASCADE;

ALTER TABLE qm_specification DROP CONSTRAINT adworkflow_qmspecification CASCADE;

ALTER TABLE qm_specification DROP CONSTRAINT mattributeset_qmspecification CASCADE;

ALTER TABLE qm_specification DROP CONSTRAINT mproduct_qmspecification CASCADE;

ALTER TABLE qm_specification DROP CONSTRAINT ppproductbom_qmspecification CASCADE;

ALTER TABLE qm_specificationline DROP CONSTRAINT mattribute_qmspecificationline CASCADE;

ALTER TABLE qm_specificationline DROP CONSTRAINT qmspecification_qmspecificatio CASCADE;

ALTER TABLE r_category DROP CONSTRAINT mproduct_rcategory CASCADE;

ALTER TABLE r_group DROP CONSTRAINT mbom_rgroup CASCADE;

ALTER TABLE r_group DROP CONSTRAINT mchangenotice_rgroup CASCADE;

ALTER TABLE r_group DROP CONSTRAINT ppproductbom_rgroup CASCADE;

ALTER TABLE r_issueknown DROP CONSTRAINT rissuerecommendation_rissuekno CASCADE;

ALTER TABLE r_issuesystem DROP CONSTRAINT aasset_rissuesystem CASCADE;

ALTER TABLE r_request DROP CONSTRAINT mchangerequest_rrequest CASCADE;

ALTER TABLE r_requestaction DROP CONSTRAINT mproductspent_rrequestaction CASCADE;

ALTER TABLE r_requestprocessor DROP CONSTRAINT rrequesttype_rrequestprocessor CASCADE;

ALTER TABLE r_requesttype DROP CONSTRAINT rstatuscategory_rrequesttype CASCADE;

ALTER TABLE r_requestupdate DROP CONSTRAINT mproductspent_rrequestupdate CASCADE;

ALTER TABLE r_status DROP CONSTRAINT nextstatus_rstatus CASCADE;

ALTER TABLE r_status DROP CONSTRAINT rstatuscategory_rstatus CASCADE;

ALTER TABLE r_status DROP CONSTRAINT updatestatus_rstatus CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT cardbankaccount_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT cardtransferbankaccount_uposte CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT cardtransfercashbook_upostermi CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT cashtransferbankaccount_uposte CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT cashtransfercashbook_upostermi CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT ccashbook_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT ccashbpartner_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT checkbankaccount_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT checktransferbankaccount_upost CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT checktransfercashbook_uposterm CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT ctemplatebpartner_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT mwarehouse_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT popricelist_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT salesrep_uposterminal CASCADE;

ALTER TABLE u_posterminal DROP CONSTRAINT sopricelist_uposterminal CASCADE;

ALTER TABLE u_rolemenu DROP CONSTRAINT adrole_urolemenu CASCADE;

ALTER TABLE u_rolemenu DROP CONSTRAINT uwebmenu_urolemenu CASCADE;

ALTER TABLE u_webmenu DROP CONSTRAINT parentmenu_uwebmenu CASCADE;

ALTER TABLE w_store DROP CONSTRAINT cpaymentterm_wstore CASCADE;

ALTER TABLE w_store DROP CONSTRAINT mpricelist_wstore CASCADE;

ALTER TABLE w_store DROP CONSTRAINT mwarehouse_wstore CASCADE;

ALTER TABLE w_store DROP CONSTRAINT salesrep_wstore CASCADE;



-- now re-add the same constraints with the clause DEFERRABLE INITIALLY DEFERRED

ALTER TABLE a_asset ADD CONSTRAINT aparentasset_aasset FOREIGN KEY(a_parent_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE a_asset ADD CONSTRAINT cbpartnersr_aasset FOREIGN KEY(c_bpartnersr_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE a_asset ADD CONSTRAINT cproject_aasset FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE a_asset ADD CONSTRAINT leasebpartner_aasset FOREIGN KEY(lease_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE a_asset ADD CONSTRAINT minoutline_aasset FOREIGN KEY(m_inoutline_id) REFERENCES m_inoutline DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE a_asset_acct ADD CONSTRAINT aassetspread_aassetacct FOREIGN KEY(a_asset_spread_id) REFERENCES a_asset_spread DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE a_asset_acct ADD CONSTRAINT adepreciationconv_aassetacct FOREIGN KEY(a_depreciation_conv_id) REFERENCES a_depreciation_convention DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_acct ADD CONSTRAINT adepreciationmethod_aassetacct FOREIGN KEY(a_depreciation_method_id) REFERENCES a_depreciation_method DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_acct ADD CONSTRAINT adepreciationtableheader_aasse FOREIGN KEY(a_depreciation_table_header_id) REFERENCES a_depreciation_table_header DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_addition ADD CONSTRAINT cinvoice_aassetaddition FOREIGN KEY(c_invoice_id) REFERENCES c_invoice DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE a_asset_addition ADD CONSTRAINT gljournalbatch_aassetaddition FOREIGN KEY(gl_journalbatch_id) REFERENCES gl_journalbatch DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_change ADD CONSTRAINT adepreciationtableheader_aass2 FOREIGN KEY(a_depreciation_table_header_id) REFERENCES a_depreciation_table_header DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_change ADD CONSTRAINT aduser_aassetchange FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE a_asset_change ADD CONSTRAINT aparentasset_aassetchange FOREIGN KEY(a_parent_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE a_asset_change ADD CONSTRAINT cacctschema_aassetchange FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE a_asset_change ADD CONSTRAINT cbpartnerlocation_aassetchange FOREIGN KEY(c_bpartner_location_id) REFERENCES c_bpartner_location DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_change ADD CONSTRAINT clocation_aassetchange FOREIGN KEY(c_location_id) REFERENCES c_location DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE a_asset_disposed ADD CONSTRAINT aassettrade_aassetdisposed FOREIGN KEY(a_asset_trade_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE a_asset_disposed ADD CONSTRAINT cperiod_aassetdisposed FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE a_asset_group_acct ADD CONSTRAINT adepreciationtableheader_aass3 FOREIGN KEY(a_depreciation_table_header_id) REFERENCES a_depreciation_table_header DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_reval_entry ADD CONSTRAINT cacctschema_aassetrevalentry FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_asset_reval_entry ADD CONSTRAINT ccurrency_aassetrevalentry FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE a_asset_reval_entry ADD CONSTRAINT cdoctype_aassetrevalentry FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE a_asset_reval_entry ADD CONSTRAINT cperiod_aassetrevalentry FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE a_asset_reval_entry ADD CONSTRAINT glcategory_aassetrevalentry FOREIGN KEY(gl_category_id) REFERENCES gl_category DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE a_asset_split ADD CONSTRAINT cperiod_aassetsplit FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE a_asset_transfer ADD CONSTRAINT cacctschema_aassettransfer FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE a_asset_transfer ADD CONSTRAINT cperiod_aassettransfer FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE a_depreciation_build ADD CONSTRAINT aendasset_adepreciationbuild FOREIGN KEY(a_end_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE a_depreciation_build ADD CONSTRAINT astartasset_adepreciationbuild FOREIGN KEY(a_start_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE a_depreciation_build ADD CONSTRAINT cperiod_adepreciationbuild FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE a_depreciation_entry ADD CONSTRAINT cacctschema_adepreciationentry FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_depreciation_entry ADD CONSTRAINT ccurrency_adepreciationentry FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE a_depreciation_entry ADD CONSTRAINT cdoctype_adepreciationentry FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE a_depreciation_entry ADD CONSTRAINT cperiod_adepreciationentry FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE a_depreciation_entry ADD CONSTRAINT glcategory_adepreciationentry FOREIGN KEY(gl_category_id) REFERENCES gl_category DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_depreciation_forecast ADD CONSTRAINT aendasset_adepreciationforecas FOREIGN KEY(a_end_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE a_depreciation_forecast ADD CONSTRAINT astartasset_adepreciationforec FOREIGN KEY(a_start_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_archive ADD CONSTRAINT cbpartner_adarchive FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE ad_client ADD CONSTRAINT adlangu_adclient FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE ad_client ADD CONSTRAINT adreplicationstrategy_adclient FOREIGN KEY(ad_replicationstrategy_id) REFERENCES ad_replicationstrategy DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_clientinfo ADD CONSTRAINT adtreeactivity_adclientinfo FOREIGN KEY(ad_tree_activity_id) REFERENCES ad_tree DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE ad_clientinfo ADD CONSTRAINT adtreecampaign_adclientinfo FOREIGN KEY(ad_tree_campaign_id) REFERENCES ad_tree DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE ad_clientinfo ADD CONSTRAINT cbpartnercashtrx_adclientinfo FOREIGN KEY(c_bpartnercashtrx_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE ad_clientinfo ADD CONSTRAINT mproductfreight_adclientinfo FOREIGN KEY(m_productfreight_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE ad_column ADD CONSTRAINT entityt_adcolumn FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE ad_document_action_access ADD CONSTRAINT adreflist_addocumentactionacce FOREIGN KEY(ad_ref_list_id) REFERENCES ad_ref_list DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_document_action_access ADD CONSTRAINT adrole_addocumentactionaccess FOREIGN KEY(ad_role_id) REFERENCES ad_role DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE ad_document_action_access ADD CONSTRAINT cdoctype_addocumentactionacces FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_element ADD CONSTRAINT entityt_adelement FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE ad_error ADD CONSTRAINT adlangu_aderror FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE ad_field ADD CONSTRAINT adreference_adfield FOREIGN KEY(ad_reference_id) REFERENCES ad_reference DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE ad_field ADD CONSTRAINT adreferencevalue_adfield FOREIGN KEY(ad_reference_value_id) REFERENCES ad_reference DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE ad_field ADD CONSTRAINT advalrule_adfield FOREIGN KEY(ad_val_rule_id) REFERENCES ad_val_rule DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE ad_field ADD CONSTRAINT entityt_adfield FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE ad_field ADD CONSTRAINT includedtab_adfield FOREIGN KEY(included_tab_id) REFERENCES ad_tab DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE ad_fieldgroup ADD CONSTRAINT entityt_adfieldgroup FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_form ADD CONSTRAINT entityt_adform FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE ad_housekeeping ADD CONSTRAINT adtable_adhousekeeping FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_image ADD CONSTRAINT entityt_adimage FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE ad_infocolumn ADD CONSTRAINT entityt_adinfocolumn FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_infowindow ADD CONSTRAINT entityt_adinfowindow FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_menu ADD CONSTRAINT entityt_admenu FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE ad_message ADD CONSTRAINT entityt_admessage FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE ad_modelvalidator ADD CONSTRAINT entityt_admodelvalidator FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE ad_org ADD CONSTRAINT ad_org__ad_repli_ad_replica FOREIGN KEY(ad_replicationstrategy_id) REFERENCES ad_replicationstrategy DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_orginfo ADD CONSTRAINT cbank_adorginfo FOREIGN KEY(transferbank_id) REFERENCES c_bank DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE ad_orginfo ADD CONSTRAINT ccalendar_adorginfo FOREIGN KEY(c_calendar_id) REFERENCES c_calendar DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE ad_orginfo ADD CONSTRAINT ccashbook_adorginfo FOREIGN KEY(transfercashbook_id) REFERENCES c_cashbook DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE ad_orginfo ADD CONSTRAINT dropshipwarehouse_adorginfo FOREIGN KEY(dropship_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE ad_orgtype ADD CONSTRAINT adprintcolor_adorgtype FOREIGN KEY(ad_printcolor_id) REFERENCES ad_printcolor DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adform_adpackageexpcommon FOREIGN KEY(ad_form_id) REFERENCES ad_form DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adimpformat_adpackageexpcommon FOREIGN KEY(ad_impformat_id) REFERENCES ad_impformat DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_common ADD CONSTRAINT admenu_adpackageexpcommon FOREIGN KEY(ad_menu_id) REFERENCES ad_menu DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adprocess_adpackageexpcommon FOREIGN KEY(ad_process_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adreportview_adpackageexpcommo FOREIGN KEY(ad_reportview_id) REFERENCES ad_reportview DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_common ADD CONSTRAINT adrole_adpackageexpcommon FOREIGN KEY(ad_role_id) REFERENCES ad_role DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adtable_adpackageexpcommon FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adwindow_adpackageexpcommon FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE ad_package_exp_common ADD CONSTRAINT adworkbench_adpackageexpcommon FOREIGN KEY(ad_workbench_id) REFERENCES ad_workbench DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_common ADD CONSTRAINT adworkflow_adpackageexpcommon FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adform_adpackageexpdetail FOREIGN KEY(ad_form_id) REFERENCES ad_form DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adimpformat_adpackageexpdetail FOREIGN KEY(ad_impformat_id) REFERENCES ad_impformat DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT admenu_adpackageexpdetail FOREIGN KEY(ad_menu_id) REFERENCES ad_menu DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT admessage_adpackageexpdetail FOREIGN KEY(ad_message_id) REFERENCES ad_message DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT admodval_adpackageexpdetail FOREIGN KEY(ad_modelvalidator_id) REFERENCES ad_modelvalidator DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adprintformat_adpackageexpdeta FOREIGN KEY(ad_printformat_id) REFERENCES ad_printformat DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adprocess_adpackageexpdetail FOREIGN KEY(ad_process_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adreference_adpackageexpdetail FOREIGN KEY(ad_reference_id) REFERENCES ad_reference DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adreportview_adpackageexpdetai FOREIGN KEY(ad_reportview_id) REFERENCES ad_reportview DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adrole_adpackageexpdetail FOREIGN KEY(ad_role_id) REFERENCES ad_role DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adtable_adpackageexpdetail FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adwindow_adpackageexpdetail FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adworkbench_adpackageexpdetail FOREIGN KEY(ad_workbench_id) REFERENCES ad_workbench DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_exp_detail ADD CONSTRAINT adworkflow_adpackageexpdetail FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_package_imp_backup ADD CONSTRAINT adcolumn_adpackageimpbackup FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE ad_package_imp_backup ADD CONSTRAINT adreference_adpackageimpbackup FOREIGN KEY(ad_reference_id) REFERENCES ad_reference DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_printform ADD CONSTRAINT distribordermailtext_adprintfo FOREIGN KEY(distrib_order_mailtext_id) REFERENCES r_mailtext DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_printform ADD CONSTRAINT distriborderprintformat_adprin FOREIGN KEY(distrib_order_printformat_id) REFERENCES ad_printformat DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_printform ADD CONSTRAINT manufordermailtext_adprintform FOREIGN KEY(manuf_order_mailtext_id) REFERENCES r_mailtext DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_printform ADD CONSTRAINT manuforderprintformat_adprintf FOREIGN KEY(manuf_order_printformat_id) REFERENCES ad_printformat DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_printformat ADD CONSTRAINT jasperprocess_adprintformat FOREIGN KEY(jasperprocess_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE ad_printtableformat ADD CONSTRAINT adimage_adprinttableformat FOREIGN KEY(ad_image_id) REFERENCES ad_image DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE ad_process ADD CONSTRAINT adform_adprocess FOREIGN KEY(ad_form_id) REFERENCES ad_form DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE ad_process ADD CONSTRAINT adworkflow_adprocess FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE ad_process ADD CONSTRAINT entityt_adprocess FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE ad_process_para ADD CONSTRAINT entityt_adprocesspara FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE ad_ref_list ADD CONSTRAINT entityt_adreflist FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE ad_ref_table ADD CONSTRAINT adwindow_adreftable FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE ad_ref_table ADD CONSTRAINT entityt_adreftable FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE ad_reference ADD CONSTRAINT entityt_adreference FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE ad_registration ADD CONSTRAINT ccurrency_adregistration FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE ad_replication ADD CONSTRAINT remoteclient_adreplication FOREIGN KEY(remote_client_id) REFERENCES ad_client DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE ad_replication ADD CONSTRAINT remoteorg_adreplication FOREIGN KEY(remote_org_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_replicationdocument ADD CONSTRAINT adtable_adreplicationdocument FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
    
ALTER TABLE ad_replicationdocument ADD CONSTRAINT cdoctype_adreplicationdocument FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE ad_replicationstrategy ADD CONSTRAINT entityt_adreplicationstrategy FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_replicationstrategy ADD CONSTRAINT expprocessor_adreplicationstra FOREIGN KEY(exp_processor_id) REFERENCES exp_processor DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE ad_replicationtable ADD CONSTRAINT entityt_adreplicationtable FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE ad_reportview ADD CONSTRAINT entityt_adreportview FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_role ADD CONSTRAINT adtreeorg_adrole FOREIGN KEY(ad_tree_org_id) REFERENCES ad_tree DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE ad_rule ADD CONSTRAINT entityt_adrule FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE ad_searchdefinition ADD CONSTRAINT adcolumn_adsearchdefinition FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE ad_searchdefinition ADD CONSTRAINT adtable_adsearchdefinition FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE ad_searchdefinition ADD CONSTRAINT adwindow_adsearchdefinition FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE ad_searchdefinition ADD CONSTRAINT powindow_adsearchdefinition FOREIGN KEY(po_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE ad_session ADD CONSTRAINT adrole_adsession FOREIGN KEY(ad_role_id) REFERENCES ad_role DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE ad_sysconfig ADD CONSTRAINT entityt_adsysconfig FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE ad_tab ADD CONSTRAINT entityt_adtab FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE ad_tab ADD CONSTRAINT parentcolumn_adtab FOREIGN KEY(parent_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE ad_table ADD CONSTRAINT entityt_adtable FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE ad_table_scriptvalidator ADD CONSTRAINT adrule_adtablescriptvalidator FOREIGN KEY(ad_rule_id) REFERENCES ad_rule DEFERRABLE INITIALLY DEFERRED;
    
ALTER TABLE ad_table_scriptvalidator ADD CONSTRAINT adtable_adtablescriptvalidator FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE ad_task ADD CONSTRAINT entityt_adtask FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE ad_user ADD CONSTRAINT cjob_aduser FOREIGN KEY(c_job_id) REFERENCES c_job DEFERRABLE INITIALLY DEFERRED;
					   
ALTER TABLE ad_userdef_win ADD CONSTRAINT adlangu_aduserdefwin FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE ad_userquery ADD CONSTRAINT adtab_aduserquery FOREIGN KEY(ad_tab_id) REFERENCES ad_tab DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE ad_val_rule ADD CONSTRAINT entityt_advalrule FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE ad_wf_activity ADD CONSTRAINT adtable_adwfactivity FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE ad_wf_activity ADD CONSTRAINT adworkflow_adwfactivity FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE ad_wf_nextcondition ADD CONSTRAINT entityt_adwfnextcondition FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE ad_wf_node ADD CONSTRAINT adcolumn_adwfnode FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE ad_wf_node ADD CONSTRAINT cbpartner_adwfnode FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE ad_wf_node ADD CONSTRAINT entityt_adwfnode FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE ad_wf_node ADD CONSTRAINT rmailtext_adwfnode FOREIGN KEY(r_mailtext_id) REFERENCES r_mailtext DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE ad_wf_node ADD CONSTRAINT sresource_adwfnode FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE ad_wf_node_para ADD CONSTRAINT entityt_adwfnodepara FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE ad_wf_nodenext ADD CONSTRAINT entityt_adwfnodenext FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE ad_wf_process ADD CONSTRAINT adtable_adwfprocess FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE ad_wf_process ADD CONSTRAINT adwfprocess_adwfprocess FOREIGN KEY(ad_wf_process_id) REFERENCES ad_wf_process DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE ad_wf_responsible ADD CONSTRAINT entityt_adwfresponsible FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE ad_window ADD CONSTRAINT entityt_adwindow FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE ad_workbench ADD CONSTRAINT adcolumn_adworkbench FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE ad_workbench ADD CONSTRAINT entityt_adworkbench FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE ad_workbenchwindow ADD CONSTRAINT entityt_adworkbenchwindow FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE ad_workflow ADD CONSTRAINT adtable_adworkflow FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE ad_workflow ADD CONSTRAINT adwfnode_adworkflow FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE ad_workflow ADD CONSTRAINT entityt_adworkflow FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE ad_workflow ADD CONSTRAINT sresource_adworkflow FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE asp_clientexception ADD CONSTRAINT adfield_aspclientexception FOREIGN KEY(ad_field_id) REFERENCES ad_field DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE asp_clientexception ADD CONSTRAINT adform_aspclientexception FOREIGN KEY(ad_form_id) REFERENCES ad_form DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE asp_clientexception ADD CONSTRAINT adprocess_aspclientexception FOREIGN KEY(ad_process_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
    
ALTER TABLE asp_clientexception ADD CONSTRAINT adprocesspara_aspclientexcepti FOREIGN KEY(ad_process_para_id) REFERENCES ad_process_para DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE asp_clientexception ADD CONSTRAINT adtab_aspclientexception FOREIGN KEY(ad_tab_id) REFERENCES ad_tab DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE asp_clientexception ADD CONSTRAINT adtask_aspclientexception FOREIGN KEY(ad_task_id) REFERENCES ad_task DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE asp_clientexception ADD CONSTRAINT adwfnode_aspclientexception FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE asp_clientexception ADD CONSTRAINT adwindow_aspclientexception FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE asp_clientexception ADD CONSTRAINT adworkflow_aspclientexception FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE asp_clientlevel ADD CONSTRAINT asplevel_aspclientlevel FOREIGN KEY(asp_level_id) REFERENCES asp_level DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE asp_clientlevel ADD CONSTRAINT aspmodule_aspclientlevel FOREIGN KEY(asp_module_id) REFERENCES asp_module DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE asp_field ADD CONSTRAINT adfield_aspfield FOREIGN KEY(ad_field_id) REFERENCES ad_field DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE asp_field ADD CONSTRAINT asptab_aspfield FOREIGN KEY(asp_tab_id) REFERENCES asp_tab DEFERRABLE INITIALLY DEFERRED;
				 
ALTER TABLE asp_form ADD CONSTRAINT adform_aspform FOREIGN KEY(ad_form_id) REFERENCES ad_form DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE asp_form ADD CONSTRAINT asplevel_aspform FOREIGN KEY(asp_level_id) REFERENCES asp_level DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE asp_level ADD CONSTRAINT aspmodule_asplevel FOREIGN KEY(asp_module_id) REFERENCES asp_module DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE asp_process ADD CONSTRAINT adprocess_aspprocess FOREIGN KEY(ad_process_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE asp_process ADD CONSTRAINT asplevel_aspprocess FOREIGN KEY(asp_level_id) REFERENCES asp_level DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE asp_process_para ADD CONSTRAINT adprocesspara_aspprocesspara FOREIGN KEY(ad_process_para_id) REFERENCES ad_process_para DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE asp_process_para ADD CONSTRAINT aspprocess_aspprocesspara FOREIGN KEY(asp_process_id) REFERENCES asp_process DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE asp_tab ADD CONSTRAINT adtab_asptab FOREIGN KEY(ad_tab_id) REFERENCES ad_tab DEFERRABLE INITIALLY DEFERRED;
					
ALTER TABLE asp_tab ADD CONSTRAINT aspwindow_asptab FOREIGN KEY(asp_window_id) REFERENCES asp_window DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE asp_task ADD CONSTRAINT adtask_asptask FOREIGN KEY(ad_task_id) REFERENCES ad_task DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE asp_task ADD CONSTRAINT asplevel_asptask FOREIGN KEY(asp_level_id) REFERENCES asp_level DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE asp_window ADD CONSTRAINT adwindow_aspwindow FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE asp_window ADD CONSTRAINT asplevel_aspwindow FOREIGN KEY(asp_level_id) REFERENCES asp_level DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE asp_workflow ADD CONSTRAINT adworkflow_aspworkflow FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE asp_workflow ADD CONSTRAINT asplevel_aspworkflow FOREIGN KEY(asp_level_id) REFERENCES asp_level DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE b_bid ADD CONSTRAINT aduser_bbid FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
					 
ALTER TABLE b_buyerfunds ADD CONSTRAINT aduser_bbuyerfunds FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE b_offer ADD CONSTRAINT aduser_boffer FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				     
ALTER TABLE b_sellerfunds ADD CONSTRAINT aduser_bsellerfunds FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE c_acctprocessor ADD CONSTRAINT adtable_cacctprocessor FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE c_acctprocessor ADD CONSTRAINT cacctschema_cacctprocessor FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE c_acctschema ADD CONSTRAINT adorgonly_cacctschema FOREIGN KEY(ad_orgonly_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE c_acctschema_element ADD CONSTRAINT adcolumn_cacctschemaelement FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE c_bp_group ADD CONSTRAINT adprintcolor_cbpgroup FOREIGN KEY(ad_printcolor_id) REFERENCES ad_printcolor DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE c_bp_group ADD CONSTRAINT cdunning_cbpgroup FOREIGN KEY(c_dunning_id) REFERENCES c_dunning DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE c_bpartner ADD CONSTRAINT ctaxgroup_cbpartner FOREIGN KEY(c_taxgroup_id) REFERENCES c_taxgroup DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE c_cashline ADD CONSTRAINT cpayment_ccashline FOREIGN KEY(c_payment_id) REFERENCES c_payment DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE c_channel ADD CONSTRAINT adprintcolor_cchannel FOREIGN KEY(ad_printcolor_id) REFERENCES ad_printcolor DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE c_charge ADD CONSTRAINT cbpartner_ccharge FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE c_charge ADD CONSTRAINT cchargetype_ccharge FOREIGN KEY(c_chargetype_id) REFERENCES c_chargetype DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE c_charge_trl ADD CONSTRAINT adlangu_cchargetrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE c_charge_trl ADD CONSTRAINT ccharge_cchargetrl FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE c_chargetype_doctype ADD CONSTRAINT cchargetype_cchargetypedoctype FOREIGN KEY(c_chargetype_id) REFERENCES c_chargetype DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE c_chargetype_doctype ADD CONSTRAINT cdoctype_cchargetypedoctype FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE c_country ADD CONSTRAINT ccountry_ccountry FOREIGN KEY(c_country_id) REFERENCES c_country DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE c_doctype ADD CONSTRAINT cdoctypedifference_cdoctype FOREIGN KEY(c_doctypedifference_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE c_doctype ADD CONSTRAINT definitesequence_cdoctype FOREIGN KEY(definitesequence_id) REFERENCES ad_sequence DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE c_dunningrunentry ADD CONSTRAINT aduser_cdunningrunentry FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE c_dunningrunentry ADD CONSTRAINT cbpartnerlocation_cdunningrune FOREIGN KEY(c_bpartner_location_id) REFERENCES c_bpartner_location DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE c_dunningrunentry ADD CONSTRAINT salesrep_cdunningrunentry FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE c_dunningrunline ADD CONSTRAINT cinvoicepayschedule_cdunningru FOREIGN KEY(c_invoicepayschedule_id) REFERENCES c_invoicepayschedule DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE c_invoice ADD CONSTRAINT cdunninglevel_cinvoice FOREIGN KEY(c_dunninglevel_id) REFERENCES c_dunninglevel DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE c_invoice ADD CONSTRAINT mrma_cinvoice FOREIGN KEY(m_rma_id) REFERENCES m_rma DEFERRABLE INITIALLY DEFERRED;
				       
ALTER TABLE c_invoice ADD CONSTRAINT reversal_cinvoice FOREIGN KEY(reversal_id) REFERENCES c_invoice DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE c_invoiceline ADD CONSTRAINT aassetgroup_cinvoiceline FOREIGN KEY(a_asset_group_id) REFERENCES a_asset_group DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE c_invoiceline ADD CONSTRAINT mrmaline_cinvoiceline FOREIGN KEY(m_rmaline_id) REFERENCES m_rmaline DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE c_order ADD CONSTRAINT cpos_corder FOREIGN KEY(c_pos_id) REFERENCES c_pos DEFERRABLE INITIALLY DEFERRED;
					   
ALTER TABLE c_order ADD CONSTRAINT dropshipbpartner_corder FOREIGN KEY(dropship_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE c_order ADD CONSTRAINT dropshiplocation_corder FOREIGN KEY(dropship_location_id) REFERENCES c_bpartner_location DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE c_order ADD CONSTRAINT dropshipuser_corder FOREIGN KEY(dropship_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE c_order ADD CONSTRAINT linkorder_corder FOREIGN KEY(link_order_id) REFERENCES c_order DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE c_order ADD CONSTRAINT mfreightcategory_order FOREIGN KEY(m_freightcategory_id) REFERENCES m_freightcategory DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE c_orderline ADD CONSTRAINT linkorderline_corderline FOREIGN KEY(link_orderline_id) REFERENCES c_orderline DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE c_orderline ADD CONSTRAINT mpromotion_corderline FOREIGN KEY(m_promotion_id) REFERENCES m_promotion DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE c_orderline ADD CONSTRAINT ppcostcollector_corderline FOREIGN KEY(pp_cost_collector_id) REFERENCES pp_cost_collector DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE c_payment ADD CONSTRAINT c_payment__c_cashbo_c_cashbook FOREIGN KEY(c_cashbook_id) REFERENCES c_cashbook DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE c_payment ADD CONSTRAINT cinvoice_cpayment FOREIGN KEY(c_invoice_id) REFERENCES c_invoice DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE c_payment ADD CONSTRAINT corder_cpayment FOREIGN KEY(c_order_id) REFERENCES c_order DEFERRABLE INITIALLY DEFERRED;
				 
ALTER TABLE c_payment ADD CONSTRAINT refpayment_cpayment FOREIGN KEY(ref_payment_id) REFERENCES c_payment DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE c_payment ADD CONSTRAINT reversal_cpayment FOREIGN KEY(reversal_id) REFERENCES c_payment DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE c_payselectioncheck ADD CONSTRAINT cbpbankaccount_cpayselectionch FOREIGN KEY(c_bp_bankaccount_id) REFERENCES c_bp_bankaccount DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE c_pos ADD CONSTRAINT cbankaccount_cpos FOREIGN KEY(c_bankaccount_id) REFERENCES c_bankaccount DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE c_pos ADD CONSTRAINT cbpartnercashtrx_cpos FOREIGN KEY(c_bpartnercashtrx_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE c_pos ADD CONSTRAINT cdoctype_cpos FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE c_poskey ADD CONSTRAINT adprintcolor_cposkey FOREIGN KEY(ad_printcolor_id) REFERENCES ad_printcolor DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE c_project ADD CONSTRAINT cbpartnersr_cproject FOREIGN KEY(c_bpartnersr_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE c_salesregion ADD CONSTRAINT salesrep_csalesregion FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE c_tax ADD CONSTRAINT adrule_ctax FOREIGN KEY(ad_rule_id) REFERENCES ad_rule DEFERRABLE INITIALLY DEFERRED;
					 
ALTER TABLE c_taxdefinition ADD CONSTRAINT adorgtype_ctaxdefinition FOREIGN KEY(ad_orgtype_id) REFERENCES ad_orgtype DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE c_taxdefinition ADD CONSTRAINT cbpartner_ctaxdefinition FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE c_taxdefinition ADD CONSTRAINT cbpgroup_ctaxdefinition FOREIGN KEY(c_bp_group_id) REFERENCES c_bp_group DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE c_taxdefinition ADD CONSTRAINT ctax_ctaxdefinition FOREIGN KEY(c_tax_id) REFERENCES c_tax DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE c_taxdefinition ADD CONSTRAINT ctaxbase_ctaxdefinition FOREIGN KEY(c_taxbase_id) REFERENCES c_taxbase DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE c_taxdefinition ADD CONSTRAINT ctaxcategory_ctaxdefinition FOREIGN KEY(c_taxcategory_id) REFERENCES c_taxcategory DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE c_taxdefinition ADD CONSTRAINT ctaxgroup_ctaxdefinition FOREIGN KEY(c_taxgroup_id) REFERENCES c_taxgroup DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE c_taxdefinition ADD CONSTRAINT ctaxtype_ctaxdefinition FOREIGN KEY(c_taxtype_id) REFERENCES c_taxtype DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE c_taxdefinition ADD CONSTRAINT mproduct_ctaxdefinition FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE c_taxdefinition ADD CONSTRAINT mproductcategory_ctaxdefinitio FOREIGN KEY(m_product_category_id) REFERENCES m_product_category DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE c_uom_conversion ADD CONSTRAINT mproduct_cuomconversion FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE c_validcombination ADD CONSTRAINT csubacct_cvalidcombination FOREIGN KEY(c_subacct_id) REFERENCES c_subacct DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE c_withholding ADD CONSTRAINT benefici_cwithholding FOREIGN KEY(beneficiary) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE cm_newschannel ADD CONSTRAINT adlangu_cmnewschannel FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE dd_networkdistribution ADD CONSTRAINT mchangenotice_ddnetworkdistrib FOREIGN KEY(m_changenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE dd_networkdistributionline ADD CONSTRAINT ddnetworkdistribution_ddnetwor FOREIGN KEY(dd_networkdistribution_id) REFERENCES dd_networkdistribution DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE dd_networkdistributionline ADD CONSTRAINT mshipper_ddnetworkdistribution FOREIGN KEY(m_shipper_id) REFERENCES m_shipper DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE dd_networkdistributionline ADD CONSTRAINT mwarehouse_ddnetworkdistributi FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE dd_networkdistributionline ADD CONSTRAINT mwarehousesource_ddnetworkdist FOREIGN KEY(m_warehousesource_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE dd_order ADD CONSTRAINT adorgtrx_ddorder FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE dd_order ADD CONSTRAINT aduser_ddorder FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE dd_order ADD CONSTRAINT cactivity_ddorder FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE dd_order ADD CONSTRAINT cbpartner_ddorder FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE dd_order ADD CONSTRAINT cbpartnerlocation_ddorder FOREIGN KEY(c_bpartner_location_id) REFERENCES c_bpartner_location DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE dd_order ADD CONSTRAINT ccampaign_ddorder FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE dd_order ADD CONSTRAINT ccharge_ddorder FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE dd_order ADD CONSTRAINT cdoctype_ddorder FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE dd_order ADD CONSTRAINT cinvoice_ddorder FOREIGN KEY(c_invoice_id) REFERENCES c_invoice DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE dd_order ADD CONSTRAINT corder_ddorder FOREIGN KEY(c_order_id) REFERENCES c_order DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE dd_order ADD CONSTRAINT cproject_ddorder FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE dd_order ADD CONSTRAINT mshipper_ddorder FOREIGN KEY(m_shipper_id) REFERENCES m_shipper DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE dd_order ADD CONSTRAINT mwarehouse_ddorder FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE dd_order ADD CONSTRAINT salesrep_ddorder FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE dd_order ADD CONSTRAINT user1_ddorder FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE dd_order ADD CONSTRAINT user2_ddorder FOREIGN KEY(user2_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE dd_orderline ADD CONSTRAINT adorgtrx_ddorderline FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE dd_orderline ADD CONSTRAINT cactivity_ddorderline FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE dd_orderline ADD CONSTRAINT ccampaign_ddorderline FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE dd_orderline ADD CONSTRAINT ccharge_ddorderline FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE dd_orderline ADD CONSTRAINT cproject_ddorderline FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE dd_orderline ADD CONSTRAINT cuom_ddorderline FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
				 
ALTER TABLE dd_orderline ADD CONSTRAINT ddorder_ddorderline FOREIGN KEY(dd_order_id) REFERENCES dd_order DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE dd_orderline ADD CONSTRAINT mlocator_ddorderline FOREIGN KEY(m_locator_id) REFERENCES m_locator DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE dd_orderline ADD CONSTRAINT mlocatorto_ddorderline FOREIGN KEY(m_locatorto_id) REFERENCES m_locator DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE dd_orderline ADD CONSTRAINT mproduct_ddorderline FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE dd_orderline ADD CONSTRAINT user1_ddorderline FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE dd_orderline ADD CONSTRAINT user2_ddorderline FOREIGN KEY(user2_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE exp_format ADD CONSTRAINT adtable_expformat FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE exp_formatline ADD CONSTRAINT adcolumn_expformatline FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE exp_formatline ADD CONSTRAINT expembeddedformat_expformatlin FOREIGN KEY(exp_embeddedformat_id) REFERENCES exp_format DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE exp_formatline ADD CONSTRAINT expformat_expformatline FOREIGN KEY(exp_format_id) REFERENCES exp_format DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE exp_processor ADD CONSTRAINT expprocessortype_expprocessor FOREIGN KEY(exp_processor_type_id) REFERENCES exp_processor_type DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE exp_processorparameter ADD CONSTRAINT expprocessor_expprocessorparam FOREIGN KEY(exp_processor_id) REFERENCES exp_processor DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE fact_acct ADD CONSTRAINT csubacct_factacct FOREIGN KEY(c_subacct_id) REFERENCES c_subacct DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE fact_acct_summary ADD CONSTRAINT account_factacctsummary FOREIGN KEY(account_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE fact_acct_summary ADD CONSTRAINT cacctschema_factacctsummary FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE fact_acct_summary ADD CONSTRAINT cactivity_factacctsummary FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE fact_acct_summary ADD CONSTRAINT cbpartner_factacctsummary FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE fact_acct_summary ADD CONSTRAINT ccampaign_factacctsummary FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE fact_acct_summary ADD CONSTRAINT cperiod_factacctsummary FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE fact_acct_summary ADD CONSTRAINT cproject_factacctsummary FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE fact_acct_summary ADD CONSTRAINT cprojectphase_factacctsummary FOREIGN KEY(c_projectphase_id) REFERENCES c_projectphase DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE fact_acct_summary ADD CONSTRAINT cprojecttask_factacctsummary FOREIGN KEY(c_projecttask_id) REFERENCES c_projecttask DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE fact_acct_summary ADD CONSTRAINT csalesregion_factacctsummary FOREIGN KEY(c_salesregion_id) REFERENCES c_salesregion DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE fact_acct_summary ADD CONSTRAINT csubacct_factacctsummary FOREIGN KEY(c_subacct_id) REFERENCES c_subacct DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE fact_acct_summary ADD CONSTRAINT glbudget_factacctsummary FOREIGN KEY(gl_budget_id) REFERENCES gl_budget DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE fact_acct_summary ADD CONSTRAINT mproduct_factacctsummary FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE fact_acct_summary ADD CONSTRAINT pareportcube_factacctsummary FOREIGN KEY(pa_reportcube_id) REFERENCES pa_reportcube DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE fact_acct_summary ADD CONSTRAINT user1_factacctsummary FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE fact_acct_summary ADD CONSTRAINT user2_factacctsummary FOREIGN KEY(user2_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE gl_journal ADD CONSTRAINT reversal_gljournal FOREIGN KEY(reversal_id) REFERENCES gl_journal DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE gl_journalbatch ADD CONSTRAINT reversal_gljournalbatch FOREIGN KEY(reversal_id) REFERENCES gl_journalbatch DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE gl_journalline ADD CONSTRAINT aasset_gljournalline FOREIGN KEY(a_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE gl_journalline ADD CONSTRAINT aassetgroup_gljournalline FOREIGN KEY(a_asset_group_id) REFERENCES a_asset_group DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE hr_attribute ADD CONSTRAINT adrule_hrattribute FOREIGN KEY(ad_rule_id) REFERENCES ad_rule DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE hr_attribute ADD CONSTRAINT cbpartner_hrattribute FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE hr_attribute ADD CONSTRAINT hrconcept_hrattribute FOREIGN KEY(hr_concept_id) REFERENCES hr_concept DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE hr_attribute ADD CONSTRAINT hrdepartment_hrattribute FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE hr_attribute ADD CONSTRAINT hremployee_hrattribute FOREIGN KEY(hr_employee_id) REFERENCES hr_employee DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE hr_attribute ADD CONSTRAINT hrjob_hrattribute FOREIGN KEY(hr_job_id) REFERENCES hr_job DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE hr_attribute ADD CONSTRAINT hrpayroll_hrattribute FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE hr_concept ADD CONSTRAINT adreference_hrconcept FOREIGN KEY(ad_reference_id) REFERENCES ad_reference DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE hr_concept ADD CONSTRAINT hrconceptcategory_hrconcept FOREIGN KEY(hr_concept_category_id) REFERENCES hr_concept_category DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE hr_concept ADD CONSTRAINT hrdepartment_hrconcept FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE hr_concept ADD CONSTRAINT hrjob_hrconcept FOREIGN KEY(hr_job_id) REFERENCES hr_job DEFERRABLE INITIALLY DEFERRED;
				  
ALTER TABLE hr_concept ADD CONSTRAINT hrpayroll_hrconcept FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE hr_concept_acct ADD CONSTRAINT cacctschema_hrconceptacct FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE hr_concept_acct ADD CONSTRAINT cbpgroup_hrconceptacct FOREIGN KEY(c_bp_group_id) REFERENCES c_bp_group DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE hr_concept_acct ADD CONSTRAINT hrconcept_hrconceptacct FOREIGN KEY(hr_concept_id) REFERENCES hr_concept DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE hr_concept_acct ADD CONSTRAINT user1_hrconceptacct FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE hr_contract ADD CONSTRAINT cbpartner_hrcontract FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_contract ADD CONSTRAINT ccampaign_hrcontract FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_contract ADD CONSTRAINT cproject_hrcontract FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE hr_department ADD CONSTRAINT activity_hrdepartment FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE hr_employee ADD CONSTRAINT cactivity_hremployee FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_employee ADD CONSTRAINT cbpartner_hremployee FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_employee ADD CONSTRAINT hrdepartment_hremployee FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE hr_employee ADD CONSTRAINT hrjob_hremployee FOREIGN KEY(hr_job_id) REFERENCES hr_job DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE hr_employee ADD CONSTRAINT hrpayroll_hremployee FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_job ADD CONSTRAINT hrdepartment_hrjob FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE hr_job ADD CONSTRAINT nextjob_hrjob FOREIGN KEY(next_job_id) REFERENCES hr_job DEFERRABLE INITIALLY DEFERRED;
				      
ALTER TABLE hr_job ADD CONSTRAINT supervisor_hrjob FOREIGN KEY(supervisor_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE hr_list ADD CONSTRAINT hrdepartment_hrlist FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE hr_list ADD CONSTRAINT hremployee_hrlist FOREIGN KEY(hr_employee_id) REFERENCES hr_employee DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_list ADD CONSTRAINT hrlisttype_hrlist FOREIGN KEY(hr_listtype_id) REFERENCES hr_listtype DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_list ADD CONSTRAINT hrpayroll_hrlist FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE hr_listline ADD CONSTRAINT hrlistversion_hrlistline FOREIGN KEY(hr_listversion_id) REFERENCES hr_listversion DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE hr_listversion ADD CONSTRAINT hrlist_hrlistversion FOREIGN KEY(hr_list_id) REFERENCES hr_list DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE hr_listversion ADD CONSTRAINT hrlistbase_hrlistversion FOREIGN KEY(hr_listbase_id) REFERENCES hr_list DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE hr_movement ADD CONSTRAINT adorgtrx_hrmovement FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE hr_movement ADD CONSTRAINT adrule_hrmovement FOREIGN KEY(ad_rule_id) REFERENCES ad_rule DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE hr_movement ADD CONSTRAINT cactivity_hrmovement FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_movement ADD CONSTRAINT cbpartner_hrmovement FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_movement ADD CONSTRAINT ccampaign_hrmovement FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_movement ADD CONSTRAINT cproject_hrmovement FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE hr_movement ADD CONSTRAINT cprojectphase_hrmovement FOREIGN KEY(c_projectphase_id) REFERENCES c_projectphase DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE hr_movement ADD CONSTRAINT cprojecttask_hrmovement FOREIGN KEY(c_projecttask_id) REFERENCES c_projecttask DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE hr_movement ADD CONSTRAINT hrconcept_hrmovement FOREIGN KEY(hr_concept_id) REFERENCES hr_concept DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_movement ADD CONSTRAINT hrconceptcategory_hrmovement FOREIGN KEY(hr_concept_category_id) REFERENCES hr_concept_category DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE hr_movement ADD CONSTRAINT hrdepartment_hrmovement FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE hr_movement ADD CONSTRAINT hrjob_hrmovement FOREIGN KEY(hr_job_id) REFERENCES hr_job DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE hr_movement ADD CONSTRAINT hrprocess_hrmovement FOREIGN KEY(hr_process_id) REFERENCES hr_process DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE hr_movement ADD CONSTRAINT ppcostcollector_hrmovement FOREIGN KEY(pp_cost_collector_id) REFERENCES pp_cost_collector DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE hr_movement ADD CONSTRAINT user1_hrmovement FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_movement ADD CONSTRAINT user2_hrmovement FOREIGN KEY(user2_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_payroll ADD CONSTRAINT adprintformat_hrpayroll FOREIGN KEY(ad_printformat_id) REFERENCES ad_printformat DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE hr_payroll ADD CONSTRAINT ccharge_hrpayroll FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE hr_payroll ADD CONSTRAINT hrcontract_hrpayroll FOREIGN KEY(hr_contract_id) REFERENCES hr_contract DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE hr_payrollconcept ADD CONSTRAINT adrule_hrpayrollconcept FOREIGN KEY(ad_rule_id) REFERENCES ad_rule DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE hr_payrollconcept ADD CONSTRAINT hrconcept_hrpayrollconcept FOREIGN KEY(hr_concept_id) REFERENCES hr_concept DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE hr_payrollconcept ADD CONSTRAINT hrpayroll_hrpayrollconcept FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE hr_period ADD CONSTRAINT cperiod_hrperiod FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE hr_period ADD CONSTRAINT cyear_hrperiod FOREIGN KEY(c_year_id) REFERENCES c_year DEFERRABLE INITIALLY DEFERRED;
				    
ALTER TABLE hr_period ADD CONSTRAINT hrpayroll_hrperiod FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE hr_period ADD CONSTRAINT hryear_hrperiod FOREIGN KEY(hr_year_id) REFERENCES hr_year DEFERRABLE INITIALLY DEFERRED;
				 
ALTER TABLE hr_process ADD CONSTRAINT adprintformat_hrprocess FOREIGN KEY(ad_printformat_id) REFERENCES ad_printformat DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE hr_process ADD CONSTRAINT adworkflow_hrprocess FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE hr_process ADD CONSTRAINT cbpartner_hrprocess FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE hr_process ADD CONSTRAINT ccharge_hrprocess FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE hr_process ADD CONSTRAINT cdoctype_hrprocess FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_process ADD CONSTRAINT cdoctypetarget_hrprocess FOREIGN KEY(c_doctypetarget_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE hr_process ADD CONSTRAINT cpayselection_hrprocess FOREIGN KEY(c_payselection_id) REFERENCES c_payselection DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE hr_process ADD CONSTRAINT hrdepartment_hrprocess FOREIGN KEY(hr_department_id) REFERENCES hr_department DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE hr_process ADD CONSTRAINT hremployee_hrprocess FOREIGN KEY(hr_employee_id) REFERENCES hr_employee DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE hr_process ADD CONSTRAINT hrjob_hrprocess FOREIGN KEY(hr_job_id) REFERENCES hr_job DEFERRABLE INITIALLY DEFERRED;
				  
ALTER TABLE hr_process ADD CONSTRAINT hrpayroll_hrprocess FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE hr_process ADD CONSTRAINT hrperiod_hrprocess FOREIGN KEY(hr_period_id) REFERENCES hr_period DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_process ADD CONSTRAINT reversal_hrprocess FOREIGN KEY(reversal_id) REFERENCES hr_process DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE hr_year ADD CONSTRAINT cyear_hryear FOREIGN KEY(c_year_id) REFERENCES c_year DEFERRABLE INITIALLY DEFERRED;
					
ALTER TABLE hr_year ADD CONSTRAINT hrpayroll_hryear FOREIGN KEY(hr_payroll_id) REFERENCES hr_payroll DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE i_asset ADD CONSTRAINT aassetgroup_iasset FOREIGN KEY(a_asset_group_id) REFERENCES a_asset_group DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_asset ADD CONSTRAINT adepreciationtableheader_iasse FOREIGN KEY(a_depreciation_table_header_id) REFERENCES a_depreciation_table_header DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE i_asset ADD CONSTRAINT cacctschema_iasset FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE i_asset ADD CONSTRAINT cbpartner_iasset FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE i_asset ADD CONSTRAINT cbpartnerlocation_iasset FOREIGN KEY(c_bpartner_location_id) REFERENCES c_bpartner_location DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE i_asset ADD CONSTRAINT clocation_iasset FOREIGN KEY(c_location_id) REFERENCES c_location DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE i_asset ADD CONSTRAINT mlocator_iasset FOREIGN KEY(m_locator_id) REFERENCES m_locator DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE i_asset ADD CONSTRAINT mproduct_iasset FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE i_bpartner ADD CONSTRAINT rinterestarea_ibpartner FOREIGN KEY(r_interestarea_id) REFERENCES r_interestarea DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE i_fajournal ADD CONSTRAINT account_ifajournal FOREIGN KEY(account_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE i_fajournal ADD CONSTRAINT adorgdoc_ifajournal FOREIGN KEY(ad_orgdoc_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE i_fajournal ADD CONSTRAINT adorgtrx_ifajournal FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE i_fajournal ADD CONSTRAINT cacctschema_ifajournal FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE i_fajournal ADD CONSTRAINT cactivity_ifajournal FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_fajournal ADD CONSTRAINT cbpartner_ifajournal FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_fajournal ADD CONSTRAINT ccampaign_ifajournal FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_fajournal ADD CONSTRAINT ccurrency_ifajournal FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_fajournal ADD CONSTRAINT cdoctype_ifajournal FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE i_fajournal ADD CONSTRAINT cperiod_ifajournal FOREIGN KEY(c_period_id) REFERENCES c_period DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE i_fajournal ADD CONSTRAINT cproject_ifajournal FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE i_fajournal ADD CONSTRAINT csalesregion_ifajournal FOREIGN KEY(c_salesregion_id) REFERENCES c_salesregion DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE i_fajournal ADD CONSTRAINT cuom_ifajournal FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE i_fajournal ADD CONSTRAINT cvalidcombination_ifajournal FOREIGN KEY(c_validcombination_id) REFERENCES c_validcombination DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE i_fajournal ADD CONSTRAINT glbudget_ifajournal FOREIGN KEY(gl_budget_id) REFERENCES gl_budget DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE i_fajournal ADD CONSTRAINT glcategory_ifajournal FOREIGN KEY(gl_category_id) REFERENCES gl_category DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE i_fajournal ADD CONSTRAINT gljournal_ifajournal FOREIGN KEY(gl_journal_id) REFERENCES gl_journal DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_fajournal ADD CONSTRAINT gljournalbatch_ifajournal FOREIGN KEY(gl_journalbatch_id) REFERENCES gl_journalbatch DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE i_fajournal ADD CONSTRAINT gljournalline_ifajournal FOREIGN KEY(gl_journalline_id) REFERENCES gl_journalline DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE i_fajournal ADD CONSTRAINT mproduct_ifajournal FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE i_fajournal ADD CONSTRAINT user1_ifajournal FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE i_fajournal ADD CONSTRAINT user2_ifajournal FOREIGN KEY(user2_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE i_gljournal ADD CONSTRAINT cuom_igljournal FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE i_invoice ADD CONSTRAINT ccharge_iinvoice FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE i_order ADD CONSTRAINT ccharge_iorder FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
				  
ALTER TABLE i_payment ADD CONSTRAINT ccurrency_ipayment FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE i_pricelist ADD CONSTRAINT cbpartner_ipricelist FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_pricelist ADD CONSTRAINT ccurrency_ipricelist FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE i_pricelist ADD CONSTRAINT cuom_ipricelist FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE i_pricelist ADD CONSTRAINT mpricelist_ipricelist FOREIGN KEY(m_pricelist_id) REFERENCES m_pricelist DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE i_pricelist ADD CONSTRAINT mpricelistversion_ipricelist FOREIGN KEY(m_pricelist_version_id) REFERENCES m_pricelist_version DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE i_pricelist ADD CONSTRAINT mproduct_ipricelist FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE imp_processor ADD CONSTRAINT impprocessortype_impprocessor FOREIGN KEY(imp_processor_type_id) REFERENCES imp_processor_type DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE imp_processorlog ADD CONSTRAINT impprocessor_impprocessorlog FOREIGN KEY(imp_processor_id) REFERENCES imp_processor DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE imp_processorparameter ADD CONSTRAINT impprocessor_impprocessorparam FOREIGN KEY(imp_processor_id) REFERENCES imp_processor DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE k_synonym ADD CONSTRAINT adlangu_ksynonym FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE m_changerequest ADD CONSTRAINT mfixchangenotice_mchangereques FOREIGN KEY(m_fixchangenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_changerequest ADD CONSTRAINT ppproductbom_mchangerequest FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE m_costdetail ADD CONSTRAINT ppcostcollector_mcostdetail FOREIGN KEY(pp_cost_collector_id) REFERENCES pp_cost_collector DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_forecast ADD CONSTRAINT mpricelist_mforecast FOREIGN KEY(m_pricelist_id) REFERENCES m_pricelist DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE m_forecastline ADD CONSTRAINT mwarehouse_mforecastline FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE m_inout ADD CONSTRAINT cinvoice_minout FOREIGN KEY(c_invoice_id) REFERENCES c_invoice DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE m_inout ADD CONSTRAINT dropshipbpartner_minout FOREIGN KEY(dropship_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE m_inout ADD CONSTRAINT dropshiplocation_minout FOREIGN KEY(dropship_location_id) REFERENCES c_bpartner_location DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE m_inout ADD CONSTRAINT dropshipuser_minout FOREIGN KEY(dropship_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE m_inout ADD CONSTRAINT mrma_minout FOREIGN KEY(m_rma_id) REFERENCES m_rma DEFERRABLE INITIALLY DEFERRED;
					   
ALTER TABLE m_inout ADD CONSTRAINT reversal_minout FOREIGN KEY(reversal_id) REFERENCES m_inout DEFERRABLE INITIALLY DEFERRED;
				  
ALTER TABLE m_inoutconfirm ADD CONSTRAINT cinvoice_minoutconfirm FOREIGN KEY(c_invoice_id) REFERENCES c_invoice DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE m_inoutconfirm ADD CONSTRAINT minventory_minoutconfirm FOREIGN KEY(m_inventory_id) REFERENCES m_inventory DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE m_inoutline ADD CONSTRAINT mrmaline_minoutline FOREIGN KEY(m_rmaline_id) REFERENCES m_rmaline DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE m_inoutline ADD CONSTRAINT reversalline_minoutline FOREIGN KEY(reversalline_id) REFERENCES m_inoutline DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE m_inoutlineconfirm ADD CONSTRAINT cinvoiceline_minoutlineconfirm FOREIGN KEY(c_invoiceline_id) REFERENCES c_invoiceline DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_inoutlineconfirm ADD CONSTRAINT minventoryline_minoutlineconfi FOREIGN KEY(m_inventoryline_id) REFERENCES m_inventoryline DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_inventory ADD CONSTRAINT cdoctype_minventory FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE m_inventory ADD CONSTRAINT reversal_minventory FOREIGN KEY(reversal_id) REFERENCES m_inventory DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE m_inventoryline ADD CONSTRAINT reversalline_minventoryline FOREIGN KEY(reversalline_id) REFERENCES m_inventoryline DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE m_movement ADD CONSTRAINT aduser_mmovement FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE m_movement ADD CONSTRAINT cbpartner_mmovement FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE m_movement ADD CONSTRAINT ccharge_mmovement FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE m_movement ADD CONSTRAINT cdoctype_mmovement FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE m_movement ADD CONSTRAINT ddorder_mmovement FOREIGN KEY(dd_order_id) REFERENCES dd_order DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE m_movement ADD CONSTRAINT mshipper_mmovement FOREIGN KEY(m_shipper_id) REFERENCES m_shipper DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE m_movement ADD CONSTRAINT reversal_mmovement FOREIGN KEY(reversal_id) REFERENCES m_movement DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE m_movement ADD CONSTRAINT salesrep_mmovement FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			    
ALTER TABLE m_movementline ADD CONSTRAINT ddorderline_mmovementline FOREIGN KEY(dd_orderline_id) REFERENCES dd_orderline DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE m_movementline ADD CONSTRAINT reversalline_mmovementline FOREIGN KEY(reversalline_id) REFERENCES m_movementline DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE m_pricelist_version ADD CONSTRAINT mpricelistversionbase_mpriceli FOREIGN KEY(m_pricelist_version_base_id) REFERENCES m_pricelist_version DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_product ADD CONSTRAINT salesrep_mproduct FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE m_product_category ADD CONSTRAINT adprintcolor_mproductcategory FOREIGN KEY(ad_printcolor_id) REFERENCES ad_printcolor DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_product_category ADD CONSTRAINT mproductcat_parentcat FOREIGN KEY(m_product_category_parent_id) REFERENCES m_product_category DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_productpricevendorbreak ADD CONSTRAINT cbpartner_mproductpricevendorb FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_productpricevendorbreak ADD CONSTRAINT mpricelistversion_mproductpric FOREIGN KEY(m_pricelist_version_id) REFERENCES m_pricelist_version DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_productpricevendorbreak ADD CONSTRAINT mproduct_mproductpricevendorbr FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotion ADD CONSTRAINT ccampaign_mpromotion FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE m_promotiondistribution ADD CONSTRAINT mpromotion_mpromotiondistribut FOREIGN KEY(m_promotion_id) REFERENCES m_promotion DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotiondistribution ADD CONSTRAINT mpromotionline_mpromotiondistr FOREIGN KEY(m_promotionline_id) REFERENCES m_promotionline DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotiongroupline ADD CONSTRAINT mproduct_mpromotiongroupline FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE m_promotiongroupline ADD CONSTRAINT mpromotiongroup_mpromotiongrou FOREIGN KEY(m_promotiongroup_id) REFERENCES m_promotiongroup DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionline ADD CONSTRAINT mpromotion_mpromotionline FOREIGN KEY(m_promotion_id) REFERENCES m_promotion DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE m_promotionline ADD CONSTRAINT mpromotiongroup_mpromotionline FOREIGN KEY(m_promotiongroup_id) REFERENCES m_promotiongroup DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionprecondition ADD CONSTRAINT cactivity_mpromotionpreconditi FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionprecondition ADD CONSTRAINT cbpartner_mpromotionpreconditi FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionprecondition ADD CONSTRAINT cbpgroup_mpromotionpreconditio FOREIGN KEY(c_bp_group_id) REFERENCES c_bp_group DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionprecondition ADD CONSTRAINT mpricelist_mpromotionprecondit FOREIGN KEY(m_pricelist_id) REFERENCES m_pricelist DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionprecondition ADD CONSTRAINT mpromotion_mpromotionprecondit FOREIGN KEY(m_promotion_id) REFERENCES m_promotion DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionprecondition ADD CONSTRAINT mwarehouse_mpromotionprecondit FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionreward ADD CONSTRAINT ccharge_mpromotionreward FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE m_promotionreward ADD CONSTRAINT mpromotion_mpromotionreward FOREIGN KEY(m_promotion_id) REFERENCES m_promotion DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE m_promotionreward ADD CONSTRAINT mpromotiondistribution_mpromot FOREIGN KEY(m_promotiondistribution_id) REFERENCES m_promotiondistribution DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_promotionreward ADD CONSTRAINT mtargetdistribution_mpromotion FOREIGN KEY(m_targetdistribution_id) REFERENCES m_promotiondistribution DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_replenish ADD CONSTRAINT mlocator_mreplenish FOREIGN KEY(m_locator_id) REFERENCES m_locator DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE m_replenish ADD CONSTRAINT mwarehousesource_mreplenish FOREIGN KEY(m_warehousesource_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE m_requisition ADD CONSTRAINT cdoctype_mrequisition FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE m_requisitionline ADD CONSTRAINT cbpartner_mrequisitionline FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE m_requisitionline ADD CONSTRAINT ccharge_mrequisitionline FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE m_requisitionline ADD CONSTRAINT corderline_mrequisitionline FOREIGN KEY(c_orderline_id) REFERENCES c_orderline DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE m_requisitionline ADD CONSTRAINT cuom_mrequisitionline FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE m_rma ADD CONSTRAINT cbpartner_mrma FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE m_rma ADD CONSTRAINT ccurrency_mrma FOREIGN KEY(c_currency_id) REFERENCES c_currency DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE m_rma ADD CONSTRAINT cdoctype_mrma FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE m_rma ADD CONSTRAINT corder_mrma FOREIGN KEY(c_order_id) REFERENCES c_order DEFERRABLE INITIALLY DEFERRED;
					 
ALTER TABLE m_rma ADD CONSTRAINT mrmatype_mrma FOREIGN KEY(m_rmatype_id) REFERENCES m_rmatype DEFERRABLE INITIALLY DEFERRED;
				   
ALTER TABLE m_rma ADD CONSTRAINT refrma_mrma FOREIGN KEY(ref_rma_id) REFERENCES m_rma DEFERRABLE INITIALLY DEFERRED;
					   
ALTER TABLE m_rma ADD CONSTRAINT salesrep_mrma FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				      
ALTER TABLE m_rmaline ADD CONSTRAINT ccharge_mrmaline FOREIGN KEY(c_charge_id) REFERENCES c_charge DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE m_rmaline ADD CONSTRAINT refrmaline_mrmaline FOREIGN KEY(ref_rmaline_id) REFERENCES m_rmaline DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE m_transaction ADD CONSTRAINT ppcostcollector_mtransaction FOREIGN KEY(pp_cost_collector_id) REFERENCES pp_cost_collector DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE m_warehouse ADD CONSTRAINT mwarehousesource_mwarehouse FOREIGN KEY(m_warehousesource_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE pa_colorschema ADD CONSTRAINT entityt_pacolorschema FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE pa_dashboardcontent ADD CONSTRAINT adwindow_padashboardcontent FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE pa_dashboardcontent ADD CONSTRAINT pagoal_padashboardcontent FOREIGN KEY(pa_goal_id) REFERENCES pa_goal DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pa_goal ADD CONSTRAINT adrole_pagoal FOREIGN KEY(ad_role_id) REFERENCES ad_role DEFERRABLE INITIALLY DEFERRED;
				     
ALTER TABLE pa_measure ADD CONSTRAINT cprojecttype_pameasure FOREIGN KEY(c_projecttype_id) REFERENCES c_projecttype DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pa_measure ADD CONSTRAINT rrequesttype_pameasure FOREIGN KEY(r_requesttype_id) REFERENCES r_requesttype DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pa_measurecalc ADD CONSTRAINT adtable_pameasurecalc FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE pa_measurecalc ADD CONSTRAINT entityt_pameasurecalc FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE pa_report ADD CONSTRAINT jasperprocess_pareport FOREIGN KEY(jasperprocess_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pa_report ADD CONSTRAINT pareportcube_pareport FOREIGN KEY(pa_reportcube_id) REFERENCES pa_reportcube DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pa_reportcolumn ADD CONSTRAINT adorgtrx_pareportcolumn FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE pa_reportcube ADD CONSTRAINT ccalendar_pareportcube FOREIGN KEY(c_calendar_id) REFERENCES c_calendar DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE pa_reportsource ADD CONSTRAINT adorgtrx_pareportsource FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE pp_cost_collector ADD CONSTRAINT adorgtrx_ppcostcollector FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_cost_collector ADD CONSTRAINT aduser_ppcostcollector FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE pp_cost_collector ADD CONSTRAINT cactivity_ppcostcollector FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE pp_cost_collector ADD CONSTRAINT ccampaign_ppcostcollector FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE pp_cost_collector ADD CONSTRAINT cdoctype_ppcostcollector FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE pp_cost_collector ADD CONSTRAINT cdoctypetarget_ppcostcollector FOREIGN KEY(c_doctypetarget_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_cost_collector ADD CONSTRAINT cproject_ppcostcollector FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE pp_cost_collector ADD CONSTRAINT cuom_ppcostcollector FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE pp_cost_collector ADD CONSTRAINT mproduct_ppcostcollector FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE pp_cost_collector ADD CONSTRAINT mwarehouse_ppcostcollector FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE pp_cost_collector ADD CONSTRAINT pporder_ppcostcollector FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_cost_collector ADD CONSTRAINT pporderbomline_ppcostcollector FOREIGN KEY(pp_order_bomline_id) REFERENCES pp_order_bomline DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_cost_collector ADD CONSTRAINT ppordernode_ppcostcollector FOREIGN KEY(pp_order_node_id) REFERENCES pp_order_node DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE pp_cost_collector ADD CONSTRAINT pporderworkflow_ppcostcollecto FOREIGN KEY(pp_order_workflow_id) REFERENCES pp_order_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_cost_collector ADD CONSTRAINT reversal_ppcostcollector FOREIGN KEY(reversal_id) REFERENCES pp_cost_collector DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE pp_cost_collector ADD CONSTRAINT sresource_ppcostcollector FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE pp_cost_collector ADD CONSTRAINT user1_ppcostcollector FOREIGN KEY(user1_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE pp_cost_collector ADD CONSTRAINT user2_ppcostcollector FOREIGN KEY(user2_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE pp_cost_collectorma ADD CONSTRAINT ppcostcollector_ppcostcollectorma FOREIGN KEY(pp_cost_collector_id) REFERENCES pp_cost_collector DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_mrp ADD CONSTRAINT cbpartner_ppmrp FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE pp_mrp ADD CONSTRAINT corder_ppmrp FOREIGN KEY(c_order_id) REFERENCES c_order DEFERRABLE INITIALLY DEFERRED;
				       
ALTER TABLE pp_mrp ADD CONSTRAINT corderline_ppmrp FOREIGN KEY(c_orderline_id) REFERENCES c_orderline DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE pp_mrp ADD CONSTRAINT ddorder_ppmrp FOREIGN KEY(dd_order_id) REFERENCES dd_order DEFERRABLE INITIALLY DEFERRED;
				    
ALTER TABLE pp_mrp ADD CONSTRAINT ddorderline_ppmrp FOREIGN KEY(dd_orderline_id) REFERENCES dd_orderline DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE pp_mrp ADD CONSTRAINT mforecast_ppmrp FOREIGN KEY(m_forecast_id) REFERENCES m_forecast DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE pp_mrp ADD CONSTRAINT mforecastline_ppmrp FOREIGN KEY(m_forecastline_id) REFERENCES m_forecastline DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE pp_mrp ADD CONSTRAINT mproduct_ppmrp FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
				 
ALTER TABLE pp_mrp ADD CONSTRAINT mrequisition_ppmrp FOREIGN KEY(m_requisition_id) REFERENCES m_requisition DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE pp_mrp ADD CONSTRAINT mrequisitionline_ppmrp FOREIGN KEY(m_requisitionline_id) REFERENCES m_requisitionline DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE pp_mrp ADD CONSTRAINT mwarehouse_ppmrp FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
			   
ALTER TABLE pp_mrp ADD CONSTRAINT planner_ppmrp FOREIGN KEY(planner_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				      
ALTER TABLE pp_mrp ADD CONSTRAINT pporder_ppmrp FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
				    
ALTER TABLE pp_mrp ADD CONSTRAINT pporderbomline_ppmrp FOREIGN KEY(pp_order_bomline_id) REFERENCES pp_order_bomline DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pp_mrp ADD CONSTRAINT sresource_ppmrp FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE pp_order ADD CONSTRAINT adorgtrx_pporder FOREIGN KEY(ad_orgtrx_id) REFERENCES ad_org DEFERRABLE INITIALLY DEFERRED;
				
ALTER TABLE pp_order ADD CONSTRAINT adworkflow_pporder FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE pp_order ADD CONSTRAINT cactivity_pporder FOREIGN KEY(c_activity_id) REFERENCES c_activity DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE pp_order ADD CONSTRAINT ccampaign_pporder FOREIGN KEY(c_campaign_id) REFERENCES c_campaign DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE pp_order ADD CONSTRAINT cdoctype_pporder FOREIGN KEY(c_doctype_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE pp_order ADD CONSTRAINT cdoctypetarget_pporder FOREIGN KEY(c_doctypetarget_id) REFERENCES c_doctype DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pp_order ADD CONSTRAINT corderline_pporder FOREIGN KEY(c_orderline_id) REFERENCES c_orderline DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE pp_order ADD CONSTRAINT cproject_pporder FOREIGN KEY(c_project_id) REFERENCES c_project DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE pp_order ADD CONSTRAINT cuom_pporder FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
					 
ALTER TABLE pp_order ADD CONSTRAINT mproduct_pporder FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
			     
ALTER TABLE pp_order ADD CONSTRAINT mwarehouse_pporder FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE pp_order ADD CONSTRAINT planner_pporder FOREIGN KEY(planner_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				  
ALTER TABLE pp_order ADD CONSTRAINT ppproductbom_pporder FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order ADD CONSTRAINT sresource_pporder FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE pp_order ADD CONSTRAINT user1_pporder FOREIGN KEY(user1_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE pp_order ADD CONSTRAINT user2_pporder FOREIGN KEY(user2_id) REFERENCES c_elementvalue DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE pp_order_bom ADD CONSTRAINT cuom_pporderbom FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
				  
ALTER TABLE pp_order_bom ADD CONSTRAINT mchangenotice_pporderbom FOREIGN KEY(m_changenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE pp_order_bom ADD CONSTRAINT mproduct_pporderbom FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE pp_order_bom ADD CONSTRAINT pporder_pporderbom FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE pp_order_bom_trl ADD CONSTRAINT adlangu_pporderbomtrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order_bom_trl ADD CONSTRAINT pporderbom_pporderbomtrl FOREIGN KEY(pp_order_bom_id) REFERENCES pp_order_bom DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE pp_order_bomline ADD CONSTRAINT aduser_pporderbomline FOREIGN KEY(ad_user_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE pp_order_bomline ADD CONSTRAINT cuom_pporderbomline FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE pp_order_bomline ADD CONSTRAINT mchangenotice_pporderbomline FOREIGN KEY(m_changenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_bomline ADD CONSTRAINT mproduct_pporderbomline FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE pp_order_bomline ADD CONSTRAINT mwarehouse_pporderbomline FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE pp_order_bomline ADD CONSTRAINT pporder_pporderbomline FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pp_order_bomline ADD CONSTRAINT pporderbom_pporderbomline FOREIGN KEY(pp_order_bom_id) REFERENCES pp_order_bom DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE pp_order_bomline_trl ADD CONSTRAINT adlangu_pporderbomlinetrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE pp_order_bomline_trl ADD CONSTRAINT pporderbomline_pporderbomlinet FOREIGN KEY(pp_order_bomline_id) REFERENCES pp_order_bomline DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_cost ADD CONSTRAINT adworkflow_ppordercost FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE pp_order_cost ADD CONSTRAINT cacctschema_ppordercost FOREIGN KEY(c_acctschema_id) REFERENCES c_acctschema DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE pp_order_cost ADD CONSTRAINT mcostelement_ppordercost FOREIGN KEY(m_costelement_id) REFERENCES m_costelement DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE pp_order_cost ADD CONSTRAINT mcosttype_ppordercost FOREIGN KEY(m_costtype_id) REFERENCES m_costtype DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pp_order_cost ADD CONSTRAINT mproduct_ppordercost FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE pp_order_cost ADD CONSTRAINT pporder_ppordercost FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE pp_order_node ADD CONSTRAINT adcolumn_ppordernode FOREIGN KEY(ad_column_id) REFERENCES ad_column DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE pp_order_node ADD CONSTRAINT adform_ppordernode FOREIGN KEY(ad_form_id) REFERENCES ad_form DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE pp_order_node ADD CONSTRAINT adimage_ppordernode FOREIGN KEY(ad_image_id) REFERENCES ad_image DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE pp_order_node ADD CONSTRAINT adprocess_ppordernode FOREIGN KEY(ad_process_id) REFERENCES ad_process DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pp_order_node ADD CONSTRAINT adtask_ppordernode FOREIGN KEY(ad_task_id) REFERENCES ad_task DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE pp_order_node ADD CONSTRAINT adwfblock_ppordernode FOREIGN KEY(ad_wf_block_id) REFERENCES ad_wf_block DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order_node ADD CONSTRAINT adwfnode_ppordernode FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE pp_order_node ADD CONSTRAINT adwfresponsible_ppordernode FOREIGN KEY(ad_wf_responsible_id) REFERENCES ad_wf_responsible DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_node ADD CONSTRAINT adwindow_ppordernode FOREIGN KEY(ad_window_id) REFERENCES ad_window DEFERRABLE INITIALLY DEFERRED;
		    
ALTER TABLE pp_order_node ADD CONSTRAINT adworkflow_ppordernode FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
	      
ALTER TABLE pp_order_node ADD CONSTRAINT cbpartner_ppordernode FOREIGN KEY(c_bpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pp_order_node ADD CONSTRAINT entityt_ppordernode FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE pp_order_node ADD CONSTRAINT pporder_ppordernode FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
		       
ALTER TABLE pp_order_node ADD CONSTRAINT pporderworkflow_ppordernode FOREIGN KEY(pp_order_workflow_id) REFERENCES pp_order_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_node ADD CONSTRAINT sresource_ppordernode FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE pp_order_node ADD CONSTRAINT workflow_ppordernode FOREIGN KEY(workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE pp_order_node_asset ADD CONSTRAINT aasset_ppordernodeasset FOREIGN KEY(a_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order_node_asset ADD CONSTRAINT pporder_ppordernodeasset FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
	    
ALTER TABLE pp_order_node_asset ADD CONSTRAINT ppordernode_ppordernodeasset FOREIGN KEY(pp_order_node_id) REFERENCES pp_order_node DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_node_asset ADD CONSTRAINT pporderworkflow_ppordernodeass FOREIGN KEY(pp_order_workflow_id) REFERENCES pp_order_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_node_product ADD CONSTRAINT mproduct_ppordernodeproduct FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE pp_order_node_product ADD CONSTRAINT pporder_ppordernodeproduct FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE pp_order_node_product ADD CONSTRAINT ppordernode_ppordernodeproduct FOREIGN KEY(pp_order_node_id) REFERENCES pp_order_node DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_node_product ADD CONSTRAINT pporderworkflow_ppordernodepro FOREIGN KEY(pp_order_workflow_id) REFERENCES pp_order_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_node_trl ADD CONSTRAINT adlangu_ppordernodetrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pp_order_nodenext ADD CONSTRAINT adwfnext_ppordernodenext FOREIGN KEY(ad_wf_next_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE pp_order_nodenext ADD CONSTRAINT adwfnode_ppordernodenext FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE pp_order_nodenext ADD CONSTRAINT entityt_ppordernodenext FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE pp_order_nodenext ADD CONSTRAINT pporder_ppordernodenext FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order_nodenext ADD CONSTRAINT ppordernext_ppordernodenext FOREIGN KEY(pp_order_next_id) REFERENCES pp_order_node DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE pp_order_nodenext ADD CONSTRAINT ppordernode_ppordernodenext FOREIGN KEY(pp_order_node_id) REFERENCES pp_order_node DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE pp_order_workflow ADD CONSTRAINT adtable_pporderworkflow FOREIGN KEY(ad_table_id) REFERENCES ad_table DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order_workflow ADD CONSTRAINT adwfnode_pporderworkflow FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE pp_order_workflow ADD CONSTRAINT adwfresponsible_pporderworkflo FOREIGN KEY(ad_wf_responsible_id) REFERENCES ad_wf_responsible DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_workflow ADD CONSTRAINT adworkflow_pporderworkflow FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
      
ALTER TABLE pp_order_workflow ADD CONSTRAINT adworkflowprocessor_pporderwor FOREIGN KEY(ad_workflowprocessor_id) REFERENCES ad_workflowprocessor DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_order_workflow ADD CONSTRAINT entityt_pporderworkflow FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE pp_order_workflow ADD CONSTRAINT pporder_pporderworkflow FOREIGN KEY(pp_order_id) REFERENCES pp_order DEFERRABLE INITIALLY DEFERRED;
	       
ALTER TABLE pp_order_workflow ADD CONSTRAINT ppordernode_pporderworkflow FOREIGN KEY(pp_order_node_id) REFERENCES pp_order_node DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE pp_order_workflow ADD CONSTRAINT sresource_pporderworkflow FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE pp_order_workflow_trl ADD CONSTRAINT adlangu_pporderworkflowtrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE pp_order_workflow_trl ADD CONSTRAINT pporderworkflow_pporderworkflo FOREIGN KEY(pp_order_workflow_id) REFERENCES pp_order_workflow DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_bom ADD CONSTRAINT cuom_ppproductbom FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
			      
ALTER TABLE pp_product_bom ADD CONSTRAINT mchangenotice_ppproductbom FOREIGN KEY(m_changenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE pp_product_bom ADD CONSTRAINT mproduct_ppproductbom FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
		  
ALTER TABLE pp_product_bom_trl ADD CONSTRAINT adlangu_ppproductbomtrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE pp_product_bom_trl ADD CONSTRAINT ppproductbom_ppproductbomtrl FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_bomline ADD CONSTRAINT cuom_ppproductbomline FOREIGN KEY(c_uom_id) REFERENCES c_uom DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE pp_product_bomline ADD CONSTRAINT mchangenotice_ppproductbomline FOREIGN KEY(m_changenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_bomline ADD CONSTRAINT mproduct_ppproductbomline FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE pp_product_bomline ADD CONSTRAINT ppproductbom_ppproductbomline FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_bomline_trl ADD CONSTRAINT adlangu_ppproductbomlinetrl FOREIGN KEY(ad_language) REFERENCES ad_language DEFERRABLE INITIALLY DEFERRED;
   
ALTER TABLE pp_product_bomline_trl ADD CONSTRAINT ppproductbomline_ppproductboml FOREIGN KEY(pp_product_bomline_id) REFERENCES pp_product_bomline DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_planning ADD CONSTRAINT adworkflow_ppproductplanning FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE pp_product_planning ADD CONSTRAINT ddnetworkdistribution_ppproduc FOREIGN KEY(dd_networkdistribution_id) REFERENCES dd_networkdistribution DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_planning ADD CONSTRAINT mproduct_ppproductplanning FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE pp_product_planning ADD CONSTRAINT mwarehouse_ppproductplanning FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
  
ALTER TABLE pp_product_planning ADD CONSTRAINT planner_ppproductplanning FOREIGN KEY(planner_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pp_product_planning ADD CONSTRAINT ppproductbom_ppproductplanning FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE pp_product_planning ADD CONSTRAINT sresource_ppproductplanning FOREIGN KEY(s_resource_id) REFERENCES s_resource DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE pp_wf_node_asset ADD CONSTRAINT aasset_ppwfnodeasset FOREIGN KEY(a_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
		     
ALTER TABLE pp_wf_node_asset ADD CONSTRAINT adwfnode_ppwfnodeasset FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE pp_wf_node_product ADD CONSTRAINT adwfnode_ppwfnodeproduct FOREIGN KEY(ad_wf_node_id) REFERENCES ad_wf_node DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE pp_wf_node_product ADD CONSTRAINT entityt_ppwfnodeproduct FOREIGN KEY(entitytype) REFERENCES ad_entitytype DEFERRABLE INITIALLY DEFERRED;
	  
ALTER TABLE pp_wf_node_product ADD CONSTRAINT mproduct_ppwfnodeproduct FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE qm_specification ADD CONSTRAINT adworkflow_qmspecification FOREIGN KEY(ad_workflow_id) REFERENCES ad_workflow DEFERRABLE INITIALLY DEFERRED;
       
ALTER TABLE qm_specification ADD CONSTRAINT mattributeset_qmspecification FOREIGN KEY(m_attributeset_id) REFERENCES m_attributeset DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE qm_specification ADD CONSTRAINT mproduct_qmspecification FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE qm_specification ADD CONSTRAINT ppproductbom_qmspecification FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE qm_specificationline ADD CONSTRAINT mattribute_qmspecificationline FOREIGN KEY(m_attribute_id) REFERENCES m_attribute DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE qm_specificationline ADD CONSTRAINT qmspecification_qmspecificatio FOREIGN KEY(qm_specification_id) REFERENCES qm_specification DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE r_category ADD CONSTRAINT mproduct_rcategory FOREIGN KEY(m_product_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE r_group ADD CONSTRAINT mbom_rgroup FOREIGN KEY(m_bom_id) REFERENCES m_bom DEFERRABLE INITIALLY DEFERRED;
					   
ALTER TABLE r_group ADD CONSTRAINT mchangenotice_rgroup FOREIGN KEY(m_changenotice_id) REFERENCES m_changenotice DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE r_group ADD CONSTRAINT ppproductbom_rgroup FOREIGN KEY(pp_product_bom_id) REFERENCES pp_product_bom DEFERRABLE INITIALLY DEFERRED;
		 
ALTER TABLE r_issueknown ADD CONSTRAINT rissuerecommendation_rissuekno FOREIGN KEY(r_issuerecommendation_id) REFERENCES r_issuerecommendation DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE r_issuesystem ADD CONSTRAINT aasset_rissuesystem FOREIGN KEY(a_asset_id) REFERENCES a_asset DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE r_request ADD CONSTRAINT mchangerequest_rrequest FOREIGN KEY(m_changerequest_id) REFERENCES m_changerequest DEFERRABLE INITIALLY DEFERRED;
	 
ALTER TABLE r_requestaction ADD CONSTRAINT mproductspent_rrequestaction FOREIGN KEY(m_productspent_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE r_requestprocessor ADD CONSTRAINT rrequesttype_rrequestprocessor FOREIGN KEY(r_requesttype_id) REFERENCES r_requesttype DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE r_requesttype ADD CONSTRAINT rstatuscategory_rrequesttype FOREIGN KEY(r_statuscategory_id) REFERENCES r_statuscategory DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE r_requestupdate ADD CONSTRAINT mproductspent_rrequestupdate FOREIGN KEY(m_productspent_id) REFERENCES m_product DEFERRABLE INITIALLY DEFERRED;
     
ALTER TABLE r_status ADD CONSTRAINT nextstatus_rstatus FOREIGN KEY(next_status_id) REFERENCES r_status DEFERRABLE INITIALLY DEFERRED;
			  
ALTER TABLE r_status ADD CONSTRAINT rstatuscategory_rstatus FOREIGN KEY(r_statuscategory_id) REFERENCES r_statuscategory DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE r_status ADD CONSTRAINT updatestatus_rstatus FOREIGN KEY(update_status_id) REFERENCES r_status DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE u_posterminal ADD CONSTRAINT cardbankaccount_uposterminal FOREIGN KEY(card_bankaccount_id) REFERENCES c_bankaccount DEFERRABLE INITIALLY DEFERRED;
 
ALTER TABLE u_posterminal ADD CONSTRAINT cardtransferbankaccount_uposte FOREIGN KEY(cardtransferbankaccount_id) REFERENCES c_bankaccount DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT cardtransfercashbook_upostermi FOREIGN KEY(cardtransfercashbook_id) REFERENCES c_cashbook DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT cashtransferbankaccount_uposte FOREIGN KEY(cashtransferbankaccount_id) REFERENCES c_bankaccount DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT cashtransfercashbook_upostermi FOREIGN KEY(cashtransfercashbook_id) REFERENCES c_cashbook DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT ccashbook_uposterminal FOREIGN KEY(c_cashbook_id) REFERENCES c_cashbook DEFERRABLE INITIALLY DEFERRED;
		
ALTER TABLE u_posterminal ADD CONSTRAINT ccashbpartner_uposterminal FOREIGN KEY(c_cashbpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;
	
ALTER TABLE u_posterminal ADD CONSTRAINT checkbankaccount_uposterminal FOREIGN KEY(check_bankaccount_id) REFERENCES c_bankaccount DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT checktransferbankaccount_upost FOREIGN KEY(checktransferbankaccount_id) REFERENCES c_bankaccount DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT checktransfercashbook_uposterm FOREIGN KEY(checktransfercashbook_id) REFERENCES c_cashbook DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT ctemplatebpartner_uposterminal FOREIGN KEY(c_templatebpartner_id) REFERENCES c_bpartner DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE u_posterminal ADD CONSTRAINT mwarehouse_uposterminal FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
	     
ALTER TABLE u_posterminal ADD CONSTRAINT popricelist_uposterminal FOREIGN KEY(po_pricelist_id) REFERENCES m_pricelist DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE u_posterminal ADD CONSTRAINT salesrep_uposterminal FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
		      
ALTER TABLE u_posterminal ADD CONSTRAINT sopricelist_uposterminal FOREIGN KEY(so_pricelist_id) REFERENCES m_pricelist DEFERRABLE INITIALLY DEFERRED;
	   
ALTER TABLE u_rolemenu ADD CONSTRAINT adrole_urolemenu FOREIGN KEY(ad_role_id) REFERENCES ad_role DEFERRABLE INITIALLY DEFERRED;
			       
ALTER TABLE u_rolemenu ADD CONSTRAINT uwebmenu_urolemenu FOREIGN KEY(u_webmenu_id) REFERENCES u_webmenu DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE u_webmenu ADD CONSTRAINT parentmenu_uwebmenu FOREIGN KEY(parentmenu_id) REFERENCES u_webmenu DEFERRABLE INITIALLY DEFERRED;
			
ALTER TABLE w_store ADD CONSTRAINT cpaymentterm_wstore FOREIGN KEY(c_paymentterm_id) REFERENCES c_paymentterm DEFERRABLE INITIALLY DEFERRED;
		   
ALTER TABLE w_store ADD CONSTRAINT mpricelist_wstore FOREIGN KEY(m_pricelist_id) REFERENCES m_pricelist DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE w_store ADD CONSTRAINT mwarehouse_wstore FOREIGN KEY(m_warehouse_id) REFERENCES m_warehouse DEFERRABLE INITIALLY DEFERRED;
			 
ALTER TABLE w_store ADD CONSTRAINT salesrep_wstore FOREIGN KEY(salesrep_id) REFERENCES ad_user DEFERRABLE INITIALLY DEFERRED;
				  

