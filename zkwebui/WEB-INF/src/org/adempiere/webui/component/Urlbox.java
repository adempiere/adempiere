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

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Hbox;

/**
 * URL Box
 */
public class Urlbox extends Hbox
{

    private static final long serialVersionUID = 1L;

    private Textbox           txtUrl;

    private Button            btnUrl;

    public Urlbox()
    {
        initComponents();
    }

    public Urlbox(String url)
    {
        initComponents();
        setText(url);
    }

    public void setButtonImage(String imageSrc)
    {
        btnUrl.setImage(imageSrc);
    }

    private void initComponents()
    {
        txtUrl = new Textbox();
        txtUrl.setWidth("100%");
        btnUrl = new Button();
        btnUrl.setHeight("98%");
        appendChild(txtUrl);
        appendChild(btnUrl);
    }

    public void setText(String value)
    {
        txtUrl.setText(value);
    }

    public String getText()
    {
        return txtUrl.getText();
    }

    public void setEnabled(boolean enabled)
    {
        txtUrl.setEnabled(enabled);
        btnUrl.setEnabled(enabled);
    }
    
    public void setButtonEnabled(boolean enabled)
    {
        btnUrl.setEnabled(enabled);
    }

    public boolean addEventListener(String evtnm, EventListener listener)
    {
        if ("onClick".equals(evtnm))
            return btnUrl.addEventListener(evtnm, listener);
        else
            return txtUrl.addEventListener(evtnm, listener);
    }
}