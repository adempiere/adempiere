CREATE OR REPLACE PROCEDURE M_Production_Run
(
	PInstance_ID    		IN NUMBER
)
/*************************************************************************
 * The contents of this file are subject to the Adempiere License.  You may
 * obtain a copy of the License at    http://www.adempiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Adempiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: M_Production_Run.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Production of BOMs
 * Description:
 *		1) Creating ProductionLines when IsCreated = 'N'
 *		2) Posting the Lines (optionally only when fully stocked)
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
	--	Parameter Variables
	MustBeStocked					CHAR(1);
	IsCreated						CHAR(1);
	Processed						CHAR(1);
	Client_ID						NUMBER;
	Org_ID							NUMBER;
	--
	Line							NUMBER;
	NextNo							NUMBER;
	CountNo							NUMBER;
	--	ProductionPlan
	CURSOR CUR_PP	IS
		SELECT 	*
		FROM	M_ProductionPlan
		WHERE	M_Production_ID=Record_ID
		ORDER BY Line, M_Product_ID;
	--	BOM Lines
	CURSOR CUR_BOM (Product_ID NUMBER)	IS
		SELECT 	*
		FROM	M_Product_BOM
		WHERE	M_Product_ID=Product_ID
		ORDER BY Line;
	--	ProductionLines which are non-stocked BOMs (need to be resolved)
	CURSOR CUR_PLineBOM (ProductionPlan_ID NUMBER) 	IS
		SELECT pl.M_ProductionLine_ID, pl.Line, pl.M_Product_ID, pl.MovementQty
		FROM M_ProductionLine pl, M_Product p
		WHERE pl.M_ProductionPlan_ID=ProductionPlan_ID
		  AND pl.M_Product_ID=p.M_Product_ID
		  AND pl.Line<>100	--	Origin Line
		  AND p.IsBOM='Y' AND p.IsStocked='N';

	--	Posting
	CURSOR CUR_PL_Post	IS
		SELECT pl.M_ProductionLine_ID, pl.AD_Client_ID, pl.AD_Org_ID, p.MovementDate,
			pl.M_Product_ID, pl.M_AttributeSetInstance_ID, pl.MovementQty, pl.M_Locator_ID
		FROM M_Production p, M_ProductionLine pl, M_ProductionPlan pp
		WHERE p.M_Production_ID=pp.M_Production_ID
		  AND pp.M_ProductionPlan_ID=pl.M_ProductionPlan_ID
		  AND pp.M_Production_ID=Record_ID
		ORDER BY pp.Line, pl.Line;



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
		IF (p.ParameterName = 'MustBeStocked') THEN
 			MustBeStocked := p.P_String;
			DBMS_OUTPUT.PUT_LINE('  MustBeStocked=' || MustBeStocked);
		ELSE
			DBMS_OUTPUT.PUT_LINE('*** Unknown Parameter=' || p.ParameterName);
	 	END IF;
	END LOOP;	--	Get Parameter
	DBMS_OUTPUT.PUT_LINE('  Record_ID=' || Record_ID);

	--	Processing??? Lock ????
	--	TODO

	/**
	 *	Get Info + Lock
	 */
	ResultStr := 'ReadingRecord';
	SELECT 	IsCreated, Processed, AD_Client_ID, AD_Org_ID
	  INTO 	IsCreated, Processed, Client_ID, Org_ID
	FROM	M_Production
	WHERE	M_Production_ID=Record_ID
	FOR UPDATE;

	/**
	 *	No Action
	 */
	IF (Processed <> 'N') THEN
		Message := '@AlreadyPosted@';
		GOTO FINISH_PROCESS;
	END IF;
	
	/**************************************************************************
	 *	Create Lines
	 */
	IF (IsCreated <> 'Y') THEN
		-- For every Production Plan
		FOR pp IN CUR_PP LOOP
			--	Delete prior lines
			DELETE 	M_ProductionLine
			WHERE 	M_ProductionPlan_ID=pp.M_ProductionPlan_ID;
		--	DBMS_OUTPUT.PUT_LINE('ProductionPlan=' || pp.M_ProductionPlan_ID);
			--	Create BOM Line
			ResultStr := 'CreatingLine BOM';
			Line := 100;	--	OriginLine
			AD_Sequence_Next('M_ProductionLine', pp.AD_Client_ID, NextNo);
			INSERT INTO M_ProductionLine
				(M_ProductionLine_ID, M_ProductionPlan_ID, Line,
				AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
				M_Product_ID, MovementQty, M_Locator_ID, Description)
		   	VALUES
				(NextNo, pp.M_ProductionPlan_ID, Line,
				pp.AD_Client_ID,pp.AD_Org_ID,'Y',SysDate,0,SysDate,0,
				pp.M_Product_ID, pp.ProductionQty, pp.M_Locator_ID, pp.Description);

			--	Create First Level
			FOR bom IN CUR_BOM (pp.M_Product_ID) LOOP
				ResultStr := 'CreatingLine Products';
				Line := Line + 100;
				AD_Sequence_Next('M_ProductionLine', pp.AD_Client_ID, NextNo);
				INSERT INTO M_ProductionLine
					(M_ProductionLine_ID, M_ProductionPlan_ID, Line,
					AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
					M_Product_ID, MovementQty, M_Locator_ID)
			   	VALUES
					(NextNo, pp.M_ProductionPlan_ID, Line,
					pp.AD_Client_ID,pp.AD_Org_ID,'Y',SysDate,0,SysDate,0,
					bom.M_ProductBOM_ID, -pp.ProductionQty*bom.BOMQty, pp.M_Locator_ID);
			END LOOP;

			--	While we have BOMs
			LOOP
				--	Are there non-stored BOMs to list details?
				ResultStr := 'CreatingLine CheckBOM';
				SELECT COUNT(*) INTO CountNo
				FROM M_ProductionLine pl, M_Product p
				WHERE pl.M_Product_ID=p.M_Product_ID
				  AND pl.M_ProductionPlan_ID=pp.M_ProductionPlan_ID
				  AND pl.Line<>100	--	Origin Line
				  AND p.IsBOM='Y' AND p.IsStocked='N';
				--	Nothing to do	
				EXIT WHEN (CountNo = 0);
				--

				--	Resolve BOMs in ProductLine which are not stocked
				FOR pl IN CUR_PLineBOM (pp.M_ProductionPlan_ID) LOOP
					ResultStr := 'CreatingLineBOM Resolution';
					Line := pl.Line;
					--	Resolve BOM Line in product line
					FOR bom IN CUR_BOM (pl.M_Product_ID) LOOP
						ResultStr := 'CreatingLine Products2';
						Line := Line + 10;
						AD_Sequence_Next('M_ProductionLine', pp.AD_Client_ID, NextNo);
						INSERT INTO M_ProductionLine
							(M_ProductionLine_ID, M_ProductionPlan_ID, Line,
							AD_Client_ID,AD_Org_ID,IsActive,Created,CreatedBy,Updated,UpdatedBy,
							M_Product_ID, MovementQty, M_Locator_ID)
			   			VALUES
							(NextNo, pp.M_ProductionPlan_ID, Line,
							pp.AD_Client_ID,pp.AD_Org_ID,'Y',SysDate,0,SysDate,0,
							bom.M_ProductBOM_ID, pl.MovementQty*bom.BOMQty, pp.M_Locator_ID);
					END LOOP;
					--	Delete BOM line
					DELETE  M_ProductionLine
					WHERE 	M_ProductionLine_ID=pl.M_ProductionLine_ID;
				END LOOP;
			END LOOP;	--	While we have BOMs

		END LOOP;	--	For every Production Plan

		--	Modifying locator to have sufficient stock


		--	Indicate that it is Created
		UPDATE	M_Production
		  SET	IsCreated='Y'
		WHERE	M_Production_ID=Record_ID;

	/**************************************************************************
	 *	Post Lines
	 */
	ELSE
		--	All Production Lines
		FOR pl IN CUR_PL_Post LOOP
		--	M_ProductionLine_ID, AD_Client_ID, AD_Org_ID, MovementDate, M_Product_ID, MovementQty, M_Locator_ID
		--	DBMS_OUTPUT.PUT_LINE('ProductionLine=' || pl.M_ProductionLine_ID);
		--	DBMS_OUTPUT.PUT_LINE('  Qty=' || pl.MovementQty || ', OnHand=' || BOM_Qty_OnHand(pl.M_Product_ID, NULL, pl.M_Locator_ID));
			--	Check Stock levels for reductions
			IF (pl.MovementQty < 0 AND MustBeStocked <> 'N'
					AND bomQtyOnHand(pl.M_Product_ID, NULL, pl.M_Locator_ID)+pl.MovementQty < 0) THEN
				ROLLBACK;
				SELECT '@NotEnoughStocked@: ' || Name	INTO Message
				FROM M_Product WHERE M_Product_ID=pl.M_Product_ID;
				GOTO FINISH_PROCESS;
			END IF;

			--	Adjust Quantity at Location
			UPDATE	M_Storage
			  SET	QtyOnHand = QtyOnHand + pl.MovementQty,
					Updated = SysDate
		 	WHERE	M_Locator_ID = pl.M_Locator_ID
              AND   M_AttributeSetInstance_ID = COALESCE(pl.M_AttributeSetInstance_ID,0)
			  AND	M_Product_ID = pl.M_Product_ID;
			--	Product not on Stock yet
			IF (SQL%ROWCOUNT = 0) THEN
				INSERT INTO M_Storage
					(M_Product_ID, M_Locator_ID, M_AttributeSetInstance_ID,
					 AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
					 QtyOnHand, QtyReserved, QtyOrdered)
			 	VALUES
					(pl.M_Product_ID, pl.M_Locator_ID, COALESCE(pl.M_AttributeSetInstance_ID,0),
					 pl.AD_Client_ID, pl.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,
					 pl.MovementQty, 0, 0);
			END IF;
				
			--	Create Transaction Entry
			ResultStr := 'CreateTransaction';
			AD_Sequence_Next('M_Transaction', pl.AD_Org_ID, NextNo);
			INSERT INTO M_Transaction
				(M_Transaction_ID, M_ProductionLine_ID,
				AD_Client_ID, AD_Org_ID, IsActive, Created, CreatedBy, Updated, UpdatedBy,
				MovementType, M_Locator_ID, M_Product_ID, M_AttributeSetInstance_ID,
				MovementDate, MovementQty)
			VALUES
				(NextNo, pl.M_ProductionLine_ID,
				pl.AD_Client_ID, pl.AD_Org_ID, 'Y', SysDate, 0, SysDate, 0,
				'P+', pl.M_Locator_ID, pl.M_Product_ID,	COALESCE(pl.M_AttributeSetInstance_ID,0),	-- not distinguishing between assemby/disassembly
				pl.MovementDate, pl.MovementQty);
            --
    		UPDATE	M_ProductionLine
	    	  SET	Processed='Y'
		    WHERE	M_ProductionLine_ID=pl.M_ProductionLine_ID;
		END LOOP;

		--	Indicate that we are done
		UPDATE	M_Production
		  SET	Processed='Y'
		WHERE	M_Production_ID=Record_ID;
		UPDATE	M_ProductionPlan
		  SET	Processed='Y'
		WHERE	M_Production_ID=Record_ID;

	END IF;
	--	Only commit when entire job successful
	COMMIT;

<<FINISH_PROCESS>>
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
        RETURN;

END M_Production_Run;
/
