/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Teo Sarca.                                            *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Teo Sarca (teo.sarca@gmail.com)                                   *
*                                                                     *
* Sponsors:                                                           *
* - SC ARHIPAC SERVICE SRL (http://www.arhipac.ro)                    *
***********************************************************************/
package org.compiere.grid;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.compiere.model.GridField;
import org.compiere.model.GridTable;
import org.compiere.model.Lookup;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * VTableExcelAdapter enables Copy Clipboard functionality on VTables.
 *
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL - FR [ 1753943 ]
 */
public class VTableExcelAdapter
	implements ActionListener
{
	public static final String CMD_Copy = "VTable.copyPaste"; 
	public static final String CMD_CopyWithHeaders = "VTable.copyPasteH";

	private static KeyStroke KS_copy = KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK,false);
	private static KeyStroke KS_copyWithHeader = KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK,false);

	/** Logger */
	private CLogger log = CLogger.getCLogger(getClass());

	/** System clipboard */
	private Clipboard system;
	/** Source table */
	private VTable table;
	
	/** System locale */
	private static Locale sysLocale = new Locale(
			System.getProperty("user.language"), 
			System.getProperty("user.country")
	);
	/** System number formater */
	private static NumberFormat sysNumberFormat = NumberFormat.getNumberInstance(sysLocale);
	/** System date formater */
	private static DateFormat sysDateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, sysLocale);

	/**
	 * The Excel Adapter is constructed with a
	 * VTable on which it enables Copy-Paste and acts
	 * as a Clipboard listener.
	 * 
	 * @param table
	 */
	public VTableExcelAdapter(VTable table)
	{
		this.table = table;
		table.registerKeyboardAction(this,CMD_Copy,KS_copy,JComponent.WHEN_FOCUSED);
		table.registerKeyboardAction(this,CMD_CopyWithHeaders,KS_copyWithHeader,JComponent.WHEN_FOCUSED);
		system = Toolkit.getDefaultToolkit().getSystemClipboard();
	}

	/**
	 * This method is activated on the Keystrokes we are listening to
	 * in this implementation. Here it listens for Copy and Paste ActionCommands.
	 * 
	 * @param e event 
	 */
	public void actionPerformed(ActionEvent e)
	{
		// Only GridTable model is supported
		if (!(table.getModel() instanceof GridTable)) {
			if(CLogMgt.isLevelFine()) log.fine("Not supported - " + table.getModel());
			return;
		}

		boolean isCopy = CMD_Copy.equals(e.getActionCommand()); 
		boolean isCopyWithHeaders = CMD_CopyWithHeaders.equals(e.getActionCommand()); 

		if (isCopy || isCopyWithHeaders)
		{
			try {
				int[] selectedRows = table.getSelectedRows();
				if (selectedRows == null || selectedRows.length == 0) {
					return;
				}

				int colscount = table.getColumnCount();
				StringBuffer sb = new StringBuffer();
				GridTable model = (GridTable)table.getModel();
				GridField[] fields = model.getFields();

				// Header
				if (isCopyWithHeaders) {
					for (int col = 0; col < colscount; col++) {
						String value = "";
						try {
							GridField field = fields[col];
							if (!field.isDisplayed(false)) {
								continue;
							}
							value = field.getHeader();
						} catch(Exception ex) {
							log.log(Level.WARNING, "Copy-headers", ex);
						}
						value = fixString(value);
						sb.append(value).append("\t");
					}
					sb.append(Env.NL);
				}

				// Selected rows
				for (int row : selectedRows) {
					for (int col = 0; col < colscount; col++) {
						Lookup lookup = null;
						String value = null;
						Object key = null;
						GridField field = null;
						try {
							key = table.getValueAt(row,col);
							field = fields[col];
							if (!field.isDisplayed(false))
								continue;
							if (field.isEncryptedColumn() || field.isEncryptedField()) {
								value = "*";
							}
							else if (key instanceof Boolean) {
								value = Msg.getMsg(Env.getCtx(), ((Boolean)key).booleanValue() ? "Yes":"No");
							}
							else if (key instanceof BigDecimal) {
								try {
									value = sysNumberFormat.format(key != null ? key : Env.ZERO);
								} catch (Exception ex) {}
							}
							else if (key instanceof Date) {
								try {
									value = sysDateFormat.format(key);
								} catch (Exception ex) {}
							}
							else {
								lookup = (field != null ? field.getLookup() : null);
								value = (lookup != null && key != null ? lookup.getDisplay(key) : null);
								if (value == null && key != null)
									value = key.toString();
							}
						} catch(Exception ex) {
							log.log(Level.WARNING, "Copy-rows", ex);
						}
						value = fixString(value);
						sb.append(value).append("\t");
						if(CLogMgt.isLevelFinest()) log.finest("col=" + col + ", row=" + row + ": key=" + key + " => value=" + value + ", " + field + ", " + lookup);
					}
					sb.append(Env.NL);
				}
				StringSelection stsel  = new StringSelection(sb.toString());
				system = Toolkit.getDefaultToolkit().getSystemClipboard();
				system.setContents(stsel,stsel);
			} catch (Exception ex) {
				log.log(Level.WARNING, "Copy", ex);
			}
		}
	} // actionPerformed

	/**
	 * Fix Cell String
	 * @param s string
	 * @return fixed string 
	 */
	private String fixString(String s) {
		if (s == null || s.length() == 0)
			return "";
		String s2 = s.replaceAll("[\t\n\f\r]+", " ");
		return s2;
	}
}
