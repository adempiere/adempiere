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
 * Contributor: Victor Perez, www.e-evolution.com                             *
 *****************************************************************************/
package org.eevolution.process;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.I_M_Movement;
import org.compiere.model.I_M_MovementLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MCampaign;
import org.compiere.model.MColumn;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.model.MShipper;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_I_Movement;
import org.eevolution.model.X_I_Movement;



/**
 *	Import Inventory Movement from I_M_Movemen
 *
 * 	@author 	Alberto Juarez Caballero, alberto.juarez@e-evolution.com, www.e-evolution.com
 * 	@author 	victor.perez@e-evolution.com, www.e-evolution.com
 * 	@version 	$Id: ImportInventoryMovement.java,v 1.0
 */

public class ImportInventoryMove extends SvrProcess
{

	private boolean			m_DeleteOldImported = false;

	private boolean			m_IsImportOnlyNoErrors = true;
	
	private String			m_docAction = MMovement.DOCACTION_Prepare;
	
	private boolean 		isImported = false;
	
	private int 			imported = 0;
	
	private int 			notimported = 0;
	
	private List<String> idsPr = new ArrayList<String>();

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para: parameters)
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;

			else if (name.equals("IsImportOnlyNoErrors"))
				m_IsImportOnlyNoErrors = "Y".equals(para.getParameter());
			else if (name.equals("DeleteOldImported"))
				m_DeleteOldImported = "Y".equals(para.getParameter());
			else if (name.equals("DocAction"))
				m_docAction = (String)para.getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);			
		}
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		
//		Delete Old Imported
		StringBuffer sql = null;
		if (m_DeleteOldImported)
		{
			int no = 0;
			for (X_I_Movement move : getRecords(true,false))
			{
			    move.deleteEx(true);
			    no++;
			}
			log.fine("Delete Old Impored =" + no);
		}
		
		fillIDValues();		
		importRecords();	
		return "Imported: " + imported + ", Not imported: " + notimported;
	}	//	doIt
	
	
	/**
	 * import records using I_M_Movement table
	 */
	
	private void importRecords()
	{
		isImported = false;
		
		for(X_I_Movement imove : getRecords(false,m_IsImportOnlyNoErrors))
		{
			MMovement mov = importMInventoryMove(imove);			
			if(mov!= null)
			{    
				isImported = importMInventoryMoveLine(mov,imove);
			}	
			else
			{    
				isImported = false;
			}	
			
			if(isImported)
			{
				imove.setM_Movement_ID(mov.getM_Movement_ID());
				imove.setI_IsImported(true);
				imove.setProcessed(true);
				imove.saveEx();
				imported++;
				
				//mov.processIt(m_docAction);
				addForProcess(mov.getM_Movement_ID());
				mov.saveEx();
			}
			else
			{
				imove.setI_IsImported(false);
				imove.setProcessed(true);
				imove.saveEx();
				notimported++;
			}
		}
		processAll();
	}
	
	private void addForProcess(int id)
	{
		String ids = String.valueOf(id);
		boolean enc = false;
		for(String idx : idsPr)
		{
			if(idx.equals(ids))
				enc=true;
		}
		if(!enc)
			idsPr.add(ids);
	}
	
	private void processAll()
	{
		for(String idx : idsPr)
		{
			int id = Integer.parseInt(idx);
			MMovement move = new MMovement(Env.getCtx(), id, get_TrxName());
			move.processIt(m_docAction);
			move.saveEx();
		}
	}
	
	/**
	 * Import Inventory Move Line using X_I_M_Movement table
	 * @param move MMovement
	 * @param imove X_I_M_Movement
	 * @return isImported
	 */
	private boolean importMInventoryMoveLine(MMovement move, X_I_Movement imove)
	{
		isImported = false;
		
		MMovementLine moveLine = getMInventoryMoveLine(move, imove);
		
		if(moveLine == null)
		{
			moveLine = new MMovementLine(Env.getCtx(), 0 , get_TrxName());
		}
		
		try
		{
			moveLine.setM_Movement_ID(move.getM_Movement_ID());
			moveLine.setAD_Org_ID(imove.getAD_Org_ID());
			moveLine.setM_Product_ID(imove.getM_Product_ID());
			moveLine.setM_Locator_ID(imove.getM_Locator_ID());
			moveLine.setM_LocatorTo_ID(imove.getM_LocatorTo_ID());
			moveLine.setMovementQty(imove.getMovementQty());
			moveLine.saveEx();
			imove.setM_MovementLine_ID(moveLine.getM_MovementLine_ID());
			imove.saveEx();			
			isImported = true;
		}
		catch(Exception e)
		{
			imove.setI_ErrorMsg(e.getMessage());
			isImported = false;
		}
		
		return isImported;
	}
	
	/**
	 * get MMovementLine unique instance based on  X_I_M_Movement data
	 * @param move MMovement
	 * @param imove X_I_M_Movement
	 * @return  unique instance of MMovementLine
	 */
	private MMovementLine getMInventoryMoveLine(MMovement move, X_I_Movement imove)
	{
		
		final StringBuffer whereClause = new StringBuffer();
		ArrayList<Object> parameters = new ArrayList();
		
		MColumn[] cols = getMInventoryMoveColumns();
		
		int count = 0;
		
		for(MColumn col: cols)
		{			
			if(X_I_Movement.COLUMNNAME_AD_Org_ID.equals(col.getColumnName())
			|| X_I_Movement.COLUMNNAME_M_Product_ID.equals(col.getColumnName())
			|| X_I_Movement.COLUMNNAME_M_Locator_ID .equals(col.getColumnName())
			|| X_I_Movement.COLUMNNAME_M_LocatorTo_ID.equals(col.getColumnName()))
			{
				whereClause.append(col.getColumnName()).append("=?");
				parameters.add(imove.get_Value(col.getColumnName()));
				if(count < 3)
				{
					whereClause.append(" AND ");
					count++;
				}
			}		
		}
		
		whereClause.append(" AND M_Movement_ID=?");
		parameters.add(move.getM_Movement_ID());
		
		return new Query(getCtx(), I_M_MovementLine.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setParameters(parameters)
		.first();		
	}
	
	/**
	 * get Inventory Move Columns
	 * @return array MColumn
	 */
	private MColumn[] getMInventoryMoveColumns()
	{
			return MTable.get(getCtx(),I_M_MovementLine.Table_Name).getColumns(false);
	}
	
	/**
	 * Import Inventory Move using X_I_M_Movement table
	 * @param imove X_I_M_Movement
	 * @return MMovement
	 */
	
	private MMovement importMInventoryMove(X_I_Movement imove)
	{
	    	final String  whereClause = I_M_Movement.COLUMNNAME_MovementDate + "= ? AND "
	    				  + I_M_Movement.COLUMNNAME_DocumentNo + "=? AND "	  
	    				  + I_M_Movement.COLUMNNAME_C_DocType_ID+"=?";
		int oldID = new Query(Env.getCtx(), I_M_Movement.Table_Name,whereClause, get_TrxName())
		.setClient_ID()
		.setParameters(imove.getMovementDate(), imove.getDocumentNo(), imove.getC_DocType_ID())
		.firstId();
		
		MMovement move = null;
		if(oldID<=0)
		{
			oldID = 0;
		}
		
		move = new MMovement(Env.getCtx(), oldID, get_TrxName());
		
		try{
			move.setDocumentNo(imove.getDocumentNo());
			move.setC_DocType_ID(imove.getC_DocType_ID());
			move.setAD_Org_ID(imove.getAD_Org_ID());
			move.setMovementDate(imove.getMovementDate());
			move.setC_DocType_ID(imove.getC_DocType_ID());
			move.setDocumentNo(imove.getDocumentNo());
			move.setC_BPartner_ID(imove.getC_BPartner_ID());
			move.setM_Shipper_ID(imove.getM_Shipper_ID());
			move.setC_Project_ID(imove.getC_Project_ID());
			move.setC_Campaign_ID(imove.getC_Campaign_ID());
			move.setAD_OrgTrx_ID(imove.getAD_OrgTrx_ID());			
			move.saveEx();
		}
		catch(Exception e)
		{	
			imove.setI_ErrorMsg(e.getMessage());
			isImported = false;
		}
		
		return move;
	}

	
	/**
	 * fill IDs values based on Search Key 
	 */
	private void fillIDValues()
	{
		for(X_I_Movement imove : getRecords(false, m_IsImportOnlyNoErrors))
		{
			//if(imov.getAD_Org_ID()==0)
				imove.setAD_Org_ID(getID(MOrg.Table_Name,"Value = ?", new Object[]{imove.getOrgValue()}));
			if(imove.getM_Product_ID()==0)
				imove.setM_Product_ID(getID(MProduct.Table_Name,"Value = ?", new Object[]{imove.getProductValue()}));
			//if(imov.getM_Locator_ID()==0)
				imove.setM_Locator_ID(getID(MLocator.Table_Name,"Value = ?", new Object[]{imove.getLocatorValue()}));
			//if(imov.getM_LocatorTo_ID()==0)
				imove.setM_LocatorTo_ID(getID(MLocator.Table_Name,"Value = ?", new Object[]{imove.getLocatorToValue()}));
			if(imove.getC_DocType_ID()==0)
				imove.setC_DocType_ID(getID(MDocType.Table_Name,"Name=?", new Object[]{imove.getDocTypeName()}));
			if(imove.getC_BPartner_ID()==0)
				imove.setC_BPartner_ID(getID(MBPartner.Table_Name,"Value =?", new Object[]{imove.getBPartnerValue()}));
			if(imove.getM_Shipper_ID()==0)
				imove.setM_Shipper_ID(getID(MShipper.Table_Name, "Name = ?", new Object[]{imove.getShipperName()}));
			if(imove.getC_Project_ID()==0)
				imove.setC_Project_ID(getID(MProject.Table_Name, "Value = ?", new Object[]{imove.getProjectValue()}));
			if(imove.getC_Campaign_ID()==0)
				imove.setC_Campaign_ID(getID(MCampaign.Table_Name, "Value = ?", new Object[]{imove.getCampaignValue()}));
			if(imove.getAD_OrgTrx_ID()==0)
				imove.setAD_OrgTrx_ID(getID(MOrg.Table_Name, "Value = ?", new Object[]{imove.getOrgTrxValue()}));
				
			
			imove.saveEx();
			
			StringBuffer err = new StringBuffer("");
			if(imove.getAD_Org_ID() <=0)
				err.append(" @AD_Org_ID@ @NotFound@,");
			
			if(imove.getM_Product_ID()<=0)
				err.append(" @M_Product_ID@ @NotFound@,");
			
			if(imove.getM_Locator_ID()<=0)
				err.append(" @M_Locator_ID@ @NotFound@,");
			
			if(imove.getM_LocatorTo_ID()<=0)
				err.append(" @M_LocatorTo_ID@ @NotFound@,");
			
			if(imove.getC_DocType_ID()<=0)
				err.append(" @C_DocType_ID@ @NotFound@,");
			
			if(err.toString()!=null && err.toString().length()>0)
			{
				notimported++;
				imove.setI_ErrorMsg(Msg.parseTranslation(getCtx(), err.toString()));
				imove.saveEx();
			}		
		}
	}
	
	/**
	 * get a record's ID 
	 * @param tableName String
	 * @param whereClause String
	 * @param values Object[]
	 * @return unique record's ID in the table   
	 */
	private int getID(String tableName, String whereClause, Object[] values)
	{
		return new Query(getCtx(),tableName,whereClause,get_TrxName())
		.setParameters(values).firstId();
	}  
	
	
	/**
	 * get all records in X_I_ProductPlanning table
	 * @param imported boolean
	 * @param isWithError boolean
	 * @return collection of X_I_ProductPlanning records
	 */
	private Collection<X_I_Movement> getRecords(boolean imported, boolean isWithError)
	{
		final StringBuffer whereClause = new StringBuffer(X_I_Movement.COLUMNNAME_I_IsImported)
		.append("=?"); 
		
		if(isWithError)
		{
		    whereClause.append(" AND ").append(X_I_Movement.COLUMNNAME_I_ErrorMsg).append(" IS NULL");
		}		

		return new Query(getCtx(),X_I_Movement.Table_Name,whereClause.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(imported)
		.list();
	}	
}	//	Import Inventory Move
