/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
*/

package oracle;

import java.util.HashMap;

/**
 * Holds all data as Strings.
 * @author Karsten Thiemann, kt@schaeffer-ag.de
 *
 */
public class ADDataElement {
	
	public static final String COLUMNNAME_NOT_FOUND ="COLUMNNAME_NOT_FOUND";
	
	private HashMap<String, String> values = new HashMap<String, String>();
	private HashMap<String, String> types = new HashMap<String, String>();
	
	
	
	public void addColumnAndValue(String columnName, String columnValue){
		values.put(columnName, columnValue);
	}
	
	
	public String getValueForColumn(String columnName){
		if(!values.containsKey(columnName)){
			return COLUMNNAME_NOT_FOUND;
		}
		return values.get(columnName);
	}
	
	public void setValueForColumn(String columnName, String columnValue){
		if(!values.containsKey(columnName)){
			return;
		}
		values.remove(columnName);
		addColumnAndValue(columnName, columnValue);
	}

}
