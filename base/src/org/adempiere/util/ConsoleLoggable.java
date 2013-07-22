package org.adempiere.util;

import java.io.PrintStream;

public class ConsoleLoggable implements ILoggable
{
	private final PrintStream out;

	public ConsoleLoggable()
	{
		this.out = System.out;
	}

	@Override
	public void addLog(String msg)
	{
		out.println(msg);
	}

}
