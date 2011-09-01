 /*****************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2010 e-Evolution. All Rights Reserved.                  *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.eevolution.process;

import java.util.Collection;
import java.util.logging.Level;

import org.compiere.model.MOrg;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.I_I_Product_BOM;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.eevolution.model.X_I_Product_BOM;

/**
 * Process for import the Bill of Material & Formulas
 * @author victor.perez@e-evolution.com. www.e-evolution.com
 * @author alberto.juarez@e-evolution.com, www.e-evolution.com
 * @version $Id: ImportProductBOM.java, v 1.3, 14 sept 2010
 */

public class ImportProductBOM extends SvrProcess 
{

	private boolean			m_DeleteOldImported = false;
	private boolean			m_IsImportOnlyNoErrors = true;	
	private boolean 		isImported = false;	
	private int 			imported = 0;
	
	@Override
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
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);			
		}
	}

	@Override
	protected String doIt() throws Exception 
	{
		if (m_DeleteOldImported)
		{
			int no = 0;
			for (X_I_Product_BOM bom : getRecords(true,false))
			{
			    bom.deleteEx(true);
			    no++;
			}
			log.fine("Delete Old Impored =" + no);
		}
		
		fillIDValues();		
		importRecords();
		return "Imported: " + imported;
	}

	
	/**
	 * fill IDs values based on Search Key 
	 **/
	private void fillIDValues() 
	{
		for(X_I_Product_BOM importBOM : getRecords(false, m_IsImportOnlyNoErrors))
		{
			if (importBOM.getAD_Org_ID() >=0)
			{
				MOrg org =new MOrg(Env.getCtx(),importBOM.getAD_Org_ID(), get_TrxName());
				importBOM.setOrgValue(org.getValue());
			}
			
			importBOM.setAD_Org_ID(getID(MOrg.Table_Name,"Value = ?", new Object[]{importBOM.getOrgValue()}));

			if(importBOM.getM_Product_ID()==0)
				importBOM.setM_Product_ID(getID(MProduct.Table_Name,"Value=?", new Object[]{importBOM.getProductValue()})); // header
			if(importBOM.getM_BOMProduct_ID()==0)
				importBOM.setM_BOMProduct_ID(getID(MProduct.Table_Name, "Value=?", new Object[]{importBOM.getProduct_BOM_Value()})); // lines
			if(importBOM.getC_UOM_ID()==0)
				importBOM.setC_UOM_ID(getID(MUOM.Table_Name,"X12DE355=?", new Object[]{importBOM.getX12DE355()})); //uom code
			
			StringBuffer err = new StringBuffer("");
			if(importBOM.getAD_Org_ID() <=0)
				err.append(" @AD_Org_ID@ @NotFound@,");
			if(importBOM.getM_Product_ID()<=0)
				err.append(" @M_Product_ID@ @NotFound@,");
			if(importBOM.getM_BOMProduct_ID()<=0)
				err.append(" @M_ProductBOM_ID@ @NotFound@");
			
			if(err.toString()!=null && err.toString().length()>0)
			{
				importBOM.setI_ErrorMsg(Msg.parseTranslation(getCtx(), err.toString()));
			}			
			importBOM.saveEx();
		}
	}
	
	/**
	 * import the I_Product_BOM records to PP_ProductBOM table
	 */	
	private void importRecords() 
	{
		for(X_I_Product_BOM importBOM : getRecords(false, m_IsImportOnlyNoErrors))
		{
			isImported=false;			
			MPPProductBOM bom= getMPPProductBOM(importBOM);
			MPPProductBOMLine bomLine = null;
			if(bom!=null)
			{
				bomLine = importBOMLine(bom, importBOM);
				isImported=true;
			}
			
			if(bomLine==null)
			{
				isImported= false;
			}

			if(isImported)
			{
				importBOM.setPP_Product_BOMLine_ID(bomLine.getPP_Product_BOMLine_ID());
				importBOM.setPP_Product_BOM_ID(bom.get_ID());
				
				imported++;
			}
			importBOM.setI_IsImported(isImported);
			importBOM.setProcessed(isImported);
			importBOM.saveEx();
		}
	}
	
	
	/**
	 * Get Product BOM, if it does not exist then create it
	 * @param importBOM I_Product_BOM
	 * @return MPPProductBOM Product BOM
	 */
	private MPPProductBOM getMPPProductBOM(X_I_Product_BOM importBOM)
	{
	    	//TODO: Validate if the import process create Alternate BOM
		String whereClause = I_I_Product_BOM.COLUMNNAME_M_Product_ID + "=? AND " +
							I_I_Product_BOM.COLUMNNAME_Name + "=? AND " + 
							I_I_Product_BOM.COLUMNNAME_Value + "=?";
		
		int id = new Query(Env.getCtx(), MPPProductBOM.Table_Name, whereClause, get_TrxName())
					.setParameters(importBOM.getM_Product_ID(), importBOM.getName(), importBOM.getValue())
					.firstId();
		
		if(id < 0)
			id=0;
		
		MPPProductBOM bom = new MPPProductBOM(Env.getCtx(), id, get_TrxName());
		
		try{
			bom.setM_Product_ID(importBOM.getM_Product_ID());
			bom.setValue(importBOM.getM_Product().getValue());
			bom.setName(importBOM.getM_Product().getName());
			bom.setValidFrom(importBOM.getValidFrom());
			bom.setValidTo(importBOM.getValidTo());
			bom.setBOMType(importBOM.getBOMType());
			bom.setBOMUse(importBOM.getBOMUse());
			bom.setDescription(importBOM.getDescription());
			//pBOM.setDocumentNo(ipBOM.getDocumentNo());
			bom.setHelp(importBOM.getHelp());
			bom.setC_UOM_ID(importBOM.getM_Product().getC_UOM_ID());
			bom.saveEx();
			return bom;
		}
		catch(Exception e)
		{
			importBOM.setI_ErrorMsg(e.getMessage());
			isImported = false;
			return null;
		}

	}
	
	
	/**
	 * Import or update an existent BOM Line
	 * @param bom Product BOM
	 * @param importBOM Import Product BOM
	 * @return MPPProductBOMLine if is imported
	 */
	private MPPProductBOMLine importBOMLine(MPPProductBOM bom, X_I_Product_BOM importBOM)
	{
		MPPProductBOMLine bomLine = getProductBOMLine(bom, importBOM);
		MProduct component = new MProduct(Env.getCtx(),importBOM.getM_BOMProduct_ID(),get_TrxName());
		
		if(bomLine==null)
			bomLine = new MPPProductBOMLine(Env.getCtx(), 0, get_TrxName());
		
		try
		{
			bomLine.setM_Product_ID(importBOM.getM_BOMProduct_ID());
			bomLine.setComponentType(importBOM.getComponentType());
			bomLine.setQtyBOM(importBOM.getQtyBOM());
			bomLine.setQtyBatch(importBOM.getQtyBatch());
			bomLine.setIssueMethod(importBOM.getIssueMethod());
			bomLine.setIsQtyPercentage(importBOM.isQtyPercentage());
			bomLine.setPP_Product_BOM_ID(bom.get_ID());
			bomLine.setValidFrom(importBOM.getValidFrom());
			bomLine.setM_ChangeNotice_ID(importBOM.getM_ChangeNotice_ID());
			bomLine.setIsCritical(importBOM.isCritical());
			bomLine.setCostAllocationPerc(importBOM.getCostAllocationPerc());
			bomLine.setScrap(importBOM.getScrap());
			bomLine.setAssay(importBOM.getAssay());
			bomLine.setBackflushGroup(importBOM.getBackflushGroup());
			bomLine.setLeadTimeOffset(importBOM.getLeadTimeOffset());
			bomLine.setPP_Product_BOM_ID(bom.get_ID());
			
			if (importBOM.getC_UOM_ID()>0)
				bomLine.setC_UOM_ID(importBOM.getC_UOM_ID());
			else
				bomLine.setC_UOM_ID(component.getC_UOM_ID());
			
			bomLine.saveEx();
			return bomLine;
			
		}
		catch(Exception e)
		{
			importBOM.setI_ErrorMsg(e.getLocalizedMessage());
			importBOM.saveEx();
			return null;
		}
	}

	/**
	 * get the BOM Line
	 * @param bom Product BOM
	 * @param importBOM Import Product BOM
	 * @return MPPProductBOMLine if exist
	 */
	private MPPProductBOMLine getProductBOMLine(MPPProductBOM bom, X_I_Product_BOM importBOM) 
	{
		
		String whereClause = I_I_Product_BOM.COLUMNNAME_M_Product_ID + "=? AND " 
				   + MPPProductBOM.COLUMNNAME_PP_Product_BOM_ID + "=?";
		
		return new Query(Env.getCtx(), MPPProductBOMLine.Table_Name, whereClause, get_TrxName())
		.setParameters(importBOM.getM_BOMProduct_ID(), bom.getPP_Product_BOM_ID())
		.first();
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
	 * get all records in X_I_Product_BOM table
	 * @param imported boolean
	 * @param isWithError boolean
	 * @return collection of X_I_Product_BOM records
	 */
	private Collection<X_I_Product_BOM> getRecords(boolean imported, boolean isWithError)
	{
		final StringBuffer whereClause = new StringBuffer(X_I_Product_BOM.COLUMNNAME_I_IsImported)
		.append("=?"); 
		
		if(isWithError)
		{
		    whereClause.append(" AND ")
		    .append(X_I_Product_BOM.COLUMNNAME_I_ErrorMsg)
		    .append(" IS NULL");
		}		

		return new Query(getCtx(),X_I_Product_BOM.Table_Name,whereClause.toString(),get_TrxName())
		.setClient_ID()
		.setParameters(imported)
		.list();
	}	

}
