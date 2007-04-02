ALTER TABLE "w_store_trl"
    DROP CONSTRAINT "wstoretrl_adlangauge";

ALTER TABLE "w_store_trl"
    DROP CONSTRAINT "wstore_wstoretrl";

ALTER TABLE "w_store"
    DROP CONSTRAINT "adclient_wstore";

ALTER TABLE "w_mailmsg_trl"
    DROP CONSTRAINT "adlanguage_wmailmsgtrl";

ALTER TABLE "w_mailmsg_trl"
    DROP CONSTRAINT "wmailmsg_wmailmsgtrl";

ALTER TABLE "w_mailmsg"
    DROP CONSTRAINT "wstore_wmailmsg";

ALTER TABLE "w_countercount"
    DROP CONSTRAINT "cbpartner_wcountercount";

ALTER TABLE "w_counter"
    DROP CONSTRAINT "aduser_wcounter";

ALTER TABLE "w_counter"
    DROP CONSTRAINT "wcountercount_wcounter";

ALTER TABLE "w_clickcount"
    DROP CONSTRAINT "cbpartner_wclickcount";

ALTER TABLE "w_click"
    DROP CONSTRAINT "aduser_wclick";

ALTER TABLE "w_click"
    DROP CONSTRAINT "wclickcount_wclick";

ALTER TABLE "w_basketline"
    DROP CONSTRAINT "mproduct_wbasketline";

ALTER TABLE "w_basketline"
    DROP CONSTRAINT "wbasket_wbasketline";

ALTER TABLE "w_basket"
    DROP CONSTRAINT "aduser_wbasket";

ALTER TABLE "w_basket"
    DROP CONSTRAINT "cbpartner_wbasket";

ALTER TABLE "w_basket"
    DROP CONSTRAINT "mpricelist_wbasket";

ALTER TABLE "w_advertisement"
    DROP CONSTRAINT "aduser_wadvertisement";

ALTER TABLE "w_advertisement"
    DROP CONSTRAINT "cbpartner_wadvertisement";

ALTER TABLE "w_advertisement"
    DROP CONSTRAINT "wclickcount_wadvertisement";

ALTER TABLE "w_advertisement"
    DROP CONSTRAINT "wcountercount_wadvertisement";

ALTER TABLE "t_trialbalance"
    DROP CONSTRAINT "ad_pinstance_t_trialbalance";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "adpinstance_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "cprojectissue_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "masi_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "minoutline_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "minventoryline_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "mlocator_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "mmovementline_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "mproduct_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "mproductionline_ttransaction";

ALTER TABLE "t_transaction"
    DROP CONSTRAINT "mtransaction_ttransaction";

ALTER TABLE "t_spool"
    DROP CONSTRAINT "adpinstance_tspool";

ALTER TABLE "t_reportstatement"
    DROP CONSTRAINT "adpinstance_treportstatement";

ALTER TABLE "t_report"
    DROP CONSTRAINT "adpinstance_treport";

ALTER TABLE "t_report"
    DROP CONSTRAINT "pareportline_treport";

ALTER TABLE "t_replenish"
    DROP CONSTRAINT "adpinstance_treplenish";

ALTER TABLE "t_replenish"
    DROP CONSTRAINT "cdoctype_treplenish";

ALTER TABLE "t_replenish"
    DROP CONSTRAINT "mproduct_treplenish";

ALTER TABLE "t_replenish"
    DROP CONSTRAINT "mwarehousesource_treplenish";

ALTER TABLE "t_replenish"
    DROP CONSTRAINT "mwarehouse_treplenish";

ALTER TABLE "t_invoicegl"
    DROP CONSTRAINT "adpinstance_tinvoicegl";

ALTER TABLE "t_invoicegl"
    DROP CONSTRAINT "cconversiontype_tinvoicegl";

ALTER TABLE "t_invoicegl"
    DROP CONSTRAINT "cdoctype_tinvoicegl";

ALTER TABLE "t_invoicegl"
    DROP CONSTRAINT "cinvoice_tinvoicegl";

ALTER TABLE "t_invoicegl"
    DROP CONSTRAINT "factacct_tinvoicegl";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "adpinstance_tinventoryvalue";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "ccurrency_tinventoryvalue";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "masi_tinventoryvalue";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "mcostelement_tinventoryvalue";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "mplversion_tinventoryvalue";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "mproduct_tinventoryvalue";

ALTER TABLE "t_inventoryvalue"
    DROP CONSTRAINT "mwarehouse_tinventoryvalue";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "cbpartner_tdrdetail";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "cbpartnerlocation_tdrdetail";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "mdistributionlist_tdrdetail";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "mdistributionlline_tdrdetail";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "mdistributionrun_tdrdetail";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "mdistributionrline_tdrdetail";

ALTER TABLE "t_distributionrundetail"
    DROP CONSTRAINT "mproduct_tdrdetail";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "adpinstance_taging";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "cactivity_taging";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "cbpartner_taging";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "cbpgroup_taging";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "ccampaign_taging";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "ccurrency_taging";

ALTER TABLE "t_aging"
    DROP CONSTRAINT "cproject_taging";

ALTER TABLE "test"
    DROP CONSTRAINT "vc_account_test";

ALTER TABLE "s_training_class"
    DROP CONSTRAINT "mproduct_strainingclass";

ALTER TABLE "s_training_class"
    DROP CONSTRAINT "straining_strainingclass";

ALTER TABLE "s_training"
    DROP CONSTRAINT "ctaxcategory_straining";

ALTER TABLE "s_training"
    DROP CONSTRAINT "cuom_straining";

ALTER TABLE "s_training"
    DROP CONSTRAINT "mproductcategory_straining";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cactivity_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cbpartner_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "ccampaign_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "ccurrency_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cinvoiceline_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "corderline_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cproject_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cprojectphase_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cprojecttask_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "cuom_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "mproduct_stimeexpenseline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "sresourceassign_steline";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "stimeexpense_line";

ALTER TABLE "s_timeexpenseline"
    DROP CONSTRAINT "stimetype_stimeexpenseline";

ALTER TABLE "s_timeexpense"
    DROP CONSTRAINT "cbpartner_stimeexpense";

ALTER TABLE "s_timeexpense"
    DROP CONSTRAINT "mpricelist_stimeexpense";

ALTER TABLE "s_timeexpense"
    DROP CONSTRAINT "mwarehouse_stimeexpense";

ALTER TABLE "s_resourceunavailable"
    DROP CONSTRAINT "sresource_sresunavailable";

ALTER TABLE "s_resourcetype"
    DROP CONSTRAINT "ctaxcategory_sresourcetype";

ALTER TABLE "s_resourcetype"
    DROP CONSTRAINT "cuom_sresourcetype";

ALTER TABLE "s_resourcetype"
    DROP CONSTRAINT "mprodcategory_sresourcetype";

ALTER TABLE "s_resourceassignment"
    DROP CONSTRAINT "sresource_sresourceassignment";

ALTER TABLE "s_resource"
    DROP CONSTRAINT "aduser_sresource";

ALTER TABLE "s_resource"
    DROP CONSTRAINT "mwarehouse_sresource";

ALTER TABLE "s_resource"
    DROP CONSTRAINT "sresourcetype_sresource";

ALTER TABLE "s_expensetype"
    DROP CONSTRAINT "ctaxcategory_sexpensetype";

ALTER TABLE "s_expensetype"
    DROP CONSTRAINT "cuom_sexpensetype";

ALTER TABLE "s_expensetype"
    DROP CONSTRAINT "mproductcategory_sexpensetype";

ALTER TABLE "r_requestupdates"
    DROP CONSTRAINT "aduser_rrequestupdates";

ALTER TABLE "r_requestupdates"
    DROP CONSTRAINT "rrequest_rrupdates";

ALTER TABLE "r_requestupdate"
    DROP CONSTRAINT "rrequest_rrequestupdate";

ALTER TABLE "r_requesttypeupdates"
    DROP CONSTRAINT "aduser_rrequesttypeupdates";

ALTER TABLE "r_requesttypeupdates"
    DROP CONSTRAINT "rrequesttype_rrtupdates";

ALTER TABLE "r_requestprocessor_route"
    DROP CONSTRAINT "aduser_rrequestprocessorroute";

ALTER TABLE "r_requestprocessor_route"
    DROP CONSTRAINT "rrequestprocessor_route";

ALTER TABLE "r_requestprocessor_route"
    DROP CONSTRAINT "rrequesttype_rprocessorrule";

ALTER TABLE "r_requestprocessorlog"
    DROP CONSTRAINT "rrequestprocessor_log";

ALTER TABLE "r_requestprocessor"
    DROP CONSTRAINT "aduser_rrequestprocessor";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "adrole_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "adusersr_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "aduser_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "aasset_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "cactivity_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "cbpartner_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "cinvoice_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "corder_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "cpayment_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "cproject_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "minout_mrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "mproduct_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "mrma_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "rcategory_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "rgroup_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "rrequest_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "rrequesttype_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "rresolution_rrequestaction";

ALTER TABLE "r_requestaction"
    DROP CONSTRAINT "rstatus_rrequestaction";

ALTER TABLE "r_request"
    DROP CONSTRAINT "adrole_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "adtable_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "adusersr_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "aduser_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "aasset_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "cactivity_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "c_bpartner_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "ccampaign_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rinvoice_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "cinvoicerequest_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "corder_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "cpayment_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "cproject_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "mfixchangenotice_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "minout_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "mproductspent_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "mproduct_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "mrma_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rcategory_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rgroup_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rmailtext_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rrequest_related";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rrequesttype_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rresolution_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rstandardresponse_rrequest";

ALTER TABLE "r_request"
    DROP CONSTRAINT "rstatus_rrequest";

ALTER TABLE "r_mailtext_trl"
    DROP CONSTRAINT "adlanguage_rmailtexttrl";

ALTER TABLE "r_mailtext_trl"
    DROP CONSTRAINT "rmailtext_rmailtexttrl";

ALTER TABLE "r_issueuser"
    DROP CONSTRAINT "aduser_rissueuser";

ALTER TABLE "r_issuesource"
    DROP CONSTRAINT "rissueproject_rissuesource";

ALTER TABLE "r_issuesource"
    DROP CONSTRAINT "rissuesystem_rissuesource";

ALTER TABLE "r_issuesource"
    DROP CONSTRAINT "rissueuser_rissuesource";

ALTER TABLE "r_issueproject"
    DROP CONSTRAINT "aasset_rissueproject";

ALTER TABLE "r_issueproject"
    DROP CONSTRAINT "cproject_rissueproject";

ALTER TABLE "r_issueknown"
    DROP CONSTRAINT "rissuestatus_rissueknown";

ALTER TABLE "r_issueknown"
    DROP CONSTRAINT "rrequest_rissueknown";

ALTER TABLE "r_groupupdates"
    DROP CONSTRAINT "aduser_ruserupdates";

ALTER TABLE "r_groupupdates"
    DROP CONSTRAINT "rgroup_rgroupupdates";

ALTER TABLE "r_contactinterest"
    DROP CONSTRAINT "aduser_rcontactinterest";

ALTER TABLE "r_contactinterest"
    DROP CONSTRAINT "rinterestarea_rcontactinterest";

ALTER TABLE "r_categoryupdates"
    DROP CONSTRAINT "aduser_rcategoryupdates";

ALTER TABLE "r_categoryupdates"
    DROP CONSTRAINT "rcategory_rcategoryupdates";

ALTER TABLE "pa_sla_measure"
    DROP CONSTRAINT "adtable_paslameasure";

ALTER TABLE "pa_sla_measure"
    DROP CONSTRAINT "paslagoal_paslameasure";

ALTER TABLE "pa_sla_goal"
    DROP CONSTRAINT "cbpartner_paslagoal";

ALTER TABLE "pa_sla_goal"
    DROP CONSTRAINT "paslacriteria_paslagoal";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "adorg_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "cactivity_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "cbpartner_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "ccampaign_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "celementvalue_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "clocation_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "cproject_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "csalesregion_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "mproduct_pareportsource";

ALTER TABLE "pa_reportsource"
    DROP CONSTRAINT "pareportline_pareportsource";

ALTER TABLE "pa_reportline"
    DROP CONSTRAINT "glbudget_pareportline";

ALTER TABLE "pa_reportline"
    DROP CONSTRAINT "pareportline_oper2";

ALTER TABLE "pa_reportline"
    DROP CONSTRAINT "pareportline_oper1";

ALTER TABLE "pa_reportline"
    DROP CONSTRAINT "pareportline_parent";

ALTER TABLE "pa_reportline"
    DROP CONSTRAINT "pareportlineset_line";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "adorg_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "cactivity_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "cbpartner_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "ccampaign_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "ccurrency_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "celementvalue_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "clocation_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "cproject_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "csalesregion_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "glbudget_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "mproduct_pareportcolumn";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "pareportcolumn_oper1";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "pareportcolumn_oper2";

ALTER TABLE "pa_reportcolumn"
    DROP CONSTRAINT "pareportcolumnset_column";

ALTER TABLE "pa_report"
    DROP CONSTRAINT "adorg_pareport";

ALTER TABLE "pa_report"
    DROP CONSTRAINT "adprintformat_pareport";

ALTER TABLE "pa_report"
    DROP CONSTRAINT "cacctschema_pareport";

ALTER TABLE "pa_report"
    DROP CONSTRAINT "ccalendar_pareport";

ALTER TABLE "pa_report"
    DROP CONSTRAINT "pareport_columnset";

ALTER TABLE "pa_report"
    DROP CONSTRAINT "pareport_lineset";

ALTER TABLE "pa_ratioelement"
    DROP CONSTRAINT "celementvalue_paratioelement";

ALTER TABLE "pa_ratioelement"
    DROP CONSTRAINT "pameasurecalc_paratioelement";

ALTER TABLE "pa_ratioelement"
    DROP CONSTRAINT "paratio_paratioelement";

ALTER TABLE "pa_ratioelement"
    DROP CONSTRAINT "paratioused_paratioelement";

ALTER TABLE "pa_ratio"
    DROP CONSTRAINT "cacctschema_paratio";

ALTER TABLE "pa_measure"
    DROP CONSTRAINT "pabenchmark_pameasure";

ALTER TABLE "pa_measure"
    DROP CONSTRAINT "pahierarchy_pameasure";

ALTER TABLE "pa_measure"
    DROP CONSTRAINT "pameasurecalc_pameasure";

ALTER TABLE "pa_measure"
    DROP CONSTRAINT "paratio_pameasure";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreeaccount_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreeactivity_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreebpartner_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreesr_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreeorg_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreeproduct_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreeproject_pahierarchy";

ALTER TABLE "pa_hierarchy"
    DROP CONSTRAINT "adtreecampaign_pahierarchy";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "adorg_pagoalrestriction";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "adorg2_pagoalrestriction";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "cbpartner_pagoalrestriction";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "cbpgroup_pagoalrestriction";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "mproduct_pagoalrestriction";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "mproductcat_pagoalrestriction";

ALTER TABLE "pa_goalrestriction"
    DROP CONSTRAINT "pagoal_pagoalrestriction";

ALTER TABLE "pa_goal"
    DROP CONSTRAINT "aduser_pagoal";

ALTER TABLE "pa_goal"
    DROP CONSTRAINT "pacolorschema_pagoal";

ALTER TABLE "pa_goal"
    DROP CONSTRAINT "pagoalparent_pagoal";

ALTER TABLE "pa_goal"
    DROP CONSTRAINT "pameasure_pagoal";

ALTER TABLE "pa_colorschema"
    DROP CONSTRAINT "adprintcolor1_pacolorschema";

ALTER TABLE "pa_colorschema"
    DROP CONSTRAINT "adprintcolor4_pacolorschema";

ALTER TABLE "pa_colorschema"
    DROP CONSTRAINT "adprintcolor3_pacolorschema";

ALTER TABLE "pa_colorschema"
    DROP CONSTRAINT "adprintcolor2_pacolorschema";

ALTER TABLE "pa_benchmarkdata"
    DROP CONSTRAINT "pabenchmark_pabenchmarkdata";

ALTER TABLE "pa_achievement"
    DROP CONSTRAINT "pameasure_paachievement";

ALTER TABLE "m_warehouse_acct"
    DROP CONSTRAINT "cacctschema_mwarehouseacct";

ALTER TABLE "m_warehouse_acct"
    DROP CONSTRAINT "vc_winventory_mwarehouse";

ALTER TABLE "m_warehouse_acct"
    DROP CONSTRAINT "vc_winvactualadjust_mwarehouse";

ALTER TABLE "m_warehouse_acct"
    DROP CONSTRAINT "vc_wrevaluation_mwarehouse";

ALTER TABLE "m_warehouse_acct"
    DROP CONSTRAINT "vc_wdifferences_mwarehouse";

ALTER TABLE "m_warehouse_acct"
    DROP CONSTRAINT "m_warehouse_warehouse_acct";

ALTER TABLE "m_warehouse"
    DROP CONSTRAINT "m_warehouse_client";

ALTER TABLE "m_warehouse"
    DROP CONSTRAINT "m_warehouse_org";

ALTER TABLE "m_warehouse"
    DROP CONSTRAINT "c_location_warehouse";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "mattributesetinst_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "minoutline_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "minoutlineout_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "minventoryline_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "minventorylineout_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "mproduct_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "mproductionlineout_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "mproductionline_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "mttransaction_mtrxalloc";

ALTER TABLE "m_transactionallocation"
    DROP CONSTRAINT "mtransactionout_mtrxalloc";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "cprojectissue_mtransaction";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "mattrsetinst_mtransaction";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "minoutline_mtransaction";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "minventoryline_mtransaction";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "mlocator_minventorycount";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "mmovementline_mtransaction";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "mproduct_minventorycount";

ALTER TABLE "m_transaction"
    DROP CONSTRAINT "mproductionline_mtransaction";

ALTER TABLE "m_substitute"
    DROP CONSTRAINT "m_substitute_client";

ALTER TABLE "m_substitute"
    DROP CONSTRAINT "m_substitute_org";

ALTER TABLE "m_substitute"
    DROP CONSTRAINT "mproduct_substitutesub";

ALTER TABLE "m_substitute"
    DROP CONSTRAINT "mproduct_substitute";

ALTER TABLE "m_storage"
    DROP CONSTRAINT "m_item_storage_client";

ALTER TABLE "m_storage"
    DROP CONSTRAINT "m_item_storage_org";

ALTER TABLE "m_storage"
    DROP CONSTRAINT "mattrsetinst_mstorage";

ALTER TABLE "m_storage"
    DROP CONSTRAINT "m_locator_storage";

ALTER TABLE "m_storage"
    DROP CONSTRAINT "mproduct_storage";

ALTER TABLE "m_shipper"
    DROP CONSTRAINT "cbpartner_mshipper";

ALTER TABLE "m_sernoctlexclude"
    DROP CONSTRAINT "adtable_msernoctlexclude";

ALTER TABLE "m_sernoctlexclude"
    DROP CONSTRAINT "msernoctl_msernoctlexclude";

ALTER TABLE "m_rmaline"
    DROP CONSTRAINT "minoutline_mrmaline";

ALTER TABLE "m_rmaline"
    DROP CONSTRAINT "mrma_mrmaline";

ALTER TABLE "m_rma"
    DROP CONSTRAINT "minout_mrma";

ALTER TABLE "m_requisitionline"
    DROP CONSTRAINT "mproduct_mrequisitionline";

ALTER TABLE "m_requisitionline"
    DROP CONSTRAINT "mrequisition_mrequisitionline";

ALTER TABLE "m_requisition"
    DROP CONSTRAINT "aduser_mrequisition";

ALTER TABLE "m_requisition"
    DROP CONSTRAINT "mprocelist_mrequisition";

ALTER TABLE "m_requisition"
    DROP CONSTRAINT "mwarehouse_mrequisition";

ALTER TABLE "m_replenish"
    DROP CONSTRAINT "m_product_replenish";

ALTER TABLE "m_replenish"
    DROP CONSTRAINT "m_warehouse_replenish";

ALTER TABLE "m_relatedproduct"
    DROP CONSTRAINT "mproduct_mrelated_product";

ALTER TABLE "m_relatedproduct"
    DROP CONSTRAINT "mproduct_mrelatedproduct";

ALTER TABLE "m_product_trl"
    DROP CONSTRAINT "adlanguage_mproducttrl";

ALTER TABLE "m_product_trl"
    DROP CONSTRAINT "mproduct_mproducttrl";

ALTER TABLE "m_product_po"
    DROP CONSTRAINT "m_productpo_client";

ALTER TABLE "m_product_po"
    DROP CONSTRAINT "m_productpo_org";

ALTER TABLE "m_product_po"
    DROP CONSTRAINT "c_buspartner_m_product_po";

ALTER TABLE "m_product_po"
    DROP CONSTRAINT "ccurrency_mproductpo";

ALTER TABLE "m_product_po"
    DROP CONSTRAINT "c_uom_m_product_po";

ALTER TABLE "m_product_po"
    DROP CONSTRAINT "m_product_productpo";

ALTER TABLE "m_product_costing"
    DROP CONSTRAINT "cacctschema_mproductcosting";

ALTER TABLE "m_product_costing"
    DROP CONSTRAINT "mproduct_mproductcosting";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "cacctschema_mprodcatacct";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_pinvoicepv_mproductcategory";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_pcogs_mproductcategory";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_pexpense_mproductcategory";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_passet_mproductcategory";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_ppurchasepv_mproductcategor";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_prevenue_mproductcategory";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_ptdiscountgrant_mproductcat";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "vc_ptdiscountrec_mproductcateg";

ALTER TABLE "m_product_category_acct"
    DROP CONSTRAINT "mprodcat_mprodcatacct";

ALTER TABLE "m_product_category"
    DROP CONSTRAINT "aassetgroup_mproductcategory";

ALTER TABLE "m_product_bom"
    DROP CONSTRAINT "mproduct_mproductbom";

ALTER TABLE "m_product_bom"
    DROP CONSTRAINT "mproduct_bomproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "cacctschema_mproductacct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_pcogs_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_pexpense_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_passet_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_pinvoicepv_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_ppurchasepv_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_prevenue_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_ptdiscountgrant_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "vc_ptdiscountrec_mproduct";

ALTER TABLE "m_product_acct"
    DROP CONSTRAINT "m_product_m_product_acct";

ALTER TABLE "m_productprice"
    DROP CONSTRAINT "mpricelistver_mproductprice";

ALTER TABLE "m_productprice"
    DROP CONSTRAINT "mproduct_mproductprice";

ALTER TABLE "m_productoperation"
    DROP CONSTRAINT "mproduct_mproductoperation";

ALTER TABLE "m_productionplan"
    DROP CONSTRAINT "mlocator_mproductionplan";

ALTER TABLE "m_productionplan"
    DROP CONSTRAINT "mproduct_mproductionplan";

ALTER TABLE "m_productionplan"
    DROP CONSTRAINT "mproduction_plan";

ALTER TABLE "m_productionlinema"
    DROP CONSTRAINT "masi_mproductionlinema";

ALTER TABLE "m_productionlinema"
    DROP CONSTRAINT "mproductionline_mplinema";

ALTER TABLE "m_productionline"
    DROP CONSTRAINT "mattrsetinst_mproductionline";

ALTER TABLE "m_productionline"
    DROP CONSTRAINT "mlocator_mproductionline";

ALTER TABLE "m_productionline"
    DROP CONSTRAINT "mproduct_mproductionline";

ALTER TABLE "m_productionline"
    DROP CONSTRAINT "mproductionplan_line";

ALTER TABLE "m_production"
    DROP CONSTRAINT "adorg_mproduction";

ALTER TABLE "m_production"
    DROP CONSTRAINT "adorgtrx_mproduction";

ALTER TABLE "m_production"
    DROP CONSTRAINT "cactivity_mproduction";

ALTER TABLE "m_production"
    DROP CONSTRAINT "ccampaign_mproduction";

ALTER TABLE "m_production"
    DROP CONSTRAINT "celementvalueuser2_mprod";

ALTER TABLE "m_production"
    DROP CONSTRAINT "celementvalueuser1_mprod";

ALTER TABLE "m_production"
    DROP CONSTRAINT "cproject_mproduction";

ALTER TABLE "m_productdownload"
    DROP CONSTRAINT "mproduct_mproductdownload";

ALTER TABLE "m_product"
    DROP CONSTRAINT "adclient_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "ad_org_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "crevrecognition_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "csubscriptiontype_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "ctaxcategory_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "cuom_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "mattributeset_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "mattrsetinst_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "mfreightcategory_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "mlocator_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "mproduct_mproductcategory";

ALTER TABLE "m_product"
    DROP CONSTRAINT "rmailtext_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "sexpensetype_mproduct";

ALTER TABLE "m_product"
    DROP CONSTRAINT "sresource_mproduct";

ALTER TABLE "m_pricelist_version"
    DROP CONSTRAINT "mdiscounts_mplversion";

ALTER TABLE "m_pricelist_version"
    DROP CONSTRAINT "mpricelist_mpricelistversion";

ALTER TABLE "m_pricelist"
    DROP CONSTRAINT "ccurrency_mpricelist";

ALTER TABLE "m_pricelist"
    DROP CONSTRAINT "basepricelist";

ALTER TABLE "m_perpetualinv"
    DROP CONSTRAINT "mprodcategory_mperpetualinv";

ALTER TABLE "m_perpetualinv"
    DROP CONSTRAINT "mwarehouse_mperpetualinv";

ALTER TABLE "m_packageline"
    DROP CONSTRAINT "minoutline_mpackageline";

ALTER TABLE "m_packageline"
    DROP CONSTRAINT "mpackage_mpackageline";

ALTER TABLE "m_package"
    DROP CONSTRAINT "minout_mpackage";

ALTER TABLE "m_package"
    DROP CONSTRAINT "mshipper_mpackage";

ALTER TABLE "m_operationresource"
    DROP CONSTRAINT "aasset_moperationresource";

ALTER TABLE "m_operationresource"
    DROP CONSTRAINT "cjob_moperationresource";

ALTER TABLE "m_operationresource"
    DROP CONSTRAINT "mproductop_mopresource";

ALTER TABLE "m_movementlinema"
    DROP CONSTRAINT "masi_mmovementlinema";

ALTER TABLE "m_movementlinema"
    DROP CONSTRAINT "mmovementline_mmovementlinema";

ALTER TABLE "m_movementlineconfirm"
    DROP CONSTRAINT "minventoryline_mmovlineconfirm";

ALTER TABLE "m_movementlineconfirm"
    DROP CONSTRAINT "mmovementconfirm_mmovlineconf";

ALTER TABLE "m_movementlineconfirm"
    DROP CONSTRAINT "mmovementline_mmovlineconfirm";

ALTER TABLE "m_movementline"
    DROP CONSTRAINT "mattrsetinst_mmovementline";

ALTER TABLE "m_movementline"
    DROP CONSTRAINT "mlocatorto_mmovementline";

ALTER TABLE "m_movementline"
    DROP CONSTRAINT "mlocator_movementline";

ALTER TABLE "m_movementline"
    DROP CONSTRAINT "mmovement_mmovementline";

ALTER TABLE "m_movementline"
    DROP CONSTRAINT "mproduct_mmovementline";

ALTER TABLE "m_movementconfirm"
    DROP CONSTRAINT "minventory_mmovconfirm";

ALTER TABLE "m_movementconfirm"
    DROP CONSTRAINT "mmovement_mmovementconfirm";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "adorgtrx_mmovement";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "adorg_mmovement";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "cactivity_mmovement";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "ccampaign_mmovement";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "celementvalueuser2_mmove";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "celementvalueuser1_mmove";

ALTER TABLE "m_movement"
    DROP CONSTRAINT "cproject_mmovement";

ALTER TABLE "m_matchpo"
    DROP CONSTRAINT "cinvoiceline_mmatchpo";

ALTER TABLE "m_matchpo"
    DROP CONSTRAINT "corderline_mmatchpo";

ALTER TABLE "m_matchpo"
    DROP CONSTRAINT "minoutline_mmatchpo";

ALTER TABLE "m_matchpo"
    DROP CONSTRAINT "mproduct_mmatchpo";

ALTER TABLE "m_matchinv"
    DROP CONSTRAINT "convoiceline_mmatchinv";

ALTER TABLE "m_matchinv"
    DROP CONSTRAINT "minoutline_mmatchinv";

ALTER TABLE "m_matchinv"
    DROP CONSTRAINT "mproduct_mmatchinv";

ALTER TABLE "m_lotctlexclude"
    DROP CONSTRAINT "adtable_mlotctlexclude";

ALTER TABLE "m_lotctlexclude"
    DROP CONSTRAINT "mlotctl_mlotctlexclude";

ALTER TABLE "m_lot"
    DROP CONSTRAINT "mlotctl_mlot";

ALTER TABLE "m_lot"
    DROP CONSTRAINT "mproduct_mlot";

ALTER TABLE "m_locator"
    DROP CONSTRAINT "m_wh_locator_client";

ALTER TABLE "m_locator"
    DROP CONSTRAINT "m_wh_locator_org";

ALTER TABLE "m_locator"
    DROP CONSTRAINT "m_warehouse_locator";

ALTER TABLE "m_inventorylinema"
    DROP CONSTRAINT "masi_minventorylinema";

ALTER TABLE "m_inventorylinema"
    DROP CONSTRAINT "minventoryline_milinema";

ALTER TABLE "m_inventoryline"
    DROP CONSTRAINT "ccharge_minventoryline";

ALTER TABLE "m_inventoryline"
    DROP CONSTRAINT "mattrsetinst_minventoryline";

ALTER TABLE "m_inventoryline"
    DROP CONSTRAINT "minventory_minventoryline";

ALTER TABLE "m_inventoryline"
    DROP CONSTRAINT "mlocator_minventoryline";

ALTER TABLE "m_inventoryline"
    DROP CONSTRAINT "mproduct_minventoryline";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "adorgtrx_minventory";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "adorg_minventory";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "cactivity_minventory";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "ccampaign_minventory";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "celementvalueuser1_minvent";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "celementvalueuser2_minvent";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "cproject_minventory";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "mperpetualinv_minventory";

ALTER TABLE "m_inventory"
    DROP CONSTRAINT "mwarehouse_minventory";

ALTER TABLE "m_inoutlinema"
    DROP CONSTRAINT "masi_minourlinema";

ALTER TABLE "m_inoutlinema"
    DROP CONSTRAINT "minoutline_minoutlinema";

ALTER TABLE "m_inoutlineconfirm"
    DROP CONSTRAINT "minoutconfirm_minoutlineconf";

ALTER TABLE "m_inoutlineconfirm"
    DROP CONSTRAINT "minoutline_minoutconfirm";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "adorg_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "adorgtrx_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "cactivity_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "ccampaign_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "ccharge_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "celemenrvalueuser1_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "celemenrvalueuser2_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "corderline_minout";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "cproject_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "c_projectphase_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "cprojecttask_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "cuom_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "mattrsetinst_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "minout_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "minoutline_ref";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "mlocator_minoutline";

ALTER TABLE "m_inoutline"
    DROP CONSTRAINT "mproduct_minoutline";

ALTER TABLE "m_inoutconfirm"
    DROP CONSTRAINT "minout_minoutconfirm";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "adorg_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "adorgtrx_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "aduser_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "aduser_sr_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "cactivity_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "cbpartner_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "vbplocation_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "ccampaign_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "ccharge_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "cdoctype_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "celementvalueuser1_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "celementvalueuser2_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "corder_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "cproject_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "minout_ref";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "mshipper_minout";

ALTER TABLE "m_inout"
    DROP CONSTRAINT "mwarehouse_minout";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "ccountry_mfreight";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "ccountryto_mfreight";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "ccurrency_mfreight";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "cregion_mfreight";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "cregionto_mfreight";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "mfreightcategory_mfreight";

ALTER TABLE "m_freight"
    DROP CONSTRAINT "mshipper_mfreight";

ALTER TABLE "m_forecastline"
    DROP CONSTRAINT "cperiod_mforecastline";

ALTER TABLE "m_forecastline"
    DROP CONSTRAINT "mforecast_mforecastline";

ALTER TABLE "m_forecastline"
    DROP CONSTRAINT "mproduct_mforecastline";

ALTER TABLE "m_forecast"
    DROP CONSTRAINT "ccalendar_mforecast";

ALTER TABLE "m_forecast"
    DROP CONSTRAINT "cyear_mforecast";

ALTER TABLE "m_edi_info"
    DROP CONSTRAINT "medi_ediinfo";

ALTER TABLE "m_edi"
    DROP CONSTRAINT "cbpedi_medi";

ALTER TABLE "m_edi"
    DROP CONSTRAINT "mproduct_medi";

ALTER TABLE "m_edi"
    DROP CONSTRAINT "mwarehouse_medi";

ALTER TABLE "m_distributionrunline"
    DROP CONSTRAINT "mdistributionlist_runline";

ALTER TABLE "m_distributionrunline"
    DROP CONSTRAINT "mdistributionrun_line";

ALTER TABLE "m_distributionrunline"
    DROP CONSTRAINT "mproduct_mdistributionrun";

ALTER TABLE "m_distributionrun"
    DROP CONSTRAINT "cbpartner_mdistributionrun";

ALTER TABLE "m_distributionrun"
    DROP CONSTRAINT "cbplocation_mdistributionrun";

ALTER TABLE "m_distributionlistline"
    DROP CONSTRAINT "cbpartner_mdistlistline";

ALTER TABLE "m_distributionlistline"
    DROP CONSTRAINT "cbpartnerloc_mdistlistline";

ALTER TABLE "m_distributionlistline"
    DROP CONSTRAINT "mdistributionlist_line";

ALTER TABLE "m_discountschemaline"
    DROP CONSTRAINT "cbpartner_mdiscountsline";

ALTER TABLE "m_discountschemaline"
    DROP CONSTRAINT "cconversiontype_mdiscschline";

ALTER TABLE "m_discountschemaline"
    DROP CONSTRAINT "mdiscountschema_mdsline";

ALTER TABLE "m_discountschemaline"
    DROP CONSTRAINT "mproduct_mdiscountsline";

ALTER TABLE "m_discountschemaline"
    DROP CONSTRAINT "mprodcategory_mdiscountsline";

ALTER TABLE "m_discountschemabreak"
    DROP CONSTRAINT "mdiscounts_mdsbreak";

ALTER TABLE "m_discountschemabreak"
    DROP CONSTRAINT "mproduct_mdiscountsbreak";

ALTER TABLE "m_discountschemabreak"
    DROP CONSTRAINT "mprodcategory_mdiscountsbreak";

ALTER TABLE "m_demandline"
    DROP CONSTRAINT "cperiod_mdemandline";

ALTER TABLE "m_demandline"
    DROP CONSTRAINT "mdemand_mdemandline";

ALTER TABLE "m_demandline"
    DROP CONSTRAINT "mproduct_mdemandline";

ALTER TABLE "m_demanddetail"
    DROP CONSTRAINT "corderline_mdemanddetail";

ALTER TABLE "m_demanddetail"
    DROP CONSTRAINT "mdemandline_mdemanddetail";

ALTER TABLE "m_demanddetail"
    DROP CONSTRAINT "mforecastline_mdemanddetail";

ALTER TABLE "m_demanddetail"
    DROP CONSTRAINT "mreqline_mdemanddetail";

ALTER TABLE "m_demand"
    DROP CONSTRAINT "ccalendar_mdemand";

ALTER TABLE "m_demand"
    DROP CONSTRAINT "cyear_mdemand";

ALTER TABLE "m_costqueue"
    DROP CONSTRAINT "cacctschema_mcostqueue";

ALTER TABLE "m_costqueue"
    DROP CONSTRAINT "masi_mcostqueue";

ALTER TABLE "m_costqueue"
    DROP CONSTRAINT "mcostelement_mcostqueue";

ALTER TABLE "m_costqueue"
    DROP CONSTRAINT "mcosttype_mcostqueue";

ALTER TABLE "m_costqueue"
    DROP CONSTRAINT "mproduct_mcostqueue";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "adclient_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "adorg_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "cacctschema_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "minvoiceline_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "corderline_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "cprojectissue_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "masi_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "mcostelement_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "minoutline_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "minventoryline_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "mmovementline_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "mproduct_mcostdetail";

ALTER TABLE "m_costdetail"
    DROP CONSTRAINT "mproductionline_mcostdetail";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "adclient_mcost";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "adorg_m_cost";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "cacctschema_mcost";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "masi_mcost";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "mcostelement_mcost";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "mcosttype_mcost";

ALTER TABLE "m_cost"
    DROP CONSTRAINT "mproduct_mcost";

ALTER TABLE "m_changerequest"
    DROP CONSTRAINT "mbom_mchangerequest";

ALTER TABLE "m_changerequest"
    DROP CONSTRAINT "mcn_mcr";

ALTER TABLE "m_bomproduct"
    DROP CONSTRAINT "masi_mbomproduct";

ALTER TABLE "m_bomproduct"
    DROP CONSTRAINT "mbom_mbomproduct";

ALTER TABLE "m_bomproduct"
    DROP CONSTRAINT "mbomalternative_mbomproduct";

ALTER TABLE "m_bomproduct"
    DROP CONSTRAINT "mcn_mbomproduct";

ALTER TABLE "m_bomproduct"
    DROP CONSTRAINT "mproduct_mbomproduct";

ALTER TABLE "m_bomproduct"
    DROP CONSTRAINT "mproductop_mbomproduct";

ALTER TABLE "m_bomalternative"
    DROP CONSTRAINT "mproduct_mbomalternative";

ALTER TABLE "m_bom"
    DROP CONSTRAINT "mcn_mbom";

ALTER TABLE "m_bom"
    DROP CONSTRAINT "mproduct_mbom";

ALTER TABLE "m_attributevalue"
    DROP CONSTRAINT "mattribute_mattributevalue";

ALTER TABLE "m_attributeuse"
    DROP CONSTRAINT "mattribute_mattributeuse";

ALTER TABLE "m_attributeuse"
    DROP CONSTRAINT "mattributeset_mattributeuse";

ALTER TABLE "m_attributesetinstance"
    DROP CONSTRAINT "mattributeset_mattribsetinst";

ALTER TABLE "m_attributesetinstance"
    DROP CONSTRAINT "mlot_mattributesetinstance";

ALTER TABLE "m_attributesetexclude"
    DROP CONSTRAINT "adtable_mattributesetexclude";

ALTER TABLE "m_attributesetexclude"
    DROP CONSTRAINT "mattributeset_masexclude";

ALTER TABLE "m_attributeset"
    DROP CONSTRAINT "mlotctl_mattributeset";

ALTER TABLE "m_attributeset"
    DROP CONSTRAINT "msernoctl_attributeset";

ALTER TABLE "m_attributeinstance"
    DROP CONSTRAINT "mattribute_mattributeinst";

ALTER TABLE "m_attributeinstance"
    DROP CONSTRAINT "mattrsetinst__mattrinst";

ALTER TABLE "m_attributeinstance"
    DROP CONSTRAINT "mattributevalue_mattrinst";

ALTER TABLE "m_attribute"
    DROP CONSTRAINT "mattributesearch_mattribute";

ALTER TABLE "k_topic"
    DROP CONSTRAINT "ktype_ktopic";

ALTER TABLE "k_indexstop"
    DROP CONSTRAINT "cmwebproject_kindexstop";

ALTER TABLE "k_indexstop"
    DROP CONSTRAINT "cdoctype_kindexstop";

ALTER TABLE "k_indexstop"
    DROP CONSTRAINT "rrequesttype_kindexstop";

ALTER TABLE "k_index"
    DROP CONSTRAINT "adtable_kindex";

ALTER TABLE "k_index"
    DROP CONSTRAINT "cmwebproject_kindex";

ALTER TABLE "k_index"
    DROP CONSTRAINT "cdoctype_kindex";

ALTER TABLE "k_index"
    DROP CONSTRAINT "rrequesttype_kindex";

ALTER TABLE "k_entryrelated"
    DROP CONSTRAINT "kentry_kentryrelatedid";

ALTER TABLE "k_entryrelated"
    DROP CONSTRAINT "kentry_kentryrelated";

ALTER TABLE "k_entrycategory"
    DROP CONSTRAINT "kcategory_kentrycategory";

ALTER TABLE "k_entrycategory"
    DROP CONSTRAINT "kcategoryvalue_kentrycategory";

ALTER TABLE "k_entrycategory"
    DROP CONSTRAINT "kentry_kentrycatalog";

ALTER TABLE "k_entry"
    DROP CONSTRAINT "adsession_kentry";

ALTER TABLE "k_entry"
    DROP CONSTRAINT "ksource_kentry";

ALTER TABLE "k_entry"
    DROP CONSTRAINT "ktopic_kentry";

ALTER TABLE "k_comment"
    DROP CONSTRAINT "adsession_kcomment";

ALTER TABLE "k_comment"
    DROP CONSTRAINT "kentry_kcomment";

ALTER TABLE "k_categoryvalue"
    DROP CONSTRAINT "kcategory_kcategoryvalue";

ALTER TABLE "i_reportline"
    DROP CONSTRAINT "celementvalue_ireportline";

ALTER TABLE "i_reportline"
    DROP CONSTRAINT "pareportline_ireportline";

ALTER TABLE "i_reportline"
    DROP CONSTRAINT "pareportlineset_ireportline";

ALTER TABLE "i_reportline"
    DROP CONSTRAINT "pareportsource_ireportline";

ALTER TABLE "i_product"
    DROP CONSTRAINT "cbpartner_iproduct";

ALTER TABLE "i_product"
    DROP CONSTRAINT "ccurrency_iproduct";

ALTER TABLE "i_product"
    DROP CONSTRAINT "cuom_iproduct";

ALTER TABLE "i_product"
    DROP CONSTRAINT "mproduct_iproduct";

ALTER TABLE "i_product"
    DROP CONSTRAINT "mproductcategory_iproduct";

ALTER TABLE "i_payment"
    DROP CONSTRAINT "cbankaccount_ipayment";

ALTER TABLE "i_payment"
    DROP CONSTRAINT "cbpartner_ipayment";

ALTER TABLE "i_payment"
    DROP CONSTRAINT "ccharge_ipayment";

ALTER TABLE "i_payment"
    DROP CONSTRAINT "cdoctype_ipayment";

ALTER TABLE "i_payment"
    DROP CONSTRAINT "cinvoice_ipayment";

ALTER TABLE "i_payment"
    DROP CONSTRAINT "cpayment_ipayment";

ALTER TABLE "i_order"
    DROP CONSTRAINT "adorgtrx_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "adorg_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "adusersalesrep_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "aduser_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cactivity_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cbpartner_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cbolocation_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cbpartnerlocbillto_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "ccampaign_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "ccountry_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "ccurrency_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cdoctype_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "clocation_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "corder_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "corderline_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cpaymentterm_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cproject_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cregion_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "ctax_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "cuom_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "mpricelist_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "mproduct_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "mshipper_iorder";

ALTER TABLE "i_order"
    DROP CONSTRAINT "mwarehouse_iorder";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "adorgtrx_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "adorg_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "adusersalesrep_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "aduser_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cactivity_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cbpartner_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cbplocation_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "ccampaign_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "ccountry_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "ccurrency_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cdoctype_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cinvoice_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cinvliceline_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "clocation_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cpaymentterm_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cproject_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "cregion_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "ctax_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "mpricelist_iinvoice";

ALTER TABLE "i_invoice"
    DROP CONSTRAINT "nproduct_iinvoice";

ALTER TABLE "i_inventory"
    DROP CONSTRAINT "minventory_iinventory";

ALTER TABLE "i_inventory"
    DROP CONSTRAINT "minventoryline_iinventory";

ALTER TABLE "i_inventory"
    DROP CONSTRAINT "mlocator_iinventory";

ALTER TABLE "i_inventory"
    DROP CONSTRAINT "mproduct_iinventory";

ALTER TABLE "i_inventory"
    DROP CONSTRAINT "mwarehouse_iinventory";

ALTER TABLE "i_inoutlineconfirm"
    DROP CONSTRAINT "minoutlineconfirm_import";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "adorgdoc_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "adorgtrx_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "adorg_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cacctschema_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cactivity_gljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cbpartner_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "ccampaign_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cconversiontype_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "ccurrency_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cdoctype_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "celvalueuser2_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cevalueuser1_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "celvalueaccount_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "clocfrom_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "clocto_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cperiod_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cproject_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "csalesregion_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "cvalidcombination_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "glbudget_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "glcategory_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "gljournal_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "gljournalbatch_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "gljourbelline_igljournal";

ALTER TABLE "i_gljournal"
    DROP CONSTRAINT "mproduct_ogljournal";

ALTER TABLE "i_elementvalue"
    DROP CONSTRAINT "adcolumn_ielementvalue";

ALTER TABLE "i_elementvalue"
    DROP CONSTRAINT "celement_ielementvalue";

ALTER TABLE "i_elementvalue"
    DROP CONSTRAINT "celementvalue_ielementvalue";

ALTER TABLE "i_elementvalue"
    DROP CONSTRAINT "cevalueparent_ielementvalue";

ALTER TABLE "i_conversion_rate"
    DROP CONSTRAINT "cconvtype_iconvrate";

ALTER TABLE "i_conversion_rate"
    DROP CONSTRAINT "cconversionrate_iconvrate";

ALTER TABLE "i_conversion_rate"
    DROP CONSTRAINT "ccurrency_iconvrateto";

ALTER TABLE "i_conversion_rate"
    DROP CONSTRAINT "ccurrency_iconvrate";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "aduser_ibpartner";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "cbpartner_ibpartner";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "cbpartnerlocation_ibpartner";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "cbpgroup_ibpartner";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "ccountry_ipartner";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "cgreeting_ibpartner";

ALTER TABLE "i_bpartner"
    DROP CONSTRAINT "cregion_ibpartner";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "cbankaccount_ibankstatement";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "cbankstatement_ibankstatement";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "cbankstmtline_ibankstmt";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "cbpartner_ibankstatement";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "ccharge_ibankstmt";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "ccurrency_ibankstatement";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "cinvoice_ibankstatement";

ALTER TABLE "i_bankstatement"
    DROP CONSTRAINT "cpayment_ibankstatement";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "adclient_gljournalline";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "adorg_gljournalline";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "cconversiontype_gljournalline";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "ccurrency_gljournalline";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "cuom_gljournalline";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "cvc_gljournalline";

ALTER TABLE "gl_journalline"
    DROP CONSTRAINT "gljournal_gljournalline";

ALTER TABLE "gl_journalbatch"
    DROP CONSTRAINT "ccurrency_gljournalbatch";

ALTER TABLE "gl_journalbatch"
    DROP CONSTRAINT "cdoctype_gljournalbatch";

ALTER TABLE "gl_journalbatch"
    DROP CONSTRAINT "c_period_journalbatch";

ALTER TABLE "gl_journalbatch"
    DROP CONSTRAINT "glcategory_gljournalbatch";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "c_acctschema_gl_journal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "cconversiontype_gljournal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "ccurrency_gljournal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "cdoctype_gljournal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "c_period_journal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "glbudget_gljournal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "glcategory_gljournal";

ALTER TABLE "gl_journal"
    DROP CONSTRAINT "gljournalbatch_gljournal";

ALTER TABLE "gl_fundrestriction"
    DROP CONSTRAINT "celementvalue_glfundrestr";

ALTER TABLE "gl_fundrestriction"
    DROP CONSTRAINT "glfund_glfundrestriction";

ALTER TABLE "gl_fund"
    DROP CONSTRAINT "cacctschema_glfund";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "adorg_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "adorgtrx_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "adorgorg_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "cactivity_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "cbpartner_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "ccampaign_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "cevalueuser1_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "cevalueuser2_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "cevalueacct_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "clocto_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "clocfrom_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "cproject_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "csalesregion_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "gldistribution_gldistline";

ALTER TABLE "gl_distributionline"
    DROP CONSTRAINT "mproduct_gldistline";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "adorg_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "adorgtrx_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "adorgorg_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cacctschema_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cactivity_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cbpartner_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "ccampaign_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cdoctype_gldistribution";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cevalueuser2_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cevalueuser1_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cevalueacct_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "clocto_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "clocfrom_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "cproject_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "csalesregopn_gldist";

ALTER TABLE "gl_distribution"
    DROP CONSTRAINT "mproduct_gldist";

ALTER TABLE "gl_budgetcontrol"
    DROP CONSTRAINT "cacctschema_glbudgetcontrol";

ALTER TABLE "gl_budgetcontrol"
    DROP CONSTRAINT "glbudget_glbudgetcontrol";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "adclient_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "adorg_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "adorgtrx_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "cacctschema_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "cactivity_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "cbpartner_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "ccampaign_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "celementvalueu1_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "celementvalueacct_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "celementvalueu2_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "clocto_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "clocfrom_factacctbalance";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "cproject_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "cprojectphase_factacctbalance";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "cprojecttask_factacctbalance";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "csalesregion_factacctbal";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "glbudget_factacctbalance";

ALTER TABLE "fact_acct_balance"
    DROP CONSTRAINT "mproduct_factacctbal";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "ac_client_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "ad_orgtrx_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "ad_org_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "adtable_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "aasset_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "cacctschema_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "cactivity_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_buspartner_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "so_campaign_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_currency_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "celementvalue_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "celementvalueuser2_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "celementvalueuser1_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_locationfrom_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_locationto_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "cperiod_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_project_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "cprojectphase_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "cprojecttask_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_salesregion_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "ctax_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "c_uom_fact_acct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "glbudget_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "glcategory_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "mlocator_factacct";

ALTER TABLE "fact_acct"
    DROP CONSTRAINT "m_product_fact_acct";

ALTER TABLE "c_year"
    DROP CONSTRAINT "c_yearclient";

ALTER TABLE "c_year"
    DROP CONSTRAINT "c_yearorg";

ALTER TABLE "c_year"
    DROP CONSTRAINT "c_calendar_year";

ALTER TABLE "c_withholding_acct"
    DROP CONSTRAINT "cacctschema_cwithholdingacct";

ALTER TABLE "c_withholding_acct"
    DROP CONSTRAINT "vc_withholding_cwithholding";

ALTER TABLE "c_withholding_acct"
    DROP CONSTRAINT "cwithholding_cwithholdingacct";

ALTER TABLE "c_withholding"
    DROP CONSTRAINT "cpaymentterm_cwithholding";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "adclient_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "adorg_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "adorgtrx_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "cacctschema_cvalidcombination";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "cactivity_cvalidcombination";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "cbpartner_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "socampaign_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "celementvalueuser2_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "celementvalueaccount_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "celementvalueuser1_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "mlocationto_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "mlocationfrom_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "cproject_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "csalesregion_vc";

ALTER TABLE "c_validcombination"
    DROP CONSTRAINT "mproduct_vc";

ALTER TABLE "c_userremuneration"
    DROP CONSTRAINT "aduser_cuserremuneration";

ALTER TABLE "c_userremuneration"
    DROP CONSTRAINT "cremuneration_cuserrem";

ALTER TABLE "c_uom_trl"
    DROP CONSTRAINT "adlanguage_cuomtrl";

ALTER TABLE "c_uom_trl"
    DROP CONSTRAINT "cuom_cuomtrl";

ALTER TABLE "c_uom_conversion"
    DROP CONSTRAINT "c_uom_conversionclient";

ALTER TABLE "c_uom_conversion"
    DROP CONSTRAINT "c_uom_conversionorg";

ALTER TABLE "c_uom_conversion"
    DROP CONSTRAINT "c_uomconversionto";

ALTER TABLE "c_uom_conversion"
    DROP CONSTRAINT "cuom_cuomconversion";

ALTER TABLE "c_uom"
    DROP CONSTRAINT "c_uomclient";

ALTER TABLE "c_uom"
    DROP CONSTRAINT "c_uomorg";

ALTER TABLE "c_tax_trl"
    DROP CONSTRAINT "adlanguage_ctaxtrl";

ALTER TABLE "c_tax_trl"
    DROP CONSTRAINT "ctax_ctaxtrl";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "cacctschema_ctaxacct";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "ctax_ctaxacct";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "vc_texpense_ctax";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "vc_tliability_ctax";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "vc_trec_ctax";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "vc_tcredit_ctax";

ALTER TABLE "c_tax_acct"
    DROP CONSTRAINT "vc_tdue_ctax";

ALTER TABLE "c_taxpostal"
    DROP CONSTRAINT "ctax_ctaxpostal";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "callocationline_ctaxdeclline";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "cbpartner_ctaxdeclline";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "ccurrency_ctaxdeclline";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "cinvoice_ctaxdeclline";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "cinvoiceline_ctaxdeclline";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "ctax_ctaxdeclline";

ALTER TABLE "c_taxdeclarationline"
    DROP CONSTRAINT "ctaxdeclaration_ctaxdeclline";

ALTER TABLE "c_taxdeclarationacct"
    DROP CONSTRAINT "cacctschema_ctaxdeclacct";

ALTER TABLE "c_taxdeclarationacct"
    DROP CONSTRAINT "ctaxdecl_ctaxdeclacct";

ALTER TABLE "c_taxdeclarationacct"
    DROP CONSTRAINT "factacct_ctaxdeclacct";

ALTER TABLE "c_taxcategory_trl"
    DROP CONSTRAINT "adlanguage_ctaxcategorytrl";

ALTER TABLE "c_taxcategory_trl"
    DROP CONSTRAINT "ctaxcategory_trl";

ALTER TABLE "c_tax"
    DROP CONSTRAINT "c_country_c_tax";

ALTER TABLE "c_tax"
    DROP CONSTRAINT "c_countryto_c_tax";

ALTER TABLE "c_tax"
    DROP CONSTRAINT "c_regionto_c_tax";

ALTER TABLE "c_tax"
    DROP CONSTRAINT "c_region_c_tax";

ALTER TABLE "c_tax"
    DROP CONSTRAINT "ctax_parent";

ALTER TABLE "c_tax"
    DROP CONSTRAINT "ctaxcategory_ctax";

ALTER TABLE "c_task"
    DROP CONSTRAINT "cphase_ctask";

ALTER TABLE "c_task"
    DROP CONSTRAINT "mproduct_ctask";

ALTER TABLE "c_subscription_delivery"
    DROP CONSTRAINT "csubcription_csubscrdelivery";

ALTER TABLE "c_subscription"
    DROP CONSTRAINT "cbpartner_csubscription";

ALTER TABLE "c_subscription"
    DROP CONSTRAINT "csubscrtype_csubscription";

ALTER TABLE "c_subscription"
    DROP CONSTRAINT "mproduct_csubscription";

ALTER TABLE "c_subacct"
    DROP CONSTRAINT "celementvalue_csubacct";

ALTER TABLE "c_servicelevelline"
    DROP CONSTRAINT "cservicelevel_line";

ALTER TABLE "c_servicelevel"
    DROP CONSTRAINT "crevrecplan_cservicelevel";

ALTER TABLE "c_servicelevel"
    DROP CONSTRAINT "mproduct_cservicelevel";

ALTER TABLE "c_rfq_topicsubscriberonly"
    DROP CONSTRAINT "crfqtopicsubscriber_only";

ALTER TABLE "c_rfq_topicsubscriberonly"
    DROP CONSTRAINT "mproduct_crfqtsubonly";

ALTER TABLE "c_rfq_topicsubscriberonly"
    DROP CONSTRAINT "mprodcategory_crfqtsubonly";

ALTER TABLE "c_rfq_topicsubscriber"
    DROP CONSTRAINT "aduser_arfqtopicsubcr";

ALTER TABLE "c_rfq_topicsubscriber"
    DROP CONSTRAINT "cbpartner_crfqtopicsubr";

ALTER TABLE "c_rfq_topicsubscriber"
    DROP CONSTRAINT "cbpartnerloc_crfqtopicsubr";

ALTER TABLE "c_rfq_topicsubscriber"
    DROP CONSTRAINT "c_rfqtopic_subscriber";

ALTER TABLE "c_rfq_topic"
    DROP CONSTRAINT "adprintformat_arfqtopic";

ALTER TABLE "c_rfqresponselineqty"
    DROP CONSTRAINT "crfqlineqty_crfqresplineqty";

ALTER TABLE "c_rfqresponselineqty"
    DROP CONSTRAINT "crfqresonseline_qty";

ALTER TABLE "c_rfqresponseline"
    DROP CONSTRAINT "crfqline_crfqresponseline";

ALTER TABLE "c_rfqresponseline"
    DROP CONSTRAINT "crfqresponse_line";

ALTER TABLE "c_rfqresponse"
    DROP CONSTRAINT "aduser_crfqresponse";

ALTER TABLE "c_rfqresponse"
    DROP CONSTRAINT "cbpartner_crfqresponse";

ALTER TABLE "c_rfqresponse"
    DROP CONSTRAINT "cbplocation_crfqresponse";

ALTER TABLE "c_rfqresponse"
    DROP CONSTRAINT "ccurrency_crfqresponse";

ALTER TABLE "c_rfqresponse"
    DROP CONSTRAINT "corder_crfqresponse";

ALTER TABLE "c_rfqresponse"
    DROP CONSTRAINT "crfq_crfqresponse";

ALTER TABLE "c_rfqlineqty"
    DROP CONSTRAINT "crfqline_crfqlineqty";

ALTER TABLE "c_rfqlineqty"
    DROP CONSTRAINT "cuom_crfqlineqty";

ALTER TABLE "c_rfqline"
    DROP CONSTRAINT "crfq_crfqline";

ALTER TABLE "c_rfqline"
    DROP CONSTRAINT "masetinstance_crfqline";

ALTER TABLE "c_rfqline"
    DROP CONSTRAINT "mproduct_crfqline";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "adusersalesrep_crfq";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "aduser_crfq";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "cbpartner_crfq";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "cbplocation_crfq";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "ccurrency_crfq";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "corder_crfq";

ALTER TABLE "c_rfq"
    DROP CONSTRAINT "crfqtopic_crfq";

ALTER TABLE "c_revenuerecognition_run"
    DROP CONSTRAINT "crevrecplan_crefrecrun";

ALTER TABLE "c_revenuerecognition_run"
    DROP CONSTRAINT "gljournal_crevenuerecrun";

ALTER TABLE "c_revenuerecognition_plan"
    DROP CONSTRAINT "cacctschema_crevrecplan";

ALTER TABLE "c_revenuerecognition_plan"
    DROP CONSTRAINT "ccurrency_crevenuerecplan";

ALTER TABLE "c_revenuerecognition_plan"
    DROP CONSTRAINT "cinvoiceline_crevenuerecplan";

ALTER TABLE "c_revenuerecognition_plan"
    DROP CONSTRAINT "crevenuerecognition_plan";

ALTER TABLE "c_revenuerecognition_plan"
    DROP CONSTRAINT "vc_unearnedrevenue_crevenuerec";

ALTER TABLE "c_revenuerecognition_plan"
    DROP CONSTRAINT "vc_prevenue_crevenuerecognitio";

ALTER TABLE "c_region"
    DROP CONSTRAINT "c_regionclient";

ALTER TABLE "c_region"
    DROP CONSTRAINT "c_regionorg";

ALTER TABLE "c_region"
    DROP CONSTRAINT "ccountry_cregion";

ALTER TABLE "c_recurring_run"
    DROP CONSTRAINT "cinvoice_crecurringrun";

ALTER TABLE "c_recurring_run"
    DROP CONSTRAINT "corder_crecurringrun";

ALTER TABLE "c_recurring_run"
    DROP CONSTRAINT "cpayment_crecurringrun";

ALTER TABLE "c_recurring_run"
    DROP CONSTRAINT "cproject_crecurringrun";

ALTER TABLE "c_recurring_run"
    DROP CONSTRAINT "crecurring_crecurringrun";

ALTER TABLE "c_recurring_run"
    DROP CONSTRAINT "gljournalbatch_crecurringrun";

ALTER TABLE "c_recurring"
    DROP CONSTRAINT "cinvoice_crecurring";

ALTER TABLE "c_recurring"
    DROP CONSTRAINT "corder_crecurring";

ALTER TABLE "c_recurring"
    DROP CONSTRAINT "cpayment_crecurring";

ALTER TABLE "c_recurring"
    DROP CONSTRAINT "cproject_crecurring";

ALTER TABLE "c_recurring"
    DROP CONSTRAINT "gljournalbatch_crecurring";

ALTER TABLE "c_project_acct"
    DROP CONSTRAINT "cacctschema_cprojectacct";

ALTER TABLE "c_project_acct"
    DROP CONSTRAINT "c_project_projectacct";

ALTER TABLE "c_project_acct"
    DROP CONSTRAINT "vc_pjasset_cproject";

ALTER TABLE "c_project_acct"
    DROP CONSTRAINT "vc_pjwip_cproject";

ALTER TABLE "c_projecttask"
    DROP CONSTRAINT "cprojectphase_cprojecttask";

ALTER TABLE "c_projecttask"
    DROP CONSTRAINT "ctask_cprojecttask";

ALTER TABLE "c_projecttask"
    DROP CONSTRAINT "mproduct_cprojecttask";

ALTER TABLE "c_projectphase"
    DROP CONSTRAINT "corder_cprojectphase";

ALTER TABLE "c_projectphase"
    DROP CONSTRAINT "cphase_cprojectphase";

ALTER TABLE "c_projectphase"
    DROP CONSTRAINT "cproject_cprojectphase";

ALTER TABLE "c_projectphase"
    DROP CONSTRAINT "mproduct_cprojectphase";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "corder_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "corderpo_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "cproject_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "cprojectissue_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "cprojectphase_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "cprojecttask_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "mproduct_cprojectline";

ALTER TABLE "c_projectline"
    DROP CONSTRAINT "mproductcat_cprojectline";

ALTER TABLE "c_projectissuema"
    DROP CONSTRAINT "cprojectissue_cprojectissuema";

ALTER TABLE "c_projectissuema"
    DROP CONSTRAINT "masi_cprojectissuema";

ALTER TABLE "c_projectissue"
    DROP CONSTRAINT "cproject_cprojectissue";

ALTER TABLE "c_projectissue"
    DROP CONSTRAINT "mattrsetinst_cprojectissue";

ALTER TABLE "c_projectissue"
    DROP CONSTRAINT "minoutline_cprojectissue";

ALTER TABLE "c_projectissue"
    DROP CONSTRAINT "mlocator_cprojectissue";

ALTER TABLE "c_projectissue"
    DROP CONSTRAINT "mproduct_cprojectissue";

ALTER TABLE "c_projectissue"
    DROP CONSTRAINT "stimeexpline_cprojectissue";

ALTER TABLE "c_project"
    DROP CONSTRAINT "adclient_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "adorg_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "aduser_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "aduser_sr_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "cbpartner_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "cbplocation_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "ccampaign_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "ccurrency_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "cpaymentterm_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "cphase_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "cprojecttype_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "mpricelistversion_cproject";

ALTER TABLE "c_project"
    DROP CONSTRAINT "mwarehouse_cproject";

ALTER TABLE "c_poskey"
    DROP CONSTRAINT "cposkeylayout_c_poskey";

ALTER TABLE "c_poskey"
    DROP CONSTRAINT "mproduct_cposkey";

ALTER TABLE "c_pos"
    DROP CONSTRAINT "aduser_cpos";

ALTER TABLE "c_pos"
    DROP CONSTRAINT "ccashbook_cpos";

ALTER TABLE "c_pos"
    DROP CONSTRAINT "cposkeylayout_cpos";

ALTER TABLE "c_pos"
    DROP CONSTRAINT "mpricelist_cpos";

ALTER TABLE "c_pos"
    DROP CONSTRAINT "mwarehouse_cpos";

ALTER TABLE "c_phase"
    DROP CONSTRAINT "cprojecttype_cphase";

ALTER TABLE "c_phase"
    DROP CONSTRAINT "mproduct_cphase";

ALTER TABLE "c_periodcontrol"
    DROP CONSTRAINT "c_period_periodcontrol";

ALTER TABLE "c_period"
    DROP CONSTRAINT "c_periodclient";

ALTER TABLE "c_period"
    DROP CONSTRAINT "c_periodorg";

ALTER TABLE "c_period"
    DROP CONSTRAINT "c_year_period";

ALTER TABLE "c_payselectionline"
    DROP CONSTRAINT "cinvoice_cpayselectline";

ALTER TABLE "c_payselectionline"
    DROP CONSTRAINT "cpaysel_cpayselline";

ALTER TABLE "c_payselectionline"
    DROP CONSTRAINT "cpayselcheck_cpayselline";

ALTER TABLE "c_payselectioncheck"
    DROP CONSTRAINT "cbpartner_cpayselectioncheck";

ALTER TABLE "c_payselectioncheck"
    DROP CONSTRAINT "cpayment_cpayselectioncheck";

ALTER TABLE "c_payselectioncheck"
    DROP CONSTRAINT "cpayselection_cpayselectcheck";

ALTER TABLE "c_payselection"
    DROP CONSTRAINT "cbankaccount_cpayselection";

ALTER TABLE "c_payschedule"
    DROP CONSTRAINT "cpaymentterm_cpayschedule";

ALTER TABLE "c_paymentterm_trl"
    DROP CONSTRAINT "adlanguage_cpaymenttermtrl";

ALTER TABLE "c_paymentterm_trl"
    DROP CONSTRAINT "cpaymentterm_cpaytermtrl";

ALTER TABLE "c_paymentprocessor"
    DROP CONSTRAINT "adsequence_cpaymentprocessor";

ALTER TABLE "c_paymentprocessor"
    DROP CONSTRAINT "cbankaccount_cpaymtprocessor";

ALTER TABLE "c_paymentprocessor"
    DROP CONSTRAINT "ccurrency_cpaymentprocessor";

ALTER TABLE "c_paymentbatch"
    DROP CONSTRAINT "cpaymtprocessor_cpaymtbatch";

ALTER TABLE "c_paymentallocate"
    DROP CONSTRAINT "cpaymtallocate_callocationline";

ALTER TABLE "c_paymentallocate"
    DROP CONSTRAINT "cinvoice_cpaymentallocate";

ALTER TABLE "c_paymentallocate"
    DROP CONSTRAINT "cpayment_cpaymentallocate";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "adorgtrx_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "adorg_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cactivity_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cbankaccount_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cbpartner_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cbpbankacct_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "ccampaign_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "ccharge_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cconversiontype_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "ccurrency_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cdoctype_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "celementvalueuser1_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "celementvalueuser2_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cpaymentbatch_cpayment";

ALTER TABLE "c_payment"
    DROP CONSTRAINT "cproject_cpayment";

ALTER TABLE "c_orgassignment"
    DROP CONSTRAINT "adorg_corgassignment";

ALTER TABLE "c_orgassignment"
    DROP CONSTRAINT "aduser_corgassignment";

ALTER TABLE "c_ordertax"
    DROP CONSTRAINT "corder_cordertax";

ALTER TABLE "c_ordertax"
    DROP CONSTRAINT "ctax_cordertax";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "adorg_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "adorgtrx_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cactivity_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cbpartner_soline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cbpartnerlocation_soline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "ccampaign_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "ccharge_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "ccurrency_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "celemenrvalueuser1_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "celemenrvalueuser2_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "corder_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "corderline_ref";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cproject_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cprojectphase_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cprojecttask_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "ctax_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "cuom_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "mattrsetinst_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "mproduct_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "mshipper_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "mwarehouse_corderline";

ALTER TABLE "c_orderline"
    DROP CONSTRAINT "sresourceassign_corderline";

ALTER TABLE "c_order"
    DROP CONSTRAINT "adorg_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "adorgtrx_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "aduserbill_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "aduser_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "aduser_sr_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cactivity_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cbpartnerbill_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cbpartnerpay_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cbpartner_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cbplocationpay_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cbpartnerlocation_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cbplocationbill_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "ccampaign_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "ccashline_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "ccharge_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cconversiontype_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "ccurrency_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "c_doctype_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cdoctypetarget_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "celementvalueuser1_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "celemenrvalueuser2_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "corder_ref";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cpayment_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cpaymentterm_soheader";

ALTER TABLE "c_order"
    DROP CONSTRAINT "cproject_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "mpricelist_soheader";

ALTER TABLE "c_order"
    DROP CONSTRAINT "mshipper_corder";

ALTER TABLE "c_order"
    DROP CONSTRAINT "mwarehouse_corder";

ALTER TABLE "c_nonbusinessday"
    DROP CONSTRAINT "c_nonbusinesdaysclient";

ALTER TABLE "c_nonbusinessday"
    DROP CONSTRAINT "c_nonbusinesdaysorg";

ALTER TABLE "c_nonbusinessday"
    DROP CONSTRAINT "c_calendarnonbusinessday";

ALTER TABLE "c_location"
    DROP CONSTRAINT "adclient_clocation";

ALTER TABLE "c_location"
    DROP CONSTRAINT "adorg_clocation";

ALTER TABLE "c_location"
    DROP CONSTRAINT "ccity_clocation";

ALTER TABLE "c_location"
    DROP CONSTRAINT "c_country_location";

ALTER TABLE "c_location"
    DROP CONSTRAINT "c_region_location";

ALTER TABLE "c_landedcostallocation"
    DROP CONSTRAINT "cinvoiceline_clandedcostalloc";

ALTER TABLE "c_landedcostallocation"
    DROP CONSTRAINT "masi_clandedcostallocation";

ALTER TABLE "c_landedcostallocation"
    DROP CONSTRAINT "mcostelement_mlandedcostalloc";

ALTER TABLE "c_landedcostallocation"
    DROP CONSTRAINT "mproduct_clandedcostalloc";

ALTER TABLE "c_landedcost"
    DROP CONSTRAINT "cinvoiceline_clandedcost";

ALTER TABLE "c_landedcost"
    DROP CONSTRAINT "ccostelement_clandedcost";

ALTER TABLE "c_landedcost"
    DROP CONSTRAINT "minout_clandedcost";

ALTER TABLE "c_landedcost"
    DROP CONSTRAINT "minoutline_clandedcost";

ALTER TABLE "c_landedcost"
    DROP CONSTRAINT "mproduct_clandedcost";

ALTER TABLE "c_jobremuneration"
    DROP CONSTRAINT "cjob_cjobremuneration";

ALTER TABLE "c_jobremuneration"
    DROP CONSTRAINT "cremuneration_cjobrem";

ALTER TABLE "c_jobassignment"
    DROP CONSTRAINT "aduser_cjobassignment";

ALTER TABLE "c_jobassignment"
    DROP CONSTRAINT "cjob_cjobassignment";

ALTER TABLE "c_job"
    DROP CONSTRAINT "cjobcategory_cjob";

ALTER TABLE "c_invoicetax"
    DROP CONSTRAINT "cinvoice_cinvoicetax";

ALTER TABLE "c_invoicetax"
    DROP CONSTRAINT "ctax_cinvoicetax";

ALTER TABLE "c_invoicepayschedule"
    DROP CONSTRAINT "cinvoice_cinvoicepaysched";

ALTER TABLE "c_invoicepayschedule"
    DROP CONSTRAINT "cpayschedule_cinvoicepaysched";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "adorg_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "adorgtrx_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "aasset_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "cactivity_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "ccampaign_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "ccharge_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "celemenrvalueuser1_cinvline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "celemenrvalueuser2_cinvline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "cinvoice_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "convoiceline_ref";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "corderline_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "cproject_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "cprojectphase_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "cprojecttask_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "ctax_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "cuom_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "mattrsetinst_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "minoutline_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "mproduct_cinvoiceline";

ALTER TABLE "c_invoiceline"
    DROP CONSTRAINT "sresourceassign_cinvoiceline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "adorg_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "adorgtrx_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "aduser_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cactivity_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cbpartner_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cbplocation_cinvoicebline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "ccharge_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cdoctype_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "celementvalueu2_cinvoicebline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "celementvalueu1_cinvoicebline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cinvoice_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cinvoicebatch_cinvoicebline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cinvoiceline_cinvoicebline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "cproject_cinvoicebatchline";

ALTER TABLE "c_invoicebatchline"
    DROP CONSTRAINT "ctax_cinvoicebatchline";

ALTER TABLE "c_invoicebatch"
    DROP CONSTRAINT "aduser_cinvoicebatch";

ALTER TABLE "c_invoicebatch"
    DROP CONSTRAINT "cconventiontype_cinvoicebatch";

ALTER TABLE "c_invoicebatch"
    DROP CONSTRAINT "ccurrency_cinvoicebatch";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "adorg_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "adorgtrx_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "aduser_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "aduser_sr_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cactivity_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cbpartner_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "c_bplocation_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "ccampaign_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "ccashline_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "ccharge_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cconversiontype_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "ccurrency_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cdoctype_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cdoctypetarget_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "celementvalueuser2_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "celementvalueuser1_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cinvoice_ref";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "corder_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cpayment_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cpaymentterm_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "cproject_cinvoice";

ALTER TABLE "c_invoice"
    DROP CONSTRAINT "mpricelist_cinvoice";

ALTER TABLE "c_interorg_acct"
    DROP CONSTRAINT "adorgto_cinterorgacct";

ALTER TABLE "c_interorg_acct"
    DROP CONSTRAINT "adorg_cinterorgacct";

ALTER TABLE "c_interorg_acct"
    DROP CONSTRAINT "cacctschema_cinterorgacct";

ALTER TABLE "c_interorg_acct"
    DROP CONSTRAINT "vc_intercompanyduefrom_cintero";

ALTER TABLE "c_interorg_acct"
    DROP CONSTRAINT "vc_intercompanydueto_cinterorg";

ALTER TABLE "c_greeting_trl"
    DROP CONSTRAINT "adlanguage_cgreetingtrl";

ALTER TABLE "c_greeting_trl"
    DROP CONSTRAINT "cgreeting_cgreetingtrl";

ALTER TABLE "c_elementvalue_trl"
    DROP CONSTRAINT "adlanguage_celementvaluetrl";

ALTER TABLE "c_elementvalue_trl"
    DROP CONSTRAINT "celementvalue_cevaluetrl";

ALTER TABLE "c_elementvalue"
    DROP CONSTRAINT "adclient_celementvalue";

ALTER TABLE "c_elementvalue"
    DROP CONSTRAINT "adorg_celementvalue";

ALTER TABLE "c_elementvalue"
    DROP CONSTRAINT "cbankaccount_celementvalue";

ALTER TABLE "c_elementvalue"
    DROP CONSTRAINT "ccurrency_celementvalue";

ALTER TABLE "c_elementvalue"
    DROP CONSTRAINT "celement_celementvalue";

ALTER TABLE "c_element"
    DROP CONSTRAINT "adclient_celement";

ALTER TABLE "c_element"
    DROP CONSTRAINT "c_elementorg";

ALTER TABLE "c_element"
    DROP CONSTRAINT "adtree_celement";

ALTER TABLE "c_dunningrunline"
    DROP CONSTRAINT "cdunningrunentry_line";

ALTER TABLE "c_dunningrunline"
    DROP CONSTRAINT "cinvoice_cdunningrunline";

ALTER TABLE "c_dunningrunline"
    DROP CONSTRAINT "cpayment_cdunningrunline";

ALTER TABLE "c_dunningrunentry"
    DROP CONSTRAINT "cbpartner_cdunningrunentry";

ALTER TABLE "c_dunningrunentry"
    DROP CONSTRAINT "ccurrency_cdunningrunentry";

ALTER TABLE "c_dunningrunentry"
    DROP CONSTRAINT "cdunningrun_cdunningrunentry";

ALTER TABLE "c_dunningrun"
    DROP CONSTRAINT "cdunninglevel_cdunningrun";

ALTER TABLE "c_dunninglevel_trl"
    DROP CONSTRAINT "adlanguage_cdunninglevel";

ALTER TABLE "c_dunninglevel_trl"
    DROP CONSTRAINT "cdunninglevel_cdltrl";

ALTER TABLE "c_dunninglevel"
    DROP CONSTRAINT "adprintformat_cdunninglevel";

ALTER TABLE "c_dunninglevel"
    DROP CONSTRAINT "cdunning_cdunninglevel";

ALTER TABLE "c_dunninglevel"
    DROP CONSTRAINT "cpaymentterm_cdunninglevel";

ALTER TABLE "c_doctype_trl"
    DROP CONSTRAINT "adlanguage_cdoctypetrl";

ALTER TABLE "c_doctype_trl"
    DROP CONSTRAINT "cdoctype_cdoctypetrl";

ALTER TABLE "c_doctypecounter"
    DROP CONSTRAINT "cdoctype_cdoctypecounter";

ALTER TABLE "c_doctypecounter"
    DROP CONSTRAINT "cdoctypecount_cdoctypecount";

ALTER TABLE "c_doctype"
    DROP CONSTRAINT "adprintformat_cdoctype";

ALTER TABLE "c_doctype"
    DROP CONSTRAINT "ad_sequence_doctypedoc";

ALTER TABLE "c_doctype"
    DROP CONSTRAINT "cdoctype_proforma";

ALTER TABLE "c_doctype"
    DROP CONSTRAINT "cdoctype_shipment";

ALTER TABLE "c_doctype"
    DROP CONSTRAINT "cdoctype_invoice";

ALTER TABLE "c_doctype"
    DROP CONSTRAINT "glcategory_cdoctype";

ALTER TABLE "c_cyclestep"
    DROP CONSTRAINT "ccycle_ccyclestep";

ALTER TABLE "c_cyclephase"
    DROP CONSTRAINT "ccyclestep_ccyclephase";

ALTER TABLE "c_cyclephase"
    DROP CONSTRAINT "cphase_ccyclephase";

ALTER TABLE "c_cycle"
    DROP CONSTRAINT "ccurrency_ccycle";

ALTER TABLE "c_currency_trl"
    DROP CONSTRAINT "adlanguage_ccurrencytrl";

ALTER TABLE "c_currency_trl"
    DROP CONSTRAINT "ccurrency_ccurrencytrl";

ALTER TABLE "c_currency_acct"
    DROP CONSTRAINT "cacctschema_ccurrencyacct";

ALTER TABLE "c_currency_acct"
    DROP CONSTRAINT "ccurrency_ccurrencyacct";

ALTER TABLE "c_currency_acct"
    DROP CONSTRAINT "vc_unrealizedloss_ccurrency";

ALTER TABLE "c_currency_acct"
    DROP CONSTRAINT "vc_realizedloss_ccurrency";

ALTER TABLE "c_currency_acct"
    DROP CONSTRAINT "vc_realizedgain_ccurrency";

ALTER TABLE "c_currency_acct"
    DROP CONSTRAINT "vc_unrealizedgain_ccurrency";

ALTER TABLE "c_currency"
    DROP CONSTRAINT "c_currencyclient";

ALTER TABLE "c_currency"
    DROP CONSTRAINT "c_currencyorg";

ALTER TABLE "c_country_trl"
    DROP CONSTRAINT "adlanguage_ccountrytrl";

ALTER TABLE "c_country_trl"
    DROP CONSTRAINT "ccountry_ccountrytrl";

ALTER TABLE "c_country"
    DROP CONSTRAINT "c_countryclient";

ALTER TABLE "c_country"
    DROP CONSTRAINT "adlanguage_ccountry";

ALTER TABLE "c_country"
    DROP CONSTRAINT "c_countryorg";

ALTER TABLE "c_country"
    DROP CONSTRAINT "ccurrency_ccountry";

ALTER TABLE "c_conversion_rate"
    DROP CONSTRAINT "c_conversion_rateclient";

ALTER TABLE "c_conversion_rate"
    DROP CONSTRAINT "c_conversion_rateorg";

ALTER TABLE "c_conversion_rate"
    DROP CONSTRAINT "cconversiontype_cconvrate";

ALTER TABLE "c_conversion_rate"
    DROP CONSTRAINT "c_currencyconvrateto";

ALTER TABLE "c_conversion_rate"
    DROP CONSTRAINT "ccurrency_cconversionrate";

ALTER TABLE "c_commissionrun"
    DROP CONSTRAINT "ccommission_ccommissionrun";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "adorgtrx_ccommissionline";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "cbpartner_ccommissionline";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "cbpgroup_commissionline";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "ccommission_ccommissionline";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "csalesregion_ccommissionline";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "mproduct_ccommissionline";

ALTER TABLE "c_commissionline"
    DROP CONSTRAINT "mproductcat_ccommissionline";

ALTER TABLE "c_commissiondetail"
    DROP CONSTRAINT "ccommissionamt_ccomdetail";

ALTER TABLE "c_commissiondetail"
    DROP CONSTRAINT "ccurrency_ccommissiondetail";

ALTER TABLE "c_commissiondetail"
    DROP CONSTRAINT "cinvoiceline_ccommissiondet";

ALTER TABLE "c_commissiondetail"
    DROP CONSTRAINT "corderline_ccommissiondetail";

ALTER TABLE "c_commissionamt"
    DROP CONSTRAINT "ccomline_ccomamt";

ALTER TABLE "c_commissionamt"
    DROP CONSTRAINT "ccommentrun_ccommissionamt";

ALTER TABLE "c_commission"
    DROP CONSTRAINT "cbpartner_ccommission";

ALTER TABLE "c_commission"
    DROP CONSTRAINT "ccharge_ccommission";

ALTER TABLE "c_commission"
    DROP CONSTRAINT "ccurrency_ccommission";

ALTER TABLE "c_city"
    DROP CONSTRAINT "c_cityclient";

ALTER TABLE "c_city"
    DROP CONSTRAINT "c_cityorg";

ALTER TABLE "c_city"
    DROP CONSTRAINT "ccountry_ccity";

ALTER TABLE "c_city"
    DROP CONSTRAINT "cregion_ccity";

ALTER TABLE "c_charge_acct"
    DROP CONSTRAINT "cacctschema_cchargeacct";

ALTER TABLE "c_charge_acct"
    DROP CONSTRAINT "cchrage_cchargeacct";

ALTER TABLE "c_charge_acct"
    DROP CONSTRAINT "vc_chexpense_ccharge";

ALTER TABLE "c_charge_acct"
    DROP CONSTRAINT "vc_chrevenue_ccharge";

ALTER TABLE "c_charge"
    DROP CONSTRAINT "ctaxcategory_ccharge";

ALTER TABLE "c_cashline"
    DROP CONSTRAINT "cbankacct_ccashline";

ALTER TABLE "c_cashline"
    DROP CONSTRAINT "ccash_ccashline";

ALTER TABLE "c_cashline"
    DROP CONSTRAINT "ccharge_ccashline";

ALTER TABLE "c_cashline"
    DROP CONSTRAINT "ccurrency_ccashline";

ALTER TABLE "c_cashline"
    DROP CONSTRAINT "cinvoice_ccashline";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "cacctschema_ccashbookacct";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "ccashbook_ccashbookacct";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "vc_cbdifferences_ccashbook";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "vc_cbreceipt_ccashbook";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "vc_cbcashtransfer_ccashbook";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "vc_cbasset_ccashbook";

ALTER TABLE "c_cashbook_acct"
    DROP CONSTRAINT "vc_cbexpense_ccashbook";

ALTER TABLE "c_cashbook"
    DROP CONSTRAINT "ccurrency_ccashbook";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "adorg_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "adorgtrx_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "cactivity_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "ccampaign_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "ccashbook_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "celementvalueuser2_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "celementvalueuser1_ccash";

ALTER TABLE "c_cash"
    DROP CONSTRAINT "cproject_ccash";

ALTER TABLE "c_campaign"
    DROP CONSTRAINT "cchannel_ccampaign";

ALTER TABLE "c_calendar"
    DROP CONSTRAINT "c_calendarclient";

ALTER TABLE "c_calendar"
    DROP CONSTRAINT "c_calendarorg";

ALTER TABLE "c_bp_withholding"
    DROP CONSTRAINT "cbpartner_cbpwithholding";

ALTER TABLE "c_bp_withholding"
    DROP CONSTRAINT "cwithholding_cbpwithholding";

ALTER TABLE "c_bp_vendor_acct"
    DROP CONSTRAINT "cacctschema_cbpvendoracct";

ALTER TABLE "c_bp_vendor_acct"
    DROP CONSTRAINT "c_buspartner_c_bp_vendor_acct";

ALTER TABLE "c_bp_vendor_acct"
    DROP CONSTRAINT "vc_vliability_cbpvendor";

ALTER TABLE "c_bp_vendor_acct"
    DROP CONSTRAINT "vc_vprepayment_cbpvendor";

ALTER TABLE "c_bp_vendor_acct"
    DROP CONSTRAINT "vc_vliabilityservices_cbpvendo";

ALTER TABLE "c_bp_relation"
    DROP CONSTRAINT "cbpartner_cbprelation";

ALTER TABLE "c_bp_relation"
    DROP CONSTRAINT "cbpartner_cbprelationbp";

ALTER TABLE "c_bp_relation"
    DROP CONSTRAINT "cbplocation_cbprelation";

ALTER TABLE "c_bp_relation"
    DROP CONSTRAINT "cbplocation_cbprelationbp";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "cacctschema_cbpgroupacct";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "cbpgroup_cbpgroupacct";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_notinvoicedreceipts_cbpgrou";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_notinvoicedrec_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_writeoff_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_paydiscountexp_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_paydiscountrev_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_unearnedrevenue_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_vliabilityservices_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_vliability_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_vprepayment_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_creceivable_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_cprepayment_cbpgroup";

ALTER TABLE "c_bp_group_acct"
    DROP CONSTRAINT "vc_notinvoicedrevenue_cbpgroup";

ALTER TABLE "c_bp_group"
    DROP CONSTRAINT "mdiscountschemapo_cbpgroup";

ALTER TABLE "c_bp_group"
    DROP CONSTRAINT "mdiscountschema_cbpgroup";

ALTER TABLE "c_bp_group"
    DROP CONSTRAINT "mpricelistpo_cbpgroup";

ALTER TABLE "c_bp_group"
    DROP CONSTRAINT "mpricelist_cbpgroup";

ALTER TABLE "c_bp_employee_acct"
    DROP CONSTRAINT "cacctschema_cbpemployeeacct";

ALTER TABLE "c_bp_employee_acct"
    DROP CONSTRAINT "cbuspartner_c_bpemployeeacct";

ALTER TABLE "c_bp_employee_acct"
    DROP CONSTRAINT "vc_eexpense_cbpemployee";

ALTER TABLE "c_bp_employee_acct"
    DROP CONSTRAINT "vc_eprepayment_cbpemployee";

ALTER TABLE "c_bp_edi"
    DROP CONSTRAINT "adsequence_cbpedi";

ALTER TABLE "c_bp_edi"
    DROP CONSTRAINT "c_bpartner_cbpedi";

ALTER TABLE "c_bp_edi"
    DROP CONSTRAINT "mwarehouse_cbpedi";

ALTER TABLE "c_bp_customer_acct"
    DROP CONSTRAINT "cacctschema_cbpcustomeracct";

ALTER TABLE "c_bp_customer_acct"
    DROP CONSTRAINT "cbuspartner_cbpcustomer_acct";

ALTER TABLE "c_bp_customer_acct"
    DROP CONSTRAINT "vc_cprepayment_cbpcustomer";

ALTER TABLE "c_bp_customer_acct"
    DROP CONSTRAINT "vc_creceivable_cbpcustomer";

ALTER TABLE "c_bp_bankaccount"
    DROP CONSTRAINT "aduser_cbpbankaccount";

ALTER TABLE "c_bp_bankaccount"
    DROP CONSTRAINT "cbank_cbpbankaccount";

ALTER TABLE "c_bp_bankaccount"
    DROP CONSTRAINT "cbpartner_cbpbankaccount";

ALTER TABLE "c_bpartner_product"
    DROP CONSTRAINT "cbpartner_cbpproduct";

ALTER TABLE "c_bpartner_product"
    DROP CONSTRAINT "mproduct_cbpproduct";

ALTER TABLE "c_bpartner_location"
    DROP CONSTRAINT "c_buspartner_locationclient";

ALTER TABLE "c_bpartner_location"
    DROP CONSTRAINT "c_buspartner_locationorg";

ALTER TABLE "c_bpartner_location"
    DROP CONSTRAINT "cbpartner_cbplocation";

ALTER TABLE "c_bpartner_location"
    DROP CONSTRAINT "clocation_cbplocation";

ALTER TABLE "c_bpartner_location"
    DROP CONSTRAINT "csalesregion_bpartnerlocation";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "adclient_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "ad_language_c_buspartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "adorg_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "adorg_cbpartnerorg";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "adprintformatinv_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "adusersalesrep_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cbpartner_cpbartnerparent";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cbpgroup_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cdunning_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cgreeting_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cinvoiceschedule_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cpopaymentterm_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "cpaymentterm_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "mdiscounts_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "mdiscountspo_cbpartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "mpricelistpo_cbuspartner";

ALTER TABLE "c_bpartner"
    DROP CONSTRAINT "mpricelist_cbpartner";

ALTER TABLE "c_bankstatementloader"
    DROP CONSTRAINT "cbankacct_cbankstmtloader";

ALTER TABLE "c_bankstatementline"
    DROP CONSTRAINT "cbstatement_cbstatementline";

ALTER TABLE "c_bankstatementline"
    DROP CONSTRAINT "cbpartner_cbankstatementline";

ALTER TABLE "c_bankstatementline"
    DROP CONSTRAINT "ccharge_cbankstmtlime";

ALTER TABLE "c_bankstatementline"
    DROP CONSTRAINT "ccurrency_cbankstmtline";

ALTER TABLE "c_bankstatementline"
    DROP CONSTRAINT "cinvoice_cbankstatementline";

ALTER TABLE "c_bankstatementline"
    DROP CONSTRAINT "cpayment_cbankstmtline";

ALTER TABLE "c_bankstatement"
    DROP CONSTRAINT "cbankaccount_cbankstatement";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "cacctschema_cbankaccountacct";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "cbankaccount_cbankacctacct";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_binterestrev_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bintransit_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bpaymentselect_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_brevaluationloss_cbankaccou";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bsettlementgain_cbankaccoun";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bsettlementloss_cbankaccoun";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bunallocatedcash_cbankaccou";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bunidentified_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_basset_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_binterestexp_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_bexpense_cbankaccount";

ALTER TABLE "c_bankaccount_acct"
    DROP CONSTRAINT "vc_brevaluationgain_cbankaccou";

ALTER TABLE "c_bankaccountdoc"
    DROP CONSTRAINT "adprintformat_cbankaccountdoc";

ALTER TABLE "c_bankaccountdoc"
    DROP CONSTRAINT "cbankaccount_cbadoc";

ALTER TABLE "c_bankaccount"
    DROP CONSTRAINT "cbank_cbankaccount";

ALTER TABLE "c_bankaccount"
    DROP CONSTRAINT "ccurrency_cbankaccount";

ALTER TABLE "c_bank"
    DROP CONSTRAINT "clocation_cbank";

ALTER TABLE "c_allocationline"
    DROP CONSTRAINT "callocation_callocationline";

ALTER TABLE "c_allocationline"
    DROP CONSTRAINT "cbpartner_callocationline";

ALTER TABLE "c_allocationline"
    DROP CONSTRAINT "ccashline_callocationline";

ALTER TABLE "c_allocationline"
    DROP CONSTRAINT "cinvoice_callocationline";

ALTER TABLE "c_allocationline"
    DROP CONSTRAINT "corder_callocation";

ALTER TABLE "c_allocationline"
    DROP CONSTRAINT "cpayment_callocationline";

ALTER TABLE "c_allocationhdr"
    DROP CONSTRAINT "ccurrency_callocation";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "cacctschema_cacctschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_incomesummary_cschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_intercompanyduefrom_cschema";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_suspenseerror_cschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_ppvoffset_cschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_retainedearning_cschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_suspensebalancing_cschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_currencybalancing_cschemagl";

ALTER TABLE "c_acctschema_gl"
    DROP CONSTRAINT "vc_intercompanydueto_cschemagl";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "adclient_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "adorg_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "adorgid_c_aschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "cacctschema_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "cactivity_cacctschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "cbuspartner_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "socampaign_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "celement_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "celementvalue_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "clocation_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "cproject_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "csalesregion_caschemaelement";

ALTER TABLE "c_acctschema_element"
    DROP CONSTRAINT "mproduct_caschemaelement";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "cacctschema_default";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_realizedgain_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_realizedloss_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_tcredit_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_tdue_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_texpense_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_tliability_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_trec_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_unearnedrevenue_cschemadefa";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_unrealizedgain_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_unrealizedloss_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_vliabilityservices_cschemad";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_vliability_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_vprepayment_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_wdifferences_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_winvactualadjust_cschemadef";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_winventory_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_withholding_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_wrevaluation_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_writeoff_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_basset_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bexpense_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_binterestexp_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_binterestrev_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bintransit_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bpaymentselect_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_brevaluationgain_cschemadef";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_brevaluationloss_cschemadef";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bsettlementgain_cschemadefa";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bsettlementloss_cschemadefa";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bunallocatedcash_cschemadef";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_bunidentified_cschemadefaul";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_cbasset_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_cbcashtransfer_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_cbdifferences_cschemadefaul";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_cbexpense_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_cbreceipt_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_chexpense_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_chrevenue_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_cprepayment_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_creceivable_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_eexpense_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_eprepayment_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_notinvoicedreceipts_cschema";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_notinvoicedrec_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_notinvoicedrevenue_cschemad";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_passet_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_paydiscountexp_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_paydiscountrev_cschemadefau";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_pcogs_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_pexpense_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_pinvoicepv_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_pjasset_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_pjwip_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_ppurchasepv_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_prevenue_cschemadefault";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_ptdiscountgrant_cschemadefa";

ALTER TABLE "c_acctschema_default"
    DROP CONSTRAINT "vc_ptdiscountrec_cschemadefaul";

ALTER TABLE "c_acctschema"
    DROP CONSTRAINT "ad_client_c_acctschema";

ALTER TABLE "c_acctschema"
    DROP CONSTRAINT "ad_org_c_acctschema";

ALTER TABLE "c_acctschema"
    DROP CONSTRAINT "c_currency_c_acctschema";

ALTER TABLE "c_acctschema"
    DROP CONSTRAINT "cperiod_cacctschema";

ALTER TABLE "c_acctschema"
    DROP CONSTRAINT "mcosttype_cacctschema";

ALTER TABLE "c_acctprocessorlog"
    DROP CONSTRAINT "cacctprocessor_log";

ALTER TABLE "c_acctprocessor"
    DROP CONSTRAINT "aduser_cacctprocessor";

ALTER TABLE "cm_wikitoken"
    DROP CONSTRAINT "adtable_cmwikitoken";

ALTER TABLE "cm_webproject_domain"
    DROP CONSTRAINT "cmcontainer_cmwebprojectdomain";

ALTER TABLE "cm_webproject_domain"
    DROP CONSTRAINT "cmwebproject_cmwpdomain";

ALTER TABLE "cm_webproject"
    DROP CONSTRAINT "adtreecmc_cmwebproject";

ALTER TABLE "cm_webproject"
    DROP CONSTRAINT "adtreecmt_cmwebproject";

ALTER TABLE "cm_webproject"
    DROP CONSTRAINT "adtreecms_cmwebproject";

ALTER TABLE "cm_webproject"
    DROP CONSTRAINT "adtreecmm_cmwebproject";

ALTER TABLE "cm_webaccesslog"
    DROP CONSTRAINT "aduser_cmwebaccesslog";

ALTER TABLE "cm_webaccesslog"
    DROP CONSTRAINT "cmbroadcastserver_cmwebalog";

ALTER TABLE "cm_webaccesslog"
    DROP CONSTRAINT "cmmedia_cmwebaccesslog";

ALTER TABLE "cm_webaccesslog"
    DROP CONSTRAINT "cmwebproject_cmwebaccesslog";

ALTER TABLE "cm_template_ad_cat"
    DROP CONSTRAINT "cmadcat_cmtemplateadcat";

ALTER TABLE "cm_template_ad_cat"
    DROP CONSTRAINT "cmtemplate_cmtemplateadcat";

ALTER TABLE "cm_templatetable"
    DROP CONSTRAINT "adtable_cmtemplatetable";

ALTER TABLE "cm_templatetable"
    DROP CONSTRAINT "cmtemplate_cmttable";

ALTER TABLE "cm_template"
    DROP CONSTRAINT "cmwebproject_cmtemplate";

ALTER TABLE "cm_newsitem"
    DROP CONSTRAINT "cmnewschannel_cmnewsitem";

ALTER TABLE "cm_newschannel"
    DROP CONSTRAINT "cmwebproject_cmnewschannel";

ALTER TABLE "cm_media_server"
    DROP CONSTRAINT "cmwebproject_cmmediaserver";

ALTER TABLE "cm_mediadeploy"
    DROP CONSTRAINT "cmmedia_cmmediadeploy";

ALTER TABLE "cm_mediadeploy"
    DROP CONSTRAINT "cmmediaserver_cmmediadeploy";

ALTER TABLE "cm_media"
    DROP CONSTRAINT "cmwebproject_cmmedia";

ALTER TABLE "cm_cstage_trl"
    DROP CONSTRAINT "adlanguage_cmcstagetrl";

ALTER TABLE "cm_cstage_trl"
    DROP CONSTRAINT "cmcstage_cmcstagetrl";

ALTER TABLE "cm_cstage_element_trl"
    DROP CONSTRAINT "adlanguage_cmcstageeletrl";

ALTER TABLE "cm_cstage_element_trl"
    DROP CONSTRAINT "cmcstageelement_cmcsetrl";

ALTER TABLE "cm_cstage_element"
    DROP CONSTRAINT "cmcstage_cmcstageelement";

ALTER TABLE "cm_cstagettable"
    DROP CONSTRAINT "cmstage_cmcstagettable";

ALTER TABLE "cm_cstagettable"
    DROP CONSTRAINT "cmttable_cmstagettable";

ALTER TABLE "cm_cstage"
    DROP CONSTRAINT "cmcstage_cmcstagelink";

ALTER TABLE "cm_cstage"
    DROP CONSTRAINT "cmtemplate_cmcstage";

ALTER TABLE "cm_cstage"
    DROP CONSTRAINT "cmwebproject_cmcstage";

ALTER TABLE "cm_container_url"
    DROP CONSTRAINT "cmcontainer_cmcontainerurl";

ALTER TABLE "cm_container_trl"
    DROP CONSTRAINT "adlanguage_cmcontainertrl";

ALTER TABLE "cm_container_trl"
    DROP CONSTRAINT "cmcontainer_cmcontainertrl";

ALTER TABLE "cm_container_element_trl"
    DROP CONSTRAINT "adlanguage_cmcontainereletrl";

ALTER TABLE "cm_container_element_trl"
    DROP CONSTRAINT "cmcontainerelement_cmcetrl";

ALTER TABLE "cm_container_element"
    DROP CONSTRAINT "cmcontainer_cmcontainerelement";

ALTER TABLE "cm_containerttable"
    DROP CONSTRAINT "cmcontainer_cmcontainerttable";

ALTER TABLE "cm_containerttable"
    DROP CONSTRAINT "cmttable_cmcontainertable";

ALTER TABLE "cm_container"
    DROP CONSTRAINT "cmcontainer_cmcontainerlink";

ALTER TABLE "cm_container"
    DROP CONSTRAINT "cmtemplate_cmcontainer";

ALTER TABLE "cm_container"
    DROP CONSTRAINT "cmwebproject_cmcontainer";

ALTER TABLE "cm_chatupdate"
    DROP CONSTRAINT "aduser_cmchatupdate";

ALTER TABLE "cm_chatupdate"
    DROP CONSTRAINT "cmchat_cmchatupdate";

ALTER TABLE "cm_chattypeupdate"
    DROP CONSTRAINT "aduser_cmchattypeupdate";

ALTER TABLE "cm_chattypeupdate"
    DROP CONSTRAINT "cmchattype_cmchattypeupdate";

ALTER TABLE "cm_chattype"
    DROP CONSTRAINT "adtable_cmchattype";

ALTER TABLE "cm_chatentry"
    DROP CONSTRAINT "aduser_cmchatentry";

ALTER TABLE "cm_chatentry"
    DROP CONSTRAINT "cmchat_chchatentry";

ALTER TABLE "cm_chatentry"
    DROP CONSTRAINT "cmentrty_cmentryparent";

ALTER TABLE "cm_chatentry"
    DROP CONSTRAINT "cmchatentry_grandparent";

ALTER TABLE "cm_chat"
    DROP CONSTRAINT "adtable_cmchat";

ALTER TABLE "cm_chat"
    DROP CONSTRAINT "cmchattype_cmchat";

ALTER TABLE "cm_broadcastserver"
    DROP CONSTRAINT "cmwebproject_cmbroadcastserver";

ALTER TABLE "cm_ad_cat"
    DROP CONSTRAINT "cmwebproject_cmadcat";

ALTER TABLE "cm_ad"
    DROP CONSTRAINT "cmadcat_cmad";

ALTER TABLE "cm_ad"
    DROP CONSTRAINT "cmmedia_cmad";

ALTER TABLE "cm_accessstage"
    DROP CONSTRAINT "cmaccessprofile_cmaccessstage";

ALTER TABLE "cm_accessstage"
    DROP CONSTRAINT "cmcstage_cmaccessstage";

ALTER TABLE "cm_accessnewschannel"
    DROP CONSTRAINT "cmaccesprofile_cmanewschannel";

ALTER TABLE "cm_accessnewschannel"
    DROP CONSTRAINT "cnmewschannel_cmaccessnewsc";

ALTER TABLE "cm_accessmedia"
    DROP CONSTRAINT "cmaccessprofile_cmaccessmedia";

ALTER TABLE "cm_accessmedia"
    DROP CONSTRAINT "cmmedia_cmaccessmedia";

ALTER TABLE "cm_accesslistrole"
    DROP CONSTRAINT "adrole_cmalistrole";

ALTER TABLE "cm_accesslistrole"
    DROP CONSTRAINT "cmaccessprofile_cmalistrole";

ALTER TABLE "cm_accesslistbpgroup"
    DROP CONSTRAINT "cmaccessprofile_cmalbpgroup";

ALTER TABLE "cm_accesslistbpgroup"
    DROP CONSTRAINT "cbpgrpup_cmalistbpgroup";

ALTER TABLE "cm_accesscontainer"
    DROP CONSTRAINT "cmaccessprofile_cmacccontainer";

ALTER TABLE "cm_accesscontainer"
    DROP CONSTRAINT "cmcontainer_cmaccesscontainer";

ALTER TABLE "b_topictype"
    DROP CONSTRAINT "mpricelist_btopictype";

ALTER TABLE "b_topictype"
    DROP CONSTRAINT "mproduct_btopictypemember";

ALTER TABLE "b_topictype"
    DROP CONSTRAINT "mproduct_btopictype";

ALTER TABLE "b_topiccategory"
    DROP CONSTRAINT "btopictype_btopiccategory";

ALTER TABLE "b_topic"
    DROP CONSTRAINT "btopiccategory_btopic";

ALTER TABLE "b_topic"
    DROP CONSTRAINT "btopictype_btopic";

ALTER TABLE "b_sellerfunds"
    DROP CONSTRAINT "bseller_bsellerfunds";

ALTER TABLE "b_sellerfunds"
    DROP CONSTRAINT "corder_bsellerfunds";

ALTER TABLE "b_sellerfunds"
    DROP CONSTRAINT "cpayment_bsellerfunds";

ALTER TABLE "b_seller"
    DROP CONSTRAINT "aduser_bseller";

ALTER TABLE "b_offer"
    DROP CONSTRAINT "bseller_boffer";

ALTER TABLE "b_offer"
    DROP CONSTRAINT "bsellerfunds_boffer";

ALTER TABLE "b_offer"
    DROP CONSTRAINT "btopic_boffer";

ALTER TABLE "b_buyerfunds"
    DROP CONSTRAINT "bbuyer_bbuyerfunds";

ALTER TABLE "b_buyerfunds"
    DROP CONSTRAINT "corder_bbuyersfunds";

ALTER TABLE "b_buyerfunds"
    DROP CONSTRAINT "cpayment_bbuyerfunds";

ALTER TABLE "b_buyer"
    DROP CONSTRAINT "aduser_bbuyer";

ALTER TABLE "b_bidcomment"
    DROP CONSTRAINT "aduser_bidcomment";

ALTER TABLE "b_bidcomment"
    DROP CONSTRAINT "btopic_bbidcomment";

ALTER TABLE "b_bid"
    DROP CONSTRAINT "bbuyer_bbid";

ALTER TABLE "b_bid"
    DROP CONSTRAINT "bbuyerfunds_bbid";

ALTER TABLE "b_bid"
    DROP CONSTRAINT "btopic_bbid";

ALTER TABLE "a_registrationvalue"
    DROP CONSTRAINT "aregistration_aregvalue";

ALTER TABLE "a_registrationvalue"
    DROP CONSTRAINT "aregattribute_aregvalue";

ALTER TABLE "a_registrationproduct"
    DROP CONSTRAINT "aregattribute_aregproduct";

ALTER TABLE "a_registrationproduct"
    DROP CONSTRAINT "mproduct_aregproduct";

ALTER TABLE "a_registrationattribute"
    DROP CONSTRAINT "adreference_aregattribute";

ALTER TABLE "a_registrationattribute"
    DROP CONSTRAINT "adreferencevalue_aregattribute";

ALTER TABLE "a_registration"
    DROP CONSTRAINT "aduser_aregistration";

ALTER TABLE "a_registration"
    DROP CONSTRAINT "aasset_aregistration";

ALTER TABLE "a_registration"
    DROP CONSTRAINT "cbpartner_aregistration";

ALTER TABLE "a_registration"
    DROP CONSTRAINT "mproduct_aregistration";

ALTER TABLE "a_asset_use"
    DROP CONSTRAINT "aasset_aassetuse";

ALTER TABLE "a_asset_retirement"
    DROP CONSTRAINT "aasset_aassetretirement";

ALTER TABLE "a_asset_retirement"
    DROP CONSTRAINT "cinvoiceline_aassetretirement";

ALTER TABLE "a_asset_group_acct"
    DROP CONSTRAINT "aassetgroup_aassetgroupacct";

ALTER TABLE "a_asset_group_acct"
    DROP CONSTRAINT "adepreciation_aassetgroupacct";

ALTER TABLE "a_asset_group_acct"
    DROP CONSTRAINT "cacctschema_aassetgroupacct";

ALTER TABLE "a_asset_delivery"
    DROP CONSTRAINT "aduser_aassetdelivery";

ALTER TABLE "a_asset_delivery"
    DROP CONSTRAINT "aasset_aassetdelivery";

ALTER TABLE "a_asset_delivery"
    DROP CONSTRAINT "moutline_aassetdelivery";

ALTER TABLE "a_asset_delivery"
    DROP CONSTRAINT "mproductdl_aassetdelivery";

ALTER TABLE "a_asset_change_amt"
    DROP CONSTRAINT "aassetchange_aassetchangeamt";

ALTER TABLE "a_asset_change_amt"
    DROP CONSTRAINT "cacctschema_aassetchangeamt";

ALTER TABLE "a_asset_change"
    DROP CONSTRAINT "aasset_aassetchange";

ALTER TABLE "a_asset_change"
    DROP CONSTRAINT "aaaddition_aachange";

ALTER TABLE "a_asset_change"
    DROP CONSTRAINT "aaretirement_aachange";

ALTER TABLE "a_asset_addition"
    DROP CONSTRAINT "aasset_aassetaddition";

ALTER TABLE "a_asset_addition"
    DROP CONSTRAINT "cinvoiceline_aassetaddition";

ALTER TABLE "a_asset_acct"
    DROP CONSTRAINT "aasset_aassetacct";

ALTER TABLE "a_asset_acct"
    DROP CONSTRAINT "adepreciation_aassetacct";

ALTER TABLE "a_asset_acct"
    DROP CONSTRAINT "cacctschema_aassetacct";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "aduser_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "aassetgroup_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "cbpartner_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "cbplocation_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "clocation_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "mattributesetinstance_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "mlocator_aasset";

ALTER TABLE "a_asset"
    DROP CONSTRAINT "mproduct_aasset";

ALTER TABLE "ad_workflow_trl"
    DROP CONSTRAINT "ad_language_workflowtrl";

ALTER TABLE "ad_workflow_trl"
    DROP CONSTRAINT "ad_workflowtrl";

ALTER TABLE "ad_workflow_access"
    DROP CONSTRAINT "ad_workflowaccess_client";

ALTER TABLE "ad_workflow_access"
    DROP CONSTRAINT "ad_workflowaccess_org";

ALTER TABLE "ad_workflow_access"
    DROP CONSTRAINT "adrole_adworkflowaccess";

ALTER TABLE "ad_workflow_access"
    DROP CONSTRAINT "adworkfow_workflowaccess";

ALTER TABLE "ad_workflowprocessorlog"
    DROP CONSTRAINT "adworkflowprocessor_log";

ALTER TABLE "ad_workflowprocessor"
    DROP CONSTRAINT "aduser_adworkflowprocessor";

ALTER TABLE "ad_workflow"
    DROP CONSTRAINT "workflowclient";

ALTER TABLE "ad_workflow"
    DROP CONSTRAINT "workfloworg";

ALTER TABLE "ad_workflow"
    DROP CONSTRAINT "adwfresponsible_adworkflow";

ALTER TABLE "ad_workflow"
    DROP CONSTRAINT "adworkflowprocessor_adwf";

ALTER TABLE "ad_workbench_trl"
    DROP CONSTRAINT "adlanguage_adworkbenchtrl";

ALTER TABLE "ad_workbench_trl"
    DROP CONSTRAINT "adworkbench_adworkbenchtrl";

ALTER TABLE "ad_workbenchwindow"
    DROP CONSTRAINT "adform_adworkbenchwindow";

ALTER TABLE "ad_workbenchwindow"
    DROP CONSTRAINT "adprocess_adworkbenchwindow";

ALTER TABLE "ad_workbenchwindow"
    DROP CONSTRAINT "adtask_adworkbenchwindow";

ALTER TABLE "ad_workbenchwindow"
    DROP CONSTRAINT "adwindow_adworkbenchwindow";

ALTER TABLE "ad_workbenchwindow"
    DROP CONSTRAINT "adworkbench_adworkbenchwindow";

ALTER TABLE "ad_workbench"
    DROP CONSTRAINT "adcolor_adworkbench";

ALTER TABLE "ad_workbench"
    DROP CONSTRAINT "adimage_adworkbench";

ALTER TABLE "ad_window_trl"
    DROP CONSTRAINT "ad_language_windowtrl";

ALTER TABLE "ad_window_trl"
    DROP CONSTRAINT "ad_windowtrl";

ALTER TABLE "ad_window_access"
    DROP CONSTRAINT "ad_functaccess_client";

ALTER TABLE "ad_window_access"
    DROP CONSTRAINT "ad_functaccessorg";

ALTER TABLE "ad_window_access"
    DROP CONSTRAINT "adrole_adwindowaccess";

ALTER TABLE "ad_window_access"
    DROP CONSTRAINT "adwindow_adwindowaccess";

ALTER TABLE "ad_window"
    DROP CONSTRAINT "windowclient";

ALTER TABLE "ad_window"
    DROP CONSTRAINT "adcolor_adwindow";

ALTER TABLE "ad_window"
    DROP CONSTRAINT "adimage_adwindow";

ALTER TABLE "ad_window"
    DROP CONSTRAINT "windoworg";

ALTER TABLE "ad_wf_responsible"
    DROP CONSTRAINT "adorg_adwfresponsible";

ALTER TABLE "ad_wf_responsible"
    DROP CONSTRAINT "adrole_adwfresponsible";

ALTER TABLE "ad_wf_responsible"
    DROP CONSTRAINT "aduser_adwfresponsible";

ALTER TABLE "ad_wf_processdata"
    DROP CONSTRAINT "adwfproccess_adwfprocessdata";

ALTER TABLE "ad_wf_process"
    DROP CONSTRAINT "wf_instanceclient";

ALTER TABLE "ad_wf_process"
    DROP CONSTRAINT "admessage_adwfprocess";

ALTER TABLE "ad_wf_process"
    DROP CONSTRAINT "wf_instanceorg";

ALTER TABLE "ad_wf_process"
    DROP CONSTRAINT "aduser_adwfprocess";

ALTER TABLE "ad_wf_process"
    DROP CONSTRAINT "adwfresponsible_adwfprocess";

ALTER TABLE "ad_wf_process"
    DROP CONSTRAINT "adworkflow_adwfprocess";

ALTER TABLE "ad_wf_node_trl"
    DROP CONSTRAINT "ad_language_wfnodetrl";

ALTER TABLE "ad_wf_node_trl"
    DROP CONSTRAINT "ad_wfnodetrl";

ALTER TABLE "ad_wf_node_para"
    DROP CONSTRAINT "adprocesspara_adwfnodepara";

ALTER TABLE "ad_wf_node_para"
    DROP CONSTRAINT "adwfnode_adwfnodepara";

ALTER TABLE "ad_wf_nodenext"
    DROP CONSTRAINT "wf_nodenextclient";

ALTER TABLE "ad_wf_nodenext"
    DROP CONSTRAINT "wf_nodenextorg";

ALTER TABLE "ad_wf_nodenext"
    DROP CONSTRAINT "adwfnodenext_adwfnodenext";

ALTER TABLE "ad_wf_nodenext"
    DROP CONSTRAINT "adwfnode_adwfnodenext";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "wf_nodeclient";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adform_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adimage_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "wf_nodeorg";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adprocess_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adtask_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adwfblock_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adwfresponsible_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adwindow_adwfnode";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adworkflow_adwfnodesubflow";

ALTER TABLE "ad_wf_node"
    DROP CONSTRAINT "adworkflow_adwfnode";

ALTER TABLE "ad_wf_nextcondition"
    DROP CONSTRAINT "adcolumn_adwfnextcondition";

ALTER TABLE "ad_wf_nextcondition"
    DROP CONSTRAINT "adwfnodenext_adwfnextcond";

ALTER TABLE "ad_wf_eventaudit"
    DROP CONSTRAINT "adtable_adwfeventaudit";

ALTER TABLE "ad_wf_eventaudit"
    DROP CONSTRAINT "aduser_adwfeventaudit";

ALTER TABLE "ad_wf_eventaudit"
    DROP CONSTRAINT "adwfnode_adwfeventaudit";

ALTER TABLE "ad_wf_eventaudit"
    DROP CONSTRAINT "adwfprocess_adwfeventaudit";

ALTER TABLE "ad_wf_eventaudit"
    DROP CONSTRAINT "adwfresponsib_adwfeventaudit";

ALTER TABLE "ad_wf_block"
    DROP CONSTRAINT "adworkflow_adwfblock";

ALTER TABLE "ad_wf_activityresult"
    DROP CONSTRAINT "adwfactivity_adwfactresult";

ALTER TABLE "ad_wf_activity"
    DROP CONSTRAINT "admessage_adwfactivity";

ALTER TABLE "ad_wf_activity"
    DROP CONSTRAINT "aduser_adwfactivity";

ALTER TABLE "ad_wf_activity"
    DROP CONSTRAINT "adwfnode_adwfactivity";

ALTER TABLE "ad_wf_activity"
    DROP CONSTRAINT "adwfprocess_adwfactivity";

ALTER TABLE "ad_wf_activity"
    DROP CONSTRAINT "adwfresponsible_adwfactivity";

ALTER TABLE "ad_val_rule"
    DROP CONSTRAINT "val_ruleclient";

ALTER TABLE "ad_val_rule"
    DROP CONSTRAINT "val_ruleorg";

ALTER TABLE "ad_user_substitute"
    DROP CONSTRAINT "aduser_adusersub";

ALTER TABLE "ad_user_substitute"
    DROP CONSTRAINT "adusersub_ad_usersub";

ALTER TABLE "ad_user_roles"
    DROP CONSTRAINT "ad_userrolesclient";

ALTER TABLE "ad_user_roles"
    DROP CONSTRAINT "ad_userrolesorg";

ALTER TABLE "ad_user_roles"
    DROP CONSTRAINT "adrole_aduserroles";

ALTER TABLE "ad_user_roles"
    DROP CONSTRAINT "aduser_userroles";

ALTER TABLE "ad_user_orgaccess"
    DROP CONSTRAINT "adorg_aduserorgaccess";

ALTER TABLE "ad_user_orgaccess"
    DROP CONSTRAINT "aduser_aduserorgaccess";

ALTER TABLE "ad_userquery"
    DROP CONSTRAINT "adtable_aduserquery";

ALTER TABLE "ad_userquery"
    DROP CONSTRAINT "aduser_aduserquery";

ALTER TABLE "ad_usermail"
    DROP CONSTRAINT "aduser_adusermail";

ALTER TABLE "ad_usermail"
    DROP CONSTRAINT "rmailtext_adusermail";

ALTER TABLE "ad_usermail"
    DROP CONSTRAINT "wmailmsg_adusermail";

ALTER TABLE "ad_userdef_win"
    DROP CONSTRAINT "adrole_aduserdefwin";

ALTER TABLE "ad_userdef_win"
    DROP CONSTRAINT "aduser_aduserdefwin";

ALTER TABLE "ad_userdef_win"
    DROP CONSTRAINT "adwindow_aduserdefwin";

ALTER TABLE "ad_userdef_tab"
    DROP CONSTRAINT "adtab_aduserdeftab";

ALTER TABLE "ad_userdef_tab"
    DROP CONSTRAINT "aduserdefwin_aduserdeftab";

ALTER TABLE "ad_userdef_field"
    DROP CONSTRAINT "adfield_aduserdeffield";

ALTER TABLE "ad_userdef_field"
    DROP CONSTRAINT "aduserdeftab_aduserdeffield";

ALTER TABLE "ad_userbpaccess"
    DROP CONSTRAINT "aduser_aduserbpaccess";

ALTER TABLE "ad_userbpaccess"
    DROP CONSTRAINT "rrequesttype_aduserbpaccess";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "ad_user_client";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "adorgtrx_aduser";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "ad_user_org";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "aduser_supervisor";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "cbpartner_aduser";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "cbplocation_aduser";

ALTER TABLE "ad_user"
    DROP CONSTRAINT "cgreeting_aduser";

ALTER TABLE "ad_treenodeu4"
    DROP CONSTRAINT "adtree_adtreenodeu4";

ALTER TABLE "ad_treenodeu3"
    DROP CONSTRAINT "adtree_adtreenodeu3";

ALTER TABLE "ad_treenodeu2"
    DROP CONSTRAINT "adtree_adtreenodeu2";

ALTER TABLE "ad_treenodeu1"
    DROP CONSTRAINT "adtree_adtreenodeu1";

ALTER TABLE "ad_treenodepr"
    DROP CONSTRAINT "adtree_adtreenodepr";

ALTER TABLE "ad_treenodemm"
    DROP CONSTRAINT "adtree_adtreenodemm";

ALTER TABLE "ad_treenodecmt"
    DROP CONSTRAINT "adtree_adtreenodecmt";

ALTER TABLE "ad_treenodecms"
    DROP CONSTRAINT "adtree_adtreenodecms";

ALTER TABLE "ad_treenodecmm"
    DROP CONSTRAINT "adtree_adtreenodecmm";

ALTER TABLE "ad_treenodecmc"
    DROP CONSTRAINT "adtree_adtreenodecmc";

ALTER TABLE "ad_treenodebp"
    DROP CONSTRAINT "adtree_adtreenodebp";

ALTER TABLE "ad_treenode"
    DROP CONSTRAINT "adtree_adtreenode";

ALTER TABLE "ad_treebar"
    DROP CONSTRAINT "adtree_adtreebar";

ALTER TABLE "ad_treebar"
    DROP CONSTRAINT "aduser_adtreebar";

ALTER TABLE "ad_task_trl"
    DROP CONSTRAINT "ad_language_tasktrl";

ALTER TABLE "ad_task_trl"
    DROP CONSTRAINT "ad_tasktrl";

ALTER TABLE "ad_task_access"
    DROP CONSTRAINT "ad_taskaccess_client";

ALTER TABLE "ad_task_access"
    DROP CONSTRAINT "ad_taskaccess_org";

ALTER TABLE "ad_task_access"
    DROP CONSTRAINT "adrole_adtaskaccess";

ALTER TABLE "ad_task_access"
    DROP CONSTRAINT "adtask_adtaskaccess";

ALTER TABLE "ad_taskinstance"
    DROP CONSTRAINT "taskinstanceclient";

ALTER TABLE "ad_taskinstance"
    DROP CONSTRAINT "taskinstanceorg";

ALTER TABLE "ad_taskinstance"
    DROP CONSTRAINT "ad_task_taskinstance";

ALTER TABLE "ad_task"
    DROP CONSTRAINT "taskclient";

ALTER TABLE "ad_task"
    DROP CONSTRAINT "taskorg";

ALTER TABLE "ad_tab_trl"
    DROP CONSTRAINT "ad_language_tabtrl";

ALTER TABLE "ad_tab_trl"
    DROP CONSTRAINT "ad_tabtrl";

ALTER TABLE "ad_table_trl"
    DROP CONSTRAINT "adlanguage_adtabletrl";

ALTER TABLE "ad_table_trl"
    DROP CONSTRAINT "adtable_adtabletrl";

ALTER TABLE "ad_table_access"
    DROP CONSTRAINT "ad_dataaccessclient";

ALTER TABLE "ad_table_access"
    DROP CONSTRAINT "ad_dataaccessorg";

ALTER TABLE "ad_table_access"
    DROP CONSTRAINT "adrole_adtableaccess";

ALTER TABLE "ad_table_access"
    DROP CONSTRAINT "adtable_adtableaccess";

ALTER TABLE "ad_table"
    DROP CONSTRAINT "tableclient";

ALTER TABLE "ad_table"
    DROP CONSTRAINT "tableorg";

ALTER TABLE "ad_table"
    DROP CONSTRAINT "ad_valrule_table";

ALTER TABLE "ad_table"
    DROP CONSTRAINT "adwindowpo_adtable";

ALTER TABLE "ad_table"
    DROP CONSTRAINT "ad_window_table";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "tabclient";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "adcolumn_adtabsortyesno";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "adcolumn_adtabsortorder";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "ad_column_ad_tab";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "adimage_adtab";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "taborg";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "adprocess_adtab";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "adtab_included";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "ad_table_tab";

ALTER TABLE "ad_tab"
    DROP CONSTRAINT "ad_window_tab";

ALTER TABLE "ad_sequence_no"
    DROP CONSTRAINT "sequence_noclient";

ALTER TABLE "ad_sequence_no"
    DROP CONSTRAINT "sequence_noorg";

ALTER TABLE "ad_sequence_no"
    DROP CONSTRAINT "ad_sequence_sequenceno";

ALTER TABLE "ad_sequence_audit"
    DROP CONSTRAINT "sequence_auditclient";

ALTER TABLE "ad_sequence_audit"
    DROP CONSTRAINT "sequence_auditorg";

ALTER TABLE "ad_sequence_audit"
    DROP CONSTRAINT "ad_sequence_sequenceaudit";

ALTER TABLE "ad_sequence_audit"
    DROP CONSTRAINT "adtable_adsequenceaudit";

ALTER TABLE "ad_sequence"
    DROP CONSTRAINT "sequenceclient";

ALTER TABLE "ad_sequence"
    DROP CONSTRAINT "sequenceorg";

ALTER TABLE "ad_scheduler_para"
    DROP CONSTRAINT "adprocesspara_adschedulerpara";

ALTER TABLE "ad_scheduler_para"
    DROP CONSTRAINT "adscheduler_adschedulerpara";

ALTER TABLE "ad_schedulerrecipient"
    DROP CONSTRAINT "adrole_adschedulerrecipient";

ALTER TABLE "ad_schedulerrecipient"
    DROP CONSTRAINT "adscheduler_recipient";

ALTER TABLE "ad_schedulerrecipient"
    DROP CONSTRAINT "aduser_adschedulerrecipient";

ALTER TABLE "ad_schedulerlog"
    DROP CONSTRAINT "adscheduler_log";

ALTER TABLE "ad_scheduler"
    DROP CONSTRAINT "adprocess_adscheduler";

ALTER TABLE "ad_scheduler"
    DROP CONSTRAINT "aduser_adscheduler";

ALTER TABLE "ad_role_orgaccess"
    DROP CONSTRAINT "adorg_adroleorgaccess";

ALTER TABLE "ad_role_orgaccess"
    DROP CONSTRAINT "adrole_adroleorgaccess";

ALTER TABLE "ad_role"
    DROP CONSTRAINT "ad_roleclient";

ALTER TABLE "ad_role"
    DROP CONSTRAINT "ad_roleorg";

ALTER TABLE "ad_role"
    DROP CONSTRAINT "adtree_adrole";

ALTER TABLE "ad_role"
    DROP CONSTRAINT "adusersupervisor_adrole";

ALTER TABLE "ad_role"
    DROP CONSTRAINT "c_currency_ad_role";

ALTER TABLE "ad_reportview_col"
    DROP CONSTRAINT "adcolumn_adreportviewcol";

ALTER TABLE "ad_reportview_col"
    DROP CONSTRAINT "adreportview_col";

ALTER TABLE "ad_reportview"
    DROP CONSTRAINT "adtable_adreportview";

ALTER TABLE "ad_replication_run"
    DROP CONSTRAINT "adreplication_adreprun";

ALTER TABLE "ad_replication_log"
    DROP CONSTRAINT "adreptable_adreplog";

ALTER TABLE "ad_replication_log"
    DROP CONSTRAINT "adreplicationrun_adreplog";

ALTER TABLE "ad_replicationtable"
    DROP CONSTRAINT "adrepstrategy_adreptable";

ALTER TABLE "ad_replicationtable"
    DROP CONSTRAINT "adtable_adreplicationtable";

ALTER TABLE "ad_replication"
    DROP CONSTRAINT "adreplicationstrategy_adrep";

ALTER TABLE "ad_registration"
    DROP CONSTRAINT "adsystem_adregistration";

ALTER TABLE "ad_ref_table"
    DROP CONSTRAINT "ref_tableclient";

ALTER TABLE "ad_ref_table"
    DROP CONSTRAINT "ad_column_reftable_display";

ALTER TABLE "ad_ref_table"
    DROP CONSTRAINT "ad_column_reftable_id";

ALTER TABLE "ad_ref_table"
    DROP CONSTRAINT "ref_tableorg";

ALTER TABLE "ad_ref_table"
    DROP CONSTRAINT "ad_reference_reftable";

ALTER TABLE "ad_ref_table"
    DROP CONSTRAINT "add_table_reftable";

ALTER TABLE "ad_ref_list_trl"
    DROP CONSTRAINT "ad_language_reflisttrl";

ALTER TABLE "ad_ref_list_trl"
    DROP CONSTRAINT "ad_reflisttrl";

ALTER TABLE "ad_ref_list"
    DROP CONSTRAINT "ad_reflist_client";

ALTER TABLE "ad_ref_list"
    DROP CONSTRAINT "ad_reflist_org";

ALTER TABLE "ad_ref_list"
    DROP CONSTRAINT "ad_reference_reflist";

ALTER TABLE "ad_reference_trl"
    DROP CONSTRAINT "ad_language_referencetrl";

ALTER TABLE "ad_reference_trl"
    DROP CONSTRAINT "ad_referencetrl";

ALTER TABLE "ad_reference"
    DROP CONSTRAINT "referenceclient";

ALTER TABLE "ad_reference"
    DROP CONSTRAINT "referenceorg";

ALTER TABLE "ad_record_access"
    DROP CONSTRAINT "adrole_ardecordaccess";

ALTER TABLE "ad_record_access"
    DROP CONSTRAINT "adtable_adrecordaccess";

ALTER TABLE "ad_process_trl"
    DROP CONSTRAINT "ad_language_ad_process_trl";

ALTER TABLE "ad_process_trl"
    DROP CONSTRAINT "ad_process_ad_process_trl";

ALTER TABLE "ad_process_para_trl"
    DROP CONSTRAINT "adlanguage_adprocessparatrl";

ALTER TABLE "ad_process_para_trl"
    DROP CONSTRAINT "adprocpara_adprocparatrl";

ALTER TABLE "ad_process_para"
    DROP CONSTRAINT "adelement_adprocesspara";

ALTER TABLE "ad_process_para"
    DROP CONSTRAINT "adprocess_adprocesspara";

ALTER TABLE "ad_process_para"
    DROP CONSTRAINT "adreference_adprocesspara";

ALTER TABLE "ad_process_para"
    DROP CONSTRAINT "adreferencevalue_adprocpara";

ALTER TABLE "ad_process_para"
    DROP CONSTRAINT "advalrule_ad_processpara";

ALTER TABLE "ad_process_access"
    DROP CONSTRAINT "ad_processaccess_client";

ALTER TABLE "ad_process_access"
    DROP CONSTRAINT "ad_processtaccess_org";

ALTER TABLE "ad_process_access"
    DROP CONSTRAINT "adprocess_adprocessaccess";

ALTER TABLE "ad_process_access"
    DROP CONSTRAINT "adrole_adprocessaccess";

ALTER TABLE "ad_process"
    DROP CONSTRAINT "adprintformat_adprocess";

ALTER TABLE "ad_process"
    DROP CONSTRAINT "adreportview_adprocess";

ALTER TABLE "ad_private_access"
    DROP CONSTRAINT "adtable_adprivateaccess";

ALTER TABLE "ad_private_access"
    DROP CONSTRAINT "aduser_adprivateaccess";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintcolor_tablefunctbg";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintcolor_tablefunctfg";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintcolor_tablehdrline";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintcolor_tablehdrtextbg";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintcolor_tableline";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintcolor_tablehdrtextfg";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintfont_tableformatfunc";

ALTER TABLE "ad_printtableformat"
    DROP CONSTRAINT "adprintfont_tablehdr";

ALTER TABLE "ad_printlabelline_trl"
    DROP CONSTRAINT "adlanguage_adplabellinetrl";

ALTER TABLE "ad_printlabelline_trl"
    DROP CONSTRAINT "adprintlabelline_trl";

ALTER TABLE "ad_printlabelline"
    DROP CONSTRAINT "adcolumn_adprintlabelline";

ALTER TABLE "ad_printlabelline"
    DROP CONSTRAINT "adlabelprintfunc_labelline";

ALTER TABLE "ad_printlabelline"
    DROP CONSTRAINT "adprintlabel_adprintlabelline";

ALTER TABLE "ad_printlabel"
    DROP CONSTRAINT "adlabelprinter_printlabel";

ALTER TABLE "ad_printlabel"
    DROP CONSTRAINT "adtable_adprintlabel";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformat_adprintgraph";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformatitem_graphdata1";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformatitem_graphdata2";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformatitem_graphdata4";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformatitem_graphdescr";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformatitem_graphdata";

ALTER TABLE "ad_printgraph"
    DROP CONSTRAINT "adprintformatitem_graphdata3";

ALTER TABLE "ad_printformatitem_trl"
    DROP CONSTRAINT "adlanguage_adprintformitemtrl";

ALTER TABLE "ad_printformatitem_trl"
    DROP CONSTRAINT "adprintformatitem_trl";

ALTER TABLE "ad_printformatitem"
    DROP CONSTRAINT "adcolumn_adprintformatitem";

ALTER TABLE "ad_printformatitem"
    DROP CONSTRAINT "adprintcolor_adprintformatitem";

ALTER TABLE "ad_printformatitem"
    DROP CONSTRAINT "adprintfont_adprintformatitem";

ALTER TABLE "ad_printformatitem"
    DROP CONSTRAINT "adprintformat_printformatitem";

ALTER TABLE "ad_printformatitem"
    DROP CONSTRAINT "adprintformat_printformatchild";

ALTER TABLE "ad_printformatitem"
    DROP CONSTRAINT "adprintgraph_printformatitem";

ALTER TABLE "ad_printformat"
    DROP CONSTRAINT "adprintcolor_adprintformat";

ALTER TABLE "ad_printformat"
    DROP CONSTRAINT "ad_printfont_adprintformat";

ALTER TABLE "ad_printformat"
    DROP CONSTRAINT "adprintpaper_adprintformat";

ALTER TABLE "ad_printformat"
    DROP CONSTRAINT "adprintformattable_format";

ALTER TABLE "ad_printformat"
    DROP CONSTRAINT "adprintview_adprintformat";

ALTER TABLE "ad_printformat"
    DROP CONSTRAINT "adtable_adprintformat";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "adclient_adprintform";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "adprintformat_formremittance";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "adprintformat_formproject";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "adprintformat_formorder";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "adprintformat_forminvoice";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "adprintformat_formshipment";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "rmailtext_remitadprintform";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "rmailtext_projectadprintform";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "rmailtext_orderadprintform";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "rmailtext_invoiceadprintform";

ALTER TABLE "ad_printform"
    DROP CONSTRAINT "rmailtext_shipadprintform";

ALTER TABLE "ad_preference"
    DROP CONSTRAINT "ad_preference_client";

ALTER TABLE "ad_preference"
    DROP CONSTRAINT "ad_preference_org";

ALTER TABLE "ad_preference"
    DROP CONSTRAINT "ad_user_preference";

ALTER TABLE "ad_preference"
    DROP CONSTRAINT "ad_window_preference";

ALTER TABLE "ad_pinstance_para"
    DROP CONSTRAINT "adpinstance_adpinstancepara";

ALTER TABLE "ad_pinstance_log"
    DROP CONSTRAINT "adpinstance_pilog";

ALTER TABLE "ad_pinstance"
    DROP CONSTRAINT "adprocess_adpinstance";

ALTER TABLE "ad_pinstance"
    DROP CONSTRAINT "aduser_pinstance";

ALTER TABLE "ad_orginfo"
    DROP CONSTRAINT "adorgparent_adorginfo";

ALTER TABLE "ad_orginfo"
    DROP CONSTRAINT "adorg_adorginfo";

ALTER TABLE "ad_orginfo"
    DROP CONSTRAINT "adorgtype_adorginfo";

ALTER TABLE "ad_orginfo"
    DROP CONSTRAINT "aduser_adorginfo";

ALTER TABLE "ad_orginfo"
    DROP CONSTRAINT "c_location_ad_orginfo";

ALTER TABLE "ad_orginfo"
    DROP CONSTRAINT "mwarehouse_adorginfo";

ALTER TABLE "ad_org"
    DROP CONSTRAINT "adclient_adorg";

ALTER TABLE "ad_note"
    DROP CONSTRAINT "admessage_adnote";

ALTER TABLE "ad_note"
    DROP CONSTRAINT "adtable_adnote";

ALTER TABLE "ad_note"
    DROP CONSTRAINT "aduser_adnote";

ALTER TABLE "ad_note"
    DROP CONSTRAINT "adwfactivity_adnote";

ALTER TABLE "ad_modification"
    DROP CONSTRAINT "adenritytype_admodification";

ALTER TABLE "ad_message_trl"
    DROP CONSTRAINT "ad_language_messagetrl";

ALTER TABLE "ad_message_trl"
    DROP CONSTRAINT "ad_messagetrl";

ALTER TABLE "ad_message"
    DROP CONSTRAINT "messageclient";

ALTER TABLE "ad_message"
    DROP CONSTRAINT "messageorg";

ALTER TABLE "ad_menu_trl"
    DROP CONSTRAINT "ad_language_menutrl";

ALTER TABLE "ad_menu_trl"
    DROP CONSTRAINT "ad_menutrl";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "adclient_admenu";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "adform_admenu";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "ad_menu_org";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "adprocess_admenu";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "adtask_admenu";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "adwindow_admenu";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "admenu_adworkbench";

ALTER TABLE "ad_menu"
    DROP CONSTRAINT "adworkflow_admenu";

ALTER TABLE "ad_ldapprocessorlog"
    DROP CONSTRAINT "adldapprocessor_adldapproclog";

ALTER TABLE "ad_ldapprocessor"
    DROP CONSTRAINT "aduser_adldapprocessor";

ALTER TABLE "ad_ldapaccess"
    DROP CONSTRAINT "adldapprocessor_adldapaccess";

ALTER TABLE "ad_ldapaccess"
    DROP CONSTRAINT "aduser_adldapaccess";

ALTER TABLE "ad_ldapaccess"
    DROP CONSTRAINT "rinterestarea_adldapaccess";

ALTER TABLE "ad_language"
    DROP CONSTRAINT "languageclient";

ALTER TABLE "ad_language"
    DROP CONSTRAINT "languageorg";

ALTER TABLE "ad_labelprinterfunction"
    DROP CONSTRAINT "adlabelprinter_function";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "adform_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "adprocess_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "adwindow_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "aasset_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "rknownissue_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "rissueproject_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "rissuesystem_ad_issue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "rissueuser_adissue";

ALTER TABLE "ad_issue"
    DROP CONSTRAINT "rrequest_adissue";

ALTER TABLE "ad_infowindow_trl"
    DROP CONSTRAINT "adinfowindow_adinfowindowtrl";

ALTER TABLE "ad_infowindow_trl"
    DROP CONSTRAINT "adlanguage_adinfowindowtrl";

ALTER TABLE "ad_infowindow"
    DROP CONSTRAINT "adtable_adinfowindow";

ALTER TABLE "ad_infocolumn_trl"
    DROP CONSTRAINT "adinfocolumn_adinfocolumntrl";

ALTER TABLE "ad_infocolumn_trl"
    DROP CONSTRAINT "adlanguage_adinfocolumntrl";

ALTER TABLE "ad_infocolumn"
    DROP CONSTRAINT "adelement_adinfocolumn";

ALTER TABLE "ad_infocolumn"
    DROP CONSTRAINT "adinfowindow_adinfocolumn";

ALTER TABLE "ad_infocolumn"
    DROP CONSTRAINT "adreference_adinfocolumn";

ALTER TABLE "ad_impformat_row"
    DROP CONSTRAINT "adcolumn_adimpformatrow";

ALTER TABLE "ad_impformat_row"
    DROP CONSTRAINT "adimpformat_adimpformatrow";

ALTER TABLE "ad_impformat"
    DROP CONSTRAINT "adtable_adimpformat";

ALTER TABLE "ad_form_trl"
    DROP CONSTRAINT "adform_adformtrl";

ALTER TABLE "ad_form_trl"
    DROP CONSTRAINT "adlanguage_adformtrl";

ALTER TABLE "ad_form_access"
    DROP CONSTRAINT "adform_adformaccess";

ALTER TABLE "ad_form_access"
    DROP CONSTRAINT "adrole_adformaccess";

ALTER TABLE "ad_find"
    DROP CONSTRAINT "adcolumn_adfind";

ALTER TABLE "ad_field_trl"
    DROP CONSTRAINT "ad_fieldtrl";

ALTER TABLE "ad_field_trl"
    DROP CONSTRAINT "ad_language_fieldtrl";

ALTER TABLE "ad_fieldgroup_trl"
    DROP CONSTRAINT "adfieldgroup_trl";

ALTER TABLE "ad_fieldgroup_trl"
    DROP CONSTRAINT "adlanguage_adfieldgrouptrl";

ALTER TABLE "ad_field"
    DROP CONSTRAINT "fieldclient";

ALTER TABLE "ad_field"
    DROP CONSTRAINT "ad_column_field";

ALTER TABLE "ad_field"
    DROP CONSTRAINT "adfieldgroup_adfield";

ALTER TABLE "ad_field"
    DROP CONSTRAINT "fieldorg";

ALTER TABLE "ad_field"
    DROP CONSTRAINT "ad_tab_field";

ALTER TABLE "ad_element_trl"
    DROP CONSTRAINT "adelement_adelementtrl";

ALTER TABLE "ad_element_trl"
    DROP CONSTRAINT "ad_language_ad_element_trl";

ALTER TABLE "ad_desktop_trl"
    DROP CONSTRAINT "addesktop_addesktoptrl";

ALTER TABLE "ad_desktop_trl"
    DROP CONSTRAINT "adlanguage_addesktoptrl";

ALTER TABLE "ad_desktopworkbench"
    DROP CONSTRAINT "addesktop_addesktopwb";

ALTER TABLE "ad_desktopworkbench"
    DROP CONSTRAINT "adworkbench_addesktopwb";

ALTER TABLE "ad_desktop"
    DROP CONSTRAINT "adcolor_addesktop";

ALTER TABLE "ad_desktop"
    DROP CONSTRAINT "adimage_addesktop";

ALTER TABLE "ad_column_trl"
    DROP CONSTRAINT "adcolumn_adcolumntrl";

ALTER TABLE "ad_column_trl"
    DROP CONSTRAINT "adlanguage_adcolumntrl";

ALTER TABLE "ad_column_access"
    DROP CONSTRAINT "adcolumn_adcolumnaccess";

ALTER TABLE "ad_column_access"
    DROP CONSTRAINT "adrole_adcolumnaccess";

ALTER TABLE "ad_column_access"
    DROP CONSTRAINT "adtable_adcolumnaccess";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "columnclient";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "ad_element_ad_column";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "columnorg";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "adprocess_adcolumn";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "ad_reference_columndatatype";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "ad_reference_columnvalue";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "ad_table_column";

ALTER TABLE "ad_column"
    DROP CONSTRAINT "ad_valrule_column";

ALTER TABLE "ad_color"
    DROP CONSTRAINT "adimage_adcolor";

ALTER TABLE "ad_clientshare"
    DROP CONSTRAINT "adclient_adclientshare";

ALTER TABLE "ad_clientshare"
    DROP CONSTRAINT "adorg_adclientshare";

ALTER TABLE "ad_clientshare"
    DROP CONSTRAINT "adtable_adclientshare";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adclient_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adtreeproject_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adtreeproduct_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adtreeorg_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adtreemenu_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adtreesalesreg_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "adtreebpartner_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "cacctschema1_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "ccalendar_adclientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "c_uom_volume_ad_clientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "c_uom_time_ad_clientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "c_uom_length_ad_clientinfo";

ALTER TABLE "ad_clientinfo"
    DROP CONSTRAINT "c_uom_weight_ad_clientinfo";

ALTER TABLE "ad_changelog"
    DROP CONSTRAINT "adcolumn_adchangelog";

ALTER TABLE "ad_changelog"
    DROP CONSTRAINT "adsession_adchangelog";

ALTER TABLE "ad_changelog"
    DROP CONSTRAINT "adtable_adchangelog";

ALTER TABLE "ad_attribute_value"
    DROP CONSTRAINT "adattribute_adattributevalue";

ALTER TABLE "ad_attribute"
    DROP CONSTRAINT "adreferencevalue_adattribute";

ALTER TABLE "ad_attribute"
    DROP CONSTRAINT "adreference_adattribute";

ALTER TABLE "ad_attribute"
    DROP CONSTRAINT "adtable_adattribute";

ALTER TABLE "ad_attribute"
    DROP CONSTRAINT "advalrule_adattribute";

ALTER TABLE "ad_attachmentnote"
    DROP CONSTRAINT "adattachment_note";

ALTER TABLE "ad_attachmentnote"
    DROP CONSTRAINT "aduser_adattachmentnote";

ALTER TABLE "ad_attachment"
    DROP CONSTRAINT "adtable_adattachment";

ALTER TABLE "ad_archive"
    DROP CONSTRAINT "adprocess_adarchive";

ALTER TABLE "ad_archive"
    DROP CONSTRAINT "adtable_adarchive";

ALTER TABLE "ad_alertrule"
    DROP CONSTRAINT "adaltert_aralertrule";

ALTER TABLE "ad_alertrule"
    DROP CONSTRAINT "adtable_adaltertrule";

ALTER TABLE "ad_alertrecipient"
    DROP CONSTRAINT "adalert_adalertrecipient";

ALTER TABLE "ad_alertrecipient"
    DROP CONSTRAINT "adrole_adaltertrecipient";

ALTER TABLE "ad_alertrecipient"
    DROP CONSTRAINT "aduser_adalertrecipient";

ALTER TABLE "ad_alertprocessorlog"
    DROP CONSTRAINT "calertprocessor_log";

ALTER TABLE "ad_alertprocessor"
    DROP CONSTRAINT "aduser_calertprocessor";

ALTER TABLE "ad_alert"
    DROP CONSTRAINT "calertprocessor_adalert";

ALTER TABLE "ad_accesslog"
    DROP CONSTRAINT "adcolumn_adaccesslog";

ALTER TABLE "ad_accesslog"
    DROP CONSTRAINT "adtable_adacceslog";

ALTER TABLE "ad_accesslog"
    ADD CONSTRAINT "adcolumn_adaccesslog" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_accesslog"
    ADD CONSTRAINT "adtable_adacceslog" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alert"
    ADD CONSTRAINT "calertprocessor_adalert" FOREIGN KEY ("ad_alertprocessor_id") REFERENCES "ad_alertprocessor" ("ad_alertprocessor_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertprocessor"
    ADD CONSTRAINT "aduser_calertprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertprocessorlog"
    ADD CONSTRAINT "calertprocessor_log" FOREIGN KEY ("ad_alertprocessor_id") REFERENCES "ad_alertprocessor" ("ad_alertprocessor_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertrecipient"
    ADD CONSTRAINT "adalert_adalertrecipient" FOREIGN KEY ("ad_alert_id") REFERENCES "ad_alert" ("ad_alert_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertrecipient"
    ADD CONSTRAINT "adrole_adaltertrecipient" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertrecipient"
    ADD CONSTRAINT "aduser_adalertrecipient" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertrule"
    ADD CONSTRAINT "adaltert_aralertrule" FOREIGN KEY ("ad_alert_id") REFERENCES "ad_alert" ("ad_alert_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertrule"
    ADD CONSTRAINT "adtable_adaltertrule" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_archive"
    ADD CONSTRAINT "adprocess_adarchive" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_archive"
    ADD CONSTRAINT "adtable_adarchive" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attachment"
    ADD CONSTRAINT "adtable_adattachment" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attachmentnote"
    ADD CONSTRAINT "adattachment_note" FOREIGN KEY ("ad_attachment_id") REFERENCES "ad_attachment" ("ad_attachment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attachmentnote"
    ADD CONSTRAINT "aduser_adattachmentnote" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute"
    ADD CONSTRAINT "adreferencevalue_adattribute" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute"
    ADD CONSTRAINT "adreference_adattribute" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute"
    ADD CONSTRAINT "adtable_adattribute" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute"
    ADD CONSTRAINT "advalrule_adattribute" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute_value"
    ADD CONSTRAINT "adattribute_adattributevalue" FOREIGN KEY ("ad_attribute_id") REFERENCES "ad_attribute" ("ad_attribute_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_changelog"
    ADD CONSTRAINT "adcolumn_adchangelog" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_changelog"
    ADD CONSTRAINT "adsession_adchangelog" FOREIGN KEY ("ad_session_id") REFERENCES "ad_session" ("ad_session_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_changelog"
    ADD CONSTRAINT "adtable_adchangelog" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adclient_adclientinfo" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreeproject_adclientinfo" FOREIGN KEY ("ad_tree_project_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreeproduct_adclientinfo" FOREIGN KEY ("ad_tree_product_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreeorg_adclientinfo" FOREIGN KEY ("ad_tree_org_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreemenu_adclientinfo" FOREIGN KEY ("ad_tree_menu_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreesalesreg_adclientinfo" FOREIGN KEY ("ad_tree_salesregion_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreebpartner_adclientinfo" FOREIGN KEY ("ad_tree_bpartner_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "cacctschema1_adclientinfo" FOREIGN KEY ("c_acctschema1_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "ccalendar_adclientinfo" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "c_uom_volume_ad_clientinfo" FOREIGN KEY ("c_uom_volume_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "c_uom_time_ad_clientinfo" FOREIGN KEY ("c_uom_time_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "c_uom_length_ad_clientinfo" FOREIGN KEY ("c_uom_length_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "c_uom_weight_ad_clientinfo" FOREIGN KEY ("c_uom_weight_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientshare"
    ADD CONSTRAINT "adclient_adclientshare" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientshare"
    ADD CONSTRAINT "adorg_adclientshare" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientshare"
    ADD CONSTRAINT "adtable_adclientshare" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_color"
    ADD CONSTRAINT "adimage_adcolor" FOREIGN KEY ("ad_image_id") REFERENCES "ad_image" ("ad_image_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "columnclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_element_ad_column" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "columnorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "adprocess_adcolumn" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_reference_columndatatype" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_reference_columnvalue" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_table_column" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_valrule_column" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_access"
    ADD CONSTRAINT "adcolumn_adcolumnaccess" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_access"
    ADD CONSTRAINT "adrole_adcolumnaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_access"
    ADD CONSTRAINT "adtable_adcolumnaccess" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_trl"
    ADD CONSTRAINT "adcolumn_adcolumntrl" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_trl"
    ADD CONSTRAINT "adlanguage_adcolumntrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_desktop"
    ADD CONSTRAINT "adcolor_addesktop" FOREIGN KEY ("ad_color_id") REFERENCES "ad_color" ("ad_color_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_desktop"
    ADD CONSTRAINT "adimage_addesktop" FOREIGN KEY ("ad_image_id") REFERENCES "ad_image" ("ad_image_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_desktopworkbench"
    ADD CONSTRAINT "addesktop_addesktopwb" FOREIGN KEY ("ad_desktop_id") REFERENCES "ad_desktop" ("ad_desktop_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_desktopworkbench"
    ADD CONSTRAINT "adworkbench_addesktopwb" FOREIGN KEY ("ad_workbench_id") REFERENCES "ad_workbench" ("ad_workbench_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_desktop_trl"
    ADD CONSTRAINT "addesktop_addesktoptrl" FOREIGN KEY ("ad_desktop_id") REFERENCES "ad_desktop" ("ad_desktop_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_desktop_trl"
    ADD CONSTRAINT "adlanguage_addesktoptrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_element_trl"
    ADD CONSTRAINT "adelement_adelementtrl" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_element_trl"
    ADD CONSTRAINT "ad_language_ad_element_trl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field"
    ADD CONSTRAINT "fieldclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field"
    ADD CONSTRAINT "ad_column_field" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field"
    ADD CONSTRAINT "adfieldgroup_adfield" FOREIGN KEY ("ad_fieldgroup_id") REFERENCES "ad_fieldgroup" ("ad_fieldgroup_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field"
    ADD CONSTRAINT "fieldorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field"
    ADD CONSTRAINT "ad_tab_field" FOREIGN KEY ("ad_tab_id") REFERENCES "ad_tab" ("ad_tab_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_fieldgroup_trl"
    ADD CONSTRAINT "adfieldgroup_trl" FOREIGN KEY ("ad_fieldgroup_id") REFERENCES "ad_fieldgroup" ("ad_fieldgroup_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_fieldgroup_trl"
    ADD CONSTRAINT "adlanguage_adfieldgrouptrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field_trl"
    ADD CONSTRAINT "ad_fieldtrl" FOREIGN KEY ("ad_field_id") REFERENCES "ad_field" ("ad_field_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field_trl"
    ADD CONSTRAINT "ad_language_fieldtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_find"
    ADD CONSTRAINT "adcolumn_adfind" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_access"
    ADD CONSTRAINT "adform_adformaccess" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_access"
    ADD CONSTRAINT "adrole_adformaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_trl"
    ADD CONSTRAINT "adform_adformtrl" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_trl"
    ADD CONSTRAINT "adlanguage_adformtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_impformat"
    ADD CONSTRAINT "adtable_adimpformat" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_impformat_row"
    ADD CONSTRAINT "adcolumn_adimpformatrow" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_impformat_row"
    ADD CONSTRAINT "adimpformat_adimpformatrow" FOREIGN KEY ("ad_impformat_id") REFERENCES "ad_impformat" ("ad_impformat_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn"
    ADD CONSTRAINT "adelement_adinfocolumn" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn"
    ADD CONSTRAINT "adinfowindow_adinfocolumn" FOREIGN KEY ("ad_infowindow_id") REFERENCES "ad_infowindow" ("ad_infowindow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn"
    ADD CONSTRAINT "adreference_adinfocolumn" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn_trl"
    ADD CONSTRAINT "adinfocolumn_adinfocolumntrl" FOREIGN KEY ("ad_infocolumn_id") REFERENCES "ad_infocolumn" ("ad_infocolumn_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn_trl"
    ADD CONSTRAINT "adlanguage_adinfocolumntrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infowindow"
    ADD CONSTRAINT "adtable_adinfowindow" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infowindow_trl"
    ADD CONSTRAINT "adinfowindow_adinfowindowtrl" FOREIGN KEY ("ad_infowindow_id") REFERENCES "ad_infowindow" ("ad_infowindow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infowindow_trl"
    ADD CONSTRAINT "adlanguage_adinfowindowtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "adform_adissue" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "adprocess_adissue" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "adwindow_adissue" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "aasset_adissue" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rknownissue_adissue" FOREIGN KEY ("r_issueknown_id") REFERENCES "r_issueknown" ("r_issueknown_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rissueproject_adissue" FOREIGN KEY ("r_issueproject_id") REFERENCES "r_issueproject" ("r_issueproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rissuesystem_ad_issue" FOREIGN KEY ("r_issuesystem_id") REFERENCES "r_issuesystem" ("r_issuesystem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rissueuser_adissue" FOREIGN KEY ("r_issueuser_id") REFERENCES "r_issueuser" ("r_issueuser_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rrequest_adissue" FOREIGN KEY ("r_request_id") REFERENCES "r_request" ("r_request_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_labelprinterfunction"
    ADD CONSTRAINT "adlabelprinter_function" FOREIGN KEY ("ad_labelprinter_id") REFERENCES "ad_labelprinter" ("ad_labelprinter_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_language"
    ADD CONSTRAINT "languageclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_language"
    ADD CONSTRAINT "languageorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ldapaccess"
    ADD CONSTRAINT "adldapprocessor_adldapaccess" FOREIGN KEY ("ad_ldapprocessor_id") REFERENCES "ad_ldapprocessor" ("ad_ldapprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ldapaccess"
    ADD CONSTRAINT "aduser_adldapaccess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ldapaccess"
    ADD CONSTRAINT "rinterestarea_adldapaccess" FOREIGN KEY ("r_interestarea_id") REFERENCES "r_interestarea" ("r_interestarea_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ldapprocessor"
    ADD CONSTRAINT "aduser_adldapprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ldapprocessorlog"
    ADD CONSTRAINT "adldapprocessor_adldapproclog" FOREIGN KEY ("ad_ldapprocessor_id") REFERENCES "ad_ldapprocessor" ("ad_ldapprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "adclient_admenu" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "adform_admenu" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "ad_menu_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "adprocess_admenu" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "adtask_admenu" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "adwindow_admenu" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "admenu_adworkbench" FOREIGN KEY ("ad_workbench_id") REFERENCES "ad_workbench" ("ad_workbench_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu"
    ADD CONSTRAINT "adworkflow_admenu" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu_trl"
    ADD CONSTRAINT "ad_language_menutrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_menu_trl"
    ADD CONSTRAINT "ad_menutrl" FOREIGN KEY ("ad_menu_id") REFERENCES "ad_menu" ("ad_menu_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message"
    ADD CONSTRAINT "messageclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message"
    ADD CONSTRAINT "messageorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message_trl"
    ADD CONSTRAINT "ad_language_messagetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message_trl"
    ADD CONSTRAINT "ad_messagetrl" FOREIGN KEY ("ad_message_id") REFERENCES "ad_message" ("ad_message_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_modification"
    ADD CONSTRAINT "adenritytype_admodification" FOREIGN KEY ("entitytype") REFERENCES "ad_entitytype" ("entitytype") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_note"
    ADD CONSTRAINT "admessage_adnote" FOREIGN KEY ("ad_message_id") REFERENCES "ad_message" ("ad_message_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_note"
    ADD CONSTRAINT "adtable_adnote" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_note"
    ADD CONSTRAINT "aduser_adnote" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_note"
    ADD CONSTRAINT "adwfactivity_adnote" FOREIGN KEY ("ad_wf_activity_id") REFERENCES "ad_wf_activity" ("ad_wf_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_org"
    ADD CONSTRAINT "adclient_adorg" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "adorgparent_adorginfo" FOREIGN KEY ("parent_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "adorg_adorginfo" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "adorgtype_adorginfo" FOREIGN KEY ("ad_orgtype_id") REFERENCES "ad_orgtype" ("ad_orgtype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "aduser_adorginfo" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "c_location_ad_orginfo" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "mwarehouse_adorginfo" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance"
    ADD CONSTRAINT "adprocess_adpinstance" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance"
    ADD CONSTRAINT "aduser_pinstance" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance_log"
    ADD CONSTRAINT "adpinstance_pilog" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance_para"
    ADD CONSTRAINT "adpinstance_adpinstancepara" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_preference"
    ADD CONSTRAINT "ad_preference_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_preference"
    ADD CONSTRAINT "ad_preference_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_preference"
    ADD CONSTRAINT "ad_user_preference" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_preference"
    ADD CONSTRAINT "ad_window_preference" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adclient_adprintform" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formremittance" FOREIGN KEY ("remittance_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formproject" FOREIGN KEY ("project_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formorder" FOREIGN KEY ("order_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_forminvoice" FOREIGN KEY ("invoice_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formshipment" FOREIGN KEY ("shipment_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_remitadprintform" FOREIGN KEY ("remittance_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_projectadprintform" FOREIGN KEY ("project_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_orderadprintform" FOREIGN KEY ("order_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_invoiceadprintform" FOREIGN KEY ("invoice_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_shipadprintform" FOREIGN KEY ("shipment_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformat"
    ADD CONSTRAINT "adprintcolor_adprintformat" FOREIGN KEY ("ad_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformat"
    ADD CONSTRAINT "ad_printfont_adprintformat" FOREIGN KEY ("ad_printfont_id") REFERENCES "ad_printfont" ("ad_printfont_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformat"
    ADD CONSTRAINT "adprintpaper_adprintformat" FOREIGN KEY ("ad_printpaper_id") REFERENCES "ad_printpaper" ("ad_printpaper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformat"
    ADD CONSTRAINT "adprintformattable_format" FOREIGN KEY ("ad_printtableformat_id") REFERENCES "ad_printtableformat" ("ad_printtableformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformat"
    ADD CONSTRAINT "adprintview_adprintformat" FOREIGN KEY ("ad_reportview_id") REFERENCES "ad_reportview" ("ad_reportview_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformat"
    ADD CONSTRAINT "adtable_adprintformat" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adcolumn_adprintformatitem" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintcolor_adprintformatitem" FOREIGN KEY ("ad_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintfont_adprintformatitem" FOREIGN KEY ("ad_printfont_id") REFERENCES "ad_printfont" ("ad_printfont_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintformat_printformatitem" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintformat_printformatchild" FOREIGN KEY ("ad_printformatchild_id") REFERENCES "ad_printformat" ("ad_printformat_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintgraph_printformatitem" FOREIGN KEY ("ad_printgraph_id") REFERENCES "ad_printgraph" ("ad_printgraph_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem_trl"
    ADD CONSTRAINT "adlanguage_adprintformitemtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem_trl"
    ADD CONSTRAINT "adprintformatitem_trl" FOREIGN KEY ("ad_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformat_adprintgraph" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata1" FOREIGN KEY ("data1_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata2" FOREIGN KEY ("data2_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata4" FOREIGN KEY ("data4_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdescr" FOREIGN KEY ("description_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata" FOREIGN KEY ("data_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata3" FOREIGN KEY ("data3_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabel"
    ADD CONSTRAINT "adlabelprinter_printlabel" FOREIGN KEY ("ad_labelprinter_id") REFERENCES "ad_labelprinter" ("ad_labelprinter_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabel"
    ADD CONSTRAINT "adtable_adprintlabel" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabelline"
    ADD CONSTRAINT "adcolumn_adprintlabelline" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabelline"
    ADD CONSTRAINT "adlabelprintfunc_labelline" FOREIGN KEY ("ad_labelprinterfunction_id") REFERENCES "ad_labelprinterfunction" ("ad_labelprinterfunction_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabelline"
    ADD CONSTRAINT "adprintlabel_adprintlabelline" FOREIGN KEY ("ad_printlabel_id") REFERENCES "ad_printlabel" ("ad_printlabel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabelline_trl"
    ADD CONSTRAINT "adlanguage_adplabellinetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printlabelline_trl"
    ADD CONSTRAINT "adprintlabelline_trl" FOREIGN KEY ("ad_printlabelline_id") REFERENCES "ad_printlabelline" ("ad_printlabelline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tablefunctbg" FOREIGN KEY ("functbg_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tablefunctfg" FOREIGN KEY ("functfg_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tablehdrline" FOREIGN KEY ("hdrline_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tablehdrtextbg" FOREIGN KEY ("hdrtextbg_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tableline" FOREIGN KEY ("line_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tablehdrtextfg" FOREIGN KEY ("hdrtextfg_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintfont_tableformatfunc" FOREIGN KEY ("funct_printfont_id") REFERENCES "ad_printfont" ("ad_printfont_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintfont_tablehdr" FOREIGN KEY ("hdr_printfont_id") REFERENCES "ad_printfont" ("ad_printfont_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_private_access"
    ADD CONSTRAINT "adtable_adprivateaccess" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_private_access"
    ADD CONSTRAINT "aduser_adprivateaccess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process"
    ADD CONSTRAINT "adprintformat_adprocess" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process"
    ADD CONSTRAINT "adreportview_adprocess" FOREIGN KEY ("ad_reportview_id") REFERENCES "ad_reportview" ("ad_reportview_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_access"
    ADD CONSTRAINT "ad_processaccess_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_access"
    ADD CONSTRAINT "ad_processtaccess_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_access"
    ADD CONSTRAINT "adprocess_adprocessaccess" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_access"
    ADD CONSTRAINT "adrole_adprocessaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adelement_adprocesspara" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adprocess_adprocesspara" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adreference_adprocesspara" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adreferencevalue_adprocpara" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "advalrule_ad_processpara" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para_trl"
    ADD CONSTRAINT "adlanguage_adprocessparatrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para_trl"
    ADD CONSTRAINT "adprocpara_adprocparatrl" FOREIGN KEY ("ad_process_para_id") REFERENCES "ad_process_para" ("ad_process_para_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_trl"
    ADD CONSTRAINT "ad_language_ad_process_trl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_trl"
    ADD CONSTRAINT "ad_process_ad_process_trl" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_record_access"
    ADD CONSTRAINT "adrole_ardecordaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_record_access"
    ADD CONSTRAINT "adtable_adrecordaccess" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reference"
    ADD CONSTRAINT "referenceclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reference"
    ADD CONSTRAINT "referenceorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reference_trl"
    ADD CONSTRAINT "ad_language_referencetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reference_trl"
    ADD CONSTRAINT "ad_referencetrl" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list"
    ADD CONSTRAINT "ad_reflist_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list"
    ADD CONSTRAINT "ad_reflist_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list"
    ADD CONSTRAINT "ad_reference_reflist" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list_trl"
    ADD CONSTRAINT "ad_language_reflisttrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list_trl"
    ADD CONSTRAINT "ad_reflisttrl" FOREIGN KEY ("ad_ref_list_id") REFERENCES "ad_ref_list" ("ad_ref_list_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ref_tableclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ad_column_reftable_display" FOREIGN KEY ("ad_display") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ad_column_reftable_id" FOREIGN KEY ("ad_key") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ref_tableorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ad_reference_reftable" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "add_table_reftable" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_registration"
    ADD CONSTRAINT "adsystem_adregistration" FOREIGN KEY ("ad_system_id", "ad_client_id") REFERENCES "ad_system" ("ad_system_id", "ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication"
    ADD CONSTRAINT "adreplicationstrategy_adrep" FOREIGN KEY ("ad_replicationstrategy_id") REFERENCES "ad_replicationstrategy" ("ad_replicationstrategy_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replicationtable"
    ADD CONSTRAINT "adrepstrategy_adreptable" FOREIGN KEY ("ad_replicationstrategy_id") REFERENCES "ad_replicationstrategy" ("ad_replicationstrategy_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replicationtable"
    ADD CONSTRAINT "adtable_adreplicationtable" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication_log"
    ADD CONSTRAINT "adreptable_adreplog" FOREIGN KEY ("ad_replicationtable_id") REFERENCES "ad_replicationtable" ("ad_replicationtable_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication_log"
    ADD CONSTRAINT "adreplicationrun_adreplog" FOREIGN KEY ("ad_replication_run_id") REFERENCES "ad_replication_run" ("ad_replication_run_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication_run"
    ADD CONSTRAINT "adreplication_adreprun" FOREIGN KEY ("ad_replication_id") REFERENCES "ad_replication" ("ad_replication_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reportview"
    ADD CONSTRAINT "adtable_adreportview" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reportview_col"
    ADD CONSTRAINT "adcolumn_adreportviewcol" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reportview_col"
    ADD CONSTRAINT "adreportview_col" FOREIGN KEY ("ad_reportview_id") REFERENCES "ad_reportview" ("ad_reportview_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role"
    ADD CONSTRAINT "ad_roleclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role"
    ADD CONSTRAINT "ad_roleorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role"
    ADD CONSTRAINT "adtree_adrole" FOREIGN KEY ("ad_tree_menu_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role"
    ADD CONSTRAINT "adusersupervisor_adrole" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role"
    ADD CONSTRAINT "c_currency_ad_role" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role_orgaccess"
    ADD CONSTRAINT "adorg_adroleorgaccess" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role_orgaccess"
    ADD CONSTRAINT "adrole_adroleorgaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler"
    ADD CONSTRAINT "adprocess_adscheduler" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler"
    ADD CONSTRAINT "aduser_adscheduler" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerlog"
    ADD CONSTRAINT "adscheduler_log" FOREIGN KEY ("ad_scheduler_id") REFERENCES "ad_scheduler" ("ad_scheduler_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerrecipient"
    ADD CONSTRAINT "adrole_adschedulerrecipient" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerrecipient"
    ADD CONSTRAINT "adscheduler_recipient" FOREIGN KEY ("ad_scheduler_id") REFERENCES "ad_scheduler" ("ad_scheduler_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerrecipient"
    ADD CONSTRAINT "aduser_adschedulerrecipient" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler_para"
    ADD CONSTRAINT "adprocesspara_adschedulerpara" FOREIGN KEY ("ad_process_para_id") REFERENCES "ad_process_para" ("ad_process_para_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler_para"
    ADD CONSTRAINT "adscheduler_adschedulerpara" FOREIGN KEY ("ad_scheduler_id") REFERENCES "ad_scheduler" ("ad_scheduler_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence"
    ADD CONSTRAINT "sequenceclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence"
    ADD CONSTRAINT "sequenceorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "sequence_auditclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "sequence_auditorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "ad_sequence_sequenceaudit" FOREIGN KEY ("ad_sequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "adtable_adsequenceaudit" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_no"
    ADD CONSTRAINT "sequence_noclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_no"
    ADD CONSTRAINT "sequence_noorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_no"
    ADD CONSTRAINT "ad_sequence_sequenceno" FOREIGN KEY ("ad_sequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "tabclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adcolumn_adtabsortyesno" FOREIGN KEY ("ad_columnsortyesno_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adcolumn_adtabsortorder" FOREIGN KEY ("ad_columnsortorder_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "ad_column_ad_tab" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adimage_adtab" FOREIGN KEY ("ad_image_id") REFERENCES "ad_image" ("ad_image_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "taborg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adprocess_adtab" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adtab_included" FOREIGN KEY ("included_tab_id") REFERENCES "ad_tab" ("ad_tab_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "ad_table_tab" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "ad_window_tab" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "tableclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "tableorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "ad_valrule_table" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "adwindowpo_adtable" FOREIGN KEY ("po_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "ad_window_table" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "ad_dataaccessclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "ad_dataaccessorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "adrole_adtableaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "adtable_adtableaccess" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_trl"
    ADD CONSTRAINT "adlanguage_adtabletrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_trl"
    ADD CONSTRAINT "adtable_adtabletrl" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab_trl"
    ADD CONSTRAINT "ad_language_tabtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab_trl"
    ADD CONSTRAINT "ad_tabtrl" FOREIGN KEY ("ad_tab_id") REFERENCES "ad_tab" ("ad_tab_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task"
    ADD CONSTRAINT "taskclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task"
    ADD CONSTRAINT "taskorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_taskinstance"
    ADD CONSTRAINT "taskinstanceclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_taskinstance"
    ADD CONSTRAINT "taskinstanceorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_taskinstance"
    ADD CONSTRAINT "ad_task_taskinstance" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "ad_taskaccess_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "ad_taskaccess_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "adrole_adtaskaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "adtask_adtaskaccess" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_trl"
    ADD CONSTRAINT "ad_language_tasktrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_trl"
    ADD CONSTRAINT "ad_tasktrl" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treebar"
    ADD CONSTRAINT "adtree_adtreebar" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treebar"
    ADD CONSTRAINT "aduser_adtreebar" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenode"
    ADD CONSTRAINT "adtree_adtreenode" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodebp"
    ADD CONSTRAINT "adtree_adtreenodebp" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecmc"
    ADD CONSTRAINT "adtree_adtreenodecmc" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecmm"
    ADD CONSTRAINT "adtree_adtreenodecmm" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecms"
    ADD CONSTRAINT "adtree_adtreenodecms" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecmt"
    ADD CONSTRAINT "adtree_adtreenodecmt" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodemm"
    ADD CONSTRAINT "adtree_adtreenodemm" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodepr"
    ADD CONSTRAINT "adtree_adtreenodepr" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu1"
    ADD CONSTRAINT "adtree_adtreenodeu1" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu2"
    ADD CONSTRAINT "adtree_adtreenodeu2" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu3"
    ADD CONSTRAINT "adtree_adtreenodeu3" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu4"
    ADD CONSTRAINT "adtree_adtreenodeu4" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "ad_user_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "adorgtrx_aduser" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "ad_user_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "aduser_supervisor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "cbpartner_aduser" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "cbplocation_aduser" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "cgreeting_aduser" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userbpaccess"
    ADD CONSTRAINT "aduser_aduserbpaccess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userbpaccess"
    ADD CONSTRAINT "rrequesttype_aduserbpaccess" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_field"
    ADD CONSTRAINT "adfield_aduserdeffield" FOREIGN KEY ("ad_field_id") REFERENCES "ad_field" ("ad_field_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_field"
    ADD CONSTRAINT "aduserdeftab_aduserdeffield" FOREIGN KEY ("ad_userdef_tab_id") REFERENCES "ad_userdef_tab" ("ad_userdef_tab_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_tab"
    ADD CONSTRAINT "adtab_aduserdeftab" FOREIGN KEY ("ad_tab_id") REFERENCES "ad_tab" ("ad_tab_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_tab"
    ADD CONSTRAINT "aduserdefwin_aduserdeftab" FOREIGN KEY ("ad_userdef_win_id") REFERENCES "ad_userdef_win" ("ad_userdef_win_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_win"
    ADD CONSTRAINT "adrole_aduserdefwin" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_win"
    ADD CONSTRAINT "aduser_aduserdefwin" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userdef_win"
    ADD CONSTRAINT "adwindow_aduserdefwin" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_usermail"
    ADD CONSTRAINT "aduser_adusermail" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_usermail"
    ADD CONSTRAINT "rmailtext_adusermail" FOREIGN KEY ("r_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_usermail"
    ADD CONSTRAINT "wmailmsg_adusermail" FOREIGN KEY ("w_mailmsg_id") REFERENCES "w_mailmsg" ("w_mailmsg_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userquery"
    ADD CONSTRAINT "adtable_aduserquery" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userquery"
    ADD CONSTRAINT "aduser_aduserquery" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_orgaccess"
    ADD CONSTRAINT "adorg_aduserorgaccess" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_orgaccess"
    ADD CONSTRAINT "aduser_aduserorgaccess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "ad_userrolesclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "ad_userrolesorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "adrole_aduserroles" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "aduser_userroles" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_substitute"
    ADD CONSTRAINT "aduser_adusersub" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_substitute"
    ADD CONSTRAINT "adusersub_ad_usersub" FOREIGN KEY ("substitute_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_val_rule"
    ADD CONSTRAINT "val_ruleclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_val_rule"
    ADD CONSTRAINT "val_ruleorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "admessage_adwfactivity" FOREIGN KEY ("ad_message_id") REFERENCES "ad_message" ("ad_message_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "aduser_adwfactivity" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "adwfnode_adwfactivity" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "adwfprocess_adwfactivity" FOREIGN KEY ("ad_wf_process_id") REFERENCES "ad_wf_process" ("ad_wf_process_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "adwfresponsible_adwfactivity" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activityresult"
    ADD CONSTRAINT "adwfactivity_adwfactresult" FOREIGN KEY ("ad_wf_activity_id") REFERENCES "ad_wf_activity" ("ad_wf_activity_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_block"
    ADD CONSTRAINT "adworkflow_adwfblock" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_eventaudit"
    ADD CONSTRAINT "adtable_adwfeventaudit" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_eventaudit"
    ADD CONSTRAINT "aduser_adwfeventaudit" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_eventaudit"
    ADD CONSTRAINT "adwfnode_adwfeventaudit" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_eventaudit"
    ADD CONSTRAINT "adwfprocess_adwfeventaudit" FOREIGN KEY ("ad_wf_process_id") REFERENCES "ad_wf_process" ("ad_wf_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_eventaudit"
    ADD CONSTRAINT "adwfresponsib_adwfeventaudit" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nextcondition"
    ADD CONSTRAINT "adcolumn_adwfnextcondition" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nextcondition"
    ADD CONSTRAINT "adwfnodenext_adwfnextcond" FOREIGN KEY ("ad_wf_nodenext_id") REFERENCES "ad_wf_nodenext" ("ad_wf_nodenext_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "wf_nodeclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adform_adwfnode" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adimage_adwfnode" FOREIGN KEY ("ad_image_id") REFERENCES "ad_image" ("ad_image_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "wf_nodeorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adprocess_adwfnode" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adtask_adwfnode" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adwfblock_adwfnode" FOREIGN KEY ("ad_wf_block_id") REFERENCES "ad_wf_block" ("ad_wf_block_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adwfresponsible_adwfnode" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adwindow_adwfnode" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adworkflow_adwfnodesubflow" FOREIGN KEY ("workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adworkflow_adwfnode" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "wf_nodenextclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "wf_nodenextorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "adwfnodenext_adwfnodenext" FOREIGN KEY ("ad_wf_next_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "adwfnode_adwfnodenext" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_para"
    ADD CONSTRAINT "adprocesspara_adwfnodepara" FOREIGN KEY ("ad_process_para_id") REFERENCES "ad_process_para" ("ad_process_para_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_para"
    ADD CONSTRAINT "adwfnode_adwfnodepara" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_trl"
    ADD CONSTRAINT "ad_language_wfnodetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_trl"
    ADD CONSTRAINT "ad_wfnodetrl" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_process"
    ADD CONSTRAINT "wf_instanceclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_process"
    ADD CONSTRAINT "admessage_adwfprocess" FOREIGN KEY ("ad_message_id") REFERENCES "ad_message" ("ad_message_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_process"
    ADD CONSTRAINT "wf_instanceorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_process"
    ADD CONSTRAINT "aduser_adwfprocess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_process"
    ADD CONSTRAINT "adwfresponsible_adwfprocess" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_process"
    ADD CONSTRAINT "adworkflow_adwfprocess" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_processdata"
    ADD CONSTRAINT "adwfproccess_adwfprocessdata" FOREIGN KEY ("ad_wf_process_id") REFERENCES "ad_wf_process" ("ad_wf_process_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_responsible"
    ADD CONSTRAINT "adorg_adwfresponsible" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_responsible"
    ADD CONSTRAINT "adrole_adwfresponsible" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_responsible"
    ADD CONSTRAINT "aduser_adwfresponsible" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window"
    ADD CONSTRAINT "windowclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window"
    ADD CONSTRAINT "adcolor_adwindow" FOREIGN KEY ("ad_color_id") REFERENCES "ad_color" ("ad_color_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window"
    ADD CONSTRAINT "adimage_adwindow" FOREIGN KEY ("ad_image_id") REFERENCES "ad_image" ("ad_image_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window"
    ADD CONSTRAINT "windoworg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_access"
    ADD CONSTRAINT "ad_functaccess_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_access"
    ADD CONSTRAINT "ad_functaccessorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_access"
    ADD CONSTRAINT "adrole_adwindowaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_access"
    ADD CONSTRAINT "adwindow_adwindowaccess" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_trl"
    ADD CONSTRAINT "ad_language_windowtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_trl"
    ADD CONSTRAINT "ad_windowtrl" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbench"
    ADD CONSTRAINT "adcolor_adworkbench" FOREIGN KEY ("ad_color_id") REFERENCES "ad_color" ("ad_color_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbench"
    ADD CONSTRAINT "adimage_adworkbench" FOREIGN KEY ("ad_image_id") REFERENCES "ad_image" ("ad_image_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbenchwindow"
    ADD CONSTRAINT "adform_adworkbenchwindow" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbenchwindow"
    ADD CONSTRAINT "adprocess_adworkbenchwindow" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbenchwindow"
    ADD CONSTRAINT "adtask_adworkbenchwindow" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbenchwindow"
    ADD CONSTRAINT "adwindow_adworkbenchwindow" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbenchwindow"
    ADD CONSTRAINT "adworkbench_adworkbenchwindow" FOREIGN KEY ("ad_workbench_id") REFERENCES "ad_workbench" ("ad_workbench_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbench_trl"
    ADD CONSTRAINT "adlanguage_adworkbenchtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workbench_trl"
    ADD CONSTRAINT "adworkbench_adworkbenchtrl" FOREIGN KEY ("ad_workbench_id") REFERENCES "ad_workbench" ("ad_workbench_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow"
    ADD CONSTRAINT "workflowclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow"
    ADD CONSTRAINT "workfloworg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow"
    ADD CONSTRAINT "adwfresponsible_adworkflow" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow"
    ADD CONSTRAINT "adworkflowprocessor_adwf" FOREIGN KEY ("ad_workflowprocessor_id") REFERENCES "ad_workflowprocessor" ("ad_workflowprocessor_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflowprocessor"
    ADD CONSTRAINT "aduser_adworkflowprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflowprocessorlog"
    ADD CONSTRAINT "adworkflowprocessor_log" FOREIGN KEY ("ad_workflowprocessor_id") REFERENCES "ad_workflowprocessor" ("ad_workflowprocessor_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "ad_workflowaccess_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "ad_workflowaccess_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "adrole_adworkflowaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "adworkfow_workflowaccess" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_trl"
    ADD CONSTRAINT "ad_language_workflowtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_trl"
    ADD CONSTRAINT "ad_workflowtrl" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "aduser_aasset" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "aassetgroup_aasset" FOREIGN KEY ("a_asset_group_id") REFERENCES "a_asset_group" ("a_asset_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "cbpartner_aasset" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "cbplocation_aasset" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "clocation_aasset" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "mattributesetinstance_aasset" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "mlocator_aasset" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset"
    ADD CONSTRAINT "mproduct_aasset" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_acct"
    ADD CONSTRAINT "aasset_aassetacct" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_acct"
    ADD CONSTRAINT "adepreciation_aassetacct" FOREIGN KEY ("a_depreciation_id") REFERENCES "a_depreciation" ("a_depreciation_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_acct"
    ADD CONSTRAINT "cacctschema_aassetacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_addition"
    ADD CONSTRAINT "aasset_aassetaddition" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_addition"
    ADD CONSTRAINT "cinvoiceline_aassetaddition" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_change"
    ADD CONSTRAINT "aasset_aassetchange" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_change"
    ADD CONSTRAINT "aaaddition_aachange" FOREIGN KEY ("a_asset_addition_id") REFERENCES "a_asset_addition" ("a_asset_addition_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_change"
    ADD CONSTRAINT "aaretirement_aachange" FOREIGN KEY ("a_asset_retirement_id") REFERENCES "a_asset_retirement" ("a_asset_retirement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_change_amt"
    ADD CONSTRAINT "aassetchange_aassetchangeamt" FOREIGN KEY ("a_asset_change_id") REFERENCES "a_asset_change" ("a_asset_change_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_change_amt"
    ADD CONSTRAINT "cacctschema_aassetchangeamt" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_delivery"
    ADD CONSTRAINT "aduser_aassetdelivery" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_delivery"
    ADD CONSTRAINT "aasset_aassetdelivery" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_delivery"
    ADD CONSTRAINT "moutline_aassetdelivery" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_delivery"
    ADD CONSTRAINT "mproductdl_aassetdelivery" FOREIGN KEY ("m_productdownload_id") REFERENCES "m_productdownload" ("m_productdownload_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_group_acct"
    ADD CONSTRAINT "aassetgroup_aassetgroupacct" FOREIGN KEY ("a_asset_group_id") REFERENCES "a_asset_group" ("a_asset_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_group_acct"
    ADD CONSTRAINT "adepreciation_aassetgroupacct" FOREIGN KEY ("a_depreciation_id") REFERENCES "a_depreciation" ("a_depreciation_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_group_acct"
    ADD CONSTRAINT "cacctschema_aassetgroupacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_retirement"
    ADD CONSTRAINT "aasset_aassetretirement" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_retirement"
    ADD CONSTRAINT "cinvoiceline_aassetretirement" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_asset_use"
    ADD CONSTRAINT "aasset_aassetuse" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registration"
    ADD CONSTRAINT "aduser_aregistration" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registration"
    ADD CONSTRAINT "aasset_aregistration" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registration"
    ADD CONSTRAINT "cbpartner_aregistration" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registration"
    ADD CONSTRAINT "mproduct_aregistration" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationattribute"
    ADD CONSTRAINT "adreference_aregattribute" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationattribute"
    ADD CONSTRAINT "adreferencevalue_aregattribute" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationproduct"
    ADD CONSTRAINT "aregattribute_aregproduct" FOREIGN KEY ("a_registrationattribute_id") REFERENCES "a_registrationattribute" ("a_registrationattribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationproduct"
    ADD CONSTRAINT "mproduct_aregproduct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationvalue"
    ADD CONSTRAINT "aregistration_aregvalue" FOREIGN KEY ("a_registration_id") REFERENCES "a_registration" ("a_registration_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationvalue"
    ADD CONSTRAINT "aregattribute_aregvalue" FOREIGN KEY ("a_registrationattribute_id") REFERENCES "a_registrationattribute" ("a_registrationattribute_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_bid"
    ADD CONSTRAINT "bbuyer_bbid" FOREIGN KEY ("ad_user_id") REFERENCES "b_buyer" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_bid"
    ADD CONSTRAINT "bbuyerfunds_bbid" FOREIGN KEY ("b_buyerfunds_id") REFERENCES "b_buyerfunds" ("b_buyerfunds_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_bid"
    ADD CONSTRAINT "btopic_bbid" FOREIGN KEY ("b_topic_id") REFERENCES "b_topic" ("b_topic_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_bidcomment"
    ADD CONSTRAINT "aduser_bidcomment" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_bidcomment"
    ADD CONSTRAINT "btopic_bbidcomment" FOREIGN KEY ("b_topic_id") REFERENCES "b_topic" ("b_topic_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_buyer"
    ADD CONSTRAINT "aduser_bbuyer" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_buyerfunds"
    ADD CONSTRAINT "bbuyer_bbuyerfunds" FOREIGN KEY ("ad_user_id") REFERENCES "b_buyer" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_buyerfunds"
    ADD CONSTRAINT "corder_bbuyersfunds" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_buyerfunds"
    ADD CONSTRAINT "cpayment_bbuyerfunds" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_offer"
    ADD CONSTRAINT "bseller_boffer" FOREIGN KEY ("ad_user_id") REFERENCES "b_seller" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_offer"
    ADD CONSTRAINT "bsellerfunds_boffer" FOREIGN KEY ("b_sellerfunds_id") REFERENCES "b_sellerfunds" ("b_sellerfunds_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_offer"
    ADD CONSTRAINT "btopic_boffer" FOREIGN KEY ("b_topic_id") REFERENCES "b_topic" ("b_topic_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_seller"
    ADD CONSTRAINT "aduser_bseller" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_sellerfunds"
    ADD CONSTRAINT "bseller_bsellerfunds" FOREIGN KEY ("ad_user_id") REFERENCES "b_seller" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_sellerfunds"
    ADD CONSTRAINT "corder_bsellerfunds" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_sellerfunds"
    ADD CONSTRAINT "cpayment_bsellerfunds" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topic"
    ADD CONSTRAINT "btopiccategory_btopic" FOREIGN KEY ("b_topiccategory_id") REFERENCES "b_topiccategory" ("b_topiccategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topic"
    ADD CONSTRAINT "btopictype_btopic" FOREIGN KEY ("b_topictype_id") REFERENCES "b_topictype" ("b_topictype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topiccategory"
    ADD CONSTRAINT "btopictype_btopiccategory" FOREIGN KEY ("b_topictype_id") REFERENCES "b_topictype" ("b_topictype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topictype"
    ADD CONSTRAINT "mpricelist_btopictype" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topictype"
    ADD CONSTRAINT "mproduct_btopictypemember" FOREIGN KEY ("m_productmember_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topictype"
    ADD CONSTRAINT "mproduct_btopictype" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesscontainer"
    ADD CONSTRAINT "cmaccessprofile_cmacccontainer" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesscontainer"
    ADD CONSTRAINT "cmcontainer_cmaccesscontainer" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistbpgroup"
    ADD CONSTRAINT "cmaccessprofile_cmalbpgroup" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistbpgroup"
    ADD CONSTRAINT "cbpgrpup_cmalistbpgroup" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistrole"
    ADD CONSTRAINT "adrole_cmalistrole" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistrole"
    ADD CONSTRAINT "cmaccessprofile_cmalistrole" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessmedia"
    ADD CONSTRAINT "cmaccessprofile_cmaccessmedia" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessmedia"
    ADD CONSTRAINT "cmmedia_cmaccessmedia" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessnewschannel"
    ADD CONSTRAINT "cmaccesprofile_cmanewschannel" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessnewschannel"
    ADD CONSTRAINT "cnmewschannel_cmaccessnewsc" FOREIGN KEY ("cm_newschannel_id") REFERENCES "cm_newschannel" ("cm_newschannel_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessstage"
    ADD CONSTRAINT "cmaccessprofile_cmaccessstage" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessstage"
    ADD CONSTRAINT "cmcstage_cmaccessstage" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_ad"
    ADD CONSTRAINT "cmadcat_cmad" FOREIGN KEY ("cm_ad_cat_id") REFERENCES "cm_ad_cat" ("cm_ad_cat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_ad"
    ADD CONSTRAINT "cmmedia_cmad" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_ad_cat"
    ADD CONSTRAINT "cmwebproject_cmadcat" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_broadcastserver"
    ADD CONSTRAINT "cmwebproject_cmbroadcastserver" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chat"
    ADD CONSTRAINT "adtable_cmchat" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chat"
    ADD CONSTRAINT "cmchattype_cmchat" FOREIGN KEY ("cm_chattype_id") REFERENCES "cm_chattype" ("cm_chattype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "aduser_cmchatentry" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "cmchat_chchatentry" FOREIGN KEY ("cm_chat_id") REFERENCES "cm_chat" ("cm_chat_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "cmentrty_cmentryparent" FOREIGN KEY ("cm_chatentryparent_id") REFERENCES "cm_chatentry" ("cm_chatentry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "cmchatentry_grandparent" FOREIGN KEY ("cm_chatentrygrandparent_id") REFERENCES "cm_chatentry" ("cm_chatentry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chattype"
    ADD CONSTRAINT "adtable_cmchattype" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chattypeupdate"
    ADD CONSTRAINT "aduser_cmchattypeupdate" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chattypeupdate"
    ADD CONSTRAINT "cmchattype_cmchattypeupdate" FOREIGN KEY ("cm_chattype_id") REFERENCES "cm_chattype" ("cm_chattype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatupdate"
    ADD CONSTRAINT "aduser_cmchatupdate" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatupdate"
    ADD CONSTRAINT "cmchat_cmchatupdate" FOREIGN KEY ("cm_chat_id") REFERENCES "cm_chat" ("cm_chat_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container"
    ADD CONSTRAINT "cmcontainer_cmcontainerlink" FOREIGN KEY ("cm_containerlink_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container"
    ADD CONSTRAINT "cmtemplate_cmcontainer" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container"
    ADD CONSTRAINT "cmwebproject_cmcontainer" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_containerttable"
    ADD CONSTRAINT "cmcontainer_cmcontainerttable" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_containerttable"
    ADD CONSTRAINT "cmttable_cmcontainertable" FOREIGN KEY ("cm_templatetable_id") REFERENCES "cm_templatetable" ("cm_templatetable_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_element"
    ADD CONSTRAINT "cmcontainer_cmcontainerelement" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_element_trl"
    ADD CONSTRAINT "adlanguage_cmcontainereletrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_element_trl"
    ADD CONSTRAINT "cmcontainerelement_cmcetrl" FOREIGN KEY ("cm_container_element_id") REFERENCES "cm_container_element" ("cm_container_element_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_trl"
    ADD CONSTRAINT "adlanguage_cmcontainertrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_trl"
    ADD CONSTRAINT "cmcontainer_cmcontainertrl" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_url"
    ADD CONSTRAINT "cmcontainer_cmcontainerurl" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage"
    ADD CONSTRAINT "cmcstage_cmcstagelink" FOREIGN KEY ("cm_cstagelink_id") REFERENCES "cm_cstage" ("cm_cstage_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage"
    ADD CONSTRAINT "cmtemplate_cmcstage" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage"
    ADD CONSTRAINT "cmwebproject_cmcstage" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstagettable"
    ADD CONSTRAINT "cmstage_cmcstagettable" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstagettable"
    ADD CONSTRAINT "cmttable_cmstagettable" FOREIGN KEY ("cm_templatetable_id") REFERENCES "cm_templatetable" ("cm_templatetable_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_element"
    ADD CONSTRAINT "cmcstage_cmcstageelement" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_element_trl"
    ADD CONSTRAINT "adlanguage_cmcstageeletrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_element_trl"
    ADD CONSTRAINT "cmcstageelement_cmcsetrl" FOREIGN KEY ("cm_cstage_element_id") REFERENCES "cm_cstage_element" ("cm_cstage_element_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_trl"
    ADD CONSTRAINT "adlanguage_cmcstagetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_trl"
    ADD CONSTRAINT "cmcstage_cmcstagetrl" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_media"
    ADD CONSTRAINT "cmwebproject_cmmedia" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_mediadeploy"
    ADD CONSTRAINT "cmmedia_cmmediadeploy" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_mediadeploy"
    ADD CONSTRAINT "cmmediaserver_cmmediadeploy" FOREIGN KEY ("cm_media_server_id") REFERENCES "cm_media_server" ("cm_media_server_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_media_server"
    ADD CONSTRAINT "cmwebproject_cmmediaserver" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_newschannel"
    ADD CONSTRAINT "cmwebproject_cmnewschannel" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_newsitem"
    ADD CONSTRAINT "cmnewschannel_cmnewsitem" FOREIGN KEY ("cm_newschannel_id") REFERENCES "cm_newschannel" ("cm_newschannel_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_template"
    ADD CONSTRAINT "cmwebproject_cmtemplate" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_templatetable"
    ADD CONSTRAINT "adtable_cmtemplatetable" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_templatetable"
    ADD CONSTRAINT "cmtemplate_cmttable" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_template_ad_cat"
    ADD CONSTRAINT "cmadcat_cmtemplateadcat" FOREIGN KEY ("cm_ad_cat_id") REFERENCES "cm_ad_cat" ("cm_ad_cat_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_template_ad_cat"
    ADD CONSTRAINT "cmtemplate_cmtemplateadcat" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webaccesslog"
    ADD CONSTRAINT "aduser_cmwebaccesslog" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webaccesslog"
    ADD CONSTRAINT "cmbroadcastserver_cmwebalog" FOREIGN KEY ("cm_broadcastserver_id") REFERENCES "cm_broadcastserver" ("cm_broadcastserver_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webaccesslog"
    ADD CONSTRAINT "cmmedia_cmwebaccesslog" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webaccesslog"
    ADD CONSTRAINT "cmwebproject_cmwebaccesslog" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject"
    ADD CONSTRAINT "adtreecmc_cmwebproject" FOREIGN KEY ("ad_treecmc_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject"
    ADD CONSTRAINT "adtreecmt_cmwebproject" FOREIGN KEY ("ad_treecmt_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject"
    ADD CONSTRAINT "adtreecms_cmwebproject" FOREIGN KEY ("ad_treecms_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject"
    ADD CONSTRAINT "adtreecmm_cmwebproject" FOREIGN KEY ("ad_treecmm_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject_domain"
    ADD CONSTRAINT "cmcontainer_cmwebprojectdomain" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject_domain"
    ADD CONSTRAINT "cmwebproject_cmwpdomain" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_wikitoken"
    ADD CONSTRAINT "adtable_cmwikitoken" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctprocessor"
    ADD CONSTRAINT "aduser_cacctprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctprocessorlog"
    ADD CONSTRAINT "cacctprocessor_log" FOREIGN KEY ("c_acctprocessor_id") REFERENCES "c_acctprocessor" ("c_acctprocessor_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema"
    ADD CONSTRAINT "ad_client_c_acctschema" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema"
    ADD CONSTRAINT "ad_org_c_acctschema" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema"
    ADD CONSTRAINT "c_currency_c_acctschema" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema"
    ADD CONSTRAINT "cperiod_cacctschema" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema"
    ADD CONSTRAINT "mcosttype_cacctschema" FOREIGN KEY ("m_costtype_id") REFERENCES "m_costtype" ("m_costtype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "cacctschema_default" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_realizedgain_cschemadefault" FOREIGN KEY ("realizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_realizedloss_cschemadefault" FOREIGN KEY ("realizedloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_tcredit_cschemadefault" FOREIGN KEY ("t_credit_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_tdue_cschemadefault" FOREIGN KEY ("t_due_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_texpense_cschemadefault" FOREIGN KEY ("t_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_tliability_cschemadefault" FOREIGN KEY ("t_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_trec_cschemadefault" FOREIGN KEY ("t_receivables_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_unearnedrevenue_cschemadefa" FOREIGN KEY ("unearnedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_unrealizedgain_cschemadefau" FOREIGN KEY ("unrealizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_unrealizedloss_cschemadefau" FOREIGN KEY ("unrealizedloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_vliabilityservices_cschemad" FOREIGN KEY ("v_liability_services_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_vliability_cschemadefault" FOREIGN KEY ("v_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_vprepayment_cschemadefault" FOREIGN KEY ("v_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_wdifferences_cschemadefault" FOREIGN KEY ("w_differences_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_winvactualadjust_cschemadef" FOREIGN KEY ("w_invactualadjust_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_winventory_cschemadefault" FOREIGN KEY ("w_inventory_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_withholding_cschemadefault" FOREIGN KEY ("withholding_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_wrevaluation_cschemadefault" FOREIGN KEY ("w_revaluation_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_writeoff_cschemadefault" FOREIGN KEY ("writeoff_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_basset_cschemadefault" FOREIGN KEY ("b_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bexpense_cschemadefault" FOREIGN KEY ("b_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_binterestexp_cschemadefault" FOREIGN KEY ("b_interestexp_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_binterestrev_cschemadefault" FOREIGN KEY ("b_interestrev_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bintransit_cschemadefault" FOREIGN KEY ("b_intransit_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bpaymentselect_cschemadefau" FOREIGN KEY ("b_paymentselect_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_brevaluationgain_cschemadef" FOREIGN KEY ("b_revaluationgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_brevaluationloss_cschemadef" FOREIGN KEY ("b_revaluationloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bsettlementgain_cschemadefa" FOREIGN KEY ("b_settlementgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bsettlementloss_cschemadefa" FOREIGN KEY ("b_settlementloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bunallocatedcash_cschemadef" FOREIGN KEY ("b_unallocatedcash_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_bunidentified_cschemadefaul" FOREIGN KEY ("b_unidentified_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_cbasset_cschemadefault" FOREIGN KEY ("cb_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_cbcashtransfer_cschemadefau" FOREIGN KEY ("cb_cashtransfer_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_cbdifferences_cschemadefaul" FOREIGN KEY ("cb_differences_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_cbexpense_cschemadefault" FOREIGN KEY ("cb_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_cbreceipt_cschemadefault" FOREIGN KEY ("cb_receipt_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_chexpense_cschemadefault" FOREIGN KEY ("ch_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_chrevenue_cschemadefault" FOREIGN KEY ("ch_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_cprepayment_cschemadefault" FOREIGN KEY ("c_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_creceivable_cschemadefault" FOREIGN KEY ("c_receivable_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_eexpense_cschemadefault" FOREIGN KEY ("e_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_eprepayment_cschemadefault" FOREIGN KEY ("e_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_notinvoicedreceipts_cschema" FOREIGN KEY ("notinvoicedreceipts_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_notinvoicedrec_cschemadefau" FOREIGN KEY ("notinvoicedreceivables_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_notinvoicedrevenue_cschemad" FOREIGN KEY ("notinvoicedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_passet_cschemadefault" FOREIGN KEY ("p_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_paydiscountexp_cschemadefau" FOREIGN KEY ("paydiscount_exp_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_paydiscountrev_cschemadefau" FOREIGN KEY ("paydiscount_rev_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_pcogs_cschemadefault" FOREIGN KEY ("p_cogs_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_pexpense_cschemadefault" FOREIGN KEY ("p_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_pinvoicepv_cschemadefault" FOREIGN KEY ("p_invoicepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_pjasset_cschemadefault" FOREIGN KEY ("pj_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_pjwip_cschemadefault" FOREIGN KEY ("pj_wip_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_ppurchasepv_cschemadefault" FOREIGN KEY ("p_purchasepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_prevenue_cschemadefault" FOREIGN KEY ("p_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_ptdiscountgrant_cschemadefa" FOREIGN KEY ("p_tradediscountgrant_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_ptdiscountrec_cschemadefaul" FOREIGN KEY ("p_tradediscountrec_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "adclient_caschemaelement" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "adorg_caschemaelement" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "adorgid_c_aschemaelement" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "cacctschema_caschemaelement" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "cactivity_cacctschemaelement" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "cbuspartner_caschemaelement" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "socampaign_caschemaelement" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "celement_caschemaelement" FOREIGN KEY ("c_element_id") REFERENCES "c_element" ("c_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "celementvalue_caschemaelement" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "clocation_caschemaelement" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "cproject_caschemaelement" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "csalesregion_caschemaelement" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "mproduct_caschemaelement" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "cacctschema_cacctschemagl" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_incomesummary_cschemagl" FOREIGN KEY ("incomesummary_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_intercompanyduefrom_cschema" FOREIGN KEY ("intercompanyduefrom_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_suspenseerror_cschemagl" FOREIGN KEY ("suspenseerror_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_ppvoffset_cschemagl" FOREIGN KEY ("ppvoffset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_retainedearning_cschemagl" FOREIGN KEY ("retainedearning_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_suspensebalancing_cschemagl" FOREIGN KEY ("suspensebalancing_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_currencybalancing_cschemagl" FOREIGN KEY ("currencybalancing_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_intercompanydueto_cschemagl" FOREIGN KEY ("intercompanydueto_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationhdr"
    ADD CONSTRAINT "ccurrency_callocation" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationline"
    ADD CONSTRAINT "callocation_callocationline" FOREIGN KEY ("c_allocationhdr_id") REFERENCES "c_allocationhdr" ("c_allocationhdr_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationline"
    ADD CONSTRAINT "cbpartner_callocationline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationline"
    ADD CONSTRAINT "ccashline_callocationline" FOREIGN KEY ("c_cashline_id") REFERENCES "c_cashline" ("c_cashline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationline"
    ADD CONSTRAINT "cinvoice_callocationline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationline"
    ADD CONSTRAINT "corder_callocation" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_allocationline"
    ADD CONSTRAINT "cpayment_callocationline" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bank"
    ADD CONSTRAINT "clocation_cbank" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount"
    ADD CONSTRAINT "cbank_cbankaccount" FOREIGN KEY ("c_bank_id") REFERENCES "c_bank" ("c_bank_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount"
    ADD CONSTRAINT "ccurrency_cbankaccount" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccountdoc"
    ADD CONSTRAINT "adprintformat_cbankaccountdoc" FOREIGN KEY ("check_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccountdoc"
    ADD CONSTRAINT "cbankaccount_cbadoc" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "cacctschema_cbankaccountacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "cbankaccount_cbankacctacct" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_binterestrev_cbankaccount" FOREIGN KEY ("b_interestrev_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bintransit_cbankaccount" FOREIGN KEY ("b_intransit_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bpaymentselect_cbankaccount" FOREIGN KEY ("b_paymentselect_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_brevaluationloss_cbankaccou" FOREIGN KEY ("b_revaluationloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bsettlementgain_cbankaccoun" FOREIGN KEY ("b_settlementgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bsettlementloss_cbankaccoun" FOREIGN KEY ("b_settlementloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bunallocatedcash_cbankaccou" FOREIGN KEY ("b_unallocatedcash_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bunidentified_cbankaccount" FOREIGN KEY ("b_unidentified_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_basset_cbankaccount" FOREIGN KEY ("b_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_binterestexp_cbankaccount" FOREIGN KEY ("b_interestexp_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bexpense_cbankaccount" FOREIGN KEY ("b_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_brevaluationgain_cbankaccou" FOREIGN KEY ("b_revaluationgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatement"
    ADD CONSTRAINT "cbankaccount_cbankstatement" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "cbstatement_cbstatementline" FOREIGN KEY ("c_bankstatement_id") REFERENCES "c_bankstatement" ("c_bankstatement_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "cbpartner_cbankstatementline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "ccharge_cbankstmtlime" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "ccurrency_cbankstmtline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "cinvoice_cbankstatementline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "cpayment_cbankstmtline" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementloader"
    ADD CONSTRAINT "cbankacct_cbankstmtloader" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "adclient_cbpartner" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "ad_language_c_buspartner" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "adorg_cbpartner" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "adorg_cbpartnerorg" FOREIGN KEY ("ad_orgbp_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "adprintformatinv_cbpartner" FOREIGN KEY ("invoice_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "adusersalesrep_cbpartner" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cbpartner_cpbartnerparent" FOREIGN KEY ("bpartner_parent_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cbpgroup_cbpartner" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cdunning_cbpartner" FOREIGN KEY ("c_dunning_id") REFERENCES "c_dunning" ("c_dunning_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cgreeting_cbpartner" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cinvoiceschedule_cbpartner" FOREIGN KEY ("c_invoiceschedule_id") REFERENCES "c_invoiceschedule" ("c_invoiceschedule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cpopaymentterm_cbpartner" FOREIGN KEY ("po_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "cpaymentterm_cbpartner" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mdiscounts_cbpartner" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mdiscountspo_cbpartner" FOREIGN KEY ("po_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mpricelistpo_cbuspartner" FOREIGN KEY ("po_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mpricelist_cbpartner" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "c_buspartner_locationclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "c_buspartner_locationorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "cbpartner_cbplocation" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "clocation_cbplocation" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "csalesregion_bpartnerlocation" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_product"
    ADD CONSTRAINT "cbpartner_cbpproduct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_product"
    ADD CONSTRAINT "mproduct_cbpproduct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_bankaccount"
    ADD CONSTRAINT "aduser_cbpbankaccount" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_bankaccount"
    ADD CONSTRAINT "cbank_cbpbankaccount" FOREIGN KEY ("c_bank_id") REFERENCES "c_bank" ("c_bank_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_bankaccount"
    ADD CONSTRAINT "cbpartner_cbpbankaccount" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_customer_acct"
    ADD CONSTRAINT "cacctschema_cbpcustomeracct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_customer_acct"
    ADD CONSTRAINT "cbuspartner_cbpcustomer_acct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_customer_acct"
    ADD CONSTRAINT "vc_cprepayment_cbpcustomer" FOREIGN KEY ("c_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_customer_acct"
    ADD CONSTRAINT "vc_creceivable_cbpcustomer" FOREIGN KEY ("c_receivable_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_edi"
    ADD CONSTRAINT "adsequence_cbpedi" FOREIGN KEY ("ad_sequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_edi"
    ADD CONSTRAINT "c_bpartner_cbpedi" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_edi"
    ADD CONSTRAINT "mwarehouse_cbpedi" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "cacctschema_cbpemployeeacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "cbuspartner_c_bpemployeeacct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "vc_eexpense_cbpemployee" FOREIGN KEY ("e_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "vc_eprepayment_cbpemployee" FOREIGN KEY ("e_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mdiscountschemapo_cbpgroup" FOREIGN KEY ("po_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mdiscountschema_cbpgroup" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mpricelistpo_cbpgroup" FOREIGN KEY ("po_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mpricelist_cbpgroup" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "cacctschema_cbpgroupacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "cbpgroup_cbpgroupacct" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_notinvoicedreceipts_cbpgrou" FOREIGN KEY ("notinvoicedreceipts_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_notinvoicedrec_cbpgroup" FOREIGN KEY ("notinvoicedreceivables_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_writeoff_cbpgroup" FOREIGN KEY ("writeoff_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_paydiscountexp_cbpgroup" FOREIGN KEY ("paydiscount_exp_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_paydiscountrev_cbpgroup" FOREIGN KEY ("paydiscount_rev_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_unearnedrevenue_cbpgroup" FOREIGN KEY ("unearnedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_vliabilityservices_cbpgroup" FOREIGN KEY ("v_liability_services_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_vliability_cbpgroup" FOREIGN KEY ("v_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_vprepayment_cbpgroup" FOREIGN KEY ("v_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_creceivable_cbpgroup" FOREIGN KEY ("c_receivable_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_cprepayment_cbpgroup" FOREIGN KEY ("c_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_notinvoicedrevenue_cbpgroup" FOREIGN KEY ("notinvoicedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbpartner_cbprelation" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbpartner_cbprelationbp" FOREIGN KEY ("c_bpartnerrelation_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbplocation_cbprelation" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbplocation_cbprelationbp" FOREIGN KEY ("c_bpartnerrelation_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "cacctschema_cbpvendoracct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "c_buspartner_c_bp_vendor_acct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "vc_vliability_cbpvendor" FOREIGN KEY ("v_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "vc_vprepayment_cbpvendor" FOREIGN KEY ("v_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "vc_vliabilityservices_cbpvendo" FOREIGN KEY ("v_liability_services_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_withholding"
    ADD CONSTRAINT "cbpartner_cbpwithholding" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_withholding"
    ADD CONSTRAINT "cwithholding_cbpwithholding" FOREIGN KEY ("c_withholding_id") REFERENCES "c_withholding" ("c_withholding_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_calendar"
    ADD CONSTRAINT "c_calendarclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_calendar"
    ADD CONSTRAINT "c_calendarorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_campaign"
    ADD CONSTRAINT "cchannel_ccampaign" FOREIGN KEY ("c_channel_id") REFERENCES "c_channel" ("c_channel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "adorg_ccash" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "adorgtrx_ccash" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "cactivity_ccash" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "ccampaign_ccash" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "ccashbook_ccash" FOREIGN KEY ("c_cashbook_id") REFERENCES "c_cashbook" ("c_cashbook_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "celementvalueuser2_ccash" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "celementvalueuser1_ccash" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "cproject_ccash" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook"
    ADD CONSTRAINT "ccurrency_ccashbook" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "cacctschema_ccashbookacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "ccashbook_ccashbookacct" FOREIGN KEY ("c_cashbook_id") REFERENCES "c_cashbook" ("c_cashbook_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbdifferences_ccashbook" FOREIGN KEY ("cb_differences_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbreceipt_ccashbook" FOREIGN KEY ("cb_receipt_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbcashtransfer_ccashbook" FOREIGN KEY ("cb_cashtransfer_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbasset_ccashbook" FOREIGN KEY ("cb_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbexpense_ccashbook" FOREIGN KEY ("cb_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "cbankacct_ccashline" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "ccash_ccashline" FOREIGN KEY ("c_cash_id") REFERENCES "c_cash" ("c_cash_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "ccharge_ccashline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "ccurrency_ccashline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "cinvoice_ccashline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge"
    ADD CONSTRAINT "ctaxcategory_ccharge" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge_acct"
    ADD CONSTRAINT "cacctschema_cchargeacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge_acct"
    ADD CONSTRAINT "cchrage_cchargeacct" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge_acct"
    ADD CONSTRAINT "vc_chexpense_ccharge" FOREIGN KEY ("ch_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge_acct"
    ADD CONSTRAINT "vc_chrevenue_ccharge" FOREIGN KEY ("ch_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_city"
    ADD CONSTRAINT "c_cityclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_city"
    ADD CONSTRAINT "c_cityorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_city"
    ADD CONSTRAINT "ccountry_ccity" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_city"
    ADD CONSTRAINT "cregion_ccity" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commission"
    ADD CONSTRAINT "cbpartner_ccommission" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commission"
    ADD CONSTRAINT "ccharge_ccommission" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commission"
    ADD CONSTRAINT "ccurrency_ccommission" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionamt"
    ADD CONSTRAINT "ccomline_ccomamt" FOREIGN KEY ("c_commissionline_id") REFERENCES "c_commissionline" ("c_commissionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionamt"
    ADD CONSTRAINT "ccommentrun_ccommissionamt" FOREIGN KEY ("c_commissionrun_id") REFERENCES "c_commissionrun" ("c_commissionrun_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissiondetail"
    ADD CONSTRAINT "ccommissionamt_ccomdetail" FOREIGN KEY ("c_commissionamt_id") REFERENCES "c_commissionamt" ("c_commissionamt_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissiondetail"
    ADD CONSTRAINT "ccurrency_ccommissiondetail" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissiondetail"
    ADD CONSTRAINT "cinvoiceline_ccommissiondet" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissiondetail"
    ADD CONSTRAINT "corderline_ccommissiondetail" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "adorgtrx_ccommissionline" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "cbpartner_ccommissionline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "cbpgroup_commissionline" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "ccommission_ccommissionline" FOREIGN KEY ("c_commission_id") REFERENCES "c_commission" ("c_commission_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "csalesregion_ccommissionline" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "mproduct_ccommissionline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionline"
    ADD CONSTRAINT "mproductcat_ccommissionline" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissionrun"
    ADD CONSTRAINT "ccommission_ccommissionrun" FOREIGN KEY ("c_commission_id") REFERENCES "c_commission" ("c_commission_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_conversion_rate"
    ADD CONSTRAINT "c_conversion_rateclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_conversion_rate"
    ADD CONSTRAINT "c_conversion_rateorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_conversion_rate"
    ADD CONSTRAINT "cconversiontype_cconvrate" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_conversion_rate"
    ADD CONSTRAINT "c_currencyconvrateto" FOREIGN KEY ("c_currency_id_to") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_conversion_rate"
    ADD CONSTRAINT "ccurrency_cconversionrate" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "c_countryclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "adlanguage_ccountry" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "c_countryorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "ccurrency_ccountry" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country_trl"
    ADD CONSTRAINT "adlanguage_ccountrytrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country_trl"
    ADD CONSTRAINT "ccountry_ccountrytrl" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency"
    ADD CONSTRAINT "c_currencyclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency"
    ADD CONSTRAINT "c_currencyorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "cacctschema_ccurrencyacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "ccurrency_ccurrencyacct" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_unrealizedloss_ccurrency" FOREIGN KEY ("unrealizedloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_realizedloss_ccurrency" FOREIGN KEY ("realizedloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_realizedgain_ccurrency" FOREIGN KEY ("realizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_unrealizedgain_ccurrency" FOREIGN KEY ("unrealizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_trl"
    ADD CONSTRAINT "adlanguage_ccurrencytrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_trl"
    ADD CONSTRAINT "ccurrency_ccurrencytrl" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cycle"
    ADD CONSTRAINT "ccurrency_ccycle" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cyclephase"
    ADD CONSTRAINT "ccyclestep_ccyclephase" FOREIGN KEY ("c_cyclestep_id") REFERENCES "c_cyclestep" ("c_cyclestep_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cyclephase"
    ADD CONSTRAINT "cphase_ccyclephase" FOREIGN KEY ("c_phase_id") REFERENCES "c_phase" ("c_phase_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cyclestep"
    ADD CONSTRAINT "ccycle_ccyclestep" FOREIGN KEY ("c_cycle_id") REFERENCES "c_cycle" ("c_cycle_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "adprintformat_cdoctype" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "ad_sequence_doctypedoc" FOREIGN KEY ("docnosequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "cdoctype_proforma" FOREIGN KEY ("c_doctypeproforma_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "cdoctype_shipment" FOREIGN KEY ("c_doctypeshipment_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "cdoctype_invoice" FOREIGN KEY ("c_doctypeinvoice_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "glcategory_cdoctype" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctypecounter"
    ADD CONSTRAINT "cdoctype_cdoctypecounter" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctypecounter"
    ADD CONSTRAINT "cdoctypecount_cdoctypecount" FOREIGN KEY ("counter_c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype_trl"
    ADD CONSTRAINT "adlanguage_cdoctypetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype_trl"
    ADD CONSTRAINT "cdoctype_cdoctypetrl" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunninglevel"
    ADD CONSTRAINT "adprintformat_cdunninglevel" FOREIGN KEY ("dunning_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunninglevel"
    ADD CONSTRAINT "cdunning_cdunninglevel" FOREIGN KEY ("c_dunning_id") REFERENCES "c_dunning" ("c_dunning_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunninglevel"
    ADD CONSTRAINT "cpaymentterm_cdunninglevel" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunninglevel_trl"
    ADD CONSTRAINT "adlanguage_cdunninglevel" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunninglevel_trl"
    ADD CONSTRAINT "cdunninglevel_cdltrl" FOREIGN KEY ("c_dunninglevel_id") REFERENCES "c_dunninglevel" ("c_dunninglevel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrun"
    ADD CONSTRAINT "cdunninglevel_cdunningrun" FOREIGN KEY ("c_dunninglevel_id") REFERENCES "c_dunninglevel" ("c_dunninglevel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunentry"
    ADD CONSTRAINT "cbpartner_cdunningrunentry" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunentry"
    ADD CONSTRAINT "ccurrency_cdunningrunentry" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunentry"
    ADD CONSTRAINT "cdunningrun_cdunningrunentry" FOREIGN KEY ("c_dunningrun_id") REFERENCES "c_dunningrun" ("c_dunningrun_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunline"
    ADD CONSTRAINT "cdunningrunentry_line" FOREIGN KEY ("c_dunningrunentry_id") REFERENCES "c_dunningrunentry" ("c_dunningrunentry_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunline"
    ADD CONSTRAINT "cinvoice_cdunningrunline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunline"
    ADD CONSTRAINT "cpayment_cdunningrunline" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_element"
    ADD CONSTRAINT "adclient_celement" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_element"
    ADD CONSTRAINT "c_elementorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_element"
    ADD CONSTRAINT "adtree_celement" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "adclient_celementvalue" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "adorg_celementvalue" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "cbankaccount_celementvalue" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "ccurrency_celementvalue" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "celement_celementvalue" FOREIGN KEY ("c_element_id") REFERENCES "c_element" ("c_element_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue_trl"
    ADD CONSTRAINT "adlanguage_celementvaluetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue_trl"
    ADD CONSTRAINT "celementvalue_cevaluetrl" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_greeting_trl"
    ADD CONSTRAINT "adlanguage_cgreetingtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_greeting_trl"
    ADD CONSTRAINT "cgreeting_cgreetingtrl" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "adorgto_cinterorgacct" FOREIGN KEY ("ad_orgto_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "adorg_cinterorgacct" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "cacctschema_cinterorgacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "vc_intercompanyduefrom_cintero" FOREIGN KEY ("intercompanyduefrom_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "vc_intercompanydueto_cinterorg" FOREIGN KEY ("intercompanydueto_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "adorg_cinvoice" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "adorgtrx_cinvoice" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "aduser_cinvoice" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "aduser_sr_cinvoice" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cactivity_cinvoice" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cbpartner_cinvoice" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "c_bplocation_cinvoice" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "ccampaign_cinvoice" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "ccashline_cinvoice" FOREIGN KEY ("c_cashline_id") REFERENCES "c_cashline" ("c_cashline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "ccharge_cinvoice" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cconversiontype_cinvoice" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "ccurrency_cinvoice" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cdoctype_cinvoice" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cdoctypetarget_cinvoice" FOREIGN KEY ("c_doctypetarget_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "celementvalueuser2_cinvoice" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "celementvalueuser1_cinvoice" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cinvoice_ref" FOREIGN KEY ("ref_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "corder_cinvoice" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cpayment_cinvoice" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cpaymentterm_cinvoice" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cproject_cinvoice" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "mpricelist_cinvoice" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatch"
    ADD CONSTRAINT "aduser_cinvoicebatch" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatch"
    ADD CONSTRAINT "cconventiontype_cinvoicebatch" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatch"
    ADD CONSTRAINT "ccurrency_cinvoicebatch" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "adorg_cinvoicebatchline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "adorgtrx_cinvoicebatchline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "aduser_cinvoicebatchline" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cactivity_cinvoicebatchline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cbpartner_cinvoicebatchline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cbplocation_cinvoicebline" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "ccharge_cinvoicebatchline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cdoctype_cinvoicebatchline" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "celementvalueu2_cinvoicebline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "celementvalueu1_cinvoicebline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cinvoice_cinvoicebatchline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cinvoicebatch_cinvoicebline" FOREIGN KEY ("c_invoicebatch_id") REFERENCES "c_invoicebatch" ("c_invoicebatch_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cinvoiceline_cinvoicebline" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cproject_cinvoicebatchline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "ctax_cinvoicebatchline" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "adorg_cinvoiceline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "adorgtrx_cinvoiceline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "aasset_cinvoiceline" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cactivity_cinvoiceline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "ccampaign_cinvoiceline" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "ccharge_cinvoiceline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "celemenrvalueuser1_cinvline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "celemenrvalueuser2_cinvline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cinvoice_cinvoiceline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "convoiceline_ref" FOREIGN KEY ("ref_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "corderline_cinvoiceline" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cproject_cinvoiceline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cprojectphase_cinvoiceline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cprojecttask_cinvoiceline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "ctax_cinvoiceline" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cuom_cinvoiceline" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "mattrsetinst_cinvoiceline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "minoutline_cinvoiceline" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "mproduct_cinvoiceline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "sresourceassign_cinvoiceline" FOREIGN KEY ("s_resourceassignment_id") REFERENCES "s_resourceassignment" ("s_resourceassignment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicepayschedule"
    ADD CONSTRAINT "cinvoice_cinvoicepaysched" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicepayschedule"
    ADD CONSTRAINT "cpayschedule_cinvoicepaysched" FOREIGN KEY ("c_payschedule_id") REFERENCES "c_payschedule" ("c_payschedule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicetax"
    ADD CONSTRAINT "cinvoice_cinvoicetax" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicetax"
    ADD CONSTRAINT "ctax_cinvoicetax" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_job"
    ADD CONSTRAINT "cjobcategory_cjob" FOREIGN KEY ("c_jobcategory_id") REFERENCES "c_jobcategory" ("c_jobcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_jobassignment"
    ADD CONSTRAINT "aduser_cjobassignment" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_jobassignment"
    ADD CONSTRAINT "cjob_cjobassignment" FOREIGN KEY ("c_job_id") REFERENCES "c_job" ("c_job_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_jobremuneration"
    ADD CONSTRAINT "cjob_cjobremuneration" FOREIGN KEY ("c_job_id") REFERENCES "c_job" ("c_job_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_jobremuneration"
    ADD CONSTRAINT "cremuneration_cjobrem" FOREIGN KEY ("c_remuneration_id") REFERENCES "c_remuneration" ("c_remuneration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcost"
    ADD CONSTRAINT "cinvoiceline_clandedcost" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcost"
    ADD CONSTRAINT "ccostelement_clandedcost" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcost"
    ADD CONSTRAINT "minout_clandedcost" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcost"
    ADD CONSTRAINT "minoutline_clandedcost" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcost"
    ADD CONSTRAINT "mproduct_clandedcost" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcostallocation"
    ADD CONSTRAINT "cinvoiceline_clandedcostalloc" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcostallocation"
    ADD CONSTRAINT "masi_clandedcostallocation" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcostallocation"
    ADD CONSTRAINT "mcostelement_mlandedcostalloc" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_landedcostallocation"
    ADD CONSTRAINT "mproduct_clandedcostalloc" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_location"
    ADD CONSTRAINT "adclient_clocation" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_location"
    ADD CONSTRAINT "adorg_clocation" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_location"
    ADD CONSTRAINT "ccity_clocation" FOREIGN KEY ("c_city_id") REFERENCES "c_city" ("c_city_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_location"
    ADD CONSTRAINT "c_country_location" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_location"
    ADD CONSTRAINT "c_region_location" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_nonbusinessday"
    ADD CONSTRAINT "c_nonbusinesdaysclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_nonbusinessday"
    ADD CONSTRAINT "c_nonbusinesdaysorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_nonbusinessday"
    ADD CONSTRAINT "c_calendarnonbusinessday" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "adorg_corder" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "adorgtrx_corder" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "aduserbill_corder" FOREIGN KEY ("bill_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "aduser_corder" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "aduser_sr_corder" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cactivity_corder" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartnerbill_corder" FOREIGN KEY ("bill_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartnerpay_corder" FOREIGN KEY ("pay_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartner_corder" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbplocationpay_corder" FOREIGN KEY ("pay_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartnerlocation_corder" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbplocationbill_corder" FOREIGN KEY ("bill_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "ccampaign_corder" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "ccashline_corder" FOREIGN KEY ("c_cashline_id") REFERENCES "c_cashline" ("c_cashline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "ccharge_corder" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cconversiontype_corder" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "ccurrency_corder" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "c_doctype_corder" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cdoctypetarget_corder" FOREIGN KEY ("c_doctypetarget_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "celementvalueuser1_corder" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "celemenrvalueuser2_corder" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "corder_ref" FOREIGN KEY ("ref_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cpayment_corder" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cpaymentterm_soheader" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cproject_corder" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "mpricelist_soheader" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "mshipper_corder" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "mwarehouse_corder" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "adorg_corderline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "adorgtrx_corderline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cactivity_corderline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cbpartner_soline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cbpartnerlocation_soline" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "ccampaign_corderline" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "ccharge_corderline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "ccurrency_corderline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "celemenrvalueuser1_corderline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "celemenrvalueuser2_corderline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "corder_corderline" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "corderline_ref" FOREIGN KEY ("ref_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cproject_corderline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cprojectphase_corderline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cprojecttask_corderline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "ctax_corderline" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cuom_corderline" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "mattrsetinst_corderline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "mproduct_corderline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "mshipper_corderline" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "mwarehouse_corderline" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "sresourceassign_corderline" FOREIGN KEY ("s_resourceassignment_id") REFERENCES "s_resourceassignment" ("s_resourceassignment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_ordertax"
    ADD CONSTRAINT "corder_cordertax" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_ordertax"
    ADD CONSTRAINT "ctax_cordertax" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orgassignment"
    ADD CONSTRAINT "adorg_corgassignment" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orgassignment"
    ADD CONSTRAINT "aduser_corgassignment" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "adorgtrx_cpayment" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "adorg_cpayment" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cactivity_cpayment" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cbankaccount_cpayment" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cbpartner_cpayment" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cbpbankacct_cpayment" FOREIGN KEY ("c_bp_bankaccount_id") REFERENCES "c_bp_bankaccount" ("c_bp_bankaccount_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "ccampaign_cpayment" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "ccharge_cpayment" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cconversiontype_cpayment" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "ccurrency_cpayment" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cdoctype_cpayment" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "celementvalueuser1_cpayment" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "celementvalueuser2_cpayment" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cpaymentbatch_cpayment" FOREIGN KEY ("c_paymentbatch_id") REFERENCES "c_paymentbatch" ("c_paymentbatch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cproject_cpayment" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentallocate"
    ADD CONSTRAINT "cpaymtallocate_callocationline" FOREIGN KEY ("c_allocationline_id") REFERENCES "c_allocationline" ("c_allocationline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentallocate"
    ADD CONSTRAINT "cinvoice_cpaymentallocate" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentallocate"
    ADD CONSTRAINT "cpayment_cpaymentallocate" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentbatch"
    ADD CONSTRAINT "cpaymtprocessor_cpaymtbatch" FOREIGN KEY ("c_paymentprocessor_id") REFERENCES "c_paymentprocessor" ("c_paymentprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentprocessor"
    ADD CONSTRAINT "adsequence_cpaymentprocessor" FOREIGN KEY ("ad_sequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentprocessor"
    ADD CONSTRAINT "cbankaccount_cpaymtprocessor" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentprocessor"
    ADD CONSTRAINT "ccurrency_cpaymentprocessor" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentterm_trl"
    ADD CONSTRAINT "adlanguage_cpaymenttermtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentterm_trl"
    ADD CONSTRAINT "cpaymentterm_cpaytermtrl" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payschedule"
    ADD CONSTRAINT "cpaymentterm_cpayschedule" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselection"
    ADD CONSTRAINT "cbankaccount_cpayselection" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselectioncheck"
    ADD CONSTRAINT "cbpartner_cpayselectioncheck" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselectioncheck"
    ADD CONSTRAINT "cpayment_cpayselectioncheck" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselectioncheck"
    ADD CONSTRAINT "cpayselection_cpayselectcheck" FOREIGN KEY ("c_payselection_id") REFERENCES "c_payselection" ("c_payselection_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselectionline"
    ADD CONSTRAINT "cinvoice_cpayselectline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselectionline"
    ADD CONSTRAINT "cpaysel_cpayselline" FOREIGN KEY ("c_payselection_id") REFERENCES "c_payselection" ("c_payselection_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payselectionline"
    ADD CONSTRAINT "cpayselcheck_cpayselline" FOREIGN KEY ("c_payselectioncheck_id") REFERENCES "c_payselectioncheck" ("c_payselectioncheck_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_period"
    ADD CONSTRAINT "c_periodclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_period"
    ADD CONSTRAINT "c_periodorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_period"
    ADD CONSTRAINT "c_year_period" FOREIGN KEY ("c_year_id") REFERENCES "c_year" ("c_year_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_periodcontrol"
    ADD CONSTRAINT "c_period_periodcontrol" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_phase"
    ADD CONSTRAINT "cprojecttype_cphase" FOREIGN KEY ("c_projecttype_id") REFERENCES "c_projecttype" ("c_projecttype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_phase"
    ADD CONSTRAINT "mproduct_cphase" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_pos"
    ADD CONSTRAINT "aduser_cpos" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_pos"
    ADD CONSTRAINT "ccashbook_cpos" FOREIGN KEY ("c_cashbook_id") REFERENCES "c_cashbook" ("c_cashbook_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_pos"
    ADD CONSTRAINT "cposkeylayout_cpos" FOREIGN KEY ("c_poskeylayout_id") REFERENCES "c_poskeylayout" ("c_poskeylayout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_pos"
    ADD CONSTRAINT "mpricelist_cpos" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_pos"
    ADD CONSTRAINT "mwarehouse_cpos" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_poskey"
    ADD CONSTRAINT "cposkeylayout_c_poskey" FOREIGN KEY ("c_poskeylayout_id") REFERENCES "c_poskeylayout" ("c_poskeylayout_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_poskey"
    ADD CONSTRAINT "mproduct_cposkey" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "adclient_cproject" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "adorg_cproject" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "aduser_cproject" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "aduser_sr_cproject" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "cbpartner_cproject" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "cbplocation_cproject" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "ccampaign_cproject" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "ccurrency_cproject" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "cpaymentterm_cproject" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "cphase_cproject" FOREIGN KEY ("c_phase_id") REFERENCES "c_phase" ("c_phase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "cprojecttype_cproject" FOREIGN KEY ("c_projecttype_id") REFERENCES "c_projecttype" ("c_projecttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "mpricelistversion_cproject" FOREIGN KEY ("m_pricelist_version_id") REFERENCES "m_pricelist_version" ("m_pricelist_version_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "mwarehouse_cproject" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissue"
    ADD CONSTRAINT "cproject_cprojectissue" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissue"
    ADD CONSTRAINT "mattrsetinst_cprojectissue" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissue"
    ADD CONSTRAINT "minoutline_cprojectissue" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissue"
    ADD CONSTRAINT "mlocator_cprojectissue" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissue"
    ADD CONSTRAINT "mproduct_cprojectissue" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissue"
    ADD CONSTRAINT "stimeexpline_cprojectissue" FOREIGN KEY ("s_timeexpenseline_id") REFERENCES "s_timeexpenseline" ("s_timeexpenseline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissuema"
    ADD CONSTRAINT "cprojectissue_cprojectissuema" FOREIGN KEY ("c_projectissue_id") REFERENCES "c_projectissue" ("c_projectissue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectissuema"
    ADD CONSTRAINT "masi_cprojectissuema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "corder_cprojectline" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "corderpo_cprojectline" FOREIGN KEY ("c_orderpo_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cproject_cprojectline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cprojectissue_cprojectline" FOREIGN KEY ("c_projectissue_id") REFERENCES "c_projectissue" ("c_projectissue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cprojectphase_cprojectline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cprojecttask_cprojectline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "mproduct_cprojectline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "mproductcat_cprojectline" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectphase"
    ADD CONSTRAINT "corder_cprojectphase" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectphase"
    ADD CONSTRAINT "cphase_cprojectphase" FOREIGN KEY ("c_phase_id") REFERENCES "c_phase" ("c_phase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectphase"
    ADD CONSTRAINT "cproject_cprojectphase" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectphase"
    ADD CONSTRAINT "mproduct_cprojectphase" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projecttask"
    ADD CONSTRAINT "cprojectphase_cprojecttask" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projecttask"
    ADD CONSTRAINT "ctask_cprojecttask" FOREIGN KEY ("c_task_id") REFERENCES "c_task" ("c_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projecttask"
    ADD CONSTRAINT "mproduct_cprojecttask" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project_acct"
    ADD CONSTRAINT "cacctschema_cprojectacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project_acct"
    ADD CONSTRAINT "c_project_projectacct" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project_acct"
    ADD CONSTRAINT "vc_pjasset_cproject" FOREIGN KEY ("pj_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project_acct"
    ADD CONSTRAINT "vc_pjwip_cproject" FOREIGN KEY ("pj_wip_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring"
    ADD CONSTRAINT "cinvoice_crecurring" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring"
    ADD CONSTRAINT "corder_crecurring" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring"
    ADD CONSTRAINT "cpayment_crecurring" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring"
    ADD CONSTRAINT "cproject_crecurring" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring"
    ADD CONSTRAINT "gljournalbatch_crecurring" FOREIGN KEY ("gl_journalbatch_id") REFERENCES "gl_journalbatch" ("gl_journalbatch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring_run"
    ADD CONSTRAINT "cinvoice_crecurringrun" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring_run"
    ADD CONSTRAINT "corder_crecurringrun" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring_run"
    ADD CONSTRAINT "cpayment_crecurringrun" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring_run"
    ADD CONSTRAINT "cproject_crecurringrun" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring_run"
    ADD CONSTRAINT "crecurring_crecurringrun" FOREIGN KEY ("c_recurring_id") REFERENCES "c_recurring" ("c_recurring_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_recurring_run"
    ADD CONSTRAINT "gljournalbatch_crecurringrun" FOREIGN KEY ("gl_journalbatch_id") REFERENCES "gl_journalbatch" ("gl_journalbatch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_region"
    ADD CONSTRAINT "c_regionclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_region"
    ADD CONSTRAINT "c_regionorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_region"
    ADD CONSTRAINT "ccountry_cregion" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "cacctschema_crevrecplan" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "ccurrency_crevenuerecplan" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "cinvoiceline_crevenuerecplan" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "crevenuerecognition_plan" FOREIGN KEY ("c_revenuerecognition_id") REFERENCES "c_revenuerecognition" ("c_revenuerecognition_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "vc_unearnedrevenue_crevenuerec" FOREIGN KEY ("unearnedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "vc_prevenue_crevenuerecognitio" FOREIGN KEY ("p_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_run"
    ADD CONSTRAINT "crevrecplan_crefrecrun" FOREIGN KEY ("c_revenuerecognition_plan_id") REFERENCES "c_revenuerecognition_plan" ("c_revenuerecognition_plan_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_run"
    ADD CONSTRAINT "gljournal_crevenuerecrun" FOREIGN KEY ("gl_journal_id") REFERENCES "gl_journal" ("gl_journal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "adusersalesrep_crfq" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "aduser_crfq" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "cbpartner_crfq" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "cbplocation_crfq" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "ccurrency_crfq" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "corder_crfq" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq"
    ADD CONSTRAINT "crfqtopic_crfq" FOREIGN KEY ("c_rfq_topic_id") REFERENCES "c_rfq_topic" ("c_rfq_topic_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqline"
    ADD CONSTRAINT "crfq_crfqline" FOREIGN KEY ("c_rfq_id") REFERENCES "c_rfq" ("c_rfq_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqline"
    ADD CONSTRAINT "masetinstance_crfqline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqline"
    ADD CONSTRAINT "mproduct_crfqline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqlineqty"
    ADD CONSTRAINT "crfqline_crfqlineqty" FOREIGN KEY ("c_rfqline_id") REFERENCES "c_rfqline" ("c_rfqline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqlineqty"
    ADD CONSTRAINT "cuom_crfqlineqty" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponse"
    ADD CONSTRAINT "aduser_crfqresponse" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponse"
    ADD CONSTRAINT "cbpartner_crfqresponse" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponse"
    ADD CONSTRAINT "cbplocation_crfqresponse" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponse"
    ADD CONSTRAINT "ccurrency_crfqresponse" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponse"
    ADD CONSTRAINT "corder_crfqresponse" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponse"
    ADD CONSTRAINT "crfq_crfqresponse" FOREIGN KEY ("c_rfq_id") REFERENCES "c_rfq" ("c_rfq_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponseline"
    ADD CONSTRAINT "crfqline_crfqresponseline" FOREIGN KEY ("c_rfqline_id") REFERENCES "c_rfqline" ("c_rfqline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponseline"
    ADD CONSTRAINT "crfqresponse_line" FOREIGN KEY ("c_rfqresponse_id") REFERENCES "c_rfqresponse" ("c_rfqresponse_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponselineqty"
    ADD CONSTRAINT "crfqlineqty_crfqresplineqty" FOREIGN KEY ("c_rfqlineqty_id") REFERENCES "c_rfqlineqty" ("c_rfqlineqty_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponselineqty"
    ADD CONSTRAINT "crfqresonseline_qty" FOREIGN KEY ("c_rfqresponseline_id") REFERENCES "c_rfqresponseline" ("c_rfqresponseline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topic"
    ADD CONSTRAINT "adprintformat_arfqtopic" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "aduser_arfqtopicsubcr" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "cbpartner_crfqtopicsubr" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "cbpartnerloc_crfqtopicsubr" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "c_rfqtopic_subscriber" FOREIGN KEY ("c_rfq_topic_id") REFERENCES "c_rfq_topic" ("c_rfq_topic_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriberonly"
    ADD CONSTRAINT "crfqtopicsubscriber_only" FOREIGN KEY ("c_rfq_topicsubscriber_id") REFERENCES "c_rfq_topicsubscriber" ("c_rfq_topicsubscriber_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriberonly"
    ADD CONSTRAINT "mproduct_crfqtsubonly" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriberonly"
    ADD CONSTRAINT "mprodcategory_crfqtsubonly" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_servicelevel"
    ADD CONSTRAINT "crevrecplan_cservicelevel" FOREIGN KEY ("c_revenuerecognition_plan_id") REFERENCES "c_revenuerecognition_plan" ("c_revenuerecognition_plan_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_servicelevel"
    ADD CONSTRAINT "mproduct_cservicelevel" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_servicelevelline"
    ADD CONSTRAINT "cservicelevel_line" FOREIGN KEY ("c_servicelevel_id") REFERENCES "c_servicelevel" ("c_servicelevel_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subacct"
    ADD CONSTRAINT "celementvalue_csubacct" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription"
    ADD CONSTRAINT "cbpartner_csubscription" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription"
    ADD CONSTRAINT "csubscrtype_csubscription" FOREIGN KEY ("c_subscriptiontype_id") REFERENCES "c_subscriptiontype" ("c_subscriptiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription"
    ADD CONSTRAINT "mproduct_csubscription" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription_delivery"
    ADD CONSTRAINT "csubcription_csubscrdelivery" FOREIGN KEY ("c_subscription_id") REFERENCES "c_subscription" ("c_subscription_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_task"
    ADD CONSTRAINT "cphase_ctask" FOREIGN KEY ("c_phase_id") REFERENCES "c_phase" ("c_phase_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_task"
    ADD CONSTRAINT "mproduct_ctask" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax"
    ADD CONSTRAINT "c_country_c_tax" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax"
    ADD CONSTRAINT "c_countryto_c_tax" FOREIGN KEY ("to_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax"
    ADD CONSTRAINT "c_regionto_c_tax" FOREIGN KEY ("to_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax"
    ADD CONSTRAINT "c_region_c_tax" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax"
    ADD CONSTRAINT "ctax_parent" FOREIGN KEY ("parent_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax"
    ADD CONSTRAINT "ctaxcategory_ctax" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxcategory_trl"
    ADD CONSTRAINT "adlanguage_ctaxcategorytrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxcategory_trl"
    ADD CONSTRAINT "ctaxcategory_trl" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationacct"
    ADD CONSTRAINT "cacctschema_ctaxdeclacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationacct"
    ADD CONSTRAINT "ctaxdecl_ctaxdeclacct" FOREIGN KEY ("c_taxdeclaration_id") REFERENCES "c_taxdeclaration" ("c_taxdeclaration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationacct"
    ADD CONSTRAINT "factacct_ctaxdeclacct" FOREIGN KEY ("fact_acct_id") REFERENCES "fact_acct" ("fact_acct_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "callocationline_ctaxdeclline" FOREIGN KEY ("c_allocationline_id") REFERENCES "c_allocationline" ("c_allocationline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "cbpartner_ctaxdeclline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "ccurrency_ctaxdeclline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "cinvoice_ctaxdeclline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "cinvoiceline_ctaxdeclline" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "ctax_ctaxdeclline" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationline"
    ADD CONSTRAINT "ctaxdeclaration_ctaxdeclline" FOREIGN KEY ("c_taxdeclaration_id") REFERENCES "c_taxdeclaration" ("c_taxdeclaration_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxpostal"
    ADD CONSTRAINT "ctax_ctaxpostal" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "cacctschema_ctaxacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "ctax_ctaxacct" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_texpense_ctax" FOREIGN KEY ("t_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_tliability_ctax" FOREIGN KEY ("t_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_trec_ctax" FOREIGN KEY ("t_receivables_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_tcredit_ctax" FOREIGN KEY ("t_credit_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_tdue_ctax" FOREIGN KEY ("t_due_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_trl"
    ADD CONSTRAINT "adlanguage_ctaxtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_trl"
    ADD CONSTRAINT "ctax_ctaxtrl" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom"
    ADD CONSTRAINT "c_uomclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom"
    ADD CONSTRAINT "c_uomorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "c_uom_conversionclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "c_uom_conversionorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "c_uomconversionto" FOREIGN KEY ("c_uom_to_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "cuom_cuomconversion" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_trl"
    ADD CONSTRAINT "adlanguage_cuomtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_trl"
    ADD CONSTRAINT "cuom_cuomtrl" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_userremuneration"
    ADD CONSTRAINT "aduser_cuserremuneration" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_userremuneration"
    ADD CONSTRAINT "cremuneration_cuserrem" FOREIGN KEY ("c_remuneration_id") REFERENCES "c_remuneration" ("c_remuneration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "adclient_vc" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "adorg_vc" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "adorgtrx_vc" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cacctschema_cvalidcombination" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cactivity_cvalidcombination" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cbpartner_vc" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "socampaign_vc" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "celementvalueuser2_vc" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "celementvalueaccount_vc" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "celementvalueuser1_vc" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "mlocationto_vc" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "mlocationfrom_vc" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cproject_vc" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "csalesregion_vc" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "mproduct_vc" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_withholding"
    ADD CONSTRAINT "cpaymentterm_cwithholding" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_withholding_acct"
    ADD CONSTRAINT "cacctschema_cwithholdingacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_withholding_acct"
    ADD CONSTRAINT "vc_withholding_cwithholding" FOREIGN KEY ("withholding_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_withholding_acct"
    ADD CONSTRAINT "cwithholding_cwithholdingacct" FOREIGN KEY ("c_withholding_id") REFERENCES "c_withholding" ("c_withholding_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_year"
    ADD CONSTRAINT "c_yearclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_year"
    ADD CONSTRAINT "c_yearorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_year"
    ADD CONSTRAINT "c_calendar_year" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "ac_client_fact_acct" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "ad_orgtrx_fact_acct" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "ad_org_fact_acct" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "adtable_factacct" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "aasset_factacct" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cacctschema_factacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cactivity_factacct" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_buspartner_fact_acct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "so_campaign_fact_acct" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_currency_fact_acct" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "celementvalue_factacct" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "celementvalueuser2_factacct" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "celementvalueuser1_factacct" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_locationfrom_fact_acct" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_locationto_fact_acct" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cperiod_factacct" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_project_fact_acct" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cprojectphase_factacct" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cprojecttask_factacct" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_salesregion_factacct" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "ctax_factacct" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_uom_fact_acct" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "glbudget_factacct" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "glcategory_factacct" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "mlocator_factacct" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "m_product_fact_acct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "adclient_factacctbal" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "adorg_factacctbal" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "adorgtrx_factacctbal" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cacctschema_factacctbal" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cactivity_factacctbal" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cbpartner_factacctbal" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "ccampaign_factacctbal" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "celementvalueu1_factacctbal" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "celementvalueacct_factacctbal" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "celementvalueu2_factacctbal" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "clocto_factacctbal" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "clocfrom_factacctbalance" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cproject_factacctbal" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cprojectphase_factacctbalance" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cprojecttask_factacctbalance" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "csalesregion_factacctbal" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "glbudget_factacctbalance" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "mproduct_factacctbal" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_budgetcontrol"
    ADD CONSTRAINT "cacctschema_glbudgetcontrol" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_budgetcontrol"
    ADD CONSTRAINT "glbudget_glbudgetcontrol" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "adorg_gldist" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "adorgtrx_gldist" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "adorgorg_gldist" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cacctschema_gldist" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cactivity_gldist" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cbpartner_gldist" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "ccampaign_gldist" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cdoctype_gldistribution" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cevalueuser2_gldist" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cevalueuser1_gldist" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cevalueacct_gldist" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "clocto_gldist" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "clocfrom_gldist" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cproject_gldist" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "csalesregopn_gldist" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "mproduct_gldist" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "adorg_gldistline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "adorgtrx_gldistline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "adorgorg_gldistline" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cactivity_gldistline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cbpartner_gldistline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "ccampaign_gldistline" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cevalueuser1_gldistline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cevalueuser2_gldistline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cevalueacct_gldistline" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "clocto_gldistline" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "clocfrom_gldistline" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cproject_gldistline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "csalesregion_gldistline" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "gldistribution_gldistline" FOREIGN KEY ("gl_distribution_id") REFERENCES "gl_distribution" ("gl_distribution_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "mproduct_gldistline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_fund"
    ADD CONSTRAINT "cacctschema_glfund" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_fundrestriction"
    ADD CONSTRAINT "celementvalue_glfundrestr" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_fundrestriction"
    ADD CONSTRAINT "glfund_glfundrestriction" FOREIGN KEY ("gl_fund_id") REFERENCES "gl_fund" ("gl_fund_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "c_acctschema_gl_journal" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "cconversiontype_gljournal" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "ccurrency_gljournal" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "cdoctype_gljournal" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "c_period_journal" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "glbudget_gljournal" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "glcategory_gljournal" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journal"
    ADD CONSTRAINT "gljournalbatch_gljournal" FOREIGN KEY ("gl_journalbatch_id") REFERENCES "gl_journalbatch" ("gl_journalbatch_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalbatch"
    ADD CONSTRAINT "ccurrency_gljournalbatch" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalbatch"
    ADD CONSTRAINT "cdoctype_gljournalbatch" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalbatch"
    ADD CONSTRAINT "c_period_journalbatch" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalbatch"
    ADD CONSTRAINT "glcategory_gljournalbatch" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "adclient_gljournalline" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "adorg_gljournalline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "cconversiontype_gljournalline" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "ccurrency_gljournalline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "cuom_gljournalline" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "cvc_gljournalline" FOREIGN KEY ("c_validcombination_id") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_journalline"
    ADD CONSTRAINT "gljournal_gljournalline" FOREIGN KEY ("gl_journal_id") REFERENCES "gl_journal" ("gl_journal_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbankaccount_ibankstatement" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbankstatement_ibankstatement" FOREIGN KEY ("c_bankstatement_id") REFERENCES "c_bankstatement" ("c_bankstatement_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbankstmtline_ibankstmt" FOREIGN KEY ("c_bankstatementline_id") REFERENCES "c_bankstatementline" ("c_bankstatementline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbpartner_ibankstatement" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "ccharge_ibankstmt" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "ccurrency_ibankstatement" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cinvoice_ibankstatement" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cpayment_ibankstatement" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "aduser_ibpartner" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cbpartner_ibpartner" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cbpartnerlocation_ibpartner" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cbpgroup_ibpartner" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "ccountry_ipartner" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cgreeting_ibpartner" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cregion_ibpartner" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "cconvtype_iconvrate" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "cconversionrate_iconvrate" FOREIGN KEY ("c_conversion_rate_id") REFERENCES "c_conversion_rate" ("c_conversion_rate_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "ccurrency_iconvrateto" FOREIGN KEY ("c_currency_id_to") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "ccurrency_iconvrate" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "adcolumn_ielementvalue" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "celement_ielementvalue" FOREIGN KEY ("c_element_id") REFERENCES "c_element" ("c_element_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "celementvalue_ielementvalue" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "cevalueparent_ielementvalue" FOREIGN KEY ("parentelementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "adorgdoc_igljournal" FOREIGN KEY ("ad_orgdoc_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "adorgtrx_igljournal" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "adorg_igljournal" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cacctschema_igljournal" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cactivity_gljournal" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cbpartner_igljournal" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "ccampaign_igljournal" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cconversiontype_igljournal" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "ccurrency_igljournal" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cdoctype_igljournal" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "celvalueuser2_igljournal" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cevalueuser1_igljournal" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "celvalueaccount_igljournal" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "clocfrom_igljournal" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "clocto_igljournal" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cperiod_igljournal" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cproject_igljournal" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "csalesregion_igljournal" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cvalidcombination_igljournal" FOREIGN KEY ("c_validcombination_id") REFERENCES "c_validcombination" ("c_validcombination_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "glbudget_igljournal" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "glcategory_igljournal" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "gljournal_igljournal" FOREIGN KEY ("gl_journal_id") REFERENCES "gl_journal" ("gl_journal_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "gljournalbatch_igljournal" FOREIGN KEY ("gl_journalbatch_id") REFERENCES "gl_journalbatch" ("gl_journalbatch_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "gljourbelline_igljournal" FOREIGN KEY ("gl_journalline_id") REFERENCES "gl_journalline" ("gl_journalline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "mproduct_ogljournal" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inoutlineconfirm"
    ADD CONSTRAINT "minoutlineconfirm_import" FOREIGN KEY ("m_inoutlineconfirm_id") REFERENCES "m_inoutlineconfirm" ("m_inoutlineconfirm_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "minventory_iinventory" FOREIGN KEY ("m_inventory_id") REFERENCES "m_inventory" ("m_inventory_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "minventoryline_iinventory" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "mlocator_iinventory" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "mproduct_iinventory" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "mwarehouse_iinventory" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "adorgtrx_iinvoice" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "adorg_iinvoice" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "adusersalesrep_iinvoice" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "aduser_iinvoice" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cactivity_iinvoice" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cbpartner_iinvoice" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cbplocation_iinvoice" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ccampaign_iinvoice" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ccountry_iinvoice" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ccurrency_iinvoice" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cdoctype_iinvoice" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cinvoice_iinvoice" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cinvliceline_iinvoice" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "clocation_iinvoice" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cpaymentterm_iinvoice" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cproject_iinvoice" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cregion_iinvoice" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ctax_iinvoice" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "mpricelist_iinvoice" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "nproduct_iinvoice" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "adorgtrx_iorder" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "adorg_iorder" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "adusersalesrep_iorder" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "aduser_iorder" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cactivity_iorder" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cbpartner_iorder" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cbolocation_iorder" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cbpartnerlocbillto_iorder" FOREIGN KEY ("billto_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ccampaign_iorder" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ccountry_iorder" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ccurrency_iorder" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cdoctype_iorder" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "clocation_iorder" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "corder_iorder" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "corderline_iorder" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cpaymentterm_iorder" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cproject_iorder" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cregion_iorder" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ctax_iorder" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cuom_iorder" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mpricelist_iorder" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mproduct_iorder" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mshipper_iorder" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mwarehouse_iorder" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cbankaccount_ipayment" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cbpartner_ipayment" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "ccharge_ipayment" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cdoctype_ipayment" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cinvoice_ipayment" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cpayment_ipayment" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "cbpartner_iproduct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "ccurrency_iproduct" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "cuom_iproduct" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "mproduct_iproduct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "mproductcategory_iproduct" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "celementvalue_ireportline" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "pareportline_ireportline" FOREIGN KEY ("pa_reportline_id") REFERENCES "pa_reportline" ("pa_reportline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "pareportlineset_ireportline" FOREIGN KEY ("pa_reportlineset_id") REFERENCES "pa_reportlineset" ("pa_reportlineset_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "pareportsource_ireportline" FOREIGN KEY ("pa_reportsource_id") REFERENCES "pa_reportsource" ("pa_reportsource_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_categoryvalue"
    ADD CONSTRAINT "kcategory_kcategoryvalue" FOREIGN KEY ("k_category_id") REFERENCES "k_category" ("k_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_comment"
    ADD CONSTRAINT "adsession_kcomment" FOREIGN KEY ("ad_session_id") REFERENCES "ad_session" ("ad_session_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_comment"
    ADD CONSTRAINT "kentry_kcomment" FOREIGN KEY ("k_entry_id") REFERENCES "k_entry" ("k_entry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entry"
    ADD CONSTRAINT "adsession_kentry" FOREIGN KEY ("ad_session_id") REFERENCES "ad_session" ("ad_session_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entry"
    ADD CONSTRAINT "ksource_kentry" FOREIGN KEY ("k_source_id") REFERENCES "k_source" ("k_source_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entry"
    ADD CONSTRAINT "ktopic_kentry" FOREIGN KEY ("k_topic_id") REFERENCES "k_topic" ("k_topic_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entrycategory"
    ADD CONSTRAINT "kcategory_kentrycategory" FOREIGN KEY ("k_category_id") REFERENCES "k_category" ("k_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entrycategory"
    ADD CONSTRAINT "kcategoryvalue_kentrycategory" FOREIGN KEY ("k_categoryvalue_id") REFERENCES "k_categoryvalue" ("k_categoryvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entrycategory"
    ADD CONSTRAINT "kentry_kentrycatalog" FOREIGN KEY ("k_entry_id") REFERENCES "k_entry" ("k_entry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entryrelated"
    ADD CONSTRAINT "kentry_kentryrelatedid" FOREIGN KEY ("k_entryrelated_id") REFERENCES "k_entry" ("k_entry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entryrelated"
    ADD CONSTRAINT "kentry_kentryrelated" FOREIGN KEY ("k_entry_id") REFERENCES "k_entry" ("k_entry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "adtable_kindex" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "cmwebproject_kindex" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "cdoctype_kindex" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "rrequesttype_kindex" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_indexstop"
    ADD CONSTRAINT "cmwebproject_kindexstop" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_indexstop"
    ADD CONSTRAINT "cdoctype_kindexstop" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_indexstop"
    ADD CONSTRAINT "rrequesttype_kindexstop" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_topic"
    ADD CONSTRAINT "ktype_ktopic" FOREIGN KEY ("k_type_id") REFERENCES "k_type" ("k_type_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attribute"
    ADD CONSTRAINT "mattributesearch_mattribute" FOREIGN KEY ("m_attributesearch_id") REFERENCES "m_attributesearch" ("m_attributesearch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeinstance"
    ADD CONSTRAINT "mattribute_mattributeinst" FOREIGN KEY ("m_attribute_id") REFERENCES "m_attribute" ("m_attribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeinstance"
    ADD CONSTRAINT "mattrsetinst__mattrinst" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeinstance"
    ADD CONSTRAINT "mattributevalue_mattrinst" FOREIGN KEY ("m_attributevalue_id") REFERENCES "m_attributevalue" ("m_attributevalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeset"
    ADD CONSTRAINT "mlotctl_mattributeset" FOREIGN KEY ("m_lotctl_id") REFERENCES "m_lotctl" ("m_lotctl_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeset"
    ADD CONSTRAINT "msernoctl_attributeset" FOREIGN KEY ("m_sernoctl_id") REFERENCES "m_sernoctl" ("m_sernoctl_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributesetexclude"
    ADD CONSTRAINT "adtable_mattributesetexclude" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributesetexclude"
    ADD CONSTRAINT "mattributeset_masexclude" FOREIGN KEY ("m_attributeset_id") REFERENCES "m_attributeset" ("m_attributeset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributesetinstance"
    ADD CONSTRAINT "mattributeset_mattribsetinst" FOREIGN KEY ("m_attributeset_id") REFERENCES "m_attributeset" ("m_attributeset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributesetinstance"
    ADD CONSTRAINT "mlot_mattributesetinstance" FOREIGN KEY ("m_lot_id") REFERENCES "m_lot" ("m_lot_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeuse"
    ADD CONSTRAINT "mattribute_mattributeuse" FOREIGN KEY ("m_attribute_id") REFERENCES "m_attribute" ("m_attribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeuse"
    ADD CONSTRAINT "mattributeset_mattributeuse" FOREIGN KEY ("m_attributeset_id") REFERENCES "m_attributeset" ("m_attributeset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributevalue"
    ADD CONSTRAINT "mattribute_mattributevalue" FOREIGN KEY ("m_attribute_id") REFERENCES "m_attribute" ("m_attribute_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bom"
    ADD CONSTRAINT "mcn_mbom" FOREIGN KEY ("m_changenotice_id") REFERENCES "m_changenotice" ("m_changenotice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bom"
    ADD CONSTRAINT "mproduct_mbom" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomalternative"
    ADD CONSTRAINT "mproduct_mbomalternative" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "masi_mbomproduct" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mbom_mbomproduct" FOREIGN KEY ("m_bom_id") REFERENCES "m_bom" ("m_bom_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mbomalternative_mbomproduct" FOREIGN KEY ("m_bomalternative_id") REFERENCES "m_bomalternative" ("m_bomalternative_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mcn_mbomproduct" FOREIGN KEY ("m_changenotice_id") REFERENCES "m_changenotice" ("m_changenotice_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mproduct_mbomproduct" FOREIGN KEY ("m_productbom_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mproductop_mbomproduct" FOREIGN KEY ("m_productoperation_id") REFERENCES "m_productoperation" ("m_productoperation_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_changerequest"
    ADD CONSTRAINT "mbom_mchangerequest" FOREIGN KEY ("m_bom_id") REFERENCES "m_bom" ("m_bom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_changerequest"
    ADD CONSTRAINT "mcn_mcr" FOREIGN KEY ("m_changenotice_id") REFERENCES "m_changenotice" ("m_changenotice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "adclient_mcost" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "adorg_m_cost" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "cacctschema_mcost" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "masi_mcost" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "mcostelement_mcost" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "mcosttype_mcost" FOREIGN KEY ("m_costtype_id") REFERENCES "m_costtype" ("m_costtype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "mproduct_mcost" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "adclient_mcostdetail" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "adorg_mcostdetail" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "cacctschema_mcostdetail" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "minvoiceline_mcostdetail" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "corderline_mcostdetail" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "cprojectissue_mcostdetail" FOREIGN KEY ("c_projectissue_id") REFERENCES "c_projectissue" ("c_projectissue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "masi_mcostdetail" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "mcostelement_mcostdetail" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "minoutline_mcostdetail" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "minventoryline_mcostdetail" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "mmovementline_mcostdetail" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "mproduct_mcostdetail" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costdetail"
    ADD CONSTRAINT "mproductionline_mcostdetail" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "cacctschema_mcostqueue" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "masi_mcostqueue" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "mcostelement_mcostqueue" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "mcosttype_mcostqueue" FOREIGN KEY ("m_costtype_id") REFERENCES "m_costtype" ("m_costtype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "mproduct_mcostqueue" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demand"
    ADD CONSTRAINT "ccalendar_mdemand" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demand"
    ADD CONSTRAINT "cyear_mdemand" FOREIGN KEY ("c_year_id") REFERENCES "c_year" ("c_year_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "corderline_mdemanddetail" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "mdemandline_mdemanddetail" FOREIGN KEY ("m_demandline_id") REFERENCES "m_demandline" ("m_demandline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "mforecastline_mdemanddetail" FOREIGN KEY ("m_forecastline_id") REFERENCES "m_forecastline" ("m_forecastline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "mreqline_mdemanddetail" FOREIGN KEY ("m_requisitionline_id") REFERENCES "m_requisitionline" ("m_requisitionline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demandline"
    ADD CONSTRAINT "cperiod_mdemandline" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demandline"
    ADD CONSTRAINT "mdemand_mdemandline" FOREIGN KEY ("m_demand_id") REFERENCES "m_demand" ("m_demand_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demandline"
    ADD CONSTRAINT "mproduct_mdemandline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemabreak"
    ADD CONSTRAINT "mdiscounts_mdsbreak" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemabreak"
    ADD CONSTRAINT "mproduct_mdiscountsbreak" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemabreak"
    ADD CONSTRAINT "mprodcategory_mdiscountsbreak" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "cbpartner_mdiscountsline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "cconversiontype_mdiscschline" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "mdiscountschema_mdsline" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "mproduct_mdiscountsline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "mprodcategory_mdiscountsline" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionlistline"
    ADD CONSTRAINT "cbpartner_mdistlistline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionlistline"
    ADD CONSTRAINT "cbpartnerloc_mdistlistline" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionlistline"
    ADD CONSTRAINT "mdistributionlist_line" FOREIGN KEY ("m_distributionlist_id") REFERENCES "m_distributionlist" ("m_distributionlist_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrun"
    ADD CONSTRAINT "cbpartner_mdistributionrun" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrun"
    ADD CONSTRAINT "cbplocation_mdistributionrun" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrunline"
    ADD CONSTRAINT "mdistributionlist_runline" FOREIGN KEY ("m_distributionlist_id") REFERENCES "m_distributionlist" ("m_distributionlist_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrunline"
    ADD CONSTRAINT "mdistributionrun_line" FOREIGN KEY ("m_distributionrun_id") REFERENCES "m_distributionrun" ("m_distributionrun_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrunline"
    ADD CONSTRAINT "mproduct_mdistributionrun" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_edi"
    ADD CONSTRAINT "cbpedi_medi" FOREIGN KEY ("c_bp_edi_id") REFERENCES "c_bp_edi" ("c_bp_edi_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_edi"
    ADD CONSTRAINT "mproduct_medi" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_edi"
    ADD CONSTRAINT "mwarehouse_medi" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_edi_info"
    ADD CONSTRAINT "medi_ediinfo" FOREIGN KEY ("m_edi_id") REFERENCES "m_edi" ("m_edi_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_forecast"
    ADD CONSTRAINT "ccalendar_mforecast" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_forecast"
    ADD CONSTRAINT "cyear_mforecast" FOREIGN KEY ("c_year_id") REFERENCES "c_year" ("c_year_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_forecastline"
    ADD CONSTRAINT "cperiod_mforecastline" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_forecastline"
    ADD CONSTRAINT "mforecast_mforecastline" FOREIGN KEY ("m_forecast_id") REFERENCES "m_forecast" ("m_forecast_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_forecastline"
    ADD CONSTRAINT "mproduct_mforecastline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "ccountry_mfreight" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "ccountryto_mfreight" FOREIGN KEY ("to_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "ccurrency_mfreight" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "cregion_mfreight" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "cregionto_mfreight" FOREIGN KEY ("to_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "mfreightcategory_mfreight" FOREIGN KEY ("m_freightcategory_id") REFERENCES "m_freightcategory" ("m_freightcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "mshipper_mfreight" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "adorg_minout" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "adorgtrx_minout" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "aduser_minout" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "aduser_sr_minout" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "cactivity_minout" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "cbpartner_minout" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "vbplocation_minout" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "ccampaign_minout" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "ccharge_minout" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "cdoctype_minout" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "celementvalueuser1_minout" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "celementvalueuser2_minout" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "corder_minout" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "cproject_minout" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "minout_ref" FOREIGN KEY ("ref_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "mshipper_minout" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "mwarehouse_minout" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutconfirm"
    ADD CONSTRAINT "minout_minoutconfirm" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "adorg_minoutline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "adorgtrx_minoutline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "cactivity_minoutline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "ccampaign_minoutline" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "ccharge_minoutline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "celemenrvalueuser1_minoutline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "celemenrvalueuser2_minoutline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "corderline_minout" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "cproject_minoutline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "c_projectphase_minoutline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "cprojecttask_minoutline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "cuom_minoutline" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "mattrsetinst_minoutline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "minout_minoutline" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "minoutline_ref" FOREIGN KEY ("ref_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "mlocator_minoutline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "mproduct_minoutline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlineconfirm"
    ADD CONSTRAINT "minoutconfirm_minoutlineconf" FOREIGN KEY ("m_inoutconfirm_id") REFERENCES "m_inoutconfirm" ("m_inoutconfirm_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlineconfirm"
    ADD CONSTRAINT "minoutline_minoutconfirm" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlinema"
    ADD CONSTRAINT "masi_minourlinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlinema"
    ADD CONSTRAINT "minoutline_minoutlinema" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "adorgtrx_minventory" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "adorg_minventory" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "cactivity_minventory" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "ccampaign_minventory" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "celementvalueuser1_minvent" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "celementvalueuser2_minvent" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "cproject_minventory" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "mperpetualinv_minventory" FOREIGN KEY ("m_perpetualinv_id") REFERENCES "m_perpetualinv" ("m_perpetualinv_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "mwarehouse_minventory" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "ccharge_minventoryline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "mattrsetinst_minventoryline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "minventory_minventoryline" FOREIGN KEY ("m_inventory_id") REFERENCES "m_inventory" ("m_inventory_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "mlocator_minventoryline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "mproduct_minventoryline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventorylinema"
    ADD CONSTRAINT "masi_minventorylinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventorylinema"
    ADD CONSTRAINT "minventoryline_milinema" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_locator"
    ADD CONSTRAINT "m_wh_locator_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_locator"
    ADD CONSTRAINT "m_wh_locator_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_locator"
    ADD CONSTRAINT "m_warehouse_locator" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_lot"
    ADD CONSTRAINT "mlotctl_mlot" FOREIGN KEY ("m_lotctl_id") REFERENCES "m_lotctl" ("m_lotctl_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_lot"
    ADD CONSTRAINT "mproduct_mlot" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_lotctlexclude"
    ADD CONSTRAINT "adtable_mlotctlexclude" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_lotctlexclude"
    ADD CONSTRAINT "mlotctl_mlotctlexclude" FOREIGN KEY ("m_lotctl_id") REFERENCES "m_lotctl" ("m_lotctl_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchinv"
    ADD CONSTRAINT "convoiceline_mmatchinv" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchinv"
    ADD CONSTRAINT "minoutline_mmatchinv" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchinv"
    ADD CONSTRAINT "mproduct_mmatchinv" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchpo"
    ADD CONSTRAINT "cinvoiceline_mmatchpo" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchpo"
    ADD CONSTRAINT "corderline_mmatchpo" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchpo"
    ADD CONSTRAINT "minoutline_mmatchpo" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_matchpo"
    ADD CONSTRAINT "mproduct_mmatchpo" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "adorgtrx_mmovement" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "adorg_mmovement" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "cactivity_mmovement" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "ccampaign_mmovement" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "celementvalueuser2_mmove" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "celementvalueuser1_mmove" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "cproject_mmovement" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementconfirm"
    ADD CONSTRAINT "minventory_mmovconfirm" FOREIGN KEY ("m_inventory_id") REFERENCES "m_inventory" ("m_inventory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementconfirm"
    ADD CONSTRAINT "mmovement_mmovementconfirm" FOREIGN KEY ("m_movement_id") REFERENCES "m_movement" ("m_movement_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mattrsetinst_mmovementline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mlocatorto_mmovementline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mlocator_movementline" FOREIGN KEY ("m_locatorto_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mmovement_mmovementline" FOREIGN KEY ("m_movement_id") REFERENCES "m_movement" ("m_movement_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mproduct_mmovementline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlineconfirm"
    ADD CONSTRAINT "minventoryline_mmovlineconfirm" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlineconfirm"
    ADD CONSTRAINT "mmovementconfirm_mmovlineconf" FOREIGN KEY ("m_movementconfirm_id") REFERENCES "m_movementconfirm" ("m_movementconfirm_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlineconfirm"
    ADD CONSTRAINT "mmovementline_mmovlineconfirm" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlinema"
    ADD CONSTRAINT "masi_mmovementlinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlinema"
    ADD CONSTRAINT "mmovementline_mmovementlinema" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_operationresource"
    ADD CONSTRAINT "aasset_moperationresource" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_operationresource"
    ADD CONSTRAINT "cjob_moperationresource" FOREIGN KEY ("c_job_id") REFERENCES "c_job" ("c_job_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_operationresource"
    ADD CONSTRAINT "mproductop_mopresource" FOREIGN KEY ("m_productoperation_id") REFERENCES "m_productoperation" ("m_productoperation_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_package"
    ADD CONSTRAINT "minout_mpackage" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_package"
    ADD CONSTRAINT "mshipper_mpackage" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_packageline"
    ADD CONSTRAINT "minoutline_mpackageline" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_packageline"
    ADD CONSTRAINT "mpackage_mpackageline" FOREIGN KEY ("m_package_id") REFERENCES "m_package" ("m_package_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_perpetualinv"
    ADD CONSTRAINT "mprodcategory_mperpetualinv" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_perpetualinv"
    ADD CONSTRAINT "mwarehouse_mperpetualinv" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_pricelist"
    ADD CONSTRAINT "ccurrency_mpricelist" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_pricelist"
    ADD CONSTRAINT "basepricelist" FOREIGN KEY ("basepricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_pricelist_version"
    ADD CONSTRAINT "mdiscounts_mplversion" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_pricelist_version"
    ADD CONSTRAINT "mpricelist_mpricelistversion" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "adclient_mproduct" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "ad_org_mproduct" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "crevrecognition_mproduct" FOREIGN KEY ("c_revenuerecognition_id") REFERENCES "c_revenuerecognition" ("c_revenuerecognition_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "csubscriptiontype_mproduct" FOREIGN KEY ("c_subscriptiontype_id") REFERENCES "c_subscriptiontype" ("c_subscriptiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "ctaxcategory_mproduct" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "cuom_mproduct" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "mattributeset_mproduct" FOREIGN KEY ("m_attributeset_id") REFERENCES "m_attributeset" ("m_attributeset_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "mattrsetinst_mproduct" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "mfreightcategory_mproduct" FOREIGN KEY ("m_freightcategory_id") REFERENCES "m_freightcategory" ("m_freightcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "mlocator_mproduct" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "mproduct_mproductcategory" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "rmailtext_mproduct" FOREIGN KEY ("r_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "sexpensetype_mproduct" FOREIGN KEY ("s_expensetype_id") REFERENCES "s_expensetype" ("s_expensetype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product"
    ADD CONSTRAINT "sresource_mproduct" FOREIGN KEY ("s_resource_id") REFERENCES "s_resource" ("s_resource_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productdownload"
    ADD CONSTRAINT "mproduct_mproductdownload" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "adorg_mproduction" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "adorgtrx_mproduction" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "cactivity_mproduction" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "ccampaign_mproduction" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "celementvalueuser2_mprod" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "celementvalueuser1_mprod" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "cproject_mproduction" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mattrsetinst_mproductionline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mlocator_mproductionline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mproduct_mproductionline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mproductionplan_line" FOREIGN KEY ("m_productionplan_id") REFERENCES "m_productionplan" ("m_productionplan_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionlinema"
    ADD CONSTRAINT "masi_mproductionlinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionlinema"
    ADD CONSTRAINT "mproductionline_mplinema" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionplan"
    ADD CONSTRAINT "mlocator_mproductionplan" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionplan"
    ADD CONSTRAINT "mproduct_mproductionplan" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionplan"
    ADD CONSTRAINT "mproduction_plan" FOREIGN KEY ("m_production_id") REFERENCES "m_production" ("m_production_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productoperation"
    ADD CONSTRAINT "mproduct_mproductoperation" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productprice"
    ADD CONSTRAINT "mpricelistver_mproductprice" FOREIGN KEY ("m_pricelist_version_id") REFERENCES "m_pricelist_version" ("m_pricelist_version_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productprice"
    ADD CONSTRAINT "mproduct_mproductprice" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "cacctschema_mproductacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_pcogs_mproduct" FOREIGN KEY ("p_cogs_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_pexpense_mproduct" FOREIGN KEY ("p_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_passet_mproduct" FOREIGN KEY ("p_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_pinvoicepv_mproduct" FOREIGN KEY ("p_invoicepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_ppurchasepv_mproduct" FOREIGN KEY ("p_purchasepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_prevenue_mproduct" FOREIGN KEY ("p_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_ptdiscountgrant_mproduct" FOREIGN KEY ("p_tradediscountgrant_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_ptdiscountrec_mproduct" FOREIGN KEY ("p_tradediscountrec_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "m_product_m_product_acct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_bom"
    ADD CONSTRAINT "mproduct_mproductbom" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_bom"
    ADD CONSTRAINT "mproduct_bomproduct" FOREIGN KEY ("m_productbom_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category"
    ADD CONSTRAINT "aassetgroup_mproductcategory" FOREIGN KEY ("a_asset_group_id") REFERENCES "a_asset_group" ("a_asset_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "cacctschema_mprodcatacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_pinvoicepv_mproductcategory" FOREIGN KEY ("p_invoicepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_pcogs_mproductcategory" FOREIGN KEY ("p_cogs_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_pexpense_mproductcategory" FOREIGN KEY ("p_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_passet_mproductcategory" FOREIGN KEY ("p_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_ppurchasepv_mproductcategor" FOREIGN KEY ("p_purchasepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_prevenue_mproductcategory" FOREIGN KEY ("p_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_ptdiscountgrant_mproductcat" FOREIGN KEY ("p_tradediscountgrant_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_ptdiscountrec_mproductcateg" FOREIGN KEY ("p_tradediscountrec_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "mprodcat_mprodcatacct" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_costing"
    ADD CONSTRAINT "cacctschema_mproductcosting" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_costing"
    ADD CONSTRAINT "mproduct_mproductcosting" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_po"
    ADD CONSTRAINT "m_productpo_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_po"
    ADD CONSTRAINT "m_productpo_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_po"
    ADD CONSTRAINT "c_buspartner_m_product_po" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_po"
    ADD CONSTRAINT "ccurrency_mproductpo" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_po"
    ADD CONSTRAINT "c_uom_m_product_po" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_po"
    ADD CONSTRAINT "m_product_productpo" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_trl"
    ADD CONSTRAINT "adlanguage_mproducttrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_trl"
    ADD CONSTRAINT "mproduct_mproducttrl" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_relatedproduct"
    ADD CONSTRAINT "mproduct_mrelated_product" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_relatedproduct"
    ADD CONSTRAINT "mproduct_mrelatedproduct" FOREIGN KEY ("relatedproduct_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_replenish"
    ADD CONSTRAINT "m_product_replenish" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_replenish"
    ADD CONSTRAINT "m_warehouse_replenish" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisition"
    ADD CONSTRAINT "aduser_mrequisition" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisition"
    ADD CONSTRAINT "mprocelist_mrequisition" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisition"
    ADD CONSTRAINT "mwarehouse_mrequisition" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisitionline"
    ADD CONSTRAINT "mproduct_mrequisitionline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisitionline"
    ADD CONSTRAINT "mrequisition_mrequisitionline" FOREIGN KEY ("m_requisition_id") REFERENCES "m_requisition" ("m_requisition_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_rma"
    ADD CONSTRAINT "minout_mrma" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_rmaline"
    ADD CONSTRAINT "minoutline_mrmaline" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_rmaline"
    ADD CONSTRAINT "mrma_mrmaline" FOREIGN KEY ("m_rma_id") REFERENCES "m_rma" ("m_rma_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_sernoctlexclude"
    ADD CONSTRAINT "adtable_msernoctlexclude" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_sernoctlexclude"
    ADD CONSTRAINT "msernoctl_msernoctlexclude" FOREIGN KEY ("m_sernoctl_id") REFERENCES "m_sernoctl" ("m_sernoctl_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_shipper"
    ADD CONSTRAINT "cbpartner_mshipper" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_storage"
    ADD CONSTRAINT "m_item_storage_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_storage"
    ADD CONSTRAINT "m_item_storage_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_storage"
    ADD CONSTRAINT "mattrsetinst_mstorage" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_storage"
    ADD CONSTRAINT "m_locator_storage" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_storage"
    ADD CONSTRAINT "mproduct_storage" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "m_substitute_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "m_substitute_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "mproduct_substitutesub" FOREIGN KEY ("substitute_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "mproduct_substitute" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "cprojectissue_mtransaction" FOREIGN KEY ("c_projectissue_id") REFERENCES "c_projectissue" ("c_projectissue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "mattrsetinst_mtransaction" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "minoutline_mtransaction" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "minventoryline_mtransaction" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "mlocator_minventorycount" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "mmovementline_mtransaction" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "mproduct_minventorycount" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transaction"
    ADD CONSTRAINT "mproductionline_mtransaction" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mattributesetinst_mtrxalloc" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minoutline_mtrxalloc" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minoutlineout_mtrxalloc" FOREIGN KEY ("out_m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minventoryline_mtrxalloc" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minventorylineout_mtrxalloc" FOREIGN KEY ("out_m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mproduct_mtrxalloc" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mproductionlineout_mtrxalloc" FOREIGN KEY ("out_m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mproductionline_mtrxalloc" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mttransaction_mtrxalloc" FOREIGN KEY ("m_transaction_id") REFERENCES "m_transaction" ("m_transaction_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mtransactionout_mtrxalloc" FOREIGN KEY ("out_m_transaction_id") REFERENCES "m_transaction" ("m_transaction_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse"
    ADD CONSTRAINT "m_warehouse_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse"
    ADD CONSTRAINT "m_warehouse_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse"
    ADD CONSTRAINT "c_location_warehouse" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "cacctschema_mwarehouseacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_winventory_mwarehouse" FOREIGN KEY ("w_inventory_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_winvactualadjust_mwarehouse" FOREIGN KEY ("w_invactualadjust_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_wrevaluation_mwarehouse" FOREIGN KEY ("w_revaluation_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_wdifferences_mwarehouse" FOREIGN KEY ("w_differences_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "m_warehouse_warehouse_acct" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_achievement"
    ADD CONSTRAINT "pameasure_paachievement" FOREIGN KEY ("pa_measure_id") REFERENCES "pa_measure" ("pa_measure_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_benchmarkdata"
    ADD CONSTRAINT "pabenchmark_pabenchmarkdata" FOREIGN KEY ("pa_benchmark_id") REFERENCES "pa_benchmark" ("pa_benchmark_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor1_pacolorschema" FOREIGN KEY ("ad_printcolor1_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor4_pacolorschema" FOREIGN KEY ("ad_printcolor4_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor3_pacolorschema" FOREIGN KEY ("ad_printcolor3_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor2_pacolorschema" FOREIGN KEY ("ad_printcolor2_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "aduser_pagoal" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "pacolorschema_pagoal" FOREIGN KEY ("pa_colorschema_id") REFERENCES "pa_colorschema" ("pa_colorschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "pagoalparent_pagoal" FOREIGN KEY ("pa_goalparent_id") REFERENCES "pa_goal" ("pa_goal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "pameasure_pagoal" FOREIGN KEY ("pa_measure_id") REFERENCES "pa_measure" ("pa_measure_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "adorg_pagoalrestriction" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "adorg2_pagoalrestriction" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "cbpartner_pagoalrestriction" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "cbpgroup_pagoalrestriction" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "mproduct_pagoalrestriction" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "mproductcat_pagoalrestriction" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "pagoal_pagoalrestriction" FOREIGN KEY ("pa_goal_id") REFERENCES "pa_goal" ("pa_goal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeaccount_pahierarchy" FOREIGN KEY ("ad_tree_account_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeactivity_pahierarchy" FOREIGN KEY ("ad_tree_activity_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreebpartner_pahierarchy" FOREIGN KEY ("ad_tree_bpartner_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreesr_pahierarchy" FOREIGN KEY ("ad_tree_salesregion_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeorg_pahierarchy" FOREIGN KEY ("ad_tree_org_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeproduct_pahierarchy" FOREIGN KEY ("ad_tree_product_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeproject_pahierarchy" FOREIGN KEY ("ad_tree_project_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreecampaign_pahierarchy" FOREIGN KEY ("ad_tree_campaign_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_measure"
    ADD CONSTRAINT "pabenchmark_pameasure" FOREIGN KEY ("pa_benchmark_id") REFERENCES "pa_benchmark" ("pa_benchmark_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_measure"
    ADD CONSTRAINT "pahierarchy_pameasure" FOREIGN KEY ("pa_hierarchy_id") REFERENCES "pa_hierarchy" ("pa_hierarchy_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_measure"
    ADD CONSTRAINT "pameasurecalc_pameasure" FOREIGN KEY ("pa_measurecalc_id") REFERENCES "pa_measurecalc" ("pa_measurecalc_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_measure"
    ADD CONSTRAINT "paratio_pameasure" FOREIGN KEY ("pa_ratio_id") REFERENCES "pa_ratio" ("pa_ratio_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_ratio"
    ADD CONSTRAINT "cacctschema_paratio" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_ratioelement"
    ADD CONSTRAINT "celementvalue_paratioelement" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_ratioelement"
    ADD CONSTRAINT "pameasurecalc_paratioelement" FOREIGN KEY ("pa_measurecalc_id") REFERENCES "pa_measurecalc" ("pa_measurecalc_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_ratioelement"
    ADD CONSTRAINT "paratio_paratioelement" FOREIGN KEY ("pa_ratio_id") REFERENCES "pa_ratio" ("pa_ratio_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_ratioelement"
    ADD CONSTRAINT "paratioused_paratioelement" FOREIGN KEY ("pa_ratioused_id") REFERENCES "pa_ratio" ("pa_ratio_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "adorg_pareport" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "adprintformat_pareport" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "cacctschema_pareport" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "ccalendar_pareport" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "pareport_columnset" FOREIGN KEY ("pa_reportcolumnset_id") REFERENCES "pa_reportcolumnset" ("pa_reportcolumnset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "pareport_lineset" FOREIGN KEY ("pa_reportlineset_id") REFERENCES "pa_reportlineset" ("pa_reportlineset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "adorg_pareportcolumn" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "cactivity_pareportcolumn" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "cbpartner_pareportcolumn" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "ccampaign_pareportcolumn" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "ccurrency_pareportcolumn" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "celementvalue_pareportcolumn" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "clocation_pareportcolumn" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "cproject_pareportcolumn" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "csalesregion_pareportcolumn" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "glbudget_pareportcolumn" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "mproduct_pareportcolumn" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "pareportcolumn_oper1" FOREIGN KEY ("oper_1_id") REFERENCES "pa_reportcolumn" ("pa_reportcolumn_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "pareportcolumn_oper2" FOREIGN KEY ("oper_2_id") REFERENCES "pa_reportcolumn" ("pa_reportcolumn_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "pareportcolumnset_column" FOREIGN KEY ("pa_reportcolumnset_id") REFERENCES "pa_reportcolumnset" ("pa_reportcolumnset_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "glbudget_pareportline" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportline_oper2" FOREIGN KEY ("oper_2_id") REFERENCES "pa_reportline" ("pa_reportline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportline_oper1" FOREIGN KEY ("oper_1_id") REFERENCES "pa_reportline" ("pa_reportline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportline_parent" FOREIGN KEY ("parent_id") REFERENCES "pa_reportline" ("pa_reportline_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportlineset_line" FOREIGN KEY ("pa_reportlineset_id") REFERENCES "pa_reportlineset" ("pa_reportlineset_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "adorg_pareportsource" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "cactivity_pareportsource" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "cbpartner_pareportsource" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "ccampaign_pareportsource" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "celementvalue_pareportsource" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "clocation_pareportsource" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "cproject_pareportsource" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "csalesregion_pareportsource" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "mproduct_pareportsource" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportsource"
    ADD CONSTRAINT "pareportline_pareportsource" FOREIGN KEY ("pa_reportline_id") REFERENCES "pa_reportline" ("pa_reportline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_sla_goal"
    ADD CONSTRAINT "cbpartner_paslagoal" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_sla_goal"
    ADD CONSTRAINT "paslacriteria_paslagoal" FOREIGN KEY ("pa_sla_criteria_id") REFERENCES "pa_sla_criteria" ("pa_sla_criteria_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_sla_measure"
    ADD CONSTRAINT "adtable_paslameasure" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_sla_measure"
    ADD CONSTRAINT "paslagoal_paslameasure" FOREIGN KEY ("pa_sla_goal_id") REFERENCES "pa_sla_goal" ("pa_sla_goal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_categoryupdates"
    ADD CONSTRAINT "aduser_rcategoryupdates" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_categoryupdates"
    ADD CONSTRAINT "rcategory_rcategoryupdates" FOREIGN KEY ("r_category_id") REFERENCES "r_category" ("r_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_contactinterest"
    ADD CONSTRAINT "aduser_rcontactinterest" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_contactinterest"
    ADD CONSTRAINT "rinterestarea_rcontactinterest" FOREIGN KEY ("r_interestarea_id") REFERENCES "r_interestarea" ("r_interestarea_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_groupupdates"
    ADD CONSTRAINT "aduser_ruserupdates" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_groupupdates"
    ADD CONSTRAINT "rgroup_rgroupupdates" FOREIGN KEY ("r_group_id") REFERENCES "r_group" ("r_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issueknown"
    ADD CONSTRAINT "rissuestatus_rissueknown" FOREIGN KEY ("r_issuestatus_id") REFERENCES "r_issuestatus" ("r_issuestatus_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issueknown"
    ADD CONSTRAINT "rrequest_rissueknown" FOREIGN KEY ("r_request_id") REFERENCES "r_request" ("r_request_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issueproject"
    ADD CONSTRAINT "aasset_rissueproject" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issueproject"
    ADD CONSTRAINT "cproject_rissueproject" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issuesource"
    ADD CONSTRAINT "rissueproject_rissuesource" FOREIGN KEY ("r_issueproject_id") REFERENCES "r_issueproject" ("r_issueproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issuesource"
    ADD CONSTRAINT "rissuesystem_rissuesource" FOREIGN KEY ("r_issuesystem_id") REFERENCES "r_issuesystem" ("r_issuesystem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issuesource"
    ADD CONSTRAINT "rissueuser_rissuesource" FOREIGN KEY ("r_issueuser_id") REFERENCES "r_issueuser" ("r_issueuser_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_issueuser"
    ADD CONSTRAINT "aduser_rissueuser" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_mailtext_trl"
    ADD CONSTRAINT "adlanguage_rmailtexttrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_mailtext_trl"
    ADD CONSTRAINT "rmailtext_rmailtexttrl" FOREIGN KEY ("r_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "adrole_rrequest" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "adtable_rrequest" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "adusersr_rrequest" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "aduser_rrequest" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "aasset_rrequest" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "cactivity_rrequest" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "c_bpartner_rrequest" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "ccampaign_rrequest" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rinvoice_rrequest" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "cinvoicerequest_rrequest" FOREIGN KEY ("c_invoicerequest_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "corder_rrequest" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "cpayment_rrequest" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "cproject_rrequest" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "mfixchangenotice_rrequest" FOREIGN KEY ("m_fixchangenotice_id") REFERENCES "m_changenotice" ("m_changenotice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "minout_rrequest" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "mproductspent_rrequest" FOREIGN KEY ("m_productspent_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "mproduct_rrequest" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "mrma_rrequest" FOREIGN KEY ("m_rma_id") REFERENCES "m_rma" ("m_rma_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rcategory_rrequest" FOREIGN KEY ("r_category_id") REFERENCES "r_category" ("r_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rgroup_rrequest" FOREIGN KEY ("r_group_id") REFERENCES "r_group" ("r_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rmailtext_rrequest" FOREIGN KEY ("r_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rrequest_related" FOREIGN KEY ("r_requestrelated_id") REFERENCES "r_request" ("r_request_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rrequesttype_rrequest" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rresolution_rrequest" FOREIGN KEY ("r_resolution_id") REFERENCES "r_resolution" ("r_resolution_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rstandardresponse_rrequest" FOREIGN KEY ("r_standardresponse_id") REFERENCES "r_standardresponse" ("r_standardresponse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_request"
    ADD CONSTRAINT "rstatus_rrequest" FOREIGN KEY ("r_status_id") REFERENCES "r_status" ("r_status_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "adrole_rrequestaction" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "adusersr_rrequestaction" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "aduser_rrequestaction" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "aasset_rrequestaction" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "cactivity_rrequestaction" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "cbpartner_rrequestaction" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "cinvoice_rrequestaction" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "corder_rrequestaction" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "cpayment_rrequestaction" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "cproject_rrequestaction" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "minout_mrequestaction" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "mproduct_rrequestaction" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "mrma_rrequestaction" FOREIGN KEY ("m_rma_id") REFERENCES "m_rma" ("m_rma_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "rcategory_rrequestaction" FOREIGN KEY ("r_category_id") REFERENCES "r_category" ("r_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "rgroup_rrequestaction" FOREIGN KEY ("r_group_id") REFERENCES "r_group" ("r_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "rrequest_rrequestaction" FOREIGN KEY ("r_request_id") REFERENCES "r_request" ("r_request_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "rrequesttype_rrequestaction" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "rresolution_rrequestaction" FOREIGN KEY ("r_resolution_id") REFERENCES "r_resolution" ("r_resolution_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "rstatus_rrequestaction" FOREIGN KEY ("r_status_id") REFERENCES "r_status" ("r_status_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestprocessor"
    ADD CONSTRAINT "aduser_rrequestprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestprocessorlog"
    ADD CONSTRAINT "rrequestprocessor_log" FOREIGN KEY ("r_requestprocessor_id") REFERENCES "r_requestprocessor" ("r_requestprocessor_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestprocessor_route"
    ADD CONSTRAINT "aduser_rrequestprocessorroute" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestprocessor_route"
    ADD CONSTRAINT "rrequestprocessor_route" FOREIGN KEY ("r_requestprocessor_id") REFERENCES "r_requestprocessor" ("r_requestprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestprocessor_route"
    ADD CONSTRAINT "rrequesttype_rprocessorrule" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requesttypeupdates"
    ADD CONSTRAINT "aduser_rrequesttypeupdates" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requesttypeupdates"
    ADD CONSTRAINT "rrequesttype_rrtupdates" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestupdate"
    ADD CONSTRAINT "rrequest_rrequestupdate" FOREIGN KEY ("r_request_id") REFERENCES "r_request" ("r_request_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestupdates"
    ADD CONSTRAINT "aduser_rrequestupdates" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestupdates"
    ADD CONSTRAINT "rrequest_rrupdates" FOREIGN KEY ("r_request_id") REFERENCES "r_request" ("r_request_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_expensetype"
    ADD CONSTRAINT "ctaxcategory_sexpensetype" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_expensetype"
    ADD CONSTRAINT "cuom_sexpensetype" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_expensetype"
    ADD CONSTRAINT "mproductcategory_sexpensetype" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resource"
    ADD CONSTRAINT "aduser_sresource" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resource"
    ADD CONSTRAINT "mwarehouse_sresource" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resource"
    ADD CONSTRAINT "sresourcetype_sresource" FOREIGN KEY ("s_resourcetype_id") REFERENCES "s_resourcetype" ("s_resourcetype_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourceassignment"
    ADD CONSTRAINT "sresource_sresourceassignment" FOREIGN KEY ("s_resource_id") REFERENCES "s_resource" ("s_resource_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourcetype"
    ADD CONSTRAINT "ctaxcategory_sresourcetype" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourcetype"
    ADD CONSTRAINT "cuom_sresourcetype" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourcetype"
    ADD CONSTRAINT "mprodcategory_sresourcetype" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourceunavailable"
    ADD CONSTRAINT "sresource_sresunavailable" FOREIGN KEY ("s_resource_id") REFERENCES "s_resource" ("s_resource_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpense"
    ADD CONSTRAINT "cbpartner_stimeexpense" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpense"
    ADD CONSTRAINT "mpricelist_stimeexpense" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpense"
    ADD CONSTRAINT "mwarehouse_stimeexpense" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cactivity_stimeexpenseline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cbpartner_stimeexpenseline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "ccampaign_stimeexpenseline" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "ccurrency_stimeexpenseline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cinvoiceline_stimeexpenseline" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "corderline_stimeexpenseline" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cproject_stimeexpenseline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cprojectphase_stimeexpenseline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cprojecttask_stimeexpenseline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "cuom_stimeexpenseline" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "mproduct_stimeexpenseline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "sresourceassign_steline" FOREIGN KEY ("s_resourceassignment_id") REFERENCES "s_resourceassignment" ("s_resourceassignment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "stimeexpense_line" FOREIGN KEY ("s_timeexpense_id") REFERENCES "s_timeexpense" ("s_timeexpense_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_timeexpenseline"
    ADD CONSTRAINT "stimetype_stimeexpenseline" FOREIGN KEY ("s_timetype_id") REFERENCES "s_timetype" ("s_timetype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_training"
    ADD CONSTRAINT "ctaxcategory_straining" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_training"
    ADD CONSTRAINT "cuom_straining" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_training"
    ADD CONSTRAINT "mproductcategory_straining" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_training_class"
    ADD CONSTRAINT "mproduct_strainingclass" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_training_class"
    ADD CONSTRAINT "straining_strainingclass" FOREIGN KEY ("s_training_id") REFERENCES "s_training" ("s_training_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "test"
    ADD CONSTRAINT "vc_account_test" FOREIGN KEY ("account_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "adpinstance_taging" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cactivity_taging" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cbpartner_taging" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cbpgroup_taging" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "ccampaign_taging" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "ccurrency_taging" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cproject_taging" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "cbpartner_tdrdetail" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "cbpartnerlocation_tdrdetail" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionlist_tdrdetail" FOREIGN KEY ("m_distributionlist_id") REFERENCES "m_distributionlist" ("m_distributionlist_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionlline_tdrdetail" FOREIGN KEY ("m_distributionlistline_id") REFERENCES "m_distributionlistline" ("m_distributionlistline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionrun_tdrdetail" FOREIGN KEY ("m_distributionrun_id") REFERENCES "m_distributionrun" ("m_distributionrun_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionrline_tdrdetail" FOREIGN KEY ("m_distributionrunline_id") REFERENCES "m_distributionrunline" ("m_distributionrunline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mproduct_tdrdetail" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "adpinstance_tinventoryvalue" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "ccurrency_tinventoryvalue" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "masi_tinventoryvalue" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mcostelement_tinventoryvalue" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mplversion_tinventoryvalue" FOREIGN KEY ("m_pricelist_version_id") REFERENCES "m_pricelist_version" ("m_pricelist_version_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mproduct_tinventoryvalue" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mwarehouse_tinventoryvalue" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "adpinstance_tinvoicegl" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "cconversiontype_tinvoicegl" FOREIGN KEY ("c_conversiontypereval_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "cdoctype_tinvoicegl" FOREIGN KEY ("c_doctypereval_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "cinvoice_tinvoicegl" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "factacct_tinvoicegl" FOREIGN KEY ("fact_acct_id") REFERENCES "fact_acct" ("fact_acct_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "adpinstance_treplenish" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "cdoctype_treplenish" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "mproduct_treplenish" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "mwarehousesource_treplenish" FOREIGN KEY ("m_warehousesource_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE SET NULL DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "mwarehouse_treplenish" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_report"
    ADD CONSTRAINT "adpinstance_treport" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_report"
    ADD CONSTRAINT "pareportline_treport" FOREIGN KEY ("pa_reportline_id") REFERENCES "pa_reportline" ("pa_reportline_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_reportstatement"
    ADD CONSTRAINT "adpinstance_treportstatement" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_spool"
    ADD CONSTRAINT "adpinstance_tspool" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "adpinstance_ttransaction" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "cprojectissue_ttransaction" FOREIGN KEY ("c_projectissue_id") REFERENCES "c_projectissue" ("c_projectissue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "masi_ttransaction" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "minoutline_ttransaction" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "minventoryline_ttransaction" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "mlocator_ttransaction" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "mmovementline_ttransaction" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "mproduct_ttransaction" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "mproductionline_ttransaction" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "mtransaction_ttransaction" FOREIGN KEY ("m_transaction_id") REFERENCES "m_transaction" ("m_transaction_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_trialbalance"
    ADD CONSTRAINT "ad_pinstance_t_trialbalance" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_advertisement"
    ADD CONSTRAINT "aduser_wadvertisement" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_advertisement"
    ADD CONSTRAINT "cbpartner_wadvertisement" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_advertisement"
    ADD CONSTRAINT "wclickcount_wadvertisement" FOREIGN KEY ("w_clickcount_id") REFERENCES "w_clickcount" ("w_clickcount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_advertisement"
    ADD CONSTRAINT "wcountercount_wadvertisement" FOREIGN KEY ("w_countercount_id") REFERENCES "w_countercount" ("w_countercount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_basket"
    ADD CONSTRAINT "aduser_wbasket" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_basket"
    ADD CONSTRAINT "cbpartner_wbasket" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_basket"
    ADD CONSTRAINT "mpricelist_wbasket" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_basketline"
    ADD CONSTRAINT "mproduct_wbasketline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_basketline"
    ADD CONSTRAINT "wbasket_wbasketline" FOREIGN KEY ("w_basket_id") REFERENCES "w_basket" ("w_basket_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_click"
    ADD CONSTRAINT "aduser_wclick" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_click"
    ADD CONSTRAINT "wclickcount_wclick" FOREIGN KEY ("w_clickcount_id") REFERENCES "w_clickcount" ("w_clickcount_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_clickcount"
    ADD CONSTRAINT "cbpartner_wclickcount" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_counter"
    ADD CONSTRAINT "aduser_wcounter" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_counter"
    ADD CONSTRAINT "wcountercount_wcounter" FOREIGN KEY ("w_countercount_id") REFERENCES "w_countercount" ("w_countercount_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_countercount"
    ADD CONSTRAINT "cbpartner_wcountercount" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_mailmsg"
    ADD CONSTRAINT "wstore_wmailmsg" FOREIGN KEY ("w_store_id") REFERENCES "w_store" ("w_store_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_mailmsg_trl"
    ADD CONSTRAINT "adlanguage_wmailmsgtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_mailmsg_trl"
    ADD CONSTRAINT "wmailmsg_wmailmsgtrl" FOREIGN KEY ("w_mailmsg_id") REFERENCES "w_mailmsg" ("w_mailmsg_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_store"
    ADD CONSTRAINT "adclient_wstore" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_store_trl"
    ADD CONSTRAINT "wstoretrl_adlangauge" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_store_trl"
    ADD CONSTRAINT "wstore_wstoretrl" FOREIGN KEY ("w_store_id") REFERENCES "w_store" ("w_store_id") ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

