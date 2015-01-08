package org.adempiere.util;

import org.compiere.util.DB;
import org.compiere.util.Trx;

public class DeleteEntitiesModel {

	public boolean mandatoryLink;
	public String tableName;
	public String keyColumn;
	public String joinColumn;
	public String parentTableName;
	public String parentColumn; 
	public String whereClause;
	

	public DeleteEntitiesModel() {
	}

	public int getCount() {
		
		return DB.getSQLValue(null, "SELECT count(*) FROM " + tableName + " WHERE " + whereClause);
	}

	public int delete(Trx m_trx) {
		String sql;
		if ( mandatoryLink )
		{
			sql = "DELETE FROM " + tableName
			+ " WHERE " + whereClause;

			//log.log(Level.FINE, "Deleting: " + sql);
		}
		else
		{
			sql = "UPDATE " + tableName + " SET " + joinColumn + " = NULL "
			+ " WHERE " + whereClause;
			

			//log.log(Level.FINE, "Updating: " + sql);
		}	
		
		int count = DB.executeUpdateEx(sql, m_trx.getTrxName());
		//log.log(Level.FINE, (mandatoryLink ? "Deleted: " : "Updated: ") + count + " FROM " + tableName);
	
		return count;
	}
	
	
	@Override
	public String toString() {
		
		return tableName + (joinColumn == null ? "" :  "." + joinColumn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((tableName == null) ? 0 : tableName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeleteEntitiesModel other = (DeleteEntitiesModel) obj;
		if (tableName == null) {
			if (other.tableName != null)
				return false;
		} else if (!tableName.equals(other.tableName))
			return false;
		return true;
	}
}