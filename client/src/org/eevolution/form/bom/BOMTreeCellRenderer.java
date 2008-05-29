/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form.bom;

import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;

import org.compiere.util.Env;

import org.eevolution.form.tree.MapTreeCellRenderer;

/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class BOMTreeCellRenderer extends MapTreeCellRenderer {

	public BOMTreeCellRenderer(HashMap map) {
		
		super(map);
	}

    protected ImageIcon getIcon(Object value) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;

    	ImageIcon icon = null;
        if(node.isLeaf()) {
        	
        	icon = Env.getImageIcon("Product10.gif");
        }

        return icon;
    }
}	