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

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.service.driver.POSScalesDriverInterface;
import org.adempiere.pos.service.driver.POSScalesDriver;
import org.compiere.apps.ADialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

/**
 * POS Scales Listener implement controller for POSScalesPanel
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 08/02/16.
 */
public class POSScalesListener implements ActionListener , KeyListener {
	private static boolean active = true;
	private VPOS pos;
	private POSScalesDriverInterface driver;

	POSScalesListener(VPOS pos)
	{
		this.pos = pos;
		this.driver = new POSScalesDriver(pos.getElectronicScales() , pos.getMeasureRequestCode());
	}

	public static void setActive(boolean active){
		POSScalesListener.active = active;
	}

	protected void doPerformAction(ActionEvent actionEvent) {
		if(actionEvent.getActionCommand().toString().equals("Calculator"))
			captureWeight();
	}

	public void actionPerformed(ActionEvent actionEvent) {
		if(active){
			try {
					if (actionEvent.getSource() == pos.getScalesTimer()) {
						pos.getScalesTimer().stop();
						return;
					}

					if (pos.getScalesTimer().isRunning())
						return;

					doPerformAction(actionEvent);
			}
			catch (AdempiereException exception) {
				ADialog.error(pos.getWindowNo(), pos.getFrame(), exception.getLocalizedMessage());
				throw new AdempiereException(exception.getMessage());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getComponent().toString().equals("Calculator"))
			captureWeight();
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}

	private BigDecimal getMeasure()
	{
		return driver.getMeasure();
	}

	public void captureWeight()
	{
		pos.setQuantity(getMeasure());
		pos.refreshPanel();
		pos.hideScales();
		pos.showKeyboard();
		pos.getScalesTimer().stop();
	}
}