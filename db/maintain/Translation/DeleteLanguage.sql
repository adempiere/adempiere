/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: DeleteLanguage.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:       Delete Language
 * Description:
 ************************************************************************/
DECLARE
	v_Language 				VARCHAR2(10) := 'xx_XX';
	CURSOR Cur_Tables IS
		SELECT TableName
		FROM AD_Table
		WHERE TableName like '%_Trl';
	v_Cmd					VARCHAR2(256);
BEGIN
	FOR t IN CUR_Tables LOOP
		v_Cmd := 'DELETE ' || t.TableName || ' WHERE AD_Language=''' || v_language || '''';
		DBMS_OUTPUT.PUT_LINE('.. ' || t.TableName);
		EXECUTE IMMEDIATE v_Cmd;
		DBMS_OUTPUT.PUT_LINE(t.TableName || '=' || SQL%ROWCOUNT);
	END LOOP;
END;
/	 
COMMIT
/
