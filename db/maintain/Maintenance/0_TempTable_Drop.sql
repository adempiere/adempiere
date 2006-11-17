/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 0_TempTable_Drop.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Drop Temporary Tables
 * Description:
 *		Drop (Temporary) Tables for report views
 ************************************************************************/


DECLARE
	CURSOR CUR_Views IS
		SELECT	View_Name
		FROM	USER_Views
		WHERE NOT
		(
			View_Name LIKE '%_VT'	--  Translation Views
			OR View_Name LIKE '%_V1'    --  Special Views
			OR View_Name LIKE 'AD_%'    --  Dictionary Views
			OR View_Name LIKE 'O_%'		-- Export Views
			OR View_Name LIKE 'GL_%'	-- GL Views
			OR View_Name LIKE 'R_%_V'	-- Request Views
			OR View_Name IN ('C_INVOICE_V','C_INVOICELINE_V', 'C_PAYMENT_V',
				'C_INVOICE_CANDIDATE_V', 'M_INOUT_CANDIDATE_V')
		)
		ORDER BY 1;
	v_Cmd		VARCHAR2(2000);
BEGIN
	FOR v IN CUR_Views LOOP
		DBMS_OUTPUT.PUT('View ' || v.View_Name || ' - ');
		BEGIN
			v_Cmd := 'DROP TABLE ' || v.View_Name || '__';
			EXECUTE IMMEDIATE v_Cmd;
		EXCEPTION WHEN OTHERS THEN
			DBMS_OUTPUT.PUT('Table not existing - ');
		END;
		--
		v_Cmd := 'UPDATE AD_Table SET TableName=SUBSTR(TableName,1,LENGTH(TableName)-2) '
			|| 'WHERE UPPER(TableName)=''' || v.View_Name || '__''';
		EXECUTE IMMEDIATE v_Cmd;
		DBMS_OUTPUT.PUT_LINE (SQL%ROWCOUNT);
	END LOOP;
END;
/
COMMIT
/

