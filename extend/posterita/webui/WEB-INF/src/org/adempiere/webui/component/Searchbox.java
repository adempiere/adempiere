/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.zkoss.zk.ui.event.EventListener;

public class Searchbox extends Panel
{
	private static final long serialVersionUID = 1L;
	private PropertyChangeSupport m_propertyChangeListeners = new PropertyChangeSupport(this);
	private Textbox txt;
	private Button btn;

	public Searchbox()
	{
	     initComponents();
	}

	 public Searchbox(String text)
	 {
	     initComponents();
	     setText(text);
	 }

	 public void setButtonImage(String imageSrc)
	 {
	     btn.setImage(imageSrc);
	 }

	 private void initComponents()
	 {
	     txt = new Textbox();
	     btn = new Button();
	     appendChild(txt);
	     appendChild(btn);
	 }

	 public Textbox getTextBox()
	 {
		 return txt;
	 }

	 public void setText(String value)
	 {
	    txt.setText(value);
	 }

	 public String getText()
	 {
	     return txt.getText();
	 }

	 public void setEnabled(boolean enabled)
	 {
	     txt.setEnabled(enabled);
	     btn.setEnabled(enabled);
	 }

	 public boolean isEnabled()
	 {
		 return txt.isReadonly();
	 }

	 public boolean addEventListener(String evtnm, EventListener listener)
	 {
	     if("onClick".equals(evtnm))
	     {
	       	 return btn.addEventListener(evtnm, listener);
	     }
	     else
	     {
	         return txt.addEventListener(evtnm, listener);
	     }
	 }
	 public synchronized void addPropertyChangeListener(PropertyChangeListener l)
	 {
			m_propertyChangeListeners.addPropertyChangeListener(l);
	 }

	 /**
	  * Set whether the SearchBox represents a mandatory field.
	  *
	  * @param mandatory whether the search box must be filled
	  */
     public void setMandatory(boolean mandatory)
     {
         txt.setMandatory(mandatory);
     }
}
