package org.compiere.util;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetRunnable {
    void run(ResultSet resultSet) throws SQLException;
}
