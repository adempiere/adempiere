/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_BPartner_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Check C_BPartner
 * Description:
 *	For C_BPartner create  
 *	-	Missing BPartner Tree Structure(s)
 *
 *	Recalculate Credit Used and Lifetime Value
 ************************************************************************/
BEGIN
	DBMS_OUTPUT.PUT_LINE('Adding to Base BPartner Tree');
	DECLARE
		CURSOR	Cur_Tree IS
			SELECT	*
			FROM	AD_ClientInfo;
		CURSOR Cur_BPartner	(Client NUMBER, Tree NUMBER) IS
			SELECT *
			FROM C_BPartner
			WHERE C_BPartner_ID NOT IN 
				(SELECT Node_ID FROM AD_TreeNodeBP WHERE AD_Tree_ID=Tree)
			AND AD_Client_ID=Client;
	BEGIN
		FOR CT IN Cur_Tree LOOP
			DBMS_OUTPUT.PUT_LINE('  For Tree ' || CT.AD_Tree_BPartner_ID);
			--
			FOR CM IN Cur_BPartner (CT.AD_Client_ID, CT.AD_Tree_BPartner_ID) LOOP
				INSERT INTO AD_TreeNodeBP
					(AD_Client_ID, AD_Org_ID,
					IsActive, Created, CreatedBy, Updated, UpdatedBy,
					AD_Tree_ID, Node_ID, Parent_ID, SeqNo)
				VALUES
					(CM.AD_Client_ID, CM.AD_Org_ID, 
					CM.IsActive, CM.Created, CM.CreatedBy, CM.Updated, CM.UpdatedBy,
					CT.AD_Tree_BPartner_ID, CM.C_BPartner_ID, 0, 999);
				DBMS_OUTPUT.PUT_LINE('    added: ' || CM.NAME);
			END LOOP;	-- BPartner Loop
		END LOOP;	--	Tree Loop
	END;	-- Adding to Tree

	COMMIT;
END;
/
/**	Recalculate Credit Used & Actual Life Time Value **/
UPDATE C_BPartner bp
	SET SO_CreditUsed = 
			(SELECT SUM(C_Base_Convert(C_Invoice_Open(C_Invoice_ID), C_Currency_ID, AD_Client_ID, SysDate))
			FROM C_Invoice i -- invoice_open returns CM corrected amt
			WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y'), 
		ActualLifeTimeValue = 
			(SELECT SUM(C_Base_Convert(GrandTotal, C_Currency_ID, AD_Client_ID, SysDate))
			FROM C_Invoice_v i 
			WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y')
WHERE EXISTS 
	(SELECT * FROM C_Invoice i 
	WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y');
COMMIT;

--	Zero
UPDATE C_BPartner bp
	SET SO_CreditUsed = 0,
		ActualLifeTimeValue = 0
WHERE NOT EXISTS 
	(SELECT * FROM C_Invoice i 
	WHERE i.C_BPartner_ID=bp.C_BPartner_ID AND i.IsSOTrx='Y');

COMMIT;


