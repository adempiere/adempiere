/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Generated Model - DO NOT CHANGE (manual stub for Cost Adjustment Line)     *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_Persistent;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.util.Env;

public class X_C_CostAdjustmentLine extends PO implements I_C_CostAdjustmentLine, I_Persistent {

    private static final long serialVersionUID = 20260908L;

    public static final int Table_ID = I_C_CostAdjustmentLine.Table_ID;
    public static final String Table_Name = I_C_CostAdjustmentLine.Table_Name;

    public X_C_CostAdjustmentLine(Properties ctx, int C_CostAdjustmentLine_ID, String trxName) {
        super(ctx, C_CostAdjustmentLine_ID, trxName);
    }

    public X_C_CostAdjustmentLine(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    protected int get_AccessLevel() {
        return 3;
    }

    protected POInfo initPO(Properties ctx) {
        POInfo poi = POInfo.getPOInfo(ctx, Table_ID, get_TrxName());
        return poi;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("X_C_CostAdjustmentLine[").append(get_ID()).append("]");
        return sb.toString();
    }

    public void setC_CostAdjustmentLine_ID(int C_CostAdjustmentLine_ID) {
        if (C_CostAdjustmentLine_ID < 1)
            set_ValueNoCheck(COLUMNNAME_C_CostAdjustmentLine_ID, null);
        else
            set_ValueNoCheck(COLUMNNAME_C_CostAdjustmentLine_ID, Integer.valueOf(C_CostAdjustmentLine_ID));
    }

    public int getC_CostAdjustmentLine_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_CostAdjustmentLine_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public int getC_CostAdjustmentEvent_ID() {
        Integer ii = (Integer) get_Value(COLUMNNAME_C_CostAdjustmentEvent_ID);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setC_CostAdjustmentEvent_ID(int C_CostAdjustmentEvent_ID) {
        set_Value(COLUMNNAME_C_CostAdjustmentEvent_ID, Integer.valueOf(C_CostAdjustmentEvent_ID));
    }

    public void setLine(int Line) {
        set_Value(COLUMNNAME_Line, Integer.valueOf(Line));
    }

    public int getLine() {
        Integer ii = (Integer) get_Value(COLUMNNAME_Line);
        if (ii == null)
            return 0;
        return ii.intValue();
    }

    public void setAdjustmentLineType(String AdjustmentLineType) {
        set_Value(COLUMNNAME_AdjustmentLineType, AdjustmentLineType);
    }

    public String getAdjustmentLineType() {
        return (String) get_Value(COLUMNNAME_AdjustmentLineType);
    }

    public void setAmt(BigDecimal Amt) {
        set_Value(COLUMNNAME_Amt, Amt);
    }

    public BigDecimal getAmt() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_Amt);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setQty(BigDecimal Qty) {
        set_Value(COLUMNNAME_Qty, Qty);
    }

    public BigDecimal getQty() {
        BigDecimal bd = (BigDecimal) get_Value(COLUMNNAME_Qty);
        if (bd == null)
            return Env.ZERO;
        return bd;
    }

    public void setDescription(String Description) {
        set_Value(COLUMNNAME_Description, Description);
    }

    public String getDescription() {
        return (String) get_Value(COLUMNNAME_Description);
    }

    public static int getTable_ID(String trxName) {
        int id = MTable.getTable_ID(Table_Name);
        return id > 0 ? id : Table_ID;
    }
}
