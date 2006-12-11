/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
 */

package oracle;

import java.util.Vector;

public class Constraint {

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            constraint name
	 * @param tableName
	 */
	public Constraint(String name, String tableName) {
		this.name = name;
		this.tableName = tableName;
	}

	public static final String PRIMARY_KEY = "P";

	public static final String FOREIGN_KEY = "R";

	public static final String CHECK = "C";

	public static final String UNIQEINDEX = "U"; // TODO: implement...

	/** constraint name */
	private String name;

	/** constraint type */
	private String type;

	/** table (to alter) */
	private String tableName;

	/** condition of check constraints */
	private String checkCondition;

	/** index name of unique index * */
	private String indexName;

	/** name of primary/foreign key column */
	// private String columnName;
	private Vector<String> columnNames = new Vector<String>();

	/**
	 * needed for foreign keys - the primary key constraint of the referenced
	 * table
	 */
	private Constraint rConstraint;

	/** on delete clause */
	private String deleteRule;

	/**
	 * Returns an sql statement that adds the constrain to its table.
	 * 
	 * @return alter table statement
	 */
	public String getAlterTableString() {
		StringBuffer buffer = new StringBuffer();

		buffer = buffer.append("ALTER TABLE ").append(tableName);
		buffer = buffer.append(" ADD ");

		if (type.equals(PRIMARY_KEY)) {
			buffer = buffer.append("CONSTRAINT ");
			// TODO more than one key column
			buffer = buffer.append(name).append(" PRIMARY KEY (");
			for(int i=0; i<columnNames.size(); i++){
				buffer = buffer.append(columnNames.get(i));
				if(i!=columnNames.size()-1){
					buffer = buffer.append(", ");
				}
			}
			buffer = buffer.append(")");
		} else if (type.equals(FOREIGN_KEY)) {
			buffer = buffer.append("CONSTRAINT ");
			buffer = buffer.append(name).append(" FOREIGN KEY (");
			buffer = buffer.append(columnNames.get(0)).append(")");
			buffer = buffer.append(" REFERENCES ").append(rConstraint.getTableName());
			buffer = buffer.append(" (").append(rConstraint.getColumnName()).append(")");
			if (deleteRule != null) {
				buffer.append(" ON DELETE " + deleteRule + " ");
			}
		} else if (type.equals(CHECK)) {
			buffer = buffer.append("CHECK (").append(checkCondition).append(")");
		} else if (type.equals(UNIQEINDEX)) {
			// TODO implementation
		}

		buffer = buffer.append(";");
		return buffer.toString();
	}

	private String getColumnName() {
		if (columnNames.size() > 0) {
			return columnNames.get(0);
		} else
			return null;
	}

	/**
	 * Returns a drop constraint statement.
	 * 
	 * @return
	 */
	public String getDropString() {
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append("ALTER TABLE ").append(tableName);
		buffer = buffer.append(" DROP CONSTRAINT ").append(name).append(";");
		return buffer.toString();
	}

	public String getCheckCondition() {
		return checkCondition;
	}

	public void setCheckCondition(String checkCondition) {
		this.checkCondition = checkCondition;
	}

	public void addColumnName(String columnName) {
		columnNames.add(columnName);
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Constraint getRConstraint() {
		return rConstraint;
	}

	public void setRConstraint(Constraint constraint) {
		rConstraint = constraint;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDeleteRule(String deleteRule) {
		this.deleteRule = deleteRule;
		if(deleteRule!=null && deleteRule.trim().toUpperCase().equals("NO ACTION")){
			this.deleteRule = null;
		}

	}

}
