package de.metas.adempiere.form.zk;

import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.session.SessionManager;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;


public class ZkDesktopClientUIInstance extends AbstractZkClientUIInstance
{
	private final Desktop desktop;
	private final IDesktop appDesktop;

	public ZkDesktopClientUIInstance()
	{
		super();
		this.desktop = retrieveCurrentExecutionDesktop();
		this.appDesktop = SessionManager.getAppDesktop();

	}

	@Override
	protected Desktop getDesktop()
	{
		return desktop;
	}

	@Override
	protected Component getEventComponent()
	{
		return appDesktop.getComponent();
	}
}
