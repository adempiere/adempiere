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
package org.compiere.grid;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.apps.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Tab to maintain Order/Sequence
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VSortTab.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VSortTab extends CPanel implements APanelTab, ActionListener
{
	/**
	 *	Tab Order Constructor
	 *
	 *  @param WindowNo Window No
	 *  @param AD_Table_ID Table No
	 *  @param AD_ColumnSortOrder_ID Sort Column
	 *  @param AD_ColumnSortYesNo_ID YesNo Column
	 */
	public VSortTab(int WindowNo, int AD_Table_ID, int AD_ColumnSortOrder_ID, int AD_ColumnSortYesNo_ID)
	{
		log.config("SortOrder=" + AD_ColumnSortOrder_ID + ", SortYesNo=" + AD_ColumnSortYesNo_ID);
		m_WindowNo = WindowNo;

		try
		{
			jbInit();
			dynInit (AD_Table_ID, AD_ColumnSortOrder_ID, AD_ColumnSortYesNo_ID);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
	}	//	VSortTab

	private int			m_WindowNo;
	private String		m_TableName = null;
	private String		m_ColumnSortName= null;
	private String		m_ColumnYesNoName = null;
	private String		m_KeyColumnName = null;
	private String		m_IdentifierColumnName = null;
	private boolean		m_IdentifierTranslated = false;
	private String		m_ParentColumnName = null;

	private boolean		m_saveSequence = false;
	private APanel		m_aPanel = null;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VSortTab.class);

	//	UI variables
	private GridBagLayout mainLayout = new GridBagLayout();
	private CLabel noLabel = new CLabel();
	private CLabel yesLabel = new CLabel();
	private CButton bAdd = new CButton();
	private CButton bRemove = new CButton();
	private CButton bUp = new CButton();
	private CButton bDown = new CButton();
	//
	private DefaultListModel noModel = new DefaultListModel();
	private DefaultListModel yesModel = new DefaultListModel();
	private JList noList = new JList(noModel);
	private JList yesList = new JList(yesModel);
	private JScrollPane noPane = new JScrollPane(noList);
	private JScrollPane yesPane = new JScrollPane(yesList);

	/**
	 * 	Static Layout
	 * 	@throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setLayout(mainLayout);
		//
		noLabel.setText("No");
		yesLabel.setText("Yes");
		//
		bAdd.setIcon(Env.getImageIcon("Detail24.gif"));
		bAdd.setMargin(new Insets(2, 2, 2, 2));
		bAdd.addActionListener(this);
		bRemove.setIcon(Env.getImageIcon("Parent24.gif"));
		bRemove.setMargin(new Insets(2, 2, 2, 2));
		bRemove.addActionListener(this);
		bUp.setIcon(Env.getImageIcon("Previous24.gif"));
		bUp.setMargin(new Insets(2, 2, 2, 2));
		bUp.addActionListener(this);
		bDown.setIcon(Env.getImageIcon("Next24.gif"));
		bDown.setMargin(new Insets(2, 2, 2, 2));
		bDown.addActionListener(this);
		//
	//	yesList.setBorder(BorderFactory.createLoweredBevelBorder());
		yesPane.setPreferredSize(new Dimension(200, 300));
		yesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	//	noList.setBorder(BorderFactory.createLoweredBevelBorder());
		noPane.setPreferredSize(new Dimension(200, 300));
		noList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//
		this.add(noLabel,    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(yesLabel,    new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(bDown,         new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		this.add(noPane,      new GridBagConstraints(0, 1, 1, 3, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		this.add(yesPane,      new GridBagConstraints(2, 1, 1, 3, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		this.add(bUp,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		this.add(bRemove,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
		this.add(bAdd,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
	}	//	jbInit

	/**
	 * 	Dyanamic Init
	 *  @param AD_Table_ID Table No
	 *  @param AD_ColumnSortOrder_ID Sort Column
	 *  @param AD_ColumnSortYesNo_ID YesNo Column
	 */
	private void dynInit (int AD_Table_ID, int AD_ColumnSortOrder_ID, int AD_ColumnSortYesNo_ID)
	{
		String sql = "SELECT t.TableName, c.AD_Column_ID, c.ColumnName, e.Name,"	//	1..4
			+ "c.IsParent, c.IsKey, c.IsIdentifier, c.IsTranslated "				//	4..8
			+ "FROM AD_Table t, AD_Column c, AD_Element e "
			+ "WHERE t.AD_Table_ID=?"						//	#1
			+ " AND t.AD_Table_ID=c.AD_Table_ID"
			+ " AND (c.AD_Column_ID=? OR AD_Column_ID=?"	//	#2..3
			+ " OR c.IsParent='Y' OR c.IsKey='Y' OR c.IsIdentifier='Y')"
			+ " AND c.AD_Element_ID=e.AD_Element_ID";
		boolean trl = !Env.isBaseLanguage(Env.getCtx(), "AD_Element");
		if (trl)
			sql = "SELECT t.TableName, c.AD_Column_ID, c.ColumnName, et.Name,"	//	1..4
				+ "c.IsParent, c.IsKey, c.IsIdentifier, c.IsTranslated "		//	4..8
				+ "FROM AD_Table t, AD_Column c, AD_Element_Trl et "
				+ "WHERE t.AD_Table_ID=?"						//	#1
				+ " AND t.AD_Table_ID=c.AD_Table_ID"
				+ " AND (c.AD_Column_ID=? OR AD_Column_ID=?"	//	#2..3
				+ "	OR c.IsParent='Y' OR c.IsKey='Y' OR c.IsIdentifier='Y')"
				+ " AND c.AD_Element_ID=et.AD_Element_ID"
				+ " AND et.AD_Language=?";						//	#4
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Table_ID);
			pstmt.setInt(2, AD_ColumnSortOrder_ID);
			pstmt.setInt(3, AD_ColumnSortYesNo_ID);
			if (trl)
				pstmt.setString(4, Env.getAD_Language(Env.getCtx()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				m_TableName = rs.getString(1);
				//	Sort Column
				if (AD_ColumnSortOrder_ID == rs.getInt(2))
				{
					log.fine("Sort=" + rs.getString(1) + "." + rs.getString(3));
					m_ColumnSortName = rs.getString(3);
					yesLabel.setText(rs.getString(4));
				}
				//	Optional YesNo
				else if (AD_ColumnSortYesNo_ID == rs.getInt(2))
				{
					log.fine("YesNo=" + rs.getString(1) + "." + rs.getString(3));
					m_ColumnYesNoName = rs.getString(3);
				}
				//	Parent2
				else if (rs.getString(5).equals("Y"))
				{
					log.fine("Parent=" + rs.getString(1) + "." + rs.getString(3));
					m_ParentColumnName = rs.getString(3);
				}
				//	KeyColumn
				else if (rs.getString(6).equals("Y"))
				{
					log.fine("Key=" + rs.getString(1) + "." + rs.getString(3));
					m_KeyColumnName = rs.getString(3);
				}
				//	Identifier
				else if (rs.getString(7).equals("Y"))
				{
					log.fine("Identifier=" + rs.getString(1) + "." + rs.getString(3));
					m_IdentifierColumnName = rs.getString(3);
					if (trl)
						m_IdentifierTranslated = "Y".equals(rs.getString(8));
				}
				else
					log.fine("??NotUsed??=" + rs.getString(1) + "." + rs.getString(3));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		noLabel.setText(Msg.getMsg(Env.getCtx(), "Available"));
		log.info(m_ColumnSortName);
	}	//	dynInit

	/**
	 * 	Register APanel
	 * 	@param panel panel
	 */
	public void registerAPanel (APanel panel)
	{
		m_aPanel = panel;
	}	//	registerAPanel

	/**
	 * 	Unregister APanel
	 */
	public void unregisterPanel ()
	{
		saveData();
		m_aPanel = null;
	}	//	dispoase


	/**************************************************************************
	 * 	ActionPerformed
	 * 	@param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//	ADD     ->
		if (e.getSource() == bAdd)
		{
			Object objects[] = noList.getSelectedValues();
			for (int i = 0; i < objects.length; i++)
			{
				if (objects[i] != null && noModel.removeElement(objects[i]))
				{
					log.config("Add=" + objects[i]);
					yesModel.addElement(objects[i]);
					yesList.setSelectedValue(objects[i], true);
					m_saveSequence = true;
				}
			}
		}
		//	REMOVE  <-
		else if (e.getSource() == bRemove)
		{
			Object objects[] = yesList.getSelectedValues();
			for (int i = 0; i < objects.length; i++)
			{
				if (objects[i] != null && yesModel.removeElement(objects[i]))
				{
					log.config("Remove=" + objects[i]);
					noModel.addElement(objects[i]);
					m_saveSequence = true;
				}
			}
		}
		//	UP      |
		else if (e.getSource() == bUp)
		{
			int indexes[] = yesList.getSelectedIndices();
			Object objects[] = yesList.getSelectedValues();
			for (int i = 0; i < indexes.length; i++)
			{
				if (indexes[i] > 0)
				{
					Object obj = yesList.getSelectedValue();
					log.config("Up=" + obj);
					if (yesModel.removeElement(obj))
						yesModel.insertElementAt(obj, indexes[i]-1);
					m_saveSequence = true;
					indexes[i]--;
				}
			}
			yesList.setSelectedIndices(indexes);
		}
		//	DOWN    |
		else if (e.getSource() == bDown)
		{
			int indexes[] = yesList.getSelectedIndices();
			for (int i = 0; i < indexes.length; i++)
			{
				if (indexes[i] != -1 && indexes[i] < yesModel.size()-1)
				{
					Object obj = yesList.getSelectedValue();
					log.config("Down=" + obj);
					if (yesModel.removeElement(obj))
						yesModel.insertElementAt(obj, indexes[i]+1);
					m_saveSequence = true;
					indexes[i]++;
				}
			}
			yesList.setSelectedIndices(indexes);
		}

		//	Enable explicit Save
		if (m_saveSequence && m_aPanel != null)
			m_aPanel.aSave.setEnabled(true);

	}	//	actionPerformed

	
	/**************************************************************************
	 * 	Load Data
	 */
	public void loadData()
	{
		yesModel.removeAllElements();
		noModel.removeAllElements();
		
		//	SELECT t.AD_Field_ID,t.Name,t.SeqNo,t.IsDisplayed FROM AD_Field t WHERE t.AD_Tab_ID=? ORDER BY 4 DESC,3,2
		//	SELECT t.AD_PrintFormatItem_ID,t.Name,t.SeqNo,t.IsPrinted FROM AD_PrintFormatItem t WHERE t.AD_PrintFormat_ID=? ORDER BY 4 DESC,3,2
		//	SELECT t.AD_PrintFormatItem_ID,t.Name,t.SortNo,t.IsOrderBy FROM AD_PrintFormatItem t WHERE t.AD_PrintFormat_ID=? ORDER BY 4 DESC,3,2
		StringBuffer sql = new StringBuffer();
		//	Columns
		sql.append("SELECT t.").append(m_KeyColumnName)				//	1
			.append(m_IdentifierTranslated ? ",tt." : ",t.")
				.append(m_IdentifierColumnName)						//	2
			.append(",t.").append(m_ColumnSortName);				//	3
		if (m_ColumnYesNoName != null)
			sql.append(",t.").append(m_ColumnYesNoName);			//	4
		//	Tables
		sql.append(" FROM ").append(m_TableName).append( " t");
		if (m_IdentifierTranslated)
			sql.append(", ").append(m_TableName).append("_Trl tt");
		//	Where
		sql.append(" WHERE t.").append(m_ParentColumnName).append("=?");
		if (m_IdentifierTranslated)
			sql.append(" AND t.").append(m_KeyColumnName).append("=tt.").append(m_KeyColumnName)
				.append(" AND tt.AD_Language=?");
		//	Order
		sql.append(" ORDER BY ");
		if (m_ColumnYesNoName != null)
			sql.append("4 DESC,");		//	t.IsDisplayed DESC
		sql.append("3,2");				//	t.SeqNo, tt.Name 
		int ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, m_ParentColumnName);
		log.config(sql.toString() + " - ID=" + ID);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, ID);
			if (m_IdentifierTranslated)
				pstmt.setString(2, Env.getAD_Language(Env.getCtx()));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				int seq = rs.getInt(3);
				boolean isYes = seq != 0;
				if (m_ColumnYesNoName != null)
					isYes = rs.getString(4).equals("Y");
				//
				KeyNamePair pp = new KeyNamePair(key, name);
				if (isYes)
					yesModel.addElement(pp);
				else
					noModel.addElement(pp);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		m_saveSequence = false;
	}	//	loadData

	/**
	 * 	Save Data
	 */
	public void saveData()
	{
		if (!m_saveSequence)
			return;
		log.info("");
		StringBuffer sql = null;
		//	noList - Set SortColumn to null and optional YesNo Column to 'N'
		for (int i = 0; i < noModel.getSize(); i++)
		{
			KeyNamePair pp = (KeyNamePair)noModel.getElementAt(i);
			sql = new StringBuffer();
			sql.append("UPDATE ").append(m_TableName)
				.append(" SET ").append(m_ColumnSortName).append("=0");
			if (m_ColumnYesNoName != null)
				sql.append(",").append(m_ColumnYesNoName).append("='N'");
			sql.append(" WHERE ").append(m_KeyColumnName).append("=").append(pp.getKey());
			if (DB.executeUpdate(sql.toString(), null) != 1)
				log.log(Level.SEVERE, "NoModel - Not updated: " + m_KeyColumnName + "=" + pp.getKey());
		}
		//	yesList - Set SortColumn to value and optional YesNo Column to 'Y'
		for (int i = 0; i < yesModel.getSize(); i++)
		{
			KeyNamePair pp = (KeyNamePair)yesModel.getElementAt(i);
			sql = new StringBuffer();
			sql.append("UPDATE ").append(m_TableName)
				.append(" SET ").append(m_ColumnSortName).append("=").append(i+1).append("0");	//	10 steps
			if (m_ColumnYesNoName != null)
				sql.append(",").append(m_ColumnYesNoName).append("='Y'");
			sql.append(" WHERE ").append(m_KeyColumnName).append("=").append(pp.getKey());
			if (DB.executeUpdate(sql.toString(), null) != 1)
				log.log(Level.SEVERE, "YesModel - Not updated: " + m_KeyColumnName + "=" + pp.getKey());
		}
	}	//	saveData

}	//	VSortTab

