/*
*
* Copyright (c) 2007 Posterita Ltd. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* Tamak ICT Ltd. ("Confidential Information").  You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into
* with Tamak ICT.
*
* Tamak ICT MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
* SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT
* NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
* A PARTICULAR PURPOSE, OR NON-INFRINGEMENT. Tamak ICT SHALL NOT
* BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
* MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*
* Created on Feb 6, 2007 by ashley
* 
*/

/**
	@author ashley
 */

package org.posterita.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.util.ModelClassGenerator;
import org.adempiere.util.ModelInterfaceGenerator;
import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;

public class RegenerateModels
{

    private static final ArrayList<String> tables = new ArrayList<String>();
    private static final String PACKAGE_NAME = "org.compiere.model";
    private static final String DIRECTORY = PathInfo.PROJECT_HOME + "/base/src/org/compiere/model/";
    
    static
    {
        tables.add("U_WebMENU");
        tables.add("U_RoleMenu");
        tables.add("U_WEB_PROPERTIES");
        tables.add("U_BlackListCheque");
        tables.add("C_ORDER");
        tables.add("AD_USER");
        tables.add("AD_ROLE");
        tables.add("M_PRODUCT");
        tables.add("AD_PRINTFORMAT");
    }
    
    public static void main(String args[]) throws Exception
    {
        Adempiere.startupEnvironment(true);
        CLogMgt.setLevel(Level.FINE);
        
        StringBuffer sqlStmt = new StringBuffer();
        
        StringBuffer tableNames = new StringBuffer();
        
        for (String tblName : tables)
        {
            tableNames.append(", '").append(tblName).append("'");
        }
        
        if (tableNames.length() > 0 && tables.size() > 0)
        {
            String tableInStmt = tableNames.substring(1);
            tableInStmt = tableInStmt.toUpperCase();
            
            String entityType = "'U','A','D','EXT'";
            sqlStmt.append("SELECT AD_Table_ID from AD_Table where ");
            sqlStmt.append(" (TableName IN ('RV_WarehousePrice','RV_BPartner')");
            sqlStmt.append(" OR IsView='N')");
            sqlStmt.append(" AND Upper(TableName) in (").append(tableInStmt).append(") and");
            sqlStmt.append(" EntityType in (").append(entityType).append(")");
            sqlStmt.append(" ORDER BY TableName");
            
            PreparedStatement pstmt = null;
            try
            {
                pstmt = DB.prepareStatement(sqlStmt.toString(), null);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next())
                {
                    int tableId = rs.getInt(1);
                    new ModelInterfaceGenerator(tableId, DIRECTORY, PACKAGE_NAME);
                    new ModelClassGenerator(tableId, DIRECTORY, PACKAGE_NAME);
                }
                rs.close();
            }
            catch (SQLException ex)
            {
                throw ex;
            }
            finally
            {
                if (pstmt != null)
                {
                    try
                    {
                        pstmt.close();
                    }
                    catch (Exception ex){}
                }
            }
            
        }
        
    }
}
