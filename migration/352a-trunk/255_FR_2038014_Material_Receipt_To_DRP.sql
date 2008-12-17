-- Aug 4, 2008 3:37:34 PM CDT
-- DRP Functionality
INSERT INTO AD_Form (AD_Client_ID,AD_Form_ID,AD_Org_ID,AccessLevel,Classname,Created,CreatedBy,Description,EntityType,IsActive,IsBetaFunctionality,Name,Updated,UpdatedBy) VALUES (0,53012,0,'3','org.eevolution.form.VOrderDistributionReceipt',TO_DATE('2008-08-04 15:37:25','YYYY-MM-DD HH24:MI:SS'),0,'Material Receipt Distribution Order','EE01','Y','N','Material Receipt Distribution Order',TO_DATE('2008-08-04 15:37:25','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 4, 2008 3:37:34 PM CDT
-- DRP Functionality
INSERT INTO AD_Form_Trl (AD_Language,AD_Form_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Form_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Form t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Form_ID=53012 AND EXISTS (SELECT * FROM AD_Form_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Form_ID!=t.AD_Form_ID)
;

-- Aug 4, 2008 3:37:34 PM CDT
-- DRP Functionality
INSERT INTO AD_Form_Access (AD_Client_ID,AD_Form_ID,AD_Org_ID,AD_Role_ID,Created,CreatedBy,IsActive,Updated,UpdatedBy) VALUES (0,53012,0,0,TO_DATE('2008-08-04 15:37:34','YYYY-MM-DD HH24:MI:SS'),0,'Y',TO_DATE('2008-08-04 15:37:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 4, 2008 3:37:35 PM CDT
-- DRP Functionality
INSERT INTO AD_Menu (AD_Client_ID,AD_Form_ID,AD_Menu_ID,AD_Org_ID,Action,Created,CreatedBy,Description,EntityType,IsActive,IsReadOnly,IsSOTrx,IsSummary,Name,Updated,UpdatedBy) VALUES (0,53012,53188,0,'X',TO_DATE('2008-08-04 15:37:34','YYYY-MM-DD HH24:MI:SS'),0,'Material Receipt Distribution Order','EE01','Y','N','N','N','Material Receipt Distribution Order',TO_DATE('2008-08-04 15:37:34','YYYY-MM-DD HH24:MI:SS'),0)
;

-- Aug 4, 2008 3:37:35 PM CDT
-- DRP Functionality
INSERT INTO AD_Menu_Trl (AD_Language,AD_Menu_ID, Description,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Menu_ID, t.Description,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Menu t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Menu_ID=53188 AND EXISTS (SELECT * FROM AD_Menu_Trl tt WHERE tt.AD_Language!=l.AD_Language OR tt.AD_Menu_ID!=t.AD_Menu_ID)
;

-- Aug 4, 2008 3:37:35 PM CDT
-- DRP Functionality
Insert INTO AD_TREENODEMM(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, Parent_ID, SeqNo, AD_Tree_ID, Node_ID)VALUES(0, 0, 0, 0, 53066,12, 10, 53188)
;

--DROP VIEW M_Movement_Candidate_v;
CREATE OR REPLACE VIEW M_Movement_Candidate_v AS
SELECT	
	o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.DD_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID, 
    o.POReference, o.Description, o.SalesRep_ID,
    l.M_Locator_ID, l.M_LocatorTo_ID
	--l.ConfirmedQty 
	--SUM((l.QtyOrdered-l.QtyDelivered)*l.PriceActual) AS TotalLines
FROM DD_Order o
INNER JOIN DD_OrderLine l ON (o.DD_Order_ID=l.DD_Order_ID)
--INNER JOIN M_Locator loc ON (loc.M_Locator_ID=l.M_Locator_ID) 
WHERE	(o.DocStatus = 'CO' AND o.IsDelivered='N')  --  Status must be CO - not CL/RE
	--	not Offers and open Walkin-Receipts
	AND o.C_DocType_ID IN (SELECT C_DocType_ID FROM C_DocType
		WHERE DocBaseType='DOO')
    --  Delivery Rule - not manual
    AND o.DeliveryRule<>'M'
    AND (l.M_Product_ID IS NULL OR EXISTS 
        (SELECT * FROM M_Product p 
        WHERE l.M_Product_ID=p.M_Product_ID AND p.IsExcludeAutoDelivery='N'))
	--	we need to ship
	AND	l.QtyOrdered <> l.QtyDelivered  AND ConfirmedQty > 0
	AND o.IsDropShip='N'
    AND (l.M_Product_ID IS NOT NULL OR l.C_Charge_ID IS NOT NULL)
    --  Not confirmed shipment
    AND NOT EXISTS (SELECT * FROM M_MovementLine iol 
        INNER JOIN M_Movement io ON (iol.M_Movement_ID=io.M_Movement_ID)
        WHERE iol.DD_OrderLine_ID=l.DD_OrderLine_ID AND io.DocStatus IN ('IP','WC'))
	--
GROUP BY o.AD_Client_ID, o.AD_Org_ID, o.C_BPartner_ID, o.DD_Order_ID,
	o.DocumentNo, o.DateOrdered, o.C_DocType_ID,
    o.POReference, o.Description, o.SalesRep_ID, l.M_Locator_ID, l.M_LocatorTo_ID;


