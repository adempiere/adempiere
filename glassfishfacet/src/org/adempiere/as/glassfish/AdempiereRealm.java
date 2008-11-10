package org.adempiere.as.glassfish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Login;

import com.sun.appserv.security.AppservRealm;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.IASRealm;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;

public class AdempiereRealm extends AppservRealm {

	private Map<String, List<String>> groupCache = new HashMap<String, List<String>>();

	@Override
	protected void init(Properties props) throws BadRealmException,
			NoSuchRealmException {
		String jaasCtx = props.getProperty(IASRealm.JAAS_CONTEXT_PARAM);
		this.setProperty(IASRealm.JAAS_CONTEXT_PARAM, jaasCtx);
		if (jaasCtx == null) {
			throw new BadRealmException(IASRealm.JAAS_CONTEXT_PARAM
					+ " is null.");
		}
	}

	@Override
	public String getAuthType() {
		return "adempiere";
	}

	@Override
	public Enumeration getGroupNames(String username)
			throws InvalidOperationException, NoSuchUserException {
		if (groupCache.containsKey(username))
			return Collections.enumeration(groupCache.get(username));
		else
			return null;
	}

	public String[] authenticate(String username, String password) {
		KeyNamePair[] roles = null;
		// do authentication
		if (username != null && password != null) {
			// perform db authentication
			Login login = new Login(Env.getCtx());
			roles = login.getRoles(username, password);
		} else {
			// no username or password
			roles = null;
		}

		String[] groups = new String[0];
		List<String> grpList = new ArrayList<String>();
		if (roles != null && roles.length > 0) {
			grpList.add("adempiereUsers");
			for (KeyNamePair knp : roles) {
				grpList.add(knp.getName());
			}
			groupCache.remove(username);
			groupCache.put(username, grpList);
			groups = grpList.toArray(groups);
		}
		return groups;
	}
}
