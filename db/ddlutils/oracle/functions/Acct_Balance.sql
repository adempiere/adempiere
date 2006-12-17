CREATE OR REPLACE FUNCTION acctBalance
(
    p_Account_ID    IN NUMBER,
    p_AmtDr         IN NUMBER,
    p_AmtCr         IN NUMBER
)
RETURN NUMBER
/*************************************************************************
 * The contents of this file are subject to the Compiere License.  You may
 * obtain a copy of the License at    http://www.compiere.org/license.html 
 * Software is on an  "AS IS" basis,  WITHOUT WARRANTY OF ANY KIND, either 
 * express or implied. See the License for details. Code: Compiere ERP+CRM
 * Copyright (C) 1999-2004 Jorg Janke, ComPiere, Inc. All Rights Reserved.
 *************************************************************************
 * $Id: Acct_Balance.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 ***
 * Title:	Aclculate Balance based on Account Sign + Type
 * Description:
 *  If an account is specified and found
 *  - If the account sign is Natural it sets it based on Account Type
 *  Returns Credit or Debit Balance
 * Test:
    SELECT Acct_Balance (0,11,22) FROM DUAL
    SELECT AccountType, AccountSign, 
        Acct_Balance(C_ElementValue_ID, 20, 10) "DR Balance",
        Acct_Balance(C_ElementValue_ID, 10, 20) "CR Balance"
    FROM C_ElementValue
    WHERE AccountSign<>'N'
    ORDER BY AccountSign
 ************************************************************************/
AS
    v_balance           NUMBER;
    v_AccountType       C_ElementValue.AccountType%TYPE;
    v_AccountSign       C_ElementValue.AccountSign%TYPE;
BEGIN
    v_balance := p_AmtDr - p_AmtCr;
    --  
    IF (p_Account_ID > 0) THEN
        SELECT AccountType, AccountSign
          INTO v_AccountType, v_AccountSign
        FROM C_ElementValue
        WHERE C_ElementValue_ID=p_Account_ID;
   --   DBMS_OUTPUT.PUT_LINE('Type=' || v_AccountType || ' - Sign=' || v_AccountSign);
        --  Natural Account Sign
        IF (v_AccountSign='N') THEN
            IF (v_AccountType IN ('A','E')) THEN
                v_AccountSign := 'D';
            ELSE
                v_AccountSign := 'C';
            END IF;
        --  DBMS_OUTPUT.PUT_LINE('Type=' || v_AccountType || ' - Sign=' || v_AccountSign);
        END IF;
        --  Debit Balance
        IF (v_AccountSign = 'C') THEN
            v_balance := p_AmtCr - p_AmtDr;
        END IF;
    END IF;
    --
    RETURN v_balance;
EXCEPTION WHEN OTHERS THEN
    -- In case Acct not found
    RETURN  p_AmtDr - p_AmtCr;
END acctBalance;
/