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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.event.TokenEvent;
import org.adempiere.webui.exception.ApplicationException;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ITheme;
import org.adempiere.webui.theme.ThemeUtils;
import org.adempiere.webui.util.BrowserToken;
import org.adempiere.webui.util.UserPreference;
import org.adempiere.webui.window.LoginWindow;
import org.compiere.Adempiere;
import org.compiere.model.MClient;
import org.compiere.model.MSession;
import org.compiere.model.MSystem;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
import org.compiere.util.Login;
import org.compiere.util.Msg;
import org.zkoss.lang.Strings;
import org.zkoss.util.Locales;
import org.zkoss.web.fn.ServletFns;
import org.zkoss.zhtml.Div;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.au.out.AuFocus;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Image;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 * @author <a href="mailto:sendy.yagambrum@posterita.org">Sendy Yagambrum</a>
 * @date    July 18, 2007
 */
public class LoginPanel extends Window implements EventListener<Event>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3168413471330471782L;
	private static final String RESOURCE = "org.compiere.apps.ALoginRes";
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE);
    private static CLogger logger = CLogger.getCLogger(LoginPanel.class);

    private Properties ctx;
    private Label lblUserId;
    private Label lblPassword;
    private Label lblLanguage;
    private Textbox txtUserId;
    private Textbox txtPassword;
    private Combobox lstLanguage;
    private LoginWindow wndLogin;
    private Checkbox chkRememberMe;

    public LoginPanel(Properties ctx, LoginWindow loginWindow)
    {
        this.ctx = ctx;
        this.wndLogin = loginWindow;
        initComponents();
        init();
        this.setId("loginPanel");
        ThemeUtils.addSclass("ad-loginpanel", this);

        AuFocus auf = new AuFocus(txtUserId);
        Clients.response(auf);

        BrowserToken.load(this.getUuid());
    }

    private void init()
    {
    	//this.setContentSclass(ITheme.LOGIN_WINDOW_CLASS);
    	
    	Div div = new Div();
    	ThemeUtils.addSclass("ad-loginpanel-header",div);
    	Label label = new Label("Login");  // TODO - localization
    	ThemeUtils.addSclass("ad-loginpanel-header-text", label);
    	div.appendChild(label);
    	this.appendChild(div);

    	Table table = new Table();
    	table.setId("grdLogin");
    	ThemeUtils.addSclass("ad-loginpanel-body", table);
    	this.appendChild(table);

    	Tr tr = new Tr();
    	table.appendChild(tr);
    	Td td = new Td();
    	ThemeUtils.addSclass("ad-loginpanel-header-logo", td);
    	tr.appendChild(td);
    	td.setDynamicProperty("colspan", "2");
    	Image image = new Image();
        image.setSrc(ThemeUtils.getLargeLogo());
    	ThemeUtils.addSclass("ad-loginpanel-header-logo",image);
        td.appendChild(image);

        tr = new Tr();
        tr.setId("rowUser");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	ThemeUtils.addSclass("login-label", td);
    	td.appendChild(lblUserId);
    	td = new Td();
    	ThemeUtils.addSclass("login-field", td);
    	tr.appendChild(td);
    	td.appendChild(txtUserId);

    	tr = new Tr();
        tr.setId("rowPassword");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	ThemeUtils.addSclass("login-label", td);
    	td.appendChild(lblPassword);
    	td = new Td();
    	ThemeUtils.addSclass("login-field", td);
    	tr.appendChild(td);
    	td.appendChild(txtPassword);

    	tr = new Tr();
        tr.setId("rowLanguage");
        table.appendChild(tr);
    	td = new Td();
    	tr.appendChild(td);
    	ThemeUtils.addSclass("login-label", td);
    	td.appendChild(lblLanguage);
    	td = new Td();
    	ThemeUtils.addSclass("login-field", td);
    	tr.appendChild(td);
    	td.appendChild(lstLanguage);

    	if (MSystem.isZKRememberUserAllowed()) {
        	tr = new Tr();
            tr.setId("rowRememberMe");
            table.appendChild(tr);
        	td = new Td();
        	tr.appendChild(td);
        	ThemeUtils.addSclass("login-label", td);
        	td.appendChild(new Label(""));
        	td = new Td();
        	ThemeUtils.addSclass("login-field", td);
        	tr.appendChild(td);
        	td.appendChild(chkRememberMe);
    	}

    	div = new Div();
    	ThemeUtils.addSclass("ad-loginpanel-footer", div);
        ConfirmPanel pnlButtons = new ConfirmPanel(false);
        pnlButtons.addActionListener(this);
        
        ThemeUtils.addSclass("ad-loginpanel-footer-pnl", pnlButtons);
        pnlButtons.getButton(ConfirmPanel.A_OK).setSclass("login-btn");
        div.appendChild(pnlButtons);
        this.appendChild(div);

        this.addEventListener(TokenEvent.ON_USER_TOKEN, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				String[] data = (String[]) event.getData();
				try
				{
					int AD_Session_ID = Integer.parseInt(data[0]);
					MSession session = new MSession(Env.getCtx(), AD_Session_ID, null);
					if (session.get_ID() == AD_Session_ID)
					{
						int AD_User_ID = session.getCreatedBy();
						MUser user = MUser.get(Env.getCtx(), AD_User_ID);
						if (user != null && user.get_ID() == AD_User_ID)
						{
						    String token = data[1];
						    if (BrowserToken.validateToken(session, user, token))
						    {
						    	if (MSystem.isZKRememberUserAllowed()) {
						    		txtUserId.setValue(user.getName());
							    	onUserIdChange();
							    	chkRememberMe.setChecked(true);
						    	}
						    	if (MSystem.isZKRememberPasswordAllowed()) {
							    	txtPassword.setValue(token);
							    	txtPassword.setAttribute("user.token.hash", token);
							    	txtPassword.setAttribute("user.token.sid", AD_Session_ID);
						    	}
						    }
						}
					}
				} catch (Exception e) {
					//safe to ignore
					logger.log(Level.INFO, e.getLocalizedMessage(), e);
				}
			}
		});
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
        //txtUserId.setCols(25);
        txtUserId.setMaxlength(40);
        //txtUserId.setWidth("220px");
        txtUserId.addEventListener(Events.ON_CHANGE, this); // Elaine 2009/02/06

        txtPassword = new Textbox();
        txtPassword.setId("txtPassword");
        txtPassword.setType("password");
        //txtPassword.setCols(25);
        //txtPassword.setWidth("220px");

        lstLanguage = new Combobox();
        lstLanguage.setAutocomplete(true);
        lstLanguage.setAutodrop(true);
        lstLanguage.setId("lstLanguage");
        lstLanguage.addEventListener(Events.ON_SELECT, this);
        //lstLanguage.setWidth("220px");

        // Update Language List
        lstLanguage.getItems().clear();
        ArrayList<String> supported = Env.getSupportedLanguages();
        String[] availableLanguages = Language.getNames();
        for (String langName : availableLanguages) {
            Language language = Language.getLanguage(langName);
            if (!language.isBaseLanguage()) {
                if (!supported.contains(language.getAD_Language()))
                    continue;
            }
            lstLanguage.appendItem(langName, language.getAD_Language());
        }

        chkRememberMe = new Checkbox(Msg.getMsg(Language.getBaseAD_Language(), "RememberMe"));
        chkRememberMe.setId("chkRememberMe");
        
        // Make the default language the language of client System
        String defaultLanguage = MClient.get(ctx, 0).getAD_Language();
        for(int i = 0; i < lstLanguage.getItemCount(); i++)
        {
        	Comboitem li = lstLanguage.getItemAtIndex(i);
        	if (li.getValue().equals(defaultLanguage))
        	{
        		lstLanguage.setSelectedIndex(i);
        		languageChanged(li.getLabel());
        		break;
        	}
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
        		onUserIdChange();
        	}
        }
        //
    }

	private void onUserIdChange() {
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
		        	Comboitem li = lstLanguage.getItemAtIndex(i);
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

    private void languageChanged(String langName)
    {
    	Language language = findLanguage(langName);

    	//	Locales
		Locale loc = language.getLocale();
		Locale.setDefault(loc);
		res = ResourceBundle.getBundle(RESOURCE, loc);

    	lblUserId.setValue(res.getString("User"));
    	lblPassword.setValue(res.getString("Password"));
    	lblLanguage.setValue(res.getString("Language"));
    	chkRememberMe.setLabel(Msg.getMsg(language, "RememberMe"));
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
     *  validates user name and password when logging in
     *
    **/
    public void validateLogin()
    {
        Login login = new Login(ctx);
        String userId = txtUserId.getValue();
        String userPassword = txtPassword.getValue();

        //check is token
        String token = (String) txtPassword.getAttribute("user.token.hash");
        if (token != null && token.equals(userPassword))
        {
        	userPassword = "";
        	int AD_Session_ID = (Integer)txtPassword.getAttribute("user.token.sid");
        	MSession session = new MSession(Env.getCtx(), AD_Session_ID, null);
        	if (session.get_ID() == AD_Session_ID)
        	{
        		MUser user = MUser.get(Env.getCtx(), session.getCreatedBy());
        		if (BrowserToken.validateToken(session, user, token))
        		{
        			userPassword = user.getPassword();
        		}
        	}
        }

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
        	Language language = findLanguage(langName);
            wndLogin.loginOk(userId, userPassword);

            Env.setContext(ctx, UserPreference.LANGUAGE_NAME, language.getName()); // Elaine 2009/02/06

            Locales.setThreadLocal(language.getLocale());

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

        Env.setContext(ctx, BrowserToken.REMEMBER_ME, chkRememberMe.isChecked());

        /* Check DB version */
        String version = DB.getSQLValueString(null, "SELECT Version FROM AD_System");
        //  Identical DB version
        if (! Adempiere.DB_VERSION.equals(version)) {
            String AD_Message = "DatabaseVersionError";
            //  Code assumes Database version {0}, but Database has Version {1}.
            String msg = Msg.getMsg(ctx, AD_Message);   //  complete message
            msg = MessageFormat.format(msg, new Object[] {Adempiere.DB_VERSION, version});
            throw new ApplicationException(msg);
        }

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

}
