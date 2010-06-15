-- create temporary index
CREATE INDEX tmp_ad_wf_activity_speed ON ad_wf_activity(ad_table_id, record_id);

-- ****** SET ProcessedOn on table C_AllocationHdr ******

-- try to get the processed from ad_changelog
update C_AllocationHdr set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 735 and record_id = C_AllocationHdr.C_AllocationHdr_id and ad_column_id = 12309
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_AllocationHdr set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 735 and a.record_id = C_AllocationHdr.C_AllocationHdr_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_AllocationHdr set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table C_BankStatement ******

-- try to get the processed from ad_changelog
update C_BankStatement set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 392 and record_id = C_BankStatement.C_BankStatement_id and ad_column_id = 4924
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_BankStatement set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 392 and a.record_id = C_BankStatement.C_BankStatement_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_BankStatement set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table C_Cash ******

-- try to get the processed from ad_changelog
update C_Cash set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 407 and record_id = C_Cash.C_Cash_id and ad_column_id = 5258
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_Cash set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 407 and a.record_id = C_Cash.C_Cash_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_Cash set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table C_Invoice ******

-- try to get the processed from ad_changelog
update C_Invoice set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 318 and record_id = C_Invoice.C_Invoice_id and ad_column_id = 3497
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_Invoice set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 318 and a.record_id = C_Invoice.C_Invoice_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_Invoice set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table C_Order ******

-- try to get the processed from ad_changelog
update C_Order set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 259 and record_id = C_Order.C_Order_id and ad_column_id = 3398
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_Order set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 259 and a.record_id = C_Order.C_Order_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_Order set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table C_Payment ******

-- try to get the processed from ad_changelog
update C_Payment set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 335 and record_id = C_Payment.C_Payment_id and ad_column_id = 3878
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_Payment set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 335 and a.record_id = C_Payment.C_Payment_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_Payment set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table C_ProjectIssue ******

-- try to get the processed from ad_changelog
update C_ProjectIssue set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 623 and record_id = C_ProjectIssue.C_ProjectIssue_id and ad_column_id = 9842
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update C_ProjectIssue set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 623 and a.record_id = C_ProjectIssue.C_ProjectIssue_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update C_ProjectIssue set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table DD_Order ******

-- try to get the processed from ad_changelog
update DD_Order set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 53037 and record_id = DD_Order.DD_Order_id and ad_column_id = 53912
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update DD_Order set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 53037 and a.record_id = DD_Order.DD_Order_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update DD_Order set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table GL_Journal ******

-- try to get the processed from ad_changelog
update GL_Journal set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 224 and record_id = GL_Journal.GL_Journal_id and ad_column_id = 5953
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update GL_Journal set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 224 and a.record_id = GL_Journal.GL_Journal_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update GL_Journal set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table HR_Process ******

-- try to get the processed from ad_changelog
update HR_Process set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 53092 and record_id = HR_Process.HR_Process_id and ad_column_id = 54876
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update HR_Process set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 53092 and a.record_id = HR_Process.HR_Process_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update HR_Process set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_InOut ******

-- try to get the processed from ad_changelog
update M_InOut set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 319 and record_id = M_InOut.M_InOut_id and ad_column_id = 3518
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_InOut set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 319 and a.record_id = M_InOut.M_InOut_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_InOut set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_Inventory ******

-- try to get the processed from ad_changelog
update M_Inventory set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 321 and record_id = M_Inventory.M_Inventory_id and ad_column_id = 3553
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_Inventory set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 321 and a.record_id = M_Inventory.M_Inventory_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_Inventory set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_MatchInv ******

-- try to get the processed from ad_changelog
update M_MatchInv set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 472 and record_id = M_MatchInv.M_MatchInv_id and ad_column_id = 6511
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_MatchInv set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 472 and a.record_id = M_MatchInv.M_MatchInv_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_MatchInv set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_MatchPO ******

-- try to get the processed from ad_changelog
update M_MatchPO set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 473 and record_id = M_MatchPO.M_MatchPO_id and ad_column_id = 6527
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_MatchPO set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 473 and a.record_id = M_MatchPO.M_MatchPO_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_MatchPO set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_Movement ******

-- try to get the processed from ad_changelog
update M_Movement set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 323 and record_id = M_Movement.M_Movement_id and ad_column_id = 3580
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_Movement set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 323 and a.record_id = M_Movement.M_Movement_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_Movement set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_Production ******

-- try to get the processed from ad_changelog
update M_Production set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 325 and record_id = M_Production.M_Production_id and ad_column_id = 3609
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_Production set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 325 and a.record_id = M_Production.M_Production_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_Production set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table M_Requisition ******

-- try to get the processed from ad_changelog
update M_Requisition set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 702 and record_id = M_Requisition.M_Requisition_id and ad_column_id = 11473
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update M_Requisition set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 702 and a.record_id = M_Requisition.M_Requisition_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update M_Requisition set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table PP_Cost_Collector ******

-- try to get the processed from ad_changelog
update PP_Cost_Collector set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 53035 and record_id = PP_Cost_Collector.PP_Cost_Collector_id and ad_column_id = 53834
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update PP_Cost_Collector set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 53035 and a.record_id = PP_Cost_Collector.PP_Cost_Collector_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update PP_Cost_Collector set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';



-- ****** SET ProcessedOn on table PP_Order ******

-- try to get the processed from ad_changelog
update PP_Order set processedon =
(select max((updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_changelog
       where ad_table_id = 53027 and record_id = PP_Order.PP_Order_id and ad_column_id = 53664
             and (newvalue = 'Y' or newvalue = 'true') and (oldvalue = 'false' or oldvalue = 'NULL' or oldvalue is null))
where processedon is null and processed = 'Y';

-- if not then try to get the processed from ad_wf_activity
update PP_Order set processedon =
(select max((a.updated - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000) from ad_wf_activity a, ad_wf_node n
where a.ad_table_id = 53027 and a.record_id = PP_Order.PP_Order_id
and a.ad_wf_node_id = n.ad_wf_node_id and n.docaction = 'CO' and a.wfstate = 'CC')
where processedon is null and processed = 'Y';

-- fallback to created date
update PP_Order set processedon = (created - to_date('1970-01-01', 'YYYY-MM-DD')) * 86400000
where processedon is null and processed = 'Y';

-- drop temporary index
DROP INDEX tmp_ad_wf_activity_speed;
