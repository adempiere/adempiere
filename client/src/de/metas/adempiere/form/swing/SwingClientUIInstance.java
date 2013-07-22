package de.metas.adempiere.form.swing;

import javax.swing.SwingUtilities;

import org.compiere.apps.ADialog;

import de.metas.adempiere.form.AbstractClientUIInstance;

class SwingClientUIInstance extends AbstractClientUIInstance
{

	public SwingClientUIInstance()
	{
		super();
	}

	@Override
	public void info(int WindowNo, String AD_Message)
	{
		ADialog.info(WindowNo, null, AD_Message);
	}

	@Override
	public void info(int WindowNo, String AD_Message, String message)
	{
		ADialog.info(WindowNo, null, AD_Message, message);
	}

	@Override
	public boolean ask(int WindowNo, String AD_Message)
	{
		return ADialog.ask(WindowNo, null, AD_Message);
	}

	@Override
	public boolean ask(int WindowNo, String AD_Message, String message)
	{
		return ADialog.ask(WindowNo, null, AD_Message, message);
	}

	@Override
	public void warn(int WindowNo, String AD_Message)
	{
		ADialog.warn(WindowNo, null, AD_Message);
	}

	@Override
	public void warn(int WindowNo, String AD_Message, String message)
	{
		ADialog.warn(WindowNo, null, AD_Message, message);
	}

	@Override
	public void error(int WIndowNo, String AD_Message)
	{
		ADialog.error(WIndowNo, null, AD_Message);
	}

	@Override
	public void error(int WIndowNo, String AD_Message, String message)
	{
		ADialog.error(WIndowNo, null, AD_Message, message);
	}

	@Override
	public void download(byte[] data, String contentType, String filename)
	{
		// FIXME: implement it using File dialog and then save the content there
		throw new UnsupportedOperationException();
	}

	@Override
	public void invokeLater(int windowNo, Runnable runnable)
	{
		SwingUtilities.invokeLater(runnable);
	}
}
