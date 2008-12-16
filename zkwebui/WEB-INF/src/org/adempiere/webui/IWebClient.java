package org.adempiere.webui;

import org.adempiere.webui.desktop.IDesktop;

public interface IWebClient {

	public void loginCompleted();

	public void logout();

	public IDesktop getAppDeskop();

}