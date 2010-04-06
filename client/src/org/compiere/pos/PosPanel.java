package org.compiere.pos;

import org.compiere.apps.form.FormFrame;
import org.compiere.apps.form.FormPanel;

public class PosPanel implements FormPanel {
	private PosBasePanel panel;

	public void dispose() {
		panel.dispose();
	}

	public void init(int WindowNo, FormFrame frame) {
		panel = new PosBasePanel();
		panel.init(0, frame);
	}

}
