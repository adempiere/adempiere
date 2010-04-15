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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.apps.AWindow;
import org.compiere.apps.ConfirmPanel;
import org.compiere.apps.search.PAttributeInstance;
import org.compiere.model.MAttribute;
import org.compiere.model.MAttributeInstance;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MAttributeValue;
import org.compiere.model.MDocType;
import org.compiere.model.MLot;
import org.compiere.model.MLotCtl;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MSerNoCtl;
import org.compiere.model.X_M_MovementLine;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CEditor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 *  Product Attribute Set Product/Instance Dialog Editor.
 * 	Called from VPAttribute.actionPerformed
 *
 *  @author Jorg Janke
 *  @version $Id: VPAttributeDialog.java,v 1.4 2006/07/30 00:51:27 jjanke Exp $
 */
public class VPAttributeDialog extends CDialog
	implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1062346984681892620L;

	/*****************************************************************************
	 *	Mouse Listener for Popup Menu
	 */
	final class VPAttributeDialog_mouseAdapter extends java.awt.event.MouseAdapter
	{
		/**
		 *	Constructor
		 *  @param adaptee adaptee
		 */
		VPAttributeDialog_mouseAdapter(VPAttributeDialog adaptee)
		{
			m_adaptee = adaptee;
		}	//	VPAttributeDialog_mouseAdapter

		private VPAttributeDialog m_adaptee;

		/**
		 *	Mouse Listener
		 *  @param e MouseEvent
		 */
		public void mouseClicked(MouseEvent e)
		{
		//	System.out.println("mouseClicked " + e.getID() + " " + e.getSource().getClass().toString());
			//	popup menu
			if (SwingUtilities.isRightMouseButton(e))
				m_adaptee.popupMenu.show((Component)e.getSource(), e.getX(), e.getY());
		}	//	mouse Clicked

	}	//	VPAttributeDialog_mouseAdapter	
	
	/**
	 *	Product Attribute Instance Dialog
	 *	@param frame parent frame
	 *	@param M_AttributeSetInstance_ID Product Attribute Set Instance id
	 * 	@param M_Product_ID Product id
	 * 	@param C_BPartner_ID b partner
	 * 	@param productWindow this is the product window (define Product Instance)
	 * 	@param AD_Column_ID column
	 * 	@param WindowNo window
	 */
	public VPAttributeDialog (Frame frame, int M_AttributeSetInstance_ID, 
		int M_Product_ID, int C_BPartner_ID, 
		boolean productWindow, int AD_Column_ID, int WindowNo)
	{
		super (frame, Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID") , true);
		log.config("M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID 
			+ ", M_Product_ID=" + M_Product_ID
			+ ", C_BPartner_ID=" + C_BPartner_ID
			+ ", ProductW=" + productWindow + ", Column=" + AD_Column_ID);
		m_WindowNo = Env.createWindowNo (this);
		m_M_AttributeSetInstance_ID = M_AttributeSetInstance_ID;
		m_M_Product_ID = M_Product_ID;
		m_C_BPartner_ID = C_BPartner_ID;
		m_productWindow = productWindow;
		m_AD_Column_ID = AD_Column_ID;
		m_WindowNoParent = WindowNo;

		//get columnName from ad_column
 	 	m_columnName = DB.getSQLValueString(null, "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID = ?", m_AD_Column_ID);
 	 	if (m_columnName == null || m_columnName.trim().length() == 0)
 	 	{
 	 		//fallback
 	 		m_columnName = "M_AttributeSetInstance_ID";
 	 	}
 	 	
		try
		{
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VPAttributeDialog" + ex);
		}
		//	Dynamic Init
		if (!initAttributes ())
		{
			dispose();
			return;
		}
		AEnv.showCenterWindow(frame, this);
	}	//	VPAttributeDialog

	private int						m_WindowNo;
	private MAttributeSetInstance	m_masi;
	private int 					m_M_AttributeSetInstance_ID;
	private int 					m_M_Locator_ID;
	private String					m_M_AttributeSetInstanceName;
	private int 					m_M_Product_ID;
	private int						m_C_BPartner_ID;
	private int						m_AD_Column_ID;
	private int						m_WindowNoParent;
	/**	Enter Product Attributes		*/
	private boolean					m_productWindow = false;
	/**	Change							*/
	private boolean					m_changed = false;
	
	private CLogger					log = CLogger.getCLogger(getClass());
	/** Row Counter					*/
	private int						m_row = 0;
	/** List of Editors				*/
	private ArrayList<CEditor>		m_editors = new ArrayList<CEditor>();
	/** Length of Instance value (40)	*/
	private static final int		INSTANCE_VALUE_LENGTH = 40;

	private CCheckBox	cbNewEdit = new CCheckBox();
	private CButton		bSelect = new CButton(Env.getImageIcon("PAttribute16.gif")); 
	//	Lot
	private VString fieldLotString = new VString ("Lot", false, false, true, 20, 20, null, null);
	private CComboBox fieldLot = null;
	private CButton bLot = new CButton(Msg.getMsg (Env.getCtx(), "New"));
	//	Lot Popup
	JPopupMenu 					popupMenu = new JPopupMenu();
	private CMenuItem 			mZoom;
	//	Ser No
	private VString fieldSerNo = new VString ("SerNo", false, false, true, 20, 20, null, null);
	private CButton bSerNo = new CButton(Msg.getMsg (Env.getCtx(), "New"));
	//	Date
	private VDate fieldGuaranteeDate = new VDate ("GuaranteeDate", false, false, true, DisplayType.Date, Msg.translate(Env.getCtx(), "GuaranteeDate"));
	//
	private CTextField fieldDescription = new CTextField (20);
	//
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel centerPanel = new CPanel();
	private ALayout centerLayout = new ALayout(5,5, true);
	private ConfirmPanel confirmPanel = new ConfirmPanel (true);
	
	private String m_columnName = null;

	/**
	 *	Layout
	 * 	@throws Exception
	 */
	private void jbInit () throws Exception
	{
		this.getContentPane().setLayout(mainLayout);
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		centerPanel.setLayout(centerLayout);
		//
		confirmPanel.addActionListener(this);
	}	//	jbInit

	/**
	 *	Dyanmic Init.
	 *  @return true if initialized
	 */
	private boolean initAttributes ()
	{
		if (m_M_Product_ID == 0 && !m_productWindow)
			return false;
		
		MAttributeSet as = null;
		
		if (m_M_Product_ID != 0)
		{
			//	Get Model
			m_masi = MAttributeSetInstance.get(Env.getCtx(), m_M_AttributeSetInstance_ID, m_M_Product_ID);
			if (m_masi == null)
			{
				log.severe ("No Model for M_AttributeSetInstance_ID=" + m_M_AttributeSetInstance_ID + ", M_Product_ID=" + m_M_Product_ID);
				return false;
			}
			Env.setContext(Env.getCtx(), m_WindowNo, "M_AttributeSet_ID", m_masi.getM_AttributeSet_ID());
	
			//	Get Attribute Set
			as = m_masi.getMAttributeSet();
		}
		else 
		{
			int M_AttributeSet_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNoParent, "M_AttributeSet_ID");
			m_masi = new MAttributeSetInstance (Env.getCtx(), 0, M_AttributeSet_ID, null);
			as = m_masi.getMAttributeSet();
		}
		
		//	Product has no Attribute Set
		if (as == null)		
		{
			ADialog.error(m_WindowNo, this, "PAttributeNoAttributeSet");
			return false;
		}
		//	Product has no Instance Attributes
		if (!m_productWindow && !as.isInstanceAttribute())
		{
			ADialog.error(m_WindowNo, this, "PAttributeNoInstanceAttribute");
			return false;
		}

		//	Show Product Attributes
		if (m_productWindow)
		{
			MAttribute[] attributes = as.getMAttributes (false);
			log.fine ("Product Attributes=" + attributes.length);
			for (int i = 0; i < attributes.length; i++)
				addAttributeLine (attributes[i], true, !m_productWindow);
		}
		else	//	Set Instance Attributes
		{
			//	New/Edit - Selection
			if (m_M_AttributeSetInstance_ID == 0)		//	new
				cbNewEdit.setText(Msg.getMsg(Env.getCtx(), "NewRecord"));
			else
				cbNewEdit.setText(Msg.getMsg(Env.getCtx(), "EditRecord"));
			cbNewEdit.addActionListener(this);
			centerPanel.add(cbNewEdit, new ALayoutConstraint(m_row++,0));
			bSelect.setText(Msg.getMsg(Env.getCtx(), "SelectExisting"));
			bSelect.addActionListener(this);
			centerPanel.add(bSelect, null);
			//	All Attributes
			MAttribute[] attributes = as.getMAttributes (true);
			log.fine ("Instance Attributes=" + attributes.length);
			for (int i = 0; i < attributes.length; i++)
				addAttributeLine (attributes[i], false, false);
		}

		//	Lot
		if (!m_productWindow && as.isLot())
		{
			CLabel label = new CLabel (Msg.translate(Env.getCtx(), "Lot"));
			label.setLabelFor (fieldLotString);
			centerPanel.add(label, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fieldLotString, null);
			fieldLotString.setText (m_masi.getLot());
			//	M_Lot_ID
		//	int AD_Column_ID = 9771;	//	M_AttributeSetInstance.M_Lot_ID
		//	fieldLot = new VLookup ("M_Lot_ID", false,false, true, 
		//		MLookupFactory.get(Env.getCtx(), m_WindowNo, 0, AD_Column_ID, DisplayType.TableDir));
			String sql = "SELECT M_Lot_ID, Name "
				+ "FROM M_Lot l "
				+ "WHERE EXISTS (SELECT M_Product_ID FROM M_Product p "
					+ "WHERE p.M_AttributeSet_ID=" + m_masi.getM_AttributeSet_ID()
					+ " AND p.M_Product_ID=l.M_Product_ID)";
			fieldLot = new CComboBox(DB.getKeyNamePairs(sql, true));
			label = new CLabel (Msg.translate(Env.getCtx(), "M_Lot_ID"));
			label.setLabelFor (fieldLot);
			centerPanel.add(label, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fieldLot, null);
			if (m_masi.getM_Lot_ID() != 0)
			{
				for (int i = 1; i < fieldLot.getItemCount(); i++)
				{
					KeyNamePair pp = (KeyNamePair)fieldLot.getItemAt(i);
					if (pp.getKey() == m_masi.getM_Lot_ID())
					{
						fieldLot.setSelectedIndex(i);
						fieldLotString.setEditable(false);
						break;
					} 
				}
			}
			fieldLot.addActionListener(this);
			//	New Lot Button
			if (m_masi.getMAttributeSet().getM_LotCtl_ID() != 0)
			{
				if (MRole.getDefault().isTableAccess(MLot.Table_ID, false)
					&& MRole.getDefault().isTableAccess(MLotCtl.Table_ID, false)
					&& !m_masi.isExcludeLot(m_AD_Column_ID, Env.isSOTrx(Env.getCtx(), m_WindowNoParent)))
				{
					centerPanel.add(bLot, null);
					bLot.addActionListener(this);
				}
			}
			//	Popup 
			fieldLot.addMouseListener(new VPAttributeDialog_mouseAdapter(this));    //  popup
			mZoom = new CMenuItem(Msg.getMsg(Env.getCtx(), "Zoom"), Env.getImageIcon("Zoom16.gif"));
			mZoom.addActionListener(this);
			popupMenu.add(mZoom);
		}	//	Lot

		//	SerNo
		if (!m_productWindow && as.isSerNo())
		{
			CLabel label = new CLabel (Msg.translate(Env.getCtx(), "SerNo"));
			label.setLabelFor(fieldSerNo);
			fieldSerNo.setText(m_masi.getSerNo());
			centerPanel.add(label, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fieldSerNo, null);
			//	New SerNo Button
			if (m_masi.getMAttributeSet().getM_SerNoCtl_ID() != 0)
			{
				if (MRole.getDefault().isTableAccess(MSerNoCtl.Table_ID, false)
					&& !m_masi.isExcludeSerNo(m_AD_Column_ID, Env.isSOTrx(Env.getCtx(), m_WindowNoParent)))
				{
					centerPanel.add(bSerNo, null);
					bSerNo.addActionListener(this);
				}
			}
		}	//	SerNo

		//	GuaranteeDate
		if (!m_productWindow && as.isGuaranteeDate())
		{
			CLabel label = new CLabel (Msg.translate(Env.getCtx(), "GuaranteeDate"));
			label.setLabelFor(fieldGuaranteeDate);
			if (m_M_AttributeSetInstance_ID == 0)
				fieldGuaranteeDate.setValue(m_masi.getGuaranteeDate(true));
			else
				fieldGuaranteeDate.setValue(m_masi.getGuaranteeDate());
			centerPanel.add(label, new ALayoutConstraint(m_row++,0));
			centerPanel.add(fieldGuaranteeDate, null);
		}	//	GuaranteeDate

		if (m_row == 0)
		{
			ADialog.error(m_WindowNo, this, "PAttributeNoInfo");
			return false;
		}

		//	New/Edit Window
		if (!m_productWindow)
		{
			cbNewEdit.setSelected(m_M_AttributeSetInstance_ID == 0);
			cmd_newEdit();
		}

		//	Attribute Set Instance Description
		CLabel label = new CLabel (Msg.translate(Env.getCtx(), "Description"));
		label.setLabelFor(fieldDescription);
		fieldDescription.setText(m_masi.getDescription());
		fieldDescription.setEditable(false);
		centerPanel.add(label, new ALayoutConstraint(m_row++,0));
		centerPanel.add(fieldDescription, null);

		//	Window usually to wide (??)
		Dimension dd = centerPanel.getPreferredSize();
		dd.width = Math.min(500, dd.width);
		centerPanel.setPreferredSize(dd);
		return true;
	}	//	initAttribute

	/**
	 * 	Add Attribute Line
	 *	@param attribute attribute
	 * 	@param product product level attribute
	 * 	@param readOnly value is read only
	 */
	private void addAttributeLine (MAttribute attribute, boolean product, boolean readOnly)
	{
		log.fine(attribute + ", Product=" + product + ", R/O=" + readOnly);
		CLabel label = new CLabel (attribute.getName());
		if (product)
			label.setFont(new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize()));
		if (attribute.getDescription() != null)
			label.setToolTipText(attribute.getDescription());
		centerPanel.add(label, new ALayoutConstraint(m_row++,0));
		//
		MAttributeInstance instance = attribute.getMAttributeInstance (m_M_AttributeSetInstance_ID);
		if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(attribute.getAttributeValueType()))
		{
			MAttributeValue[] values = attribute.getMAttributeValues();	//	optional = null
			CComboBox editor = new CComboBox(values);
			boolean found = false;
			if (instance != null)
			{
				for (int i = 0; i < values.length; i++)
				{
					if (values[i] != null && values[i].getM_AttributeValue_ID () == instance.getM_AttributeValue_ID ())
					{
						editor.setSelectedIndex (i);
						found = true;
						break;
					}
				}
				if (found)
					log.fine("Attribute=" + attribute.getName() + " #" + values.length + " - found: " + instance);
				else
					log.warning("Attribute=" + attribute.getName() + " #" + values.length + " - NOT found: " + instance);
			}	//	setComboBox
			else
				log.fine("Attribute=" + attribute.getName() + " #" + values.length + " no instance");
			label.setLabelFor(editor);
			centerPanel.add(editor, null);
			if (readOnly)
				editor.setEnabled(false);
			else
				m_editors.add (editor);
		}
		else if (MAttribute.ATTRIBUTEVALUETYPE_Number.equals(attribute.getAttributeValueType()))
		{
			VNumber editor = new VNumber(attribute.getName(), attribute.isMandatory(), 
				false, true, DisplayType.Number, attribute.getName());
			if (instance != null)
				editor.setValue(instance.getValueNumber());
			else
				editor.setValue(Env.ZERO);
			label.setLabelFor(editor);
			centerPanel.add(editor, null);
			if (readOnly)
				editor.setEnabled(false);
			else
				m_editors.add (editor);
		}
		else	//	Text Field
		{
			VString editor = new VString (attribute.getName(), attribute.isMandatory(), 
				false, true, 20, INSTANCE_VALUE_LENGTH, null, null);
			if (instance != null)
				editor.setText(instance.getValue());
			label.setLabelFor(editor);
			centerPanel.add(editor, null);
			if (readOnly)
				editor.setEnabled(false);
			else
				m_editors.add (editor);
		}
	}	//	addAttributeLine

	/**
	 *	dispose
	 */
	public void dispose()
	{
		removeAll();
		Env.clearWinContext(m_WindowNo);
		//
		Env.setContext(Env.getCtx(), m_WindowNo, Env.TAB_INFO, m_columnName, 
			String.valueOf(m_M_AttributeSetInstance_ID));
		Env.setContext(Env.getCtx(), m_WindowNo, Env.TAB_INFO, "M_Locator_ID", 
			String.valueOf(m_M_Locator_ID));
		//
		super.dispose();
	}	//	dispose

	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		//	Select Instance
		if (e.getSource() == bSelect)
		{
			if (cmd_select())
				dispose();
		}
		//	New/Edit
		else if (e.getSource() == cbNewEdit)
		{
			cmd_newEdit();
		}
		//	Select Lot from existing
		else if (e.getSource() == fieldLot)
		{
			KeyNamePair pp = (KeyNamePair)fieldLot.getSelectedItem();
			if (pp != null && pp.getKey() != -1)
			{
				fieldLotString.setText(pp.getName());
				fieldLotString.setEditable(false);
				m_masi.setM_Lot_ID(pp.getKey());
			}
			else
			{
				fieldLotString.setEditable(true);
				m_masi.setM_Lot_ID(0);
			}
		}
		//	Create New Lot
		else if (e.getSource() == bLot)
		{
			KeyNamePair pp = m_masi.createLot(m_M_Product_ID);
			if (pp != null)
			{
				fieldLot.addItem(pp);
				fieldLot.setSelectedItem(pp);				
			}
		}
		//	Create New SerNo
		else if (e.getSource() == bSerNo)
		{
			fieldSerNo.setText(m_masi.getSerNo(true));
		}
		
		//	OK
		else if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (saveSelection())
				dispose();
		}
		//	Cancel
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
		{
			m_changed = false;
			m_M_AttributeSetInstance_ID = 0;
			m_M_Locator_ID = 0;
			dispose();
		}
		//	Zoom M_Lot
		else if (e.getSource() == mZoom)
		{
			cmd_zoom();
		}
		else
			log.log(Level.SEVERE, "not found - " + e);
	}	//	actionPerformed

	/**
	 * 	Instance Selection Button
	 * 	@return true if selected
	 */
	private boolean cmd_select()
	{
		log.config("");
		
		int M_Warehouse_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNoParent, "M_Warehouse_ID");
		
		int C_DocType_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNoParent, "C_DocType_ID");
		if (C_DocType_ID > 0) {
			MDocType doctype = new MDocType (Env.getCtx(), C_DocType_ID, null);
			String docbase = doctype.getDocBaseType();
			if (docbase.equals(MDocType.DOCBASETYPE_MaterialReceipt))
				M_Warehouse_ID = 0;
		}
		
		// teo_sarca [ 1564520 ] Inventory Move: can't select existing attributes
		// Trifon - Always read Locator from Context. There are too many windows to read explicitly one by one.
		int M_Locator_ID = 0;
		M_Locator_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNoParent, X_M_MovementLine.COLUMNNAME_M_Locator_ID, true); // only window
		
		String title = "";
		//	Get Text
		String sql = "SELECT p.Name, w.Name, w.M_Warehouse_ID FROM M_Product p, M_Warehouse w "
			+ "WHERE p.M_Product_ID=? AND w.M_Warehouse_ID"
				+ (M_Locator_ID <= 0 ? "=?" : " IN (SELECT M_Warehouse_ID FROM M_Locator where M_Locator_ID=?)"); // teo_sarca [ 1564520 ]
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, m_M_Product_ID);
			pstmt.setInt(2, M_Locator_ID <= 0 ? M_Warehouse_ID : M_Locator_ID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				title = ": " + rs.getString(1) + " - " + rs.getString(2);
				M_Warehouse_ID = rs.getInt(3); // fetch the actual warehouse - teo_sarca [ 1564520 ]
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//		
		PAttributeInstance pai = new PAttributeInstance(this, title, 
			M_Warehouse_ID, M_Locator_ID, m_M_Product_ID, m_C_BPartner_ID);
		if (pai.getM_AttributeSetInstance_ID() != -1)
		{
			m_M_AttributeSetInstance_ID = pai.getM_AttributeSetInstance_ID();
			m_M_AttributeSetInstanceName = pai.getM_AttributeSetInstanceName();
			m_M_Locator_ID = pai.getM_Locator_ID();
			m_changed = true;
			return true;
		}
		return false;
	}	//	cmd_select

	/**
	 * 	Instance New/Edit
	 */
	private void cmd_newEdit()
	{
		boolean rw = cbNewEdit.isSelected();
		log.config("R/W=" + rw + " " + m_masi);
		//
		fieldLotString.setEditable(rw && m_masi.getM_Lot_ID()==0);
		if (fieldLot != null)
			fieldLot.setReadWrite(rw);
		bLot.setReadWrite(rw);
		fieldSerNo.setReadWrite(rw);
		bSerNo.setReadWrite(rw);
		fieldGuaranteeDate.setReadWrite(rw);
		//
		for (int i = 0; i < m_editors.size(); i++)
		{
			CEditor editor = (CEditor)m_editors.get(i);
			editor.setReadWrite(rw);
		}	
	}	//	cmd_newEdit

	/**
	 * 	Zoom M_Lot
	 */
	private void cmd_zoom()
	{
		int M_Lot_ID = 0;
		KeyNamePair pp = (KeyNamePair)fieldLot.getSelectedItem();
		if (pp != null)
			M_Lot_ID = pp.getKey();
		MQuery zoomQuery = new MQuery("M_Lot");
		zoomQuery.addRestriction("M_Lot_ID", MQuery.EQUAL, M_Lot_ID);
		log.info(zoomQuery.toString());
		//
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//
		int AD_Window_ID = 257;		//	Lot
		AWindow frame = new AWindow();
		if (frame.initWindow(AD_Window_ID, zoomQuery))
		{
			this.setVisible(false);
			this.setModal (false);	//	otherwise blocked
			this.setVisible(true);
			AEnv.addToWindowManager(frame);
			AEnv.showScreen(frame, SwingConstants.EAST);
		}
		//  async window - not able to get feedback
		frame = null;
		//
		setCursor(Cursor.getDefaultCursor());
	}	//	cmd_zoom

	/**
	 *	Save Selection
	 *	@return true if saved
	 */
	private boolean saveSelection()
	{
		log.info("");
		MAttributeSet as = m_masi.getMAttributeSet();
		if (as == null)
			return true;
		//
		m_changed = false;
		String mandatory = "";
		if (!m_productWindow && as.isLot())
		{
			log.fine("Lot=" + fieldLotString.getText ());
			String text = fieldLotString.getText();
			m_masi.setLot (text);
			if (as.isLotMandatory() && (text == null || text.length() == 0))
				mandatory += " - " + Msg.translate(Env.getCtx(), "Lot");
			m_changed = true;
		}	//	Lot
		if (!m_productWindow && as.isSerNo())
		{
			log.fine("SerNo=" + fieldSerNo.getText());
			String text = fieldSerNo.getText();
			m_masi.setSerNo(text);
			if (as.isSerNoMandatory() && (text == null || text.length() == 0))
				mandatory += " - " + Msg.translate(Env.getCtx(), "SerNo");
			m_changed = true;
		}	//	SerNo
		if (!m_productWindow && as.isGuaranteeDate())
		{
			log.fine("GuaranteeDate=" + fieldGuaranteeDate.getValue());
			Timestamp ts = (Timestamp)fieldGuaranteeDate.getValue();
			m_masi.setGuaranteeDate(ts);
			if (as.isGuaranteeDateMandatory() && ts == null)
				mandatory += " - " + Msg.translate(Env.getCtx(), "GuaranteeDate");
			m_changed = true;
		}	//	GuaranteeDate

		//	***	Save Attributes ***
		//	New Instance
		if (m_changed || m_masi.getM_AttributeSetInstance_ID() == 0)
		{
			m_masi.save ();
			m_M_AttributeSetInstance_ID = m_masi.getM_AttributeSetInstance_ID ();
			m_M_AttributeSetInstanceName = m_masi.getDescription();
		}

		//	Save Instance Attributes
		MAttribute[] attributes = as.getMAttributes(!m_productWindow);
		for (int i = 0; i < attributes.length; i++)
		{
			if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(attributes[i].getAttributeValueType()))
			{
				CComboBox editor = (CComboBox)m_editors.get(i);
				MAttributeValue value = (MAttributeValue)editor.getSelectedItem();
				log.fine(attributes[i].getName() + "=" + value);
				if (attributes[i].isMandatory() && value == null)
					mandatory += " - " + attributes[i].getName();
				attributes[i].setMAttributeInstance(m_M_AttributeSetInstance_ID, value);
			}
			else if (MAttribute.ATTRIBUTEVALUETYPE_Number.equals(attributes[i].getAttributeValueType()))
			{
				VNumber editor = (VNumber)m_editors.get(i);
				BigDecimal value = (BigDecimal)editor.getValue();
				log.fine(attributes[i].getName() + "=" + value);
				if (attributes[i].isMandatory() && value == null)
					mandatory += " - " + attributes[i].getName();
				attributes[i].setMAttributeInstance(m_M_AttributeSetInstance_ID, value);
			}
			else
			{
				VString editor = (VString)m_editors.get(i);
				String value = editor.getText();
				log.fine(attributes[i].getName() + "=" + value);
				if (attributes[i].isMandatory() && (value == null || value.length() == 0))
					mandatory += " - " + attributes[i].getName();
				attributes[i].setMAttributeInstance(m_M_AttributeSetInstance_ID, value);
			}
			m_changed = true;
		}	//	for all attributes
		
		//	Save Model
		if (m_changed)
		{
			m_masi.setDescription ();
			m_masi.save ();
		}
		m_M_AttributeSetInstance_ID = m_masi.getM_AttributeSetInstance_ID ();
		m_M_AttributeSetInstanceName = m_masi.getDescription();
		//
		if (mandatory.length() > 0)
		{
			ADialog.error(m_WindowNo, this, "FillMandatory", mandatory);
			return false;
		}
		return true;
	}	//	saveSelection

	
	/**************************************************************************
	 * 	Get Instance ID
	 * 	@return Instance ID
	 */
	public int getM_AttributeSetInstance_ID()
	{
		return m_M_AttributeSetInstance_ID;
	}	//	getM_AttributeSetInstance_ID

	/**
	 * 	Get Instance Name
	 * 	@return Instance Name
	 */
	public String getM_AttributeSetInstanceName()
	{
		return m_M_AttributeSetInstanceName;
	}	//	getM_AttributeSetInstanceName
	
	/**
	 * Get Locator ID
	 * @return M_Locator_ID
	 */
	public int getM_Locator_ID()
	{
		return m_M_Locator_ID; 
	}

	/**
	 * 	Value Changed
	 *	@return true if changed
	 */
	public boolean isChanged()
	{
		return m_changed;
	}	//	isChanged

} //	VPAttributeDialog
