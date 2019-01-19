/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Contributor(s): carlosaparada@gmail.com 							  		  *
 *****************************************************************************/

package org.eevolution.grid;

import org.adempiere.model.MBrowseField;
import org.compiere.model.GridField;
import org.compiere.model.MRule;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.script.ScriptEngine;

/**
 *   @author carlosaparada@gmail.com Carlos Parada, ERP Consultores y asociados
 *   @author victor.perez@www.e-evolution.com, e-Evolution
 *   @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 268 ] Smart Browse Table don't have a MVC
 * 		@see https://github.com/adempiere/adempiere/issues/268
 */
public class BrowserRow {

	/**
     * Logger.
     */
    private static CLogger log = CLogger.getCLogger(BrowserRow.class);
	/**	No View Columns				*/
	private Integer noViewColumns = 0;
	/**	Rows 						*/
	private LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> rows;
	/**	Fields (Columns)			*/
	private LinkedHashMap<Integer, MBrowseField> browserFields;
	/**	Fields Index (Columns)		*/
	private LinkedHashMap<Integer, Integer> fieldIndexes;
	/**	Display Index				*/
	private LinkedHashMap<Integer, Integer> displayIndex;
	/**	Columns Name Index			*/
	private LinkedHashMap<String, Integer> columnNamesIndex;
	/**	Table						*/
	private IBrowserTable m_Table;
	/**	Current Row					*/
	private int	m_CurrentRow = 0;	
	
    /**	Active BrowseCallOuts		*/
    private List<String> activeCallOuts = new ArrayList<String>();
    /**	Active BrowseCallOutsInstances*/
    private List<BrowserCallOut> activeCallOutInstance = new ArrayList<BrowserCallOut>();

	/**
	 * Build With table
	 * *** Build Of Class ***
	 * @param table
	 */
	public BrowserRow(IBrowserTable table) {
		m_Table = table;
		rows = new LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>>();
		browserFields = new LinkedHashMap<Integer, MBrowseField>();
		fieldIndexes = new LinkedHashMap<Integer, Integer>();
		displayIndex = new LinkedHashMap<Integer, Integer>();
		columnNamesIndex = new LinkedHashMap<String, Integer>();
	}

	/**
	 * Add Column
	 * @param field
	 * @return void
	 */
	public void addBrowserField(MBrowseField field, int column)
	{
		if (field.isDisplayed()){
			displayIndex.put(column, noViewColumns);
			fieldIndexes.put(noViewColumns, column);
			noViewColumns++;
		}
		columnNamesIndex.put(field.getAD_View_Column().getColumnName(), column);
		browserFields.put(column, field);
	}

	/**
	 * Get BrowseField Column
	 * @param col
	 * @return
	 * @return MBrowseField
	 */
	public MBrowseField getBrowserField(int col)
	{
		return browserFields.get(col);
	}
	
	/**
	 * Set Value to Browse Rows
	 * @param row
	 * @param col
	 * @return void
	 */
	public void setValue(int row , int col, GridField gridField)
	{
		LinkedHashMap<Integer, GridField> values = rows.get(row);
		if (values == null)
			values = new LinkedHashMap<Integer, GridField>();

		values.put(col , gridField);
		rows.put(row , values);
	}

	/**
	 * Get Value From BrowseRows
	 * @param row
	 * @param col
	 * @return
	 * @return Object
	 */
	public GridField getValue(int row , int col)
	{
		if (rows.size() > row)
		{
			LinkedHashMap<Integer, GridField> values = rows.get(row);
			return values.get(col);
		}
		return null;
	}

	/**
	 * Heads
	 * @return
	 * @return LinkedHashMap<Integer,MBrowseField>
	 */
	public LinkedHashMap<Integer, MBrowseField> getBrowserFields() {
		return browserFields;
	}

	/**
	 * Rows
	 * @return
	 * @return LinkedHashMap<Integer,LinkedHashMap<Integer,Object>>
	 */
	public LinkedHashMap<Integer, LinkedHashMap<Integer, GridField>> getRows() {
		return rows;
	}

	/**
	 * Qty Rows
	 * @return
	 * @return int
	 */
	public int size()
	{
		return rows.size();
	}

	/**
	 * Clear Rows
	 * @return void
	 */
	public void clear()
	{
		rows.clear();
	}

	/**
	 * Get Number of Columns
	 * @return
	 * @return int
	 */
	public int getColumnCount()
	{
		return browserFields.size();
	}


	/**
	 * Returns Qty Columns Displayed
	 * @return
	 * @return Integer
	 */
	public Integer getNoViewColumns() {
		return noViewColumns;
	}

	/**
	 * get Column Index From Table
	 * @param index
	 * @return
	 * @return int
	 */
	public int getDisplayIndex(int index) {

		return displayIndex.get(index);
	}

	/**
	 * Get Column Index From Browse Fields
	 * @param display
	 * @return
	 * @return int
	 */
	public int getTableIndex(int display) {
		return fieldIndexes.get(display);
	}

	/**
	 * get Table
	 * @return
	 * @return BrowseTable
	 */
	public IBrowserTable getTable() {
		return m_Table;
	}

	/**
	 * set Table
	 * @param table 
	 * @return void
	 */
	public void setTable(IBrowserTable table) {
		this.m_Table = table;
	}

	/**
	 * Get Selected Row
	 * @return
	 * @return int
	 */
	public int getSelectedRow()
	{
		if (m_Table==null)
			return -1;
		else
			return m_Table.getSelectedRow();
	}

	/**
	 * Get Selected Row
	 * @return
	 * @return int
	 */
	public int getSelectedColumn()
	{
		if (m_Table==null)
			return -1;
		else
			return m_Table.getSelectedColumn();
	}

	/**
	 * Get Value of Selected Cell
	 * @return
	 * @return Object
	 */
	public Object getValueOfSelectedCell()
	{
		if (m_Table!=null)
			return getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
		else
			return null;
	}

	/**
	 *
	 * @param gridField
	 */
	public void setValueOfSelectedCell(GridField gridField)
	{
		if (m_Table != null){
			//GridField gridField = getValue(getSelectedRow(), getTableIndex(getSelectedColumn()));
			if (gridField != null){
				m_Table.setValueAt(getSelectedRow(), getSelectedColumn(), gridField);
			}
		}
	}


	/**
	 * Get Object GridField from Column Index
	 * @param col
	 * @return
	 * @return Object
	 */
	public Object getValueOfColumn(int col)
	{
		if(m_Table!=null)
			return getValue(getSelectedRow(), col);
		else
			return null;
	}

	/**
	 *
	 * @param row
	 * @param ColumnName
	 * @param gridField
	 */
	public void setValueOfColumn(int row, String ColumnName, GridField gridField)
	{
		if (m_Table != null){
			if (columnNamesIndex.get(ColumnName) != null) {
				//GridField gridField = getValue(row, columnNamesIndex.get(ColumnName));
				if (gridField != null){
					m_Table.setValueAt(row, getDisplayIndex(columnNamesIndex.get(ColumnName)), gridField);
				}
			}

		}
	}
	
	/**
	 * BR[ 268 ]
	 * Set value to a column and row
	 * @param p_Row
	 * @param p_ColumnName
	 * @param p_Value
	 */
	public void setValue(int p_Row, String p_ColumnName, Object p_Value) {
		//	Valid Table
		if (m_Table == null)
			return;
		Integer columnIndex = columnNamesIndex.get(p_ColumnName);
		//	Valid Index
		if(columnIndex == null)
			return;
		//	Get current Value
		GridField gridField = getValue(p_Row, columnIndex);
		gridField.setValue(p_Value, true);
		if(gridField.isDisplayed()) {
			m_Table.setValueAt(p_Row, getDisplayIndex(columnIndex), gridField);
		} else {
			setValue(p_Row, columnIndex, gridField);
		}
	}
	
	/**
	 * BR[ 268 ]
	 * Set value to a column to current row
	 * @param p_ColumnName
	 * @param p_Value
	 */
	public void setValue(String p_ColumnName, Object p_Value) {
		setValue(getCurrentRow(), p_ColumnName, p_Value);
	}

	/**
	 * BR[ 268 ]
	 * Get Value from a column name in a row
	 * @param p_Row
	 * @param p_ColumnName
	 * @return
	 */
	public Object getValue(int p_Row, String p_ColumnName) {
		Integer columnIndex = columnNamesIndex.get(p_ColumnName);
		//	Valid Index
		if(columnIndex == null)
			return null;
		//	Get current Value
		GridField gridField = getValue(p_Row, columnIndex);
		if (gridField == null)
			return null;
		//	Return value of GridField
		return gridField.getValue();
	}
	
	/**
	 * BR[ 268 ]
	 * Get Value from a column name in the current row
	 * @param p_ColumnName
	 * @return
	 */
	public Object getValue(String p_ColumnName) {
		return getValue(getCurrentRow(), p_ColumnName);
	}
	
	/**
	 *
	 * @param row
	 * @param ColumnName
	 * @return
	 */
	public Object getValueOfColumn(int row, String ColumnName)
	{
		int index;
		if(m_Table != null){
			index = (columnNamesIndex.get(ColumnName) == null ? -1: columnNamesIndex.get(ColumnName));
			if (index >= 0)
				return getValue(row, index);
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int i=0; i < rows.size() ; i++)
			result.append(rows.get(i) + "\n");
		return result.toString();
	}
	
    /**
     * ***********************************************************************
     * Adapted for Browse CallOuts
     * Process Callout(s) Adapted.
     * <p/>
     * The Callout is in the string of
     * "class.method;class.method;"
     * If there is no class name, i.e. only a method name, the class is regarded
     * as CalloutSystem.
     * The class needs to comply with the Interface Callout.
     * <p/>
     * For a limited time, the old notation of Sx_matheod / Ux_menthod is maintained.
     *
     * @param field field
     * @return error message or ""
     * @see org.compiere.model.Callout
     */
    public String processCallOut(Properties ctx, int WindowNo, GridField field, Object value, Object oldValue, int currentRow, int currentColumn) {
        //	Set current Row
    	setCurrentRow(currentRow);
    	//	Valid Callout
    	String callout = field.getCallout();
        if (callout.length() == 0)
            return "";


        //Object value = field.getValue();
        //Object oldValue = field.getOldValue();
        log.fine(field.getColumnName() + "=" + value
                + " (" + callout + ") - old=" + oldValue);

        StringTokenizer st = new StringTokenizer(callout, ";,", false);
        while (st.hasMoreTokens())      //  for each callout
        {
            String cmd = st.nextToken().trim();

            //detect infinite loop
            if (activeCallOuts.contains(cmd)) continue;

            String retValue = "";
            // FR [1877902]
            // CarlosRuiz - globalqss - implement beanshell callout
            // Victor Perez  - vpj-cd implement JSR 223 Scripting
            if (cmd.toLowerCase().startsWith(MRule.SCRIPT_PREFIX)) {

                MRule rule = MRule.get(ctx, cmd.substring(MRule.SCRIPT_PREFIX.length()));
                if (rule == null) {
                    retValue = "Callout " + cmd + " not found";
                    log.log(Level.SEVERE, retValue);
                    return retValue;
                }
                if (!(rule.getEventType().equals(MRule.EVENTTYPE_Callout)
                        && rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs))) {
                    retValue = "Callout " + cmd
                            + " must be of type JSR 223 and event Callout";
                    log.log(Level.SEVERE, retValue);
                    return retValue;
                }

                ScriptEngine engine = rule.getScriptEngine();

                // Window context are    W_
                // Login context  are    G_
                MRule.setContext(engine, ctx, WindowNo);
                // now add the callout parameters windowNo, tab, field, value, oldValue to the engine
                // Method arguments context are A_
                engine.put(MRule.ARGUMENTS_PREFIX + "WindowNo", WindowNo);
                engine.put(MRule.ARGUMENTS_PREFIX + "Tab", this);
                engine.put(MRule.ARGUMENTS_PREFIX + "Field", field);
                engine.put(MRule.ARGUMENTS_PREFIX + "Value", value);
                engine.put(MRule.ARGUMENTS_PREFIX + "OldValue", oldValue);
                engine.put(MRule.ARGUMENTS_PREFIX + "currentRow", currentRow);
                engine.put(MRule.ARGUMENTS_PREFIX + "currentColumn", currentColumn);
                engine.put(MRule.ARGUMENTS_PREFIX + "Ctx", ctx);

                try {
                    activeCallOuts.add(cmd);
                    retValue = engine.eval(rule.getScript()).toString();
                } catch (Exception e) {
                    log.log(Level.SEVERE, "", e);
                    retValue = "Callout Invalid: " + e.toString();
                    return retValue;
                } finally {
                    activeCallOuts.remove(cmd);
                }

            } else {

                BrowserCallOut call = null;
                String method = null;
                int methodStart = cmd.lastIndexOf('.');
                try {
                    if (methodStart != -1)      //  no class
                    {
                        Class<?> cClass = Class.forName(cmd.substring(0, methodStart));
                        call = (BrowserCallOut) cClass.newInstance();
                        method = cmd.substring(methodStart + 1);
                    }
                } catch (Exception e) {
                    log.log(Level.SEVERE, "class", e);
                    return "Callout Invalid: " + cmd + " (" + e.toString() + ")";
                }

                if (call == null || method == null || method.length() == 0)
                    return "Callout Invalid: " + method;

                try {
                    activeCallOuts.add(cmd);
                    activeCallOutInstance.add(call);
                    retValue = call.start(ctx, method, WindowNo, this, field, value, oldValue, currentRow, currentColumn);
                } catch (Exception e) {
                    log.log(Level.SEVERE, "start", e);
                    retValue = "Callout Invalid: " + e.toString();
                    return retValue;
                } finally {
                    activeCallOuts.remove(cmd);
                    activeCallOutInstance.remove(call);
                }

            }

            if (!Util.isEmpty(retValue))        //	interrupt on first error
            {
                log.severe(retValue);
                return retValue;
            }
        }   //  for each callout
        return "";
    }    //	processCallOut
    
    /**
     * Set Current Row for Callout
     * @param p_CurrentRow
     */
    public void setCurrentRow(int p_CurrentRow) {
    	m_CurrentRow = p_CurrentRow;
    	log.fine("Row=" + m_CurrentRow);
		if(m_CurrentRow < 0)
			return;
		//  Update Field Values
		int size = getColumnCount();
		for (int i = 0; i < size; i++) {
			GridField mField = getValue(m_CurrentRow, i);
			if(mField != null) {
				mField.updateContext();
			}
		}
    }
    
    /**
     * Get Current Row
     * @return
     */
    public int getCurrentRow() {
    	return m_CurrentRow;
    }
}
