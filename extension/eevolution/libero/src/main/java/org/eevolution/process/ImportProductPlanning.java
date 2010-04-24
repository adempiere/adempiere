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
 * Contributor: victor.perez@e-evolution.com                                  *
 *****************************************************************************/
package org.eevolution.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import org.compiere.model.I_AD_Org;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_M_Forecast;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Warehouse;
import org.compiere.model.I_S_Resource;
import org.compiere.model.MColumn;
import org.compiere.model.MForecast;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_M_ForecastLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.eevolution.model.I_DD_NetworkDistribution;
import org.eevolution.model.I_PP_Product_BOM;
import org.eevolution.model.MPPProductPlanning;
import org.eevolution.model.X_I_ProductPlanning;

/**
 *	Import Product Planning
 *
 * 	@author victor.perez@e-evolution.com, www.e-evolution.com
 * 	@author alberto.juarez@e-evolution.com, www.e-evolution.com
 *  <li>Create new importer for Planning Data and Forecast
 *  <li>http://sourceforge.net/support/tracker.php?aid=2952245
 * 	@version 	
 */
public class ImportProductPlanning extends SvrProcess
{
	/** Is Imported                     */
	private boolean 		isImported = false;
	/**	Delete old Imported				*/
	private boolean			p_DeleteOldImported = false;
	/** Import if no Errors				*/
	private boolean			p_IsImportOnlyNoErrors = true;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] paramaters = getParameter();
		for (ProcessInfoParameter  para: paramaters)
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("IsImportOnlyNoErrors"))
				p_IsImportOnlyNoErrors = "Y".equals(para.getParameter());
			else if (name.equals("DeleteOldImported"))
				p_DeleteOldImported = "Y".equals(para.getParameter());
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
		// delete imported records
		deleteImportedRecords();
		// fill IDs using Search Key
		fillIDValues();
		// import record 
		importRecords();		
		return "";
		// 
	}	//	doIt
	
	/**
	 * import record using X_I_ProductPlanning table 
	 */
	private void importRecords()
	{
		for(X_I_ProductPlanning ipp:getRecords(false, p_IsImportOnlyNoErrors))
		{
			if(ipp.getM_Product_ID()>0 && ipp.getS_Resource_ID()>0 && ipp.getM_Warehouse_ID()>0)
			{
				importProductPlanning(ipp);
			}
			else if(ipp.getForecastValue()==null || ipp.getM_Forecast_ID()==0)
			{
				String error="";
				if(ipp.getM_Product_ID()==0)
				{
					error = error + " @M_Product_ID@ @NotFound@ ,";
				}
				if(ipp.getS_Resource_ID()==0)
				{
					error = error + " @S_Resource_ID@ @NotFound@ ,";
				}
				if(ipp.getM_Warehouse_ID()==0)
				{
					error = error + " @M_Waehouse_ID@ @NotFound@";
				}
				ipp.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
				isImported=false;
				ipp.saveEx();
				return;
			}
			
			if(ipp.getForecastValue()==null)
			{
				isImported = true;
				
			}
			else if(ipp.getM_Forecast_ID()>0 && ipp.getM_Warehouse_ID()>0 && ipp.getM_Product_ID()>0 && ipp.getQty().signum()>0)
			{
				importForecast(ipp);
			}
			else
			{
				String error = "";
				if(ipp.getM_Forecast_ID()==0)
				{
					error = error + " @M_Forecast_ID@ @NotFound@ ,";	
				}
				if(ipp.getM_Warehouse_ID()==0)
				{
					error = error + " @M_Warehouse_ID@ @NotFound@ ,";
				}
				if(ipp.getQty().signum()<=0)
				{
					error = error + " @Qty@ @Error@";
				}
				ipp.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
				isImported=false;
				ipp.saveEx();
				return;
			}
				
			
			if(isImported)
			{
				ipp.setI_IsImported(true);
				ipp.setProcessed(true);
				ipp.saveEx();
			}
		}
	}
	
	/**
	 * import record using X_I_ProductPlanning table 
	 * @param ipp X_I_ProductPlanning
	 */
	private void importProductPlanning(X_I_ProductPlanning ipp)
	{
		MPPProductPlanning pp = null;
		if(ipp.getPP_Product_Planning_ID()>0)
		{
			pp = new 	MPPProductPlanning(getCtx(),ipp.getPP_Product_Planning_ID(), get_TrxName());
		}
		else
		{
			pp = getProductPlanning(ipp);
		}
			
			
		if(pp==null)
		{
			pp = new MPPProductPlanning(getCtx(), 0, get_TrxName());
			
		}
		fillValue(pp,ipp);
		if(ipp.getC_BPartner_ID()>0 && ipp.getVendorProductNo()!=null)
		{
			importPurchaseProductPlanning(ipp);
		}
	}
	
	/**
	 *  Import record using X_I_ProductPlanning table
	 * @param ipp X_I_ProductPlanning
	 */
	private void importPurchaseProductPlanning(X_I_ProductPlanning ipp) 
	{
		MProduct product = MProduct.get(getCtx(), ipp.getM_Product_ID());
		if(product.isPurchased())
		{
			final StringBuffer whereClause = new StringBuffer();
			whereClause.append(MProductPO.COLUMNNAME_M_Product_ID).append("=? AND ");
			whereClause.append(MProductPO.COLUMNNAME_C_BPartner_ID).append("=?");
			MProductPO productPO = new Query(getCtx(),MProductPO.Table_Name,whereClause.toString(), get_TrxName())
			.setClient_ID()
			.setParameters(new Object[]{ipp.getM_Product_ID(),ipp.getC_BPartner_ID()})
			.first();
			
			if(productPO==null)
			{
				productPO = new MProductPO(getCtx(),0,get_TrxName());
			}
			
			productPO.setAD_Org_ID(ipp.getAD_Org_ID());
			productPO.setM_Product_ID(ipp.getM_Product_ID());
			productPO.setC_BPartner_ID(ipp.getC_BPartner_ID());
			productPO.setOrder_Min(ipp.getOrder_Min());
			productPO.setOrder_Pack(ipp.getOrder_Pack());
			productPO.setDeliveryTime_Promised(ipp.getDeliveryTime_Promised().intValue());
			productPO.setVendorProductNo(ipp.getVendorProductNo());
			productPO.saveEx();
		}
		
	}

	/**
	 * Import Forecast Record using X_I_ProductPlanning table
	 * @param ipp X_I_ProductPlanning
	 */
	private void importForecast(X_I_ProductPlanning ipp)
	{
		
		if(ipp.getForecastValue()==null && ipp.getM_Forecast_ID()==0)
		{
			ipp.setI_ErrorMsg(Msg.getMsg(getCtx(),"@M_Forecast_ID@ @NotFound@"));
			ipp.saveEx();
			isImported=false;
			return;
		}
				
		MForecast forecast = new MForecast(getCtx(), ipp.getM_Forecast_ID(), get_TrxName());
		
		final StringBuffer whereClause=new StringBuffer();
		whereClause.append(X_M_ForecastLine.COLUMNNAME_M_Forecast_ID).append("=? AND ")
				   .append(X_M_ForecastLine.COLUMNNAME_M_Product_ID).append("=? AND ")
				    .append(X_M_ForecastLine.COLUMNNAME_M_Warehouse_ID).append("=? AND ")
				   .append(X_M_ForecastLine.COLUMNNAME_DatePromised).append("=? AND ")
				   .append(X_M_ForecastLine.COLUMNNAME_SalesRep_ID).append("=?");
		
		X_M_ForecastLine forecastLine = null;
		if(ipp.getM_ForecastLine_ID()>0)
		{
			forecastLine = new X_M_ForecastLine(getCtx(),ipp.getM_ForecastLine_ID(),get_TrxName());
		}
		else
		{
			forecastLine = new Query(getCtx(), X_M_ForecastLine.Table_Name,whereClause.toString(), get_TrxName())
							.setClient_ID().setParameters(new Object[]{ipp.getM_Forecast_ID(),ipp.getM_Product_ID(),ipp.getM_Warehouse_ID()
							,ipp.getDatePromised(), ipp.getSalesRep_ID()}).first();
		}
		
		if(forecastLine==null)
		{
			forecastLine = new X_M_ForecastLine (getCtx(),0,get_TrxName());
		}
		
		forecastLine.setM_Forecast_ID(ipp.getM_Forecast_ID());
		forecastLine.setAD_Org_ID(ipp.getAD_Org_ID());
		forecastLine.setM_Product_ID(ipp.getM_Product_ID());
		forecastLine.setM_Warehouse_ID(ipp.getM_Warehouse_ID());
		forecastLine.setC_Period_ID(MPeriod.getC_Period_ID(getCtx(), ipp.getDatePromised(), ipp.getAD_Org_ID()));
		forecastLine.setDatePromised(ipp.getDatePromised());
		forecastLine.setSalesRep_ID(ipp.getSalesRep_ID());
		forecastLine.setQty(ipp.getQty());
		forecastLine.saveEx();
		ipp.setM_ForecastLine_ID(forecastLine.getM_ForecastLine_ID());
		ipp.saveEx();
		isImported=true;
	}

	/**
	 * fill MPPProductPlanning using I_ProductPlanning's values
	 * @param pp MPPProductPlanning
	 * @param ipp I_ProductPlanning
	 */
	private void fillValue(MPPProductPlanning pp, X_I_ProductPlanning ipp) 
	{
		
		for(MColumn col: getProductPlanningColumns())
		{
			//if(!col.isUpdateable())
				//continue;
			
			
			if(MPPProductPlanning.COLUMNNAME_IsRequiredDRP.equals(col.getColumnName()) 
			|| MPPProductPlanning.COLUMNNAME_IsRequiredMRP.equals(col.getColumnName())
			|| MPPProductPlanning.COLUMNNAME_PP_Product_Planning_ID.equals(col.getColumnName())
			|| MPPProductPlanning.COLUMNNAME_Updated.equals(col.getColumnName())
			|| col.getAD_Reference_ID() ==DisplayType.ID)	
					continue;
			
			if(ipp.get_Value(col.getColumnName()) !=null 
			&& pp.get_Value(col.getColumnName()).equals(ipp.get_Value(col.getColumnName())))
			{
				continue;
			}
			
			pp.set_ValueOfColumn(col.getColumnName(), ipp.get_Value(col.getColumnName()));

		}
		pp.setIsRequiredDRP(false);
		pp.setIsRequiredMRP(false);
		String error=null;
		try
		{
		pp.saveEx();
		ipp.setPP_Product_Planning_ID(pp.getPP_Product_Planning_ID());	
		ipp.saveEx();
		}
		catch(Exception e)
		{
			error = e.getMessage();
			ipp.setI_ErrorMsg(error);
			isImported = false;
			return;
		}
		
		isImported=true;
	}

	/**
	 * get Product Planning Columns
	 * @return array MColumn
	 */
	private MColumn[] getProductPlanningColumns() {
		return MTable.get(getCtx(),MPPProductPlanning.Table_Name).getColumns(false);
	}

	/**
	 * get MPPProductPlanning unique instance based on  X_I_ProductPlanning data
	 * @param ipp X_I_ProductPlanning
	 * @return  unique instance of MPPProductPlanning
	 */
	private MPPProductPlanning getProductPlanning(X_I_ProductPlanning ipp) {
		
		final StringBuffer whereClause = new StringBuffer();
		ArrayList<Object> parameters = new ArrayList();
		
		MColumn[] cols = getProductPlanningColumns();
		
		int count = 0;
		
		for(MColumn col: cols)
		{
			//column primary key for MPPProductPlanning 
			if(MPPProductPlanning.COLUMNNAME_AD_Org_ID.equals(col.getColumnName())
			|| MPPProductPlanning.COLUMNNAME_S_Resource_ID.equals(col.getColumnName())
			|| MPPProductPlanning.COLUMNNAME_M_Warehouse_ID.equals(col.getColumnName())
			|| MPPProductPlanning.COLUMNNAME_M_Product_ID.equals(col.getColumnName()))
			{
				whereClause.append(col.getColumnName()).append("=?");
				parameters.add(ipp.get_Value(col.getColumnName()));
				if(count < 3)
				{
					whereClause.append(" AND ");
					count++;
				}
			}
		
		}
		
		return new Query(getCtx(), MPPProductPlanning.Table_Name, whereClause.toString(), get_TrxName())
		.setClient_ID()
		.setParameters(parameters).firstOnly();

	}

	/**
	 * fill IDs values based on Search Key 
	 */
	private void fillIDValues()
	{
		for(X_I_ProductPlanning ppi : getRecords(false, p_IsImportOnlyNoErrors))
		{
			if(ppi.getC_BPartner_ID()==0)
				ppi.setC_BPartner_ID(getID(I_C_BPartner.Table_Name,I_C_BPartner.COLUMNNAME_Value +  "=?", new Object[]{ppi.getBPartner_Value()}));
			if(ppi.getM_Product_ID()==0)	
				ppi.setM_Product_ID(getID(I_M_Product.Table_Name,I_M_Product.COLUMNNAME_Value +  "=?", new Object[]{ppi.getProductValue()}));
			if(ppi.getM_Warehouse_ID()==0)
				ppi.setM_Warehouse_ID(getID(I_M_Warehouse.Table_Name,I_M_Warehouse.COLUMNNAME_Value + "=?", new Object[]{ppi.getWarehouseValue()}));
			if(ppi.getAD_Org_ID()==0)
				ppi.setAD_Org_ID(getID(I_AD_Org.Table_Name,I_AD_Org.COLUMNNAME_Value +  "=?", new Object[]{ppi.getOrgValue()}));
			if(ppi.getDD_NetworkDistribution_ID()==0)
				ppi.setDD_NetworkDistribution_ID(getID(I_DD_NetworkDistribution.Table_Name,I_DD_NetworkDistribution.COLUMNNAME_Value 
						+  "=?", new Object[]{ppi.getNetworkDistributionValue()}));
			if(ppi.getPP_Product_BOM_ID()==0)
				ppi.setPP_Product_BOM_ID(getID(I_PP_Product_BOM.Table_Name,I_PP_Product_BOM.COLUMNNAME_Value +  "=?", new Object[]{ppi.getProduct_BOM_Value()}));
			if(ppi.getM_Forecast_ID()==0)
				ppi.setM_Forecast_ID(getID(I_M_Forecast.Table_Name,I_M_Forecast.COLUMNNAME_Name + "=?" , new Object[]{ppi.getForecastValue()}));
			if(ppi.getS_Resource_ID()==0)
				ppi.setS_Resource_ID(getID(I_S_Resource.Table_Name, I_S_Resource.COLUMNNAME_Value +  "=? AND " 
						+ I_S_Resource.COLUMNNAME_ManufacturingResourceType + "=?", new Object[]{ppi.getResourceValue(), "PT"}));
			
				ppi.saveEx();
			
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
		.setParameters(values).firstId();
	}  
	
	/**
	 * get all records in X_I_ProductPlanning table
	 * @param imported boolean
	 * @param isWithError boolean
	 * @return collection of X_I_ProductPlanning records
	 */
	private Collection<X_I_ProductPlanning> getRecords(boolean imported, boolean isWithError)
	{
		final StringBuffer whereClause = new StringBuffer(X_I_ProductPlanning.COLUMNNAME_I_IsImported)
		.append("=?"); 
		if(isWithError)
		{
			whereClause.append(" AND ").append(X_I_ProductPlanning.COLUMNNAME_I_ErrorMsg).append(" IS NULL");
		}

		return new Query(getCtx(),X_I_ProductPlanning.Table_Name,whereClause.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(new Object[]{imported})
		.list();
	}
	
	/**
	 * delete all imported records
	 */
	private void deleteImportedRecords()
	{
		if(p_DeleteOldImported)
		{
			for(X_I_ProductPlanning ipp:getRecords(false, p_IsImportOnlyNoErrors))
			{
				ipp.deleteEx(true);
			}
		}
	}
}	//	ImportGLJournal
