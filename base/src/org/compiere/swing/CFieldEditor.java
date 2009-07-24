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
package org.compiere.swing;

import java.awt.Component;

import javax.swing.ComboBoxEditor;
import javax.swing.JTextField;

/**
 *  Adempiere Field Editor.
 *
 *
 *  @author     Jorg Janke
 *  @version    $Id: CFieldEditor.java,v 1.2 2006/07/30 00:52:24 jjanke Exp $
 */
public class CFieldEditor extends JTextField implements ComboBoxEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1048599104686258148L;

	/**
	 *
	 */
	public CFieldEditor()
	{
	}

	/**
	 *  Return the component that should be added to the tree hierarchy
	 *  for this editor
	 */
	public Component getEditorComponent()
	{
		return this;
	}   //  getEditorCimponent

	/**
	 *  Set Editor
	 *  @param anObject
	 */
	public void setItem (Object anObject)
	{
		if (anObject == null)
			setText("");
		else
			setText(anObject.toString());
	}   //  setItem

	/**
	 *  Get edited item
	 *  @return edited text
	 */
	public Object getItem()
	{
		return getText();
	}   //  getItem

	/**
	 *  Returns format Info (for Popup)
	 *  @return format
	 */
	public Object getFormat()
	{
		return null;
	}   //  getFormat

}   //  CFieldEditor
