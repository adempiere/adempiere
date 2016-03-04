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
import org.adempiere.pos.service.driver.POSScalesDriver;
import org.adempiere.pos.service.driver.POSScalesDriverInterface;
import org.adempiere.webui.window.FDialog;
import org.compiere.swing.CButton;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import org.zkoss.zul.Timer;

/**
 * POS Scales Listener implement controller for POSScalesPanel
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 08/02/16.
 */
public class WPOSScalesListener implements EventListener, KeyListener {
	private static boolean active = true;
	private POSScalesPanelInterface pos;
	private POSScalesDriverInterface driver;

	public WPOSScalesListener(POSScalesPanelInterface pos)
	{
		this.pos = pos;
		this.driver = new POSScalesDriver(pos.getElectronicScales() , pos.getMeasureRequestCode());
	}

	public static void setActive(boolean active){
		WPOSScalesListener.active = active;
	}

	protected void doPerformAction(ActionEvent actionEvent) {
		if (((Timer)pos.getScalesTimer()).isRunning() && pos.getScalesTimer() != actionEvent.getSource())
		{
			if (actionEvent.getSource()  instanceof CButton) {
				CButton source = (CButton)  actionEvent.getSource();
				if(source.getName().equals("Ok")) {
					readMeasure();
					captureMeasure();
					return;
				}
			}
			return;
		}

		if (actionEvent.getSource() == pos.getScalesTimer()) {
			readMeasure();
			return;
		}
	}

	public void actionPerformed(ActionEvent actionEvent) {
		if(active){
			try {
					doPerformAction(actionEvent);
			}
			catch (AdempiereException exception) {
				FDialog.error(pos.getWindowNo() , exception.getLocalizedMessage());
				throw new AdempiereException(exception.getMessage());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(keyEvent.getComponent().toString().equals("Ok")) {
			readMeasure();
			captureMeasure();
			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {

	}

	private BigDecimal getMeasure()
	{
		return driver.getMeasure();
	}

	public void readMeasure()
	{
		pos.setScalesMeasure(getMeasure().toString() + " " + pos.getProductUOMSymbol());
	}

	public void captureMeasure()
	{
		pos.setQuantity(getMeasure());
		pos.updateLineTable();
		pos.refreshPanel();
		pos.changeViewPanel();
		pos.hideScales();
		pos.showKeyboard();
		pos.getMainFocus();
		((Timer)pos.getScalesTimer()).stop();
	}

	@Override
	public void onEvent(Event event) throws Exception {

	}
}