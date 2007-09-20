package org.posterita.model;

import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.util.DB;

public class UDIMMovementLineOverride extends MMovementLine 
{

	/**
	 * Override the beforeSave method of MMovementLine as the query picks a wrong table name
	 */
	private static final long serialVersionUID = 1L;

	public UDIMMovementLineOverride(MMovement parent) 
	{
		super(parent);
		
	}

	protected boolean beforeSave (boolean newRecord)
	{
		//	Set Line No
		if (getLine() == 0)
		{
			String sql = "SELECT COALESCE(MAX(Line),0)+10 AS DefaultValue FROM M_MovementLine WHERE M_Movement_ID=?";
			int ii = DB.getSQLValue (get_TrxName(), sql, getM_Movement_ID());
			setLine (ii);
		}
		return true;
	}	//	beforeSave
	
	
}
