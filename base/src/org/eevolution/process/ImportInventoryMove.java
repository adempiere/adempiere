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
import org.eevolution.model.X_I_Movement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;


/**
 *	Import Inventory Movement from I_Movemen
 *
 * 	@author 	Alberto Juarez Caballero, alberto.juarez@e-evolution.com, www.e-evolution.com
 * 	@author 	victor.perez@e-evolution.com, www.e-evolution.com
 * 	@version 	$Id: ImportInventoryMovement.java,v 1.0
 */

public class ImportInventoryMove extends SvrProcess
{

	private boolean 		deleteOldImported = false;
	private boolean 		isImportOnlyNoErrors = true;
	private String 			docAction = MMovement.DOCACTION_Prepare;
	private boolean 		isImported = false;
	private int 			imported = 0;
	private int 			notImported = 0;
	private LinkedHashMap<Integer, MMovement> movementProcessed = new LinkedHashMap<>();

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para: getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if ("IsImportOnlyNoErrors".equals(name))
				isImportOnlyNoErrors = para.getParameterAsBoolean();
			else if ("DeleteOldImported".equals(name))
				deleteOldImported = para.getParameterAsBoolean();
			else if ("DocAction".equals(name))
				docAction = para.getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);			
		}
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return Message
	 *  @throws Exception
	 */
	protected String doIt() throws Exception
	{
		
		//Delete Old Imported
		if (deleteOldImported)
		{
			int no = 0;
			for (X_I_Movement movement : getRecords(true,false))
			{
			    movement.deleteEx(true);
			    no++;
			}
			log.fine("Delete Old Impored =" + no);
		}
		
		fillIDValues();		
		importRecords();	
		return "@I_IsImported@: " + imported + ", @I_ErrorMsg@ : " + notImported;
	}	//	doIt
	
	
	/**
	 * import records using I_M_Movement table
	 */
	
	private void importRecords()
	{
		isImported = false;
		for(X_I_Movement movementImport : getRecords(false,isImportOnlyNoErrors))
		{
			MMovement movement = importInventoryMovement(movementImport);
			if(movement != null)
				isImported = importInventoryMovementLine(movement,movementImport);
			else
				isImported = false;
			
			if(isImported)
			{
				movementImport.setM_Movement_ID(movement.getM_Movement_ID());
				movementImport.setI_IsImported(true);
				movementImport.setProcessed(true);
				movementImport.saveEx();
				movement.saveEx();
				imported++;
				addMovementToProcess(movement);
			}
			else
			{
				movementImport.setI_IsImported(false);
				movementImport.setProcessed(true);
				movementImport.saveEx();
				notImported++;
			}
		}
		processAll();
	}

	/**
	 * Add Movement to be processing
	 * @param movement
     */
	private void addMovementToProcess(MMovement movement)
	{
		movementProcessed.put(movement.get_ID(), movement);
	}

	/**
	 * Process all movement
	 */
	private void processAll()
	{
		for(Map.Entry<Integer, MMovement> entry : movementProcessed.entrySet())
		{
			MMovement movement = entry.getValue();
			movement.processIt(docAction);
			movement.saveEx();
		}
	}
	
	/**
	 * Import Inventory Movement Line using X_I_M_Movement table
	 * @param movement MMovement
	 * @param movementImport X_I_Movement
	 * @return isImported
	 */
	private boolean importInventoryMovementLine(MMovement movement, X_I_Movement movementImport)
	{
		isImported = false;
		MMovementLine movementLine = getInventoryMovementLine(movement, movementImport);
		if(movementLine == null)
			movementLine = new MMovementLine(Env.getCtx(), 0 , get_TrxName());
		
		try
		{
			movementLine.setM_Movement_ID(movement.getM_Movement_ID());
			movementLine.setAD_Org_ID(movementImport.getAD_Org_ID());
			movementLine.setM_Product_ID(movementImport.getM_Product_ID());
			movementLine.setM_Locator_ID(movementImport.getM_Locator_ID());
			movementLine.setM_LocatorTo_ID(movementImport.getM_LocatorTo_ID());
			movementLine.setMovementQty(movementLine.getMovementQty().add(movementImport.getMovementQty()));
			movementLine.saveEx();
			movementImport.setM_MovementLine_ID(movementLine.getM_MovementLine_ID());
			movementImport.saveEx();
			isImported = true;
		}
		catch(Exception e)
		{
			movementImport.setI_ErrorMsg(e.getMessage());
			isImported = false;
		}
		return isImported;
	}
	
	/**
	 * get MMovementLine unique instance based on  X_I_Movement data
	 * @param movement MMovement
	 * @param movementImport X_I_Movement
	 * @return  unique instance of MMovementLine
	 */
	private MMovementLine getInventoryMovementLine(MMovement movement, X_I_Movement movementImport)
	{
		final StringBuilder whereClause = new StringBuilder();
		ArrayList<Object> parameters = new ArrayList();
		MColumn[] columns = getInventoryMovementColumns();
		int count = 0;

		for(MColumn column: columns)
		{			
			if(X_I_Movement.COLUMNNAME_AD_Org_ID.equals(column.getColumnName())
			|| X_I_Movement.COLUMNNAME_M_Product_ID.equals(column.getColumnName())
			|| X_I_Movement.COLUMNNAME_M_Locator_ID .equals(column.getColumnName())
			|| X_I_Movement.COLUMNNAME_M_LocatorTo_ID.equals(column.getColumnName()))
			{
				whereClause.append(column.getColumnName()).append("=?");
				parameters.add(movementImport.get_Value(column.getColumnName()));
				if(count < 3)
				{
					whereClause.append(" AND ");
					count++;
				}
			}		
		}
		
		whereClause.append(" AND M_Movement_ID=?");
		parameters.add(movement.getM_Movement_ID());
		return new Query(getCtx(), I_M_MovementLine.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setParameters(parameters)
		.first();		
	}
	
	/**
	 * get Inventory Move Columns
	 * @return array MColumn
	 */
	private MColumn[] getInventoryMovementColumns()
	{
			return MTable.get(getCtx(),I_M_MovementLine.Table_Name).getColumns(false);
	}
	
	/**
	 * Import Inventory Move using X_I_M_Movement table
	 * @param movementImport X_I_Movement
	 * @return MMovement
	 */
	private MMovement importInventoryMovement(X_I_Movement movementImport)
	{
		StringBuilder whereClause = new StringBuilder();
			whereClause.append("TRUNC(").append(I_M_Movement.COLUMNNAME_MovementDate)
						.append(",'DD') = " + DB.TO_DATE(movementImport.getMovementDate()) + " AND ")
	    				.append(I_M_Movement.COLUMNNAME_DocumentNo).append("=? AND ")
	    				.append(I_M_Movement.COLUMNNAME_C_DocType_ID).append("=?");
		int movementId = new Query(Env.getCtx(), I_M_Movement.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setParameters(movementImport.getDocumentNo(), movementImport.getC_DocType_ID())
		.firstId();
		
		MMovement movement = null;
		if(movementId <= 0)
			movementId = 0;

		movement = new MMovement(getCtx(), movementId, get_TrxName());
		try{
			movement.setDocumentNo(movementImport.getDocumentNo());
			movement.setC_DocType_ID(movementImport.getC_DocType_ID());
			movement.setAD_Org_ID(movementImport.getAD_Org_ID());
			movement.setMovementDate(movementImport.getMovementDate());
			movement.setC_DocType_ID(movementImport.getC_DocType_ID());
			movement.setDocumentNo(movementImport.getDocumentNo());
			movement.setC_BPartner_ID(movementImport.getC_BPartner_ID());
			movement.setM_Shipper_ID(movementImport.getM_Shipper_ID());
			movement.setC_Project_ID(movementImport.getC_Project_ID());
			movement.setC_Campaign_ID(movementImport.getC_Campaign_ID());
			movement.setAD_OrgTrx_ID(movementImport.getAD_OrgTrx_ID());
			movement.saveEx();
		}
		catch(Exception e)
		{	
			movementImport.setI_ErrorMsg(e.getMessage());
			isImported = false;
		}
		
		return movement;
	}

	
	/**
	 * fill IDs values based on Search Key 
	 */
	private void fillIDValues()
	{
		for(X_I_Movement movementImport : getRecords(false, isImportOnlyNoErrors))
		{
			//if(movementImport.getAD_Org_ID()==0)
				movementImport.setAD_Org_ID(getID(MOrg.Table_Name,"Value = ?", new Object[]{movementImport.getOrgValue()}));
			if(movementImport.getM_Product_ID()==0)
				movementImport.setM_Product_ID(getID(MProduct.Table_Name,"Value = ?", new Object[]{movementImport.getProductValue()}));
			//if(imov.getM_Locator_ID()==0)
				movementImport.setM_Locator_ID(getID(MLocator.Table_Name,"Value = ?", new Object[]{movementImport.getLocatorValue()}));
			//if(imov.getM_LocatorTo_ID()==0)
				movementImport.setM_LocatorTo_ID(getID(MLocator.Table_Name,"Value = ?", new Object[]{movementImport.getLocatorToValue()}));
			if(movementImport.getC_DocType_ID()==0)
				movementImport.setC_DocType_ID(getID(MDocType.Table_Name,"Name=?", new Object[]{movementImport.getDocTypeName()}));
			if(movementImport.getC_BPartner_ID()==0)
				movementImport.setC_BPartner_ID(getID(MBPartner.Table_Name,"Value =?", new Object[]{movementImport.getBPartnerValue()}));
			if(movementImport.getM_Shipper_ID()==0)
				movementImport.setM_Shipper_ID(getID(MShipper.Table_Name, "Name = ?", new Object[]{movementImport.getShipperName()}));
			if(movementImport.getC_Project_ID()==0)
				movementImport.setC_Project_ID(getID(MProject.Table_Name, "Value = ?", new Object[]{movementImport.getProjectValue()}));
			if(movementImport.getC_Campaign_ID()==0)
				movementImport.setC_Campaign_ID(getID(MCampaign.Table_Name, "Value = ?", new Object[]{movementImport.getCampaignValue()}));
			if(movementImport.getAD_OrgTrx_ID()==0)
				movementImport.setAD_OrgTrx_ID(getID(MOrg.Table_Name, "Value = ?", new Object[]{movementImport.getOrgTrxValue()}));

			movementImport.saveEx();
			StringBuilder err = new StringBuilder("");
			if(movementImport.getAD_Org_ID() <=0)
				err.append(" @AD_Org_ID@ @NotFound@,");
			
			if(movementImport.getM_Product_ID()<=0)
				err.append(" @M_Product_ID@ @NotFound@,");
			
			if(movementImport.getM_Locator_ID()<=0)
				err.append(" @M_Locator_ID@ @NotFound@,");
			
			if(movementImport.getM_LocatorTo_ID()<=0)
				err.append(" @M_LocatorTo_ID@ @NotFound@,");
			
			if(movementImport.getC_DocType_ID()<=0)
				err.append(" @C_DocType_ID@ @NotFound@,");
			
			if(err.toString() != null && err.toString().length()>0)
			{
				notImported++;
				movementImport.setI_ErrorMsg(Msg.parseTranslation(getCtx(), err.toString()));
				movementImport.saveEx();
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
		.setClient_ID()
		.setParameters(values)
		.firstId();
	}  
	
	
	/**
	 * get all records in X_I_Movement table
	 * @param isImported boolean
	 * @param isWithError boolean
	 * @return List  of X_I_Movement records
	 */
	private List<X_I_Movement> getRecords(boolean isImported, boolean isWithError)
	{
		final StringBuilder whereClause = new StringBuilder(X_I_Movement.COLUMNNAME_I_IsImported)
		.append("=?"); 
		
		if(isWithError)
		    whereClause.append(" AND ").append(X_I_Movement.COLUMNNAME_I_ErrorMsg).append(" IS NULL");

		return new Query(getCtx(),X_I_Movement.Table_Name,whereClause.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(isImported)
		.list();
	}	
}	//	Import Inventory Move