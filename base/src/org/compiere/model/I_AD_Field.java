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

    /** Generated Interface for AD_Field
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:21.921
     */
    public interface I_AD_Field 
{

    /** TableName=AD_Field */
    public static final String Table_Name = "AD_Field";

    /** AD_Table_ID=107 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = new BigDecimal(4);

    /** Load Meta Data */

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getI_AD_Column() throws Exception;

    /** Column name AD_FieldGroup_ID */
    public static final String COLUMNNAME_AD_FieldGroup_ID = "AD_FieldGroup_ID";

	/** Set Field Group.
	  * Logical grouping of fields
	  */
	public void setAD_FieldGroup_ID (int AD_FieldGroup_ID);

	/** Get Field Group.
	  * Logical grouping of fields
	  */
	public int getAD_FieldGroup_ID();

	public I_AD_FieldGroup getI_AD_FieldGroup() throws Exception;

    /** Column name AD_Field_ID */
    public static final String COLUMNNAME_AD_Field_ID = "AD_Field_ID";

	/** Set Field.
	  * Field on a database table
	  */
	public void setAD_Field_ID (int AD_Field_ID);

	/** Get Field.
	  * Field on a database table
	  */
	public int getAD_Field_ID();

    /** Column name AD_Reference_ID */
    public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";

	/** Set Reference.
	  * System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID);

	/** Get Reference.
	  * System Reference and Validation
	  */
	public int getAD_Reference_ID();

    /** Column name AD_Tab_ID */
    public static final String COLUMNNAME_AD_Tab_ID = "AD_Tab_ID";

	/** Set Tab.
	  * Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID);

	/** Get Tab.
	  * Tab within a Window
	  */
	public int getAD_Tab_ID();

	public I_AD_Tab getI_AD_Tab() throws Exception;

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

    /** Column name DisplayLength */
    public static final String COLUMNNAME_DisplayLength = "DisplayLength";

	/** Set Display Length.
	  * Length of the display in characters
	  */
	public void setDisplayLength (int DisplayLength);

	/** Get Display Length.
	  * Length of the display in characters
	  */
	public int getDisplayLength();

    /** Column name DisplayLogic */
    public static final String COLUMNNAME_DisplayLogic = "DisplayLogic";

	/** Set Display Logic.
	  * If the Field is displayed, the result determines if the field is actually displayed
	  */
	public void setDisplayLogic (String DisplayLogic);

	/** Get Display Logic.
	  * If the Field is displayed, the result determines if the field is actually displayed
	  */
	public String getDisplayLogic();

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsCentrallyMaintained */
    public static final String COLUMNNAME_IsCentrallyMaintained = "IsCentrallyMaintained";

	/** Set Centrally maintained.
	  * Information maintained in System Element table
	  */
	public void setIsCentrallyMaintained (boolean IsCentrallyMaintained);

	/** Get Centrally maintained.
	  * Information maintained in System Element table
	  */
	public boolean isCentrallyMaintained();

    /** Column name IsDisplayed */
    public static final String COLUMNNAME_IsDisplayed = "IsDisplayed";

	/** Set Displayed.
	  * Determines, if this field is displayed
	  */
	public void setIsDisplayed (boolean IsDisplayed);

	/** Get Displayed.
	  * Determines, if this field is displayed
	  */
	public boolean isDisplayed();

    /** Column name IsEncrypted */
    public static final String COLUMNNAME_IsEncrypted = "IsEncrypted";

	/** Set Encrypted.
	  * Display or Storage is encrypted
	  */
	public void setIsEncrypted (boolean IsEncrypted);

	/** Get Encrypted.
	  * Display or Storage is encrypted
	  */
	public boolean isEncrypted();

    /** Column name IsFieldOnly */
    public static final String COLUMNNAME_IsFieldOnly = "IsFieldOnly";

	/** Set Field Only.
	  * Label is not displayed
	  */
	public void setIsFieldOnly (boolean IsFieldOnly);

	/** Get Field Only.
	  * Label is not displayed
	  */
	public boolean isFieldOnly();

    /** Column name IsHeading */
    public static final String COLUMNNAME_IsHeading = "IsHeading";

	/** Set Heading only.
	  * Field without Column - Only label is displayed
	  */
	public void setIsHeading (boolean IsHeading);

	/** Get Heading only.
	  * Field without Column - Only label is displayed
	  */
	public boolean isHeading();

    /** Column name IsMandatory */
    public static final String COLUMNNAME_IsMandatory = "IsMandatory";

	/** Set Mandatory.
	  * Data entry is required in this column
	  */
	public void setIsMandatory (String IsMandatory);

	/** Get Mandatory.
	  * Data entry is required in this column
	  */
	public String getIsMandatory();

    /** Column name IsReadOnly */
    public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

	/** Set Read Only.
	  * Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly);

	/** Get Read Only.
	  * Field is read only
	  */
	public boolean isReadOnly();

    /** Column name IsSameLine */
    public static final String COLUMNNAME_IsSameLine = "IsSameLine";

	/** Set Same Line.
	  * Displayed on same line as previous field
	  */
	public void setIsSameLine (boolean IsSameLine);

	/** Get Same Line.
	  * Displayed on same line as previous field
	  */
	public boolean isSameLine();

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

    /** Column name ObscureType */
    public static final String COLUMNNAME_ObscureType = "ObscureType";

	/** Set Obscure.
	  * Type of obscuring the data (limiting the display)
	  */
	public void setObscureType (String ObscureType);

	/** Get Obscure.
	  * Type of obscuring the data (limiting the display)
	  */
	public String getObscureType();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name SortNo */
    public static final String COLUMNNAME_SortNo = "SortNo";

	/** Set Record Sort No.
	  * Determines in what order the records are displayed
	  */
	public void setSortNo (BigDecimal SortNo);

	/** Get Record Sort No.
	  * Determines in what order the records are displayed
	  */
	public BigDecimal getSortNo();
}
