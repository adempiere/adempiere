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

    /** Generated Interface for AD_PrintFormat
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:25.484
     */
    public interface I_AD_PrintFormat 
{

    /** TableName=AD_PrintFormat */
    public static final String Table_Name = "AD_PrintFormat";

    /** AD_Table_ID=493 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(7);

    /** Load Meta Data */

    /** Column name AD_PrintColor_ID */
    public static final String COLUMNNAME_AD_PrintColor_ID = "AD_PrintColor_ID";

	/** Set Print Color.
	  * Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID);

	/** Get Print Color.
	  * Color used for printing and display
	  */
	public int getAD_PrintColor_ID();

	public I_AD_PrintColor getI_AD_PrintColor() throws Exception;

    /** Column name AD_PrintFont_ID */
    public static final String COLUMNNAME_AD_PrintFont_ID = "AD_PrintFont_ID";

	/** Set Print Font.
	  * Maintain Print Font
	  */
	public void setAD_PrintFont_ID (int AD_PrintFont_ID);

	/** Get Print Font.
	  * Maintain Print Font
	  */
	public int getAD_PrintFont_ID();

	public I_AD_PrintFont getI_AD_PrintFont() throws Exception;

    /** Column name AD_PrintFormat_ID */
    public static final String COLUMNNAME_AD_PrintFormat_ID = "AD_PrintFormat_ID";

	/** Set Print Format.
	  * Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID);

	/** Get Print Format.
	  * Data Print Format
	  */
	public int getAD_PrintFormat_ID();

    /** Column name AD_PrintPaper_ID */
    public static final String COLUMNNAME_AD_PrintPaper_ID = "AD_PrintPaper_ID";

	/** Set Print Paper.
	  * Printer paper definition
	  */
	public void setAD_PrintPaper_ID (int AD_PrintPaper_ID);

	/** Get Print Paper.
	  * Printer paper definition
	  */
	public int getAD_PrintPaper_ID();

	public I_AD_PrintPaper getI_AD_PrintPaper() throws Exception;

    /** Column name AD_PrintTableFormat_ID */
    public static final String COLUMNNAME_AD_PrintTableFormat_ID = "AD_PrintTableFormat_ID";

	/** Set Print Table Format.
	  * Table Format in Reports
	  */
	public void setAD_PrintTableFormat_ID (int AD_PrintTableFormat_ID);

	/** Get Print Table Format.
	  * Table Format in Reports
	  */
	public int getAD_PrintTableFormat_ID();

	public I_AD_PrintTableFormat getI_AD_PrintTableFormat() throws Exception;

    /** Column name AD_ReportView_ID */
    public static final String COLUMNNAME_AD_ReportView_ID = "AD_ReportView_ID";

	/** Set Report View.
	  * View used to generate this report
	  */
	public void setAD_ReportView_ID (int AD_ReportView_ID);

	/** Get Report View.
	  * View used to generate this report
	  */
	public int getAD_ReportView_ID();

	public I_AD_ReportView getI_AD_ReportView() throws Exception;

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

	public I_AD_Table getI_AD_Table() throws Exception;

    /** Column name CreateCopy */
    public static final String COLUMNNAME_CreateCopy = "CreateCopy";

	/** Set Create Copy	  */
	public void setCreateCopy (String CreateCopy);

	/** Get Create Copy	  */
	public String getCreateCopy();

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

    /** Column name FooterMargin */
    public static final String COLUMNNAME_FooterMargin = "FooterMargin";

	/** Set Footer Margin.
	  * Margin of the Footer in 1/72 of an inch
	  */
	public void setFooterMargin (int FooterMargin);

	/** Get Footer Margin.
	  * Margin of the Footer in 1/72 of an inch
	  */
	public int getFooterMargin();

    /** Column name HeaderMargin */
    public static final String COLUMNNAME_HeaderMargin = "HeaderMargin";

	/** Set Header Margin.
	  * Margin of the Header in 1/72 of an inch
	  */
	public void setHeaderMargin (int HeaderMargin);

	/** Get Header Margin.
	  * Margin of the Header in 1/72 of an inch
	  */
	public int getHeaderMargin();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsForm */
    public static final String COLUMNNAME_IsForm = "IsForm";

	/** Set Form.
	  * If Selected, a Form is printed, if not selected a columnar List report
	  */
	public void setIsForm (boolean IsForm);

	/** Get Form.
	  * If Selected, a Form is printed, if not selected a columnar List report
	  */
	public boolean isForm();

    /** Column name IsStandardHeaderFooter */
    public static final String COLUMNNAME_IsStandardHeaderFooter = "IsStandardHeaderFooter";

	/** Set Standard Header/Footer.
	  * The standard Header and Footer is used
	  */
	public void setIsStandardHeaderFooter (boolean IsStandardHeaderFooter);

	/** Get Standard Header/Footer.
	  * The standard Header and Footer is used
	  */
	public boolean isStandardHeaderFooter();

    /** Column name IsTableBased */
    public static final String COLUMNNAME_IsTableBased = "IsTableBased";

	/** Set Table Based.
	  * Table based List Reporting
	  */
	public void setIsTableBased (boolean IsTableBased);

	/** Get Table Based.
	  * Table based List Reporting
	  */
	public boolean isTableBased();

    /** Column name JasperProcess_ID */
    public static final String COLUMNNAME_JasperProcess_ID = "JasperProcess_ID";

	/** Set Jasper Process.
	  * The Jasper Process used by the printengine if any process defined
	  */
	public void setJasperProcess_ID (int JasperProcess_ID);

	/** Get Jasper Process.
	  * The Jasper Process used by the printengine if any process defined
	  */
	public int getJasperProcess_ID();

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

    /** Column name PrinterName */
    public static final String COLUMNNAME_PrinterName = "PrinterName";

	/** Set Printer Name.
	  * Name of the Printer
	  */
	public void setPrinterName (String PrinterName);

	/** Get Printer Name.
	  * Name of the Printer
	  */
	public String getPrinterName();
}
