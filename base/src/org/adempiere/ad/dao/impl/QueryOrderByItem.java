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