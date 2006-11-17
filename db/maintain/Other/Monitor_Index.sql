/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Monitor_Index.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Monitor Index Usage
 * Description:
		SELECT * FROM V$OBJECT_USAGE WHERE USED<>'NO' ORDER BY 1;
		SELECT * FROM V$OBJECT_USAGE WHERE USED='NO' ORDER BY 1;
 ************************************************************************/

DECLARE
	CURSOR CUR_IDX IS
		SELECT 	Index_Name, Index_Type 
		FROM 	USER_INDEXES
		WHERE	INDEX_TYPE<>'LOB';	--	 no LOB Indexes
	v_Cmd		VARCHAR2(2000);
BEGIN
	FOR i IN Cur_IDX LOOP
		v_Cmd := 'ALTER INDEX ' || i.Index_Name || ' MONITORING USAGE';
--		v_Cmd := 'ALTER INDEX ' || i.Index_Name || ' NOMONITORING USAGE';
		EXECUTE IMMEDIATE v_Cmd;
  	END LOOP;
END;
/
