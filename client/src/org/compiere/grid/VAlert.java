package org.compiere.grid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import javax.swing.JDialog;

import org.compiere.apps.ConfirmPanel;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *  Display for Text (textArea) with HTML (textPane) View
 *
 * 	@author 	Paul Bowden
 * 	@version 	$Id: Memo.java,v 1.00 2005/05/28 21:03:20 pb Exp $
 */
public class VAlert extends CDialog
		implements ActionListener
	{
		/**
	 * 
	 */
	private static final long serialVersionUID = -2406580979448574335L;

		/**
		 *	Standard constructor
		 *	@param frame parent
		 *	@param textArray of topic/memo pairs
		 *	@param editable if false = r/o
		 */
		public VAlert(Frame frame )
		{
			super (frame, Msg.getMsg(Env.getCtx(), "Note"), false);
			
			try
			{
				jbInit();
				setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				setAlwaysOnTop(true);
			}
			
			catch(Exception ex)
			{
				log.log(Level.SEVERE, "Editor", ex);
			}
			
		}	//	Memo

		/**
		 *  IDE Constructor
		 
		public Editor()
		{
			this (null);
		}	//	Editor
		*/
		
		
		/**	Logger			*/
		private static CLogger log = CLogger.getCLogger(VAlert.class);

		private CPanel panel = new CPanel();
		private BorderLayout panelLayout = new BorderLayout();
		private CTextPane textPane = new CTextPane();
		private ConfirmPanel confirmPanel = new ConfirmPanel(false);

		/**
		 *	Static Init
		 * 	@throws Exception
		 */
		private void jbInit() throws Exception
		{
			panel.setLayout(panelLayout);
			getContentPane().add(panel);
			textPane.setPreferredSize(new Dimension(350,300));
			textPane.setReadWrite(false);
			textPane.setMargin(new Insets(2,2,2,2));
			panel.add(textPane, BorderLayout.CENTER);
			this.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
			confirmPanel.addActionListener(this);
			pack();  //realise components
			confirmPanel.getOKButton().requestFocusInWindow();
		}	//	jbInit
		
		public void setText(String text) {
			textPane.setText(text);
		}
		
		/**
		 *	ActionListener
		 * 	@param e event
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals(ConfirmPanel.A_OK))
			{
				dispose();
			}
			
		}	//	actionPerformed


}
