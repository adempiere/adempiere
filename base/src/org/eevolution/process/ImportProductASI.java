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
 * Copyright (C) 2003-2012 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com www.e-evolution.com   		  *
 *****************************************************************************/
package org.eevolution.process;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_M_AttributeSearch;
import org.adempiere.core.domains.models.I_M_AttributeUse;
import org.adempiere.core.domains.models.X_I_Product_ASI;
import org.adempiere.core.domains.models.X_M_AttributeSearch;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeInstance;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MAttributeUse;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;

/**
 * Import Attribute Set Instance from I_Product_ASI
 * 
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */

public class ImportProductASI extends SvrProcess {

	private boolean m_DeleteOldImported = false;
	private boolean m_IsImportOnlyNoErrors = true;
	private boolean isImported = false;
	private int imported = 0;
	private int notimported = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
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
	} // prepare

	/**
	 * Perform process.
	 * 
	 * @return Message
	 * @throws Exception
	 */
	protected String doIt() throws java.lang.Exception {
		if (m_DeleteOldImported) {
			int no = 0;
			for (X_I_Product_ASI ip_asi : getRecords(true, false)) {
				ip_asi.deleteEx(true);
				no++;
			}
			log.fine("Delete Old Impored =" + no);
		}

		fillIDValues();
		importRecords();
		return "Imported: " + imported + ", Not imported: " + notimported;
	} // doIt

	/**
	 * import records using I_Product_ASI table
	 * 
	 * @throws SQLException
	 */
	private void importRecords() throws SQLException {
		for (X_I_Product_ASI ip_asi : getRecords(false, m_IsImportOnlyNoErrors)) {
			isImported = false;
			MAttributeInstance ai = importProductASI(ip_asi);
			if (ai != null || ai.get_ID() > 0)
				isImported = true;

			if (isImported) {
				ip_asi.setI_IsImported(true);
				ip_asi.setProcessed(true);
				ip_asi.setI_ErrorMsg("");
				ip_asi.saveEx();
				imported++;
				ip_asi.saveEx();
			} else {
				ip_asi.setI_IsImported(false);
				ip_asi.setProcessed(false);
				ip_asi.saveEx();
				notimported++;
			}
		}
	}

	/**
	 * Import Product ASI using X_I_Product_ASI table
	 * 
	 * @param X_I_Product_ASI
	 *            Product ASI
	 * @return Attribute Instance
	 * @throws SQLException
	 */
	private MAttributeInstance importProductASI(X_I_Product_ASI ip_asi)
			throws SQLException {

		MProduct product = (MProduct) ip_asi.getM_Product();
		MAttributeSet attributeSet = null;
		if (ip_asi.getM_AttributeSet_ID() <= 0) {
			attributeSet = getAttributeSet(ip_asi);
			product.setM_AttributeSet_ID(attributeSet.get_ID());
			product.saveEx();
			ip_asi.setM_AttributeSet_ID(attributeSet.getM_AttributeSet_ID());
			ip_asi.saveEx();
		} else {
			attributeSet = (MAttributeSet) product.getM_AttributeSet();
		}

		// Attribute
		MAttribute attribute = null;
		if (ip_asi.getM_Attribute_ID() <= 0) {
			attribute = getAttribute(ip_asi);
			ip_asi.setM_Attribute_ID(attribute.getM_Attribute_ID());
			ip_asi.saveEx();
		} else
			attribute = (MAttribute) ip_asi.getM_Attribute();

		// Check Attribute in Attribute Set
		checkAttributeUse(attributeSet, attribute);

		MAttributeSetInstance asi = (MAttributeSetInstance) product
				.getM_AttributeSetInstance();
		if (asi == null || asi.get_ID() <= 0) {
			asi = getAttributeSetInstance(product, attributeSet);
			product.setM_AttributeSetInstance_ID(asi.get_ID());
			product.saveEx();
			ip_asi.setM_AttributeSetInstance_ID(asi
					.getM_AttributeSetInstance_ID());
			ip_asi.saveEx();
		}

		MAttributeInstance ai = addAttributeLine(asi, attribute, ip_asi);
		ip_asi.saveEx();
		commitEx();
		return ai;
	}

	private void checkAttributeUse(MAttributeSet attributeSet,
			MAttribute attribute) {

		StringBuilder select = new StringBuilder("FROM M_AttributeUse WHERE ");
		select.append(I_M_AttributeUse.COLUMNNAME_M_AttributeSet_ID).append(
				"=? AND ");
		select.append(I_M_AttributeUse.COLUMNNAME_M_Attribute_ID).append("=? ");
		MAttributeUse attributeUse = null;

		int attributeId = DB.getSQLValue(get_TrxName(),
				"SELECT M_Attribute_ID " + select.toString(),
				attributeSet.get_ID(), attribute.get_ID());
		int seqNo = DB.getSQLValue(get_TrxName(),
				"SELECT count(M_Attribute_ID) " + select.toString(),
				attributeSet.get_ID(), attribute.get_ID());

		if (attributeId <= 0) {
			attributeUse = new MAttributeUse(getCtx(), 0, get_TrxName());
			attributeUse.setM_AttributeSet_ID(attributeSet.get_ID());
			attributeUse.setM_Attribute_ID(attribute.get_ID());
			attributeUse.setSeqNo(seqNo == 0 ? 10 : seqNo * 10);
			attributeUse.saveEx();
		}
		return;
	}

	private MAttributeSetInstance getAttributeSetInstance(MProduct product,
			MAttributeSet attributeSet) {
		MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(),
				product.getM_AttributeSetInstance_ID(), product.get_TrxName());
		if (asi == null || asi.get_ID() <= 0) {
			asi = new MAttributeSetInstance(product.getCtx(), 0,
					product.get_TrxName());
			asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
			asi.saveEx();
		}
		return asi;
	}

	private MAttributeInstance addAttributeLine(MAttributeSetInstance asi,
			MAttribute attribute, X_I_Product_ASI ip_asi) {

		MAttributeInstance instance = attribute.getMAttributeInstance(asi
				.get_ID());
		if (instance == null) {
			instance = new MAttributeInstance(getCtx(), 0, get_TrxName());
			instance.setM_Attribute_ID(attribute.getM_Attribute_ID());
			instance.setM_AttributeSetInstance_ID(asi
					.getM_AttributeSetInstance_ID());
			instance.saveEx();
		}

		if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(attribute
				.getAttributeValueType())) {
			MAttributeValue attributeValue = getAttributeValue(attribute,
					ip_asi);
			attribute.setMAttributeInstance(instance.get_ID(),
					attributeValue.getValue());
			ip_asi.setM_AttributeValue_ID(attributeValue.get_ID());
			ip_asi.saveEx();

		} else if (MAttribute.ATTRIBUTEVALUETYPE_Number.equals(attribute
				.getAttributeValueType())) {
			BigDecimal numericValue = new BigDecimal(ip_asi.getAttributeValue());
			attribute.setMAttributeInstance(instance.get_ID(), numericValue);
		} else if (MAttribute.ATTRIBUTEVALUETYPE_StringMax40.equals(attribute
				.getAttributeValueType())) {
			attribute.setMAttributeInstance(
					instance.get_ID(),
					ip_asi.getAttributeValue().length() > 40 ? ip_asi
							.getAttributeValue().substring(0, 40) : ip_asi
							.getAttributeValue());
		}

		return instance;
	}

	private MAttributeSet getAttributeSet(X_I_Product_ASI ip_asi) {
		MAttributeSet attributeSet = new MAttributeSet(getCtx(),
				ip_asi.getM_AttributeSet_ID(), get_TrxName());

		if (attributeSet == null || attributeSet.get_ID() <= 0) {
			attributeSet = new MAttributeSet(ip_asi.getCtx(), 0,
					ip_asi.get_TrxName());
			attributeSet.setName(ip_asi.getAttributeSetName());
			attributeSet.setIsLot(ip_asi.isLot());
			attributeSet.setIsLotMandatory(ip_asi.isLotMandatory());
			attributeSet.setIsGuaranteeDate(ip_asi.isGuaranteeDate());
			attributeSet.setIsGuaranteeDateMandatory(ip_asi
					.isGuaranteeDateMandatory());
			attributeSet.setGuaranteeDays(ip_asi.getGuaranteeDays());
			attributeSet.setMandatoryType(ip_asi.getMandatoryType());
			attributeSet.saveEx();
		}

		return attributeSet;
	}

	private MAttribute getAttribute(X_I_Product_ASI ip_asi) {
		MAttribute attribute = null;
		int attributeId = getID(MAttribute.Table_Name,
				MAttribute.COLUMNNAME_Name + "=?", ip_asi.getAttributeName());
		if (attributeId <= 0) {
			attribute = new MAttribute(ip_asi.getCtx(), 0, ip_asi.get_TrxName());
			attribute.setName(ip_asi.getAttributeName());
			attribute.setIsMandatory(ip_asi.isMandatory());
			attribute.setAttributeValueType(ip_asi.getAttributeValueType());
			attribute.setIsMandatory(ip_asi.isMandatory());
			attribute.saveEx();
		} else {
			attribute = new MAttribute(getCtx(), attributeId, get_TrxName());
		}

		if (ip_asi.getAttributeSearchName() != null) {
			X_M_AttributeSearch attributeSearch = (X_M_AttributeSearch) getAttributeSearch(ip_asi);
			attribute.setM_AttributeSearch_ID(attributeSearch.get_ID());
			attribute.saveEx();
			ip_asi.setM_AttributeSearch_ID(attributeSearch.get_ID());
			ip_asi.saveEx();
		}

		if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(ip_asi
				.getAttributeValueType())) {
			getAttributeValue(attribute, ip_asi);
		}
		return attribute;
	}

	private MAttributeValue getAttributeValue(MAttribute attribute,
			X_I_Product_ASI ip_asi) {
		MAttributeValue attributeValue = null;
		if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(ip_asi
				.getAttributeValueType())) {
			MAttributeValue[] attributeValues = attribute.getMAttributeValues();
			for (MAttributeValue aValue : attributeValues) {
				if (aValue.getName().equals(ip_asi.getElementName())) {
					return aValue;
				}
			}

			attributeValue = new MAttributeValue(getCtx(), 0, get_TrxName());
			attributeValue.setM_Attribute_ID(attribute.getM_Attribute_ID());
			attributeValue.setValue(ip_asi.getAttributeValue());
			attributeValue.setName(ip_asi.getElementName());
			attributeValue.saveEx();
		}
		return attributeValue;
	}

	private I_M_AttributeSearch getAttributeSearch(X_I_Product_ASI ip_asi) {
		X_M_AttributeSearch asearch = null;
		int attributeSearchId = getID(I_M_AttributeSearch.Table_Name,
				I_M_AttributeSearch.COLUMNNAME_Name + "=?",
				ip_asi.getAttributeSearchName());
		if (attributeSearchId <= 0) {
			asearch = new X_M_AttributeSearch(getCtx(), 0, get_TrxName());
			asearch.setName(ip_asi.getAttributeSearchName());
			asearch.saveEx();
			ip_asi.setM_AttributeSearch_ID(asearch.get_ID());
			ip_asi.saveEx();
		} else {
			asearch = new X_M_AttributeSearch(getCtx(), attributeSearchId,
					get_TrxName());
		}
		return asearch;
	}

	/**
	 * fill IDs values based on Search Key
	 * 
	 * @throws SQLException
	 */
	private void fillIDValues() throws SQLException {
		for (X_I_Product_ASI ip_asi : getRecords(false, m_IsImportOnlyNoErrors)) {

			// Product
			int M_Product_ID = 0;
			if (ip_asi.getM_Product_ID() > 0)
				M_Product_ID = getID(MProduct.Table_Name, "M_Product_ID = ?",
						ip_asi.getM_Product_ID());

			if (M_Product_ID <= 0 && ip_asi.getProductValue() != null) {
				M_Product_ID = getID(MProduct.Table_Name, "Value = ?",
						ip_asi.getProductValue());
				ip_asi.setM_Product_ID(M_Product_ID);
			} else
				ip_asi.setM_Product_ID(M_Product_ID);

			// Attribute Set
			int M_AttributeSet_ID = 0;
			if (ip_asi.getM_AttributeSet_ID() > 0)
				M_AttributeSet_ID = getID(MAttributeSet.Table_Name,
						"M_AttributeSet_ID = ?", ip_asi.getM_AttributeSet_ID());

			if (M_AttributeSet_ID <= 0 && ip_asi.getAttributeSetName() != null) {
				M_AttributeSet_ID = getID(MAttributeSet.Table_Name, "Name = ?",
						ip_asi.getAttributeSetName());
				ip_asi.setM_AttributeSet_ID(M_AttributeSet_ID);
			} else
				ip_asi.setM_AttributeSet_ID(M_AttributeSet_ID);

			// Attribute
			int M_Attribute_ID = 0;
			if (ip_asi.getM_Attribute_ID() > 0)
				M_Attribute_ID = getID(MAttribute.Table_Name,
						"M_Attribute_ID = ?", ip_asi.getM_Attribute_ID());

			if (M_Attribute_ID <= 0 && ip_asi.getAttributeName() != null) {
				M_Attribute_ID = getID(MAttribute.Table_Name, "Name = ?",
						ip_asi.getAttributeName());
				ip_asi.setM_Attribute_ID(M_Attribute_ID);
			} else
				ip_asi.setM_Attribute_ID(M_Attribute_ID);

			if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(ip_asi
					.getAttributeValueType()) && M_Attribute_ID > 0) {
				MAttribute attribute = new MAttribute(getCtx(), M_Attribute_ID,
						get_TrxName());

			}

			// Attribute Set Instance
			int M_AttributeSetInstance_ID = 0;
			if (ip_asi.getM_AttributeSetInstance_ID() > 0)
				M_AttributeSetInstance_ID = getID(
						MAttributeSetInstance.Table_Name,
						"M_AttributeSetInstance_ID = ?",
						ip_asi.getM_AttributeSetInstance_ID());

			StringBuffer err = new StringBuffer("");

			if (ip_asi.getM_Product_ID() <= 0)
				err.append(" @M_Product_ID@ @NotFound@,");

			if (ip_asi.getAttributeSetName() == null)
				err.append(" @AttributeSetName@ @NotFound@,");

			if (ip_asi.getMandatoryType() == null)
				err.append(" @MandatoryType@ @NotFound@,");

			if (ip_asi.getAttributeName() == null)
				err.append(" @AttributeName@ @NotFound@,");

			if (ip_asi.getAttributeValueType() == null)
				err.append(" @AttributeValueType@ @NotFound@,");

			if (ip_asi.getAttributeValue() == null)
				err.append(" @AttributeValue@ @NotFound@,");
			if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(ip_asi
					.getAttributeValueType())
					&& ip_asi.getElementName() == null)
				err.append(" @ElementName@ @NotFound@,");

			if (err.toString() != null && err.toString().length() > 0) {
				notimported++;
				ip_asi.setI_ErrorMsg(Msg.parseTranslation(getCtx(),
						err.toString()));
			}

			ip_asi.saveEx();
			commitEx();

		}
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
	private int getID(String tableName, String whereClause,
			Object... parameters) {
		return new Query(getCtx(), tableName, whereClause, get_TrxName())
				.setParameters(parameters).firstId();
	}

	/**
	 * get all records in X_I_Product_ASI table
	 * 
	 * @param imported
	 *            boolean
	 * @param isWithError
	 *            boolean
	 * @return collection of X_I_Product_ASI records
	 */
	private List<X_I_Product_ASI> getRecords(boolean imported,
			boolean isWithError) {
		final StringBuffer whereClause = new StringBuffer(
				X_I_Product_ASI.COLUMNNAME_I_IsImported).append("=?");

		if (isWithError) {
			whereClause.append(" AND ")
					.append(X_I_Product_ASI.COLUMNNAME_I_ErrorMsg)
					.append(" IS NULL");
		}

		return new Query(getCtx(), X_I_Product_ASI.Table_Name,
				whereClause.toString(), get_TrxName()).setClient_ID()
				.setParameters(imported).list();
	}
} // Import Inventory Move
