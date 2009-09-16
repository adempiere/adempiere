DROP VIEW rv_pp_cost_bomline
;
CREATE OR REPLACE VIEW T_BOMLine_Costs
  AS 
    SELECT  t.seqno, t.levelno, t.levels, t.ad_client_id,t.C_AcctSchema_ID,
            t.ad_org_id, t.createdby, t.updatedby, t.updated,
            t.created, t.ad_pinstance_id, t.implosion, t.sel_product_id as m_product_id,t.m_costelement_id, t.currentcostprice,currentcostpricell,t.qtybom, t.currentcostprice + currentcostpricell as cost,
            bl.isactive, bl.pp_product_bom_id, bl.pp_product_bomline_id, bl.description, 
            bl.iscritical, bl.componenttype, t.m_product_id as tm_product_id, bl.c_uom_id,
            bl.issuemethod, bl.line, bl.m_attributesetinstance_id, bl.scrap,
            bl.validfrom, bl.validto, bl.isqtypercentage
       FROM t_bomline t LEFT OUTER JOIN pp_product_bomline bl 
            ON t.pp_product_bomline_id = bl.pp_product_bomline_id 
;





delete from AD_Sequence where AD_Sequence_ID IN (53252, 53253);

-- 27.01.2009 12:41:16 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Table SET TableName='T_BOMLine_Costs',Updated=TO_DATE('2009-01-27 12:41:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53161
;

-- 27.01.2009 12:41:18 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
INSERT INTO AD_Sequence (AD_Client_ID,AD_Org_ID,AD_Sequence_ID,Created,CreatedBy,CurrentNext,CurrentNextSys,Description,IncrementNo,IsActive,IsAudited,IsAutoSequence,IsTableID,Name,StartNewYear,StartNo,Updated,UpdatedBy) VALUES (0,0,53255,TO_DATE('2009-01-27 12:41:16','YYYY-MM-DD HH24:MI:SS'),0,1500000,50000,'Table T_BOMLine_Costs',1,'Y','N','Y','Y','T_BOMLine_Costs','N',1500000,TO_DATE('2009-01-27 12:41:16','YYYY-MM-DD HH24:MI:SS'),0)
;

-- 27.01.2009 12:42:49 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_ReportView SET Name='T_BOMLine_Costs',Updated=TO_DATE('2009-01-27 12:42:49','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_ReportView_ID=53025
;

-- 27.01.2009 12:47:11 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Process SET AD_ReportView_ID=53025, IsReport='Y',Updated=TO_DATE('2009-01-27 12:47:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53159
;

-- 27.01.2009 13:00:22 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_PrintFormat SET IsDefault='Y',Updated=TO_DATE('2009-01-27 13:00:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50038
;

-- 27.01.2009 13:00:30 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_PrintFormat SET AD_PrintTableFormat_ID=NULL,Updated=TO_DATE('2009-01-27 13:00:30','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_PrintFormat_ID=50038
;

-- 27.01.2009 14:52:31 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56633
;

-- 27.01.2009 14:52:34 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56634
;

-- 27.01.2009 14:52:35 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:35','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56639
;

-- 27.01.2009 14:52:36 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56662
;

-- 27.01.2009 14:52:36 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56652
;

-- 27.01.2009 14:52:38 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56646
;

-- 27.01.2009 14:52:39 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56638
;

-- 27.01.2009 14:52:39 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:39','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56635
;

-- 27.01.2009 14:52:40 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:40','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56654
;

-- 27.01.2009 14:52:41 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56643
;

-- 27.01.2009 14:52:41 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:41','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56644
;

-- 27.01.2009 14:52:42 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56650
;

-- 27.01.2009 14:52:43 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:43','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56640
;

-- 27.01.2009 14:52:44 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56647
;

-- 27.01.2009 14:52:44 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:44','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56651
;

-- 27.01.2009 14:52:52 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:52','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56661
;

-- 27.01.2009 14:52:53 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56655
;

-- 27.01.2009 14:52:53 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56631
;

-- 27.01.2009 14:52:54 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56632
;

-- 27.01.2009 14:52:54 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:54','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56656
;

-- 27.01.2009 14:52:55 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56657
;

-- 27.01.2009 14:52:55 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:55','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56642
;

-- 27.01.2009 14:52:56 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56641
;

-- 27.01.2009 14:52:57 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56648
;

-- 27.01.2009 14:52:57 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:57','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56649
;

-- 27.01.2009 14:52:58 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56645
;

-- 27.01.2009 14:52:58 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:58','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56658
;

-- 27.01.2009 14:52:59 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56630
;

-- 27.01.2009 14:52:59 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:52:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56653
;

-- 27.01.2009 14:53:00 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:53:00','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56637
;

-- 27.01.2009 14:53:01 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:53:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56659
;

-- 27.01.2009 14:53:02 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:53:02','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56636
;

-- 27.01.2009 14:53:06 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 14:53:06','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56660
;

-- 27.01.2009 15:02:56 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:02:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54072
;

-- 27.01.2009 15:03:01 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:01','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54073
;

-- 27.01.2009 15:03:03 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:03','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54074
;

-- 27.01.2009 15:03:05 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56663
;

-- 27.01.2009 15:03:07 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:07','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56629
;

-- 27.01.2009 15:03:08 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:08','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54075
;

-- 27.01.2009 15:03:09 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:09','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54076
;

-- 27.01.2009 15:03:11 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:11','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56626
;

-- 27.01.2009 15:03:16 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:16','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56627
;

-- 27.01.2009 15:03:18 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:18','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55324
;

-- 27.01.2009 15:03:19 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:19','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54077
;

-- 27.01.2009 15:03:20 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:20','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54078
;

-- 27.01.2009 15:03:21 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54079
;

-- 27.01.2009 15:03:22 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:22','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56625
;

-- 27.01.2009 15:03:24 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:24','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54080
;

-- 27.01.2009 15:03:25 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:25','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54082
;

-- 27.01.2009 15:03:26 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54081
;

-- 27.01.2009 15:03:28 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56628
;

-- 27.01.2009 15:03:29 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=55325
;

-- 27.01.2009 15:03:31 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:31','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54083
;

-- 27.01.2009 15:03:34 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N', IsUpdateable='N',Updated=TO_DATE('2009-01-27 15:03:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54084
;

-- 27.01.2009 15:03:36 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:36','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54085
;

-- 27.01.2009 15:03:38 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET IsAllowLogging='N',Updated=TO_DATE('2009-01-27 15:03:38','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=54086
;

-- 27.01.2009 15:06:29 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Table SET AccessLevel='3',Updated=TO_DATE('2009-01-27 15:06:29','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Table_ID=53045
;

-- 27.01.2009 15:18:34 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Column SET AD_Reference_Value_ID=53225,Updated=TO_DATE('2009-01-27 15:18:34','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Column_ID=56652
;

-- 27.01.2009 16:15:15 EET
-- BF [ 2539976 ] Rename view RV_PP_Cost_BOMLine to T_BOMLine_Costs
UPDATE AD_Process SET Classname='org.eevolution.report.CostBillOfMaterial', Value='PP_CostBillOfMaterial',Updated=TO_DATE('2009-01-27 16:15:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=0 WHERE AD_Process_ID=53159
;

