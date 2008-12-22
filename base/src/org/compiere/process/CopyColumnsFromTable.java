/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2007 ADempiere, Inc. All Rights Reserved.                    *
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
 * Adempiere, Inc.                                                            *
 *****************************************************************************/
package org.compiere.process;

import java.util.logging.Level;

import org.compiere.model.MColumn;
import org.compiere.model.MTable;
import org.compiere.model.M_Element;
import org.compiere.util.AdempiereSystemError;

/**
 *	Copy columns from one table to other
 *	
 *  @author Carlos Ruiz - globalqss
 *  @version $Id: CopyColumnsFromTable
 */
public class CopyColumnsFromTable extends SvrProcess
{
	/** Target Table		*/
	private int		p_target_AD_Table_ID = 0;
	/** Source Table		*/
	private int		p_source_AD_Table_ID = 0;
	
	/** Column Count	*/
	private int 	m_count = 0;

	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Table_ID"))
				p_source_AD_Table_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_target_AD_Table_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		if (p_target_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ " + p_target_AD_Table_ID);
		if (p_source_AD_Table_ID == 0)
			throw new AdempiereSystemError("@NotFound@ @AD_Table_ID@ " + p_source_AD_Table_ID);
		log.info("Source AD_Table_ID=" + p_source_AD_Table_ID
				+ ", Target AD_Table_ID=" + p_target_AD_Table_ID);
		
		MTable targetTable = new MTable(getCtx(), p_target_AD_Table_ID, get_TrxName());
		MColumn[] targetColumns = targetTable.getColumns(true);
		if (targetColumns.length > 0)
			// TODO: dictionary message
			throw new AdempiereSystemError("Target table must not have columns");
		
		MTable sourceTable = new MTable(getCtx(), p_source_AD_Table_ID, get_TrxName());
		MColumn[] sourceColumns = sourceTable.getColumns(true);
		
		for (int i = 0; i < sourceColumns.length; i++)
		{
			MColumn colTarget = new MColumn(targetTable);
			// special case the key -> sourceTable_ID
			if (sourceColumns[i].getColumnName().equals(sourceTable.getTableName()+"_ID")) {
				String targetColumnName = new String(targetTable.getTableName()+"_ID");
				colTarget.setColumnName(targetColumnName);
				// if the element don't exist, create it 
				M_Element element = M_Element.get (getCtx (), targetColumnName);
				if (element == null)
				{
					element = new M_Element (getCtx (), targetColumnName, targetTable.getEntityType(), get_TrxName ());
					if (targetColumnName.equalsIgnoreCase (targetTable.getTableName() + "_ID")) {
						element.setColumnName(targetTable.getTableName() + "_ID");
						element.setName(targetTable.getName());
						element.setPrintName(targetTable.getName());
					}
					element.save (get_TrxName());
				}
				colTarget.setAD_Element_ID(element.getAD_Element_ID());
				colTarget.setName(targetTable.getName());
				colTarget.setDescription(targetTable.getDescription());
				colTarget.setHelp(targetTable.getHelp());
			} else {
				colTarget.setColumnName(sourceColumns[i].getColumnName());
				colTarget.setAD_Element_ID(sourceColumns[i].getAD_Element_ID());
				colTarget.setName(sourceColumns[i].getName());
				colTarget.setDescription(sourceColumns[i].getDescription());
				colTarget.setHelp(sourceColumns[i].getHelp());
			}
			colTarget.setVersion(sourceColumns[i].getVersion());
			colTarget.setAD_Val_Rule_ID(sourceColumns[i].getAD_Val_Rule_ID());
			colTarget.setDefaultValue(sourceColumns[i].getDefaultValue());
			colTarget.setFieldLength(sourceColumns[i].getFieldLength());
			colTarget.setIsKey(sourceColumns[i].isKey());
			colTarget.setIsParent(sourceColumns[i].isParent());
			colTarget.setIsMandatory(sourceColumns[i].isMandatory());
			colTarget.setIsTranslated(sourceColumns[i].isTranslated());
			colTarget.setIsIdentifier(sourceColumns[i].isIdentifier());
			colTarget.setSeqNo(sourceColumns[i].getSeqNo());
			colTarget.setIsEncrypted(sourceColumns[i].getIsEncrypted());
			colTarget.setAD_Reference_ID(sourceColumns[i].getAD_Reference_ID());
			colTarget.setAD_Reference_Value_ID(sourceColumns[i].getAD_Reference_Value_ID());
			colTarget.setIsActive(sourceColumns[i].isActive());
			colTarget.setVFormat(sourceColumns[i].getVFormat());
			colTarget.setCallout(sourceColumns[i].getCallout());
			colTarget.setIsUpdateable(sourceColumns[i].isUpdateable());
			colTarget.setAD_Process_ID(sourceColumns[i].getAD_Process_ID());
			colTarget.setValueMin(sourceColumns[i].getValueMin());
			colTarget.setValueMax(sourceColumns[i].getValueMax());
			colTarget.setIsSelectionColumn(sourceColumns[i].isSelectionColumn());
			colTarget.setReadOnlyLogic(sourceColumns[i].getReadOnlyLogic());
			colTarget.setIsSyncDatabase(sourceColumns[i].getIsSyncDatabase());
			colTarget.setIsAlwaysUpdateable(sourceColumns[i].isAlwaysUpdateable());
			colTarget.setColumnSQL(sourceColumns[i].getColumnSQL());
			colTarget.save(get_TrxName());
			// TODO: Copy translations
			m_count++;
		}
		
		//
		return "#" + m_count;
	}	//	doIt

	
}	//	CopyColumnsFromTable