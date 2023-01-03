/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DD_TransportUnit
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_DD_TransportUnit 
{

    /** TableName=DD_TransportUnit */
    public static final String Table_Name = "DD_TransportUnit";

    /** AD_Table_ID=54248 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public org.adempiere.core.domains.models.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

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

    /** Column name AxlesNumber */
    public static final String COLUMNNAME_AxlesNumber = "AxlesNumber";

	/** Set Number of axles	  */
	public void setAxlesNumber (BigDecimal AxlesNumber);

	/** Get Number of axles	  */
	public BigDecimal getAxlesNumber();

    /** Column name CarrierNumber */
    public static final String COLUMNNAME_CarrierNumber = "CarrierNumber";

	/** Set Carrier Number	  */
	public void setCarrierNumber (String CarrierNumber);

	/** Get Carrier Number	  */
	public String getCarrierNumber();

    /** Column name CopyFrom */
    public static final String COLUMNNAME_CopyFrom = "CopyFrom";

	/** Set Copy From.
	  * Copy From Record
	  */
	public void setCopyFrom (String CopyFrom);

	/** Get Copy From.
	  * Copy From Record
	  */
	public String getCopyFrom();

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

    /** Column name DD_CompatibilityGroup_ID */
    public static final String COLUMNNAME_DD_CompatibilityGroup_ID = "DD_CompatibilityGroup_ID";

	/** Set Transport Compatibility Group	  */
	public void setDD_CompatibilityGroup_ID (int DD_CompatibilityGroup_ID);

	/** Get Transport Compatibility Group	  */
	public int getDD_CompatibilityGroup_ID();

	public org.adempiere.core.domains.models.I_DD_CompatibilityGroup getDD_CompatibilityGroup() throws RuntimeException;

    /** Column name DD_TransportUnit_ID */
    public static final String COLUMNNAME_DD_TransportUnit_ID = "DD_TransportUnit_ID";

	/** Set Transport Unit	  */
	public void setDD_TransportUnit_ID (int DD_TransportUnit_ID);

	/** Get Transport Unit	  */
	public int getDD_TransportUnit_ID();

    /** Column name DD_TransportUnitType_ID */
    public static final String COLUMNNAME_DD_TransportUnitType_ID = "DD_TransportUnitType_ID";

	/** Set Transport Unit Type	  */
	public void setDD_TransportUnitType_ID (int DD_TransportUnitType_ID);

	/** Get Transport Unit Type	  */
	public int getDD_TransportUnitType_ID();

	public org.adempiere.core.domains.models.I_DD_TransportUnitType getDD_TransportUnitType() throws RuntimeException;

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

    /** Column name Dimension_UOM_ID */
    public static final String COLUMNNAME_Dimension_UOM_ID = "Dimension_UOM_ID";

	/** Set Dimension Unit of Measure	  */
	public void setDimension_UOM_ID (int Dimension_UOM_ID);

	/** Get Dimension Unit of Measure	  */
	public int getDimension_UOM_ID();

	public org.adempiere.core.domains.models.I_C_UOM getDimension_UOM() throws RuntimeException;

    /** Column name DocumentNote */
    public static final String COLUMNNAME_DocumentNote = "DocumentNote";

	/** Set Document Note.
	  * Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote);

	/** Get Document Note.
	  * Additional information for a Document
	  */
	public String getDocumentNote();

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

    /** Column name MaximumVolume */
    public static final String COLUMNNAME_MaximumVolume = "MaximumVolume";

	/** Set Maximum Volume	  */
	public void setMaximumVolume (BigDecimal MaximumVolume);

	/** Get Maximum Volume	  */
	public BigDecimal getMaximumVolume();

    /** Column name MaximumWeight */
    public static final String COLUMNNAME_MaximumWeight = "MaximumWeight";

	/** Set Maximum Weight	  */
	public void setMaximumWeight (BigDecimal MaximumWeight);

	/** Get Maximum Weight	  */
	public BigDecimal getMaximumWeight();

    /** Column name MinimumVolume */
    public static final String COLUMNNAME_MinimumVolume = "MinimumVolume";

	/** Set Minimum Volume	  */
	public void setMinimumVolume (BigDecimal MinimumVolume);

	/** Get Minimum Volume	  */
	public BigDecimal getMinimumVolume();

    /** Column name MinimumWeight */
    public static final String COLUMNNAME_MinimumWeight = "MinimumWeight";

	/** Set Minimum Weight.
	  * Minimum Weight of a product
	  */
	public void setMinimumWeight (BigDecimal MinimumWeight);

	/** Get Minimum Weight.
	  * Minimum Weight of a product
	  */
	public BigDecimal getMinimumWeight();

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

    /** Column name TransportHeight */
    public static final String COLUMNNAME_TransportHeight = "TransportHeight";

	/** Set Transport Height	  */
	public void setTransportHeight (BigDecimal TransportHeight);

	/** Get Transport Height	  */
	public BigDecimal getTransportHeight();

    /** Column name TransportLength */
    public static final String COLUMNNAME_TransportLength = "TransportLength";

	/** Set Transport Length	  */
	public void setTransportLength (BigDecimal TransportLength);

	/** Get Transport Length	  */
	public BigDecimal getTransportLength();

    /** Column name TransportStatus */
    public static final String COLUMNNAME_TransportStatus = "TransportStatus";

	/** Set Transport Status.
	  * Transport unit status
	  */
	public void setTransportStatus (String TransportStatus);

	/** Get Transport Status.
	  * Transport unit status
	  */
	public String getTransportStatus();

    /** Column name TransportWidth */
    public static final String COLUMNNAME_TransportWidth = "TransportWidth";

	/** Set Transport Width	  */
	public void setTransportWidth (BigDecimal TransportWidth);

	/** Get Transport Width	  */
	public BigDecimal getTransportWidth();

    /** Column name UnladenWeight */
    public static final String COLUMNNAME_UnladenWeight = "UnladenWeight";

	/** Set Unladen Weight	  */
	public void setUnladenWeight (BigDecimal UnladenWeight);

	/** Get Unladen Weight	  */
	public BigDecimal getUnladenWeight();

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

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name Volume */
    public static final String COLUMNNAME_Volume = "Volume";

	/** Set Volume.
	  * Volume of a product
	  */
	public void setVolume (BigDecimal Volume);

	/** Get Volume.
	  * Volume of a product
	  */
	public BigDecimal getVolume();

    /** Column name Volume_UOM_ID */
    public static final String COLUMNNAME_Volume_UOM_ID = "Volume_UOM_ID";

	/** Set Volume Unit of Measure	  */
	public void setVolume_UOM_ID (int Volume_UOM_ID);

	/** Get Volume Unit of Measure	  */
	public int getVolume_UOM_ID();

	public org.adempiere.core.domains.models.I_C_UOM getVolume_UOM() throws RuntimeException;

    /** Column name Weight */
    public static final String COLUMNNAME_Weight = "Weight";

	/** Set Weight.
	  * Weight of a product
	  */
	public void setWeight (BigDecimal Weight);

	/** Get Weight.
	  * Weight of a product
	  */
	public BigDecimal getWeight();

    /** Column name Weight_UOM_ID */
    public static final String COLUMNNAME_Weight_UOM_ID = "Weight_UOM_ID";

	/** Set Weight Unit of Measure	  */
	public void setWeight_UOM_ID (int Weight_UOM_ID);

	/** Get Weight Unit of Measure	  */
	public int getWeight_UOM_ID();

	public org.adempiere.core.domains.models.I_C_UOM getWeight_UOM() throws RuntimeException;
}
