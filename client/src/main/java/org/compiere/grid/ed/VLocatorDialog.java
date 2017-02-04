/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.grid.ed;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.model.MLocator;
import org.compiere.model.MLocatorLookup;
import org.compiere.model.MRole;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 *	Dialog to enter Warehouse Locator Info
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: VLocatorDialog.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VLocatorDialog extends CDialog
	implements ActionListener, KeyListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4727764506935600690L;

	/**
	 *	Constructor
	 *  @param frame frame
	 *  @param title title
	 *  @param mLocator locator
	 *  @param M_Locator_ID locator id
	 * 	@param mandatory mandatory
	 * 	@param only_Warehouse_ID of not 0 restrict warehouse
	 */
	public VLocatorDialog (Frame frame, String title, MLocatorLookup mLocator,
		int M_Locator_ID, boolean mandatory, int only_Warehouse_ID)
	{
		super (frame, title, true);
		m_WindowNo = Env.getWindowNo(frame);
		try
		{
			jbInit();
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VLocatorDialog", ex);
		}
		//
		m_mLocator = mLocator;
		m_M_Locator_ID = M_Locator_ID;
		m_mandatory = mandatory;
		m_only_Warehouse_ID = only_Warehouse_ID;
		//
		initLocator();
		AEnv.positionCenterWindow(frame, this);
	}	//	VLocatorDialog

	private int				m_WindowNo;
	private boolean 		m_change = false;
	private MLocatorLookup	m_mLocator;
	private int				m_M_Locator_ID;
	private boolean			m_mandatory = false;
	private int				m_only_Warehouse_ID = 0;
	//
	private int				m_M_Warehouse_ID;
	private String			m_M_WarehouseName;
	private String 			m_M_WarehouseValue;
	private String 			m_Separator;
	private int				m_AD_Client_ID;
	private int				m_AD_Org_ID;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLocatorDialog.class);
	//
	private CPanel panel = new CPanel();
	private CPanel mainPanel = new CPanel();
	private CPanel southPanel = new CPanel();
	private BorderLayout panelLayout = new BorderLayout();
	private GridBagLayout gridBagLayout = new GridBagLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private BorderLayout southLayout = new BorderLayout();
	//
	private VComboBox fLocator = new VComboBox();
	private CComboBox fWarehouse = new CComboBox();
	private JCheckBox fCreateNew = new JCheckBox();
	private CTextField fX = new CTextField();
	private CTextField fY = new CTextField();
	private CTextField fZ = new CTextField();
	private JLabel lLocator = new JLabel();
	private CTextField fWarehouseInfo = new CTextField();
	private CTextField fValue = new CTextField();
	private JLabel lWarehouseInfo = new JLabel();
	private JLabel lWarehouse = new JLabel();
	private JLabel lX = new JLabel();
	private JLabel lY = new JLabel();
	private JLabel lZ = new JLabel();
	private JLabel lValue = new JLabel();

	/**
	 *	Static component init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		panel.setLayout(panelLayout);
		southPanel.setLayout(southLayout);
		mainPanel.setLayout(gridBagLayout);
		panelLayout.setHgap(5);
		panelLayout.setVgap(10);
		fCreateNew.setText(Msg.getMsg(Env.getCtx(), "CreateNew"));
		fX.setColumns(15);
		fY.setColumns(15);
		fZ.setColumns(15);
		lLocator.setLabelFor(fLocator);
		lLocator.setText(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		fWarehouseInfo.setBackground(AdempierePLAF.getFieldBackground_Inactive());
		fWarehouseInfo.setReadWrite(false);
		fWarehouseInfo.setColumns(15);
		fValue.setColumns(15);
		lWarehouseInfo.setLabelFor(fWarehouseInfo);
		lWarehouseInfo.setText(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		lWarehouse.setLabelFor(fWarehouse);
		lWarehouse.setText(Msg.translate(Env.getCtx(), "M_Warehouse_ID"));
		lX.setLabelFor(fX);
		lX.setText(Msg.getElement(Env.getCtx(), "X"));
		lY.setLabelFor(fY);
		lY.setText(Msg.getElement(Env.getCtx(), "Y"));
		lZ.setLabelFor(fZ);
		lZ.setText(Msg.getElement(Env.getCtx(), "Z"));
		lValue.setLabelFor(fValue);
		lValue.setText(Msg.translate(Env.getCtx(), "Value"));
		getContentPane().add(panel);
		panel.add(mainPanel, BorderLayout.CENTER);
		//
		mainPanel.add(lLocator, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fLocator, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		mainPanel.add(fCreateNew, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 0, 0, 5), 0, 0));
		mainPanel.add(lWarehouseInfo, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fWarehouseInfo, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		mainPanel.add(lWarehouse, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fWarehouse, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		mainPanel.add(lX, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fX, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		mainPanel.add(lY, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fY, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		mainPanel.add(lZ, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fZ, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		mainPanel.add(lValue, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		mainPanel.add(fValue, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 5), 0, 0));
		//
		panel.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(confirmPanel, BorderLayout.NORTH);
		confirmPanel.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dynanmic Init & fill fields
	 */
	private void initLocator()
	{
		log.fine("");

		//	Load Warehouse
		String sql = "SELECT M_Warehouse_ID, Name FROM M_Warehouse";
		if (m_only_Warehouse_ID != 0)
			sql += " WHERE M_Warehouse_ID=" + m_only_Warehouse_ID;
		String SQL = MRole.getDefault().addAccessSQL(
			sql, "M_Warehouse", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO)
			+ " ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
				fWarehouse.addItem(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
		log.fine("Warehouses=" + fWarehouse.getItemCount());

		//	Load existing Locators
		m_mLocator.fillComboBox(m_mandatory, true, true, false);
		log.fine(m_mLocator.toString());
		fLocator.setModel(m_mLocator);
		fLocator.setValue(m_M_Locator_ID);
		fLocator.addActionListener(this);
		displayLocator();
		//
		fCreateNew.setSelected(false);
		fCreateNew.addActionListener(this);
		enableNew();
		//
		fWarehouse.addActionListener(this);
		fX.addKeyListener(this);
		fY.addKeyListener(this);
		fZ.addKeyListener(this);

		//	Update UI
		pack();
	}	//	initLocator


	/*************************************************************************/

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		//
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			actionOK();
			m_change = true;
			dispose();
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			m_change = false;
			dispose();
		}
		//	Locator Change
		else if (e.getSource() == fLocator)
			displayLocator();

		//	New Value Change
		else if (source == fCreateNew)
			enableNew();

		//	Entered/Changed data for Value
		else if (fCreateNew.isSelected() && source == fWarehouse)
			createValue();

	}	//	actionPerformed

	/**
	 *	KeyListener - nop
	 *  @param e event
	 */
	public void keyPressed(KeyEvent e)
	{}
	/**
	 *	KeyListener
	 *  @param e event
	 */
	public void keyReleased(KeyEvent e)
	{
		if (fCreateNew.isSelected())
			createValue();
	}
	/**
	 *	KeyListener - nop
	 *  @param e event
	 */
	public void keyTyped(KeyEvent e)
	{}

	/**
	 *	Display value of current locator
	 */
	private void displayLocator()
	{
		MLocator l = (MLocator) fLocator.getSelectedItem();
		if (l == null)
			return;
		//
		m_M_Locator_ID = l.getM_Locator_ID();
		fWarehouseInfo.setText(l.getWarehouseName());
		fX.setText(l.getX());
		fY.setText(l.getY());
		fZ.setText(l.getZ());
		fValue.setText(l.getValue());
		getWarehouseInfo(l.getM_Warehouse_ID());
		//	Set Warehouse
		int size = fWarehouse.getItemCount();
		for (int i = 0; i < size; i++)
		{
			KeyNamePair pp = (KeyNamePair)fWarehouse.getItemAt(i);
			if (pp.getKey() == l.getM_Warehouse_ID())
			{
				fWarehouse.setSelectedIndex(i);
				continue;
			}
		}
	}	//	displayLocator

	/**
	 *	Enable/disable New data entry
	 */
	private void enableNew()
	{
		boolean sel = fCreateNew.isSelected();
		lWarehouse.setVisible(sel);
		fWarehouse.setVisible(sel);
		lWarehouseInfo.setVisible(!sel);
		fWarehouseInfo.setVisible(!sel);
		fX.setReadWrite(sel);
		fY.setReadWrite(sel);
		fZ.setReadWrite(sel);
		fValue.setReadWrite(sel);
		pack();
	}	//	enableNew

	/**
	 *	Get Warehouse Info
	 *  @param M_Warehouse_ID warehouse
	 */
	private void getWarehouseInfo (int M_Warehouse_ID)
	{
		if (M_Warehouse_ID == m_M_Warehouse_ID)
			return;
		//	Defaults
		m_M_Warehouse_ID = 0;
		m_M_WarehouseName = "";
		m_M_WarehouseValue = "";
		m_Separator = ".";
		m_AD_Client_ID = 0;
		m_AD_Org_ID = 0;
		//
		String SQL = "SELECT M_Warehouse_ID, Value, Name, Separator, AD_Client_ID, AD_Org_ID "
			+ "FROM M_Warehouse WHERE M_Warehouse_ID=?";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, M_Warehouse_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_M_Warehouse_ID = rs.getInt(1);
				m_M_WarehouseValue = rs.getString(2);
				m_M_WarehouseName = rs.getString(3);
				m_Separator = rs.getString(4);
				m_AD_Client_ID = rs.getInt(5);
				m_AD_Org_ID = rs.getInt(6);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
	}	//	getWarehouseInfo

	/**
	 *	Create Locator-Value
	 */
	private void createValue()
	{
		//	Get Warehouse Info
		KeyNamePair pp = (KeyNamePair)fWarehouse.getSelectedItem();
		if (pp == null)
			return;
		getWarehouseInfo(pp.getKey());
		//
		StringBuffer buf = new StringBuffer(m_M_WarehouseValue);
		buf.append(m_Separator).append(fX.getText());
		buf.append(m_Separator).append(fY.getText());
		buf.append(m_Separator).append(fZ.getText());
		fValue.setText(buf.toString());
	}	//	createValue

	/**
	 * 	OK - check for changes (save them) & Exit
	 */
	private void actionOK()
	{
		if (fCreateNew.isSelected())
		{
			//	Get Warehouse Info
			KeyNamePair pp = (KeyNamePair)fWarehouse.getSelectedItem();
			if (pp != null)
				getWarehouseInfo(pp.getKey());

			//	Check mandatory values
			String mandatoryFields = "";
			if (m_M_Warehouse_ID == 0)
				mandatoryFields += lWarehouse.getText() + " - ";
			if (fValue.getText().length()==0)
				mandatoryFields += lValue.getText() + " - ";
			if (fX.getText().length()==0)
				mandatoryFields += lX.getText() + " - ";
			if (fY.getText().length()==0)
				mandatoryFields += lY.getText() + " - ";
			if (fZ.getText().length()==0)
				mandatoryFields += lZ.getText() + " - ";
			if (mandatoryFields.length() != 0)
			{
				ADialog.error(m_WindowNo, this, "FillMandatory", mandatoryFields.substring(0, mandatoryFields.length()-3));
				return;
			}

			MLocator loc = MLocator.get(Env.getCtx(), m_M_Warehouse_ID, fValue.getText(),
				fX.getText(), fY.getText(), fZ.getText());
			m_M_Locator_ID = loc.getM_Locator_ID();
			fLocator.addItem(loc);
			fLocator.setSelectedItem(loc);
		}	//	createNew
		//
		log.config("M_Locator_ID=" + m_M_Locator_ID);
	}	//	actionOK

	/**
	 *	Get Selected value
	 *  @return value as Integer
	 */
	public Integer getValue()
	{
		MLocator l = (MLocator) fLocator.getSelectedItem();
		if (l != null && l.getM_Locator_ID() != 0)
			return new Integer (l.getM_Locator_ID());
		return null;
	}	//	getValue

	/**
	 *	Get result
	 *  @return true if changed
	 */
	public boolean isChanged()
	{
		if (m_change)
		{
			MLocator l = (MLocator) fLocator.getSelectedItem();
			if (l != null)
				return l.getM_Locator_ID() == m_M_Locator_ID;
		}
		return m_change;
	}	//	getChange

}	//	VLocatorDialog
