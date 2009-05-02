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

/**
 * Throwed when there are some fields that are mandatory but unfilled.
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class FillMandatoryException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9074980284529933724L;

	public FillMandatoryException(String...fields)
	{
		super("@FillMandatory@ "+buildMessage(fields));
	}
	
	private static final String buildMessage(String...fields)
	{
		StringBuffer sb = new StringBuffer();
		for (String f : fields) {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append("@").append(f).append("@");
		}
		return sb.toString();
	}
}
