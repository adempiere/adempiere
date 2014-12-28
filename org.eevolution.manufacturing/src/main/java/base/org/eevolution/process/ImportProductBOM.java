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

import java.util.List;
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
 * 
 * @author victor.perez@e-evolution.com. www.e-evolution.com
 * @author alberto.juarez@e-evolution.com, www.e-evolution.com
 * @version $Id: ImportProductBOM.java, v 1.3, 14 sept 2010
 */

public class ImportProductBOM extends SvrProcess {

	private boolean m_DeleteOldImported = false;
	private boolean m_IsImportOnlyNoErrors = true;
	private boolean isImported = false;
	private int imported = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] parameters = getParameter();
		for (ProcessInfoParameter para : parameters) {
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
	protected String doIt() throws Exception {
		if (m_DeleteOldImported) {
			int no = 0;
			for (X_I_Product_BOM bom : getRecords(true, false)) {
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
	private void fillIDValues() {
		for (X_I_Product_BOM importBOM : getRecords(false,
				m_IsImportOnlyNoErrors)) {
			// Validate Organization
			int AD_Org_ID = 0;
			if (importBOM.getAD_Org_ID() > 0)
				AD_Org_ID = getID(MOrg.Table_Name, "AD_Org_ID = ?",
						new Object[] { importBOM.getAD_Org_ID() });

			if (AD_Org_ID < 0 && importBOM.getOrgValue() != null) {
				AD_Org_ID = getID(MOrg.Table_Name, "Value = ?",
						new Object[] { importBOM.getOrgValue() });
				importBOM.setAD_Org_ID(AD_Org_ID);
			} else
				importBOM.setAD_Org_ID(AD_Org_ID);

			// Validate Product
			int M_Product_ID = 0;
			if (importBOM.getM_Product_ID() > 0)
				M_Product_ID = getID(MProduct.Table_Name, "M_Product_ID=?",
						new Object[] { importBOM.getM_Product_ID() });

			if (M_Product_ID <= 0 && importBOM.getProductValue() != null) {
				M_Product_ID = getID(MProduct.Table_Name, "Value=?",
						new Object[] { importBOM.getProductValue() });
			}
			importBOM.setM_Product_ID(M_Product_ID); // header

			// Validate Component
			int M_Component_ID = 0;
			if (importBOM.getM_BOMProduct_ID() > 0)
				M_Component_ID = getID(MProduct.Table_Name, "M_Product_ID=?",
						new Object[] { importBOM.getM_BOMProduct_ID() });

			if (M_Component_ID <= 0 && importBOM.getProduct_BOM_Value() != null) {
				M_Component_ID = getID(MProduct.Table_Name, "Value=?",
						new Object[] { importBOM.getProduct_BOM_Value() });
			}
			importBOM.setM_BOMProduct_ID(M_Component_ID); // lines

			// Validate UOM
			int C_UOM_ID = 0;
			if (importBOM.getC_UOM_ID() > 0)
				C_UOM_ID = getID(MUOM.Table_Name, "C_UOM_ID=?",
						new Object[] { importBOM.getC_UOM_ID() });

			if (C_UOM_ID <= 0 && importBOM.getX12DE355() != null) {
				C_UOM_ID = getID(MUOM.Table_Name, "X12DE355=?",
						new Object[] { importBOM.getX12DE355() });				
			}
			importBOM.setC_UOM_ID(C_UOM_ID); // uom code

			StringBuffer err = new StringBuffer("");
			if (importBOM.getAD_Org_ID() < 0)
				err.append(" @AD_Org_ID@ @NotFound@,");
			if (importBOM.getM_Product_ID() <= 0)
				err.append(" @M_Product_ID@ @NotFound@,");
			if (importBOM.getM_BOMProduct_ID() <= 0)
				err.append(" @M_ProductBOM_ID@ @NotFound@");
			if (importBOM.getDocumentNo() == null)
				err.append(" @FillMandatory@ @DocumentNo@");

			if (err.toString() != null && err.toString().length() > 0) {
				importBOM.setI_ErrorMsg(Msg.parseTranslation(getCtx(),
						err.toString()));
			}
			importBOM.saveEx();
		}
	}

	/**
	 * import the I_Product_BOM records to PP_ProductBOM table
	 */
	private void importRecords() {
		for (X_I_Product_BOM importBOM : getRecords(false,
				m_IsImportOnlyNoErrors)) {
			isImported = false;
			MPPProductBOM bom = getMPPProductBOM(importBOM);
			MPPProductBOMLine bomLine = null;
			if (bom != null)
				bomLine = importBOMLine(bom, importBOM);
			if (bomLine != null) {
				importBOM.setPP_Product_BOMLine_ID(bomLine
						.getPP_Product_BOMLine_ID());
				importBOM.setPP_Product_BOM_ID(bom.get_ID());
				imported++;
				isImported = true;

			}

			importBOM.setI_IsImported(isImported);
			importBOM.setProcessed(isImported);
			importBOM.saveEx();
		}
	}

	/**
	 * Get Product BOM, if it does not exist then create it
	 * 
	 * @param importBOM
	 *            I_Product_BOM
	 * @return MPPProductBOM Product BOM
	 */
	private MPPProductBOM getMPPProductBOM(X_I_Product_BOM importBOM) {
		final String whereClause = I_I_Product_BOM.COLUMNNAME_M_Product_ID
				+ "=? AND " + I_I_Product_BOM.COLUMNNAME_Value + "=?";

		MPPProductBOM bom = new Query(Env.getCtx(), MPPProductBOM.Table_Name,
				whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(importBOM.getM_Product_ID(),
						importBOM.getValue()).firstOnly();

		if (bom == null) {
			bom = new MPPProductBOM(Env.getCtx(), 0, get_TrxName());
			bom.setAD_Org_ID(importBOM.getAD_Org_ID());
			bom.setM_Product_ID(importBOM.getM_Product_ID());
			bom.setValue(((importBOM.getValue() == null || importBOM.getValue().isEmpty())? importBOM.getM_Product().getValue():importBOM.getValue()));
		}

		bom.setName((importBOM.getName() == null || importBOM.getName().isEmpty())? importBOM.getM_Product().getName():importBOM.getName());
		bom.setValidFrom(importBOM.getValidFrom());
		bom.setValidTo(importBOM.getValidTo());
		bom.setBOMType(importBOM.getBOMType());
		bom.setBOMUse(importBOM.getBOMUse());
		bom.setDescription(importBOM.getDescription());
		bom.setDocumentNo(importBOM.getDocumentNo());
		bom.setHelp(importBOM.getHelp());
		bom.setC_UOM_ID(importBOM.getM_Product().getC_UOM_ID());
		bom.saveEx();

		return bom;
	}

	/**
	 * Import or update an existent BOM Line
	 * 
	 * @param bom
	 *            Product BOM
	 * @param importBOM
	 *            Import Product BOM
	 * @return MPPProductBOMLine if is imported
	 */
	private MPPProductBOMLine importBOMLine(MPPProductBOM bom,
			X_I_Product_BOM importBOM) {
		// Search a BOM Line if not exist create a new based on import record
		MPPProductBOMLine bomLine = getProductBOMLine(bom, importBOM);
		// Component
		MProduct component = new MProduct(Env.getCtx(),
				importBOM.getM_BOMProduct_ID(), get_TrxName());

		if (bomLine == null) {
			bomLine = new MPPProductBOMLine(Env.getCtx(), 0, get_TrxName());
			bomLine.setAD_Org_ID(importBOM.getAD_Org_ID());
			bomLine.setPP_Product_BOM_ID(bom.get_ID());
			bomLine.setM_Product_ID(importBOM.getM_BOMProduct_ID());
		}
		bomLine.setComponentType(importBOM.getComponentType());
		bomLine.setQtyBOM(importBOM.getQtyBOM());
		bomLine.setQtyBatch(importBOM.getQtyBatch());
		bomLine.setIssueMethod(importBOM.getIssueMethod());
		bomLine.setIsQtyPercentage(importBOM.isQtyPercentage());
		bomLine.setValidFrom(importBOM.getValidFrom());
		bomLine.setM_ChangeNotice_ID(importBOM.getM_ChangeNotice_ID());
		bomLine.setIsCritical(importBOM.isCritical());
		bomLine.setCostAllocationPerc(importBOM.getCostAllocationPerc());
		bomLine.setScrap(importBOM.getScrap());
		bomLine.setAssay(importBOM.getAssay());
		bomLine.setBackflushGroup(importBOM.getBackflushGroup());
		bomLine.setLeadTimeOffset(importBOM.getLeadTimeOffset());
		if (importBOM.getC_UOM_ID() > 0)
			bomLine.setC_UOM_ID(importBOM.getC_UOM_ID());
		else
			bomLine.setC_UOM_ID(component.getC_UOM_ID());
		bomLine.saveEx();
		return bomLine;
	}

	/**
	 * get the BOM Line
	 * 
	 * @param bom
	 *            Product BOM
	 * @param importBOM
	 *            Import Product BOM
	 * @return MPPProductBOMLine if exist
	 */
	private MPPProductBOMLine getProductBOMLine(MPPProductBOM bom,
			X_I_Product_BOM importBOM) {

		final String whereClause = MPPProductBOMLine.COLUMNNAME_PP_Product_BOM_ID
				+ "=? AND "
				+ MPPProductBOMLine.COLUMNNAME_M_Product_ID
				+ "=? AND "
				+ MPPProductBOMLine.COLUMNNAME_BackflushGroup
				+ "=?";

		return new Query(Env.getCtx(), MPPProductBOMLine.Table_Name,
				whereClause, get_TrxName())
				.setClient_ID()
				.setParameters(bom.getPP_Product_BOM_ID(),
						importBOM.getM_BOMProduct_ID(),
						importBOM.getBackflushGroup()).first();
	}

	/**
	 * get a record's ID
	 * 
	 * @param tableName
	 *            String
	 * @param whereClause
	 *            String
	 * @param values
	 *            Object[]
	 * @return unique record's ID in the table
	 */
	private int getID(String tableName, String whereClause, Object[] values) {
		return new Query(getCtx(), tableName, whereClause, get_TrxName())
				.setClient_ID().setParameters(values).firstIdOnly();
	}

	/**
	 * get all records in X_I_Product_BOM table
	 * 
	 * @param imported
	 *            boolean
	 * @param isWithError
	 *            boolean
	 * @return collection of X_I_Product_BOM records
	 */
	private List<X_I_Product_BOM> getRecords(boolean imported,
			boolean isWithError) {
		final StringBuffer whereClause = new StringBuffer(
				X_I_Product_BOM.COLUMNNAME_I_IsImported).append("=?");

		if (isWithError) {
			whereClause.append(" AND ")
					.append(X_I_Product_BOM.COLUMNNAME_I_ErrorMsg)
					.append(" IS NULL");
		}

		return new Query(getCtx(), X_I_Product_BOM.Table_Name,
				whereClause.toString(), get_TrxName()).setClient_ID()
				.setParameters(imported).list();
	}
}
