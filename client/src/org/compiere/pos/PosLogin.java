package org.compiere.pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import net.miginfocom.swing.MigLayout;

import org.compiere.apps.AppsAction;
import org.compiere.model.MUser;
import org.compiere.swing.CButton;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class PosLogin extends CDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8490567722808711399L;
	private PosBasePanel posPanel;
	private PosTextField username;
	private PosTextField pin;
	private CButton bProcess;

	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public PosLogin (PosBasePanel posPanel)
	{
		super (Env.getFrame(posPanel),Msg.translate(posPanel.getCtx(), "Login"), true);
		init();
		this.posPanel = posPanel;
	}

	private void init() {
		CPanel panel = new CPanel();
		panel.setLayout(new MigLayout());
		getContentPane().add(panel);
		
		panel.add(new CLabel(Msg.translate(posPanel.getCtx(),"SalesRep_ID")));
		
		username = new PosTextField(Msg.translate(posPanel.getCtx(),"SalesRep_ID"),
		posPanel, posPanel.p_pos.getOSK_KeyLayout_ID());	
		
		panel.add( username, "wrap");
		
		panel.add(new CLabel(Msg.translate(posPanel.getCtx(), "UserPIN")));
		
		pin = new PosTextField(Msg.translate(posPanel.getCtx(), "UserPIN"), posPanel, posPanel.p_pos.getOSNP_KeyLayout_ID());
		
		panel.add(pin, "");
		
		AppsAction act = new AppsAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), false);
		act.setDelegate(this);
		bProcess = (CButton)act.getButton();
		bProcess.setFocusable(false);
		panel.add (bProcess, "h 50!, w 50!");
		
		pack();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource().equals(bProcess) )
		{
			MUser.get(posPanel.getCtx(), username.getText(), pin.getText());
		}
		
		dispose();
	}

}
