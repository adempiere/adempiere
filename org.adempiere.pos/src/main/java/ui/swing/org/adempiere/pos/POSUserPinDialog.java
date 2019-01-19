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
import org.compiere.swing.CPanel;
import org.compiere.util.Msg;

import javax.swing.*;
import java.awt.*;

/**
 * This Component allows display the POS password Pin
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/01/16.
 */
public class POSUserPinDialog {
    public static void show(VPOS pos) {
        JPasswordField passwordField = new JPasswordField(20);

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets.top = 4;
        constraints.insets.bottom = 4;
        CPanel passwordPanel = new CPanel(grid);
        passwordPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));
        constraints.anchor = GridBagConstraints.WEST;
        String msg = Msg.parseTranslation(pos.getCtx(), "@UserPIN@");
        passwordPanel.add(new JLabel(msg), constraints);
        constraints.gridy = 1;
        passwordPanel.add(passwordField, constraints);
        JOptionPane optionPane = new JOptionPane(passwordPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog(msg);
        dialog.addComponentListener(new java.awt.event.ComponentListener(){
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                passwordField.requestFocusInWindow();
            }
            @Override public void componentHidden(java.awt.event.ComponentEvent e) {}
            @Override public void componentResized(java.awt.event.ComponentEvent e) {}
            @Override public void componentMoved(java.awt.event.ComponentEvent e) {}
        });
        dialog.setVisible(true);

        int result = (Integer)optionPane.getValue();
        if (result == JOptionPane.OK_OPTION)
            pos.validateAndSetUserPin(passwordField.getPassword());
    }
}
