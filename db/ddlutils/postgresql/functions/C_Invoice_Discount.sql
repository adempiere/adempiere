/*
 *This file is part of Adempiere ERP Bazaar
 *http://www.adempiere.org
 *
 *Copyright (C) 2007 Gavin Dunse
 *Copyright (C) 1999-2006 ComPiere, inc
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.of
 */

/*
 * Calculate Payment Discount Amount
 * - Calculate discountable amount (i.e. with or without tax)
 * - Calculate and return payment discount
 */

CREATE OR REPLACE FUNCTION invoiceDiscount (
  IN NUMERIC,                   -- $1 C_Invoice_ID
  IN TIMESTAMP WITH TIME ZONE,  -- $2 PayDate
  IN NUMERIC                    -- $3 C_InvoicePaySchedule_ID
) RETURNS NUMERIC AS
$$
  DECLARE
  v_Amount             NUMERIC;
  v_IsDiscountLineAmt  CHAR(1);
  v_GrandTotal         NUMERIC;
  v_TotalLines         NUMERIC;
  v_C_PaymentTerm_ID   NUMERIC;
  v_DocDate            TIMESTAMP WITH TIME ZONE;
  v_PayDate            TIMESTAMP WITH TIME ZONE;
  v_IsPayScheduleValid CHAR(1);

  BEGIN
    SELECT  CURRENT_DATE INTO v_PayDate;
    SELECT  ci.IsDiscountLineAmt, i.GrandTotal, i.TotalLines, i.C_PaymentTerm_ID, i.DateInvoiced, i.IsPayScheduleValid
    INTO    v_IsDiscountLineAmt, v_GrandTotal, v_TotalLines, v_C_PaymentTerm_ID, v_DocDate, v_IsPayScheduleValid
    FROM    AD_ClientInfo AS ci, C_Invoice AS i
    WHERE   ci.AD_Client_ID=i.AD_Client_ID
    AND     i.C_Invoice_ID=p_C_Invoice_ID;
    IF FOUND THEN
      /*	What Amount is the Discount Base? */
      IF (v_IsDiscountLineAmt = 'Y') THEN
        v_Amount := v_TotalLines;
      ELSE
        v_Amount := v_GrandTotal;
      END IF;

      /* Anything to discount? */
      IF (v_Amount = 0) THEN
        RETURN 0;
      END IF;
      IF (p_PayDate IS NOT NULL) THEN
        v_PayDate := p_PayDate;
      END IF;

      /*  Valid Payment Schedule */
      IF (v_IsPayScheduleValid='Y' AND p_C_InvoicePaySchedule_ID > 0) THEN
        SELECT COALESCE(MAX(DiscountAmt),0)
        INTO v_Amount
        FROM C_InvoicePaySchedule
        WHERE C_InvoicePaySchedule_ID=p_C_InvoicePaySchedule_ID
        AND DiscountDate <= v_PayDate;

        RETURN v_Amount;
      END IF;
      /* return discount amount */
      RETURN paymentTermDiscount (v_Amount, 0, v_C_PaymentTerm_ID, v_DocDate, p_PayDate);
    ELSE
      RETURN -1;
      /* RAISE EXCEPTION 'Some error'*/
      /* RETURN NULL */
    END IF;
  END;
$$ LANGUAGE plpgsql;

