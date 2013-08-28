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
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeInstance;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.Query;

/**
 *	Forcast Line Model
 *	
 *  @author Victor Perez www.e-evolution.com      
 *  @version $Id: MQMSpecification.java,v 1.11 2005/05/17 05:29:52 vpj-cd vpj-cd $
 */
public class MQMSpecification extends  X_QM_Specification
{
	/**
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_ForecastLine_ID id
	 */
	public MQMSpecification (Properties ctx, int QM_Specification_ID, String trxName)
	{
		super (ctx, QM_Specification_ID, trxName);
		if (QM_Specification_ID == 0)
		{		
		}
		
	}	//	MQMSpecification

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 */
	public MQMSpecification (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MQMSpecification	
		
	/** Lines						*/
	private List<MQMSpecificationLine>		m_lines = null;
	
	/**
	 * 	Get Lines
	 *	@return array of lines
	 */
	public List<MQMSpecificationLine> getLines(String where)
	{
		if (m_lines != null)
			return m_lines;
		
		String whereClause = MQMSpecification.COLUMNNAME_QM_Specification_ID + "=? ";
		if (where != null && where.length() > 0)
			whereClause = whereClause + "AND " + where;
		
		m_lines = new Query(getCtx(), I_QM_SpecificationLine.Table_Name, whereClause, get_TrxName())
		.setClient_ID()
		.setParameters( getQM_Specification_ID())
		.setOrderBy("SeqNo")
		.list();		
		return m_lines;
	}	//	getLines
	
	public boolean isValid(int M_AttributeSetInstance_ID)
	{
		//MAttributeSet mas = MAttributeSet.get(getCtx(), getM_AttributeSet_ID());
		
//		Save Instance Attributes
		if(M_AttributeSetInstance_ID==0)
			return false;
		MAttributeSetInstance asi = new MAttributeSetInstance(getCtx(),M_AttributeSetInstance_ID, get_TrxName());
		MAttributeSet 		  as = MAttributeSet.get(getCtx(),asi.getM_AttributeSet_ID());
		MAttribute[] attributes = as.getMAttributes(true);
		for (int i = 0; i < attributes.length; i++)
		{
		
			MAttributeInstance instance = attributes[i].getMAttributeInstance (M_AttributeSetInstance_ID);			
			List<MQMSpecificationLine> lines = getLines(" M_Attribute_ID="+attributes[i].getM_Attribute_ID());
			if(lines == null)
				return false;
			
			for (MQMSpecificationLine line : lines)
			{
				if (MAttribute.ATTRIBUTEVALUETYPE_Number.equals(attributes[i].getAttributeValueType()))
				{
				BigDecimal objValue = instance.getValueNumber();
				if(!line.evaluate(objValue,instance.getValue()));
				return false;
				}
				else
				{
				String	objValue = instance.getValue();
				if(!line.evaluate(objValue,instance.getValue()))
					return false;	
				}
				//if(line.evaluate(mas.getValueNumber())
			}			
		}	//	for all attributes			
		return true;
	}
	
}	//	MQMSpecification
