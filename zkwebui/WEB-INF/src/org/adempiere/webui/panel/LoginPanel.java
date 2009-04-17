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

package org.adempiere.webui.panel;

import java.util.Properties;
import java.util.ResourceBundle;

import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.UserPreference;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * @author <a href="mailto:sendy.yagambrum@posterita.org">Sendy Yagambrum</a>
 * @date    July 18, 2007
 */
public class LoginPanel extends Window implements EventListener
{
    private static final long serialVersionUID = 1L;
    
    private static final String RESOURCE = "org.compiere.apps.ALoginRes";
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE);

    private Properties ctx;
    private Label lblUserId;
    private Label lblPassword;
    private Label lblLanguage;
    private Textbox txtUserId;
    private Textbox txtPassword;
    private Listbox lstLanguage;
/*    private Button btnOk;
    private Button btnCancel;*/
    private LoginWindow wndLogin;

    public LoginPanel(Properties ctx, LoginWindow loginWindow)
    {
        this.ctx = ctx;
        this.wndLogin = loginWindow;
        initComponents();
        init();
        this.setId("loginPanel");
        
        AuFocus auf = new AuFocus(txtUserId);
        Clients.response(auf);
    }

    private void init()
    {
        Grid grid = new Grid();
        grid.setOddRowSclass("even");
        grid.setId("grdLogin");
        Rows rows = new Rows();
        Row logo = new Row();
        logo.setSpans("2");
        Image image = new Image();
        image.setSrc("images/logo.png");
        logo.appendChild(image);        
        Row rowUser = new Row();
        rowUser.setId("rowUser");
        Row rowPassword = new Row();
        rowPassword.setId("rowPassword");
        Row rowLanguage = new Row();
        rowLanguage.setId("rowLanguage");

        rowUser.appendChild(lblUserId.rightAlign());
        rowUser.appendChild(txtUserId);

        rowPassword.appendChild(lblPassword.rightAlign());
        rowPassword.appendChild(txtPassword);

        rowLanguage.appendChild(lblLanguage.rightAlign());
        rowLanguage.appendChild(lstLanguage);

        Row rowButtons = new Row();
        rowButtons.setSpans("2");
        ConfirmPanel pnlButtons = new ConfirmPanel(false);
        pnlButtons.addActionListener(this);
        rowButtons.appendChild(pnlButtons);

        rows.appendChild(logo);
        rows.appendChild(rowUser);
        rows.appendChild(rowPassword);
        rows.appendChild(rowLanguage);
        rows.appendChild(rowButtons);
        grid.appendChild(rows);
        this.appendChild(grid);
    }

    private void initComponents()
    {
        lblUserId = new Label();
        lblUserId.setId("lblUserId");
        lblUserId.setValue("User ID");

        lblPassword = new Label();
        lblPassword.setId("lblPassword");
        lblPassword.setValue("Password");

        lblLanguage = new Label();
        lblLanguage.setId("lblLanguage");
        lblLanguage.setValue("Language");

        txtUserId = new Textbox();
        txtUserId.setId("txtUserId");
        txtUserId.setCols(25);
        txtUserId.setMaxlength(40);
        txtUserId.setWidth("220px");
        txtUserId.addEventListener(Events.ON_CHANGE, this); // Elaine 2009/02/06

        txtPassword = new Textbox();
        txtPassword.setId("txtPassword");
        txtPassword.setType("password");
        txtPassword.setCols(25);
        txtPassword.setMaxlength(40);
        txtPassword.setWidth("220px");

        lstLanguage = new Listbox();
        lstLanguage.setId("lstLanguage");
        lstLanguage.setRows(1);
        lstLanguage.setMold("select");
        lstLanguage.addEventListener(Events.ON_SELECT, this);
        lstLanguage.setWidth("220px");
        
        // Update Language List
        lstLanguage.getItems().clear();
        String[] availableLanguages = Language.getNames();
        for (String langName : availableLanguages) {
    		Language language = Language.getLanguage(langName);
			lstLanguage.appendItem(langName, language.getAD_Language());
		}
   }

    public void onEvent(Event event)
    {
        Component eventComp = event.getTarget();

        if (event.getTarget().getId().equals(ConfirmPanel.A_OK))
        {
            validateLogin();
        }
        if (event.getName().equals(Events.ON_SELECT))
        {
            if(eventComp.getId().equals(lstLanguage.getId())) {
            	String langName = (String) lstLanguage.getSelectedItem().getLabel();
            	languageChanged(langName);
            }
        }
        // Elaine 2009/02/06 - initial language
        if (event.getName().equals(Events.ON_CHANGE))
        {
        	if(eventComp.getId().equals(txtUserId.getId()))
        	{
        		String userId = txtUserId.getValue();
        		if(userId != null && userId.length() > 0)
        		{
	        		int AD_User_ID = DB.getSQLValue(null, "SELECT AD_User_ID FROM AD_User WHERE Name = ?", userId);
	        		if(AD_User_ID > 0)
	        		{
	        			// Elaine 2009/02/06 Load preference from AD_Preference
	        			UserPreference userPreference = SessionManager.getSessionApplication().loadUserPreference(AD_User_ID);
	        			String initDefault = userPreference.getProperty(UserPreference.P_LANGUAGE);
	        			for(int i = 0; i < lstLanguage.getItemCount(); i++)
	        	        {
	        	        	Listitem li = lstLanguage.getItemAtIndex(i);
	        	        	if(li.getLabel().equals(initDefault))
	        	        	{
	        	        		lstLanguage.setSelectedIndex(i);
	        	        		languageChanged(li.getLabel()); // Elaine 2009/04/17 language changed
	        	        		break;
	        	        	}
	        	        }
	        		}
        		}
        	}
        }
        //
    }
    
    private void languageChanged(String langName)
    {
    	Language language = Language.getLanguage(langName);
    	Env.verifyLanguage(ctx, language);
    	Env.setContext(ctx, Env.LANGUAGE, language.getAD_Language());
    	
    	res = ResourceBundle.getBundle(RESOURCE, language.getLocale());
    	lblUserId.setValue(res.getString("User"));
    	lblPassword.setValue(res.getString("Password"));
    	lblLanguage.setValue(res.getString("Language"));
    }
    /**
     *  validates user name and password when logging in
     *
    **/
    public void validateLogin()
    {
        Login login = new Login(ctx);
        String userId = txtUserId.getValue();
        String userPassword = txtPassword.getValue();
        KeyNamePair rolesKNPairs[] = login.getRoles(userId, userPassword);
        if(rolesKNPairs == null || rolesKNPairs.length == 0)
            throw new WrongValueException("User Id or Password invalid!!!");

        else
        {
        	String langName = null;
        	if ( lstLanguage.getSelectedItem() != null )
        		langName = (String) lstLanguage.getSelectedItem().getLabel();
        	else
        		langName = Language.getBaseLanguage().getName();
        	Language language = Language.getLanguage(langName);
        	Env.verifyLanguage(ctx, language);
            wndLogin.loginOk(userId, userPassword);      
            
            Env.setContext(ctx, UserPreference.LANGUAGE_NAME, language.getName()); // Elaine 2009/02/06
        }
    }
}
