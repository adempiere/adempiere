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

import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.dao.IQueryOrderByBuilder;
import org.adempiere.util.Check;

class QueryOrderByBuilder implements IQueryOrderByBuilder
{
	private List<QueryOrderByItem> orderBys = new ArrayList<QueryOrderByItem>();

	public QueryOrderByBuilder()
	{
	}


	@Override
	public IQueryOrderByBuilder addColumn(String columnName)
	{
		return addColumn(columnName, true);
	}

	@Override
	public IQueryOrderByBuilder addColumn(String columnName, boolean asc)
	{
		Check.assumeNotNull(columnName, "columnName not null");

		final QueryOrderByItem orderByItem = new QueryOrderByItem(columnName, asc);
		orderBys.add(orderByItem);

		return this;
	}

	@Override
	public IQueryOrderBy create()
	{
		final QueryOrderBy orderBy = new QueryOrderBy(orderBys);
		return orderBy;
	}
}
