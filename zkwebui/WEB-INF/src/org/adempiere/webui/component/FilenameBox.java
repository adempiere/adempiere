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

import org.adempiere.webui.apps.AEnv;
import org.zkoss.zk.ui.event.EventListener;

/**
 * URL Box
 */
public class FilenameBox extends Panel
{

    private static final long serialVersionUID = 1L;

    private Textbox           textbox;

    private Button            button;

    public FilenameBox()
    {
        initComponents();
    }

    public FilenameBox(String url)
    {
        initComponents();
        setText(url);
    }

    public void setButtonImage(String imageSrc)
    {
        button.setImage(imageSrc);
    }

    private void initComponents()
    {
        textbox = new Textbox();
        textbox.setStyle("display: inline");
        button = new Button();
        button.setSclass("editor-button");
        appendChild(textbox);
        appendChild(button);
        
        String style = AEnv.isFirefox2() ? "display: inline" : "display: inline-block";
        style = style + ";white-space:nowrap";
	    this.setStyle(style);
    }

    public void setText(String value)
    {
        textbox.setText(value);
    }

    public String getText()
    {
        return textbox.getText();
    }

    public void setEnabled(boolean enabled)
    {
        textbox.setReadonly(!enabled);
        button.setEnabled(enabled);
    }
    
    public boolean isEnabled()
    {
    	return !textbox.isReadonly();
    }
    
    public void setButtonEnabled(boolean enabled)
    {
        button.setEnabled(enabled);
    }

    public boolean addEventListener(String evtnm, EventListener listener)
    {
        if ("onClick".equals(evtnm))
            return button.addEventListener(evtnm, listener);
        else
            return textbox.addEventListener(evtnm, listener);
    }
}