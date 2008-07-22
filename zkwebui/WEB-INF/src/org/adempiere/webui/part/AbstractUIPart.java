package org.adempiere.webui.part;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;

public abstract class AbstractUIPart implements UIPart {
	
	protected Page page = null;
	
	public Component createPart(Object parent) {
		if (parent == null)
			throw new IllegalArgumentException("Null parent.");
		
		Component component = null;
		if (parent instanceof Page) {
			page = (Page)parent;
		} else if (parent instanceof Component) {
			component = (Component)parent;
			page = component.getPage();
		} else {
			throw new IllegalArgumentException("Parent must of class Page or Component.");
		}
		
		return doCreatePart(component);
	}
	
	protected abstract Component doCreatePart(Component parent);

}
