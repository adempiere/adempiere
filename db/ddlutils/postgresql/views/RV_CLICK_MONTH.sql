CREATE OR REPLACE VIEW RV_CLICK_MONTH
(AD_CLIENT_ID, AD_ORG_ID, NAME, DESCRIPTION, TARGETURL, 
 C_BPARTNER_ID, CREATED, COUNTER)
AS 
SELECT cc.AD_Client_ID, cc.AD_Org_ID,
    cc.Name, cc.Description, cc.TargetURL, cc.C_BPartner_ID, 
    firstOf(c.Created,'MM') AS Created,
    COUNT(*) AS Counter
FROM W_ClickCount cc
  INNER JOIN W_Click c ON (cc.W_ClickCount_ID=c.W_ClickCount_ID)
WHERE cc.IsActive='Y'
GROUP BY cc.AD_Client_ID, cc.AD_Org_ID,
    cc.Name, cc.Description, cc.TargetURL, cc.C_BPartner_ID,
    firstOf(c.Created,'MM');



