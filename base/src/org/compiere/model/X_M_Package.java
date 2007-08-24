/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for M_Package
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_M_Package extends PO implements I_M_Package, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_M_Package (Properties ctx, int M_Package_ID, String trxName)
    {
      super (ctx, M_Package_ID, trxName);
      /** if (M_Package_ID == 0)        {			setDocumentNo (null);
			setM_InOut_ID (0);
			setM_Package_ID (0);
			setM_Shipper_ID (0);
} */
    }

    /** Load Constructor */
    public X_M_Package (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_M_Package[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date received.
		@param DateReceived 
		Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived)
	{
		set_Value (COLUMNNAME_DateReceived, DateReceived);
	}

	/** Get Date received.
		@return Date a product was received
	  */
	public Timestamp getDateReceived () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReceived);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		if (DocumentNo == null)
			throw new IllegalArgumentException ("DocumentNo is mandatory.");
		if (DocumentNo.length() > 30)
		{
			log.warning("Length > 30 - truncated");
			DocumentNo = DocumentNo.substring(0, 29);
		}
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	public I_M_InOut getI_M_InOut() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_InOut.Table_Name);
        I_M_InOut result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_InOut)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_InOut_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Shipment/Receipt.
		@param M_InOut_ID 
		Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID)
	{
		if (M_InOut_ID < 1)
			 throw new IllegalArgumentException ("M_InOut_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_InOut_ID, Integer.valueOf(M_InOut_ID));
	}

	/** Get Shipment/Receipt.
		@return Material Shipment Document
	  */
	public int getM_InOut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Package.
		@param M_Package_ID 
		Shipment Package
	  */
	public void setM_Package_ID (int M_Package_ID)
	{
		if (M_Package_ID < 1)
			 throw new IllegalArgumentException ("M_Package_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_M_Package_ID, Integer.valueOf(M_Package_ID));
	}

	/** Get Package.
		@return Shipment Package
	  */
	public int getM_Package_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Package_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Shipper getI_M_Shipper() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_M_Shipper.Table_Name);
        I_M_Shipper result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_M_Shipper)constructor.newInstance(new Object[] {getCtx(), new Integer(getM_Shipper_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Shipper.
		@param M_Shipper_ID 
		Method or manner of product delivery
	  */
	public void setM_Shipper_ID (int M_Shipper_ID)
	{
		if (M_Shipper_ID < 1)
			 throw new IllegalArgumentException ("M_Shipper_ID is mandatory.");
		set_Value (COLUMNNAME_M_Shipper_ID, Integer.valueOf(M_Shipper_ID));
	}

	/** Get Shipper.
		@return Method or manner of product delivery
	  */
	public int getM_Shipper_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Shipper_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Info Received.
		@param ReceivedInfo 
		Information of the receipt of the package (acknowledgement)
	  */
	public void setReceivedInfo (String ReceivedInfo)
	{
		if (ReceivedInfo != null && ReceivedInfo.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			ReceivedInfo = ReceivedInfo.substring(0, 254);
		}
		set_Value (COLUMNNAME_ReceivedInfo, ReceivedInfo);
	}

	/** Get Info Received.
		@return Information of the receipt of the package (acknowledgement)
	  */
	public String getReceivedInfo () 
	{
		return (String)get_Value(COLUMNNAME_ReceivedInfo);
	}

	/** Set Ship Date.
		@param ShipDate 
		Shipment Date/Time
	  */
	public void setShipDate (Timestamp ShipDate)
	{
		set_Value (COLUMNNAME_ShipDate, ShipDate);
	}

	/** Get Ship Date.
		@return Shipment Date/Time
	  */
	public Timestamp getShipDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ShipDate);
	}

	/** Set Tracking Info.
		@param TrackingInfo Tracking Info	  */
	public void setTrackingInfo (String TrackingInfo)
	{
		if (TrackingInfo != null && TrackingInfo.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			TrackingInfo = TrackingInfo.substring(0, 254);
		}
		set_Value (COLUMNNAME_TrackingInfo, TrackingInfo);
	}

	/** Get Tracking Info.
@return Tracking Info	  */
	public String getTrackingInfo () 
	{
		return (String)get_Value(COLUMNNAME_TrackingInfo);
	}
}