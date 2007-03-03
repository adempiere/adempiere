/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Parameter Dialog.
 *	- called from ProcessCtl
 *	- checks, if parameters exist and inquires and saves them
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ProcessParameter.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class ProcessParameter extends CDialog
	implements ActionListener, VetoableChangeListener
{
	/**
	 *	Dynamic generated Parameter Dialog screen.
	 *	Called from ProcessCtl.process
	 *
	 *  @param frame frame
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessParameter (Frame frame, int WindowNo, ProcessInfo pi)
	{
		super(frame, pi.getTitle(), true);
		m_frame = frame;
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
	}	//	ProcessParameter

	private Frame		m_frame;
	private int			m_WindowNo;
	private ProcessInfo m_processInfo;
	private boolean 	m_isOK = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ProcessParameter.class);
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
	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel centerPanel = new CPanel();
	private GridBagLayout centerLayout = new GridBagLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);

	/**
	 *	Static Layout
	 *  @throws Exception
	 */
	void jbInit() throws Exception
	{
		mainPanel.setLayout(mainLayout);
		centerPanel.setLayout(centerLayout);
		this.getContentPane().add(mainPanel);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
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
		this.removeAll();
		super.dispose();
	}   //  dispose

	/**
	 *	Read Fields to display
	 *  @return true if loaded OK
	 */
	public boolean initDialog()
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
				+ "ORDER BY SeqNo";
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
				+ "ORDER BY SeqNo";

		//	Create Fields
		boolean hasFields = false;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_processInfo.getAD_Process_ID());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				hasFields = true;
				createField (rs);
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
			AEnv.positionCenterWindow(m_frame, this);
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
		//  Set Default
		Object defaultObject = mField.getDefault();
		mField.setValue (defaultObject, true);
		//
		centerPanel.add ((Component)vEditor, gbc);
		m_vEditors.add (vEditor);                   //  add to Editors
		//
		if (voF.isRange)
		{
			//	To Label
			gbc.gridx = 2;
			gbc.weightx = 0;
			gbc.fill = GridBagConstraints.NONE;
			centerPanel.add (new JLabel(" - "), gbc);
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
			//  Set Default
			Object defaultObject2 = mField2.getDefault();
			mField2.setValue (defaultObject2, true);
			//
			centerPanel.add ((Component)vEditor2, gbc);
			m_vEditors2.add (vEditor2);
		}
		else
		{
			m_mFields2.add (null);
			m_vEditors2.add (null);
		}
	}	//	createField

	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		m_isOK = false;
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			//	check if saving parameters is complete
			if (saveParameters())
			{
				m_isOK = true;
				dispose();
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
	}	//	actionPerformed

	/**
	 *	Editor Listener
	 *	@param evt Event
	 * 	@exception PropertyVetoException if the recipient wishes to roll back.
	 */
	public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException
	{
	//	log.fine( "ProcessParameter.vetoableChange");
		String value = evt.getNewValue() == null ? "" : evt.getNewValue().toString();
		Env.setContext(Env.getCtx(), m_WindowNo, evt.getPropertyName(), value);
	}	//	vetoableChange

	/**
	 *	Save Parameter values
	 *  @return true if parameters saved
	 */
	private boolean saveParameters()
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
	 *	Is everything OK?
	 *  @return true if parameters saved correctly
	 */
	public boolean isOK()
	{
		return m_isOK;
	}	//	isOK
	
	/** deathmeat: POSTPONED TO END OF BUG FIXING BEFORE 3.2 */
//	public void setVisible(boolean b)
//	{
//		MProcess m_process = new MProcess(Env.getCtx(),
//				m_processInfo.getAD_Process_ID(), null);
//		if(m_process.getShowHelp() != null && m_process.getShowHelp().equals("S"))
//		{
//			// It is defined as a silent process
//			if(saveParameters())
//			{
//				m_isOK = true;
//				dispose();
//			}
//		}
//		else
//		{
//			// Not a silent process
//			super.setVisible(b);
//		}
//	}
}	//	ProcessParameter
