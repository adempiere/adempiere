/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2006 Timo Kontro
 *Copyright (C) 1999-2006 ComPiere, inc
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
 */

/*
 * Returns credit or debit balance based on account sign and type.
 * 
 * Account types and account signs are defined in ad_ref_list table.
 * In version 3.1.3 types and signs are defined in following way:
 *  value |      name      |               description
 *-------+----------------+------------------------------------------
 * A     | Asset          | Asset (Balance Sheet) Account
 * L     | Liability      | Liability (Balance Sheet) Account
 * R     | Revenue        | Revenue (P&L) Account
 * E     | Expense        | Expense (P&L) Account
 * O     | Owner's Equity | Owner's Equity (Balance Sheet) Account
 * M     | Memo           | Memo (Non Balance Sheet nor P&L) Account
 *
 *
 * N     | Natural        | Natural sign of the Account Type
 * D     | Debit          | Debit Balance Account
 * C     | Credit         | Credit Balance Account
 */
CREATE OR REPLACE FUNCTION acctBalance(
    IN NUMERIC, -- $1 account id
    IN NUMERIC, -- $2 amount debit
    IN NUMERIC  -- $3 amount credit
) RETURNS NUMERIC AS 
$$
  DECLARE
    accType CHAR(1); --account type
    accSign CHAR(1); --account sign
  BEGIN
    IF $1 != 0 THEN
      SELECT t.AccountType, t.AccountSign
        INTO accType, accSign
        FROM C_ElementValue AS t  WHERE t.C_ElementValue_ID = $1;
      IF accSign = 'N' AND accType NOT IN ('A', 'E') THEN
        --If account sign is natural and account type not asset or expense
        --mark sign to be credit.
        accSign := 'C';
      END IF;
    END IF;

    IF accSign = 'C' THEN
      RETURN $2 - $3;
    ELSE
      RETURN $3 - $2;
    END IF;
  END;
$$ LANGUAGE plpgsql STABLE STRICT;
