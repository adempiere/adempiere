/**
 * 
 */
package de.metas.adempiere.form.swing;

import de.metas.adempiere.form.AbstractClientUI;
import de.metas.adempiere.form.IClientUIInstance;

/**
 * @author cg
 * 
 */
public class SwingClientUI extends AbstractClientUI
{
	private final SwingClientUIInstance clientInstance;

	public SwingClientUI()
	{
		super();
		clientInstance = new SwingClientUIInstance();
	}

	@Override
	public IClientUIInstance createInstance()
	{
		return clientInstance;
	}

	@Override
	protected IClientUIInstance getCurrentInstance()
	{
		return clientInstance;
	}
}
