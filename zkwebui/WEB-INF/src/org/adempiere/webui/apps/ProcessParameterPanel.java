/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui.apps;



import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.IProcessParameter;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MClient;
import org.compiere.model.MLookup;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * 	@author 	Low Heng Sin
 * 	@author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 		<li>FR [ 3426137 ] Smart Browser
 *  	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 	@version 	2006-12-01
 */
public class ProcessParameterPanel extends Panel 
implements ValueChangeListener, IProcessParameter 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3372945363384709062L;
		private String width;

		/**
		 *	Dynamic generated Parameter panel.
		 *  @param WindowNo window
		 *  @param pi process info
		 */
		public ProcessParameterPanel(int WindowNo, ProcessInfo pi)
		{
			this(WindowNo, pi, "100%");
		}	//	ProcessParameterPanel
		
		/**
		 *	Dynamic generated Parameter panel.
		 *  @param WindowNo window
		 *  @param pi process info
		 */
		public ProcessParameterPanel(int WindowNo, ProcessInfo pi, String width)
		{
			//
			m_WindowNo = WindowNo;
			m_processInfo = pi;
			this.width = width;
			//
			initComponent();
		}	//	ProcessParameterPanel

		private void initComponent() {
			centerPanel = GridFactory.newGridLayout();
			centerPanel.setInnerWidth(width);
			this.appendChild(centerPanel);
			
			//setup columns
	    	Columns columns = new Columns();
	    	centerPanel.appendChild(columns);
	    	Column col = new Column();
	    	col.setWidth("30%");
	    	columns.appendChild(col);
	    	col = new Column();
	    	col.setWidth("65%");
	    	columns.appendChild(col);
	    	col = new Column();
	    	col.setWidth("5%");
	    	columns.appendChild(col);
		}

		private int			m_WindowNo;
		private ProcessInfo m_processInfo;
		/**	Logger			*/
		private static CLogger log = CLogger.getCLogger(ProcessParameterPanel.class);
	
		//
		private ArrayList<WEditor>	m_wEditors = new ArrayList<WEditor>();
		private ArrayList<WEditor>	m_wEditors2 = new ArrayList<WEditor>();		//	for ranges
		private ArrayList<GridField>	m_mFields = new ArrayList<GridField>();
		private ArrayList<GridField>	m_mFields2 = new ArrayList<GridField>();
		private ArrayList<Label> m_separators = new ArrayList<Label>();
		//
		private Grid centerPanel = null;
		
		public static final int DEFAULT_MODE = 1;
		public static final int BROWSER_MODE = 2;
		private int mode=1;
		
		public void setMode(int mode)
		{
			this.mode = mode;
		}

		/**
		 *  Dispose
		 */
		public void dispose()
		{
			m_wEditors.clear();
			m_wEditors2.clear();
			m_mFields.clear();
			m_mFields2.clear();
			
		}   //  dispose

		/**
		 *	Read Fields to display
		 *  @return true if loaded OK
		 */
		public boolean init()
		{
			log.config("");

			// ASP
			MClient client = MClient.get(Env.getCtx());
			String ASPFilter = "";
			if (client.isUseASP())
				ASPFilter =
					  "   AND (   p.AD_Process_Para_ID IN ( "
					// Just ASP subscribed process parameters for client "
					+ "              SELECT pp.AD_Process_Para_ID "
					+ "                FROM ASP_Process_Para pp, ASP_Process p, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE p.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND pp.ASP_Process_ID = p.ASP_Process_ID "
					+ "                 AND pp.IsActive = 'Y' "
					+ "                 AND p.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND pp.ASP_Status = 'S') " // Show
					+ "        OR p.AD_Process_Para_ID IN ( "
					// + show ASP exceptions for client
					+ "              SELECT AD_Process_Para_ID "
					+ "                FROM ASP_ClientException ce "
					+ "               WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND ce.IsActive = 'Y' "
					+ "                 AND ce.AD_Process_Para_ID IS NOT NULL "
					+ "                 AND ce.AD_Tab_ID IS NULL "
					+ "                 AND ce.AD_Field_ID IS NULL "
					+ "                 AND ce.ASP_Status = 'S') " // Show
					+ "       ) "
					+ "   AND p.AD_Process_Para_ID NOT IN ( "
					// minus hide ASP exceptions for client
					+ "          SELECT AD_Process_Para_ID "
					+ "            FROM ASP_ClientException ce "
					+ "           WHERE ce.AD_Client_ID = " + client.getAD_Client_ID()
					+ "             AND ce.IsActive = 'Y' "
					+ "             AND ce.AD_Process_Para_ID IS NOT NULL "
					+ "             AND ce.AD_Tab_ID IS NULL "
					+ "             AND ce.AD_Field_ID IS NULL "
					+ "             AND ce.ASP_Status = 'H')"; // Hide
			//
			String sql = null;
			if (Env.isBaseLanguage(Env.getCtx(), "AD_Process_Para"))
				sql = "SELECT p.Name, p.Description, p.Help, "
					+ "p.AD_Reference_ID, p.AD_Process_Para_ID, "
					+ "p.FieldLength, p.IsMandatory, p.IsRange, p.ColumnName, "
					+ "p.DefaultValue, p.DefaultValue2, p.VFormat, p.ValueMin, p.ValueMax, "
					+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode, "
					+ "p.ReadOnlyLogic, p.DisplayLogic "
					+ "FROM AD_Process_Para p"
					+ " LEFT OUTER JOIN AD_Val_Rule vr ON (p.AD_Val_Rule_ID=vr.AD_Val_Rule_ID) "
					+ "WHERE p.AD_Process_ID=?"		//	1
					+ " AND p.IsActive='Y' "
					+ ASPFilter + " ORDER BY SeqNo";
			else
				sql = "SELECT t.Name, t.Description, t.Help, "
					+ "p.AD_Reference_ID, p.AD_Process_Para_ID, "
					+ "p.FieldLength, p.IsMandatory, p.IsRange, p.ColumnName, "
					+ "p.DefaultValue, p.DefaultValue2, p.VFormat, p.ValueMin, p.ValueMax, "
					+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode, "
					+ "p.ReadOnlyLogic, p.DisplayLogic "
					+ "FROM AD_Process_Para p"
					+ " INNER JOIN AD_Process_Para_Trl t ON (p.AD_Process_Para_ID=t.AD_Process_Para_ID)"
					+ " LEFT OUTER JOIN AD_Val_Rule vr ON (p.AD_Val_Rule_ID=vr.AD_Val_Rule_ID) "
					+ "WHERE p.AD_Process_ID=?"		//	1
					+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
					+ " AND p.IsActive='Y' "
					+ ASPFilter + " ORDER BY SeqNo";

			//	Create Fields
			boolean hasFields = false;
			Rows rows = new Rows();
			try
			{
				PreparedStatement pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, m_processInfo.getAD_Process_ID());
				ResultSet rs = pstmt.executeQuery();
				int field = 0;
				Row row = null;
				while (rs.next())
				{
					hasFields = true;
					field++;
					if(field % 2 ==0 && mode==BROWSER_MODE)
					{
						row = createField (rs, rows, row);
					}
					else
					{
						row = createField (rs, rows, null);
					}
				}
				rs.close();
				pstmt.close();
			}
			catch(SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}

			//	both vectors the same?
			if (m_mFields.size() != m_mFields2.size()
					|| m_mFields.size() != m_wEditors.size()
					|| m_mFields2.size() != m_wEditors2.size())
				log.log(Level.SEVERE, "View & Model vector size is different");

			//	clean up
			if (hasFields)
			{
				centerPanel.appendChild(rows);
				dynamicDisplay();
			}
			else
				dispose();
			return hasFields;
		}	//	initDialog


		/**
		 *	Create Field.
		 *	- creates Fields and adds it to m_mFields list
		 *  - creates Editor and adds it to m_vEditors list
		 *  Handeles Ranges by adding additional mField/vEditor.
		 *  <p>
		 *  mFields are used for default value and mandatory checking;
		 *  vEditors are used to retrieve the value (no data binding)
		 *
		 * @param rs result set
		 */
		private Row createField (ResultSet rs, Rows rows, Row rw)
		{
			//  Create Field
			GridFieldVO voF = GridFieldVO.createParameter(Env.getCtx(), m_WindowNo, rs);
			GridField mField = new GridField (voF);
			m_mFields.add(mField);                      //  add to Fields

			Row row;
			
			if(rw==null)
				row = new Row();
			else
				row = rw;
			
			//	The Editor
			WEditor editor = WebEditorFactory.getEditor(mField, false);
			editor.addValueChangeListener(this);
			editor.dynamicDisplay();
			//  MField => VEditor - New Field value to be updated to editor
			mField.addPropertyChangeListener(editor);
			//  Set Default
			Object defaultObject = mField.getDefault();
			mField.setValue (defaultObject, true);
			//streach component to fill grid cell
			if(mode==DEFAULT_MODE)
				editor.fillHorizontal();
            //setup editor context menu
            WEditorPopupMenu popupMenu = editor.getPopupMenu();                    
            if (popupMenu != null)
            {
            	popupMenu.addMenuListener((ContextMenuListener)editor);
                this.appendChild(popupMenu);
            }
			//
			m_wEditors.add (editor);                   //  add to Editors
			
        	Div div = new Div();
            div.setAlign("right");
            org.adempiere.webui.component.Label label = editor.getLabel();
            div.appendChild(label);
            if (label.getDecorator() != null)
            	div.appendChild(label.getDecorator());
            row.appendChild(div);

			//
			if (voF.isRange)
			{
				Hbox box = new Hbox();
				box.appendChild(editor.getComponent());
				//
				GridFieldVO voF2 = GridFieldVO.createParameter(voF);
				GridField mField2 = new GridField (voF2);
				m_mFields2.add (mField2);
				//	The Editor
				WEditor editor2 = WebEditorFactory.getEditor(mField2, false);
				//  New Field value to be updated to editor
				mField2.addPropertyChangeListener(editor2);
				editor2.dynamicDisplay();
				editor2.fillHorizontal();
				//setup editor context menu
                popupMenu = editor2.getPopupMenu();                    
                if (popupMenu != null)
                {
                	popupMenu.addMenuListener((ContextMenuListener)editor2);
                    this.appendChild(popupMenu);
                }
				//  Set Default
				Object defaultObject2 = mField2.getDefault();
				mField2.setValue (defaultObject2, true);
				//
				m_wEditors2.add (editor2);
				Label separator = new Label(" - ");
				m_separators.add(separator);
				box.appendChild(separator);
				box.appendChild(editor2.getComponent());
				row.appendChild(box);
			}
			else
			{
				row.appendChild(editor.getComponent());
				m_mFields2.add (null);
				m_wEditors2.add (null);
				m_separators.add(null);
			}
			
			if(rw==null)
				rows.appendChild(row);
			
			return row;
		}	//	createField

		

		/**
		 *	Validate Parameter values
		 *  @return true if parameters saved
		 */
		public boolean validateParameters()
		{
			log.config("");

			/**
			 *	Mandatory fields
			 *  see - MTable.getMandatory
			 */
			StringBuffer sb = new StringBuffer();
			int size = m_mFields.size();
			for (int i = 0; i < size; i++)
			{
				GridField field = (GridField)m_mFields.get(i);
				if (field.isMandatory(true))        //  check context
				{
					WEditor wEditor = (WEditor)m_wEditors.get(i);
					Object data = wEditor.getValue();
					if (data == null || data.toString().length() == 0)
					{
						field.setInserting (true);  //  set editable (i.e. updateable) otherwise deadlock
						field.setError(true);
						if (sb.length() > 0)
							sb.append(", ");
						sb.append(field.getHeader());
					}
					else
						field.setError(false);
					//  Check for Range
					WEditor wEditor2 = (WEditor)m_wEditors2.get(i);
					if (wEditor2 != null)
					{
						Object data2 = wEditor.getValue();
						GridField field2 = (GridField)m_mFields2.get(i);
						if (data2 == null || data2.toString().length() == 0)
						{
							field.setInserting (true);  //  set editable (i.e. updateable) otherwise deadlock
							field2.setError(true);
							if (sb.length() > 0)
								sb.append(", ");
							sb.append(field.getHeader());
						}
						else
							field2.setError(false);
					}   //  range field
				}   //  mandatory
			}   //  field loop


			if (sb.length() != 0)
			{
				FDialog.error(m_WindowNo, this, "FillMandatory", sb.toString());
				return false;
			}
			
			return true;
		}	//	validateParameters

		/**
		 *	Save Parameter values
		 *  @return true if parameters saved
		 */
		public boolean saveParameters()
		{
			log.config("");

			if (!validateParameters())
				return false;

			/**********************************************************************
			 *	Save Now
			 */
			for (int i = 0; i < m_mFields.size(); i++)
			{
				//	Get Values
				WEditor editor = (WEditor)m_wEditors.get(i);
				WEditor editor2 = (WEditor)m_wEditors2.get(i);
				Object result = editor.getValue();
				Object result2 = null;
				if (editor2 != null)
					result2 = editor2.getValue();
				
				//	Create Parameter
				MPInstancePara para = new MPInstancePara (Env.getCtx(), m_processInfo.getAD_PInstance_ID(), i);
				GridField mField = (GridField)m_mFields.get(i);
				para.setParameterName(mField.getColumnName());
				
				//	Date
				if (result instanceof Timestamp || result2 instanceof Timestamp)
				{
					para.setP_Date((Timestamp)result);
					if (editor2 != null && result2 != null)
						para.setP_Date_To((Timestamp)result2);
				}
				//	Integer
				else if (result instanceof Integer || result2 instanceof Integer)
				{
					if (result != null)
					{
						Integer ii = (Integer)result;
						para.setP_Number(ii.intValue());
					}
					if (editor2 != null && result2 != null)
					{
						Integer ii = (Integer)result2;
						para.setP_Number_To(ii.intValue());
					}
				}
				//	BigDecimal
				else if (result instanceof BigDecimal || result2 instanceof BigDecimal)
				{
					para.setP_Number ((BigDecimal)result);
					if (editor2 != null && result2 != null)
						para.setP_Number_To ((BigDecimal)result2);
				}
				//	Boolean
				else if (result instanceof Boolean)
				{
					Boolean bb = (Boolean)result;
					String value = bb.booleanValue() ? "Y" : "N";
					para.setP_String (value);
					//	to does not make sense
				}
				//	String
				else
				{
					if (result != null)
						para.setP_String (result.toString());
					if (editor2 != null && result2 != null)
						para.setP_String_To (result2.toString());
				}

				//  Info
				para.setInfo (editor.getDisplay());
				if (editor2 != null)
					para.setInfo_To (editor2.getDisplay());
				//
				para.save();
				log.fine(para.toString());
			}	//	for every parameter

			return true;
		}	//	saveParameters

		/**
		 *	Editor Listener
		 *	@param evt ValueChangeEvent
		 
		 */
		
		public void valueChange(ValueChangeEvent evt) 
		{
			if (evt.getSource() instanceof WEditor) {
				GridField changedField = ((WEditor) evt.getSource()).getGridField();
				if (changedField != null) {
					processDependencies (changedField);
					// future processCallout (changedField);
				}
			}
			processNewValue(evt.getNewValue(), evt.getPropertyName());
		}
		
		/**
		 *  Evaluate Dependencies
		 *  @param changedField changed field
		 */
		private void processDependencies (GridField changedField)
		{
			String columnName = changedField.getColumnName();

			for (GridField field : m_mFields) {
				if (field == null || field == changedField)
					continue;
				verifyChangedField(field, columnName);
			}
			for (GridField field : m_mFields2) {
				if (field == null || field == changedField)
					continue;
				verifyChangedField(field, columnName);
			}
		}   //  processDependencies

		private void verifyChangedField(GridField field, String columnName) {
			ArrayList<String> list = field.getDependentOn();
			if (list.contains(columnName)) {
				if (field.getLookup() instanceof MLookup)
				{
					MLookup mLookup = (MLookup)field.getLookup();
					//  if the lookup is dynamic (i.e. contains this columnName as variable)
					if (mLookup.getValidation().indexOf("@"+columnName+"@") != -1)
					{
						log.fine(columnName + " changed - "
							+ field.getColumnName() + " set to null");
						//  invalidate current selection
						field.setValue(null, true);
					}
				}
			}
		}
		
		private void processNewValue(Object value, String name) {
			if (value == null)
				value = new String("");

			if (value instanceof String)
				Env.setContext(Env.getCtx(), m_WindowNo, name, (String) value);
			else if (value instanceof Integer)
				Env.setContext(Env.getCtx(), m_WindowNo, name, ((Integer) value)
						.intValue());
			else if (value instanceof Boolean)
				Env.setContext(Env.getCtx(), m_WindowNo, name, ((Boolean) value)
						.booleanValue());
			else if (value instanceof Timestamp)
				Env.setContext(Env.getCtx(), m_WindowNo, name, (Timestamp) value);
			else
				Env.setContext(Env.getCtx(), m_WindowNo, name, value.toString());

			dynamicDisplay();
		}
		
		private void dynamicDisplay() {
			for(int i = 0; i < m_wEditors.size(); i++) {
				WEditor editor = m_wEditors.get(i);
				GridField mField = editor.getGridField();
				if (mField.isDisplayed(true)) {
					if (!editor.isVisible()) {
						editor.setVisible(true);
						if (mField.getVO().isRange) {
							m_separators.get(i).setVisible(true);
							m_wEditors2.get(i).setVisible(true);
						}
					}
					boolean rw = mField.isEditablePara(true); // r/w - check if field is Editable
					editor.setReadWrite(rw);
					editor.dynamicDisplay();
					if (mField.getVO().isRange) {
						m_wEditors2.get(i).setReadWrite(rw);
						m_wEditors2.get(i).dynamicDisplay();
					}
				} else if (editor.isVisible()) {
					editor.setVisible(false);
					if (mField.getVO().isRange) {
						m_separators.get(i).setVisible(false);
						m_wEditors2.get(i).setVisible(false);
					}
				}
			}			
		}

		/**
		 * Restore window context.
		 * @author teo_sarca [ 1699826 ]
		 * @see org.compiere.model.GridField#restoreValue()
		 */
		protected void restoreContext() {
			for (GridField f : m_mFields) {
				if (f != null)
					f.restoreValue();
			}
			for (GridField f : m_mFields2) {
				if (f != null)
					f.restoreValue();
			}
		}
	}	//	ProcessParameterPanel

