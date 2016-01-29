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
import org.compiere.swing.CPanel;
import org.compiere.util.Msg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Component allows display the POS password Pin
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 26/01/16.
 */
public class POSUserPinListener implements ActionListener {

    private VPOS pos;
    private javax.swing.Timer userPinTimer = null;

    POSUserPinListener(VPOS pos)
    {

        this.pos = pos;
    }

    private static boolean active = true;
    public static void setActive(boolean active){
        POSUserPinListener.active = active;
    }

    public void setTimer(javax.swing.Timer timer)
    {
        this.userPinTimer = timer;
    }

    protected void doPerformAction(ActionEvent e)
    {
        if(e.getSource()==userPinTimer) {
            pos.setIsCorrectUserPin(false);
            userPinTimer.stop();
            return;
        }

        if (userPinTimer.isRunning())
            return;


        if (!pos.isRequiredPIN())
            return;

        JPasswordField passwordField = new JPasswordField(20);
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.top = 4;
        constraints.insets.bottom = 4;
        CPanel passwordPanel = new CPanel(grid);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        constraints.anchor = GridBagConstraints.WEST;
        String msg = Msg.parseTranslation(pos.getCtx() , "@UserPIN@");
        passwordPanel.add(new JLabel(msg),constraints);
        constraints.gridy=1;
        passwordPanel.add(passwordField,constraints);
        int result = JOptionPane.showConfirmDialog(null, passwordPanel,msg,JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
            pos.setIsCorrectUserPin(passwordField.getPassword());

    }

    @Override
    public final void actionPerformed(ActionEvent event){

        if(active){
            try {
                doPerformAction(event);
            }
            catch (AdempiereException exception) {
                ADialog.error(pos.getWindowNo(), pos.getFrame(), exception.getLocalizedMessage());
                throw new AdempiereException(exception.getMessage());
            }
        }
    }
}
