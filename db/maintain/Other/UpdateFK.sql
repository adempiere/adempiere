/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: UpdateFK.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Update FK
 * Description:
 ************************************************************************/
DECLARE
    p_TableName     VARCHAR2(60)    := 'C_Country';
    p_oldID         NUMBER(10)      := 1000003;
    p_newID         NUMBER(10)      := 313;
    --
    CURSOR CUR_Table (x_TableName VARCHAR2) IS
        SELECT t.TableName
        FROM AD_Table t
          INNER JOIN AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)
        WHERE t.TableName NOT IN (x_TableName, x_TableName || '_Trl')
          AND c.ColumnName = x_TableName || '_ID';
    v_cmd           VARCHAR2(2000);
BEGIN
    FOR t IN CUR_Table (p_TableName) LOOP
        v_cmd := 'UPDATE ' || t.TableName 
            || ' SET '
            || p_TableName || '_ID=' || p_newID
            || ' WHERE '
            || p_TableName || '_ID=' || p_oldID;
        BEGIN
            EXECUTE IMMEDIATE v_cmd;
            DBMS_OUTPUT.PUT_LINE(t.TableName || ' = ' || SQL%ROWCOUNT);
        EXCEPTION
            WHEN OTHERS THEN
                DBMS_OUTPUT.PUT_LINE(v_cmd);
        END;
    END LOOP;
    --
    v_cmd := 'DELETE ' || p_TableName
            || ' WHERE '
            || p_TableName || '_ID=' || p_oldID;
    BEGIN
        EXECUTE IMMEDIATE v_cmd;
        DBMS_OUTPUT.PUT_LINE(p_TableName || ' delete = ' || SQL%ROWCOUNT);
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(v_cmd);
    END;

END;
/
COMMIT
/