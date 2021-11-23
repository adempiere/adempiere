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

package org.adempiere.webui;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.DrillCommand;
import org.adempiere.webui.component.TokenCommand;
import org.adempiere.webui.component.ZoomCommand;
import org.adempiere.webui.desktop.DefaultDesktop;
import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.event.TokenEvent;
import org.adempiere.webui.session.ServerContext;
import org.adempiere.webui.session.SessionContextListener;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.BrowserToken;
import org.adempiere.webui.util.UserPreference;
import org.compiere.model.MRole;
import org.compiere.model.MSession;
import org.compiere.model.MSysConfig;
import org.compiere.model.MSystem;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.web.Attributes;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import static org.adempiere.webui.session.SessionContextListener.SESSION_CTX;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @date    Feb 25, 2007
 * @version $Revision: 0.10 $
 *
 * @author hengsin
 * @author eEvolution author Victor Perez <victor.perez@e-evolution.com>
 * @see  [ 1258 ]The change role throw exception  </a>
 *         <a href="https://github.com/adempiere/adempiere/issues/1258">
 */
public class AdempiereWebUI extends Window implements EventListener, IWebClient
{

	private static final long serialVersionUID = 3744725245132180915L;

	public static final String APP_NAME = "ADempiere";

    public static final String UID          = "3.5";

    private WLogin             loginDesktop;

    private IDesktop applicationDesktop;

    private ClientInfo		   clientInfo;

	private String langSession;

	private Keylistener keyListener;

	private static final CLogger logger = CLogger.getCLogger(AdempiereWebUI.class);

	private static final String SAVED_CONTEXT = "saved.context";

	public static String typedPassword = null;

    public AdempiereWebUI()
    {
    	this.addEventListener(Events.ON_CLIENT_INFO, this);
    	this.setVisible(false);
    }

    public void onCreate()
    {
        this.getPage().setTitle(ThemeManager.getBrowserTitle());
		Session session = SessionManager.getSession();
		ServerContext.setCurrentInstance((Properties) session.getAttribute(SESSION_CTX));
		Properties ctx =  Env.getCtx();
		langSession = Env.getContext(ctx, Env.LANGUAGE);
		HttpSession httpSession = (HttpSession) session.getNativeSession();
		setId(httpSession.getId());
		SessionManager.addSession(session);
		SessionManager.setApplicationToSession(this);
        @SuppressWarnings("unchecked")
		Map<String, Object>map = (Map<String, Object>) session.getAttribute(SAVED_CONTEXT);
		session.removeAttribute(SAVED_CONTEXT);
        if (map != null && !map.isEmpty())
        {
        	onChangeRole(map);
        	return;
        }

        if (session.getAttribute(SESSION_CTX) == null || !SessionManager.isUserLoggedIn(ctx))
        {
			loginDesktop = new WLogin();
            loginDesktop.createPart(this.getPage());
			loginDesktop.getComponent().getRoot().addEventListener(Events.ON_CLIENT_INFO, this);
        }
        else
        {
            loginCompleted();
        }
    }

    public void onOk()
    {
    }

    public void onCancel()
    {
    }

	private void onChangeRole(Map<String, Object> map)
	{
		Locale locale = (Locale) map.get("locale");
		Properties properties = (Properties) map.get("context");
		Session session = Executions.getCurrent().getDesktop().getSession();
		SessionManager.addSession(session);
		loginDesktop = new WLogin();
		loginDesktop.createPart(this.getPage());
		loginDesktop.setTypedPassword(typedPassword);
		loginDesktop.changeRole(locale, properties);
	}

    /* (non-Javadoc)
	 * @see org.adempiere.webui.IWebClient#loginCompleted()
	 */
    public void loginCompleted()
    {
        Properties ctx = Env.getCtx();
        String langLogin = Env.getContext(ctx, Env.LANGUAGE);
        if (langLogin == null || langLogin.length() <= 0)
        {
        	langLogin = langSession;
        	Env.setContext(ctx, Env.LANGUAGE, langSession);
        }

        // Validate language
		Language language = Language.getLanguage(langLogin);
		String locale = Env.getContext(ctx, AEnv.LOCALE);
		if (locale != null && locale.length() > 0 && !language.getLocale().toString().equals(locale))
		{
			String adLanguage = language.getAD_Language();
			Language tmp = Language.getLanguage(locale);
			language = new Language(tmp.getName(), adLanguage, tmp.getLocale(), tmp.isDecimalPoint(),
	    			tmp.getDateFormat().toPattern(), tmp.getMediaSize());
		}
		else
		{
			Language tmp = language;
			language = new Language(tmp.getName(), tmp.getAD_Language(), tmp.getLocale(), tmp.isDecimalPoint(),
	    			tmp.getDateFormat().toPattern(), tmp.getMediaSize());
		}
    	Env.verifyLanguage(ctx, language);
    	Env.setContext(ctx, Env.LANGUAGE, language.getAD_Language()); //Bug

		//	Create adempiere Session - user id in ctx
        Session currentSession = SessionManager.getSession();
        HttpSession httpSession = (HttpSession) currentSession.getNativeSession();
		//Setting the timeout for this session
		Optional<Integer> maybeMaxInactiveInterval = Optional.ofNullable((Integer) httpSession.getAttribute("MaxInactiveInterval"));
		maybeMaxInactiveInterval.ifPresent(currentSession::setMaxInactiveInterval);

		MSession adempiereSession = MSession.get (ctx, currentSession.getRemoteAddr(),
				currentSession.getRemoteHost(), httpSession.getId() );

		//enable full interface, relook into this when doing preference
		Env.setContext(ctx, "#ShowTrl", true);
		Env.setContext(ctx, "#ShowAcct", MRole.getDefault().isShowAcct());
		Env.setContext(ctx, "#ShowAdvanced", true);

		keyListener = new Keylistener();
		keyListener.setPage(this.getPage());
		keyListener.setCtrlKeys("@a@c@d@e@f@h@l@m@n@o@p@r@s@t@z@x@#left@#right@#up@#down@#home@#end#enter^u@u@#pgdn@#pgup$#f2^#f2");
		keyListener.setAutoBlur(false);

		//auto commit user preference
		String autoCommit = SessionManager.getUserPreference().getProperty(UserPreference.P_AUTO_COMMIT);
		Env.setAutoCommit(ctx, "true".equalsIgnoreCase(autoCommit) || "y".equalsIgnoreCase(autoCommit));

		//auto new user preference
		String autoNew = SessionManager.getUserPreference().getProperty(UserPreference.P_AUTO_NEW);
		Env.setAutoNew(ctx, "true".equalsIgnoreCase(autoNew) || "y".equalsIgnoreCase(autoNew));
		if (applicationDesktop == null)
		{
			//create new desktop
			createDesktop();
			applicationDesktop.setClientInfo(clientInfo);
			applicationDesktop.createPart(this.getPage());
			if (loginDesktop != null)
			{
				typedPassword = loginDesktop.getTypedPassword();
				loginDesktop.cleanup();
				loginDesktop = null;
			}
		}

		if ("Y".equalsIgnoreCase(Env.getContext(ctx, BrowserToken.REMEMBER_ME)) && MSystem.isZKRememberUserAllowed())
		{
			MUser user = MUser.get(ctx);
			BrowserToken.save(adempiereSession, user);
		}
		else
		{
			BrowserToken.remove();
		}

    }

	/**
	 * @return key listener
	 */
	public Keylistener getKeylistener() {
		return keyListener;
	}


    private void createDesktop()
    {
    	applicationDesktop = null;
		String className = MSysConfig.getValue(IDesktop.CLASS_NAME_KEY);
		if ( className != null && className.trim().length() > 0)
		{
			try
			{
				Class<?> clazz = this.getClass().getClassLoader().loadClass(className);
				applicationDesktop = (IDesktop) clazz.newInstance();
			}
			catch (Throwable t)
			{
				logger.warning("Failed to instantiate desktop. Class=" + className);
			}
		}
		//fallback to default
		if (applicationDesktop == null)
			applicationDesktop = new DefaultDesktop();
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.IWebClient#logout()
	 */
    public void logout()
    {
		//The context is destroy until the Adempiere Session is processed
		//Clean the session and components
		SessionManager.clearSession(getId());
		SessionManager.removeSession(getId());
		ServerContext.dispose();
		super.getChildren().clear();
		detach();
		Executions.sendRedirect("index.zul");
    }

	@Override
	public synchronized void logoutDestroyed() {
		//stop Dashboard background thread
		IDesktop dashboard = getApplicationDesktop();
		if (dashboard != null) {
			dashboard.logout();
		}

		//Clean and Logout Adempiere Session
		clearDesktop();
	}

	public IDesktop getApplicationDesktop(){
		return applicationDesktop;
	}

	public void onEvent(Event event) {
		if (event instanceof ClientInfoEvent) {
			ClientInfoEvent c = (ClientInfoEvent)event;
			clientInfo = new ClientInfo();
			clientInfo.colorDepth = c.getColorDepth();
			clientInfo.desktopHeight = c.getDesktopHeight();
			clientInfo.desktopWidth = c.getDesktopWidth();
			clientInfo.desktopXOffset = c.getDesktopXOffset();
			clientInfo.desktopYOffset = c.getDesktopYOffset();
			clientInfo.timeZone = c.getTimeZone();
			if (applicationDesktop != null)
				applicationDesktop.setClientInfo(clientInfo);
		}

	}

	//global command
	static {
		new ZoomCommand("onZoom", Command.IGNORE_OLD_EQUIV);
		new DrillCommand("onDrillAcross", Command.IGNORE_OLD_EQUIV);
		new DrillCommand("onDrillDown", Command.IGNORE_OLD_EQUIV);
		new TokenCommand(TokenEvent.ON_USER_TOKEN, Command.IGNORE_OLD_EQUIV);
	}

	@Override
	public void changeRole(MUser user)
	{
		// save context for re-login
		Properties properties = new Properties();
		Env.setContext(properties, Env.AD_CLIENT_ID, Env.getAD_Client_ID(Env.getCtx()));
		Env.setContext(properties, Env.AD_ORG_ID, Env.getAD_Org_ID(Env.getCtx()));
		Env.setContext(properties, Env.AD_USER_ID, user.getAD_User_ID());
		Env.setContext(properties, Env.AD_ROLE_ID, Env.getAD_Role_ID(Env.getCtx()));
		Env.setContext(properties, Env.AD_ORG_NAME, Env.getContext(Env.getCtx(), Env.AD_ORG_NAME));
		Env.setContext(properties, Env.M_WAREHOUSE_ID, Env.getContext(Env.getCtx(), Env.M_WAREHOUSE_ID));
		Env.setContext(properties, BrowserToken.REMEMBER_ME, Env.getContext(Env.getCtx(), BrowserToken.REMEMBER_ME));
		Env.setContext(properties, UserPreference.LANGUAGE_NAME,
				Env.getContext(Env.getCtx(), UserPreference.LANGUAGE_NAME));
		Env.setContext(properties, Env.LANGUAGE, Env.getContext(Env.getCtx(), Env.LANGUAGE));
		Env.setContext(properties, AEnv.LOCALE, Env.getContext(Env.getCtx(), AEnv.LOCALE));

		Locale locale = (Locale) Executions.getCurrent().getDesktop().getSession().getAttribute(Attributes.PREFERRED_LOCALE);
		HttpServletRequest httpRequest = (HttpServletRequest) Executions.getCurrent().getNativeRequest();

		//stop key listener
		if (keyListener != null) {
			keyListener.detach();
			keyListener = null;
		}

		// stop background thread
		IDesktop dashboard = getApplicationDesktop();
		if (dashboard != null)
			dashboard.logout();

		// clear remove all children and root component
		getChildren().clear();
		getPage().removeComponents();

		Env.getCtx().clear();

		// clear session attributes
		//getSession().getAttributes().clear();

		// put saved context into new session
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("context", properties);
		map.put("locale", locale);

		HttpSession newSession = httpRequest.getSession(false);

		newSession.setAttribute(SAVED_CONTEXT, map);
		properties.setProperty(SessionContextListener.SERVLET_SESSION_ID, newSession.getId());

		Executions.sendRedirect("index.zul");
	}

	public void changeRole(Locale locale, Properties properties)
	{
		loginDesktop.changeRole(locale, properties);
	}

	private synchronized void clearDesktop(){
		//Reset the password
		typedPassword = null;
		keyListener = null;
		clientInfo = null;
		loginDesktop = null;
		applicationDesktop = null;

		//stop key listener
		/*if (keyListener != null) {
			//keyListener.detach();
			keyListener = null;
		}

		if (clientInfo != null) {
			clientInfo = null;
		}

		if (loginDesktop != null){
			loginDesktop.cleanup();
			loginDesktop = null;
		}

		desktop = null;*/
	}
}
