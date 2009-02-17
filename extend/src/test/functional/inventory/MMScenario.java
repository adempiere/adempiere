/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2009 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package test.functional.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.adempiere.exceptions.AdempiereException;

/**
 * @author Teo Sarca, www.arhipac.ro
 */
public class MMScenario
{
	public final String name;
	public String key = null;
	public final List<MMDocument> docs = new ArrayList<MMDocument>();
	
	/** ASI Code -> M_AttributeSetInstance_ID */
	public final HashMap<String, Integer> asiCodes = new HashMap<String, Integer>();
	
	public MMScenario(String name)
	{
		this.name = name;
	}
	
	public MMDocument get(String docBaseType, String documentNo)
	{
		if (documentNo == null)
			throw new IllegalArgumentException("documentNo is null");
		//
		for (MMDocument doc : docs)
		{
			if (docBaseType.equals(doc.DocBaseType) && documentNo.equals(documentNo))
				return doc;
		}
		throw new AdempiereException("["+name+"] document not found for"
					+" DocBaseType="+docBaseType+", documentNo="+documentNo);
	}
	
	public void registerASICode(String asiCode, int M_ASI_ID, boolean isCreated)
	{
		asiCode = asiCode.trim();
		
		if (isCreated && asiCodes.get(asiCode) != null)
		{
			throw new AdempiereException("ASI Should be unique : Code="+asiCode+", ID="+M_ASI_ID);
		}
		asiCodes.put(asiCode, M_ASI_ID);
	}
	
	public int getM_ASI_ID(String asiCode)
	{
		asiCode = asiCode.trim();
		if (asiCode.equals("0"))
			return 0;
		
		Integer asi_id = asiCodes.get(asiCode);
		if (asi_id == null)
		{
			throw new AdempiereException("No ASI created for Code="+asiCode);
		}
		return asi_id;
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("__SCENARIO________________________________________________\n");
		sb.append("   Name : "+this.name).append("\n");
		sb.append("    Key : "+this.key).append("\n");
		//
		sb.append("Lines: \n");
		for (MMDocument doc : this.docs)
		{
			sb.append("    ").append(doc.toString()).append("\n");
		}
		//
		sb.append("ASI(Code=>ID): ");
		for (Entry<String, Integer> entry : this.asiCodes.entrySet())
		{
			sb.append("(")
				.append(entry.getKey()).append("=>").append(entry.getValue())
				.append("); ");
		}
		sb.append("\n");
		//
		sb.append("__________________________________________________________\n");
		return sb.toString();
	}
	
	
}
