CREATE OR REPLACE VIEW RV_C_RFQRESPONSE
(AD_CLIENT_ID, AD_ORG_ID, C_RFQ_ID, C_RFQ_TOPIC_ID, C_BPARTNER_ID, 
 C_BPARTNER_LOCATION_ID, AD_USER_ID, C_RFQRESPONSE_ID, C_CURRENCY_ID, DATERESPONSE, 
 DATEWORKSTART, DELIVERYDAYS, DATEWORKCOMPLETE, PRICE, RANKING, 
 ISSELFSERVICE, DESCRIPTION, HELP, M_PRODUCT_ID, M_ATTRIBUTESETINSTANCE_ID, 
 LINE, LINEDATEWORKSTART, LINEDELIVERYDAYS, LINEDATEWORKCOMPLETE, LINEDESCRIPTION, 
 LINEHELP, C_UOM_ID, QTY, BENCHMARKPRICE, BENCHMARKDIFFERENCE, 
 QTYPRICE, DISCOUNT, QTYRANKING)
AS 
SELECT q.AD_Client_ID, q.AD_Org_ID, q.C_RfQ_ID, q.C_RfQ_Topic_ID,
    r.C_BPartner_ID, r.C_BPartner_Location_ID, r.AD_User_ID,
    r.C_RfQResponse_ID,
    r.C_Currency_ID, r.DateResponse, r.DateWorkStart, r.DeliveryDays, r.DateWorkComplete,
    r.Price, r.Ranking, r.IsSelfService,
    r.Description, r.Help,
    --  Line
    ql.M_Product_ID, ql.M_AttributeSetInstance_ID, 	
    ql.Line, rl.DateWorkStart AS LineDateWorkStart, rl.DeliveryDays AS LineDeliveryDays, rl.DateWorkComplete AS LineDateworkComplete,
    rl.Description AS LineDescription, rl.Help AS LineHelp,
    --  Qty
    qlq.C_UOM_ID, qlq.Qty, qlq.BenchmarkPrice, rlq.Price-qlq.BenchmarkPrice AS BenchmarkDifference,
    rlq.Price AS QtyPrice, rlq.Discount, rlq.Ranking AS QtyRanking
FROM C_RfQ q
    INNER JOIN C_RfQLine ql ON (q.C_RfQ_ID = ql.C_RfQ_ID)
    INNER JOIN C_RfQLineQty qlq ON (ql.C_RfQLine_ID = qlq.C_RfQLine_ID)
    INNER JOIN C_RfQResponse r ON (q.C_RfQ_ID = r.C_RfQ_ID)
    INNER JOIN C_RfQResponseLine rl ON 
        (r.C_RfQResponse_ID = rl.C_RfQResponse_ID AND ql.C_RfQLine_ID = rl.C_RfQLine_ID)
    INNER JOIN C_RfQResponseLineQty rlq ON 
        (rl.C_RfQResponseLine_ID = rlq.C_RfQResponseLine_ID AND qlq.C_RfQLineQty_ID = rlq.C_RfQLineQty_ID)
WHERE r.IsComplete='Y'
    AND q.Processed='N';



