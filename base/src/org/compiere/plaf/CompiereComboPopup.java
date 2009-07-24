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
package org.compiere.plaf;

import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboPopup;

import org.compiere.swing.CComboBox;
import org.compiere.swing.CField;

/**
 *  Adempiere Combo Popup - allows to prevent the display of the popup
 *
 *  @author     Jorg Janke
 *  @version    $Id: AdempiereComboPopup.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CompiereComboPopup extends BasicComboPopup
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2031710222285745816L;

	/**
	 *  Constructor
	 *  @param combo
	 */
	public CompiereComboPopup(JComboBox combo)
	{
		super(combo);
	}   //  AdempiereComboPopup

	/**
	 *  Conditionally show the Popup.
	 *  If the combo is a CComboBox/CField, the return value of the
	 *  method displayPopup determines if the popup is actually displayed
	 *  @see CComboBox#displayPopup()
	 *  @see CField#displayPopup()
	 */
	public void show()
	{
		//  Check ComboBox if popup should be displayed
		if (comboBox instanceof CComboBox && !((CComboBox)comboBox).displayPopup())
			return;
		//  Check Field if popup should be displayed
		if (comboBox instanceof CField && !((CField)comboBox).displayPopup())
			return;
		super.show();
	}   //  show


	/**
	 *  Inform CComboBox and CField that Popup was hidden
	 *  @see CComboBox.hidingPopup
	 *  @see CField.hidingPopup
	 *
	public void hide()
	{
		super.hide();
		//  Inform ComboBox that popup was hidden
		if (comboBox instanceof CComboBox)
			(CComboBox)comboBox).hidingPopup();
		else if (comboBox instanceof CComboBox)
			(CComboBox)comboBox).hidingPopup();
	}   //  hided
	/**/
}   //  AdempiereComboPopup
