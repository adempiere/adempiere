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
package org.eevolution.model;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import org.compiere.model.*;

/** Generated Model for PP_Order_NodeNext
 *  @author Adempiere (generated) 
 *  @version Release 3.5.1a - $Id$ */
public class X_PP_Order_NodeNext extends PO implements I_PP_Order_NodeNext, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_PP_Order_NodeNext (Properties ctx, int PP_Order_NodeNext_ID, String trxName)
    {
      super (ctx, PP_Order_NodeNext_ID, trxName);
      /** if (PP_Order_NodeNext_ID == 0)
        {
			setAD_WF_Node_ID (0);
			setEntityType (null);
// U
			setPP_Order_ID (0);
			setPP_Order_Node_ID (0);
			setSeqNo (0);
// 10
        } */
    }

    /** Load Constructor */
    public X_PP_Order_NodeNext (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
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
      StringBuffer sb = new StringBuffer ("X_PP_Order_NodeNext[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** AD_WF_Next_ID AD_Reference_ID=109 */
	public static final int AD_WF_NEXT_ID_AD_Reference_ID=109;
	/** Set Next Node.
		@param AD_WF_Next_ID 
		Next Node in workflow
	  */
	public void setAD_WF_Next_ID (int AD_WF_Next_ID)
	{
		if (AD_WF_Next_ID < 1) 
			set_Value (COLUMNNAME_AD_WF_Next_ID, null);
		else 
			set_Value (COLUMNNAME_AD_WF_Next_ID, Integer.valueOf(AD_WF_Next_ID));
	}

	/** Get Next Node.
		@return Next Node in workflow
	  */
	public int getAD_WF_Next_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Next_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_WF_Node getAD_WF_Node() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_AD_WF_Node.Table_Name);
        I_AD_WF_Node result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_AD_WF_Node)constructor.newInstance(new Object[] {getCtx(), new Integer(getAD_WF_Node_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Node.
		@param AD_WF_Node_ID 
		Workflow Node (activity), step or process
	  */
	public void setAD_WF_Node_ID (int AD_WF_Node_ID)
	{
		if (AD_WF_Node_ID < 1)
			 throw new IllegalArgumentException ("AD_WF_Node_ID is mandatory.");
		set_Value (COLUMNNAME_AD_WF_Node_ID, Integer.valueOf(AD_WF_Node_ID));
	}

	/** Get Node.
		@return Workflow Node (activity), step or process
	  */
	public int getAD_WF_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_WF_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			Description = Description.substring(0, 255);
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

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{

		if (EntityType.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			EntityType = EntityType.substring(0, 40);
		}
		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** Set Std User Workflow.
		@param IsStdUserWorkflow 
		Standard Manual User Approval Workflow
	  */
	public void setIsStdUserWorkflow (boolean IsStdUserWorkflow)
	{
		set_Value (COLUMNNAME_IsStdUserWorkflow, Boolean.valueOf(IsStdUserWorkflow));
	}

	/** Get Std User Workflow.
		@return Standard Manual User Approval Workflow
	  */
	public boolean isStdUserWorkflow () 
	{
		Object oo = get_Value(COLUMNNAME_IsStdUserWorkflow);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.eevolution.model.I_PP_Order getPP_Order() throws Exception 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Order.Table_Name);
        org.eevolution.model.I_PP_Order result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Order)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Order_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set PP_Order_ID.
		@param PP_Order_ID PP_Order_ID	  */
	public void setPP_Order_ID (int PP_Order_ID)
	{
		if (PP_Order_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
	}

	/** Get PP_Order_ID.
		@return PP_Order_ID	  */
	public int getPP_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** PP_Order_Next_ID AD_Reference_ID=53232 */
	public static final int PP_ORDER_NEXT_ID_AD_Reference_ID=53232;
	/** Set PP_Order_Next_ID.
		@param PP_Order_Next_ID PP_Order_Next_ID	  */
	public void setPP_Order_Next_ID (int PP_Order_Next_ID)
	{
		if (PP_Order_Next_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_Next_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_Next_ID, Integer.valueOf(PP_Order_Next_ID));
	}

	/** Get PP_Order_Next_ID.
		@return PP_Order_Next_ID	  */
	public int getPP_Order_Next_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Next_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PP_Order_NodeNext_ID.
		@param PP_Order_NodeNext_ID PP_Order_NodeNext_ID	  */
	public void setPP_Order_NodeNext_ID (int PP_Order_NodeNext_ID)
	{
		if (PP_Order_NodeNext_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Order_NodeNext_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Order_NodeNext_ID, Integer.valueOf(PP_Order_NodeNext_ID));
	}

	/** Get PP_Order_NodeNext_ID.
		@return PP_Order_NodeNext_ID	  */
	public int getPP_Order_NodeNext_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_NodeNext_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_Node getPP_Order_Node() throws Exception 
    {
        Class<?> clazz = MTable.getClass(org.eevolution.model.I_PP_Order_Node.Table_Name);
        org.eevolution.model.I_PP_Order_Node result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (org.eevolution.model.I_PP_Order_Node)constructor.newInstance(new Object[] {getCtx(), new Integer(getPP_Order_Node_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set PP_Order_Node_ID.
		@param PP_Order_Node_ID PP_Order_Node_ID	  */
	public void setPP_Order_Node_ID (int PP_Order_Node_ID)
	{
		if (PP_Order_Node_ID < 1)
			 throw new IllegalArgumentException ("PP_Order_Node_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_PP_Order_Node_ID, Integer.valueOf(PP_Order_Node_ID));
	}

	/** Get PP_Order_Node_ID.
		@return PP_Order_Node_ID	  */
	public int getPP_Order_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** SeqNo AD_Reference_ID=110 */
	public static final int SEQNO_AD_Reference_ID=110;
	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transition Code.
		@param TransitionCode 
		Code resulting in TRUE of FALSE
	  */
	public void setTransitionCode (String TransitionCode)
	{

		if (TransitionCode != null && TransitionCode.length() > 2000)
		{
			log.warning("Length > 2000 - truncated");
			TransitionCode = TransitionCode.substring(0, 2000);
		}
		set_Value (COLUMNNAME_TransitionCode, TransitionCode);
	}

	/** Get Transition Code.
		@return Code resulting in TRUE of FALSE
	  */
	public String getTransitionCode () 
	{
		return (String)get_Value(COLUMNNAME_TransitionCode);
	}
}