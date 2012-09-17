/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Chart
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS
 */
public interface I_AD_Chart 
{

    /** TableName=AD_Chart */
    public static final String Table_Name = "AD_Chart";

    /** AD_Table_ID=53284 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Chart_ID */
    public static final String COLUMNNAME_AD_Chart_ID = "AD_Chart_ID";

	/** Set Chart	  */
	public void setAD_Chart_ID (int AD_Chart_ID);

	/** Get Chart	  */
	public int getAD_Chart_ID();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name ChartOrientation */
    public static final String COLUMNNAME_ChartOrientation = "ChartOrientation";

	/** Set Orientation.
	  * The orientation of the chart.
	  */
	public void setChartOrientation (String ChartOrientation);

	/** Get Orientation.
	  * The orientation of the chart.
	  */
	public String getChartOrientation();

    /** Column name ChartType */
    public static final String COLUMNNAME_ChartType = "ChartType";

	/** Set Chart Type.
	  * Type of chart to render
	  */
	public void setChartType (String ChartType);

	/** Get Chart Type.
	  * Type of chart to render
	  */
	public String getChartType();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

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

    /** Column name DomainLabel */
    public static final String COLUMNNAME_DomainLabel = "DomainLabel";

	/** Set Domain Label.
	  * Label for the domain axis.
	  */
	public void setDomainLabel (String DomainLabel);

	/** Get Domain Label.
	  * Label for the domain axis.
	  */
	public String getDomainLabel();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsDisplayLegend */
    public static final String COLUMNNAME_IsDisplayLegend = "IsDisplayLegend";

	/** Set Display Legend.
	  * Display chart legend
	  */
	public void setIsDisplayLegend (boolean IsDisplayLegend);

	/** Get Display Legend.
	  * Display chart legend
	  */
	public boolean isDisplayLegend();

    /** Column name IsTimeSeries */
    public static final String COLUMNNAME_IsTimeSeries = "IsTimeSeries";

	/** Set Time Series.
	  * The domain data for the chart is organised by time.
	  */
	public void setIsTimeSeries (boolean IsTimeSeries);

	/** Get Time Series.
	  * The domain data for the chart is organised by time.
	  */
	public boolean isTimeSeries();

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

    /** Column name RangeLabel */
    public static final String COLUMNNAME_RangeLabel = "RangeLabel";

	/** Set Range Label.
	  * Label for the range axis.
	  */
	public void setRangeLabel (String RangeLabel);

	/** Get Range Label.
	  * Label for the range axis.
	  */
	public String getRangeLabel();

    /** Column name TimeScope */
    public static final String COLUMNNAME_TimeScope = "TimeScope";

	/** Set Time Scope.
	  * The number of time units to include the chart result.
	  */
	public void setTimeScope (int TimeScope);

	/** Get Time Scope.
	  * The number of time units to include the chart result.
	  */
	public int getTimeScope();

    /** Column name TimeUnit */
    public static final String COLUMNNAME_TimeUnit = "TimeUnit";

	/** Set Time Unit.
	  * The unit of time for grouping chart data.
	  */
	public void setTimeUnit (String TimeUnit);

	/** Get Time Unit.
	  * The unit of time for grouping chart data.
	  */
	public String getTimeUnit();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name WinHeight */
    public static final String COLUMNNAME_WinHeight = "WinHeight";

	/** Set Window Height	  */
	public void setWinHeight (int WinHeight);

	/** Get Window Height	  */
	public int getWinHeight();
}
