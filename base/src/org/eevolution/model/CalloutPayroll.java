/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is Compiere ERP & CRM Smart Business Solution. The Initial
 * Developer of the Original Code is Jorg Janke. Portions created by Jorg Janke
 * are Copyright (C) 1999-2005 Jorg Janke.
 * All parts are Copyright (C) 1999-2005 ComPiere, Inc.  All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.eevolution.model;

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;
import org.compiere.model.*;
import org.compiere.model.MOrder;
import org.eevolution.model.*;

/**
 *	Payroll Callouts.
 *	org.eevolution.model.CalloutPayroll.*
 *	
 *  
 *  @version $Id: CalloutPayment.java,v 1.17 2005/11/06 01:17:27 jjanke Exp $
 */
public class CalloutPayroll extends CalloutEngine
{
	/**
	 *  Payroll
	 */
	public String ColumnType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null )
			return "";
		Integer HR_Concept_ID = (Integer) value;
		if ( HR_Concept_ID == null || HR_Concept_ID.intValue() == 0)
			return "";
		mTab.setValue("ColumnType",DB.getSQLValueString("HR_Concept",
													"SELECT ColumnType FROM HR_Concept WHERE HR_Concept_ID=?",HR_Concept_ID.intValue()) );
		return "";
	}

	/**
	 *	HRProcess Change - DocType.
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 *  @return Error message or ""
	 */
	public String docType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer C_DocType_ID = (Integer)value;		//	Actually C_DocTypeTarget_ID
		if (C_DocType_ID == null || C_DocType_ID.intValue() == 0)
			return "";
		
		//	Re-Create new DocNo, if there is a doc number already
		//	and the existing source used a different Sequence number
		String oldDocNo = (String)mTab.getValue("DocumentNo");
		boolean newDocNo = (oldDocNo == null);
		
		if (!newDocNo && oldDocNo.startsWith("<") && oldDocNo.endsWith(">"))
			newDocNo = true;
		Integer oldC_DocType_ID = (Integer)mTab.getValue("C_DocType_ID");
		
		String sql = "SELECT d.DocSubTypeSO,d.HasCharges,'N',"			//	1..3
			+ "d.IsDocNoControlled,s.CurrentNext,s.CurrentNextSys,"     //  4..6
			+ "s.AD_Sequence_ID,d.IsSOTrx "                             //	7..8
			+ "FROM C_DocType d, AD_Sequence s "
			+ "WHERE C_DocType_ID=?"	//	#1
			+ " AND d.DocNoSequence_ID=s.AD_Sequence_ID(+)";
		
		try
		{
			int AD_Sequence_ID = 0;
			//	Get old AD_SeqNo for comparison	
			if (!newDocNo && oldC_DocType_ID.intValue() != 0)
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, oldC_DocType_ID.intValue());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next())
					AD_Sequence_ID = rs.getInt(6);
				rs.close();
				pstmt.close();
			}
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_DocType_ID.intValue());
			ResultSet rs = pstmt.executeQuery();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		}
		return "";
	}	//	docType	
}	//	CalloutPayroll
