/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
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
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Level;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.compiere.model.MRole;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CMenuItem;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 *  Dialog Windows
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: ADialogDialog.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class ADialogDialog extends CDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5670261006862936363L;

	/**
	 *	Create Dialog Window for Frame
	 *
	 * @param frame
	 * @param title
	 * @param message
	 * @param messageType
	 */
	public ADialogDialog(Frame frame, String title, String message, int messageType)
	{
		super (frame, title, frame != null);
		common (message, messageType);
		AEnv.showCenterWindow(frame, this);
	}	//	ADialogDialog

	/**
	 *	Create Dialog Window for Dialog
	 *
	 * @param dialog
	 * @param title
	 * @param message
	 * @param messageType
	 */
	public ADialogDialog(Dialog dialog, String title, String message, int messageType)
	{
		super (dialog, title, dialog != null);
		common (message, messageType);
		AEnv.showCenterWindow(dialog, this);
	}	//	ADialogDialog

	/**
	 *	Common Init
	 *  @param message
	 *  @param messageType
	 */
	private void common (String message, int messageType)
	{
		try
		{
			setInfoMessage (message);
			jbInit();
			setInfoIcon (messageType);
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "ADialogDialog.common - " + ex.getMessage());
		}
		//  Default Button
		this.getRootPane().setDefaultButton(confirmPanel.getOKButton());
	}	//	common

	/**
	 *  Window Events - requestFocus
	 *  @param e
	 */
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_OPENED)
			confirmPanel.getOKButton().requestFocusInWindow();
	}   //  processWindowEvent

	/** Answer OK (0)       */
	public static int	A_OK = 0;
	/** Answer Cancel (1)   */
	public static int	A_CANCEL = 1;
	/** Answer Close (-1) - Default	*/
	public static int	A_CLOSE = -1;
	/** Answer				*/
	private int			m_returnCode = A_CLOSE;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(ADialogDialog.class);

	private static Icon i_inform = Env.getImageIcon("Inform32.gif");
	private static Icon i_warn = Env.getImageIcon("Warn32.gif");
	private static Icon i_question = Env.getImageIcon("Question32.gif");
	private static Icon i_error = Env.getImageIcon("Error32.gif");

	private JMenuBar menuBar = new JMenuBar();
	private JMenu mFile = AEnv.getMenu("File");
	private CMenuItem mEMail = new CMenuItem();
	private CMenuItem mPrintScreen = new CMenuItem();
	private CMenuItem mScreenShot = new CMenuItem();
	private CMenuItem mEnd = new CMenuItem();
	private CMenuItem mPreference = new CMenuItem();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private CPanel westPanel = new CPanel();
	private CLabel iconLabel = new CLabel();
	private GridBagLayout westLayout = new GridBagLayout();
	private CTextPane info = new CTextPane ();
	private GridBagLayout infoLayout = new GridBagLayout();
	private CPanel infoPanel = new CPanel();

	/**
	 *	Static Constructor
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setJMenuBar(menuBar);
		//
		mEMail.setIcon(Env.getImageIcon("EMailSupport16.gif"));
		mEMail.setText(Msg.getMsg(Env.getCtx(), "EMailSupport"));
		mEMail.addActionListener(this);
		mPrintScreen.setIcon(Env.getImageIcon("PrintScreen16.gif"));
		mPrintScreen.setText(Msg.getMsg(Env.getCtx(), "PrintScreen"));
		mPrintScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, 0));
		mPrintScreen.addActionListener(this);
		mScreenShot.setIcon(Env.getImageIcon("ScreenShot16.gif"));
		mScreenShot.setText(Msg.getMsg(Env.getCtx(), "ScreenShot"));
		mScreenShot.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PRINTSCREEN, Event.SHIFT_MASK));
		mScreenShot.addActionListener(this);
		mPreference.setIcon(Env.getImageIcon("Preference16.gif"));
		mPreference.setText(Msg.getMsg(Env.getCtx(), "Preference"));
		mPreference.addActionListener(this);
		mEnd.setIcon(Env.getImageIcon("End16.gif"));
		mEnd.setText(Msg.getMsg(Env.getCtx(), "End"));
		mEnd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.ALT_MASK));
		mEnd.addActionListener(this);
		//
		westPanel.setLayout(westLayout);
		westPanel.setName("westPanel");
		westPanel.setRequestFocusEnabled(false);
		infoPanel.setLayout(infoLayout);
		infoPanel.setName("infoPanel");
		infoPanel.setRequestFocusEnabled(false);
		this.getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		this.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.add(iconLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		this.getContentPane().add(infoPanel, BorderLayout.CENTER);
		infoPanel.add(info, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));
		//
		menuBar.add(mFile);
		mFile.add(mPrintScreen);
		mFile.add(mScreenShot);
		mFile.addSeparator();
		mFile.add(mEMail);
		if (MRole.getDefault().isShowPreference())
			mFile.add(mPreference);
		mFile.addSeparator();
		mFile.add(mEnd);
		//
		confirmPanel.addActionListener(this);
	}	//	jbinit

	
	/**
	 *	Convert to HTML and Calculate Size
	 *  @param message message
	 */
	private void setInfoMessage(String message)
	{
		StringBuffer sb = new StringBuffer (message.length()+20);
		sb.append("<b>");
		String html = Util.maskHTML(message);
		char[] chars = html.toCharArray();
		boolean first = true;
		int paras = 0;
		for (int i = 0; i < chars.length; i++)
		{
			char c = chars[i];
			if (c == '\n')
			{
				if (first)
				{
					sb.append("</b>");
					first = false;
				}
				if (paras > 1)
					sb.append("<br>");
				else
					sb.append("<p>");
				paras++;
			}
			else
				sb.append(c);
		}
		info.setText(sb.toString());
		Dimension size = info.getPreferredSize();
		size.width = 450;
		size.height = (Math.max(paras, message.length()/60)+1) * 30;
		size.height = Math.min(size.height, 600);
		info.setPreferredSize(size);
	//	Log.print("Para=" + paras + " - " + info.getPreferredSize());
		
		info.setRequestFocusEnabled(false);
		info.setReadWrite(false);
		info.setOpaque(false);
		info.setBorder(null);
		//
		info.setCaretPosition(0);
	}	//	calculateSize

	
	/**************************************************************************
	 *	Set Info
	 *  @param messageType
	 */
	private void setInfoIcon (int messageType)
	{
		confirmPanel.getCancelButton().setVisible(false);
		//
		switch (messageType)
		{
			case JOptionPane.ERROR_MESSAGE:
				iconLabel.setIcon(i_error);
				break;
			case JOptionPane.INFORMATION_MESSAGE:
				iconLabel.setIcon(i_inform);
				break;
			case JOptionPane.QUESTION_MESSAGE:
				confirmPanel.getCancelButton().setVisible(true);
				iconLabel.setIcon(i_question);
				break;
			case JOptionPane.WARNING_MESSAGE:
				iconLabel.setIcon(i_warn);
				break;

			case JOptionPane.PLAIN_MESSAGE:
			default:
				break;
		}	//	switch
	}	//	setInfo

	
	/**************************************************************************
	 *	ActionListener
	 *  @param e
	 */
	public void actionPerformed (ActionEvent e)
	{
	//	log.finest( "ADialogDialog.actionPerformed - " + e);
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			m_returnCode = A_OK;
			dispose();
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL) || e.getSource() == mEnd)
		{
			m_returnCode = A_CANCEL;
			dispose();
		}
		else if (e.getSource() == mPrintScreen)
			printScreen();
		else if (e.getSource() == mEMail)
		{
			String title = getTitle();
			String text = info.getText();
			dispose();                  //  otherwise locking
			ADialog.createSupportEMail(this, title, text);
		}
		else if (e.getSource() == mPreference)
		{
			if (MRole.getDefault().isShowPreference())
			{
				Preference p = new Preference (null, 0);
				p.setVisible(true);
			}
		}
	}	//	actionPerformed

	/**
	 *	Get Return Code
	 *  @return return code
	 */
	public int getReturnCode()
	{
		return m_returnCode;
	}	//	getReturnCode

	/**
	 *	PrintScreen
	 */
	private void printScreen()
	{
		PrintScreenPainter.printScreen(this);
	}	//	printScreen

}	//	ADialogDialog
