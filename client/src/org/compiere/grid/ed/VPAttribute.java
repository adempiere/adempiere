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
import org.compiere.model.MColumn;
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
	 *  @param isUpdated isUpdated
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 */
	public VPAttribute (boolean mandatory,
						boolean isReadOnly,
						boolean isUpdated,
						int WindowNo,
						MPAttributeLookup lookup,
						boolean searchOnly)
	{
		this(null, mandatory, isReadOnly, isUpdated, WindowNo, lookup, searchOnly);
	}

	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdatable updatable
	 * 	@param windowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 */
	public VPAttribute (GridTab gridTab,
						boolean mandatory,
						boolean isReadOnly,
						boolean isUpdatable,
						int windowNo,
						MPAttributeLookup lookup,
						boolean searchOnly)
	{
		this(gridTab, mandatory, isReadOnly, isUpdatable, windowNo, lookup, searchOnly, false);
	}
	
	/**
	 *	Create Product Attribute Set Instance Editor.
	 *  @param gridTab
	 *  @param mandatory mandatory
	 *  @param isReadOnly read only
	 *  @param isUpdateable updatable
	 * 	@param WindowNo WindowNo
	 * 	@param lookup Model Product Attribute
	 *  @param searchOnly True if only used to search instances
	 *  @param tableCellEditor true if the editor will be used in a table cell
	 */
	public VPAttribute (GridTab gridTab, boolean mandatory, boolean isReadOnly, boolean isUpdateable, 
		int windowNo, MPAttributeLookup lookup, boolean searchOnly, boolean tableCellEditor)
	{
		super("M_AttributeSetInstance_ID", mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		textEditor = (CTextField) ((AdempierePAttributeUI) getUI()).getEditorComponent();
		button = ((AdempierePAttributeUI) getUI()).getButtonComponent();
		
		textEditor.setName("VPAttribute Text - " + columnName);
		button.setName("VPAttribute Button - " + columnName);
		
		this.gridTab = gridTab; // added for processCallout
		this.windowNo = windowNo;
		attributeLookup = lookup;
		partnerId = Env.getContextAsInt(Env.getCtx(), windowNo, "C_BPartner_ID");
		isSearchOnly = searchOnly;
		
		menuEditor = new CMenuItem(Msg.getMsg(Env.getCtx(), "PAttribute"), Env.getImageIcon("Zoom16.gif"));
		menuEditor.addActionListener(this);
		popupMenu.add(menuEditor);

	}	//	VPAttribute
	
	
	/** Attribute Where Clause  */
	private String attributeWhere = null;
	
	/** Column Name - fixed		*/
	private String 				columnName = "M_AttributeSetInstance_ID";
	
	/** The Attribute Instance	*/
	private MPAttributeLookup	attributeLookup;

	/** The Text Field          */
	private CTextField			textEditor;
	/** The Button              */
	private CButton				button;

	private CMenuItem 			menuEditor;

	private int					windowNo;
	private int					partnerId;
	private boolean 			isSearchOnly;
	
	/** The Grid Tab * */
	private GridTab gridTab; // added for processCallout
	
	/**	Calling Window Info				*/
	private int					columnId = 0;
	
	private String oldWhere = "";
	
	/**	No Instance Key					*/
	private static Integer		NO_INSTANCE = new Integer(0);
	
	private Integer currentValue;
	
	@Override
	public String setDisplayBasedOnValue(Object value)
	{
		if (value == null || NO_INSTANCE.equals(value))
		{
			currentValue = NO_INSTANCE;  // Set to zero, not null as would normally be expected. This is an artifact of 
										 // FIFO/LIFO tracking using AttributeSetInstances.  
			textEditor.setText("");
			attributeWhere = "";
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
		
		attributeWhere = "EXISTS (SELECT * FROM M_Storage s "
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
		
		return attributeWhere;
	
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
		int attributeSetInstanceId = oldValueInt;
		int productId= 0;
		int productBOMId = 0;
		if (gridTab != null) {
		    productId = Env.getContextAsInt (Env.getCtx (), windowNo, gridTab.getTabNo(), "M_Product_ID");
		    productBOMId = Env.getContextAsInt (Env.getCtx (), windowNo, gridTab.getTabNo(), "M_ProductBOM_ID");
		} else {
		    productId = Env.getContextAsInt (Env.getCtx (), windowNo, "M_Product_ID");
		    productBOMId = Env.getContextAsInt (Env.getCtx (), windowNo, "M_ProductBOM_ID");
		}
		int locatorId = -1;

		log.config("M_Product_ID=" + productId + "/" + productBOMId
			+ ",M_AttributeSetInstance_ID=" + attributeSetInstanceId
			+ ", AD_Column_ID=" + columnId);
		
		//	M_Product.M_AttributeSetInstance_ID = 8418
		boolean productWindow = columnId == 8418;		//	HARDCODED
		
		//	Exclude ability to enter ASI
		boolean exclude = false;
		
		if (productId != 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), productId);
			int attributeSetId = product.getM_AttributeSet_ID();
			if (attributeSetId != 0)
			{
				int tableId = MColumn.getTable_ID(Env.getCtx(), columnId, null);
				MAttributeSet mas = MAttributeSet.get(Env.getCtx(), attributeSetId);
				exclude = mas.excludeEntry(tableId, Env.isSOTrx(Env.getCtx(), this.windowNo));
			}
		}
		
		boolean changed = false;
		if (productBOMId != 0)	//	Use BOM Component
			productId = productBOMId;

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
			attributeWhere = ia.getWhereClause();
			String oldText = textEditor.getText();
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
		else if (!productWindow && (productId == 0 || exclude))
		{
			//  If this isn't the product window and there is no product
			//  or the field is excluded for this window
			//  then there can be no attribute set instance
			changed = true;
				attributeSetInstanceId = 0;
		}
		else
		{
			VPAttributeDialog vad = new VPAttributeDialog (SwingEnv.getFrame (this), 
				attributeSetInstanceId, productId, this.partnerId,
				productWindow,this.columnId, this.windowNo, isReadWrite());
			if (vad.isChanged())
			{
			    attributeSetInstanceId = vad.getM_AttributeSetInstance_ID();
				if (!productWindow && vad.getM_Locator_ID() > 0)
				{
						locatorId = vad.getM_Locator_ID();
				}
				changed = true;
			}
		}
		
		//	Set Value
		if (changed)
		{
			log.finest("Changed M_AttributeSetInstance_ID=" + attributeSetInstanceId);
			
			setDisplayBasedOnValue(attributeSetInstanceId);
				
			//  TODO - this is the wrong place for this - it is model info
			//	The locator ID may relate to another ASI on the tab so 
			//  setting the value should be in a controller that is 
			//  aware of the tab and other fields
			//  Possibly move to the callout?
			if (gridTab != null && locatorId > 0)
			{
				log.finest("Change M_Locator_ID="+locatorId);
				gridTab.setValue("M_Locator_ID", locatorId);
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

		if(attributeWhere != null)
			if(oldWhere != null)
				return !oldWhere.equals(attributeWhere);
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
		oldWhere = attributeWhere;
	
	}
	
	@Override
	public JComponent getComponent() {
		return textEditor;
	}

	@Override
	protected void handleInvalidValue() {
		actionButton();
	}

}