/**
 * 
 */
package de.metas.adempiere.form.zk;

import de.metas.adempiere.form.AbstractClientUI;
import de.metas.adempiere.form.IClientUIInstance;

/**
 * @author cg
 * 
 */
public class ZKClientUI extends AbstractClientUI
{
	private final ZkCurrentClientUIInstance currentInstance;

	public ZKClientUI()
	{
		super();
		currentInstance = new ZkCurrentClientUIInstance();
	}

	@Override
	public IClientUIInstance createInstance()
	{
		return new ZkDesktopClientUIInstance();
	}

	@Override
	protected IClientUIInstance getCurrentInstance()
	{
		return currentInstance;
	}

}
