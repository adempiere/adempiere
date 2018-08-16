/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 or later of the GNU General Public License as    *
 * published by the Free Software Foundation. This program is distributed in  *
 * the hope that it will be useful, but WITHOUT ANY WARRANTY; without even    *
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR        *
 * PURPOSE. See the GNU General Public License for more details.              *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import org.adempiere.webui.editor.WEditor;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;


public class Col extends Div {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8036743691725958481L;

	public Col()
    {
		super();
		
    }
	
	private boolean hasFocus = false;
	private WEditor editor;
	private Component component;
	private Textbox anchorInput; 
	private String text;
	private boolean isReadOnly;
	private String textAlign = "text-align:";

	@Override
	public void setFocus(boolean focus) {
		hasFocus = focus;
		if(hasFocus) {
		    setStyle(textAlign+";border:2px solid #1f9bde;padding: 5px;");
		}
		else {
			setStyle(textAlign+";border:0px solid;padding: 0px;");
		}
		anchorInput.setFocus(focus);
		//super.setFocus(focus);
	}
	
	public void setTextAlign(String align) {
		textAlign +=align;
		setStyle(textAlign);
	}
	
	public boolean hasFocus() {
		return hasFocus;
	}
	
	@Override
	public boolean addEventListener(String evtnm, EventListener listener) {

		return super.addEventListener(evtnm, listener);
	}

    public boolean setComponent(Component comp) {
    	if(super.appendChild(comp)) {
    		component = comp;
    	return true;
    	}
    	else {
    		return false;
    	}
    }

    public boolean setEditor(WEditor comp) {
    	if(setComponent(comp.getComponent())) {	
    		editor = comp;
    		return true;
    	}
    	else {
    		return false;
    	}
    }    

	/**
	 * @return
	 */
	public void createAnchorInput() {
		anchorInput = new Textbox();
		anchorInput.setValue("");
		appendChild(anchorInput);
		anchorInput.setStyle("top: -10px; opacity:0; height: 0px; width: 0px;");
		
	}
	public void setReadOnly(boolean readOnly) {
		isReadOnly = readOnly;
	}
	
	public boolean isReadOnly() {
		return isReadOnly;
	}
	
	public Textbox getAnchorInput() {
		return anchorInput;
	}

    public Component getComponent() {
    	return component;
    }
    
    public WEditor getEditor() {
    	return editor;
    }

    public String getText() {
    	return text;
    }
    
    public void setText(String text) {
    	this.text = text;
    }
	

}
