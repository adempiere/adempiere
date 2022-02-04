package org.compiere.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface to run ResultSets in anonymous function
 * @author Raul Capecce, raul.capecce@openupsolutions.com
 * 		<li>FR [ 3725 ] New way to iterate ResultSets
 * 		@see https://github.com/adempiere/adempiere/issues/3725
 */
@FunctionalInterface
public interface ResultSetRunnable {
    void run(ResultSet resultSet) throws SQLException;
}
