/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Product_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	M_Product check
 * Description:
 *	- Activate Products with QtyOnHand/Reserved/Ordered
 *	- For M_Product create missing
 *		-	Costing Records
 *		-	Translations
 *		-	Product Tree Structure(s)
 *	- Update Product_PO Statistics
 ************************************************************************/

--	Activate Products with QtyOnHand/Reserved/Ordered
UPDATE M_Product p
	SET IsActive='Y'
WHERE IsActive='N'
  AND (SELECT NVL(SUM(QtyOnHand)+SUM(QtyReserved)*.111+SUM(QtyOrdered)*999, 0)
		FROM M_Storage s WHERE s.M_Product_ID=p.M_Product_ID) <> 0
/

--	Insert missing Costing Records
INSERT INTO M_Product_Costing
	(M_Product_ID, C_AcctSchema_ID,
	AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
	CurrentCostPrice, CostStandard, FutureCostPrice, 
	CostStandardPOQty,CostStandardPOAmt,CostStandardCumQty,CostStandardCumAmt,
	CostAverage, CostAverageCumQty, CostAverageCumAmt,
	PriceLastPO, PriceLastInv,
	TotalInvQty, TotalInvAmt)
SELECT	p.M_Product_ID, a.C_AcctSchema_ID,
	p.AD_Client_ID, p.AD_Org_ID, 'Y', p.Created, p.CreatedBy, SysDate, 0,
	0,0,0, 0,0,0,0, 0,0,0, 0,0, 0,0
FROM C_AcctSchema a, M_Product p
WHERE	p.AD_Client_ID=a.AD_Client_ID
  AND NOT EXISTS
	(SELECT * FROM M_Product_Costing pc 
	WHERE pc.C_AcctSchema_ID=a.C_AcctSchema_ID 
	  AND pc.M_Product_ID=p.M_Product_ID
	  AND p.AD_Client_ID=a.AD_Client_ID)
/

--	COMMIT
COMMIT
/

/**	Insert missing Translations
INSERT INTO M_Product_Trl (M_Product_ID, AD_Language, AD_Client_ID, AD_Org_ID,
	IsActive, Created, CreatedBy, Updated, UpdatedBy,
	Name, DocumentNote, IsTranslated)
SELECT m.M_Product_ID, l.AD_Language, m.AD_Client_ID, m.AD_Org_ID,
	m.IsActive, m.Created, m.CreatedBy, m.Updated, m.UpdatedBy,
	m.Name, m.DocumentNote, 'N'
FROM	AD_Language l, M_Product m 
WHERE	l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y'
AND	M_Product_ID || AD_Language NOT IN 
	(SELECT M_Product_ID || AD_Language FROM M_Product_Trl)
AND EXISTS (SELECT * FROM AD_Client 
	WHERE AD_Client_ID=m.AD_Client_ID AND IsMultiLingualDocument='Y');
**/
BEGIN
    DECLARE
        CURSOR Cur_Lang IS
			SELECT l.AD_Language, c.AD_Client_ID 
			FROM adempiere.AD_Language l, adempiere.AD_Client c
			WHERE l.IsActive = 'Y' AND l.IsSystemLanguage = 'Y' 
			AND c.ISACTIVE = 'Y' AND c.ISMULTILINGUALDOCUMENT = 'Y';
	BEGIN
    	DBMS_OUTPUT.PUT_LINE('Adding to Translation');
		FOR CL IN Cur_Lang LOOP
			INSERT INTO M_Product_Trl 
				(M_Product_ID, AD_Language, AD_Client_ID, 
				AD_Org_ID, IsActive, Created, CreatedBy, Updated, 
				UpdatedBy, Name, DocumentNote, IsTranslated)
			SELECT m.M_Product_ID, CL.AD_Language, CL.AD_Client_ID, 
				m.AD_Org_ID, m.IsActive, m.Created, m.CreatedBy, 
				m.Updated, m.UpdatedBy,	m.Name, m.DocumentNote, 'N'
			FROM M_Product m 
			WHERE m.AD_Client_ID = CL.AD_Client_ID 
			  AND M_Product_ID NOT IN 
				(SELECT M_Product_ID FROM M_Product_Trl 
				WHERE AD_Language = CL.AD_Language);
		    DBMS_OUTPUT.PUT('.');
		END LOOP;
		COMMIT;
	END;
	COMMIT;
END;
/

--	Add missing Tree Nodes
BEGIN
	DBMS_OUTPUT.PUT_LINE('Adding to Base Product Tree');
	DECLARE
		CURSOR	Cur_Tree IS
			SELECT	*
			FROM	AD_ClientInfo;
		CURSOR Cur_Product (Client NUMBER, Tree NUMBER) IS
			SELECT *
			FROM M_Product
			WHERE M_Product_ID NOT IN 
				(SELECT Node_ID FROM AD_TreeNodePR WHERE AD_Tree_ID=Tree)
			AND AD_Client_ID=Client;
	BEGIN
		FOR CT IN Cur_Tree LOOP
			DBMS_OUTPUT.PUT_LINE('  For Tree ' || CT.AD_Tree_Product_ID
				|| ' Client=' || CT.AD_Client_ID);
			FOR CP IN Cur_Product (CT.AD_Client_ID, CT.AD_Tree_Product_ID) LOOP
				INSERT INTO AD_TreeNodePR
					(AD_Client_ID, AD_Org_ID,
					IsActive, Created, CreatedBy, Updated, UpdatedBy,
					AD_Tree_ID, Node_ID, Parent_ID, SeqNo)
				VALUES
					(CP.AD_Client_ID, CP.AD_Org_ID, 
					CP.IsActive, CP.Created, CP.CreatedBy, CP.Updated, CP.UpdatedBy,
					CT.AD_Tree_Product_ID, CP.M_Product_ID, 0, 999);
				DBMS_OUTPUT.PUT_LINE('    added: ' || CP.NAME);
			END LOOP;	-- Product Loop
		END LOOP;	--	Tree Loop
		COMMIT;
	END;	-- Adding to Tree
END;
/

--	Product_PO Update
DECLARE
	CURSOR	Cur_PO	IS
		SELECT	*
		FROM	M_Product_PO
		FOR UPDATE OF PriceLastPO, PriceLastInv;
	--
	v_PriceLastPO		NUMBER;
	v_PriceLastInv		NUMBER;
	v_no				NUMBER := 0;
BEGIN
	FOR po IN Cur_PO LOOP
		SELECT	NVL(MAX (C_Currency_Convert (ol.PriceActual, 
			o.C_Currency_ID, po.C_Currency_ID, o.DateOrdered, null)), 0)
		  INTO	v_PriceLastPO
		FROM	C_Order o, C_OrderLine ol
		WHERE	o.C_BPartner_ID=po.C_BPartner_ID
		  AND	ol.M_Product_ID=po.M_Product_ID
		  AND	o.C_Order_ID=ol.C_Order_ID
		  AND	o.IsSOTrx='N' AND ROWNUM=1
		ORDER BY o.DateOrdered DESC;

		SELECT	NVL(MAX(C_Currency_Convert (il.PriceActual,
			i.C_Currency_ID, po.C_Currency_ID, i.DateInvoiced, null)), 0)
		  INTO	v_PriceLastInv
		FROM	C_Invoice i, C_InvoiceLine il	--	no need to use _v
		WHERE	i.C_BPartner_ID=po.C_BPartner_ID
		  AND	il.M_Product_ID=po.M_Product_ID
		  AND	i.C_Invoice_ID=il.C_Invoice_ID
		  AND	i.IsSOTrx='N' AND ROWNUM=1
		ORDER BY i.DateInvoiced DESC;

		UPDATE	M_Product_PO
		  SET	PriceLastPO = v_PriceLastPO,
				PriceLastInv = v_PriceLastInv
		WHERE CURRENT OF Cur_PO;
		v_no := v_no + 1;
	END LOOP;
	COMMIT;
	DBMS_OUTPUT.PUT_LINE('Product_PO Rows=' || v_no);
END;
/

--	Product_Costing Update
DECLARE
	CURSOR	Cur_Costing	IS
		SELECT	*
		FROM	M_Product_Costing
		FOR UPDATE;
	--
	v_PriceLastPO		NUMBER;
	v_PriceLastInv		NUMBER;
	v_no				NUMBER := 0;
	v_C_Currency_ID		NUMBER;
BEGIN
	FOR c IN Cur_Costing LOOP
		--	Get Currency
		SELECT	C_Currency_ID
		  INTO	v_C_Currency_ID
		FROM	C_AcctSchema
		WHERE	C_AcctSchema_ID=c.C_AcctSchema_ID;
		--
		SELECT	NVL(MAX (C_Currency_Convert (ol.PriceActual,
			o.C_Currency_ID, v_C_Currency_ID, o.DateOrdered, null)), 0)
		  INTO	v_PriceLastPO
		FROM	C_Order o, C_OrderLine ol
		WHERE	ol.M_Product_ID=c.M_Product_ID
		  AND	o.C_Order_ID=ol.C_Order_ID
		  AND	o.IsSOTrx='N' AND ROWNUM=1
		ORDER BY o.DateOrdered DESC;

		SELECT	NVL(MAX (C_Currency_Convert (il.PriceActual,
			i.C_Currency_ID, v_C_Currency_ID, i.DateInvoiced, null)), 0)
		  INTO	v_PriceLastInv
		FROM	C_Invoice i, C_InvoiceLine il	-- no need to use _v
		WHERE	il.M_Product_ID=c.M_Product_ID
		  AND	i.C_Invoice_ID=il.C_Invoice_ID
		  AND	i.IsSOTrx='N' AND ROWNUM=1
		ORDER BY i.DateInvoiced DESC;

		UPDATE	M_Product_Costing
		  SET	PriceLastPO = v_PriceLastPO,
				PriceLastInv = v_PriceLastInv
		WHERE CURRENT OF Cur_Costing;
		v_no := v_no + 1;
	END LOOP;
	COMMIT;
	DBMS_OUTPUT.PUT_LINE('Product_Costing Rows=' || v_no);
END;
/

--	Product Costing Update
UPDATE	M_Product_Costing c
	SET	
		--	Not corrected for receipts
--		CostStandardPOAmt = 
--		(SELECT	NVL(SUM (C_Currency_Convert (ol.LineNetAmt, 
--			o.C_Currency_ID, acct.C_Currency_ID, o.DateOrdered, null)), 0)
--		FROM 	C_Order o, C_OrderLine ol, C_AcctSchema acct
--		WHERE 	o.C_Order_ID=ol.C_Order_ID
--		  AND	o.IsSOTrx='N'
--		  AND	c.C_AcctSchema_ID=acct.C_AcctSchema_ID
--		  AND	c.M_Product_ID=ol.M_Product_ID),
		--	Not corrected for Receipts
--		CostStandardPOQty =
--		(SELECT	NVL(SUM (C_UOM_Convert(ol.QtyOrdered, ol.C_UOM_ID, p.C_UOM_ID, 'N')), 0)
--		FROM 	C_Order o, C_OrderLine ol, M_Product p
--		WHERE 	o.C_Order_ID=ol.C_Order_ID
--		  AND	o.IsSOTrx='N'
--		  AND	p.M_Product_ID=ol.M_Product_ID
--		  AND	c.M_Product_ID=ol.M_Product_ID),
		--
		CostStandardCumAmt = 
		(SELECT	NVL(SUM (C_Currency_Convert (ol.LineNetAmt, 
			o.C_Currency_ID, acct.C_Currency_ID, o.DateOrdered, null)), 0)
		FROM	C_Order o, C_OrderLine ol, C_AcctSchema acct
		WHERE	o.C_Order_ID=ol.C_Order_ID
		  AND	o.IsSOTrx='N'
		  AND	c.C_AcctSchema_ID=acct.C_AcctSchema_ID
		  AND	c.M_Product_ID=ol.M_Product_ID),
		--
		CostStandardCumQty =
		(SELECT	NVL(SUM (C_UOM_Convert(ol.QtyOrdered, ol.C_UOM_ID, p.C_UOM_ID, null)), 0)
		FROM	C_Order o, C_OrderLine ol, M_Product p
		WHERE	o.C_Order_ID=ol.C_Order_ID
		  AND	o.IsSOTrx='N'
		  AND	p.M_Product_ID=ol.M_Product_ID
		  AND	c.M_Product_ID=ol.M_Product_ID),
		--	Invoice (Not corrected for receipts)
--		CostAverageCumAmt = 
--		(SELECT	NVL(SUM (C_Currency_Convert (il.LineNetAmt*i.Multiplier, 
--			i.C_Currency_ID, acct.C_Currency_ID, i.DateInvoiced, null)), 0)
--		FROM 	C_Invoice_v i, C_InvoiceLine il, C_AcctSchema acct
--		WHERE 	i.C_Invoice_ID=il.C_Invoice_ID
--		  AND	i.IsSOTrx='N'
--		  AND	c.C_AcctSchema_ID=acct.C_AcctSchema_ID
--		  AND	c.M_Product_ID=il.M_Product_ID),
		--	(Not corrected for receipts)
--		CostAverageCumQty =
--		(SELECT	NVL(SUM (C_UOM_Convert(il.QtyInvoiced*i.Multiplier, il.C_UOM_ID, p.C_UOM_ID, 'N')), 0)
--		FROM 	C_Invoice_v i, C_InvoiceLine il, M_Product p
--		WHERE 	i.C_Invoice_ID=il.C_Invoice_ID
--		  AND	i.IsSOTrx='N'
--		  AND	p.M_Product_ID=il.M_Product_ID
--		  AND	c.M_Product_ID=il.M_Product_ID),
		--
		TotalInvAmt = 
		(SELECT	NVL(SUM (C_Currency_Convert (il.LineNetAmt*i.Multiplier, 
			i.C_Currency_ID, acct.C_Currency_ID, i.DateInvoiced, null)), 0)
		FROM	C_Invoice_v i, C_InvoiceLine il, C_AcctSchema acct
		WHERE	i.C_Invoice_ID=il.C_Invoice_ID
		  AND	i.IsSOTrx='N'
		  AND	c.C_AcctSchema_ID=acct.C_AcctSchema_ID
		  AND	c.M_Product_ID=il.M_Product_ID),
		--	(UOM conversion)
		TotalInvQty =
		(SELECT	NVL(SUM (C_UOM_Convert(il.QtyInvoiced*i.Multiplier, il.C_UOM_ID, p.C_UOM_ID, 'N')), 0)
		FROM	C_Invoice_v i, C_InvoiceLine il, M_Product p
		WHERE	i.C_Invoice_ID=il.C_Invoice_ID
		  AND	i.IsSOTrx='N'
		  AND	p.M_Product_ID=il.M_Product_ID
		  AND	c.M_Product_ID=il.M_Product_ID)
/
COMMIT
/

