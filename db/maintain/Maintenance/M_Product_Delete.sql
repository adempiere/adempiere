/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Product_Delete.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Delete Inactive Products
 * Description:
 *			Will delete unused products, 
 *			but not if they were invoiced or shipped.
 ************************************************************************/
DECLARE
	CURSOR	 Cur_Products 	IS
		SELECT	M_Product_ID, Name
		FROM	M_Product
		WHERE	IsActive = 'N';
	--
	BatchSize			NUMBER := 10000;
	No					NUMBER := 0;
	NoTotal				NUMBER := 0;
BEGIN
	FOR p IN Cur_Products LOOP
		EXIT WHEN (No > BatchSize);
		--
		NoTotal := NoTotal + 1;
		BEGIN
			DELETE	M_Product
			WHERE	M_Product_ID = p.M_Product_ID;
			No := No + 1;
		--	DBMS_OUTPUT.PUT_LINE(p.Name);
		EXCEPTION WHEN OTHERS THEN
			DELETE	M_ProductPrice
			WHERE	M_Product_ID = p.M_Product_ID;
		--	DBMS_OUTPUT.PUT_LINE(p.Name || ' NOT deleted');
		END;
		COMMIT;
	END LOOP;
	DBMS_OUTPUT.PUT_LINE('>> Deleted ' || No || ' of ' || NoTotal);
END;
