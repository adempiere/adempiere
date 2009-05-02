/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package org.adempiere.exceptions;

import org.compiere.model.MDocType;
import org.compiere.model.MRefList;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Throwed when desired document type was not found
 * @author Teo Sarca, www.arhipac.ro
 */
public class DocTypeNotFoundException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4218893853798807816L;
	/** Doc Base Type */
	private String m_docBaseType = null;
	
	/**
	 * @param docBaseType Document Base Type (see MDocType.DOCBASETYPE_*)
	 * @param additionalInfo optional if there is some additional info
	 */
	public DocTypeNotFoundException(String docBaseType, String additionalInfo)
	{
		super(additionalInfo);
		m_docBaseType = docBaseType;
	}
	
	public String getDocBaseType()
	{
		return m_docBaseType;
	}

	@Override
	public String getMessage()
	{
		String additionalInfo = super.getMessage();
		String docBaseTypeName = MRefList.getListName(Env.getCtx(), MDocType.DOCBASETYPE_AD_Reference_ID, getDocBaseType());
		StringBuffer sb = new StringBuffer("@NotFound@ @C_DocType_ID@");
		sb.append(" - @DocBaseType@ : " + docBaseTypeName);
		if (!Util.isEmpty(additionalInfo, true))
		{
			sb.append(" (").append(additionalInfo).append(")");
		}
		return sb.toString();
	}

	
}
