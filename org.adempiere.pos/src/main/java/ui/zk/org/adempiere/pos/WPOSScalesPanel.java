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

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;

/**
 * POS Scales Dialog allows get measure weight
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 08/02/16.
 */
public class WPOSScalesPanel {

	private WPOS pos;
	private Button buttonCaptureWeight;
	private Label labelWeight;

	/**	Main Panel					*/
	private Panel dialog;
	private Panel 			mainPanel;
	private Panel 			centerPanel;
	private Panel 			parameterPanel;
	//private BorderLayout 	mainLayout;
	private Grid parameterLayout;
	//private JScrollPane 	scrollPane;

	public WPOSScalesPanel(WPOS pos) {
		this.pos = pos;
		init();
	}

	public void init()
	{	//TODO :: replace to ZK code
		/*dialog = new Panel();
		dialog.setName(Msg.translate(pos.getCtx(), "@ElectronicScales@"));
		mainLayout = new BorderLayout();
		parameterLayout = new GridBagLayout();
		mainPanel = new Panel();
		parameterPanel = new Panel();

		centerPanel = new Panel();
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(700, 700));

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
		*/
	}

	private boolean dynInit() {
		//TODO :: replace to ZK code
		/*labelWeight = new Label("0.000", Label.CENTER);
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

		mainPanel.add(parameterPanel, BorderLayout.SOUTH);*/
		return true;
	}

	public void hidePanel()
	{
//		dialog.setVisible(false);
	}

	public void showPanel()
	{
		dialog.setVisible(true);
		buttonCaptureWeight.focus();
	}

	public void setMeasure(String measure)
	{
		labelWeight.setText(measure);
	}

	public Panel getPanel()
	{
		return dialog;
	}
}