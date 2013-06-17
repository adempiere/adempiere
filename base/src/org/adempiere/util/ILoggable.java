package org.adempiere.util;


/**
 * Interface implementations can be passed to business logic to perform high-level logging. The signature of this
 * interface's only method is chosen so that all {@link org.compiere.process.SvrProcess} subclasses can implement it without further code
 * changes.
 * 
 * @author ts
 * 
 */
public interface ILoggable
{
	/**
	 * Add a log message.
	 * 
	 * @param msg
	 */
	public void addLog(String msg);
}
