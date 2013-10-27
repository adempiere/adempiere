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
import java.util.Comparator;
import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.util.comparator.AccessorComparator;
import org.adempiere.util.comparator.ComparableComparator;
import org.adempiere.util.comparator.ComparatorChain;
import org.adempiere.util.comparator.NullComparator;

class QueryOrderBy implements IQueryOrderBy
{
	private List<QueryOrderByItem> items;

	private boolean sqlOrderByCompiled = false;
	private String sqlOrderBy = null;

	private Comparator<Object> comparator = null;

	public QueryOrderBy(final List<QueryOrderByItem> items)
	{
		this.items = items == null ? null : new ArrayList<QueryOrderByItem>(items);
	}

	@Override
	public String toString()
	{
		return "QueryOrderBy[" + items + "]";
	}

	@Override
	public String getSql()
	{
		if (sqlOrderByCompiled)
		{
			return sqlOrderBy;
		}

		if (items != null && !items.isEmpty())
		{
			final StringBuilder sb = new StringBuilder();
			for (QueryOrderByItem item : items)
			{
				if (sb.length() > 0)
				{
					sb.append(", ");
				}
				sb.append(item.getColumnName()).append(item.isAsc() ? " ASC" : " DESC");
			}
			sqlOrderBy = sb.toString();
		}
		else
		{
			sqlOrderBy = null;
		}
		sqlOrderByCompiled = true;

		return sqlOrderBy;
	}

	@Override
	public Comparator<Object> getComparator()
	{
		if (comparator != null)
		{
			return comparator;
		}

		if (items != null && !items.isEmpty())
		{
			final ComparatorChain<Object> cmpChain = new ComparatorChain<Object>();
			for (final QueryOrderByItem item : items)
			{
				@SuppressWarnings("rawtypes")
				final ModelAccessor<Comparable> accessor = new ModelAccessor<Comparable>(item.getColumnName());

				@SuppressWarnings("rawtypes")
				final Comparator<Object> cmp = new AccessorComparator<Object, Comparable>(
						ComparableComparator.getInstance(),
						accessor);

				cmpChain.addComparator(cmp, !item.isAsc());
			}
			comparator = cmpChain;
		}
		else
		{
			comparator = NullComparator.getInstance();
		}

		return comparator;
	}
}
