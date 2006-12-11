/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
*/

package oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Column {
	private static final int NOT_SET = -1;

	private String tableName;

	/** column name */
	private String columnName;

	/** column type */
	private String dataType;

	/** column can be null */
	private boolean isNullable = false;

	private int dataPrecision = NOT_SET;

	private int dataScale = NOT_SET;

	/** B for byte and C for character */
	private String charUsed;

	/** char length in column declaration */
	private int charColDeclLength = NOT_SET;

	/** columns default value */
	private String defaultValue;

	/** temporary sensible default value for the column */
	private String tmpDefaultValue;

	private boolean isStringType;

	private boolean isNumberType;

	private boolean nullHasChanged = false;

	private boolean defaultHasChanged = false;

	public int getDataPrecision() {
		return dataPrecision;
	}

	public int getDataScale() {
		return dataScale;
	}

	/**
	 * Constructor.
	 * 
	 * @param columnName
	 */
	public Column(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Constructor. The resultset must be set to the correct data with calling
	 * next() before!
	 * 
	 * @param rs
	 *            ResultSet with all values of this column.
	 */
	public Column(ResultSet rs) {
		try {
			if (rs != null) {
				defaultValue = rs.getString("DATA_DEFAULT");
				// default value of NULL is eaqual to default value not set
				if (defaultValue != null) {
					defaultValue = defaultValue.trim();
					if (defaultValue.toUpperCase().equals("NULL")) {
						defaultValue = null;
					}
				}
				tableName = rs.getString("TABLE_NAME");
				columnName = rs.getString("COLUMN_NAME");
				dataType = rs.getString("DATA_TYPE");
				isNumberType = "NUMBER".equals(dataType);
				isStringType = dataType != null && dataType.indexOf("CHAR") != -1;
				isNullable = "Y".equals(rs.getString("NULLABLE"));
				dataPrecision = rs.getInt("DATA_PRECISION");
				dataScale = rs.getInt("DATA_SCALE");
				charUsed = rs.getString("CHAR_USED");
				charColDeclLength = rs.getInt("CHAR_COL_DECL_LENGTH");
			}
		} catch (SQLException e) {
			System.out.println("Column constructor - sql exception");
			System.out.println("Don't use getString at last position...");
			e.printStackTrace();
		}
	}

	/**
	 * Returns a sql sniplet that defines the column.
	 * 
	 * @return
	 */
	public String getDefinitionString() {

		StringBuffer buffer = getTypeDefinitionString();
		if (defaultValue != null && !defaultValue.equals("")) {
			buffer = buffer.append(" DEFAULT ").append(defaultValue);
		} else {
			buffer = buffer.append(" DEFAULT NULL ");
		}

		if (!isNullable) {
			buffer = buffer.append(" NOT NULL ");
		} else {
			buffer = buffer.append(" NULL ");
		}
		return buffer.toString();
	}
	
	/**
	 * Returns a sql sniplet that defines the column.
	 * Without DEFAULT NULL if no default value is set,
	 * because resulting create table statement looks better :)
	 * 
	 * @return
	 */
	public String getDefinitionStringForCreateTable() {

		StringBuffer buffer = getTypeDefinitionString();
		if (defaultValue != null && !defaultValue.equals("")) {
			buffer = buffer.append(" DEFAULT ").append(defaultValue);
		}

		if (!isNullable) {
			buffer = buffer.append(" NOT NULL ");
		} else {
			buffer = buffer.append(" NULL ");
		}
		return buffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof Column) || obj == null) {
			return false;
		}
		Column col = (Column) obj;
		return col.isNullable == isNullable
				&& col.charColDeclLength == charColDeclLength
				&& col.dataPrecision == dataPrecision
				&& col.dataScale == dataScale
				&& ((col.charUsed != null && col.charUsed.equals(charUsed)) || (col.charUsed == null && charUsed == null))
				&& ((col.columnName != null && col.columnName.equals(columnName)) || (col.columnName == null && columnName == null))
				&& ((col.dataType != null && col.dataType.equals(dataType)) || (col.dataType == null && dataType == null))
				&& ((col.defaultValue != null && col.defaultValue.equals(defaultValue)) || (col.defaultValue == null && defaultValue == null))
				&& ((col.tableName != null && col.tableName.equals(tableName)) || (col.tableName == null && tableName == null));
	}

	public String toString() {
		return tableName + " - " + columnName + " - " + charUsed + " - " + charColDeclLength
				+ " - " + dataPrecision + " - " + dataScale + " - " + dataType + " - "
				+ defaultValue + " - " + isNullable;
	}

	public boolean isNullable() {
		return isNullable;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public String getTableName() {
		return tableName;
	}

	public boolean isStringType() {
		return isStringType;
	}

	public boolean isNumberType() {
		return isNumberType;
	}

	public String getTmpDefaultValue() {
		return tmpDefaultValue;
	}

	public void setTmpDefaultValue(String tmpDefaultValue) {
		this.tmpDefaultValue = tmpDefaultValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * Returns a sql snippet with temp default value.
	 * @return
	 */
	public String getDefinitionStringWithTempDefault() {
		StringBuffer buffer = getTypeDefinitionString();
		buffer = buffer.append(" DEFAULT ").append(tmpDefaultValue);
		if (!isNullable) {
			buffer = buffer.append(" NOT NULL ");
		}
		return buffer.toString();
	}

	/**
	 * Returns a sql snippet to set the default value.
	 * @return
	 */
	public String getSetDefaultString() {
		StringBuffer buffer = getTypeDefinitionString();
		if (defaultValue == null) {
			buffer = buffer.append(" DEFAULT NULL ");
		} else {
			buffer = buffer.append(" DEFAULT ").append(defaultValue);
		}
		return buffer.toString();
	}

	/**
	 * Returns a sql snippet with the column type definition
	 * @return
	 */
	public StringBuffer getTypeDefinitionString() {
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append("   ").append(columnName + " " + dataType);
		if (charUsed != null && !charUsed.equals("")) {
			buffer = buffer.append("(").append(charColDeclLength);
			if ("CHAR".equals(dataType)) {
				buffer = buffer.append(" ").append(("C".equals(charUsed)) ? "CHAR" : "BYTE");
			}
			buffer = buffer.append(") ");
		}
		if (dataPrecision != NOT_SET && dataPrecision != 0) {
			buffer = buffer.append("(").append(dataPrecision);
			if (dataScale != NOT_SET) {
				buffer = buffer.append(",").append(dataScale);
			}
			buffer = buffer.append(")");
		}
		return buffer;
	}

	/**
	 * Returns a sql snippet to set column to NULL
	 * @return
	 */
	public String getNullDefinitionString() {
		StringBuffer buffer = getTypeDefinitionString();
		buffer = buffer.append(" NULL ");
		return buffer.toString();
	}

	/**
	 * True if the default value has changed.
	 * @return
	 */
	public boolean isDefaultHasChanged() {
		return defaultHasChanged;
	}

	public void setDefaultHasChanged(boolean defaultHasChanged) {
		this.defaultHasChanged = defaultHasChanged;
	}

	/**
	 * True if the null status has changed.
	 * @return
	 */
	public boolean isNullHasChanged() {
		return nullHasChanged;
	}

	public void setNullHasChanged(boolean nullHasChanged) {
		this.nullHasChanged = nullHasChanged;
	}

}
