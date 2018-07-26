/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/

package compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * User Callout Example.
 *
 * @author Jorg Janke
 * @version $Id: CalloutUser.java,v 1.2 2006/07/30 00:51:57 jjanke Exp $
 */
public class CalloutUser extends CalloutEngine {
  /**
   * JustAnExample. The string in the Callout field is: 
   * <code>com.adempiere.custom.CalloutEngine.justAnExample</code>
   *
   * @param ctx
   *          Context
   * @param windowNo
   *          current Window No
   * @param tab
   *          Model Tab
   * @param field
   *          Model Field
   * @param value
   *          The new value
   * @param oldValue
   *          The old value
   * @return error message or "" if OK
   */
  public String justAnExample(Properties ctx, int windowNo, GridTab tab, GridField field, 
      Object value, Object oldValue) {
    log.info("JustAnExample");
    return "";
  } // justAnExample

  /**
   * Invoice Header- BPartner. - C_BPartner_Location_ID - AD_User_ID
   * 
   * @param ctx
   *          Context
   * @param windowNo
   *          current Window No
   * @param tab
   *          Model Tab
   * @param field
   *          Model Field
   * @param value
   *          The new value
   * @param oldValue
   *          The old value
   * @return error message
   */
  public String bPartner(Properties ctx, int windowNo, GridTab tab, GridField field, 
      Object value, Object oldValue) {
    Integer bpartnerId = (Integer) value;
    if (bpartnerId == null || bpartnerId.intValue() == 0) {
      return "";
    }

    String sql =
        "SELECT p.AD_Language,p.C_PaymentTerm_ID,"
            + " COALESCE(p.M_PriceList_ID,g.M_PriceList_ID) AS M_PriceList_ID,"
            + " p.PaymentRule,p.POReference,"
            + " p.SO_Description,p.IsDiscountPrinted,"
            + " p.SO_CreditLimit, p.SO_CreditLimit-p.SO_CreditUsed AS CreditAvailable,"
            + " l.C_BPartner_Location_ID,c.AD_User_ID,"
            + " COALESCE(p.PO_PriceList_ID,g.PO_PriceList_ID) AS PO_PriceList_ID,"
            + " p.PaymentRulePO,p.PO_PaymentTerm_ID "
            + "FROM C_BPartner p" + " INNER JOIN C_BP_Group g ON (p.C_BP_Group_ID=g.C_BP_Group_ID)"
            + " LEFT OUTER JOIN C_BPartner_Location l ON (p.C_BPartner_ID=l.C_BPartner_ID AND l.IsBillTo='Y' AND l.IsActive='Y')"
            + " LEFT OUTER JOIN AD_User c ON (p.C_BPartner_ID=c.C_BPartner_ID) "
            + "WHERE p.C_BPartner_ID=? AND p.IsActive='Y'"; // #1

    try {
      PreparedStatement pstmt = DB.prepareStatement(sql, null);
      pstmt.setInt(1, bpartnerId.intValue());
      ResultSet rs = pstmt.executeQuery();
      //
      if (rs.next()) {
        // Location
        int locId = rs.getInt("C_BPartner_Location_ID");
        // overwritten by InfoBP selection - works only if InfoWindow
        // was used otherwise creates error (uses last value, may belong to differnt BP)
        if (bpartnerId.toString()
            .equals(Env.getContext(ctx, windowNo, Env.TAB_INFO, "C_BPartner_ID"))) {
          String loc = Env.getContext(ctx, windowNo, Env.TAB_INFO, "C_BPartner_Location_ID");
          if (loc.length() > 0) {
            locId = Integer.parseInt(loc);
          }
        }
        if (locId == 0) {
          tab.setValue("C_BPartner_Location_ID", null);
        } else {
          tab.setValue("C_BPartner_Location_ID", new Integer(locId));
        }

        // Contact - overwritten by InfoBP selection
        int contId = rs.getInt("AD_User_ID");
        if (bpartnerId.toString()
            .equals(Env.getContext(ctx, windowNo, Env.TAB_INFO, "C_BPartner_ID"))) {
          String cont = Env.getContext(ctx, windowNo, Env.TAB_INFO, "AD_User_ID");
          if (cont.length() > 0) {
            contId = Integer.parseInt(cont);
          }
        }
        if (contId == 0) {
          tab.setValue("AD_User_ID", null);
        } else {
          tab.setValue("AD_User_ID", new Integer(contId));
        }
      }
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      log.log(Level.SEVERE, sql, e);
      return e.getLocalizedMessage();
    }

    return "";
  } // bPartner

  /**************************************************************************
   * convert to standardized Name.
   *
   * @param value
   *          Name
   * @return Name
   */
  public String frie_Name(String value) {
    if (value == null || value.length() == 0) {
      return "";
    }
    //
    String retValue = value;
    String sql = "SELECT FRIE_Name(?) FROM DUAL";
    try {
      PreparedStatement pstmt = DB.prepareStatement(sql, null);
      pstmt.setString(1, value);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        retValue = rs.getString(1);
      }
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      log.log(Level.SEVERE, sql, e);
    }
    return retValue;
  } // Frie_Name

  /**
   * convert Name to Value.
   *
   * @param value
   *          Name
   * @return Value of Name
   */
  public String frie_Value(String value) {
    if (value == null || value.length() == 0) {
      return "";
    }
    //
    String retValue = value;
    String sql = "SELECT FRIE_Value(FRIE_Name(?)) FROM DUAL";
    try {
      PreparedStatement pstmt = DB.prepareStatement(sql, null);
      pstmt.setString(1, value);
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        retValue = rs.getString(1);
      }
      rs.close();
      pstmt.close();
    } catch (SQLException e) {
      log.log(Level.SEVERE, sql, e);
    }
    return retValue;
  } // Frie_Value

  /**
   * convert to Status.
   *
   * @param value
   *          value
   * @return Status
   */
  public String frie_Status(String value) {
    String retValue = "N"; // default
    if (value != null && value.equals("A")) { // Auslaufartikel
      retValue = "Y"; //
    }
    return retValue;
  } // Frie_Status

} // CalloutUser
