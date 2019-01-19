/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DD_TransportUnitType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_DD_TransportUnitType 
{

    /** TableName=DD_TransportUnitType */
    public static final String Table_Name = "DD_TransportUnitType";

    /** AD_Table_ID=54244 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

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

    /** Column name DD_TransportUnitType_ID */
    public static final String COLUMNNAME_DD_TransportUnitType_ID = "DD_TransportUnitType_ID";

	/** Set Transport Unit Type	  */
	public void setDD_TransportUnitType_ID (int DD_TransportUnitType_ID);

	/** Get Transport Unit Type	  */
	public int getDD_TransportUnitType_ID();

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

	public org.compiere.model.I_C_UOM getDimension_UOM() throws RuntimeException;

    /** Column name HasVaporRecoverySystem */
    public static final String COLUMNNAME_HasVaporRecoverySystem = "HasVaporRecoverySystem";

	/** Set Has Vapor Recovery System.
	  * Indicates the existence of a vapor recovery system
	  */
	public void setHasVaporRecoverySystem (boolean HasVaporRecoverySystem);

	/** Get Has Vapor Recovery System.
	  * Indicates the existence of a vapor recovery system
	  */
	public boolean isHasVaporRecoverySystem();

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

    /** Column name TransportMode */
    public static final String COLUMNNAME_TransportMode = "TransportMode";

	/** Set Transport Mode.
	  * The modes of transport supported
	  */
	public void setTransportMode (String TransportMode);

	/** Get Transport Mode.
	  * The modes of transport supported
	  */
	public String getTransportMode();

    /** Column name TransportPhysicalType */
    public static final String COLUMNNAME_TransportPhysicalType = "TransportPhysicalType";

	/** Set Transport Physical Type.
	  * The system divides the physical type of the transport unit into a part that carries the load, a mover, and other load bearing elements
	  */
	public void setTransportPhysicalType (String TransportPhysicalType);

	/** Get Transport Physical Type.
	  * The system divides the physical type of the transport unit into a part that carries the load, a mover, and other load bearing elements
	  */
	public String getTransportPhysicalType();

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

    /** Column name Volume_UOM_ID */
    public static final String COLUMNNAME_Volume_UOM_ID = "Volume_UOM_ID";

	/** Set Volume Unit of Measure	  */
	public void setVolume_UOM_ID (int Volume_UOM_ID);

	/** Get Volume Unit of Measure	  */
	public int getVolume_UOM_ID();

	public org.compiere.model.I_C_UOM getVolume_UOM() throws RuntimeException;

    /** Column name Weight_UOM_ID */
    public static final String COLUMNNAME_Weight_UOM_ID = "Weight_UOM_ID";

	/** Set Weight Unit of Measure	  */
	public void setWeight_UOM_ID (int Weight_UOM_ID);

	/** Get Weight Unit of Measure	  */
	public int getWeight_UOM_ID();

	public org.compiere.model.I_C_UOM getWeight_UOM() throws RuntimeException;
}
