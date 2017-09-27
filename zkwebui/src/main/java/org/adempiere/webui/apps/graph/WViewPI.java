package org.adempiere.webui.apps.graph;

import org.adempiere.webui.panel.ADForm;

public class WViewPI extends ADForm {

	/**
	 *
	 */
	private static final long serialVersionUID = -755873621984745607L;

	@Override
	protected void initForm() {
		WPAPanel paPanel = WPAPanel.get();
		appendChild(paPanel);
	}

}
