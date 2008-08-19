package org.adempiere.webui.component;

import org.compiere.util.Env;
import org.compiere.util.Msg;

public class ButtonFactory {

	public static Button createButton(String action, String label) {
		Button button = new Button();
		button.setId(action);
		button.setImage("images/" + action + "16.gif");
		if (label == null) {
			label = Msg.getMsg(Env.getCtx(), action);
			if (label != null && !label.equals(action)) {
				label = label.replaceAll("[&]", "");
				button.setLabel(label);
			}
		}
		
		return button;
	}
}
