/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_SalesRegion_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	C_SalesRegion_Check
 * Description:
 *	For C_SalesRegion create  
 *	-	Missing SalesRegion Tree Structure(s)
 ************************************************************************/
BEGIN
    DBMS_OUTPUT.PUT_LINE('Adding to Base SalesRegion Tree');
	DECLARE
		CURSOR	Cur_Tree IS
			SELECT  *
        	FROM    AD_ClientInfo;
		CURSOR Cur_SalesRegion	(Client NUMBER, Tree NUMBER) IS
			SELECT *
			FROM C_SalesRegion
			WHERE C_SalesRegion_ID NOT IN 
				(SELECT Node_ID FROM AD_TreeNode WHERE AD_Tree_ID=Tree)
			AND AD_Client_ID=Client;
	BEGIN
		FOR CT IN Cur_Tree LOOP
		    DBMS_OUTPUT.PUT_LINE('  For Tree ' || CT.AD_Tree_SalesRegion_ID);
			--
			FOR CM IN Cur_SalesRegion (CT.AD_Client_ID, CT.AD_Tree_SalesRegion_ID) LOOP
	         	INSERT INTO AD_TreeNode
       		        (AD_Client_ID, AD_Org_ID,
               		IsActive, Created, CreatedBy, Updated, UpdatedBy,
	                AD_Tree_ID, Node_ID, Parent_ID, SeqNo)
       		    VALUES
               		(CM.AD_Client_ID, CM.AD_Org_ID, 
	                CM.IsActive, CM.Created, CM.CreatedBy, CM.Updated, CM.UpdatedBy,
       		        CT.AD_Tree_SalesRegion_ID, CM.C_SalesRegion_ID, 0, 999);
	            DBMS_OUTPUT.PUT_LINE('    added: ' || CM.NAME);
       		END LOOP;	-- SalesRegion Loop
		END LOOP;	--	Tree Loop
	END;	-- Adding to Tree

	COMMIT;
END;
/
