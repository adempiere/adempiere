CREATE OR REPLACE FUNCTION financialRateToDate
(
  p_FM_Rate_ID IN NUMBER, 
  DateTo DATE
)
/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************
 ***
 * Title:	Get Financial Rate to Date
 * Description:
 *	Get a Financial Rate to a specific Date
 * Test:
 * 	SELECT financialRateToDate (113, null) FROM DUAL; - Get monthly salary for GardenAdmin BP now
 * 	SELECT financialRateToDate (113, SYSDATE) FROM DUAL; - Get monthly salary for GardenAdmin BP now
 ************************************************************************/
RETURN NUMBER AS
	v_Rate  	NUMBER := 0;
BEGIN
	SELECT rv.Rate INTO v_Rate
		FROM FM_RateVersion rv
		WHERE rv.FM_Rate_ID = p_FM_Rate_ID
		AND rv.IsActive = 'Y'
		AND rv.ValidFrom <= COALESCE(DateTo, getdate())
		AND ROWNUM <= 1
		ORDER BY rv.ValidFrom DESC;
	RETURN v_Rate;
END financialRateToDate;