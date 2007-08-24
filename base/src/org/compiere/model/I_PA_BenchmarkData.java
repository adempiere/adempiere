/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for PA_BenchmarkData
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:52.578
     */
    public interface I_PA_BenchmarkData 
{

    /** TableName=PA_BenchmarkData */
    public static final String Table_Name = "PA_BenchmarkData";

    /** AD_Table_ID=834 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = new BigDecimal(6);

    /** Load Meta Data */

    /** Column name BenchmarkDate */
    public static final String COLUMNNAME_BenchmarkDate = "BenchmarkDate";

	/** Set Date.
	  * Benchmark Date
	  */
	public void setBenchmarkDate (Timestamp BenchmarkDate);

	/** Get Date.
	  * Benchmark Date
	  */
	public Timestamp getBenchmarkDate();

    /** Column name BenchmarkValue */
    public static final String COLUMNNAME_BenchmarkValue = "BenchmarkValue";

	/** Set Value.
	  * Benchmark Value
	  */
	public void setBenchmarkValue (BigDecimal BenchmarkValue);

	/** Get Value.
	  * Benchmark Value
	  */
	public BigDecimal getBenchmarkValue();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PA_BenchmarkData_ID */
    public static final String COLUMNNAME_PA_BenchmarkData_ID = "PA_BenchmarkData_ID";

	/** Set Benchmark Data.
	  * Performance Benchmark Data Point
	  */
	public void setPA_BenchmarkData_ID (int PA_BenchmarkData_ID);

	/** Get Benchmark Data.
	  * Performance Benchmark Data Point
	  */
	public int getPA_BenchmarkData_ID();

    /** Column name PA_Benchmark_ID */
    public static final String COLUMNNAME_PA_Benchmark_ID = "PA_Benchmark_ID";

	/** Set Benchmark.
	  * Performance Benchmark
	  */
	public void setPA_Benchmark_ID (int PA_Benchmark_ID);

	/** Get Benchmark.
	  * Performance Benchmark
	  */
	public int getPA_Benchmark_ID();

	public I_PA_Benchmark getI_PA_Benchmark() throws Exception;
}
