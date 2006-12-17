CREATE OR REPLACE PROCEDURE M_Product_Delete
(
	whereClause	IN VARCHAR2	DEFAULT NULL 
)
AS
/******************************************************************************
 * ** Adempiere Product **             Copyright (c) 1999-2001 Accorto, Inc. USA
 * Open  Source  Software        Provided "AS IS" without warranty or liability
 * When you use any parts (changed or unchanged), add  "Powered by Adempiere" to
 * your product name;  See license details http://www.adempiere.org/license.html
 ******************************************************************************
 *	Delete Products
 */
	CURSOR CUR_DEL IS
		SELECT 	M_Product_ID, Value, Name
		FROM 	M_Product 
		WHERE	IsActive='N';
	--
	SQL_Base		VARCHAR2(255) := 'SELECT M_Product_ID FROM M_Product WHERE ';
--	SQL_Where		VARCHAR2(255) := 'ValueX IN (SELECT ValueX FROM M_Product GROUP BY ValueX HAVING Count(*) <> 1) AND INSTR(Value,''@'') <> 0';
	SQL_Statement	VARCHAR2(255);
BEGIN
	--	Delete inactive
	IF (whereClause IS NULL OR LENGTH(whereClause) = 0) THEN
		For d IN CUR_DEL LOOP
			BEGIN
				DBMS_OUTPUT.PUT('Deleting ' || d.Name || ' - ');
				DELETE M_Product
				WHERE M_Product_ID=d.M_Product_ID;
				DBMS_OUTPUT.PUT_LINE('OK');
			EXCEPTION WHEN OTHERS THEN
				DBMS_OUTPUT.PUT_LINE('Error ' || SQLERRM);
			END;
		END LOOP;
	END IF;
END M_Product_Delete;
/
