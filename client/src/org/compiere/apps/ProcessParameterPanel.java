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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.Box;
import javax.swing.JLabel;

import org.adempiere.exceptions.DBException;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MClient;
import org.compiere.model.MLookup;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * @author Low Heng Sin
 * @author Juan David Arboleda (arboleda), GlobalQSS, [ 1795398 ] Process
 *         Parameter: add display and readonly logic
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2548216 ] Process Param Panel is not showing any parameter if error 
 * @version 	2006-12-01
 */
public class ProcessParameterPanel extends CPanel implements VetoableChangeListener, IProcessParameter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4042260793108029845L;

		/**
		 *	Dynamic generated Parameter panel.
		 *  @param WindowNo window
		 *  @param pi process info
		 */
		public ProcessParameterPanel(int WindowNo, ProcessInfo pi)
		{
			try
			{
				jbInit();
			}
			catch(Exception ex)
			{
				log.log(Level.SEVERE, ex.getMessage());
			}
			//
			m_WindowNo = WindowNo;
			m_processInfo = pi;
			//
		}	//	ProcessParameterPanel

		private int			m_WindowNo;
		private ProcessInfo m_processInfo;
		/**	Logger			*/
		private static CLogger log = CLogger.getCLogger(ProcessParameterPanel.class);
		//
		private GridBagConstraints gbc = new GridBagConstraints();
		private Insets      nullInset       = new Insets(0,0,0,0);
		private Insets 		labelInset      = new Insets(2,12,2,0);		// 	top,left,bottom,right
		private Insets 		fieldInset      = new Insets(2,5,2,0);		// 	top,left,bottom,right
		private Insets 		fieldInsetRight = new Insets(2,5,2,12);		// 	top,left,bottom,right
		private int			m_line = 0;
		//
		private ArrayList<VEditor>	m_vEditors = new ArrayList<VEditor>();
		private ArrayList<VEditor>	m_vEditors2 = new ArrayList<VEditor>();		//	for ranges
		private ArrayList<GridField>	m_mFields = new ArrayList<GridField>();
		private ArrayList<GridField>	m_mFields2 = new ArrayList<GridField>();
		private ArrayList<JLabel> m_separators = new ArrayList<JLabel>();
		//
		private BorderLayout mainLayout = new BorderLayout();
		private CPanel centerPanel = new CPanel();
		private GridBagLayout centerLayout = new GridBagLayout();

		/**
		 *	Static Layout
		 *  @throws Exception
		 */
		void jbInit() throws Exception
		{
			this.setLayout(mainLayout);
			centerPanel.setLayout(centerLayout);
			this.add(centerPanel, BorderLayout.CENTER);
		}	//	jbInit

		/**
		 *  Dispose
		 */
		public void dispose()
		{
			m_vEditors.clear();
			m_vEditors2.clear();
			m_mFields.clear();
			m_mFields2.clear();
			m_separators.clear();
			this.removeAll();
		}   //  dispose

		/**
		 *	Read Fields to display
		 *  @return true if loaded OK
		 */
		public boolean init()
		{
			log.config("");

			//	Prepare panel
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.weightx = 0;
			gbc.weighty = 0;
			gbc.gridy = m_line++;
			gbc.gridx = 0;
			gbc.gridwidth = 1;
			gbc.insets = nullInset;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			centerPanel.add(Box.createVerticalStrut(10), gbc);    	//	top gap 10+2=12

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
					+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode, p.ReadOnlyLogic, p.DisplayLogic "
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
					+ "p.SeqNo, p.AD_Reference_Value_ID, vr.Code AS ValidationCode, p.ReadOnlyLogic, p.DisplayLogic "
					+ "FROM AD_Process_Para p"
					+ " INNER JOIN AD_Process_Para_Trl t ON (p.AD_Process_Para_ID=t.AD_Process_Para_ID)"
					+ " LEFT OUTER JOIN AD_Val_Rule vr ON (p.AD_Val_Rule_ID=vr.AD_Val_Rule_ID) "
					+ "WHERE p.AD_Process_ID=?"		//	1
					+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
					+ " AND p.IsActive='Y' "
					+ ASPFilter + " ORDER BY SeqNo";

			//	Create Fields
			boolean hasFields = false;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, m_processInfo.getAD_Process_ID());
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					hasFields = true;
					createField (rs);
				}
			}
			catch(SQLException e)
			{
				throw new DBException(e, sql);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}

			//	both vectors the same?
			if (m_mFields.size() != m_mFields2.size()
					|| m_mFields.size() != m_vEditors.size()
					|| m_mFields2.size() != m_vEditors2.size())
				log.log(Level.SEVERE, "View & Model vector size is different");

			//	clean up
			if (hasFields)
			{
				gbc.gridy = m_line++;
				centerPanel.add(Box.createVerticalStrut(10), gbc);    	//	bottom gap
				gbc.gridx = 3;
				centerPanel.add(Box.createHorizontalStrut(12), gbc);   	//	right gap
				dynamicDisplay();
			}
			else
				dispose();
			return hasFields;
		}	//	init

		/**
		 *	Create Field.
		 *	- creates Fields and adds it to m_mFields list
		 *  - creates Editor and adds it to m_vEditors list
		 *  Handles Ranges by adding additional mField/vEditor.
		 *  <p>
		 *  mFields are used for default value and mandatory checking;
		 *  vEditors are used to retrieve the value (no data binding)
		 *
		 * @param rs result set
		 */
		private void createField (ResultSet rs)
		{
			//  Create Field
			GridFieldVO voF = GridFieldVO.createParameter(Env.getCtx(), m_WindowNo, rs);
			GridField mField = new GridField (voF);
			m_mFields.add(mField);                      //  add to Fields

			//	Label Preparation
			gbc.gridy = m_line++;
			gbc.gridwidth = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;	//	required for right justified
			gbc.gridx = 0;
			gbc.weightx = 0;
			JLabel label = VEditorFactory.getLabel(mField);
			if (label == null)
			{
				gbc.insets = nullInset;
				centerPanel.add(Box.createHorizontalStrut(12), gbc);   	//	left gap
			}
			else
			{
				gbc.insets = labelInset;
				centerPanel.add(label, gbc);
			}

			//	Field Preparation
			gbc.insets = fieldInset;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridwidth = 1;
			gbc.gridx = 1;
			gbc.weightx = 1;

			//	The Editor
			VEditor vEditor = VEditorFactory.getEditor(mField, false);
			vEditor.addVetoableChangeListener(this);
			//  MField => VEditor - New Field value to be updated to editor
			mField.addPropertyChangeListener(vEditor);
			//
			centerPanel.add ((Component)vEditor, gbc);
			m_vEditors.add (vEditor);                   //  add to Editors
			//  Set Default
			Object defaultObject = mField.getDefault();
			mField.setValue (defaultObject, true);
			//
			if (voF.isRange)
			{
				//	To Label
				gbc.gridx = 2;
				gbc.weightx = 0;
				gbc.fill = GridBagConstraints.NONE;
				JLabel dash = new JLabel(" - ");
				centerPanel.add (dash, gbc);
				m_separators.add(dash);
				//  To Field
				gbc.gridx = 3;
				gbc.insets = fieldInsetRight;
				gbc.weightx = 1;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.WEST;
				//
				GridFieldVO voF2 = GridFieldVO.createParameter(voF);
				GridField mField2 = new GridField (voF2);
				m_mFields2.add (mField2);
				//	The Editor
				VEditor vEditor2 = VEditorFactory.getEditor(mField2, false);
				//  New Field value to be updated to editor
				mField2.addPropertyChangeListener(vEditor2);
				//
				centerPanel.add ((Component)vEditor2, gbc);
				m_vEditors2.add (vEditor2);
				//  Set Default
				Object defaultObject2 = mField2.getDefault();
				mField2.setValue (defaultObject2, true);
			}
			else
			{
				m_separators.add(null);
				m_mFields2.add (null);
				m_vEditors2.add (null);
			}
		}	//	createField

		/**
		 *	Editor Listener
		 *	@param evt Event
		 * 	@exception PropertyVetoException if the recipient wishes to roll back.
		 */
		public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
		{
		//	log.fine( "ProcessParameterPanel.vetoableChange");
			if (evt.getSource() instanceof VEditor) {
				GridField changedField = ((VEditor) evt.getSource()).getField();
				if (changedField != null) {
					processDependencies (changedField);
					// future processCallout (changedField);
				}
			}
			processNewValue(evt.getNewValue(), evt.getPropertyName());
		}	//	vetoableChange

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

		/**
		 * Dynamic Display.
		 * 
		 **/
		public void dynamicDisplay() {
			Component[] comps = centerPanel.getComponents();
			for (int i = 0; i < comps.length; i++) {
				Component comp = comps[i];
				if (comp instanceof CLabel)
					continue;
				String columnName = comp.getName();

				if (columnName != null && columnName.length() > 0) {
					int index = getIndex(columnName);
					if (m_mFields.get(index) != null) {
						if (m_mFields.get(index).isDisplayed(true)) { // check
							// context
							if (!comp.isVisible()) {
								comp.setVisible(true); // visibility
								if (m_mFields.get(index).getVO().isRange)
									m_separators.get(index).setText(" - ");
							}
							boolean rw = m_mFields.get(index).isEditablePara(true); // r/w - check if field is Editable
							m_vEditors.get(index).setReadWrite(rw);
							if (m_mFields.get(index).getVO().isRange)
								m_vEditors2.get(index).setReadWrite(rw);
						} else {
							if (comp.isVisible()) {
								comp.setVisible(false);
								if (m_mFields.get(index).getVO().isRange)
									m_separators.get(index).setText("");
							}
						}
					}
				}
			}
		} // Dynamic Display.

		/**
		 * getIndex. Get m_mFields index from columnName
		 * 
		 * @param columnName
		 * @return int
		 **/
		private int getIndex(String columnName) {

			for (int i = 0; i < m_mFields.size(); i++) {
				if (m_mFields.get(i).getColumnName().equals(columnName)) {
					return i;
				}
			}
			return 0;
		} // getIndex

		/* (non-Javadoc)
		 * @see org.compiere.apps.ProcessParameters#saveParameters()
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
					VEditor vEditor = (VEditor)m_vEditors.get(i);
					Object data = vEditor.getValue();
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
					VEditor vEditor2 = (VEditor)m_vEditors2.get(i);
					if (vEditor2 != null)
					{
						Object data2 = vEditor.getValue();
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
				ADialog.error(m_WindowNo, this, "FillMandatory", sb.toString());
				return false;
			}

			/**********************************************************************
			 *	Save Now
			 */
			for (int i = 0; i < m_mFields.size(); i++)
			{
				//	Get Values
				VEditor editor = (VEditor)m_vEditors.get(i);
				VEditor editor2 = (VEditor)m_vEditors2.get(i);
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
				para.saveEx();
				log.fine(para.toString());
			}	//	for every parameter

			return true;
		}	//	saveParameters
		
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
