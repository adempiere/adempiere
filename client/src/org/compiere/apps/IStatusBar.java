package org.compiere.apps;

import org.compiere.model.DataStatusEvent;

public interface IStatusBar 
{
	public void setStatusDB (String text);
	
	public void setStatusDB (String text, DataStatusEvent dse);
	
	public void setStatusLine (String text);
	
	public void setStatusLine (String text, boolean error);
	
	public void setInfo (String text);
}
