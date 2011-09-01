 /**********************************************************************
 * This file is part of ADempiere Business Suite                       *
 * http://www.adempiere.org                                            *
 *                                                                     *
 * Copyright (C) Trifon Trifonov.                                      *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
 *                                                                     *
 ***********************************************************************
 * 
 * Title.........: Calculate Balance based on Account Sign + Type
 * Description...: If an account is specified and found 
 *                 - If the account sign is Natural it sets it based on Account Type
 *                 Returns Credit or Debit Balance
 * 
 * Test..........: SELECT acctBalance(0, 11, 22);  => -11.0000000000
 *                 SELECT AccountType, AccountSign, 
                     acctBalance(C_ElementValue_ID, 20, 10) "DR Balance",
                     acctBalance(C_ElementValue_ID, 10, 20) "CR Balance"
                   FROM C_ElementValue
                   WHERE AccountSign <> 'N'
                   ORDER BY AccountSign            => EMPTY Set as DB do not have accounts with NON 'N' AccountSign
 * 
 * Converted to MySQL..: by Trifon Trifonov
 ************************************************************************/
DELIMITER $$

-- ## Drop statement
DROP FUNCTION IF EXISTS acctBalance $$

-- ## Create statement
CREATE FUNCTION acctBalance (
  p_Account_ID  DECIMAL(10, 0),
  p_AmtDr       DECIMAL(20, 10),
  p_AmtCr       DECIMAL(20, 10)
)
  RETURNS DECIMAL(20, 10)
  DETERMINISTIC
BEGIN
  DECLARE v_balance      DECIMAL(20, 10);
  DECLARE v_AccountType  CHAR(1);
  DECLARE v_AccountSign  CHAR(1);
  
  SET v_balance = p_AmtDr - p_AmtCr;
  --
  IF (p_Account_ID > 0) THEN
    SELECT AccountType, AccountSign
    INTO v_AccountType, v_AccountSign
    FROM C_ElementValue
    WHERE C_ElementValue_ID=p_Account_ID;
    
    -- Natural Account Sign
    IF (v_AccountSign='N') THEN
      IF (v_AccountType IN ('A','E')) THEN
        SET v_AccountSign = 'D';
      ELSE
        SET v_AccountSign = 'C';
      END IF;
      
    END IF;
    
    -- Debit Balance
    IF (v_AccountSign = 'C') THEN
      SET v_balance = p_AmtCr - p_AmtDr;
    END IF;
    
    RETURN v_balance;
  ELSE
    -- Account not found
    RETURN v_balance;
  END IF;  
END$$
DELIMITER ;