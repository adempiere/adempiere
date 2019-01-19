/** ****************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 * ****************************************************************************/

package org.adempiere.pos;

import org.compiere.apps.AppsAction;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.Msg;

import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

/**
 * POS Scales Dialog allows get measure weight
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 08/02/16.
 */
public class POSScalesPanel {

	private VPOS pos;
	private CButton buttonCaptureWeight;
	private CLabel labelWeight;

	/**	Main Panel					*/
	private CPanel			dialog;
	private CPanel 			mainPanel;
	private CPanel 			centerPanel;
	private CPanel 			parameterPanel;
	private BorderLayout 	mainLayout;
	private GridBagLayout 	parameterLayout;
	private JScrollPane 	scrollPane;

	public POSScalesPanel(VPOS pos) {
		this.pos = pos;
		init();
	}

	public void init()
	{
		dialog = new CPanel();
		dialog.setName(Msg.translate(pos.getCtx(), "@ElectronicScales@"));
		mainLayout = new BorderLayout();
		parameterLayout = new GridBagLayout();
		mainPanel = new CPanel();
		parameterPanel = new CPanel();

		centerPanel = new CPanel();
		scrollPane = new JScrollPane();
		Dimension screenSize = pos.getSize();
		int widthSize = (screenSize.width * 40) / 100;
		int heightSize = (screenSize.height * 70) / 100;

		scrollPane.setPreferredSize(new Dimension(widthSize, heightSize));

		mainPanel.setLayout(mainLayout);
		parameterPanel.setLayout(parameterLayout);
		centerPanel.setLayout(parameterLayout);
		mainPanel.add(scrollPane);
		scrollPane.getViewport().add(centerPanel);

		dialog.add(mainPanel, BorderLayout.CENTER);
		if (!dynInit()) {
			dialog.setVisible(false);
			return;
		}
	}

	private boolean dynInit() {
		labelWeight = new CLabel("0.000", CLabel.CENTER);
		labelWeight.setFont(new Font("Arial", Font.BOLD, 50));
		centerPanel.setLayout(parameterLayout);
		centerPanel.add(labelWeight);

		AppsAction action = new AppsAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),false);
		action.setDelegate(pos.getScalesListener());
		buttonCaptureWeight = (CButton)action.getButton();
		buttonCaptureWeight.setPreferredSize(new Dimension(pos.getButtonSize(), pos.getButtonSize()));
		buttonCaptureWeight.setFocusable(true);
		buttonCaptureWeight.addKeyListener(pos.getScalesListener());
		parameterPanel.add(buttonCaptureWeight, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE,new Insets(0, 0, 0, 0), 0, 0));

		mainPanel.add(parameterPanel, BorderLayout.SOUTH);
		return true;
	}

	public void hidePanel()
	{
		dialog.setVisible(false);
	}

	public void showPanel()
	{
		dialog.setVisible(true);
		buttonCaptureWeight.requestFocus();
	}

	public void setMeasure(String measure)
	{
		labelWeight.setText(measure);
	}

	public CPanel getPanel()
	{
		return dialog;
	}
}