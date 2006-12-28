CREATE OR REPLACE PROCEDURE M_Product_BOM_Check
(
	PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Product_BOM_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Check BOM Structure (free of cycles)
 * Description:
 *		Tree cannot contain BOMs which are already referenced
 ************************************************************************/
AS
	--	Logistice
	ResultStr						VARCHAR2(2000);
	Message							VARCHAR2(2000);
	Record_ID						NUMBER;
	--	Parameter
	CURSOR Cur_Parameter (PInstance NUMBER) IS
		SELECT i.Record_ID, p.ParameterName, p.P_String, p.P_Number, p.P_Date
		FROM AD_PInstance i, AD_PInstance_Para p
		WHERE i.AD_PInstance_ID=PInstance
		AND i.AD_PInstance_ID=p.AD_PInstance_ID(+)
		ORDER BY p.SeqNo;
	--	Variables
	Verified				CHAR(1) := 'Y';
	IsBOM					CHAR(1);
	CountNo					NUMBER;

BEGIN
    --  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Processing ' || PInstance_ID);
    ResultStr := 'PInstanceNotFound';
    UPDATE AD_PInstance
    SET Created = SysDate,
        IsProcessing = 'Y'
    WHERE AD_PInstance_ID=PInstance_ID;
    COMMIT;

	--	Get Parameters
	ResultStr := 'ReadingParameters';
	FOR p IN Cur_Parameter (PInstance_ID) LOOP
		Record_ID := p.Record_ID;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || Record_ID);

	--	Record ID is M_Product_ID of product to be tested
	SELECT 	IsBOM
	  INTO	IsBOM
	FROM	M_Product
	WHERE	M_Product_ID=Record_ID;

	--	No BOM - should not happen, but no problem
	IF (IsBOM = 'N') THEN
		GOTO FINISH_PROCESS;
	--	Did not find product
	ELSIF (IsBOM <> 'Y') THEN
		RETURN;
	END IF;

	--	Checking BOM Structure
	ResultStr := 'InsertingRoot';
	--	Table to put all BOMs - duplicate will cause exception
	DELETE FROM T_Selection2 WHERE Query_ID = 0 AND AD_PInstance_ID = PInstance_ID;
	INSERT INTO T_Selection2 (AD_PInstance_ID, Query_ID, T_Selection_ID) VALUES (PInstance_ID, 0, Record_ID);
	--	Table of root modes
	DELETE FROM T_Selection WHERE AD_PInstance_ID = PInstance_ID;
	INSERT INTO T_Selection (AD_PInstance_ID, T_Selection_ID) VALUES (PInstance_ID, Record_ID);

	LOOP
		--	How many do we have?
		SELECT 	COUNT(*) 
		  INTO	CountNo
		FROM	T_Selection 
		WHERE AD_PInstance_ID = PInstance_ID;
		--	Nothing to do
		EXIT WHEN (CountNo = 0);

		--	Insert BOM Nodes into "All" table
		INSERT INTO T_Selection2 (AD_PInstance_ID, Query_ID, T_Selection_ID)
		SELECT PInstance_ID, 0, p.M_Product_ID
		FROM M_Product p
		WHERE IsBOM='Y' 
		  AND EXISTS (SELECT * FROM M_Product_BOM b WHERE p.M_Product_ID=b.M_ProductBOM_ID
		  	AND b.M_Product_ID IN (SELECT T_Selection_ID FROM T_Selection WHERE AD_PInstance_ID = PInstance_ID));

		--	Insert BOM Nodes into temporary table
		DELETE FROM T_Selection2 WHERE Query_ID = 1 AND AD_PInstance_ID = PInstance_ID;
		INSERT INTO T_Selection2 (AD_PInstance_ID, Query_ID, T_Selection_ID)
		SELECT PInstance_ID, 1, p.M_Product_ID
		FROM M_Product p
		WHERE IsBOM='Y' 
		  AND EXISTS (SELECT * FROM M_Product_BOM b WHERE p.M_Product_ID=b.M_ProductBOM_ID
		  	AND b.M_Product_ID IN (SELECT T_Selection_ID FROM T_Selection WHERE AD_PInstance_ID = PInstance_ID));

		--	Copy into root table
		DELETE FROM T_Selection WHERE AD_PInstance_ID = PInstance_ID;
		INSERT INTO T_Selection (AD_PInstance_ID, T_Selection_ID) 
		SELECT 	PInstance_ID, T_Selection_ID
		FROM	T_Selection2
		WHERE Query_ID = 1 AND AD_PInstance_ID = PInstance_ID;

	END LOOP;

<<FINISH_PROCESS>>
	--	OK
	Message := 'OK';
	UPDATE M_Product
      SET IsVerified = 'Y'
	WHERE	M_Product_ID=Record_ID;

	--  Update AD_PInstance
	DBMS_OUTPUT.PUT_LINE('Updating PInstance - Finished ' || Message);
    UPDATE  AD_PInstance
    SET Updated = SysDate,
        IsProcessing = 'N',
        Result = 1,                 -- success
        ErrorMsg = Message
    WHERE   AD_PInstance_ID=PInstance_ID;
    COMMIT;
    RETURN;

EXCEPTION
    WHEN  OTHERS THEN
		ResultStr := ResultStr || ': ' || SQLERRM || ' - ' || Message;
		DBMS_OUTPUT.PUT_LINE(ResultStr);
        UPDATE  AD_PInstance
        SET Updated = SysDate,
            IsProcessing = 'N',
            Result = 0,             -- failure
            ErrorMsg = ResultStr
        WHERE   AD_PInstance_ID=PInstance_ID;
        COMMIT;
		--
		UPDATE M_Product
    	  SET IsVerified = 'N'
		WHERE	M_Product_ID=Record_ID;
		COMMIT;
		--
        RETURN;

END M_Product_BOM_Check;
/