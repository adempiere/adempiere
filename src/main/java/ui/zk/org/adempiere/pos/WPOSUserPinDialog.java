package org.adempiere.pos;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;

/**
 * This Component allows display the POS password Pin
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/01/16.
 */
public class WPOSUserPinDialog extends Window implements EventListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6703604418836828930L;

	public WPOSUserPinDialog() {
		super();
	}

    /** Actions 						*/
    private Button 			b_ok 		 = new Button("Ok");
    private Button 			b_cancel	 = new Button("Cancel");
    private WPOSTextField	passwordField; 
    private boolean isKeyboard = false;
    
	/** A OK button. */
	public static final int OK = 0x0001;
	/** A Cancel button. */
	public static final int CANCEL = 0x0002;

	private char[] 			returnValue;
	
	private void showDialog(WPOS pos) {
		passwordField = new WPOSTextField("", pos.getKeyboard());
        passwordField.setStyle("Font-size:medium; font-weight:bold");
        passwordField.addEventListener(this);
        passwordField.addEventListener(Events.ON_OK,this);
        passwordField.setType("password");
        
        Panel mainPanel = new Panel();
        this.setWidth("200px");
        this.setHeight("100px");
        b_ok.addActionListener(pos);
        b_ok.addEventListener(Events.ON_OK,pos);
        b_cancel.addActionListener(pos);

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
        this.appendChild(mainPanel);
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

        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);
        AEnv.showWindow(this);
        pos.validateAndSetUserPin(returnValue);
    }
	
	public static void show(WPOS p_POS) {
		WPOSUserPinDialog msg = new WPOSUserPinDialog();
		msg.showDialog(p_POS);
	}

	@Override
	public void onEvent(Event e) throws Exception {
		if (e.getTarget().equals(b_ok) || e.getName().equals(Events.ON_OK)) {
			returnValue = passwordField.getText().toCharArray();
		}
		else if(e.getTarget().equals(passwordField.getComponent(WPOSTextField.SECONDARY))
					&& e.getName().equals(Events.ON_FOCUS) && !isKeyboard){
						isKeyboard = true;
						passwordField.showKeyboard();
						passwordField.setFocus(true);
						return;
				}
		else if(e.getTarget().equals(passwordField.getComponent(WPOSTextField.PRIMARY)) 
				   && e.getName().equals(Events.ON_FOCUS)){
					isKeyboard = false;
					return;
		}
		
		this.onClose();
	}
    
    
    
}
