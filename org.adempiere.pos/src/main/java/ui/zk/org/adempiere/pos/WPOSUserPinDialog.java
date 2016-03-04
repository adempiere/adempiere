package org.adempiere.pos;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.*;
import org.compiere.util.Msg;
import org.zkoss.zkex.zul.Center;

/**
 * This Component allows display the POS password Pin
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/01/16.
 */
public class WPOSUserPinDialog {
    public static void show(WPOS pos) {

        /** Actions 						*/
        Button 			b_ok 		 = new Button("Ok");
        Button 			b_cancel	 = new Button("Cancel");
        WPOSTextField	passwordField = new WPOSTextField("", pos.getKeyboard());
        passwordField.setStyle("Font-size:medium; font-weight:bold");
        passwordField.addEventListener(pos);
        passwordField.setType("password");
        Window 			w_alert;w_alert = new Window();
        Panel mainPanel = new Panel();
        w_alert.setWidth("200px");
        w_alert.setHeight("100px");

        Borderlayout mainLayout = new Borderlayout();
        Grid layout = GridFactory.newGridLayout();
        mainPanel.appendChild(mainLayout);
        mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
        mainLayout.setHeight("100%");
        mainLayout.setWidth("100%");

        Panel centerPanel = new Panel();
        Center center = new Center();
        center.setStyle("border: none");
        mainLayout.appendChild(center);
        center.appendChild(centerPanel);
        centerPanel.appendChild(layout);
        layout.setWidth("100%");
        layout.setHeight("100%");
        w_alert.appendChild(mainPanel);
        Rows rows = null;
        Row row = null;
        rows = layout.newRows();
        row = rows.newRow();

        Label msg = new Label(Msg.parseTranslation(pos.getCtx() , "@UserPIN@"));
        row.appendChild(msg);

        row = rows.newRow();
        row.appendChild(passwordField);

        row.setSpans("2");
        row = rows.newRow();
        row.appendChild(b_ok);
        row.appendChild(b_cancel);

        b_ok.addActionListener(pos);
        b_cancel.addEventListener("onClick", pos);
        AEnv.showWindow(w_alert);
    }
}
