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
 * Created by Victor Perez are Copyright (C) e-Evolution,SC. All Rights Reserved.
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.sqlj;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *	SQLJ Manufacturing related Functions
 *	
 *  @author Victor Perez
 *  @version $Id: Manufacturing.java,v 1 2005/04/06 10:15:02 vj-cd Exp $
 */
public class Manufacturing
{
	/**
	 * 	Get Order_ID for OrderType.	 * 	
	 * 	Test:
	 	    SELECT CASE WHEN mrp.OrderType = 'FTC' THEN (SELECT f.Name FROM M_Forecast f WHERE f.M_Forecast_ID=mrp.M_Forecast_ID)
	 	    			WHEN mrp.OrderType = 'POO' THEN (SELECT o.DocumentNo FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID) 
	 	    			WHEN mrp.OrderType = 'DOO' THEN (SELECT o.DocumentNo FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID) 
         		        WHEN mrp.OrderType = 'SOO' THEN (SELECT o.DocumentNo FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID)        		                                              														
         		        WHEN mrp.OrderType = 'MOP' THEN (SELECT o.DocumentNo FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID)
         		        WHEN mrp.OrderType = 'POR' THEN (SELECT r.DocumentNo FROM M_Requisition r WHERE r.M_Requisition_ID=mrp.M_Requisition_ID) END AS DocumentNo															   
         		        FROM PP_MRP mrp WHERE mrp.PP_MRP_ID=PP_MRP.PP_MRP_ID))
	 *	@param p_PP_MRP_ID
	 *	@return DocumentNo
	 */
	public static String documentNo (int p_PP_MRP_ID)
		throws SQLException
	{
		if (p_PP_MRP_ID == 0)
			return "";
		//
		String documentNo = "";
		//	Get Base Info
		String sql = "SELECT CASE WHEN mrp.OrderType = 'FTC' THEN (SELECT f.Name FROM M_Forecast f WHERE f.M_Forecast_ID=mrp.M_Forecast_ID) "
					+ "WHEN mrp.OrderType = 'POO' THEN (SELECT o.DocumentNo FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID) "
					+ "WHEN mrp.OrderType = 'DOO' THEN (SELECT o.DocumentNo FROM DD_Order o WHERE o.DD_Order_ID=mrp.DD_Order_ID) " 
			        + "WHEN mrp.OrderType = 'SOO' THEN (SELECT o.DocumentNo FROM C_Order o WHERE o.C_Order_ID=mrp.C_Order_ID) "
					+ "WHEN mrp.OrderType = 'MOP' THEN (SELECT o.DocumentNo FROM PP_Order o WHERE o.PP_Order_ID=mrp.PP_Order_ID) "
					+ "WHEN mrp.OrderType = 'POR' THEN (SELECT r.DocumentNo FROM M_Requisition r WHERE r.M_Requisition_ID=mrp.M_Requisition_ID) " 
					+ "END AS DocumentNo "
					+ "FROM PP_MRP mrp WHERE mrp.PP_MRP_ID=?";
			
		PreparedStatement pstmt = Adempiere.prepareStatement(sql);
		pstmt.setInt(1, p_PP_MRP_ID);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			documentNo = rs.getString(1);			
		}
		rs.close();
		pstmt.close();
        return documentNo;		
	}	//	getdocumentNo
	

	
}	//	Manufacturing
