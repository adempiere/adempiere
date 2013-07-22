package de.metas.adempiere.form.zk;

import org.adempiere.webui.desktop.IDesktop;
import org.adempiere.webui.session.SessionManager;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;

public class ZkCurrentClientUIInstance extends AbstractZkClientUIInstance
{

	public ZkCurrentClientUIInstance()
	{
		super();
	}

	@Override
	protected Desktop getDesktop()
	{
		return retrieveCurrentExecutionDesktop();
	}

	@Override
	protected Component getEventComponent()
	{
		final IDesktop appDesktop = SessionManager.getAppDesktop();
		return appDesktop.getComponent();
	}

}
