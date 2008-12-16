/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
 */

package oracle;

import java.util.Vector;

public class Table {

	/** all columns of the table */
	private Vector<Column> allTableColumns = new Vector<Column>();

	/** missing columns of the table in comparisment with the other db */
	private Vector<Column> columnsToAdd = new Vector<Column>();

	/** changed columns of the table in comparisment with the other db */
	private Vector<Column> columnsToModify = new Vector<Column>();

	/** columns of the table deleted (missing) in db2 */
	private Vector<Column> columnsToDrop = new Vector<Column>();

	/** table name */
	private String name;

	//
	public static final String STATEMENT_SEPARATOR = "<STATEMENT_SEPARATOR>\n";

	/**
	 * Constructor.
	 * 
	 * @param name
	 */
	public Table(String name) {
		this.name = name;
	}

	/**
	 * Returns a create table statement.
	 * 
	 * @return create table statement
	 */
	public String getCreateStatement() {
		StringBuffer insert = new StringBuffer();
		insert = insert.append("CREATE TABLE ").append(name).append("\n");
		insert = insert.append("(").append("\n");
		for (int i = 0; i < allTableColumns.size(); i++) {
			insert = insert.append(allTableColumns.get(i).getDefinitionStringForCreateTable());
			if (i != allTableColumns.size() - 1) {
				insert = insert.append(",\n");
			}
		}
		insert = insert.append("\n").append(");\n");
		return insert.toString();
	}

	/**
	 * Returns a alter (modify) table statement.
	 * 
	 * @return alter table statement
	 */
	public String getAlterModifyStatement() {

		StringBuffer alter = new StringBuffer();
		String prefix = "ALTER TABLE " + name + " MODIFY (\n";
		String postfix = "\n);\n";

		for (int i = 0; i < columnsToModify.size(); i++) {
			Column column = columnsToModify.get(i);
			if (column.isNullHasChanged()) {
				if (column.isNullable()) {
					// switched from not null to null - may be problem with pk
					alter = alter.append(prefix).append(column.getDefinitionString()).append(
							postfix).append(STATEMENT_SEPARATOR);
				} else {
					// switched to not null - default value needed for existing
					// data!
					String defaultValue = null;
					boolean isTempDefault = false;
					if (column.getDefaultValue() != null && !"".equals(column.getDefaultValue())) {
						defaultValue = column.getDefaultValue();
					} else {
						// use temp default and reset default after that
						defaultValue = column.getTmpDefaultValue();
						isTempDefault = true;
					}
					// ok now we have a default value
					// but we may have to change existing null values
					if (isTempDefault) {
						alter = alter.append("-- TEMP VALUE - REPLACE_ME: " + defaultValue + "\n");
					}
					alter = alter.append("UPDATE ").append(name).append(" SET ").append(
							column.getColumnName()).append("=").append(defaultValue);

					alter = alter.append(" WHERE ").append(column.getColumnName()).append(
							" IS NULL;\n").append(STATEMENT_SEPARATOR);
					alter = alter.append(prefix).append(column.getDefinitionString()).append(
							postfix).append(STATEMENT_SEPARATOR);
				}
			} else if (column.isDefaultHasChanged()) {
				alter = alter.append(prefix).append(column.getSetDefaultString()).append(postfix)
						.append(STATEMENT_SEPARATOR);
			} else {
				// type/precision changed
				alter = alter.append(prefix).append(column.getTypeDefinitionString()).append(
						postfix).append(STATEMENT_SEPARATOR);
			}
		}

		alter = alter.delete(alter.lastIndexOf(STATEMENT_SEPARATOR), alter.length());
		return alter.toString();
	}

	/**
	 * Returns a alter table (drop column) statement.
	 * 
	 * @return alter table statement
	 */
	public String getAlterDropStatement() {
		StringBuffer alter = new StringBuffer();
		alter = alter.append("ALTER TABLE ").append(name).append("\n");
		alter = alter.append("DROP (").append("\n");
		for (int i = 0; i < columnsToDrop.size(); i++) {
			alter = alter.append(columnsToDrop.get(i).getColumnName());
			if (i != columnsToDrop.size() - 1) {
				alter = alter.append(",\n");
			}
		}
		alter = alter.append("\n").append(");\n");
		return alter.toString();
	}

	/**
	 * Returns a alter table (add column) statement.
	 * 
	 * @return alter table statement
	 */
	public String getAlterAddStatement() {
		StringBuffer alter = new StringBuffer();
		String prefixAdd = "ALTER TABLE " + name + " ADD (\n";
		String prefixModify = "ALTER TABLE " + name + " MODIFY (\n";
		String postfix = "\n);\n";

		for (int i = 0; i < columnsToAdd.size(); i++) {
			Column column = columnsToAdd.get(i);
			if (column.isNullable()) {
				// no problem - just add column
				alter = alter.append(prefixAdd).append(column.getDefinitionString())
						.append(postfix).append(STATEMENT_SEPARATOR);
			} else {
				// not null - default value needed for existing data!
				if (column.getDefaultValue() != null && !"".equals(column.getDefaultValue())) {
					// ok we have a default value - no problem
					alter = alter.append(prefixAdd).append(column.getDefinitionString()).append(
							postfix).append(STATEMENT_SEPARATOR);
				} else {
					// use temp default and reset default after that
					alter = alter.append(prefixAdd).append("-- TEMP VALUE - REPLACE_ME: ").append(
							column.getTmpDefaultValue()).append("\n").append(
							column.getDefinitionStringWithTempDefault()).append(postfix).append(
							STATEMENT_SEPARATOR);
					alter = alter.append(prefixModify).append(column.getSetDefaultString()).append(
							postfix).append(STATEMENT_SEPARATOR);
				}
			}
		}
		alter = alter.delete(alter.lastIndexOf(STATEMENT_SEPARATOR), alter.length());
		return alter.toString();
	}

	/**
	 * Returns a drop table statement.
	 * 
	 * @return
	 */
	public String getDropStatement() {
		return "DROP TABLE " + name + ";";
	}

	/**
	 * Adds a columns to the table.
	 * 
	 * @param column
	 */
	public void addColumn(Column column) {
		if (!allTableColumns.contains(column)) {
			allTableColumns.add(column);
		}
	}

	/**
	 * Adds a missing column that will be included in the alter statement.
	 * 
	 * @param column
	 */
	public void addColumnToAdd(Column column) {
		if (!columnsToAdd.contains(column)) {
			columnsToAdd.add(column);
		}
	}

	/**
	 * Adds a column that will be dropped from the table.
	 * 
	 * @param column
	 */
	public void addColumnToDrop(Column column) {
		if (!columnsToDrop.contains(column)) {
			columnsToDrop.add(column);
		}
	}

	/**
	 * Adds a column that has been modified between the two dbs.
	 * 
	 * @param column
	 */
	public void addColumnToModify(Column column) {
		if (!columnsToModify.contains(column)) {
			columnsToModify.add(column);
		}
	}

	/**
	 * True if the table has added columns.
	 * 
	 * @return
	 */
	public boolean isAlterAdd() {
		return columnsToAdd.size() > 0;
	}

	/**
	 * True if the table has columns to remove.
	 * 
	 * @return
	 */
	public boolean isAlterDrop() {
		return columnsToDrop.size() > 0;
	}

	/**
	 * True if the table has changed columns.
	 * 
	 * @return
	 */
	public boolean isAlterModify() {
		return columnsToModify.size() > 0;
	}

}
