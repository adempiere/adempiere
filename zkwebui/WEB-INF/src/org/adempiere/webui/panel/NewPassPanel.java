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

import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.BrowserToken;
import org.adempiere.webui.util.UserPreference;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.model.MSession;
import org.compiere.model.MUser;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.spin.model.MADTokenDefinition;
import org.spin.util.TokenGeneratorHandler;
import org.zkoss.lang.Strings;
import org.zkoss.util.Locales;
import org.zkoss.zhtml.Div;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.fn.ZkFns;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Image;

/**
 * Panel for establish new password
 * @author Raul Muñoz, rMunoz@erpya.com , http://www.erpya.com
 * <li> FR [ 1769 ] Add option to restore the password from the login
 * @see https://github.com/adempiere/adempiere/issues/1699
 */
public class NewPassPanel extends Window implements EventListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3168413471330471782L;

    /** Properties  */
    private Properties 		ctx;
    /** Label New Password  	*/
    private Label 			lblPassword;
    /** Label Repeat Password   */
    private Label 			lblRepeatPassword;
    /** Label Msg Token		  	*/
    private Label 			lblMsgToken;
    /** TextBox New Password  	*/
    private Textbox			txtNewPassword;
    /** TextBox Repeat Password */
    private Textbox			txtRepeatPassword;
    /** Login Widow 			*/
    private LoginWindow 	wndLogin;
    /** AD_User_ID				*/
    private int 			userId;

    public NewPassPanel(Properties ctx, LoginWindow loginWindow)
    {
        this.ctx = ctx;
        this.wndLogin = loginWindow;
        initComponents();
        init();
        this.setId("NewPassPanel");

        AuFocus auf = new AuFocus(txtNewPassword);
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

    	ConfirmPanel pnlButtons = new ConfirmPanel(false);
    	 
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

        try {
    		lblMsgToken = new Label();
			lblMsgToken.setId("lblMsgToken");
			lblMsgToken.setStyle("Color:red; text-align:center;");
			lblMsgToken.setValue("Token Not Valid");
			String token = Executions.getCurrent().getParameter("token");
	 		String userId = Executions.getCurrent().getParameter("userId");
	    	if(userId != null) {
	    		this.userId = Integer.parseInt(userId); 
	    	}
	    	 
			if(token == null 
					|| !TokenGeneratorHandler.getInstance().validateToken(MADTokenDefinition.TOKENTYPE_URLTokenUsedAsURL, token,this.userId)) {
				tr = new Tr();
				tr.setId("Message");
				table.appendChild(tr);
				td = new Td();
		    	td.setDynamicProperty("colspan", "2");
				tr.appendChild(td);
				td.appendChild(lblMsgToken);
				pnlButtons.getButton(ConfirmPanel.A_OK).setEnabled(false);
			}
        
    	} catch (Exception e) {
			e.printStackTrace();
		}

    	tr = new Tr();
        tr.setId("rowPassword");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblPassword);
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(txtNewPassword);
    	

    	tr = new Tr();
        tr.setId("rowRepeatPassword");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	td.setSclass(ITheme.LOGIN_LABEL_CLASS);
    	td.appendChild(lblRepeatPassword);
    	td = new Td();
    	td.setSclass(ITheme.LOGIN_FIELD_CLASS);
    	tr.appendChild(td);
    	td.appendChild(txtRepeatPassword);
    	
    	tr = new Tr();
        table.appendChild(tr);
        td = new Td();
      	tr.appendChild(td);
      	td.appendChild(new Label(""));
      	td = new Td();
      	tr.appendChild(td);
      	td.appendChild(new Label(""));
      	
        tr = new Tr();
        table.appendChild(tr);
        td = new Td();
    	tr.appendChild(td);
    	td.appendChild(new Label(""));
    	td = new Td();
    	tr.appendChild(td);
    	td.appendChild(new Label(""));
    	

    	div = new Div();
    	div.setSclass(ITheme.LOGIN_BOX_FOOTER_CLASS);
       
        pnlButtons.addActionListener(this);
        LayoutUtils.addSclass(ITheme.LOGIN_BOX_FOOTER_PANEL_CLASS, pnlButtons);
        pnlButtons.setWidth(null);
        pnlButtons.getButton(ConfirmPanel.A_OK).setSclass(ITheme.LOGIN_BUTTON_CLASS);
        div.appendChild(pnlButtons);
        this.appendChild(div);

       
    }

    private void initComponents() {
	        lblPassword = new Label();
	        lblPassword.setId("lblPassword");
	        lblPassword.setValue("New Password");

	        lblRepeatPassword = new Label();
	        lblRepeatPassword.setId("lblRepeatPassword");
	        lblRepeatPassword.setValue("Repeat New Password");
	    	
	        txtNewPassword = new Textbox();
	        txtNewPassword.setId("txtPassword");
	        txtNewPassword.setType("password");
	        txtNewPassword.setCols(25);
	        txtNewPassword.setWidth("200px");

	        txtRepeatPassword = new Textbox();
	        txtRepeatPassword.setId("txtRepeatPassword");
	        txtRepeatPassword.setType("password");
	        txtRepeatPassword.setCols(25);
	        txtRepeatPassword.setWidth("200px");
    
   }

    public void onEvent(Event event)
    {
        if (event.getTarget().getId().equals(ConfirmPanel.A_OK)) {

            validateLogin();
        }
       
       
        //
    }

	private Language findLanguage(String langName) {
		Language tmp = Language.getLanguage(langName);
    	Language language = new Language(tmp.getName(), tmp.getAD_Language(), tmp.getLocale(), tmp.isDecimalPoint(),
    			tmp.getDateFormat().toPattern(), tmp.getMediaSize());
    	Env.verifyLanguage(ctx, language);
    	Env.setContext(ctx, Env.LANGUAGE, language.getAD_Language());
    	Env.setContext(ctx, AEnv.LOCALE, language.getLocale().toString());
		return language;
	}
    /**
     *  validates password when is change
     *
    **/
    public void validateLogin()
    {
    	if(txtNewPassword.getValue() != null && !txtNewPassword.getValue().equals(txtRepeatPassword.getValue())) {
    		lblMsgToken.setValue("Password not are equals");
    		return;
    	}
		
        Login login = new Login(ctx);
        String userName;
        MUser user = null;
			user = MUser.get(Env.getCtx(), userId);
		if(user == null) 
			return;
		userName = user.getValue();
		user.setPassword(txtNewPassword.getValue());
		user.save();
        String userPassword = txtNewPassword.getValue();

        //check is token
        String token = (String) txtNewPassword.getAttribute("user.token.hash");
        if (token != null && token.equals(userPassword))
        {
        	userPassword = "";
        	int AD_Session_ID = (Integer)txtNewPassword.getAttribute("user.token.sid");
        	MSession session = new MSession(Env.getCtx(), AD_Session_ID, null);
        	if (session.get_ID() == AD_Session_ID)
        	{
        		if (BrowserToken.validateToken(session, user, token))
        		{
        			userPassword = user.getPassword();
        		}
        	}
        }

        KeyNamePair rolesKNPairs[] = login.getRoles(userName, userPassword);
        if(rolesKNPairs == null || rolesKNPairs.length == 0)
            throw new WrongValueException("User Id or Password invalid!!!");

        else
        {
        	String langName = null;
        		langName = Language.getBaseLanguage().getName();
        	Language language = findLanguage(langName);

            wndLogin.setPassReset(true);
            wndLogin.loginOk(userName, userPassword);
            Env.setContext(ctx, UserPreference.LANGUAGE_NAME, language.getName()); // Elaine 2009/02/06

            Locales.setThreadLocal(language.getLocale());

            Clients.response("zkLocaleJavaScript", new AuScript(null, ZkFns.outLocaleJavaScript()));
            String timeoutText = getUpdateTimeoutTextScript();
            if (!Strings.isEmpty(timeoutText))
            	Clients.response("zkLocaleJavaScript2", new AuScript(null, timeoutText));
        }

		// This temporary validation code is added to check the reported bug
		// [ adempiere-ZK Web Client-2832968 ] User context lost?
		// https://sourceforge.net/tracker/?func=detail&atid=955896&aid=2832968&group_id=176962
		// it's harmless, if there is no bug then this must never fail
        Session currSess = Executions.getCurrent().getDesktop().getSession();
        currSess.setAttribute("Check_AD_User_ID", Env.getAD_User_ID(ctx));
		// End of temporary code for [ adempiere-ZK Web Client-2832968 ] User context lost?


    }

	private String getUpdateTimeoutTextScript() {
		String msg = Msg.getMsg(Env.getCtx(), "SessionTimeoutText");
		if (msg == null || msg.equals("SessionTimeoutText")) {
			return null;
		}
		msg = Strings.escape(msg, "\"");
		String s = "adempiere.store.set(\"zkTimeoutText\", \"" + msg + "\")";
		return s;
	}

	public String getTypedPassword()
	{
		if (txtNewPassword != null)
			return txtNewPassword.getValue();

		return null;
	}

	public void setTypedPassword(String passwod)
	{
		if (txtNewPassword != null)
			txtNewPassword.setValue(passwod);
	}
	
	
}
