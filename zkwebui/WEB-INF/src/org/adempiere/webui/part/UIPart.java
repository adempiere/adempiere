package org.adempiere.webui.part;

import org.zkoss.zk.ui.Component;

public interface UIPart {
	
	public Component createPart(Object parent);
	
	public Component getComponent();
	
}
