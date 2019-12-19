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

import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import org.adempiere.plaf.AdempiereLocationUI;
import org.compiere.model.GridTab;
import org.compiere.model.MLocation;
import org.compiere.model.MLocationLookup;
import org.compiere.swing.CButton;
import org.compiere.swing.CMenuItem;
import org.compiere.util.CLogger;
import org.compiere.util.DefaultContextProvider;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	Location Control (Address).  The location editor is a text field with two buttons.
 *  The text field does not accept input.  The value is defined by the location lookup
 *  based on the location id.
 *
 *  @author 	Jorg Janke
 *  @author victor.perez@e-evolution.com, www.e-evolution.com
 *  		<li>BF [ 3294610] The location should allow open a google map
 *  			https://sourceforge.net/tracker/?func=detail&atid=879335&aid=3294610&group_id=176962
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 146 ] Remove unnecessary class, add support for info to specific column
 *		@see https://github.com/adempiere/adempiere/issues/146
 *  @author Michael McKay, mckayERP@gmail.com
 *  	<li><a href="https://github.com/adempiere/adempiere/issues/2908">#2908</a>Updates to ADempiere Look and Feel
 *
 */
public class VLocation extends VEditorAbstract
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -288409058154703379L;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VLocation.class);

	/**
	 * Define the ui class ID. It will be added to the 
	 * look and feel to define the ui class to use.
	 */
	private final static String uiClassID = "LocationUI";

	@Override
    public String getUIClassID() {
        return uiClassID ;
    }
	
	/** The Text Field                  */
	private JTextField			textEditor;
	/** The Button                      */
	private CButton				openDialogButton;
	
	/** The "to map" Button                      */
	private CButton				openMapButton;

	private MLocationLookup		locationLookup;
	private MLocation 			currentLocation;


	//	Popup
	JPopupMenu 					popupMenu = new JPopupMenu();
	private CMenuItem 			mDelete;

	/** The Grid Tab */
	@SuppressWarnings("unused") // TODO
	private GridTab m_GridTab; // added for processCallout. 
	
	private Object currentValue;
	
			
	/**
	 *	Constructor
	 *
	 * 	@param columnName column name
	 * 	@param mandatory mandatory
	 * 	@param isReadOnly read only
	 * 	@param isUpdateable updateable
	 * 	@param mLocation location model
	 */
	public VLocation(String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MLocationLookup mLocation)
	{
		this(null, columnName, mandatory, isReadOnly, isUpdateable, mLocation);
	}
	
	/**
	 *	Constructor
	 * 
	 *  @param gridTab
	 * 	@param columnName column name
	 * 	@param mandatory mandatory
	 * 	@param isReadOnly read only
	 * 	@param isUpdateable updateable
	 * 	@param mLocation location model
	 */
	public VLocation(GridTab gridTab, String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MLocationLookup mLocation)
	{
		this(gridTab, columnName, mandatory, isReadOnly, isUpdateable, mLocation, false);
	}
	
	/**
	 *	Constructor
	 * 
	 *  @param gridTab
	 * 	@param columnName column name
	 * 	@param mandatory mandatory
	 * 	@param isReadOnly read only
	 * 	@param isUpdateable updateable
	 * 	@param mLocation location model
	 *  @param tableCellEditor true if the editor will be used in at table cell
	 */
	public VLocation(GridTab gridTab, String columnName, boolean mandatory, boolean isReadOnly, boolean isUpdateable,
		MLocationLookup mLocation, boolean tableCellEditor)
	{
		
		super(columnName, mandatory, isReadOnly, isUpdateable, tableCellEditor);
		
		m_GridTab = gridTab; // Not used?
		
		locationLookup = mLocation;
		
		getButtons();
		openMapButton.addActionListener(this);
		openDialogButton.addActionListener(this);
				
		textEditor = (JTextField) getEditorComponent();
		textEditor.setEditable(false);

	}	//	VLocation

	/**
	 *	Enable/disable
	 *  @param value true if ReadWrite
	 */
	@Override
	public void setReadWrite (boolean readWrite)
	{
		getButtons();
		super.setReadWrite(readWrite);
		openDialogButton.setReadWrite (readWrite);  // Same as enabled
		if (openDialogButton.isVisible() != readWrite)
			openDialogButton.setVisible (readWrite);
	}	//	setReadWrite

	private void getButtons() {
		
		if (openDialogButton == null)
			openDialogButton = ((AdempiereLocationUI) this.getUI()).getOpenDialogButton();
		
		if (openMapButton == null)
			openMapButton = ((AdempiereLocationUI) this.getUI()).getOpenMapButton();
		
	}

	/**
	 *	Set Editor to value
	 *  @param value value
	 */
	@Override
	public String setDisplayBasedOnValue(Object value)
	{
		currentValue = value;
		
		if (value == null)
		{
			currentLocation = null;
		}
		else
		{
			currentLocation = locationLookup.getLocation(value, null);
			
			if (currentLocation == null)
				super.setCurrentValueValid(false);
			
		}
		
		if (currentLocation == null)
		{
			textEditor.setText(null);
		}
		else
		{
			if (currentLocation.get_ID() == -1)
				textEditor.setText("<" + currentValue + ">");
			else
				textEditor.setText(currentLocation.toString());
		}
		
		openMapButton.setEnabled(currentValue != null);
		textEditor.setToolTipText(textEditor.getText());
		
		return textEditor.getText();

	}	//	setValue

	/**
	 *	Return Editor value
	 *  Deprecated, use getValue() instead.
	 *  @return value
	 */
	@Deprecated
	public int getC_Location_ID()
	{
		if (currentLocation == null)
			return 0;
		return currentLocation.getC_Location_ID();
	}	//	getC_Location_ID

	/**
	 *  Return Display Value
	 *  @return display value
	 */
	public String getDisplay()
	{
		return textEditor.getText();
	}   //  getDisplay

	/**
	 *	ActionListener - Button - Start Dialog
	 *  @param e ActionEvent
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		
		if (e.getSource() == mDelete)
		{
			currentLocation = null;        //  create new
			openDialog();
		}
		
		if (e.getSource() == openMapButton)
		{
			Env.startBrowser(DefaultContextProvider.GOOGLE_MAPS_URL_PREFIX + currentLocation.toString().replace(" ", "%20"));
			return;
		}
		
		if (e.getSource() == getEditorComponent()
				|| e.getSource() == openDialogButton)
		{
			openDialog();
		}
	}
	
	private void openDialog() 
	{
		//
		log.config( "actionPerformed - " + currentLocation);
		VLocationDialog ld = new VLocationDialog(Env.getFrame(this),
			Msg.getMsg(Env.getCtx(), "Location"), currentLocation);
		ld.setVisible(true);
		
		// Allow the dialog to return null
		MLocation newLocation = ld.getValue();
		Integer id = null;
		if (newLocation != null)
			id = newLocation.get_ID();
		setDisplayBasedOnValue(id);
		
	}	//	actionPerformed


	/**
	 * Send the focus to the openDialogButton
	 */
	@Override
	public JComponent getComponent() {
		return openDialogButton;
	}

	@Override
	protected Object getCurrentValue() {
		if (currentLocation == null)
			return null;
		return currentLocation.getC_Location_ID();
	}

	@Override
	protected void handleInvalidValue() {
		
		openDialog();
		
	}

}	//	VLocation
