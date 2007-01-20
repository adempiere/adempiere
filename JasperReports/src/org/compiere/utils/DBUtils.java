/*
 * Class DBUtils.
 */
package org.compiere.utils;

import org.compiere.report.ReportStarter;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

import java.sql.*;

public class DBUtils {
    static {
        try {
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println("ERROR !!!");
            e.printStackTrace();
        }
    }
//  logger
	private static CLogger log = CLogger.getCLogger(ReportStarter.class);

    public static Connection getConnection( String db, String user, String password) throws SQLException {
        return DriverManager.getConnection( db, user, password);
    }

    public static String amount2char( int amount,  boolean upperCase) {
        return amount2char( (double) amount, upperCase);
    }

    public static String amount2char( long amount,  boolean upperCase) {
        return amount2char( (double) amount, upperCase);
    }

    public static String amount2char( float amount,  boolean upperCase) {
        return amount2char( (double) amount, upperCase);
    }

    public static String amount2char(double amount, boolean upperCase) {
        log.info("DBUtils.amount2char amount=" + amount + "  upperCase=" + upperCase);
        String sql = "{? = call amount2char(?,?)}";
        try {
            CallableStatement cstmt = DB.prepareCall(sql);
            //
            cstmt.registerOutParameter(1, Types.VARCHAR);
            //
            cstmt.setDouble(2, amount);
            cstmt.setString(3, (upperCase==true)?"Y":"N");
            cstmt.execute();
            //  Result
            String retValue = cstmt.getString(1);
            cstmt.close();
            return retValue;
        } catch (SQLException e) {
            log.severe("DBUtils.amount2char - "+sql+" "+ e.getMessage());
            return null;
        }
    }

    public static void close( ResultSet rs) {
        try {
            if (rs!=null) rs.close();
        } catch (SQLException e) {
            ;
        }
    }
    public static void close( Statement st) {
        try {
            if (st!=null) st.close();
        } catch (SQLException e) {
            ;
        }
    }
}
