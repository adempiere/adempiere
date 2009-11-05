/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
 */

package oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

/**
 * Class to compare two oracle compiere/adempiere databases. It creates a sql
 * script to upgrade the first db to the second. Please read the readme.txt
 * before you use it!
 * 
 * @author Karsten Thiemann, kt@schaeffer-ag.de
 * 
 */
public class DBDifference {

	// global setting - please adjust them to your needs...

	/** name of first database - the one you want to migrate (old compiere) */
	private static final String DB1_NAME = "c253a";

	/** url of first database - check servername and port */
	private static final String DB1_URL = "jdbc:oracle:thin:@meitner:1521:";

	/** user name of first database - should be compiere */
	private static final String DB1_USER = "compiere";

	/** password first database */
	private static final String DB1_PASSWD = "compiere";

	/** name of second database - the migration target (adempiere) */
	private static final String DB2_NAME = "c253b";

	/** user name of second database - should be adempiere or compiere */
	private static final String DB2_USER = "compiere";

	/** password second database */
	private static final String DB2_PASSWD = "compiere";

	/** url of second database - check servername and port */
	private static final String DB2_URL = "jdbc:oracle:thin:@meitner:1521:";

	/**
	 * id of the (custom)clients admin role. If not null access to new
	 * forms/windows is added
	 */
	private static final String AD_ROLE_ID = "1000000";

	/**
	 * id of the (custom) client. If not null access to new forms/windows is
	 * added
	 */
	private static final String AD_CLIENT_ID = "1000000";

	/** time format used for AD_* entries */
	private static final String TIME_FORMAT = "RRRR-MM-DD";

	// global variables - no need to edit them...
	/** new tables to create */
	private Vector<Table> m_newTables = new Vector<Table>();

	/** tables with additional columns */
	private Vector<Table> m_changedTables = new Vector<Table>();

	/** tables missing in DB2 */
	private Vector<Table> m_tablesToDrop = new Vector<Table>();

	/** new views to create */
	private Vector<View> m_newViews = new Vector<View>();

	/** changed views */
	private Vector<View> m_changedViews = new Vector<View>();

	/** new constraints */
	private Vector<Constraint> m_newConstraints = new Vector<Constraint>();

	/** constraints missing in db2 if not a customization you can drop them */
	private Vector<Constraint> m_constraintsToDrop = new Vector<Constraint>();

	/** new lines for AD_Tables */
	private Vector<String> m_newTableEntry = new Vector<String>();

	/** changed lines for AD_Tables */
	private Vector<String> m_alterADEntry = new Vector<String>();
	
	/** deleted lines for AD_Tables */
	private Vector<String> m_deleteADEntry = new Vector<String>();

	/** new functions/procedure statements to create */
	private Vector<String> m_newFunctionStatements = new Vector<String>();

	/** new index statements to create */
	private Vector<String> m_newIndexStatements = new Vector<String>();

	/** drop index statements */
	private Vector<String> m_dropIndexStatements = new Vector<String>();

	/** functions/procedure statements to drop */
	private Vector<String> m_dropFunctionStatements = new Vector<String>();

	/** triggers to drop */
	private Vector<String> m_dropTriggerStatements = new Vector<String>();

	/** unappliable statements */
	private Vector<String> m_unappliableStatements = new Vector<String>();

	private Statement stmtdb1 = null;

	private Statement stmtdb2 = null;

	private Connection con1 = null;

	private Connection con2 = null;

	/**
	 * Main method...
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DBDifference().showDifference();
	}

	/**
	 * Compares the two databases and prints out the sql statements and hints to
	 * update the first db to the second.
	 * 
	 * @param db1
	 *            name of first database (working db)
	 * @param db2
	 *            name of second database (reference db)
	 */
	public void showDifference() {
		try {
			con1 = getConnection(DB1_NAME, DB1_USER, DB1_PASSWD, DB1_URL);
			con1.setAutoCommit(false);
			con2 = getConnection(DB2_NAME, DB2_USER, DB2_PASSWD, DB2_URL);
			con2.setAutoCommit(false);

			stmtdb1 = con1.createStatement();
			stmtdb2 = con2.createStatement();

			 System.out.println("compare tables ...");
			 compareTables();
			 System.out.println("compare constraints ...");
			 compareConstraints();
			
			 System.out.println("compare views ...");
			 compareViews();
			
			 System.out.println("compare functions/procedures ...");
			 compareFunctionsAndProcedures();
			
			 System.out.println("drop triggers ...");
			 dropTriggers();
			
			 System.out.println("compare ad_elements ...");
			 compareADElements();

			System.out.println("compare indexes ...");
			compareIndexes();

			sortAndPrintSQL();

			stmtdb1.close();
			stmtdb2.close();

			con1.close();
			con2.close();

			System.out.println("done.");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Newer compiere dbs don't use triggers. So just create drop statements.
	 * 
	 * @throws SQLException
	 */
	private void dropTriggers() throws SQLException {
		String sql = "select trigger_name from user_triggers order by trigger_name";
		ResultSet rs = stmtdb1.executeQuery(sql);
		while (rs.next()) {
			m_dropTriggerStatements.add("DROP " + rs.getString("TRIGGER_NAME") + ";");
		}
		rs.close();

	}

	/**
	 * Compare indexes.
	 * 
	 * @throws SQLException
	 */
	private void compareIndexes() throws SQLException {
		final Vector<String> indexNamesDB1 = new Vector<String>();
		final Vector<String> indexNamesDB2 = new Vector<String>();
		String sql = "select index_name, uniqueness, table_name from user_indexes "
				+ " where index_type='NORMAL' and index_name not like 'SYS_%' and index_name not like 'BIN$%'";
		ResultSet rs = stmtdb1.executeQuery(sql);
		while (rs.next()) {
			indexNamesDB1.add(rs.getString("INDEX_NAME"));
		}
		rs.close();
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			indexNamesDB2.add(rs.getString("INDEX_NAME"));
		}
		rs.close();

		final Vector<String> newIndexes = getNewElements(indexNamesDB1, indexNamesDB2);
		for (int i = 0; i < newIndexes.size(); i++) {
			if (!newIndexes.get(i).endsWith("KEY")) {
				// key indexes are generated by oracle when you create the table
				createNewIndexEntry(newIndexes.get(i));
			}

		}
		// drop indexes not found in db2
		final Vector<String> missingIndexes = getMissingElements(indexNamesDB1, indexNamesDB2);
		for (int i = 0; i < missingIndexes.size(); i++) {
			if (!missingIndexes.get(i).endsWith("KEY")) {
				//unable to drop key indexes - they are dropped if table is dropped
				m_dropIndexStatements.add("DROP INDEX " + missingIndexes.get(i) + ";");
			}
		}
		// find changed index
		for (int i = 0; i < indexNamesDB2.size(); i++) {
			if (indexNamesDB1.contains(indexNamesDB2.get(i))) {
				// index found in both dbs, test for changes
				// get columns for index
				final Vector<String> columnNames1 = new Vector<String>();
				final Vector<String> columnNames2 = new Vector<String>();
				sql = "select column_name from user_ind_columns where index_name='"
						+ indexNamesDB2.get(i) + "' order by column_position";
				rs = stmtdb1.executeQuery(sql);
				while (rs.next()) {
					columnNames1.add(rs.getString("COLUMN_NAME"));
				}
				rs.close();
				rs = stmtdb2.executeQuery(sql);
				while (rs.next()) {
					columnNames2.add(rs.getString("COLUMN_NAME"));
				}
				rs.close();
				// simple comparison - just compare the length...
				if (columnNames1.size() != columnNames2.size()) {
					if (!indexNamesDB2.get(i).endsWith("KEY")) {
						// cant drop key indexes...
						m_dropIndexStatements.add("DROP INDEX " + indexNamesDB2.get(i) + ";");
						createNewIndexEntry(indexNamesDB2.get(i));
					}
				}

			}
		}
	}

	/**
	 * Creates create index statements for the giving indexName and adds them to
	 * the global m_newIndexStatements Vector.
	 * 
	 * @param newIndexes
	 * @param i
	 * @throws SQLException
	 */
	private void createNewIndexEntry(String indexName) throws SQLException {
		ResultSet rs;
		String unique = "";
		String tableName = "";
		rs = stmtdb2
				.executeQuery("select table_name, uniqueness from user_indexes where index_name='"
						+ indexName + "'");
		if (rs.next()) {
			tableName = rs.getString("TABLE_NAME");
			unique = rs.getString("UNIQUENESS");
			if (unique.equals("NONUNIQUE")) {
				unique = "";
			}
		}
		rs.close();
		String createStatement = "CREATE " + unique + " INDEX " + indexName + " ON " + tableName
				+ " (";
		// get columns for index
		rs = stmtdb2.executeQuery("select column_name from user_ind_columns where index_name='"
				+ indexName + "' order by column_position");
		int k = 0;
		while (rs.next()) {
			if (k != 0) {
				createStatement += " ,";
			}
			createStatement += rs.getString("COLUMN_NAME");
			k++;
		}
		rs.close();
		createStatement += ");";
		m_newIndexStatements.add(createStatement);
	}

	/**
	 * Compares the functions and procedures of the two dbs.
	 * 
	 * @throws SQLException
	 */
	private void compareFunctionsAndProcedures() throws SQLException {
		final Vector<String> functionNamesDB1 = new Vector<String>();
		final Vector<String> functionNamesDB2 = new Vector<String>();
		// user_procedures holds functions and procedures...
		String sql = "select object_name from user_procedures order by object_name";
		ResultSet rs = stmtdb1.executeQuery(sql);
		while (rs.next()) {
			functionNamesDB1.add(rs.getString("OBJECT_NAME"));
		}
		rs.close();
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			functionNamesDB2.add(rs.getString("OBJECT_NAME"));
		}
		rs.close();
		// compare existing functions
		for (int i = 0; i < functionNamesDB2.size(); i++) {
			if (functionNamesDB1.contains(functionNamesDB2.get(i))) {
				sql = "select text from user_source where name='" + functionNamesDB2.get(i) + "'";
				rs = stmtdb1.executeQuery(sql);
				String text1 = "";
				String text2 = "";
				if (rs.next()) {
					text1 = rs.getString("TEXT");
				}
				rs.close();
				rs = stmtdb2.executeQuery(sql);
				if (rs.next()) {
					text2 = rs.getString("TEXT");
				}
				rs.close();
				if (!text2.equals(text1)) {
					// changed function
					System.out.println("FUNCTION OR PROCEDURE " + functionNamesDB2.get(i)
							+ " HAS CHANGED - please check it for customizations");
					rs = stmtdb2.executeQuery("select text from user_source where name='"
							+ functionNamesDB2.get(i) + "'");
					String createStatement = "create or replace ";
					while (rs.next()) {
						createStatement += rs.getString("TEXT");
					}
					m_newFunctionStatements.add(createStatement);
				}
			}
		}

		System.out.println("searching new functions/procedures ...");
		final Vector<String> newFunctions = getNewElements(functionNamesDB1, functionNamesDB2);
		for (int i = 0; i < newFunctions.size(); i++) {
			rs = stmtdb2.executeQuery("select text from user_source where name='"
					+ newFunctions.get(i) + "'");
			String createStatement = " CREATE OR REPLACE ";
			while (rs.next()) {
				createStatement += rs.getString("TEXT");
			}
			m_newFunctionStatements.add(createStatement);
		}
		final Vector<String> missingFunctions = getMissingElements(functionNamesDB1,
				functionNamesDB2);
		for (int i = 0; i < missingFunctions.size(); i++) {
			m_dropFunctionStatements.add(" DROP FUNCTION " + missingFunctions.get(i) + ";");
		}

	}

	/**
	 * Compares the views of the two dbs.
	 * 
	 * @throws SQLException
	 */
	private void compareViews() throws SQLException {
		final Vector<View> viewsDB1 = new Vector<View>();
		final Vector<View> viewsDB2 = new Vector<View>();
		String sql = "select view_name, text from user_views where view_name not like 'BIN$%'";
		ResultSet rs = stmtdb1.executeQuery(sql);
		while (rs.next()) {
			viewsDB1.add(new View(rs.getString("VIEW_NAME"), rs.getString("TEXT")));
		}
		rs.close();
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			viewsDB2.add(new View(rs.getString("VIEW_NAME"), rs.getString("TEXT")));
		}
		rs.close();
		// find new views
		for (int i = 0; i < viewsDB2.size(); i++) {
			String name = viewsDB2.get(i).getName();
			String text = viewsDB2.get(i).getText();
			boolean found = false;
			boolean identical = false;
			for (int j = 0; j < viewsDB1.size(); j++) {
				if (name.equals(viewsDB1.get(j).getName())) {
					found = true;
					if (text.equals(viewsDB1.get(j).getText())) {
						identical = true;
					}
					break;
				}
			}
			if (!found) {
				// add view to new views
				m_newViews.add(viewsDB2.get(i));
			} else if (!identical) {
				// add view to changed views
				m_changedViews.add(viewsDB2.get(i));
			}
		}
		// find views missing in db2 - no need to drop them
		for (int i = 0; i < viewsDB1.size(); i++) {
			String name = viewsDB1.get(i).getName();
			boolean found = false;
			for (int j = 0; j < viewsDB2.size(); j++) {
				if (name.equals(viewsDB2.get(j).getName())) {
					found = true;
					break;
				}
			}
			if (!found) {
				System.out.println();
				System.out.println("THE VIEW " + name
						+ " DOESN'T EXIST IN NEW DB - but no need to drop them...");
				System.out.println();
			}
		}
	}

	/**
	 * Compares the tables of the two dbs.
	 * 
	 * @throws SQLException
	 */
	private void compareTables() throws SQLException {
		final Vector<String> tableNamesDB1 = new Vector<String>();
		final Vector<String> tableNamesDB2 = new Vector<String>();
		String sql = "select table_name from user_tables where table_name not like 'BIN$%'";
		ResultSet rs = stmtdb1.executeQuery(sql);
		while (rs.next()) {
			tableNamesDB1.add(rs.getString("TABLE_NAME"));
		}
		rs.close();
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			tableNamesDB2.add(rs.getString("TABLE_NAME"));
		}
		rs.close();
		System.out.println("searching new tables ...");
		final Vector<String> newTables = getNewElements(tableNamesDB1, tableNamesDB2);
		for (int i = 0; i < newTables.size(); i++) {
			final String tableName = newTables.get(i);
			final Table table = new Table(tableName);
			sql = "select * from user_tab_columns where table_name='" + tableName + "'";
			rs = stmtdb2.executeQuery(sql);
			while (rs.next()) {
				Column column = new Column(rs);
				table.addColumn(column);
			}
			rs.close();
			m_newTables.add(table);
			//createEntriesForTable(tableName);
		}
		System.out.println("searching missing tables ...");
		final Vector<String> missingTables = getMissingElements(tableNamesDB1, tableNamesDB2);
		for (int i = 0; i < missingTables.size(); i++) {
			final Table table = new Table(missingTables.get(i));
			m_tablesToDrop.add(table);
		}
		System.out.println("searching changed tables ...");
		addChangedTablesToGlobalVector(tableNamesDB1, tableNamesDB2);
	}

	/**
	 * Searches for changed tables and adds them to the global m_changedTables
	 * Vector.
	 * 
	 * @param tableNamesDB1
	 * @param tableNamesDB2
	 * @throws SQLException
	 */
	private void addChangedTablesToGlobalVector(Vector<String> tableNamesDB1,
			Vector<String> tableNamesDB2) throws SQLException {
		final PreparedStatement stmtGetColumNamesForTable1 = con1
				.prepareStatement("select column_name from user_tab_columns where table_name=?");
		final PreparedStatement stmtGetColumNamesForTable2 = con2
				.prepareStatement("select column_name from user_tab_columns where table_name=?");
		final PreparedStatement stmtGetColumDetailsDB1 = con1
				.prepareStatement("select * from user_tab_columns where column_name=? and table_name=?");
		final PreparedStatement stmtGetColumDetailsDB2 = con2
				.prepareStatement("select * from user_tab_columns where column_name=? and table_name=?");

		Iterator iter = tableNamesDB2.iterator();
		while (iter.hasNext()) {
			String tableName = (String) iter.next();
			if (tableNamesDB1.contains(tableName)) {
				// get all column names for this table
				final Vector<String> columnNamesDB1 = new Vector<String>();
				final Vector<String> columnNamesDB2 = new Vector<String>();
				stmtGetColumNamesForTable1.setString(1, tableName);
				stmtGetColumNamesForTable2.setString(1, tableName);
				ResultSet rs = stmtGetColumNamesForTable1.executeQuery();
				while (rs.next()) {
					columnNamesDB1.add(rs.getString("COLUMN_NAME"));
				}
				rs.close();
				rs = stmtGetColumNamesForTable2.executeQuery();
				while (rs.next()) {
					columnNamesDB2.add(rs.getString("COLUMN_NAME"));
				}
				rs.close();
				// search for new columns in existing tables
				final Vector<String> addedColumns = getNewElements(columnNamesDB1, columnNamesDB2);
				if (addedColumns.size() != 0) {
					final Table table = new Table(tableName);
					for (int i = 0; i < addedColumns.size(); i++) {
						stmtGetColumDetailsDB2.setString(1, addedColumns.get(i));
						stmtGetColumDetailsDB2.setString(2, tableName);
						rs = stmtGetColumDetailsDB2.executeQuery();
						while (rs.next()) {
							final Column column = new Column(rs);
							if (!column.isNullable() && column.getDefaultValue() == null) {
								// search for sensible default value and set it
								column.setTmpDefaultValue(getTempDefaultValueForColumn(tableName,
										column));
							}
							table.addColumnToAdd(column);
						}
						rs.close();
					}
					m_changedTables.add(table);
				}
				// search for missing columns in existing tables
				final Vector<String> missingColumns = getMissingElements(columnNamesDB1,
						columnNamesDB2);
				if (missingColumns.size() != 0) {
					final Table table = new Table(tableName);
					for (int i = 0; i < missingColumns.size(); i++) {
						table.addColumnToDrop(new Column(missingColumns.get(i)));
					}
					m_changedTables.add(table);
				}
				// find changed columns
				Table changedTable = null;
				for (int i = 0; i < columnNamesDB1.size(); i++) {
					String colName = columnNamesDB1.get(i);
					if (columnNamesDB2.contains(colName)) {
						// column with that name exists in both dbs
						stmtGetColumDetailsDB1.setString(1, colName);
						stmtGetColumDetailsDB1.setString(2, tableName);
						stmtGetColumDetailsDB2.setString(1, colName);
						stmtGetColumDetailsDB2.setString(2, tableName);
						Column colDB1 = null;
						Column colDB2 = null;
						rs = stmtGetColumDetailsDB1.executeQuery();
						if (rs.next()) {
							colDB1 = new Column(rs);
						}
						rs.close();
						rs = stmtGetColumDetailsDB2.executeQuery();
						if (rs.next()) {
							colDB2 = new Column(rs);
						}
						rs.close();
						if (!colDB1.equals(colDB2)) {
							if (changedTable == null) {
								changedTable = new Table(tableName);
							}
							if (!colDB2.isNullable() && colDB2.getDefaultValue() == null) {
								// search for sensible default value and set it
								colDB2.setTmpDefaultValue(getTempDefaultValueForColumn(tableName,
										colDB2));
							}
							colDB2.setNullHasChanged(colDB2.isNullable() != colDB1.isNullable());
							final String def1 = colDB1.getDefaultValue();
							final String def2 = colDB2.getDefaultValue();
							if (def1 == null && def2 == null) {
								colDB2.setDefaultHasChanged(false);
							} else if (def1 != null && def2 != null) {
								colDB2.setDefaultHasChanged(!def1.equals(def2));
							} else {
								colDB2.setDefaultHasChanged(true);
							}
							changedTable.addColumnToModify(colDB2);
						}

					}
				}
				if (changedTable != null) {
					m_changedTables.add(changedTable);
				}
			}
		}
		stmtGetColumNamesForTable1.close();
		stmtGetColumNamesForTable2.close();
		stmtGetColumDetailsDB1.close();
		stmtGetColumDetailsDB2.close();
	}

	/**
	 * Returns a sensible (applieable) value for the given table and column. The
	 * value can be used as a default value for the column. It is used to
	 * add/modify columns with not null state to tables with existing data.
	 * 
	 * @param tableName
	 * @param column
	 * @return
	 * @throws SQLException
	 */
	private String getTempDefaultValueForColumn(String tableName, Column column)
			throws SQLException {
		String retValue = null;
		// test if it is a foreign key
		String sql = "select col2.Table_Name, col2.Column_Name from User_Cons_Columns col "
				+ "inner join user_constraints con on (col.Constraint_Name=con.Constraint_Name) "
				+ "inner join User_Cons_Columns col2 on (con.R_Constraint_Name=col2.Constraint_Name) "
				+ "where col.table_name='" + tableName + "' and con.constraint_type='R' "
				+ "and col.Column_Name='" + column.getColumnName() + "'";
		ResultSet rs = stmtdb2.executeQuery(sql);
		String fkTableName = "";
		String fkColumnName = "";
		if (rs.next()) {
			fkTableName = rs.getString("Table_Name");
			fkColumnName = rs.getString("Column_Name");
		}
		rs.close();
		if (!"".equals(fkColumnName) && !"".equals(fkTableName)) {
			try {
				// foreign key found - try to get a value from the old db
				// may be an error if fkColumn is new
				sql = "select max(" + fkColumnName + ")  from " + fkTableName;
				rs = stmtdb1.executeQuery(sql);
				if (rs.next()) {
					retValue = rs.getString(1);
				}
				rs.close();
			} catch (SQLException e) {
				// fkColumn doesn't exist in db1
				System.out.println("foreign key column missing in db1: " + fkColumnName
						+ " - table: " + fkTableName);
			}
		}
		if (retValue == null) {
			// no foreign key - check for column type
			if (column.isNumberType()) {
				retValue = "-1";
			} else if (column.isStringType()) {
				retValue = "'N'";
			}
		}
		return retValue;
	}

	/**
	 * Returns a Vector containing the Strings found in objNamesDB1 but not in
	 * objNamesDB2. This could be deleted tables, columns etc. or
	 * customizations.
	 * 
	 * @param objNamesDB1
	 * @param objNamesDB2
	 * @return
	 */
	private Vector<String> getMissingElements(Vector<String> objNamesDB1, Vector<String> objNamesDB2) {
		Vector<String> missingElements = new Vector<String>();
		Iterator iter = objNamesDB1.iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			if (!objNamesDB2.contains(name)) {
				missingElements.add(name);
			}
		}
		return missingElements;
	}

	/**
	 * Returns a Vector containing the Strings found in objNamesDB2 but not in
	 * objNamesDB1.
	 * 
	 * @param objNamesDB1
	 * @param objNamesDB2
	 * @return
	 */
	private Vector<String> getNewElements(Vector<String> objNamesDB1, Vector<String> objNamesDB2) {
		Vector<String> newElements = new Vector<String>();
		Iterator iter = objNamesDB2.iterator();
		while (iter.hasNext()) {
			String name = (String) iter.next();
			if (!objNamesDB1.contains(name)) {
				newElements.add(name);
			}
		}
		return newElements;
	}

	/**
	 * Sorts the generated sql statements by applying them to db1 and prints the
	 * sorted statement list.
	 * 
	 * @throws SQLException
	 */
	private void sortAndPrintSQL() throws SQLException {

		final Vector<String> statements = new Vector<String>(1000, 500);
		final Vector<String> sortedStatements = new Vector<String>(1000, 500);
		for (int i = 0; i < m_newTables.size(); i++) {
			statements.add(m_newTables.get(i).getCreateStatement());
		}
		for (int i = 0; i < m_changedTables.size(); i++) {
			if (m_changedTables.get(i).isAlterAdd()) {
				statements.add(m_changedTables.get(i).getAlterAddStatement());
			} else if (m_changedTables.get(i).isAlterDrop()) {
				statements.add(m_changedTables.get(i).getAlterDropStatement());
			} else if (m_changedTables.get(i).isAlterModify()) {
				statements.add(m_changedTables.get(i).getAlterModifyStatement());
			}
		}
		for (int i = 0; i < m_constraintsToDrop.size(); i++) {
			statements.add(m_constraintsToDrop.get(i).getDropString());
		}
		for (int i = 0; i < m_newConstraints.size(); i++) {
			statements.add(m_newConstraints.get(i).getAlterTableString());
		}
		Vector<String> tempVector = sortStatements(statements);
		for (int i = 0; i < tempVector.size(); i++) {
			sortedStatements.add(tempVector.get(i));
		}
		
		// new data
		sortedStatements.add("COMMIT;");
		sortedStatements.add("SET DEFINE OFF;");
		statements.clear();
		for (int i = 0; i < m_newTableEntry.size(); i++) {
			statements.add(m_newTableEntry.get(i).replaceAll("\n", " "));
		}
		tempVector = sortStatements(statements);
		for (int i = 0; i < tempVector.size(); i++) {
			sortedStatements.add(tempVector.get(i));
		}
		// changed data
		sortedStatements.add("COMMIT;");
		sortedStatements.add("SET DEFINE OFF;");
		statements.clear();
		for (int i = 0; i < m_alterADEntry.size(); i++) {
			statements.add(m_alterADEntry.get(i).replaceAll("\n", " "));
		}
		tempVector = sortStatements(statements);
		for (int i = 0; i < tempVector.size(); i++) {
			sortedStatements.add(tempVector.get(i));
		}
		// data to delete
		sortedStatements.add("COMMIT;");
		sortedStatements.add("SET DEFINE OFF;");
		statements.clear();
		for (int i = 0; i < m_deleteADEntry.size(); i++) {
			statements.add(m_deleteADEntry.get(i).replaceAll("\n", " "));
		}
		tempVector = sortStatements(statements);
		for (int i = 0; i < tempVector.size(); i++) {
			sortedStatements.add(tempVector.get(i));
		}
		

		System.out.println();
		System.out.println("---------------------------");
		System.out.println("--   SCRIPT STARTS HERE!");
		System.out.println("---------------------------");

		System.out.println("-- UNABLE TO APPLY THESE STATEMENTS - START");
		for (int i = 0; i < m_unappliableStatements.size(); i++) {
			System.out.println(m_unappliableStatements.get(i));
		}
		System.out.println("-- UNABLE TO APPLY THESE STATEMENTS - END");

		System.out.println();
		System.out.println("-- NEW/CHANGED TABLES - NEW/CHANGED AD_ENTRIES");
		for (int i = 0; i < sortedStatements.size(); i++) {
			System.out.println(sortedStatements.get(i));
		}

		// no sorting needed
		System.out.println();
		System.out.println("-- NEW VIEWS");
		for (int i = 0; i < m_newViews.size(); i++) {
			System.out.println(m_newViews.get(i).getCreateStatement());
		}
		System.out.println();
		System.out
				.println("-- CHANGED VIEWS - but check them first - don't overwrite your customizations...");
		for (int i = 0; i < m_changedViews.size(); i++) {
			System.out.println(m_changedViews.get(i).getCreateStatement());
		}

		System.out.println();
		System.out.println("-- NEW OR CHANGED FUNCTIONS/PROCEDURES");
		for (int i = 0; i < m_newFunctionStatements.size(); i++) {
			System.out.println(m_newFunctionStatements.get(i));
		}
		System.out.println();
		System.out.println("-- DROP FUNCTIONS/PROCEDURES");
		for (int i = 0; i < m_dropFunctionStatements.size(); i++) {
			System.out.println(m_dropFunctionStatements.get(i));
		}
		System.out.println();
		System.out.println("-- DROP TRIGGERS");
		for (int i = 0; i < m_dropTriggerStatements.size(); i++) {
			System.out.println(m_dropTriggerStatements.get(i));
		}
		System.out.println();
		System.out.println("-- DROP INDEXES");
		for (int i = 0; i < m_dropIndexStatements.size(); i++) {
			System.out.println(m_dropIndexStatements.get(i));
		}
		System.out.println();
		System.out.println("-- NEW OR CHANGED INDEXES");
		for (int i = 0; i < m_newIndexStatements.size(); i++) {
			System.out.println(m_newIndexStatements.get(i));
		}

		System.out.println();
		System.out.println("-- PLEASE CHECK THE SEQUENCES BY HAND - USE:");
		System.out.println("-- select * from user_sequences;");

		System.out.println();
		System.out.println(getUpdateVersionStatement());
		System.out.println("COMMIT;");

	}

	/**
	 * Sorts the given statements by applying them to db1. Returns a Vector
	 * containing the sorted statements.
	 * 
	 * @param statements
	 * @param sortedStatements
	 */
	private Vector<String> sortStatements(final Vector<String> statements) {
		final Vector<String> sortedStatements = new Vector<String>(1000, 500);
		int maxTries = statements.size();
		int attempt = 0;
		while (statements.size() != 0 && attempt <= maxTries) {
			final String statement = statements.firstElement();
			try {
				// there may be not null workaround, and we need to preserve the
				// sequence
				String[] stmts = statement.split(Table.STATEMENT_SEPARATOR);
				for (int j = 0; j < stmts.length; j++) {
					if (!"".equals(stmts[j])) {
						stmtdb1.executeUpdate(stmts[j].substring(0, stmts[j].lastIndexOf(';')));
					}
				}
				sortedStatements.add(statement.replaceAll(Table.STATEMENT_SEPARATOR, ""));
				statements.remove(0);
				maxTries = statements.size();
				attempt = 0;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				statements.remove(0);
				statements.add(statement);
			}
			attempt++;
		}

		if (statements.size() != 0) {
			for (int i = 0; i < statements.size(); i++) {
				m_unappliableStatements.add(statements.get(i));
			}
		}
		return sortedStatements;
	}

	/**
	 * Prints out the generated sql statements to the console. Old version.
	 * 
	 * @deprecated
	 */
	private void printSQL() {
		System.out.println();
		System.out
				.println("-- please replace all PA_Measure/PA_MeasureCalc related entries with the following lines");
		System.out.println("and place them at the end of the alter tables section");
		System.out.println(getHardcodedStuff());
		System.out.println();
		System.out.println("-- end of the PA_Measure/PA_MeasureCalc replacement lines");
		System.out.println();
		System.out.println("-- USE THE FOLLOWING LINES FOR YOUR MIGRATION SCRIPT:");
		System.out.println("-- INSERT TABLES");
		for (int i = 0; i < m_newTables.size(); i++) {
			System.out.println(m_newTables.get(i).getCreateStatement());
		}
		System.out.println();
		System.out.println("-- ALTER TABLES");
		for (int i = 0; i < m_changedTables.size(); i++) {
			if (m_changedTables.get(i).isAlterAdd()) {
				System.out.println(m_changedTables.get(i).getAlterAddStatement());
			}
		}
		System.out.println("-- PLEASE CHECK THE ADD/MODIFY STATEMENTS:");
		System.out.println("-- If you find a 'BEWARE' first check if the table has some data");
		System.out.println("-- if not, you can ignort the 'BEWARE', if the table has data");
		System.out.println("-- you might have to set a temporary default value for the column");
		System.out.println("-- (if not all rows have a value for the modied column)");

		System.out.println("-- Sample: 		ALTER TABLE TEST ADD ( columnname NOT NULL);");
		System.out
				.println("-- first set default value (for existing data), than remove it (for new data)");
		System.out
				.println("-- change to:	ALTER TABLE TEST ADD ( columnname DEFAULT 'tempDefault' NOT NULL);");
		System.out.println("-- 				ALTER TABLE TEST MODIFY ( columnname NULL);");
		System.out.println("-- 				ALTER TABLE TEST MODIFY ( columnname NOT NULL);");
		System.out.println();
		System.out.println("-- FOR MODIFY STATEMENTS");
		System.out.println("-- just set a value for all existing rows");
		System.out.println();
		System.out.println("-- Sample: 		ALTER TABLE TEST MODIFY ( columnname not null);");
		System.out.println("-- insert an update statement before the alter statement");
		System.out.println("-- insert before:	UPDATE TEST SET columnname 'myDefault';");

		for (int i = 0; i < m_changedTables.size(); i++) {
			if (m_changedTables.get(i).isAlterModify()) {
				System.out.println(m_changedTables.get(i).getAlterModifyStatement());
			}
		}
		System.out.println("-- PLEASE CHECK THE FOLLOWING COLUMNS - DON'T DROP CUSTOMIZATIONS");
		for (int i = 0; i < m_changedTables.size(); i++) {
			if (m_changedTables.get(i).isAlterDrop()) {
				System.out.println(m_changedTables.get(i).getAlterDropStatement());
			}
		}
		System.out.println();
		System.out.println("-- DROP TABLES - but check them first - don't drop customizations...");
		for (int i = 0; i < m_tablesToDrop.size(); i++) {
			System.out.println(m_tablesToDrop.get(i).getDropStatement());
		}

		System.out.println();
		System.out.println();
		System.out.println("-- PUT THE FOLLOWING LINES INTO A NEW FILE");
		System.out.println();

		System.out.println();
		System.out.println("-- DROP CONSTRAINTS");
		for (int i = 0; i < m_constraintsToDrop.size(); i++) {
			System.out.println(m_constraintsToDrop.get(i).getDropString());
		}
		System.out.println();
		System.out.println("-- ADD/RECREATE CONSTRAINTS");
		for (int i = m_newConstraints.size() - 1; i >= 0; i--) {
			System.out.println(m_newConstraints.get(i).getAlterTableString());
		}

		System.out.println();
		System.out.println();
		System.out.println("-- PUT THE FOLLOWING LINES INTO A NEW FILE");
		System.out.println();

		System.out.println();
		System.out.println("-- NEW AD_* ENTRIES AND ROWS OF ADDED TABLES");
		System.out.println();
		System.out.println("SET DEFINE OFF;");
		System.out.println();
		Collections.sort(m_newTableEntry, new AD_Comparator());
		for (int i = 0; i < m_newTableEntry.size(); i++) {
			System.out.println(m_newTableEntry.get(i).replaceAll("\n", " "));
		}
		System.out.println();
		System.out.println("-- CHANGED AD_* ENTRIES");
		Collections.sort(m_alterADEntry);
		for (int i = 0; i < m_alterADEntry.size(); i++) {
			System.out.println(m_alterADEntry.get(i).replaceAll("\n", " "));
		}

		System.out.println();
		System.out.println();
		System.out.println("-- PUT THE FOLLOWING LINES INTO A NEW FILE");
		System.out.println();

		System.out.println();
		System.out.println("-- NEW VIEWS");
		for (int i = 0; i < m_newViews.size(); i++) {
			System.out.println(m_newViews.get(i).getCreateStatement());
		}
		System.out.println();
		System.out
				.println("-- CHANGED VIEWS - but check them first - don't overwrite your customizations...");
		for (int i = 0; i < m_changedViews.size(); i++) {
			System.out.println(m_changedViews.get(i).getCreateStatement());
		}

		System.out.println();
		System.out.println("-- NEW OR CHANGED FUNCTIONS/PROCEDURES");
		for (int i = 0; i < m_newFunctionStatements.size(); i++) {
			System.out.println(m_newFunctionStatements.get(i));
		}

		System.out.println();
		System.out.println("-- DROP FUNCTIONS/PROCEDURES");
		for (int i = 0; i < m_dropFunctionStatements.size(); i++) {
			System.out.println(m_dropFunctionStatements.get(i));
		}

		System.out.println();
		System.out.println("-- DROP TRIGGERS - BUT DON'T DELETE CUSTOMIZATIONS");
		for (int i = 0; i < m_dropTriggerStatements.size(); i++) {
			System.out.println(m_dropTriggerStatements.get(i));
		}
		System.out.println();
		System.out.println("-- DROP INDEXES - BUT DON'T DELETE CUSTOMIZATIONS");
		for (int i = 0; i < m_dropIndexStatements.size(); i++) {
			System.out.println(m_dropIndexStatements.get(i));
		}
		System.out.println();
		System.out.println("-- NEW OR CHANGED INDEXES");
		for (int i = 0; i < m_newIndexStatements.size(); i++) {
			System.out.println(m_newIndexStatements.get(i));
		}

		System.out.println();
		System.out.println("-- PLEASE CHECK THE SEQUENCES BY HAND - USE:");
		System.out.println("-- select * from user_sequences;");

		System.out.println();
		System.out.println(getUpdateVersionStatement());
		System.out.println("COMMIT;");

	}

	/**
	 * Compares the constraint, if there are differences drop the old and create
	 * a new one.
	 * 
	 * @param name
	 * @param stat1
	 * @param stat2
	 * @throws SQLException
	 */
	private void compareConstraints() throws SQLException {
		final Vector<Constraint> constraintsDB1 = new Vector<Constraint>();
		final Vector<Constraint> constraintsDB2 = new Vector<Constraint>();
		// get all constrains but not the U(nique) and SYS_* ones
		// SYS_* constraints - name is autocreated for the not null and check
		// constraints without name
		// They are handled by the alter table statements
		String sql = "select * from user_constraints where constraint_name not like 'SYS_%' and constraint_name not like 'BIN%' and constraint_type != 'U'";

		ResultSet rs = stmtdb1.executeQuery(sql);
		while (rs.next()) {
			constraintsDB1.add(new Constraint(rs.getString("CONSTRAINT_NAME"), rs
					.getString("TABLE_NAME")));
		}
		rs.close();
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			constraintsDB2.add(new Constraint(rs.getString("CONSTRAINT_NAME"), rs
					.getString("TABLE_NAME")));
		}
		rs.close();
		// find missing constraints - don't delete them might be customizations,
		// just alert them
		System.out.println("searching constraints to drop...");

		Vector<Constraint> constraintsToDrop = new Vector<Constraint>();
		Vector<Constraint> duplicatedConstraints = new Vector<Constraint>();
		for (int i = 0; i < constraintsDB1.size(); i++) {
			String n1 = constraintsDB1.get(i).getName();
			boolean found = false;
			for (int j = 0; j < constraintsDB2.size(); j++) {
				String n2 = constraintsDB2.get(j).getName();
				if (n2.equals(n1)) {
					found = true;
					break;
				}
			}
			if (!found) {
				// constraint in db1 but not found in db2
				constraintsToDrop.add(constraintsDB1.get(i));
			} else {
				// check for changes
				duplicatedConstraints.add(constraintsDB1.get(i));
			}
		}

		for (int i = 0; i < constraintsToDrop.size(); i++) {
			m_constraintsToDrop.add(constraintsToDrop.get(i));
		}
		System.out.println("searching new constraints...");
		// find new constraints
		for (int i = 0; i < constraintsDB2.size(); i++) {
			String n2 = constraintsDB2.get(i).getName();
			boolean found = false;
			for (int j = 0; j < constraintsDB1.size(); j++) {
				String n1 = constraintsDB1.get(j).getName();
				if (n2.equals(n1)) {
					found = true;
					break;
				}
			}
			if (!found) {
				m_newConstraints.add(createNewConstraint(constraintsDB2.get(i).getName()));
			}
		}

		// old version
		for (int i = 0; i < duplicatedConstraints.size(); i++) {

			String name = duplicatedConstraints.get(i).getName();
			sql = "select Constraint_Type, Table_Name,"
					+ "Search_Condition, R_Constraint_Name, Delete_Rule"
					+ " from User_Constraints where Constraint_Name='" + name + "'";
			String sqlColumn = "select * from User_Cons_Columns where Constraint_Name='" + name
					+ "'";
			rs = stmtdb2.executeQuery(sql);
			String tableName2 = "";
			String constraintType2 = "";
			String condition2 = "";
			String rConstraintName2 = "";
			String deleteRule2 = "";
			String column2 = "";
			if (rs.next()) {
				constraintType2 = rs.getString("Constraint_Type");
				tableName2 = rs.getString("Table_Name");
				condition2 = rs.getString("Search_Condition");
				rConstraintName2 = rs.getString("R_Constraint_Name");
				deleteRule2 = rs.getString("Delete_Rule");
			}
			rs.close();
			rs = stmtdb2.executeQuery(sqlColumn);
			if (rs.next()) {
				column2 = rs.getString("Column_Name");
			}
			rs.close();
			rs = stmtdb1.executeQuery(sql);
			String tableName1 = "";
			String constraintType1 = "";
			String condition1 = "";
			String rConstraintName1 = "";
			String deleteRule1 = "";
			String column1 = "";
			if (rs.next()) {
				constraintType1 = rs.getString("Constraint_Type");
				tableName1 = rs.getString("Table_Name");
				condition1 = rs.getString("Search_Condition");
				rConstraintName1 = rs.getString("R_Constraint_Name");
				deleteRule1 = rs.getString("Delete_Rule");
			}
			rs.close();
			rs = stmtdb1.executeQuery(sqlColumn);
			if (rs.next()) {
				column1 = rs.getString("Column_Name");
			}
			rs.close();
			if (condition1 == null) {
				condition1 = "";
			}
			if (rConstraintName1 == null) {
				rConstraintName1 = "";
			}
			if (deleteRule1 == null) {
				deleteRule1 = "";
			}
			if (column1 == null) {
				column1 = "";
			}
			if (condition2 == null) {
				condition2 = "";
			}
			if (rConstraintName2 == null) {
				rConstraintName2 = "";
			}
			if (deleteRule2 == null) {
				deleteRule2 = "";
			}
			if (column2 == null) {
				column2 = "";
			}
			if (tableName1.equals(tableName2) && constraintType1.equals(constraintType2)
					&& condition1.equals(condition2) && rConstraintName1.equals(rConstraintName2)
					&& deleteRule1.equals(deleteRule2) && column1.equals(column2)) {
				// seems to be equal...
			} else {
				m_constraintsToDrop.add(duplicatedConstraints.get(i));
				m_newConstraints.add(createNewConstraint(name));
			}

		}
		addMissingSysConstraints();

	}

	/**
	 * Creates check constrains found in db2 but not in db1 and adds them to the
	 * global m_newConstraints Vector.
	 */
	private void addMissingSysConstraints() {
		String sql2 = "";
		final Vector<String> newConstraintNames = new Vector<String>();
		try {
			String sql = "select * from user_constraints where constraint_name like 'SYS_%' and constraint_type='C'";
			ResultSet rs2 = stmtdb2.executeQuery(sql);
			while (rs2.next()) {
				final String searchCondition = rs2.getString("SEARCH_CONDITION");
				if (searchCondition == null) {
					continue;
				} else if (searchCondition.toUpperCase().indexOf("IS NOT NULL") != -1) {
					// not null constraints are handled by alter table
					continue;
				}
				final String tableName = rs2.getString("TABLE_NAME");

				sql2 = "select * from user_constraints where table_name='" + tableName
						+ "' and search_condition is not null";
				ResultSet rs1 = stmtdb1.executeQuery(sql2);
				boolean found = false;
				while (rs1.next()) {
					if (searchCondition.equals(rs1.getString("SEARCH_CONDITION"))) {
						found = true;
						continue;
					}
				}
				rs1.close();
				if (!found) {
					newConstraintNames.add(rs2.getString("CONSTRAINT_NAME"));
				}
			}
			rs2.close();
			for (int i = 0; i < newConstraintNames.size(); i++) {
				m_newConstraints.add(createNewConstraint(newConstraintNames.get(i)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(sql2);
		}
	}

	/**
	 * Creates a new constraint from the data found for the constraint with the
	 * given name in db2.
	 * 
	 * @param stmtdb2
	 * @param name
	 * @throws SQLException
	 */
	private Constraint createNewConstraint(String name) throws SQLException {
		ResultSet rs = stmtdb2.executeQuery("select Constraint_Type, Table_Name,"
				+ "Search_Condition, R_Constraint_Name, Delete_Rule"
				+ " from User_Constraints where Constraint_Name='" + name + "'");
		String tableName = "";
		String constraintType = "";
		String condition = "";
		String rConstraintName = "";
		String deleteRule = "";
		String column = "";
		if (rs.next()) {
			constraintType = rs.getString("Constraint_Type");
			tableName = rs.getString("Table_Name");
			condition = rs.getString("Search_Condition");
			rConstraintName = rs.getString("R_Constraint_Name");
			deleteRule = rs.getString("Delete_Rule");
		}
		rs.close();

		if (constraintType.equals(Constraint.FOREIGN_KEY)) {
			Constraint refConstraint = null;
			rs = stmtdb2.executeQuery("select * from User_Cons_Columns where Constraint_Name='"
					+ name + "'");
			if (rs.next()) {
				column = rs.getString("Column_Name");
			}
			rs.close();
			rs = stmtdb2.executeQuery("select * from User_Cons_Columns where Constraint_Name='"
					+ rConstraintName + "'");
			if (rs.next()) {
				String refColumn = rs.getString("Column_Name");
				String refTableName = rs.getString("Table_Name");
				refConstraint = new Constraint(rConstraintName, refTableName);
				refConstraint.addColumnName(refColumn);
				refConstraint.setDeleteRule(deleteRule);
			}
			rs.close();
			Constraint c = new Constraint(name, tableName);
			c.setType(Constraint.FOREIGN_KEY);
			c.addColumnName(column);
			c.setRConstraint(refConstraint);
			c.addColumnName(column);
			c.setDeleteRule(deleteRule);
			return (c);
		} else if (constraintType.equals(Constraint.PRIMARY_KEY)) {
			rs = stmtdb2.executeQuery("select * from User_Cons_Columns where Constraint_Name='"
					+ name + "'");
			Constraint c = new Constraint(name, tableName);
			c.setType(Constraint.PRIMARY_KEY);
			c.setDeleteRule(deleteRule);
			
			while (rs.next()) {
				c.addColumnName(rs.getString("Column_Name"));
			}
			rs.close();
			return (c);
		} else if (constraintType.equals(Constraint.CHECK)) {
			rs = stmtdb2.executeQuery("select * from User_Cons_Columns where Constraint_Name='"
					+ name + "'");
			if (rs.next()) {
				column = rs.getString("Column_Name");
			}
			rs.close();
			Constraint c = new Constraint(name, tableName);
			c.setType(Constraint.CHECK);
			c.addColumnName(column);
			c.setCheckCondition(condition);
			c.setDeleteRule(deleteRule);
			return (c);
		}
		return null;
	}

	/**
	 * Compares the data in the AD_% tables. Looks for new elements only.
	 * 
	 * @param stmtdb1
	 * @param stmtdb2
	 * @throws SQLException
	 */
	private void compareADElements() throws SQLException {
		final String TABLE_DOES_NOT_EXITST = "ORA-00942";
		final Vector<String> adTableNames2 = new Vector<String>();
		String sql = "select table_name from user_tables";// where table_name like 'AD_%'";

		ResultSet rs = stmtdb2.executeQuery(sql);

		while (rs.next()) {
			final String tableName = rs.getString("TABLE_NAME");

			if (tableName.endsWith("_TRL")) {
				continue;
			}
			adTableNames2.add(tableName);
		}
		rs.close();

		for (int i = 0; i < adTableNames2.size(); i++) {
			String tableName = adTableNames2.get(i);
			boolean addClientAccess = false;

			if (tableName.equals("AD_WINDOW_ACCESS") || tableName.equals("AD_PROCESS_ACCESS")
					|| tableName.equals("AD_FORM_ACCESS")) {
				addClientAccess = true;
			}

			// get all column names
			final Vector<Column> columns1 = new Vector<Column>();
			final Vector<Column> columns2 = new Vector<Column>();
			sql = "select * from user_tab_columns where table_name='" + tableName
					+ "' order by column_id";
			rs = stmtdb1.executeQuery(sql);
			while (rs.next()) {
				columns1.add(new Column(rs));
			}
			rs.close();
			rs = stmtdb2.executeQuery(sql);
			while (rs.next()) {
				columns2.add(new Column(rs));
			}
			rs.close();
			// get keycolumns for table
			sql = "select col.column_name from user_constraints constr "
					+ "inner join User_Cons_Columns col ON (col.constraint_name = constr.constraint_name) "
					+ "where constr.constraint_type='P' and constr.table_name = '" + tableName
					+ "'";
			final Vector<String> keycolumns = new Vector<String>();
			rs = stmtdb2.executeQuery(sql);

			// create select statement for table
			sql = "select * from " + tableName + " where ";
			if (rs.next()) {
				sql += rs.getString("COLUMN_NAME");
				sql += " <1000000 ";
				keycolumns.add(rs.getString("COLUMN_NAME"));
			} else {
				// no keyColumn found
				System.out.println("Please check table " + tableName
						+ " for new data and add it by hand");
				continue;
			}
			while (rs.next()) {
				sql += " and " + rs.getString("COLUMN_NAME");
				sql += " <1000000 ";
				keycolumns.add(rs.getString("COLUMN_NAME"));
			}
			rs.close();

			Vector<ADDataElement> dataElements1 = new Vector<ADDataElement>();
			Vector<ADDataElement> dataElements2 = new Vector<ADDataElement>();
			try {
				// get data from db1 and db2 and compare them...

				rs = stmtdb2.executeQuery(sql);
				while (rs.next()) {
					final ADDataElement data = new ADDataElement();
					for (int j = 0; j < columns2.size(); j++) {
						final String colName = columns2.get(j).getColumnName();
						data.addColumnAndValue(colName, rs.getString(colName));
					}
					dataElements2.add(data);
				}
				rs.close();
				// error if new colums was added and is key column...
				rs = stmtdb1.executeQuery(sql);
				while (rs.next()) {
					final ADDataElement data = new ADDataElement();
					for (int j = 0; j < columns1.size(); j++) {
						final String colName = columns1.get(j).getColumnName();
						data.addColumnAndValue(colName, rs.getString(colName));
					}
					dataElements1.add(data);
				}
				rs.close();
				// got all data - lets compare them (will take some time...)
				// new entry? search for data with same keyValues
				
				//TODO: drop entries missing in db2...
				try {
					//find elements to drop
					for(int j = 0; j < dataElements1.size(); j++) {
						boolean found = false;
						final ADDataElement data1 = dataElements1.get(j);
						sql = "select * from " + tableName + " where ";
						for (int m = 0; m < keycolumns.size(); m++) {
							if (m != 0) {
								sql += " and ";
							}
							sql += keycolumns.get(m) + "=" + data1.getValueForColumn(keycolumns.get(m));
						}
						rs = stmtdb2.executeQuery(sql);
						if (rs.next()) {
							found = true;
						}
						rs.close();
						if(!found){
							//data exist in db1 but no longer in db2 - delete it
							createDeleteTableEntry(tableName, data1, keycolumns, columns1);
						}
					}
				} catch (SQLException e1) {
					// if keyColumns have changed...
					System.out.println(e1.getMessage() + " - on searching data to drop for table: " + tableName);
				}

				for (int j = 0; j < dataElements2.size(); j++) {
					boolean found = false;
					final ADDataElement data2 = dataElements2.get(j);
					sql = "select * from " + tableName + " where ";
					for (int m = 0; m < keycolumns.size(); m++) {
						if (m != 0) {
							sql += " and ";
						}
						sql += keycolumns.get(m) + "=" + data2.getValueForColumn(keycolumns.get(m));
					}
					rs = stmtdb1.executeQuery(sql);
					if (rs.next()) {
						found = true;
					}
					rs.close();
					if (!found) {
						createNewTableEntry(tableName, data2, keycolumns, columns2);
						if (addClientAccess && AD_CLIENT_ID != null && AD_ROLE_ID != null) {
							data2.setValueForColumn("AD_CLIENT_ID", AD_CLIENT_ID);
							data2.setValueForColumn("AD_ROLE_ID", AD_ROLE_ID);
							createNewTableEntry(tableName, data2, keycolumns, columns2);
						}
					} else {
						// could be changed...
						// compare Strings and Numbers but not Dates like
						// created/updated
						// because they are changed every version...
						sql = "select * from " + tableName + " where ";
						boolean and = false;
						for (int m = 0; m < columns2.size(); m++) {
							if (!columns1.contains(columns2.get(m))) {
								// column added in db2
								continue;
							}
							String value = data2.getValueForColumn(columns2.get(m).getColumnName());
							if (value != null && value.indexOf('\'') != -1) {
								value = value.replaceAll("'", "''");
							}
							if (and) {
								sql += " and ";
							}
							if (value == null) {
								sql += columns2.get(m).getColumnName() + " is null ";
								and = true;
								continue;
							}
							if (columns2.get(m).isStringType()) {

								sql += columns2.get(m).getColumnName() + "='" + value + "'";
								and = true;
							} else if (columns2.get(m).isNumberType()) {
								sql += columns2.get(m).getColumnName() + "="
										+ data2.getValueForColumn(columns2.get(m).getColumnName());
								and = true;
							} else {
								and = false;
							}

						}
						try {
							found = false;
							// System.out.println(sql);
							rs = stmtdb1.executeQuery(sql);

							if (rs.next()) {
								found = true;
							}
							rs.close();
							if (!found) {
								updateADEntry(tableName, data2, keycolumns, columns2);
							}
						} catch (SQLException e) {
							// corrupt sql statement because sometimes Strings
							// contains '
							if (e.getMessage().startsWith("ORA-00933")
									|| e.getMessage().startsWith("ORA-01722")) {
								// we could miss some changed ad elements but
								// never mind...
								;
							} else {
								System.out.println(e.getMessage() + ":");
								System.out.println(sql);
							}
						}

					}
				}

			} catch (SQLException e) {
				if (e.getMessage().startsWith(TABLE_DOES_NOT_EXITST)) {
					// new ad_* table, add all elements from db2
					for (int j = 0; j < dataElements2.size(); j++) {
						createNewTableEntry(tableName, dataElements2.get(j), keycolumns, columns2);
					}
				} else {
					System.out.println(e.getMessage() + " - for table " + tableName);
				}
			}

		}
	}

	/**
	 * Creates a delete from table statement for the given entry and adds it to the 
	 * global m_deleteADEntry Vector.
	 * @param tableName
	 * @param data1
	 * @param keycolumns
	 * @param columns1
	 */
	private void createDeleteTableEntry(String tableName, ADDataElement data1, Vector<String> keycolumns, Vector<Column> columns1) {
		String alterStatement = "DELETE FROM " + tableName + " WHERE ";
		boolean and = false;
		for (int i = 0; i < columns1.size(); i++) {
			final Column column = columns1.get(i);
			final String columnName = column.getColumnName();
			if (keycolumns.contains(columnName)) {
				if (and) {
					alterStatement += " AND ";
				}
				and = true;
				if (data1.getValueForColumn(columnName) == null) {
					alterStatement += column.getColumnName() + " is null ";
					continue;
				}
				alterStatement += column.getColumnName() + "=";
				if (column.isStringType()) {
					alterStatement += "'"
							+ data1.getValueForColumn(columnName).replaceAll("'", "''") + "'";
				} else {
					alterStatement += data1.getValueForColumn(columnName);
				}
			}
		}
		alterStatement += ";";
		m_deleteADEntry.add(alterStatement);
		
	}

	/**
	 * Creates an update statement for the AD_* entry identified by the given
	 * tableName, ADDataElement and keycolumns. And adds them to the global
	 * m_alterADEntry Vector.
	 * 
	 * @param tableName
	 * @param data2
	 * @param keycolumns
	 * @param columns2
	 */
	private void updateADEntry(String tableName, ADDataElement data2, Vector<String> keycolumns,
			Vector<Column> columns2) {
		String alterStatement = "UPDATE " + tableName + " SET ";
		boolean comma = false;
		for (int i = 0; i < columns2.size(); i++) {
			final Column column = columns2.get(i);
			if (!keycolumns.contains(column.getColumnName())
					&& !"DATE".equals(column.getDataType())) {
				if (comma) {
					alterStatement += ",";
				}
				comma = true;
				final String columnName = column.getColumnName();
				alterStatement += column.getColumnName() + "=";
				if (column.isStringType()) {
					if (data2.getValueForColumn(columnName) == null) {
						alterStatement += "null";
						continue;
					}
					alterStatement += "'"
							+ data2.getValueForColumn(columnName).replaceAll("'", "''") + "'";
				} else {
					alterStatement += data2.getValueForColumn(columnName);
				}
			}
		}
		alterStatement += " WHERE ";
		boolean and = false;
		for (int i = 0; i < columns2.size(); i++) {

			final Column column = columns2.get(i);
			final String columnName = column.getColumnName();
			if (keycolumns.contains(columnName)) {
				if (and) {
					alterStatement += " AND ";
				}
				and = true;
				if (data2.getValueForColumn(columnName) == null) {
					alterStatement += column.getColumnName() + " is null ";
					continue;
				}
				alterStatement += column.getColumnName() + "=";
				if (column.isStringType()) {
					alterStatement += "'"
							+ data2.getValueForColumn(columnName).replaceAll("'", "''") + "'";
				} else {
					alterStatement += data2.getValueForColumn(columnName);
				}
			}
		}
		alterStatement += ";";
		m_alterADEntry.add(alterStatement);
	}

	/**
	 * Creates an insert statement for the data identified by the given
	 * tableName, keyColumnName and keyColumValue and adds them to the global
	 * m_newTableEntry Vector. It's a row in an AD_% table found in db2 but not
	 * in db1 or a row of a new table.
	 * 
	 * @param tableName
	 * @param data2
	 * @param keyColumnNames2
	 * @throws SQLException
	 */
	private void createNewTableEntry(String tableName, ADDataElement data2,
			Vector<String> keyColumnNames2, Vector<Column> columns) throws SQLException {

		String insertStatement = "INSERT INTO " + tableName + "(";
		for (int i = 0; i < columns.size(); i++) {
			if (i != 0) {
				insertStatement += ",";
			}
			insertStatement += columns.get(i).getColumnName();
		}

		insertStatement += ")values(";

		for (int i = 0; i < columns.size(); i++) {
			if (i != 0) {
				insertStatement += ",";
			}
			// get the column (type/name) for the actual dataelement
			String type = columns.get(i).getDataType();
			String columnName = columns.get(i).getColumnName();
			int precision = columns.get(i).getDataPrecision();
			int scale = columns.get(i).getDataScale();
			if (data2.getValueForColumn(columnName) == null) {
				insertStatement += "null";
			} else {
				if (type.equals("BLOB")) {
					// I'm not shure but I think just do it with ''
					insertStatement += "'"
							+ data2.getValueForColumn(columnName).replaceAll("'", "''") + "'";
				} else if (type.equals("RAW")) {
					// I don't know.... //TODO
				} else if (type.equals("CLOB")) {
					insertStatement += "'"
							+ data2.getValueForColumn(columnName).replaceAll("'", "''") + "'";
				} else if (type.equals("CHAR") || type.equals("NCHAR") || type.equals("NVARCHAR2")
						|| type.equals("VARCHAR2")) {
					insertStatement += "'"
							+ data2.getValueForColumn(columnName).replaceAll("'", "''") + "'";
				} else if (type.equals("DATE")) {
					String date = data2.getValueForColumn(columnName);
					if (date.indexOf(' ') != -1) {
						date = date.substring(0, date.indexOf(' '));
					}
					insertStatement += "to_date('" + date + "','" + TIME_FORMAT + "')";
				} else if (type.equals("NUMBER")) {
					if (scale == 0) {
						insertStatement += data2.getValueForColumn(columnName);
					} else {
						insertStatement += data2.getValueForColumn(columnName);
					}
				}
			}

		}

		insertStatement += ");";
		m_newTableEntry.add(insertStatement);
	}

	/**
	 * Creates insert statements for every row of the given table and adds them
	 * to the global m_newTableEntry Vector.
	 * 
	 * @param tableName
	 * @throws SQLException
	 */
	private void createEntriesForTable(String tableName) throws SQLException {

		// get all column names
		final Vector<Column> columns2 = new Vector<Column>();
		String sql = "select * from user_tab_columns where table_name='" + tableName
				+ "' order by column_id";
		ResultSet rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			columns2.add(new Column(rs));
		}
		rs.close();

		// get keycolumns for table
		sql = "select col.column_name from user_constraints constr "
				+ "inner join User_Cons_Columns col ON (col.constraint_name = constr.constraint_name) "
				+ "where constr.constraint_type='P' and constr.table_name = '" + tableName + "'";
		final Vector<String> keycolumns = new Vector<String>();
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			keycolumns.add(rs.getString("COLUMN_NAME"));
		}
		rs.close();
		// create select statement for table
		sql = "select * from " + tableName;

		Vector<ADDataElement> dataElements2 = new Vector<ADDataElement>();

		// get data from db2
		rs = stmtdb2.executeQuery(sql);
		while (rs.next()) {
			final ADDataElement data = new ADDataElement();
			for (int i = 0; i < columns2.size(); i++) {
				final String colName = columns2.get(i).getColumnName();
				data.addColumnAndValue(colName, rs.getString(colName));
			}
			dataElements2.add(data);
		}
		rs.close();

		for (int i = 0; i < dataElements2.size(); i++) {
			try {
				createNewTableEntry(tableName, dataElements2.get(i), keycolumns, columns2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Some tables cannot be created the normal way because of to many
	 * constraints. This code is tested for 253b.
	 * 
	 * @deprecated
	 * @return
	 */
	private String getHardcodedStuff() {
		StringBuffer buffer = new StringBuffer();
		buffer = buffer
				.append("--  BEWARE: ALL ENTRIES IN PA_MEASURE AND PA_MEASURECALC ARE DELETED \n")
				.append("DELETE FROM PA_MEASURE;\n")
				.append("DELETE FROM PA_MEASURECALC;\n")
				.append("\n")
				.append("  ALTER TABLE PA_MEASURECALC \n")
				.append("  ADD (	AD_TABLE_ID NUMBER(10,0) NOT NULL ENABLE, \n")
				.append("	KEYCOLUMN NVARCHAR2(60) NOT NULL ENABLE, \n")
				.append("	ENTITYTYPE VARCHAR2(4 BYTE) NOT NULL ENABLE\n")
				.append("   ) ;\n")
				.append("\n")
				.append("--  INSERTING into PA_MEASURECALC \n")
				.append(
						"INSERT INTO PA_MEASURECALC (PA_MEASURECALC_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,SELECTCLAUSE,WHERECLAUSE,DATECOLUMN,ORGCOLUMN,BPARTNERCOLUMN,PRODUCTCOLUMN,AD_TABLE_ID,KEYCOLUMN,ENTITYTYPE) values (100,0,0,'Y',to_date('26.04.01','DD.MM.RR'),0,to_date('01.01.06','DD.MM.RR'),100,'Invoiced Net Revenue','Invoiced net revenue, without tax and charges','SELECT SUM(il.LineNetAmt) \n")
				.append("FROM RV_C_Invoice C_Invoice\n")
				.append(
						"  INNER JOIN RV_C_InvoiceLine il ON (C_Invoice.C_Invoice_ID=il.C_Invoice_ID)','WHERE C_Invoice.IsSOTrx=''Y'' AND C_Invoice.Processed=''Y''','C_Invoice.DateInvoiced','C_Invoice.AD_Org_ID','C_Invoice.C_BPartner_ID','il.M_Product_ID',318,'C_Invoice.C_Invoice_ID','D');\n")
				.append(
						"INSERT INTO PA_MEASURECALC (PA_MEASURECALC_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,SELECTCLAUSE,WHERECLAUSE,DATECOLUMN,ORGCOLUMN,BPARTNERCOLUMN,PRODUCTCOLUMN,AD_TABLE_ID,KEYCOLUMN,ENTITYTYPE) values (101,0,0,'Y',to_date('26.04.01','DD.MM.RR'),0,to_date('01.01.06','DD.MM.RR'),100,'Invoiced Gross Revenue','Invoice gross amount including tax; Does not allow selection by product (Category)','SELECT SUM(GrandTotal) \n")
				.append(
						"FROM RV_C_Invoice C_Invoice','WHERE IsSOTrx=''Y'' AND Processed=''Y''','DateInvoiced','AD_Org_ID','C_BPartner_ID',null,318,'C_Invoice_ID','D');\n")
				.append(
						"INSERT INTO PA_MEASURECALC (PA_MEASURECALC_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,SELECTCLAUSE,WHERECLAUSE,DATECOLUMN,ORGCOLUMN,BPARTNERCOLUMN,PRODUCTCOLUMN,AD_TABLE_ID,KEYCOLUMN,ENTITYTYPE) values (102,0,0,'Y',to_date('26.04.01','DD.MM.RR'),0,to_date('01.01.06','DD.MM.RR'),100,'Invoiced Sales Margin','Difference between Limit and Actual price','SELECT SUM((il.PriceActual-il.PriceLimit)*QtyInvoiced) \n")
				.append("FROM RV_C_Invoice C_Invoice\n")
				.append(
						"  INNER JOIN RV_C_InvoiceLine il ON (C_Invoice.C_Invoice_ID=il.C_Invoice_ID)','WHERE C_Invoice.IsSOTrx=''Y'' AND C_Invoice.Processed=''Y''','C_Invoice.DateInvoiced','C_Invoice.AD_Org_ID','C_Invoice.C_BPartner_ID','il.M_Product_ID',318,'C_Invoice.C_Invoice_ID','D');\n")
				.append(
						"INSERT INTO PA_MEASURECALC (PA_MEASURECALC_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,SELECTCLAUSE,WHERECLAUSE,DATECOLUMN,ORGCOLUMN,BPARTNERCOLUMN,PRODUCTCOLUMN,AD_TABLE_ID,KEYCOLUMN,ENTITYTYPE) values (103,0,0,'Y',to_date('26.04.01','DD.MM.RR'),0,to_date('25.12.05','DD.MM.RR'),100,'Number of Customers','Number of (new) customers','SELECT COUNT(*) \n")
				.append(
						"FROM C_BPartner','WHERE IsCustomer=''Y''','Created','AD_Org_ID','C_BPartner_ID',null,291,'C_BPartner_ID','D');\n")
				.append(
						"INSERT INTO PA_MEASURECALC (PA_MEASURECALC_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,SELECTCLAUSE,WHERECLAUSE,DATECOLUMN,ORGCOLUMN,BPARTNERCOLUMN,PRODUCTCOLUMN,AD_TABLE_ID,KEYCOLUMN,ENTITYTYPE) values (104,0,0,'Y',to_date('25.12.05','DD.MM.RR'),100,to_date('01.01.06','DD.MM.RR'),100,'Invoiced Paid Quantities','Invoiced paid quantities','SELECT SUM(il.QtyInvoiced) \n")
				.append("FROM RV_C_Invoice C_Invoice\n")
				.append(
						"  INNER JOIN RV_C_InvoiceLine il ON (C_Invoice.C_Invoice_ID=il.C_Invoice_ID)','WHERE C_Invoice.IsSOTrx=''Y'' AND C_Invoice.Processed=''Y'' AND C_Invoice.IsPaid=''Y''\n")
				.append(
						"','C_Invoice.DateInvoiced','C_Invoice.AD_Org_ID','C_Invoice.C_BPartner_ID','il.M_Product_ID',318,'C_Invoice.C_Invoice_ID','D');\n")
				.append(
						"INSERT INTO PA_MEASURECALC (PA_MEASURECALC_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,SELECTCLAUSE,WHERECLAUSE,DATECOLUMN,ORGCOLUMN,BPARTNERCOLUMN,PRODUCTCOLUMN,AD_TABLE_ID,KEYCOLUMN,ENTITYTYPE) values (105,0,0,'Y',to_date('01.01.06','DD.MM.RR'),100,to_date('01.01.06','DD.MM.RR'),100,'Open Invoice Amount','Open Invoice Amount  in Accounting Currency','SELECT COALESCE(SUM(currencyBase(invoiceOpen(C_Invoice_ID, C_InvoicePaySchedule_ID),C_Currency_ID, DateAcct, AD_Client_ID, AD_Org_ID)),0)\n")
				.append(
						"FROM C_Invoice_v C_Invoice','WHERE IsSOTrx=''Y'' AND Processed=''Y''','DateInvoiced','AD_Org_ID','C_BPartner_ID',null,318,'C_Invoice_ID','D');\n")
				.append("\n")
				.append("\n")
				.append("  ALTER TABLE PA_MEASURE \n")
				.append("  ADD (	PA_BENCHMARK_ID NUMBER(10,0), \n")
				.append("	PA_RATIO_ID NUMBER(10,0), \n")
				.append("	PA_HIERARCHY_ID NUMBER(10,0), \n")
				.append("	MEASUREDATATYPE CHAR(1 BYTE) NOT NULL ENABLE, \n")
				.append("	R_REQUESTTYPE_ID NUMBER(10,0), \n")
				.append("	C_PROJECTTYPE_ID NUMBER(10,0)\n")
				.append("   ) ;\n")
				.append("   \n")
				.append(" ALTER TABLE  PA_MEASURE\n")
				.append(" ADD CONSTRAINT  PABENCHMARK_PAMEASURE FOREIGN KEY (PA_BENCHMARK_ID)\n")
				.append("   	  REFERENCES PA_BENCHMARK (PA_BENCHMARK_ID); \n")
				.append(" ALTER TABLE  PA_MEASURE\n")
				.append(" ADD CONSTRAINT PAHIERARCHY_PAMEASURE FOREIGN KEY (PA_HIERARCHY_ID)\n")
				.append("   	  REFERENCES PA_HIERARCHY (PA_HIERARCHY_ID);  \n")
				.append(" ALTER TABLE  PA_MEASURE\n")
				.append(" ADD CONSTRAINT PARATIO_PAMEASURE FOREIGN KEY (PA_RATIO_ID)\n")
				.append("	  REFERENCES PA_RATIO (PA_RATIO_ID);\n")
				.append("   \n")
				.append("--  INSERTING into PA_MEASURE \n")
				.append(
						"INSERT INTO PA_MEASURE (PA_MEASURE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,MEASURETYPE,MANUALACTUAL,MANUALNOTE,CALCULATIONCLASS,PA_MEASURECALC_ID,PA_BENCHMARK_ID,PA_RATIO_ID,PA_HIERARCHY_ID,MEASUREDATATYPE,R_REQUESTTYPE_ID,C_PROJECTTYPE_ID) values (102,11,0,'Y',to_date('01.01.06','DD.MM.RR'),100,to_date('01.01.06','DD.MM.RR'),100,'Open Invoice Amount',null,'C',0,null,null,105,null,null,null,'S',null,null);\n")
				.append(
						"INSERT INTO PA_MEASURE (PA_MEASURE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,MEASURETYPE,MANUALACTUAL,MANUALNOTE,CALCULATIONCLASS,PA_MEASURECALC_ID,PA_BENCHMARK_ID,PA_RATIO_ID,PA_HIERARCHY_ID,MEASUREDATATYPE,R_REQUESTTYPE_ID,C_PROJECTTYPE_ID) values (103,11,0,'Y',to_date('20.01.06','DD.MM.RR'),100,to_date('20.01.06','DD.MM.RR'),100,'Service Requests (Time)',null,'Q',0,null,null,null,null,null,null,'T',101,null);\n")
				.append(
						"INSERT INTO PA_MEASURE (PA_MEASURE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,MEASURETYPE,MANUALACTUAL,MANUALNOTE,CALCULATIONCLASS,PA_MEASURECALC_ID,PA_BENCHMARK_ID,PA_RATIO_ID,PA_HIERARCHY_ID,MEASUREDATATYPE,R_REQUESTTYPE_ID,C_PROJECTTYPE_ID) values (104,11,0,'Y',to_date('20.01.06','DD.MM.RR'),100,to_date('20.01.06','DD.MM.RR'),100,'Service Requests (Status)',null,'Q',0,null,null,null,null,null,null,'S',101,null);\n")
				.append(
						"INSERT INTO PA_MEASURE (PA_MEASURE_ID,AD_CLIENT_ID,AD_ORG_ID,ISACTIVE,CREATED,CREATEDBY,UPDATED,UPDATEDBY,NAME,DESCRIPTION,MEASURETYPE,MANUALACTUAL,MANUALNOTE,CALCULATIONCLASS,PA_MEASURECALC_ID,PA_BENCHMARK_ID,PA_RATIO_ID,PA_HIERARCHY_ID,MEASUREDATATYPE,R_REQUESTTYPE_ID,C_PROJECTTYPE_ID) values (101,11,0,'Y',to_date('25.12.05','DD.MM.RR'),100,to_date('26.12.05','DD.MM.RR'),101,'Invoices Gross Revenue',null,'C',0,null,null,101,null,null,null,'T',null,null);\n")
				.append("\n");

		return buffer.toString();
	}

	/**
	 * Returns the update statement to set the db version to the one needed for
	 * 253b. You need to change this if the target db is not 253b.
	 * 
	 * @return
	 */
	private String getUpdateVersionStatement() {
		return "UPDATE AD_SYSTEM SET VERSION='2006-01-20';";
	}

	/**
	 * Erzeugt eine Connection zur Auftrags-Datenbank.
	 * 
	 * @return Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection(String dbName, String username, String password, String url)
			throws ClassNotFoundException, SQLException {

		Connection connection;
		// Load the JDBC driver
		String driverName = "oracle.jdbc.OracleDriver";
		Class.forName(driverName);

		url = url + dbName;

		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	/**
	 * Comparator for insert statements. Sorts AD_* entries.
	 * 
	 * @author Karsten Thiemann, kt@schaeffer-ag.de
	 * @deprecated
	 */
	private class AD_Comparator implements Comparator {

		String[] reihenfolge = new String[] { "AD_ELEMENT(", "AD_WINDOW(", "AD_WINDOW_ACCESS(",
				"AD_TABLE(", "AD_REFERENCE(", "AD_VAL_RULE(", "AD_REPORTVIEW(", "AD_PROCESS(",
				"AD_PROCESS_ACCESS(", "AD_PROCESS_PARA(", "AD_PINSTANCE(", "AD_PINSTANCE_PARA(",
				"AD_COLUMN(", "AD_TAB(", "AD_FIELD(", "AD_FORM(", "AD_FORM_ACCESS(", "AD_MENU(",
				"AD_PRINTFORMAT(", "AD_PRINTFORMATITEM(", "AD_MESSAGE(", "AD_REF_LIST(",
				"AD_TREE(", "AD_TREENODE(", "AD_TREENODEMM(", };

		public int compare(Object arg1, Object arg2) {
			if (!(arg1 instanceof String && arg2 instanceof String)) {
				return 0;
			}
			final String str1 = (String) arg1;
			final String str2 = (String) arg2;
			int prio1 = 100;
			int prio2 = 100;
			for (int i = 0; i < reihenfolge.length; i++) {
				if (str1.startsWith("INSERT INTO " + reihenfolge[i])) {
					prio1 = i;
					break;
				}
			}
			for (int i = 0; i < reihenfolge.length; i++) {
				if (str2.startsWith("INSERT INTO " + reihenfolge[i])) {
					prio2 = i;
					break;
				}
			}
			if (prio1 != prio2) {
				if (prio1 < prio2) {
					return -1;
				} else {
					return 1;
				}
			} else
				return str1.compareTo(str2);

		}

	}
}
