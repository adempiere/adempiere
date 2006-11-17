/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2003 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: C_ValidCombbination_Constraints.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:     Create Account Constraints
 * Description:
 ************************************************************************/
DECLARE
	CURSOR CUR_Accts IS
		 SELECT t.TableName, c.ColumnName
		 FROM Reference.AD_Table t
		   INNER JOIN Reference.AD_Column c ON (t.AD_Table_ID=c.AD_Table_ID)
		 WHERE c.AD_Reference_ID=25
		   AND NOT EXISTS (SELECT * FROM USER_Cons_Columns cc
			  WHERE UPPER(t.TableName)=cc.Table_Name 
				AND UPPER(c.ColumnName)=cc.Column_Name
				AND cc.Position=1);
	--
	v_cmd		   VARCHAR2 (256);
	v_name		   VARCHAR2 (256);
BEGIN
	FOR a IN CUR_Accts LOOP
		v_name := 'VC_' || REPLACE(a.ColumnName, '_', '') 
		    || '_' || REPLACE(a.TableName, '_', '');
        v_name := REPLACE (v_name, 'Acct', '');
        v_name := REPLACE (v_name, 'TradeDiscount', 'TDiscount');
        v_name := REPLACE (v_name, 'PriceVariance', 'PV');
        v_name := REPLACE (v_name, 'Receivables', 'Rec');
        v_name := SUBSTR (v_name, 1, 30);          
        DBMS_OUTPUT.PUT_LINE (a.TableName || '.' || a.ColumnName || ' - ' || v_name);
		v_cmd := 'ALTER TABLE ' || a.TableName
			  || ' ADD CONSTRAINT ' || v_name
			  || ' FOREIGN KEY (' || a.ColumnName 
			  || ') REFERENCES C_ValidCombination(C_ValidCombination_ID)';
		BEGIN
		    EXECUTE IMMEDIATE v_cmd;
		EXCEPTION
			WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE ('** ' || SQLERRM);
				DBMS_OUTPUT.PUT_LINE ('   ' || v_cmd);
                IF (SQLCODE <> 2264) THEN
	    		    DBMS_OUTPUT.PUT_LINE ('   SELECT * FROM ' || a.TableName 
    					|| ' x WHERE NOT EXISTS (SELECT * FROM C_ValidCombination vc'
						|| ' WHERE vc.C_ValidCombination_ID=x.' || a.ColumnName 
						|| ' AND vc.AD_Client_ID=x.AD_Client_ID)');
                END IF;
		END;
	END LOOP;

END;
/