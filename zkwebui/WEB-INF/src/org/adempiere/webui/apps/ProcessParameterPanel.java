/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
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

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.apps.IProcessParameter;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MClient;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * 	@author 	Low Heng Sin
 * 	@version 	2006-12-01
 */
@SuppressWarnings("serial")
public class ProcessParameterPanel extends Panel 
implements ValueChangeListener, IProcessParameter 
{
		/**
		 *	Dynamic generated Parameter panel.
		 *  @param WindowNo window
		 *  @param pi process info
		 */
		public ProcessParameterPanel(int WindowNo, ProcessInfo pi)
		{
			//
			m_WindowNo = WindowNo;
			m_processInfo = pi;
			//
			initComponent();
		}	//	ProcessParameterPanel

		private void initComponent() {
			centerPanel = new Grid();
			this.appendChild(centerPanel);
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
		//
		private Grid centerPanel = null;

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
					  "   AND (   AD_Process_Para_ID IN ( "
					// Just ASP subscribed process parameters for client "
					+ "              SELECT w.AD_Process_Para_ID "
					+ "                FROM ASP_Process_Para w, ASP_Level l, ASP_ClientLevel cl "
					+ "               WHERE w.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND cl.AD_Client_ID = " + client.getAD_Client_ID()
					+ "                 AND cl.ASP_Level_ID = l.ASP_Level_ID "
					+ "                 AND w.IsActive = 'Y' "
					+ "                 AND l.IsActive = 'Y' "
					+ "                 AND cl.IsActive = 'Y' "
					+ "                 AND w.ASP_Status = 'S') " // Show
					+ "        OR AD_Process_Para_ID IN ( "
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
					+ "   AND AD_Process_Para_ID NOT IN ( "
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
					+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode "
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
					+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode "
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
				while (rs.next())
				{
					hasFields = true;
					createField (rs, rows);
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
		private void createField (ResultSet rs, Rows rows)
		{
			//  Create Field
			GridFieldVO voF = GridFieldVO.createParameter(Env.getCtx(), m_WindowNo, rs);
			GridField mField = new GridField (voF);
			m_mFields.add(mField);                      //  add to Fields

			Row row = new Row();
			
			//	The Editor
			WEditor wEditor = WebEditorFactory.getEditor(mField, false);
			wEditor.addValueChangeListner(this);
			//  MField => VEditor - New Field value to be updated to editor
			mField.addPropertyChangeListener(wEditor);
			//  Set Default
			Object defaultObject = mField.getDefault();
			mField.setValue (defaultObject, true);
			//
			m_wEditors.add (wEditor);                   //  add to Editors
			
			row.appendChild(wEditor.getLabel());
			//
			if (voF.isRange)
			{
				Hbox box = new Hbox();
				box.appendChild(wEditor.getComponent());
				//
				GridFieldVO voF2 = GridFieldVO.createParameter(voF);
				GridField mField2 = new GridField (voF2);
				m_mFields2.add (mField2);
				//	The Editor
				WEditor wEditor2 = WebEditorFactory.getEditor(mField2, false);
				//  New Field value to be updated to editor
				mField2.addPropertyChangeListener(wEditor2);
				//  Set Default
				Object defaultObject2 = mField2.getDefault();
				mField2.setValue (defaultObject2, true);
				//
				m_wEditors2.add (wEditor2);
				box.appendChild(new Label(" - "));
				box.appendChild(wEditor2.getComponent());
				row.appendChild(box);
			}
			else
			{
				row.appendChild(wEditor.getComponent());
				m_mFields2.add (null);
				m_wEditors2.add (null);
			}
			rows.appendChild(row);
		}	//	createField

		

		/**
		 *	Save Parameter values
		 *  @return true if parameters saved
		 */
		public boolean saveParameters()
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
				//ADialog.error(m_WindowNo, this, "FillMandatory", sb.toString());
				return false;
			}

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

				//	Don't save NULL values
				if (result == null && result2 == null)
					continue;
				
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
			String value = evt.getNewValue() == null ? "" : evt.getNewValue().toString();
            
            if (evt.getSource() instanceof WEditor)
            {
                WEditor comp = (WEditor)(evt.getSource());
                comp.setValue(value);
                
            }
			Env.setContext(Env.getCtx(), m_WindowNo, evt.getPropertyName(), value);
			
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

