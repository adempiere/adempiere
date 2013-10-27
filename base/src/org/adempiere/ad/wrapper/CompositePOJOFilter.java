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
 *																		      *
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.util.Check;

public class CompositePOJOFilter<T> implements IPOJOFilter<T>
{
	private final List<IPOJOFilter<T>> filters = new ArrayList<IPOJOFilter<T>>();

	public CompositePOJOFilter<T> addFilter(IPOJOFilter<T> filter)
	{
		Check.assumeNotNull(filter, "filter not null");
		if (!filters.contains(filter))
		{
			filters.add(filter);
		}

		return this;
	}

	public CompositePOJOFilter<T> addFilters(final List<IPOJOFilter<T>> filters)
	{
		if (filters == null || filters.isEmpty())
		{
			return this;
		}

		for (final IPOJOFilter<T> filter : filters)
		{
			addFilter(filter);
		}

		return this;
	}

	public List<IPOJOFilter<T>> getFilters()
	{
		return new ArrayList<IPOJOFilter<T>>(filters);
	}

	@Override
	public boolean accept(T pojo)
	{
		for (final IPOJOFilter<T> filter : filters)
		{
			if (!filter.accept(pojo))
			{
				return false;
			}
		}

		return true;
	}

}
