package org.compiere.session;

import java.io.IOException;
import java.security.Identity;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;

public class AdempiereLoginModule implements LoginModule {

	private String unauthenticatedIdentity;
	private CallbackHandler handler;
	private Subject subject;
	private KeyNamePair[] roles;
	private String name;
	
	public boolean abort() throws LoginException {
		roles = null;
		name = null;
		return false;
	}

	public boolean commit() throws LoginException {
		if (roles == null || roles.length == 0)
		{
			subject.getPrincipals().add(new SimplePrincipal(unauthenticatedIdentity));
			SimpleGroup roleGroup = new SimpleGroup("Roles");
			subject.getPrincipals().add(roleGroup);
		}
		else
		{
			subject.getPrincipals().add(new SimplePrincipal(name));
			SimpleGroup roleGroup = new SimpleGroup("Roles");
			roleGroup.addMember(new SimplePrincipal("adempiereUsers"));
			for(int i = 0; i < roles.length; i++)
			{
				roleGroup.addMember(new SimplePrincipal(roles[i].getName()));
			}
			subject.getPrincipals().add(roleGroup);
		}
		return true;
	}

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		unauthenticatedIdentity = (String)options.get("unauthenticatedIdentity");
		handler = callbackHandler;
		this.subject = subject;
	}

	public boolean login() throws LoginException {
		Callback callbacks[] = new Callback[]{new NameCallback("Login:"), new PasswordCallback("Password:", false)};
		try {
			handler.handle(callbacks);
		} catch (IOException e) {
		} catch (UnsupportedCallbackException e) {
		}
		name = ((NameCallback)callbacks[0]).getName();
		char[] pass = ((PasswordCallback)callbacks[1]).getPassword();
		String passwd = pass != null ? new String(pass) : null;
		if (name != null && passwd != null)
		{
			Login login = new Login(Env.getCtx());
			roles = login.getRoles(name, passwd);
		}
		else
		{
			roles = null;
		}
		
		return true;
	}

	public boolean logout() throws LoginException {
		roles = null;
		name = null;
		
		return true;
	}

}
