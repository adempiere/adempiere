/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2019 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.compiere.grid.ed;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import org.adempiere.plaf.AdempierePAttributeUI;
import org.compiere.apps.search.InfoPAttribute;
import org.compiere.apps.search.InfoProduct;
import org.compiere.model.GridTab;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MProduct;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.SwingEnv;

/**
 *  Product Attribute Set Instance Editor
 *
 *  @author Jorg Janke
 *  @version $Id: VPAttribute.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1895041 ] NPE when move product with attribute set
 * 			<li>BF [ 1770177 ] Inventory Move Locator Error - integrated MGrigioni bug fix
 * 			<li>BF [ 2011222 ] ASI Dialog is reseting locator
 * 
 * @author Michael McKay, 
 * 		<li>ADEMPIERE-72 VLookup and Info Window improvements
 * 					https://adempiere.atlassian.net/browse/ADEMPIERE-72
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 */
public class VPAttribute extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1823370077523962901L;
	
	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "PAttributeUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(VPAttribute.class);
		
	/**
	 *	IDE Constructor
	 */
	public VPAttribute()
	{
		this (null, false, false, true, 0, null, false);
	}	//	VAssigment

	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 */
	public VPAttribute (boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MPAttributeLookup lookup, boolean searchOnly)
	{
		this(null, mandatory, isReadOnly, isUpdateable, WindowNo, lookup, searchOnly);
	}
	
	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 */
	public VPAttribute (GridTab gridTab, boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MPAttributeLookup lookup, boolean searchOnly)
	{
		this(gridTab, mandatory, isReadOnly, isUpdateable, WindowNo, lookup, searchOnly, false);
	}
	
	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updateable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VPAttribute (GridTab gridTab, boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int WindowNo, MPAttributeLookup lookup, boolean searchOnly, boolean tableCellEditor)
	{
		super("M_AttributeSetInstance_ID", mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		textEditor = (CTextField) ((AdempierePAttributeUI) getUI()).getEditorComponent();
		button = ((AdempierePAttributeUI) getUI()).getButtonComponent();
		
		textEditor.setName("VPAttribute Text - " + m_columnName);
		button.setName("VPAttribute Button - " + m_columnName);
		
		this.gridTab = gridTab; // added for processCallout
		windowNo = WindowNo;
		attributeLookup = lookup;
		c_bpartner_id = Env.getContextAsInt(Env.getCtx(), WindowNo, "C_BPartner_ID");
		isSearchOnly = searchOnly;
		
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "PAttribute"), Env.getImageIcon("Zoom16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);
		
	}	//	VPAttribute
	
	
	/** Attribute Where Clause  */
	private String pAttributeWhere = null;
	
	/** Column Name - fixed		*/
	private String				m_columnName = "M_AttributeSetInstance_ID";
	
	/** The Attribute Instance	*/
	private MPAttributeLookup	attributeLookup;

	/** The Text Field          */
	private CTextField			textEditor;
	/** The Button              */
	private CButton				button;

	private CMenuItem 			menuEditor;

	private int					windowNo;
	private int					c_bpartner_id;
	private boolean 			isSearchOnly;
	
	/** The Grid Tab * */
	private GridTab gridTab; // added for processCallout
	
	/**	Calling Window Info				*/
	private int					ad_column_id = 0;
	
	private String oldWhere = "";
	
	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = new Integer(0);
	
	private Integer currentValue;
	
	@Override
	public String setDisplayBasedOnValue(Object value)
	{
		if (value == null || NO_INSTANCE.equals(value))
		{
			currentValue = null;
			textEditor.setText("");
			pAttributeWhere = "";
			return "";
		}
		
		//	The same
		if (value.equals(currentValue)) 
			return textEditor.getText() == null ? "" : textEditor.getText();
		
		//	new value
		log.fine("Value=" + value);
		try {
			currentValue = (Integer) value;
		}
		catch (ClassCastException e)
		{
			this.setCurrentValueValid(false);
		}
		
		textEditor.setText(attributeLookup.getDisplay(currentValue));	//	loads value
		
		// The text can be long.  Use the tooltip to help display the info.
		textEditor.setToolTipText(textEditor.getText());
		
		pAttributeWhere = "EXISTS (SELECT * FROM M_Storage s "
				+ "WHERE s.M_AttributeSetInstance_ID=" + currentValue
				+ " AND s.M_Product_ID=p.M_Product_ID)";
		
		return textEditor.getText() == null ? "" : textEditor.getText();
		
	}	//	setDisplayBasedOnValue

	@Override
	public Object getCurrentValue()
	{
		
		return currentValue;
		
	}	//	getCurrentValue

	/**
	 * Get Attribute Where clause
	 * @return String
	 */
	public String getAttributeWhere()
	{
		
		return pAttributeWhere;
	
	}	//	getAttributeWhere()

	/**
	 * 	Get Display Value
	 *	@return info
	 */
	public String getDisplay()
	{
		
		return textEditor.getText();
		
	}	//	getDisplay
	
	/**
	 *  Action Listener Interface
	 *  @param listener listener
	 */
	public void addActionListener(ActionListener listener)
	{
		textEditor.addActionListener(listener);
	}   //  addActionListener

	/**
	 * 	Action Listener - start dialog
	 * 	@param e Event
	 */
	public void actionPerformed(ActionEvent e)
	{
		
		actionButton();
	}	//	actionPerformed

	private void actionButton() {
		if (!button.isEnabled ())
			return;
		button.setEnabled (false);
		//		
		int oldValueInt = currentValue == null ? 0 : currentValue.intValue ();
		int M_AttributeSetInstance_ID = oldValueInt;
		int M_Product_ID = 0;
		int M_ProductBOM_ID = 0;
		if (gridTab != null) {
			M_Product_ID = Env.getContextAsInt (Env.getCtx (), windowNo, gridTab.getTabNo(), "M_Product_ID");
			M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), windowNo, gridTab.getTabNo(), "M_ProductBOM_ID");
		} else {
			M_Product_ID = Env.getContextAsInt (Env.getCtx (), windowNo, "M_Product_ID");
			M_ProductBOM_ID = Env.getContextAsInt (Env.getCtx (), windowNo, "M_ProductBOM_ID");
		}
		int M_Locator_ID = -1;

		log.config("M_Product_ID=" + M_Product_ID + "/" + M_ProductBOM_ID
			+ ",M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID
			+ ", AD_Column_ID=" + ad_column_id);
		
		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = ad_column_id == 8418;		//	HARDCODED
		
		//	Exclude ability to enter ASI
		boolean exclude = false;
		
		if (M_Product_ID != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), M_Product_ID);
			int M_AttributeSet_ID = product.getM_AttributeSet_ID();
			if (M_AttributeSet_ID != 0)
			{
				MAttributeSet mas = MAttributeSet.get(Env.getCtx(), M_AttributeSet_ID);
				exclude = mas.excludeEntry(ad_column_id, Env.isSOTrx(Env.getCtx(), windowNo));
			}
		}
		
		boolean changed = false;
		if (M_ProductBOM_ID != 0)	//	Use BOM Component
			M_Product_ID = M_ProductBOM_ID;

		//  If the VPAttribute component is in a dialog, use the search
		//  This should only happen when the editor is in the InfoProduct window
		if (isSearchOnly)
		{	
			// The component is an element in a CPanel, which is part of a JPanel
			// which is in a JLayeredPane which is in ...  the InfoProduct window
			Container me = ((Container) this).getParent();
			while (me != null)
			{
				if (me instanceof InfoProduct)
					break;
				me = me.getParent();
			}
			// TODO - trap and throw an error if the InfoProduct parent is not found
			
			//  For the search, there is no currentValue.  The search
			//  provides a where clause that is used to find products with
			//  matching attribute set instances.
			InfoPAttribute ia = new InfoPAttribute((CDialog) me);
			pAttributeWhere = ia.getWhereClause();
			textEditor.setText(ia.getDisplay());
			// The text can be long.  Use the tooltip to help display the info.
			textEditor.setToolTipText(textEditor.getText());

			//  To signal to the infoProduct window that the whereClause is
			//  updated. We could use a vetoable change but an action event
			//  works just as well.
			ActionEvent ae = new ActionEvent(textEditor, 1001, "updated");
			//  TODO not the generally correct way to fire an event
			((InfoProduct) me).actionPerformed(ae);
		}
		else if (!productWindow && (M_Product_ID == 0 || exclude))
		{
			//  If this isn't the product window and there is no product
			//  or the field is excluded for this window
			//  then there can be no attribute set instance
			changed = true;
			M_AttributeSetInstance_ID = 0;
		}
		else
		{
			// Product window or some other window with a product
			VPAttributeDialog vad = new VPAttributeDialog (SwingEnv.getFrame (this), 
				M_AttributeSetInstance_ID, M_Product_ID, c_bpartner_id,
				productWindow, ad_column_id, windowNo, isReadWrite());
			if (vad.isChanged())
			{
				M_AttributeSetInstance_ID = vad.getM_AttributeSetInstance_ID();
				if (!productWindow && vad.getM_Locator_ID() > 0)
				{
					M_Locator_ID = vad.getM_Locator_ID();
				}
				changed = true;
			}
		}
		
		//	Set Value
		if (changed)
		{
			log.finest("Changed M_AttributeSetInstance_ID=" + M_AttributeSetInstance_ID);
			lookup.refresh();
				setDisplayBasedOnValue(M_AttributeSetInstance_ID);
				
			//  TODO - this is the wrong place for this - it is model info
			//	The locator ID may relate to another ASI on the tab so 
			//  setting the value should be in a controller that is 
			//  aware of the tab and other fields
			//  Possibly move to the callout?
			if (gridTab != null && M_Locator_ID > 0)
			{
				log.finest("Change M_Locator_ID="+M_Locator_ID);
				gridTab.setValue("M_Locator_ID", M_Locator_ID);
			}
			
		}	//	change
		button.setEnabled(true);
		requestFocus();
	}

	/**
	 * Has the field changed over time?
	 * @return true if the old value or the where clause is different than the current.
	 */
	@Override
	public boolean hasChanged() {

		if(pAttributeWhere != null)
			if(oldWhere != null)
				return !oldWhere.equals(pAttributeWhere);
			else
				return true;
		else  // m_pAttributeWhere is null
			if(oldWhere != null)
				return true;

		return super.hasChanged();

	}

	@Override
	public void set_oldValue() {
		
		super.set_oldValue();
		oldWhere = pAttributeWhere;
	
	}
	
	@Override
	public JComponent getComponent() {
		return textEditor;
	}

	@Override
	protected void handleInvalidValue() {
		actionButton();
	}

}	//	VPAttribute
