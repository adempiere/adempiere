/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/
package org.eevolution.process;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.MRefList;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.eevolution.model.MPPProductBOMLine;


/**
 *	Component Change into BOM
 *	
 *  @author victor.perez@e-evolution.com
 *  @version $Id: ComponentChange.java
 *  
 *  @author Teo Sarca, www.arhipac.ro
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> BR [ 264 ] Bad index for Process SeqNo
 *		@see https://github.com/adempiere/adempiere/issues/264
 */
public class ComponentChange extends SvrProcess
{
	private static final int	ACTION_AD_Reference_ID	= 53227;
	private static final String ACTION_Add				= "A";
	private static final String ACTION_Deactivate		= "D";
	private static final String ACTION_Expire			= "E";
	private static final String ACTION_Replace			= "R";
	private static final String ACTION_ReplaceAndExpire = "RE";
	
	private int			 	p_M_Product_ID = 0;     
	private Timestamp       p_ValidTo = null;
	private Timestamp       p_ValidFrom = null;
	private String          p_Action;
	private int             p_New_M_Product_ID =0;
	private BigDecimal      p_Qty = null;
	private int 			p_M_ChangeNotice_ID=0;

	@Override
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();

			if (para.getParameter() == null)
				;
			//	BR [ 264 ] Parameter Name Changed
			else if (name.equals("M_Product_ID"))
				p_M_Product_ID = para.getParameterAsInt();
			else if (name.equals("ValidTo"))
				p_ValidTo = ((Timestamp)para.getParameter());
			else if (name.equals("ValidFrom")) 
				p_ValidFrom = ((Timestamp)para.getParameter());
			else if (name.equals("Action"))
				p_Action = ((String)para.getParameter());
			else if (name.equals("M_Product_To_ID"))
				p_New_M_Product_ID = para.getParameterAsInt();
			else if (name.equals("Qty")) 
				p_Qty = ((BigDecimal)para.getParameter());
			else if (name.equals("M_ChangeNotice_ID")) 
				p_M_ChangeNotice_ID = para.getParameterAsInt();
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}	//	prepare

	@Override
	protected String doIt() throws Exception
	{
		if (p_Action == null)
		{
			throw new FillMandatoryException("Action");
		}
		
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		
		whereClause.append(MPPProductBOMLine.COLUMNNAME_M_Product_ID+"=?");
		params.add(p_M_Product_ID);
		
		if (p_ValidTo != null)
		{
			whereClause.append(" AND TRUNC("+MPPProductBOMLine.COLUMNNAME_ValidTo+") <= ?");
			params.add(p_ValidTo);
		}
		if (p_ValidFrom != null)
		{
			whereClause.append(" AND TRUNC("+MPPProductBOMLine.COLUMNNAME_ValidFrom+") >= ?");
			params.add(p_ValidFrom);
		}

		List<MPPProductBOMLine> components = new Query(getCtx(), MPPProductBOMLine.Table_Name, whereClause.toString(), get_TrxName())
													.setParameters(params)
													.list();
		for(MPPProductBOMLine bomline : components) 
		{		
			if (p_Action.equals(ACTION_Add))
			{
				actionAdd(bomline, 0);
			}
			else if (p_Action.equals(ACTION_Deactivate))
			{
				actionDeactivate(bomline);
			}
			else if (p_Action.equals(ACTION_Expire))
			{
				actionExpire(bomline);
			}
			else if (p_Action.equals(ACTION_Replace))
			{
				actionAdd(bomline, bomline.getLine() + 1);
				actionDeactivate(bomline);
			}
			else if (p_Action.equals(ACTION_ReplaceAndExpire))
			{
				actionAdd(bomline, bomline.getLine() + 1);
				actionExpire(bomline);
			}
			else
			{
				throw new AdempiereException("Action not supported - "+p_Action);
			}
			addLog(MRefList.getListName(getCtx(), ACTION_AD_Reference_ID, p_Action));
		}                    
		return "@OK@";
	}	//	doIt

	protected void actionAdd(MPPProductBOMLine bomline, int line)
	{
		MPPProductBOMLine newbomline = new MPPProductBOMLine(getCtx(), 0, get_TrxName());
		MPPProductBOMLine.copyValues(bomline, newbomline);
		newbomline.setIsActive(true);
		newbomline.setLine(line);
		newbomline.setM_ChangeNotice_ID(p_M_ChangeNotice_ID);
		//
		newbomline.setM_Product_ID(p_New_M_Product_ID);
		if (p_Qty.signum() != 0)
		{
			newbomline.setQtyBOM(p_Qty);
		}
		newbomline.setValidFrom(newbomline.getUpdated());
		newbomline.saveEx();
	}
	
	protected void actionDeactivate(MPPProductBOMLine bomline)
	{
		bomline.setIsActive(false);
		bomline.setM_ChangeNotice_ID(p_M_ChangeNotice_ID);
		bomline.saveEx();
	}

	protected void actionExpire(MPPProductBOMLine bomline)
	{
		bomline.setIsActive(true);
		bomline.setValidTo(bomline.getUpdated());
		bomline.setM_ChangeNotice_ID(p_M_ChangeNotice_ID);
		bomline.saveEx();
	}
}	//	Component Change
