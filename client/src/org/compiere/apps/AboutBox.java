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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.compiere.Adempiere;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextArea;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	About Dialog
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: AboutBox.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public final class AboutBox extends CDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5126987147443121045L;

	/**
	 *	Constructor for modal about dialog
	 *  @param parent parent
	 */
	public AboutBox(JFrame parent)
	{
		super (parent, true);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		//
		labelVersion.setText(Adempiere.getVersion());
		labelCopyright.setText(Adempiere.COPYRIGHT);
		infoArea.setText(CLogMgt.getInfo(null).toString());
		//  create 5 pt border
		Dimension d = imageControl.getPreferredSize();
		imageControl.setPreferredSize(new Dimension(d.width+10, d.height+10));
		//
		AEnv.positionCenterWindow(parent, this);
	}	//	AWindow_AboutBox

	private CPanel panel = new CPanel();
	private CPanel mainPanel = new CPanel();
	private JLabel imageControl = new JLabel();
	private JLabel labelHeading = new JLabel();
	private JLabel labelVersion = new JLabel();
	private JLabel labelCopyright = new JLabel();
	private JLabel labelDescription = new JLabel();
	private BorderLayout panelLayout = new BorderLayout();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel northPanel = new CPanel();
	private CPanel headerPanel = new CPanel();
	private GridLayout headerLayout = new GridLayout();
	private CTextArea infoArea = new CTextArea();
	private BorderLayout northLayout = new BorderLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(false);

	/**
	 *	Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		this.setTitle(Msg.translate(Env.getCtx(), "About"));
		//
		setResizable(false);
		labelHeading.setFont(new java.awt.Font("Dialog", 1, 14));
		labelHeading.setHorizontalAlignment(SwingConstants.CENTER);
		labelHeading.setHorizontalTextPosition(SwingConstants.CENTER);
		labelHeading.setText(" Smart ERP & CRM Business Solution ");
		labelVersion.setHorizontalAlignment(SwingConstants.CENTER);
		labelVersion.setHorizontalTextPosition(SwingConstants.CENTER);
		labelVersion.setText(".");
		labelCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		labelCopyright.setHorizontalTextPosition(SwingConstants.CENTER);
		labelCopyright.setText(".");
		labelDescription.setForeground(Color.blue);
		labelDescription.setHorizontalAlignment(SwingConstants.CENTER);
		labelDescription.setHorizontalTextPosition(SwingConstants.CENTER);
		labelDescription.setText(Adempiere.getURL());
		//
		imageControl.setFont(new java.awt.Font("Serif", 2, 10));
		imageControl.setForeground(Color.blue);
		imageControl.setAlignmentX((float) 0.5);
		imageControl.setHorizontalAlignment(SwingConstants.CENTER);
		imageControl.setHorizontalTextPosition(SwingConstants.CENTER);
		imageControl.setIcon(Adempiere.getImageIconLogo());
		imageControl.setText(Adempiere.getSubtitle());
		imageControl.setVerticalTextPosition(SwingConstants.BOTTOM);
		//
		mainPanel.setLayout(mainLayout);
		mainLayout.setHgap(10);
		mainLayout.setVgap(10);
		northPanel.setLayout(northLayout);
		northLayout.setHgap(10);
		northLayout.setVgap(10);
		panel.setLayout(panelLayout);
		panelLayout.setHgap(10);
		panelLayout.setVgap(10);
		headerPanel.setLayout(headerLayout);
		headerLayout.setColumns(1);
		headerLayout.setRows(4);
		//
		infoArea.setReadWrite(false);

		this.getContentPane().add(panel, null);
		panel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(imageControl, BorderLayout.WEST);
		northPanel.add(headerPanel, BorderLayout.CENTER);
		headerPanel.add(labelHeading, null);
		headerPanel.add(labelCopyright, null);
		headerPanel.add(labelVersion, null);
		headerPanel.add(labelDescription, null);
		panel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(infoArea, BorderLayout.CENTER);
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
	}   //  jbInit

	
	/**
	 *	ActionListener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(ConfirmPanel.A_OK))
			dispose();
	}   //  actionPerformed
}	//	AboutBox
