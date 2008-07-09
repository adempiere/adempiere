package org.adempiere.webui;

public interface IWebClient {

	public void loginCompleted();

	public void logout();

	public IDesktop getAppDeskop();

}