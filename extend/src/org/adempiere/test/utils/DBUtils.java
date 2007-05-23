package org.adempiere.test.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
/*	
    static {
        try {
            DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println("ERROR !!!");
            e.printStackTrace();
        }
    }
*/
	
    // logger
	//private static CLogger log = CLogger.getCLogger(DBUtils.class);

    public static Connection getConnection( String db, String user, String password) throws SQLException {
        return DriverManager.getConnection( db, user, password);
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
