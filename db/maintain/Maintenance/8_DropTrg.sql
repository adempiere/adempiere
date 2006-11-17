/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2002 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: 8_DropTrg.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	 Delete all triggers
 * Description:
 * This scrpt deletes all triggers of a user - DANGEROUS !!!
 *
 * Make sure that you run this script ONLY 
 * - with a migrated DB if Adempiere Release 2.5.1g or later
 * - with the Adempiere user
 * - when you have a backup
 * Execute:
 * - sqlplus adempiere/adempiere @8_DropTrg.sql
 */
DECLARE
    CURSOR Cur_Trg IS
        SELECT TRIGGER_NAME FROM USER_TRIGGERS;
    v_cmd				VARCHAR(2000);
BEGIN
	DBMS_OUTPUT.PUT_LINE('DROP all Triggers');
    FOR t IN CUR_Trg LOOP
        DBMS_OUTPUT.PUT_LINE(' - ' || t.TRIGGER_NAME);
        v_cmd := 'DROP TRIGGER ' || t.TRIGGER_NAME;
        EXECUTE IMMEDIATE (v_cmd);
    END LOOP;
END;
/
