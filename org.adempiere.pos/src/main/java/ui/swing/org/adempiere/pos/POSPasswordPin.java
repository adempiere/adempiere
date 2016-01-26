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

import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * Component allows display the POS password Pin
 *  eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 26/01/16.
 */
public class POSPasswordPin extends CPanel  {

    CLabel passwordLabel = new CLabel(Msg.parseTranslation(Env.getCtx(),"@UserPIN@"));
    JPasswordField passwordField = new JPasswordField(10);
    String userPIN = "";

    /**
     Default Constructor
     */
    public POSPasswordPin()
    {
        add(passwordLabel);
        add(passwordField);

        Object[] options = {Util.cleanAmp(Msg.getMsg(Env.getCtx(), "OK")), Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Cancel"))};
        int option = JOptionPane.showOptionDialog(null, this, Msg.parseTranslation(Env.getCtx(),"@UserPIN@"),
                JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            char[] password = passwordField.getPassword();
            userPIN = new String(password);
        }
        else
            userPIN = "";
    }

    //Get typed User Pin
    public String getUserPin()
    {
        return this.userPIN;
    }
}
