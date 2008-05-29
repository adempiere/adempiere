/******************************************************************************
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form.crp;

import java.awt.Component;
import java.awt.Graphics;
import java.util.Date;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.compiere.model.MResource;
import org.compiere.util.Env;

import org.eevolution.form.tree.MapTreeCellRenderer;

import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderNode;

/**
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class DiagramTreeCellRenderer extends MapTreeCellRenderer {
	
	public DiagramTreeCellRenderer(HashMap map) {

		super(map);
	}
	
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    	
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        String name = (String)getMapping(value);
        ImageIcon icon = getIcon(value);
       if(isNotAvailable(name)) {
        	
	    	final int x1 = getFontMetrics(getFont()).stringWidth(name)+icon.getIconWidth();
        	JLabel l = new JLabel(name.substring(1, name.length()-1), icon, JLabel.LEFT) {
        		
        	    public void paint(Graphics g) {

        	    	super.paint(g);
        	    	
        	    	int y = getFont().getSize()/2;
        	    
        	    	g.drawLine(0, y, x1, y);
        	    }
        	};
        	l.setFont(getFont());
        	return l;
        }
        return this;
    }
    
    private boolean isNotAvailable(String value) {
    	
    	return value.startsWith("{") && value.endsWith("}");
    }
   
    protected ImageIcon getIcon(Object value) {
    	
        ImageIcon icon = null;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        if(node.getUserObject() instanceof MResource) {
        
        }
        else if(node.getUserObject() instanceof Date) {
        	
        	icon = Env.getImageIcon("Calendar10.gif");
        }
        else if(node.getUserObject() instanceof MPPOrder) {
        	
        }
        else if(node.getUserObject() instanceof MPPOrderNode) {
        	
        }

        return icon;
    }
}	