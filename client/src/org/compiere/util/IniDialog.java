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
package org.compiere.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.swing.CPanel;

/**
 *  Init Dialog
 *
 *  @author     Jorg Janke
 *  @version    $Id: IniDialog.java,v 1.3 2006/10/12 00:58:32 jjanke Exp $
 */
public final class IniDialog extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1469122685638342569L;

	/**
	 *  Constructor
	 */
	public IniDialog()
	{
		super();
		try
		{
			jbInit();
			String where = "org/compiere/license.html";
			URL url = null;
			ClassLoader cl = getClass().getClassLoader();
			if (cl != null)	//	Bootstrap
				url = cl.getResource(where);
			if (url == null)
			{
				log.warning("No license in resource ");
				url = new URL("http://www.adempiere.org/license.htm");
			}
			if (url == null)
				cmd_reject();
			//
			licensePane.setPage(url);
			AdempierePLAF.showCenterScreen(this);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "init", ex);
			cmd_reject();
		}
	}   //  IniDialog

	/** Translation     */
	static ResourceBundle   s_res = ResourceBundle.getBundle("org.compiere.util.IniRes");
	private boolean         m_accept = false;
	/**	Logger	*/
	private static Logger	log	= Logger.getLogger (IniDialog.class.getName());
	

	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private JScrollPane scrollPane = new JScrollPane();
	private CPanel southPanel = new CPanel();
	private JButton bReject = AdempierePLAF.getCancelButton();
	private JButton bAccept = AdempierePLAF.getOKButton();
	private FlowLayout southLayout = new FlowLayout();
	private JLabel southLabel = new JLabel();
	private JEditorPane licensePane = new JEditorPane();

	/**
	 *  Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		setTitle("Adempiere - " + s_res.getString("Adempiere_License"));
		southLabel.setText(s_res.getString("Do_you_accept"));
		bReject.setText(s_res.getString("No"));
		bAccept.setText(s_res.getString("Yes_I_Understand"));
		//
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		//
		mainPanel.setLayout(mainLayout);
		bReject.setForeground(Color.red);
		bReject.addActionListener(this);
		bAccept.addActionListener(this);
		southPanel.setLayout(southLayout);
		southLayout.setAlignment(FlowLayout.RIGHT);
		licensePane.setEditable(false);
		licensePane.setContentType("text/html");
		scrollPane.setPreferredSize(new Dimension(700, 400));
		southPanel.add(southLabel, null);
		getContentPane().add(mainPanel);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(licensePane, null);
		mainPanel.add(southPanel,  BorderLayout.SOUTH);
		southPanel.add(bReject, null);
		southPanel.add(bAccept, null);
	}   //  jbInit

	/**
	 * ActionListener
	 * @param e event
	 */
	public final void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == bAccept)
			m_accept = true;
		dispose();
	}   //  actionPerformed

	/**
	 *  Dispose
	 */
	public final void dispose()
	{
		super.dispose();
		if (!m_accept)
			cmd_reject();
	}   //  dispose

	/**
	 *  Is Accepted
	 *  @return true if accepted
	 */
	public final boolean isAccepted()
	{
		return m_accept;
	}   //  isAccepted

	/**
	 *  Reject License
	 */
	public final void cmd_reject()
	{
		String info = "License rejected or expired";
		try
		{
			info = s_res.getString("License_rejected");
		}
		catch (Exception e)
		{
		}
		log.severe(info);
		System.exit(10);
	}   //  cmd_reject

	/**
	 *  Display License and exit if rejected
	 *  @return true if acceptes
	 */
	public static final boolean accept()
	{
		IniDialog id = new IniDialog();
		if (id.isAccepted())
		{
			log.info("License Accepted");
			return true;
		}
		System.exit(10);
		return false;       //  never executed.
	}   //  accpept

}   //  IniDialog 
