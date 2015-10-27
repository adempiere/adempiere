package org.adempiere.pos;

import java.awt.KeyboardFocusManager;
import java.util.Properties;

import javax.swing.JFrame;

import org.compiere.Adempiere;
import org.compiere.apps.AEnv;
import org.compiere.apps.AKeyboardFocusManager;
import org.compiere.apps.ALogin;
import org.compiere.apps.form.FormFrame;
import org.compiere.model.MSession;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Splash;

public class POSApplication {
	
	/**	Properties		*/
	private Properties m_ctx;

	/**
	 * *** Constructor ***
	 */
	public POSApplication() {
		Adempiere.startup(true);	//	needs to be here for UI
		Splash splash = Splash.getSplash();
		final FormFrame frame = new FormFrame(splash.getGraphicsConfiguration());
		//  Focus Traversal
		KeyboardFocusManager.setCurrentKeyboardFocusManager(AKeyboardFocusManager.get());
		
		ALogin login = new ALogin(splash);
		if (!login.initLogin())		//	no automatic login
		{
			//	Center the window
			try {
				AEnv.showCenterScreen(login);	//	HTML load errors
			} catch (Exception ex) {
				
			}
			if (!login.isConnected() || !login.isOKpressed())
				AEnv.exit(1);
		}

		//  Check Build
		if (!DB.isBuildOK(m_ctx))
			AEnv.exit(1);

		//  Check DB	(AppsServer Version checked in Login)
		DB.isDatabaseOK(m_ctx);
	
		splash.setText(Msg.getMsg(m_ctx, "Loading"));
		splash.toFront();
		splash.paint(splash.getGraphics());
	
		//
		if (!Adempiere.startupEnvironment(true)) // Load Environment
			System.exit(1);		
		MSession.get (Env.getCtx(), true);		//	Start Session
		
		//  Default Image
		frame.setIconImage(Adempiere.getImage16());

		// Setting close operation/listener - teo_sarca [ 1684168 ]
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.addWindowListener(new WindowListener() {
//			public void windowClosing(WindowEvent e) {
//				if (!ADialog.ask(0, null, "ExitApplication?"))
//					return;
//				frame.dispose();
//			}
//			public void windowActivated(WindowEvent e) {}
//			public void windowClosed(WindowEvent e) {}
//			public void windowDeactivated(WindowEvent e) {}
//			public void windowDeiconified(WindowEvent e) {}
//			public void windowIconified(WindowEvent e) {}
//			public void windowOpened(WindowEvent e) {}
//		});

		VPOS pos = new VPOS();
		pos.init(0,(FormFrame) frame);
		frame.pack();
		splash.dispose();
		splash = null;	
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new POSApplication();
	}
}