-- globalqss - fix entitytype for 2pack dictionary, wrongly was created as type U, must be D 

update ad_column set entitytype='D' where ad_column_id between 50001 and 50179;

update ad_element set entitytype='D' where ad_element_id between 50001 and 50036;

update ad_field set entitytype='D' where ad_field_id between 50001 and 50153;

update ad_menu set entitytype='D' where ad_menu_id between 50001 and 50007;

update ad_process set entitytype='D' where ad_process_id between 50002 and 50010;

update ad_process_para set entitytype='D' where ad_process_para_id between 50001 and 50004;

update ad_ref_list set entitytype='D' where ad_ref_list_id between 50001 and 50035;

update ad_reference set entitytype='D' where ad_reference_id between 50001 and 50005;

update ad_tab set entitytype='D' where ad_tab_id between 50001 and 50008;

update ad_table set entitytype='D' where ad_table_id between 50001 and 50008;

update ad_window set entitytype='D' where ad_window_id between 50001 and 50005;

commit;