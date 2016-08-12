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
import org.compiere.apps.ADialog;
import org.compiere.grid.ed.VNumber;
import org.compiere.swing.CButton;
import org.compiere.util.Env;
import org.compiere.util.Msg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Component allows display the POS password Pin
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 26/01/16.
 */
public class POSUserPinListener implements ActionListener {

    private VPOS pos;

    POSUserPinListener(VPOS pos)
    {
        this.pos = pos;
    }

    private static boolean active = true;

    public static void setActive(boolean active){
        POSUserPinListener.active = active;
    }

    protected void doPerformAction(ActionEvent actionEvent) {
        if (!"KeyEvent".equals(actionEvent.getActionCommand().toString())
        ||  !actionEvent.getActionCommand().toString().equals("Cancel"))
            return;

        Object objectSource = actionEvent.getSource();
        VNumber number = null;
        CButton button = null;

        if (objectSource instanceof VNumber)
            number = (VNumber) objectSource;

        if (objectSource instanceof CButton)
            button = (CButton) objectSource;

        if (!pos.isRequiredPIN())
            return;

        if (
               //Number field validation
               (
                number != null && (Msg.translate(Env.getCtx(), "Discount").equals(number.getName()) || Msg.translate(Env.getCtx(), "PriceActual").equals(number.getName())
               )
           ||
               //Button validation
               (
               button != null && ("Cancel".equals(button.getName()))
               )
           ))
        {
           POSUserPinDialog.show(pos);
    }

    }

    @Override
    public final void actionPerformed(ActionEvent actionEvent){

        if(active){
                try {

                    if (actionEvent.getSource() == pos.getUserPinTimer()) {
                        pos.invalidateUserPin();
                        pos.getUserPinTimer().stop();
                        return;
                    }

                    if (pos.getUserPinTimer().isRunning())
                        return;

                    doPerformAction(actionEvent);
                }
                catch (AdempiereException exception) {
                    ADialog.error(pos.getWindowNo(), pos.getFrame(), exception.getLocalizedMessage());
                    throw new AdempiereException(exception.getMessage());
                }
        }
    }
}
