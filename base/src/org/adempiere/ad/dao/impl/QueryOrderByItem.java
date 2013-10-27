/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * @author Teo Sarca , t.sarca@metas.ro , METAS GROUP						  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.dao.impl;

import org.compiere.util.EqualsBuilder;
import org.compiere.util.HashcodeBuilder;

final class QueryOrderByItem
{
	private final String columnName;
	private final boolean asc;

	public QueryOrderByItem(String columnName, boolean asc)
	{
		super();
		this.columnName = columnName;
		this.asc = asc;
	}

	@Override
	public String toString()
	{
		return "OrderByItem [columnName=" + columnName + ", asc=" + asc + "]";
	}

	@Override
	public int hashCode()
	{
		return new HashcodeBuilder()
				.append(columnName)
				.append(asc)
				.toHashcode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		final QueryOrderByItem other = EqualsBuilder.getOther(this, obj);
		if (other == null)
		{
			return false;
		}
		return new EqualsBuilder()
				.append(this.columnName, other.columnName)
				.append(this.asc, other.asc)
				.isEqual();
	}

	/**
	 * @return the columnName
	 */
	public String getColumnName()
	{
		return columnName;
	}

	/**
	 * @return the asc
	 */
	public boolean isAsc()
	{
		return asc;
	}
}