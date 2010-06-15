-- Apr 19, 2010 2:44:05 PM CEST
-- BF [2989334] - Wrong SQL for Reference: RelType M_InOut (Shipment) <= C_Or
-- https://sourceforge.net/tracker/?func=detail&aid=2989334&group_id=176962&atid=879332
UPDATE AD_Ref_Table SET WhereClause='M_InOut_ID IN (
  select i.M_InOut_ID from M_InOut i
    left join M_InOutline il on il.M_InOut_ID = i.M_InOut_ID
    left join C_OrderLine ol on ol.C_OrderLine_ID = il.C_OrderLine_ID
  where ol.C_Order_ID=@C_Order_ID@ AND i.MovementType IN (''C-'')
)',Updated=TO_TIMESTAMP('2010-04-19 14:44:05','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Reference_ID=53347
;

