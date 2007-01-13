ALTER TABLE "ad_accesslog"
    ADD CONSTRAINT "adcolumn_adaccesslog" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_accesslog"
    ADD CONSTRAINT "adtable_adacceslog" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alert"
    ADD CONSTRAINT "calertprocessor_adalert" FOREIGN KEY ("ad_alertprocessor_id") REFERENCES "ad_alertprocessor" ("ad_alertprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertprocessor"
    ADD CONSTRAINT "aduser_calertprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_alertprocessorlog"
    ADD CONSTRAINT "calertprocessor_log" FOREIGN KEY ("ad_alertprocessor_id") REFERENCES "ad_alertprocessor" ("ad_alertprocessor_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adtable_adattribute" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute"
    ADD CONSTRAINT "advalrule_adattribute" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_attribute_value"
    ADD CONSTRAINT "adattribute_adattributevalue" FOREIGN KEY ("ad_attribute_id") REFERENCES "ad_attribute" ("ad_attribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_changelog"
    ADD CONSTRAINT "adcolumn_adchangelog" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_changelog"
    ADD CONSTRAINT "adsession_adchangelog" FOREIGN KEY ("ad_session_id") REFERENCES "ad_session" ("ad_session_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_changelog"
    ADD CONSTRAINT "adtable_adchangelog" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adclient_adclientinfo" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreeproduct_adclientinfo" FOREIGN KEY ("ad_tree_product_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreeorg_adclientinfo" FOREIGN KEY ("ad_tree_org_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreemenu_adclientinfo" FOREIGN KEY ("ad_tree_menu_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreesalesreg_adclientinfo" FOREIGN KEY ("ad_tree_salesregion_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_clientinfo"
    ADD CONSTRAINT "adtreeproject_adclientinfo" FOREIGN KEY ("ad_tree_project_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adprocess_adcolumn" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_reference_columndatatype" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_reference_columnvalue" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_table_column" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column"
    ADD CONSTRAINT "ad_valrule_column" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_access"
    ADD CONSTRAINT "adcolumn_adcolumnaccess" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_access"
    ADD CONSTRAINT "adrole_adcolumnaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_access"
    ADD CONSTRAINT "adtable_adcolumnaccess" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_column_trl"
    ADD CONSTRAINT "adcolumn_adcolumntrl" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adelement_adelementtrl" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ad_tab_field" FOREIGN KEY ("ad_tab_id") REFERENCES "ad_tab" ("ad_tab_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_fieldgroup_trl"
    ADD CONSTRAINT "adfieldgroup_trl" FOREIGN KEY ("ad_fieldgroup_id") REFERENCES "ad_fieldgroup" ("ad_fieldgroup_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_fieldgroup_trl"
    ADD CONSTRAINT "adlanguage_adfieldgrouptrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field_trl"
    ADD CONSTRAINT "ad_fieldtrl" FOREIGN KEY ("ad_field_id") REFERENCES "ad_field" ("ad_field_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_field_trl"
    ADD CONSTRAINT "ad_language_fieldtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_find"
    ADD CONSTRAINT "adcolumn_adfind" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_access"
    ADD CONSTRAINT "adform_adformaccess" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_access"
    ADD CONSTRAINT "adrole_adformaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_trl"
    ADD CONSTRAINT "adform_adformtrl" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_form_trl"
    ADD CONSTRAINT "adlanguage_adformtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_impformat"
    ADD CONSTRAINT "adtable_adimpformat" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_impformat_row"
    ADD CONSTRAINT "adcolumn_adimpformatrow" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_impformat_row"
    ADD CONSTRAINT "adimpformat_adimpformatrow" FOREIGN KEY ("ad_impformat_id") REFERENCES "ad_impformat" ("ad_impformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn"
    ADD CONSTRAINT "adelement_adinfocolumn" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn"
    ADD CONSTRAINT "adinfowindow_adinfocolumn" FOREIGN KEY ("ad_infowindow_id") REFERENCES "ad_infowindow" ("ad_infowindow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn"
    ADD CONSTRAINT "adreference_adinfocolumn" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn_trl"
    ADD CONSTRAINT "adinfocolumn_adinfocolumntrl" FOREIGN KEY ("ad_infocolumn_id") REFERENCES "ad_infocolumn" ("ad_infocolumn_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infocolumn_trl"
    ADD CONSTRAINT "adlanguage_adinfocolumntrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infowindow"
    ADD CONSTRAINT "adtable_adinfowindow" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infowindow_trl"
    ADD CONSTRAINT "adinfowindow_adinfowindowtrl" FOREIGN KEY ("ad_infowindow_id") REFERENCES "ad_infowindow" ("ad_infowindow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_infowindow_trl"
    ADD CONSTRAINT "adlanguage_adinfowindowtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "adform_adissue" FOREIGN KEY ("ad_form_id") REFERENCES "ad_form" ("ad_form_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "adprocess_adissue" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "adwindow_adissue" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "aasset_adissue" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rknownissue_adissue" FOREIGN KEY ("r_issueknown_id") REFERENCES "r_issueknown" ("r_issueknown_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rissueproject_adissue" FOREIGN KEY ("r_issueproject_id") REFERENCES "r_issueproject" ("r_issueproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rissuesystem_ad_issue" FOREIGN KEY ("r_issuesystem_id") REFERENCES "r_issuesystem" ("r_issuesystem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rissueuser_adissue" FOREIGN KEY ("r_issueuser_id") REFERENCES "r_issueuser" ("r_issueuser_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_issue"
    ADD CONSTRAINT "rrequest_adissue" FOREIGN KEY ("r_request_id") REFERENCES "r_request" ("r_request_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ad_menutrl" FOREIGN KEY ("ad_menu_id") REFERENCES "ad_menu" ("ad_menu_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message"
    ADD CONSTRAINT "messageclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message"
    ADD CONSTRAINT "messageorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message_trl"
    ADD CONSTRAINT "ad_language_messagetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_message_trl"
    ADD CONSTRAINT "ad_messagetrl" FOREIGN KEY ("ad_message_id") REFERENCES "ad_message" ("ad_message_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorgparent_adorginfo" FOREIGN KEY ("parent_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "adorg_adorginfo" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "adorgtype_adorginfo" FOREIGN KEY ("ad_orgtype_id") REFERENCES "ad_orgtype" ("ad_orgtype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "aduser_adorginfo" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "c_location_ad_orginfo" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_orginfo"
    ADD CONSTRAINT "mwarehouse_adorginfo" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance"
    ADD CONSTRAINT "adprocess_adpinstance" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance"
    ADD CONSTRAINT "aduser_pinstance" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance_log"
    ADD CONSTRAINT "adpinstance_pilog" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_pinstance_para"
    ADD CONSTRAINT "adpinstance_adpinstancepara" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adprintformat_formproject" FOREIGN KEY ("project_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formorder" FOREIGN KEY ("order_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_forminvoice" FOREIGN KEY ("invoice_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formremittance" FOREIGN KEY ("remittance_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "adprintformat_formshipment" FOREIGN KEY ("shipment_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_shipadprintform" FOREIGN KEY ("shipment_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_remitadprintform" FOREIGN KEY ("remittance_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_projectadprintform" FOREIGN KEY ("project_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_orderadprintform" FOREIGN KEY ("order_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printform"
    ADD CONSTRAINT "rmailtext_invoiceadprintform" FOREIGN KEY ("invoice_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adcolumn_adprintformatitem" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintcolor_adprintformatitem" FOREIGN KEY ("ad_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintfont_adprintformatitem" FOREIGN KEY ("ad_printfont_id") REFERENCES "ad_printfont" ("ad_printfont_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintformat_printformatitem" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintformat_printformatchild" FOREIGN KEY ("ad_printformatchild_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem"
    ADD CONSTRAINT "adprintgraph_printformatitem" FOREIGN KEY ("ad_printgraph_id") REFERENCES "ad_printgraph" ("ad_printgraph_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem_trl"
    ADD CONSTRAINT "adlanguage_adprintformitemtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printformatitem_trl"
    ADD CONSTRAINT "adprintformatitem_trl" FOREIGN KEY ("ad_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformat_adprintgraph" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdescr" FOREIGN KEY ("description_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata2" FOREIGN KEY ("data2_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata4" FOREIGN KEY ("data4_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata" FOREIGN KEY ("data_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printgraph"
    ADD CONSTRAINT "adprintformatitem_graphdata1" FOREIGN KEY ("data1_printformatitem_id") REFERENCES "ad_printformatitem" ("ad_printformatitem_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adprintcolor_tablehdrtextfg" FOREIGN KEY ("hdrtextfg_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_printtableformat"
    ADD CONSTRAINT "adprintcolor_tableline" FOREIGN KEY ("line_printcolor_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adprocess_adprocessaccess" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_access"
    ADD CONSTRAINT "adrole_adprocessaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adelement_adprocesspara" FOREIGN KEY ("ad_element_id") REFERENCES "ad_element" ("ad_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adprocess_adprocesspara" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adreferencevalue_adprocpara" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "adreference_adprocesspara" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para"
    ADD CONSTRAINT "advalrule_ad_processpara" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para_trl"
    ADD CONSTRAINT "adlanguage_adprocessparatrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_para_trl"
    ADD CONSTRAINT "adprocpara_adprocparatrl" FOREIGN KEY ("ad_process_para_id") REFERENCES "ad_process_para" ("ad_process_para_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_trl"
    ADD CONSTRAINT "ad_language_ad_process_trl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_process_trl"
    ADD CONSTRAINT "ad_process_ad_process_trl" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ad_referencetrl" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list"
    ADD CONSTRAINT "ad_reflist_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list"
    ADD CONSTRAINT "ad_reflist_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list"
    ADD CONSTRAINT "ad_reference_reflist" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list_trl"
    ADD CONSTRAINT "ad_language_reflisttrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_list_trl"
    ADD CONSTRAINT "ad_reflisttrl" FOREIGN KEY ("ad_ref_list_id") REFERENCES "ad_ref_list" ("ad_ref_list_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ref_tableclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ad_column_reftable_display" FOREIGN KEY ("ad_display") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ad_column_reftable_id" FOREIGN KEY ("ad_key") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ref_tableorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "ad_reference_reftable" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_ref_table"
    ADD CONSTRAINT "add_table_reftable" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_registration"
    ADD CONSTRAINT "adsystem_adregistration" FOREIGN KEY ("ad_system_id", "ad_client_id") REFERENCES "ad_system" ("ad_system_id", "ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication"
    ADD CONSTRAINT "adreplicationstrategy_adrep" FOREIGN KEY ("ad_replicationstrategy_id") REFERENCES "ad_replicationstrategy" ("ad_replicationstrategy_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replicationtable"
    ADD CONSTRAINT "adrepstrategy_adreptable" FOREIGN KEY ("ad_replicationstrategy_id") REFERENCES "ad_replicationstrategy" ("ad_replicationstrategy_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replicationtable"
    ADD CONSTRAINT "adtable_adreplicationtable" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication_log"
    ADD CONSTRAINT "adreptable_adreplog" FOREIGN KEY ("ad_replicationtable_id") REFERENCES "ad_replicationtable" ("ad_replicationtable_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication_log"
    ADD CONSTRAINT "adreplicationrun_adreplog" FOREIGN KEY ("ad_replication_run_id") REFERENCES "ad_replication_run" ("ad_replication_run_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_replication_run"
    ADD CONSTRAINT "adreplication_adreprun" FOREIGN KEY ("ad_replication_id") REFERENCES "ad_replication" ("ad_replication_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_reportview"
    ADD CONSTRAINT "adtable_adreportview" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorg_adroleorgaccess" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_role_orgaccess"
    ADD CONSTRAINT "adrole_adroleorgaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler"
    ADD CONSTRAINT "adprocess_adscheduler" FOREIGN KEY ("ad_process_id") REFERENCES "ad_process" ("ad_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler"
    ADD CONSTRAINT "aduser_adscheduler" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerlog"
    ADD CONSTRAINT "adscheduler_log" FOREIGN KEY ("ad_scheduler_id") REFERENCES "ad_scheduler" ("ad_scheduler_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerrecipient"
    ADD CONSTRAINT "adrole_adschedulerrecipient" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerrecipient"
    ADD CONSTRAINT "adscheduler_recipient" FOREIGN KEY ("ad_scheduler_id") REFERENCES "ad_scheduler" ("ad_scheduler_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_schedulerrecipient"
    ADD CONSTRAINT "aduser_adschedulerrecipient" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler_para"
    ADD CONSTRAINT "adprocesspara_adschedulerpara" FOREIGN KEY ("ad_process_para_id") REFERENCES "ad_process_para" ("ad_process_para_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_scheduler_para"
    ADD CONSTRAINT "adscheduler_adschedulerpara" FOREIGN KEY ("ad_scheduler_id") REFERENCES "ad_scheduler" ("ad_scheduler_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence"
    ADD CONSTRAINT "sequenceclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence"
    ADD CONSTRAINT "sequenceorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "sequence_auditclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "sequence_auditorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "ad_sequence_sequenceaudit" FOREIGN KEY ("ad_sequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_audit"
    ADD CONSTRAINT "adtable_adsequenceaudit" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_no"
    ADD CONSTRAINT "sequence_noclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_no"
    ADD CONSTRAINT "sequence_noorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_sequence_no"
    ADD CONSTRAINT "ad_sequence_sequenceno" FOREIGN KEY ("ad_sequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "tabclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adcolumn_adtabsortorder" FOREIGN KEY ("ad_columnsortorder_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab"
    ADD CONSTRAINT "adcolumn_adtabsortyesno" FOREIGN KEY ("ad_columnsortyesno_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ad_window_tab" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "tableclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "tableorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "ad_valrule_table" FOREIGN KEY ("ad_val_rule_id") REFERENCES "ad_val_rule" ("ad_val_rule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "adwindowpo_adtable" FOREIGN KEY ("po_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table"
    ADD CONSTRAINT "ad_window_table" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "ad_dataaccessclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "ad_dataaccessorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "adrole_adtableaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_access"
    ADD CONSTRAINT "adtable_adtableaccess" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_trl"
    ADD CONSTRAINT "adlanguage_adtabletrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_table_trl"
    ADD CONSTRAINT "adtable_adtabletrl" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab_trl"
    ADD CONSTRAINT "ad_language_tabtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_tab_trl"
    ADD CONSTRAINT "ad_tabtrl" FOREIGN KEY ("ad_tab_id") REFERENCES "ad_tab" ("ad_tab_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task"
    ADD CONSTRAINT "taskclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task"
    ADD CONSTRAINT "taskorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_taskinstance"
    ADD CONSTRAINT "taskinstanceclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_taskinstance"
    ADD CONSTRAINT "taskinstanceorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_taskinstance"
    ADD CONSTRAINT "ad_task_taskinstance" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "ad_taskaccess_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "ad_taskaccess_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "adrole_adtaskaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_access"
    ADD CONSTRAINT "adtask_adtaskaccess" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_trl"
    ADD CONSTRAINT "ad_language_tasktrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_task_trl"
    ADD CONSTRAINT "ad_tasktrl" FOREIGN KEY ("ad_task_id") REFERENCES "ad_task" ("ad_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treebar"
    ADD CONSTRAINT "adtree_adtreebar" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treebar"
    ADD CONSTRAINT "aduser_adtreebar" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenode"
    ADD CONSTRAINT "adtree_adtreenode" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodebp"
    ADD CONSTRAINT "adtree_adtreenodebp" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecmc"
    ADD CONSTRAINT "adtree_adtreenodecmc" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecmm"
    ADD CONSTRAINT "adtree_adtreenodecmm" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecms"
    ADD CONSTRAINT "adtree_adtreenodecms" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodecmt"
    ADD CONSTRAINT "adtree_adtreenodecmt" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodemm"
    ADD CONSTRAINT "adtree_adtreenodemm" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodepr"
    ADD CONSTRAINT "adtree_adtreenodepr" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu1"
    ADD CONSTRAINT "adtree_adtreenodeu1" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu2"
    ADD CONSTRAINT "adtree_adtreenodeu2" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu3"
    ADD CONSTRAINT "adtree_adtreenodeu3" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_treenodeu4"
    ADD CONSTRAINT "adtree_adtreenodeu4" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "ad_user_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "ad_user_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "adorgtrx_aduser" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "aduser_supervisor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "cbpartner_aduser" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "cbplocation_aduser" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user"
    ADD CONSTRAINT "cgreeting_aduser" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userbpaccess"
    ADD CONSTRAINT "aduser_aduserbpaccess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userbpaccess"
    ADD CONSTRAINT "rrequesttype_aduserbpaccess" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "aduser_adusermail" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_usermail"
    ADD CONSTRAINT "rmailtext_adusermail" FOREIGN KEY ("r_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_usermail"
    ADD CONSTRAINT "wmailmsg_adusermail" FOREIGN KEY ("w_mailmsg_id") REFERENCES "w_mailmsg" ("w_mailmsg_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userquery"
    ADD CONSTRAINT "adtable_aduserquery" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_userquery"
    ADD CONSTRAINT "aduser_aduserquery" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_orgaccess"
    ADD CONSTRAINT "adorg_aduserorgaccess" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_orgaccess"
    ADD CONSTRAINT "aduser_aduserorgaccess" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "ad_userrolesclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "ad_userrolesorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "adrole_aduserroles" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_roles"
    ADD CONSTRAINT "aduser_userroles" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_substitute"
    ADD CONSTRAINT "adusersub_ad_usersub" FOREIGN KEY ("substitute_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_user_substitute"
    ADD CONSTRAINT "aduser_adusersub" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_val_rule"
    ADD CONSTRAINT "val_ruleclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_val_rule"
    ADD CONSTRAINT "val_ruleorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "admessage_adwfactivity" FOREIGN KEY ("ad_message_id") REFERENCES "ad_message" ("ad_message_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "aduser_adwfactivity" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "adwfnode_adwfactivity" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "adwfprocess_adwfactivity" FOREIGN KEY ("ad_wf_process_id") REFERENCES "ad_wf_process" ("ad_wf_process_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activity"
    ADD CONSTRAINT "adwfresponsible_adwfactivity" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_activityresult"
    ADD CONSTRAINT "adwfactivity_adwfactresult" FOREIGN KEY ("ad_wf_activity_id") REFERENCES "ad_wf_activity" ("ad_wf_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_block"
    ADD CONSTRAINT "adworkflow_adwfblock" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adwfnodenext_adwfnextcond" FOREIGN KEY ("ad_wf_nodenext_id") REFERENCES "ad_wf_nodenext" ("ad_wf_nodenext_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adwfblock_adwfnode" FOREIGN KEY ("ad_wf_block_id") REFERENCES "ad_wf_block" ("ad_wf_block_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adwfresponsible_adwfnode" FOREIGN KEY ("ad_wf_responsible_id") REFERENCES "ad_wf_responsible" ("ad_wf_responsible_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adwindow_adwfnode" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adworkflow_adwfnode" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node"
    ADD CONSTRAINT "adworkflow_adwfnodesubflow" FOREIGN KEY ("workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "wf_nodenextclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "wf_nodenextorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "adwfnode_adwfnodenext" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_nodenext"
    ADD CONSTRAINT "adwfnodenext_adwfnodenext" FOREIGN KEY ("ad_wf_next_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_para"
    ADD CONSTRAINT "adprocesspara_adwfnodepara" FOREIGN KEY ("ad_process_para_id") REFERENCES "ad_process_para" ("ad_process_para_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_para"
    ADD CONSTRAINT "adwfnode_adwfnodepara" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_trl"
    ADD CONSTRAINT "ad_language_wfnodetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_node_trl"
    ADD CONSTRAINT "ad_wfnodetrl" FOREIGN KEY ("ad_wf_node_id") REFERENCES "ad_wf_node" ("ad_wf_node_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adworkflow_adwfprocess" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_wf_processdata"
    ADD CONSTRAINT "adwfproccess_adwfprocessdata" FOREIGN KEY ("ad_wf_process_id") REFERENCES "ad_wf_process" ("ad_wf_process_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adrole_adwindowaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_access"
    ADD CONSTRAINT "adwindow_adwindowaccess" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_trl"
    ADD CONSTRAINT "ad_language_windowtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_window_trl"
    ADD CONSTRAINT "ad_windowtrl" FOREIGN KEY ("ad_window_id") REFERENCES "ad_window" ("ad_window_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adworkflowprocessor_adwf" FOREIGN KEY ("ad_workflowprocessor_id") REFERENCES "ad_workflowprocessor" ("ad_workflowprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflowprocessor"
    ADD CONSTRAINT "aduser_adworkflowprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflowprocessorlog"
    ADD CONSTRAINT "adworkflowprocessor_log" FOREIGN KEY ("ad_workflowprocessor_id") REFERENCES "ad_workflowprocessor" ("ad_workflowprocessor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "ad_workflowaccess_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "ad_workflowaccess_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "adrole_adworkflowaccess" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_access"
    ADD CONSTRAINT "adworkfow_workflowaccess" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_trl"
    ADD CONSTRAINT "ad_language_workflowtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "ad_workflow_trl"
    ADD CONSTRAINT "ad_workflowtrl" FOREIGN KEY ("ad_workflow_id") REFERENCES "ad_workflow" ("ad_workflow_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "moutline_aassetdelivery" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adreferencevalue_aregattribute" FOREIGN KEY ("ad_reference_value_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationattribute"
    ADD CONSTRAINT "adreference_aregattribute" FOREIGN KEY ("ad_reference_id") REFERENCES "ad_reference" ("ad_reference_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationproduct"
    ADD CONSTRAINT "aregattribute_aregproduct" FOREIGN KEY ("a_registrationattribute_id") REFERENCES "a_registrationattribute" ("a_registrationattribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationproduct"
    ADD CONSTRAINT "mproduct_aregproduct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationvalue"
    ADD CONSTRAINT "aregistration_aregvalue" FOREIGN KEY ("a_registration_id") REFERENCES "a_registration" ("a_registration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "a_registrationvalue"
    ADD CONSTRAINT "aregattribute_aregvalue" FOREIGN KEY ("a_registrationattribute_id") REFERENCES "a_registrationattribute" ("a_registrationattribute_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mproduct_btopictype" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "b_topictype"
    ADD CONSTRAINT "mproduct_btopictypemember" FOREIGN KEY ("m_productmember_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesscontainer"
    ADD CONSTRAINT "cmaccessprofile_cmacccontainer" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesscontainer"
    ADD CONSTRAINT "cmcontainer_cmaccesscontainer" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistbpgroup"
    ADD CONSTRAINT "cmaccessprofile_cmalbpgroup" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistbpgroup"
    ADD CONSTRAINT "cbpgrpup_cmalistbpgroup" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistrole"
    ADD CONSTRAINT "adrole_cmalistrole" FOREIGN KEY ("ad_role_id") REFERENCES "ad_role" ("ad_role_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accesslistrole"
    ADD CONSTRAINT "cmaccessprofile_cmalistrole" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessmedia"
    ADD CONSTRAINT "cmaccessprofile_cmaccessmedia" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessmedia"
    ADD CONSTRAINT "cmmedia_cmaccessmedia" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessnewschannel"
    ADD CONSTRAINT "cmaccesprofile_cmanewschannel" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessnewschannel"
    ADD CONSTRAINT "cnmewschannel_cmaccessnewsc" FOREIGN KEY ("cm_newschannel_id") REFERENCES "cm_newschannel" ("cm_newschannel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessstage"
    ADD CONSTRAINT "cmaccessprofile_cmaccessstage" FOREIGN KEY ("cm_accessprofile_id") REFERENCES "cm_accessprofile" ("cm_accessprofile_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_accessstage"
    ADD CONSTRAINT "cmcstage_cmaccessstage" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_ad"
    ADD CONSTRAINT "cmadcat_cmad" FOREIGN KEY ("cm_ad_cat_id") REFERENCES "cm_ad_cat" ("cm_ad_cat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_ad"
    ADD CONSTRAINT "cmmedia_cmad" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_ad_cat"
    ADD CONSTRAINT "cmwebproject_cmadcat" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_broadcastserver"
    ADD CONSTRAINT "cmwebproject_cmbroadcastserver" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chat"
    ADD CONSTRAINT "adtable_cmchat" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chat"
    ADD CONSTRAINT "cmchattype_cmchat" FOREIGN KEY ("cm_chattype_id") REFERENCES "cm_chattype" ("cm_chattype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "aduser_cmchatentry" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "cmchat_chchatentry" FOREIGN KEY ("cm_chat_id") REFERENCES "cm_chat" ("cm_chat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "cmchatentry_grandparent" FOREIGN KEY ("cm_chatentrygrandparent_id") REFERENCES "cm_chatentry" ("cm_chatentry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatentry"
    ADD CONSTRAINT "cmentrty_cmentryparent" FOREIGN KEY ("cm_chatentryparent_id") REFERENCES "cm_chatentry" ("cm_chatentry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chattype"
    ADD CONSTRAINT "adtable_cmchattype" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chattypeupdate"
    ADD CONSTRAINT "aduser_cmchattypeupdate" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chattypeupdate"
    ADD CONSTRAINT "cmchattype_cmchattypeupdate" FOREIGN KEY ("cm_chattype_id") REFERENCES "cm_chattype" ("cm_chattype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatupdate"
    ADD CONSTRAINT "aduser_cmchatupdate" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_chatupdate"
    ADD CONSTRAINT "cmchat_cmchatupdate" FOREIGN KEY ("cm_chat_id") REFERENCES "cm_chat" ("cm_chat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container"
    ADD CONSTRAINT "cmcontainer_cmcontainerlink" FOREIGN KEY ("cm_containerlink_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container"
    ADD CONSTRAINT "cmtemplate_cmcontainer" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container"
    ADD CONSTRAINT "cmwebproject_cmcontainer" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_containerttable"
    ADD CONSTRAINT "cmcontainer_cmcontainerttable" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_containerttable"
    ADD CONSTRAINT "cmttable_cmcontainertable" FOREIGN KEY ("cm_templatetable_id") REFERENCES "cm_templatetable" ("cm_templatetable_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_element"
    ADD CONSTRAINT "cmcontainer_cmcontainerelement" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_element_trl"
    ADD CONSTRAINT "adlanguage_cmcontainereletrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_element_trl"
    ADD CONSTRAINT "cmcontainerelement_cmcetrl" FOREIGN KEY ("cm_container_element_id") REFERENCES "cm_container_element" ("cm_container_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_trl"
    ADD CONSTRAINT "adlanguage_cmcontainertrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_trl"
    ADD CONSTRAINT "cmcontainer_cmcontainertrl" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_container_url"
    ADD CONSTRAINT "cmcontainer_cmcontainerurl" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage"
    ADD CONSTRAINT "cmcstage_cmcstagelink" FOREIGN KEY ("cm_cstagelink_id") REFERENCES "cm_cstage" ("cm_cstage_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage"
    ADD CONSTRAINT "cmtemplate_cmcstage" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage"
    ADD CONSTRAINT "cmwebproject_cmcstage" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstagettable"
    ADD CONSTRAINT "cmstage_cmcstagettable" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstagettable"
    ADD CONSTRAINT "cmttable_cmstagettable" FOREIGN KEY ("cm_templatetable_id") REFERENCES "cm_templatetable" ("cm_templatetable_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_element"
    ADD CONSTRAINT "cmcstage_cmcstageelement" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_element_trl"
    ADD CONSTRAINT "adlanguage_cmcstageeletrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_element_trl"
    ADD CONSTRAINT "cmcstageelement_cmcsetrl" FOREIGN KEY ("cm_cstage_element_id") REFERENCES "cm_cstage_element" ("cm_cstage_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_trl"
    ADD CONSTRAINT "adlanguage_cmcstagetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_cstage_trl"
    ADD CONSTRAINT "cmcstage_cmcstagetrl" FOREIGN KEY ("cm_cstage_id") REFERENCES "cm_cstage" ("cm_cstage_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_media"
    ADD CONSTRAINT "cmwebproject_cmmedia" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_mediadeploy"
    ADD CONSTRAINT "cmmedia_cmmediadeploy" FOREIGN KEY ("cm_media_id") REFERENCES "cm_media" ("cm_media_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_mediadeploy"
    ADD CONSTRAINT "cmmediaserver_cmmediadeploy" FOREIGN KEY ("cm_media_server_id") REFERENCES "cm_media_server" ("cm_media_server_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_media_server"
    ADD CONSTRAINT "cmwebproject_cmmediaserver" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_newschannel"
    ADD CONSTRAINT "cmwebproject_cmnewschannel" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_newsitem"
    ADD CONSTRAINT "cmnewschannel_cmnewsitem" FOREIGN KEY ("cm_newschannel_id") REFERENCES "cm_newschannel" ("cm_newschannel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_template"
    ADD CONSTRAINT "cmwebproject_cmtemplate" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_templatetable"
    ADD CONSTRAINT "adtable_cmtemplatetable" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_templatetable"
    ADD CONSTRAINT "cmtemplate_cmttable" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_template_ad_cat"
    ADD CONSTRAINT "cmadcat_cmtemplateadcat" FOREIGN KEY ("cm_ad_cat_id") REFERENCES "cm_ad_cat" ("cm_ad_cat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_template_ad_cat"
    ADD CONSTRAINT "cmtemplate_cmtemplateadcat" FOREIGN KEY ("cm_template_id") REFERENCES "cm_template" ("cm_template_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adtreecmm_cmwebproject" FOREIGN KEY ("ad_treecmm_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject"
    ADD CONSTRAINT "adtreecms_cmwebproject" FOREIGN KEY ("ad_treecms_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject"
    ADD CONSTRAINT "adtreecmt_cmwebproject" FOREIGN KEY ("ad_treecmt_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject_domain"
    ADD CONSTRAINT "cmcontainer_cmwebprojectdomain" FOREIGN KEY ("cm_container_id") REFERENCES "cm_container" ("cm_container_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_webproject_domain"
    ADD CONSTRAINT "cmwebproject_cmwpdomain" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "cm_wikitoken"
    ADD CONSTRAINT "adtable_cmwikitoken" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctprocessor"
    ADD CONSTRAINT "aduser_cacctprocessor" FOREIGN KEY ("supervisor_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctprocessorlog"
    ADD CONSTRAINT "cacctprocessor_log" FOREIGN KEY ("c_acctprocessor_id") REFERENCES "c_acctprocessor" ("c_acctprocessor_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cacctschema_default" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

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

ALTER TABLE "c_acctschema_default"
    ADD CONSTRAINT "vc_realizedgain_cschemadefault" FOREIGN KEY ("realizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "adclient_caschemaelement" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "adorgid_c_aschemaelement" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "adorg_caschemaelement" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_element"
    ADD CONSTRAINT "cacctschema_caschemaelement" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cacctschema_cacctschemagl" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_intercompanyduefrom_cschema" FOREIGN KEY ("intercompanyduefrom_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_intercompanydueto_cschemagl" FOREIGN KEY ("intercompanydueto_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_ppvoffset_cschemagl" FOREIGN KEY ("ppvoffset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_suspensebalancing_cschemagl" FOREIGN KEY ("suspensebalancing_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_suspenseerror_cschemagl" FOREIGN KEY ("suspenseerror_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_currencybalancing_cschemagl" FOREIGN KEY ("currencybalancing_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_incomesummary_cschemagl" FOREIGN KEY ("incomesummary_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_acctschema_gl"
    ADD CONSTRAINT "vc_retainedearning_cschemagl" FOREIGN KEY ("retainedearning_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cbank_cbankaccount" FOREIGN KEY ("c_bank_id") REFERENCES "c_bank" ("c_bank_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount"
    ADD CONSTRAINT "ccurrency_cbankaccount" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccountdoc"
    ADD CONSTRAINT "adprintformat_cbankaccountdoc" FOREIGN KEY ("check_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccountdoc"
    ADD CONSTRAINT "cbankaccount_cbadoc" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "cacctschema_cbankaccountacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "cbankaccount_cbankacctacct" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bpaymentselect_cbankaccount" FOREIGN KEY ("b_paymentselect_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_brevaluationgain_cbankaccou" FOREIGN KEY ("b_revaluationgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_brevaluationloss_cbankaccou" FOREIGN KEY ("b_revaluationloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "vc_bintransit_cbankaccount" FOREIGN KEY ("b_intransit_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_binterestrev_cbankaccount" FOREIGN KEY ("b_interestrev_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankaccount_acct"
    ADD CONSTRAINT "vc_bsettlementgain_cbankaccoun" FOREIGN KEY ("b_settlementgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatement"
    ADD CONSTRAINT "cbankaccount_cbankstatement" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bankstatementline"
    ADD CONSTRAINT "cbstatement_cbstatementline" FOREIGN KEY ("c_bankstatement_id") REFERENCES "c_bankstatement" ("c_bankstatement_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adusersalesrep_cbpartner" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mdiscountspo_cbpartner" FOREIGN KEY ("po_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mdiscounts_cbpartner" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mpricelistpo_cbuspartner" FOREIGN KEY ("po_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner"
    ADD CONSTRAINT "mpricelist_cbpartner" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "c_buspartner_locationclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "c_buspartner_locationorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "cbpartner_cbplocation" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "clocation_cbplocation" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_location"
    ADD CONSTRAINT "csalesregion_bpartnerlocation" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_product"
    ADD CONSTRAINT "cbpartner_cbpproduct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bpartner_product"
    ADD CONSTRAINT "mproduct_cbpproduct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_bankaccount"
    ADD CONSTRAINT "aduser_cbpbankaccount" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_bankaccount"
    ADD CONSTRAINT "cbank_cbpbankaccount" FOREIGN KEY ("c_bank_id") REFERENCES "c_bank" ("c_bank_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_bankaccount"
    ADD CONSTRAINT "cbpartner_cbpbankaccount" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_customer_acct"
    ADD CONSTRAINT "cacctschema_cbpcustomeracct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_customer_acct"
    ADD CONSTRAINT "cbuspartner_cbpcustomer_acct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cacctschema_cbpemployeeacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "cbuspartner_c_bpemployeeacct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "vc_eexpense_cbpemployee" FOREIGN KEY ("e_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_employee_acct"
    ADD CONSTRAINT "vc_eprepayment_cbpemployee" FOREIGN KEY ("e_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mdiscountschemapo_cbpgroup" FOREIGN KEY ("po_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mdiscountschema_cbpgroup" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mpricelistpo_cbpgroup" FOREIGN KEY ("po_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group"
    ADD CONSTRAINT "mpricelist_cbpgroup" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "cacctschema_cbpgroupacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "cbpgroup_cbpgroupacct" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_notinvoicedrevenue_cbpgroup" FOREIGN KEY ("notinvoicedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_paydiscountexp_cbpgroup" FOREIGN KEY ("paydiscount_exp_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_paydiscountrev_cbpgroup" FOREIGN KEY ("paydiscount_rev_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_vliabilityservices_cbpgroup" FOREIGN KEY ("v_liability_services_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_vliability_cbpgroup" FOREIGN KEY ("v_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_vprepayment_cbpgroup" FOREIGN KEY ("v_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_writeoff_cbpgroup" FOREIGN KEY ("writeoff_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_creceivable_cbpgroup" FOREIGN KEY ("c_receivable_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_cprepayment_cbpgroup" FOREIGN KEY ("c_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_notinvoicedrec_cbpgroup" FOREIGN KEY ("notinvoicedreceivables_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_notinvoicedreceipts_cbpgrou" FOREIGN KEY ("notinvoicedreceipts_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_group_acct"
    ADD CONSTRAINT "vc_unearnedrevenue_cbpgroup" FOREIGN KEY ("unearnedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbpartner_cbprelation" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbpartner_cbprelationbp" FOREIGN KEY ("c_bpartnerrelation_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbplocation_cbprelation" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_relation"
    ADD CONSTRAINT "cbplocation_cbprelationbp" FOREIGN KEY ("c_bpartnerrelation_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "cacctschema_cbpvendoracct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "c_buspartner_c_bp_vendor_acct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "vc_vliabilityservices_cbpvendo" FOREIGN KEY ("v_liability_services_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "vc_vprepayment_cbpvendor" FOREIGN KEY ("v_prepayment_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_vendor_acct"
    ADD CONSTRAINT "vc_vliability_cbpvendor" FOREIGN KEY ("v_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_withholding"
    ADD CONSTRAINT "cbpartner_cbpwithholding" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_bp_withholding"
    ADD CONSTRAINT "cwithholding_cbpwithholding" FOREIGN KEY ("c_withholding_id") REFERENCES "c_withholding" ("c_withholding_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_calendar"
    ADD CONSTRAINT "c_calendarclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_calendar"
    ADD CONSTRAINT "c_calendarorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_campaign"
    ADD CONSTRAINT "cchannel_ccampaign" FOREIGN KEY ("c_channel_id") REFERENCES "c_channel" ("c_channel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "adorgtrx_ccash" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "adorg_ccash" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "cactivity_ccash" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "ccampaign_ccash" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "ccashbook_ccash" FOREIGN KEY ("c_cashbook_id") REFERENCES "c_cashbook" ("c_cashbook_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "celementvalueuser1_ccash" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "celementvalueuser2_ccash" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cash"
    ADD CONSTRAINT "cproject_ccash" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook"
    ADD CONSTRAINT "ccurrency_ccashbook" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "cacctschema_ccashbookacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "ccashbook_ccashbookacct" FOREIGN KEY ("c_cashbook_id") REFERENCES "c_cashbook" ("c_cashbook_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbcashtransfer_ccashbook" FOREIGN KEY ("cb_cashtransfer_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbasset_ccashbook" FOREIGN KEY ("cb_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbexpense_ccashbook" FOREIGN KEY ("cb_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbdifferences_ccashbook" FOREIGN KEY ("cb_differences_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashbook_acct"
    ADD CONSTRAINT "vc_cbreceipt_ccashbook" FOREIGN KEY ("cb_receipt_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "cbankacct_ccashline" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "ccash_ccashline" FOREIGN KEY ("c_cash_id") REFERENCES "c_cash" ("c_cash_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "ccharge_ccashline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "ccurrency_ccashline" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cashline"
    ADD CONSTRAINT "cinvoice_ccashline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge"
    ADD CONSTRAINT "ctaxcategory_ccharge" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_charge_acct"
    ADD CONSTRAINT "cacctschema_cchargeacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ccommentrun_ccommissionamt" FOREIGN KEY ("c_commissionrun_id") REFERENCES "c_commissionrun" ("c_commissionrun_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_commissiondetail"
    ADD CONSTRAINT "ccommissionamt_ccomdetail" FOREIGN KEY ("c_commissionamt_id") REFERENCES "c_commissionamt" ("c_commissionamt_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ccommission_ccommissionline" FOREIGN KEY ("c_commission_id") REFERENCES "c_commission" ("c_commission_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ccurrency_cconversionrate" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_conversion_rate"
    ADD CONSTRAINT "c_currencyconvrateto" FOREIGN KEY ("c_currency_id_to") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "c_countryclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "adlanguage_ccountry" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "c_countryorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country"
    ADD CONSTRAINT "ccurrency_ccountry" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country_trl"
    ADD CONSTRAINT "adlanguage_ccountrytrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_country_trl"
    ADD CONSTRAINT "ccountry_ccountrytrl" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency"
    ADD CONSTRAINT "c_currencyclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency"
    ADD CONSTRAINT "c_currencyorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "cacctschema_ccurrencyacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "ccurrency_ccurrencyacct" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_realizedgain_ccurrency" FOREIGN KEY ("realizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_realizedloss_ccurrency" FOREIGN KEY ("realizedloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_unrealizedgain_ccurrency" FOREIGN KEY ("unrealizedgain_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_acct"
    ADD CONSTRAINT "vc_unrealizedloss_ccurrency" FOREIGN KEY ("unrealizedloss_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_trl"
    ADD CONSTRAINT "adlanguage_ccurrencytrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_currency_trl"
    ADD CONSTRAINT "ccurrency_ccurrencytrl" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cycle"
    ADD CONSTRAINT "ccurrency_ccycle" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cyclephase"
    ADD CONSTRAINT "ccyclestep_ccyclephase" FOREIGN KEY ("c_cyclestep_id") REFERENCES "c_cyclestep" ("c_cyclestep_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cyclephase"
    ADD CONSTRAINT "cphase_ccyclephase" FOREIGN KEY ("c_phase_id") REFERENCES "c_phase" ("c_phase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_cyclestep"
    ADD CONSTRAINT "ccycle_ccyclestep" FOREIGN KEY ("c_cycle_id") REFERENCES "c_cycle" ("c_cycle_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "adprintformat_cdoctype" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "ad_sequence_doctypedoc" FOREIGN KEY ("docnosequence_id") REFERENCES "ad_sequence" ("ad_sequence_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "cdoctype_invoice" FOREIGN KEY ("c_doctypeinvoice_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "cdoctype_proforma" FOREIGN KEY ("c_doctypeproforma_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "cdoctype_shipment" FOREIGN KEY ("c_doctypeshipment_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype"
    ADD CONSTRAINT "glcategory_cdoctype" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctypecounter"
    ADD CONSTRAINT "cdoctypecount_cdoctypecount" FOREIGN KEY ("counter_c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctypecounter"
    ADD CONSTRAINT "cdoctype_cdoctypecounter" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype_trl"
    ADD CONSTRAINT "adlanguage_cdoctypetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_doctype_trl"
    ADD CONSTRAINT "cdoctype_cdoctypetrl" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cdunningrun_cdunningrunentry" FOREIGN KEY ("c_dunningrun_id") REFERENCES "c_dunningrun" ("c_dunningrun_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunline"
    ADD CONSTRAINT "cdunningrunentry_line" FOREIGN KEY ("c_dunningrunentry_id") REFERENCES "c_dunningrunentry" ("c_dunningrunentry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunline"
    ADD CONSTRAINT "cinvoice_cdunningrunline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_dunningrunline"
    ADD CONSTRAINT "cpayment_cdunningrunline" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_element"
    ADD CONSTRAINT "adclient_celement" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_element"
    ADD CONSTRAINT "c_elementorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_element"
    ADD CONSTRAINT "adtree_celement" FOREIGN KEY ("ad_tree_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "adclient_celementvalue" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "adorg_celementvalue" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "cbankaccount_celementvalue" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "ccurrency_celementvalue" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue"
    ADD CONSTRAINT "celement_celementvalue" FOREIGN KEY ("c_element_id") REFERENCES "c_element" ("c_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue_trl"
    ADD CONSTRAINT "adlanguage_celementvaluetrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_elementvalue_trl"
    ADD CONSTRAINT "celementvalue_cevaluetrl" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_greeting_trl"
    ADD CONSTRAINT "adlanguage_cgreetingtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_greeting_trl"
    ADD CONSTRAINT "cgreeting_cgreetingtrl" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "adorgto_cinterorgacct" FOREIGN KEY ("ad_orgto_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "adorg_cinterorgacct" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "cacctschema_cinterorgacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "vc_intercompanydueto_cinterorg" FOREIGN KEY ("intercompanydueto_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_interorg_acct"
    ADD CONSTRAINT "vc_intercompanyduefrom_cintero" FOREIGN KEY ("intercompanyduefrom_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "adorgtrx_cinvoice" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "adorg_cinvoice" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cdoctypetarget_cinvoice" FOREIGN KEY ("c_doctypetarget_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "cdoctype_cinvoice" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "celementvalueuser1_cinvoice" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoice"
    ADD CONSTRAINT "celementvalueuser2_cinvoice" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorgtrx_cinvoicebatchline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "adorg_cinvoicebatchline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "celementvalueu1_cinvoicebline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "celementvalueu2_cinvoicebline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cinvoice_cinvoicebatchline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cinvoicebatch_cinvoicebline" FOREIGN KEY ("c_invoicebatch_id") REFERENCES "c_invoicebatch" ("c_invoicebatch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cinvoiceline_cinvoicebline" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "cproject_cinvoicebatchline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicebatchline"
    ADD CONSTRAINT "ctax_cinvoicebatchline" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "adorgtrx_cinvoiceline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "adorg_cinvoiceline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "aasset_cinvoiceline" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cactivity_cinvoiceline" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "ccampaign_cinvoiceline" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "ccharge_cinvoiceline" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "celemenrvalueuser2_cinvline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "celemenrvalueuser1_cinvline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cinvoice_cinvoiceline" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "convoiceline_ref" FOREIGN KEY ("ref_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "corderline_cinvoiceline" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cproject_cinvoiceline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cprojectphase_cinvoiceline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoiceline"
    ADD CONSTRAINT "cprojecttask_cinvoiceline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cinvoice_cinvoicepaysched" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicepayschedule"
    ADD CONSTRAINT "cpayschedule_cinvoicepaysched" FOREIGN KEY ("c_payschedule_id") REFERENCES "c_payschedule" ("c_payschedule_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_invoicetax"
    ADD CONSTRAINT "cinvoice_cinvoicetax" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cinvoiceline_clandedcost" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorgtrx_corder" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "adorg_corder" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "aduser_corder" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "aduser_sr_corder" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "aduserbill_corder" FOREIGN KEY ("bill_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cactivity_corder" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartnerbill_corder" FOREIGN KEY ("bill_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartner_corder" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartnerpay_corder" FOREIGN KEY ("pay_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbpartnerlocation_corder" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "cbplocationpay_corder" FOREIGN KEY ("pay_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cdoctypetarget_corder" FOREIGN KEY ("c_doctypetarget_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_order"
    ADD CONSTRAINT "c_doctype_corder" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorgtrx_corderline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "adorg_corderline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "celemenrvalueuser2_corderline" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "celemenrvalueuser1_corderline" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "corder_corderline" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "corderline_ref" FOREIGN KEY ("ref_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cproject_corderline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cprojectphase_corderline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_orderline"
    ADD CONSTRAINT "cprojecttask_corderline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "corder_cordertax" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cbpbankacct_cpayment" FOREIGN KEY ("c_bp_bankaccount_id") REFERENCES "c_bp_bankaccount" ("c_bp_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "celementvalueuser2_cpayment" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "celementvalueuser1_cpayment" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cpaymentbatch_cpayment" FOREIGN KEY ("c_paymentbatch_id") REFERENCES "c_paymentbatch" ("c_paymentbatch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_payment"
    ADD CONSTRAINT "cproject_cpayment" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentallocate"
    ADD CONSTRAINT "cpaymtallocate_callocationline" FOREIGN KEY ("c_allocationline_id") REFERENCES "c_allocationline" ("c_allocationline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentallocate"
    ADD CONSTRAINT "cinvoice_cpaymentallocate" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_paymentallocate"
    ADD CONSTRAINT "cpayment_cpaymentallocate" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cpaymentterm_cpaytermtrl" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cpaysel_cpayselline" FOREIGN KEY ("c_payselection_id") REFERENCES "c_payselection" ("c_payselection_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cprojecttype_cphase" FOREIGN KEY ("c_projecttype_id") REFERENCES "c_projecttype" ("c_projecttype_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cposkeylayout_c_poskey" FOREIGN KEY ("c_poskeylayout_id") REFERENCES "c_poskeylayout" ("c_poskeylayout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_poskey"
    ADD CONSTRAINT "mproduct_cposkey" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "adclient_cproject" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "adorg_cproject" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "aduser_sr_cproject" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project"
    ADD CONSTRAINT "aduser_cproject" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "corderpo_cprojectline" FOREIGN KEY ("c_orderpo_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "corder_cprojectline" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cproject_cprojectline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cprojectissue_cprojectline" FOREIGN KEY ("c_projectissue_id") REFERENCES "c_projectissue" ("c_projectissue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cprojectphase_cprojectline" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projectline"
    ADD CONSTRAINT "cprojecttask_cprojectline" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cprojectphase_cprojecttask" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projecttask"
    ADD CONSTRAINT "ctask_cprojecttask" FOREIGN KEY ("c_task_id") REFERENCES "c_task" ("c_task_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_projecttask"
    ADD CONSTRAINT "mproduct_cprojecttask" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project_acct"
    ADD CONSTRAINT "cacctschema_cprojectacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_project_acct"
    ADD CONSTRAINT "c_project_projectacct" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "vc_prevenue_crevenuerecognitio" FOREIGN KEY ("p_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_revenuerecognition_plan"
    ADD CONSTRAINT "vc_unearnedrevenue_crevenuerec" FOREIGN KEY ("unearnedrevenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "crfq_crfqline" FOREIGN KEY ("c_rfq_id") REFERENCES "c_rfq" ("c_rfq_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqline"
    ADD CONSTRAINT "masetinstance_crfqline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqline"
    ADD CONSTRAINT "mproduct_crfqline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqlineqty"
    ADD CONSTRAINT "crfqline_crfqlineqty" FOREIGN KEY ("c_rfqline_id") REFERENCES "c_rfqline" ("c_rfqline_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "crfq_crfqresponse" FOREIGN KEY ("c_rfq_id") REFERENCES "c_rfq" ("c_rfq_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponseline"
    ADD CONSTRAINT "crfqline_crfqresponseline" FOREIGN KEY ("c_rfqline_id") REFERENCES "c_rfqline" ("c_rfqline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponseline"
    ADD CONSTRAINT "crfqresponse_line" FOREIGN KEY ("c_rfqresponse_id") REFERENCES "c_rfqresponse" ("c_rfqresponse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponselineqty"
    ADD CONSTRAINT "crfqlineqty_crfqresplineqty" FOREIGN KEY ("c_rfqlineqty_id") REFERENCES "c_rfqlineqty" ("c_rfqlineqty_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfqresponselineqty"
    ADD CONSTRAINT "crfqresonseline_qty" FOREIGN KEY ("c_rfqresponseline_id") REFERENCES "c_rfqresponseline" ("c_rfqresponseline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topic"
    ADD CONSTRAINT "adprintformat_arfqtopic" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "aduser_arfqtopicsubcr" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "cbpartner_crfqtopicsubr" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "cbpartnerloc_crfqtopicsubr" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriber"
    ADD CONSTRAINT "c_rfqtopic_subscriber" FOREIGN KEY ("c_rfq_topic_id") REFERENCES "c_rfq_topic" ("c_rfq_topic_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriberonly"
    ADD CONSTRAINT "crfqtopicsubscriber_only" FOREIGN KEY ("c_rfq_topicsubscriber_id") REFERENCES "c_rfq_topicsubscriber" ("c_rfq_topicsubscriber_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriberonly"
    ADD CONSTRAINT "mproduct_crfqtsubonly" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_rfq_topicsubscriberonly"
    ADD CONSTRAINT "mprodcategory_crfqtsubonly" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_servicelevel"
    ADD CONSTRAINT "crevrecplan_cservicelevel" FOREIGN KEY ("c_revenuerecognition_plan_id") REFERENCES "c_revenuerecognition_plan" ("c_revenuerecognition_plan_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_servicelevel"
    ADD CONSTRAINT "mproduct_cservicelevel" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_servicelevelline"
    ADD CONSTRAINT "cservicelevel_line" FOREIGN KEY ("c_servicelevel_id") REFERENCES "c_servicelevel" ("c_servicelevel_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subacct"
    ADD CONSTRAINT "celementvalue_csubacct" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription"
    ADD CONSTRAINT "cbpartner_csubscription" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription"
    ADD CONSTRAINT "csubscrtype_csubscription" FOREIGN KEY ("c_subscriptiontype_id") REFERENCES "c_subscriptiontype" ("c_subscriptiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription"
    ADD CONSTRAINT "mproduct_csubscription" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_subscription_delivery"
    ADD CONSTRAINT "csubcription_csubscrdelivery" FOREIGN KEY ("c_subscription_id") REFERENCES "c_subscription" ("c_subscription_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_task"
    ADD CONSTRAINT "cphase_ctask" FOREIGN KEY ("c_phase_id") REFERENCES "c_phase" ("c_phase_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ctaxcategory_ctax" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxcategory_trl"
    ADD CONSTRAINT "adlanguage_ctaxcategorytrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxcategory_trl"
    ADD CONSTRAINT "ctaxcategory_trl" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationacct"
    ADD CONSTRAINT "cacctschema_ctaxdeclacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationacct"
    ADD CONSTRAINT "ctaxdecl_ctaxdeclacct" FOREIGN KEY ("c_taxdeclaration_id") REFERENCES "c_taxdeclaration" ("c_taxdeclaration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxdeclarationacct"
    ADD CONSTRAINT "factacct_ctaxdeclacct" FOREIGN KEY ("fact_acct_id") REFERENCES "fact_acct" ("fact_acct_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "ctaxdeclaration_ctaxdeclline" FOREIGN KEY ("c_taxdeclaration_id") REFERENCES "c_taxdeclaration" ("c_taxdeclaration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_taxpostal"
    ADD CONSTRAINT "ctax_ctaxpostal" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "cacctschema_ctaxacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "ctax_ctaxacct" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_tdue_ctax" FOREIGN KEY ("t_due_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_tcredit_ctax" FOREIGN KEY ("t_credit_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_tliability_ctax" FOREIGN KEY ("t_liability_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_texpense_ctax" FOREIGN KEY ("t_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_acct"
    ADD CONSTRAINT "vc_trec_ctax" FOREIGN KEY ("t_receivables_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_trl"
    ADD CONSTRAINT "adlanguage_ctaxtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_tax_trl"
    ADD CONSTRAINT "ctax_ctaxtrl" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom"
    ADD CONSTRAINT "c_uomclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom"
    ADD CONSTRAINT "c_uomorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "c_uom_conversionclient" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "c_uom_conversionorg" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "cuom_cuomconversion" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_conversion"
    ADD CONSTRAINT "c_uomconversionto" FOREIGN KEY ("c_uom_to_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_trl"
    ADD CONSTRAINT "adlanguage_cuomtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_uom_trl"
    ADD CONSTRAINT "cuom_cuomtrl" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_userremuneration"
    ADD CONSTRAINT "aduser_cuserremuneration" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_userremuneration"
    ADD CONSTRAINT "cremuneration_cuserrem" FOREIGN KEY ("c_remuneration_id") REFERENCES "c_remuneration" ("c_remuneration_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "adclient_vc" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "adorgtrx_vc" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "adorg_vc" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cacctschema_cvalidcombination" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cactivity_cvalidcombination" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cbpartner_vc" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "socampaign_vc" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "celementvalueuser1_vc" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "celementvalueuser2_vc" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "celementvalueaccount_vc" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "mlocationfrom_vc" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "mlocationto_vc" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "cproject_vc" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "csalesregion_vc" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_validcombination"
    ADD CONSTRAINT "mproduct_vc" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_withholding"
    ADD CONSTRAINT "cpaymentterm_cwithholding" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "c_withholding_acct"
    ADD CONSTRAINT "cacctschema_cwithholdingacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cacctschema_factacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cactivity_factacct" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_buspartner_fact_acct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "so_campaign_fact_acct" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_currency_fact_acct" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "celementvalueuser2_factacct" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "celementvalue_factacct" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "celementvalueuser1_factacct" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_locationto_fact_acct" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_locationfrom_fact_acct" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cperiod_factacct" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "c_project_fact_acct" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cprojectphase_factacct" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct"
    ADD CONSTRAINT "cprojecttask_factacct" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorg_factacctbal" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "celementvalueacct_factacctbal" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "celementvalueu1_factacctbal" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "celementvalueu2_factacctbal" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "clocfrom_factacctbalance" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "clocto_factacctbal" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cproject_factacctbal" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cprojectphase_factacctbalance" FOREIGN KEY ("c_projectphase_id") REFERENCES "c_projectphase" ("c_projectphase_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "fact_acct_balance"
    ADD CONSTRAINT "cprojecttask_factacctbalance" FOREIGN KEY ("c_projecttask_id") REFERENCES "c_projecttask" ("c_projecttask_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorgorg_gldist" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "adorgtrx_gldist" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "adorg_gldist" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cevalueuser1_gldist" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cevalueacct_gldist" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cevalueuser2_gldist" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "clocfrom_gldist" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "clocto_gldist" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "cproject_gldist" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "csalesregopn_gldist" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distribution"
    ADD CONSTRAINT "mproduct_gldist" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "adorgorg_gldistline" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "adorgtrx_gldistline" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "adorg_gldistline" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "clocfrom_gldistline" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "clocto_gldistline" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "cproject_gldistline" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "csalesregion_gldistline" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "gldistribution_gldistline" FOREIGN KEY ("gl_distribution_id") REFERENCES "gl_distribution" ("gl_distribution_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_distributionline"
    ADD CONSTRAINT "mproduct_gldistline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_fund"
    ADD CONSTRAINT "cacctschema_glfund" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_fundrestriction"
    ADD CONSTRAINT "celementvalue_glfundrestr" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "gl_fundrestriction"
    ADD CONSTRAINT "glfund_glfundrestriction" FOREIGN KEY ("gl_fund_id") REFERENCES "gl_fund" ("gl_fund_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "gljournalbatch_gljournal" FOREIGN KEY ("gl_journalbatch_id") REFERENCES "gl_journalbatch" ("gl_journalbatch_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "gljournal_gljournalline" FOREIGN KEY ("gl_journal_id") REFERENCES "gl_journal" ("gl_journal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbankaccount_ibankstatement" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbankstatement_ibankstatement" FOREIGN KEY ("c_bankstatement_id") REFERENCES "c_bankstatement" ("c_bankstatement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbankstmtline_ibankstmt" FOREIGN KEY ("c_bankstatementline_id") REFERENCES "c_bankstatementline" ("c_bankstatementline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cbpartner_ibankstatement" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "ccharge_ibankstmt" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "ccurrency_ibankstatement" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cinvoice_ibankstatement" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bankstatement"
    ADD CONSTRAINT "cpayment_ibankstatement" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "aduser_ibpartner" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cbpartner_ibpartner" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cbpartnerlocation_ibpartner" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cbpgroup_ibpartner" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "ccountry_ipartner" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cgreeting_ibpartner" FOREIGN KEY ("c_greeting_id") REFERENCES "c_greeting" ("c_greeting_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_bpartner"
    ADD CONSTRAINT "cregion_ibpartner" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "cconvtype_iconvrate" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "cconversionrate_iconvrate" FOREIGN KEY ("c_conversion_rate_id") REFERENCES "c_conversion_rate" ("c_conversion_rate_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "ccurrency_iconvrateto" FOREIGN KEY ("c_currency_id_to") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_conversion_rate"
    ADD CONSTRAINT "ccurrency_iconvrate" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "adcolumn_ielementvalue" FOREIGN KEY ("ad_column_id") REFERENCES "ad_column" ("ad_column_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "celement_ielementvalue" FOREIGN KEY ("c_element_id") REFERENCES "c_element" ("c_element_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "celementvalue_ielementvalue" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_elementvalue"
    ADD CONSTRAINT "cevalueparent_ielementvalue" FOREIGN KEY ("parentelementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "adorgtrx_igljournal" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "adorgdoc_igljournal" FOREIGN KEY ("ad_orgdoc_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "adorg_igljournal" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cacctschema_igljournal" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cactivity_gljournal" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cbpartner_igljournal" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "ccampaign_igljournal" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cconversiontype_igljournal" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "ccurrency_igljournal" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cdoctype_igljournal" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "celvalueuser2_igljournal" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "celvalueaccount_igljournal" FOREIGN KEY ("account_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cevalueuser1_igljournal" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "clocto_igljournal" FOREIGN KEY ("c_locto_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "clocfrom_igljournal" FOREIGN KEY ("c_locfrom_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cperiod_igljournal" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cproject_igljournal" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "csalesregion_igljournal" FOREIGN KEY ("c_salesregion_id") REFERENCES "c_salesregion" ("c_salesregion_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "cvalidcombination_igljournal" FOREIGN KEY ("c_validcombination_id") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "glbudget_igljournal" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "glcategory_igljournal" FOREIGN KEY ("gl_category_id") REFERENCES "gl_category" ("gl_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "gljournal_igljournal" FOREIGN KEY ("gl_journal_id") REFERENCES "gl_journal" ("gl_journal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "gljournalbatch_igljournal" FOREIGN KEY ("gl_journalbatch_id") REFERENCES "gl_journalbatch" ("gl_journalbatch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "gljourbelline_igljournal" FOREIGN KEY ("gl_journalline_id") REFERENCES "gl_journalline" ("gl_journalline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_gljournal"
    ADD CONSTRAINT "mproduct_ogljournal" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inoutlineconfirm"
    ADD CONSTRAINT "minoutlineconfirm_import" FOREIGN KEY ("m_inoutlineconfirm_id") REFERENCES "m_inoutlineconfirm" ("m_inoutlineconfirm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "minventory_iinventory" FOREIGN KEY ("m_inventory_id") REFERENCES "m_inventory" ("m_inventory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "minventoryline_iinventory" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "mlocator_iinventory" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "mproduct_iinventory" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_inventory"
    ADD CONSTRAINT "mwarehouse_iinventory" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "adorgtrx_iinvoice" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "adorg_iinvoice" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "adusersalesrep_iinvoice" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "aduser_iinvoice" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cactivity_iinvoice" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cbpartner_iinvoice" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cbplocation_iinvoice" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ccampaign_iinvoice" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ccountry_iinvoice" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ccurrency_iinvoice" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cdoctype_iinvoice" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cinvoice_iinvoice" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cinvliceline_iinvoice" FOREIGN KEY ("c_invoiceline_id") REFERENCES "c_invoiceline" ("c_invoiceline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "clocation_iinvoice" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cpaymentterm_iinvoice" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cproject_iinvoice" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "cregion_iinvoice" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "ctax_iinvoice" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "mpricelist_iinvoice" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_invoice"
    ADD CONSTRAINT "nproduct_iinvoice" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "adorgtrx_iorder" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "adorg_iorder" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "adusersalesrep_iorder" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "aduser_iorder" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cactivity_iorder" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cbpartner_iorder" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cbpartnerlocbillto_iorder" FOREIGN KEY ("billto_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cbolocation_iorder" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ccampaign_iorder" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ccountry_iorder" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ccurrency_iorder" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cdoctype_iorder" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "clocation_iorder" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "corder_iorder" FOREIGN KEY ("c_order_id") REFERENCES "c_order" ("c_order_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "corderline_iorder" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cpaymentterm_iorder" FOREIGN KEY ("c_paymentterm_id") REFERENCES "c_paymentterm" ("c_paymentterm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cproject_iorder" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cregion_iorder" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "ctax_iorder" FOREIGN KEY ("c_tax_id") REFERENCES "c_tax" ("c_tax_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "cuom_iorder" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mpricelist_iorder" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mproduct_iorder" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mshipper_iorder" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_order"
    ADD CONSTRAINT "mwarehouse_iorder" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cbankaccount_ipayment" FOREIGN KEY ("c_bankaccount_id") REFERENCES "c_bankaccount" ("c_bankaccount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cbpartner_ipayment" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "ccharge_ipayment" FOREIGN KEY ("c_charge_id") REFERENCES "c_charge" ("c_charge_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cdoctype_ipayment" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cinvoice_ipayment" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_payment"
    ADD CONSTRAINT "cpayment_ipayment" FOREIGN KEY ("c_payment_id") REFERENCES "c_payment" ("c_payment_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "cbpartner_iproduct" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "ccurrency_iproduct" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "cuom_iproduct" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "mproduct_iproduct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_product"
    ADD CONSTRAINT "mproductcategory_iproduct" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "celementvalue_ireportline" FOREIGN KEY ("c_elementvalue_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "pareportline_ireportline" FOREIGN KEY ("pa_reportline_id") REFERENCES "pa_reportline" ("pa_reportline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "pareportlineset_ireportline" FOREIGN KEY ("pa_reportlineset_id") REFERENCES "pa_reportlineset" ("pa_reportlineset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "i_reportline"
    ADD CONSTRAINT "pareportsource_ireportline" FOREIGN KEY ("pa_reportsource_id") REFERENCES "pa_reportsource" ("pa_reportsource_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "kentry_kentryrelated" FOREIGN KEY ("k_entry_id") REFERENCES "k_entry" ("k_entry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_entryrelated"
    ADD CONSTRAINT "kentry_kentryrelatedid" FOREIGN KEY ("k_entryrelated_id") REFERENCES "k_entry" ("k_entry_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "adtable_kindex" FOREIGN KEY ("ad_table_id") REFERENCES "ad_table" ("ad_table_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "cmwebproject_kindex" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "cdoctype_kindex" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_index"
    ADD CONSTRAINT "rrequesttype_kindex" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_indexstop"
    ADD CONSTRAINT "cmwebproject_kindexstop" FOREIGN KEY ("cm_webproject_id") REFERENCES "cm_webproject" ("cm_webproject_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_indexstop"
    ADD CONSTRAINT "cdoctype_kindexstop" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_indexstop"
    ADD CONSTRAINT "rrequesttype_kindexstop" FOREIGN KEY ("r_requesttype_id") REFERENCES "r_requesttype" ("r_requesttype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "k_topic"
    ADD CONSTRAINT "ktype_ktopic" FOREIGN KEY ("k_type_id") REFERENCES "k_type" ("k_type_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attribute"
    ADD CONSTRAINT "mattributesearch_mattribute" FOREIGN KEY ("m_attributesearch_id") REFERENCES "m_attributesearch" ("m_attributesearch_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeinstance"
    ADD CONSTRAINT "mattribute_mattributeinst" FOREIGN KEY ("m_attribute_id") REFERENCES "m_attribute" ("m_attribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_attributeinstance"
    ADD CONSTRAINT "mattrsetinst__mattrinst" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mattribute_mattributevalue" FOREIGN KEY ("m_attribute_id") REFERENCES "m_attribute" ("m_attribute_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bom"
    ADD CONSTRAINT "mcn_mbom" FOREIGN KEY ("m_changenotice_id") REFERENCES "m_changenotice" ("m_changenotice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bom"
    ADD CONSTRAINT "mproduct_mbom" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomalternative"
    ADD CONSTRAINT "mproduct_mbomalternative" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "masi_mbomproduct" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mbom_mbomproduct" FOREIGN KEY ("m_bom_id") REFERENCES "m_bom" ("m_bom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mbomalternative_mbomproduct" FOREIGN KEY ("m_bomalternative_id") REFERENCES "m_bomalternative" ("m_bomalternative_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_bomproduct"
    ADD CONSTRAINT "mcn_mbomproduct" FOREIGN KEY ("m_changenotice_id") REFERENCES "m_changenotice" ("m_changenotice_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cacctschema_mcost" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "masi_mcost" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "mcostelement_mcost" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_cost"
    ADD CONSTRAINT "mcosttype_mcost" FOREIGN KEY ("m_costtype_id") REFERENCES "m_costtype" ("m_costtype_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "cacctschema_mcostqueue" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "masi_mcostqueue" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "mcostelement_mcostqueue" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "mcosttype_mcostqueue" FOREIGN KEY ("m_costtype_id") REFERENCES "m_costtype" ("m_costtype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_costqueue"
    ADD CONSTRAINT "mproduct_mcostqueue" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demand"
    ADD CONSTRAINT "ccalendar_mdemand" FOREIGN KEY ("c_calendar_id") REFERENCES "c_calendar" ("c_calendar_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demand"
    ADD CONSTRAINT "cyear_mdemand" FOREIGN KEY ("c_year_id") REFERENCES "c_year" ("c_year_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "corderline_mdemanddetail" FOREIGN KEY ("c_orderline_id") REFERENCES "c_orderline" ("c_orderline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "mdemandline_mdemanddetail" FOREIGN KEY ("m_demandline_id") REFERENCES "m_demandline" ("m_demandline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "mforecastline_mdemanddetail" FOREIGN KEY ("m_forecastline_id") REFERENCES "m_forecastline" ("m_forecastline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demanddetail"
    ADD CONSTRAINT "mreqline_mdemanddetail" FOREIGN KEY ("m_requisitionline_id") REFERENCES "m_requisitionline" ("m_requisitionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demandline"
    ADD CONSTRAINT "cperiod_mdemandline" FOREIGN KEY ("c_period_id") REFERENCES "c_period" ("c_period_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demandline"
    ADD CONSTRAINT "mdemand_mdemandline" FOREIGN KEY ("m_demand_id") REFERENCES "m_demand" ("m_demand_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_demandline"
    ADD CONSTRAINT "mproduct_mdemandline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemabreak"
    ADD CONSTRAINT "mdiscounts_mdsbreak" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemabreak"
    ADD CONSTRAINT "mproduct_mdiscountsbreak" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemabreak"
    ADD CONSTRAINT "mprodcategory_mdiscountsbreak" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "cbpartner_mdiscountsline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "cconversiontype_mdiscschline" FOREIGN KEY ("c_conversiontype_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "mdiscountschema_mdsline" FOREIGN KEY ("m_discountschema_id") REFERENCES "m_discountschema" ("m_discountschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "mproduct_mdiscountsline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_discountschemaline"
    ADD CONSTRAINT "mprodcategory_mdiscountsline" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionlistline"
    ADD CONSTRAINT "cbpartner_mdistlistline" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionlistline"
    ADD CONSTRAINT "cbpartnerloc_mdistlistline" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionlistline"
    ADD CONSTRAINT "mdistributionlist_line" FOREIGN KEY ("m_distributionlist_id") REFERENCES "m_distributionlist" ("m_distributionlist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrun"
    ADD CONSTRAINT "cbpartner_mdistributionrun" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrun"
    ADD CONSTRAINT "cbplocation_mdistributionrun" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrunline"
    ADD CONSTRAINT "mdistributionlist_runline" FOREIGN KEY ("m_distributionlist_id") REFERENCES "m_distributionlist" ("m_distributionlist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_distributionrunline"
    ADD CONSTRAINT "mdistributionrun_line" FOREIGN KEY ("m_distributionrun_id") REFERENCES "m_distributionrun" ("m_distributionrun_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mforecast_mforecastline" FOREIGN KEY ("m_forecast_id") REFERENCES "m_forecast" ("m_forecast_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_forecastline"
    ADD CONSTRAINT "mproduct_mforecastline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "ccountryto_mfreight" FOREIGN KEY ("to_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "ccountry_mfreight" FOREIGN KEY ("c_country_id") REFERENCES "c_country" ("c_country_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "ccurrency_mfreight" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "cregionto_mfreight" FOREIGN KEY ("to_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "cregion_mfreight" FOREIGN KEY ("c_region_id") REFERENCES "c_region" ("c_region_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "mfreightcategory_mfreight" FOREIGN KEY ("m_freightcategory_id") REFERENCES "m_freightcategory" ("m_freightcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_freight"
    ADD CONSTRAINT "mshipper_mfreight" FOREIGN KEY ("m_shipper_id") REFERENCES "m_shipper" ("m_shipper_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "adorgtrx_minout" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inout"
    ADD CONSTRAINT "adorg_minout" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "minout_minoutconfirm" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "minout_minoutline" FOREIGN KEY ("m_inout_id") REFERENCES "m_inout" ("m_inout_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "minoutline_ref" FOREIGN KEY ("ref_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "mlocator_minoutline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutline"
    ADD CONSTRAINT "mproduct_minoutline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlineconfirm"
    ADD CONSTRAINT "minoutconfirm_minoutlineconf" FOREIGN KEY ("m_inoutconfirm_id") REFERENCES "m_inoutconfirm" ("m_inoutconfirm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlineconfirm"
    ADD CONSTRAINT "minoutline_minoutconfirm" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlinema"
    ADD CONSTRAINT "masi_minourlinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inoutlinema"
    ADD CONSTRAINT "minoutline_minoutlinema" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "adorgtrx_minventory" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "adorg_minventory" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "cactivity_minventory" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "ccampaign_minventory" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "celementvalueuser2_minvent" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventory"
    ADD CONSTRAINT "celementvalueuser1_minvent" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "minventory_minventoryline" FOREIGN KEY ("m_inventory_id") REFERENCES "m_inventory" ("m_inventory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "mlocator_minventoryline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventoryline"
    ADD CONSTRAINT "mproduct_minventoryline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventorylinema"
    ADD CONSTRAINT "masi_minventorylinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_inventorylinema"
    ADD CONSTRAINT "minventoryline_milinema" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_locator"
    ADD CONSTRAINT "m_wh_locator_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_locator"
    ADD CONSTRAINT "m_wh_locator_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_locator"
    ADD CONSTRAINT "m_warehouse_locator" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "celementvalueuser1_mmove" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "celementvalueuser2_mmove" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movement"
    ADD CONSTRAINT "cproject_mmovement" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementconfirm"
    ADD CONSTRAINT "minventory_mmovconfirm" FOREIGN KEY ("m_inventory_id") REFERENCES "m_inventory" ("m_inventory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementconfirm"
    ADD CONSTRAINT "mmovement_mmovementconfirm" FOREIGN KEY ("m_movement_id") REFERENCES "m_movement" ("m_movement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mattrsetinst_mmovementline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mlocatorto_mmovementline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mlocator_movementline" FOREIGN KEY ("m_locatorto_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mmovement_mmovementline" FOREIGN KEY ("m_movement_id") REFERENCES "m_movement" ("m_movement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementline"
    ADD CONSTRAINT "mproduct_mmovementline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlineconfirm"
    ADD CONSTRAINT "minventoryline_mmovlineconfirm" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlineconfirm"
    ADD CONSTRAINT "mmovementconfirm_mmovlineconf" FOREIGN KEY ("m_movementconfirm_id") REFERENCES "m_movementconfirm" ("m_movementconfirm_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlineconfirm"
    ADD CONSTRAINT "mmovementline_mmovlineconfirm" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlinema"
    ADD CONSTRAINT "masi_mmovementlinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_movementlinema"
    ADD CONSTRAINT "mmovementline_mmovementlinema" FOREIGN KEY ("m_movementline_id") REFERENCES "m_movementline" ("m_movementline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_operationresource"
    ADD CONSTRAINT "aasset_moperationresource" FOREIGN KEY ("a_asset_id") REFERENCES "a_asset" ("a_asset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_operationresource"
    ADD CONSTRAINT "cjob_moperationresource" FOREIGN KEY ("c_job_id") REFERENCES "c_job" ("c_job_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_operationresource"
    ADD CONSTRAINT "mproductop_mopresource" FOREIGN KEY ("m_productoperation_id") REFERENCES "m_productoperation" ("m_productoperation_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mpricelist_mpricelistversion" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mattributeset_mproduct" FOREIGN KEY ("m_attributeset_id") REFERENCES "m_attributeset" ("m_attributeset_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorgtrx_mproduction" FOREIGN KEY ("ad_orgtrx_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "adorg_mproduction" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "cactivity_mproduction" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "ccampaign_mproduction" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "celementvalueuser1_mprod" FOREIGN KEY ("user1_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "celementvalueuser2_mprod" FOREIGN KEY ("user2_id") REFERENCES "c_elementvalue" ("c_elementvalue_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_production"
    ADD CONSTRAINT "cproject_mproduction" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mattrsetinst_mproductionline" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mlocator_mproductionline" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mproduct_mproductionline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionline"
    ADD CONSTRAINT "mproductionplan_line" FOREIGN KEY ("m_productionplan_id") REFERENCES "m_productionplan" ("m_productionplan_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionlinema"
    ADD CONSTRAINT "masi_mproductionlinema" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionlinema"
    ADD CONSTRAINT "mproductionline_mplinema" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionplan"
    ADD CONSTRAINT "mlocator_mproductionplan" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionplan"
    ADD CONSTRAINT "mproduct_mproductionplan" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productionplan"
    ADD CONSTRAINT "mproduction_plan" FOREIGN KEY ("m_production_id") REFERENCES "m_production" ("m_production_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productoperation"
    ADD CONSTRAINT "mproduct_mproductoperation" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productprice"
    ADD CONSTRAINT "mpricelistver_mproductprice" FOREIGN KEY ("m_pricelist_version_id") REFERENCES "m_pricelist_version" ("m_pricelist_version_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_productprice"
    ADD CONSTRAINT "mproduct_mproductprice" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "cacctschema_mproductacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_pexpense_mproduct" FOREIGN KEY ("p_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_passet_mproduct" FOREIGN KEY ("p_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_acct"
    ADD CONSTRAINT "vc_pcogs_mproduct" FOREIGN KEY ("p_cogs_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "m_product_m_product_acct" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_bom"
    ADD CONSTRAINT "mproduct_bomproduct" FOREIGN KEY ("m_productbom_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_bom"
    ADD CONSTRAINT "mproduct_mproductbom" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category"
    ADD CONSTRAINT "aassetgroup_mproductcategory" FOREIGN KEY ("a_asset_group_id") REFERENCES "a_asset_group" ("a_asset_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "cacctschema_mprodcatacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_pexpense_mproductcategory" FOREIGN KEY ("p_expense_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_passet_mproductcategory" FOREIGN KEY ("p_asset_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_pcogs_mproductcategory" FOREIGN KEY ("p_cogs_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_pinvoicepv_mproductcategory" FOREIGN KEY ("p_invoicepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_ppurchasepv_mproductcategor" FOREIGN KEY ("p_purchasepricevariance_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_prevenue_mproductcategory" FOREIGN KEY ("p_revenue_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_ptdiscountgrant_mproductcat" FOREIGN KEY ("p_tradediscountgrant_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "vc_ptdiscountrec_mproductcateg" FOREIGN KEY ("p_tradediscountrec_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_category_acct"
    ADD CONSTRAINT "mprodcat_mprodcatacct" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_costing"
    ADD CONSTRAINT "cacctschema_mproductcosting" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_costing"
    ADD CONSTRAINT "mproduct_mproductcosting" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "m_product_productpo" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_trl"
    ADD CONSTRAINT "adlanguage_mproducttrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_product_trl"
    ADD CONSTRAINT "mproduct_mproducttrl" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_relatedproduct"
    ADD CONSTRAINT "mproduct_mrelatedproduct" FOREIGN KEY ("relatedproduct_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_relatedproduct"
    ADD CONSTRAINT "mproduct_mrelated_product" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_replenish"
    ADD CONSTRAINT "m_product_replenish" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_replenish"
    ADD CONSTRAINT "m_warehouse_replenish" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisition"
    ADD CONSTRAINT "aduser_mrequisition" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisition"
    ADD CONSTRAINT "mprocelist_mrequisition" FOREIGN KEY ("m_pricelist_id") REFERENCES "m_pricelist" ("m_pricelist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisition"
    ADD CONSTRAINT "mwarehouse_mrequisition" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisitionline"
    ADD CONSTRAINT "mproduct_mrequisitionline" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_requisitionline"
    ADD CONSTRAINT "mrequisition_mrequisitionline" FOREIGN KEY ("m_requisition_id") REFERENCES "m_requisition" ("m_requisition_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "m_locator_storage" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_storage"
    ADD CONSTRAINT "mproduct_storage" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "m_substitute_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "m_substitute_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "mproduct_substitutesub" FOREIGN KEY ("substitute_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_substitute"
    ADD CONSTRAINT "mproduct_substitute" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "minoutlineout_mtrxalloc" FOREIGN KEY ("out_m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minoutline_mtrxalloc" FOREIGN KEY ("m_inoutline_id") REFERENCES "m_inoutline" ("m_inoutline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minventorylineout_mtrxalloc" FOREIGN KEY ("out_m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "minventoryline_mtrxalloc" FOREIGN KEY ("m_inventoryline_id") REFERENCES "m_inventoryline" ("m_inventoryline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mproduct_mtrxalloc" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mproductionlineout_mtrxalloc" FOREIGN KEY ("out_m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mproductionline_mtrxalloc" FOREIGN KEY ("m_productionline_id") REFERENCES "m_productionline" ("m_productionline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mtransactionout_mtrxalloc" FOREIGN KEY ("out_m_transaction_id") REFERENCES "m_transaction" ("m_transaction_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_transactionallocation"
    ADD CONSTRAINT "mttransaction_mtrxalloc" FOREIGN KEY ("m_transaction_id") REFERENCES "m_transaction" ("m_transaction_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse"
    ADD CONSTRAINT "m_warehouse_client" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse"
    ADD CONSTRAINT "m_warehouse_org" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse"
    ADD CONSTRAINT "c_location_warehouse" FOREIGN KEY ("c_location_id") REFERENCES "c_location" ("c_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "cacctschema_mwarehouseacct" FOREIGN KEY ("c_acctschema_id") REFERENCES "c_acctschema" ("c_acctschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_wdifferences_mwarehouse" FOREIGN KEY ("w_differences_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_winvactualadjust_mwarehouse" FOREIGN KEY ("w_invactualadjust_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_winventory_mwarehouse" FOREIGN KEY ("w_inventory_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "vc_wrevaluation_mwarehouse" FOREIGN KEY ("w_revaluation_acct") REFERENCES "c_validcombination" ("c_validcombination_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "m_warehouse_acct"
    ADD CONSTRAINT "m_warehouse_warehouse_acct" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_achievement"
    ADD CONSTRAINT "pameasure_paachievement" FOREIGN KEY ("pa_measure_id") REFERENCES "pa_measure" ("pa_measure_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_benchmarkdata"
    ADD CONSTRAINT "pabenchmark_pabenchmarkdata" FOREIGN KEY ("pa_benchmark_id") REFERENCES "pa_benchmark" ("pa_benchmark_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor1_pacolorschema" FOREIGN KEY ("ad_printcolor1_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor2_pacolorschema" FOREIGN KEY ("ad_printcolor2_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor3_pacolorschema" FOREIGN KEY ("ad_printcolor3_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_colorschema"
    ADD CONSTRAINT "adprintcolor4_pacolorschema" FOREIGN KEY ("ad_printcolor4_id") REFERENCES "ad_printcolor" ("ad_printcolor_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "aduser_pagoal" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "pacolorschema_pagoal" FOREIGN KEY ("pa_colorschema_id") REFERENCES "pa_colorschema" ("pa_colorschema_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "pagoalparent_pagoal" FOREIGN KEY ("pa_goalparent_id") REFERENCES "pa_goal" ("pa_goal_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goal"
    ADD CONSTRAINT "pameasure_pagoal" FOREIGN KEY ("pa_measure_id") REFERENCES "pa_measure" ("pa_measure_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "adorg2_pagoalrestriction" FOREIGN KEY ("org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_goalrestriction"
    ADD CONSTRAINT "adorg_pagoalrestriction" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adtreecampaign_pahierarchy" FOREIGN KEY ("ad_tree_campaign_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeorg_pahierarchy" FOREIGN KEY ("ad_tree_org_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeproduct_pahierarchy" FOREIGN KEY ("ad_tree_product_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreeproject_pahierarchy" FOREIGN KEY ("ad_tree_project_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_hierarchy"
    ADD CONSTRAINT "adtreesr_pahierarchy" FOREIGN KEY ("ad_tree_salesregion_id") REFERENCES "ad_tree" ("ad_tree_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "adorg_pareport" FOREIGN KEY ("ad_org_id") REFERENCES "ad_org" ("ad_org_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_report"
    ADD CONSTRAINT "adprintformat_pareport" FOREIGN KEY ("ad_printformat_id") REFERENCES "ad_printformat" ("ad_printformat_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "pareportcolumn_oper2" FOREIGN KEY ("oper_2_id") REFERENCES "pa_reportcolumn" ("pa_reportcolumn_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "pareportcolumn_oper1" FOREIGN KEY ("oper_1_id") REFERENCES "pa_reportcolumn" ("pa_reportcolumn_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportcolumn"
    ADD CONSTRAINT "pareportcolumnset_column" FOREIGN KEY ("pa_reportcolumnset_id") REFERENCES "pa_reportcolumnset" ("pa_reportcolumnset_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "glbudget_pareportline" FOREIGN KEY ("gl_budget_id") REFERENCES "gl_budget" ("gl_budget_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportline_oper2" FOREIGN KEY ("oper_2_id") REFERENCES "pa_reportline" ("pa_reportline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportline_oper1" FOREIGN KEY ("oper_1_id") REFERENCES "pa_reportline" ("pa_reportline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportline_parent" FOREIGN KEY ("parent_id") REFERENCES "pa_reportline" ("pa_reportline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "pa_reportline"
    ADD CONSTRAINT "pareportlineset_line" FOREIGN KEY ("pa_reportlineset_id") REFERENCES "pa_reportlineset" ("pa_reportlineset_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "pareportline_pareportsource" FOREIGN KEY ("pa_reportline_id") REFERENCES "pa_reportline" ("pa_reportline_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "rmailtext_rmailtexttrl" FOREIGN KEY ("r_mailtext_id") REFERENCES "r_mailtext" ("r_mailtext_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "aduser_rrequestaction" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "r_requestaction"
    ADD CONSTRAINT "adusersr_rrequestaction" FOREIGN KEY ("salesrep_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "rrequestprocessor_log" FOREIGN KEY ("r_requestprocessor_id") REFERENCES "r_requestprocessor" ("r_requestprocessor_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "sresourcetype_sresource" FOREIGN KEY ("s_resourcetype_id") REFERENCES "s_resourcetype" ("s_resourcetype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourceassignment"
    ADD CONSTRAINT "sresource_sresourceassignment" FOREIGN KEY ("s_resource_id") REFERENCES "s_resource" ("s_resource_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourcetype"
    ADD CONSTRAINT "ctaxcategory_sresourcetype" FOREIGN KEY ("c_taxcategory_id") REFERENCES "c_taxcategory" ("c_taxcategory_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourcetype"
    ADD CONSTRAINT "cuom_sresourcetype" FOREIGN KEY ("c_uom_id") REFERENCES "c_uom" ("c_uom_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourcetype"
    ADD CONSTRAINT "mprodcategory_sresourcetype" FOREIGN KEY ("m_product_category_id") REFERENCES "m_product_category" ("m_product_category_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "s_resourceunavailable"
    ADD CONSTRAINT "sresource_sresunavailable" FOREIGN KEY ("s_resource_id") REFERENCES "s_resource" ("s_resource_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "stimeexpense_line" FOREIGN KEY ("s_timeexpense_id") REFERENCES "s_timeexpense" ("s_timeexpense_id") DEFERRABLE INITIALLY DEFERRED;

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

ALTER TABLE "tire_storage"
    ADD CONSTRAINT "aduser_tirestorage" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "tire_storage"
    ADD CONSTRAINT "cbpartner_tirestorage" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "tire_storage"
    ADD CONSTRAINT "mlocator_tirestorage" FOREIGN KEY ("m_locator_id") REFERENCES "m_locator" ("m_locator_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "adpinstance_taging" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cactivity_taging" FOREIGN KEY ("c_activity_id") REFERENCES "c_activity" ("c_activity_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cbpartner_taging" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cbpgroup_taging" FOREIGN KEY ("c_bp_group_id") REFERENCES "c_bp_group" ("c_bp_group_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "ccampaign_taging" FOREIGN KEY ("c_campaign_id") REFERENCES "c_campaign" ("c_campaign_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "ccurrency_taging" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_aging"
    ADD CONSTRAINT "cproject_taging" FOREIGN KEY ("c_project_id") REFERENCES "c_project" ("c_project_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "cbpartner_tdrdetail" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "cbpartnerlocation_tdrdetail" FOREIGN KEY ("c_bpartner_location_id") REFERENCES "c_bpartner_location" ("c_bpartner_location_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionlist_tdrdetail" FOREIGN KEY ("m_distributionlist_id") REFERENCES "m_distributionlist" ("m_distributionlist_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionlline_tdrdetail" FOREIGN KEY ("m_distributionlistline_id") REFERENCES "m_distributionlistline" ("m_distributionlistline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionrun_tdrdetail" FOREIGN KEY ("m_distributionrun_id") REFERENCES "m_distributionrun" ("m_distributionrun_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mdistributionrline_tdrdetail" FOREIGN KEY ("m_distributionrunline_id") REFERENCES "m_distributionrunline" ("m_distributionrunline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_distributionrundetail"
    ADD CONSTRAINT "mproduct_tdrdetail" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "adpinstance_tinventoryvalue" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "ccurrency_tinventoryvalue" FOREIGN KEY ("c_currency_id") REFERENCES "c_currency" ("c_currency_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "masi_tinventoryvalue" FOREIGN KEY ("m_attributesetinstance_id") REFERENCES "m_attributesetinstance" ("m_attributesetinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mcostelement_tinventoryvalue" FOREIGN KEY ("m_costelement_id") REFERENCES "m_costelement" ("m_costelement_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mplversion_tinventoryvalue" FOREIGN KEY ("m_pricelist_version_id") REFERENCES "m_pricelist_version" ("m_pricelist_version_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mproduct_tinventoryvalue" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_inventoryvalue"
    ADD CONSTRAINT "mwarehouse_tinventoryvalue" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "adpinstance_tinvoicegl" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "cconversiontype_tinvoicegl" FOREIGN KEY ("c_conversiontypereval_id") REFERENCES "c_conversiontype" ("c_conversiontype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "cdoctype_tinvoicegl" FOREIGN KEY ("c_doctypereval_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "cinvoice_tinvoicegl" FOREIGN KEY ("c_invoice_id") REFERENCES "c_invoice" ("c_invoice_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_invoicegl"
    ADD CONSTRAINT "factacct_tinvoicegl" FOREIGN KEY ("fact_acct_id") REFERENCES "fact_acct" ("fact_acct_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "adpinstance_treplenish" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "cdoctype_treplenish" FOREIGN KEY ("c_doctype_id") REFERENCES "c_doctype" ("c_doctype_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "mproduct_treplenish" FOREIGN KEY ("m_product_id") REFERENCES "m_product" ("m_product_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "mwarehouse_treplenish" FOREIGN KEY ("m_warehouse_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_replenish"
    ADD CONSTRAINT "mwarehousesource_treplenish" FOREIGN KEY ("m_warehousesource_id") REFERENCES "m_warehouse" ("m_warehouse_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_report"
    ADD CONSTRAINT "adpinstance_treport" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_report"
    ADD CONSTRAINT "pareportline_treport" FOREIGN KEY ("pa_reportline_id") REFERENCES "pa_reportline" ("pa_reportline_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_reportstatement"
    ADD CONSTRAINT "adpinstance_treportstatement" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_spool"
    ADD CONSTRAINT "adpinstance_tspool" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_transaction"
    ADD CONSTRAINT "adpinstance_ttransaction" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "mtransaction_ttransaction" FOREIGN KEY ("m_transaction_id") REFERENCES "m_transaction" ("m_transaction_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "t_trialbalance"
    ADD CONSTRAINT "ad_pinstance_t_trialbalance" FOREIGN KEY ("ad_pinstance_id") REFERENCES "ad_pinstance" ("ad_pinstance_id") DEFERRABLE INITIALLY DEFERRED;

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
    ADD CONSTRAINT "wclickcount_wclick" FOREIGN KEY ("w_clickcount_id") REFERENCES "w_clickcount" ("w_clickcount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_clickcount"
    ADD CONSTRAINT "cbpartner_wclickcount" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_counter"
    ADD CONSTRAINT "aduser_wcounter" FOREIGN KEY ("ad_user_id") REFERENCES "ad_user" ("ad_user_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_counter"
    ADD CONSTRAINT "wcountercount_wcounter" FOREIGN KEY ("w_countercount_id") REFERENCES "w_countercount" ("w_countercount_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_countercount"
    ADD CONSTRAINT "cbpartner_wcountercount" FOREIGN KEY ("c_bpartner_id") REFERENCES "c_bpartner" ("c_bpartner_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_mailmsg"
    ADD CONSTRAINT "wstore_wmailmsg" FOREIGN KEY ("w_store_id") REFERENCES "w_store" ("w_store_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_mailmsg_trl"
    ADD CONSTRAINT "adlanguage_wmailmsgtrl" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_mailmsg_trl"
    ADD CONSTRAINT "wmailmsg_wmailmsgtrl" FOREIGN KEY ("w_mailmsg_id") REFERENCES "w_mailmsg" ("w_mailmsg_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_store"
    ADD CONSTRAINT "adclient_wstore" FOREIGN KEY ("ad_client_id") REFERENCES "ad_client" ("ad_client_id") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_store_trl"
    ADD CONSTRAINT "wstoretrl_adlangauge" FOREIGN KEY ("ad_language") REFERENCES "ad_language" ("ad_language") DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE "w_store_trl"
    ADD CONSTRAINT "wstore_wstoretrl" FOREIGN KEY ("w_store_id") REFERENCES "w_store" ("w_store_id") DEFERRABLE INITIALLY DEFERRED;

