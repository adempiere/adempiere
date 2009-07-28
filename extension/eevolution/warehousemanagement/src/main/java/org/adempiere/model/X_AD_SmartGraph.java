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
/** Generated Model - DO NOT CHANGE */
package org.adempiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for AD_SmartGraph
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a - $Id$ */
public class X_AD_SmartGraph extends PO implements I_AD_SmartGraph, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20081221L;

    /** Standard Constructor */
    public X_AD_SmartGraph (Properties ctx, int AD_SmartGraph_ID, String trxName)
    {
      super (ctx, AD_SmartGraph_ID, trxName);
      /** if (AD_SmartGraph_ID == 0)
        {
			setAD_SmartGraph_ID (0);
        } */
    }

    /** Load Constructor */
    public X_AD_SmartGraph (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_SmartGraph[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Smart Graph ID.
		@param AD_SmartGraph_ID Smart Graph ID	  */
	public void setAD_SmartGraph_ID (int AD_SmartGraph_ID)
	{
		if (AD_SmartGraph_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_SmartGraph_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_SmartGraph_ID, Integer.valueOf(AD_SmartGraph_ID));
	}

	/** Get Smart Graph ID.
		@return Smart Graph ID	  */
	public int getAD_SmartGraph_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_SmartGraph_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_SmartGraph_ID()));
    }
}