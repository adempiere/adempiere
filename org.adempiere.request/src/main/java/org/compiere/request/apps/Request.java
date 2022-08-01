/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.request.apps;

import org.compiere.model.GridTab;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.I_C_Campaign;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_Order;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_C_Payment;
import org.compiere.model.I_C_Project;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_RMA;
import org.compiere.model.I_R_Request;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequest;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.RefactoryUtil;
import org.compiere.util.ValueNamePair;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Request Controller
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>, http://www.e-evolution.com
 *      <li>#1394 Add a submenu with details of each request, in the request icon on the window toolbar
 *      <li>Reference to issue https://github.com/adempiere/adempiere/issues/1394
 */
public class Request {

    /**
     * Where Clause
     */
    protected StringBuffer whereClause = null;
    /**	The Table						*/
    protected int tableId;
    /** The Record						*/
    protected int recordId;
    /** BPartner						*/
    protected int partnerId;

    protected HashMap<String ,ValueNamePair> requestList = new HashMap<>();

    protected String REQUEST_PROCESSED = "Y";
    protected String REQUEST_UNPROCESSED = "N";
    protected String REQUEST_ALL = "";

    /**
     * Logger
     */
    protected static CLogger log = CLogger.getCLogger(Request.class);

    protected void buildWhereClause()
    {
        whereClause = new StringBuffer();
        whereClause.append("(AD_Table_ID=").append(tableId).append(" AND Record_ID=").append(recordId).append(")");

        if (tableId == MUser.Table_ID)
            whereClause.append(" OR AD_User_ID=").append(recordId)
                    .append(" OR SalesRep_ID=").append(recordId);
        else if (tableId == I_C_BPartner.Table_ID)
            whereClause.append(" OR C_BPartner_ID=").append(recordId);
        else if (tableId == I_C_Order.Table_ID)
            whereClause.append(" OR C_Order_ID=").append(recordId);
        else if (tableId == I_C_Invoice.Table_ID)
            whereClause.append(" OR C_Invoice_ID=").append(recordId);
        else if (tableId == I_C_Payment.Table_ID)
            whereClause.append(" OR C_Payment_ID=").append(recordId);
        else if (tableId == I_M_Product.Table_ID)
            whereClause.append(" OR M_Product_ID=").append(recordId);
        else if (tableId == I_C_Project.Table_ID)
            whereClause.append(" OR C_Project_ID=").append(recordId);
        else if (tableId == I_C_Campaign.Table_ID)
            whereClause.append(" OR C_Campaign_ID=").append(recordId);
        else if (tableId == RefactoryUtil.A_Asset_Table_ID)
            whereClause.append(" OR A_Asset_ID=").append(recordId);
    }

    protected void defineGridTab (GridTab gridTab)
    {
        gridTab.dataNew(false);
        gridTab.setValue("AD_Table_ID", Integer.valueOf(tableId));
        gridTab.setValue("Record_ID", Integer.valueOf(recordId));
        if (partnerId != 0)
            gridTab.setValue("C_BPartner_ID", Integer.valueOf(partnerId));
        if (tableId == I_C_BPartner.Table_ID)
            gridTab.setValue("C_BPartner_ID", Integer.valueOf(recordId));
        else if (tableId == I_AD_User.Table_ID)
            gridTab.setValue("AD_User_ID", Integer.valueOf(recordId));
        else if (tableId == I_C_Project.Table_ID)
            gridTab.setValue("C_Project_ID", Integer.valueOf(recordId));
        else if (tableId == RefactoryUtil.A_Asset_Table_ID)
            gridTab.setValue("A_Asset_ID", Integer.valueOf(recordId));
        else if (tableId == I_C_Order.Table_ID)
            gridTab.setValue("C_Order_ID", Integer.valueOf(recordId));
        else if (tableId == I_C_Invoice.Table_ID)
            gridTab.setValue("C_Invoice_ID", Integer.valueOf(recordId));
        else if (tableId == I_M_Product.Table_ID)
            gridTab.setValue("M_Product_ID", Integer.valueOf(recordId));
        else if (tableId == I_C_Payment.Table_ID)
            gridTab.setValue("C_Payment_ID", Integer.valueOf(recordId));
        else if (tableId == I_M_InOut.Table_ID)
            gridTab.setValue("M_InOut_ID", Integer.valueOf(recordId));
        else if (tableId == I_M_RMA.Table_ID)
            gridTab.setValue("M_RMA_ID", Integer.valueOf(recordId));
        else if (tableId == I_C_Campaign.Table_ID)
            gridTab.setValue("C_Campaign_ID", Integer.valueOf(recordId));
        else if (tableId == I_R_Request.Table_ID)
            gridTab.setValue(MRequest.COLUMNNAME_R_RequestRelated_ID, Integer.valueOf(recordId));
        else if (tableId == I_C_OrderLine.Table_ID) {
            MOrderLine oLine = new MOrderLine(Env.getCtx(), recordId, null);
            if (oLine != null) {
                gridTab.setValue(MOrderLine.COLUMNNAME_C_Order_ID, Integer.valueOf(oLine.getC_Order_ID()));
            }
        }
    }

    /**
     * Add each request on the menu
     */
    protected  HashMap<String ,ValueNamePair> getRequestList() {
        if (requestList != null && requestList.size() > 0)
            return requestList;

        String sql = "SELECT r.R_Request_ID,r.DocumentNo,coalesce  (r.Summary,'')," +
                "COALESCE(rt.Name,' '),COALESCE(rg.Name,' '),COALESCE(rc.Name,' '), COALESCE(rs.Name,' ')," +
                "COALESCE(rrep.Name,' ') , r.Processed , r.DateNextAction " +
                "FROM R_Request r " +
                "JOIN R_RequestType rt ON (r.R_RequestType_ID = rt.R_RequestType_ID) " +
                "LEFT JOIN R_Group rg ON (r.R_Group_ID = rg.R_Group_ID) " +
                "LEFT JOIN R_Category rc ON (r.R_Category_ID = rc.R_Category_ID) " +
                "LEFT JOIN R_Status rs ON (r.R_Status_ID = rs.R_Status_ID) " +
                "LEFT JOIN AD_User rrep ON (r.SalesRep_ID = rrep.AD_User_ID) " +
                "WHERE " + whereClause + "ORDER BY R_Request_ID";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String requestId =  rs.getString(1);
                String documentNo = rs.getString(2);
                String requestSummary = rs.getString(3);
                String requestType = rs.getString(4);
                String requestGroup = rs.getString(5);
                String requestCategory = rs.getString(6);
                String requestStatus = rs.getString(7);
                String requestResponsible = rs.getString(8);
                String processed = rs.getString(9);
                String nextAction = rs.getString(10);
                StringBuilder requestInfo = new StringBuilder();
                requestInfo.append(documentNo).append("_");
                requestInfo.append(nextAction).append("_");
                requestInfo.append(requestResponsible).append("_");
                requestInfo.append(requestSummary).append("_");
                requestInfo.append(requestType).append("_");
                requestInfo.append(requestGroup).append("_");
                requestInfo.append(requestCategory).append("_");
                requestInfo.append(requestStatus);

                ValueNamePair valueNamePair = new ValueNamePair (processed, requestInfo.toString());
                requestList.put(requestId ,valueNamePair);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
        } finally {
            DB.close(rs, pstmt);
        }
        return requestList;
    }

    protected int getCount() {
        return getRequestList().size();
    }

    protected long getProcessingCount()
    {
        return getRequestList().entrySet().stream()
                .filter(requestEntry -> requestEntry.getValue().getID().equals(REQUEST_PROCESSED)).count();
    }

    protected long getUnprocessingCount()
    {
        return getRequestList().entrySet().stream()
                .filter(requestEntry -> requestEntry.getValue().getID().equals(REQUEST_UNPROCESSED)).count();
    }
}
