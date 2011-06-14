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
 * Contributor(s):                                                            *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.compiere.grid.ed.VLookup;
import org.compiere.model.Lookup;
import org.compiere.model.MClient;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextArea;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	EMail Dialog
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: EMailDialog.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  
 *  globalqss: integrate phib fixing bug reported here
 *     http://sourceforge.net/tracker/index.php?func=detail&aid=1568765&group_id=176962&atid=879332
 * 
 *  phib - fixing bug [ 1568765 ] Close email dialog button broken
 *  
 *  globalqss - Carlos Ruiz - implement CC - FR [ 1754879 ] Enhancements on sending e-mail
 *
 */
public class EMailDialog extends CDialog 
	implements ActionListener, VetoableChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6850428309037475213L;

	/**
	 * 	EMail Dialog
	 *	@param owner calling window
	 *	@param title title
	 *	@param from from
	 *	@param to to 
	 *	@param subject subject
	 *	@param message message
	 *	@param attachment optional attachment
	 */
	public EMailDialog (Dialog owner, String title, MUser from, String to, 
		String subject, String message, File attachment)
	{
		super (owner, title, true);
		commonInit(from, to, subject, message, attachment);
	}	//	EmailDialog

	/**
	 * 	EMail Dialog
	 *	@param owner calling window
	 *	@param title title
	 *	@param from from
	 *	@param to to 
	 *	@param subject subject
	 *	@param message message
	 *	@param attachment optional attachment
	 */
	public EMailDialog (Frame owner, String title, MUser from, String to, 
		String subject, String message, File attachment)
	{
		super (owner, title, true);
		commonInit(from, to, subject, message, attachment);
	}	//	EmailDialog

	/**
	 * 	Common Init
	 *	@param from from
	 *	@param to to 
	 *	@param subject subject
	 *	@param message message
	 *	@param attachment optional attachment
	 */
	private void commonInit (MUser from, String to, 
		String subject, String message, File attachment)
	{
		m_client = MClient.get(Env.getCtx());
		try
		{
			int WindowNo = 0;
			int AD_Column_ID = 0;
			Lookup lookup = MLookupFactory.get (Env.getCtx(), WindowNo, 
				AD_Column_ID, DisplayType.Search,
				Env.getLanguage(Env.getCtx()), "AD_User_ID", 0, false,
				"EMail IS NOT NULL");
			fUser = new VLookup ("AD_User_ID", false, false, true, lookup);
			fUser.addVetoableChangeListener(this);
			fCcUser = new VLookup ("AD_User_ID", false, false, true, lookup);
			fCcUser.addVetoableChangeListener(this);
			jbInit();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "EMailDialog", ex);
		}
		set(from, to, subject, message);
		setAttachment(attachment);
		AEnv.showCenterScreen(this);
	}	//	commonInit


	/**	Client				*/
	private MClient	m_client = null;
	/** Sender				*/
	private MUser	m_from = null;
	/** Primary Recipient	*/
	private MUser	m_user = null;
	/** Cc Recipient	*/
	private MUser	m_ccuser = null;
	//
	private String  m_to;
	private String  m_cc;
	private String  m_subject;
	private String  m_message;
	/**	File to be optionally attached	*/
	private File	m_attachFile;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(EMailDialog.class);

	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel headerPanel = new CPanel();
	private GridBagLayout headerLayout = new GridBagLayout();
	private CTextField fFrom = new CTextField(20);
	private CTextField fTo = new CTextField(20);
	private CTextField fCc = new CTextField(20);
	private VLookup fUser = null;
	private VLookup fCcUser = null;
	private CTextField fSubject = new CTextField(40);
	private	CLabel lFrom = new CLabel();
	private CLabel lTo = new CLabel();
	private CLabel lCc = new CLabel();
	private CLabel lSubject = new CLabel();
	private CLabel lAttachment = new CLabel();
	private CTextField fAttachment = new CTextField(40);
	private CTextArea fMessage = new CTextArea();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private StatusBar statusBar = new StatusBar();

	/**
	 *	Static Init
	 */
	void jbInit() throws Exception
	{
		lFrom.setText(Msg.getMsg(Env.getCtx(), "From") + ":");
		lTo.setText(Msg.getMsg(Env.getCtx(), "To") + ":");
		lCc.setText(Msg.getMsg(Env.getCtx(), "Cc") + ":");
		lSubject.setText(Msg.getMsg(Env.getCtx(), "Subject") + ":");
		lAttachment.setText(Msg.getMsg(Env.getCtx(), "Attachment") + ":");
		fFrom.setReadWrite(false);
		//
		mainPanel.setLayout(mainLayout);
		headerPanel.setLayout(headerLayout);
		mainLayout.setHgap(5);
		mainLayout.setVgap(5);
		fMessage.setPreferredSize(new Dimension(150, 150));
		getContentPane().add(mainPanel);
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		
		headerPanel.add(lFrom, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
		headerPanel.add(fFrom, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 5, 10), 0, 0));

		headerPanel.add(lTo, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
		headerPanel.add(fUser, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
		headerPanel.add(fTo, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
		headerPanel.add(lCc, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
			headerPanel.add(fCcUser, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));
		headerPanel.add(fCc, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 10), 0, 0));

		headerPanel.add(lSubject, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 10, 0, 5), 0, 0));
		headerPanel.add(fSubject, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 10), 1, 0));

		headerPanel.add(lAttachment, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 10, 0, 5), 0, 0));
		headerPanel.add(fAttachment, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 0, 0, 10), 1, 0));

		mainPanel.add(fMessage, BorderLayout.CENTER);
		//
		mainPanel.add (confirmPanel, BorderLayout.SOUTH);
		this.getContentPane().add(statusBar, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
		statusBar.setStatusDB(null);
	}	//	jbInit

	/**
	 *	Set all properties
	 */
	public void set (MUser from, String to, String subject, String message)
	{
		//	Content
		setFrom(from);
		setTo(to);
		setSubject(subject);
		setMessage(message);
		//
		statusBar.setStatusLine(m_client.getSMTPHost());
	}	//	set

	/**
	 *  Set Address
	 */
	public void setTo(String newTo)
	{
		m_to = newTo;
		fTo.setText(m_to);
	}	//	setTo

	/**
	 *  Set CC Address
	 */
	public void setCc(String newCc)
	{
		m_cc = newCc;
		fCc.setText(m_cc);
	}	//	setCc

	/**
	 *  Get Address
	 */
	public String getTo()
	{
		m_to = fTo.getText();
		return m_to;
	}	//	getTo

	/**
	 *  Get CC Address
	 */
	public String getCc()
	{
		m_cc = fCc.getText();
		return m_cc;
	}	//	getCc

	/**
	 *  Set Sender
	 */
	public void setFrom(MUser newFrom)
	{
		m_from = newFrom;
		if (newFrom == null 
			|| !newFrom.isEMailValid() 
			|| !newFrom.isCanSendEMail())
		{
			confirmPanel.getOKButton().setEnabled(false);
			fFrom.setText("**Invalid**");
		}
		else
			fFrom.setText(m_from.getEMail());
	}	//	setFrom

	/**
	 *  Get Sender
	 */
	public MUser getFrom()
	{
		return m_from;
	}	//	getFrom

	/**
	 *  Set Subject
	 */
	public void setSubject(String newSubject)
	{
		m_subject = newSubject;
		fSubject.setText(m_subject);
	}	//	setSubject

	/**
	 *  Get Subject
	 */
	public String getSubject()
	{
		m_subject = fSubject.getText();
		return m_subject;
	}	//	getSubject

	/**
	 *  Set Message
	 */
	public void setMessage(String newMessage)
	{
		m_message = newMessage;
		fMessage.setText(m_message);
		fMessage.setCaretPosition(0);
	}   //  setMessage

	/**
	 *  Get Message
	 */
	public String getMessage()
	{
		m_message = fMessage.getText();
		return m_message;
	}   //  getMessage

	/**
	 *  Set Attachment
	 */
	public void setAttachment (File attachment)
	{
		m_attachFile = attachment;
		if (attachment == null)
		{
			lAttachment.setVisible(false);
			fAttachment.setVisible(false);
		}
		else
		{
			lAttachment.setVisible(true);
			fAttachment.setVisible(true);
			fAttachment.setText(attachment.getName());
			fAttachment.setReadWrite(false);
		}
	}	//	setAttachment

	/**
	 *  Get Attachment
	 */
	public File getAttachment()
	{
		return m_attachFile;
	}	//	getAttachment

	/**************************************************************************
	 * 	Action Listener - Send email
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();
		
		if (getTo() == null || getTo().length() == 0)
		{
			return;
		}
		//	Send
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			confirmPanel.getOKButton().setEnabled(false);

			StringTokenizer st = new StringTokenizer(getTo(), " ,;", false);
			String to = st.nextToken();
			EMail email = m_client.createEMail(getFrom(), to, getSubject(), getMessage());
			String status = "Check Setup";
			if (email != null)
			{
				while (st.hasMoreTokens())
					email.addTo(st.nextToken());
				// cc
				StringTokenizer stcc = new StringTokenizer(getCc(), " ,;", false);
				while (stcc.hasMoreTokens())
				{
					String cc = stcc.nextToken();
					if (cc != null && cc.length() > 0)
                        email.addCc(cc);
				}
				//	Attachment
				if (m_attachFile != null && m_attachFile.exists())
					email.addAttachment(m_attachFile);
				status = email.send();
				//
				if (m_user != null)
					new MUserMail(m_user, m_user.getAD_User_ID(), email).saveEx();
				if (email.isSentOK())
				{
					ADialog.info(0, this, "MessageSent");
					dispose();
				}
				else
					ADialog.error(0, this, "MessageNotSent", status);
			}
			else
				ADialog.error(0, this, "MessageNotSent", status);
			//
			confirmPanel.getOKButton().setEnabled(false);
			setCursor(Cursor.getDefaultCursor());
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();

	}	//	actionPerformed

	/**
	 * 	Vetoable Change - User selected 
	 *	@param evt
	 *	@throws PropertyVetoException
	 */
	public void vetoableChange (PropertyChangeEvent evt)
		throws PropertyVetoException
	{
		VLookup source = (VLookup) evt.getSource();
		Object value = evt.getNewValue();
		log.info("Value=" + value);
		if (source.equals(fUser)) {
			// fUser			
			if (value == null)
				fTo.setText("");
			if (value instanceof Integer)
			{
				int AD_User_ID = ((Integer)value).intValue();
				m_user = MUser.get(Env.getCtx(), AD_User_ID);
				fTo.setValue(m_user.getEMail());
			}
		} else {
			// fCcUser
			if (value == null)
				fCc.setText("");
			if (value instanceof Integer)
			{
				int AD_User_ID = ((Integer)value).intValue();
				m_ccuser = MUser.get(Env.getCtx(), AD_User_ID);
				fCc.setValue(m_ccuser.getEMail());
			}
		}
	}	//	vetoableChange

}	//	VEMailDialog