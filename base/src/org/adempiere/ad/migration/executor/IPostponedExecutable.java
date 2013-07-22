package org.adempiere.ad.migration.executor;

public interface IPostponedExecutable
{
	/**
	 * Execute postponed steps from the current migration context heap.
	 */
	public void execute();
}
