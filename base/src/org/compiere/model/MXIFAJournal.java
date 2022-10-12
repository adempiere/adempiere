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
package org.compiere.model;
import java.util.*;
import java.sql.*;
import java.math.*;

import org.adempiere.core.domains.models.X_I_FAJournal;
import org.compiere.model.*;
import org.compiere.util.*;
/** Generated Model for A_Asset
 ** @version $Id: X_A_Asset.java,v 1.88 2004/08/27 21:26:37 jjanke Exp $ */
public class MXIFAJournal extends X_I_FAJournal
{
	public MXIFAJournal (Properties ctx, int I_FAJournal_ID, String trxName)
	{
		super (ctx, I_FAJournal_ID, trxName);
		// if (I_FAJournal_ID == 0)
		// {
		//	setIsDepreciated (false);
		//	setIsFullyDepreciated (false);
		//	setValue (null);
		//	setName (null);
		//	setIsInPosession (false);
		//	setIsOwned (false);
		//	setA_Asset_Group_ID (0);
		//	setIsDisposed (false);
		//	setM_AttributeSetInstance_ID(0);
		// }
	}	//	MXIFAJournal

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MXIFAJournal (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MAsset


	public BigDecimal getExpenseDr()
	{
	   BigDecimal bd = getAmtAcctDr();
	   return bd;
	}

	public BigDecimal getExpenseCr()
	{
		 BigDecimal bd = getAmtAcctCr();
		 return bd;
	}

	public BigDecimal getAmtAcctTotal()
	   {
	   BigDecimal dr = getAmtAcctDr();
	   BigDecimal cr = getAmtAcctCr();   
	   BigDecimal bd = (dr).subtract(cr);
	   if (bd == null) return Env.ZERO;
	   return bd;
	   }
	   /** Set Currency Type.
	   Currency Conversion Rate Type */
	   public void setC_ConversionType_ID (int C_ConversionType_ID)
	   {
	   if (C_ConversionType_ID == 0) set_Value ("C_ConversionType_ID", null);
		else 
	   set_Value ("C_ConversionType_ID", Integer.valueOf(C_ConversionType_ID));
	   }
	   /** Get Currency Type.
	   Currency Conversion Rate Type */
	   public int getC_ConversionType_ID() 
	   {
	   Integer ii = (Integer)get_Value("C_ConversionType_ID");
	   if (ii == null) return 0;
	   return ii.intValue();
	   }


}
