CREATE OR REPLACE VIEW RV_C_RFQ_UNANSWERED
(AD_CLIENT_ID, AD_ORG_ID, C_RFQ_ID, NAME, DESCRIPTION, 
 HELP, SALESREP_ID, C_RFQ_TOPIC_ID, QUOTETYPE, ISQUOTETOTALAMT, 
 ISQUOTEALLQTY, C_CURRENCY_ID, DATERESPONSE, ISRFQRESPONSEACCEPTED, DATEWORKSTART, 
 DELIVERYDAYS, DATEWORKCOMPLETE, C_BPARTNER_ID, C_BPARTNER_LOCATION_ID, AD_USER_ID)
AS 
SELECT q.AD_Client_ID, q.AD_Org_ID, q.C_RfQ_ID, q.Name, q.Description, q.Help, q.SalesRep_ID,
    q.C_RfQ_Topic_ID, q.QuoteType, q.IsQuoteTotalAmt, q.IsQuoteAllQty, q.C_Currency_ID,
    q.DateResponse, q.IsRfQResponseAccepted, q.DateWorkStart, q.DeliveryDays, q.DateWorkComplete,
    r.C_BPartner_ID, r.C_BPartner_Location_ID, r.AD_User_ID
FROM C_RfQ q
    INNER JOIN C_RfQResponse r ON (q.C_RfQ_ID=r.C_RfQ_ID)
WHERE r.IsComplete='N'
    AND q.Processed='N';



