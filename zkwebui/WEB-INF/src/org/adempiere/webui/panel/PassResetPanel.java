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
 *                                                                            *
 * Contributors:                                                              *
 * - Heng Sin Low                                                             *
 *                                                                            *
 * Sponsors:                                                                  *
 * - Idalica Corporation                                                      *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.util.Properties;
import java.util.ResourceBundle;

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.BrowserToken;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.model.MSystem;
import org.compiere.util.CLogger;
import org.compiere.util.EMail;
import org.compiere.util.Msg;
import org.spin.util.GeneratePassword;
import org.zkoss.zhtml.Div;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;

/**
 * Panel for establish new password
 * @author Raul Muñoz, rMunoz@erpya.com , http://www.erpya.com
 * <li> FR [ 1769 ] Add option to restore the password from the login
 * @see https://github.com/adempiere/adempiere/issues/1769
 */
public class PassResetPanel extends Window implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3168413471330471782L;


    /** Properties  */
    private Properties 	ctx;
    /** Label AD_User 	 */
    private Label 		lblUserId;
    /** TextBox AD_User  */
    private Textbox 	txtUserId;
    /** Label Msg	  	 */
    private Label 		lblMsg;
    /** Login Widow 	  */
    private LoginWindow wndLogin;

    public PassResetPanel(Properties ctx, LoginWindow loginWindow)
    {
        this.ctx = ctx;
        this.wndLogin = loginWindow;
        initComponents();
        init();
        this.setId("PassResetPanel");

        AuFocus auf = new AuFocus(txtUserId);
        Clients.response(auf);

        BrowserToken.load(this.getUuid());
    }

    private void init()
    {
    	Div div = new Div();
    	div.setSclass(ITheme.LOGIN_BOX_HEADER_CLASS);
    	Label label = new Label("Login");
    	label.setSclass(ITheme.LOGIN_BOX_HEADER_TXT_CLASS);
    	div.appendChild(label);
    	this.appendChild(div);

    	Table table = new Table();
    	table.setId("grdLogin");
    	table.setDynamicProperty("cellpadding", "0");
    	table.setDynamicProperty("cellspacing", "5");
    	table.setSclass(ITheme.LOGIN_BOX_BODY_CLASS);

    	this.appendChild(table);

    	Tr tr = new Tr();
    	table.appendChild(tr);
    	Td td = new Td();
    	td.setSclass(ITheme.LOGIN_BOX_HEADER_LOGO_CLASS);
    	tr.appendChild(td);
    	td.setDynamicProperty("colspan", "2");
    	Image image = new Image();
        image.setSrc(ThemeManager.getLargeLogo());
        td.appendChild(image);

        tr = new Tr();
        tr.setId("rowUser");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblUserId);
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(txtUserId);

    	tr = new Tr();
		tr.setId("Message");
		table.appendChild(tr);
		td = new Td();
		tr.appendChild(td);
		td.appendChild(new Label(""));
		td = new Td();
		tr.appendChild(td);
		td.appendChild(lblMsg);

    	tr = new Tr();
        table.appendChild(tr);

    	div = new Div();
    	div.setSclass(ITheme.LOGIN_BOX_FOOTER_CLASS);
        ConfirmPanel pnlButtons = new ConfirmPanel(true);
        pnlButtons.addActionListener(this);
        LayoutUtils.addSclass(ITheme.LOGIN_BOX_FOOTER_PANEL_CLASS, pnlButtons);
        pnlButtons.setWidth(null);

        pnlButtons.getButton(ConfirmPanel.A_OK).setSclass(ITheme.LOGIN_BUTTON_CLASS);
        pnlButtons.getButton(ConfirmPanel.A_CANCEL).setSclass(ITheme.LOGIN_BUTTON_CLASS);
        
        div.appendChild(pnlButtons);
        this.appendChild(div);


    }

    private void initComponents()
    {
        lblUserId = new Label();
        lblUserId.setId("lblUserId");
        lblUserId.setValue("User ID");

        txtUserId = new Textbox();
        txtUserId.setId("txtUserId");
        txtUserId.setCols(25);
        txtUserId.setMaxlength(40);
        txtUserId.setWidth("220px");
        txtUserId.addEventListener(Events.ON_CHANGE, this); // Elaine 2009/02/06
    	
        lblMsg= new Label();
		lblMsg.setId("lblMsgToken");
		lblMsg.setValue("Token is Not Valid");
		lblMsg.setVisible(false);

   }
    
    /**
     *  validate password Reset
     *
    **/
    public void validatePassReset()
    {
    	GeneratePassword pass = new GeneratePassword();
		String msg = pass.doIt(txtUserId.getValue());
		if(msg.contains(EMail.SENT_OK)) {
        	Executions.sendRedirect("index.zul");
		} else {
			lblMsg.setValue(Msg.parseTranslation(ctx,msg));
			lblMsg.setStyle("Color:Red; text-align:center;");
			lblMsg.setVisible(true);
		}
    }
    public void onEvent(Event event)
    {
        if (event.getTarget().getId().equals(ConfirmPanel.A_OK))
        {
        	validatePassReset();
        }
        else if (event.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
        {
        	wndLogin.loginCancelled();
        }
    }


 
}
