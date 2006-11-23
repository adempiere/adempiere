/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for M_Package
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_M_Package extends PO
{
/** Standard Constructor
@param ctx context
@param M_Package_ID id
@param trxName transaction
*/
public X_M_Package (Properties ctx, int M_Package_ID, String trxName)
{
super (ctx, M_Package_ID, trxName);
/** if (M_Package_ID == 0)
{
setDocumentNo (null);
setM_InOut_ID (0);
setM_Package_ID (0);
setM_Shipper_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Package (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=664 */
public static final int Table_ID=664;

/** TableName=M_Package */
public static final String Table_Name="M_Package";

protected static KeyNamePair Model = new KeyNamePair(664,"M_Package");

protected BigDecimal accessLevel = new BigDecimal(1);
/** AccessLevel
@return 1 - Org 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_M_Package[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Date received.
@param DateReceived Date a product was received */
public void setDateReceived (Timestamp DateReceived)
{
set_Value ("DateReceived", DateReceived);
}
/** Get Date received.
@return Date a product was received */
public Timestamp getDateReceived() 
{
return (Timestamp)get_Value("DateReceived");
}
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Document No.
@param DocumentNo Document sequence number of the document */
public void setDocumentNo (String DocumentNo)
{
if (DocumentNo == null) throw new IllegalArgumentException ("DocumentNo is mandatory.");
if (DocumentNo.length() > 30)
{
log.warning("Length > 30 - truncated");
DocumentNo = DocumentNo.substring(0,29);
}
set_ValueNoCheck ("DocumentNo", DocumentNo);
}
/** Get Document No.
@return Document sequence number of the document */
public String getDocumentNo() 
{
return (String)get_Value("DocumentNo");
}
/** Set Shipment/Receipt.
@param M_InOut_ID Material Shipment Document */
public void setM_InOut_ID (int M_InOut_ID)
{
if (M_InOut_ID < 1) throw new IllegalArgumentException ("M_InOut_ID is mandatory.");
set_ValueNoCheck ("M_InOut_ID", new Integer(M_InOut_ID));
}
/** Get Shipment/Receipt.
@return Material Shipment Document */
public int getM_InOut_ID() 
{
Integer ii = (Integer)get_Value("M_InOut_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Package.
@param M_Package_ID Shipment Package */
public void setM_Package_ID (int M_Package_ID)
{
if (M_Package_ID < 1) throw new IllegalArgumentException ("M_Package_ID is mandatory.");
set_ValueNoCheck ("M_Package_ID", new Integer(M_Package_ID));
}
/** Get Package.
@return Shipment Package */
public int getM_Package_ID() 
{
Integer ii = (Integer)get_Value("M_Package_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Shipper.
@param M_Shipper_ID Method or manner of product delivery */
public void setM_Shipper_ID (int M_Shipper_ID)
{
if (M_Shipper_ID < 1) throw new IllegalArgumentException ("M_Shipper_ID is mandatory.");
set_Value ("M_Shipper_ID", new Integer(M_Shipper_ID));
}
/** Get Shipper.
@return Method or manner of product delivery */
public int getM_Shipper_ID() 
{
Integer ii = (Integer)get_Value("M_Shipper_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Info Received.
@param ReceivedInfo Information of the receipt of the package (acknowledgement) */
public void setReceivedInfo (String ReceivedInfo)
{
if (ReceivedInfo != null && ReceivedInfo.length() > 255)
{
log.warning("Length > 255 - truncated");
ReceivedInfo = ReceivedInfo.substring(0,254);
}
set_Value ("ReceivedInfo", ReceivedInfo);
}
/** Get Info Received.
@return Information of the receipt of the package (acknowledgement) */
public String getReceivedInfo() 
{
return (String)get_Value("ReceivedInfo");
}
/** Set Ship Date.
@param ShipDate Shipment Date/Time */
public void setShipDate (Timestamp ShipDate)
{
set_Value ("ShipDate", ShipDate);
}
/** Get Ship Date.
@return Shipment Date/Time */
public Timestamp getShipDate() 
{
return (Timestamp)get_Value("ShipDate");
}
/** Set Tracking Info.
@param TrackingInfo Tracking Info */
public void setTrackingInfo (String TrackingInfo)
{
if (TrackingInfo != null && TrackingInfo.length() > 255)
{
log.warning("Length > 255 - truncated");
TrackingInfo = TrackingInfo.substring(0,254);
}
set_Value ("TrackingInfo", TrackingInfo);
}
/** Get Tracking Info.
@return Tracking Info */
public String getTrackingInfo() 
{
return (String)get_Value("TrackingInfo");
}
}
