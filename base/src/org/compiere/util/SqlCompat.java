/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * SQL-compatible utility functions for function migration.
 * These methods match PostgreSQL function semantics exactly.
 */
public class SqlCompat {

    private SqlCompat() {
        // Utility class - prevent instantiation
    }

    /**
     * Round a numeric value to specified scale (SQL semantics).
     * Equivalent to PostgreSQL: ROUND($1, cast($2 as integer))
     * Uses HALF_UP rounding mode (standard SQL behavior).
     *
     * @param value value to round (may be null)
     * @param scale number of decimal places (can be negative for rounding to 10s, 100s, etc.)
     * @return rounded value, or null if value is null
     */
    public static BigDecimal round(BigDecimal value, int scale) {
        if (value == null) {
            return null;
        }

        if (scale >= 0) {
            return value.setScale(scale, RoundingMode.HALF_UP);
        } else {
            // Negative scale: round to 10s, 100s, etc.
            BigDecimal multiplier = BigDecimal.TEN.pow(-scale);
            BigDecimal divided = value.divide(multiplier, 0, RoundingMode.HALF_UP);
            return divided.multiply(multiplier);
        }
    }

    /**
     * Get character at position (SQL semantics, 1-based).
     * Equivalent to PostgreSQL: SUBSTR($1, $2, 1)
     *
     * @param str input string (may be null)
     * @param position 1-based position (0 or negative returns empty string)
     * @return character at position, empty string if out of bounds, null if str is null
     */
    public static String charAt(String str, int position) {
        if (str == null) {
            return null;
        }

        // SQL positions are 1-based
        int index = position - 1;

        if (index < 0 || index >= str.length()) {
            return "";
        }

        return String.valueOf(str.charAt(index));
    }
}
