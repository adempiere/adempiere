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
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.adempiere.core.domains.models.X_M_MovementLine;
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
import org.compiere.model.MProduct;
import org.compiere.model.MQuery;
import org.compiere.model.MRole;
import org.compiere.model.MSerNoCtl;
import org.compiere.model.MWindow;
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
 *  
 *  @author Michael McKay (mjmckay)
 *  		<li>BF3468823 - Attribute Set Instance editor does not display
 * 			<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
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

	private boolean m_readWrite;
	
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
		boolean productWindow, int AD_Column_ID, int WindowNo, boolean readWrite)
	{
		super (frame, Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID") , true);
		log.config("M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID 
			+ ", M_Product_ID=" + M_Product_ID
			+ ", C_BPartner_ID=" + C_BPartner_ID
			+ ", ProductW=" + productWindow + ", Column=" + AD_Column_ID);
		windowNo = Env.createWindowNo (this);
		attributeSetInstanceId = M_AttributeSetInstance_ID;
		productId = M_Product_ID;
		partnerId = C_BPartner_ID;
		this.productWindow = productWindow;
		columnId = AD_Column_ID;
		windowNoParent = WindowNo;
		m_readWrite = readWrite;

		//get columnName from ad_column
 	 	m_columnName = DB.getSQLValueString(null, "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID = ?", columnId);
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

	private int 					windowNo;
	private MAttributeSetInstance 	attributeSetInstance;
	private int 					attributeSetInstanceId;
	private int 					locatorId;
	private String 					attributeSetInstanceName;
	private int 					productId;
	private int 					partnerId;
	private int 					columnId;
	private int 					windowNoParent;
	/**	Enter Product Attributes		*/
	private boolean 				productWindow = false;
	/**	Change							*/
	private boolean 				changed = false;
	
	private CLogger					log = CLogger.getCLogger(getClass());
	/** Row Counter					*/
	private int 					row = 0;
	/** List of Editors				*/
	private ArrayList<CEditor> 		editors = new ArrayList<CEditor>();
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
	private MProduct m_product;
	private boolean m_productASI;

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
		if (productId == 0 && !productWindow)
			return false;
		
		MAttributeSet as = null;
		
		if (productId != 0)
		{
			//	Get Model
			m_product = MProduct.get(Env.getCtx(), productId);
			if (m_product.getM_AttributeSetInstance_ID() > 0)
			{
				m_productASI = true;
				//  The product has an instance associated with it.
				if (attributeSetInstanceId != m_product.getM_AttributeSetInstance_ID())
				{
					log.fine("Different ASI than what is specified on Product!");
				}
			}
			else 
			{
				// Only show product attributes when in the product window.
				m_productASI = productWindow;
			}
			attributeSetInstance = MAttributeSetInstance.get(Env.getCtx(), attributeSetInstanceId, productId);
			if (attributeSetInstance == null)
			{
				log.severe ("No Model for M_AttributeSetInstance_ID=" + attributeSetInstanceId + ", M_Product_ID=" + productId);
				return false;
			}
			Env.setContext(Env.getCtx(), windowNo, "M_AttributeSet_ID", attributeSetInstance.getM_AttributeSet_ID());
			
			//	Get Attribute Set
			as = attributeSetInstance.getMAttributeSet();
		}
		else 
		{
			int M_AttributeSet_ID = Env.getContextAsInt(Env.getCtx(), windowNoParent, "M_AttributeSet_ID");
			attributeSetInstance = new MAttributeSetInstance (Env.getCtx(), 0, M_AttributeSet_ID, null);
			as = attributeSetInstance.getMAttributeSet();
		}
		
		//	Product has no Attribute Set
		if (as == null)		
		{
			//ADialog.error(m_WindowNo, this, "PAttributeNoAttributeSet");
			//return false;
		}

		//	BF3468823 Show Product Attributes
		//  Product attributes can be shown in any window but are read/write only in the product
		//  window.  Instance attributes are shown in any window but the product window and are
		//  always read/write.  The two are exclusive and can't co-exist.  
		if (!productWindow || !m_productASI)	//	Set Instance Attributes and dialog controls
		{
			if (!m_productASI)  // Instance attributes possible.  Set up controls.
			{
				//	New/Edit - Selection
				if (attributeSetInstanceId == 0)		//	new
					cbNewEdit.setText(Msg.getMsg(Env.getCtx(), "NewRecord"));
				else
					cbNewEdit.setText(Msg.getMsg(Env.getCtx(), "EditRecord"));
				cbNewEdit.addActionListener(this);
				centerPanel.add(cbNewEdit, new ALayoutConstraint(row++,0));
				bSelect.setText(Msg.getMsg(Env.getCtx(), "SelectExisting"));
				bSelect.addActionListener(this);
				centerPanel.add(bSelect, null);
			}
			//	Add the Instance Attributes if any.  If its a product attribute set
			//  this will do nothing.
			MAttribute[] attributes = as.getMAttributes (true);
			log.fine ("Instance Attributes=" + attributes.length);
			for (int i = 0; i < attributes.length; i++)
				addAttributeLine (attributes[i], false, false);
		}

                if (as != null)
                {
                        //  Product attributes can be shown in any window but are read/write in the Product window only.
                        //  This will do nothing if it is an instance attribute set. 
		MAttribute[] attributes = as.getMAttributes (false);  // False = product attribute instances
                        log.fine ("Product Attributes=" + attributes.length);
                        for (int i = 0; i < attributes.length; i++)
                                addAttributeLine (attributes[i], true, !productWindow);
                }
		//	Lot
		if ((!productWindow || !m_productASI) && as.isLot())
		{
			CLabel label = new CLabel (Msg.translate(Env.getCtx(), "Lot"));
			label.setLabelFor (fieldLotString);
			centerPanel.add(label, new ALayoutConstraint(row++,0));
			centerPanel.add(fieldLotString, null);
			fieldLotString.setText (attributeSetInstance.getLot());
			List<KeyNamePair> keyNamePairLotList = MLot.getByAttributeSetId(Env.getCtx(), attributeSetInstance.getM_AttributeSet_ID(), attributeSetInstance.get_TrxName())
					.stream()
					.map(lot -> new KeyNamePair(lot.getM_Lot_ID(), lot.getName()))
					.collect(Collectors.toList());
			fieldLot = new CComboBox(keyNamePairLotList.toArray());
			label = new CLabel (Msg.translate(Env.getCtx(), "M_Lot_ID"));
			label.setLabelFor (fieldLot);
			centerPanel.add(label, new ALayoutConstraint(row++,0));
			centerPanel.add(fieldLot, null);
			if (attributeSetInstance.getM_Lot_ID() != 0)
			{
				for (int i = 1; i < fieldLot.getItemCount(); i++)
				{
					KeyNamePair pp = (KeyNamePair)fieldLot.getItemAt(i);
					if (pp.getKey() == attributeSetInstance.getM_Lot_ID())
					{
						fieldLot.setSelectedIndex(i);
						fieldLotString.setEditable(false);
						break;
					} 
				}
			}
			fieldLot.addActionListener(this);
			//	New Lot Button
			if (attributeSetInstance.getMAttributeSet().getM_LotCtl_ID() != 0 && m_readWrite)
			{
				if (MRole.getDefault().isTableAccess(MLot.Table_ID, false)
					&& MRole.getDefault().isTableAccess(MLotCtl.Table_ID, false)
					&& !attributeSetInstance.isExcludeLot(columnId, Env.isSOTrx(Env.getCtx(), windowNoParent)))
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
		if ((!productWindow || !m_productASI) && as.isSerNo())
		{
			CLabel label = new CLabel (Msg.translate(Env.getCtx(), "SerNo"));
			label.setLabelFor(fieldSerNo);
			fieldSerNo.setText(attributeSetInstance.getSerNo());
			centerPanel.add(label, new ALayoutConstraint(row++,0));
			centerPanel.add(fieldSerNo, null);
			//	New SerNo Button
			if (attributeSetInstance.getMAttributeSet().getM_SerNoCtl_ID() != 0 && m_readWrite)
			{
				if (MRole.getDefault().isTableAccess(MSerNoCtl.Table_ID, false)
					&& !attributeSetInstance.isExcludeSerNo(columnId, Env.isSOTrx(Env.getCtx(), windowNoParent)))
				{
					centerPanel.add(bSerNo, null);
					bSerNo.addActionListener(this);
				}
			}
		}	//	SerNo

		//	GuaranteeDate
		if ((!productWindow || !m_productASI) && as.isGuaranteeDate())
		{
			CLabel label = new CLabel (Msg.translate(Env.getCtx(), "GuaranteeDate"));
			label.setLabelFor(fieldGuaranteeDate);
			if (attributeSetInstanceId == 0)
				fieldGuaranteeDate.setValue(attributeSetInstance.getGuaranteeDate(true));
			else
				fieldGuaranteeDate.setValue(attributeSetInstance.getGuaranteeDate());
			centerPanel.add(label, new ALayoutConstraint(row++,0));
			centerPanel.add(fieldGuaranteeDate, null);
		}	//	GuaranteeDate

		if (row == 0)
		{
			ADialog.error(windowNo, this, "PAttributeNoInfo");
			//return false;
		}

		//	New/Edit Window
		if ((!productWindow || !m_productASI) && columnId != 0 && m_readWrite)
		{
			cbNewEdit.setSelected(attributeSetInstanceId == 0);
			cmd_newEdit();
		}

		//	Attribute Set Instance Description
		CLabel label = new CLabel (Msg.translate(Env.getCtx(), "Description"));
		label.setLabelFor(fieldDescription);
		fieldDescription.setText(attributeSetInstance.getDescription());
		fieldDescription.setEditable(false);
		centerPanel.add(label, new ALayoutConstraint(row++,0));
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
		centerPanel.add(label, new ALayoutConstraint(row++,0));
		//
		MAttributeInstance instance = attribute.getMAttributeInstance (attributeSetInstanceId);
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
				editors.add (editor);
		}
		else if (MAttribute.ATTRIBUTEVALUETYPE_Number.equals(attribute.getAttributeValueType()))
		{
			VNumber editor = new VNumber(attribute.getName(), attribute.isMandatory(), 
				readOnly, !readOnly, DisplayType.Number, attribute.getName());
			if (instance != null)
				editor.setValue(instance.getValueNumber());
			else
				editor.setValue(Env.ZERO);
			label.setLabelFor(editor);
			centerPanel.add(editor, null);
			if (readOnly)
				editor.setEnabled(false);
			else
				editors.add (editor);
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
				editors.add (editor);
		}
	}	//	addAttributeLine

	/**
	 *	dispose
	 */
	public void dispose()
	{
		removeAll();
		Env.clearWinContext(windowNo);
		//
		Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, m_columnName,
			String.valueOf(attributeSetInstanceId));
		Env.setContext(Env.getCtx(), windowNo, Env.TAB_INFO, "M_Locator_ID",
			String.valueOf(locatorId));
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
				attributeSetInstance.setM_Lot_ID(pp.getKey());
			}
			else
			{
				fieldLotString.setEditable(true);
				attributeSetInstance.setM_Lot_ID(0);
			}
		}
		//	Create New Lot
		else if (e.getSource() == bLot)
		{
			KeyNamePair pp = attributeSetInstance.createLot(productId);
			if (pp != null)
			{
				fieldLot.addItem(pp);
				fieldLot.setSelectedItem(pp);				
				fieldLotString.setText (attributeSetInstance.getLot());
				fieldLotString.setEditable(false);
			}
		}
		//	Create New SerNo
		else if (e.getSource() == bSerNo)
		{
			fieldSerNo.setText(attributeSetInstance.getSerNo(true));
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
			if (productWindow || !m_productASI)
			{
				changed = attributeSetInstanceId != 0;
				attributeSetInstanceId = 0;
				locatorId = 0;
			}
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
		
		int M_Warehouse_ID = Env.getContextAsInt(Env.getCtx(), windowNoParent, "M_Warehouse_ID");
		
		int C_DocType_ID = Env.getContextAsInt(Env.getCtx(), windowNoParent, "C_DocType_ID");
		if (C_DocType_ID > 0) {
			MDocType doctype = new MDocType (Env.getCtx(), C_DocType_ID, null);
			String docbase = doctype.getDocBaseType();
			// consider also old lot numbers at inventory
			if (docbase.equals(MDocType.DOCBASETYPE_MaterialReceipt)
				||  docbase.equals(MDocType.DOCBASETYPE_MaterialPhysicalInventory))
				M_Warehouse_ID = 0;
		}
		
		// teo_sarca [ 1564520 ] Inventory Move: can't select existing attributes
		// Trifon - Always read Locator from Context. There are too many windows to read explicitly one by one.
		int M_Locator_ID = 0;
		M_Locator_ID = Env.getContextAsInt(Env.getCtx(), windowNoParent, X_M_MovementLine.COLUMNNAME_M_Locator_ID, true); // only window
		
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
			pstmt.setInt(1, productId);
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
			M_Warehouse_ID, M_Locator_ID, productId, partnerId);
		//
		if (attributeSetInstanceId != pai.getM_AttributeSetInstance_ID() ||
				!(attributeSetInstanceId == 0 && pai.getM_AttributeSetInstance_ID() == -1))
		{
			changed = true;
			//
			if (pai.getM_AttributeSetInstance_ID() != -1)
			{
				attributeSetInstanceId = pai.getM_AttributeSetInstance_ID();
				attributeSetInstanceName = pai.getM_AttributeSetInstanceName();
				locatorId = pai.getM_Locator_ID();
			}
			else
			{
				attributeSetInstanceId = 0;
				attributeSetInstanceName = "";
				// Leave the locator alone
			}
		}
		return changed;
	}	//	cmd_select

	/**
	 * 	Instance New/Edit
	 */
	private void cmd_newEdit()
	{
		boolean rw = cbNewEdit.isSelected();
		log.config("R/W=" + rw + " " + attributeSetInstance);
		//
		fieldLotString.setEditable(rw && attributeSetInstance.getM_Lot_ID()==0);
		if (fieldLot != null)
			fieldLot.setReadWrite(rw);
		bLot.setReadWrite(rw);
		fieldSerNo.setReadWrite(rw);
		bSerNo.setReadWrite(rw);
		fieldGuaranteeDate.setReadWrite(rw);
		//
		for (int i = 0; i < editors.size(); i++)
		{
			CEditor editor = (CEditor) editors.get(i);
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
		int AD_Window_ID = MWindow.getWindow_ID("Lot");		//	Lot
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
		if(!m_readWrite)
			return true;
		
		log.info("");
		MAttributeSet as = attributeSetInstance.getMAttributeSet();
		if (as == null)
			return true;
		//
		changed = false;
		String mandatory = "";
		if ((!productWindow || !m_productASI) && as.isLot())
		{
			log.fine("Lot=" + fieldLotString.getText ());
			String text = fieldLotString.getText();
			attributeSetInstance.setLot (text);
			if (as.isLotMandatory() && (text == null || text.length() == 0))
				mandatory += " - " + Msg.translate(Env.getCtx(), "Lot");
			changed = true;
		}	//	Lot
		if ((!productWindow || !m_productASI) && as.isSerNo())
		{
			log.fine("SerNo=" + fieldSerNo.getText());
			String text = fieldSerNo.getText();
			attributeSetInstance.setSerNo(text);
			if (as.isSerNoMandatory() && (text == null || text.length() == 0))
				mandatory += " - " + Msg.translate(Env.getCtx(), "SerNo");
			changed = true;
		}	//	SerNo
		if ((!productWindow || !m_productASI) && as.isGuaranteeDate())
		{
			log.fine("GuaranteeDate=" + fieldGuaranteeDate.getValue());
			Timestamp ts = (Timestamp)fieldGuaranteeDate.getValue();
			attributeSetInstance.setGuaranteeDate(ts);
			if (as.isGuaranteeDateMandatory() && ts == null)
				mandatory += " - " + Msg.translate(Env.getCtx(), "GuaranteeDate");
			changed = true;
		}	//	GuaranteeDate

		//	***	Save Attributes ***
		//	New Instance
		if (changed || attributeSetInstance.getM_AttributeSetInstance_ID() == 0)
		{
			attributeSetInstance.save ();
			attributeSetInstanceId = attributeSetInstance.getM_AttributeSetInstance_ID ();
			attributeSetInstanceName = attributeSetInstance.getDescription();
		}

		//  Save attributes
		if (attributeSetInstanceId > 0 && m_readWrite) {
			//	Save Instance Attributes
			MAttribute[] attributes = as.getMAttributes(!m_productASI);
			for (int i = 0; i < attributes.length; i++)
			{
				if (MAttribute.ATTRIBUTEVALUETYPE_List.equals(attributes[i].getAttributeValueType()))
				{
					CComboBox editor = (CComboBox) editors.get(i);
					MAttributeValue value = (MAttributeValue)editor.getSelectedItem();
					log.fine(attributes[i].getName() + "=" + value);
					if (attributes[i].isMandatory() && value == null)
						mandatory += " - " + attributes[i].getName();
					attributes[i].setMAttributeInstance(attributeSetInstanceId, value);
				}
				else if (MAttribute.ATTRIBUTEVALUETYPE_Number.equals(attributes[i].getAttributeValueType()))
				{
					VNumber editor = (VNumber) editors.get(i);
					BigDecimal value = (BigDecimal)editor.getValue();
					log.fine(attributes[i].getName() + "=" + value);
					if (attributes[i].isMandatory() && value == null)
						mandatory += " - " + attributes[i].getName();
					//setMAttributeInstance doesn't work without decimal point
					if (value != null && value.scale() == 0)
						value = value.setScale(1, RoundingMode.HALF_UP);
					attributes[i].setMAttributeInstance(attributeSetInstanceId, value);
				}
				else
				{
					VString editor = (VString) editors.get(i);
					String value = editor.getText();
					log.fine(attributes[i].getName() + "=" + value);
					if (attributes[i].isMandatory() && (value == null || value.length() == 0))
						mandatory += " - " + attributes[i].getName();
					attributes[i].setMAttributeInstance(attributeSetInstanceId, value);
				}
			}
			changed = true;
		}	//	for all attributes
		
		//	Save Model
		if (changed)
		{
			attributeSetInstance.setDescription ();
			attributeSetInstance.save ();
		}
		attributeSetInstanceId = attributeSetInstance.getM_AttributeSetInstance_ID ();
		attributeSetInstanceName = attributeSetInstance.getDescription();
		//
		if (mandatory.length() > 0)
		{
			ADialog.error(windowNo, this, "FillMandatory", mandatory);
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
		return attributeSetInstanceId;
	}	//	getM_AttributeSetInstance_ID

	/**
	 * 	Get Instance Name
	 * 	@return Instance Name
	 */
	public String getM_AttributeSetInstanceName()
	{
		return attributeSetInstanceName;
	}	//	getM_AttributeSetInstanceName
	
	/**
	 * Get Locator ID
	 * @return M_Locator_ID
	 */
	public int getM_Locator_ID()
	{
		return locatorId;
	}

	/**
	 * 	Value Changed
	 *	@return true if changed
	 */
	public boolean isChanged()
	{
		return changed;
	}	//	isChanged

} //	VPAttributeDialog
