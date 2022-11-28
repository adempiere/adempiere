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
import org.spin.authentication.services.OpenIDUtil;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.impl.ExecutionCarryOver;
import org.zkoss.zk.ui.sys.DesktopCache;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zk.ui.sys.ExecutionCtrl;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zk.ui.sys.SessionCtrl;
import org.zkoss.zk.ui.sys.Visualizer;
import org.zkoss.zul.Window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

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

	public AdempiereWebUI()
    {
    	this.addEventListener(Events.ON_CLIENT_INFO, this);
    	this.setVisible(false);
    }

    public void onCreate()
    {
		Session session = getDesktop().getSession();
		HttpSession httpSession = (HttpSession) session.getNativeSession();
		ServerContext.setCurrentInstance(SessionManager.getSessionContext(httpSession.getId()));
		this.getPage().setTitle(ThemeManager.getBrowserTitle());
		setId(httpSession.getId());
		langSession = Env.getContext(Env.getCtx(), Env.LANGUAGE);
		SessionManager.setApplication(httpSession.getId(), this);
		int userId = Env.getAD_User_ID(Env.getCtx());
		
		if (externalAuthentication())
			userId = Env.getAD_User_ID(Env.getCtx());
		
		if (userId > 0 && !SessionManager.existsExecutionCarryOver(httpSession.getId())) {
			onChangeRole(userId);
			return;
		}
		if (!SessionManager.isUserLoggedIn(Env.getCtx()))
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

	private void onChangeRole(int userId)
	{
		loginDesktop = new WLogin();
		loginDesktop.createPart(this.getPage());
		loginDesktop.setTypedPassword(SessionManager.getUserAuthentication(getId()));
		SessionManager.removeUserAuthentication(getId());
		Properties newContext =  (Properties) Env.getCtx().clone();
		Language language = Env.getLanguage(Env.getCtx());
		if (userId > 0)
			loginDesktop.externalAuthentication(language.getLocale(), Env.getCtx(), MUser.get(Env.getCtx(), userId));
		else
			loginDesktop.changeRole(language.getLocale(),newContext);
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
        Session currentSession = SessionManager.getApplication(getId()).getPage().getDesktop().getSession();
        HttpSession httpSession = SessionManager.getSession(getId());
		//Setting the timeout for this session
		Optional<Integer> maybeMaxInactiveInterval = Optional.ofNullable((Integer) httpSession.getAttribute("MaxInactiveInterval"));
		maybeMaxInactiveInterval.ifPresent(maxInactiveInterval -> {
			httpSession.setAttribute("MaxInactiveInterval",maxInactiveInterval);
			httpSession.setMaxInactiveInterval(maxInactiveInterval);
		});

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
		String autoCommit = SessionManager.getUserPreference(httpSession.getId()).getProperty(UserPreference.P_AUTO_COMMIT);
		Env.setAutoCommit(ctx, "true".equalsIgnoreCase(autoCommit) || "y".equalsIgnoreCase(autoCommit));

		//auto new user preference
		String autoNew = SessionManager.getUserPreference(httpSession.getId()).getProperty(UserPreference.P_AUTO_NEW);
		Env.setAutoNew(ctx, "true".equalsIgnoreCase(autoNew) || "y".equalsIgnoreCase(autoNew));

		checkCarryover(currentSession);
		MUser user = MUser.get(ctx);
		if (applicationDesktop == null)
		{
			//create new desktop
			createDesktop();
			applicationDesktop.setClientInfo(clientInfo);
			applicationDesktop.createPart(this.getPage());
			Desktop desktop = this.getPage().getDesktop();
			ExecutionCarryOver executionCarryOver = new ExecutionCarryOver(desktop);
			SessionManager.setApplicationDesktop(httpSession.getId(), applicationDesktop);
			SessionManager.setExecutionCarryOverCache(httpSession.getId(), executionCarryOver);

			if (loginDesktop != null)
			{
				loginDesktop.cleanup();
				loginDesktop = null;
			}
		}

		if ("Y".equalsIgnoreCase(Env.getContext(ctx, BrowserToken.REMEMBER_ME)) && MSystem.isZKRememberUserAllowed())
		{
			BrowserToken.save(adempiereSession, user);
		}
		else
		{
			BrowserToken.remove();
		}
    }

	private void checkCarryover(Session currentSession) {
		HttpSession httpSession = (HttpSession) currentSession.getNativeSession();
		Optional.ofNullable(SessionManager.getApplicationDesktop(httpSession.getId()))
				.ifPresent(desktop -> {
					Optional.ofNullable(SessionManager.getExecutionCarryOver(httpSession.getId()))
							.ifPresent(executionCarryOver -> {
								try {
									applicationDesktop = desktop;
									ExecutionCarryOver currentExecutionCarryOver = new ExecutionCarryOver(this.getPage().getDesktop());
									ExecutionCtrl currentCtrl = ExecutionsCtrl.getCurrentCtrl();
									Visualizer visualizer = currentCtrl.getVisualizer();
									executionCarryOver.carryOver();
									Collection<Component> rootComponents = new ArrayList<>();
									try {
										currentCtrl = ExecutionsCtrl.getCurrentCtrl();
										((DesktopCtrl) Executions.getCurrent().getDesktop()).setVisualizer(visualizer);

										//detach root component from old page
										Page page = applicationDesktop.getComponent().getPage();
										Collection<?> collection = page.getRoots();
										Object[] objects = new Object[0];
										objects = collection.toArray(objects);
										for (Object obj : objects) {
											if (obj instanceof Component) {
												((Component) obj).detach();
												rootComponents.add((Component) obj);
											}
										}
										applicationDesktop.getComponent().detach();
										DesktopCache desktopCache = ((SessionCtrl) currentSession).getDesktopCache();
										if (desktopCache != null)
											desktopCache.removeDesktop(Executions.getCurrent().getDesktop());
									} catch (Exception e) {
										applicationDesktop = null;
									} finally {
										executionCarryOver.cleanup();
										currentExecutionCarryOver.carryOver();
									}

									if (applicationDesktop != null) {
										//re-attach root components
										for (Component component : rootComponents) {
											try {
												Optional.ofNullable(this.getPage()).ifPresent(component::setPage);
											} catch (UiException e) {
												// e.printStackTrace();
												// an exception is thrown here when refreshing the page, it seems is harmless to catch and ignore it
												// i.e.: org.zkoss.zk.ui.UiException: Not unique in the ID space of [Page z_kg_0]: zk_comp_2
											}
										}
										Optional.ofNullable(this.getPage()).ifPresent(page -> applicationDesktop.setPage(page));
										SessionManager.setExecutionCarryOverCache(httpSession.getId(), currentExecutionCarryOver);
										SessionManager.setApplication(httpSession.getId() , this);
									}
								} catch (Throwable t) {
									//restore fail
									applicationDesktop = null;
								}
							});
				});
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

		HttpServletRequest httpRequest = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		//Get current session
		HttpSession httpSession = httpRequest.getSession(false);
		// save context for re-login
		Env.getCtx().clear();
		Properties context = new Properties();
		Env.setContext(context, SessionContextListener.SERVLET_SESSION_ID, httpSession.getId());
		Env.setCtx(context);
		Env.setContext(Env.getCtx(), SessionContextListener.SERVLET_SESSION_ID, httpSession.getId());
		langSession = Env.getContext(Env.getCtx(), Env.LANGUAGE);
		SessionManager.clearSession(httpSession.getId());
		SessionManager.removeExecutionCarryOver(httpSession.getId());
		SessionManager.removeDestop(httpSession.getId());
		SessionManager.removeUserAuthentication(httpSession.getId());
		SessionManager.removeApplication(httpSession.getId());
		Executions.sendRedirect("index.zul");
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
		HttpServletRequest httpRequest = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		HttpSession httpSession = httpRequest.getSession(false);
		// save context for re-login
		Properties context = new Properties();
		Env.setContext(context, Env.AD_CLIENT_ID, Env.getAD_Client_ID(Env.getCtx()));
		Env.setContext(context, Env.AD_ORG_ID, Env.getAD_Org_ID(Env.getCtx()));
		Env.setContext(context, Env.AD_USER_ID, user.getAD_User_ID());
		Env.setContext(context, Env.AD_ROLE_ID, Env.getAD_Role_ID(Env.getCtx()));
		Env.setContext(context, Env.AD_ORG_NAME, Env.getContext(Env.getCtx(), Env.AD_ORG_NAME));
		Env.setContext(context, Env.M_WAREHOUSE_ID, Env.getContext(Env.getCtx(), Env.M_WAREHOUSE_ID));
		Env.setContext(context, BrowserToken.REMEMBER_ME, Env.getContext(Env.getCtx(), BrowserToken.REMEMBER_ME));
		Env.setContext(context, UserPreference.LANGUAGE_NAME, Env.getContext(Env.getCtx(), UserPreference.LANGUAGE_NAME));
		Env.setContext(context, Env.LANGUAGE, Env.getContext(Env.getCtx(), Env.LANGUAGE));
		Env.setContext(context, AEnv.LOCALE, Env.getContext(Env.getCtx(), AEnv.LOCALE));
		Env.setContext(context, SessionContextListener.SERVLET_SESSION_ID, httpSession.getId());
		langSession = Env.getContext(Env.getCtx(), Env.LANGUAGE);
		Env.getCtx().clear();
		Env.setCtx(context);
		SessionManager.cleanSessionBackground(httpSession.getId());
		Executions.sendRedirect("index.zul");
	}

	public void changeRole(Locale locale, Properties properties)
	{
		loginDesktop.changeRole(locale, properties);
	}

	public void clearDesktop(){
		//Reset the password
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
	
	/**
	 * External Authentication
	 * @return
	 */
	private boolean externalAuthentication() {
		
		AtomicReference<Boolean> authenticated = new AtomicReference<>(false);
		Optional<String> maybeCode = Optional.ofNullable(Executions.getCurrent().getParameter("code"));
		Optional<String> maybeStatus = Optional.ofNullable(Executions.getCurrent().getParameter("state"));
		maybeCode.ifPresent(code -> {
			maybeStatus.ifPresent(state ->{
				Optional<MUser> maybeUser = Optional.ofNullable(OpenIDUtil.getUserAuthenticated(code, state));
				maybeUser.ifPresent(user -> {
					Env.setContext(Env.getCtx(), Env.AD_USER_ID, user.get_ID());
					authenticated.set(true);
		        	OpenIDUtil.setErrorMessage(Env.getCtx(), "");
				});
				Executions.sendRedirect("index.zul");
			});
		});
		return authenticated.get();
	}
}
