CREATE OR REPLACE FUNCTION currencyRound(
	p_Amount	NUMERIC,
	p_CurTo_ID	NUMERIC,
	p_Costing	VARCHAR		--	Default 'N'
) 

RETURNS numeric AS $body$
	
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Round amount for Traget Currency
 * Description:
 *		Round Amount using Costing or Standard Precision
 *		Returns unmodified amount if currency not found
 * Test:
 *		SELECT currencyRound(currencyConvert(100,116,100,null,null),100,null) FROM AD_System => 64.72 
 ************************************************************************/
	
	
DECLARE
	v_StdPrecision		NUMERIC;
	v_CostPrecision		NUMERIC;

BEGIN
	--	Nothing to convert
	IF (p_Amount IS NULL OR p_CurTo_ID IS NULL) THEN
		RETURN p_Amount;
	END IF;

	--	Ger Precision
	SELECT	MAX(StdPrecision), MAX(CostingPrecision)
	  INTO	v_StdPrecision, v_CostPrecision
	FROM	C_Currency
	  WHERE	C_Currency_ID = p_CurTo_ID;
	--	Currency Not Found
	IF (v_StdPrecision IS NULL) THEN
		RETURN p_Amount;
	END IF;

	IF (p_Costing = 'Y') THEN
		RETURN ROUND (p_Amount, v_CostPrecision);
	END IF;

	RETURN ROUND (p_Amount, v_StdPrecision);
	
END;

$body$ LANGUAGE plpgsql;


 	  	 


/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *Copyright (C) 2006-2008 victor.perez@e-evolution.com, e-Evolution
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 * Title: Calculate Open Item Amount in Invoice Currency
 * Description:
 * Add up total amount open for C_Invoice_ID if no split payment.
 *  Grand Total minus Sum of Allocations in Invoice Currency
 *
 *  For Split Payments:
 *  Allocate Payments starting from first schedule.
 
SELECT C_Invoice_Open (109) FROM DUAL;
SELECT C_Invoice_Open (109, null) FROM DUAL;
SELECT C_Invoice_Open (109, 11) FROM DUAL;
SELECT C_Invoice_Open (109, 102) FROM DUAL;
SELECT C_Invoice_Open (109, 103) FROM DUAL;
SELECT * FROM RV_OpenItem WHERE C_Invoice_ID=109;
SELECT C_InvoicePaySchedule_ID, DueAmt FROM C_InvoicePaySchedule WHERE C_Invoice_ID=109 ORDER BY DueDate;

*  Cannot be used for IsPaid as mutating
************************************************************************/
CREATE OR REPLACE FUNCTION InvoiceopenToDate
(
 p_C_Invoice_ID             IN numeric,
    p_C_InvoicePaySchedule_ID   IN  numeric,
	p_DateAcct IN DATE
)
RETURNS numeric
AS
$BODY$
DECLARE
	v_Currency_ID  numeric(10);
	v_TotalOpenAmt   numeric := 0;
	v_PaidAmt           numeric := 0;
	v_Remaining         numeric := 0;
	v_MultiplierAP      numeric := 0;
	v_MultiplierCM      numeric := 0;
	v_Temp              numeric := 0;
	allocationline	    record;
	invoiceschedule	    record;
BEGIN
 -- Get Currency
 BEGIN
  SELECT MAX(C_Currency_ID), SUM(GrandTotal), MAX(MultiplierAP), MAX(Multiplier)
    INTO v_Currency_ID, v_TotalOpenAmt, v_MultiplierAP, v_MultiplierCM
  FROM C_Invoice_v  -- corrected for CM / Split Payment
  WHERE C_Invoice_ID = p_C_Invoice_ID
    AND DateAcct <= p_DateAcct;
 EXCEPTION -- Invoice in draft form
  WHEN OTHERS THEN
            --DBMS_OUTPUT.PUT_LINE('InvoiceOpen - ' || SQLERRM);
   RETURN NULL;
 END;
--  DBMS_OUTPUT.PUT_LINE('== C_Invoice_ID=' || p_C_Invoice_ID || ', Total=' || v_TotalOpenAmt || ', AP=' || v_MultiplierAP || ', CM=' || v_MultiplierCM);

 -- Calculate Allocated Amount
 FOR allocationline IN  
  SELECT a.AD_Client_ID, a.AD_Org_ID,
            al.Amount, al.DiscountAmt, al.WriteOffAmt,
            a.C_Currency_ID, a.DateTrx
  FROM C_ALLOCATIONLINE al
          INNER JOIN C_ALLOCATIONHDR a ON (al.C_AllocationHdr_ID=a.C_AllocationHdr_ID)
  WHERE al.C_Invoice_ID = p_C_Invoice_ID
    AND a.DateAcct <= p_DateAcct
    AND   a.IsActive='Y'
 LOOP
        v_Temp := allocationline.Amount + allocationline.DisCountAmt + allocationline.WriteOffAmt;
  v_PaidAmt := v_PaidAmt
        -- Allocation
   + Currencyconvert(v_Temp * v_MultiplierAP,
    allocationline.C_Currency_ID, v_Currency_ID, allocationline.DateTrx, NULL, allocationline.AD_Client_ID, allocationline.AD_Org_ID);
      --DBMS_OUTPUT.PUT_LINE('   PaidAmt=' || v_PaidAmt || ', Allocation=' || v_Temp || ' * ' || v_MultiplierAP);
 END LOOP;

    --  Do we have a Payment Schedule ?
    IF (p_C_InvoicePaySchedule_ID > 0) THEN --   if not valid = lists invoice amount
        v_Remaining := v_PaidAmt;
        FOR invoiceschedule IN 
        SELECT  C_InvoicePaySchedule_ID, DueAmt FROM    C_INVOICEPAYSCHEDULE WHERE C_Invoice_ID = p_C_Invoice_ID AND IsValid='Y'
        ORDER BY DueDate
        LOOP
            IF (invoiceschedule.C_InvoicePaySchedule_ID = p_C_InvoicePaySchedule_ID) THEN
                v_TotalOpenAmt := (invoiceschedule.DueAmt*v_MultiplierCM) - v_Remaining;
                IF (invoiceschedule.DueAmt - v_Remaining < 0) THEN
                    v_TotalOpenAmt := 0;
                END IF;
            --  DBMS_OUTPUT.PUT_LINE('Sched Total=' || v_TotalOpenAmt || ', Due=' || s.DueAmt || ',Remaining=' || v_Remaining || ',CM=' || v_MultiplierCM);
            ELSE -- calculate amount, which can be allocated to next schedule
                v_Remaining := v_Remaining - invoiceschedule.DueAmt;
                IF (v_Remaining < 0) THEN
                    v_Remaining := 0;
                END IF;
            --  DBMS_OUTPUT.PUT_LINE('Remaining=' || v_Remaining);
            END IF;
        END LOOP;
    ELSE
        v_TotalOpenAmt := v_TotalOpenAmt - v_PaidAmt;
    END IF;
--  DBMS_OUTPUT.PUT_LINE('== Total=' || v_TotalOpenAmt);

 -- Ignore Rounding
 IF (v_TotalOpenAmt BETWEEN -0.00999 AND 0.00999) THEN
  v_TotalOpenAmt := 0;
 END IF;

 -- Round to penny
 v_TotalOpenAmt := ROUND(COALESCE(v_TotalOpenAmt,0), 2);
 RETURN v_TotalOpenAmt;
END;
$BODY$
  LANGUAGE 'plpgsql' ;


/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *Copyright (C) 2006-2008 victor.perez@e-evolution.com, e-Evolution
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 * Title:	Calculate Paid/Allocated amount in Currency
 * Description:
 *	Add up total amount paid for for C_Invoice_ID.
 *  Split Payments are ignored.
 *  all allocation amounts  converted to invoice C_Currency_ID
 *	round it to the nearest cent
 *	and adjust for CreditMemos by using C_Invoice_v
 *  and for Payments with the multiplierAP (-1, 1)
 *
    SELECT C_Invoice_ID, IsPaid, IsSOTrx, GrandTotal,
    C_Invoice_Paid (C_Invoice_ID, C_Currency_ID, MultiplierAP, DateAcct)
    FROM C_Invoice_v;
    --
    UPDATE C_Invoice_v1
 	SET IsPaid = CASE WHEN C_Invoice_Paid(C_Invoice_ID,C_Currency_ID,MultiplierAP,DateAcct)=GrandTotal THEN 'Y' ELSE 'N' END
    WHERE C_Invoice_ID>1000000
 *
 ************************************************************************/
 
CREATE OR REPLACE FUNCTION InvoicepaidToDate
(
	p_C_Invoice_ID		IN	numeric,
	p_C_Currency_ID	    	IN	numeric,
	p_MultiplierAP		IN	numeric,	-- DEFAULT 1
	p_DateAcct 		IN	timestamp with time zone
)
RETURNS numeric
AS
$BODY$
DECLARE
	v_MultiplierAP		numeric := 1;
	v_PaymentAmt		numeric := 0;
	allocation 		record;
BEGIN
	--	Default
	IF (p_MultiplierAP IS NOT NULL) THEN
		v_MultiplierAP := p_MultiplierAP;
	END IF;
	--	Calculate Allocated Amount
	FOR allocation IN 
	SELECT	allocation.AD_Client_ID, allocation.AD_Org_ID,al.Amount, al.DiscountAmt, al.WriteOffAmt,allocation.C_Currency_ID, allocation.DateTrx
	FROM	C_ALLOCATIONLINE al
        WHERE	al.C_Invoice_ID = p_C_Invoice_ID AND   allocation.IsActive='Y' AND allocation.DateAcct <= p_DateAcct
	LOOP
		v_PaymentAmt := v_PaymentAmt
			+ Currencyconvert(allocation.Amount + allocation.DisCountAmt + allocation.WriteOffAmt,
				allocation.C_Currency_ID, p_C_Currency_ID, allocation.DateTrx, NULL, allocation.AD_Client_ID, allocation.AD_Org_ID);
	END LOOP;
	--
	RETURN	ROUND(COALESCE(v_PaymentAmt,0), 2) * v_MultiplierAP;
END;	
$BODY$
LANGUAGE 'plpgsql' ;


/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *Copyright (C) 2006-2008 Antonio Cañaveral, e-Evolution
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.of 
 * Return the Document for Dcocument Type
 */
create or replace FUNCTION documentNo
(
      p_PP_MRP_ID IN PP_MRP.PP_MRP_ID%TYPE
)
RETURNS PP_MRP.Value%TYPE
AS
$BODY$
DECLARE
	v_DocumentNo PP_MRP.Value%TYPE := '';
BEGIN
	-- If NO id return empty string
	IF p_PP_MRP_ID <= 0 THEN
		RETURN '';
	END IF;
	SELECT --ordertype, m_forecast_id, c_order_id, dd_order_id, pp_order_id, m_requisition_id,
	CASE
			WHEN trim(mrp.ordertype) = 'FTC' THEN (SELECT f.Name FROM M_Forecast f WHERE f.M_Forecast_ID=mrp.M_Forecast_ID)
			WHEN trim(mrp.ordertype) = 'POO' THEN (SELECT co.DocumentNo  FROM C_Order co WHERE co.C_Order_ID=mrp.C_Order_ID)
			WHEN trim(mrp.ordertype) = 'DOO' THEN (SELECT dd.DocumentNo  FROM DD_Order dd WHERE dd.DD_Order_ID=mrp.DD_Order_ID)
			WHEN trim(mrp.ordertype) = 'SOO' THEN (SELECT co.DocumentNo  FROM C_Order co WHERE co.C_Order_ID=mrp.C_Order_ID)
			WHEN trim(mrp.ordertype) = 'MOP' THEN (SELECT po.DocumentNo FROM PP_Order po WHERE po.PP_Order_ID=mrp.PP_Order_ID)
			WHEN trim(mrp.ordertype) = 'POR' THEN (SELECT r.DocumentNo  FROM M_Requisition r WHERE r.M_Requisition_ID=mrp.M_Requisition_ID)
			
	END INTO v_DocumentNo
	FROM pp_mrp mrp
	WHERE mrp.pp_mrp_id = p_PP_MRP_ID;
	RETURN v_DocumentNo;
END;	
$BODY$
  LANGUAGE 'plpgsql' ;


CREATE OR REPLACE FUNCTION nextidfunc(
	p_AD_Sequence_ID 	IN 	INTEGER, 
	p_System 		IN 	VARCHAR
)
  RETURNS INTEGER AS $body$
DECLARE
          o_NextIDFunc INTEGER;
	  dummy INTEGER;
BEGIN
    o_NextIDFunc := nextid(p_AD_Sequence_ID, p_System);
    RETURN o_NextIDFunc;
END;
$body$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION nextid(
	p_AD_Sequence_ID 	IN 	INTEGER, 
	p_System 		IN 	VARCHAR,
	o_NextID		OUT	INTEGER
)
  RETURNS INTEGER AS $body$
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2005 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 ***
 * Title:	Get Next ID - no Commit
 * Description: Returns the next id of the sequence.
 * Test:
 *	select * from nextid((select ad_sequence_id from ad_sequence where name = 'Test')::Integer, 'Y'::Varchar);
 * 
 ************************************************************************/

BEGIN
    IF (p_System = 'Y') THEN
	RAISE NOTICE 'system';
        SELECT CurrentNextSys
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --
        UPDATE AD_Sequence
          SET CurrentNextSys = CurrentNextSys + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    ELSE
        SELECT CurrentNext
            INTO o_NextID
        FROM AD_Sequence
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
        --
        UPDATE AD_Sequence
          SET CurrentNext = CurrentNext + IncrementNo
        WHERE AD_Sequence_ID=p_AD_Sequence_ID;
    END IF;
    --
EXCEPTION
    WHEN  OTHERS THEN
    	RAISE NOTICE '%',SQLERRM;
END;

$body$ LANGUAGE plpgsql;


 	  	 


set client_encoding='LATIN1';

CREATE OR REPLACE FUNCTION      ProductAttribute 
(
    p_M_AttributeSetInstance_ID     NUMERIC
)
RETURNS VARCHAR AS $body$

/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2001 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *
 * converted to postgreSQL by Karsten Thiemann (Schaeffer AG), 
 * kthiemann@adempiere.org
 *************************************************************************
 * Title: Return Instance Attribute Info
 * Description:
 *  
 * Test:
    SELECT ProductAttribute (M_AttributeSetInstance_ID) 
    FROM M_InOutLine WHERE M_AttributeSetInstance_ID > 0
    --
    SELECT p.Name
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    SELECT p.Name || ProductAttribute (il.M_AttributeSetInstance_ID) 
    FROM C_InvoiceLine il LEFT OUTER JOIN M_Product p ON (il.M_Product_ID=p.M_Product_ID);
    
 ************************************************************************/

	
DECLARE

    v_Name          VARCHAR(2000) := '';
    v_NameAdd       VARCHAR(2000) := '';
    --
    v_Lot           M_AttributeSetInstance.Lot%TYPE;
    v_LotStart      M_AttributeSet.LotCharSOverwrite%TYPE;
    v_LotEnd        M_AttributeSet.LotCharEOverwrite%TYPE;
    v_SerNo         M_AttributeSetInstance.SerNo%TYPE;
    v_SerNoStart    M_AttributeSet.SerNoCharSOverwrite%TYPE;
    v_SerNoEnd      M_AttributeSet.SerNoCharEOverwrite%TYPE;
    v_GuaranteeDate M_AttributeSetInstance.GuaranteeDate%TYPE;
    
    r   RECORD;
    --

BEGIN
    --  Get Product Attribute Set Instance
    IF (p_M_AttributeSetInstance_ID > 0) THEN
        SELECT asi.Lot, asi.SerNo, asi.GuaranteeDate,
            COALESCE(a.SerNoCharSOverwrite, '#'::CHAR(1)), COALESCE(a.SerNoCharEOverwrite, ''::CHAR(1)),
            COALESCE(a.LotCharSOverwrite, '�'::CHAR(1)), COALESCE(a.LotCharEOverwrite, '�'::CHAR(1))
          INTO v_Lot, v_SerNo, v_GuaranteeDate,
            v_SerNoStart, v_SerNoEnd, v_LotStart, v_LotEnd
        FROM M_AttributeSetInstance asi
          INNER JOIN M_AttributeSet a ON (asi.M_AttributeSet_ID=a.M_AttributeSet_ID)
        WHERE asi.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID;
        --
        IF (v_SerNo IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_SerNoStart || v_SerNo || v_SerNoEnd || ' ';
        END IF;
        IF (v_Lot IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_LotStart || v_Lot || v_LotEnd || ' ';
        END IF;
        IF (v_GuaranteeDate IS NOT NULL) THEN
            v_NameAdd := v_NameAdd || v_GuaranteeDate || ' ';
        END IF;
        --
        
        FOR r IN
	     SELECT ai.Value, a.Name
	        FROM M_AttributeInstance ai
	        INNER JOIN M_Attribute a ON (ai.M_Attribute_ID=a.M_Attribute_ID AND a.IsInstanceAttribute='Y')
        	WHERE ai.M_AttributeSetInstance_ID=p_M_AttributeSetInstance_ID
    	LOOP
            v_NameAdd := v_NameAdd || r.Name || ':' || r.Value || ' ';
        END LOOP;
        --
        IF (LENGTH(v_NameAdd) > 0) THEN
            v_Name := v_Name || ' (' || TRIM(v_NameAdd) || ')';
	ELSE 
	    v_Name := NULL;
        END IF;
    END IF;
    RETURN v_Name;
END;
$body$ LANGUAGE plpgsql;
