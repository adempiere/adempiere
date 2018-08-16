/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.component;

import java.io.IOException;

import org.adempiere.webui.apps.AEnv;
import org.compiere.util.CLogger;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;

/**
* Messagebox : Replaces ZK's Messagebox
*
* @author  Niraj Sohun
* @date    Jul 31, 2007
* @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/609">
 * 		@see FR [ 609 ] Confirm dialog for ZK don't have a Standard ADempiere Buttons</a>
*/
public class Messagebox extends Window implements EventListener
{	
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -4957498533838144942L;
	private static final String MESSAGE_PANEL_STYLE = "text-align:left; word-break: break-all; overflow: auto; max-height: 350pt; min-width: 230pt; max-width: 450pt;";	
	private String msg = new String("");
	private String imgSrc = new String("");
	/**	Logger			*/
	public static CLogger log = CLogger.getCLogger(Messagebox.class);

	private Text lblMsg = new Text();

	private Button btnOk = new Button();
	private Button btnCancel = new Button();
	private Button btnYes = new Button();
	private Button btnNo = new Button();
	private Button btnAbort = new Button();
	private Button btnRetry = new Button();
	private Button btnIgnore = new Button();

	private Image img = new Image();

	private int returnValue;

	/** A OK button. */
	public static final int OK = 0x0001;

	/** A Cancel button. */
	public static final int CANCEL = 0x0002;
	@Deprecated
	/** A Yes button. */
	public static final int YES = 0x0010;
	@Deprecated
	/** A No button. */
	public static final int NO = 0x0020;
	@Deprecated
	/** A Abort button. */
	public static final int ABORT = 0x0100;
	@Deprecated
	/** A Retry button. */
	public static final int RETRY = 0x0200;
	@Deprecated
	/** A IGNORE button. */
	public static final int IGNORE = 0x0400;

	/** A symbol consisting of a question mark in a circle. */
	public static final String QUESTION = "/images/dark/Question-btn.png";

	/** A symbol consisting of an exclamation point in a triangle with a yellow background. */
	public static final String EXCLAMATION  = "/images/dark/Warning-btn.png";

	/** A symbol of a lowercase letter i in a circle. */
	public static final String INFORMATION = "/images/dark/Info-btn.png";

	/** A symbol consisting of a white X in a circle with a red background. */
	public static final String ERROR = "/images/dark/Stop-btn.png";

	/** Contains no symbols. */
	public static final String NONE = null;
	
	/**	 */
	private Keylistener keyListener;
	
	private static final int KEYBOARD_KEY_RETURN = 13;
	
	public Messagebox()
	{
		super();
	}

	private void init()
	{
		lblMsg.setValue(msg);
		
		WAppsAction action;
		try {
			//	For Cancel
			action = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			btnCancel = action.getButton();
			btnCancel.addEventListener(Events.ON_CLICK, this);
			//	For Ok
			action = new WAppsAction (ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
			btnOk = action.getButton();
			btnOk.addEventListener(Events.ON_CLICK, this);
			//	For Yes
			action = new WAppsAction (ConfirmPanel.A_OK, null, ConfirmPanel.A_OK);
			btnYes = action.getButton();
			btnYes.addEventListener(Events.ON_CLICK, this);
			btnYes.setName("btnYes");
			btnYes.setId("btnYes");
			//	For No
			action = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			btnNo = action.getButton();
			btnNo.addEventListener(Events.ON_CLICK, this);
			btnYes.setName("btnNo");
			btnYes.setId("btnNo");
			//	For Abort
			action = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			btnAbort = action.getButton();
			btnAbort.addEventListener(Events.ON_CLICK, this);
			btnAbort.setLabel("Abort");
			btnAbort.setName("btnAbort");
			btnAbort.setId("btnAbort");
			//	For Retry
			action = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			btnRetry = action.getButton();
			btnRetry.addEventListener(Events.ON_CLICK, this);
			btnRetry.setLabel("Retry");
			btnRetry.setName("btnRetry");
			btnRetry.setId("btnRetry");
			//	For Ignore
			action = new WAppsAction (ConfirmPanel.A_CANCEL, null, ConfirmPanel.A_CANCEL);
			btnIgnore = action.getButton();
			btnIgnore.addEventListener(Events.ON_CLICK, this);
			btnIgnore.setLabel("Ignore");
			btnIgnore.setName("btnIgnore");
			btnIgnore.setId("btnIgnore");
		} catch (IOException e) {
			log.warning("Error loading buttons " + e.getLocalizedMessage());
		}

		Panel pnlMessage = new Panel();
		pnlMessage.setStyle(MESSAGE_PANEL_STYLE);
		pnlMessage.appendChild(lblMsg);

		keyListener = new Keylistener();
		
		keyListener.setCtrlKeys("#enter");
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);
		addEventListener(Events.ON_CANCEL, this);
		appendChild(keyListener);
		Hbox pnlImage = new Hbox();

		img.setSrc(imgSrc);

		pnlImage.setWidth("72px");
		pnlImage.setAlign("center");
		pnlImage.setPack("center");
		pnlImage.appendChild(img);
				
		Hbox north = new Hbox();
		north.setAlign("center");
		north.setStyle("margin: 20pt 10pt 20pt 10pt;"); //trbl
		this.appendChild(north);		
		north.appendChild(pnlImage);
		north.appendChild(pnlMessage);

		Hbox pnlButtons = new Hbox();
		pnlButtons.setHeight("52px");
		pnlButtons.setAlign("center");
		pnlButtons.setPack("end");
		pnlButtons.appendChild(btnCancel);		
		pnlButtons.appendChild(btnOk);
		pnlButtons.appendChild(btnYes);
		pnlButtons.appendChild(btnNo);
		pnlButtons.appendChild(btnAbort);
		pnlButtons.appendChild(btnRetry);
		pnlButtons.appendChild(btnIgnore);

		Separator separator = new Separator();
		separator.setWidth("100%");
		separator.setBar(true);
		this.appendChild(separator);
		
		Hbox south = new Hbox();
		south.setPack("end");
		south.setWidth("100%");
		this.appendChild(south);		
		south.appendChild(pnlButtons);
		
		this.setBorder("normal");
		this.setContentStyle("background-color:#ffffff;");
		this.setPosition("left, top");
	}

	public int show(String message, String title, int buttons, String icon)
	{
		this.msg = message;
		this.imgSrc = icon;
		
		init();
		
		btnOk.setVisible(false);
		btnCancel.setVisible(false);
		btnYes.setVisible(false);
		btnNo.setVisible(false);
		btnRetry.setVisible(false);
		btnAbort.setVisible(false);
		btnIgnore.setVisible(false);

		if ((buttons & OK) != 0)
			btnOk.setVisible(true);

		if ((buttons & CANCEL) != 0)
			btnCancel.setVisible(true);

		if ((buttons & YES) != 0)
			btnYes.setVisible(true);

		if ((buttons & NO) != 0)
			btnNo.setVisible(true);

		if ((buttons & RETRY) != 0)
			btnRetry.setVisible(true);

		if ((buttons & ABORT) != 0)
			btnAbort.setVisible(true);

		if ((buttons & IGNORE) != 0)
			btnIgnore.setVisible(true);

		this.setTitle(title);
		this.setPosition("center");
		this.setClosable(true);
		if (Events.inEventListener())
			this.setAttribute(Window.MODE_KEY, Window.MODE_MODAL);
		else
			this.setAttribute(Window.MODE_KEY, Window.MODE_HIGHLIGHTED);
		this.setSizable(true);

		this.setVisible(true);
		AEnv.showCenterScreen(this);

		return returnValue;
	}

	public static int showDialog(String message, String title, int buttons, String icon) throws InterruptedException
	{
		Messagebox msg = new Messagebox();

		return msg.show(message, title, buttons, icon);
	}

	public void onEvent(Event event) throws Exception
	{
		if (event == null)
			return;
		
		if (event.getName().equals(Events.ON_CTRL_KEY) && event.getTarget() == keyListener) {
				
				KeyEvent keyEvent = (KeyEvent) event;
				int code = keyEvent.getKeyCode();
				if (code == KEYBOARD_KEY_RETURN) {
					returnValue = OK; 
				}
		 }
		if (event.getTarget() == btnOk || event.getName().equals(Events.ON_OK))
		{
			returnValue = OK;
		}
		else if (event.getTarget() == btnCancel || event.getName().equals(Events.ON_CANCEL))
		{
			returnValue = CANCEL;
		}
		else if (event.getTarget() == btnYes)
		{
			returnValue = YES;
		}
		else if (event.getTarget() == btnNo)
		{
			returnValue = NO;
		}
		else if (event.getTarget() == btnAbort)
		{
			returnValue = ABORT;
		}
		else if (event.getTarget() == btnRetry)
		{
			returnValue = RETRY;
		}
		else if (event.getTarget() == btnIgnore)
		{
			returnValue = IGNORE;
		}

		this.detach();
	}
}
